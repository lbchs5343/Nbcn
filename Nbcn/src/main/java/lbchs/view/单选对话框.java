/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.graphics.drawable.Drawable
 *  android.os.Looper
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.RuntimeException
 *  java.lang.String
 */
package lbchs.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;


public class 单选对话框
        extends 组件 {
    private static 对话框.DialogHandler handler;
    private static int item;

    static {
        item = 0;
    }

    private final AlertDialog.Builder builder;
    private AlertDialog dialog;
    private 项目被选中 $event_internal_项目被选中;
    private 按钮1被单击 $event_internal_按钮1被单击;
    private 按钮2被单击 $event_internal_按钮2被单击;
    private 按钮3被单击 $event_internal_按钮3被单击;

    public 单选对话框(Context context) {
        super(context);
        this.builder = new AlertDialog.Builder(context);
    }

    public static int singleChoiceBox(Context context, String[] stringArray) {
        return 单选对话框.singleChoiceBox(context, "", stringArray, 0);
    }

    public static int singleChoiceBox(Context context, String[] stringArray, int n) {
        return 单选对话框.singleChoiceBox(context, "", stringArray, n);
    }

    public static int singleChoiceBox(Context context, String string, String[] stringArray, final int n) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!string.isEmpty()) {
            builder.setTitle(string);
        }
        builder.setCancelable(false);
        builder.setSingleChoiceItems(stringArray, n, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                item = n;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                handler.sendMessage(handler.obtainMessage());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n2) {
                item = n;
                handler.sendMessage(handler.obtainMessage());
            }
        });
        builder.create().show();
        handler = new 对话框.DialogHandler(Looper.getMainLooper());
        try {
            Looper.getMainLooper();
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            Log.e("错误", runtimeException.toString());
        }
        return item;
    }

    public void 置项目(int n, String... stringArray) {
        this.builder.setSingleChoiceItems(stringArray, n, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (单选对话框.this.$event_internal_项目被选中 != null) {
                    单选对话框.this.$event_internal_项目被选中.项目被选中(n);
                }
            }
        });
    }

    public void 标题(String string) {
        this.builder.setTitle(string);
    }

    public void 信息(String string) {
        this.builder.setMessage(string);
    }

    public void 图标(String string) {
        try {
            this.builder.setIcon(Drawable.createFromStream(this.窗口环境.getAssets().open(string), string));
        } catch (IOException iOException) {
            Log.e("错误",iOException.toString());
        }
    }

    public void 图标(int n) {
        this.builder.setIcon(n);
    }

    public void 按钮1(String string) {
        this.builder.setPositiveButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (单选对话框.this.$event_internal_按钮1被单击 != null) {
                    单选对话框.this.$event_internal_按钮1被单击.按钮1被单击();
                }
            }
        });
    }

    public void 按钮2(String string) {
        this.builder.setNegativeButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (单选对话框.this.$event_internal_按钮2被单击 != null) {
                    单选对话框.this.$event_internal_按钮2被单击.按钮2被单击();
                }
            }
        });
    }

    public void 按钮3(String string) {
        this.builder.setNeutralButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (单选对话框.this.$event_internal_按钮3被单击 != null) {
                    单选对话框.this.$event_internal_按钮3被单击.按钮3被单击();
                }
            }
        });
    }

    public void 可取消(boolean bl) {
        this.builder.setCancelable(bl);
    }

    public void 显示() {
        this.dialog = this.builder.create();
        this.dialog.show();
    }

    public void 隐藏() {
        this.dialog.hide();
    }

    public void 关闭() {
        this.dialog.dismiss();
    }

    public void 置项目被选中(项目被选中 项目被选中2) {
        this.$event_internal_项目被选中 = 项目被选中2;
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
        super.初始化事件();
    }

    public interface 按钮3被单击 {
        void 按钮3被单击();
    }

    public interface 按钮2被单击 {
        void 按钮2被单击();
    }

    public interface 按钮1被单击 {
        void 按钮1被单击();
    }

    public interface 项目被选中 {
        void 项目被选中(int var1);
    }
}

