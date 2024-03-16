/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.os.Looper
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.view.View
 *  android.widget.EditText
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
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.IOException;

public class 输入对话框
        extends 组件 {
    private static 对话框.DialogHandler handler;
    private static String inputResult;
    private final EditText et;
    private AlertDialog dialog;
    private final AlertDialog.Builder builder;
    private 输入内容被改变 $event_internal_输入内容被改变;
    private 按钮1被单击 $event_internal_按钮1被单击;
    private 按钮2被单击 $event_internal_按钮2被单击;
    private 按钮3被单击 $event_internal_按钮3被单击;

    public 输入对话框(Context context) {
        super(context);
        this.builder = new AlertDialog.Builder(context);
        this.et = new EditText(context);
        this.builder.setView(this.et);
        this.et.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                if (输入对话框.this.$event_internal_输入内容被改变 != null) {
                    输入对话框.this.$event_internal_输入内容被改变.输入内容被改变(charSequence.toString());
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public static String inputBox(Context context, String string, String string2, String string3) {
        return 输入对话框.inputBox(context, string, string2, string3, 0);
    }

    public static String inputBox(Context context, String string, String string2) {
        return 输入对话框.inputBox(context, string, string2, "", 0);
    }

    public static String inputBox(Context context, String string) {
        return 输入对话框.inputBox(context, string, "", "", 0);
    }

    public static String passwordInputBox(Context context, String string, String string2) {
        return 输入对话框.inputBox(context, string, "", string2, 129);
    }

    public static String passwordInputBox(Context context, String string) {
        return 输入对话框.inputBox(context, string, "", "", 129);
    }

    public static String inputBox(Context context, String string, String string2, String string3, int n) {
        final EditText editText = new EditText(context);
        if (n != 0) {
            editText.setInputType(n);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!string.equals("")) {
            builder.setTitle(string);
        }
        builder.setView(editText);
        builder.setCancelable(false);
        editText.setHint(string3);
        editText.setText(string2);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                inputResult = editText.getText().toString();
                handler.sendMessage(handler.obtainMessage());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                inputResult = "";
                handler.sendMessage(handler.obtainMessage());
            }
        });
        builder.create().show();
        handler = new 对话框.DialogHandler(Looper.getMainLooper());
        try {
            Looper.getMainLooper();
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
        }
        return inputResult;
    }

    public void 内容(String string) {
        this.et.setText(string);
    }

    public String 内容() {
        return this.et.getText().toString();
    }

    public void 提示文本(String string) {
        this.et.setHint(string);
    }

    public String 提示文本() {
        return this.et.getHint().toString();
    }

    public void 提示文本颜色(int n) {
        this.et.setHintTextColor(n);
    }

    public void 提示文本颜色(String string) {
        this.提示文本颜色(Color.parseColor(string));
    }

    public int 提示文本颜色() {
        return this.et.getHintTextColors().getDefaultColor();
    }

    public void 字体大小(int n) {
        this.et.setTextSize((float) n);
    }

    public int 字体大小() {
        return (int) this.et.getTextSize();
    }

    public void 字体颜色(int n) {
        this.et.setTextColor(n);
    }

    public int 字体颜色() {
        return this.et.getTextColors().getDefaultColor();
    }

    public void 字体颜色(String string) {
        this.字体颜色(Color.parseColor(string));
    }

    public void 密码输入(boolean bl) {
        if (bl) {
            this.et.setInputType(129);
        } else {
            this.et.setInputType(0);
        }
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

    public void 按钮1(String string) {
        this.builder.setPositiveButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (输入对话框.this.$event_internal_按钮1被单击 != null) {
                    输入对话框.this.$event_internal_按钮1被单击.按钮1被单击();
                }
            }
        });
    }

    public void 按钮2(String string) {
        this.builder.setNegativeButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (输入对话框.this.$event_internal_按钮2被单击 != null) {
                    输入对话框.this.$event_internal_按钮2被单击.按钮2被单击();
                }
            }
        });
    }

    public void 按钮3(String string) {
        this.builder.setNeutralButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (输入对话框.this.$event_internal_按钮3被单击 != null) {
                    输入对话框.this.$event_internal_按钮3被单击.按钮3被单击();
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

    public void 置输入内容被改变(输入内容被改变 输入内容被改变2) {
        this.$event_internal_输入内容被改变 = 输入内容被改变2;
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

    public interface 输入内容被改变 {
        void 输入内容被改变(String var1);
    }
}

