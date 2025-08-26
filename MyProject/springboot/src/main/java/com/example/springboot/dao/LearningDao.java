package com.example.springboot.dao;

import com.example.springboot.entity.Learning;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LearningDao extends Mapper<Learning> {

    @Select("select * from teacher where `Sno` = #{Sno}")
    Learning findBySno(@Param("Sno") String Sno);

    //分页样式
    List<Learning> findBySearch(@Param("search") Learning search);
}
