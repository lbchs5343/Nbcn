/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.graphics.Color
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.text.Html
 *  android.text.Spannable
 *  android.text.SpannableString
 *  android.text.SpannableStringBuilder
 *  android.text.TextPaint
 *  android.text.TextUtils$TruncateAt
 *  android.text.method.LinkMovementMethod
 *  android.text.style.ClickableSpan
 *  android.text.style.ForegroundColorSpan
 *  android.text.style.URLSpan
 *  android.text.style.UnderlineSpan
 *  android.view.View
 *  android.widget.TextView
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package lbchs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 文本框
        extends 可视化组件 {
    private final AssetManager am;
    private final Context 窗口环境;
    private 超链接被单击 $event_internal_超链接被单击;

    public 文本框(Context context) {
        super(context);
        this.窗口环境 = context;
        this.am = context.getResources().getAssets();
    }

    private void interceptHyperLink(TextView textView) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence charSequence = textView.getText();
        if (charSequence instanceof Spannable) {
            int n = charSequence.length();
            Spannable spannable = (Spannable) textView.getText();
            URLSpan[] uRLSpanArray = spannable.getSpans(0, n, URLSpan.class);
            if (uRLSpanArray.length == 0) {
                return;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
            for (URLSpan uRLSpan : uRLSpanArray) {
                String string = uRLSpan.getURL();
                if (string.indexOf("http://") != 0 && string.indexOf("https://") != 0) continue;
                CustomUrlSpan customUrlSpan = new CustomUrlSpan(this.窗口环境, string);
                spannableStringBuilder.setSpan(customUrlSpan, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 17);
            }
            textView.setText(spannableStringBuilder);
        }
    }

    private void removeHyperLinkUnderline(TextView textView) {
        CharSequence charSequence = textView.getText();
        if (charSequence instanceof Spannable) {
            Spannable spannable = (Spannable) textView.getText();
            spannable.setSpan(new NoUnderlineSpan(), 0, charSequence.length(), 17);
        }
    }

    public TextView getView() {
        return (TextView) super.getView();
    }

    @Override
    public View 创建视图() {
        return new TextView(this.取上下文());
    }

    public void 内容(String string) {
        this.getView().setText(string);
    }

    public String 内容() {
        return this.getView().getText().toString();
    }

    public void 字体大小(int n) {
        this.getView().setTextSize((float) n);
    }

    public int 字体大小() {
        return (int) this.getView().getTextSize();
    }

    public void 字体颜色(int n) {
        this.getView().setTextColor(n);
    }

    public int 字体颜色() {
        return this.getView().getTextColors().getDefaultColor();
    }

    public void 字体颜色(String string) {
        this.字体颜色(Color.parseColor(string));
    }

    public void 显示行数(int n) {
        this.getView().setLines(n);
    }

    public void 最大显示行数(int n) {
        this.getView().setMaxLines(n);
    }

    public void 最小显示行数(int n) {
        this.getView().setMinLines(n);
    }

    public void 行间距(int n) {
        this.getView().setLineSpacing(0.0f, (float) n);
    }

    public void 可选择文本(boolean bl) {
        this.getView().setTextIsSelectable(bl);
    }

    public void 斜体(boolean bl) {
        if (bl) {
            this.getView().setTypeface(this.getView().getTypeface(), Typeface.ITALIC);
        } else {
            this.getView().setTypeface(this.getView().getTypeface(), Typeface.NORMAL);
        }
    }

    @SuppressLint("WrongConstant")
    public void 粗体(boolean bl) {
        if (bl) {
            this.getView().getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        } else {
            this.getView().getPaint().setFlags(Paint.DEV_KERN_TEXT_FLAG);
        }
    }

    public void 按下图片(String string) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream(inputStream, string);
            this.sld.addState(new int[]{16842919}, drawable);
            this.getView().setBackground(this.sld);
            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 弹起图片(String string) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream(inputStream, string);
            this.sld.addState(new int[]{0}, drawable);
            this.getView().setBackground(this.sld);
            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 字体(String string) {
        if (!string.startsWith("/")) {
            this.getView().setTypeface(Typeface.createFromAsset(this.窗口环境.getAssets(), string));
        } else {
            this.getView().setTypeface(Typeface.createFromFile(string));
        }
    }

    public void 左侧图标(String string) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream(inputStream, string);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.getView().setCompoundDrawables(drawable, null, null, null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 跑马灯效果(boolean bl) {
        TextView textView = this.getView();
        textView.setMarqueeRepeatLimit(Integer.MAX_VALUE);
        textView.setFocusable(true);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine();
        textView.setFocusableInTouchMode(true);
        textView.setHorizontallyScrolling(bl);
    }

    public void 单行显示(boolean bl) {
        this.getView().setSingleLine(bl);
    }

    public void 省略显示(int n) {
        TextView textView = this.getView();
        if (n == 0) {
            textView.setEllipsize(TextUtils.TruncateAt.START);
        } else if (n == 1) {
            textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        } else if (n == 2) {
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    public void 加载超文本(String string) {
        this.getView().setText(Html.fromHtml(string));
        this.getView().invalidate();
    }

    public void 识别超链接() {
        this.getView().setAutoLinkMask(1);
        this.interceptHyperLink(this.getView());
    }

    public void 识别超链接(boolean bl) {
        this.getView().setAutoLinkMask(1);
        this.interceptHyperLink(this.getView());
        if (bl) {
            this.removeHyperLinkUnderline(this.getView());
        }
    }

    public void 高亮(String[] stringArray, int n) {
        SpannableString spannableString = new SpannableString(this.内容());
        for (String s : stringArray) {
            Pattern pattern = Pattern.compile(s);
            Matcher matcher = pattern.matcher(spannableString);
            while (matcher.find()) {
                int n2 = matcher.start();
                int n3 = matcher.end();
                spannableString.setSpan(new ForegroundColorSpan(n), n2, n3, 33);
            }
        }
        this.getView().setText(spannableString);
    }

    public void 高亮(String[] stringArray, String string) {
        this.高亮(stringArray, Color.parseColor(string));
    }

    public void 置左侧图标(String string, int n, int n2, int n3) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream(inputStream, string);
            drawable.setBounds(0, 0, n, n2);
            this.getView().setCompoundDrawables(drawable, null, null, null);
            this.getView().setCompoundDrawablePadding(n3);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 置超链接被单击(超链接被单击 超链接被单击2) {
        this.$event_internal_超链接被单击 = 超链接被单击2;
    }

    @Override
    public void 初始化事件() {
    }

    public interface 超链接被单击 {
        void 超链接被单击(String var1);
    }

    static class NoUnderlineSpan
            extends UnderlineSpan {
        NoUnderlineSpan() {
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    class CustomUrlSpan
            extends ClickableSpan {
        private final String url;

        public CustomUrlSpan(Context context, String string) {
            this.url = string;
        }

        public void onClick(View view) {
            if (文本框.this.$event_internal_超链接被单击 != null) {
                文本框.this.$event_internal_超链接被单击.超链接被单击(this.url);
            }
        }
    }
}

