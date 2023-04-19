package com.example.dripchipsystem.location.service;

import ch.hsr.geohash.GeoHash;
import com.example.dripchipsystem.common.service.AbstractService;
import com.example.dripchipsystem.common.service.CommonService;
import com.example.dripchipsystem.location.dto.LocationPointDto;
import com.example.dripchipsystem.location.mapper.LocationPointMapper;
import com.example.dripchipsystem.location.model.LocationPoint;
import com.example.dripchipsystem.location.repository.LocationRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LocationService
        extends AbstractService<LocationPoint, LocationRepository, LocationPointMapper, LocationPointDto>
        implements CommonService<LocationPointDto> {
    public LocationService(LocationRepository repository, LocationPointMapper locationPointMapper) {
        super(repository, locationPointMapper);
    }

    public Long getIdByCoordinates(Double latitude, Double longitude) {
        return repository.findByLatitudeAndLongitude(latitude, longitude)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getId();
    }

    public String getGeoHash(Double latitude, Double longitude) {
        repository.findByLatitudeAndLongitude(latitude, longitude)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return GeoHash.withCharacterPrecision(latitude, longitude, 12).toBase32();
    }

    public String getGeoHashV2(Double latitude, Double longitude) {
        byte[] encoded = getGeoHash(latitude, longitude).getBytes(StandardCharsets.UTF_8);
        return new String(Base64.encodeBase64(encoded));
    }

    public String getGeoHashV3(Double latitude, Double longitude) throws NoSuchAlgorithmException {
        byte[] encoded = getGeoHash(latitude, longitude).getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5Encoded = md.digest(encoded);
        byte[] revertedMd5 = new byte[md5Encoded.length];
        for (int i = 0; i < md5Encoded.length; i++) {
            revertedMd5[md5Encoded.length - i - 1] = md5Encoded[i];
        }
        return new String(Base64.encodeBase64(revertedMd5));
    }
}
