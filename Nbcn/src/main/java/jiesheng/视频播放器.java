/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.media.MediaPlayer$OnErrorListener
 *  android.media.MediaPlayer$OnPreparedListener
 *  android.net.Uri
 *  android.view.View
 *  android.widget.VideoView
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package jiesheng;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.VideoView;

public class 视频播放器
        extends 可视化组件 {
    private boolean autoPlay = false;
    private 视频加载完成 $event_internal_视频加载完成;
    private 视频播放完成 $event_internal_视频播放完成;
    private 视频播放错误 $event_internal_视频播放错误;

    public 视频播放器(Context context) {
        super(context);
        this.getView().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                if (视频播放器.this.autoPlay) {
                    视频播放器.this.getView().start();
                }
                if (视频播放器.this.$event_internal_视频加载完成 != null) {
                    视频播放器.this.$event_internal_视频加载完成.视频加载完成();
                }
            }
        });
        this.getView().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mediaPlayer) {
                if (视频播放器.this.$event_internal_视频播放完成 != null) {
                    视频播放器.this.$event_internal_视频播放完成.视频播放完成();
                }
            }
        });
        this.getView().setOnErrorListener(new MediaPlayer.OnErrorListener() {

            public boolean onError(MediaPlayer mediaPlayer, int n, int n2) {
                if (视频播放器.this.$event_internal_视频播放错误 != null) {
                    视频播放器.this.$event_internal_视频播放错误.视频播放错误();
                }
                return true;
            }
        });
    }

    public VideoView getView() {
        return (VideoView) super.getView();
    }

    @Override
    public View 创建视图() {
        return new VideoView(this.取上下文());
    }

    public void 置播放路径(String string) {
        this.置播放路径(string, false);
    }

    public void 置播放路径(String string, boolean bl) {
        if (string.startsWith("http")) {
            this.getView().setVideoURI(Uri.parse((String) string));
        } else if (string.startsWith("/")) {
            this.getView().setVideoPath(string);
        }
        this.autoPlay = bl;
    }

    public void 开始播放() {
        this.getView().start();
    }

    public void 暂停播放() {
        this.getView().pause();
    }

    public void 停止播放() {
        this.getView().stopPlayback();
    }

    public int 取视频总时长() {
        return this.getView().getDuration();
    }

    public int 取当前播放位置() {
        return this.getView().getCurrentPosition();
    }

    public void 快进至(int n) {
        this.getView().seekTo(n);
    }

    public void 重新播放() {
        this.getView().resume();
    }

    public boolean 是否在播放() {
        return this.getView().isPlaying();
    }

    public void 置视频加载完成(视频加载完成 视频加载完成2) {
        this.$event_internal_视频加载完成 = 视频加载完成2;
    }

    public void 置视频播放完成(视频播放完成 视频播放完成2) {
        this.$event_internal_视频播放完成 = 视频播放完成2;
    }

    public void 置视频播放错误(视频播放错误 视频播放错误2) {
        this.$event_internal_视频播放错误 = 视频播放错误2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 视频播放错误 {
        public void 视频播放错误();
    }

    public static interface 视频播放完成 {
        public void 视频播放完成();
    }

    public static interface 视频加载完成 {
        public void 视频加载完成();
    }
}

