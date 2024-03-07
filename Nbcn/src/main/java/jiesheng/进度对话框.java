/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.graphics.drawable.Drawable
 *  android.os.Handler
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.Runnable
 *  java.lang.String
 */
package jiesheng;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;

public class 进度对话框
        extends 组件 {
    private ProgressDialog dialog;
    private int progress;
    private 按钮1被单击 $event_internal_按钮1被单击;
    private 按钮2被单击 $event_internal_按钮2被单击;
    private 按钮3被单击 $event_internal_按钮3被单击;

    public 进度对话框(Context context) {
        super(context);
        this.dialog = new ProgressDialog(context);
    }

    public 进度对话框(Context context, String string) {
        this(context);
        this.信息(string);
    }

    public 进度对话框(Context context, String string, String string2) {
        this(context);
        this.标题(string);
        this.信息(string2);
    }

    public void 风格(int n) {
        this.dialog.setProgressStyle(n);
    }

    public void 标题(String string) {
        this.dialog.setTitle((CharSequence) string);
    }

    public void 信息(String string) {
        this.dialog.setMessage((CharSequence) string);
    }

    public void 进度(int n) {
        this.progress = n;
        new Handler().post(new Runnable() {

            public void run() {
                进度对话框.this.dialog.setProgress(进度对话框.this.progress);
            }
        });
    }

    public int 进度() {
        return this.dialog.getProgress();
    }

    public void 最大进度(int n) {
        this.dialog.setMax(n);
    }

    public int 最大进度() {
        return this.dialog.getMax();
    }

    public void 缓冲进度(int n) {
        this.dialog.setSecondaryProgress(n);
    }

    public int 缓冲进度() {
        return this.dialog.getSecondaryProgress();
    }

    public void 模糊进度(boolean bl) {
        this.dialog.setIndeterminate(bl);
    }

    public boolean 模糊进度() {
        return this.dialog.isIndeterminate();
    }

    public void 图标(String string) {
        try {
            this.dialog.setIcon(Drawable.createFromStream((InputStream) this.窗口环境.getAssets().open(string), (String) string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 图标(int n) {
        this.dialog.setIcon(n);
    }

    public void 按钮1(String string) {
        this.dialog.setButton((CharSequence) string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (进度对话框.this.$event_internal_按钮1被单击 != null) {
                    进度对话框.this.$event_internal_按钮1被单击.按钮1被单击();
                }
            }
        });
    }

    public void 按钮2(String string) {
        this.dialog.setButton2((CharSequence) string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (进度对话框.this.$event_internal_按钮2被单击 != null) {
                    进度对话框.this.$event_internal_按钮2被单击.按钮2被单击();
                }
            }
        });
    }

    public void 按钮3(String string) {
        this.dialog.setButton3((CharSequence) string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (进度对话框.this.$event_internal_按钮3被单击 != null) {
                    进度对话框.this.$event_internal_按钮3被单击.按钮3被单击();
                }
            }
        });
    }

    public void 可取消(boolean bl) {
        this.dialog.setCancelable(bl);
    }

    public boolean 是否在显示() {
        return this.dialog.isShowing();
    }

    public void 显示() {
        this.dialog.show();
    }

    public void 隐藏() {
        this.dialog.hide();
    }

    public void 关闭() {
        this.dialog.dismiss();
    }

    public void 置按钮1被单击(按钮1被单击 按钮1被单击2) {
        this.$event_internal_按钮1被单击 = 按钮1被单击2;
    }

    public void 置按钮2被单击(按钮2被单击 按钮2被单击2) {
        this.$event_internal_按钮2被单击 = 按钮2被单击2;
    }

    public void 置按钮3被单击(按钮3被单击 按钮3被单击2) {
        this.$event_internal_按钮3被单击 = 按钮3被单击2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 按钮3被单击 {
        public void 按钮3被单击();
    }

    public static interface 按钮2被单击 {
        public void 按钮2被单击();
    }

    public static interface 按钮1被单击 {
        public void 按钮1被单击();
    }
}

