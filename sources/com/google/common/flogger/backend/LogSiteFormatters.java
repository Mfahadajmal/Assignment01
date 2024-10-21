package com.google.common.flogger.backend;

import com.google.common.flogger.LogSite;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum LogSiteFormatters implements LogSiteFormatter {
    DEFAULT,
    NO_OP,
    SIMPLE_CLASSNAME;

    @Override // com.google.common.flogger.backend.LogSiteFormatter
    public final /* synthetic */ boolean appendLogSite(LogSite logSite, StringBuilder sb) {
        int ordinal = ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return false;
            }
            if (ordinal != 2) {
                throw null;
            }
            throw null;
        }
        if (logSite == LogSite.INVALID) {
            return false;
        }
        sb.append(logSite.getClassName());
        sb.append('.');
        sb.append(logSite.getMethodName());
        sb.append(':');
        sb.append(logSite.getLineNumber());
        return true;
    }
}
