package com.example.dripchipsystem.dto.childs;

import com.example.dripchipsystem.dto.AbstractDto;
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
