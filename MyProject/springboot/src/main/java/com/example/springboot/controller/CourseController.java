package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Course;
import com.example.springboot.entity.Faculties;
import com.example.springboot.entity.Course;
import com.example.springboot.entity.Teacher;
import com.example.springboot.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    CourseService courseService;

    @GetMapping("/alldata")

    public Result getDate()
    {
        return Result.success(courseService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result courseRegister(@RequestBody Course course){
        return Result.success(courseService.courseRegister(course));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Cno}")
    public Result delete(@PathVariable String Cno) {
        courseService.delete(Cno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result courseEdit(@RequestBody Course course){
        return Result.success(courseService.courseEdit(course));
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Course search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(courseService.findPage(search, pageNum, pageSize));
    }
}
