/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 */
package jiesheng;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class 广播接收器
        extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            this.接收意图(intent.getAction(), new 意图(intent));
            String string = intent.getStringExtra("ID");
            String string2 = intent.getStringExtra("content");
            if (string != null && string2 != null) {
                this.接收数据(intent.getAction(), string, string2);
            }
        }
    }

    public void 接收意图(String string, 意图 意图2) {
    }

    public void 接收数据(String string, String string2, String string3) {
    }

    public void 初始化事件() {
    }
}

