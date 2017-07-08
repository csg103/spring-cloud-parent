package com.csg.application.service;

import com.csg.application.entity.CourseVo;

import java.util.List;

/*
* 描述 ： 
* 作者 ： csg
* 时间 ： 2017-07-05
*/
public interface CourseService
{
     List<CourseVo> findCourse();
}
