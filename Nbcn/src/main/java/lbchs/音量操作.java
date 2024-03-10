/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.media.AudioManager
 *  java.lang.Object
 */
package lbchs;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

public class 音量操作 {
    public static int 取音量(Activity activity, int n) {
        int n2;
        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        switch (n) {
            case 1: {
                n2 = audioManager.getStreamVolume(1);
                break;
            }
            case 2: {
                n2 = audioManager.getStreamVolume(0);
                break;
            }
            case 3: {
                n2 = audioManager.getStreamVolume(2);
                break;
            }
            case 4: {
                n2 = audioManager.getStreamVolume(3);
                break;
            }
            case 5: {
                n2 = audioManager.getStreamVolume(4);
                break;
            }
            default: {
                n2 = audioManager.getStreamVolume(1);
            }
        }
        return n2;
    }

    public static int 取最大音量(Activity activity, int n) {
        int n2;
        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        switch (n) {
            case 1: {
                n2 = audioManager.getStreamMaxVolume(1);
                break;
            }
            case 2: {
                n2 = audioManager.getStreamMaxVolume(0);
                break;
            }
            case 3: {
                n2 = audioManager.getStreamMaxVolume(2);
                break;
            }
            case 4: {
                n2 = audioManager.getStreamMaxVolume(3);
                break;
            }
            case 5: {
                n2 = audioManager.getStreamMaxVolume(4);
                break;
            }
            default: {
                n2 = audioManager.getStreamMaxVolume(1);
            }
        }
        return n2;
    }

    public static void 置音量(Activity activity, int n, int n2) {
        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        switch (n) {
            case 1: {
                audioManager.setStreamVolume(1, n2, 0);
                break;
            }
            case 2: {
                audioManager.setStreamVolume(0, n2, 0);
                break;
            }
            case 3: {
                audioManager.setStreamVolume(2, n2, 0);
                break;
            }
            case 4: {
                audioManager.setStreamVolume(3, n2, 0);
                break;
            }
            case 5: {
                audioManager.setStreamVolume(4, n2, 0);
                break;
            }
            default: {
                audioManager.setStreamVolume(1, n2, 0);
            }
        }
    }

    public static boolean 耳机是否插入(Activity activity) {
        return ((AudioManager) activity.getSystemService(Context.AUDIO_SERVICE)).isWiredHeadsetOn();
    }

    public void 初始化事件() {
    }
}

