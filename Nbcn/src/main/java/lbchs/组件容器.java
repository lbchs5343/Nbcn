/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedHashMap
 */
package lbchs;

import java.util.LinkedHashMap;

import lbchs.view.单选框;
import lbchs.view.可视化组件;
import lbchs.view.图片框;
import lbchs.view.多选框;
import lbchs.view.开关;
import lbchs.view.拖动条;
import lbchs.view.按钮;
import lbchs.view.文本框;
import lbchs.view.浏览框;
import lbchs.view.编辑框;
import lbchs.view.视频播放器;
import lbchs.view.进度圈;
import lbchs.view.进度条;

public class 组件容器 {
    private final LinkedHashMap<String, 可视化组件> 哈希表1 = new LinkedHashMap<>();

    public void 添加组件(String string, 可视化组件 可视化组件2) {
        this.哈希表1.put(string, 可视化组件2);
        可视化组件2.getView().setTag(this);
    }

    public <通用型 extends 可视化组件> 通用型 取组件(String string) {
        if (this.哈希表1.containsKey(string)) {
            return (通用型) (this.哈希表1.get(string));
        }
        throw new NullPointerException("组件容器中未找到组件: " + string);
    }

    public <通用型 extends 可视化组件> 通用型 取根组件() {
        return (通用型) (this.哈希表1.values().iterator().next());
    }

    public 文本框 取文本框(String string) {
        return (文本框) this.哈希表1.get(string);
    }

    public 按钮 取按钮(String string) {
        return (按钮) this.哈希表1.get(string);
    }

    public 编辑框 取编辑框(String string) {
        return (编辑框) this.哈希表1.get(string);
    }

    public 图片框 取图片框(String string) {
        return (图片框) this.哈希表1.get(string);
    }

    public 单选框 取单选框(String string) {
        return (单选框) this.哈希表1.get(string);
    }

    public 多选框 取多选框(String string) {
        return (多选框) this.哈希表1.get(string);
    }

    public 开关 取开关(String string) {
        return (开关) this.哈希表1.get(string);
    }

    public 进度圈 取进度圈(String string) {
        return (进度圈) this.哈希表1.get(string);
    }

    public 进度条 取进度条(String string) {
        return (进度条) this.哈希表1.get(string);
    }

    public 拖动条 取拖动条(String string) {
        return (拖动条) this.哈希表1.get(string);
    }

    public 浏览框 取浏览框(String string) {
        return (浏览框) this.哈希表1.get(string);
    }

    public 视频播放器 取视频播放器(String string) {
        return (视频播放器) this.哈希表1.get(string);
    }

    public void 初始化事件() {
    }
}

