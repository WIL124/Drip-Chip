package com.example.dripchipsystem.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Animal extends AbstractEntity {
    private float weight;
    private float length;
    private float height;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private LifeStatus lifeStatus = LifeStatus.ALIVE;
    @CreationTimestamp
    private OffsetDateTime chippingDateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chipper_id")
    @ToString.Exclude
    private Account chipper;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chipping_location_id")
    @ToString.Exclude
    private LocationPoint chippingLocation;
    @ManyToMany(targetEntity = AnimalVisitedLocation.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "animal_visited_locations",
            joinColumns = {@JoinColumn(name = "animal_id", table = "animal_visited_locations")},
            inverseJoinColumns = {@JoinColumn(name = "id", table = "animal_visited_locations")}
    )
//    @JoinColumn(name = "visited_locations_id", table = "animal_visited_locations")
    @ToString.Exclude
    private List<AnimalVisitedLocation> visitedLocations;
    private OffsetDateTime deathDateTime;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "animal_animal_type",
            joinColumns = {@JoinColumn(name = "animal_id")},
            inverseJoinColumns = {@JoinColumn(name = "animal_type_id")}
    )
    @ToString.Exclude
    private List<AnimalType> animalTypes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Animal animal = (Animal) o;
        return getId() != null && Objects.equals(getId(), animal.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
