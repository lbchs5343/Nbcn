/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.widget.Button
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class 按钮
        extends 文本框 {
    public 按钮(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new Button(this.取上下文());
    }

    public Button getView() {
        return (Button) super.getView();
    }

    @Override
    public void 初始化事件() {
    }
}

