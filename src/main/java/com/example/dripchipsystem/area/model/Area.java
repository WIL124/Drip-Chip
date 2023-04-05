package com.example.dripchipsystem.area.model;

import com.example.dripchipsystem.common.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "area")
@Table(name = "area")
@Data
@AllArgsConstructor
public class Area extends AbstractEntity {
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "area_id")
    private List<AreaPoint> areaPoints;

    @Builder
    public Area(Long id, String name, List<AreaPoint> areaPoints) {
        super(id);
        this.name = name;
        this.areaPoints = areaPoints;
    }

    public Area() {
    }
}
