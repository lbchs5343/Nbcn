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

import java.util.List;

import lbchs.view.组件;

public class 光线传感器
        extends 组件
        implements SensorEventListener {
    private final SensorManager sensors;
    private 光线改变 $event_internal_光线改变;

    public 光线传感器(Activity activity) {
        super(activity);
        this.sensors = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        this.sensors.registerListener(this, this.sensors.getDefaultSensor(5), 1);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 5 && this.$event_internal_光线改变 != null) {
            this.$event_internal_光线改变.光线改变(sensorEvent.values[0]);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int n) {
    }

    public boolean 有效() {
        List<Sensor> list = this.sensors.getSensorList(5);
        return list != null && !list.isEmpty();
    }

    public void 置光线改变(光线改变 光线改变2) {
        this.$event_internal_光线改变 = 光线改变2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 光线改变 {
        void 光线改变(float var1);
    }
}

