package com.google.android.accessibility.utils.input;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.TtsSpan;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.VoiceActionMonitor;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.monitor.InputModeTracker;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.InternalSubchannel;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.List;
import java.util.regex.Pattern;
import org.chromium.net.impl.CronetLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextEventInterpreter {
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{Punct}");
    public final HapticPatternParser$$ExternalSyntheticLambda1 actionHistory$ar$class_merging$ar$class_merging;
    public final SpannableUtils$NonCopyableTextSpan actorStateProvider$ar$class_merging$ar$class_merging$ar$class_merging;
    public final TextEventFilter filter;
    public final InputModeTracker inputModeTracker;
    public final Context mContext;
    public final CronetLogger.CronetEngineBuilderInitializedInfo mHistory$ar$class_merging;
    public final SpannableUtils$NonCopyableTextSpan preferenceProvider$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 selectionStateReader$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final InternalSubchannel.Index textCursorTracker$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface InterpretationConsumer {
        void accept$ar$class_merging$ar$class_merging$d1a82255_0$ar$class_merging(OptionalMethod optionalMethod);
    }

    public TextEventInterpreter(Context context, InternalSubchannel.Index index, InputModeTracker inputModeTracker, CronetLogger.CronetEngineBuilderInitializedInfo cronetEngineBuilderInitializedInfo, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan2, VoiceActionMonitor voiceActionMonitor, TextEventFilter textEventFilter) {
        this.mContext = context;
        this.textCursorTracker$ar$class_merging = index;
        TalkBackService.AnonymousClass2 anonymousClass2 = (TalkBackService.AnonymousClass2) spannableUtils$NonCopyableTextSpan;
        this.selectionStateReader$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = (HapticPatternParser$$ExternalSyntheticLambda1) anonymousClass2.val$directionNavigationActor.DirectionNavigationActor$ar$state;
        this.inputModeTracker = inputModeTracker;
        this.mHistory$ar$class_merging = cronetEngineBuilderInitializedInfo;
        this.actionHistory$ar$class_merging$ar$class_merging = anonymousClass2.this$0.editTextActionHistory.provider$ar$class_merging$ar$class_merging;
        this.actorStateProvider$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.preferenceProvider$ar$class_merging = spannableUtils$NonCopyableTextSpan2;
        this.filter = textEventFilter;
        textEventFilter.voiceActionDelegate$ar$class_merging = voiceActionMonitor;
    }

    public static boolean areInvalidIndices(CharSequence charSequence, int i, int i2) {
        if (i >= 0 && i2 <= charSequence.length() && i < i2) {
            return false;
        }
        return true;
    }

    public static CharSequence getAddedText(AccessibilityEvent accessibilityEvent) {
        List<CharSequence> text = accessibilityEvent.getText();
        if (text != null && text.size() <= 1) {
            if (text.isEmpty()) {
                return "";
            }
            CharSequence charSequence = text.get(0);
            if (charSequence == null) {
                LogUtils.w("TextEventInterpreter", "getAddedText: First text entry was null", new Object[0]);
                return null;
            }
            int fromIndex = accessibilityEvent.getFromIndex();
            int addedCount = accessibilityEvent.getAddedCount() + fromIndex;
            if (areInvalidIndices(charSequence, fromIndex, addedCount)) {
                LogUtils.w("TextEventInterpreter", "getAddedText: Invalid indices (%d,%d) for \"%s\"", Integer.valueOf(fromIndex), Integer.valueOf(addedCount), charSequence);
                return "";
            }
            return getSubsequenceWithSpans(charSequence, fromIndex, addedCount);
        }
        LogUtils.w("TextEventInterpreter", "getAddedText: Text list was null or bad size", new Object[0]);
        return null;
    }

    public static CharSequence getEventText(AccessibilityEvent accessibilityEvent) {
        List<CharSequence> text = accessibilityEvent.getText();
        if (text.isEmpty()) {
            return "";
        }
        return text.get(0);
    }

    public static CharSequence getSubsequenceWithSpans(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i < 0 || charSequence.length() < i2 || i2 < i) {
            return null;
        }
        SpannableString valueOf = SpannableString.valueOf(charSequence);
        CharSequence subSequence = charSequence.subSequence(i, i2);
        SpannableString valueOf2 = SpannableString.valueOf(subSequence);
        for (TtsSpan ttsSpan : (TtsSpan[]) valueOf2.getSpans(0, subSequence.length(), TtsSpan.class)) {
            if (valueOf.getSpanStart(ttsSpan) < i || i2 < valueOf.getSpanEnd(ttsSpan)) {
                valueOf2.removeSpan(ttsSpan);
            }
        }
        return subSequence;
    }

    public static boolean isJunkyCharacterReplacedByBulletInUnlockPinEntry(AccessibilityEvent accessibilityEvent) {
        if (SpannableUtils$IdentifierSpan.isAtLeastP() && accessibilityEvent.getAddedCount() == 1 && accessibilityEvent.getRemovedCount() == 1) {
            return AccessibilityNodeInfoUtils.isPinEntry(AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource()));
        }
        return false;
    }

    public static boolean isPunctuation(char c) {
        return PUNCTUATION_PATTERN.matcher(Character.toString(c)).matches();
    }

    public static boolean isWhiteSpace(char c) {
        if (!Character.isWhitespace(c) && c != 160) {
            return false;
        }
        return true;
    }

    public final void addListener(InterpretationConsumer interpretationConsumer) {
        this.filter.listeners.add(interpretationConsumer);
    }

    public final CharSequence getSubsequence(boolean z, CharSequence charSequence, int i, int i2) {
        if (z && !this.preferenceProvider$ar$class_merging.shouldSpeakPasswords()) {
            int i3 = i + 1;
            if (i2 - i == 1) {
                return this.mContext.getString(R.string.template_password_traversed, Integer.valueOf(i3));
            }
            return this.mContext.getString(R.string.template_password_selected, Integer.valueOf(i3), Integer.valueOf(i2));
        }
        return getSubsequenceWithSpans(charSequence, i, i2);
    }

    public final void setHistoryLastNode(AccessibilityEvent accessibilityEvent) {
        this.mHistory$ar$class_merging.CronetLogger$CronetEngineBuilderInitializedInfo$ar$apiVersion = accessibilityEvent.getSource();
    }

    public final boolean sourceEqualsLastNode(AccessibilityEvent accessibilityEvent) {
        CronetLogger.CronetEngineBuilderInitializedInfo cronetEngineBuilderInitializedInfo = this.mHistory$ar$class_merging;
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        Object obj = cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$apiVersion;
        if (source != null && source.equals(obj)) {
            return true;
        }
        return false;
    }
}
