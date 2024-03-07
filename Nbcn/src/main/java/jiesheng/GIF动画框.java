/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  java.io.InputStream
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package jiesheng;

import android.content.Context;
import android.view.View;

import java.io.InputStream;

public class GIF动画框
        extends 可视化组件 {
    public GIF动画框(Context context) {
        super(context);
    }

    @Override
    public View 创建视图() {
        return new GIFView(this.取上下文());
    }

    public void 动画资源(String string) {
        if (string.startsWith("R.")) {
            String[] stringArray = string.split(".");
            this.getView().setMovieResource(this.取上下文().getResources().getIdentifier(stringArray[2], stringArray[1], this.取上下文().getPackageName()));
        } else if (string.startsWith("/")) {
            this.getView().setMovieResource(string);
        } else {
            try {
                InputStream inputStream = this.取上下文().getAssets().open(string);
                this.getView().setMovieResource(inputStream);
                inputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void 动画资源(int n) {
        this.getView().setMovieResource(n);
    }

    public boolean 播放状态() {
        return !this.getView().isPaused();
    }

    public void 播放状态(boolean bl) {
        this.getView().setPaused(!bl);
    }

    public int 动画时长() {
        return this.getView().getMovieTime();
    }

    public void 动画时长(int n) {
        this.getView().setMovieTime(n);
    }

    @Override
    public GIFView getView() {
        return (GIFView) super.getView();
    }

    @Override
    public void 初始化事件() {
    }
}

