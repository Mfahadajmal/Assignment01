package com.google.android.accessibility.talkback.actor;

import android.text.TextUtils;
import android.widget.NumberPicker;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation;
import com.google.android.accessibility.talkback.imagecaption.CharacterCaptionRequest;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.ClassLoadingCache;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.mdi.Download$ClientFileGroup;
import com.google.common.base.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FocusActorForScreenStateChange$$ExternalSyntheticLambda0 implements Predicate {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FocusActorForScreenStateChange$$ExternalSyntheticLambda0(int i) {
        this.switching_field = i;
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        switch (this.switching_field) {
            case 0:
                return ((AccessibilityNodeInfoCompat) obj).isSelected();
            case 1:
                ((AccessibilityNodeInfoCompat) obj).getClass();
                return false;
            case 2:
                return AccessibilityNodeInfoUtils.shouldFocusNode((AccessibilityNodeInfoCompat) obj);
            case 3:
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat == null || TextUtils.isEmpty(accessibilityNodeInfoCompat.getContainerTitle())) {
                    return false;
                }
                return true;
            case 4:
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat2 == null || !ClassLoadingCache.checkInstanceOf(accessibilityNodeInfoCompat2.getClassName(), NumberPicker.class)) {
                    return false;
                }
                return true;
            case 5:
                int i = FocusProcessorForLogicalNavigation.FocusProcessorForLogicalNavigation$ar$NoOp;
                if (TextUtils.isEmpty(((AccessibilityNodeInfoCompat) obj).getPaneTitle())) {
                    return false;
                }
                return true;
            case 6:
                int i2 = FocusProcessorForLogicalNavigation.FocusProcessorForLogicalNavigation$ar$NoOp;
                if (TextUtils.isEmpty(((AccessibilityNodeInfoCompat) obj).getPaneTitle())) {
                    return false;
                }
                return true;
            case 7:
                if (SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj) == 16) {
                    return false;
                }
                return true;
            case 8:
                if (SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj) == 16) {
                    return false;
                }
                return true;
            case 9:
                int i3 = FocusProcessorForLogicalNavigation.FocusProcessorForLogicalNavigation$ar$NoOp;
                return false;
            case 10:
                int i4 = CharacterCaptionRequest.CharacterCaptionRequest$ar$NoOp;
                return true;
            case 11:
                if (SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj) != 5) {
                    return false;
                }
                return true;
            case 12:
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = (AccessibilityNodeInfoCompat) obj;
                int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat3);
                if (role != 8 && role != 5 && role != 16 && (accessibilityNodeInfoCompat3 == null || accessibilityNodeInfoCompat3.getCollectionInfo$ar$class_merging() == null)) {
                    return false;
                }
                return true;
            case 13:
                if (((AccessibilityNodeInfoCompat) obj).getPaneTitle() == null) {
                    return false;
                }
                return true;
            case 14:
                if ((((Download$ClientFileGroup) obj).bitField0_ & 2) == 0) {
                    return false;
                }
                return true;
            case 15:
                if ((((Download$ClientFileGroup) obj).bitField0_ & 1) == 0) {
                    return false;
                }
                return true;
            case 16:
                if ((((Download$ClientFileGroup) obj).bitField0_ & 4) == 0) {
                    return false;
                }
                return true;
            case 17:
                if ((((Download$ClientFileGroup) obj).bitField0_ & 8) == 0) {
                    return false;
                }
                return true;
            default:
                if ((((Download$ClientFileGroup) obj).bitField0_ & 16) == 0) {
                    return false;
                }
                return true;
        }
    }
}
