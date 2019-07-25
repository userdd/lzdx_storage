package com.ycy.storehouse.utils;

import com.ycy.storehouse.base.Contents;

/**
 * Created by Administrator on 2018/9/4.
 */

public class Methods {

    /**
     * 用户token
     * */
    public static String getToken(){
        return (String)SharedPreferencesUtils.getSp(Contents.TOKEN+"","");
    }

    /**
     * 用户userId
     * */
    public static String getUserId(){
        return "";
    }
}
