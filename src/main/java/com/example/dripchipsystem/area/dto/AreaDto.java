package com.example.dripchipsystem.area.dto;

import com.example.dripchipsystem.common.dto.AbstractDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class AreaDto extends AbstractDto {
    @NotNull
    @NotBlank
    private String name;
    @Valid
    @NotEmpty
    @NotNull
    private List<AreaPointDto> areaPoints;

    @Builder
    public AreaDto(Long id ,String name, List<AreaPointDto> areaPoints) {
        super(id);
        this.name = name;
        this.areaPoints = areaPoints;
    }
}
