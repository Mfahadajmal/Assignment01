package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.EditTextActionHistory;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.InternalSubchannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextEditActor {
    public static final SpeechController.SpeakOptions SPEAK_OPTIONS;
    private final ClipboardManager clipboard;
    private final EditTextActionHistory editTextActionHistory;
    public Pipeline.FeedbackReturner pipeline;
    public final AccessibilityService service;
    private final HapticPatternParser$$ExternalSyntheticLambda1 stateReader$ar$class_merging$ar$class_merging$ar$class_merging;
    private final InternalSubchannel.Index textCursorTracker$ar$class_merging;

    static {
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mQueueMode = 3;
        speakOptions.mFlags = 30;
        SPEAK_OPTIONS = speakOptions;
    }

    public TextEditActor(AccessibilityService accessibilityService, EditTextActionHistory editTextActionHistory, InternalSubchannel.Index index, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, ClipboardManager clipboardManager) {
        this.service = accessibilityService;
        this.editTextActionHistory = editTextActionHistory;
        this.textCursorTracker$ar$class_merging = index;
        this.stateReader$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.clipboard = clipboardManager;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
    
        r0 = r1.service.getInputMethod();
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000f, code lost:
    
        r0 = r0.getCurrentInputConnection();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean performSelectText(int r2, int r3) {
        /*
            r1 = this;
            boolean r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.isAtLeastT()
            if (r0 != 0) goto L7
            goto L1a
        L7:
            android.accessibilityservice.AccessibilityService r0 = r1.service
            android.accessibilityservice.InputMethod r0 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m(r0)
            if (r0 == 0) goto L1a
            android.accessibilityservice.InputMethod$AccessibilityInputConnection r0 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m(r0)
            if (r0 == 0) goto L1a
            org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m(r0, r2, r3)
            r2 = 1
            return r2
        L1a:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.TextEditActor.performSelectText(int, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean copy(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r6, com.google.android.accessibility.utils.Performance.EventId r7) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            java.lang.CharSequence r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getNodeText(r6)
            java.lang.CharSequence r2 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getSelectedNodeText(r6)
            boolean r3 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isTextSelectable(r6)
            r4 = 1
            if (r3 == 0) goto L20
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L20
            r1 = 16384(0x4000, float:2.2959E-41)
            boolean r6 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.performAction(r6, r1, r7)
            goto L33
        L20:
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            r2 = 0
            if (r6 != 0) goto L32
            android.content.ClipData r6 = android.content.ClipData.newPlainText(r2, r1)
            android.content.ClipboardManager r2 = r5.clipboard
            r2.setPrimaryClip(r6)
            r6 = r4
            goto L34
        L32:
            r6 = r0
        L33:
            r1 = r2
        L34:
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r2 = r5.pipeline
            if (r1 != 0) goto L42
            android.accessibilityservice.AccessibilityService r0 = r5.service
            r1 = 2132083195(0x7f1501fb, float:1.9806525E38)
            java.lang.String r0 = r0.getString(r1)
            goto L53
        L42:
            android.accessibilityservice.AccessibilityService r3 = r5.service
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r0] = r1
            r0 = 2132086238(0x7f150dde, float:1.9812697E38)
            java.lang.String r0 = r3.getString(r0, r4)
        L53:
            com.google.android.accessibility.utils.output.SpeechController$SpeakOptions r1 = com.google.android.accessibility.talkback.actor.TextEditActor.SPEAK_OPTIONS
            com.google.android.accessibility.talkback.Feedback$Part$Builder r0 = com.google.android.accessibility.talkback.Feedback.speech(r0, r1)
            com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r2, r7, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.TextEditActor.copy(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }

    public final boolean correctTypo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CharSequence charSequence, AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion, Performance.EventId eventId) {
        CharSequence concat;
        if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4 && !TextUtils.isEmpty(charSequence) && spellingSuggestion != null) {
            CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
            if (!TextUtils.isEmpty(text) && (text instanceof Spanned)) {
                int start = spellingSuggestion.start();
                int end = spellingSuggestion.end();
                if (start != -1 && end != -1) {
                    if (start == 0) {
                        concat = TextUtils.concat(charSequence, text.subSequence(end, text.length()));
                    } else {
                        concat = TextUtils.concat(text.subSequence(0, start), charSequence, text.subSequence(end, text.length()));
                    }
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", concat.toString());
                    this.editTextActionHistory.setTextStartTime = SystemClock.uptimeMillis();
                    boolean performAction = SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 2097152, bundle, eventId);
                    this.editTextActionHistory.setTextFinishTime = SystemClock.uptimeMillis();
                    if (performAction) {
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(this.service.getString(R.string.text_replaced_feedback), SPEAK_OPTIONS));
                    }
                    return moveCursor(accessibilityNodeInfoCompat, start + charSequence.length(), eventId);
                }
                LogUtils.d("TextEditActor", "The suggestionSpan is not in the currentText.", new Object[0]);
            }
        }
        return false;
    }

    public final boolean cursorToBeginning$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        if (accessibilityNodeInfoCompat == null || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4) {
            return false;
        }
        boolean moveCursor = moveCursor(accessibilityNodeInfoCompat, 0, eventId);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(this.service.getString(R.string.notification_type_beginning_of_field), SPEAK_OPTIONS));
        return moveCursor;
    }

    public final boolean cursorToEnd$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        boolean z = false;
        if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4) {
            CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
            if (text != null) {
                z = moveCursor(accessibilityNodeInfoCompat, text.length(), eventId);
            }
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(this.service.getString(R.string.notification_type_end_of_field), SPEAK_OPTIONS));
        }
        return z;
    }

    public final boolean cut(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        String string;
        if (accessibilityNodeInfoCompat == null || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4) {
            return false;
        }
        CharSequence selectedNodeText = AccessibilityNodeInfoUtils.getSelectedNodeText(accessibilityNodeInfoCompat);
        this.editTextActionHistory.mCutStartTime = SystemClock.uptimeMillis();
        boolean performAction = SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 65536, eventId);
        this.editTextActionHistory.mCutFinishTime = SystemClock.uptimeMillis();
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        if (selectedNodeText != null) {
            string = this.service.getString(R.string.template_text_cut, new Object[]{selectedNodeText.toString()});
        } else {
            string = this.service.getString(R.string.cannot_cut_feedback);
        }
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, Feedback.speech(string, SPEAK_OPTIONS));
        return performAction;
    }

    public final boolean delete(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        String string;
        if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4) {
            CharSequence nodeText = AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat);
            CharSequence selectedNodeText = AccessibilityNodeInfoUtils.getSelectedNodeText(accessibilityNodeInfoCompat);
            Pair selectionIndexesSafe = AccessibilityNodeInfoUtils.getSelectionIndexesSafe(accessibilityNodeInfoCompat);
            int intValue = ((Integer) selectionIndexesSafe.first).intValue();
            CharSequence concat = TextUtils.concat(nodeText.subSequence(0, intValue), nodeText.subSequence(((Integer) selectionIndexesSafe.second).intValue(), nodeText.length()));
            Bundle bundle = new Bundle();
            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", concat);
            if (SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 2097152, bundle, eventId)) {
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                if (selectedNodeText == null) {
                    string = this.service.getString(R.string.cannot_delete_feedback);
                } else {
                    string = this.service.getString(R.string.template_text_removed, new Object[]{selectedNodeText.toString()});
                }
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, Feedback.speech(string, SPEAK_OPTIONS));
                return moveCursor(accessibilityNodeInfoCompat, intValue, eventId);
            }
        }
        return false;
    }

    public final boolean endSelect(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        String string;
        if (accessibilityNodeInfoCompat == null || !AccessibilityNodeInfoUtils.isTextSelectable(accessibilityNodeInfoCompat)) {
            return false;
        }
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.focusDirection(Feedback.FocusDirection.Action.SELECTION_MODE_OFF));
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(this.service.getString(R.string.notification_type_selection_mode_off), SPEAK_OPTIONS));
        CharSequence subsequenceSafe = AccessibilityNodeInfoUtils.subsequenceSafe(AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat), accessibilityNodeInfoCompat.getTextSelectionStart(), accessibilityNodeInfoCompat.getTextSelectionEnd());
        if (TextUtils.isEmpty(subsequenceSafe)) {
            string = this.service.getString(R.string.template_no_text_selected);
        } else {
            string = this.service.getString(R.string.template_announce_selected_text, new Object[]{subsequenceSafe});
        }
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mFlags = 28;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(string, speakOptions));
        return true;
    }

    public final boolean insert(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CharSequence charSequence, Performance.EventId eventId) {
        CharSequence charSequence2;
        if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4) {
            Pair selectionIndexesSafe = AccessibilityNodeInfoUtils.getSelectionIndexesSafe(accessibilityNodeInfoCompat);
            int intValue = ((Integer) selectionIndexesSafe.first).intValue();
            int intValue2 = ((Integer) selectionIndexesSafe.second).intValue();
            CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
            if (true == TextUtils.equals(text, accessibilityNodeInfoCompat.getHintText())) {
                text = null;
            }
            if (text == null) {
                charSequence2 = "null";
            } else {
                charSequence2 = text;
            }
            LogUtils.v("TextEditActor", "insert() currentText=\"%s\"", charSequence2);
            if (text == null) {
                text = "";
            }
            CharSequence concat = TextUtils.concat(text.subSequence(0, intValue), charSequence, text.subSequence(intValue2, text.length()));
            Bundle bundle = new Bundle();
            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", concat);
            if (SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 2097152, bundle, eventId)) {
                return moveCursor(accessibilityNodeInfoCompat, intValue + charSequence.length(), eventId);
            }
        }
        return false;
    }

    public final boolean moveCursor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Performance.EventId eventId) {
        boolean z = false;
        if (accessibilityNodeInfoCompat == null || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4) {
            return false;
        }
        Bundle bundle = new Bundle();
        InternalSubchannel.Index index = this.textCursorTracker$ar$class_merging;
        index.groupIndex = i;
        index.addressIndex = i;
        CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
        if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, 131072)) {
            int textSelectionStart = accessibilityNodeInfoCompat.getTextSelectionStart();
            if (SpannableUtils$IdentifierSpan.isAtLeastT() && this.stateReader$ar$class_merging$ar$class_merging$ar$class_merging.isSelectionModeActive() && i != textSelectionStart) {
                z = performSelectText(textSelectionStart, i);
            }
            if (!z) {
                bundle.putInt("ACTION_ARGUMENT_SELECTION_START_INT", i);
                bundle.putInt("ACTION_ARGUMENT_SELECTION_END_INT", i);
                return SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 131072, bundle, eventId);
            }
            return z;
        }
        if (i == 0) {
            bundle.putInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT", 16);
            return SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 512, bundle, eventId);
        }
        if (text == null || i != text.length()) {
            return false;
        }
        bundle.putInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT", 16);
        return SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 256, bundle, eventId);
    }

    public final boolean paste(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4) {
            this.editTextActionHistory.mPasteStartTime = SystemClock.uptimeMillis();
            boolean performAction = SpannableUtils$IdentifierSpan.performAction(accessibilityNodeInfoCompat, 32768, eventId);
            this.editTextActionHistory.mPasteFinishTime = SystemClock.uptimeMillis();
            if (!performAction) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(this.service.getString(R.string.cannot_paste_feedback), SPEAK_OPTIONS));
            }
            return performAction;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
    
        if (com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.performAction(r6, 131072, r2, r7) == false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean selectAll(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r6, com.google.android.accessibility.utils.Performance.EventId r7) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L85
            boolean r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isTextSelectable(r6)
            if (r1 != 0) goto Lb
            goto L85
        Lb:
            java.lang.CharSequence r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getText(r6)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L85
            com.google.android.accessibility.utils.output.EditTextActionHistory r2 = r5.editTextActionHistory
            long r3 = android.os.SystemClock.uptimeMillis()
            r2.mSelectAllStartTime = r3
            boolean r2 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.isAtLeastT()
            if (r2 == 0) goto L3f
            int r2 = r1.length()
            boolean r2 = r5.performSelectText(r0, r2)
            if (r2 == 0) goto L3f
            com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1 r1 = r5.stateReader$ar$class_merging$ar$class_merging$ar$class_merging
            boolean r1 = r1.isSelectionModeActive()
            if (r1 != 0) goto L5b
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r1 = r5.pipeline
            com.google.android.accessibility.talkback.Feedback$Part$Builder r2 = com.google.android.accessibility.talkback.Feedback.selectionModeOn(r6)
            com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r1, r7, r2)
            goto L5b
        L3f:
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.String r3 = "ACTION_ARGUMENT_SELECTION_START_INT"
            r2.putInt(r3, r0)
            int r1 = r1.length()
            java.lang.String r3 = "ACTION_ARGUMENT_SELECTION_END_INT"
            r2.putInt(r3, r1)
            r1 = 131072(0x20000, float:1.83671E-40)
            boolean r1 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.performAction(r6, r1, r2, r7)
            if (r1 != 0) goto L5b
            goto L85
        L5b:
            com.google.android.accessibility.utils.output.EditTextActionHistory r1 = r5.editTextActionHistory
            long r2 = android.os.SystemClock.uptimeMillis()
            r1.mSelectAllFinishTime = r2
            com.google.android.accessibility.talkback.Pipeline$FeedbackReturner r1 = r5.pipeline
            android.accessibilityservice.AccessibilityService r2 = r5.service
            java.lang.CharSequence r6 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getText(r6)
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r0] = r6
            r6 = 2132086181(0x7f150da5, float:1.9812582E38)
            java.lang.String r6 = r2.getString(r6, r4)
            java.lang.CharSequence r6 = com.google.android.accessibility.utils.output.SpeechCleanupUtils.cleanUp(r2, r6)
            com.google.android.accessibility.utils.output.SpeechController$SpeakOptions r0 = com.google.android.accessibility.talkback.actor.TextEditActor.SPEAK_OPTIONS
            com.google.android.accessibility.talkback.Feedback$Part$Builder r6 = com.google.android.accessibility.talkback.Feedback.speech(r6, r0)
            com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r1, r7, r6)
            return r3
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.TextEditActor.selectAll(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }
}
