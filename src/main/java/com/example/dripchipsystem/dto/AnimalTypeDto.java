package com.example.dripchipsystem.dto;

import com.example.dripchipsystem.model.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalTypeDto implements Dto<AnimalType> {
    private Long id;
    private String type;
}
