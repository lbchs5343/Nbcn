/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.ByteArrayOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Override
 *  java.lang.String
 *  java.net.Socket
 */
package lbchs;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class 套接字
        extends 组件 {
    private Socket socket;

    public 套接字(Context context) {
    }

    public 套接字(String string, int n) {
        try {
            this.socket = new Socket(string, n);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void 关闭连接() {
        try {
            this.socket.close();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public boolean 是否连接成功() {
        return this.socket.isConnected();
    }

    public boolean 是否关闭() {
        return this.socket.isClosed();
    }

    public boolean 发送数据(byte[] byArray) {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            outputStream.write(byArray);
            outputStream.flush();
            outputStream.close();
            this.socket.close();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public byte[] 取数据() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] byArray = new byte[1024];
            int n;
            while ((n = inputStream.read(byArray)) != -1) {
                byteArrayOutputStream.write(byArray, 0, n);
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public int 取端口() {
        return this.socket.getPort();
    }

    public int 取本地端口() {
        return this.socket.getLocalPort();
    }

    @Override
    public void 初始化事件() {
        super.初始化事件();
    }
}

