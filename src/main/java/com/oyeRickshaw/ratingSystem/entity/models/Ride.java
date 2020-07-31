package com.oyeRickshaw.ratingSystem.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oyeRickshaw.ratingSystem.entity.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author sourabhrana
 */

@Entity
@Table(name = "ride")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ride extends GenericAbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Integer rideId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "driver")
    @JsonBackReference
    private Driver driver;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "passenger")
    @JsonBackReference
    private Passenger passenger;

    @Column(name = "ride_status", nullable = false)
    private RideStatus rideStatus;

}
