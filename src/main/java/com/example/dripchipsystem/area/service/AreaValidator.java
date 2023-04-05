package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaPointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaValidator {
    public boolean validateArea(List<AreaPointDto> areaPoints) {
        areaPoints.add(areaPoints.get(0));
        if (!validate(areaPoints.stream()
                .map(point -> new Coordinate(point.getLatitude(), point.getLongitude()))
                .toList()
                .toArray(new Coordinate[0]))){
            return false;
        }
        areaPoints.remove( areaPoints.size() -1);
        return true;
    }

    private boolean validate(Coordinate[] coordinates) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Polygon polygonFromCoordinates = geometryFactory.createPolygon(coordinates);
        LinearRing linearRing = polygonFromCoordinates.getExteriorRing();
        return linearRing.isValid();
    }
}
