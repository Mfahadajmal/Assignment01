package com.google.android.accessibility.talkback.focusmanagement.interpreter;

import android.view.accessibility.AccessibilityWindowInfo;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.accessibility.utils.input.WindowsDelegate;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenState implements WindowsDelegate {
    public final AccessibilityWindowInfo activeWindow;
    public boolean interpretFirstTimeWhenWakeUp;
    public final long screenTransitionStartTime;
    public final WindowsDelegate windowsDelegate;

    public ScreenState(WindowsDelegate windowsDelegate, AccessibilityWindowInfo accessibilityWindowInfo, long j, boolean z) {
        this.windowsDelegate = windowsDelegate;
        this.activeWindow = accessibilityWindowInfo;
        this.screenTransitionStartTime = j;
        this.interpretFirstTimeWhenWakeUp = z;
    }

    public final void consumeInterpretFirstTimeWhenWakeUp() {
        this.interpretFirstTimeWhenWakeUp = false;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ScreenState)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ScreenState screenState = (ScreenState) obj;
        if (this.screenTransitionStartTime == screenState.screenTransitionStartTime && screenState.interpretFirstTimeWhenWakeUp == this.interpretFirstTimeWhenWakeUp) {
            AccessibilityWindowInfo accessibilityWindowInfo = this.activeWindow;
            AccessibilityWindowInfo accessibilityWindowInfo2 = screenState.activeWindow;
            if (accessibilityWindowInfo != null ? accessibilityWindowInfo.equals(accessibilityWindowInfo2) : accessibilityWindowInfo2 == null) {
                return true;
            }
        }
        return false;
    }

    public final CharSequence getWindowTitle(int i) {
        return ((WindowEventInterpreter) this.windowsDelegate).getWindowTitleForFeedback(i);
    }

    public final int hashCode() {
        int hashCode;
        AccessibilityWindowInfo accessibilityWindowInfo = this.activeWindow;
        if (accessibilityWindowInfo == null) {
            hashCode = 0;
        } else {
            hashCode = accessibilityWindowInfo.hashCode();
        }
        return Objects.hash(Integer.valueOf(hashCode), Long.valueOf(this.screenTransitionStartTime), Boolean.valueOf(this.interpretFirstTimeWhenWakeUp));
    }

    public final String toString() {
        return StringBuilderUtils.joinFields(StringBuilderUtils.optionalSubObj("activeWindow", this.activeWindow), StringBuilderUtils.optionalInt$ar$ds("screenTransitionStartTime", this.screenTransitionStartTime), StringBuilderUtils.optionalTag("interpretFirstTimeWhenWakeUp", this.interpretFirstTimeWhenWakeUp));
    }
}
