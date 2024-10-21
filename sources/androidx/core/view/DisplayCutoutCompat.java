package androidx.core.view;

import android.graphics.Rect;
import android.view.DisplayCutout;
import androidx.lifecycle.Lifecycle;
import j$.util.Objects;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DisplayCutoutCompat {
    public final DisplayCutout mDisplayCutout;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api28Impl {
        static DisplayCutout createDisplayCutout(Rect rect, List<Rect> list) {
            return new DisplayCutout(rect, list);
        }

        static List<Rect> getBoundingRects(DisplayCutout displayCutout) {
            return displayCutout.getBoundingRects();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int getSafeInsetBottom(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetBottom();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int getSafeInsetLeft(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetLeft();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int getSafeInsetRight(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetRight();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int getSafeInsetTop(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetTop();
        }

        public static final Lifecycle.State min$lifecycle_runtime_release$ar$ds(Lifecycle.State state, Lifecycle.State state2) {
            state.getClass();
            if (state2 != null && state2.compareTo(state) < 0) {
                return state2;
            }
            return state;
        }
    }

    public DisplayCutoutCompat(DisplayCutout displayCutout) {
        this.mDisplayCutout = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Objects.equals(this.mDisplayCutout, ((DisplayCutoutCompat) obj).mDisplayCutout);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        hashCode = this.mDisplayCutout.hashCode();
        return hashCode;
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }
}
