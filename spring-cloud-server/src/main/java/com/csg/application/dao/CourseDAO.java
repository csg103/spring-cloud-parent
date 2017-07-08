package com.csg.application.dao;

import com.csg.application.entity.CourseVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* 描述 ： 
* 作者 ： csg
* 时间 ： 2017-07-05
*/
public interface CourseDAO extends JpaRepository<CourseVo, String>, JpaSpecificationExecutor {

    //利用原生的SQL进行查询操作
    @Query(value = "select * from t_course where c_course_id=?1", nativeQuery = true)
    @Modifying
    List<CourseVo> findAllByC_course_id1(String id);

}
