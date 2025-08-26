package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Faculties;
import com.example.springboot.entity.Learning;
import com.example.springboot.entity.Teacher;
import com.example.springboot.service.FacultiesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/faculties")
public class FacultiesController {

    @Resource
    FacultiesService facultiesService;

    @GetMapping("/alldata")

    public Result getDate()
    {
        return Result.success(facultiesService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result facultiesRegister(@RequestBody Faculties faculties){
        return Result.success(facultiesService.facultiesRegister(faculties));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Dno}")
    public Result delete(@PathVariable String Dno) {
        facultiesService.delete(Dno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result facultiesEdit(@RequestBody Faculties faculties){

        return Result.success(facultiesService.facultiesEdit(faculties));

    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Faculties search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(facultiesService.findPage(search, pageNum, pageSize));
    }
}
