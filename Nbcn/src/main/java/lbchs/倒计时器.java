/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.CountDownTimer
 *  java.lang.Object
 *  java.lang.Override
 */
package lbchs;

import android.content.Context;
import android.os.CountDownTimer;

public class 倒计时器
        extends 组件 {
    private CountDownTimer timer;
    private 正在倒计时 $event_internal_正在倒计时;
    private 倒计时结束 $event_internal_倒计时结束;

    public 倒计时器(Context context) {
        super(context);
    }

    public 倒计时器(long l, long l2) {
        this.timer = new CountDownTimer(l, l2) {

            public void onTick(long l) {
                if (倒计时器.this.$event_internal_正在倒计时 != null) {
                    倒计时器.this.$event_internal_正在倒计时.正在倒计时(l);
                }
            }

            public void onFinish() {
                if (倒计时器.this.$event_internal_倒计时结束 != null) {
                    倒计时器.this.$event_internal_倒计时结束.倒计时结束();
                }
            }
        };
    }

    public void 开始倒计时() {
        if (this.timer != null) {
            this.timer.start();
        }
    }

    public void 开始倒计时(long l, long l2) {
        this.timer = new CountDownTimer(l, l2) {

            public void onTick(long l) {
                if (倒计时器.this.$event_internal_正在倒计时 != null) {
                    倒计时器.this.$event_internal_正在倒计时.正在倒计时(l);
                }
            }

            public void onFinish() {
                if (倒计时器.this.$event_internal_倒计时结束 != null) {
                    倒计时器.this.$event_internal_倒计时结束.倒计时结束();
                }
            }
        };
        this.timer.start();
    }

    public void 关闭() {
        this.timer.cancel();
    }

    public void 置正在倒计时(正在倒计时 正在倒计时2) {
        this.$event_internal_正在倒计时 = 正在倒计时2;
    }

    public void 置倒计时结束(倒计时结束 倒计时结束2) {
        this.$event_internal_倒计时结束 = 倒计时结束2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 倒计时结束 {
        void 倒计时结束();
    }

    public interface 正在倒计时 {
        void 正在倒计时(long var1);
    }
}

