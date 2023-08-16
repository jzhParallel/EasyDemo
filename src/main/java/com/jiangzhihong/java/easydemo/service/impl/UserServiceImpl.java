package com.jiangzhihong.java.easydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiangzhihong.java.easydemo.mapper.UserMapper;
import com.jiangzhihong.java.easydemo.model.User;
import com.jiangzhihong.java.easydemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: EasyDemo
 * @description: 用户服务实现类
 * @author: jiangzhihong
 * @create: 2023-08-06 10:23
 **/

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User login(String account, String password) {
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("account",account);//数据库中列的值相等
//        userQueryWrapper.eq("password",password);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, account)
                //.eq(account!=null,User::getAccount,account)//添加条件判定的查询条件
                .eq(User::getPassword, password);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void register(User user) {
        userMapper.insert(user);
    }

    /**
     * current是当前页码，size是每页显示数
     */
    @Override
    public List<User> listUserByPage(int current, int size) {
        IPage page = new Page(current, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUid, User::getAccount);//查询投影,只查询部分指定的列
        //queryWrapper.groupBy();
        userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数" + page.getPages());
        System.out.println("当前页数" + page.getCurrent());
        System.out.println("总数据量" + page.getTotal());
        System.out.println("每页显示" + page.getSize());
        System.out.println(page.getRecords());
        List<User> records = page.getRecords();
        return records;
    }

    @Override
    public boolean delete(int uid) {
//        userMapper.deleteBatchIds();//多数据删除
//        List<User> users = userMapper.selectBatchIds();//多数据查询
        return true;
    }
}
