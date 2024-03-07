/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.TimePickerDialog
 *  android.app.TimePickerDialog$OnTimeSetListener
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.graphics.drawable.Drawable
 *  android.os.Looper
 *  android.widget.TimePicker
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.util.Calendar
 */
package jiesheng;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.widget.TimePicker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class 时间选择框
        extends 组件 {
    private static 对话框.DialogHandler handler;
    private static String timeResult;
    private TimePickerDialog dialog;
    private 时间被改变 $event_internal_时间被改变;
    private 时间被确定 $event_internal_时间被确定;
    private 按钮1被单击 $event_internal_按钮1被单击;
    private 按钮2被单击 $event_internal_按钮2被单击;
    private 按钮3被单击 $event_internal_按钮3被单击;

    public 时间选择框(Context context) {
        super(context);
        Calendar calendar = Calendar.getInstance();
        this.dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker timePicker, int n, int n2) {
                if (时间选择框.this.$event_internal_时间被确定 != null) {
                    时间选择框.this.$event_internal_时间被确定.时间被确定(n, n2);
                }
            }
        }, calendar.get(11), calendar.get(12), true) {

            public void onTimeChanged(TimePicker timePicker, int n, int n2) {
                if (时间选择框.this.$event_internal_时间被改变 != null) {
                    时间选择框.this.$event_internal_时间被改变.时间被改变(n, n2);
                }
            }
        };
    }

    public static String timePickerBox(Context context) {
        Calendar calendar = Calendar.getInstance();
        return 时间选择框.timePickerBox(context, calendar.get(11), calendar.get(12));
    }

    public static String timePickerBox(Context context, int n, int n2) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker timePicker, int n, int n2) {
                if (n < 10) {
                    timeResult = "0" + n;
                } else {
                    timeResult = "" + n;
                }
                if (n2 < 10) {
                    timeResult = timeResult + ":0" + n2;
                } else {
                    timeResult = timeResult + ":" + n2;
                }
            }
        }, n, n2, true) {

            public void onTimeChanged(TimePicker timePicker, int n, int n2) {
                if (n < 10) {
                    timeResult = "0" + n;
                } else {
                    timeResult = "" + n;
                }
                if (n2 < 10) {
                    timeResult = timeResult + ":0" + n2;
                } else {
                    timeResult = timeResult + ":" + n2;
                }
            }
        };
        timePickerDialog.setCancelable(false);
        timePickerDialog.setButton((CharSequence) "确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                handler.sendMessage(handler.obtainMessage());
            }
        });
        timePickerDialog.setButton2((CharSequence) "取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                timeResult = "";
                handler.sendMessage(handler.obtainMessage());
            }
        });
        timePickerDialog.show();
        handler = new 对话框.DialogHandler(Looper.getMainLooper());
        try {
            Looper.getMainLooper();
            Looper.loop();
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
        }
        return timeResult;
    }

    public void 标题(String string) {
        this.dialog.setTitle((CharSequence) string);
    }

    public void 信息(String string) {
        this.dialog.setMessage((CharSequence) string);
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
                if (时间选择框.this.$event_internal_按钮1被单击 != null) {
                    时间选择框.this.$event_internal_按钮1被单击.按钮1被单击();
                }
            }
        });
    }

    public void 按钮2(String string) {
        this.dialog.setButton2((CharSequence) string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (时间选择框.this.$event_internal_按钮2被单击 != null) {
                    时间选择框.this.$event_internal_按钮2被单击.按钮2被单击();
                }
            }
        });
    }

    public void 按钮3(String string) {
        this.dialog.setButton3((CharSequence) string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (时间选择框.this.$event_internal_按钮3被单击 != null) {
                    时间选择框.this.$event_internal_按钮3被单击.按钮3被单击();
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

    public void 置时间被改变(时间被改变 时间被改变2) {
        this.$event_internal_时间被改变 = 时间被改变2;
    }

    public void 置时间被确定(时间被确定 时间被确定2) {
        this.$event_internal_时间被确定 = 时间被确定2;
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

    public static interface 时间被确定 {
        public void 时间被确定(int var1, int var2);
    }

    public static interface 时间被改变 {
        public void 时间被改变(int var1, int var2);
    }
}

