/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Canvas
 *  android.graphics.LinearGradient
 *  android.graphics.Matrix
 *  android.graphics.Paint
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffXfermode
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.Xfermode
 *  android.net.Uri
 *  android.provider.MediaStore$Images$Media
 *  java.io.ByteArrayInputStream
 *  java.io.ByteArrayOutputStream
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Byte
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 */
package jiesheng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class 图片操作 {
    public static byte[] Bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap Bytes2Bitmap(byte[] byArray) {
        return BitmapFactory.decodeByteArray((byte[]) byArray, (int) 0, (int) byArray.length);
    }

    public static byte[] 取图片字节数组(Context context, String string) {
        if (string.startsWith("/")) {
            File file = new File(string);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile((String) string);
                return 图片操作.Bitmap2Bytes(bitmap);
            }
        } else {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) context.getResources().getAssets().open(string));
                return 图片操作.Bitmap2Bytes(bitmap);
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] 旋转图片(byte[] byArray, float f) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        Bitmap bitmap2 = Bitmap.createBitmap((Bitmap) bitmap, (int) 0, (int) 0, (int) bitmap.getWidth(), (int) bitmap.getHeight(), (Matrix) matrix, (boolean) true);
        return 图片操作.Bitmap2Bytes(bitmap2);
    }

    public static byte[] 缩放图片(byte[] byArray, int n, int n2) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        int n3 = bitmap.getWidth();
        int n4 = bitmap.getHeight();
        float f = n / n3;
        float f2 = n2 / n4;
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return 图片操作.Bitmap2Bytes(Bitmap.createBitmap((Bitmap) bitmap, (int) 0, (int) 0, (int) n3, (int) n4, (Matrix) matrix, (boolean) true));
    }

    public static byte[] 反转图片(byte[] byArray, int n) {
        float[] fArray = null;
        switch (n) {
            case 0: {
                fArray = new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
                break;
            }
            case 1: {
                fArray = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
            }
        }
        if (fArray != null) {
            Matrix matrix = new Matrix();
            matrix.setValues(fArray);
            Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
            return 图片操作.Bitmap2Bytes(Bitmap.createBitmap((Bitmap) bitmap, (int) 0, (int) 0, (int) bitmap.getWidth(), (int) bitmap.getHeight(), (Matrix) matrix, (boolean) true));
        }
        return byArray;
    }

    public static byte[] 压缩图片(byte[] byArray) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream) byteArrayOutputStream);
        int n = 100;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, n, (OutputStream) byteArrayOutputStream);
            n -= 10;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Bitmap bitmap2 = BitmapFactory.decodeStream((InputStream) byteArrayInputStream, null, null);
        return 图片操作.Bitmap2Bytes(bitmap2);
    }

    public static void 压缩图片2(String string, String string2, int n) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(string, options);
        options.inJustDecodeBounds = false;
        int n2 = options.outHeight / n;
        if (n2 <= 0) {
            n2 = 1;
        }
        options.inSampleSize = n2;
        bitmap = BitmapFactory.decodeFile(string, options);

        File file = new File(string2);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream) fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static 集合<Byte[]> 切割图片(byte[] byArray, int n, int n2) {
        集合 集合2 = new 集合();
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        int n3 = bitmap.getWidth();
        int n4 = bitmap.getHeight();
        int n5 = n3 / n;
        int n6 = n4 / n2;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                int n7 = j * n5;
                int n8 = i * n6;
                Bitmap bitmap2 = Bitmap.createBitmap(bitmap, n7, n8, n5, n6);
                集合2.添加项目(Bitmap2Bytes(bitmap2));
            }
        }
        return 集合2;
    }

    public static byte[] 倾斜图片(byte[] byArray, float f, float f2) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        Matrix matrix = new Matrix();
        matrix.postSkew(f, f2);
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, (int) bitmap.getWidth(), (int) bitmap.getHeight(), (Matrix) matrix, (boolean) true);
        return 图片操作.Bitmap2Bytes(bitmap2);
    }

    public static byte[] 设置图片圆角(byte[] byArray, int n) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        Bitmap bitmap2 = 图片操作.toRoundCorner(bitmap, n);
        return 图片操作.Bitmap2Bytes(bitmap2);
    }

    public static byte[] 设置图片倒影(byte[] byArray) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        Bitmap bitmap2 = 图片操作.createReflectionImageWithOrigin(bitmap);
        return 图片操作.Bitmap2Bytes(bitmap2);
    }

    public static int 取图片宽度(Activity activity, String string) {
        int n = 0;
        if (string.length() > 0) {
            if (string.startsWith("/")) {
                File file = new File(string);
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(string);
                    n = bitmap.getWidth();
                }
            } else {
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(activity.getResources().getAssets().open(string));
                    n = bitmap.getWidth();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return n;
    }

    public static int 取图片高度(Activity activity, String string) {
        int n = 0;
        if (string.length() > 0) {
            if (string.startsWith("/")) {
                File file = new File(string);
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(string);
                    n = bitmap.getHeight();
                }
            } else {
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(activity.getResources().getAssets().open(string));
                    n = bitmap.getHeight();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return n;
    }

    public static int 取图片宽度2(byte[] byArray) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        int n = bitmap.getWidth();
        return n;
    }

    public static int 取图片高度2(byte[] byArray) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        int n = bitmap.getHeight();
        return n;
    }

    public static byte[] 取图片部分(byte[] byArray, int n, int n2, int n3, int n4) {
        Bitmap bitmap = 图片操作.Bytes2Bitmap(byArray);
        bitmap = Bitmap.createBitmap(bitmap, n, n2, n3, n4);
        return 图片操作.Bitmap2Bytes(bitmap);
    }

    public static void 更新系统相册(Activity activity, String string) {
        try {
            File file = new File(string);
            MediaStore.Images.Media.insertImage(activity.getContentResolver(), (String) file.getAbsolutePath(), (String) file.getName(), null);
        } catch (IOException iOException) {
            // empty catch block
        }
        activity.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile((File) new File(string))));
    }

    private static Bitmap toRoundCorner(Bitmap bitmap, int n) {
        Bitmap bitmap2 = Bitmap.createBitmap((int) bitmap.getWidth(), (int) bitmap.getHeight(), (Bitmap.Config) Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);
        int n2 = -12434878;
        Paint paint = new Paint();
        paint.setColor(n2);
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f = n;
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode((Xfermode) new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmap2;
    }

    private static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        int n = bitmap.getWidth();
        int n2 = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, (n2 / 2), n, (n2 / 2), matrix, false);
        Bitmap bitmap3 = Bitmap.createBitmap(n, (n2 + n2 / 2), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap3);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        Paint paint = new Paint();
        canvas.drawRect(0.0f, (float) n2, (float) n, (float) (n2 + 4), paint);
        canvas.drawBitmap(bitmap2, 0.0f, (float) (n2 + 4), null);
        Paint paint2 = new Paint();
        LinearGradient linearGradient = new LinearGradient(0.0f, (float) bitmap.getHeight(), 0.0f, (float) (bitmap3.getHeight() + 4), 0x70FFFFFF, 0xFFFFFF, Shader.TileMode.CLAMP);
        paint2.setShader((Shader) linearGradient);
        paint2.setXfermode((Xfermode) new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, (float) n2, (float) n, (float) (bitmap3.getHeight() + 4), paint2);
        return bitmap3;
    }

    public void 初始化事件() {
    }
}

