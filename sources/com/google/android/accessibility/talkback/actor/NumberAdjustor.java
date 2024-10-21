package com.google.android.accessibility.talkback.actor;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NumberAdjustor {
    private final Object NumberAdjustor$ar$accessibilityFocusMonitor;
    private final Context context;
    public Pipeline.FeedbackReturner pipeline;

    public NumberAdjustor(Context context, Object obj) {
        this.context = context;
        this.NumberAdjustor$ar$accessibilityFocusMonitor = obj;
    }

    public static final boolean performAction$ar$ds$c95c0523_0(Feedback.WebAction webAction, Performance.EventId eventId) {
        return SpannableUtils$IdentifierSpan.performAction(webAction.target, webAction.nodeAction, webAction.nodeActionArgs, eventId);
    }

    private final void speakTTSText(CharSequence charSequence, Performance.EventId eventId) {
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mQueueMode = 1;
        speakOptions.mFlags = 4096;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(charSequence, speakOptions));
    }

    public final boolean adjustValue(boolean z) {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat rangeInfo$ar$class_merging;
        AccessibilityNodeInfoCompat supportedAdjustableNode = ((AccessibilityFocusMonitor) this.NumberAdjustor$ar$accessibilityFocusMonitor).getSupportedAdjustableNode();
        if (supportedAdjustableNode == null) {
            return false;
        }
        if (SpannableUtils$IdentifierSpan.getRole(supportedAdjustableNode) == 10 && (rangeInfo$ar$class_merging = supportedAdjustableNode.getRangeInfo$ar$class_merging()) != null) {
            CharSequence stateDescription = supportedAdjustableNode.getStateDescription();
            if (z && rangeInfo$ar$class_merging.getCurrent() <= rangeInfo$ar$class_merging.getMin()) {
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                if (TextUtils.isEmpty(stateDescription)) {
                    stateDescription = this.context.getString(R.string.template_seekbar_range, 0);
                }
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(stateDescription, new SpeechController.SpeakOptions()));
                return false;
            }
            if (!z && rangeInfo$ar$class_merging.getCurrent() >= rangeInfo$ar$class_merging.getMax()) {
                Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
                Logger logger2 = Performance.DEFAULT_LOGGER;
                if (TextUtils.isEmpty(stateDescription)) {
                    stateDescription = this.context.getString(R.string.template_seekbar_range, 100);
                }
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, Feedback.speech(stateDescription, new SpeechController.SpeakOptions()));
                return false;
            }
        }
        if (z) {
            if (AccessibilityNodeInfoUtils.supportsAction(supportedAdjustableNode, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId())) {
                Pipeline.FeedbackReturner feedbackReturner3 = this.pipeline;
                Logger logger3 = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner3, (Performance.EventId) null, Feedback.nodeAction(supportedAdjustableNode, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId()));
                return true;
            }
        } else if (AccessibilityNodeInfoUtils.supportsAction(supportedAdjustableNode, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId())) {
            Pipeline.FeedbackReturner feedbackReturner4 = this.pipeline;
            Logger logger4 = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner4, (Performance.EventId) null, Feedback.nodeAction(supportedAdjustableNode, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId()));
            return true;
        }
        LogUtils.d("NumberAdjustor", "adjustValue does not happen", new Object[0]);
        return false;
    }

    public final boolean navigateToHtmlElement(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, Performance.EventId eventId) {
        String str;
        char c;
        int i;
        int i2;
        String string;
        int i3 = navigationAction.targetType;
        if (i3 != 0) {
            switch (i3) {
                case 65641:
                    str = "LIST";
                    break;
                case 65642:
                    str = "BUTTON";
                    break;
                case 65643:
                    str = "CHECKBOX";
                    break;
                case 65644:
                    str = "LANDMARK";
                    break;
                case 65645:
                    str = "TEXT_FIELD";
                    break;
                case 65646:
                    str = "FOCUSABLE";
                    break;
                case 65647:
                    str = "H1";
                    break;
                case 65648:
                    str = "H2";
                    break;
                case 65649:
                    str = "H3";
                    break;
                case 65650:
                    str = "H4";
                    break;
                case 65651:
                    str = "H5";
                    break;
                case 65652:
                    str = "H6";
                    break;
                case 65653:
                    str = "GRAPHIC";
                    break;
                case 65654:
                    str = "LIST_ITEM";
                    break;
                case 65655:
                    str = "TABLE";
                    break;
                case 65656:
                    str = "COMBOBOX";
                    break;
                default:
                    switch (i3) {
                        case 262145:
                            str = "LINK";
                            break;
                        case 262146:
                            str = "CONTROL";
                            break;
                        case 262147:
                            str = "HEADING";
                            break;
                        default:
                            str = null;
                            break;
                    }
            }
        } else {
            str = "";
        }
        if (str == null) {
            LogUtils.w("WebActor", "Cannot navigate to HTML target: invalid targetType=%d.", Integer.valueOf(i3));
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("ACTION_ARGUMENT_HTML_ELEMENT_STRING", str);
        if (!WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
            LogUtils.w("WebActor", "Cannot navigate to HTML target: current pivot is not a web element.", new Object[0]);
            if (navigationAction.inputMode == 1) {
                speakTTSText(((AccessibilityService) this.context).getString(R.string.keycombo_announce_shortcut_not_supported), eventId);
            }
            return false;
        }
        Context context = this.context;
        int i4 = navigationAction.searchDirection;
        if (i4 == 0) {
            c = 0;
        } else if (TraversalStrategyUtils.getLogicalDirection(i4, SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(context)) == 1) {
            c = 1;
        } else {
            c = 65535;
        }
        if (c == 0) {
            LogUtils.w("WebActor", "Cannot navigate to HTML target: invalid direction.", new Object[0]);
            return false;
        }
        if (c == 1) {
            i = 1024;
        } else {
            i = 2048;
        }
        if (!SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, i, bundle, eventId)) {
            if (c == 1) {
                i2 = R.string.end_of_page;
            } else {
                i2 = R.string.start_of_page;
            }
            Context context2 = this.context;
            int i5 = navigationAction.targetType;
            if (i5 != 0) {
                if (i5 != 201) {
                    if (i5 != 202) {
                        switch (i5) {
                            case 65641:
                                string = context2.getString(R.string.display_name_list);
                                break;
                            case 65642:
                                string = context2.getString(R.string.display_name_button);
                                break;
                            case 65643:
                                string = context2.getString(R.string.display_name_checkbox);
                                break;
                            case 65644:
                                string = context2.getString(R.string.display_name_aria_landmark);
                                break;
                            case 65645:
                                string = context2.getString(R.string.display_name_edit_field);
                                break;
                            case 65646:
                                string = context2.getString(R.string.display_name_focusable_item);
                                break;
                            case 65647:
                                string = context2.getString(R.string.display_name_heading_1);
                                break;
                            case 65648:
                                string = context2.getString(R.string.display_name_heading_2);
                                break;
                            case 65649:
                                string = context2.getString(R.string.display_name_heading_3);
                                break;
                            case 65650:
                                string = context2.getString(R.string.display_name_heading_4);
                                break;
                            case 65651:
                                string = context2.getString(R.string.display_name_heading_5);
                                break;
                            case 65652:
                                string = context2.getString(R.string.display_name_heading_6);
                                break;
                            case 65653:
                                string = context2.getString(R.string.display_name_graphic);
                                break;
                            case 65654:
                                string = context2.getString(R.string.display_name_list_item);
                                break;
                            case 65655:
                                string = context2.getString(R.string.display_name_table);
                                break;
                            case 65656:
                                string = context2.getString(R.string.display_name_combobox);
                                break;
                            default:
                                switch (i5) {
                                    case 262145:
                                        string = context2.getString(R.string.display_name_link);
                                        break;
                                    case 262146:
                                        string = context2.getString(R.string.display_name_control);
                                        break;
                                    case 262147:
                                        string = context2.getString(R.string.display_name_heading);
                                        break;
                                    default:
                                        switch (i5) {
                                            case 1048577:
                                                string = context2.getString(R.string.display_name_heading);
                                                break;
                                            case 1048578:
                                                string = context2.getString(R.string.display_name_control);
                                                break;
                                            case 1048579:
                                                string = context2.getString(R.string.display_name_link);
                                                break;
                                            default:
                                                LogUtils.e("NavigationTarget", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i5, "htmlTargetToDisplayName() unhandled target type="), new Object[0]);
                                                string = "(unknown)";
                                                break;
                                        }
                                }
                        }
                    } else {
                        string = context2.getString(R.string.display_name_container);
                    }
                } else {
                    string = context2.getString(R.string.display_name_window);
                }
            } else {
                string = context2.getString(R.string.granularity_default);
            }
            speakTTSText(((AccessibilityService) context2).getString(i2, new Object[]{string}), eventId);
            return false;
        }
        Object obj = this.NumberAdjustor$ar$accessibilityFocusMonitor;
        FocusActionInfo.Builder builder = new FocusActionInfo.Builder();
        builder.sourceAction = 4;
        builder.navigationAction = navigationAction;
        ((HapticPatternParser$$ExternalSyntheticLambda1) obj).updateHistory(accessibilityNodeInfoCompat, new FocusActionInfo(builder));
        return true;
    }
}
