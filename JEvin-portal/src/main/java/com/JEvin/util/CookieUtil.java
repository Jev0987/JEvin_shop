package com.JEvin.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.util
 *  @文件名:   CookieUtil
 *  @创建者:   85169
 *  @创建时间:  2018/12/26 16:22
 *  @描述：    TODO
 */
public class CookieUtil {

    public  static String findTicket(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                if ("ticket".equals(name)) {

                    return cookie.getValue();

                }
            }

        }
        return null;

    }
}



