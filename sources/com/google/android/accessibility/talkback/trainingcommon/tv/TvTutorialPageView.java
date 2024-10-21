package com.google.android.accessibility.talkback.trainingcommon.tv;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
class TvTutorialPageView extends LinearLayout {
    public TvTutorialPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.google.android.accessibility.talkback.trainingcommon.tv.TvTutorialPageView.1
            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN.getId()) {
                    return false;
                }
                return super.performAccessibilityAction(view, i, bundle);
            }
        });
    }
}
