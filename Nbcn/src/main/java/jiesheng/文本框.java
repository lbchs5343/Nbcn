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
package jiesheng;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
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
    private AssetManager am;
    private CustomUrlSpan customUrlSpan;
    private Context 窗口环境;
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
            URLSpan[] uRLSpanArray = (URLSpan[]) spannable.getSpans(0, n, URLSpan.class);
            if (uRLSpanArray.length == 0) {
                return;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
            for (URLSpan uRLSpan : uRLSpanArray) {
                String string = uRLSpan.getURL();
                if (string.indexOf("http://") != 0 && string.indexOf("https://") != 0) continue;
                this.customUrlSpan = new CustomUrlSpan(this.窗口环境, string);
                spannableStringBuilder.setSpan((Object) this.customUrlSpan, spannable.getSpanStart((Object) uRLSpan), spannable.getSpanEnd((Object) uRLSpan), 17);
            }
            textView.setText((CharSequence) spannableStringBuilder);
        }
    }

    private void removeHyperLinkUnderline(TextView textView) {
        CharSequence charSequence = textView.getText();
        if (charSequence instanceof Spannable) {
            Spannable spannable = (Spannable) textView.getText();
            spannable.setSpan((Object) new NoUnderlineSpan(), 0, charSequence.length(), 17);
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
        this.getView().setText((CharSequence) string);
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
        this.字体颜色(Color.parseColor((String) string));
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
            this.getView().setTypeface(this.getView().getTypeface(), 2);
        } else {
            this.getView().setTypeface(this.getView().getTypeface(), 0);
        }
    }

    public void 粗体(boolean bl) {
        if (bl) {
            this.getView().getPaint().setFlags(32);
        } else {
            this.getView().getPaint().setFlags(256);
        }
    }

    public void 按下图片(String string) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream((InputStream) inputStream, (String) string);
            this.sld.addState(new int[]{16842919}, drawable);
            this.getView().setBackground((Drawable) this.sld);
            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 弹起图片(String string) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream((InputStream) inputStream, (String) string);
            this.sld.addState(new int[]{0}, drawable);
            this.getView().setBackground((Drawable) this.sld);
            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void 字体(String string) {
        if (!string.startsWith("/")) {
            this.getView().setTypeface(Typeface.createFromAsset((AssetManager) this.窗口环境.getAssets(), (String) string));
        } else {
            this.getView().setTypeface(Typeface.createFromFile((String) string));
        }
    }

    public void 左侧图标(String string) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream((InputStream) inputStream, (String) string);
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
        if (bl) {
            textView.setHorizontallyScrolling(true);
        } else {
            textView.setHorizontallyScrolling(false);
        }
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
        this.getView().setText((CharSequence) Html.fromHtml((String) string));
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
        SpannableString spannableString = new SpannableString((CharSequence) this.内容());
        for (int i = 0; i < stringArray.length; ++i) {
            Pattern pattern = Pattern.compile((String) stringArray[i]);
            Matcher matcher = pattern.matcher((CharSequence) spannableString);
            while (matcher.find()) {
                int n2 = matcher.start();
                int n3 = matcher.end();
                spannableString.setSpan((Object) new ForegroundColorSpan(n), n2, n3, 33);
            }
        }
        this.getView().setText((CharSequence) spannableString);
    }

    public void 高亮(String[] stringArray, String string) {
        this.高亮(stringArray, Color.parseColor((String) string));
    }

    public void 置左侧图标(String string, int n, int n2, int n3) {
        try {
            InputStream inputStream = this.am.open(string);
            Drawable drawable = Drawable.createFromStream((InputStream) inputStream, (String) string);
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

    public static interface 超链接被单击 {
        public void 超链接被单击(String var1);
    }

    class NoUnderlineSpan
            extends UnderlineSpan {
        NoUnderlineSpan() {
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    class CustomUrlSpan
            extends ClickableSpan {
        private Context context;
        private String url;

        public CustomUrlSpan(Context context, String string) {
            this.context = context;
            this.url = string;
        }

        public void onClick(View view) {
            if (文本框.this.$event_internal_超链接被单击 != null) {
                文本框.this.$event_internal_超链接被单击.超链接被单击(this.url);
            }
        }
    }
}

