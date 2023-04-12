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
//        list.add(new AreaPointDto(14.0, -151.0));
//        list.add(new AreaPointDto(14.0, -164.0));
//        list.add(new AreaPointDto(3.0, -157.5));
//        AreaDto areaDto = areaService.create(new AreaDto(null, "best12", list));
////        ArrayList<AreaPointDto> list2 = new ArrayList<>(){};
////        list2.add(new AreaPointDto(14.0, -166.0));
////        list2.add(new AreaPointDto(14.0, -179.0));
////        list2.add(new AreaPointDto(3.0, -172.5));
////        areaService.create(new AreaDto(null, "second", list2));
////        System.out.println();
//    }
//}