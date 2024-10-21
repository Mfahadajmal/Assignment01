package com.google.common.flogger.util;

import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ThrowableStackGetter implements StackGetter {
    private static final int findCallerIndex$ar$ds(StackTraceElement[] stackTraceElementArr, Class cls) {
        String name = cls.getName();
        boolean z = false;
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            if (stackTraceElementArr[i].getClassName().equals(name)) {
                z = true;
            } else if (z) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.common.flogger.util.StackGetter
    public final StackTraceElement callerOf$ar$ds(Class cls) {
        ContextDataProvider.checkArgument(true, "skipFrames must be >= 0");
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int findCallerIndex$ar$ds = findCallerIndex$ar$ds(stackTrace, cls);
        if (findCallerIndex$ar$ds != -1) {
            return stackTrace[findCallerIndex$ar$ds];
        }
        return null;
    }

    @Override // com.google.common.flogger.util.StackGetter
    public final StackTraceElement[] getStackForCaller$ar$ds(Class cls, int i) {
        boolean z;
        if (i != -1 && i <= 0) {
            z = false;
        } else {
            z = true;
        }
        ContextDataProvider.checkArgument(z, "maxDepth must be > 0 or -1");
        ContextDataProvider.checkArgument(true, "skipFrames must be >= 0");
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int findCallerIndex$ar$ds = findCallerIndex$ar$ds(stackTrace, cls);
        if (findCallerIndex$ar$ds == -1) {
            return new StackTraceElement[0];
        }
        int length = stackTrace.length - findCallerIndex$ar$ds;
        if (i <= 0 || i >= length) {
            i = length;
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i];
        System.arraycopy(stackTrace, findCallerIndex$ar$ds, stackTraceElementArr, 0, i);
        return stackTraceElementArr;
    }
}
