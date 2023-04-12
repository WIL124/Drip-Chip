package com.example.dripchipsystem.area.endpoint;

import com.example.dripchipsystem.area.dto.AreaAnalytics;
import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.service.AreaService;
import com.example.dripchipsystem.common.endpoint.AbstractEndpoint;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@RestController
@RequestMapping("/areas")
@Validated
public class AreaEndpoint extends AbstractEndpoint<AreaService, AreaDto> {
    public AreaEndpoint(AreaService service) {
        super(service);
    }

    @GetMapping("/{areaId}")
    public AreaDto get(@PathVariable @NotNull @Positive Long areaId) {
        return service.getEntity(areaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaDto create(@RequestBody @NotNull AreaDto areaDto) {
        return service.create(areaDto);
    }

    @PutMapping("/{areaId}")
    public AreaDto update(@RequestBody @NotNull AreaDto areaDto,
                          @PathVariable @NotNull @Positive Long areaId) {
        return service.updateEntity(areaId, areaDto);
    }

    @DeleteMapping("/{areaId}")
    public void delete(@PathVariable @NotNull @Positive Long areaId) {
        service.delete(areaId);
    }

    @GetMapping("/{areaId}/analytics")
    public AreaAnalytics getAnalytics(@PathVariable @NotNull @Positive Long areaId,
                                      @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                      @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return service.getAreaAnalytics(areaId, startDate, endDate);
    }
}
