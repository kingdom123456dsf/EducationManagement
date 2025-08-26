package com.example.springboot.dao;

import com.example.springboot.entity.Student;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StudentDao extends Mapper<Student> {

    @Select("select * from student where `Sno` = #{Sno}")
    Student findBySno(@Param("Sno") String Sno);

    //分页样式
    List<Student> findBySearch(@Param("search") Student search);
}
