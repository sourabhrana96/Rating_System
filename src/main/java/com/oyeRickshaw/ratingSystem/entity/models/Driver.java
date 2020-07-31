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
@Table(name = "driver")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Driver extends GenericAbstractEntity<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "ratings", columnDefinition = "float default 0.0")
    private Float driverRating;

    @Column(name = "rating_count", columnDefinition = "integer default 0")
    private Integer ratingCount;

    @OneToMany(mappedBy = "driver", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Ride> rides;

}
