/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.BitmapFactory
 *  android.graphics.Color
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.Window
 *  android.view.WindowManager$LayoutParams
 *  android.widget.Toast
 *  androidx.appcompat.app.ActionBar
 *  androidx.appcompat.app.AppCompatActivity
 *  androidx.appcompat.widget.Toolbar
 *  java.io.InputStream
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 */
package lbchs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.io.InputStream;
import lbchs.全局方法类;
import lbchs.单选对话框;
import lbchs.可视化组件;
import lbchs.后台服务;
import lbchs.存储卡操作;
import lbchs.安卓应用;
import lbchs.对话框;
import lbchs.意图;
import lbchs.日期选择框;
import lbchs.时间选择框;
import lbchs.组件容器;
import lbchs.输入对话框;
import lbchs.颜色选择框;

public class 兼容窗口
extends AppCompatActivity {
    private Intent serviceIntent;
    private Menu menu;
    private ProgressDialog pd;
    private 可视化组件 component;
    private 创建完毕 $event_internal_创建完毕;
    private 窗口启动 $event_internal_窗口启动;
    private 窗口重新启动 $event_internal_窗口重新启动;
    private 窗口停止 $event_internal_窗口停止;
    private 窗口暂停 $event_internal_窗口暂停;
    private 窗口恢复 $event_internal_窗口恢复;
    private 窗口销毁 $event_internal_窗口销毁;
    private 创建菜单 $event_internal_创建菜单;
    private 菜单项被单击 $event_internal_菜单项被单击;
    private 标题栏返回键被单击 $event_internal_标题栏返回键被单击;
    private 按下某键 $event_internal_按下某键;
    private 接收到参数 $event_internal_接收到参数;
    private 权限申请回调 $event_internal_权限申请回调;
    private 窗口回调 $event_internal_窗口回调;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((安卓应用)this.getApplication()).添加窗口((Activity)this);
        this.正在载入();
        this.component = this.初始化布局();
        if (this.component != null) {
            this.setContentView(this.component.getView());
        }
        this.载入布局完毕();
        this.初始化事件();
        if (this.$event_internal_创建完毕 != null) {
            this.$event_internal_创建完毕.创建完毕();
        }
        try {
            Intent intent = this.getIntent();
            String string = intent.getStringExtra("param");
            if (this.$event_internal_接收到参数 != null) {
                this.$event_internal_接收到参数.接收到参数(string);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void onStart() {
        super.onStart();
        this.通知_被启动();
    }

    protected void onRestart() {
        super.onStart();
        this.通知_被重新启动();
    }

    protected void onStop() {
        super.onStop();
        this.通知_被停止();
    }

    protected void onPause() {
        super.onPause();
        this.通知_被暂停();
    }

    protected void onResume() {
        super.onResume();
        this.通知_被恢复();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.通知_被销毁();
    }

    public void onBackPressed() {
        this.通知_返回键被按下();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (this.通知_按键被按下(n)) {
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        this.通知_菜单被创建();
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        CharSequence charSequence = menuItem.getTitle();
        if (charSequence == null) {
            if (menuItem.getItemId() == 16908332) {
                this.通知_标题栏返回键被单击();
            }
        } else {
            this.通知_菜单被单击(charSequence.toString());
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onRequestPermissionsResult(int n, String[] stringArray, int[] nArray) {
        super.onRequestPermissionsResult(n, stringArray, nArray);
        this.通知_申请权限完毕(n, stringArray, nArray);
    }

    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (intent != null) {
            this.通知_获得返回数据(n, n2, new 意图(intent));
        } else {
            this.通知_获得返回数据(n, n2, null);
        }
    }

    public View getRootView() {
        return this.component.getView();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void 结束窗口() {
        this.关闭窗口();
    }

    public void 结束窗口(int n) {
        this.关闭窗口(n);
    }

    public void 正在载入() {
    }

    public void 载入布局完毕() {
    }

    public 可视化组件 初始化布局() {
        return null;
    }

    public void 通知_被启动() {
        if (this.$event_internal_窗口启动 != null) {
            this.$event_internal_窗口启动.窗口启动();
        }
    }

    public void 通知_被重新启动() {
        if (this.$event_internal_窗口重新启动 != null) {
            this.$event_internal_窗口重新启动.窗口重新启动();
        }
    }

    public void 通知_被停止() {
        if (this.$event_internal_窗口停止 != null) {
            this.$event_internal_窗口停止.窗口停止();
        }
    }

    public void 通知_被暂停() {
        if (this.$event_internal_窗口暂停 != null) {
            this.$event_internal_窗口暂停.窗口暂停();
        }
    }

    public void 通知_被恢复() {
        if (this.$event_internal_窗口恢复 != null) {
            this.$event_internal_窗口恢复.窗口恢复();
        }
    }

    public void 通知_被销毁() {
        if (this.$event_internal_窗口销毁 != null) {
            this.$event_internal_窗口销毁.窗口销毁();
        }
    }

    public void 通知_返回键被按下() {
        super.onBackPressed();
    }

    public boolean 通知_按键被按下(int n) {
        Boolean bl;
        Boolean bl2 = bl = this.$event_internal_按下某键 != null ? Boolean.valueOf((boolean)this.$event_internal_按下某键.按下某键(n)) : null;
        if (bl != null) {
            return bl;
        }
        return false;
    }

    public void 通知_菜单被创建() {
        if (this.$event_internal_创建菜单 != null) {
            this.$event_internal_创建菜单.创建菜单();
        }
    }

    public void 通知_菜单被单击(String string) {
        if (this.$event_internal_菜单项被单击 != null) {
            this.$event_internal_菜单项被单击.菜单项被单击(string);
        }
    }

    public void 通知_标题栏返回键被单击() {
        if (this.$event_internal_标题栏返回键被单击 != null) {
            this.$event_internal_标题栏返回键被单击.标题栏返回键被单击();
        }
    }

    public void 通知_获得返回数据(int n, int n2, 意图 意图2) {
        if (this.$event_internal_窗口回调 != null) {
            this.$event_internal_窗口回调.窗口回调(n, n2, 意图2);
        }
    }

    public void 通知_申请权限完毕(int n, String[] stringArray, int[] nArray) {
        if (this.$event_internal_权限申请回调 != null) {
            this.$event_internal_权限申请回调.权限申请回调(n, stringArray, nArray);
        }
    }

    public void 主题(int n) {
        this.setTheme(n);
    }

    public void 标题(String string) {
        this.setTitle(string);
    }

    public String 标题() {
        return this.getTitle().toString();
    }

    public void 标题颜色(int n) {
        this.setTitleColor(n);
    }

    public void 标题颜色(String string) {
        this.setTitleColor(Color.parseColor((String)string));
    }

    public int 标题颜色() {
        return this.getTitleColor();
    }

    public void 显示标题栏(boolean bl) {
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        if (bl) {
            actionBar.show();
        } else {
            actionBar.hide();
        }
    }

    public void 显示标题栏返回键(boolean bl) {
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        if (bl) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public void 状态栏颜色(int n) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(n);
        }
    }

    public int 状态栏颜色() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.getWindow().getStatusBarColor();
        }
        return 0;
    }

    public void 导航栏颜色(int n) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setNavigationBarColor(n);
        }
    }

    public int 导航栏颜色() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.getWindow().getNavigationBarColor();
        }
        return 0;
    }

    public void 状态栏字体黑色(boolean bl) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (bl) {
                this.getWindow().getDecorView().setSystemUiVisibility(8192);
            } else {
                this.getWindow().getDecorView().setSystemUiVisibility(0);
            }
        }
    }

    public void 沉浸模式(boolean bl) {
        this.setImmersive(bl);
        if (bl) {
            this.getWindow().addFlags(0x8000000);
            this.getWindow().addFlags(0x4000000);
        } else {
            this.getWindow().clearFlags(0x8000000);
            this.getWindow().clearFlags(0x4000000);
        }
    }

    public void 沉浸模式2(boolean bl) {
        this.setImmersive(bl);
        if (bl) {
            this.getWindow().addFlags(0x4000000);
        } else {
            this.getWindow().clearFlags(0x4000000);
        }
    }

    public void 全屏模式(boolean bl) {
        if (this.getWindow() != null) {
            this.getWindow().setFlags(bl ? 1024 : 0, 1024);
        }
    }

    public void 常亮模式(boolean bl) {
        if (bl) {
            this.getWindow().addFlags(128);
        } else {
            this.getWindow().clearFlags(128);
        }
    }

    public void 亮度(double d) {
        Window window = this.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = (float)d;
        window.setAttributes(layoutParams);
    }

    public double 亮度() {
        Window window = this.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        return layoutParams.alpha;
    }

    public void 屏幕方向(int n) {
        this.setRequestedOrientation(n);
    }

    public void 可视(boolean bl) {
        this.setVisible(bl);
    }

    public void 背景颜色(int n) {
        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawable((Drawable)new ColorDrawable(n));
        }
    }

    public void 背景颜色(String string) {
        this.背景颜色(Color.parseColor((String)string));
    }

    public void 背景图片(int n) {
        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(n);
        }
    }

    public void 背景图片(String string) {
        Window window = this.getWindow();
        if (window != null) {
            if (string.startsWith("/")) {
                window.setBackgroundDrawable((Drawable)new BitmapDrawable(BitmapFactory.decodeFile((String)string)));
            } else {
                try {
                    InputStream inputStream = this.getAssets().open(string);
                    Drawable drawable = Drawable.createFromStream((InputStream)inputStream, (String)string);
                    window.setBackgroundDrawable(drawable);
                    inputStream.close();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void 绑定标题栏(可视化组件 可视化组件2) {
        this.setSupportActionBar((Toolbar)可视化组件2.getView());
    }

    public void 弹出提示(Object object) {
        Toast.makeText((Context)this, (CharSequence)全局方法类.到文本(object), (int)1500).show();
    }

    public void 弹出提示(Object object, int n) {
        Toast.makeText((Context)this, (CharSequence)全局方法类.到文本(object), (int)n).show();
    }

    public void 信息框(Object object) {
        对话框 对话框2 = new 对话框((Context)this);
        对话框2.信息(全局方法类.到文本(object));
        对话框2.按钮1("确定");
        对话框2.显示();
    }

    public void 信息框(Object object, Object object2) {
        对话框 对话框2 = new 对话框((Context)this);
        对话框2.标题(全局方法类.到文本(object));
        对话框2.信息(全局方法类.到文本(object2));
        对话框2.按钮1("确定");
        对话框2.显示();
    }

    public void 进度框(String string) {
        if (this.pd == null) {
            this.pd = new ProgressDialog((Context)this);
            this.pd.setCancelable(false);
        }
        this.pd.setMessage((CharSequence)string);
        this.pd.show();
    }

    public void 进度框(String string, String string2) {
        if (this.pd == null) {
            this.pd = new ProgressDialog((Context)this);
            this.pd.setCancelable(false);
        }
        this.pd.setTitle((CharSequence)string);
        this.pd.setMessage((CharSequence)string2);
        this.pd.show();
    }

    public void 关闭进度框() {
        if (this.pd != null) {
            this.pd.dismiss();
        }
    }

    public String 输入框(String string) {
        return 输入对话框.inputBox((Context)this, string);
    }

    public String 输入框(String string, String string2) {
        return 输入对话框.inputBox((Context)this, string, string2);
    }

    public String 输入框(String string, String string2, String string3) {
        return 输入对话框.inputBox((Context)this, string, string2, string3);
    }

    public String 密码输入框(String string) {
        return 输入对话框.passwordInputBox((Context)this, string);
    }

    public String 密码输入框(String string, String string2) {
        return 输入对话框.passwordInputBox((Context)this, string, string2);
    }

    public String 日期选择框() {
        return 日期选择框.datePickerBox((Context)this);
    }

    public String 日期选择框(int n, int n2, int n3) {
        return 日期选择框.datePickerBox((Context)this, n, n2, n3);
    }

    public String 时间选择框() {
        return 时间选择框.timePickerBox((Context)this);
    }

    public String 时间选择框(int n, int n2) {
        return 时间选择框.timePickerBox((Context)this, n, n2);
    }

    public int 颜色选择框(String string) {
        return 颜色选择框.colorPickerBox((Context)this, string);
    }

    public int 颜色选择框(String string, int n) {
        return 颜色选择框.colorPickerBox((Context)this, string, n);
    }

    public int 单选框(String[] stringArray) {
        return 单选对话框.singleChoiceBox((Context)this, stringArray);
    }

    public int 单选框(String[] stringArray, int n) {
        return 单选对话框.singleChoiceBox((Context)this, stringArray, n);
    }

    public int 单选框(String string, String[] stringArray, int n) {
        return 单选对话框.singleChoiceBox((Context)this, string, stringArray, n);
    }

    public void 切换窗口(Activity activity) {
        Intent intent = new Intent((Context)this, activity.getClass());
        this.startActivity(intent);
    }

    public void 切换窗口(Activity activity, String string) {
        this.切换窗口(activity, 3, string);
    }

    public void 切换窗口(Activity activity, int n) {
        this.切换窗口(activity, n, "");
    }

    public void 切换窗口(Activity activity, int n, String string) {
        Intent intent = new Intent((Context)this, activity.getClass());
        intent.putExtra("param", string);
        intent.addFlags(0x10000000);
        this.startActivity(intent);
        switch (n) {
            case 0: {
                this.overridePendingTransition(17432579, 17432578);
                break;
            }
            case 1: {
                this.overridePendingTransition(17432578, 17432579);
                break;
            }
            case 2: {
                this.overridePendingTransition(0x10A0000, 0x10A0001);
            }
        }
    }

    public void 切换窗口(Activity activity, 意图 意图2) {
        this.切换窗口(activity, 3, 意图2);
    }

    public void 切换窗口(Activity activity, int n, 意图 意图2) {
        意图2.setClass((Context)this, activity.getClass());
        意图2.addFlags(0x10000000);
        this.startActivity(意图2);
        switch (n) {
            case 0: {
                this.overridePendingTransition(17432579, 17432578);
                break;
            }
            case 1: {
                this.overridePendingTransition(17432578, 17432579);
                break;
            }
            case 2: {
                this.overridePendingTransition(0x10A0000, 0x10A0001);
            }
        }
    }

    public void 过渡动画(int n, int n2) {
        this.overridePendingTransition(n, n2);
    }

    public void 重启窗口() {
        this.recreate();
    }

    public void 关闭窗口() {
        this.finish();
    }

    public void 关闭窗口(int n) {
        this.finish();
        switch (n) {
            case 0: {
                this.overridePendingTransition(17432579, 17432578);
                break;
            }
            case 1: {
                this.overridePendingTransition(17432578, 17432579);
                break;
            }
            case 2: {
                this.overridePendingTransition(0x10A0000, 0x10A0001);
                break;
            }
            case 3: {
                this.overridePendingTransition(0x10A0001, 0x10A0000);
            }
        }
    }

    public void 添加菜单(String string) {
        this.menu.add((CharSequence)string);
    }

    public void 申请权限(int n, String ... stringArray) {
        this.requestPermissions(stringArray, n);
    }

    public void 检查权限(String string) {
        this.checkSelfPermission(string);
    }

    public String 取私有目录路径() {
        return 存储卡操作.取私有目录路径((Activity)this);
    }

    public void 关闭程序() {
        ((安卓应用)this.getApplication()).关闭程序();
    }

    public void 启动服务(意图 意图2) {
        this.startService(意图2);
    }

    public void 启动服务(后台服务 后台服务2) {
        this.serviceIntent = new Intent((Context)this, 后台服务2.getClass());
        this.startService(this.serviceIntent);
    }

    public void 关闭服务(意图 意图2) {
        this.stopService(意图2);
    }

    public void 关闭服务() {
        this.stopService(this.serviceIntent);
    }

    public void 发送广播(String string, String string2, String string3) {
        Intent intent = new Intent();
        intent.setAction(string);
        intent.putExtra("ID", string2);
        intent.putExtra("content", string3);
        this.sendBroadcast(intent);
    }

    public void 发送广播(String string, 意图 意图2) {
        意图2.setAction(string);
        this.sendBroadcast(意图2);
    }

    public void 启动意图(意图 意图2) {
        this.startActivity(意图2);
    }

    public void 启动意图_带返回值(意图 意图2, int n) {
        this.startActivityForResult(意图2, n);
    }

    public void 置返回数据(int n, 意图 意图2) {
        this.setResult(n, 意图2);
    }

    public 意图 取意图() {
        Intent intent = this.getIntent();
        if (intent != null) {
            return new 意图(intent);
        }
        return null;
    }

    public void 置窗口布局(组件容器 组件容器2) {
        this.component = 组件容器2.取根组件();
        this.setContentView(this.component.getView());
    }

    public void 置窗口布局(可视化组件 可视化组件2) {
        this.component = 可视化组件2;
        this.setContentView(this.component.getView());
    }

    public 可视化组件 取窗口布局() {
        return this.component;
    }

    public void 返回桌面() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        this.startActivity(intent);
    }

    public void 移动任务到后台() {
        this.moveTaskToBack(true);
    }

    public void 置创建完毕(创建完毕 创建完毕2) {
        this.$event_internal_创建完毕 = 创建完毕2;
    }

    public void 置窗口启动(窗口启动 窗口启动2) {
        this.$event_internal_窗口启动 = 窗口启动2;
    }

    public void 置窗口重新启动(窗口重新启动 窗口重新启动2) {
        this.$event_internal_窗口重新启动 = 窗口重新启动2;
    }

    public void 置窗口停止(窗口停止 窗口停止2) {
        this.$event_internal_窗口停止 = 窗口停止2;
    }

    public void 置窗口暂停(窗口暂停 窗口暂停2) {
        this.$event_internal_窗口暂停 = 窗口暂停2;
    }

    public void 置窗口恢复(窗口恢复 窗口恢复2) {
        this.$event_internal_窗口恢复 = 窗口恢复2;
    }

    public void 置窗口销毁(窗口销毁 窗口销毁2) {
        this.$event_internal_窗口销毁 = 窗口销毁2;
    }

    public void 置创建菜单(创建菜单 创建菜单2) {
        this.$event_internal_创建菜单 = 创建菜单2;
    }

    public void 置菜单项被单击(菜单项被单击 菜单项被单击2) {
        this.$event_internal_菜单项被单击 = 菜单项被单击2;
    }

    public void 置标题栏返回键被单击(标题栏返回键被单击 标题栏返回键被单击2) {
        this.$event_internal_标题栏返回键被单击 = 标题栏返回键被单击2;
    }

    public void 置按下某键(按下某键 按下某键2) {
        this.$event_internal_按下某键 = 按下某键2;
    }

    public void 置接收到参数(接收到参数 接收到参数2) {
        this.$event_internal_接收到参数 = 接收到参数2;
    }

    public void 置权限申请回调(权限申请回调 权限申请回调2) {
        this.$event_internal_权限申请回调 = 权限申请回调2;
    }

    public void 置窗口回调(窗口回调 窗口回调2) {
        this.$event_internal_窗口回调 = 窗口回调2;
    }

    public void 初始化事件() {
    }

    public static interface 窗口回调 {
        public void 窗口回调(int var1, int var2, 意图 var3);
    }

    public static interface 权限申请回调 {
        public void 权限申请回调(int var1, String[] var2, int[] var3);
    }

    public static interface 接收到参数 {
        public void 接收到参数(String var1);
    }

    public static interface 按下某键 {
        public boolean 按下某键(int var1);
    }

    public static interface 标题栏返回键被单击 {
        public void 标题栏返回键被单击();
    }

    public static interface 菜单项被单击 {
        public void 菜单项被单击(String var1);
    }

    public static interface 创建菜单 {
        public void 创建菜单();
    }

    public static interface 窗口销毁 {
        public void 窗口销毁();
    }

    public static interface 窗口恢复 {
        public void 窗口恢复();
    }

    public static interface 窗口暂停 {
        public void 窗口暂停();
    }

    public static interface 窗口停止 {
        public void 窗口停止();
    }

    public static interface 窗口重新启动 {
        public void 窗口重新启动();
    }

    public static interface 窗口启动 {
        public void 窗口启动();
    }

    public static interface 创建完毕 {
        public void 创建完毕();
    }
}

