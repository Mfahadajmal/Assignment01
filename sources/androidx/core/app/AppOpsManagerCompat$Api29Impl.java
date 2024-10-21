package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.graphics.Shader;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppOpsManagerCompat$Api29Impl {
    public static int checkOpNoThrow(AppOpsManager appOpsManager, String str, int i, String str2) {
        if (appOpsManager == null) {
            return 1;
        }
        return appOpsManager.checkOpNoThrow(str, i, str2);
    }

    public static String getOpPackageName(Context context) {
        return context.getOpPackageName();
    }

    public static AppOpsManager getSystemService(Context context) {
        return (AppOpsManager) context.getSystemService(AppOpsManager.class);
    }

    public static Shader.TileMode parseTileMode(int i) {
        if (i != 1) {
            if (i != 2) {
                return Shader.TileMode.CLAMP;
            }
            return Shader.TileMode.MIRROR;
        }
        return Shader.TileMode.REPEAT;
    }
}
