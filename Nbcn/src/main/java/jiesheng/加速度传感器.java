/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  java.lang.Float
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 *  java.util.LinkedList
 *  java.util.Queue
 */
package jiesheng;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.LinkedList;
import java.util.Queue;

public class 加速度传感器
        extends 组件
        implements SensorEventListener {
    private static final int SENSOR_CACHE_SIZE = 10;
    private static final double SHAKE_THRESHOLD = 8.0;
    private final Queue<Float> X_CACHE = new LinkedList<>();
    private final Queue<Float> Y_CACHE = new LinkedList<>();
    private final Queue<Float> Z_CACHE = new LinkedList<>();
    private final Sensor sensor;
    private boolean enabled;
    private float xAccel;
    private float yAccel;
    private float zAccel;
    private 加速度改变 $event_internal_加速度改变;
    private 摇晃手机 $event_internal_摇晃手机;

    public 加速度传感器(Activity activity) {
        super((Context) activity);
        SensorManager sensorManager = (SensorManager) activity.getSystemService("sensor");
        this.sensor = sensorManager.getDefaultSensor(1);
        if (this.sensor != null) {
            sensorManager.registerListener((SensorEventListener) this, this.sensor, 1);
        }
    }

    private void addToSensorCache(Queue<Float> queue, float f) {
        if (queue.size() >= 10) {
            queue.remove();
        }
        queue.add(Float.valueOf(f));
    }

    private boolean isShaking(Queue<Float> queue, float f) {
        float f2 = 0.0f;
        for (Float f3 : queue) {
            f2 += f3.floatValue();
        }
        return (double) Math.abs((f2 / (float) queue.size() - f)) > 8.0;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1 && this.enabled) {
            this.xAccel = sensorEvent.values[0];
            this.yAccel = sensorEvent.values[1];
            this.zAccel = sensorEvent.values[2];
            this.addToSensorCache(this.X_CACHE, this.xAccel);
            this.addToSensorCache(this.Y_CACHE, this.yAccel);
            this.addToSensorCache(this.Z_CACHE, this.zAccel);
            if ((this.isShaking(this.X_CACHE, this.xAccel) || this.isShaking(this.Y_CACHE, this.yAccel) || this.isShaking(this.Z_CACHE, this.zAccel)) && this.$event_internal_摇晃手机 != null) {
                this.$event_internal_摇晃手机.摇晃手机();
            }
            if (this.$event_internal_加速度改变 != null) {
                this.$event_internal_加速度改变.加速度改变(this.xAccel, this.yAccel, this.zAccel);
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int n) {
    }

    public boolean 有效() {
        return this.sensor != null;
    }

    public boolean 可用() {
        return this.enabled;
    }

    public void 可用(boolean bl) {
        this.enabled = bl;
    }

    public float X加速度() {
        return this.xAccel;
    }

    public float Y加速度() {
        return this.yAccel;
    }

    public float Z加速度() {
        return this.zAccel;
    }

    public void 置加速度改变(加速度改变 加速度改变2) {
        this.$event_internal_加速度改变 = 加速度改变2;
    }

    public void 置摇晃手机(摇晃手机 摇晃手机2) {
        this.$event_internal_摇晃手机 = 摇晃手机2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 摇晃手机 {
        public void 摇晃手机();
    }

    public static interface 加速度改变 {
        public void 加速度改变(float var1, float var2, float var3);
    }
}

