package com.google.android.accessibility.talkback;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Mappers$Variables {
    public final Context context;
    public final AccessibilityEvent event;
    public final SpannableUtils$NonCopyableTextSpan interpretation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 monitors$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final SharedPreferences prefs;
    private AccessibilityNodeInfoCompat source;

    public Mappers$Variables(Context context, AccessibilityEvent accessibilityEvent, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        FormFactorUtils.getInstance();
        this.context = context;
        this.event = accessibilityEvent;
        this.interpretation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.monitors$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    public final AccessibilityNodeInfoCompat source$ar$ds() {
        if (this.source == null) {
            this.source = SpannableUtils$IdentifierSpan.sourceCompat(this.event);
        }
        return this.source;
    }

    public final String toString() {
        AccessibilityEvent accessibilityEvent = this.event;
        return "interpretation=" + String.valueOf(this.interpretation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging) + " event=" + String.valueOf(accessibilityEvent);
    }
}
