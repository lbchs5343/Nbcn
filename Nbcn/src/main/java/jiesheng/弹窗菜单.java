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
package jiesheng;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.widget.PopupMenu;

import java.io.IOException;
import java.io.InputStream;

public class 弹窗菜单
        extends 组件 {
    private Context activity;
    private PopupMenu mPopupMenu;
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
        this.mPopupMenu.getMenu().add(n, n2, n3, (CharSequence) string);
    }

    public void 添加菜单(String string) {
        this.mPopupMenu.getMenu().add((CharSequence) string);
    }

    public void 添加菜单(String string, String string2) {
        try {
            this.mPopupMenu.getMenu().add((CharSequence) string).setIcon(Drawable.createFromStream((InputStream) this.activity.getAssets().open(string2), (String) string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 添加菜单(int n, int n2, int n3, String string, String string2) {
        try {
            this.mPopupMenu.getMenu().add(n, n2, n3, (CharSequence) string).setIcon(Drawable.createFromStream((InputStream) this.activity.getAssets().open(string2), (String) string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 添加子菜单(int n, int n2, int n3, String string) {
        this.mPopupMenu.getMenu().addSubMenu(n, n2, n3, (CharSequence) string);
    }

    public void 添加子菜单(int n, int n2, int n3, String string, String string2) {
        try {
            this.mPopupMenu.getMenu().addSubMenu(n, n2, n3, (CharSequence) string).setIcon(Drawable.createFromStream((InputStream) this.activity.getAssets().open(string2), (String) string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 重力属性(int n) {
        this.mPopupMenu.setGravity(n);
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
    }

    public static interface 菜单项被单击 {
        public void 菜单项被单击(String var1);
    }
}

