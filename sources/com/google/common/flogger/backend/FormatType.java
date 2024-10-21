package com.google.common.flogger.backend;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum FormatType {
    GENERAL(true),
    BOOLEAN(false),
    CHARACTER(false),
    INTEGRAL(false),
    FLOAT(true);

    public final boolean supportsPrecision;

    FormatType(boolean z) {
        this.supportsPrecision = z;
    }
}
