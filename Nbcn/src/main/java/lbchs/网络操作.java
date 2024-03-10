/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.File
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs;

import android.content.Context;

import java.io.File;

public class 网络操作
        extends 组件 {
    private 取网页源码结束 $event_internal_取网页源码结束;
    private 发送数据结束 $event_internal_发送数据结束;
    private 正在下载 $event_internal_正在下载;
    private 下载结束 $event_internal_下载结束;
    private 正在上传 $event_internal_正在上传;
    private 上传结束 $event_internal_上传结束;
    private 取网页源码失败 $event_internal_取网页源码失败;
    private 发送数据失败 $event_internal_发送数据失败;
    private 下载失败 $event_internal_下载失败;
    private 上传失败 $event_internal_上传失败;

    public 网络操作(Context context) {
        super(context);
    }

    public 网络操作() {
    }

    public static void 添加请求头(String string, String string2) {
        EasyHttp.setRequestProperty(string, string2);
    }

    public static void 置请求头(String string) {
        String[] stringArray;
        for (String string2 : stringArray = string.split(",")) {
            String[] stringArray2 = string2.split("=");
            for (int i = 0; i < stringArray2.length; ++i) {
                EasyHttp.setRequestProperty(stringArray2[0], stringArray2[1]);
            }
        }
    }

    public static String 取请求头() {
        return EasyHttp.getHeader().toString();
    }

    public static void 清除请求头() {
        EasyHttp.getHeader().clear();
    }

    public static String 取网页源码_同步(String string) {
        return EasyHttp.get_sync(string);
    }

    public static String 取网页源码_同步(String string, String string2) {
        return EasyHttp.get_sync(string, string2);
    }

    public static String 取网页源码_同步(String string, String string2, String string3) {
        return EasyHttp.get_sync(string, string2, string3);
    }

    public static String 发送数据_同步(String string, String string2) {
        return EasyHttp.post_sync(string, string2);
    }

    public static String 发送数据_同步(String string, String string2, String string3) {
        return EasyHttp.post_sync(string, string2, string3);
    }

    public static String 发送数据_同步(String string, String string2, String string3, String string4) {
        return EasyHttp.post_sync(string, string2, string3, string4);
    }

    public void 取网页源码(String string) {
        EasyHttp.get(string, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_取网页源码结束 != null) {
                    网络操作.this.$event_internal_取网页源码结束.取网页源码结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_取网页源码失败 != null) {
                    网络操作.this.$event_internal_取网页源码失败.取网页源码失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
            }
        });
    }

    public void 取网页源码(String string, String string2) {
        EasyHttp.get(string, string2, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_取网页源码结束 != null) {
                    网络操作.this.$event_internal_取网页源码结束.取网页源码结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_取网页源码失败 != null) {
                    网络操作.this.$event_internal_取网页源码失败.取网页源码失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
            }
        });
    }

    public void 取网页源码(String string, String string2, String string3) {
        EasyHttp.get(string, string2, string3, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_取网页源码结束 != null) {
                    网络操作.this.$event_internal_取网页源码结束.取网页源码结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_取网页源码失败 != null) {
                    网络操作.this.$event_internal_取网页源码失败.取网页源码失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
            }
        });
    }

    public void 发送数据(String string, String string2) {
        EasyHttp.post(string, string2, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_发送数据结束 != null) {
                    网络操作.this.$event_internal_发送数据结束.发送数据结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_发送数据失败 != null) {
                    网络操作.this.$event_internal_发送数据失败.发送数据失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
            }
        });
    }

    public void 发送数据(String string, String string2, String string3) {
        EasyHttp.post(string, string2, string3, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_发送数据结束 != null) {
                    网络操作.this.$event_internal_发送数据结束.发送数据结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_发送数据失败 != null) {
                    网络操作.this.$event_internal_发送数据失败.发送数据失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
            }
        });
    }

    public void 发送数据(String string, String string2, String string3, String string4) {
        EasyHttp.post(string, string2, string3, string4, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_发送数据结束 != null) {
                    网络操作.this.$event_internal_发送数据结束.发送数据结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_发送数据失败 != null) {
                    网络操作.this.$event_internal_发送数据失败.发送数据失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
            }
        });
    }

    public void 下载(String string, String string2) {
        EasyHttp.download(string, string2, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_下载结束 != null) {
                    网络操作.this.$event_internal_下载结束.下载结束(string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_下载失败 != null) {
                    网络操作.this.$event_internal_下载失败.下载失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
                if (网络操作.this.$event_internal_正在下载 != null) {
                    网络操作.this.$event_internal_正在下载.正在下载(n);
                }
            }
        });
    }

    public void 下载(String string, String string2, String string3) {
        EasyHttp.download(string, string2, string3, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_下载结束 != null) {
                    网络操作.this.$event_internal_下载结束.下载结束(string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_下载失败 != null) {
                    网络操作.this.$event_internal_下载失败.下载失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
                if (网络操作.this.$event_internal_正在下载 != null) {
                    网络操作.this.$event_internal_正在下载.正在下载(n);
                }
            }
        });
    }

    public void 上传(String string, String string2) {
        EasyHttp.upload(string, string2, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_上传结束 != null) {
                    网络操作.this.$event_internal_上传结束.上传结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_上传失败 != null) {
                    网络操作.this.$event_internal_上传失败.上传失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
                if (网络操作.this.$event_internal_正在上传 != null) {
                    网络操作.this.$event_internal_正在上传.正在上传(n);
                }
            }
        });
    }

    public void 上传(String string, String string2, String string3) {
        EasyHttp.upload(string, string2, string3, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_上传结束 != null) {
                    网络操作.this.$event_internal_上传结束.上传结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_上传失败 != null) {
                    网络操作.this.$event_internal_上传失败.上传失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
                if (网络操作.this.$event_internal_正在上传 != null) {
                    网络操作.this.$event_internal_正在上传.正在上传(n);
                }
            }
        });
    }

    public void 上传2(String string, String string2, String string3, String string4) {
        EasyHttp.upload2(string, new Object[]{string2, new File(string3), string4}, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_上传结束 != null) {
                    网络操作.this.$event_internal_上传结束.上传结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_上传失败 != null) {
                    网络操作.this.$event_internal_上传失败.上传失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
                if (网络操作.this.$event_internal_正在上传 != null) {
                    网络操作.this.$event_internal_正在上传.正在上传(n);
                }
            }
        });
    }

    public void 上传2(String string, String string2, String string3, String string4, String string5) {
        EasyHttp.upload2(string, new Object[]{string2, new File(string3), string4}, string5, new EasyHttp.OnRequestListener() {

            @Override
            public void onCompleted(String string, String string2, byte[] byArray, String string3) {
                if (网络操作.this.$event_internal_上传结束 != null) {
                    网络操作.this.$event_internal_上传结束.上传结束(string2, string3);
                }
            }

            @Override
            public void onFailed(String string, String string2, byte[] byArray) {
                if (网络操作.this.$event_internal_上传失败 != null) {
                    网络操作.this.$event_internal_上传失败.上传失败(string);
                }
            }

            @Override
            public void onProgressChanged(int n) {
                if (网络操作.this.$event_internal_正在上传 != null) {
                    网络操作.this.$event_internal_正在上传.正在上传(n);
                }
            }
        });
    }

    public void 置取网页源码结束(取网页源码结束 取网页源码结束2) {
        this.$event_internal_取网页源码结束 = 取网页源码结束2;
    }

    public void 置发送数据结束(发送数据结束 发送数据结束2) {
        this.$event_internal_发送数据结束 = 发送数据结束2;
    }

    public void 置正在下载(正在下载 正在下载2) {
        this.$event_internal_正在下载 = 正在下载2;
    }

    public void 置下载结束(下载结束 下载结束2) {
        this.$event_internal_下载结束 = 下载结束2;
    }

    public void 置正在上传(正在上传 正在上传2) {
        this.$event_internal_正在上传 = 正在上传2;
    }

    public void 置上传结束(上传结束 上传结束2) {
        this.$event_internal_上传结束 = 上传结束2;
    }

    public void 置取网页源码失败(取网页源码失败 取网页源码失败2) {
        this.$event_internal_取网页源码失败 = 取网页源码失败2;
    }

    public void 置发送数据失败(发送数据失败 发送数据失败2) {
        this.$event_internal_发送数据失败 = 发送数据失败2;
    }

    public void 置下载失败(下载失败 下载失败2) {
        this.$event_internal_下载失败 = 下载失败2;
    }

    public void 置上传失败(上传失败 上传失败2) {
        this.$event_internal_上传失败 = 上传失败2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 上传失败 {
        void 上传失败(String var1);
    }

    public interface 下载失败 {
        void 下载失败(String var1);
    }

    public interface 发送数据失败 {
        void 发送数据失败(String var1);
    }

    public interface 取网页源码失败 {
        void 取网页源码失败(String var1);
    }

    public interface 上传结束 {
        void 上传结束(String var1, String var2);
    }

    public interface 正在上传 {
        void 正在上传(int var1);
    }

    public interface 下载结束 {
        void 下载结束(String var1);
    }

    public interface 正在下载 {
        void 正在下载(int var1);
    }

    public interface 发送数据结束 {
        void 发送数据结束(String var1, String var2);
    }

    public interface 取网页源码结束 {
        void 取网页源码结束(String var1, String var2);
    }
}

