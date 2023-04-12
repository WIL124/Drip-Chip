package com.example.dripchipsystem.locationPoint.model;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
import com.example.dripchipsystem.common.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class LocationPoint extends AbstractEntity {
    private Double latitude;
    private Double longitude;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "chipping_location_id")
    @ToString.Exclude
    private List<Animal> chippedAnimals;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "visited_locations_id")
    @ToString.Exclude
    private List<AnimalVisitedLocation> visitedLocations;
    @Builder
    public LocationPoint(Long id, Double latitude, Double longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
