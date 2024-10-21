package android.support.v4.app;

import androidx.collection.SimpleArrayMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FragmentFactory {
    public static final /* synthetic */ int FragmentFactory$ar$NoOp = 0;
    private static final SimpleArrayMap sClassCacheMap = new SimpleArrayMap();

    public static Class loadClass(ClassLoader classLoader, String str) {
        SimpleArrayMap simpleArrayMap = sClassCacheMap;
        SimpleArrayMap simpleArrayMap2 = (SimpleArrayMap) simpleArrayMap.get(classLoader);
        if (simpleArrayMap2 == null) {
            simpleArrayMap2 = new SimpleArrayMap();
            simpleArrayMap.put(classLoader, simpleArrayMap2);
        }
        Class cls = (Class) simpleArrayMap2.get(str);
        if (cls == null) {
            Class<?> cls2 = Class.forName(str, false, classLoader);
            simpleArrayMap2.put(str, cls2);
            return cls2;
        }
        return cls;
    }

    public Fragment instantiate$ar$ds(String str) {
        throw null;
    }
}
