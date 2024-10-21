package com.google.android.accessibility.utils.compat.view;

import android.view.MotionEvent;
import com.google.android.accessibility.utils.compat.CompatUtils;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MotionEventCompatUtils {
    private static final Class CLASS_MotionEvent = MotionEvent.class;
    public static final Method METHOD_setSource;

    static {
        CompatUtils.getMethod(MotionEvent.class, "getSource", new Class[0]);
        METHOD_setSource = CompatUtils.getMethod(MotionEvent.class, "setSource", Integer.TYPE);
        CompatUtils.getMethod(MotionEvent.class, "setDownTime", Long.TYPE);
    }
}
