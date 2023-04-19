package com.example.dripchipsystem.visit.model;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.common.model.AbstractEntity;
import com.example.dripchipsystem.location.model.LocationPoint;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "animal_visited_locations")
@Table(name = "animal_visited_locations")
public class AnimalVisitedLocation extends AbstractEntity {
    @CreationTimestamp
    private Timestamp dateTimeOfVisitLocationPoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visited_locations_id", table = "animal_visited_locations")
    @ToString.Exclude
    private LocationPoint locationPoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", table = "animal_visited_locations")
    @ToString.Exclude
    private Animal animal;

    public AnimalVisitedLocation(LocationPoint locationPoint, Animal animal) {
        this.locationPoint = locationPoint;
        this.animal = animal;
    }

    @Builder
    public AnimalVisitedLocation(Long id, Timestamp dateTimeOfVisitLocationPoint, LocationPoint locationPoint, Animal animal) {
        super(id);
        this.dateTimeOfVisitLocationPoint = dateTimeOfVisitLocationPoint;
        this.locationPoint = locationPoint;
        this.animal = animal;
    }
}
