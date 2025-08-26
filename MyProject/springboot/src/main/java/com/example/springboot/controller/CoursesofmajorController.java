package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Coursesofmajor;
import com.example.springboot.entity.Learning;
import com.example.springboot.service.CoursesofmajorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/coursesofmajor")
public class CoursesofmajorController {

    @Resource
    CoursesofmajorService coursesofmajorService;

    @GetMapping("/alldata")
    public Result getDate(){
        return Result.success(coursesofmajorService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result coursesofmajorRegister(@RequestBody Coursesofmajor coursesofmajor){
        return Result.success(coursesofmajorService.coursesofmajorRegister(coursesofmajor));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Cno}")
    public Result delete(@PathVariable String Cno) {
        coursesofmajorService.delete(Cno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result coursesofmajorEdit(@RequestBody Coursesofmajor coursesofmajor){

        return Result.success(coursesofmajorService.coursesofmajorEdit(coursesofmajor));

    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Coursesofmajor search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(coursesofmajorService.findPage(search, pageNum, pageSize));
    }
}
