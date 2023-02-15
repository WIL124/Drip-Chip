package com.example.dripchipsystem.model;

import lombok.*;

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
    private LocalDateTime dateTimeOfVisitLocationPoint;
    @ManyToOne
    @JoinColumn(name = "location_point_id", table = "animal_visited_locations")
    private LocationPoint locationPoint;
}
