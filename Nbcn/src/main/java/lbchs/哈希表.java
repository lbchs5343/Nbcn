/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.Iterator
 */
package lbchs;

import java.util.HashMap;
import java.util.Iterator;

public class 哈希表<键通用型, 值通用型>
        extends HashMap<键通用型,值通用型> {
    public 哈希表(){
        super();
    }
    private Iterator<?> iterator;
    private <T> T next() {
        return (T) iterator.next();
    }

    public void 添加项目(键通用型 欲添加项目键名称, 值通用型 欲添加项目值) {
        put(欲添加项目键名称, 欲添加项目值);
        iterator = keySet().iterator();
    }

    public String 取文本项目(String string) {
        return (String) this.get(string);
    }

    public 值通用型 取项目(键通用型 键通用型) {
        try {
            return this.get(键通用型);
        } catch (Exception exception) {
            throw new RuntimeException("哈希表取项目错误");
        }
    }

    public int 取项目总数() {
        return this.size();
    }

    public boolean 是否包含(键通用型 键通用型) {
        return this.containsKey(键通用型);
    }

    public void 删除项目(键通用型 键通用型) {
        this.remove(键通用型);
    }

    public void 清空() {
        this.clear();
        this.iterator = this.keySet().iterator();
    }

    public 值通用型 下一个() {
        return this.next();
    }

    public void 到起始位置() {
        this.iterator = this.keySet().iterator();
    }

    public boolean 是否有下一个() {
        return this.iterator.hasNext();
    }

    public void 初始化事件() {
    }
}

