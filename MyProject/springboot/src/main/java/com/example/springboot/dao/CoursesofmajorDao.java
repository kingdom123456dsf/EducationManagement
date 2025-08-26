package com.example.springboot.dao;

import com.example.springboot.entity.Coursesofmajor;
import com.example.springboot.entity.Coursesofmajor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CoursesofmajorDao extends Mapper<Coursesofmajor> {

    @Select("select * from major where `Cno` = #{Cno}")
    Coursesofmajor findByCno(@Param("Cno") String Cno);

    //分页样式
    List<Coursesofmajor> findBySearch(@Param("search") Coursesofmajor search);
}
