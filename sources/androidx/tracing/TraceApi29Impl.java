package androidx.tracing;

import android.graphics.Path;
import android.os.Trace;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceApi29Impl {
    public TraceApi29Impl() {
    }

    public static Path getPath$ar$ds(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f3, f4);
        return path;
    }

    public static boolean isEnabled() {
        return Trace.isEnabled();
    }

    public TraceApi29Impl(byte[] bArr) {
        this();
    }
}
