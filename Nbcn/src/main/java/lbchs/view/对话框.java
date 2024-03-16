/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnMultiChoiceClickListener
 *  android.graphics.BitmapFactory
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  android.view.Window
 *  android.view.WindowManager$LayoutParams
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.RuntimeException
 *  java.lang.String
 */
package lbchs.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

import lbchs.utils.转换操作;
import lbchs.组件容器;

public class 对话框
        extends 组件 {
    private AlertDialog dialog;
    private final AlertDialog.Builder builder;
    private 组件容器 container;
    private 按钮1被单击 $event_internal_按钮1被单击;
    private 按钮2被单击 $event_internal_按钮2被单击;
    private 按钮3被单击 $event_internal_按钮3被单击;
    private 项目被单击 $event_internal_项目被单击;

    public 对话框(Context context) {
        super(context);
        this.builder = new AlertDialog.Builder(context);
    }

    public 对话框(Context context, String string, String string2, String string3) {
        this(context);
        this.标题(string);
        this.信息(string2);
        this.按钮1(string3);
    }

    public void 标题(String string) {
        this.builder.setTitle(string);
    }

    public void 信息(String string) {
        this.builder.setMessage(string);
    }

    public void 图标(String string) {
        try {
            this.builder.setIcon(Drawable.createFromStream(this.窗口环境.getAssets().open(string), string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 图标(int n) {
        this.builder.setIcon(n);
    }

    public void 按钮1(String string) {
        this.builder.setPositiveButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (对话框.this.$event_internal_按钮1被单击 != null) {
                    对话框.this.$event_internal_按钮1被单击.按钮1被单击();
                }
            }
        });
    }

    public void 按钮2(String string) {
        this.builder.setNegativeButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (对话框.this.$event_internal_按钮2被单击 != null) {
                    对话框.this.$event_internal_按钮2被单击.按钮2被单击();
                }
            }
        });
    }

    public void 按钮3(String string) {
        this.builder.setNeutralButton(string, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (对话框.this.$event_internal_按钮3被单击 != null) {
                    对话框.this.$event_internal_按钮3被单击.按钮3被单击();
                }
            }
        });
    }

    public void 组件(组件容器 组件容器2) {
        this.container = 组件容器2;
        this.builder.setView(this.container.取根组件().getView());
    }

    public 组件容器 组件() {
        return this.container;
    }

    public void 透明度(float f) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        WindowManager.LayoutParams layoutParams = null;
        if (window != null) {
            layoutParams = window.getAttributes();
        }
        if (layoutParams != null) {
            layoutParams.alpha = f;
        }
        if (window != null) {
            window.setAttributes(layoutParams);
        }
    }

    public void 动画主题(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(n);
        }
    }

    public void 垂直边距(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        WindowManager.LayoutParams layoutParams = null;
        if (window != null) {
            layoutParams = window.getAttributes();
        }
        if (layoutParams != null) {
            layoutParams.verticalMargin = n;
        }
        if (window != null) {
            window.setAttributes(layoutParams);
        }
    }

    public void 水平边距(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        WindowManager.LayoutParams layoutParams = null;
        if (window != null) {
            layoutParams = window.getAttributes();
        }
        if (layoutParams != null) {
            layoutParams.horizontalMargin = n;
        }
        if (window != null) {
            window.setAttributes(layoutParams);
        }
    }

    public void 重力属性(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.setGravity(n);
        }
    }

    public void 可取消(boolean bl) {
        this.builder.setCancelable(bl);
        if (this.dialog != null) {
            this.dialog.setCancelable(bl);
        }
    }

    public void 背景颜色(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(n));
        }
    }

    public void 背景颜色(String string) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(转换操作.转换颜色(string)));
        }
    }

    public void 背景图片(String string) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        if (string == null) {
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(0));
            }
            return;
        }
        if (string.startsWith("/")) {
            if (window != null) {
                window.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile(string)));
            }
        } else {
            try {
                InputStream inputStream = this.取上下文().getAssets().open(string);
                Drawable drawable = Drawable.createFromStream(inputStream, string);
                if (window != null) {
                    window.setBackgroundDrawable(drawable);
                }
                inputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void 背景图片(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(n);
        }
    }

    public void 置对话框高度(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        WindowManager.LayoutParams layoutParams = null;
        if (window != null) {
            layoutParams = window.getAttributes();
        }
        if (layoutParams != null) {
            layoutParams.height = n;
        }
        if (window != null) {
            window.setAttributes(layoutParams);
        }
    }

    public void 置对话框宽度(int n) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        WindowManager.LayoutParams layoutParams = null;
        if (window != null) {
            layoutParams = window.getAttributes();
        }
        if (layoutParams != null) {
            layoutParams.width = n;
        }
        if (window != null) {
            window.setAttributes(layoutParams);
        }
    }

    public void 置列表项(String[] stringArray) {
        this.builder.setItems(stringArray, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (对话框.this.$event_internal_项目被单击 != null) {
                    对话框.this.$event_internal_项目被单击.项目被单击(n);
                }
            }
        });
    }

    public void 置单选列表(String[] stringArray, int n) {
        this.builder.setSingleChoiceItems(stringArray, n, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int n) {
                if (对话框.this.$event_internal_项目被单击 != null) {
                    对话框.this.$event_internal_项目被单击.项目被单击(n);
                }
            }
        });
    }

    public void 置多选列表(String[] stringArray, boolean[] blArray) {
        this.builder.setMultiChoiceItems(stringArray, blArray, new DialogInterface.OnMultiChoiceClickListener() {

            public void onClick(DialogInterface dialogInterface, int n, boolean bl) {
                if (对话框.this.$event_internal_项目被单击 != null) {
                    对话框.this.$event_internal_项目被单击.项目被单击(n);
                }
            }
        });
    }

    public void 置对话框边距(int n, int n2, int n3, int n4) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        Window window = this.dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(n, n2, n3, n4);
        }
    }

    public void 置对话框布局(组件容器 组件容器2) {
        this.container = 组件容器2;
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        this.dialog.setContentView(this.container.取根组件().getView());
    }

    public void 置对话框布局(可视化组件 可视化组件2) {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        this.dialog.setContentView(可视化组件2.getView());
    }

    public void 显示() {
        if (this.dialog == null) {
            this.dialog = this.builder.create();
        }
        this.dialog.show();
    }

    public void 隐藏() {
        this.dialog.hide();
    }

    public void 关闭() {
        this.dialog.dismiss();
    }

    public void 置按钮1被单击(按钮1被单击 按钮1被单击2) {
        this.$event_internal_按钮1被单击 = 按钮1被单击2;
    }

    public void 置按钮2被单击(按钮2被单击 按钮2被单击2) {
        this.$event_internal_按钮2被单击 = 按钮2被单击2;
    }

    public void 置按钮3被单击(按钮3被单击 按钮3被单击2) {
        this.$event_internal_按钮3被单击 = 按钮3被单击2;
    }

    public void 置项目被单击(项目被单击 项目被单击2) {
        this.$event_internal_项目被单击 = 项目被单击2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 项目被单击 {
        void 项目被单击(int var1);
    }

    public interface 按钮3被单击 {
        void 按钮3被单击();
    }

    public interface 按钮2被单击 {
        void 按钮2被单击();
    }

    public interface 按钮1被单击 {
        void 按钮1被单击();
    }

    public static class DialogHandler
            extends Handler {
        public DialogHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            throw new RuntimeException();
        }
    }
}

