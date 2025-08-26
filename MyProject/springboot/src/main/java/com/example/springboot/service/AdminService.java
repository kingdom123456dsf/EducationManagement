package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.AdminDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

//处理业务
@Service
public class AdminService {

    @Resource
    AdminDao adminDao;

    public List<Admin> GetAll() {
//        return adminDao.getAll();
        return adminDao.selectAll();
    }

    public Admin adminLogin(Admin admin) {

//        1.拿到用户传来的用户名，先到数据库查看这个用户名是否存在,如果不存在抛出异常：“用户不存在”
        Admin dbAdmin = adminDao.findByName(admin.getUserName());
        if (ObjectUtil.isEmpty(dbAdmin)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
//        2.如果用户存在，则那用户输入的密码跟数据库找到的用户的密码比对，密码输入正确，允许登录
        String password = admin.getPassword(); //用户输入的密码
        String dbPassword = dbAdmin.getPassword(); // 数据库找到用户的密码

        if (!password.equalsIgnoreCase(dbPassword)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }

        return dbAdmin;

    }

    //根据id查询
    public Admin findById(Integer id) {
        return adminDao.findById(id);
    }


    //模态框
    public Admin adminRegister(Admin admin) {
        String userName = admin.getUserName();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(userName)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
//     2.校验用户名的唯一性：
        Admin dbAdmin = adminDao.findByName(userName);
        if (ObjectUtil.isNotEmpty(dbAdmin)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        adminDao.insertSelective(admin);
        return admin;
    }

    //删除方法
    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);//根据主键删除的方法
    }


    //编辑方法
    public Admin adminEdit(Admin admin) {
        String userName = admin.getUserName();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(userName)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        adminDao.updateByPrimaryKeySelective(admin);
        return admin;
    }

    // 分页查询和模糊查询的方法
    public PageInfo<Admin> findPage(Admin search, Integer pageNum, Integer pageSize) {
        //获取当前用户，判断当前用户是否有登录状态
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }

        // 2开启分页(这句代码一定要在操作数据库代码的上面，要不不能正确运行)
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> all = findByCondition(search);
        return PageInfo.of(all);
    }
    // 根据条件查询的方法
    public List<Admin> findByCondition(Admin search) {
        return adminDao.findBySearch(search);
    }

}
