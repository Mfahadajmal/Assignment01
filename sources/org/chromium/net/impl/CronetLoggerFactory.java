package org.chromium.net.impl;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.mlkit.logging.schema.acceleration.NNAPIInfo;
import org.chromium.net.impl.CronetLogger;
import org.chromium.net.telemetry.CronetLoggerImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetLoggerFactory {
    private static final String TAG = "CronetLoggerFactory";
    private static CronetLogger sLogger;

    private CronetLoggerFactory() {
    }

    public static CronetLogger createLogger(Context context, CronetLogger.CronetSource cronetSource) {
        CronetLogger cronetLogger;
        synchronized (CronetLoggerFactory.class) {
            if (sLogger == null) {
                boolean z = true;
                if (cronetSource != CronetLogger.CronetSource.CRONET_SOURCE_PLATFORM && cronetSource != CronetLogger.CronetSource.CRONET_SOURCE_PLAY_SERVICES) {
                    z = false;
                }
                if (NNAPIInfo.DeviceInfo.getMetaData(context).getBoolean("android.net.http.EnableTelemetry", z) && Build.VERSION.SDK_INT >= 30) {
                    try {
                        sLogger = new CronetLoggerImpl();
                    } catch (Exception e) {
                        Log.e(TAG, "Exception creating an instance of CronetLoggerImpl", e);
                    }
                }
            }
            if (sLogger == null) {
                sLogger = new NoOpLogger();
            }
            cronetLogger = sLogger;
        }
        return cronetLogger;
    }
}
