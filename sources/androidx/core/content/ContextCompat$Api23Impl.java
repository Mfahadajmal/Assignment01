package androidx.core.content;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextCompat$Api23Impl {
    public static void checkArgumentNonnegative$ar$ds(int i) {
        if (i >= 0) {
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNotNull$ar$ds$4e7b8cd1_0(Object obj, Object obj2) {
        if (obj != null) {
        } else {
            throw new NullPointerException((String) obj2);
        }
    }

    public static int getColor(Context context, int i) {
        return context.getColor(i);
    }

    static <T> T getSystemService(Context context, Class<T> cls) {
        return (T) context.getSystemService(cls);
    }

    static String getSystemServiceName(Context context, Class<?> cls) {
        return context.getSystemServiceName(cls);
    }
}
