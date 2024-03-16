/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.AnimationSet
 *  android.view.animation.RotateAnimation
 *  android.view.animation.ScaleAnimation
 *  android.view.animation.TranslateAnimation
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package lbchs.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.util.HashMap;

import lbchs.view.可视化组件;

public class 动画操作 {
    private static final HashMap<String, String> namesMap = new HashMap<>();

    static {
        namesMap.put("X偏移", "translationX");
        namesMap.put("Y偏移", "translationY");
        namesMap.put("X坐标", "x");
        namesMap.put("Y坐标", "y");
        namesMap.put("X伸缩", "scaleX");
        namesMap.put("Y伸缩", "scaleY");
        namesMap.put("X旋转", "rotationX");
        namesMap.put("Y旋转", "rotationY");
        namesMap.put("旋转", "rotation");
        namesMap.put("透明度", "alpha");
    }

    public static void 播放属性动画(可视化组件 可视化组件2, String string, float... fArray) {
        ObjectAnimator.ofFloat(可视化组件2.getView(), namesMap.containsKey(string.toUpperCase()) ? namesMap.get(string.toUpperCase()) : string, fArray).start();
    }

    public static void 播放属性动画(可视化组件 可视化组件2, long l, String string, float... fArray) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(可视化组件2.getView(), namesMap.containsKey(string.toUpperCase()) ? namesMap.get(string.toUpperCase()) : string, fArray);
        objectAnimator.setDuration(l);
        objectAnimator.start();
    }

    public static void 播放属性动画(可视化组件 可视化组件2, long l, int n, String string, float... fArray) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(可视化组件2.getView(), namesMap.containsKey(string.toUpperCase()) ? namesMap.get(string.toUpperCase()) : string, fArray);
        objectAnimator.setDuration(l);
        objectAnimator.setRepeatCount(n);
        objectAnimator.start();
    }

    public static Object 加载属性动画(可视化组件 可视化组件2, String string, float... fArray) {
        return ObjectAnimator.ofFloat(可视化组件2.getView(), namesMap.containsKey(string.toUpperCase()) ? namesMap.get(string.toUpperCase()) : string, fArray);
    }

    public static Object 加载渐变透明度动画(float f, float f2) {
        return new AlphaAnimation(f, f2);
    }

    public static Object 加载渐变旋转动画(float f, float f2) {
        return new RotateAnimation(f, f2);
    }

    public static Object 加载渐变伸缩动画(float f, float f2, float f3, float f4) {
        return new ScaleAnimation(f, f2, f3, f4);
    }

    public static Object 加载渐变坐标动画(float f, float f2, float f3, float f4) {
        return new TranslateAnimation(f, f2, f3, f4);
    }

    public static void 播放渐变透明度动画(可视化组件 可视化组件2, float f, float f2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        可视化组件2.getView().startAnimation(alphaAnimation);
    }

    public static void 播放渐变旋转动画(可视化组件 可视化组件2, float f, float f2) {
        RotateAnimation rotateAnimation = new RotateAnimation(f, f2);
        可视化组件2.getView().startAnimation(rotateAnimation);
    }

    public static void 播放渐变伸缩动画(可视化组件 可视化组件2, float f, float f2, float f3, float f4) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f3, f4);
        可视化组件2.getView().startAnimation(scaleAnimation);
    }

    public static void 播放渐变坐标动画(可视化组件 可视化组件2, float f, float f2, float f3, float f4) {
        TranslateAnimation translateAnimation = new TranslateAnimation(f, f2, f3, f4);
        可视化组件2.getView().startAnimation(translateAnimation);
    }

    public static void 播放渐变透明度动画(可视化组件 可视化组件2, long l, float f, float f2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(l);
        可视化组件2.getView().startAnimation(alphaAnimation);
    }

    public static void 播放渐变旋转动画(可视化组件 可视化组件2, long l, float f, float f2) {
        RotateAnimation rotateAnimation = new RotateAnimation(f, f2);
        rotateAnimation.setDuration(l);
        可视化组件2.getView().startAnimation(rotateAnimation);
    }

    public static void 播放渐变伸缩动画(可视化组件 可视化组件2, long l, float f, float f2, float f3, float f4) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f3, f4);
        scaleAnimation.setDuration(l);
        可视化组件2.getView().startAnimation(scaleAnimation);
    }

    public static void 播放渐变坐标动画(可视化组件 可视化组件2, long l, float f, float f2, float f3, float f4) {
        TranslateAnimation translateAnimation = new TranslateAnimation(f, f2, f3, f4);
        translateAnimation.setDuration(l);
        可视化组件2.getView().startAnimation(translateAnimation);
    }

    public static Object 置属性动画周期(Object object, int n) {
        return ((Animator) object).setDuration(n);
    }

    public static void 置属性动画重复次数(Object object, int n) {
        ((ObjectAnimator) object).setRepeatCount(n);
    }

    public static void 置渐变动画周期(Object object, int n) {
        ((Animation) object).setDuration(n);
    }

    public static void 置渐变动画重复次数(Object object, int n) {
        ((Animation) object).setRepeatCount(n);
    }

    public static void 播放渐变动画(可视化组件 可视化组件2, Object object) {
        可视化组件2.getView().startAnimation((Animation) object);
    }

    public static void 置属性动画延时(Object object, int n) {
        ((Animator) object).setStartDelay(n);
    }

    public static void 播放动画(Object object) {
        ((Animator) object).start();
    }

    public static void 暂停播放(Object object) {
        ((Animator) object).pause();
    }

    public static void 继续播放(Object object) {
        ((Animator) object).resume();
    }

    public static void 结束播放(Object object) {
        ((Animator) object).end();
    }

    public static void 取消播放(Object object) {
        ((ObjectAnimator) object).cancel();
    }

    public static void 并行播放(Object... objectArray) {
        if (objectArray[0] instanceof Animator) {
            Animator[] animatorArray = new Animator[objectArray.length];
            for (int i = 0; i < objectArray.length; ++i) {
                animatorArray[i] = (Animator) objectArray[i];
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animatorArray);
            animatorSet.start();
        } else {
            AnimationSet animationSet = new AnimationSet(true);
            for (Object object : objectArray) {
                animationSet.addAnimation((Animation) object);
            }
            animationSet.start();
        }
    }

    public static void 顺序播放(Object... objectArray) {
        Animator[] animatorArray = new Animator[objectArray.length];
        for (int i = 0; i < objectArray.length; ++i) {
            animatorArray[i] = (Animator) objectArray[i];
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorArray);
        animatorSet.start();
    }

    public void 初始化事件() {
    }
}

