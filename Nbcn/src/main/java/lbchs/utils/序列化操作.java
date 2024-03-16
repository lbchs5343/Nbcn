/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.ObjectInputStream
 *  java.io.ObjectOutputStream
 *  java.io.OutputStream
 *  java.lang.ClassNotFoundException
 *  java.lang.Object
 *  java.lang.String
 */
package lbchs.utils;




import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;

public class 序列化操作 {
    private static <T> T readObject(String string) {
        try {
            ObjectInputStream objectInputStream = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(string)));
            }
            if (objectInputStream != null) {
                return (T) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException iOException) {
            iOException.printStackTrace();
        }
        return null;
    }

    public static <通用型对象> 通用型对象 读入对象(String string) {
        return 序列化操作.readObject(string);
    }

    public static void 写出对象(Object object, String string) {
        try {
            ObjectOutputStream objectOutputStream = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(string)));
            }
            if (objectOutputStream != null) {
                objectOutputStream.writeObject(object);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 初始化事件() {
    }
}

