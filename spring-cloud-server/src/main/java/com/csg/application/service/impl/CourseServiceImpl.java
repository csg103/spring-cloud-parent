package com.csg.application.service.impl;

import com.csg.application.dao.CourseDAO;
import com.csg.application.entity.CourseVO;
import com.csg.application.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 描述 ： 课程实现类service 
* 作者 ： csg
* 时间 ： 2017-07-05
*/
@Service
@AllArgsConstructor
@Log4j2
public class CourseServiceImpl implements CourseService{

    private CourseDAO courseDAO;

    @Override
    public List<CourseVO> findCourse() {
        log.info("查询课程——————————————");

        return courseDAO.findAll();
    }
}
