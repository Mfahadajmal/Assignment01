package androidx.core.view;

import android.content.Context;
import android.view.VelocityTracker;
import androidx.core.content.ContextCompat$Api26Impl;
import androidx.core.content.ContextCompat$Api30Impl;
import androidx.preference.Preference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DifferentialMotionFlingController {
    private final Context mContext;
    private final int[] mFlingVelocityThresholds;
    private float mLastFlingVelocity;
    private int mLastProcessedAxis;
    private int mLastProcessedDeviceId;
    private int mLastProcessedSource;
    private final DifferentialMotionFlingTarget mTarget;
    private final ContextCompat$Api30Impl mVelocityProvider$ar$class_merging$ar$class_merging;
    private final ContextCompat$Api26Impl mVelocityThresholdCalculator$ar$class_merging$ar$class_merging;
    private VelocityTracker mVelocityTracker;

    public DifferentialMotionFlingController(Context context, DifferentialMotionFlingTarget differentialMotionFlingTarget) {
        ContextCompat$Api26Impl contextCompat$Api26Impl = new ContextCompat$Api26Impl();
        ContextCompat$Api30Impl contextCompat$Api30Impl = new ContextCompat$Api30Impl();
        this.mLastProcessedAxis = -1;
        this.mLastProcessedSource = -1;
        this.mLastProcessedDeviceId = -1;
        this.mFlingVelocityThresholds = new int[]{Preference.DEFAULT_ORDER, 0};
        this.mContext = context;
        this.mTarget = differentialMotionFlingTarget;
        this.mVelocityThresholdCalculator$ar$class_merging$ar$class_merging = contextCompat$Api26Impl;
        this.mVelocityProvider$ar$class_merging$ar$class_merging = contextCompat$Api30Impl;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMotionEvent(android.view.MotionEvent r25, int r26) {
        /*
            Method dump skipped, instructions count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.DifferentialMotionFlingController.onMotionEvent(android.view.MotionEvent, int):void");
    }
}
