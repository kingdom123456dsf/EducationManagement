package com.example.springboot.dao;

import com.example.springboot.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//数据访问层简单接口,mapper访问复杂接口
@Repository
public interface AdminDao extends Mapper<Admin> {
//继承方法，简单的增删改查则不需要再自己去写方法了
    List<Admin> getAll();

    //    根据用户名查询，查询跟参数相同的用户名 返回一个用户
    @Select("select * from admin where `userName` = #{userName}")
    Admin findByName(@Param("userName") String userName);

    @Select("select * from admin where `id` = #{id}")
    Admin findById(@Param("id")Integer id);

    //分页样式
    List<Admin> findBySearch(@Param("search") Admin search);
}
