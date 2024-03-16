/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.io.UnsupportedEncodingException
 *  java.lang.IllegalArgumentException
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.net.URLDecoder
 *  java.net.URLEncoder
 */
package lbchs.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class 编码操作 {
    public static String URL编码(String string) {
        return URLEncoder.encode(string);
    }

    public static String URL解码(String string) {
        return URLDecoder.decode(string);
    }

    public static String URL编码(String string, String string2) {
        try {
            return URLEncoder.encode(string, string2);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            return "";
        }
    }

    public static String URL解码(String string, String string2) {
        try {
            return URLDecoder.decode(string, string2);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            return "";
        }
    }

    public static String 转换编码(String string, String string2, String string3) {
        if (string == null) {
            return "";
        }
        try {
            return new String(string.getBytes(string2), string3);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            return "";
        }
    }

    public static String UCS2编码(String string) {
        StringBuilder string2 = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            String string3 = Integer.toHexString(string.charAt(i) & 0xFFFF);
            if (string3.length() == 2) {
                string3 = "00" + string3;
            }
            string2.append("\\u").append(string3);
        }
        return string2.toString();
    }

    public static String UCS2解码(String string) {
        int n = string.length();
        StringBuilder stringBuffer = new StringBuilder(n);
        int n2 = 0;
        while (n2 < n) {
            int n3 = n2 + 1;
            int n4 = string.charAt(n2);
            if (n4 == 92) {
                n2 = n3 + 1;
                n4 = string.charAt(n3);
                if (n4 == 117) {
                    int n5 = 0;
                    for (int i = 0; i < 4; ++i) {
                        n3 = n2 + 1;
                        n4 = string.charAt(n2);
                        switch (n4) {
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57: {
                                n5 = (n5 << 4) + n4 - 48;
                                break;
                            }
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70: {
                                n5 = (n5 << 4) + 10 + n4 - 65;
                                break;
                            }
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case 101:
                            case 102: {
                                n5 = (n5 << 4) + 10 + n4 - 97;
                                break;
                            }
                            default: {
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                            }
                        }
                        n2 = n3;
                    }
                    stringBuffer.append((char) n5);
                    continue;
                }
                if (n4 == 116) {
                    n4 = 9;
                } else if (n4 == 114) {
                    n4 = 13;
                } else if (n4 == 110) {
                    n4 = 10;
                } else if (n4 == 102) {
                    n4 = 12;
                }
                stringBuffer.append((char) n4);
                continue;
            }
            stringBuffer.append((char) n4);
            n2 = n3;
        }
        return stringBuffer.toString();
    }

    public void 初始化事件() {
    }
}

