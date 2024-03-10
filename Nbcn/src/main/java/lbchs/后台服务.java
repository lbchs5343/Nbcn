/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Intent
 *  android.os.IBinder
 *  java.lang.Object
 */
package lbchs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class 后台服务
        extends Service {
    public IBinder onBind(Intent intent) {
        this.绑定服务(new 意图(intent));
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.创建服务();
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        int n3 = super.onStartCommand(intent, n, n2);
        this.启动一次服务(new 意图(intent));
        return n3;
    }

    public void onDestroy() {
        super.onDestroy();
        this.销毁服务();
    }

    public void 绑定服务(意图 意图2) {
    }

    public void 创建服务() {
    }

    public void 启动一次服务(意图 意图2) {
    }

    public void 销毁服务() {
    }

    public void 初始化事件() {
    }
}

