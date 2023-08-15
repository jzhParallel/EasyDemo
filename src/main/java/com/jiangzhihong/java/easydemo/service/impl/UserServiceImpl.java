package com.jiangzhihong.java.easydemo.service.impl;

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
        return userMapper.selectByAccountAndPassword(account, password);
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
        userMapper.selectPage(page, null);
        System.out.println("总页数" + page.getPages());
        System.out.println("当前页数" + page.getCurrent());
        System.out.println("总数据量" + page.getTotal());
        System.out.println("每页显示" + page.getSize());
        System.out.println(page.getRecords());
        List<User> records = page.getRecords();
        return records;
    }
}
