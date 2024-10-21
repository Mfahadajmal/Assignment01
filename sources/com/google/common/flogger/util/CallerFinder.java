package com.google.common.flogger.util;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallerFinder {
    public static final StackGetter STACK_GETTER;
    private static final String[] STACK_GETTER_IMPLEMENTATIONS = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.common.flogger.util.StackGetter] */
    static {
        ThrowableStackGetter throwableStackGetter;
        int i = 0;
        while (true) {
            if (i < 2) {
                throwableStackGetter = null;
                try {
                    throwableStackGetter = (StackGetter) Class.forName(STACK_GETTER_IMPLEMENTATIONS[i]).asSubclass(StackGetter.class).getDeclaredConstructor(null).newInstance(null);
                } catch (Throwable unused) {
                }
                if (throwableStackGetter != null) {
                    break;
                } else {
                    i++;
                }
            } else {
                throwableStackGetter = new ThrowableStackGetter();
                break;
            }
        }
        STACK_GETTER = throwableStackGetter;
    }
}
