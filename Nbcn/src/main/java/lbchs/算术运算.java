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
package lbchs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class 算术运算 {
    public static double E = Math.E;
    public static double PI = Math.PI;

    public static double 求反正切(double d) {
        return Math.atan(d);
    }

    public static double 求余弦(double d) {
        return Math.cos(d);
    }

    public static double 求反对数(double d) {
        return Math.exp(d);
    }

    public static double 求自然对数(double d) {
        return Math.log(d);
    }

    public static int 取随机数(int n, int n2) {
        return (int) (Math.random() * (double) (n2 + 1 - n) + (double) n);
    }

    public static double 求正弦(double d) {
        return Math.sin(d);
    }

    public static int 取符号(double d) {
        return (int) Math.signum(d);
    }

    public static double 求平方根(double d) {
        return Math.sqrt(d);
    }

    public static double 求正切(double d) {
        return Math.tan(d);
    }

    public static double 角度转弧度(double d) {
        return Math.toRadians(d);
    }

    public static double 弧度转角度(double d) {
        return Math.toDegrees(d);
    }

    public static double 四舍五入(double d, int n) {
        return new BigDecimal(String.valueOf(d)).setScale(n, RoundingMode.HALF_UP).doubleValue();
    }

    public static double 求余数(double d, double d2) {
        return d % d2;
    }

    public static double 求反正弦(double d) {
        return Math.asin(d);
    }

    public static double 求反余弦(double d) {
        return Math.acos(d);
    }

    public static double 表达式计算(String 表达式 ) {
        double[] num = new double[20];
        int flag = 0, begin = 0, end = 0, now;
        now = -1;
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < 表达式.length(); i++) {
            char s = 表达式.charAt(i);
            if (s == ' ') {

            } else if (s == '+' || s == '-' || s == '*' || s == '/' || s == '(' || s == ')' || s == '%') {
                if (flag == 1) {
                    now += 1;
                    if (end < begin) {
                        num[now] = Integer.parseInt(表达式.substring(begin, begin + 1));
                    } else {
                        num[now] = Integer.parseInt(表达式.substring(begin, end + 1));
                    }
                    flag = 0;
                }
                if (s == '-') {
                    if (i == 0) {
                        flag = 1;
                        begin = 0;
                    } else if (表达式.charAt(i - 1) == '(' || 表达式.charAt(i - 1) == '*'
                            || 表达式.charAt(i - 1) == '/') {
                        flag = 1;
                        begin = i;
                    } else {
                        if (st.empty()) {
                            st.push(s);
                        } else if (s == ')') {
                            num[now - 1] = compute(num[now - 1], num[now], st.pop());
                            now -= 1;
                            st.pop();
                        } else if (s == '(') {
                            st.push(s);
                        } else if (priority(s) <= priority(st.peek())) {
                            num[now - 1] = compute(num[now - 1], num[now], st.pop());
                            now -= 1;
                            st.push(s);
                        } else {
                            st.push(s);
                        }
                    }
                } else if (st.empty()) {
                    st.push(s);
                } else if (s == ')') {
                    num[now - 1] = compute(num[now - 1], num[now], st.pop());
                    now -= 1;
                    st.pop();
                } else if (s == '(') {
                    st.push(s);
                } else if (priority(s) <= priority(st.peek())) {
                    num[now - 1] = compute(num[now - 1], num[now], st.pop());
                    now -= 1;
                    st.push(s);
                } else {
                    st.push(s);
                }

            } else if (flag == 0) {
                flag = 1;
                begin = i;
            } else {
                end = i;
            }

        }
        if (flag == 1) {
            now += 1;
            if (end < begin) {
                num[now] = Integer.parseInt(表达式.substring(begin, begin + 1));
            } else {
                num[now] = Integer.parseInt(表达式.substring(begin, end + 1));
            }
        }
        while (now > 0) {
            num[now - 1] = compute(num[now - 1], num[now], st.pop());
            now -= 1;
        }
        return num[0];
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

