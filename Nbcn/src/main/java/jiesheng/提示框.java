/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.widget.Toast
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package jiesheng;

import android.content.Context;
import android.widget.Toast;

public class 提示框
        extends 组件 {
    private Toast toast;

    public 提示框(Context context) {
        super(context);
        this.toast = new Toast(context);
    }

    public static void 弹出提示(Context context, String string) {
        Toast.makeText((Context) context, (CharSequence) string, (int) 1500).show();
    }

    public static void 弹出提示(Context context, String string, int n) {
        Toast.makeText((Context) context, (CharSequence) string, (int) n).show();
    }

    public void 内容(String string) {
        this.toast.setText((CharSequence) string);
    }

    public void 组件(组件容器 组件容器2) {
        this.toast.setView(((可视化组件) 组件容器2.取根组件()).getView());
    }

    public void 显示时长(int n) {
        this.toast.setDuration(n);
    }

    public void 显示() {
        this.toast.show();
    }

    public void 关闭() {
        this.toast.cancel();
    }

    public void 置重力属性(int n, int n2, int n3) {
        this.toast.setGravity(n, n2, n3);
    }

    @Override
    public void 初始化事件() {
    }
}

