package com.ycy.storehouse.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseApp extends MultiDexApplication {

    private static BaseApp instance = new BaseApp();
    private static Context mContext;
    private static Map<String, Activity> destoryMap = new HashMap<>();
    private List<Activity> activities = new ArrayList<>();


    public static Context getContext() {
        return mContext;
    }

    public static BaseApp getInstance() {
        return instance;
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    public synchronized static BaseApp getAppContext() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
    }
    public void addActivity(Activity activity){
        this.activities.add(activity);
    }
    public void removeActivity(Activity activity){this.activities.remove(activity);}
    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(key).finish();
        }
    }
}
