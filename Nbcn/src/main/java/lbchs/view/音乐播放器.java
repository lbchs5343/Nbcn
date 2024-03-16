/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnBufferingUpdateListener
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.media.MediaPlayer$OnPreparedListener
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.util.Timer
 *  java.util.TimerTask
 */
package lbchs.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class 音乐播放器
        extends 组件 {
    private final MediaPlayer player = new MediaPlayer();
    private final Timer mTimer = new Timer();
    private final TimerTask mTimerTask;
    private final Handler handleProgress;
    private boolean autoPlay = true;
    private 音乐正在缓冲 $event_internal_音乐正在缓冲;
    private 音乐缓冲完毕 $event_internal_音乐缓冲完毕;
    private 音乐正在播放 $event_internal_音乐正在播放;
    private 音乐播放完毕 $event_internal_音乐播放完毕;

    public 音乐播放器(Context context) {
        super(context);
        this.player.setAudioStreamType(3);
        this.player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mediaPlayer, int n) {
                if (音乐播放器.this.$event_internal_音乐正在缓冲 != null) {
                    音乐播放器.this.$event_internal_音乐正在缓冲.音乐正在缓冲(n);
                }
            }
        });
        this.player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                if (音乐播放器.this.autoPlay) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.start();
                    } else {
                        mediaPlayer.start();
                    }
                }
                if (音乐播放器.this.$event_internal_音乐缓冲完毕 != null) {
                    音乐播放器.this.$event_internal_音乐缓冲完毕.音乐缓冲完毕();
                }
            }
        });
        this.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mediaPlayer) {
                if (音乐播放器.this.$event_internal_音乐播放完毕 != null) {
                    音乐播放器.this.$event_internal_音乐播放完毕.音乐播放完毕();
                }
            }
        });
        this.mTimerTask = new TimerTask() {

            public void run() {
                if (音乐播放器.this.player.isPlaying()) {
                    音乐播放器.this.handleProgress.sendEmptyMessage(0);
                }
            }
        };
        this.mTimer.schedule(this.mTimerTask, 0L, 1000L);
        this.handleProgress = new HandleProgressHandler(this);


    }

    public 音乐播放器() {
        this.player.setAudioStreamType(3);
        this.player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mediaPlayer, int n) {
                if (音乐播放器.this.$event_internal_音乐正在缓冲 != null) {
                    音乐播放器.this.$event_internal_音乐正在缓冲.音乐正在缓冲(n);
                }
            }
        });
        this.player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                if (音乐播放器.this.autoPlay) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.start();
                    } else {
                        mediaPlayer.start();
                    }
                }
                if (音乐播放器.this.$event_internal_音乐缓冲完毕 != null) {
                    音乐播放器.this.$event_internal_音乐缓冲完毕.音乐缓冲完毕();
                }
            }
        });
        this.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mediaPlayer) {
                if (音乐播放器.this.$event_internal_音乐播放完毕 != null) {
                    音乐播放器.this.$event_internal_音乐播放完毕.音乐播放完毕();
                }
            }
        });
        this.mTimerTask = new TimerTask() {

            public void run() {
                if (音乐播放器.this.player.isPlaying()) {
                    音乐播放器.this.handleProgress.sendEmptyMessage(0);
                }
            }
        };
        this.mTimer.schedule(this.mTimerTask, 0L, 1000L);
        this.handleProgress = new HandleProgressHandler(this);


    }

    public void 循环播放(boolean bl) {
        this.player.setLooping(bl);
    }

    public boolean 循环播放() {
        return this.player.isLooping();
    }

    public void 置播放路径(String string) {
        this.置播放路径(string, false);
    }

    public void 置播放路径(String string, boolean bl) {
        this.autoPlay = bl;
        this.player.reset();
        try {
            this.player.setDataSource(string);
            this.player.prepareAsync();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 开始播放() {
        this.player.start();
    }

    public void 暂停播放() {
        this.player.pause();
    }

    public void 停止播放() {
        this.player.stop();
    }

    public int 取音乐总时长() {
        return this.player.getDuration();
    }

    public int 取当前播放位置() {
        return this.player.getCurrentPosition();
    }

    public void 快进至(int n) {
        this.player.seekTo(n);
    }

    public void 置音量(float f, float f2) {
        this.player.setVolume(f, f2);
    }

    public void 重置() {
        this.player.reset();
    }

    public void 释放资源() {
        this.player.release();
    }

    public boolean 是否在播放() {
        return this.player.isPlaying();
    }

    public void 置音乐正在缓冲(音乐正在缓冲 音乐正在缓冲2) {
        this.$event_internal_音乐正在缓冲 = 音乐正在缓冲2;
    }

    public void 置音乐缓冲完毕(音乐缓冲完毕 音乐缓冲完毕2) {
        this.$event_internal_音乐缓冲完毕 = 音乐缓冲完毕2;
    }

    public void 置音乐正在播放(音乐正在播放 音乐正在播放2) {
        this.$event_internal_音乐正在播放 = 音乐正在播放2;
    }

    public void 置音乐播放完毕(音乐播放完毕 音乐播放完毕2) {
        this.$event_internal_音乐播放完毕 = 音乐播放完毕2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 音乐播放完毕 {
        void 音乐播放完毕();
    }

    public interface 音乐正在播放 {
        void 音乐正在播放();
    }

    public interface 音乐缓冲完毕 {
        void 音乐缓冲完毕();
    }

    public interface 音乐正在缓冲 {
        void 音乐正在缓冲(int var1);
    }
    private static class HandleProgressHandler extends Handler {
        private final WeakReference<音乐播放器> mPlayerRef;

        public HandleProgressHandler(音乐播放器 player) {
            mPlayerRef = new WeakReference<>(player);
        }

        @Override
        public void handleMessage(Message message) {
            音乐播放器 player = mPlayerRef.get();
            if (player != null && player.$event_internal_音乐正在播放 != null) {
                player.$event_internal_音乐正在播放.音乐正在播放();
            }
        }
    }
}

