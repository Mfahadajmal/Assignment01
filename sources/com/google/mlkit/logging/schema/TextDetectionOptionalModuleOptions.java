package com.google.mlkit.logging.schema;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextDetectionOptionalModuleOptions {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DetectionType {
        public static final int TYPE_UNKNOWN$ar$edu$59c699fb_0 = 1;
        public static final int LATIN$ar$edu = 2;
        public static final int LATIN_AND_CHINESE$ar$edu = 3;
        public static final int LATIN_AND_DEVANAGARI$ar$edu = 4;
        public static final int LATIN_AND_JAPANESE$ar$edu = 5;
        public static final int LATIN_AND_KOREAN$ar$edu = 6;
        public static final int CREDIT_CARD$ar$edu = 7;
        public static final int DOCUMENT$ar$edu = 8;
        public static final int PIXEL_AI$ar$edu = 9;
        private static final /* synthetic */ int[] $VALUES$ar$edu$1a185eca_0 = {TYPE_UNKNOWN$ar$edu$59c699fb_0, LATIN$ar$edu, LATIN_AND_CHINESE$ar$edu, LATIN_AND_DEVANAGARI$ar$edu, LATIN_AND_JAPANESE$ar$edu, LATIN_AND_KOREAN$ar$edu, CREDIT_CARD$ar$edu, DOCUMENT$ar$edu, PIXEL_AI$ar$edu};

        public static int[] values$ar$edu$75f12964_0() {
            return new int[]{TYPE_UNKNOWN$ar$edu$59c699fb_0, LATIN$ar$edu, LATIN_AND_CHINESE$ar$edu, LATIN_AND_DEVANAGARI$ar$edu, LATIN_AND_JAPANESE$ar$edu, LATIN_AND_KOREAN$ar$edu, CREDIT_CARD$ar$edu, DOCUMENT$ar$edu, PIXEL_AI$ar$edu};
        }
    }

    public static final void dispatcherFailure(Continuation continuation, Throwable th) {
        continuation.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th));
        throw th;
    }

    public static /* synthetic */ void startCoroutineCancellable$default$ar$ds(Function2 function2, Object obj, Continuation continuation) {
        try {
            DispatchedContinuationKt.resumeCancellableWith$ar$ds(OnDevicePoseDetectionLogEvent.intercepted(OnDevicePoseDetectionLogEvent.createCoroutineUnintercepted(function2, obj, continuation)), Unit.INSTANCE);
        } catch (Throwable th) {
            dispatcherFailure(continuation, th);
        }
    }

    public static final int systemProp(String str, int i, int i2, int i3) {
        return (int) systemProp(str, i, i2, i3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x003b, code lost:
    
        if (r9 == '+') goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long systemProp(java.lang.String r21, long r22, long r24, long r26) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions.systemProp(java.lang.String, long, long, long):long");
    }

    public static final boolean systemProp(String str, boolean z) {
        String systemProp = SystemPropsKt__SystemPropsKt.systemProp(str);
        return systemProp != null ? Boolean.parseBoolean(systemProp) : z;
    }
}
