package com.example.dripchipsystem.area.model;

import com.example.dripchipsystem.common.model.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity(name = "area_point")
public class AreaPoint extends AbstractEntity {
    private double longitude;
    private double latitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @Builder
    public AreaPoint(Long id, double longitude, double latitude) {
        super(id);
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
