/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Object
 */
package jiesheng;

public class 上下文操作 {
    private static 安卓应用 application;

    private 上下文操作() {
    }

    public static 安卓应用 取全局上下文() {
        return application;
    }

    public static void 置全局上下文(安卓应用 安卓应用2) {
        application = 安卓应用2;
    }

    public void 初始化事件() {
    }
}

