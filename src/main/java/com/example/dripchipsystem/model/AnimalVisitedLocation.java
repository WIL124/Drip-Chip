package com.example.dripchipsystem.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animal_visited_locations")
@Table(name = "animal_visited_locations")
public class AnimalVisitedLocation extends AbstractEntity {
    @CreationTimestamp
    private LocalDateTime dateTimeOfVisitLocationPoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visited_locations_id", table = "animal_visited_locations")
    @ToString.Exclude
    private LocationPoint locationPoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", table = "animal_visited_locations")
    @ToString.Exclude
    private Animal animal;

    @Builder
    public AnimalVisitedLocation(Long id, LocalDateTime dateTimeOfVisitLocationPoint, LocationPoint locationPoint, Animal animal) {
        super(id);
        this.dateTimeOfVisitLocationPoint = dateTimeOfVisitLocationPoint;
        this.locationPoint = locationPoint;
        this.animal = animal;
    }
}
