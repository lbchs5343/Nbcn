/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.List
 */
package lbchs;

import java.util.ArrayList;
import java.util.Collections;

public class 集合<通用型> extends ArrayList {
    public void 添加项目(通用型  通用型) {
        this.add(通用型);
    }

    public void 删除项目(int n) {
        this.remove(n);
    }

    public String 取文本项目(int n) {
        try {
            return (String) this.get(n);
        } catch (Exception exception) {
            throw new RuntimeException("取文本项目错误");
        }
    }

    public 通用型 取项目(int n) {
        try {
            return (通用型) get(n);
        } catch (Exception exception) {
            throw new RuntimeException("集合取项目错误: 索引超过集合下标");
        }
    }

    public String[] 到文本数组() {
        return (String[]) this.toArray(new Object[0]);
    }

    public <通用型 extends Object> 通用型[] 到数组(通用型[] 通用型Array) {
        return (通用型[]) toArray(通用型Array);
    }

    public int 取项目总数() {
        return this.size();
    }

    public void 打乱顺序() {
        Collections.shuffle(this);
    }

    public void 插入项目(int n, 通用型 通用型) {
        this.add(n, 通用型);
    }

    public void 置项目(int n, 通用型 通用型) {
        if (n < this.取项目总数()) {
            this.set(n, 通用型);
        }
    }

    public boolean 是否为空() {
        return this.isEmpty();
    }

    public boolean 是否包含(通用型 通用型) {
        return this.contains(通用型);
    }

    public int 查找项目索引(通用型 通用型) {
        return this.indexOf(通用型);
    }

    public int 查找项目索引_逆序(通用型 通用型) {
        return this.lastIndexOf(通用型);
    }

    public void 清空() {
        this.clear();
    }

    public void 初始化事件() {
    }
}

