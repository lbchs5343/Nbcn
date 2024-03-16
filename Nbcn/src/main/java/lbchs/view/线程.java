/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.Runnable
 *  java.lang.Thread
 */
package lbchs.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class 线程
        extends 组件 {
    private final MyThread thread;
    private 接收到消息 $event_internal_接收到消息;
    private 开始操作 $event_internal_开始操作;

    public 线程(Context context) {
        super(context);
        this.thread = new MyThread(new Runnable() {

            public void run() {
                if (线程.this.$event_internal_开始操作 != null) {
                    线程.this.$event_internal_开始操作.开始操作();
                }
            }
        });
    }

    public 线程() {
        this.thread = new MyThread(new Runnable() {

            public void run() {
                if (线程.this.$event_internal_开始操作 != null) {
                    线程.this.$event_internal_开始操作.开始操作();
                }
            }
        });
    }

    public static void 暂停(long l) {
        try {
            Thread.sleep(l);
        } catch (Exception exception) {
            // empty catch block
        }
    }

    public void 开始操作() {
        this.thread.start();
    }

    public void 发送消息(int n) {
        this.thread.getHandler().sendEmptyMessage(n);
    }

    public void 停止() {
        this.thread.stop();
    }

    public void 拦截() {
        this.thread.interrupt();
    }

    public void 销毁() {
        this.thread.destroy();
    }

    public boolean 是否存活() {
        return this.thread.isAlive();
    }

    public void 置接收到消息(接收到消息 接收到消息2) {
        this.$event_internal_接收到消息 = 接收到消息2;
    }

    public void 置开始操作(开始操作 开始操作2) {
        this.$event_internal_开始操作 = 开始操作2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 开始操作 {
        void 开始操作();
    }

    public interface 接收到消息 {
        void 接收到消息(int var1);
    }

    class MyThread
            extends Thread {
        private final Handler handler;

        @SuppressLint("HandlerLeak")
        MyThread(Runnable runnable) {
            super(runnable);
            this.handler = new Handler() {

                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    if (线程.this.$event_internal_接收到消息 != null) {
                        线程.this.$event_internal_接收到消息.接收到消息(message.what);
                    }
                }
            };
        }

        public Handler getHandler() {
            return this.handler;
        }
    }
}

