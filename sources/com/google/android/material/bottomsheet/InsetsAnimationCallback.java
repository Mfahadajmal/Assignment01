package com.google.android.material.bottomsheet;

import android.view.View;
import androidx.core.view.WindowInsetsAnimationCompat$BoundsCompat;
import androidx.core.view.WindowInsetsAnimationCompat$Callback;
import androidx.core.view.WindowInsetsAnimationCompat$Impl;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class InsetsAnimationCallback extends WindowInsetsAnimationCompat$Callback {
    private int startTranslationY;
    private int startY;
    private final int[] tmpLocation = new int[2];
    private final View view;

    public InsetsAnimationCallback(View view) {
        this.view = view;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public final void onEnd$ar$ds() {
        this.view.setTranslationY(0.0f);
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public final void onPrepare$ar$ds() {
        this.view.getLocationOnScreen(this.tmpLocation);
        this.startY = this.tmpLocation[1];
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public final void onProgress$ar$ds$82a9f68b_0(WindowInsetsCompat windowInsetsCompat, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if ((((WindowInsetsAnimationCompat$Impl) ((OnDeviceTextDetectionLoadLogEvent) it.next()).OnDeviceTextDetectionLoadLogEvent$ar$errorCode).getTypeMask() & 8) != 0) {
                this.view.setTranslationY(AnimationUtils.lerp(this.startTranslationY, 0, r3.getInterpolatedFraction()));
                return;
            }
        }
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public final void onStart$ar$ds(WindowInsetsAnimationCompat$BoundsCompat windowInsetsAnimationCompat$BoundsCompat) {
        this.view.getLocationOnScreen(this.tmpLocation);
        int i = this.startY - this.tmpLocation[1];
        this.startTranslationY = i;
        this.view.setTranslationY(i);
    }
}
