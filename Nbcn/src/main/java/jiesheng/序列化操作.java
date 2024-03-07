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
package jiesheng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class 序列化操作 {
    private static <T> T readObject(String string) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((InputStream) new FileInputStream(string));
            return (T) objectInputStream.readObject();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return null;
    }

    public static <通用型对象> 通用型对象 读入对象(String string) {
        return (通用型对象) 序列化操作.readObject(string);
    }

    public static void 写出对象(Object object, String string) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream((OutputStream) new FileOutputStream(string));
            objectOutputStream.writeObject(object);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 初始化事件() {
    }
}

