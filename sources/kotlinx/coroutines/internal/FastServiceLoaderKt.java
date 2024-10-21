package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Result;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FastServiceLoaderKt {
    public static final boolean ANDROID_DETECTED;

    static {
        Object createFailure;
        try {
            createFailure = Class.forName("android.os.Build");
        } catch (Throwable th) {
            createFailure = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th);
        }
        ANDROID_DETECTED = Result.m249isSuccessimpl(createFailure);
    }
}
