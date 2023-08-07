package com.jiangzhihong.java.easydemo.mapper;

import com.jiangzhihong.java.easydemo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: EasyDemo
 * @description: 用户mapper
 * @author: jiangzhihong
 * @create: 2023-08-06 10:03
 **/

@Mapper
public interface UserMapper {

    @Select("select uid,account,password from ed_user where account = #{account} and password = #{password}")
    User selectByAccountAndPassword(String account, String password);

    @Insert("insert into ed_user(account,password) values(#{account},#{password}) ")
        //@Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn="uid")
    void insertUser(String account, String password);
}
