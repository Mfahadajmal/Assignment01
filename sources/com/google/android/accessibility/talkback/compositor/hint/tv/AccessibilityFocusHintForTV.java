package com.google.android.accessibility.talkback.compositor.hint.tv;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.hint.AccessibilityFocusHint;
import com.google.android.accessibility.talkback.compositor.hint.ClickableHint;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityFocusHintForTV extends AccessibilityFocusHint {
    private final NodeRoleHintForTV roleHint;

    public AccessibilityFocusHintForTV(Context context, GlobalVariables globalVariables) {
        super(context, globalVariables);
        this.roleHint = new NodeRoleHintForTV(context, globalVariables);
    }

    @Override // com.google.android.accessibility.talkback.compositor.hint.AccessibilityFocusHint
    public final CharSequence getHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CharSequence clickHint;
        StringBuilder sb = new StringBuilder();
        boolean z = this.globalVariables.usageHintEnabled;
        boolean isEnabled = accessibilityNodeInfoCompat.isEnabled();
        boolean isAccessibilityFocused = accessibilityNodeInfoCompat.isAccessibilityFocused();
        sb.append(String.format("enableUsageHint=%s", Boolean.valueOf(z)));
        sb.append(String.format(", isEnabled=%s", Boolean.valueOf(isEnabled)));
        sb.append(String.format(", isAccessibilityFocused=%s", Boolean.valueOf(isAccessibilityFocused)));
        if (z && isEnabled && isAccessibilityFocused) {
            NodeRoleHintForTV nodeRoleHintForTV = this.roleHint;
            int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat);
            if (role != 3) {
                if (role != 4) {
                    if (role != 10) {
                        if (role != 16) {
                            clickHint = nodeRoleHintForTV.clickableHint.getHint(accessibilityNodeInfoCompat);
                        } else {
                            Context context = nodeRoleHintForTV.context;
                            ClickableHint clickableHint = nodeRoleHintForTV.clickableHint;
                            if (!AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, 8192) && !AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, 4096)) {
                                clickHint = context.getString(R.string.template_hint_pager_single_page);
                            } else {
                                clickHint = clickableHint.getHint(accessibilityNodeInfoCompat);
                            }
                        }
                    } else {
                        Context context2 = nodeRoleHintForTV.context;
                        clickHint = context2.getString(R.string.template_hint_seek_control_tv, context2.getString(R.string.value_press_select));
                    }
                } else {
                    clickHint = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_25(accessibilityNodeInfoCompat, nodeRoleHintForTV.context, nodeRoleHintForTV.globalVariables, nodeRoleHintForTV.clickableHint, nodeRoleHintForTV.longClickableHint);
                }
            } else {
                clickHint = ((ClickableHintForTV) nodeRoleHintForTV.clickableHint).getClickHint(R.string.template_hint_spinner_keyboard, R.string.template_hint_spinner);
            }
            sb.append(String.format("\n    nodeRoleHint={%s}", clickHint));
            LogUtils.v("AccessibilityFocusHintForTV", "tv    getHint: %s", sb.toString());
            return clickHint;
        }
        LogUtils.v("AccessibilityFocusHintForTV", "tv    getHint: %s", sb.toString());
        return "";
    }
}
