package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.service.AdminService;
import com.example.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    AdminService adminService;

    @Resource
    UserService userService;



    /**
     * 注册接口
     */

    @PostMapping("/register")
    public Result accountRegister(@RequestBody Account account){
        Integer role = account.getRole();
        Account login = new Account(); // 定义一个Account类的login,用于返回给前端
        if ( 2 == role){ //这里只有用户注册 如果有其他角色也需要注册，也是同样的逻辑
            User user = new User();
            BeanUtils.copyProperties(account,user);
            login = userService.userRegister(user);
        }
        return Result.success(login);
    }


    /**
     * 用户登录接口
     */
    /*@PostMapping("/login")
    public Result AcountLogin(@RequestBody Account account){
        Integer role = account.getRole();
        Account login = new Account(); // 定义一个Account类的login,用于返回给前端
        if ( 1 == role){
            Admin admin = new Admin(); //创建一个管理员类admin
            BeanUtils.copyProperties(account,admin); //把父类的属性拷贝到admin
            login = adminService.adminLogin(admin); //调用服务层的登录逻辑
        }
        if ( 2 == role){
            User user = new User();
            BeanUtils.copyProperties(account,user);//把父类的属性拷贝到user
            login = userService.userLogin(user);//调用服务层的登录逻辑
        }
        return Result.success(login);
    }*/

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public Result AcountLogin(@RequestBody Account account){
        Integer role = account.getRole();
        Account login = new Account(); // 定义一个Account类的login,用于返回给前端
        if ( 1 == role){
            Admin admin = new Admin(); //创建一个管理员类admin
            BeanUtils.copyProperties(account,admin); //把父类的属性拷贝到admin
            login = adminService.adminLogin(admin); //调用服务层的登录逻辑
        }
        if ( 2 == role){
            User user = new User();
            BeanUtils.copyProperties(account,user);
            login = userService.userLogin(user);
        }
        //        生成token
        String token = JwtTokenUtils.genToken(login.getId() + "-" + login.getRole(), login.getPassword());
        //        创建一个键值对map集合，把token和user塞进去，返回给前端
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", login);
        return Result.success(map); //装好数据后，把map结合返回给前端
    }

}
