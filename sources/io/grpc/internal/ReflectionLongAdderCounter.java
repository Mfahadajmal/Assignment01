package io.grpc.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReflectionLongAdderCounter implements LongCounter {
    private static final Method addMethod;
    private static final Constructor defaultConstructor;
    public static final RuntimeException initializationException;
    private static final Logger logger = Logger.getLogger(ReflectionLongAdderCounter.class.getName());
    private static final Object[] one;
    private final Object instance;

    static {
        Method method;
        Constructor<?> constructor;
        Method method2;
        try {
            Class<?> cls = Class.forName("java.util.concurrent.atomic.LongAdder");
            method2 = cls.getMethod("add", Long.TYPE);
            try {
                cls.getMethod("sum", null);
                Constructor<?>[] constructors = cls.getConstructors();
                int length = constructors.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        constructor = constructors[i];
                        if (constructor.getParameterTypes().length == 0) {
                            break;
                        } else {
                            i++;
                        }
                    } else {
                        constructor = null;
                        break;
                    }
                }
                th = null;
            } catch (Throwable th) {
                th = th;
                method = method2;
                logger.logp(Level.FINE, "io.grpc.internal.ReflectionLongAdderCounter", "<clinit>", "LongAdder can not be found via reflection, this is normal for JDK7 and below", th);
                constructor = null;
                method2 = method;
                if (th != null) {
                }
                defaultConstructor = null;
                addMethod = null;
                initializationException = new RuntimeException(th);
                one = new Object[]{1L};
            }
        } catch (Throwable th2) {
            th = th2;
            method = null;
        }
        if (th != null && constructor != null) {
            defaultConstructor = constructor;
            addMethod = method2;
            initializationException = null;
        } else {
            defaultConstructor = null;
            addMethod = null;
            initializationException = new RuntimeException(th);
        }
        one = new Object[]{1L};
    }

    public ReflectionLongAdderCounter() {
        RuntimeException runtimeException = initializationException;
        if (runtimeException == null) {
            try {
                this.instance = defaultConstructor.newInstance(null);
                return;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3);
            }
        }
        throw runtimeException;
    }

    @Override // io.grpc.internal.LongCounter
    public final void add$ar$ds$3d014f3e_0() {
        try {
            addMethod.invoke(this.instance, one);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }
}
