/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.ActivityManager
 *  android.app.ActivityManager$MemoryInfo
 *  android.os.Environment
 *  android.os.StatFs
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.io.FileReader
 *  java.io.IOException
 *  java.io.Reader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package jiesheng;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class 存储卡操作 {
    public static boolean 取存储卡状态() {
        return "mounted".equals((Object) Environment.getExternalStorageState());
    }

    public static boolean 存储卡是否可写() {
        return Environment.getExternalStorageDirectory().canWrite();
    }

    public static String 取私有目录路径(Activity activity) {
        return activity.getFilesDir().getAbsolutePath();
    }

    public static String 取存储卡路径() {
        if (存储卡操作.取存储卡状态() && 存储卡操作.存储卡是否可写()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }

    public static long 取存储卡总容量() {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long l = statFs.getBlockSize();
        long l2 = statFs.getBlockCount();
        return l2 * l / 1024L / 1024L;
    }

    public static long 取存储卡剩余容量() {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long l = statFs.getBlockSize();
        long l2 = statFs.getAvailableBlocks();
        return l2 * l / 1024L / 1024L;
    }

    public static long 取内部存储卡总容量() {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long l = statFs.getBlockSize();
        long l2 = statFs.getBlockCount();
        return l2 * l / 1024L / 1024L;
    }

    public static long 取内部存储卡剩余容量() {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long l = statFs.getBlockSize();
        long l2 = statFs.getAvailableBlocks();
        return l2 * l / 1024L / 1024L;
    }

    public static long 取手机总内存() {
        String string = "/proc/meminfo";
        long l = 0L;
        try {
            FileReader fileReader = new FileReader(string);
            BufferedReader bufferedReader = new BufferedReader((Reader) fileReader, 8192);
            String string2 = bufferedReader.readLine();
            String[] stringArray = string2.split("\\s+");
            l = Integer.valueOf((String) stringArray[1]).intValue();
            bufferedReader.close();
        } catch (IOException iOException) {
            return 0L;
        }
        return l > 0L ? l / 1024L : 0L;
    }

    public static long 取手机剩余内存(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / 1024L / 1024L;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static double 取CPU主频() {
        int n = 0;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            bufferedReader = new BufferedReader((Reader) fileReader);
            String string = bufferedReader.readLine();
            n = Integer.parseInt((String) string.trim());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return n / 1000 / 1000;
    }

    public void 初始化事件() {
    }
}

