package com.example.springboot.dao;

import com.example.springboot.entity.Faculties;
import com.example.springboot.entity.Major;
import com.example.springboot.entity.Major;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MajorDao extends Mapper<Major> {

    @Select("select * from major where `Mno` = #{Mno}")
    Major findByMno(@Param("Mno") String Mno);

    //分页样式
    List<Major> findBySearch(@Param("search") Major search);
}
