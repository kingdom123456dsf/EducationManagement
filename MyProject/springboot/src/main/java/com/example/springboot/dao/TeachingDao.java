package com.example.springboot.dao;

import com.example.springboot.entity.Teaching;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TeachingDao extends Mapper<Teaching> {

    @Select("select * from teacher where `Tno` = #{Tno}")
    Teaching findByTno(@Param("Tno") String Tno);

    //分页样式
    List<Teaching> findBySearch(@Param("search") Teaching search);
}
