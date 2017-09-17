package com.gionee.sso.util;


import com.yeqy.sso.biz.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqy on 2017/9/13.
 */
public class UserSessionMap {

    private static Map<String,User> userMap = new HashMap<String, User>();


    public static User getUser(String key){
        return userMap.get(key);
    }

    public static User setUser(String key, User user){
        return userMap.put(key, user);
    }
}
