package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.model.LocationPoint;
import com.example.dripchipsystem.service.impl.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationEndpoint extends AbstractEndpoint<LocationPoint, LocationService> {
    public LocationEndpoint(LocationService service) {
        super(service);
    }
}
