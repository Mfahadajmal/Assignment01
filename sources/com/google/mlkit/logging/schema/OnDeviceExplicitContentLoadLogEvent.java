package com.google.mlkit.logging.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceExplicitContentLoadLogEvent {
    public static void checkBuilderRequirement(Object obj, Class cls) {
        if (obj != null) {
        } else {
            throw new IllegalStateException(String.valueOf(cls.getCanonicalName()).concat(" must be set"));
        }
    }

    public static List presizedList(int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        return new ArrayList(i);
    }
}
