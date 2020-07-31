package com.oyeRickshaw.ratingSystem.repositories;

import com.oyeRickshaw.ratingSystem.entity.enums.RideStatus;
import com.oyeRickshaw.ratingSystem.entity.models.Driver;
import com.oyeRickshaw.ratingSystem.entity.models.Passenger;
import com.oyeRickshaw.ratingSystem.entity.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sourabhrana
 */

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {

    List<Ride> findByDriverOrPassengerAndRideStatus(Driver driver, Passenger passenger, RideStatus rideStatus);

    List<Ride> findByDriverAndPassengerAndRideStatus(Driver driver, Passenger passenger, RideStatus rideStatus);

    List<Ride> findByRideStatus(RideStatus rideStatus);
}
