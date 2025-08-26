package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;



    //    查询所有数据
    @GetMapping("/alldata")
    public Result getData(){

        return Result.success(userService.GetAll());

    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result userRegister(@RequestBody User user){
        return Result.success(userService.userRegister(user));
    }


    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success();
    }


    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result userEdit(@RequestBody User user){

        return Result.success(userService.userEdit(user));

    }

}