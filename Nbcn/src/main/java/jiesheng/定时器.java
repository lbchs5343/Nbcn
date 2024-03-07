/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Object
 *  java.lang.Override
 *  java.util.Timer
 *  java.util.TimerTask
 */
package jiesheng;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class 定时器
        extends 组件 {
    private Timer timer;
    private 定时事件 $event_internal_定时事件;
    private Handler handler = new Handler() {

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (定时器.this.$event_internal_定时事件 != null) {
                定时器.this.$event_internal_定时事件.定时事件();
            }
        }
    };

    public 定时器(Context context) {
        super(context);
        this.timer = new Timer();
    }

    public 定时器() {
        this.timer = new Timer();
    }

    public void 开始定时(long l) {
        this.开始定时(0L, l);
    }

    public void 开始定时(long l, long l2) {
        this.timer.schedule(new TimerTask() {

            public void run() {
                定时器.this.handler.sendEmptyMessage(0);
            }
        }, l, l2);
    }

    public void 关闭() {
        this.timer.cancel();
    }

    public void 置定时事件(定时事件 定时事件2) {
        this.$event_internal_定时事件 = 定时事件2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 定时事件 {
        public void 定时事件();
    }
}

