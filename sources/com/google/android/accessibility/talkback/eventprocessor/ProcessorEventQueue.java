package com.google.android.accessibility.talkback.eventprocessor;

import android.os.Message;
import android.util.SparseIntArray;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.compositor.EventFilter;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.input.TextEventInterpretation;
import com.google.android.accessibility.utils.input.TextEventInterpreter;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessorEventQueue implements AccessibilityEventListener {
    private final EventFilter eventFilter;
    private final TextEventInterpreter textEventInterpreter;
    public final ProcessorEventHandler handler = new ProcessorEventHandler(this);
    public final WindowTrackerFactory eventQueue$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory((byte[]) null, (byte[]) null);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProcessorEventHandler extends WeakReferenceHandler {
        public ProcessorEventHandler(ProcessorEventQueue processorEventQueue) {
            super(processorEventQueue);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            AccessibilityEvent accessibilityEvent;
            ProcessorEventQueue processorEventQueue = (ProcessorEventQueue) obj;
            if (message.what != 1) {
                return;
            }
            while (true) {
                synchronized (processorEventQueue.eventQueue$ar$class_merging$ar$class_merging$ar$class_merging) {
                    if (((ArrayList) processorEventQueue.eventQueue$ar$class_merging$ar$class_merging$ar$class_merging.WindowTrackerFactory$ar$executorProvider).isEmpty()) {
                        return;
                    }
                    WindowTrackerFactory windowTrackerFactory = processorEventQueue.eventQueue$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (((ArrayList) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).isEmpty()) {
                        accessibilityEvent = null;
                    } else {
                        AccessibilityEvent accessibilityEvent2 = (AccessibilityEvent) ((ArrayList) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).remove(0);
                        if (accessibilityEvent2 != null && SpannableUtils$IdentifierSpan.eventMatchesAnyType(accessibilityEvent2, 36992)) {
                            int eventType = accessibilityEvent2.getEventType();
                            ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).put(eventType, ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).get(eventType, 0) - 1);
                        }
                        accessibilityEvent = accessibilityEvent2;
                    }
                }
                processorEventQueue.processEvent(accessibilityEvent, Performance.instance.toEventId(accessibilityEvent));
            }
        }
    }

    public ProcessorEventQueue(final EventFilter eventFilter, TextEventInterpreter textEventInterpreter) {
        this.eventFilter = eventFilter;
        this.textEventInterpreter = textEventInterpreter;
        eventFilter.getClass();
        textEventInterpreter.addListener(new TextEventInterpreter.InterpretationConsumer() { // from class: com.google.android.accessibility.talkback.eventprocessor.ProcessorEventQueue$$ExternalSyntheticLambda0
            @Override // com.google.android.accessibility.utils.input.TextEventInterpreter.InterpretationConsumer
            public final void accept$ar$class_merging$ar$class_merging$d1a82255_0$ar$class_merging(OptionalMethod optionalMethod) {
                int i;
                AccessibilityEvent accessibilityEvent = (AccessibilityEvent) optionalMethod.OptionalMethod$ar$methodName;
                boolean isPassword = accessibilityEvent.isPassword();
                EventFilter eventFilter2 = EventFilter.this;
                eventFilter2.globalVariables.mLastTextEditIsPassword = isPassword;
                Object obj = optionalMethod.OptionalMethod$ar$returnType;
                if (obj == null) {
                    i = Verifier.toCompositorEvent(accessibilityEvent);
                } else {
                    i = ((TextEventInterpretation) obj).event;
                }
                EventInterpretation eventInterpretation = new EventInterpretation(i);
                eventInterpretation.setTextEventInterpretation((TextEventInterpretation) optionalMethod.OptionalMethod$ar$returnType);
                CharSequence packageName = accessibilityEvent.getPackageName();
                eventInterpretation.checkIsWritable();
                eventInterpretation.mPackageName = packageName;
                eventInterpretation.setReadOnly();
                eventFilter2.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(accessibilityEvent, (Performance.EventId) optionalMethod.OptionalMethod$ar$methodParams, eventInterpretation);
            }
        });
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return -1;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        synchronized (this.eventQueue$ar$class_merging$ar$class_merging$ar$class_merging) {
            WindowTrackerFactory windowTrackerFactory = this.eventQueue$ar$class_merging$ar$class_merging$ar$class_merging;
            AccessibilityEvent obtain = AccessibilityEvent.obtain(accessibilityEvent);
            int eventType = obtain.getEventType();
            if (SpannableUtils$IdentifierSpan.eventMatchesAnyType(obtain, 36992)) {
                ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).put(eventType, ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).get(eventType, 0) + 1);
            }
            ((ArrayList) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).add(obtain);
            int i = 0;
            for (int i2 = 0; i2 < ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).size(); i2++) {
                int keyAt = ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).keyAt(i2);
                if (((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).valueAt(i2) > 2) {
                    i |= keyAt;
                }
            }
            Iterator it = ((ArrayList) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).iterator();
            while (it.hasNext() && i != 0) {
                AccessibilityEvent accessibilityEvent2 = (AccessibilityEvent) it.next();
                if (SpannableUtils$IdentifierSpan.eventMatchesAnyType(accessibilityEvent2, i)) {
                    int eventType2 = accessibilityEvent2.getEventType();
                    int i3 = ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).get(eventType2, 0) - 1;
                    ((SparseIntArray) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider).put(eventType2, i3);
                    it.remove();
                    if (i3 <= 2) {
                        i &= ~eventType2;
                    }
                }
            }
            ProcessorEventHandler processorEventHandler = this.handler;
            if (!processorEventHandler.hasMessages(1)) {
                processorEventHandler.sendEmptyMessage(1);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x016a, code lost:
    
        if (r15.shouldMuteFeedback == false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x0175, code lost:
    
        if (com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.sourceCompat(r29) == null) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02a0, code lost:
    
        if (r10 != 131072) goto L168;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:103:0x088b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:111:0x08d5  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x093c  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x09eb A[LOOP:0: B:137:0x09e5->B:139:0x09eb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x09f5  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0875  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0847  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0854  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0859  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0870  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x087b  */
    /* JADX WARN: Type inference failed for: r10v30, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v25, types: [java.lang.CharSequence, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void processEvent(android.view.accessibility.AccessibilityEvent r29, com.google.android.accessibility.utils.Performance.EventId r30) {
        /*
            Method dump skipped, instructions count: 2638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.eventprocessor.ProcessorEventQueue.processEvent(android.view.accessibility.AccessibilityEvent, com.google.android.accessibility.utils.Performance$EventId):void");
    }
}
