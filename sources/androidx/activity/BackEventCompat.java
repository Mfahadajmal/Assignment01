package androidx.activity;

import android.window.BackEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackEventCompat {
    public final float progress;
    private final int swipeEdge;
    private final float touchX;
    private final float touchY;

    public BackEventCompat(BackEvent backEvent) {
        float f = Api34Impl.INSTANCE.touchX(backEvent);
        float f2 = Api34Impl.INSTANCE.touchY(backEvent);
        float progress = Api34Impl.INSTANCE.progress(backEvent);
        int swipeEdge = Api34Impl.INSTANCE.swipeEdge(backEvent);
        this.touchX = f;
        this.touchY = f2;
        this.progress = progress;
        this.swipeEdge = swipeEdge;
    }

    public final String toString() {
        return "BackEventCompat{touchX=" + this.touchX + ", touchY=" + this.touchY + ", progress=" + this.progress + ", swipeEdge=" + this.swipeEdge + '}';
    }
}
