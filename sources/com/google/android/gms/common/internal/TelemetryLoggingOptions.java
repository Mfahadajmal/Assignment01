package com.google.android.gms.common.internal;

import _COROUTINE._BOUNDARY;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TelemetryLoggingOptions implements Api$ApiOptions {
    public static final TelemetryLoggingOptions DEFAULT_OPTIONS = new OnDeviceTextDetectionLoadLogEvent().m224build();
    public final String api;

    public TelemetryLoggingOptions(String str) {
        this.api = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TelemetryLoggingOptions)) {
            return false;
        }
        return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.api, ((TelemetryLoggingOptions) obj).api);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.api});
    }
}
