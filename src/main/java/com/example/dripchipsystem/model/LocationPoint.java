package com.example.dripchipsystem.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocationPoint extends AbstractEntity {
    private double latitude;
    private double longitude;
}
