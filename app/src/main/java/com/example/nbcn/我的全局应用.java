package com.example.nbcn;

import lbchs.安卓应用;
import lbchs.程序崩溃处理;

public class 我的全局应用 extends 安卓应用 {
    public void 载入完毕() {
        super.载入完毕();
        程序崩溃处理.初始化(this ,"");
    }
}
