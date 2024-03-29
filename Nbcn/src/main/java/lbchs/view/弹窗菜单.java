/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.view.MenuItem
 *  android.widget.PopupMenu
 *  android.widget.PopupMenu$OnMenuItemClickListener
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MenuItem;
import android.widget.PopupMenu;

import java.io.IOException;

public class 弹窗菜单
        extends 组件 {
    private final Context activity;
    private final PopupMenu mPopupMenu;
    private 菜单项被单击 $event_internal_菜单项被单击;

    public 弹窗菜单(Context context, 可视化组件 可视化组件2) {
        this.activity = context;
        this.mPopupMenu = new PopupMenu(context, 可视化组件2.getView());
        this.mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem menuItem) {
                if (弹窗菜单.this.$event_internal_菜单项被单击 != null) {
                    弹窗菜单.this.$event_internal_菜单项被单击.菜单项被单击(menuItem.getTitle().toString());
                }
                return true;
            }
        });
    }

    public void 添加菜单(int n, int n2, int n3, String string) {
        this.mPopupMenu.getMenu().add(n, n2, n3, string);
    }

    public void 添加菜单(String string) {
        this.mPopupMenu.getMenu().add(string);
    }

    public void 添加菜单(String string, String string2) {
        try {
            this.mPopupMenu.getMenu().add(string).setIcon(Drawable.createFromStream(this.activity.getAssets().open(string2), string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 添加菜单(int n, int n2, int n3, String string, String string2) {
        try {
            this.mPopupMenu.getMenu().add(n, n2, n3, string).setIcon(Drawable.createFromStream(this.activity.getAssets().open(string2), string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 添加子菜单(int n, int n2, int n3, String string) {
        this.mPopupMenu.getMenu().addSubMenu(n, n2, n3, string);
    }

    public void 添加子菜单(int n, int n2, int n3, String string, String string2) {
        try {
            this.mPopupMenu.getMenu().addSubMenu(n, n2, n3, string).setIcon(Drawable.createFromStream(this.activity.getAssets().open(string2), string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 重力属性(int n) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.mPopupMenu.setGravity(n);
        }
    }

    public void 显示() {
        this.mPopupMenu.show();
    }

    public void 关闭() {
        this.mPopupMenu.dismiss();
    }

    public void 置菜单项被单击(菜单项被单击 菜单项被单击2) {
        this.$event_internal_菜单项被单击 = 菜单项被单击2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 菜单项被单击 {
        void 菜单项被单击(String var1);
    }
}

