package com.google.android.accessibility.utils.ocr;

import android.graphics.Rect;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class OcrInfo {
    public final AccessibilityNodeInfoCompat node;
    public List textBlocks;

    public OcrInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.node = accessibilityNodeInfoCompat;
    }

    public void getBoundsInScreenForOcr(Rect rect) {
        this.node.getBoundsInScreen(rect);
    }
}
