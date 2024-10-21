package com.google.android.accessibility.talkback.interpreters;

import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.Interpretation$ID;
import com.google.android.accessibility.talkback.actor.AutoScrollActor;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.Supplier;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.monitor.DisplayMonitor;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.ScrollActionRecord;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SubtreeChangeEventInterpreter implements AccessibilityEventListener, DisplayMonitor.DisplayStateChangedListener {
    public final DisplayMonitor displayMonitor;
    public final int maskEventType;
    private final HapticPatternParser$$ExternalSyntheticLambda1 screenState$ar$class_merging$ar$class_merging$ar$class_merging;
    public Supplier scrollActorState;
    public int subtreeChangedDelayMs = 150;
    private boolean defaultDisplayOn = true;
    public final SubtreeChangedHandler subtreeChangedHandler = new SubtreeChangedHandler(this);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SubtreeChangedHandler extends WeakReferenceHandler {
        public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

        public SubtreeChangedHandler(SubtreeChangeEventInterpreter subtreeChangeEventInterpreter) {
            super(subtreeChangeEventInterpreter, Looper.myLooper());
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            SubtreeChangeEventInterpreter subtreeChangeEventInterpreter = (SubtreeChangeEventInterpreter) obj;
            if (this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null && message.what == 1) {
                ScrollActionRecord scrollActionRecord = ((AutoScrollActor.StateReader) subtreeChangeEventInterpreter.scrollActorState).get();
                if (SpannableUtils$IdentifierSpan.supportMultipleAutoScroll() && scrollActionRecord != null) {
                    if (SystemClock.uptimeMillis() - scrollActionRecord.autoScrolledTime < 1000) {
                        LogUtils.i("SubtreeChangeEventInterpreter", "Keep delaying because we have a new auto scroll action.", new Object[0]);
                        sendEmptyMessageDelayed(1, subtreeChangeEventInterpreter.subtreeChangedDelayMs);
                        return;
                    }
                }
                Logger logger = Performance.DEFAULT_LOGGER;
                Performance.EventId eventId = null;
                if (message.obj != null && (message.obj instanceof Performance.EventId)) {
                    eventId = (Performance.EventId) message.obj;
                }
                subtreeChangeEventInterpreter.subtreeChangedDelayMs = 150;
                this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, null, new Interpretation$ID(Interpretation$ID.Value.SUBTREE_CHANGED), null);
            }
        }
    }

    public SubtreeChangeEventInterpreter(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, DisplayMonitor displayMonitor) {
        int i;
        this.screenState$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.displayMonitor = displayMonitor;
        if (true != SpannableUtils$IdentifierSpan.supportMultipleAutoScroll()) {
            i = 2048;
        } else {
            i = 6144;
        }
        this.maskEventType = i;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return this.maskEventType;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        if ((accessibilityEvent.getEventType() & this.maskEventType) != 0) {
            if ((accessibilityEvent.getEventType() & 2048) == 0 || (accessibilityEvent.getContentChangeTypes() & 1) != 0) {
                this.subtreeChangedHandler.removeMessages(1);
                if (this.screenState$ar$class_merging$ar$class_merging$ar$class_merging.areMainWindowsStable() && this.defaultDisplayOn) {
                    if (SpannableUtils$IdentifierSpan.supportMultipleAutoScroll() && (accessibilityEvent.getEventType() & 4096) != 0) {
                        this.subtreeChangedDelayMs = 350;
                    }
                    this.subtreeChangedHandler.sendMessageDelayed(this.subtreeChangedHandler.obtainMessage(1, eventId), this.subtreeChangedDelayMs);
                }
            }
        }
    }

    @Override // com.google.android.accessibility.utils.monitor.DisplayMonitor.DisplayStateChangedListener
    public final void onDisplayStateChanged(boolean z) {
        this.defaultDisplayOn = z;
        if (!z) {
            this.subtreeChangedHandler.removeMessages(1);
        }
    }
}
