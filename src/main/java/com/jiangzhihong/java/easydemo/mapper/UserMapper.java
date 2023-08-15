package com.jiangzhihong.java.easydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangzhihong.java.easydemo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: EasyDemo
 * @description: 用户mapper
 * @author: jiangzhihong
 * @create: 2023-08-06 10:03
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select uid,account,password from ed_user where account = #{account} and password = #{password}")
    User selectByAccountAndPassword(String account, String password);

}
