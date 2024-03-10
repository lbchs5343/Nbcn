/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.ActionBar
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
 *  java.io.InputStream
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 */
package lbchs;

import static lbchs.全局方法类.到文本;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
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

import java.io.InputStream;

public class 窗口 extends Activity {
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

    public 窗口() {
        super();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((安卓应用) getApplication()).添加窗口(this);
        正在载入();
        component = 初始化布局();
        if (component != null) {
            setContentView(component.getView());
        }
        载入布局完毕();
        初始化事件();
        if (this.$event_internal_创建完毕 != null) {
            this.$event_internal_创建完毕.创建完毕();
        }
        try {
            Intent intent = getIntent();
            String msg = intent.getStringExtra("param");
            if (this.$event_internal_接收到参数 != null) {
                this.$event_internal_接收到参数.接收到参数(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        通知_被启动();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        通知_被重新启动();
    }

    @Override
    protected void onStop() {
        super.onStop();
        通知_被停止();
    }

    @Override
    protected void onPause() {
        super.onPause();
        通知_被暂停();
    }

    @Override
    protected void onResume() {
        super.onResume();
        通知_被恢复();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        通知_被销毁();
    }

    @Override
    public void onBackPressed() {
        通知_返回键被按下();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (通知_按键被按下(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        通知_菜单被创建();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CharSequence sequence = item.getTitle();
        if (sequence == null) {
            if (item.getItemId() == android.R.id.home) {
                通知_标题栏返回键被单击();
            }
        } else {
            通知_菜单被单击(sequence.toString());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        通知_申请权限完毕(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            通知_获得返回数据(requestCode, resultCode, new 意图(data));
        } else {
            通知_获得返回数据(requestCode, resultCode, null);
        }
    }

    public View getRootView() {
        return component.getView();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void 结束窗口() {
        关闭窗口();
    }

    public void 结束窗口(int code) {
        关闭窗口(code);
    }


    //虚拟方法===

    //正在创建
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

    public boolean 通知_按键被按下(int 键代码) {

        Boolean value = this.$event_internal_按下某键 != null ? (boolean) this.$event_internal_按下某键.按下某键(键代码) : null;
        if (value != null) {
            return value;
        }
        return false;
    }

    public void 通知_菜单被创建() {
        if (this.$event_internal_创建菜单 != null) {
            this.$event_internal_创建菜单.创建菜单();
        }
    }

    public void 通知_菜单被单击(String 菜单) {
        if (this.$event_internal_菜单项被单击 != null) {
            this.$event_internal_菜单项被单击.菜单项被单击(菜单);
        }
    }

    public void 通知_标题栏返回键被单击() {
        if (this.$event_internal_标题栏返回键被单击 != null) {
            this.$event_internal_标题栏返回键被单击.标题栏返回键被单击();
        }
    }


    public void 通知_获得返回数据(int 请求码, int 结果码, 意图 数据) {

        if (this.$event_internal_窗口回调 != null) {
            this.$event_internal_窗口回调.窗口回调(请求码, 结果码, 数据);
        }
    }

    public void 通知_申请权限完毕(int 请求码, String[] 权限, int[] 请求结果) {

        if (this.$event_internal_权限申请回调 != null) {
            this.$event_internal_权限申请回调.权限申请回调(请求码, 权限, 请求结果);
        }
    }

    //属性===

    //设置窗口主题
    public void 主题(int 资源ID) {
        setTheme(资源ID);
    }


    //设置窗口标题
    public void 标题(String 标题) {
        setTitle(标题);
    }


    //读取窗口标题
    public String 标题() {
        return (getTitle().toString());
    }


    //设置窗口标题颜色
    public void 标题颜色(int 颜色) {
        setTitleColor(颜色);
    }

    //设置窗口标题颜色
    public void 标题颜色(String 颜色) {

        setTitleColor(Color.parseColor(颜色));
    }

    //读取窗口标题颜色
    public int 标题颜色() {
        return (getTitleColor());
    }

    //设置是否显示标题栏
    public void 显示标题栏(boolean 是否显示标题栏) {

        ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        if (是否显示标题栏) {
            actionBar.show();
        } else {
            actionBar.hide();
        }

    }

    //设置是否显示标题栏返回键
    public void 显示标题栏返回键(boolean 是否显示) {

        ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        if (是否显示) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

    }

    //设置状态栏颜色
    public void 状态栏颜色(int 颜色) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(颜色);
        }

    }

    //读取状态栏颜色
    public int 状态栏颜色() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getWindow().getStatusBarColor();
        }
        return 0;

    }

    //设置导航栏颜色
    public void 导航栏颜色(int 颜色) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(颜色);
        }
    }

    //读取导航栏颜色
    public int 导航栏颜色() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getWindow().getNavigationBarColor();
        }
        return 0;
    }

    //设置状态栏字体颜色是否为黑色
    public void 状态栏字体黑色(boolean 是否黑色) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (是否黑色) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

    //设置是否处于沉浸式体验模式
    public void 沉浸模式(boolean 是否启用) {

        setImmersive(是否启用);
        if (是否启用) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    //设置是否处于沉浸式体验模式，和"沉浸模式"不同，该属性不会隐藏导航栏
    public void 沉浸模式2(boolean 是否启用) {

        setImmersive(是否启用);
        if (是否启用) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    //设置全屏模式
    public void 全屏模式(boolean 是否启用) {

        if (getWindow() != null) {
            getWindow().setFlags(是否启用 ? WindowManager.LayoutParams.FLAG_FULLSCREEN : 0, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    //设置常亮模式
    public void 常亮模式(boolean 是否启用) {

        if (是否启用) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }


    //设置窗口亮度
    public void 亮度(float 欲设置亮度) {

        final Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = 欲设置亮度;
        window.setAttributes(params);
    }

    //获取窗口亮度
    public float 亮度() {

        final Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        return params.alpha;
    }

    //设置屏幕方向
    public void 屏幕方向(int 屏幕方向) {

        setRequestedOrientation(屏幕方向);
    }

    //设置当前窗口可视状态
    public void 可视(boolean 是否可视) {

        setVisible(是否可视);
    }

    //设置背景颜色
    public void 背景颜色(int 颜色) {

        final Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(颜色));
        }
    }

    //设置背景颜色
    public void 背景颜色(String 颜色) {
        背景颜色(Color.parseColor(颜色));
    }

    //设置背景图片
    public void 背景图片(int 资源ID) {

        final Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(资源ID);
        }
    }

    //设置背景图片
    public void 背景图片(String 图片路径) {

        final Window window = getWindow();
        if (window != null) {
            if (图片路径.startsWith("/")) {
                window.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile(图片路径)));
            } else {
                try {
                    InputStream is = getAssets().open(图片路径);
                    Drawable drawable = Drawable.createFromStream(is, 图片路径);
                    window.setBackgroundDrawable(drawable);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //方法====

    //弹出一个提示框
    public void 弹出提示(Object 内容) {
        Toast.makeText(this, 到文本(内容), Toast.LENGTH_SHORT).show();
    }

    //弹出一个指定时长的提示框
    public void 弹出提示(Object 内容, int 时长) {
        Toast.makeText(this, 到文本(内容), 时长).show();
    }

    //显示一个类似电脑信息框的东西
    public void 信息框(Object 内容) {
        对话框 对话框1 = new 对话框(this);
        对话框1.标题(到文本(内容));
        对话框1.按钮1("确定");
        对话框1.显示();
    }

    //显示一个类似电脑信息框的东西
    public void 信息框(Object 标题, Object 内容) {
        对话框 对话框1 = new 对话框(this);
        对话框1.标题(到文本(标题));
        对话框1.信息(到文本(内容));
        对话框1.按钮1("确定");
        对话框1.显示();
    }

    public void 进度框(String 信息) {

        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setCancelable(false);
        }
        pd.setMessage(信息);
        pd.show();

    }

    public void 进度框(String 标题, String 信息) {
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setCancelable(false);
        }
        pd.setTitle(标题);
        pd.setMessage(信息);
        pd.show();
    }

    public void 关闭进度框() {

        if (pd != null) {
            pd.dismiss();
        }
    }

    public String 输入框(String 标题) {
        return 输入对话框.inputBox(this, 标题);
    }

    public String 输入框(String 标题, String 初始内容) {
        return 输入对话框.inputBox(this, 标题, 初始内容);
    }

    public String 输入框(String 标题, String 初始内容, String 提示文本) {
        return 输入对话框.inputBox(this, 标题, 初始内容, 提示文本);
    }

    public String 密码输入框(String 标题) {
        return 输入对话框.passwordInputBox(this, 标题);
    }

    public String 密码输入框(String 标题, String 提示文本) {

        return 输入对话框.passwordInputBox(this, 标题, 提示文本);
    }

    public String 日期选择框() {
        return 日期选择框.datePickerBox(this);
    }

    public String 日期选择框(int 初始年, int 初始月, int 初始日) {
        return 日期选择框.datePickerBox(this, 初始年, 初始月, 初始日);
    }

    public String 时间选择框() {
        return 时间选择框.timePickerBox(this);
    }

    public String 时间选择框(int 初始时, int 初始分) {
        return 时间选择框.timePickerBox(this, 初始时, 初始分);
    }

    public int 颜色选择框(String 标题) {
        return 颜色选择框.colorPickerBox(this, 标题);
    }

    public int 颜色选择框(String 标题, int 初始颜色) {
        return 颜色选择框.colorPickerBox(this, 标题, 初始颜色);
    }

    public int 单选框(String[] 项目) {
        return 单选对话框.singleChoiceBox(this, 项目);
    }

    public int 单选框(String[] 项目, int 默认选中项索引) {
        return 单选对话框.singleChoiceBox(this, 项目, 默认选中项索引);
    }

    public int 单选框(String 标题, String[] 项目, int 默认选中项索引) {
        return 单选对话框.singleChoiceBox(this, 标题, 项目, 默认选中项索引);
    }

    //切换至另一个窗口
    public void 切换窗口(Activity 欲切换新窗口) {

        Intent intent = new Intent(this, 欲切换新窗口.getClass());
        startActivity(intent);
    }

    //切换至另一个窗口，并向新窗口传递参数
    public void 切换窗口(Activity 欲切换新窗口, String 欲传递参数) {
        切换窗口(欲切换新窗口, 3, 欲传递参数);
    }

    //切换至另一个窗口，并播放动画
    public void 切换窗口(Activity 欲切换新窗口, int 动画代码) {
        切换窗口(欲切换新窗口, 动画代码, "");
    }

    //切换至另一个窗口，播放动画，并传递参数
    public void 切换窗口(Activity 欲切换新窗口, int 动画代码, String 欲传递参数) {

        Intent intent = new Intent(this, 欲切换新窗口.getClass());
        intent.putExtra("param", 欲传递参数);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        switch (动画代码) {
            case 0:
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                break;
            case 1:
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case 2:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

    //切换至另一个窗口，并传递意图
    public void 切换窗口(Activity 欲切换新窗口, 意图 欲传递意图) {
        切换窗口(欲切换新窗口, 3, 欲传递意图);
    }

    //切换至另一个窗口，播放动画，并传递意图
    public void 切换窗口(Activity 欲切换新窗口, int 动画代码, 意图 欲传递意图) {

        欲传递意图.setClass(this, 欲切换新窗口.getClass());
        欲传递意图.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(欲传递意图);
        switch (动画代码) {
            case 0:
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                break;
            case 1:
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case 2:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

    //在切换窗口时播放自定义动画，动画xml资源存放在res/anim文件夹下
    public void 过渡动画(int 进入新窗口动画资源ID, int 隐藏当前窗口动画资源ID) {
        overridePendingTransition(进入新窗口动画资源ID, 隐藏当前窗口动画资源ID);
    }

    //重新载入当前窗口
    public void 重启窗口() {
        recreate();
    }

    //关闭当前窗口
    public void 关闭窗口() {
        finish();
    }

    //关闭当前窗口，并播放动画
    public void 关闭窗口(int 动画代码) {

        finish();
        switch (动画代码) {
            case 0:
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                break;
            case 1:
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case 2:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case 3:
                overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
                break;
        }
    }

    //添加菜单
    public void 添加菜单(String 菜单标题) {
        menu.add(菜单标题);
    }

    //申请权限
    public void 申请权限(int 请求码, String[] 欲申请权限) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(欲申请权限, 请求码);
        }
    }

    //检查权限
    public void 检查权限(String 欲检查权限) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(欲检查权限);
        }
    }

    //获取应用私有目录路径
    public String 取私有目录路径() {
        return (存储卡操作.取私有目录路径(this));
    }

    public void 关闭程序() {
        ((安卓应用) getApplication()).关闭程序();
    }

    //通过意图启动服务
    public void 启动服务(意图 欲启动服务意图) {
        startService(欲启动服务意图);
    }

    //启动服务
    public void 启动服务(后台服务 欲启动服务) {
        startService(serviceIntent = new Intent(this, 欲启动服务.getClass()));
    }

    //通过意图关闭服务
    public void 关闭服务(意图 欲关闭服务意图) {
        stopService(欲关闭服务意图);
    }

    //关闭服务
    public void 关闭服务() {
        stopService(serviceIntent);
    }

    //发送一条广播
    public void 发送广播(String 行动名称, String 广播ID, String 广播内容) {

        Intent BroadcastIntent = new Intent();
        BroadcastIntent.setAction(行动名称);
        BroadcastIntent.putExtra("ID", 广播ID);
        BroadcastIntent.putExtra("content", 广播内容);
        sendBroadcast(BroadcastIntent);
    }

    //发送一条广播
    public void 发送广播(String 行动名称, 意图 数据) {

        数据.setAction(行动名称);
        sendBroadcast(数据);
    }

    //启动一个意图
    public void 启动意图(意图 意图对象) {
        startActivity(意图对象);
    }

    //启动一个意图，并让窗口接收其返回值，请求码等同于给这个操作一个ID便于与其他区分
    public void 启动意图_带返回值(意图 意图对象, int 请求码) {
        startActivityForResult(意图对象, 请求码);
    }

    //置返回数据
    public void 置返回数据(int 结果码, 意图 欲返回意图) {

        setResult(结果码, 欲返回意图);
    }

    //获取接收到的意图
    public 意图 取意图() {

        Intent intent = getIntent();
        if (intent != null) {
            return new 意图(intent);
        }
        return null;
    }

    //设置窗口布局
    public void 置窗口布局(组件容器 容器) {

        this.component = 容器.取根组件();
        setContentView(component.getView());
    }

    //设置窗口布局
    public void 置窗口布局(可视化组件 组件) {

        this.component = 组件;
        setContentView(component.getView());
    }

    //取窗口布局
    public 可视化组件 取窗口布局() {
        return (component);
    }

    //返回桌面，等同于手机按下Home键的效果
    public void 返回桌面() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    //将当前窗口所处任务移动到后台
    public void 移动任务到后台() {

        moveTaskToBack(true);
    }

    //事件===

    //窗口创建完毕时触发该事件
    public void 初始化事件() {
    }

    public interface 窗口回调 {
        void 窗口回调(int var1, int var2, 意图 var3);
    }

    public interface 权限申请回调 {
        void 权限申请回调(int var1, String[] var2, int[] var3);
    }

    public interface 接收到参数 {
        void 接收到参数(String var1);
    }

    public interface 按下某键 {
        boolean 按下某键(int var1);
    }

    public interface 标题栏返回键被单击 {
        void 标题栏返回键被单击();
    }

    public interface 菜单项被单击 {
        void 菜单项被单击(String var1);
    }

    public interface 创建菜单 {
        void 创建菜单();
    }

    public interface 窗口销毁 {
        void 窗口销毁();
    }

    public interface 窗口恢复 {
        void 窗口恢复();
    }

    public interface 窗口暂停 {
        void 窗口暂停();
    }

    public interface 窗口停止 {
        void 窗口停止();
    }

    public interface 窗口重新启动 {
        void 窗口重新启动();
    }

    public interface 窗口启动 {
        void 窗口启动();
    }

    public interface 创建完毕 {
        void 创建完毕();
    }

}

