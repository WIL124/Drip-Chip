package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.locationPoint.dto.LocationPointDto;
import org.springframework.stereotype.Component;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.List;

@Component
public class AreaValidator {
    public boolean validateArea(List<LocationPointDto> areaPoints) {
        Path2D path = new Path2D.Double();
//        if (areaPoints.size() < 3) return false;
        LocationPointDto firstPoint = areaPoints.remove(0);
        path.moveTo(firstPoint.getLatitude(), firstPoint.getLongitude());
        for (LocationPointDto point : areaPoints) {
            path.lineTo(point.getLatitude(), point.getLatitude());
        }
        path.closePath();
        if (!validateSelfIntersects(path)) {
            return false;
        }
        return true;
    }

    private boolean validateSelfIntersects(Path2D path2D) {
        PathIterator pathIterator = path2D.getPathIterator(null);
        double[] coordinates = new double[6];
        GeneralPath generalPath = new GeneralPath();
        generalPath.
        for (: pathIterator) {

        }
        return true;
    }
}
