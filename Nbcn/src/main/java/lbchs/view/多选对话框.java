/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnMultiChoiceClickListener
 *  android.graphics.drawable.Drawable
 *  android.os.Looper
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.List
 */
package lbchs.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Looper;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class 多选对话框 extends 组件 {
    private static 对话框.DialogHandler handler;
    private static Integer[] multiResult = null;
    private AlertDialog dialog;
    private final AlertDialog.Builder builder;

    public 多选对话框(Context context) {
        super(context);
        this.builder = new AlertDialog.Builder(context);
    }

    public static Integer[] multiChoiceBox(Context activity, String[] items, boolean[] choose) {
        return multiChoiceBox(activity, "", items, choose);
    }

    public static Integer[] multiChoiceBox(Context activity, String[] items) {
        return multiChoiceBox(activity, "", items, new boolean[]{});
    }

    public static Integer[] multiChoiceBox(Context activity, String title, String[] items) {
        return multiChoiceBox(activity, title, items, new boolean[]{});
    }

    public static Integer[] multiChoiceBox(Context activity, String title, String[] items, boolean[] choose) {
        final List<Integer> list = new ArrayList<>();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
        if (!title.equals(""))
            builder2.setTitle(title);
        builder2.setCancelable(false);
        builder2.setMultiChoiceItems(items, choose, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2, boolean p3) {
                list.add(p2);
                handler.sendMessage(handler.obtainMessage());
            }
        });
        builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
                multiResult = list.toArray(new Integer[0]);
                handler.sendMessage(handler.obtainMessage());
            }
        });
        builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
                multiResult = null;
                handler.sendMessage(handler.obtainMessage());
            }
        });
        builder2.create().show();
        handler = new 对话框.DialogHandler(Looper.getMainLooper());
        try {
            Looper.getMainLooper();
            Looper.loop();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return multiResult;
    }

    public void 置项目(String[] 欲设置项目, boolean[] 选中项数组) {
        builder.setMultiChoiceItems(欲设置项目, 选中项数组, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2, boolean p3) {
                项目被单击(p2, p3);
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
            iOException.printStackTrace();
        }
    }

    public void 图标(int n) {
        this.builder.setIcon(n);
    }

    public void 按钮1(String 按钮1文本) {
        builder.setNegativeButton(按钮1文本, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
                按钮2被单击();
            }
        });

    }

    public void 按钮2(String 按钮2文本) {
        builder.setNegativeButton(按钮2文本, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
                按钮2被单击();
            }
        });

    }

    public void 按钮3(String 按钮3文本) {
        builder.setNeutralButton(按钮3文本, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
                按钮3被单击();
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


    @Override
    public void 初始化事件() {
        super.初始化事件();
    }


    public void 按钮3被单击() {

    }




    public void 按钮2被单击() {

    }


    public void 按钮1被单击() {

    }


    public void 项目被单击(int var1, boolean var2) {

    }

}

