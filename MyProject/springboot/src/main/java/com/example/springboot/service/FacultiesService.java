package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.FacultiesDao;
import com.example.springboot.entity.*;
import com.example.springboot.entity.Faculties;
import com.example.springboot.entity.Faculties;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultiesService {

    @Resource
    FacultiesDao facultiesDao;

    public List<Faculties> GetAll()
    {
        return facultiesDao.selectAll();
    }

    //模态框
    public Faculties facultiesRegister(Faculties faculties) {
        String Dno = faculties.getDno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Dno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
//     2.校验用户名的唯一性：
        Faculties dbFaculties = facultiesDao.findByDno(Dno);
        if (ObjectUtil.isNotEmpty(dbFaculties)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        facultiesDao.insertSelective(faculties);
        return faculties;
    }

    //删除方法
    public void delete(String Dno) {
        facultiesDao.deleteByPrimaryKey(Dno);//根据主键删除的方法
    }

    //编辑方法
    public Faculties facultiesEdit(Faculties faculties) {
        String Dno = faculties.getDno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Dno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        facultiesDao.updateByPrimaryKeySelective(faculties);

        return faculties;
    }

    // 分页查询和模糊查询的方法
    public PageInfo<Faculties> findPage(Faculties search, Integer pageNum, Integer pageSize) {
        //获取当前用户，判断当前用户是否有登录状态
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }

        // 2开启分页(这句代码一定要在操作数据库代码的上面，要不不能正确运行)
        PageHelper.startPage(pageNum, pageSize);
        List<Faculties> all = findByCondition(search);
        return PageInfo.of(all);
    }
    // 根据条件查询的方法
    public List<Faculties> findByCondition(Faculties search) {
        return facultiesDao.findBySearch(search);
    }
}
