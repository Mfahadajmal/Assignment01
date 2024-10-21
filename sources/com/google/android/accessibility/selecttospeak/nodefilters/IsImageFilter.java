package com.google.android.accessibility.selecttospeak.nodefilters;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IsImageFilter extends Filter {
    @Override // com.google.android.accessibility.utils.Filter
    public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
        Bundle extras;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat);
        if (role != 6 && role != 7) {
            if (WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
                return WebInterfaceUtils.containsImage(accessibilityNodeInfoCompat);
            }
            if (!TextUtils.equals("android.view.View", accessibilityNodeInfoCompat.getClassName()) && (((extras = accessibilityNodeInfoCompat.getExtras()) == null || !extras.getBoolean("AccessibilityNodeInfo.hasImageForOCR")) && !TextUtils.equals("com.google.android.apps.photos:id/photos_photofragment_components_background_photo_view", accessibilityNodeInfoCompat.getViewIdResourceName()) && !TextUtils.equals("com.google.android.apps.photos:id/photos_videoplayer_videolayout", accessibilityNodeInfoCompat.getViewIdResourceName()))) {
                return false;
            }
        }
        return true;
    }
}
