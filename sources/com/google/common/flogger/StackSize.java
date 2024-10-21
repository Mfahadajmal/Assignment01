package com.google.common.flogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum StackSize {
    SMALL(10),
    MEDIUM(20),
    LARGE(50),
    FULL(-1),
    NONE(0);

    public final int maxDepth;

    StackSize(int i) {
        this.maxDepth = i;
    }
}
