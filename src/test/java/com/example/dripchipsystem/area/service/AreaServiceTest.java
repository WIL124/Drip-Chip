//package com.example.dripchipsystem.area.service;
//
//import com.example.dripchipsystem.account.dto.AccountDto;
//import com.example.dripchipsystem.account.service.AccountService;
//import com.example.dripchipsystem.animal.dto.AnimalDto;
//import com.example.dripchipsystem.animal.model.Gender;
//import com.example.dripchipsystem.animal.model.LifeStatus;
//import com.example.dripchipsystem.animal.service.AnimalService;
//import com.example.dripchipsystem.animalType.dto.AnimalTypeDto;
//import com.example.dripchipsystem.animalType.service.AnimalTypeService;
//import com.example.dripchipsystem.animalVisitedLocation.dto.AnimalVisitedLocationDto;
//import com.example.dripchipsystem.animalVisitedLocation.service.AnimalVisitedLocationsService;
//import com.example.dripchipsystem.area.dto.AreaAnalytics;
//import com.example.dripchipsystem.area.dto.AreaDto;
//import com.example.dripchipsystem.area.dto.AreaPointDto;
//import com.example.dripchipsystem.locationPoint.dto.LocationPointDto;
//import com.example.dripchipsystem.locationPoint.service.LocationService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.OffsetDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
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
//    @Autowired
//    AccountService accountService;
//    @Autowired
//    AnimalVisitedLocationsService visitedLocationsService;
//    @Test
//    void positiveGetAnalyticsTestsAll() {
//        ArrayList<AreaPointDto> list = new ArrayList<>(){};
//        list.add(new AreaPointDto(-90.0, 0.0));
//        list.add(new AreaPointDto(-90.0, -15.0));
//        list.add(new AreaPointDto(-75.0, -15.0));
//        list.add(new AreaPointDto(-75.0, 0.0));
//        AreaDto areaDto = areaService.create(new AreaDto(null, "rectangle", list));
//
//        AnimalTypeDto animalType32 = animalTypeService.create(new AnimalTypeDto(null, "type1"));
//        AnimalTypeDto animalType37 = animalTypeService.create(new AnimalTypeDto(null, "type2"));
//        AnimalTypeDto animalType41 = animalTypeService.create(new AnimalTypeDto(null, "type3"));
//        AnimalTypeDto animalType43 = animalTypeService.create(new AnimalTypeDto(null, "type4"));
//
//        LocationPointDto point57 = locationService.create(new LocationPointDto(null, -80.0, -10.0));
//        LocationPointDto point59 = locationService.create(new LocationPointDto(null, 0.0, 0.0));
//        LocationPointDto point61 = locationService.create(new LocationPointDto(null, -75.0, 0.0));
//        AccountDto accountDto = accountService.create(new AccountDto(null, "firstname", "lastname", "email@mail.com", "password", "ADMIN"));
//
//        AnimalDto animalDto46 = animalService.create(new AnimalDto(null, List.of(animalType32.getId(), animalType37.getId()), 1, 2,3, Gender.FEMALE, LifeStatus.ALIVE, OffsetDateTime.now(), accountDto.getId(), point57.getId(), new ArrayList<>(),null));
//        AnimalDto animalDto47 = animalService.create(new AnimalDto(null, List.of(animalType37.getId()), 1, 2,3, Gender.FEMALE, LifeStatus.ALIVE, OffsetDateTime.now(), accountDto.getId(), point57.getId(), new ArrayList<>(),null));
//        AnimalDto animalDto48 = animalService.create(new AnimalDto(null, List.of(animalType32.getId(), animalType41.getId(), animalType43.getId()), 1, 2,3, Gender.FEMALE, LifeStatus.ALIVE, OffsetDateTime.now(), accountDto.getId(), point59.getId(), new ArrayList<>(),null));
//
//
//        AnimalVisitedLocationDto visit37 = visitedLocationsService.create(animalDto47.getId(), point59.getId());
//        AnimalVisitedLocationDto visit38 = visitedLocationsService.create(animalDto48.getId(), point61.getId());
//        AreaAnalytics analytics = areaService.getAreaAnalytics(areaDto.getId(), LocalDate.MIN, LocalDate.MAX);
//
//        assertEquals(2L, analytics.getTotalQuantityAnimals());
//        assertEquals(1L, analytics.getTotalAnimalsArrived());
//        assertEquals(1L, analytics.getTotalAnimalsGone());
//    }
//}