/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.DataInputStream
 *  java.io.DataOutputStream
 *  java.io.IOException
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Process
 *  java.lang.Runtime
 *  java.lang.String
 */
package jiesheng;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Root操作 {
    private static final String TAG = "linin.root";
    private static boolean mHaveRoot = false;

    public static boolean 是否Root() {
        if (!mHaveRoot) {
            int n = Root操作.执行命令2("echo test");
            if (n != -1) {
                Log.i((String) TAG, (String) "have root!");
                mHaveRoot = true;
            } else {
                Log.i((String) TAG, (String) "not root!");
            }
        } else {
            Log.i((String) TAG, (String) "mHaveRoot = true, have root!");
        }
        return mHaveRoot;
    }

    public static boolean 获取Root() {
        try {
            Runtime.getRuntime().exec(new String[]{"/system/bin/su", "-c", "chmod 777 /dev/graphics/fb0"});
        } catch (IOException iOException) {
            iOException.printStackTrace();
            Log.i((String) TAG, (String) "root fail!");
            return false;
        }
        Log.i((String) TAG, (String) "root success!");
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String 执行shell(String string) {
        String string2 = "";
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            Process process = Runtime.getRuntime().exec("sh");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataInputStream = new DataInputStream(process.getInputStream());
            Log.i((String) TAG, (String) string);
            dataOutputStream.writeBytes(string + "\n");
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            String string3 = null;
            while ((string3 = dataInputStream.readLine()) != null) {
                string2 = string2 + string3 + "\n";
            }
            process.waitFor();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return string2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int 执行shell2(String string) {
        int n = -1;
        DataOutputStream dataOutputStream = null;
        try {
            Process process = Runtime.getRuntime().exec("sh");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            Log.i((String) TAG, (String) string);
            dataOutputStream.writeBytes(string + "\n");
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            n = process.exitValue();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String 执行命令(String string) {
        String string2 = "";
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataInputStream = new DataInputStream(process.getInputStream());
            Log.i((String) TAG, (String) string);
            dataOutputStream.writeBytes(string + "\n");
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            String string3 = null;
            while ((string3 = dataInputStream.readLine()) != null) {
                string2 = string2 + string3 + "\n";
            }
            process.waitFor();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return string2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int 执行命令2(String string) {
        int n = -1;
        DataOutputStream dataOutputStream = null;
        try {
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            Log.i((String) TAG, (String) string);
            dataOutputStream.writeBytes(string + "\n");
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            n = process.exitValue();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return n;
    }

    public void 初始化事件() {
    }
}

