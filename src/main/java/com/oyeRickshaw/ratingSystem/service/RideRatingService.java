package com.oyeRickshaw.ratingSystem.service;

import com.oyeRickshaw.ratingSystem.entity.enums.RideStatus;
import com.oyeRickshaw.ratingSystem.entity.models.Driver;
import com.oyeRickshaw.ratingSystem.entity.models.Passenger;
import com.oyeRickshaw.ratingSystem.entity.models.Ride;
import com.oyeRickshaw.ratingSystem.repositories.DriverRepository;
import com.oyeRickshaw.ratingSystem.repositories.PassengerRepository;
import com.oyeRickshaw.ratingSystem.repositories.RideRepository;
import com.oyeRickshaw.ratingSystem.request.RatingRequest;
import com.oyeRickshaw.ratingSystem.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author sourabhrana
 */

@Service
public class RideRatingService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public BaseResponse rateRide(RatingRequest ratingRequest, boolean ratePassenger) {
        Optional<Driver> driverEntity = driverRepository.findById(ratingRequest.getDriverId());
        Optional<Passenger> passengerEntity = passengerRepository.findById(ratingRequest.getPassengerId());
        if (driverEntity.isPresent() && passengerEntity.isPresent()) {
            List<Ride> rides = rideRepository.findByDriverAndPassengerAndRideStatus(driverEntity.get(), passengerEntity.get(), RideStatus.COMPLETED);
            if (rides != null && rides.size() > 0) {
                if (ratePassenger) {
                    //update passenger rating
                    updatePassengerRating(passengerEntity, ratingRequest);
                } else {
                    //rate driver rating
                    updateDriverRating(driverEntity, ratingRequest);
                }
                return createBaseResponse(true, "Ride is rated successfully");
            } else {
                return createBaseResponse(false, "No ride exist or ride is not completed");
            }
        } else {
            return createBaseResponse(false, "Please enter correct driver id or passenger id");
        }

    }

    private void updateDriverRating(Optional<Driver> entity, RatingRequest ratingRequest) {
        Driver driver = entity.get();
        Integer originalRatingCount = driver.getRatingCount();
        Float originalAggregateRating = driver.getDriverRating();
        Integer updatedRatingCount = originalRatingCount + 1;
        Float updatedAggregateRating = (originalAggregateRating + ratingRequest.getRating()) / updatedRatingCount;
        driver.setRatingCount(updatedRatingCount);
        driver.setDriverRating(updatedAggregateRating);
        driverRepository.save(driver);
    }

    private void updatePassengerRating(Optional<Passenger> entity, RatingRequest ratingRequest) {

        Passenger passenger = entity.get();
        Integer originalRatingCount = passenger.getRatingCount();
        Float originalAggregateRating = passenger.getPassengerRating();
        Integer updatedRatingCount = originalRatingCount + 1;
        Float updatedAggregateRating = (originalAggregateRating + ratingRequest.getRating()) / updatedRatingCount;
        passenger.setRatingCount(updatedRatingCount);
        passenger.setPassengerRating(updatedAggregateRating);
        passengerRepository.save(passenger);
    }

    private BaseResponse createBaseResponse(boolean success, String message) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(success);
        response.setMessage(message);
        return response;
    }

}
