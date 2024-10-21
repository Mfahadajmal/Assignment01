package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Lifecycling {
    public static final Lifecycling INSTANCE = new Lifecycling();
    private static final Map callbackCache = new HashMap();
    public static final Map classToAdapters = new HashMap();

    private Lifecycling() {
    }

    public static final GeneratedAdapter createGeneratedAdapter$ar$ds(Constructor constructor, Object obj) {
        try {
            Object newInstance = constructor.newInstance(obj);
            newInstance.getClass();
            return (GeneratedAdapter) newInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static final boolean isLifecycleParent$ar$ds(Class cls) {
        if (cls != null && LifecycleObserver.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00aa, code lost:
    
        if (r5.booleanValue() != false) goto L63;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int getObserverConstructorType(java.lang.Class r11) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.Lifecycling.getObserverConstructorType(java.lang.Class):int");
    }
}
