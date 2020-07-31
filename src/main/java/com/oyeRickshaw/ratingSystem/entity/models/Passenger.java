package com.oyeRickshaw.ratingSystem.entity.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author sourabhrana
 */

@Entity
@Table(name = "passenger")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger extends GenericAbstractEntity<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    @Column(name = "ratings", columnDefinition = "float default 0.0")
    private Float passengerRating;

    @Column(name = "rating_count", columnDefinition = "integer default 0")
    private Integer ratingCount;

    @OneToMany(mappedBy = "passenger", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Ride> rideSet;
}
