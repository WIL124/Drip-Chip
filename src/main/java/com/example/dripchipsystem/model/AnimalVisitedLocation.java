package com.example.dripchipsystem.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animal_visited_locations")
public class AnimalVisitedLocation extends AbstractEntity {
    @CreationTimestamp
    private LocalDateTime dateTimeOfVisitLocationPoint;
    @ManyToOne
    @JoinColumn(name = "visited_locations_id", table = "animal_visited_locations")
    private LocationPoint locationPoint;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Builder
    public AnimalVisitedLocation(Long id, LocalDateTime dateTimeOfVisitLocationPoint, LocationPoint locationPoint, Animal animal) {
        super(id);
        this.dateTimeOfVisitLocationPoint = dateTimeOfVisitLocationPoint;
        this.locationPoint = locationPoint;
        this.animal = animal;
    }
}
