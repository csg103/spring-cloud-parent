package com.csg.application.service.impl;

import com.csg.application.dao.CourseDAO;
import com.csg.application.entity.CourseVo;
import com.csg.application.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
* 描述 ： 课程实现类service 
* 作者 ： csg
* 时间 ： 2017-07-05
*/
@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class CourseServiceImpl implements CourseService{

    private CourseDAO courseDAO;

    @Override
    public List<CourseVo> findCourse() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(0, 29, sort);
        log.info("查询课程——————————————");
        List listall =courseDAO.findAll();
        List list = courseDAO.findAllByC_course_id1("2");
        CourseVo CourseVo =new CourseVo();
        CourseVo.setC_course_name("JAVA");
        courseDAO.save(CourseVo);
        Page page=  courseDAO.findAll(pageable);
        return page.getContent();
    }
}
