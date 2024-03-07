/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.math.BigDecimal
 *  java.util.Stack
 */
package jiesheng;

import java.math.BigDecimal;
import java.util.Stack;

public class 算术运算 {
    public static double E = Math.E;
    public static double PI = Math.PI;

    public static double 求反正切(double d) {
        return Math.atan((double) d);
    }

    public static double 求余弦(double d) {
        return Math.cos((double) d);
    }

    public static double 求反对数(double d) {
        return Math.exp((double) d);
    }

    public static double 求自然对数(double d) {
        return Math.log((double) d);
    }

    public static int 取随机数(int n, int n2) {
        return (int) (Math.random() * (double) (n2 + 1 - n) + (double) n);
    }

    public static double 求正弦(double d) {
        return Math.sin((double) d);
    }

    public static int 取符号(double d) {
        return (int) Math.signum((double) d);
    }

    public static double 求平方根(double d) {
        return Math.sqrt((double) d);
    }

    public static double 求正切(double d) {
        return Math.tan((double) d);
    }

    public static double 角度转弧度(double d) {
        return Math.toRadians((double) d);
    }

    public static double 弧度转角度(double d) {
        return Math.toDegrees((double) d);
    }

    public static double 四舍五入(double d, int n) {
        return new BigDecimal(String.valueOf((double) d)).setScale(n, 4).doubleValue();
    }

    public static double 求余数(double d, double d2) {
        return d % d2;
    }

    public static double 求反正弦(double d) {
        return Math.asin((double) d);
    }

    public static double 求反余弦(double d) {
        return Math.acos((double) d);
    }

    public static double 表达式计算(String string) {
        double[] dArray = new double[20];
        boolean bl = false;
        int n = 0;
        int n2 = 0;
        int n3 = -1;
        Stack stack = new Stack();
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (c == ' ') continue;
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '%') {
                if (bl) {
                    dArray[++n3] = n2 < n ? (double) Integer.valueOf((String) string.substring(n, n + 1)).intValue() : (double) Integer.valueOf((String) string.substring(n, n2 + 1)).intValue();
                    bl = false;
                }
                if (c == '-') {
                    if (i == 0) {
                        bl = true;
                        n = 0;
                        continue;
                    }
                    if (string.charAt(i - 1) == '(' || string.charAt(i - 1) == '*' || string.charAt(i - 1) == '/') {
                        bl = true;
                        n = i;
                        continue;
                    }
                    if (stack.empty()) {
                        stack.push((Object) Character.valueOf((char) c));
                        continue;
                    }
                    if (c == ')') {
                        dArray[n3 - 1] = 算术运算.compute(dArray[n3 - 1], dArray[n3], ((Character) stack.pop()).charValue());
                        --n3;
                        stack.pop();
                        continue;
                    }
                    if (c == '(') {
                        stack.push((Object) Character.valueOf((char) c));
                        continue;
                    }
                    if (算术运算.priority(c) <= 算术运算.priority(((Character) stack.peek()).charValue())) {
                        dArray[n3 - 1] = 算术运算.compute(dArray[n3 - 1], dArray[n3], ((Character) stack.pop()).charValue());
                        --n3;
                        stack.push((Object) Character.valueOf((char) c));
                        continue;
                    }
                    stack.push((Object) Character.valueOf((char) c));
                    continue;
                }
                if (stack.empty()) {
                    stack.push((Object) Character.valueOf((char) c));
                    continue;
                }
                if (c == ')') {
                    dArray[n3 - 1] = 算术运算.compute(dArray[n3 - 1], dArray[n3], ((Character) stack.pop()).charValue());
                    --n3;
                    stack.pop();
                    continue;
                }
                if (c == '(') {
                    stack.push((Object) Character.valueOf((char) c));
                    continue;
                }
                if (算术运算.priority(c) <= 算术运算.priority(((Character) stack.peek()).charValue())) {
                    dArray[n3 - 1] = 算术运算.compute(dArray[n3 - 1], dArray[n3], ((Character) stack.pop()).charValue());
                    --n3;
                    stack.push((Object) Character.valueOf((char) c));
                    continue;
                }
                stack.push((Object) Character.valueOf((char) c));
                continue;
            }
            if (!bl) {
                bl = true;
                n = i;
                continue;
            }
            n2 = i;
        }
        if (bl) {
            ++n3;
            dArray[n3] = n2 < n ? (double) Integer.valueOf((String) string.substring(n, n + 1)).intValue() : (double) Integer.valueOf((String) string.substring(n, n2 + 1)).intValue();
        }
        while (n3 > 0) {
            dArray[n3 - 1] = 算术运算.compute(dArray[n3 - 1], dArray[n3], ((Character) stack.pop()).charValue());
            --n3;
        }
        return dArray[0];
    }

    private static int priority(char c) {
        switch (c) {
            case '(':
            case ')': {
                return 0;
            }
            case '+':
            case '-': {
                return 1;
            }
            case '%':
            case '*':
            case '/': {
                return 2;
            }
        }
        return -1;
    }

    private static double compute(double d, double d2, char c) {
        switch (c) {
            case '(':
            case ')': {
                return 0.0;
            }
            case '-': {
                return d - d2;
            }
            case '+': {
                return d + d2;
            }
            case '%': {
                return d % d2;
            }
            case '*': {
                return d * d2;
            }
            case '/': {
                return d / d2;
            }
        }
        return 0.0;
    }

    public void 初始化事件() {
    }
}

