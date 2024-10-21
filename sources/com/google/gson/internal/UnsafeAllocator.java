package com.google.gson.internal;

import _COROUTINE._BOUNDARY;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class UnsafeAllocator {
    public static final UnsafeAllocator INSTANCE;

    static {
        UnsafeAllocator unsafeAllocator;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            final Object obj = declaredField.get(null);
            final Method method = cls.getMethod("allocateInstance", Class.class);
            unsafeAllocator = new UnsafeAllocator() { // from class: com.google.gson.internal.UnsafeAllocator.1
                @Override // com.google.gson.internal.UnsafeAllocator
                public final Object newInstance(Class cls2) {
                    UnsafeAllocator.assertInstantiable(cls2);
                    return method.invoke(obj, cls2);
                }
            };
        } catch (Exception unused) {
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    final int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                    final Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    unsafeAllocator = new UnsafeAllocator() { // from class: com.google.gson.internal.UnsafeAllocator.2
                        @Override // com.google.gson.internal.UnsafeAllocator
                        public final Object newInstance(Class cls2) {
                            UnsafeAllocator.assertInstantiable(cls2);
                            return declaredMethod2.invoke(null, cls2, Integer.valueOf(intValue));
                        }
                    };
                } catch (Exception unused2) {
                    final Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    unsafeAllocator = new UnsafeAllocator() { // from class: com.google.gson.internal.UnsafeAllocator.3
                        @Override // com.google.gson.internal.UnsafeAllocator
                        public final Object newInstance(Class cls2) {
                            UnsafeAllocator.assertInstantiable(cls2);
                            return declaredMethod3.invoke(null, cls2, Object.class);
                        }
                    };
                }
            } catch (Exception unused3) {
                unsafeAllocator = new UnsafeAllocator() { // from class: com.google.gson.internal.UnsafeAllocator.4
                    @Override // com.google.gson.internal.UnsafeAllocator
                    public final Object newInstance(Class cls2) {
                        throw new UnsupportedOperationException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(cls2, "Cannot allocate ", ". Usage of JDK sun.misc.Unsafe is enabled, but it could not be used. Make sure your runtime is configured correctly."));
                    }
                };
            }
        }
        INSTANCE = unsafeAllocator;
    }

    public static void assertInstantiable(Class cls) {
        String checkInstantiable = ConstructorConstructor.checkInstantiable(cls);
        if (checkInstantiable == null) {
        } else {
            throw new AssertionError("UnsafeAllocator is used for non-instantiable type: ".concat(checkInstantiable));
        }
    }

    public abstract Object newInstance(Class cls);
}
