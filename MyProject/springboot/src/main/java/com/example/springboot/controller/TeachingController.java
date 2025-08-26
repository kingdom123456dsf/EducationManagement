package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Teaching;
import com.example.springboot.service.TeachingService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/teaching")
public class TeachingController {
    @Resource
    TeachingService teachingService;

    @GetMapping("/alldata")

    public Result getDate(){
        return Result.success(teachingService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result teachingRegister(@RequestBody Teaching teaching){
        return Result.success(teachingService.teachingRegister(teaching));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Tno}")
    public Result delete(@PathVariable String Tno) {
        teachingService.delete(Tno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result teachingEdit(@RequestBody Teaching teaching){
        return Result.success(teachingService.teachingEdit(teaching));
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Teaching search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(teachingService.findPage(search, pageNum, pageSize));
    }
}
