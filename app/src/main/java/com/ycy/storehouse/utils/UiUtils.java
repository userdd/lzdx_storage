package com.ycy.storehouse.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.ycy.storehouse.base.BaseApp;

/**
 * Created by Administrator on 2017\11\14 0014.
 */

public class UiUtils {
    private UiUtils() {
        throw new AssertionError("无法实例化该类");
    }

    /**
     * 获取到字符数组
     *
     * @param tabNames 字符数组的id
     */
    public static String[] getStringArray(@ArrayRes int tabNames) {
        return getResource().getStringArray(tabNames);
    }

    //这里采用资源注解，如传递参数与资源注解不符合则会报错
    public static int getColor(@ColorRes int color) {
        return getResource().getColor(color);
    }

    @NonNull //当返回值为null时会出现警告
    public static String getText(@StringRes int str) {
        return getResource().getString(str);
    }

    //这里采用资源注解，如传递参数与资源注解不符合则会报错
    public static Drawable getDrawable(@DrawableRes int drawable) {
        return getResource().getDrawable(drawable);
    }

    public static Resources getResource() {
        return BaseApp.getAppContext().getResources();
    }

    public static Context getContext() {
        return BaseApp.getAppContext();
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * dip转换px
     */
    public static int dip2px(float dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */

    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    /**
     * dip转换px
     */
    public static int sp2px(int dip) {
        final float scale = getResource().getDisplayMetrics().scaledDensity;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */

    public static int px2sp(int px) {
        final float scale = getResource().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5f);
    }

    public static int getScreenWidth() {
        return getResource().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getResource().getDisplayMetrics().heightPixels;
    }
}
