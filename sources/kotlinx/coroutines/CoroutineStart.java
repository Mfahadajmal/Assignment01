package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoroutineStart {
    public static final int DEFAULT$ar$edu$74eab234_0 = 1;
    public static final int LAZY$ar$edu = 2;
    public static final int ATOMIC$ar$edu = 3;
    public static final int UNDISPATCHED$ar$edu = 4;
    private static final /* synthetic */ int[] $VALUES$ar$edu$ae1e9287_0 = {DEFAULT$ar$edu$74eab234_0, LAZY$ar$edu, ATOMIC$ar$edu, UNDISPATCHED$ar$edu};

    public static void invoke$ar$edu(int i, Function2 function2, Object obj, Continuation continuation) {
        Object invoke;
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 == 3) {
                    try {
                        CoroutineContext context = continuation.getContext();
                        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, null);
                        try {
                            if (!(function2 instanceof BaseContinuationImpl)) {
                                invoke = OnDevicePoseDetectionLogEvent.wrapWithContinuationImpl(function2, obj, continuation);
                            } else {
                                TypeIntrinsics.beforeCheckcastToFunctionOfArity$ar$ds(function2, 2);
                                invoke = function2.invoke(obj, continuation);
                            }
                            if (invoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                continuation.resumeWith(invoke);
                                return;
                            }
                            return;
                        } finally {
                            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                        }
                    } catch (Throwable th) {
                        continuation.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th));
                        return;
                    }
                }
                return;
            }
            OnDevicePoseDetectionLogEvent.intercepted(OnDevicePoseDetectionLogEvent.createCoroutineUnintercepted(function2, obj, continuation)).resumeWith(Unit.INSTANCE);
            return;
        }
        TextDetectionOptionalModuleOptions.startCoroutineCancellable$default$ar$ds(function2, obj, continuation);
    }

    public static int[] values$ar$edu$30af0825_0() {
        return new int[]{DEFAULT$ar$edu$74eab234_0, LAZY$ar$edu, ATOMIC$ar$edu, UNDISPATCHED$ar$edu};
    }
}
