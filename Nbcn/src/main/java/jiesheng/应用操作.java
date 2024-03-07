/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.pm.ResolveInfo
 *  android.net.Uri
 *  android.os.Parcelable
 *  android.widget.Toast
 *  java.io.File
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.util.List
 */
package jiesheng;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class 应用操作 {
    public static void 弹出提示(Context context, Object object) {
        Toast.makeText((Context) context, (CharSequence) 全局方法类.到文本(object), (int) 1500).show();
    }

    public static void 弹出提示(Context context, Object object, int n) {
        Toast.makeText((Context) context, (CharSequence) 全局方法类.到文本(object), (int) n).show();
    }

    public static void 信息框(Context context, Object object) {
        对话框 对话框2 = new 对话框(context);
        对话框2.信息(全局方法类.到文本(object));
        对话框2.按钮1("确定");
        对话框2.显示();
    }

    public static void 信息框(Context context, Object object, Object object2) {
        对话框 对话框2 = new 对话框(context);
        对话框2.标题(全局方法类.到文本(object));
        对话框2.信息(全局方法类.到文本(object2));
        对话框2.按钮1("确定");
        对话框2.显示();
    }

    public static String 输入框(Context context, String string) {
        return 输入对话框.inputBox(context, string);
    }

    public static String 输入框(Context context, String string, String string2) {
        return 输入对话框.inputBox(context, string, string2);
    }

    public static String 输入框(Context context, String string, String string2, String string3) {
        return 输入对话框.inputBox(context, string, string2, string3);
    }

    public static String 密码输入框(Context context, String string) {
        return 输入对话框.passwordInputBox(context, string);
    }

    public static String 密码输入框(Context context, String string, String string2) {
        return 输入对话框.passwordInputBox(context, string, string2);
    }

    public static String 日期选择框(Context context) {
        return 日期选择框.datePickerBox(context);
    }

    public static String 日期选择框(Context context, int n, int n2, int n3) {
        return 日期选择框.datePickerBox(context, n, n2, n3);
    }

    public static String 时间选择框(Context context) {
        return 时间选择框.timePickerBox(context);
    }

    public static String 时间选择框(Context context, int n, int n2) {
        return 时间选择框.timePickerBox(context, n, n2);
    }

    public static int 颜色选择框(Context context, String string) {
        return 颜色选择框.colorPickerBox(context, string);
    }

    public static int 颜色选择框(Context context, String string, int n) {
        return 颜色选择框.colorPickerBox(context, string, n);
    }

    public static int 单选框(Context context, String[] stringArray) {
        return 单选对话框.singleChoiceBox(context, stringArray);
    }

    public static int 单选框(Context context, String[] stringArray, int n) {
        return 单选对话框.singleChoiceBox(context, stringArray, n);
    }

    public static int 单选框(Context context, String string, String[] stringArray, int n) {
        return 单选对话框.singleChoiceBox(context, string, stringArray, n);
    }

    public static void 打开Uri(Activity activity, String string) {
        意图.打开Uri(activity, string);
    }

    public static void 打开应用(Activity activity, String string) {
        String string2 = null;
        PackageManager packageManager = activity.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(0x10200000);
        List list = packageManager.queryIntentActivities(intent, 1);
        for (int i = 0; i < list.size(); ++i) {
            ResolveInfo resolveInfo = (ResolveInfo) list.get(i);
            if (!resolveInfo.activityInfo.packageName.equals((Object) string)) continue;
            string2 = resolveInfo.activityInfo.name;
            break;
        }
        if (string2.isEmpty()) {
            return;
        }
        intent.setComponent(new ComponentName(string, string2));
        activity.startActivity(intent);
    }

    public static boolean 打开QQ聊天(Activity activity, String string) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String) ("mqqwpa://im/chat?chat_type=wpa&uin=" + string + "&version=1"))));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean 打开QQ加群(Activity activity, String string) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String) ("mqqwpa://im/chat?chat_type=group&uin=" + string + "&version=1"))));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean 打开QQ加群2(Activity activity, String string) {
        Intent intent = new Intent();
        intent.setData(Uri.parse((String) ("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + string)));
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static void 一键分享(Activity activity, String string) {
        应用操作.一键分享(activity, string, "");
    }

    public static void 一键分享(Activity activity, String string, String string2) {
        Intent intent = new Intent("android.intent.action.SEND");
        if ("".equals((Object) string2)) {
            intent.setType("text/plain");
        } else {
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", (Parcelable) Uri.fromFile((File) new File(string2)));
        }
        intent.putExtra("android.intent.extra.SUBJECT", "分享到");
        intent.putExtra("android.intent.extra.TEXT", string);
        intent.setFlags(0x10000000);
        activity.startActivity(Intent.createChooser((Intent) intent, (CharSequence) "分享到"));
    }

    public void 初始化事件() {
    }
}

