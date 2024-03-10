/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  java.io.StringWriter
 *  java.io.Writer
 *  java.lang.Boolean
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  org.xmlpull.v1.XmlPullParserFactory
 *  org.xmlpull.v1.XmlSerializer
 */
package lbchs;

import android.app.Activity;


import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;


public class XML合成器
        extends 组件 {
    private XmlSerializer serializer;
    private StringWriter writer;

    public XML合成器() {
        try {
            this.serializer = XmlPullParserFactory.newInstance().newSerializer();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public XML合成器(Activity activity) {
        super(activity);
        try {
            this.serializer = XmlPullParserFactory.newInstance().newSerializer();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean 开始XML文档() {
        return this.开始XML文档("utf-8");
    }

    public boolean 开始XML文档(String string) {
        if (this.serializer == null) {
            return false;
        }
        try {
            this.writer = new StringWriter();
            this.serializer.setOutput(this.writer);
            this.serializer.startDocument(string, Boolean.TRUE);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean 结束XML文档() {
        if (this.serializer == null) {
            return false;
        }
        try {
            this.serializer.endDocument();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean 开始节点(String string) {
        if (this.serializer == null) {
            return false;
        }
        try {
            this.serializer.startTag(null, string);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean 添加节点属性(String string, String string2) {
        if (this.serializer == null) {
            return false;
        }
        try {
            this.serializer.attribute(null, string, string2);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean 置节点内容(String string) {
        if (this.serializer == null) {
            return false;
        }
        try {
            this.serializer.text(string);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean 添加注释(String string) {
        if (this.serializer == null) {
            return false;
        }
        try {
            this.serializer.comment(string);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean 结束节点(String string) {
        if (this.serializer == null) {
            return false;
        }
        try {
            this.serializer.endTag(null, string);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public String 导出文本() {
        if (this.writer != null) {
            return this.writer.toString();
        }
        return "";
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

