package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.impl.LocationPointDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.endpoint.CommonEndpoint;
import com.example.dripchipsystem.service.impl.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationEndpoint
        extends AbstractEndpoint<LocationService, LocationPointDto>
        implements CommonEndpoint<LocationPointDto> {
    public LocationEndpoint(LocationService service) {
        super(service);
    }
}
