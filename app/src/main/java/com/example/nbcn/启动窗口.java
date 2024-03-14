package com.example.nbcn;


import lbchs.应用操作;

import android.app.Activity;

import android.os.Bundle;

import lbchs.可视化组件;

import lbchs.按钮;
import lbchs.窗口;
import lbchs.编辑框;
import lbchs.自适应布局;

public class 启动窗口 extends 窗口 {
    public 按钮 按钮1;
private final Activity 本对象=this;
    public 编辑框 编辑框1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void 载入布局完毕() {

        super.载入布局完毕();
        弹出提示("测试");
        按钮1.置被单击(new 可视化组件.被单击() {
            @Override
            public void 被单击() {
                应用操作.打开应用(本对象,"com.example.a14api");
                弹出提示("按钮1" + 按钮1.宽度() + "编辑框1" + 编辑框1.组件索引());
            }
        });
        编辑框1.置内容被改变(new 编辑框.内容被改变() {
            @Override
            public void 内容被改变(String var1) {
                弹出提示("内容变成"+var1);
            }
        });
    }

    @Override
    public 可视化组件 初始化布局() {

        自适应布局 r0 = new 自适应布局(this);
        r0.宽度(-1);
        r0.高度(-1);
        this.按钮1 = new 按钮(this);
        r0.添加组件(按钮1);
        按钮1.X坐标(按钮1.getRealWidth(0.2519d));
        按钮1.Y坐标(按钮1.getRealHeight(0.219d));
        按钮1.组件索引(1);
        按钮1.内容("选择音乐文件");
        按钮1.宽度("200dp");
        按钮1.高度(-2);
        编辑框1 = new 编辑框(this);
        r0.添加组件(编辑框1);
        编辑框1.X坐标(编辑框1.getRealWidth(0.0d));
        编辑框1.Y坐标(编辑框1.getRealHeight(0.3169d));
        编辑框1.组件索引(2);
        编辑框1.内容("编辑框1");
        编辑框1.宽度(-1);
        编辑框1.高度(-2);
        return r0;

    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

}
