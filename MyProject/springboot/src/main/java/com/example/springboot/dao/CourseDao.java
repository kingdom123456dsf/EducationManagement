package com.example.springboot.dao;

import com.example.springboot.entity.Course;
import com.example.springboot.entity.Faculties;
import com.example.springboot.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CourseDao extends Mapper<Course> {

    @Select("select * from course where `Cno` = #{Cno}")
    Course findByCno(@Param("Cno") String Cno);

    //分页样式
    List<Course> findBySearch(@Param("search") Course search);
}
