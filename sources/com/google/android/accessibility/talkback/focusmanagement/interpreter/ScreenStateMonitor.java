package com.google.android.accessibility.talkback.focusmanagement.interpreter;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.google.android.accessibility.talkback.monitor.InputMethodMonitor;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.accessibility.utils.input.WindowsDelegate;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenStateMonitor implements WindowEventInterpreter.WindowEventHandler {
    public boolean areMainWindowsStable;
    private final InputMethodMonitor inputMethodMonitor;
    private final AccessibilityService service;
    public ScreenState stableScreenState;
    public WindowsDelegate windowsDelegate;
    public final HapticPatternParser$$ExternalSyntheticLambda1 state$ar$class_merging$728ccbc7_0$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
    private final List listeners = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ScreenStateChangeListener {
        void onScreenStateChanged$ar$ds(ScreenState screenState, Performance.EventId eventId);
    }

    public ScreenStateMonitor(AccessibilityService accessibilityService, InputMethodMonitor inputMethodMonitor) {
        this.service = accessibilityService;
        this.inputMethodMonitor = inputMethodMonitor;
    }

    public final void addScreenStateChangeListener(ScreenStateChangeListener screenStateChangeListener) {
        if (screenStateChangeListener != null) {
            this.listeners.add(screenStateChangeListener);
            return;
        }
        throw new IllegalArgumentException("Listener must not be null.");
    }

    @Override // com.google.android.accessibility.utils.input.WindowEventInterpreter.WindowEventHandler
    public final void handle(WindowEventInterpreter.EventInterpretation eventInterpretation, Performance.EventId eventId) {
        AccessibilityNodeInfo rootInActiveWindow;
        boolean z = eventInterpretation.mainWindowsChanged;
        this.areMainWindowsStable = !z;
        if ((z || (eventInterpretation.inputMethodChanged && this.inputMethodMonitor.useInputWindowAsActiveWindow())) && eventInterpretation.windowsStable) {
            this.areMainWindowsStable = true;
            AccessibilityService accessibilityService = this.service;
            AccessibilityWindowInfo accessibilityWindowInfo = null;
            if (accessibilityService != null && (rootInActiveWindow = accessibilityService.getRootInActiveWindow()) != null) {
                accessibilityWindowInfo = AccessibilityNodeInfoUtils.getWindow(rootInActiveWindow);
            }
            if (this.inputMethodMonitor.useInputWindowAsActiveWindow() && SpannableUtils$IdentifierSpan.isInputWindowOnScreen(this.service)) {
                accessibilityWindowInfo = SpannableUtils$IdentifierSpan.getOnscreenInputWindowInfo(this.inputMethodMonitor.service);
            }
            ScreenState screenState = new ScreenState(this.windowsDelegate, accessibilityWindowInfo, eventInterpretation.eventStartTime, eventInterpretation.interpretFirstTimeWhenWakeUp);
            this.stableScreenState = screenState;
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((ScreenStateChangeListener) it.next()).onScreenStateChanged$ar$ds(screenState, eventId);
            }
        }
    }
}
