package com.google.android.accessibility.utils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ReadOnly {
    public boolean mIsWritable = true;

    public final void checkIsWritable() {
        if (this.mIsWritable) {
        } else {
            throw new IllegalStateException("Attempting to write a read-only object.");
        }
    }

    public final void setReadOnly() {
        this.mIsWritable = false;
    }
}
