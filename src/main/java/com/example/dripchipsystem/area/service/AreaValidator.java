package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaPointDto;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.model.AreaPoint;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaValidator {
    private final GeometryFactory geometryFactory = new GeometryFactory();

    public boolean isValidAreaPoints(List<AreaPointDto> areaPoints) {
        areaPoints.add(areaPoints.get(0));
        Coordinate[] areaCoordinates = getCoordinates(areaPoints);
        if (!isValidAreaPoints(areaCoordinates)) {
            return false;
        }
        areaPoints.remove(areaPoints.size() - 1);
        return true;
    }

    public boolean hasIntersections(List<AreaPointDto> areaPoints, List<Area> existsAreas) {
        areaPoints.add(areaPoints.get(0));
        Coordinate[] coordinates = getCoordinates(areaPoints);
        areaPoints.remove(areaPoints.size() - 1);
        Polygon polygon = geometryFactory.createPolygon(coordinates);
        List<Polygon> polygonList = existsAreas.stream()
                .map(area -> {
                    List<AreaPoint> points = area.getAreaPoints();
                    points.add(points.get(0));
                    Coordinate[] coord = getCoordinatesFromPoints(points);
                    points.remove(points.size() -1 );
                    return coord;
                })
                .map(geometryFactory::createPolygon)
                .toList();
        return polygonList.stream().anyMatch(entryPolygon ->
                entryPolygon.within(polygon) ||
                        entryPolygon.contains(polygon) ||
                        entryPolygon.crosses(polygon) ||
                        entryPolygon.overlaps(polygon));
    }

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

    private Coordinate[] getCoordinatesFromPoints(List<AreaPoint> areaPoints) {
        return areaPoints.stream()
                .map(point -> new Coordinate(point.getX(), point.getY()))
                .toList()
                .toArray(new Coordinate[0]);
    }
}
