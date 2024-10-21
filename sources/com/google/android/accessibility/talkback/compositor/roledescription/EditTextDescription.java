package com.google.android.accessibility.talkback.compositor.roledescription;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.CompositorUtils;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditTextDescription implements RoleDescription {
    private final ExecutionList.RunnableExecutorPair imageContents$ar$class_merging$ar$class_merging;
    private final /* synthetic */ int switching_field;

    public EditTextDescription(ExecutionList.RunnableExecutorPair runnableExecutorPair, int i) {
        this.switching_field = i;
        this.imageContents$ar$class_merging$ar$class_merging = runnableExecutorPair;
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final CharSequence nodeName(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        Locale locale = globalVariables.userPreferredLocale;
                        if (locale == null) {
                            locale = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
                        }
                        CharSequence nodeContentDescription = SpannableUtils$NonCopyableTextSpan.getNodeContentDescription(accessibilityNodeInfoCompat, context, locale);
                        if (TextUtils.isEmpty(nodeContentDescription)) {
                            if (!TextUtils.isEmpty(SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, locale))) {
                                return SpannableUtils$NonCopyableTextSpan.getNodeText(accessibilityNodeInfoCompat, context, locale);
                            }
                            return SpannableUtils$NonCopyableTextSpan.getNodeLabelText$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, this.imageContents$ar$class_merging$ar$class_merging);
                        }
                        return nodeContentDescription;
                    }
                    return SpannableUtils$NonCopyableTextSpan.getNodeTextOrLabelDescription$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, this.imageContents$ar$class_merging$ar$class_merging, globalVariables);
                }
                return SpannableUtils$NonCopyableTextSpan.getNodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, this.imageContents$ar$class_merging$ar$class_merging, globalVariables);
            }
            return SpannableUtils$NonCopyableTextSpan.getNodeTextOrLabelDescription$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, this.imageContents$ar$class_merging$ar$class_merging, globalVariables);
        }
        ArrayList arrayList = new ArrayList();
        Locale locale2 = globalVariables.userPreferredLocale;
        if (locale2 == null) {
            locale2 = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
        }
        ExecutionList.RunnableExecutorPair runnableExecutorPair = this.imageContents$ar$class_merging$ar$class_merging;
        if (accessibilityNodeInfoCompat.isPassword()) {
            CharSequence nodeContentDescription2 = SpannableUtils$NonCopyableTextSpan.getNodeContentDescription(accessibilityNodeInfoCompat, context, locale2);
            if (!TextUtils.isEmpty(nodeContentDescription2)) {
                arrayList.add(nodeContentDescription2);
            } else {
                arrayList.add(context.getString(R.string.value_password));
            }
            CharSequence nodeText = SpannableUtils$NonCopyableTextSpan.getNodeText(accessibilityNodeInfoCompat, context, locale2);
            if (!TextUtils.isEmpty(nodeText)) {
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, 131072)) {
                    int length = nodeText.length();
                    arrayList.add(context.getResources().getQuantityString(R.plurals.template_password_character_count, length, Integer.valueOf(length)));
                } else {
                    arrayList.add(nodeText);
                }
            } else {
                arrayList.add(SpannableUtils$NonCopyableTextSpan.getNodeLabelText$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, runnableExecutorPair));
            }
        } else {
            CharSequence nodeText2 = SpannableUtils$NonCopyableTextSpan.getNodeText(accessibilityNodeInfoCompat, context, locale2);
            CharSequence nodeContentDescription3 = SpannableUtils$NonCopyableTextSpan.getNodeContentDescription(accessibilityNodeInfoCompat, context, locale2);
            CharSequence nodeLabelText$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.getNodeLabelText$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, runnableExecutorPair);
            if (!TextUtils.isEmpty(nodeText2)) {
                arrayList.add(nodeText2);
            } else if (!TextUtils.isEmpty(nodeContentDescription3)) {
                arrayList.add(nodeContentDescription3);
            } else if (!TextUtils.isEmpty(nodeLabelText$ar$class_merging$ar$class_merging)) {
                arrayList.add(nodeLabelText$ar$class_merging$ar$class_merging);
            }
        }
        return CompositorUtils.joinCharSequences$ar$ds(arrayList, CompositorUtils.separator);
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final CharSequence nodeRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return SpannableUtils$NonCopyableTextSpan.defaultRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
                    }
                    if (!globalVariables.speakRoles) {
                        return "";
                    }
                    CharSequence nodeRoleDescription = SpannableUtils$NonCopyableTextSpan.getNodeRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
                    if (TextUtils.isEmpty(nodeRoleDescription)) {
                        return SpannableUtils$IdentifierSpan.wrapWithNonCopyableTextSpan(context.getString(R.string.value_pager_page));
                    }
                    return nodeRoleDescription;
                }
                if (!globalVariables.speakRoles || accessibilityNodeInfoCompat == null) {
                    return "";
                }
                CharSequence nodeRoleDescription2 = SpannableUtils$NonCopyableTextSpan.getNodeRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
                if (TextUtils.isEmpty(nodeRoleDescription2)) {
                    if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 6 && AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, 4)) {
                        if (!accessibilityNodeInfoCompat.isAccessibilityFocused()) {
                            return "";
                        }
                        return context.getString(R.string.value_image);
                    }
                    return SpannableUtils$NonCopyableTextSpan.getNodeRoleName(accessibilityNodeInfoCompat, context);
                }
                return nodeRoleDescription2;
            }
            return SpannableUtils$NonCopyableTextSpan.defaultRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
        }
        return SpannableUtils$NonCopyableTextSpan.defaultRoleDescription(accessibilityNodeInfoCompat, context, globalVariables);
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final CharSequence nodeState(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        Locale locale = globalVariables.userPreferredLocale;
                        if (locale == null) {
                            locale = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
                        }
                        CharSequence nodeStateDescription = SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, locale);
                        if (TextUtils.isEmpty(nodeStateDescription)) {
                            CharSequence nodeText = SpannableUtils$NonCopyableTextSpan.getNodeText(accessibilityNodeInfoCompat, context, locale);
                            if (TextUtils.isEmpty(nodeText)) {
                                if (accessibilityNodeInfoCompat.isChecked()) {
                                    return context.getString(R.string.value_on);
                                }
                                return context.getString(R.string.value_off);
                            }
                            return nodeText;
                        }
                        return nodeStateDescription;
                    }
                    Locale locale2 = globalVariables.userPreferredLocale;
                    if (locale2 == null) {
                        locale2 = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
                    }
                    CharSequence nodeStateDescription2 = SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, locale2);
                    if (TextUtils.isEmpty(nodeStateDescription2)) {
                        return SpannableUtils$NonCopyableTextSpan.getPagerIndexCount(accessibilityEvent, context, globalVariables);
                    }
                    return nodeStateDescription2;
                }
                CharSequence nodeStateDescription3 = SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, globalVariables.getPreferredLocaleByNode(accessibilityNodeInfoCompat));
                if (TextUtils.isEmpty(nodeStateDescription3)) {
                    if (TextUtils.isEmpty(SpannableUtils$NonCopyableTextSpan.getNodeTextOrLabelOrIdDescription$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, context, this.imageContents$ar$class_merging$ar$class_merging, globalVariables))) {
                        return context.getString(R.string.value_unlabelled);
                    }
                    return "";
                }
                return nodeStateDescription3;
            }
            Locale locale3 = globalVariables.userPreferredLocale;
            if (locale3 == null) {
                locale3 = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
            }
            return SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, locale3);
        }
        ArrayList arrayList = new ArrayList();
        Locale locale4 = globalVariables.userPreferredLocale;
        if (locale4 == null) {
            locale4 = AccessibilityNodeInfoUtils.getLocalesByNode(accessibilityNodeInfoCompat);
        }
        CharSequence nodeStateDescription4 = SpannableUtils$NonCopyableTextSpan.getNodeStateDescription(accessibilityNodeInfoCompat, context, locale4);
        if (!TextUtils.isEmpty(nodeStateDescription4)) {
            arrayList.add(nodeStateDescription4);
        }
        if (accessibilityNodeInfoCompat.isFocused()) {
            AccessibilityService accessibilityService = globalVariables.mService;
            if (SpannableUtils$IdentifierSpan.isInputWindowOnScreen(accessibilityService) || accessibilityService.getResources().getConfiguration().hardKeyboardHidden == 1) {
                arrayList.add(context.getString(R.string.value_edit_box_editing));
            }
        }
        if (globalVariables.mSelectionModeActive) {
            arrayList.add(context.getString(R.string.notification_type_selection_mode_on));
        }
        return CompositorUtils.joinCharSequences$ar$ds(arrayList, CompositorUtils.separator);
    }

    @Override // com.google.android.accessibility.talkback.compositor.roledescription.RoleDescription
    public final /* synthetic */ boolean shouldIgnoreDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return false;
    }
}
