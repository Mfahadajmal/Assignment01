package com.google.android.accessibility.talkback.compositor.roledescription;

import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SeekBarDescription implements RoleDescription {
    private final ExecutionList.RunnableExecutorPair imageContents$ar$class_merging$ar$class_merging;

    public SeekBarDescription(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.imageContents$ar$class_merging$ar$class_merging = runnableExecutorPair;
    }

    public static CharSequence stateDescription(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        float current;
        CharSequence nodeStateDescription = SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, globalVariables.getPreferredLocaleByNode(accessibilityNodeInfoCompat));
        if (TextUtils.isEmpty(nodeStateDescription)) {
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat rangeInfo$ar$class_merging = accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging();
            if (rangeInfo$ar$class_merging == null) {
                current = 0.0f;
            } else {
                current = rangeInfo$ar$class_merging.getCurrent();
            }
            if (rangeInfo$ar$class_merging != null) {
                int type = rangeInfo$ar$class_merging.getType();
                if (type != 0) {
                    if (type != 1) {
                        if (type == 2) {
                            return context.getString(R.string.template_percent, String.valueOf(current));
                        }
                    } else {
                        return context.getString(R.string.template_value, String.valueOf(current));
                    }
                } else {
                    return context.getString(R.string.template_value, String.valueOf((int) current));
                }
            }
            if (accessibilityEvent.getItemCount() > 0) {
                return context.getString(R.string.template_percent, String.valueOf(AccessibilityNodeInfoUtils.roundForProgressPercent(AccessibilityNodeInfoUtils.getProgressPercent(accessibilityNodeInfoCompat))));
            }
            return "";
        }
        return nodeStateDescription;
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final CharSequence nodeName(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        return SpannableUtils$NonCopyableTextSpan.getNodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, this.imageContents$ar$class_merging$ar$class_merging, globalVariables);
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final CharSequence nodeRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        return SpannableUtils$NonCopyableTextSpan.defaultRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final CharSequence nodeState(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        return stateDescription(accessibilityEvent, accessibilityNodeInfoCompat, context, globalVariables);
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final boolean shouldIgnoreDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        boolean isAccessibilityFocused = accessibilityNodeInfoCompat.isAccessibilityFocused();
        int liveRegion = accessibilityNodeInfoCompat.getLiveRegion();
        if (!accessibilityNodeInfoCompat.isAccessibilityFocused() && liveRegion == 0) {
            sb.append("ignore description");
            z = true;
            liveRegion = 0;
        } else {
            z = false;
        }
        Integer valueOf = Integer.valueOf(accessibilityNodeInfoCompat.hashCode());
        sb.append(String.format(", isAccessibilityFocused=%s", Boolean.valueOf(isAccessibilityFocused)));
        sb.append(String.format(", nodeLiveRegion=%s", Integer.valueOf(liveRegion)));
        LogUtils.v("SeekBarDescription", String.format("    shouldIgnoreDescription: (%s)  %s", valueOf, sb), new Object[0]);
        return z;
    }
}
