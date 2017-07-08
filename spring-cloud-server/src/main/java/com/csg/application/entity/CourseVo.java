package com.csg.application.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
* 描述 ： 课程VO
* 作者 ： csg
* 时间 ： 2017-07-05
*/
@Data
@Entity(name = "t_Course")
public class CourseVo {
    //课程ID
    @Id
    @Column(name="c_course_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //课程名
    private String c_course_name;
    //讲师名
    private String c_teacher_id;
    //课程简介
    private String c_course_description;
    //课程分类
    private String c_course_type_id;
    //学习人数
    private Integer  n_course_num;
    //课程创建时间
    private Date d_course_time;
    //课程创建人
    private String c_courser;


}
