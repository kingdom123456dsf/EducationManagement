package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.CoursesofmajorDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Coursesofmajor;
import com.example.springboot.entity.Coursesofmajor;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesofmajorService {

    @Resource
    CoursesofmajorDao coursesofmajorDao;

    public List<Coursesofmajor> GetAll()
    {
        return coursesofmajorDao.selectAll();
    }

    //模态框
    public Coursesofmajor coursesofmajorRegister(Coursesofmajor coursesofmajor) {
        String Cno = coursesofmajor.getCno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Cno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
//     2.校验用户名的唯一性：
        Coursesofmajor dbCoursesofmajor = coursesofmajorDao.findByCno(Cno);
        if (ObjectUtil.isNotEmpty(dbCoursesofmajor)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        coursesofmajorDao.insertSelective(coursesofmajor);
        return coursesofmajor;
    }

    //删除方法
    public void delete(String Cno) {
        coursesofmajorDao.deleteByPrimaryKey(Cno);//根据主键删除的方法
    }

    //编辑方法
    public Coursesofmajor coursesofmajorEdit(Coursesofmajor coursesofmajor) {
        String Cno = coursesofmajor.getCno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Cno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        coursesofmajorDao.updateByPrimaryKeySelective(coursesofmajor);

        return coursesofmajor;
    }

    // 分页查询和模糊查询的方法
    public PageInfo<Coursesofmajor> findPage(Coursesofmajor search, Integer pageNum, Integer pageSize) {
        //获取当前用户，判断当前用户是否有登录状态
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }

        // 2开启分页(这句代码一定要在操作数据库代码的上面，要不不能正确运行)
        PageHelper.startPage(pageNum, pageSize);
        List<Coursesofmajor> all = findByCondition(search);
        return PageInfo.of(all);
    }
    // 根据条件查询的方法
    public List<Coursesofmajor> findByCondition(Coursesofmajor search) {
        return coursesofmajorDao.findBySearch(search);
    }
}
