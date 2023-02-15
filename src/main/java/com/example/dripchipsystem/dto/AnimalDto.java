package com.example.dripchipsystem.dto;

import com.example.dripchipsystem.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalDto implements Dto<Animal> {
    private Long id;
}
