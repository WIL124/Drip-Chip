package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaPointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaValidator {
    private final GeometryFactory geometryFactory = new GeometryFactory();

    public boolean isValidAreaPoints(List<AreaPointDto> areaPoints) {
        //self intersection
        areaPoints.add(areaPoints.get(0));
        Coordinate[] areaCoordinates = getCoordinates(areaPoints);
        if (!isValidAreaPoints(areaCoordinates)) {
            return false;
        }
        areaPoints.remove(areaPoints.size() - 1);
        return true;
    }

//    public boolean hasIntersections(Area target, List<Area> existsAreas) {
//        geometryFactory.
//    }

    private boolean isValidAreaPoints(Coordinate[] coordinates) {
        LinearRing linearRing;
        try {
            linearRing = geometryFactory.createLinearRing(coordinates);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return linearRing.isValid();
    }

    private Coordinate[] getCoordinates(List<AreaPointDto> areaPoints) {
        return areaPoints.stream()
                .map(point -> new Coordinate(point.getX(), point.getY()))
                .toList()
                .toArray(new Coordinate[0]);
    }
}
