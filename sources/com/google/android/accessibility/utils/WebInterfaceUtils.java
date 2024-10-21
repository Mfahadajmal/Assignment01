package com.google.android.accessibility.utils;

import android.os.Bundle;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WebInterfaceUtils {
    public static final Filter FILTER_WEB_VIEW_CONTAINER = new Filter() { // from class: com.google.android.accessibility.utils.WebInterfaceUtils.1
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 15 && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat.getParent()) != 15) {
                return true;
            }
            return false;
        }
    };
    public static final Filter FILTER_WEB_VIEW = new Filter() { // from class: com.google.android.accessibility.utils.WebInterfaceUtils.2
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 15) {
                return true;
            }
            return false;
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SupportedHtmlNodeCollector extends Filter {
        public final ArrayList supportedTypes = new ArrayList();

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            CharSequence charSequence;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat != null && (charSequence = accessibilityNodeInfoCompat.getExtras().getCharSequence("ACTION_ARGUMENT_HTML_ELEMENT_STRING_VALUES")) != null) {
                Collections.addAll(this.supportedTypes, charSequence.toString().split(","));
                return true;
            }
            return false;
        }
    }

    public static boolean containsImage(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Bundle extras = accessibilityNodeInfoCompat.getExtras();
        if (extras != null && "true".equals(extras.getString("AccessibilityNodeInfo.hasImage"))) {
            return true;
        }
        return false;
    }

    public static String[] getSupportedHtmlElements(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        SupportedHtmlNodeCollector supportedHtmlNodeCollector = new SupportedHtmlNodeCollector();
        AccessibilityNodeInfoUtils.isOrHasMatchingAncestor(accessibilityNodeInfoCompat, supportedHtmlNodeCollector);
        if (supportedHtmlNodeCollector.supportedTypes.isEmpty()) {
            return null;
        }
        return (String[]) supportedHtmlNodeCollector.supportedTypes.toArray(new String[0]);
    }

    public static boolean isWebContainer(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String str;
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (!supportsWebActions(accessibilityNodeInfoCompat)) {
            if (accessibilityNodeInfoCompat.getPackageName() != null) {
                str = accessibilityNodeInfoCompat.getPackageName().toString();
            } else {
                str = "";
            }
            if (!str.startsWith("org.mozilla.")) {
                return false;
            }
            return true;
        }
        return true;
    }

    public static boolean supportsWebActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return AccessibilityNodeInfoUtils.supportsAnyAction(accessibilityNodeInfoCompat, 1024, 2048);
    }
}
