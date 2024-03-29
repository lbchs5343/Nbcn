/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  android.view.WindowManager
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Override
 *  java.util.List
 */
package lbchs.Sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;
import android.view.WindowManager;

import java.util.List;

import lbchs.view.组件;

public class 方向传感器
        extends 组件
        implements SensorEventListener {
    private boolean enabled;
    private float pitch;
    private float roll;
    private final SensorManager sensors;
    private float yaw;
    private final Activity activity;
    private 方向改变 $event_internal_方向改变;

    public 方向传感器(Activity activity) {
        this.activity = activity;
        this.sensors = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
        this.sensors.registerListener(this, this.sensors.getDefaultSensor(3), 1);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 3 && this.enabled) {
            this.yaw = sensorEvent.values[0];
            this.pitch = sensorEvent.values[1];
            this.roll = sensorEvent.values[2];
            if (this.$event_internal_方向改变 != null) {
                this.$event_internal_方向改变.方向改变(this.yaw, this.pitch, this.roll);
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int n) {
    }

    public boolean 有效() {
        List<Sensor> list = this.sensors.getSensorList(3);
        return list != null && !list.isEmpty();
    }

    public boolean 可用() {
        return this.enabled;
    }

    public void 可用(boolean bl) {
        this.enabled = bl;
    }

    public float 俯仰角() {
        return this.pitch;
    }

    public float 横滚角() {
        return this.roll;
    }

    public float 偏航角() {
        return this.yaw;
    }

    public float 倾斜角() {
        return (float) Math.toDegrees(Math.atan2(this.pitch, this.roll));
    }

    public int 取屏幕旋转角度() {
        switch (((WindowManager) this.activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0: {
                return 0;
            }
            case Surface.ROTATION_90: {
                return 90;
            }
            case Surface.ROTATION_180: {
                return 180;
            }
            case Surface.ROTATION_270: {
                return 270;
            }
        }
        return 0;
    }

    public void 置方向改变(方向改变 方向改变2) {
        this.$event_internal_方向改变 = 方向改变2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 方向改变 {
        void 方向改变(float var1, float var2, float var3);
    }
}

