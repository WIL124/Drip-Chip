//package com.example.dripchipsystem.area.service;
//
//import com.example.dripchipsystem.animal.dto.AnimalDto;
//import com.example.dripchipsystem.animal.service.AnimalService;
//import com.example.dripchipsystem.animalType.dto.AnimalTypeDto;
//import com.example.dripchipsystem.animalType.model.AnimalType;
//import com.example.dripchipsystem.animalType.service.AnimalTypeService;
//import com.example.dripchipsystem.area.dto.AreaDto;
//import com.example.dripchipsystem.area.dto.AreaPointDto;
//import com.example.dripchipsystem.locationPoint.dto.LocationPointDto;
//import com.example.dripchipsystem.locationPoint.service.LocationService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class AreaServiceTest {
//    @Autowired
//    AreaService areaService;
//    @Autowired
//    LocationService locationService;
//    @Autowired
//    AnimalTypeService animalTypeService;
//    @Autowired
//    AnimalService animalService;
//    @Test
//    void create() {
//        ArrayList<AreaPointDto> list = new ArrayList<>(){};
//        list.add(new AreaPointDto(-90.0, 0.0));
//        list.add(new AreaPointDto(-90.0, -15.0));
//        list.add(new AreaPointDto(-75.0, -15.0));
//        list.add(new AreaPointDto(-75.0, 0.0));
//        AreaDto areaDto = areaService.create(new AreaDto(null, "rectangle", list));
//
//        LocationPointDto inner = locationService.create(new LocationPointDto(null, -80.0, 10.0));
//        LocationPointDto outer = locationService.create(new LocationPointDto(null, 0.0, 0.0));
//
//        AnimalTypeDto animalType1 = animalTypeService.create(new AnimalTypeDto(null, "type1"));
//        AnimalTypeDto animalType2 = animalTypeService.create(new AnimalTypeDto(null, "type2"));
//
////        AnimalDto animalDto = animalService.create(new AnimalDto(null, List.of()))
//        areaService.getAreaAnalytics(areaDto.getId(), LocalDate.MIN, LocalDate.MAX);
//
//        System.out.println();
//    }
//}