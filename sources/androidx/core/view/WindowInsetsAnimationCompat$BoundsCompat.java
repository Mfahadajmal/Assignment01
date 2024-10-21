package androidx.core.view;

import android.view.WindowInsetsAnimation;
import androidx.core.graphics.Insets;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowInsetsAnimationCompat$BoundsCompat {
    public final Insets mLowerBound;
    public final Insets mUpperBound;

    public WindowInsetsAnimationCompat$BoundsCompat(Insets insets, Insets insets2) {
        this.mLowerBound = insets;
        this.mUpperBound = insets2;
    }

    public final String toString() {
        return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
    }

    public WindowInsetsAnimationCompat$BoundsCompat(WindowInsetsAnimation.Bounds bounds) {
        android.graphics.Insets lowerBound;
        android.graphics.Insets upperBound;
        lowerBound = bounds.getLowerBound();
        this.mLowerBound = Insets.toCompatInsets(lowerBound);
        upperBound = bounds.getUpperBound();
        this.mUpperBound = Insets.toCompatInsets(upperBound);
    }
}
