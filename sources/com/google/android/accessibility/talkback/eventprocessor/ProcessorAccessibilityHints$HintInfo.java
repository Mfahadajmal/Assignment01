package com.google.android.accessibility.talkback.eventprocessor;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessorAccessibilityHints$HintInfo {
    public int pendingHintEventType;
    public AccessibilityNodeInfoCompat pendingHintSource;
    public CharSequence pendingSelectorHint;
    public boolean nodeHintForceFeedbackEvenIfAudioPlaybackActive = true;
    public boolean nodeHintForceFeedbackEvenIfMicrophoneActive = false;
    public boolean spellingSuggestionHint = false;
}
