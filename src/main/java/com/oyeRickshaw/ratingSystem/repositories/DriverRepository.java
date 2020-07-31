package com.oyeRickshaw.ratingSystem.repositories;

import com.oyeRickshaw.ratingSystem.entity.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sourabhrana
 */

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
