package com.google.android.accessibility.utils.compat.media;

import com.google.android.accessibility.utils.compat.CompatUtils;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AudioSystemCompatUtils {
    private static final Class CLASS_AudioSystem;
    private static final Method METHOD_isSourceActive;

    static {
        Class cls = CompatUtils.getClass("android.media.AudioSystem");
        CLASS_AudioSystem = cls;
        METHOD_isSourceActive = CompatUtils.getMethod(cls, "isSourceActive", Integer.TYPE);
    }

    public static boolean isSourceActive(int i) {
        return ((Boolean) CompatUtils.invoke(null, false, METHOD_isSourceActive, Integer.valueOf(i))).booleanValue();
    }
}
