/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.InputStream
 *  java.io.ObjectInputStream
 *  java.io.ObjectOutputStream
 *  java.io.OutputStream
 *  java.io.UnsupportedEncodingException
 *  java.lang.Byte
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.StringBuilder
 *  java.lang.Throwable
 *  java.math.BigInteger
 *  java.security.Key
 *  java.security.KeyPair
 *  java.security.KeyPairGenerator
 *  java.security.MessageDigest
 *  java.security.NoSuchAlgorithmException
 *  java.security.PrivateKey
 *  java.security.PublicKey
 *  java.security.SecureRandom
 *  java.security.spec.AlgorithmParameterSpec
 *  java.util.Base64
 *  java.util.Random
 *  javax.crypto.Cipher
 *  javax.crypto.spec.IvParameterSpec
 *  javax.crypto.spec.SecretKeySpec
 */
package jiesheng;

import android.app.Activity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class 加解密操作 {
    private static char[] base64EncodeChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] base64DecodeChars = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String MD5加密(String string) {
        char[] cArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] byArray = string.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance((String) "MD5");
            messageDigest.update(byArray);
            byte[] byArray2 = messageDigest.digest();
            int n = byArray2.length;
            char[] cArray2 = new char[n * 2];
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                byte by = byArray2[i];
                cArray2[n2++] = cArray[by >>> 4 & 0xF];
                cArray2[n2++] = cArray[by & 0xF];
            }
            return new String(cArray2);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static String SHA加密(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance((String) "SHA");
            messageDigest.update(string.getBytes());
            return new String(messageDigest.digest());
        } catch (Exception exception) {
            return null;
        }
    }

    public static String Base64编码(String string) {
        byte[] byArray = Base64.getEncoder().encode(string.getBytes());
        return new String(byArray);
    }

    public static String Base64解码(String string) {
        byte[] byArray = Base64.getDecoder().decode(string);
        return new String(byArray);
    }

    public static String Base64编码(String string, String string2) {
        return Base64Utils.encode(string, string2);
    }

    public static String Base64解码(String string, String string2) {
        return Base64Utils.decode(string, string2);
    }

    public static String Base64编码(String string, String string2, String string3) {
        byte[] byArray;
        String string4 = "=";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            byArray = string.getBytes(string2);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException((Throwable) unsupportedEncodingException);
        }
        String string5 = 加解密操作.binary(byArray, 2);
        int n = 0;
        while (string5.length() % 24 != 0) {
            string5 = string5 + "0";
            ++n;
        }
        for (int i = 0; i <= string5.length() - 6; i += 6) {
            int n2 = Integer.parseInt((String) string5.substring(i, i + 6), (int) 2);
            if (n2 == 0 && i >= string5.length() - n) {
                stringBuilder.append(string4);
                continue;
            }
            stringBuilder.append(string3.charAt(n2));
        }
        return stringBuilder.toString();
    }

    public static String Base64解码(String string, String string2, String string3) {
        int n;
        String string4 = "";
        for (int i = 0; i < string.length(); ++i) {
            n = string.charAt(i);
            if (n == 61) continue;
            String string5 = Integer.toBinaryString((int) string3.indexOf(n));
            while (string5.length() != 6) {
                string5 = "0" + string5;
            }
            string4 = string4 + string5;
        }
        string4 = string4.substring(0, string4.length() - string4.length() % 8);
        byte[] byArray = new byte[string4.length() / 8];
        for (n = 0; n < string4.length() / 8; ++n) {
            byArray[n] = (byte) Integer.parseInt(string4.substring(n * 8, n * 8 + 8), 2);
        }
        try {
            return new String(byArray, string2);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new RuntimeException((Throwable) unsupportedEncodingException);
        }
    }

    public static String BASE64编码(byte[] byArray) {
        return 加解密操作.encode(byArray);
    }

    public static byte[] BASE64解码(String string) {
        try {
            return 加解密操作.decode(string);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            return new byte[0];
        }
    }

    public static String RSA加密(Activity activity, String string) {
        String string2;
        try {
            RSASecurity rSASecurity = new RSASecurity(activity);
            byte[] byArray = rSASecurity.encrypt(string);
            string2 = new String(byArray);
        } catch (Exception exception) {
            exception.printStackTrace();
            string2 = "";
        }
        return string2;
    }

    public static String RSA解密(Activity activity, String string) {
        String string2;
        try {
            RSASecurity rSASecurity = new RSASecurity(activity);
            byte[] byArray = rSASecurity.decrypt(string.getBytes());
            string2 = new String(byArray);
        } catch (Exception exception) {
            exception.printStackTrace();
            string2 = "";
        }
        return string2;
    }

    public static String DES加密(String string, String string2) {
        try {
            byte[] byArray = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
            IvParameterSpec ivParameterSpec = new IvParameterSpec(byArray);
            SecretKeySpec secretKeySpec = new SecretKeySpec(string2.getBytes("GBK"), "DES");
            Cipher cipher = Cipher.getInstance((String) "DES/CBC/PKCS5Padding");
            cipher.init(1, (Key) secretKeySpec, (AlgorithmParameterSpec) ivParameterSpec);
            byte[] byArray2 = cipher.doFinal(string.getBytes("GBK"));
            return 加解密操作.BASE64编码(byArray2);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static String DES解密(String string, String string2) {
        try {
            byte[] byArray = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
            byte[] byArray2 = 加解密操作.BASE64解码(string);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(byArray);
            SecretKeySpec secretKeySpec = new SecretKeySpec(string2.getBytes("GBK"), "DES");
            Cipher cipher = Cipher.getInstance((String) "DES/CBC/PKCS5Padding");
            cipher.init(2, (Key) secretKeySpec, (AlgorithmParameterSpec) ivParameterSpec);
            byte[] byArray3 = cipher.doFinal(byArray2);
            return new String(byArray3, "GBK");
        } catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }

    public static String RC4加密(String string, String string2) {
        if (string == null || string2 == null) {
            return null;
        }
        try {
            byte[] byArray = 加解密操作.RC4Base(string.getBytes("GBK"), string2);
            char[] cArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            int n = byArray.length;
            char[] cArray2 = new char[n * 2];
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                byte by = byArray[i];
                cArray2[n2++] = cArray[by >>> 4 & 0xF];
                cArray2[n2++] = cArray[by & 0xF];
            }
            return new String(cArray2);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static String RC4解密(String string, String string2) {
        if (string == null || string2 == null) {
            return null;
        }
        try {
            return new String(加解密操作.RC4Base(加解密操作.HexString2Bytes(string), string2), "GBK");
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static String Authcode加密(String string, String string2) {
        return 加解密操作.Encode(string, string2);
    }

    public static String Authcode解密(String string, String string2) {
        return 加解密操作.Decode(string, string2);
    }

    private static String binary(byte[] byArray, int n) {
        String string = new BigInteger(1, byArray).toString(n);
        while (string.length() % 8 != 0) {
            string = "0" + string;
        }
        return string;
    }

    private static String Encode(String string, String string2) {
        try {
            if (string == null || string2 == null) {
                return "";
            }
            int n = 4;
            string2 = 加解密操作.MD52(string2);
            String string3 = 加解密操作.MD52(加解密操作.CutString(string2, 0, 16));
            String string4 = 加解密操作.MD52(加解密操作.CutString(string2, 16, 16));
            String string5 = n > 0 ? 加解密操作.RandomString(n) : "";
            String string6 = string3 + 加解密操作.MD52(string3 + string5);
            string = "0000000000" + 加解密操作.CutString(加解密操作.MD52(string + string4), 0, 16) + string;
            byte[] byArray = 加解密操作.RC4(string.getBytes("UTF-8"), string6);
            return string5 + 加解密操作.encode(byArray);
        } catch (Exception exception) {
            return "";
        }
    }

    private static String Decode(String string, String string2) {
        try {
            if (string == null || string2 == null) {
                return "";
            }
            int n = 4;
            string2 = 加解密操作.MD52(string2);
            String string3 = 加解密操作.MD52(加解密操作.CutString(string2, 0, 16));
            String string4 = 加解密操作.MD52(加解密操作.CutString(string2, 16, 16));
            String string5 = n > 0 ? 加解密操作.CutString(string, 0, n) : "";
            String string6 = string3 + 加解密操作.MD52(string3 + string5);
            byte[] byArray = 加解密操作.decode(加解密操作.CutString(string, n));
            String string7 = new String(加解密操作.RC4(byArray, string6));
            if (加解密操作.CutString(string7, 10, 16).equals((Object) 加解密操作.CutString(加解密操作.MD52(加解密操作.CutString(string7, 26) + string4), 0, 16))) {
                return 加解密操作.CutString(string7, 26);
            }
            byArray = 加解密操作.decode(加解密操作.CutString(string + "=", n));
            string7 = new String(加解密操作.RC4(byArray, string6));
            if (加解密操作.CutString(string7, 10, 16).equals((Object) 加解密操作.CutString(加解密操作.MD52(加解密操作.CutString(string7, 26) + string4), 0, 16))) {
                return 加解密操作.CutString(string7, 26);
            }
            byArray = 加解密操作.decode(加解密操作.CutString(string + "==", n));
            string7 = new String(加解密操作.RC4(byArray, string6));
            if (加解密操作.CutString(string7, 10, 16).equals((Object) 加解密操作.CutString(加解密操作.MD52(加解密操作.CutString(string7, 26) + string4), 0, 16))) {
                return 加解密操作.CutString(string7, 26);
            }
            return "2";
        } catch (Exception exception) {
            return "";
        }
    }

    private static String encode(byte[] byArray) {
        StringBuffer stringBuffer = new StringBuffer();
        int n = byArray.length;
        int n2 = 0;
        while (n2 < n) {
            int n3 = byArray[n2++] & 0xFF;
            if (n2 == n) {
                stringBuffer.append(base64EncodeChars[n3 >>> 2]);
                stringBuffer.append(base64EncodeChars[(n3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int n4 = byArray[n2++] & 0xFF;
            if (n2 == n) {
                stringBuffer.append(base64EncodeChars[n3 >>> 2]);
                stringBuffer.append(base64EncodeChars[(n3 & 3) << 4 | (n4 & 0xF0) >>> 4]);
                stringBuffer.append(base64EncodeChars[(n4 & 0xF) << 2]);
                stringBuffer.append("=");
                break;
            }
            int n5 = byArray[n2++] & 0xFF;
            stringBuffer.append(base64EncodeChars[n3 >>> 2]);
            stringBuffer.append(base64EncodeChars[(n3 & 3) << 4 | (n4 & 0xF0) >>> 4]);
            stringBuffer.append(base64EncodeChars[(n4 & 0xF) << 2 | (n5 & 0xC0) >>> 6]);
            stringBuffer.append(base64EncodeChars[n5 & 0x3F]);
        }
        return stringBuffer.toString();
    }

    private static byte[] decode(String string) throws UnsupportedEncodingException {
        int n = string.length() % 4;
        if (n == 2) {
            string = string + "==";
        } else if (n == 3) {
            string = string + "=";
        }
        StringBuffer stringBuffer = new StringBuffer();
        byte[] byArray = string.getBytes("US-ASCII");
        int n2 = byArray.length;
        int n3 = 0;
        while (n3 < n2) {
            byte by;
            byte by2;
            byte by3;
            byte by4;
            do {
                by4 = base64DecodeChars[byArray[n3++]];
            } while (n3 < n2 && by4 == -1);
            if (by4 == -1) break;
            do {
                by3 = base64DecodeChars[byArray[n3++]];
            } while (n3 < n2 && by3 == -1);
            if (by3 == -1) break;
            stringBuffer.append((char) (by4 << 2 | (by3 & 0x30) >>> 4));
            do {
                if ((by2 = byArray[n3++]) == 61) {
                    return stringBuffer.toString().getBytes("iso8859-1");
                }
                by2 = base64DecodeChars[by2];
            } while (n3 < n2 && by2 == -1);
            if (by2 == -1) break;
            stringBuffer.append((char) ((by3 & 0xF) << 4 | (by2 & 0x3C) >>> 2));
            do {
                if ((by = byArray[n3++]) == 61) {
                    return stringBuffer.toString().getBytes("iso8859-1");
                }
                by = base64DecodeChars[by];
            } while (n3 < n2 && by == -1);
            if (by == -1) break;
            stringBuffer.append((char) ((by2 & 3) << 6 | by));
        }
        return stringBuffer.toString().getBytes("iso8859-1");
    }

    private static String CutString(String string, int n, int n2) {
        if (n >= 0) {
            if (n2 < 0) {
                if (n - (n2 *= -1) < 0) {
                    n2 = n;
                    n = 0;
                } else {
                    n -= n2;
                }
            }
            if (n > string.length()) {
                return "";
            }
        } else {
            if (n2 < 0) {
                return "";
            }
            if (n2 + n > 0) {
                n2 += n;
                n = 0;
            } else {
                return "";
            }
        }
        if (string.length() - n < n2) {
            n2 = string.length() - n;
        }
        return string.substring(n, n + n2);
    }

    private static byte[] HexString2Bytes(String string) {
        try {
            int n = string.length();
            byte[] byArray = new byte[n / 2];
            byte[] byArray2 = string.getBytes("GBK");
            for (int i = 0; i < n / 2; ++i) {
                byArray[i] = 加解密操作.uniteBytes(byArray2[i * 2], byArray2[i * 2 + 1]);
            }
            return byArray;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static byte uniteBytes(byte by, byte by2) {
        char c = (char) Byte.decode((String) ("0x" + new String(new byte[]{by}))).byteValue();
        c = (char) (c << 4);
        char c2 = (char) Byte.decode((String) ("0x" + new String(new byte[]{by2}))).byteValue();
        byte by3 = (byte) (c ^ c2);
        return by3;
    }

    private static byte[] RC4Base(byte[] byArray, String string) {
        int n = 0;
        int n2 = 0;
        byte[] byArray2 = 加解密操作.initKey(string);
        byte[] byArray3 = new byte[byArray.length];
        for (int i = 0; i < byArray.length; ++i) {
            n = n + 1 & 0xFF;
            n2 = (byArray2[n] & 0xFF) + n2 & 0xFF;
            byte by = byArray2[n];
            byArray2[n] = byArray2[n2];
            byArray2[n2] = by;
            int n3 = (byArray2[n] & 0xFF) + (byArray2[n2] & 0xFF) & 0xFF;
            byArray3[i] = (byte) (byArray[i] ^ byArray2[n3]);
        }
        return byArray3;
    }

    private static byte[] initKey(String string) {
        try {
            int n;
            byte[] byArray = string.getBytes("GBK");
            byte[] byArray2 = new byte[256];
            for (n = 0; n < 256; ++n) {
                byArray2[n] = (byte) n;
            }
            n = 0;
            int n2 = 0;
            if (byArray == null || byArray.length == 0) {
                return null;
            }
            for (int i = 0; i < 256; ++i) {
                n2 = (byArray[n] & 0xFF) + (byArray2[i] & 0xFF) + n2 & 0xFF;
                byte by = byArray2[i];
                byArray2[i] = byArray2[n2];
                byArray2[n2] = by;
                n = (n + 1) % byArray.length;
            }
            return byArray2;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static String CutString(String string, int n) {
        return 加解密操作.CutString(string, n, string.length());
    }

    private static byte[] GetKey(byte[] byArray, int n) {
        int n2;
        byte[] byArray2 = new byte[n];
        for (n2 = 0; n2 < n; ++n2) {
            byArray2[n2] = (byte) n2;
        }
        n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 = (n2 + (byArray2[i] + 256) % 256 + byArray[i % byArray.length]) % n;
            byte by = byArray2[i];
            byArray2[i] = byArray2[n2];
            byArray2[n2] = by;
        }
        return byArray2;
    }

    private static String RandomString(int n) {
        char[] cArray = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int n2 = cArray.length;
        String string = "";
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            string = string + cArray[Math.abs((int) random.nextInt(n2))];
        }
        return string;
    }

    private static byte[] RC4(byte[] byArray, String string) {
        if (byArray == null || string == null) {
            return null;
        }
        byte[] byArray2 = new byte[byArray.length];
        byte[] byArray3 = 加解密操作.GetKey(string.getBytes(), 256);
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < byArray.length; ++i) {
            n = (n + 1) % byArray3.length;
            n2 = (n2 + (byArray3[n] + 256) % 256) % byArray3.length;
            byte by = byArray3[n];
            byArray3[n] = byArray3[n2];
            byArray3[n2] = by;
            byte by2 = byArray[i];
            byte by3 = byArray3[(加解密操作.toInt(byArray3[n]) + 加解密操作.toInt(byArray3[n2])) % byArray3.length];
            byArray2[i] = (byte) (by2 ^ 加解密操作.toInt(by3));
        }
        return byArray2;
    }

    private static String MD52(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        String string2 = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance((String) "MD5");
            byte[] byArray = messageDigest.digest(string.getBytes());
            for (int i = 0; i < byArray.length; ++i) {
                string2 = Integer.toHexString((int) (byArray[i] & 0xFF));
                if (string2.length() == 1) {
                    string2 = "0" + string2;
                }
                stringBuffer.append(string2);
            }
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private static int toInt(byte by) {
        return (by + 256) % 256;
    }

    public void 初始化事件() {
    }

    static class RSASecurity {
        private final String ALGORITHM = "RSA";
        private final int KEY_SIZE = 1024;
        private String PUBLIC_KEY_FILE;
        private String PRIVATE_KEY_FILE;

        public RSASecurity(Activity activity) {
            this.PUBLIC_KEY_FILE = activity.getDir("S5droid", 0).getAbsolutePath() + "PublicKey";
            this.PRIVATE_KEY_FILE = activity.getDir("S5droid", 0).getAbsolutePath() + "PrivateKey";
        }

        public void generateKeyPair() throws Exception {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance((String) "RSA");
            keyPairGenerator.initialize(1024, secureRandom);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            ObjectOutputStream objectOutputStream = null;
            ObjectOutputStream objectOutputStream2 = null;
            try {
                objectOutputStream = new ObjectOutputStream((OutputStream) new FileOutputStream(this.PUBLIC_KEY_FILE));
                objectOutputStream2 = new ObjectOutputStream((OutputStream) new FileOutputStream(this.PRIVATE_KEY_FILE));
                objectOutputStream.writeObject((Object) publicKey);
                objectOutputStream2.writeObject((Object) privateKey);
            } catch (Exception exception) {
                throw exception;
            } finally {
                objectOutputStream.close();
                objectOutputStream2.close();
            }
        }

        public byte[] encrypt(String string) throws Exception {

            this.generateKeyPair();
            Key publicKey;
            ObjectInputStream ois = null;
            try {

                /** 将文件中的公钥对象读出 */
                ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
                publicKey = (Key) ois.readObject();
            } catch (Exception e) {
                throw e;
            } finally {
                ois.close();
            }
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, publicKey);
            byte[] byArray = string.getBytes();
            byte[] byArray2 = cipher.doFinal(byArray);
            return byArray2;
        }

        public byte[] decrypt(byte[] byArray) throws Exception {
            Key privateKey;
            ObjectInputStream ois = null;
            try {
                /** 将文件中的私钥对象读出 */
                ois = new ObjectInputStream(new FileInputStream(
                        PRIVATE_KEY_FILE));
                privateKey = (Key) ois.readObject();
            } catch (Exception e) {
                throw e;
            } finally {
                ois.close();
            }

            /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            /** 执行解密操作*/
            byte[] b = cipher.doFinal(byArray);
            return b;
        }
    }

    static class Base64Utils {
        private static String base64Table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        private static String add = "=";

        Base64Utils() {
        }

        public static String encode(String string, String string2) {
            byte[] byArray;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                byArray = string.getBytes(string2);
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                throw new RuntimeException((Throwable) unsupportedEncodingException);
            }
            String string3 = Base64Utils.binary(byArray, 2);
            int n = 0;
            while (string3.length() % 24 != 0) {
                string3 = string3 + "0";
                ++n;
            }
            for (int i = 0; i <= string3.length() - 6; i += 6) {
                int n2 = Integer.parseInt(string3.substring(i, i + 6), 2);
                if (n2 == 0 && i >= string3.length() - n) {
                    stringBuilder.append(add);
                    continue;
                }
                stringBuilder.append(base64Table.charAt(n2));
            }
            return stringBuilder.toString();
        }

        public static String decode(String string, String string2) {
            int n;
            String string3 = "";
            for (int i = 0; i < string.length(); ++i) {
                n = string.charAt(i);
                if (n == 61) continue;
                String string4 = Integer.toBinaryString((int) base64Table.indexOf(n));
                while (string4.length() != 6) {
                    string4 = "0" + string4;
                }
                string3 = string3 + string4;
            }
            string3 = string3.substring(0, string3.length() - string3.length() % 8);
            byte[] byArray = new byte[string3.length() / 8];
            for (n = 0; n < string3.length() / 8; ++n) {
                byArray[n] = (byte) Integer.parseInt((String) string3.substring(n * 8, n * 8 + 8), (int) 2);
            }
            try {
                return new String(byArray, string2);
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                throw new RuntimeException((Throwable) unsupportedEncodingException);
            }
        }

        public static String binary(byte[] byArray, int n) {
            String string = new BigInteger(1, byArray).toString(n);
            while (string.length() % 8 != 0) {
                string = "0" + string;
            }
            return string;
        }
    }
}

