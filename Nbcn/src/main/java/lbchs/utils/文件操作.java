/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  java.io.BufferedInputStream
 *  java.io.BufferedReader
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.OutputStream
 *  java.io.OutputStreamWriter
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Comparator
 *  java.util.Date
 */
package lbchs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import lbchs.常量类;
import lbchs.集合;

public class 文件操作 {
    private static FileInputStream fin;
    private static BufferedReader br;
    private static FileOutputStream fout;
    private static BufferedWriter bw;

    public static boolean 重命名文件(String string, String string2) {
        if (string2.equals(string)) {
            return true;
        }
        File file = new File(string);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(string2);
        if (file2.exists()) {
            return false;
        }
        return file.renameTo(file2);
    }

    public static boolean 复制文件(String string, String string2) {
        try {
            FileUtils.copyTo(new File(string), new File(string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return false;
        }
        return true;
    }

    public static void 移动文件(String string, String string2) {
        try {
            FileUtils.moveTo(new File(string), new File(string2));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static String 取文件前缀名(String string) {
        return FileUtils.getFilePrefix(new File(string));
    }

    public static String 取文件后缀名(String string) {
        return FileUtils.getFileSuffix(new File(string));
    }

    public static String 取文件名(String string) {
        return FileUtils.getFileName(string);
    }

    public static String 取文件MD5(String string) {
        try {
            return FileUtils.getMD5(new File(string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return "";
        }
    }

    public static String 取文件SHA1(String string) {
        try {
            return FileUtils.getSHA1(new File(string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return "";
        }
    }

    public static String 取文件CRC32(String string) {
        try {
            return FileUtils.getCRC32(new File(string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return "";
        }
    }

    public static void 取文件MD5(String string, String string2) {
        try {
            FileUtils.append(new File(string), string2);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static boolean 删除文件(String string) {
        return FileUtils.deleteFile(new File(string));
    }

    public static boolean 创建目录(String string) {
        return FileUtils.createDirectory(new File(string));
    }

    public static boolean 创建文件(String string) {
        return FileUtils.createFile(new File(string));
    }

    public static boolean 是否为目录(String string) {
        File file = new File(string);
        return file.exists() && file.isDirectory();
    }

    public static boolean 是否为隐藏文件(String string) {
        File file = new File(string);
        if (file.exists()) {
            return file.isHidden();
        }
        return false;
    }

    public static boolean 文件是否存在(String string) {
        return new File(string).exists();
    }

    public static String 取文件编码(String string) {
        try {
            BufferedInputStream bufferedInputStream = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bufferedInputStream = new BufferedInputStream(Files.newInputStream(new File(string).toPath()));
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.mark(4);
            }
            byte[] byArray = new byte[3];
            if (bufferedInputStream != null) {
                bufferedInputStream.read(byArray);
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.reset();
            }
            if (byArray[0] == -17 && byArray[1] == -69 && byArray[2] == -65) {
                return "utf-8";
            }
            if (byArray[0] == -1 && byArray[1] == -2) {
                return "unicode";
            }
            if (byArray[0] == -2 && byArray[1] == -1) {
                return "utf-16be";
            }
            if (byArray[0] == -1 && byArray[1] == -1) {
                return "utf-16le";
            }
            return "GBK";
        } catch (Exception exception) {
            throw new RuntimeException("取文件编码( 未找到文件:" + string);
        }
    }

    public static String 读入文本文件(String string) {
        try {
            return FileUtils.readString(new File(string));
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return "";
        }
    }

    public static String 读入文本文件(String string, String string2) {
        try {
            return FileUtils.readString(new File(string), string2);
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return "";
        }
    }

    public static void 写出文本文件(String string, String string2) {
        try {
            FileUtils.write(new File(string), string2);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static byte[] 读入字节(String string) {
        byte[] byArray;
        if (!new File(string).exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(string);
            byArray = new byte[fileInputStream.available()];
            fileInputStream.read(byArray);
            fileInputStream.close();
            return byArray;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("读入字节文件( 错误");
        }
    }

    public static boolean 写出字节文件(String string, byte[] byArray) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(string);
            fileOutputStream.write(byArray);
            fileOutputStream.close();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("写出字节文件( 错误");
        }
    }

    public static long 取文件大小(String string) {
        File file = new File(string);
        try {
            if (file.isDirectory()) {
                return 文件操作.getFileSizes(file);
            }
            return 文件操作.getFileSize(file);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("取文件大小( 错误");
        }
    }

    public static boolean 写出资源文件(Context context, String string, String string2) {
        try {
            InputStream inputStream = context.getAssets().open(string);
            File file = new File(string2);
            if (!Objects.requireNonNull(file.getParentFile()).exists()) {
                file.getParentFile().mkdirs();
            }
            return 文件操作.writeStreamToFile(inputStream, file);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("写出资源文件( " + string + "或" + string2);
        }
    }

    public static String 读入资源文件(Context context, String string) {
        return 文件操作.读入资源文件(context, string, "utf-8");
    }

    public static String 读入资源文件(Context context, String string, String string2) {
        try {
            InputStream inputStream = context.getAssets().open(string);
            int n = inputStream.available();
            byte[] byArray = new byte[n];
            inputStream.read(byArray);
            String string3 = new String(byArray, 0, n, string2);
            inputStream.close();
            return string3;
        } catch (IOException iOException) {
            iOException.printStackTrace();
            throw new RuntimeException("读入资源文件( 未找到文件: " + string);
        }
    }

    public static String 寻找文件关键词(String string, String string2) {
        StringBuilder string3 = new StringBuilder();
        for (File file : Objects.requireNonNull(new File(string).listFiles())) {
            if (!file.getName().contains(string2)) continue;
            string3.insert(0, file.getPath() + "\n");
        }
        return string3.toString();
    }

    public static String 寻找文件后缀名(String string, String string2) {
        StringBuilder string3 = new StringBuilder();
        for (File file : Objects.requireNonNull(new File(string).listFiles())) {
            if (!file.getPath().endsWith(string2) || file.isDirectory())
                continue;
            string3.insert(0, file.getPath() + "\n");
        }
        return string3.toString();
    }

    public static boolean 打开文本文件_读(String string) {
        return 文件操作.打开文本文件_读(string, "utf-8");
    }

    public static boolean 打开文本文件_读(String string, String string2) {
        if (!new File(string).exists()) {
            return false;
        }
        try {
            fin = new FileInputStream(string);
            InputStreamReader isr = new InputStreamReader(fin, string2);
            br = new BufferedReader(isr);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean 关闭读() {
        try {
            br.close();
            fin.close();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static String 读一行() {
        try {
            String string;
            return br.readLine();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean 打开文本文件_写(String string) {
        return 文件操作.打开文本文件_写(string, "utf-8");
    }

    public static boolean 打开文本文件_写(String string, String string2) {
        if (!new File(string).exists()) {
            return false;
        }
        try {
            fout = new FileOutputStream(string);
            OutputStreamWriter osw = new OutputStreamWriter(fout, string2);
            bw = new BufferedWriter(osw);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean 关闭写() {
        try {
            bw.close();
            fout.close();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean 写一行(String string) {
        try {
            bw.newLine();
            bw.write(string);
            bw.flush();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static String[] 取子目录(String string) {
        File[] fileArray = new File(string).listFiles();
        String[] stringArray = new String[0];
        if (fileArray != null) {
            stringArray = new String[fileArray.length];
        }
        if (fileArray != null) {
            for (int i = 0; i < fileArray.length; ++i) {
                if (!fileArray[i].isDirectory()) continue;
                stringArray[i] = fileArray[i].getAbsolutePath();
            }
        }
        return stringArray;
    }

    public static String[] 取子文件列表(String 路径) {
        List<String> list = new ArrayList<>();
        File[] ff = new File(路径).listFiles();
        if (ff != null) {
            for (File file : ff) {
                list.add(file.getAbsolutePath());
            }
        }
        return list.toArray(new String[0]);

    }

    public static String[] 取子文件列表(String 路径, int 排序方式, final boolean 是否正序) {
        List<String> list = new ArrayList<>();
        File[] files = new File(路径).listFiles();
        if (排序方式 == 常量类.文件排序_名称排序) {
            if (files != null) {
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        if (o1.isDirectory() && o2.isFile()) {
                            if (是否正序)
                                return -1;
                            else
                                return 1;
                        }
                        if (o1.isFile() && o2.isDirectory()) {
                            if (是否正序)
                                return 1;
                            else
                                return -1;
                        }
                        if (o1.getName().compareTo(o2.getName()) > 0) {
                            if (是否正序) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else {
                            if (是否正序) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    }
                });
            }
        } else if (排序方式 == 常量类.文件排序_时间排序) {
            if (files != null) {
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File f1, File f2) {
                        long diff = f1.lastModified() - f2.lastModified();
                        if (diff > 0) {
                            if (是否正序) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else if (diff == 0) {
                            return 0;
                        } else {
                            if (是否正序) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    }

                    public boolean equals(Object obj) {
                        return true;
                    }
                });
            }
        } else if (排序方式 == 常量类.文件排序_大小排序) {
            if (files != null) {
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File f1, File f2) {
                        long diff = f1.length() - f2.length();
                        if (diff > 0) {
                            if (是否正序) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else if (diff == 0) {
                            return 0;
                        } else {
                            if (是否正序) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    }

                    public boolean equals(Object obj) {
                        return true;
                    }
                });
            }
        }
        if (files != null) {
            for (File file : files) {
                list.add(file.getAbsolutePath());
            }
        }
        return list.toArray(new String[0]);
    }

    public static 集合<String> 取子文件集合(String 路径) {
        集合<String> 集合2 = new 集合<>();
        File[] fileArray = new File(路径).listFiles();
        if (fileArray != null) {
            for (File file : fileArray) {
                集合2.add(file.getAbsolutePath());
            }
        }
        return 集合2;
    }

    public static 集合<String> 取子文件集合(String 路径, int 排序方式, final boolean 是否正序) {
        集合<String> list = new 集合<>();
        File[] files = new File(路径).listFiles();
        if (排序方式 == 常量类.文件排序_名称排序) {
            if (files != null) {
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        if (o1.isDirectory() && o2.isFile()) {
                            if (是否正序)
                                return -1;
                            else
                                return 1;
                        }
                        if (o1.isFile() && o2.isDirectory()) {
                            if (是否正序)
                                return 1;
                            else
                                return -1;
                        }
                        if (o1.getName().compareTo(o2.getName()) > 0) {
                            if (是否正序) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else {
                            if (是否正序) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    }
                });
            }
        } else if (排序方式 == 常量类.文件排序_时间排序) {
            if (files != null) {
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File f1, File f2) {
                        long diff = f1.lastModified() - f2.lastModified();
                        if (diff > 0) {
                            if (是否正序) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else if (diff == 0) {
                            return 0;
                        } else {
                            if (是否正序) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    }

                    public boolean equals(Object obj) {
                        return true;
                    }
                });
            }
        } else if (排序方式 == 常量类.文件排序_大小排序) {
            if (files != null) {
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File f1, File f2) {
                        long diff = f1.length() - f2.length();
                        if (diff > 0) {
                            if (是否正序) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else if (diff == 0) {
                            return 0;
                        } else {
                            if (是否正序) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                    }

                    public boolean equals(Object obj) {
                        return true;
                    }
                });
            }
        }
        if (files != null) {
            for (File file : files) {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    public static String 取文件修改时间(String string) {
        File file = new File(string);
        Date lastModifiedDate = new Date(file.lastModified());

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
        return df.format(lastModifiedDate);
    }

    public static void 打开文件(Activity activity, String string) {
        activity.startActivity(文件操作.openFile(string));
    }

    private static Intent openFile(String string) {
        File file = new File(string);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        String string2 = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
        if (string2.equals("m4a") || string2.equals("mp3") || string2.equals("mid") || string2.equals("xmf") || string2.equals("ogg") || string2.equals("wav")) {
            return 文件操作.getAudioFileIntent(string);
        }
        if (string2.equals("3gp") || string2.equals("mp4")) {
            return 文件操作.getVideoFileIntent(string);
        }
        if (string2.equals("jpg") || string2.equals("gif") || string2.equals("png") || string2.equals("jpeg") || string2.equals("bmp")) {
            return 文件操作.getImageFileIntent(string);
        }
        if (string2.equals("apk")) {
            return 文件操作.getApkFileIntent(string);
        }
        if (string2.equals("ppt")) {
            return 文件操作.getPptFileIntent(string);
        }
        if (string2.equals("xls")) {
            return 文件操作.getExcelFileIntent(string);
        }
        if (string2.equals("doc")) {
            return 文件操作.getWordFileIntent(string);
        }
        if (string2.equals("pdf")) {
            return 文件操作.getPdfFileIntent(string);
        }
        if (string2.equals("chm")) {
            return 文件操作.getChmFileIntent(string);
        }
        if (string2.equals("txt")) {
            return 文件操作.getTextFileIntent(string);
        }
        return 文件操作.getAllIntent(string);
    }

    private static Intent getAllIntent(String string) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(string)), "*/*");
        return intent;
    }

    private static Intent getApkFileIntent(String string) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
        return intent;
    }

    private static Intent getVideoFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(Uri.fromFile(new File(string)), "video/*");
        return intent;
    }

    private static Intent getAudioFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(Uri.fromFile(new File(string)), "audio/*");
        return intent;
    }

    private static Intent getImageFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(string)), "image/*");
        return intent;
    }

    private static Intent getPptFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.ms-powerpoint");
        return intent;
    }

    private static Intent getExcelFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.ms-excel");
        return intent;
    }

    private static Intent getWordFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(string)), "application/msword");
        return intent;
    }

    private static Intent getChmFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(string)), "application/x-chm");
        return intent;
    }

    private static Intent getTextFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(string)), "text/plain");
        return intent;
    }

    private static Intent getPdfFileIntent(String string) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(string)), "application/pdf");
        return intent;
    }

    private static boolean writeStreamToFile(InputStream inputStream, File file) throws IOException {
        int n;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] byArray = new byte[1024];
        while ((n = inputStream.read(byArray)) != -1) {
            fileOutputStream.write(byArray, 0, n);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();
        return true;
    }

    private static long getFileSize(File file) throws Exception {
        boolean fileCreated = false;

        if (!file.exists()) {
            fileCreated = file.createNewFile();
        }

        if (fileCreated || file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                return fis.available();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        // 处理关闭异常
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return 0L;
        }
    }
    private static long getFileSizes(File file) throws Exception {
        long l = 0L;
        File[] fileArray = file.listFiles();
        if (fileArray != null) {
            for (File value : fileArray) {
                if (value.isDirectory()) {
                    l += 文件操作.getFileSizes(value);
                    continue;
                }
                l += 文件操作.getFileSize(value);
            }
        }
        return l;
    }

    public void 初始化事件() {
    }
}

