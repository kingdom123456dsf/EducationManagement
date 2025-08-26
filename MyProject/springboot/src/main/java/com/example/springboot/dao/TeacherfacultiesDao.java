package com.example.springboot.dao;

import com.example.springboot.entity.Teacherfaculties;
import com.example.springboot.entity.Teacherfaculties;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TeacherfacultiesDao extends Mapper<Teacherfaculties> {

    @Select("select * from major where `Tno` = #{Tno}")
    Teacherfaculties findByTno(@Param("Tno") String Tno);

    //分页样式
    List<Teacherfaculties> findBySearch(@Param("search") Teacherfaculties search);
}
