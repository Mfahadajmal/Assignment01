package com.google.mlkit.logging.schema;

import io.perfmark.Tag;
import java.io.Closeable;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceShadowRemovalLogEvent {
    public static final void closeFinally(Closeable closeable, Throwable th) {
        if (th == null) {
            closeable.close();
            return;
        }
        try {
            closeable.close();
        } catch (Throwable th2) {
            Tag.addSuppressed(th, th2);
        }
    }

    public static final Class getJavaClass(KClass kClass) {
        kClass.getClass();
        return ((ClassBasedDeclarationContainer) kClass).getJClass();
    }

    public static final Class getJavaObjectType(KClass kClass) {
        String name;
        kClass.getClass();
        Class jClass = ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (jClass.isPrimitive() && (name = jClass.getName()) != null) {
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    return jClass;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    return jClass;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    return jClass;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    return jClass;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    return jClass;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    return jClass;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    return jClass;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    return jClass;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    return jClass;
                default:
                    return jClass;
            }
        }
        return jClass;
    }

    public static final KClass getKotlinClass(Class cls) {
        cls.getClass();
        return Reflection.getOrCreateKotlinClass(cls);
    }
}
