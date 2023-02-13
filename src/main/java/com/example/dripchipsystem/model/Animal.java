package com.example.dripchipsystem.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Animal extends AbstractEntity {
    private float weight;
    private float length;
    private float height;
    private Gender gender;
    private LifeStatus lifeStatus;
    private LocalDateTime chippingDateTime;
    private int chipperId;
    private long chippingLocationId;
    //    List<Long> visitedLocations;
    LocalDateTime deathDateTime;
//    List<AnimalType> animalTypes;

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
