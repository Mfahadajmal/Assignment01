package com.google.gson.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ReflectionAccessFilterHelper$AccessChecker {
    public static final ReflectionAccessFilterHelper$AccessChecker INSTANCE;

    static {
        ReflectionAccessFilterHelper$AccessChecker reflectionAccessFilterHelper$AccessChecker = null;
        if (JavaVersion.isJava9OrLater()) {
            try {
                final Method declaredMethod = AccessibleObject.class.getDeclaredMethod("canAccess", Object.class);
                reflectionAccessFilterHelper$AccessChecker = new ReflectionAccessFilterHelper$AccessChecker() { // from class: com.google.gson.internal.ReflectionAccessFilterHelper$AccessChecker.1
                    @Override // com.google.gson.internal.ReflectionAccessFilterHelper$AccessChecker
                    public final boolean canAccess(AccessibleObject accessibleObject, Object obj) {
                        try {
                            return ((Boolean) declaredMethod.invoke(accessibleObject, obj)).booleanValue();
                        } catch (Exception e) {
                            throw new RuntimeException("Failed invoking canAccess", e);
                        }
                    }
                };
            } catch (NoSuchMethodException unused) {
            }
        }
        if (reflectionAccessFilterHelper$AccessChecker == null) {
            reflectionAccessFilterHelper$AccessChecker = new ReflectionAccessFilterHelper$AccessChecker() { // from class: com.google.gson.internal.ReflectionAccessFilterHelper$AccessChecker.2
                @Override // com.google.gson.internal.ReflectionAccessFilterHelper$AccessChecker
                public final boolean canAccess(AccessibleObject accessibleObject, Object obj) {
                    return true;
                }
            };
        }
        INSTANCE = reflectionAccessFilterHelper$AccessChecker;
    }

    public abstract boolean canAccess(AccessibleObject accessibleObject, Object obj);
}
