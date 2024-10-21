package com.google.android.accessibility.selecttospeak.ui;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OverlayCoordinatesAnimator extends ValueAnimator {
    public final NodeMenuRuleCreator overlayCoordinatesSynchronizer$ar$class_merging;
    public final Interpolator snapToSideInterpolator = new OvershootInterpolator();
    private final Interpolator moveInterpolator = new AccelerateDecelerateInterpolator();
    public final int[] fromPixelCoordinates = new int[2];
    public final int[] toPixelCoordinates = new int[2];

    public OverlayCoordinatesAnimator(NodeMenuRuleCreator nodeMenuRuleCreator) {
        this.overlayCoordinatesSynchronizer$ar$class_merging = nodeMenuRuleCreator;
        addUpdateListener(new CircularProgressDrawable.AnonymousClass1(this, nodeMenuRuleCreator, 2));
    }

    public final void prepareForMoveAnimation(int i, int i2) {
        cancel();
        removeAllListeners();
        this.overlayCoordinatesSynchronizer$ar$class_merging.getPixelCoordinates(this.fromPixelCoordinates);
        int[] iArr = this.toPixelCoordinates;
        int i3 = 0;
        iArr[0] = i;
        iArr[1] = i2;
        setFloatValues(0.0f, 1.0f);
        setInterpolator(this.moveInterpolator);
        int[] iArr2 = this.fromPixelCoordinates;
        int[] iArr3 = this.toPixelCoordinates;
        int hypot = (int) Math.hypot(iArr3[0] - iArr2[0], iArr3[1] - iArr2[1]);
        NodeMenuRuleCreator nodeMenuRuleCreator = this.overlayCoordinatesSynchronizer$ar$class_merging;
        int max = Math.max(nodeMenuRuleCreator.getMaxWindowX(), nodeMenuRuleCreator.getMaxWindowY());
        if (hypot > max) {
            hypot = max;
        }
        if (max != 0) {
            i3 = (int) (((hypot / max) * 300.0f) + 0.0f);
        }
        setDuration(i3);
    }
}
