package com.google.android.accessibility.utils;

import android.text.TextUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClassLoadingCache {
    private static final HashMap mCachedClasses = new HashMap();

    public static boolean checkInstanceOf(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != null) {
            if (TextUtils.equals(charSequence, charSequence2)) {
                return true;
            }
            Class loadOrGetCachedClass = loadOrGetCachedClass(charSequence2.toString());
            Class<?> loadOrGetCachedClass2 = loadOrGetCachedClass(charSequence.toString());
            if (loadOrGetCachedClass != null && loadOrGetCachedClass2 != null && loadOrGetCachedClass.isAssignableFrom(loadOrGetCachedClass2)) {
                return true;
            }
        }
        return false;
    }

    public static Class loadOrGetCachedClass(String str) {
        Class<?> cls = null;
        if (TextUtils.isEmpty(str)) {
            LogUtils.d("ClassLoadingCache", "Missing class name. Failed to load class.", new Object[0]);
            return null;
        }
        HashMap hashMap = mCachedClasses;
        if (!hashMap.containsKey(str)) {
            try {
                ClassLoader classLoader = ClassLoadingCache.class.getClassLoader();
                if (classLoader != null) {
                    cls = classLoader.loadClass(str);
                }
                if (cls == null) {
                    LogUtils.d("ClassLoadingCache", "Failed to load class: %s", str);
                }
            } catch (ClassNotFoundException unused) {
                LogUtils.d("ClassLoadingCache", "Failed to load class: %s", str);
            }
            mCachedClasses.put(str, cls);
            return cls;
        }
        return (Class) hashMap.get(str);
    }

    public static boolean checkInstanceOf(CharSequence charSequence, Class cls) {
        if (charSequence != null && cls != null) {
            if (TextUtils.equals(charSequence, cls.getName())) {
                return true;
            }
            Class<?> loadOrGetCachedClass = loadOrGetCachedClass(charSequence.toString());
            if (loadOrGetCachedClass != null && cls.isAssignableFrom(loadOrGetCachedClass)) {
                return true;
            }
        }
        return false;
    }
}
