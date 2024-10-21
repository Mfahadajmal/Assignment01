package com.google.android.accessibility.talkback;

import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface UserInterface$UserInputEventListener {
    void editTextOrSelectableTextSelected(boolean z);

    void newItemFocused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(AccessibilityNodeInfo accessibilityNodeInfo, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan);

    void touchInteractionState(boolean z);
}
