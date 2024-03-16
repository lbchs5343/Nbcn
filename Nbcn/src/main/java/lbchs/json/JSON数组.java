/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.RuntimeException
 *  java.lang.String
 *  org.json.JSONArray
 */
package lbchs.json;

import android.app.Activity;


import org.json.JSONArray;

import lbchs.view.组件;

public class JSON数组
        extends 组件 {
    private JSONArray array;

    public JSON数组(Activity activity) {
        super(activity);
        this.array = new JSONArray();
    }

    public JSON数组(JSONArray jSONArray) {
        this.array = jSONArray;
    }

    public JSON数组() {
        this.array = new JSONArray();
    }

    public void JSON文本(String string) {
        try {
            this.array = new JSONArray(string);
        } catch (Exception exception) {
            throw new RuntimeException("JSON文本错误");
        }
    }

    public int 数组长度() {
        return this.array.length();
    }

    public String 取文本(int n) {
        try {
            return this.array.getString(n);
        } catch (Exception exception) {
            return "";
        }
    }

    public boolean 取逻辑值(int n) {
        try {
            return this.array.getBoolean(n);
        } catch (Exception exception) {
            return false;
        }
    }

    public int 取整数(int n) {
        try {
            return this.array.getInt(n);
        } catch (Exception exception) {
            return -1;
        }
    }

    public long 取长整数(int n) {
        try {
            return this.array.getLong(n);
        } catch (Exception exception) {
            return -1L;
        }
    }

    public double 取双精度数(int n) {
        try {
            return this.array.getDouble(n);
        } catch (Exception exception) {
            return 0.0;
        }
    }

    public JSON数组 取JSON数组(int n) {
        try {
            return new JSON数组(this.array.getJSONArray(n));
        } catch (Exception exception) {
            return new JSON数组();
        }
    }

    public JSON对象 取JSON对象(int n) {
        try {
            return new JSON对象(this.array.getJSONObject(n));
        } catch (Exception exception) {
            return new JSON对象();
        }
    }

    public void 添加项目(Object object) {
        this.array.put(object);
    }

    public void 添加项目(int n, Object object) {
        try {
            this.array.put(n, object);
        } catch (Exception exception) {
            throw new RuntimeException("JSON数组添加项目错误");
        }
    }

    public void 移除项目(int n) {
        this.array.remove(n);
    }

    public String 到文本() {
        return this.array.toString();
    }

    public String 到文本(int n) {
        try {
            return this.array.toString(n);
        } catch (Exception exception) {
            return "";
        }
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

