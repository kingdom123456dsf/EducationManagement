package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Teacherfaculties;
import com.example.springboot.entity.Teacherfaculties;
import com.example.springboot.service.TeacherfacultiesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/teacherfaculties")
public class TeacherfacultiesController {

    @Resource
    TeacherfacultiesService teacherfacultiesService;

    @GetMapping("/alldata")
    public Result getDate(){
        return Result.success(teacherfacultiesService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result teacherfacultiesRegister(@RequestBody Teacherfaculties teacherfaculties){
        return Result.success(teacherfacultiesService.teacherfacultiesRegister(teacherfaculties));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Tno}")
    public Result delete(@PathVariable String Tno) {
        teacherfacultiesService.delete(Tno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result teacherfacultiesEdit(@RequestBody Teacherfaculties teacherfaculties){

        return Result.success(teacherfacultiesService.teacherfacultiesEdit(teacherfaculties));

    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Teacherfaculties search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(teacherfacultiesService.findPage(search, pageNum, pageSize));
    }
}
