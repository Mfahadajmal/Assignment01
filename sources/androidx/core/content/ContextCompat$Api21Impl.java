package androidx.core.content;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextCompat$Api21Impl {
    static File getCodeCacheDir(Context context) {
        return context.getCodeCacheDir();
    }

    public static Drawable getDrawable(Context context, int i) {
        return context.getDrawable(i);
    }

    static File getNoBackupFilesDir(Context context) {
        return context.getNoBackupFilesDir();
    }

    public static void requireNonNull$ar$ds$6106c18d_0(Object obj, String str) {
        if (obj != null) {
        } else {
            throw new NullPointerException(str);
        }
    }
}
