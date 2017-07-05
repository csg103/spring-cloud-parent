package com.csg.application.dao;

import com.csg.application.entity.CourseVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
* 描述 ： 
* 作者 ： csg
* 时间 ： 2017-07-05
*/
public interface CourseDAO extends JpaRepository<CourseVO, String>, JpaSpecificationExecutor {


}
