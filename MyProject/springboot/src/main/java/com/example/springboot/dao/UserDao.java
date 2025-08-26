package com.example.springboot.dao;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//数据访问层简单接口,mapper访问复杂接口
@Repository
public interface UserDao extends Mapper<User> {
//继承方法，简单的增删改查则不需要再自己去写方法了
    List<User> getAll();

    //    根据用户名查询，查询跟参数相同的用户名 返回一个用户
    @Select("select * from user where `userName` = #{userName}")
    User findByUsername(@Param("userName") String userName);
    //Param注解判断userName是否相等

    @Select("select * from user where `userName` = #{userName}")
    User findByName(@Param("userName") String userName);

    @Select("select * from user where `id` = #{id}")
    User findById(@Param("id")Integer id);
}
