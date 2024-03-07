/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.Runnable
 */
package jiesheng;

import android.content.Context;
import android.os.Handler;

public class 时钟
        extends 组件
        implements Runnable {
    public boolean 可用;
    public int 时钟周期;
    private Handler handler = new Handler();
    private 周期事件 $event_internal_周期事件;

    public 时钟(Context context) {
        super(context);
    }

    public 时钟() {
    }

    public void run() {
        if (this.可用) {
            if (this.$event_internal_周期事件 != null) {
                this.$event_internal_周期事件.周期事件();
            }
            this.handler.postDelayed((Runnable) this, (long) this.时钟周期);
        }
    }

    public void 时钟周期(int n) {
        this.时钟周期 = n;
        this.可用 = n > 0;
        if (this.可用) {
            this.handler.removeCallbacks((Runnable) this);
            this.handler.postDelayed((Runnable) this, (long) n);
        }
    }

    public int 时钟周期() {
        return this.时钟周期;
    }

    public void 可用(boolean bl) {
        this.可用 = bl;
        if (bl) {
            this.handler.removeCallbacks((Runnable) this);
            this.handler.postDelayed((Runnable) this, (long) this.时钟周期);
        }
    }

    public boolean 可用() {
        return this.可用;
    }

    public void 置周期事件(周期事件 周期事件2) {
        this.$event_internal_周期事件 = 周期事件2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 周期事件 {
        public void 周期事件();
    }
}

