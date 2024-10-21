package com.google.common.flogger.android;

import com.google.common.flogger.AbstractLogger;
import com.google.common.flogger.LoggingApi;
import com.google.common.flogger.backend.LoggerBackend;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AndroidAbstractLogger extends AbstractLogger {
    protected static final LoggingApi.NoOp NO_OP$ar$class_merging$bfbdd76d_0$ar$class_merging = new LoggingApi.NoOp((char[]) null);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Api extends LoggingApi {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AndroidAbstractLogger(LoggerBackend loggerBackend) {
        super(loggerBackend);
    }
}
