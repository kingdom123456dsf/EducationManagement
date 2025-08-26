package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.LearningDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Learning;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningService {

    @Resource
    LearningDao learningDao;

    public List<Learning> GetAll(){
        return learningDao.selectAll();
    }

    //模态框
    public Learning learningRegister(Learning learning) {
        String Sno = learning.getSno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Sno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
//     2.校验用户名的唯一性：
        Learning dbLearning = learningDao.findBySno(Sno);
        if (ObjectUtil.isNotEmpty(dbLearning)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        learningDao.insertSelective(learning);
        return learning;
    }

    //删除方法
    public void delete(String Sno) {
        learningDao.deleteByPrimaryKey(Sno);//根据主键删除的方法
    }

    //编辑方法
    public Learning learningEdit(Learning learning) {
        String Sno = learning.getSno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Sno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        learningDao.updateByPrimaryKeySelective(learning);

        return learning;
    }

    // 分页查询和模糊查询的方法
    public PageInfo<Learning> findPage(Learning search, Integer pageNum, Integer pageSize) {
        //获取当前用户，判断当前用户是否有登录状态
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }

        // 2开启分页(这句代码一定要在操作数据库代码的上面，要不不能正确运行)
        PageHelper.startPage(pageNum, pageSize);
        List<Learning> all = findByCondition(search);
        return PageInfo.of(all);
    }
    // 根据条件查询的方法
    public List<Learning> findByCondition(Learning search) {
        return learningDao.findBySearch(search);
    }
}
