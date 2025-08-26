package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.TeacherfacultiesDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Teacherfaculties;
import com.example.springboot.entity.Teacherfaculties;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherfacultiesService {

    @Resource
    TeacherfacultiesDao teacherfacultiesDao;

    public List<Teacherfaculties> GetAll()
    {
        return teacherfacultiesDao.selectAll();
    }

    //模态框
    public Teacherfaculties teacherfacultiesRegister(Teacherfaculties teacherfaculties) {
        String Tno = teacherfaculties.getTno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Tno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
//     2.校验用户名的唯一性：
        Teacherfaculties dbTeacherfaculties = teacherfacultiesDao.findByTno(Tno);
        if (ObjectUtil.isNotEmpty(dbTeacherfaculties)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        teacherfacultiesDao.insertSelective(teacherfaculties);
        return teacherfaculties;
    }

    //删除方法
    public void delete(String Tno) {
        teacherfacultiesDao.deleteByPrimaryKey(Tno);//根据主键删除的方法
    }

    //编辑方法
    public Teacherfaculties teacherfacultiesEdit(Teacherfaculties teacherfaculties) {
        String Tno = teacherfaculties.getTno();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(Tno)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        teacherfacultiesDao.updateByPrimaryKeySelective(teacherfaculties);

        return teacherfaculties;
    }
    

    // 分页查询和模糊查询的方法
    public PageInfo<Teacherfaculties> findPage(Teacherfaculties search, Integer pageNum, Integer pageSize) {
        //获取当前用户，判断当前用户是否有登录状态
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }

        // 2开启分页(这句代码一定要在操作数据库代码的上面，要不不能正确运行)
        PageHelper.startPage(pageNum, pageSize);
        List<Teacherfaculties> all = findByCondition(search);
        return PageInfo.of(all);
    }
    // 根据条件查询的方法
    public List<Teacherfaculties> findByCondition(Teacherfaculties search) {
        return teacherfacultiesDao.findBySearch(search);
    }
}
