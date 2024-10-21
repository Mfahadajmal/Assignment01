package kotlin.internal;

import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PlatformImplementations$ReflectThrowable {
    public static final Method addSuppressed;

    static {
        int length;
        Method method;
        Method[] methods = Throwable.class.getMethods();
        methods.getClass();
        int i = 0;
        while (true) {
            length = methods.length;
            method = null;
            Class<?> cls = null;
            if (i >= length) {
                break;
            }
            Method method2 = methods[i];
            if (Intrinsics.areEqual(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                parameterTypes.getClass();
                if (parameterTypes.length == 1) {
                    cls = parameterTypes[0];
                }
                if (Intrinsics.areEqual(cls, Throwable.class)) {
                    method = method2;
                    break;
                }
            }
            i++;
        }
        addSuppressed = method;
        for (int i2 = 0; i2 < length && !Intrinsics.areEqual(methods[i2].getName(), "getSuppressed"); i2++) {
        }
    }
}
