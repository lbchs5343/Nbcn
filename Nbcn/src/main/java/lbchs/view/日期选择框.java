/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.DatePickerDialog
 *  android.app.DatePickerDialog$OnDateSetListener
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.graphics.drawable.Drawable
 *  android.os.Looper
 *  android.widget.DatePicker
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.util.Calendar
 */
package lbchs.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.widget.DatePicker;

import java.io.IOException;

import java.util.Calendar;

public class 日期选择框
        extends 组件 {
    private static 对话框.DialogHandler handler;
    private static String dateResult;
    private final DatePickerDialog dialog;
    private 日期被改变 $event_internal_日期被改变;
    private 日期被确定 $event_internal_日期被确定;
    private 按钮1被单击 $event_internal_按钮1被单击;
    private 按钮2被单击 $event_internal_按钮2被单击;
    private 按钮3被单击 $event_internal_按钮3被单击;

    public 日期选择框(Context context) {
        super(context);
        Calendar calendar = Calendar.getInstance();
        this.dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datePicker, int n, int n2, int n3) {
                if (日期选择框.this.$event_internal_日期被确定 != null) {
                    日期选择框.this.$event_internal_日期被确定.日期被确定(n, n2, n3);
                }
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)) {

            public void onDateChanged(DatePicker datePicker, int n, int n2, int n3) {
                if (日期选择框.this.$event_internal_日期被改变 != null) {
                    日期选择框.this.$event_internal_日期被改变.日期被改变(n, n2, n3);
                }
            }
        };
    }

    public static String datePickerBox(Context context) {
        Calendar calendar = Calendar.getInstance();
        return 日期选择框.datePickerBox(context, calendar.get(1), calendar.get(2), calendar.get(5));
    }

    public static String datePickerBox(Context context, int n, int n2, int n3) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datePicker, int n, int n2, int n3) {
                if (n2 + 1 < 10) {
                    dateResult = n + "/0" + (n2 + 1);
                } else {
                    dateResult = n + "/" + (n2 + 1);
                }
                if (n3 < 10) {
                    dateResult = dateResult + "/0" + n3;
                } else {
                    dateResult = dateResult + "/" + n3;
                }
            }
        }, n, n2, n3) {

            public void onDateChanged(DatePicker datePicker, int n, int n2, int n3) {
                if (n2 + 1 < 10) {
                    dateResult = n + "/0" + (n2 + 1);
                } else {
                    dateResult = n + "/" + (n2 + 1);
                }
                if (n3 < 10) {
                    dateResult = dateResult + "/0" + n3;
                } else {
                    dateResult = dateResult + "/" + n3;
                }
            }
        };
        datePickerDialog.setCancelable(false);
        datePickerDialog.setButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                handler.sendMessage(handler.obtainMessage());
            }
        });
        datePickerDialog.setButton2("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                dateResult = "";
                handler.sendMessage(handler.obtainMessage());
            }
        });
        datePickerDialog.show();
        handler = new 对话框.DialogHandler(Looper.getMainLooper());
        try {
            Looper.getMainLooper();
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
        }
        return dateResult;
    }

    public void 标题(String string) {
        this.dialog.setTitle(string);
    }

    public void 信息(String string) {
        this.dialog.setMessage(string);
    }

    public void 图标(String string) {
        try {
            this.dialog.setIcon(Drawable.createFromStream(this.窗口环境.getAssets().open(string), string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 图标(int n) {
        this.dialog.setIcon(n);
    }

    public void 按钮1(String string) {
        this.dialog.setButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (日期选择框.this.$event_internal_按钮1被单击 != null) {
                    日期选择框.this.$event_internal_按钮1被单击.按钮1被单击();
                }
            }
        });
    }

    public void 按钮2(String string) {
        this.dialog.setButton2(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (日期选择框.this.$event_internal_按钮2被单击 != null) {
                    日期选择框.this.$event_internal_按钮2被单击.按钮2被单击();
                }
            }
        });
    }

    public void 按钮3(String string) {
        this.dialog.setButton3(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (日期选择框.this.$event_internal_按钮3被单击 != null) {
                    日期选择框.this.$event_internal_按钮3被单击.按钮3被单击();
                }
            }
        });
    }

    public void 可取消(boolean bl) {
        this.dialog.setCancelable(bl);
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

    public void 置日期被改变(日期被改变 日期被改变2) {
        this.$event_internal_日期被改变 = 日期被改变2;
    }

    public void 置日期被确定(日期被确定 日期被确定2) {
        this.$event_internal_日期被确定 = 日期被确定2;
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

    public interface 日期被确定 {
        void 日期被确定(int var1, int var2, int var3);
    }

    public interface 日期被改变 {
        void 日期被改变(int var1, int var2, int var3);
    }
}

