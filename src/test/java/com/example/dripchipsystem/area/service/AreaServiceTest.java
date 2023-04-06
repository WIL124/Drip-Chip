//package com.example.dripchipsystem.area.service;
//
//import com.example.dripchipsystem.area.dto.AreaDto;
//import com.example.dripchipsystem.area.dto.AreaPointDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class AreaServiceTest {
//    @Autowired
//    AreaService areaService;
//    @Test
//    void create() {
//        ArrayList<AreaPointDto> list = new ArrayList<>(){};
//        list.add(new AreaPointDto(-82.0, -180.0));
//        list.add(new AreaPointDto(-81.0, -180.0));
//        list.add(new AreaPointDto(-81.0, -179.0));
//        AreaDto areaDto = areaService.create(new AreaDto(null, "best1", list));
//        System.out.println(" ");
//    }
//}