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
package lbchs;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class 定时器
        extends 组件 {
    private final Timer timer;
    private final Handler handler = new  HandleProgressHandler(this);
    private 定时事件 $event_internal_定时事件;


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
        $event_internal_定时事件=定时事件2;
    }

    @Override
    public void 初始化事件() {
    }

    public interface 定时事件 {
        void 定时事件();
    }
    private static class HandleProgressHandler extends Handler {
        private final WeakReference<定时器 > mPlayerRef;
        public HandleProgressHandler(定时器 player) {
            mPlayerRef = new WeakReference<>(player );;
        }
        @Override
        public void handleMessage(Message message) {
            定时器  player = mPlayerRef.get();
            super.handleMessage(message);
            if (player != null && player.$event_internal_定时事件 != null) {
                player.$event_internal_定时事件.定时事件();
            }
        }
    }
}

