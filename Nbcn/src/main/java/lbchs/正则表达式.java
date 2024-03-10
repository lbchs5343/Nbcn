/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package lbchs;

import android.content.Context;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则表达式 {
    private Matcher matcher = null;
    private Pattern pattern = null;

    public 正则表达式() {
    }

    public 正则表达式(Context context) {
        this();
    }

    public static String[] 正则匹配(String string, String string2) {
        Matcher matcher = Pattern.compile(string2, 40).matcher(string);
        ArrayList<String> arrayList = new ArrayList<>();
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return arrayList.toArray(new String[0]);
    }

    public static 集合<String> 正则匹配2(String string, String string2) {
        Matcher matcher = Pattern.compile(string2, 40).matcher(string);
        集合<String> 集合2 = new 集合<>();
        while (matcher.find()) {
            集合2.add(matcher.group());
        }
        return 集合2;
    }

    public void 创建表达式(String string, boolean bl, boolean bl2) {
        if (bl && bl2) {
            this.pattern = Pattern.compile(string, 10);
        } else if (!bl && !bl2) {
            this.pattern = Pattern.compile(string);
        } else if (bl) {
            this.pattern = Pattern.compile(string, 2);
        } else {
            this.pattern = Pattern.compile(string, 8);
        }
    }

    public void 创建表达式(String string) {
        this.pattern = Pattern.compile(string);
    }

    public String[] 全部分割(String string) {
        if (this.pattern != null) {
            return this.pattern.split(string);
        }
        return new String[0];
    }

    public void 开始匹配(String string) {
        if (this.pattern != null) {
            this.matcher = this.pattern.matcher(string);
        }
    }

    public String 全部替换(String string) {
        if (this.matcher != null) {
            return this.matcher.replaceAll(string);
        }
        return "";
    }

    public boolean 匹配下一个() {
        if (this.matcher != null) {
            return this.matcher.find();
        }
        return false;
    }

    public String 取匹配文本() {
        if (this.matcher != null) {
            return this.matcher.group();
        }
        return "";
    }

    public int 取匹配开始位置() {
        if (this.matcher != null) {
            return this.matcher.start();
        }
        return 0;
    }

    public int 取匹配结束位置() {
        if (this.matcher != null) {
            return this.matcher.end();
        }
        return 0;
    }

    public int 取子匹配数量() {
        if (this.matcher != null) {
            return this.matcher.groupCount();
        }
        return 0;
    }

    public String 取子匹配文本(int n) {
        if (this.matcher != null) {
            return this.matcher.group(n);
        }
        return "";
    }

    public void 初始化事件() {
    }
}

