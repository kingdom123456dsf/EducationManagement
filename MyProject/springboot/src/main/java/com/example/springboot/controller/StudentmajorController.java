package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Learning;
import com.example.springboot.entity.Studentmajor;
import com.example.springboot.service.StudentmajorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/studentmajor")
public class StudentmajorController {

    @Resource
    StudentmajorService studentmajorService;

    @GetMapping("/alldata")

    public Result getDate()
    {
        return Result.success(studentmajorService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result studentmajorRegister(@RequestBody Studentmajor studentmajor){
        return Result.success(studentmajorService.studentmajorRegister(studentmajor));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Sno}")
    public Result delete(@PathVariable String Sno) {
        studentmajorService.delete(Sno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result studentmajorEdit(@RequestBody Studentmajor studentmajor){

        return Result.success(studentmajorService.studentmajorEdit(studentmajor));

    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Studentmajor search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(studentmajorService.findPage(search, pageNum, pageSize));
    }
}
