package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    //    查询所有数据
    @GetMapping("/alldata")
    public Result getData(){

        return Result.success(adminService.GetAll());

    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result adminRegister(@RequestBody Admin admin){
        return Result.success(adminService.adminRegister(admin));
    }


    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.delete(id);
        return Result.success();
    }


    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result adminEdit(@RequestBody Admin admin){
        return Result.success(adminService.adminEdit(admin));
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Admin search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(adminService.findPage(search, pageNum, pageSize));
    }
}