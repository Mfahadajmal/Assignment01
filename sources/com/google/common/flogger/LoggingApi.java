package com.google.common.flogger;

import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.LoggingApi;
import com.google.common.flogger.android.AndroidAbstractLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LoggingApi<API extends LoggingApi<API>> {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NoOp implements LoggingApi, GoogleLogger.Api, AndroidAbstractLogger.Api {
        public NoOp() {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final boolean isEnabled() {
            return false;
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final LoggingApi withStackTrace(StackSize stackSize) {
            stackSize.getClass();
            return this;
        }

        public NoOp(byte[] bArr) {
            this();
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, int i) {
        }

        public NoOp(char[] cArr) {
            this((byte[]) null);
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, long j) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, long j, Object obj) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, Object obj) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, Object obj, Object obj2) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void log(String str, Object obj, Object obj2, Object obj3) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final LoggingApi withCause(Throwable th) {
            return this;
        }

        @Override // com.google.common.flogger.LoggingApi
        public final void logVarargs(String str, Object[] objArr) {
        }

        @Override // com.google.common.flogger.LoggingApi
        public final LoggingApi withInjectedLogSite(String str, String str2, int i, String str3) {
            return this;
        }
    }

    boolean isEnabled();

    void log(String str);

    void log(String str, int i);

    void log(String str, long j);

    void log(String str, long j, Object obj);

    void log(String str, Object obj);

    void log(String str, Object obj, Object obj2);

    void log(String str, Object obj, Object obj2, Object obj3);

    void logVarargs(String str, Object[] objArr);

    LoggingApi withCause(Throwable th);

    LoggingApi withInjectedLogSite(String str, String str2, int i, String str3);

    LoggingApi withStackTrace(StackSize stackSize);
}
