/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  java.io.File
 *  java.io.IOException
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 */
package lbchs.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.IOException;

public class 数据库操作 {
    private static SQLiteDatabase db;

    public static void 打开数据库(String string) {
        try {
            db = SQLiteDatabase.openOrCreateDatabase(string, null);
        } catch (Exception exception) {
            throw new RuntimeException("打开数据库( 未找到:" + string);
        }
    }

    public static void 关闭数据库() {
        try {
            db.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static boolean 创建数据库(String string) {
        boolean bl = false;
        File file = new File(string);
        if (file.exists()) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return bl;
        }
    }

    public static void 创建数据表(String string, String string2) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + string + " (" + string2 + ")");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void 删除数据表(String string) {
        try {
            db.execSQL("DROP TABLE " + string);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void 插入记录(String string, String string2) {
        try {
            db.execSQL("INSERT INTO " + string + " VALUES (" + string2 + ")");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void 删除记录(String string, String string2) {
        try {
            db.execSQL("DELETE FROM " + string + " WHERE " + string2);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void 修改记录(String string, String string2, String string3) {
        try {
            db.execSQL("UPDATE " + string + " SET " + string2 + " WHERE " + string3);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String 条件查询(String string, String string2, String string3, String string4) {
        StringBuilder string5 = new StringBuilder();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + string + " WHERE " + string2, null);
            string5 = new StringBuilder();
            while (cursor.moveToNext()) {
                int n = cursor.getColumnCount();
                for (int i = 0; i < n; ++i) {
                    string5.append(cursor.getString(i)).append(string3);
                }
                string5.append(string4);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception exception) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            exception.printStackTrace();
        }
        return string5.toString();
    }

    public static String 范围查询(String string, int n, int n2, String string2, String string3) {
        StringBuilder string4 = new StringBuilder();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + string + " LIMIT " + n + "," + n2, null);
            string4 = new StringBuilder();
            while (cursor.moveToNext()) {
                int n3 = cursor.getColumnCount();
                for (int i = 0; i < n3; ++i) {
                    string4.append(cursor.getString(i)).append(string2);
                }
                string4.append(string3);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception exception) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            exception.printStackTrace();
        }
        return string4.toString();
    }

    public static int 查询最大值(String string, String string2) {
        int n = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT max(" + string2 + ") FROM " + string, null);
            cursor.moveToFirst();
            n = cursor.getInt(0);
            if (!cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception exception) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            exception.printStackTrace();
        }
        return n;
    }

    public static String 数据库查询(String string, String string2, String string3) {
        StringBuilder string4 = new StringBuilder();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(string, null);
            string4 = new StringBuilder();
            while (cursor.moveToNext()) {
                int n = cursor.getColumnCount();
                for (int i = 0; i < n; ++i) {
                    string4.append(cursor.getString(i)).append(string2);
                }
                string4.append(string3);
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception exception) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            exception.printStackTrace();
        }
        return string4.toString();
    }

    public static void 数据库执行(String string) {
        try {
            db.execSQL(string);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static long 取记录数(String string) {
        long l = 0L;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select count(*)from " + string, null);
            cursor.moveToFirst();
            l = cursor.getLong(0);
            if (!cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception exception) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            exception.printStackTrace();
        }
        return l;
    }

    public static boolean 数据表是否存在(String string) {
        int n = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + string + "'", null);
            cursor.moveToFirst();
            n = cursor.getInt(0);
            if (!cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception exception) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            exception.printStackTrace();
        }
        return n > 0;
    }

    public static boolean 字段是否存在(String string, String string2) {
        boolean bl = false;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + string + " LIMIT 0", null);
            boolean bl2 = bl = cursor != null && cursor.getColumnIndex(string2) != -1;
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception exception) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            exception.printStackTrace();
        }
        return bl;
    }

    public static String[] 取所有数据表() {
        Cursor cursor = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table'", null);
        cursor.moveToFirst();
        int n = cursor.getInt(0);
        cursor.close();
        Cursor cursor2 = db.rawQuery("select name from sqlite_master where type='table' order by name", null);
        cursor2.moveToFirst();
        String[] stringArray = new String[n];
        int n2 = 0;
        while (cursor2.moveToNext()) {
            stringArray[n2] = cursor2.getString(0);
            ++n2;
        }
        cursor2.close();
        return stringArray;
    }

    public void 初始化事件() {
    }
}

