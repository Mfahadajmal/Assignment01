package com.google.android.libraries.accessibility.utils.screenunderstanding;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.google.android.accessibility.brailleime.BrailleImeVibrator$VibrationType;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UiChangesTracker {
    private static UiChangesTracker instance$ar$class_merging$295aa713_0$ar$class_merging;
    public final Object UiChangesTracker$ar$dirtyRegion;
    public boolean wholeScreenChangeObserved;

    private UiChangesTracker(Context context) {
        this.wholeScreenChangeObserved = false;
        this.UiChangesTracker$ar$dirtyRegion = (Vibrator) context.getSystemService("vibrator");
    }

    public static UiChangesTracker getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(Context context) {
        if (instance$ar$class_merging$295aa713_0$ar$class_merging == null) {
            instance$ar$class_merging$295aa713_0$ar$class_merging = new UiChangesTracker(context.getApplicationContext());
        }
        return instance$ar$class_merging$295aa713_0$ar$class_merging;
    }

    public final Region getDirtyRegion() {
        Region region;
        synchronized (this) {
            if (this.wholeScreenChangeObserved) {
                region = null;
            } else {
                region = new Region((Region) this.UiChangesTracker$ar$dirtyRegion);
            }
        }
        return region;
    }

    public final boolean isWholeScreenChangeObserved() {
        boolean z;
        synchronized (this) {
            z = this.wholeScreenChangeObserved;
        }
        return z;
    }

    public final void onPartialUiChange(Rect rect) {
        synchronized (this) {
            if (!this.wholeScreenChangeObserved) {
                Object obj = this.UiChangesTracker$ar$dirtyRegion;
                ((Region) obj).op(rect, (Region) obj, Region.Op.UNION);
            }
        }
    }

    public final void onWholeScreenChange() {
        synchronized (this) {
            if (!this.wholeScreenChangeObserved) {
                this.wholeScreenChangeObserved = true;
                ((Region) this.UiChangesTracker$ar$dirtyRegion).setEmpty();
            }
        }
    }

    public final void reset() {
        synchronized (this) {
            this.wholeScreenChangeObserved = false;
            ((Region) this.UiChangesTracker$ar$dirtyRegion).setEmpty();
        }
    }

    public final void vibrate(BrailleImeVibrator$VibrationType brailleImeVibrator$VibrationType) {
        VibrationEffect createOneShot;
        if (!this.wholeScreenChangeObserved) {
            return;
        }
        Object obj = this.UiChangesTracker$ar$dirtyRegion;
        BrailleImeVibrator$VibrationType brailleImeVibrator$VibrationType2 = BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION;
        createOneShot = VibrationEffect.createOneShot(brailleImeVibrator$VibrationType.duration, brailleImeVibrator$VibrationType.amplitude);
        ((Vibrator) obj).vibrate(createOneShot);
    }

    public UiChangesTracker(byte[] bArr) {
        this.wholeScreenChangeObserved = false;
        this.UiChangesTracker$ar$dirtyRegion = new ArrayList();
    }

    public UiChangesTracker() {
        this.UiChangesTracker$ar$dirtyRegion = new Region();
        reset();
    }
}
