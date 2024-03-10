/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.content.Context
 *  android.os.Process
 *  java.lang.Object
 *  java.util.ArrayList
 *  java.util.List
 */
package lbchs;

import static lbchs.上下文操作.置全局上下文;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;

public class 安卓应用
        extends Application {
    private final List<Activity> 窗口集合 = new ArrayList<>();

    public List<Activity> getActivities() {
        return this.窗口集合;
    }

    public void onCreate() {
        super.onCreate();
        this.载入完毕();
        置全局上下文(this);
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        this.即将载入();
    }

    public void 添加窗口(Activity activity) {
        this.窗口集合.add(activity);
    }

    public void 关闭程序() {
        for (Activity activity : this.窗口集合) {
            activity.finish();
        }
        Process.killProcess(Process.myPid());
    }

    public void 即将载入() {
    }

    public void 载入完毕() {
    }

    public void 初始化事件() {
    }
}

