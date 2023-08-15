package com.jiangzhihong.java.easydemo.util;

import java.util.UUID;

/**
 * @description: 字符串工具类
 * @author: jzh
 * @create: 2023-03-24 14:05
 **/
public class StringUtil {

    public static boolean isBlank(String s) {
        return s == null || s.equals("");
    }

    /**
     * 获取唯一标志码
     */
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }
}
