/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;

public class 拖动条
        extends 进度条 {
    private 进度被改变 $event_internal_进度被改变;
    private 开始滑动 $event_internal_开始滑动;
    private 结束滑动 $event_internal_结束滑动;

    public 拖动条(Context context) {
        super(context);
        this.getView().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
                if (拖动条.this.$event_internal_进度被改变 != null) {
                    拖动条.this.$event_internal_进度被改变.进度被改变(seekBar.getProgress());
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                if (拖动条.this.$event_internal_开始滑动 != null) {
                    拖动条.this.$event_internal_开始滑动.开始滑动();
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (拖动条.this.$event_internal_结束滑动 != null) {
                    拖动条.this.$event_internal_结束滑动.结束滑动();
                }
            }
        });
    }

    @Override
    public View 创建视图() {
        return new SeekBar(this.取上下文());
    }

    public SeekBar getView() {
        return (SeekBar) super.getView();
    }

    public void 置进度被改变(进度被改变 进度被改变2) {
        this.$event_internal_进度被改变 = 进度被改变2;
    }

    public void 置开始滑动(开始滑动 开始滑动2) {
        this.$event_internal_开始滑动 = 开始滑动2;
    }

    public void 置结束滑动(结束滑动 结束滑动2) {
        this.$event_internal_结束滑动 = 结束滑动2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 结束滑动 {
        public void 结束滑动();
    }

    public static interface 开始滑动 {
        public void 开始滑动();
    }

    public static interface 进度被改变 {
        public void 进度被改变(int var1);
    }
}

