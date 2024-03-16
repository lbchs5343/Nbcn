/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Color
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.view.View
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.EditText
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 */
package lbchs.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class 编辑框
        extends 文本框 {
    private final Context 窗口环境;
    private 内容被改变 $event_internal_内容被改变;

    public 编辑框(Context context) {
        super(context);
        this.窗口环境 = context;
        this.getView().addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                if (编辑框.this.$event_internal_内容被改变 != null) {
                    编辑框.this.$event_internal_内容被改变.内容被改变(charSequence.toString());
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public EditText getView() {
        return (EditText) super.getView();
    }

    @Override
    public View 创建视图() {
        return new EditText(this.取上下文());
    }

    @Override
    public String 内容() {
        return super.内容();
    }

    public void 提示文本(String string) {
        this.getView().setHint(string);
    }

    public String 提示文本() {
        return this.getView().getHint().toString();
    }

    public void 提示文本颜色(int n) {
        this.getView().setHintTextColor(n);
    }

    public void 提示文本颜色(String string) {
        this.提示文本颜色(Color.parseColor(string));
    }

    public int 提示文本颜色() {
        return this.getView().getHintTextColors().getDefaultColor();
    }

    public void 输入方式(int n) {
        this.getView().setInputType(n);
    }

    public int 输入方式() {
        return this.getView().getInputType();
    }

    public void 光标位置(int n) {
        this.getView().setSelection(n);
    }

    public int 光标位置() {
        return this.getView().getSelectionStart();
    }

    public void 密码输入(boolean bl) {
        if (bl) {
            this.输入方式(129);
        } else {
            this.输入方式(0);
        }
    }

    public void 单行输入(boolean bl) {
        this.getView().setSingleLine(bl);
    }

    public void 显示光标(boolean bl) {
        this.getView().setCursorVisible(bl);
    }

    public void 全选() {
        this.getView().selectAll();
    }

    public void 删除文本(int n, int n2) {
        this.getView().getText().delete(n, n2);
    }

    public void 选中文本(int n, int n2) {
        this.getView().setSelection(n, n2);
    }

    public void 插入文本(int n, String string) {
        this.getView().getText().insert(n, string);
    }

    public void 追加文本(String string) {
        this.getView().getText().append(string);
    }

    public void 显示输入法() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((InputMethodManager) this.窗口环境.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.getView(), 0);
        }
    }

    public void 隐藏输入法() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((InputMethodManager) this.窗口环境.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getView().getApplicationWindowToken(), 0);
        }
    }

    public void 置内容被改变(内容被改变 内容被改变2) {
        this.$event_internal_内容被改变 = 内容被改变2;
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }

    public interface 内容被改变 {
        void 内容被改变(String var1);



    }
}
