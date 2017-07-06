package com.csg.application.dto;

import lombok.Data;

import java.util.Date;

/*
* 描述 ： 课程VO
* 作者 ： csg
* 时间 ： 2017-07-05
*/
@Data
public class CourseDto {
    //课程ID
    private String c_course_id;
    //课程名
    private String c_course_name;
    //讲师名
    private String c_teacher_id;
    //课程简介
    private String c_course_description;
    //课程分类
    private String c_course_type;
    //学习人数
    private int n_course_num;
    //课程创建时间
    private Date d_course_time;
    //课程创建人
    private String c_create_course_opt;


}
