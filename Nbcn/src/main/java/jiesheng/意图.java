/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.net.Uri
 *  android.os.Build$VERSION
 *  android.os.Environment
 *  android.provider.DocumentsContract
 *  android.provider.MediaStore$Audio$Media
 *  android.provider.MediaStore$Images$Media
 *  android.provider.MediaStore$Video$Media
 *  android.widget.Toast
 *  java.io.File
 *  java.lang.CharSequence
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 */
package jiesheng;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

public class 意图
        extends Intent {
    public 意图() {
    }

    public 意图(意图 意图2) {
        super((Intent) 意图2);
    }

    public 意图(String string) {
        super(string);
    }

    public 意图(Context context, Object object) {
        super(context, object.getClass());
    }

    public 意图(Intent intent) {
        super(intent);
    }

    public static void 选择图片(Activity activity, int n) {
        activity.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), n);
    }

    public static void 选择图片(Activity activity) {
        意图.选择图片(activity, 1);
    }

    public static String 解析图片地址(Activity activity, 意图 意图2) {
        if (null != 意图2) {
            Uri uri = 意图2.getData();
            String[] stringArray = new String[]{"_data"};
            Cursor cursor = activity.getContentResolver().query(uri, stringArray, null, null, null);
            cursor.moveToFirst();
            int n = cursor.getColumnIndex(stringArray[0]);
            String string = cursor.getString(n);
            cursor.close();
            return string;
        }
        return "";
    }

    public static void 选择文件(Activity activity, int n) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        activity.startActivityForResult(intent, n);
    }

    public static void 选择文件(Activity activity) {
        意图.选择文件(activity, 1);
    }

    public static String 解析文件地址(Activity activity, 意图 意图2) {
        if (null != 意图2) {
            return FileChooseUtil.getInstance((Context) activity).getChooseFileResultPath(意图2.getData());
        }
        return "";
    }

    public static void 打开Uri(Activity activity, String string) {
        Uri uri = Uri.parse((String) string);
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        activity.startActivity(intent);
    }

    public String 动作文本() {
        return this.getAction();
    }

    public void 动作文本(String string) {
        this.setAction(string);
    }

    public String 外部启动协议() {
        return this.getData().getScheme();
    }

    public String 外部启动主机() {
        return this.getData().getHost();
    }

    public int 外部启动端口() {
        return this.getData().getPort();
    }

    public String 外部启动疑问词() {
        return this.getData().getQuery();
    }

    public String 外部启动路径() {
        return this.getData().getPath();
    }

    public void 置文件数据及类型(String string, String string2) {
        this.置数据及类型(Uri.fromFile((File) new File(string)), string2);
    }

    public void 置数据及类型(Uri uri, String string) {
        this.setDataAndType(uri, string);
    }

    public void 置文本(String string, String string2) {
        this.putExtra(string, string2);
    }

    public String 取文本(String string) {
        return this.getStringExtra(string);
    }

    public void 置整数(String string, int n) {
        this.putExtra(string, n);
    }

    public int 取整数(String string) {
        return this.getIntExtra(string, 0);
    }

    public int 取整数(String string, int n) {
        return this.getIntExtra(string, n);
    }

    public void 置文本数组(String string, String[] stringArray) {
        this.putExtra(string, stringArray);
    }

    public String[] 取文本数组(String string) {
        return this.getStringArrayExtra(string);
    }

    public void 置整数数组(String string, int[] nArray) {
        this.putExtra(string, nArray);
    }

    public int[] 取整数数组(String string) {
        return this.getIntArrayExtra(string);
    }

    public void 标志(int n) {
        this.setFlags(n);
    }

    public void 添加标志(int n) {
        this.addFlags(n);
    }

    public void 初始化事件() {
    }

    static class FileChooseUtil {
        private static FileChooseUtil util = null;
        private Context context;

        private FileChooseUtil(Context context) {
            this.context = context;
        }

        public static FileChooseUtil getInstance(Context context) {
            if (util == null) {
                util = new FileChooseUtil(context);
            }
            return util;
        }

        public String getChooseFileResultPath(Uri uri) {
            String string = null;
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                string = uri.getPath();
                Toast.makeText((Context) this.context, (CharSequence) string, (int) 0).show();
                return string;
            }
            string = Build.VERSION.SDK_INT > 19 ? this.getPath(this.context, uri) : this.getRealPathFromURI(uri);
            return string;
        }

        private String getRealPathFromURI(Uri uri) {
            String string = null;
            String[] stringArray = new String[]{"_data"};
            Cursor cursor = this.context.getContentResolver().query(uri, stringArray, null, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                int n = cursor.getColumnIndexOrThrow("_data");
                string = cursor.getString(n);
                cursor.close();
            }
            return string;
        }

        private String getPath(Context context, Uri uri) {
            boolean bl;
            boolean bl2 = bl = Build.VERSION.SDK_INT >= 19;
            if (bl && DocumentsContract.isDocumentUri((Context) context, (Uri) uri)) {
                if (this.isExternalStorageDocument(uri)) {
                    String string = DocumentsContract.getDocumentId((Uri) uri);
                    String[] stringArray = string.split(":");
                    String string2 = stringArray[0];
                    if ("primary".equalsIgnoreCase(string2)) {
                        return Environment.getExternalStorageDirectory() + "/" + stringArray[1];
                    }
                } else {
                    if (this.isDownloadsDocument(uri)) {
                        String string = DocumentsContract.getDocumentId((Uri) uri);
                        Uri uri2 = ContentUris.withAppendedId((Uri) Uri.parse((String) "content://downloads/public_downloads"), (long) Long.valueOf((String) string));
                        return this.getDataColumn(context, uri2, null, null);
                    }
                    if (this.isMediaDocument(uri)) {
                        String string = DocumentsContract.getDocumentId((Uri) uri);
                        String[] stringArray = string.split(":");
                        String string3 = stringArray[0];
                        Uri uri3 = null;
                        if ("image".equals((Object) string3)) {
                            uri3 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if ("video".equals((Object) string3)) {
                            uri3 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else if ("audio".equals((Object) string3)) {
                            uri3 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }
                        String[] stringArray2 = new String[]{stringArray[1]};
                        return this.getDataColumn(context, uri3, "_id=?", stringArray2);
                    }
                }
            } else {
                if ("content".equalsIgnoreCase(uri.getScheme())) {
                    return this.getDataColumn(context, uri, null, null);
                }
                if ("file".equalsIgnoreCase(uri.getScheme())) {
                    uri.getPath();
                }
            }
            return null;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
            Cursor cursor = null;
            final String column = "_data";
            final String[] projection = {column};
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                        null);
                if (cursor != null && cursor.moveToFirst()) {
                    final int column_index = cursor.getColumnIndexOrThrow(column);
                    return cursor.getString(column_index);
                }
            } finally {
                if (cursor != null)
                    cursor.close();
            }
            return null;
        }

        private boolean isExternalStorageDocument(Uri uri) {
            return "com.android.externalstorage.documents".equals((Object) uri.getAuthority());
        }

        private boolean isDownloadsDocument(Uri uri) {
            return "com.android.providers.downloads.documents".equals((Object) uri.getAuthority());
        }

        private boolean isMediaDocument(Uri uri) {
            return "com.android.providers.media.documents".equals((Object) uri.getAuthority());
        }
    }
}

