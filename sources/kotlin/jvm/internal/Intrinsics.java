package kotlin.jvm.internal;

import _COROUTINE._BOUNDARY;
import java.util.Arrays;
import kotlin.UninitializedPropertyAccessException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Intrinsics {
    public static boolean areEqual(Object obj, Object obj2) {
        if (obj == null) {
            if (obj2 != null) {
                return false;
            }
            return true;
        }
        return obj.equals(obj2);
    }

    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        if (i != i2) {
            return 1;
        }
        return 0;
    }

    public static final String renderLambdaToString$ar$ds(FunctionBase functionBase) {
        String obj = functionBase.getClass().getGenericInterfaces()[0].toString();
        if (obj.startsWith("kotlin.jvm.functions.")) {
            return obj.substring(21);
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sanitizeStackTrace$ar$ds(Throwable th, String str) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (true == str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
    }

    public static void throwUninitializedPropertyAccessException(String str) {
        UninitializedPropertyAccessException uninitializedPropertyAccessException = new UninitializedPropertyAccessException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "lateinit property ", " has not been initialized"));
        sanitizeStackTrace$ar$ds(uninitializedPropertyAccessException, Intrinsics.class.getName());
        throw uninitializedPropertyAccessException;
    }
}
