package com.google.mlkit.logging.schema.acceleration;

import android.content.Context;
import org.chromium.base.StrictModeContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeviceInfo {
    public static Context createContextForSplit(Context context, String str) {
        Context createContextForSplit;
        StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
        try {
            createContextForSplit = context.createContextForSplit(str);
            allowDiskReads.close();
            return createContextForSplit;
        } catch (Throwable th) {
            try {
                allowDiskReads.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
