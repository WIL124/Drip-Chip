package com.example.dripchipsystem.area.model;

import com.example.dripchipsystem.common.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "area_point")
@Table(name = "area_point")
public class AreaPoint extends AbstractEntity {
    private double longitude;
    private double latitude;
    private Integer areaId;

    @Builder
    public AreaPoint(Long id, double longitude, double latitude, Integer areaId) {
        super(id);
        this.longitude = longitude;
        this.latitude = latitude;
        this.areaId = areaId;
    }
}
