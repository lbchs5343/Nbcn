/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.Intent
 *  android.location.Criteria
 *  android.location.Location
 *  android.location.LocationListener
 *  android.location.LocationManager
 *  android.os.Bundle
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.Date
 *  java.util.List
 */
package lbchs;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class 位置传感器
        extends 组件 {
    private Criteria criteria;
    private Location location;
    private final LocationManager locationManager;
    private final Context mContext;
    private 位置改变 $event_internal_位置改变;
    private 状态改变 $event_internal_状态改变;
    private 设备开启 $event_internal_设备开启;
    private 设备关闭 $event_internal_设备关闭;
    private final LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            if (location != null && 位置传感器.this.$event_internal_位置改变 != null) {
                位置传感器.this.$event_internal_位置改变.位置改变(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getSpeed(), location.getBearing());
            }
        }

        public void onProviderDisabled(String string) {
            if (位置传感器.this.$event_internal_设备关闭 != null) {
                位置传感器.this.$event_internal_设备关闭.设备关闭();
            }
        }

        public void onProviderEnabled(String string) {
            if (位置传感器.this.$event_internal_设备开启 != null) {
                位置传感器.this.$event_internal_设备开启.设备开启();
            }
        }

        public void onStatusChanged(String string, int n, Bundle bundle) {
            switch (n) {
                case 0: {
                    if (位置传感器.this.$event_internal_状态改变 == null) break;
                    位置传感器.this.$event_internal_状态改变.状态改变(2);
                    break;
                }
                case 1: {
                    if (位置传感器.this.$event_internal_状态改变 == null) break;
                    位置传感器.this.$event_internal_状态改变.状态改变(3);
                    break;
                }
                case 2: {
                    if (位置传感器.this.$event_internal_状态改变 == null) break;
                    位置传感器.this.$event_internal_状态改变.状态改变(1);
                }
            }
        }
    };

    public 位置传感器(Context context) {
        super(context);
        this.mContext = context;
        this.locationManager = (LocationManager) this.mContext.getSystemService(Context.LOCATION_SERVICE);
    }

    public boolean 是否有效() {
        if (this.locationManager == null) {
            return false;
        }
        List<String> list = this.locationManager.getAllProviders();
        return list.contains("gps");
    }

    public boolean 是否已开启() {
        return this.locationManager.isProviderEnabled("gps");
    }

    public double 纬度() {
        if (this.location != null) {
            return this.location.getLatitude();
        }
        return 0.0;
    }

    public double 经度() {
        if (this.location != null) {
            return this.location.getLongitude();
        }
        return 0.0;
    }

    public double 海拔() {
        if (this.location != null) {
            return this.location.getAltitude();
        }
        return 0.0;
    }

    public double 速度() {
        if (this.location != null) {
            return this.location.getSpeed();
        }
        return 0.0;
    }

    public double 方向() {
        if (this.location != null) {
            return this.location.getBearing();
        }
        return 0.0;
    }

    public double 精度() {
        if (this.location == null || !this.location.hasAccuracy()) {
            return 0.0;
        }
        return this.location.getAccuracy();
    }

    @SuppressLint("SimpleDateFormat")
    public String 时间() {
        if (this.location != null) {
            return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(this.location.getTime()));
        }
        return "";
    }

    public void 打开设置界面() {
        Intent intent = new Intent();
        intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            activityNotFoundException.printStackTrace();
            intent.setAction("android.settings.SETTINGS");
            try {
                this.mContext.startActivity(intent);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @SuppressLint("MissingPermission")
    public void 开始监测() {
        if (this.locationManager.isProviderEnabled("gps")) {
            if (this.criteria == null) {
                this.criteria = new Criteria();
                this.criteria.setAccuracy(Criteria.ACCURACY_FINE);
                this.criteria.setAltitudeRequired(true);
                this.criteria.setBearingRequired(true);
                this.criteria.setCostAllowed(true);
                this.criteria.setPowerRequirement(Criteria.POWER_LOW);
            }
            String provider = this.locationManager.getBestProvider(this.criteria, true);
            this.locationManager.requestLocationUpdates("gps", 1000L, 0.0f, this.locationListener);
            this.location = this.locationManager.getLastKnownLocation("gps");
        }
    }

    @SuppressLint("MissingPermission")
    public void 停止监测() {
        this.locationManager.removeUpdates(this.locationListener);
    }

    public void 置位置改变(位置改变 位置改变2) {
        this.$event_internal_位置改变 = 位置改变2;
    }

    public void 置状态改变(状态改变 状态改变2) {
        this.$event_internal_状态改变 = 状态改变2;
    }

    public void 置设备开启(设备开启 设备开启2) {
        this.$event_internal_设备开启 = 设备开启2;
    }

    public void 置设备关闭(设备关闭 设备关闭2) {
        this.$event_internal_设备关闭 = 设备关闭2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 设备关闭 {
        void 设备关闭();
    }

    public interface 设备开启 {
        void 设备开启();
    }

    public interface 状态改变 {
        void 状态改变(int var1);
    }

    public interface 位置改变 {
        void 位置改变(double var1, double var3, double var5, double var7, double var9);
    }
}

