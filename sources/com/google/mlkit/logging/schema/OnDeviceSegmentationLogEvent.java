package com.google.mlkit.logging.schema;

import java.lang.reflect.Method;
import kotlin.internal.PlatformImplementations$ReflectThrowable;
import kotlin.random.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public class OnDeviceSegmentationLogEvent {
    public static long mod(long j, long j2) {
        return 0L;
    }

    public void addSuppressed(Throwable th, Throwable th2) {
        Method method = PlatformImplementations$ReflectThrowable.addSuppressed;
        if (method != null) {
            method.invoke(th, th2);
        }
    }

    public Random defaultPlatformRandom() {
        throw null;
    }
}
