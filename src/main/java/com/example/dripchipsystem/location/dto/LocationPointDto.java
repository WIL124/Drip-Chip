package com.example.dripchipsystem.location.dto;

import com.example.dripchipsystem.common.dto.AbstractDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class LocationPointDto extends AbstractDto {
    @NotNull
    @Min(-90)
    @Max(90)
    @JsonProperty("latitude")
    private Double latitude;
    @NotNull
    @Min(-180)
    @Max(180)
    private Double longitude;

    @Builder
    public LocationPointDto(Long id, Double latitude, Double longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
