package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Learning;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.Teacher;
import com.example.springboot.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentService studentService;

    @GetMapping("/alldata")

    public Result getDate(){
        return Result.success(studentService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result studentRegister(@RequestBody Student student){
        return Result.success(studentService.studentRegister(student));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Sno}")
    public Result delete(@PathVariable String Sno) {
        studentService.delete(Sno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result studentEdit(@RequestBody Student student){
        return Result.success(studentService.studentEdit(student));
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Student search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(studentService.findPage(search, pageNum, pageSize));
    }
}
