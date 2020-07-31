package com.oyeRickshaw.ratingSystem.service;

import com.oyeRickshaw.ratingSystem.entity.enums.RideStatus;
import com.oyeRickshaw.ratingSystem.entity.models.Driver;
import com.oyeRickshaw.ratingSystem.entity.models.Passenger;
import com.oyeRickshaw.ratingSystem.entity.models.Ride;
import com.oyeRickshaw.ratingSystem.repositories.DriverRepository;
import com.oyeRickshaw.ratingSystem.repositories.PassengerRepository;
import com.oyeRickshaw.ratingSystem.repositories.RideRepository;
import com.oyeRickshaw.ratingSystem.request.CreateRideRequest;
import com.oyeRickshaw.ratingSystem.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author sourabhrana
 */

@Service
public class RideCreationService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public BaseResponse createRide(CreateRideRequest createRideRequest) {
        Optional<Driver> driverEntity = driverRepository.findById(createRideRequest.getDriverId());
        Optional<Passenger> passengerEntity = passengerRepository.findById(createRideRequest.getPassengerId());
        if (driverEntity.isPresent() && passengerEntity.isPresent()) {
            List<Ride> rideList = rideRepository.findByDriverOrPassengerAndRideStatus(driverEntity.get(), passengerEntity.get(), RideStatus.ON_GOING);
            if (rideList != null && rideList.size() > 0)
                return createBaseResponse(false, "Ride is on-going, please create another ride after this ride completed");
            else {

                Ride newRideEntity = new Ride();
                newRideEntity.setDriver(driverEntity.get());
                newRideEntity.setPassenger(passengerEntity.get());
                newRideEntity.setRideStatus(RideStatus.ON_GOING);
                rideRepository.save(newRideEntity);
                return createBaseResponse(true, "Ride created successfully");
            }
        } else {
            return createBaseResponse(false, "Invalid driver or passenger");
        }
    }


    private BaseResponse createBaseResponse(boolean success, String message) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(success);
        response.setMessage(message);
        return response;
    }

}
