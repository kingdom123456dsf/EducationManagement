package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.User;
import com.example.springboot.entity.User;
import com.example.springboot.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

//处理业务
@Service
public class UserService {

    @Resource
    UserDao userDao;

    public List<User> GetAll() {
//        return userDao.getAll();
        return userDao.selectAll();
    }

    public User userRegister(User user) {
    // 插入数据库前做一下用户名唯一性校验，
        String userName = user.getUserName();
    // 先拿到用户输入的用户名
        User dbUser = userDao.findByUsername(userName);
        if (ObjectUtil.isNotEmpty(dbUser)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //insertSelective方法,把用户注册信息写入信息表中
        userDao.insertSelective(user);
        return user;
    }


    public User userLogin(User user) {

//        1.拿到用户传来的用户名，先到数据库查看这个用户名是否存在,如果不存在抛出异常：“用户不存在”
        User dbUser = userDao.findByName(user.getUserName());
        if (ObjectUtil.isEmpty(dbUser)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
//        2.如果用户存在，则那用户输入的密码跟数据库找到的用户的密码比对，密码输入正确，允许登录
        String password = user.getPassword(); //用户输入的密码
        String dbPassword = dbUser.getPassword(); // 数据库找到用户的密码

        if (!password.equalsIgnoreCase(dbPassword)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }

        return dbUser;

    }


    //根据id查询
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    //模态框
    //删除方法
    public void delete(Integer id) {
        userDao.deleteByPrimaryKey(id);//根据主键删除的方法
    }


    //编辑方法
    public User userEdit(User user) {

        String userName = user.getUserName();
//     1.先校验用户名是不是为空： 如果用户名，为空，抛出异常
        if (ObjectUtil.isEmpty(userName)){
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        userDao.updateByPrimaryKeySelective(user);

        return user;
    }

}
