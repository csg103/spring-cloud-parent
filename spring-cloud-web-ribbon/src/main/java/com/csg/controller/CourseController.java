package com.csg.controller;

import com.csg.busi.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public List add() {
      List list =   courseService.findCourse();
        return list;
    }
}