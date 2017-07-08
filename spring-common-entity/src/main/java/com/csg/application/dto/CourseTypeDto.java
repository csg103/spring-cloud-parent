package com.csg.application.dto;

import lombok.Data;

/*
* 描述 ： 课程VO
* 作者 ： csg
* 时间 ： 2017-07-05
*/
@Data
public class CourseTypeDto {

    //课程类型ID
    private Long id;
    //课程类型
    private String c_course_name;
    //父ID
    private String c_father_id;
    //是否是顶级节点
    private boolean c_top_flag;


}
