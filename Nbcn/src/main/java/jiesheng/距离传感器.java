/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class 距离传感器
        extends 组件 {
    private Context mContext;
    private float lastValue;
    private int mRate = 3;
    private Sensor mSensor;
    private SensorEventListener mSensorListener;
    private SensorManager mSensorManager;
    private int min = 1;
    private float value = -999.0f;
    private boolean enable;
    private 距离改变 $event_internal_距离改变;
    private 物体靠近 $event_internal_物体靠近;
    private 物体离开 $event_internal_物体离开;

    public 距离传感器(Context context) {
        super(context);
        this.mContext = context;
        this.mSensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        this.mSensor = this.mSensorManager.getDefaultSensor(8);
        this.enable = this.mSensor != null;
        this.mSensorListener = new SensorEventListener() {

            public void onAccuracyChanged(Sensor sensor, int n) {
            }

            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType() == 8) {
                    距离传感器.this.value = sensorEvent.values[0];
                    if ((距离传感器.this.value == 0.0f || 距离传感器.this.value < (float) 距离传感器.this.min) && 距离传感器.this.$event_internal_物体靠近 != null) {
                        距离传感器.this.$event_internal_物体靠近.物体靠近();
                    }
                    if ((距离传感器.this.value == 1.0f || 距离传感器.this.value > (float) 距离传感器.this.min) && 距离传感器.this.$event_internal_物体离开 != null) {
                        距离传感器.this.$event_internal_物体离开.物体离开();
                    }
                    if (距离传感器.this.lastValue != 距离传感器.this.value) {
                        距离传感器.this.lastValue = 距离传感器.this.value;
                        if (距离传感器.this.$event_internal_距离改变 != null) {
                            距离传感器.this.$event_internal_距离改变.距离改变(距离传感器.this.value);
                        }
                    }
                }
            }
        };
        if (this.mSensor != null) {
            this.mSensorManager.registerListener(this.mSensorListener, this.mSensor, this.mRate);
        }
    }

    public boolean 可用() {
        return this.enable;
    }

    public void 可用(boolean bl) {
        if (this.mSensor != null) {
            if (this.enable && !bl) {
                this.mSensorManager.unregisterListener(this.mSensorListener);
                this.enable = false;
            }
            if (!this.enable && bl) {
                this.mSensorManager.registerListener(this.mSensorListener, this.mSensor, this.mRate);
                this.enable = true;
            }
        }
    }

    public float 距离() {
        return this.lastValue;
    }

    public int 最小距离() {
        return this.min;
    }

    public void 最小距离(int n) {
        this.min = n;
    }

    public int 检测间隔() {
        return this.mRate;
    }

    public void 检测间隔(int n) {
        this.mSensorManager.unregisterListener(this.mSensorListener);
        this.mRate = n;
        this.mSensorManager.registerListener(this.mSensorListener, this.mSensor, this.mRate);
    }

    public void 置距离改变(距离改变 距离改变2) {
        this.$event_internal_距离改变 = 距离改变2;
    }

    public void 置物体靠近(物体靠近 物体靠近2) {
        this.$event_internal_物体靠近 = 物体靠近2;
    }

    public void 置物体离开(物体离开 物体离开2) {
        this.$event_internal_物体离开 = 物体离开2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 物体离开 {
        public void 物体离开();
    }

    public static interface 物体靠近 {
        public void 物体靠近();
    }

    public static interface 距离改变 {
        public void 距离改变(float var1);
    }
}

