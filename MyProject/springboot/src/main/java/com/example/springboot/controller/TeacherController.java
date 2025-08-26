package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Teacher;
import com.example.springboot.entity.Teacher;
import com.example.springboot.service.TeacherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    TeacherService teacherService;

    @GetMapping("/alldata")

    public Result getDate(){
        return Result.success(teacherService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result teacherRegister(@RequestBody Teacher teacher){
        return Result.success(teacherService.teacherRegister(teacher));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Tno}")
    public Result delete(@PathVariable String Tno) {
        teacherService.delete(Tno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result teacherEdit(@RequestBody Teacher teacher){
        return Result.success(teacherService.teacherEdit(teacher));
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Teacher search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(teacherService.findPage(search, pageNum, pageSize));
    }
}
