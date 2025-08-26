package com.example.springboot.dao;

import com.example.springboot.entity.Teacher;
import com.example.springboot.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TeacherDao extends Mapper<Teacher> {

    @Select("select * from teacher where `Tno` = #{Tno}")
    Teacher findByTno(@Param("Tno") String Tno);

    //分页样式
    List<Teacher> findBySearch(@Param("search") Teacher search);
}
