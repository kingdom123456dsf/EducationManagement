package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Learning;
import com.example.springboot.service.LearningService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/learning")
public class LearningController {
    @Resource
    LearningService learningService;

    @GetMapping("/alldata")

    public Result getDate(){
        return Result.success(learningService.GetAll());
    }

    /**
     * 新增管理员接口,模态框
     */
    @PostMapping
    public Result learningRegister(@RequestBody Learning learning){
        return Result.success(learningService.learningRegister(learning));
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{Sno}")
    public Result delete(@PathVariable String Sno) {
        learningService.delete(Sno);
        return Result.success();
    }

    /**
     * 编辑管理员信息接口
     */
    @PostMapping("/edit")
    public Result learningEdit(@RequestBody Learning learning){
        return Result.success(learningService.learningEdit(learning));
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Learning search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(learningService.findPage(search, pageNum, pageSize));
    }
}
