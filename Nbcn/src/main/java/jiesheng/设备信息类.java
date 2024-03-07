/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 *  java.lang.String
 */
package jiesheng;

import android.os.Build;

public class 设备信息类 {
    public static final int 安卓版本号 = Build.VERSION.SDK_INT;
    public static final String 主板信息 = Build.BOARD;
    public static final String 系统启动程序版本号 = Build.BOOTLOADER;
    public static final String 品牌 = Build.BRAND;
    public static final String CPU指令集 = Build.CPU_ABI;
    public static final String CPU指令集2 = Build.CPU_ABI2;
    public static final String 设备参数 = Build.DEVICE;
    public static final String 显示屏参数 = Build.DISPLAY;
    public static final String 唯一识别码 = Build.FINGERPRINT;
    public static final String 硬件名称 = Build.HARDWARE;
    public static final String 硬件制造商 = Build.MANUFACTURER;
    public static final String 硬件序列号 = Build.SERIAL;
    public static final String 用户可见名称 = Build.MODEL;
    public static final String 产品名称 = Build.PRODUCT;
    public static final String 无线电固件版本 = Build.RADIO;

    public void 初始化事件() {
    }
}

