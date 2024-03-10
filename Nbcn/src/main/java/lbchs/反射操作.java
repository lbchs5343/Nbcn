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
package lbchs;

import android.content.Context;
import android.os.Build;

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
    private final static List < String > loadedPackages = new ArrayList<>();
    private final static Class < ? >[] primitiveClasses = new Class < ? >[]{int.class,byte.class,char.class,long.class,short.class,double.class,float.class,void.class,boolean.class};
    private final static Class < ? >[] numberClasses = new Class < ? >[]{int.class,long.class,short.class,double.class,float.class,char.class,byte.class};
    private final static Class < ? >[] wrapperClasses = new Class < ? >[]{Integer.class,Long.class,Short.class,Double.class,Float.class,Character.class,Byte.class};
    private final static Class < ? >[] integerNumberClasses = new Class < ? >[]{Integer.class,Character.class,Byte.class,Long.class,Short.class,int.class,short.class,long.class,char.class,byte.class};
    static{
        loadedPackages.add("java.lang");
        loadedPackages.add("android.content");
    }

    public static void 导入包(String 欲导入包名) {
        if (欲导入包名 == null || loadedPackages.contains(欲导入包名)) {
            return;
        }
        loadedPackages.add(欲导入包名);
    }

    //移除已经导入的包，参数为要移除的完整包名
    public static void  移除导入(String 欲移除包名) {
        loadedPackages.remove(欲移除包名);
    }

    //移除所有已经导入的包
    public static void  清除导入() {
        loadedPackages.clear();
    }

    //创建一个类的实例，参数一为类对象/完整类名，其他参数为创建这个类需要的参数
    public static Object  创建实例(Object 类对象, Object... 参数) {

        try {
            Class<?> targetClass = getClass(类对象);
            Constructor<?> cons = null;
            int mark = -1;
            for (Constructor<?> c : targetClass.getDeclaredConstructors()) {
                int newMark = getMatchCount(参数, c.getParameterTypes());
                if (newMark > mark) {
                    cons = c;
                    mark = newMark;
                }
            }
            if (cons == null) {
                throw new NoSuchMethodException();
            }
            cons.setAccessible(true);
            CastArg(参数, cons.getParameterTypes());
            return cons.newInstance(参数);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //设置一个变量的值，参数一为类的实例，如若是静态变量，该参数可为空；参数二为类对象/完整类名，参数三为要设置的变量名，参数四为要设置的值
    public static Boolean  设置变量(Object 实例对象,Object 类对象,String 变量名,Object 变量值) {

        try {
            Class<?> klass = getClass(类对象);
            Field field = klass.getDeclaredField(String.valueOf(变量名));
            Object[] arr = new Object[]{变量值};
            CastArg(arr, new Class[]{field.getType()});
            field.setAccessible(true);
            field.set(实例对象, arr[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取一个变量，参数一为类的实例，如若是静态变量，该参数可为空；参数二为类对象/完整类名，参数三为变量名
    public static Object  获取变量(Object 实例对象,Object 类对象,String 变量名) {

        try {
            Class<?> klass = getClass(类对象);
            Field field = klass.getDeclaredField(String.valueOf(变量名));
            field.setAccessible(true);
            return field.get(实例对象);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取类的信息，参数一为类对象(可由获取类()方法获得)/完整类名，参数二为要去获取的类的信息类型，有"构造器"、"变量"、"方法"
    public static Object[] 取类信息(Object 类对象,String 欲取信息名) {

        try {
            String n = String.valueOf(欲取信息名);
            Class<?> c = getClass(类对象);
            switch (n) {
                case "构造器":
                    return c.getDeclaredConstructors();
                case "变量":
                    return c.getDeclaredFields();
                case "方法":
                    return c.getDeclaredMethods();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //加载一个DEX文件，参数一为当前窗口环境，参数二为DEX文件路径，返回一个DEX加载器对象
    public static Object  加载DEX(Context 窗口环境, String DEX文件路径) {
        Object loader = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            loader = new DexClassLoader(DEX文件路径, 窗口环境.getCodeCacheDir().getAbsolutePath(), null, 窗口环境.getClassLoader());
        }
        return loader;
    }

    //获取一个类，参数为完整类名，返回一个类对象
    public static Object  获取类(String 类名) {

        try {
            return getClass(类名);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取一个DEX文件中的类，参数一为DEX加载器对象(可由加载DEX方法获得)，参数二为要获取的类的完整类名
    public static Object  获取类(Object DEX加载器对象,String 类名) {

        try {
            return getClassByName((ClassLoader) DEX加载器对象, 类名);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //反射执行一个方法，参数一为类的实例，参数二为类对象/完整类名，参数三为要执行的方法名，其他参数为方法的参数
    public static Object  反射执行(Object 实例对象,Object 类对象,String 方法名,Object...参数) {

        try {
            Class<?> targetClass = getClass(类对象);
            if (方法名 == null) {
                throw new IllegalArgumentException("method name null");
            }
            Class<?>[] params = new Class[参数.length / 2];
            Object[] passArgs = new Object[参数.length / 2];
            if (参数.length % 2 != 0) {
                throw new IllegalArgumentException("extra argument");
            }
            for (int i = 0, j = 0; i < 参数.length; i += 2, j++) {
                params[j] = getClass(参数[i]);
            }
            for (int i = 1, j = 0; i < 参数.length; i += 2, j++) {
                passArgs[j] = 参数[i];
            }
            Method good = targetClass.getDeclaredMethod(方法名, params);
            good.setAccessible(true);
            CastArg(passArgs, params);
            return good.invoke(实例对象, passArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    protected static Class < ? > getClass(Object clazz) throws ClassNotFoundException {
        Class<?> targetClass;
        if (clazz == null) {
            return null;
        }
        if (clazz instanceof CharSequence) {
            targetClass = getClassByName(clazz.toString());
        }
        else if (clazz instanceof Class) {
            targetClass = (Class<?>)clazz;
        }
        else {
            throw new IllegalArgumentException("Not a valid class description argument");
        }
        return targetClass;
    }

    protected static Class < ? > getClassByName(String name) throws ClassNotFoundException {
        return getClassByName(null, name);
    }

    protected static Class < ? > getClassByName(ClassLoader cl, String name) throws ClassNotFoundException {
        int count = 0;
        while (name.endsWith("[]")) {
            count++;
            name = name.substring(0, name.length() - 2);
        }
        Class < ? > klass = null;
        for (Class<?> primitiveClass : primitiveClasses) {
            if (name.equals(primitiveClass.getSimpleName())) {
                klass = primitiveClass;
                break;
            }
        }
        if (klass == null) {
            try {
                klass = (cl != null) ? cl.loadClass(name) : Class.forName(name);
            }
            catch (ClassNotFoundException e) {
                for (int i=0;i < loadedPackages.size();i++) {
                    try {
                        klass = (cl != null) ? cl.loadClass(loadedPackages.get(i) + "."  + name) : Class.forName(loadedPackages.get(i) + "." + name);
                    }
                    catch (ClassNotFoundException e0) {
                        e0.printStackTrace();
                    }
                    if (klass != null) {
                        break;
                    }
                }
            }
        }
        if (klass == null) {
            throw new ClassNotFoundException(name);
        }
        while (count > 0) {
            Object obj = Array.newInstance(klass, 0);
            klass = obj.getClass();
            count--;
        }
        return klass;
    }

    protected static boolean isMemberOf(Object[] collection, Object element) {
        if (collection == null) {
            return false;
        }
        for (Object obj : collection) {
            if (obj == element) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isNumberClass(Class<?> c) {
        return isMemberOf(numberClasses, c);
    }

    protected static boolean isWrapperClass(Class<?> c) {
        return isMemberOf(wrapperClasses, c);
    }

    protected static Class < ? > getWrapperClass(Class < ? > c) {
        if (c == null) {
            return null;
        }
        for (int i=0;i < numberClasses.length;i++) {
            if (numberClasses[i] == c || wrapperClasses[i] == c) {
                return wrapperClasses[i];
            }
        }
        return null;
    }

    protected static String trimIfNeed(String num, Class<?> to) {
        if (isMemberOf(integerNumberClasses, to)) {
            int i = num.indexOf('.');
            if (i != - 1) {
                num = num.substring(0, i);
            }
        }
        return num;
    }

    protected static void CastArg(Object[] a, Class < ? >[] b) throws IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InstantiationException, InvocationTargetException, SecurityException {
        CastArg(a, b, true);
    }

    protected static void CastArg(Object[] objs, Class < ? >[] cls, boolean modify) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        for (int i=0;i < objs.length && i < cls.length;i++) {
            Object obj = objs[i];
            Class < ? > klass = cls[i];
            if (obj == null) {
                if (isNumberClass(klass)) {
                    throw new IllegalArgumentException();
                }
                else {
                    continue;
                }
            }
            else {
                Class < ? > objClass = obj.getClass();
                if ((getWrapperClass(objClass) != null || objClass == String.class)) {
                    if (isNumberClass(klass)) {
                        String str = trimIfNeed(String.valueOf(obj), klass);
                        Class < ? > wrapper = getWrapperClass(klass);
                        if (modify)
                            if (klass == char.class) {
                                objs[i] = (char) Double.valueOf(str).intValue();
                            }
                            else if (klass == int.class) {
                                objs[i] = Integer.parseInt(str);
                            }
                            else {
                                objs[i] = wrapper.getMethod("parse" + wrapper.getSimpleName(), String.class).invoke(null, str);
                            }
                        continue;
                    }
                    else if (klass == String.class) {
                        if (modify)
                            objs[i] = obj.toString();
                        continue;
                    }
                    else if (isWrapperClass(klass)) {
                        String str = trimIfNeed(String.valueOf(obj), klass);
                        if (modify)
                            if (klass == Character.class) {
                                objs[i] = (char) Double.valueOf((String) obj).intValue();
                            }
                            else {
                                objs[i] = getWrapperClass(klass).getConstructor(String.class).newInstance(str);
                            }
                        continue;
                    }
                    if (klass == Boolean.class) {
                        String str = obj.toString().toLowerCase();
                        switch (str) {
                            case "true":
                                if (modify)
                                    objs[i] = Boolean.TRUE;
                                continue;
                            case "false":
                                if (modify)
                                    objs[i] = Boolean.FALSE;
                                continue;
                            default:
                                throw new IllegalArgumentException();
                        }
                    }
                    if (klass == boolean.class) {
                        String str = obj.toString().toLowerCase();
                        switch (str) {
                            case "true":
                                if (modify)
                                    objs[i] = true;
                                continue;
                            case "false":
                                if (modify)
                                    objs[i] = false;
                                continue;
                            default:
                                throw new IllegalArgumentException();
                        }
                    }
                }
                if (klass.isInstance(obj)) {
                    continue;
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    protected static Object getUnsafe() {
        try {
            return 获取变量(null, "sun.misc.Unsafe", "theUnsafe");
        }
        catch (Exception e) {
            return null;
        }
    }

    private static int getMatchCount(Object[] args, Class<?>[] types) {
        if (args.length != types.length) {
            return - 1;
        }
        try {
            CastArg(args, types, false);
        }
        catch (Exception e) {
            return - 1;
        }
        int mark = 0;
        for (int i=0;i < types.length;i++) {
            if (args[i] == null) {
                mark++;
            }
            else if (args[i].getClass() == types[i]) {
                mark++;
            }
        }
        return mark;
    }

    public static void forceThrow(Throwable ex) {
        ThrowableContainer con = new ThrowableContainer();
        try {
            Field f = ThrowableContainer.class.getDeclaredField("exception");
            long l = (long)javaxt(getUnsafe(), "sun.misc.Unsafe", "objectFieldOffset", f);
            javaxt(getUnsafe(), "sun.misc.Unsafe", "putObject", con, l, ex);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        con.throwNow();
    }

    public static Object javat(Object obj, CharSequence desc, Object... args) throws IllegalAccessException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, SecurityException {
        String str = desc.toString();
        int i = str.lastIndexOf('.');
        if (i == - 1) {
            throw new IllegalArgumentException();
        }
        return javaxt(obj, desc.subSequence(0, i), desc.subSequence(i + 1, desc.length()), args);
    }

    public static Object javaxt(Object obj, Object clazz, Object name, Object... args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InstantiationException, InvocationTargetException, SecurityException {
        Class<?> targetClass = getClass(clazz);
        if (name == null) {
            throw new IllegalArgumentException("null.method name");
        }
        String m_name = String.valueOf(name);
        Method method = null;
        int mark = - 1;
        for (Method meth : targetClass.getDeclaredMethods()) {
            if (meth.getName().equals(m_name)) {
                int newMark = getMatchCount(args, meth.getParameterTypes());
                if (newMark > mark) {
                    mark = newMark;
                    method = meth;
                }
            }
        }
        if (method == null) {
            throw new NoSuchMethodException(m_name);
        }
        method.setAccessible(true);
        CastArg(args, method.getParameterTypes());
        return method.invoke(obj, args);
    }

    public static Object javacb(Object clazz, Callback callback) throws ClassNotFoundException {
        return javacb(clazz, callback, false);
    }

    public static Object javacb(Object clazz, Callback callback, boolean wrap) throws ClassNotFoundException {
        Class<?> targetClass = getClass(clazz);
        if (!targetClass.isInterface()) {
            throw new IllegalArgumentException("Not a interface");
        }
        if (callback == null) {
            throw new NullPointerException();
        }
        return Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[]{targetClass}, wrap ? new JavaWrappedCallback(callback) : new JavaCallback(callback));
    }

    private static class ThrowableContainer {

        private RuntimeException exception;

        private ThrowableContainer() {

        }

        public void throwNow() {
            if (exception != null) {
                throw exception;
            }
        }

    }

    public static class JavaWrappedCallback implements InvocationHandler {

        private final Callback cb;

        private JavaWrappedCallback(Callback callback) {
            if (callback == null) {
                throw new NullPointerException();
            }
            cb = callback;
        }

        @Override
        public Object invoke(Object target, Method method, Object[] args) {
            Object rt = cb.onCallMethod(target, method, args);
            if (rt == null) {
                switch (method.getReturnType().getName()) {
                    case "boolean":
                        rt = false;
                        break;
                    case "int":
                    case "char":
                    case "long":
                    case "short":
                    case "double":
                    case "float":
                    case "byte":
                        rt = 0;
                        break;
                }
            }
            else {
                if (method.getReturnType() == void.class) {
                    return null;
                }
                try {
                    Object[] obj = new Object[]{rt};
                    CastArg(obj, new Class[]{method.getReturnType()});
                    rt = obj[0];
                }
                catch (Throwable t) {
                    //无力回天
                    t.printStackTrace();
                }
            }
            return rt;
        }
    }

    private static class JavaCallback implements InvocationHandler {

        private final Callback cb;

        private JavaCallback(Callback callback) {
            if (callback == null) {
                throw new NullPointerException();
            }
            cb = callback;
        }

        @Override
        public Object invoke(Object target, Method method, Object[] args) {
            return cb.onCallMethod(target, method, args);
        }

    }

    public interface Callback {

        Object onCallMethod(Object target, Method proxyMethod, Object[] args);

    }


}

