package com.google.common.flogger.backend;

import com.google.common.flogger.LogContext;
import io.grpc.internal.RetryingNameResolver;
import java.util.Collections;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleMessageFormatter {
    private static final Set DEFAULT_KEYS_TO_IGNORE;
    public static final /* synthetic */ int SimpleMessageFormatter$ar$NoOp = 0;

    static {
        Set singleton = Collections.singleton(LogContext.Key.LOG_CAUSE);
        DEFAULT_KEYS_TO_IGNORE = singleton;
        new RetryingNameResolver.ResolutionResultListener(singleton);
    }
}
