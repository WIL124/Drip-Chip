package com.example.dripchipsystem.dto.childs;

import com.example.dripchipsystem.dto.AbstractDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AnimalTypeDto extends AbstractDto {
    @NotNull
    @NotBlank
    private String type;
    @Builder
    public AnimalTypeDto(@DecimalMin(value = "0", inclusive = false) Long id, String type) {
        super(id);
        this.type = type;
    }
}
