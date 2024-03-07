/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.ActivityManager
 *  android.app.ActivityManager$RunningAppProcessInfo
 *  android.content.ClipboardManager
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.Intent$ShortcutIconResource
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.graphics.Rect
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Debug$MemoryInfo
 *  android.os.Parcelable
 *  android.provider.Settings$Global
 *  android.provider.Settings$System
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.WindowManager
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.OutputStream
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.List
 *  java.util.UUID
 */
package jiesheng;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

public class 系统操作 {
    private static String clipText;

    public static int 取屏幕宽度(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int 取屏幕高度(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int 取屏幕高度_不含导航栏(Context context) {
        if (!系统操作.导航栏是否显示(context)) {
            return 系统操作.取屏幕高度(context);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int n = displayMetrics.heightPixels;
        if (Build.MANUFACTURER.equals((Object) "Xiaomi") && Settings.Global.getInt((ContentResolver) context.getContentResolver(), (String) "force_fsg_nav_bar", (int) 0) != 0) {
            n += 系统操作.取导航栏高度(context);
        }
        if (系统操作.取导航栏高度(context) + n < 系统操作.取屏幕高度(context)) {
            return n + 系统操作.取状态栏高度(context);
        }
        return n;
    }

    public static double 取屏幕密度(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public static int 取状态栏高度(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                Class clazz = Class.forName((String) "com.android.internal.R$dimen");
                return context.getResources().getDimensionPixelSize(Integer.parseInt((String) clazz.getField("status_bar_height").get(clazz.newInstance()).toString()));
            } catch (Exception exception) {
                exception.printStackTrace();
                return 0;
            }
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int 取导航栏高度(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static boolean 导航栏是否显示(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (Build.MANUFACTURER.equals((Object) "Xiaomi") && Settings.Global.getInt((ContentResolver) context.getContentResolver(), (String) "force_fsg_nav_bar", (int) 0) != 0) {
            return false;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        int n = displayMetrics.heightPixels;
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return n - displayMetrics.heightPixels - 系统操作.取状态栏高度(context) > 0;
    }

    public static void 创建快捷方式(Activity activity, String string, int n) {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("android.intent.extra.shortcut.NAME", string);
        intent.putExtra("duplicate", false);
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.setClassName((Context) activity, activity.getClass().getName());
        intent.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable) intent2);
        Intent.ShortcutIconResource shortcutIconResource = Intent.ShortcutIconResource.fromContext((Context) activity, (int) n);
        intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable) shortcutIconResource);
        activity.sendBroadcast(intent);
    }

    public static boolean 是否已创建快捷方式(Activity activity, String string) {
        Uri uri;
        boolean bl = false;
        ContentResolver contentResolver = activity.getContentResolver();
        Cursor cursor = contentResolver.query(uri = Uri.parse((String) "content://com.android.launcher.settings/favorites?notify=true"), new String[]{"title", "iconResource"}, "title=?", new String[]{string}, null);
        if (cursor != null && cursor.getCount() > 0) {
            bl = true;
        } else {
            Uri uri2 = Uri.parse((String) "content://com.android.launcher2.settings/favorites?notify=true");
            Cursor cursor2 = contentResolver.query(uri2, new String[]{"title", "iconResource"}, "title=?", new String[]{string}, null);
            if (cursor2 != null && cursor2.getCount() > 0) {
                bl = true;
            }
        }
        return bl;
    }

    public static String 取进程列表(Activity 窗口环境) {
        String result = "";

        ActivityManager mActivityManager = (ActivityManager) 窗口环境.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
            int pid = appProcessInfo.pid;
            String processName = appProcessInfo.processName;
            int[] myMempid = {pid};
            Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(myMempid);
            int memSize = memoryInfo[0].dalvikPrivateDirty;
            if (result.equals(""))
                result = Integer.toString(pid) + "-" + processName + "-" + Integer.toString(memSize);
            else {
                result = result + "\n" + Integer.toString(pid) + "-" + processName + "-" + Integer.toString(memSize);
            }
        }
        return result;
    }

    public static void 延时(int n) {
        try {
            Thread.currentThread();
            Thread.sleep((long) n);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public static void 置剪切板文本(Activity activity, String string) {
        ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
        clipboardManager.setText((CharSequence) string);
    }

    public static String 取剪切板文本(final Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            activity.getWindow().getDecorView().post(new Runnable() {

                public void run() {
                    ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
                    if (clipboardManager != null && clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClip().getItemCount() > 0) {
                        CharSequence charSequence = clipboardManager.getPrimaryClip().getItemAt(0).getText();
                        clipText = String.valueOf((Object) charSequence);
                    }
                }
            });
        } else {
            ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService("clipboard");
            if (clipboardManager.hasText()) {
                clipText = clipboardManager.getText().toString();
            }
        }
        return clipText;
    }

    public static String 取ANDROID_ID(Activity activity) {
        return Settings.System.getString((ContentResolver) activity.getContentResolver(), (String) "android_id");
    }

    public static String 取设备唯一标识符() {
        String string = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
        String string2 = "serial";
        try {
            string2 = Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new UUID((long) string.hashCode(), (long) string2.hashCode()).toString();
    }

    public static void 隐藏状态栏(Activity activity) {
        activity.getWindow().clearFlags(2048);
        activity.getWindow().setFlags(1024, 1024);
    }

    public static void 显示状态栏(Activity activity) {
        activity.getWindow().clearFlags(1024);
        activity.getWindow().setFlags(2048, 2048);
    }

    public static void 置屏幕方向(Activity activity, int n) {
        activity.setRequestedOrientation(n);
    }

    public static void 加载so库(String string) {
        if (string.startsWith("/")) {
            System.load((String) string);
        } else {
            System.loadLibrary((String) string);
        }
    }

    public static void 优化内存() {
        System.gc();
    }

    public static void 屏幕截图(Activity activity, String string) {
        系统操作.savePic(系统操作.takeScreenShot(activity), string);
    }

    public static String 取自身包名(Context context) {
        return context.getPackageName();
    }

    public static int 取自身版本号(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException nameNotFoundException) {
            nameNotFoundException.printStackTrace();
            return 0;
        }
    }

    public static int 取系统版本号() {
        return Build.VERSION.SDK_INT;
    }

    public static int 取资源索引(Activity activity, String string, String string2) {
        Resources resources = activity.getResources();
        return resources.getIdentifier(string, string2, activity.getPackageName());
    }

    public static String 生成GUID() {
        UUID uUID = UUID.randomUUID();
        String string = uUID.toString();
        return string;
    }

    private static Bitmap takeScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int n = rect.top;
        int n2 = activity.getWindowManager().getDefaultDisplay().getWidth();
        int n3 = activity.getWindowManager().getDefaultDisplay().getHeight();
        Bitmap bitmap2 = Bitmap.createBitmap((Bitmap) bitmap, (int) 0, (int) n, (int) n2, (int) (n3 - n));
        view.destroyDrawingCache();
        return bitmap2;
    }

    private static Bitmap takeScreenShot2(Activity activity, int n, int n2, int n3, int n4) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Bitmap bitmap2 = Bitmap.createBitmap((Bitmap) bitmap, (int) n, (int) n2, (int) n3, (int) n4);
        view.destroyDrawingCache();
        return bitmap2;
    }

    private static void savePic(Bitmap bitmap, String string) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(string);
            if (null != fileOutputStream) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, (OutputStream) fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            throw new RuntimeException("屏幕截图 未找到文件:" + string);
        } catch (IOException iOException) {
            iOException.printStackTrace();
            throw new RuntimeException("屏幕截图 没有存储权限");
        }
    }

    public void 初始化事件() {
    }
}

