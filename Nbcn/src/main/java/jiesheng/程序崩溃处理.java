/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Looper
 *  java.io.File
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.StackTraceElement
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 *  java.lang.Thread$UncaughtExceptionHandler
 *  java.lang.Throwable
 */
package jiesheng;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;

import java.io.File;

public class 程序崩溃处理
        implements Thread.UncaughtExceptionHandler {
    private static 程序崩溃处理 INSTANCE = new 程序崩溃处理();
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;
    private String path;

    private 程序崩溃处理() {
    }

    public static void 初始化(Context context, String string) {
        INSTANCE.init(context, string);
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!this.handleException(throwable) && this.mDefaultHandler != null) {
            this.mDefaultHandler.uncaughtException(thread, throwable);
        }
    }

    private boolean handleException(Throwable throwable) {
        if (throwable == null) {
            return false;
        }
        new Thread() {

            public void run() {
                Looper.prepare();
                Looper.loop();
            }
        }.start();
        this.sendError(throwable);
        return true;
    }

    private void sendError(Throwable throwable) {
        StackTraceElement[] stackTraceElementArray;
        StringBuilder stringBuilder = new StringBuilder();
        Throwable throwable2 = throwable.getCause();
        Intent intent = new Intent();
        intent.setPackage("com.develop.s5droid");
        intent.setAction("com.s5droid.LOGCAT");
        intent.putExtra("msg", "[运行异常]Caused by: " + throwable2.toString());
        this.mContext.sendBroadcast(intent);
        stringBuilder.append("Caused by: ").append(throwable2.toString());
        for (StackTraceElement stackTraceElement : stackTraceElementArray = throwable2.getStackTrace()) {
            intent.putExtra("msg", "[运行异常]\tat " + stackTraceElement.toString());
            this.mContext.sendBroadcast(intent);
            stringBuilder.append("\n").append("\tat ").append(stackTraceElement.toString());
        }
        if (this.path != null && !"".equals((Object) this.path)) {
            try {
                FileUtils.write(new File(this.path), stringBuilder.toString());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void init(Context context, String string) {
        this.mContext = context;
        this.path = string;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler) this);
    }

    public void 初始化事件() {
    }
}

