/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package jiesheng;

public class 变量操作 {
    public static boolean 是否相等(Object object, Object object2) {
        if (object instanceof String && object2 instanceof String) {
            return ((String) object).equals((Object) ((String) object));
        }
        return object == object2;
    }

    public void 初始化事件() {
    }
}

