/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  java.io.Reader
 *  java.io.StringReader
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserFactory
 */
package jiesheng;

import android.app.Activity;
import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.Reader;
import java.io.StringReader;

public class XML解析器
        extends 组件 {
    private XmlPullParser parser;

    public XML解析器() {
    }

    public XML解析器(Activity activity) {
        super((Context) activity);
    }

    public boolean 载入XML(String string) {
        try {
            this.parser = XmlPullParserFactory.newInstance().newPullParser();
            StringReader stringReader = new StringReader(string);
            this.parser.setInput((Reader) stringReader);
            if (this.parser.getEventType() == 1) {
                stringReader.close();
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public int 当前解析位置() {
        try {
            return this.parser.getEventType();
        } catch (Exception exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public String 当前节点名() {
        String string = this.parser.getName();
        return string != null ? string : "";
    }

    public String 当前节点内容() {
        String string = this.parser.getText();
        return string != null ? string : "";
    }

    public int 属性数量() {
        return this.parser.getAttributeCount();
    }

    public String 取属性名(int n) {
        String string = this.parser.getAttributeName(n);
        return string != null ? string : "";
    }

    public String 取属性内容(int n) {
        String string = this.parser.getAttributeValue(n);
        return string != null ? string : "";
    }

    public int 解析() {
        try {
            return this.parser.next();
        } catch (Exception exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    @Override
    public void 初始化事件() {
    }
}

