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
 *  java.util.ArrayList
 *  java.util.Iterator
 *  org.json.JSONObject
 */
package lbchs.json;

import android.app.Activity;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import lbchs.view.组件;

public class JSON对象
        extends 组件 {
    private JSONObject obj;

    public JSON对象(Activity activity) {
        super(activity);
        this.obj = new JSONObject();
    }

    public JSON对象(JSONObject jSONObject) {
        this.obj = jSONObject;
    }

    public JSON对象() {
        this.obj = new JSONObject();
    }

    public JSON对象(String string) {
        try {
            this.obj = new JSONObject(string);
        } catch (Exception exception) {
            throw new RuntimeException("JSON文本错误");
        }
    }

    public void JSON文本(String string) {
        try {
            this.obj = new JSONObject(string);
        } catch (Exception exception) {
            throw new RuntimeException("JSON文本错误");
        }
    }

    public String[] 键名() {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<String> iterator = this.obj.keys();
        while (iterator.hasNext()) {
            arrayList.add(iterator.next());
        }
        return (String[]) arrayList.toArray((Object[]) new String[0]);
    }

    public String 取文本(String string) {
        try {
            return this.obj.getString(string);
        } catch (Exception exception) {
            return "";
        }
    }

    public boolean 取逻辑值(String string) {
        try {
            return this.obj.getBoolean(string);
        } catch (Exception exception) {
            return false;
        }
    }

    public int 取整数(String string) {
        try {
            return this.obj.getInt(string);
        } catch (Exception exception) {
            return -1;
        }
    }

    public long 取长整数(String string) {
        try {
            return this.obj.getLong(string);
        } catch (Exception exception) {
            return -1L;
        }
    }

    public double 取双精度数(String string) {
        try {
            return this.obj.getDouble(string);
        } catch (Exception exception) {
            return 0.0;
        }
    }

    public JSON数组 取JSON数组(String string) {
        try {
            return new JSON数组(this.obj.getJSONArray(string));
        } catch (Exception exception) {
            return new JSON数组();
        }
    }

    public JSON对象 取JSON对象(String string) {
        try {
            return new JSON对象(this.obj.getJSONObject(string));
        } catch (Exception exception) {
            return new JSON对象();
        }
    }

    public void 添加项目(String string, Object object) {
        try {
            this.obj.put(string, object);
        } catch (Exception exception) {
            throw new RuntimeException("JSON对象添加项目错误");
        }
    }

    public void 移除项目(String string) {
        this.obj.remove(string);
    }

    public boolean 是否包含(String string) {
        return this.obj.has(string);
    }

    public String 到文本() {
        return this.obj.toString();
    }

    public String 到文本(int n) {
        try {
            return this.obj.toString(n);
        } catch (Exception exception) {
            return "";
        }
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

