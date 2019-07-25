package com.ycy.storehouse.utils;

/**
 * Created by Administrator on 2019\6\14 .
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.ycy.storehouse.base.BaseApp;

import java.util.Set;

/**
 * SharedPreferences工具类
 * Created by Administrator on 2015/10/28 0028.
 */
public class SharedPreferencesUtils {
    private static SharedPreferences Sp;
    // 便于修改保存文件名称
    private static String SP_NAME ="ehouse_sp";
    //这里的上下文必须是全局的，如果是activity或者其他界面传递过来的，则界面销毁后，这些引用还在，然后就会出现内存泄漏，因为这些方法都是静态的，会一直持有引用对象直至虚拟机销毁
    private static Context context = BaseApp.getAppContext();

    /**
     * 将数据存储在Sp里面。instanceof关键字是判断前面的类型是不是跟后面的类型一致
     * 这里的上下文不能用界面传过来的上下文，因为这里的方法是静态的，当界面销毁时，由于静态方法没有被销毁，因此还持有界面的引用，这样就会产生内存泄漏，而这里引用的上下文是全局的上下文，生命周期较长，几乎等同于虚拟机的生命周期，因此不会产生内存泄漏
     * 建议使用此方法来将数据保存在Sp里
     *
     * @param <T>
     * @param Key ：存储的键
     * @param defaultValue ：存储的内容（泛型）
     */
    @SuppressLint("ApplySharedPref")
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static <T> String saveSp(String Key, T defaultValue) {
        if (Sp == null) {
            // 第二个参数的0代表访问模式为私有
            Sp = context.getSharedPreferences(SP_NAME, 0);
        }
        //Sp.edits().putBoolean(Key, value).commit();
        // 如果对返回结果没有要求的话则可以用下面的提交，效率更高apply()
        if (defaultValue instanceof Boolean) {
            Sp.edit().putBoolean(Key, (Boolean) defaultValue).commit();
        } else if (defaultValue instanceof String) {
            Sp.edit().putString(Key, (String) defaultValue).commit();
        } else if (defaultValue instanceof Integer) {
            Sp.edit().putInt(Key, (Integer) defaultValue).commit();
        } else if (defaultValue instanceof Long) {
            Sp.edit().putLong(Key, (Long) defaultValue).commit();
        } else if (defaultValue instanceof Float) {
            Sp.edit().putFloat(Key, (Float) defaultValue).commit();
        } else if (defaultValue instanceof Set) {
            //这里的defaultValue不能为null，否则会报错
            Sp.edit().putStringSet(Key, (Set<String>) defaultValue).commit();
        }
        return Key;
    }

    //第三个参数表示返回的类型

    /**
     * 从Sp里面取数据
     *
     * @param Key：获取内容的键
     * @param defaultValue：默认值（泛型），当默认值为Set<String>类型时，defaultValue不能为null
     * @param <T>
     * @return 返回一个Object对象，用时强转到相应的类型
     */
    public static <T> Object getSp(String Key, T defaultValue) {
        if (Sp == null) {
            Sp = context.getSharedPreferences(SP_NAME, 0);
        }
        if (defaultValue instanceof Boolean) {
            return Sp.getBoolean(Key, (Boolean) defaultValue);
        } else if (defaultValue instanceof String) {
            return Sp.getString(Key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return Sp.getInt(Key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return Sp.getLong(Key, (Long) defaultValue);
        } else if (defaultValue instanceof Float) {
            return Sp.getFloat(Key, (Float) defaultValue);
        } else if (defaultValue instanceof Set) {
            //这里的t不能为空，否则不会得到存储的值
            return Sp.getStringSet(Key, (Set<String>) defaultValue);
        }
        return null;
    }

    public static void clear() {
//        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
//                0);
        if (Sp != null) {
            Sp.edit().clear().commit();
        }
    }

     private static void clearSp() {
//         SharedPreferences sp = context.getSharedPreferences(spText, Context.MODE_PRIVATE);
//         SharedPreferences.Editor editor = sp.edit();
         Sp.edit().clear();
         Sp.edit().commit();
     }
}
