/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.app.Activity
 *  dalvik.system.DexClassLoader
 *  java.lang.Boolean
 *  java.lang.Byte
 *  java.lang.CharSequence
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.ClassNotFoundException
 *  java.lang.Double
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.IllegalAccessException
 *  java.lang.IllegalArgumentException
 *  java.lang.InstantiationException
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.NoSuchMethodException
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.SecurityException
 *  java.lang.Short
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.Void
 *  java.lang.reflect.Array
 *  java.lang.reflect.Constructor
 *  java.lang.reflect.Field
 *  java.lang.reflect.InvocationHandler
 *  java.lang.reflect.InvocationTargetException
 *  java.lang.reflect.Method
 *  java.lang.reflect.Proxy
 *  java.util.ArrayList
 *  java.util.List
 */
package jiesheng;

import android.app.Activity;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;

public class 反射操作 {
    private static final List<String> loadedPackages = new ArrayList();
    private static final Class<?>[] primitiveClasses = new Class[]{Integer.TYPE, Byte.TYPE, Character.TYPE, Long.TYPE, Short.TYPE, Double.TYPE, Float.TYPE, Void.TYPE, Boolean.TYPE};
    private static final Class<?>[] numberClasses = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Double.TYPE, Float.TYPE, Character.TYPE, Byte.TYPE};
    private static final Class<?>[] wrapperClasses = new Class[]{Integer.class, Long.class, Short.class, Double.class, Float.class, Character.class, Byte.class};
    private static final Class<?>[] integerNumberClasses = new Class[]{Integer.class, Character.class, Byte.class, Long.class, Short.class, Integer.TYPE, Short.TYPE, Long.TYPE, Character.TYPE, Byte.TYPE};

    static {
        loadedPackages.add("java.lang");
        loadedPackages.add("android.content");
    }

    public static void 导入包(String string) {
        if (string == null || loadedPackages.contains((Object) string)) {
            return;
        }
        loadedPackages.add(string);
    }

    public static void 移除导入(String string) {
        loadedPackages.remove((Object) string);
    }

    public static void 清除导入() {
        loadedPackages.clear();
    }

    public static Object 创建实例(Object object, Object... objectArray) {
        try {
            Class<?> clazz = 反射操作.getClass(object);
            Constructor constructor = null;
            int n = -1;
            for (Constructor constructor2 : clazz.getDeclaredConstructors()) {
                int n2 = 反射操作.getMatchCount(objectArray, constructor2.getParameterTypes());
                if (n2 <= n) continue;
                constructor = constructor2;
                n = n2;
            }
            if (constructor == null) {
                throw new NoSuchMethodException();
            }
            constructor.setAccessible(true);
            反射操作.CastArg(objectArray, constructor.getParameterTypes());
            return constructor.newInstance(objectArray);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean 设置变量(Object object, Object object2, String string, Object object3) {
        try {
            Class<?> clazz = 反射操作.getClass(object2);
            Field field = clazz.getDeclaredField(String.valueOf((Object) string));
            Object[] objectArray = new Object[]{object3};
            反射操作.CastArg(objectArray, new Class[]{field.getType()});
            field.setAccessible(true);
            field.set(object, objectArray[0]);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static Object 获取变量(Object object, Object object2, String string) {
        try {
            Class<?> clazz = 反射操作.getClass(object2);
            Field field = clazz.getDeclaredField(String.valueOf((Object) string));
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /*
     * Exception decompiling
     */
    public static Object[] 取类信息(Object var0, String var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         *
         * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$TooOptimisticMatchException
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.getString(Unknown Source:35)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.access$600(Unknown Source:0)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$SwitchStringMatchResultCollector.collectMatches(Unknown Source:68)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(Unknown Source:13)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.KleeneN.match(Unknown Source:9)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.MatchSequence.match(Unknown Source:14)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(Unknown Source:3)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewriteComplex(Unknown Source:649)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewrite(Unknown Source:37)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(Unknown Source:235)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(Unknown Source:7)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(Unknown Source:95)
         *     at android.s.ۥۢۦۥ.ۥ۟۟(Unknown Source:10)
         *     at org.benf.cfr.reader.entities.Method.ۥ۟۟۟(Unknown Source:5)
         *     at org.benf.cfr.reader.entities.ۥ.ۥ۟۟ۤ(Unknown Source:92)
         *     at org.benf.cfr.reader.entities.ۥ.ۥ۟۟ۨ(Unknown Source:1)
         *     at android.s.ۦۢۥۨ.ۥ۟۟۟(Unknown Source:191)
         *     at android.s.ۦۢۥۨ.ۥ۟۟(Unknown Source:145)
         *     at android.s.ۥۦۣۧ.ۥ(Unknown Source:108)
         *     at android.s.ۥۥۨۥ.ۥ(Unknown Source:84)
         *     at org.eclipse.tm4e.core.internal.grammar.⁣⁣⁣⁣⁣⁠⁤⁠⁤⁤⁣⁣⁣⁣⁤⁤⁣⁤⁠⁠⁤⁣⁠⁠⁤⁠⁣.⁣⁣⁣⁣⁣⁠⁤⁠⁤⁤⁣⁣⁣⁣⁤⁤⁣⁤⁠⁠⁠⁤⁠⁤⁣⁠⁤⁣⁠⁤⁣(Unknown Source:8)
         *     at android.s.ۥۥۨۤ.ۥ۟(Unknown Source:223)
         *     at org.dom4j.bean.⁤⁤⁠⁤⁤⁣⁤⁠⁠⁤.⁣⁤⁠⁠⁠⁣⁤⁤⁣⁤⁤⁤⁣⁤⁠⁠⁣⁣⁤⁠⁠⁠⁠⁠⁠⁣⁣⁠⁣⁤⁣⁠⁣⁤⁤(Unknown Source:10)
         *     at android.s.ۥۥۨۤ.ۥ(Unknown Source:21)
         *     at np.x2a.chunks.⁣⁤⁠⁠⁠⁣⁤⁤⁣⁤⁤⁤⁣⁤⁠⁠⁣⁤⁤⁠⁤⁤⁠⁠⁣.⁠⁣⁤⁣⁤⁠⁤⁤⁠⁤⁤⁣⁤⁠⁠⁤(Unknown Source:8)
         *     at android.s.o7$ۥ۟۟۠$ۥ.run(Unknown Source:63)
         *     at java.lang.Thread.run(Thread.java:919)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static Object 加载DEX(Activity activity, String string) {
        DexClassLoader dexClassLoader = new DexClassLoader(string, activity.getCodeCacheDir().getAbsolutePath(), null, activity.getClassLoader());
        return dexClassLoader;
    }

    public static Object 获取类(String string) {
        try {
            return 反射操作.getClass(string);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Object 获取类(Object object, String string) {
        try {
            return 反射操作.getClassByName((ClassLoader) object, string);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Object 反射执行(Object object, Object object2, String string, Object... objectArray) {
        try {
            Class<?> clazz = 反射操作.getClass(object2);
            if (string == null) {
                throw new IllegalArgumentException("method name null");
            }
            String string2 = String.valueOf((Object) string);
            Class[] classArray = new Class[objectArray.length / 2];
            Object[] objectArray2 = new Object[objectArray.length / 2];
            if (objectArray.length % 2 != 0) {
                throw new IllegalArgumentException("extra argument");
            }
            int n = 0;
            int n2 = 0;
            while (n < objectArray.length) {
                classArray[n2] = 反射操作.getClass(objectArray[n]);
                n += 2;
                ++n2;
            }
            n = 1;
            n2 = 0;
            while (n < objectArray.length) {
                objectArray2[n2] = objectArray[n];
                n += 2;
                ++n2;
            }
            Method method = clazz.getDeclaredMethod(string2, classArray);
            method.setAccessible(true);
            反射操作.CastArg(objectArray2, classArray);
            return method.invoke(object, objectArray2);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    protected static Class<?> getClass(Object object) throws ClassNotFoundException {
        Class clazz = null;
        if (object == null) {
            return null;
        }
        if (object instanceof CharSequence) {
            clazz = 反射操作.getClassByName(object.toString());
        } else if (object instanceof Class) {
            clazz = (Class) object;
        } else {
            throw new IllegalArgumentException("Not a valid class description argument");
        }
        return clazz;
    }

    protected static Class<?> getClassByName(String string) throws ClassNotFoundException {
        return 反射操作.getClassByName(null, string);
    }

    protected static Class<?> getClassByName(ClassLoader classLoader, String string) throws ClassNotFoundException {
        int n = 0;
        while (string.endsWith("[]")) {
            ++n;
            string = string.substring(0, string.length() - 2);
        }
        Class clazz = null;
        for (int i = 0; i < primitiveClasses.length; ++i) {
            if (!string.equals((Object) primitiveClasses[i].getSimpleName())) continue;
            clazz = primitiveClasses[i];
            break;
        }
        if (clazz == null) {
            try {
                clazz = classLoader != null ? classLoader.loadClass(string) : Class.forName((String) string);
            } catch (ClassNotFoundException classNotFoundException) {
                for (int i = 0; i < loadedPackages.size(); ++i) {
                    try {
                        clazz = classLoader != null ? classLoader.loadClass((String) loadedPackages.get(i) + "." + string) : Class.forName((String) ((String) loadedPackages.get(i) + "." + string));
                    } catch (ClassNotFoundException classNotFoundException2) {
                        // empty catch block
                    }
                    if (clazz != null) break;
                }
            }
        }
        if (clazz == null) {
            throw new ClassNotFoundException(string);
        }
        while (n > 0) {
            Object object = Array.newInstance((Class) clazz, (int) 0);
            clazz = object.getClass();
            --n;
        }
        return clazz;
    }

    protected static boolean isMemberOf(Object[] objectArray, Object object) {
        if (objectArray == null) {
            return false;
        }
        for (Object object2 : objectArray) {
            if (object2 != object) continue;
            return true;
        }
        return false;
    }

    protected static boolean isNumberClass(Class clazz) {
        return 反射操作.isMemberOf(numberClasses, clazz);
    }

    protected static boolean isWrapperClass(Class clazz) {
        return 反射操作.isMemberOf(wrapperClasses, clazz);
    }

    protected static Class<?> getWrapperClass(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        for (int i = 0; i < numberClasses.length; ++i) {
            if (numberClasses[i] != clazz && wrapperClasses[i] != clazz) continue;
            return wrapperClasses[i];
        }
        return null;
    }

    protected static String trimIfNeed(String string, Class clazz) {
        int n;
        if (反射操作.isMemberOf(integerNumberClasses, clazz) && (n = string.indexOf(46)) != -1) {
            string = string.substring(0, n);
        }
        return string;
    }

    protected static void CastArg(Object[] objectArray, Class<?>[] classArray) throws IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InstantiationException, InvocationTargetException, SecurityException {
        反射操作.CastArg(objectArray, classArray, true);
    }

    /*
     * Exception decompiling
     */
    protected static void CastArg(Object[] var0, Class<?>[] var1_1, boolean var2_2) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         *
         * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$TooOptimisticMatchException
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.getString(Unknown Source:35)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.access$600(Unknown Source:0)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$SwitchStringMatchResultCollector.collectMatches(Unknown Source:68)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(Unknown Source:13)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.KleeneN.match(Unknown Source:9)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.MatchSequence.match(Unknown Source:14)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(Unknown Source:3)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewriteComplex(Unknown Source:649)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewrite(Unknown Source:37)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(Unknown Source:235)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(Unknown Source:7)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(Unknown Source:95)
         *     at android.s.ۥۢۦۥ.ۥ۟۟(Unknown Source:10)
         *     at org.benf.cfr.reader.entities.Method.ۥ۟۟۟(Unknown Source:5)
         *     at org.benf.cfr.reader.entities.ۥ.ۥ۟۟ۤ(Unknown Source:92)
         *     at org.benf.cfr.reader.entities.ۥ.ۥ۟۟ۨ(Unknown Source:1)
         *     at android.s.ۦۢۥۨ.ۥ۟۟۟(Unknown Source:191)
         *     at android.s.ۦۢۥۨ.ۥ۟۟(Unknown Source:145)
         *     at android.s.ۥۦۣۧ.ۥ(Unknown Source:108)
         *     at android.s.ۥۥۨۥ.ۥ(Unknown Source:84)
         *     at org.eclipse.tm4e.core.internal.grammar.⁣⁣⁣⁣⁣⁠⁤⁠⁤⁤⁣⁣⁣⁣⁤⁤⁣⁤⁠⁠⁤⁣⁠⁠⁤⁠⁣.⁣⁣⁣⁣⁣⁠⁤⁠⁤⁤⁣⁣⁣⁣⁤⁤⁣⁤⁠⁠⁠⁤⁠⁤⁣⁠⁤⁣⁠⁤⁣(Unknown Source:8)
         *     at android.s.ۥۥۨۤ.ۥ۟(Unknown Source:223)
         *     at org.dom4j.bean.⁤⁤⁠⁤⁤⁣⁤⁠⁠⁤.⁣⁤⁠⁠⁠⁣⁤⁤⁣⁤⁤⁤⁣⁤⁠⁠⁣⁣⁤⁠⁠⁠⁠⁠⁠⁣⁣⁠⁣⁤⁣⁠⁣⁤⁤(Unknown Source:10)
         *     at android.s.ۥۥۨۤ.ۥ(Unknown Source:21)
         *     at np.x2a.chunks.⁣⁤⁠⁠⁠⁣⁤⁤⁣⁤⁤⁤⁣⁤⁠⁠⁣⁤⁤⁠⁤⁤⁠⁠⁣.⁠⁣⁤⁣⁤⁠⁤⁤⁠⁤⁤⁣⁤⁠⁠⁤(Unknown Source:8)
         *     at android.s.o7$ۥ۟۟۠$ۥ.run(Unknown Source:63)
         *     at java.lang.Thread.run(Thread.java:919)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    protected static Object getUnsafe() {
        try {
            return 反射操作.获取变量(null, "sun.misc.Unsafe", "theUnsafe");
        } catch (Exception exception) {
            return null;
        }
    }

    private static int getMatchCount(Object[] objectArray, Class[] classArray) {
        if (objectArray.length != classArray.length) {
            return -1;
        }
        try {
            反射操作.CastArg(objectArray, classArray, false);
        } catch (Exception exception) {
            return -1;
        }
        int n = 0;
        for (int i = 0; i < classArray.length; ++i) {
            if (objectArray[i] == null) {
                ++n;
                continue;
            }
            if (objectArray[i].getClass() != classArray[i]) continue;
            ++n;
        }
        return n;
    }

    public static void forceThrow(Throwable throwable) {
        ThrowableContainer throwableContainer = new ThrowableContainer();
        try {
            Field field = ThrowableContainer.class.getDeclaredField("exception");
            long l = (Long) 反射操作.javaxt(反射操作.getUnsafe(), "sun.misc.Unsafe", "objectFieldOffset", field);
            反射操作.javaxt(反射操作.getUnsafe(), "sun.misc.Unsafe", "putObject", new Object[]{throwableContainer, l, throwable});
        } catch (Exception exception) {
            // empty catch block
        }
        throwableContainer.throwNow();
    }

    public static Object javat(Object object, CharSequence charSequence, Object... objectArray) throws IllegalAccessException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, SecurityException {
        String string = charSequence.toString();
        int n = string.lastIndexOf(46);
        if (n == -1) {
            throw new IllegalArgumentException();
        }
        return 反射操作.javaxt(object, charSequence.subSequence(0, n), charSequence.subSequence(n + 1, charSequence.length()), objectArray);
    }

    public static Object javaxt(Object object, Object object2, Object object3, Object... objectArray) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InstantiationException, InvocationTargetException, SecurityException {
        Class<?> clazz = 反射操作.getClass(object2);
        if (object3 == null) {
            throw new IllegalArgumentException("null.method name");
        }
        String string = String.valueOf((Object) object3);
        Method method = null;
        int n = -1;
        for (Method method2 : clazz.getDeclaredMethods()) {
            int n2;
            if (!method2.getName().equals((Object) string) || (n2 = 反射操作.getMatchCount(objectArray, method2.getParameterTypes())) <= n)
                continue;
            n = n2;
            method = method2;
        }
        if (method == null) {
            throw new NoSuchMethodException(string);
        }
        method.setAccessible(true);
        反射操作.CastArg(objectArray, method.getParameterTypes());
        return method.invoke(object, objectArray);
    }

    public static Object javacb(Object object, Callback callback) throws ClassNotFoundException {
        return 反射操作.javacb(object, callback, false);
    }

    public static Object javacb(Object object, Callback callback, boolean bl) throws ClassNotFoundException {
        Class<?> clazz = 反射操作.getClass(object);
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException("Not a interface");
        }
        if (callback == null) {
            throw new NullPointerException();
        }
        return Proxy.newProxyInstance(clazz.getClassLoader(), (Class[]) new Class[]{clazz}, (InvocationHandler) (bl ? new JavaWrappedCallback(callback) : new JavaCallback(callback)));
    }

    public void 初始化事件() {
    }

    public static interface Callback {
        public Object onCallMethod(Object var1, Method var2, Object[] var3);
    }

    private static class JavaCallback
            implements InvocationHandler {
        private Callback cb;

        private JavaCallback(Callback callback) {
            if (callback == null) {
                throw new NullPointerException();
            }
            this.cb = callback;
        }

        public Object invoke(Object object, Method method, Object[] objectArray) throws Throwable {
            return this.cb.onCallMethod(object, method, objectArray);
        }
    }

    public static class JavaWrappedCallback
            implements InvocationHandler {
        private Callback cb;

        private JavaWrappedCallback(Callback callback) {
            if (callback == null) {
                throw new NullPointerException();
            }
            this.cb = callback;
        }

        /*
         * Exception decompiling
         */
        public Object invoke(Object var1_1, Method var2_2, Object[] var3_3) throws Throwable {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             *
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$TooOptimisticMatchException
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.getString(Unknown Source:35)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.access$600(Unknown Source:0)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$SwitchStringMatchResultCollector.collectMatches(Unknown Source:68)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(Unknown Source:13)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.KleeneN.match(Unknown Source:9)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.MatchSequence.match(Unknown Source:14)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(Unknown Source:3)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewriteComplex(Unknown Source:649)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewrite(Unknown Source:37)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(Unknown Source:235)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(Unknown Source:7)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(Unknown Source:95)
             *     at android.s.ۥۢۦۥ.ۥ۟۟(Unknown Source:10)
             *     at org.benf.cfr.reader.entities.Method.ۥ۟۟۟(Unknown Source:5)
             *     at org.benf.cfr.reader.entities.ۥ.ۥ۟۟ۤ(Unknown Source:92)
             *     at org.benf.cfr.reader.entities.ۥ.ۥۣ۟۟(Unknown Source:32)
             *     at org.benf.cfr.reader.entities.ۥ.ۥ۟۟ۤ(Unknown Source:27)
             *     at org.benf.cfr.reader.entities.ۥ.ۥ۟۟ۨ(Unknown Source:1)
             *     at android.s.ۦۢۥۨ.ۥ۟۟۟(Unknown Source:191)
             *     at android.s.ۦۢۥۨ.ۥ۟۟(Unknown Source:145)
             *     at android.s.ۥۦۣۧ.ۥ(Unknown Source:108)
             *     at android.s.ۥۥۨۥ.ۥ(Unknown Source:84)
             *     at org.eclipse.tm4e.core.internal.grammar.⁣⁣⁣⁣⁣⁠⁤⁠⁤⁤⁣⁣⁣⁣⁤⁤⁣⁤⁠⁠⁤⁣⁠⁠⁤⁠⁣.⁣⁣⁣⁣⁣⁠⁤⁠⁤⁤⁣⁣⁣⁣⁤⁤⁣⁤⁠⁠⁠⁤⁠⁤⁣⁠⁤⁣⁠⁤⁣(Unknown Source:8)
             *     at android.s.ۥۥۨۤ.ۥ۟(Unknown Source:223)
             *     at org.dom4j.bean.⁤⁤⁠⁤⁤⁣⁤⁠⁠⁤.⁣⁤⁠⁠⁠⁣⁤⁤⁣⁤⁤⁤⁣⁤⁠⁠⁣⁣⁤⁠⁠⁠⁠⁠⁠⁣⁣⁠⁣⁤⁣⁠⁣⁤⁤(Unknown Source:10)
             *     at android.s.ۥۥۨۤ.ۥ(Unknown Source:21)
             *     at np.x2a.chunks.⁣⁤⁠⁠⁠⁣⁤⁤⁣⁤⁤⁤⁣⁤⁠⁠⁣⁤⁤⁠⁤⁤⁠⁠⁣.⁠⁣⁤⁣⁤⁠⁤⁤⁠⁤⁤⁣⁤⁠⁠⁤(Unknown Source:8)
             *     at android.s.o7$ۥ۟۟۠$ۥ.run(Unknown Source:63)
             *     at java.lang.Thread.run(Thread.java:919)
             */
            throw new IllegalStateException("Decompilation failed");
        }
    }

    private static class ThrowableContainer {
        private RuntimeException exception;

        private ThrowableContainer() {
        }

        public void throwNow() {
            if (this.exception != null) {
                throw this.exception;
            }
        }
    }
}

