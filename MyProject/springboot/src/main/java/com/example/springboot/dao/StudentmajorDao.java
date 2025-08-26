package com.example.springboot.dao;

import com.example.springboot.entity.Studentmajor;
import com.example.springboot.entity.Studentmajor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StudentmajorDao extends Mapper<Studentmajor> {

    @Select("select * from faculties where `Sno` = #{Sno}")
    Studentmajor findBySno(@Param("Sno") String Sno);

    //分页样式
    List<Studentmajor> findBySearch(@Param("search") Studentmajor search);
}
