package com.oyeRickshaw.ratingSystem.controller;

import com.oyeRickshaw.ratingSystem.entity.enums.RideStatus;
import com.oyeRickshaw.ratingSystem.entity.models.Ride;
import com.oyeRickshaw.ratingSystem.repositories.RideRepository;
import com.oyeRickshaw.ratingSystem.request.CreateRideRequest;
import com.oyeRickshaw.ratingSystem.request.RatingRequest;
import com.oyeRickshaw.ratingSystem.response.*;
import com.oyeRickshaw.ratingSystem.service.RatingsFetcherService;
import com.oyeRickshaw.ratingSystem.service.RideCreationService;
import com.oyeRickshaw.ratingSystem.service.RideRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sourabhrana
 */

@RestController
@RequestMapping(value = "/ratingSystem", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingSystemController {

    private static final Logger log = LoggerFactory.getLogger(RatingSystemController.class);

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideCreationService rideCreationService;

    @Autowired
    private RideRatingService rideRatingService;

    @Autowired
    private RatingsFetcherService ratingsFetcherService;

    @RequestMapping(value = "/createRide", method = RequestMethod.POST)
    public BaseApiResponse<BaseResponse> createRide(@RequestBody CreateRideRequest request) {
        BaseResponse response = rideCreationService.createRide(request);
        if (response.isSuccess())
            return new BaseSuccessResponse<>(response);
        else
            return new BaseFailureResponse<>(response);
    }

    @RequestMapping(value = "/rateDriver", method = RequestMethod.POST)
    public BaseApiResponse<BaseResponse> rateDriver(@RequestBody RatingRequest request) {
        BaseResponse response = rideRatingService.rateRide(request, false);
        if (response.isSuccess())
            return new BaseSuccessResponse<>(response);
        else
            return new BaseFailureResponse<>(response);
    }

    @RequestMapping(value = "/ratePassenger", method = RequestMethod.POST)
    public BaseApiResponse<BaseResponse> ratePassenger(@RequestBody RatingRequest request) {
        BaseResponse response = rideRatingService.rateRide(request, true);
        if (response.isSuccess())
            return new BaseSuccessResponse<>(response);
        else
            return new BaseFailureResponse<>(response);
    }

    @RequestMapping(value = "/getAggregateDriverRating", method = RequestMethod.GET)
    public BaseApiResponse<GenericRatingResponse> getAggregateDriverRating(@RequestParam Integer driverId) {
        GenericRatingResponse response = ratingsFetcherService.getAggregateDriverRating(driverId);
        if (response.isSuccess())
            return new BaseSuccessResponse<>(response);
        else
            return new BaseFailureResponse<>(response);
    }

    @RequestMapping(value = "/getAggregatePassengerRating", method = RequestMethod.GET)
    public BaseApiResponse<GenericRatingResponse> rateDriver(@RequestParam Integer passengerId) {
        GenericRatingResponse response = ratingsFetcherService.getAggregatePassengerRating(passengerId);
        if (response.isSuccess())
            return new BaseSuccessResponse<>(response);
        else
            return new BaseFailureResponse<>(response);
    }

    @RequestMapping(value = "/fetchRides", method = RequestMethod.GET)
    public List<Ride> getAllRides(@RequestParam String status) {
        return rideRepository.findByRideStatus(RideStatus.valueOf(status));
    }
}
