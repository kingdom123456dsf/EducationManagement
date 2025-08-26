package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Faculties;
import com.example.springboot.entity.Learning;
import com.example.springboot.entity.Major;
import com.example.springboot.entity.Major;
import com.example.springboot.service.MajorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/major")
public class MajorController {

    @Resource
    MajorService majorService;

    @GetMapping("/alldata")
    public Result getDate(){
        return Result.success(majorService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result majorRegister(@RequestBody Major major){
        return Result.success(majorService.majorRegister(major));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Mno}")
    public Result delete(@PathVariable String Mno) {
        majorService.delete(Mno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result majorEdit(@RequestBody Major major){
        return Result.success(majorService.majorEdit(major));
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Major search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(majorService.findPage(search, pageNum, pageSize));
    }
}
