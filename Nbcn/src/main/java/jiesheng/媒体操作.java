/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnBufferingUpdateListener
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.media.MediaPlayer$OnPreparedListener
 *  java.io.File
 *  java.lang.Exception
 *  java.lang.IllegalArgumentException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 */
package jiesheng;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

import java.io.File;

public class 媒体操作 {
    private static int MAX_VOLUME = 100;
    private static int Progress;
    private static MediaPlayer mp;
    private static boolean over;

    public static void 播放音乐(String string) {
        Progress = 0;
        mp = new MediaPlayer();
        over = false;
        try {
            if (string.startsWith("/")) {
                if (new File(string).exists()) {
                    mp.setDataSource(string);
                }
            } else if (string.startsWith("http://")) {
                mp.setDataSource(string);
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

                    public void onBufferingUpdate(MediaPlayer mediaPlayer, int n) {
                        媒体操作.setProgress(n);
                    }
                });
            }
            mp.prepareAsync();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("播放音频文件地址不正确");
        }
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

    public static void 播放assets音频(Activity activity, String string) {
        try {
            String string2 = activity.getFilesDir().getAbsolutePath() + "/assets/" + string;
            if (!new File(string2).exists()) {
                文件操作.写出资源文件((Context) activity, string, string2);
            }
            媒体操作.播放音乐(string2);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("播放音频文件地址不正确");
        }
    }

    public static int 取缓冲进度() {
        return Progress;
    }

    public static void 停止播放() {
        if (!over && mp != null) {
            try {
                over = true;
                mp.stop();
                mp.release();
            } catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
            }
        }
    }

    public static void 暂停播放() {
        if (!over && mp != null) {
            try {
                mp.pause();
            } catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
            }
        }
    }

    public static void 继续播放() {
        if (!over && mp != null) {
            try {
                mp.start();
            } catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
            }
        }
    }

    public static boolean 是否循环播放() {
        if (over && mp == null) {
            return false;
        }
        return mp.isLooping();
    }

    public static void 置循环播放(boolean bl) {
        if (!over && mp != null) {
            mp.setLooping(bl);
        }
    }

    public static int 取音乐时长() {
        if (over && mp == null) {
            return 0;
        }
        return mp.getDuration();
    }

    public static int 取播放位置() {
        if (over && mp == null) {
            return 0;
        }
        return mp.getCurrentPosition();
    }

    public static void 置播放位置(int n) {
        if (!over && mp != null) {
            mp.seekTo(n);
        }
    }

    public static void 置播放音量(int n) {
        if (!over && mp != null) {
            float f = (float) (1.0 - Math.log((double) (MAX_VOLUME - n)) / Math.log((double) MAX_VOLUME));
            mp.setVolume(f, f);
        }
    }

    public static boolean 取播放状态() {
        if (over && mp == null) {
            return false;
        }
        return mp.isPlaying();
    }

    private static void setProgress(int n) {
        Progress = n;
    }

    public void 初始化事件() {
    }
}

