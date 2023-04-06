package com.example.dripchipsystem.area.model;

import com.example.dripchipsystem.common.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "area_point")
@Table(name = "area_point")
public class AreaPoint extends AbstractEntity {
    private double x;
    private double y;
    @Column(name = "area_id")
    private Long areaId;

    @Builder
    public AreaPoint(Long id, double x, double y, Long areaId) {
        super(id);
        this.x = x;
        this.y = y;
        this.areaId = areaId;
    }
}
