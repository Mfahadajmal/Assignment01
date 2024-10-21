package androidx.activity;

import android.window.BackEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Api34Impl {
    public static final Api34Impl INSTANCE = new Api34Impl();

    private Api34Impl() {
    }

    public final BackEvent createOnBackEvent(float f, float f2, float f3, int i) {
        return new BackEvent(f, f2, f3, i);
    }

    public final float progress(BackEvent backEvent) {
        backEvent.getClass();
        return backEvent.getProgress();
    }

    public final int swipeEdge(BackEvent backEvent) {
        backEvent.getClass();
        return backEvent.getSwipeEdge();
    }

    public final float touchX(BackEvent backEvent) {
        backEvent.getClass();
        return backEvent.getTouchX();
    }

    public final float touchY(BackEvent backEvent) {
        backEvent.getClass();
        return backEvent.getTouchY();
    }
}
