package com.example.dripchipsystem.area.model;

import com.example.dripchipsystem.common.model.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "area")
@Table(name = "area")
@Data
public class Area extends AbstractEntity {
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "area")
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
