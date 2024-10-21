package com.google.android.accessibility.talkback.eventprocessor;

import android.content.SharedPreferences;
import android.os.Looper;
import android.os.Message;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityRecord;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$ID;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.RingerModeAndScreenMonitor;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.VoiceActionMonitor;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.monitor.DisplayMonitor;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityEventProcessor implements DisplayMonitor.DisplayStateChangedListener {
    public static Method getSourceNodeIdMethod;
    public final AccessibilityManager accessibilityManager;
    public ActorState actorState;
    public final DisplayMonitor displayMonitor;
    public int dumpEventMask;
    public final DelayedEventHandler handler;
    public AccessibilityEvent lastFocusedEvent;
    public long lastWindowStateChanged;
    public RingerModeAndScreenMonitor ringerModeAndScreenMonitor;
    public final TalkBackService service;
    public VoiceActionMonitor voiceActionMonitor;
    public long lastClearedSourceId = -1;
    public int lastClearedWindowId = -1;
    public long lastClearA11yFocus = System.currentTimeMillis();
    public long lastPronouncedSourceId = -1;
    public int lastPronouncedWindowId = -1;
    public final List accessibilityEventListeners = new ArrayList();
    public boolean speakWhenScreenOff = false;
    public boolean defaultDisplayOn = true;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AccessibilityEventIdleListener {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DelayedEventHandler extends WeakReferenceHandler {
        public AccessibilityEventIdleListener accessibilityEventIdleListener;

        public DelayedEventHandler(AccessibilityEventProcessor accessibilityEventProcessor) {
            super(accessibilityEventProcessor, Looper.myLooper());
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            AccessibilityEventProcessor accessibilityEventProcessor = (AccessibilityEventProcessor) obj;
            int i = message.what;
            if (i != 1) {
                if (i == 2 && this.accessibilityEventIdleListener != null) {
                    LogUtils.d("A11yEventProcessor", "Processor idle state.", new Object[0]);
                    HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = ((Pipeline) this.accessibilityEventIdleListener).interpreters.accessibilityEventIdleInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    Logger logger = Performance.DEFAULT_LOGGER;
                    hapticPatternParser$$ExternalSyntheticLambda1.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null, null, new Interpretation$ID(Interpretation$ID.Value.ACCESSIBILITY_EVENT_IDLE), null);
                    return;
                }
                return;
            }
            if (message.obj != null) {
                WindowTrackerFactory windowTrackerFactory = (WindowTrackerFactory) message.obj;
                accessibilityEventProcessor.processEvent((AccessibilityEvent) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider, (Performance.EventId) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider);
            }
        }
    }

    static {
        try {
            Method declaredMethod = AccessibilityRecord.class.getDeclaredMethod("getSourceNodeId", null);
            getSourceNodeIdMethod = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            LogUtils.d("A11yEventProcessor", "Error setting up fields: ".concat(String.valueOf(e.toString())), new Object[0]);
            e.printStackTrace();
        }
    }

    public AccessibilityEventProcessor(TalkBackService talkBackService, DisplayMonitor displayMonitor) {
        this.dumpEventMask = 0;
        this.accessibilityManager = (AccessibilityManager) talkBackService.getSystemService("accessibility");
        this.service = talkBackService;
        this.displayMonitor = displayMonitor;
        int[] allEventTypes = SpannableUtils$IdentifierSpan.getAllEventTypes();
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(talkBackService);
        for (int i = 0; i < 25; i++) {
            int i2 = allEventTypes[i];
            if (sharedPreferences.getBoolean(this.service.getString(R.string.pref_dump_event_key_prefix, new Object[]{Integer.valueOf(i2)}), false)) {
                this.dumpEventMask = i2 | this.dumpEventMask;
            }
        }
        this.handler = new DelayedEventHandler(this);
    }

    @Override // com.google.android.accessibility.utils.monitor.DisplayMonitor.DisplayStateChangedListener
    public final void onDisplayStateChanged(boolean z) {
        this.defaultDisplayOn = z;
        if (!z) {
            this.handler.removeMessages(2);
        }
    }

    public final void processEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        String str;
        if (LogUtils.minLogLevel <= 2) {
            StringBuilder sb = new StringBuilder();
            for (AccessibilityEventListener accessibilityEventListener : this.accessibilityEventListeners) {
                if (SpannableUtils$IdentifierSpan.eventMatchesAnyType(accessibilityEvent, accessibilityEventListener.getEventTypes())) {
                    if (sb.length() == 0) {
                        str = "";
                    } else {
                        str = ",";
                    }
                    sb.append(str);
                    sb.append(accessibilityEventListener.getClass().getSimpleName());
                }
            }
            LogUtils.v("A11yEventProcessor", "Event listeners: %s", sb);
        }
        for (AccessibilityEventListener accessibilityEventListener2 : this.accessibilityEventListeners) {
            if (SpannableUtils$IdentifierSpan.eventMatchesAnyType(accessibilityEvent, accessibilityEventListener2.getEventTypes())) {
                accessibilityEventListener2.onAccessibilityEvent(accessibilityEvent, eventId);
            }
        }
    }
}
