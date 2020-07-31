package com.oyeRickshaw.ratingSystem.service;

import com.oyeRickshaw.ratingSystem.entity.models.Driver;
import com.oyeRickshaw.ratingSystem.entity.models.Passenger;
import com.oyeRickshaw.ratingSystem.repositories.DriverRepository;
import com.oyeRickshaw.ratingSystem.repositories.PassengerRepository;
import com.oyeRickshaw.ratingSystem.response.GenericRatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author sourabhrana
 */

@Service
public class RatingsFetcherService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public GenericRatingResponse getAggregateDriverRating(Integer driverId) {
        Optional<Driver> driverEntity = driverRepository.findById(driverId);
        if (driverEntity.isPresent()) {
            Driver driver = driverEntity.get();
            return createRatingResponse(true, "Success", driver.getDriverRating(), driver.getRatingCount());
        } else {
            return createRatingResponse(false, "Driver not found. Please enter correct driver id", null, null);
        }
    }

    public GenericRatingResponse getAggregatePassengerRating(Integer passengerId) {
        Optional<Passenger> passengerEntity = passengerRepository.findById(passengerId);
        if (passengerEntity.isPresent()) {
            Passenger passenger = passengerEntity.get();
            return createRatingResponse(true, "Success", passenger.getPassengerRating(), passenger.getRatingCount());
        } else {
            return createRatingResponse(false, "Driver not found. Please enter correct driver id", null, null);
        }
    }

    private GenericRatingResponse createRatingResponse(boolean success, String message, Float aggregateRating, Integer ratingCount) {
        GenericRatingResponse response = new GenericRatingResponse();
        response.setSuccess(success);
        response.setMessage(message);
        response.setAggregateRating(aggregateRating);
        response.setTotalRatingCounts(ratingCount);
        return response;
    }
}
