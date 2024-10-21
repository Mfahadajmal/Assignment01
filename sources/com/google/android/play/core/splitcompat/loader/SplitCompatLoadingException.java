package com.google.android.play.core.splitcompat.loader;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitCompatLoadingException extends RuntimeException {
    public SplitCompatLoadingException(String str) {
        super(str);
    }

    public SplitCompatLoadingException(Throwable th) {
        super("Failed to initialize FileStorage", th);
    }
}
