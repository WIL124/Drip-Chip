package com.example.dripchipsystem.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    private LocalDateTime chippingDateTime;
    @ManyToOne
    @JoinColumn(name = "chipper_id")
    private Account chipper;
    @ManyToOne
    @JoinColumn(name = "chipping_location_id")
    private LocationPoint chippingLocation;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "animal_visited_locations",
//            joinColumns = {@JoinColumn(name = "animal_id")},
//            inverseJoinColumns = {@JoinColumn(name = "location_point_id")}
//    )
    @ToString.Exclude
    private List<AnimalVisitedLocation> visitedLocations;
    private LocalDateTime deathDateTime;
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
