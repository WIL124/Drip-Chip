package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AnimalDto;
import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.service.impl.AnimalService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/animals")
@Validated
public class AnimalEndpoint extends AbstractEndpoint<Animal, AnimalService> {
    public AnimalEndpoint(AnimalService service) {
        super(service);
    }

    @GetMapping("/search")
    List<Animal> search(@RequestParam(name = "startDateTime", required = false) LocalDateTime startDateTime,
                        @RequestParam(name = "endDateTime", required = false) LocalDateTime endDateTime,
                        @RequestParam(name = "chipperId", required = false) Integer chipperId,
                        @RequestParam(name = "chippingLocationId", required = false) Long chippingLocationId,
                        @RequestParam(name = "lifeStatus", required = false) String lifeStatus,
                        @RequestParam(name = "gender", required = false) String gender,
                        @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) int from,
                        @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) int size){
        return service.search(startDateTime, endDateTime,chipperId, chippingLocationId, lifeStatus, gender, from, size);
    }

    @Override
    protected Dto<Animal> toDto(Animal entity) {
        return AnimalDto.builder()
                .id(entity.getId())
                .build();
    }
}
