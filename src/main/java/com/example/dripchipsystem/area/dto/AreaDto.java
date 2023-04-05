package com.example.dripchipsystem.area.dto;

import com.example.dripchipsystem.common.dto.AbstractDto;
import com.example.dripchipsystem.locationPoint.dto.LocationPointDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AreaDto extends AbstractDto {
    @NotNull @NotBlank
    private String name;
    @Valid @NotEmpty @NotNull
    private List<LocationPointDto> areaPoints;
}
