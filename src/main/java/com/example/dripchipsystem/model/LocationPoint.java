package com.example.dripchipsystem.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class LocationPoint extends AbstractEntity {
    private Double latitude;
    private Double longitude;

    @Builder
    public LocationPoint(Long id, Double latitude, Double longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
