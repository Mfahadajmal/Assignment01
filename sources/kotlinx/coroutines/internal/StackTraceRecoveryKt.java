package kotlinx.coroutines.internal;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StackTraceRecoveryKt {
    private static final StackTraceElement ARTIFICIAL_FRAME;
    private static final String baseContinuationImplClassName;
    private static final String stackTraceRecoveryClassName;

    static {
        Object createFailure;
        Object createFailure2;
        Object obj = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        Object obj2 = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        Exception exc = new Exception();
        String simpleName = _BOUNDARY.class.getSimpleName();
        simpleName.getClass();
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        ARTIFICIAL_FRAME = new StackTraceElement("_COROUTINE." + simpleName, "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
        try {
            createFailure = Class.forName("kotlin.coroutines.jvm.internal.BaseContinuationImpl").getCanonicalName();
        } catch (Throwable th) {
            createFailure = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th);
        }
        if (Result.m248exceptionOrNullimpl(createFailure) == null) {
            obj2 = createFailure;
        }
        baseContinuationImplClassName = (String) obj2;
        try {
            createFailure2 = Class.forName("kotlinx.coroutines.internal.StackTraceRecoveryKt").getCanonicalName();
        } catch (Throwable th2) {
            createFailure2 = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th2);
        }
        if (Result.m248exceptionOrNullimpl(createFailure2) == null) {
            obj = createFailure2;
        }
        stackTraceRecoveryClassName = (String) obj;
    }

    private static final int firstFrameIndex(StackTraceElement[] stackTraceElementArr, String str) {
        int length = stackTraceElementArr.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual(str, stackTraceElementArr[i].getClassName())) {
                return i;
            }
        }
        return -1;
    }

    public static final boolean isArtificial(StackTraceElement stackTraceElement) {
        return OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(stackTraceElement.getClassName(), "_COROUTINE");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Throwable recoverFromStackFrame(java.lang.Throwable r10, kotlin.coroutines.jvm.internal.CoroutineStackFrame r11) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.StackTraceRecoveryKt.recoverFromStackFrame(java.lang.Throwable, kotlin.coroutines.jvm.internal.CoroutineStackFrame):java.lang.Throwable");
    }

    public static final Throwable recoverStackTrace(Throwable th) {
        Throwable tryCopyException;
        int i;
        StackTraceElement stackTraceElement;
        if (DebugKt.RECOVER_STACK_TRACES && (tryCopyException = ExceptionsConstructorKt.tryCopyException(th)) != null) {
            StackTraceElement[] stackTrace = tryCopyException.getStackTrace();
            int length = stackTrace.length;
            int i2 = length - 1;
            if (i2 >= 0) {
                while (true) {
                    int i3 = i2 - 1;
                    if (Intrinsics.areEqual(stackTraceRecoveryClassName, stackTrace[i2].getClassName())) {
                        break;
                    }
                    if (i3 < 0) {
                        break;
                    }
                    i2 = i3;
                }
            }
            i2 = -1;
            int firstFrameIndex = firstFrameIndex(stackTrace, baseContinuationImplClassName);
            if (firstFrameIndex == -1) {
                i = 0;
            } else {
                i = length - firstFrameIndex;
            }
            int i4 = (length - i2) - i;
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                if (i5 == 0) {
                    stackTraceElement = ARTIFICIAL_FRAME;
                } else {
                    stackTraceElement = stackTrace[((i2 + 1) + i5) - 1];
                }
                stackTraceElementArr[i5] = stackTraceElement;
            }
            tryCopyException.setStackTrace(stackTraceElementArr);
            return tryCopyException;
        }
        return th;
    }

    public static final Throwable unwrapImpl(Throwable th) {
        Throwable cause = th.getCause();
        if (cause != null) {
            if (Intrinsics.areEqual(cause.getClass(), th.getClass())) {
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (isArtificial(stackTraceElement)) {
                        return cause;
                    }
                }
            }
        }
        return th;
    }
}
