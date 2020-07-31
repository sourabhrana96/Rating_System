package com.oyeRickshaw.ratingSystem.repositories;

import com.oyeRickshaw.ratingSystem.entity.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sourabhrana
 */

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
