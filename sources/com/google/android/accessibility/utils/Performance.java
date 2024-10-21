package com.google.android.accessibility.utils;

import _COROUTINE._BOUNDARY;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.Interpreters$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.logging.EventLatencyLogger;
import com.google.android.accessibility.utils.performance.AccessibilityActionDetails;
import com.google.common.collect.ImmutableList;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Performance {
    public long timeInteractionStart;
    public static final Logger DEFAULT_LOGGER = new TreeDebug$$ExternalSyntheticLambda0(1);
    public static final ImmutableList STAGE_NAMES = ImmutableList.of((Object) "STAGE_FRAMEWORK", (Object) "STAGE_INLINE_HANDLING", (Object) "STAGE_FEEDBACK_QUEUED", (Object) "STAGE_FEEDBACK_HEARD", (Object) "STAGE_BETWEEN_FEEDBACK_QUEUED_AND_FEEDBACK_HEARD", (Object) "STAGE_FEEDBACK_COMPOSED", (Object) "STAGE_ACTION_PERFORMED");
    public static final ImmutableList EVENT_TYPE_NAMES = ImmutableList.of((Object) "EVENT_TYPE_ACCESSIBILITY", (Object) "EVENT_TYPE_KEY", (Object) "EVENT_TYPE_KEY_COMBO", (Object) "EVENT_TYPE_VOLUME_KEY_COMBO", (Object) "EVENT_TYPE_GESTURE", (Object) "EVENT_TYPE_ROTATE", (Object) "EVENT_TYPE_FINGERPRINT_GESTURE", (Object) "EVENT_TYPE_MOTION_EVENT_SOURCE", (Object) "EVENT_TYPE_GESTURE_DETECTION");
    public static final Performance instance = new Performance();
    public boolean computeStatsEnabled = false;
    protected final ArrayDeque eventQueue = new ArrayDeque();
    protected final HashMap eventIndex = new HashMap();
    public final HashMap utteranceToEvent = new HashMap();
    public final Object lockRecentEvents = new Object();
    protected final HashMap labelToStats = new HashMap();
    public final SparseArray gestureDetectionToStats = new SparseArray();
    public final Object lockGestureDetectionToStats = new Object();
    protected final Object lockLabelToStats = new Object();
    protected final Statistics allEventStats = new Statistics();
    public final List latencyTrackers = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BarInfo {
        public final String label;
        public final float rangeEnd;
        public final float value;

        public BarInfo(String str, float f) {
            this.rangeEnd = -1.0f;
            this.label = str;
            this.value = f;
        }

        public BarInfo(String str, float f, float f2) {
            this.label = str;
            this.value = f;
            this.rangeEnd = f2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EventData {
        public AccessibilityActionDetails actionDetails;
        private boolean changeToSameLocale;
        public final EventId eventId;
        private boolean flushTtsQueue;
        public GestureEventData gestureEventData;
        public final String[] labels;
        public final long timeReceivedAtTalkback;
        public final long uptimeReceivedAtTalkback;
        private String utteranceId;
        long timeInlineHandled = -1;
        private long timeFeedbackComposed = -1;
        private long timeFeedbackQueued = -1;
        private long timeFeedbackOutput = -1;

        public EventData(long j, long j2, String[] strArr, EventId eventId) {
            this.labels = strArr;
            this.eventId = eventId;
            this.timeReceivedAtTalkback = j;
            this.uptimeReceivedAtTalkback = j2;
        }

        public final synchronized AccessibilityActionDetails getActionDetails() {
            return this.actionDetails;
        }

        public final synchronized boolean getChangeToSameLocale() {
            return this.changeToSameLocale;
        }

        public final synchronized long getTimeFeedbackComposed() {
            return this.timeFeedbackComposed;
        }

        public final synchronized long getTimeFeedbackOutput() {
            return this.timeFeedbackOutput;
        }

        public final synchronized long getTimeFeedbackQueued() {
            return this.timeFeedbackQueued;
        }

        public final synchronized String getUtteranceId() {
            return this.utteranceId;
        }

        public final synchronized void setFeedbackComposed(long j) {
            this.timeFeedbackComposed = j;
        }

        public final synchronized void setFeedbackOutput(long j) {
            this.timeFeedbackOutput = j;
        }

        public final synchronized void setFeedbackQueued(long j, String str, boolean z, boolean z2) {
            this.timeFeedbackQueued = j;
            this.utteranceId = str;
            this.flushTtsQueue = z;
            this.changeToSameLocale = z2;
        }

        public final String toString() {
            EventId eventId = this.eventId;
            return " labels=" + TextUtils.join(",", this.labels) + " eventId=" + eventId.toString() + " uptimeReceivedAtTalkback=" + this.uptimeReceivedAtTalkback + " timeReceivedAtTalkback=" + this.timeReceivedAtTalkback + " mTimeFeedbackQueued=" + this.timeFeedbackComposed + " timeFeedbackComposed=" + this.timeFeedbackQueued + " mTimeFeedbackOutput=" + this.timeFeedbackOutput + " timeInlineHandled=" + this.timeInlineHandled + " flushTtsQueue=" + this.flushTtsQueue + String.format(" mUtteranceId=%s", this.utteranceId) + " actionDetails=" + String.valueOf(this.actionDetails);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EventId {
        public final int displayId;
        public final int eventSubtype;
        public final long eventTimeMs;
        public final int eventType;

        public EventId(long j, int i, int i2, int i3) {
            this.eventTimeMs = j;
            this.eventType = i;
            this.eventSubtype = i2;
            this.displayId = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EventId)) {
                return false;
            }
            EventId eventId = (EventId) obj;
            if (this.eventTimeMs == eventId.eventTimeMs && this.eventType == eventId.eventType && this.eventSubtype == eventId.eventSubtype && this.displayId == eventId.displayId) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Long.valueOf(this.eventTimeMs), Integer.valueOf(this.eventType), Integer.valueOf(this.eventSubtype));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final String toString() {
            String typeToString;
            Locale.Category category;
            Locale locale;
            Locale.Category category2;
            Locale locale2;
            Locale.Category category3;
            Locale locale3;
            switch (this.eventType) {
                case 0:
                    typeToString = SpannableUtils$IdentifierSpan.typeToString(this.eventSubtype);
                    break;
                case 1:
                    typeToString = KeyEvent.keyCodeToString(this.eventSubtype);
                    break;
                case 2:
                    category = Locale.Category.FORMAT;
                    locale = Locale.getDefault(category);
                    typeToString = String.format(locale, "KEY_COMBO_%d", Integer.valueOf(this.eventSubtype));
                    break;
                case 3:
                    category2 = Locale.Category.FORMAT;
                    locale2 = Locale.getDefault(category2);
                    typeToString = String.format(locale2, "VOLUME_KEY_COMBO_%d", Integer.valueOf(this.eventSubtype));
                    break;
                case 4:
                    typeToString = SpannableUtils$IdentifierSpan.gestureIdToString(this.eventSubtype);
                    break;
                case 5:
                    typeToString = Performance.orientationToSymbolicName(this.eventSubtype);
                    break;
                case 6:
                    typeToString = SpannableUtils$IdentifierSpan.fingerprintGestureIdToString(this.eventSubtype);
                    break;
                case 7:
                    category3 = Locale.Category.FORMAT;
                    locale3 = Locale.getDefault(category3);
                    typeToString = String.format(locale3, "MOTION_EVENT_SOURCE_%d", Integer.valueOf(this.eventSubtype));
                    break;
                default:
                    typeToString = Integer.toString(this.eventSubtype);
                    break;
            }
            return "type:" + ((String) Performance.EVENT_TYPE_NAMES.get(this.eventType)) + " subtype:" + typeToString + " displayId:" + this.displayId + " time:" + this.eventTimeMs;
        }

        public EventId(long j, int i, int i2) {
            this(j, i, i2, 0);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GestureEventData {
        public int displayId = -1;
        public int gestureId = 0;
        public long gestureDetectionStartedTime = -1;
        public long lastMotionEventTime = -1;
        public long gestureDecisionTime = -1;
        public long gestureDetectedTime = -1;

        public final String toString() {
            return "GestureEventData {\n\tdisplayId:" + this.displayId + "\n\tgestureId:" + this.gestureId + "\n\tgestureDetectionStartedTime:" + this.gestureDetectionStartedTime + "\n\tlastMotionEventTime:" + this.lastMotionEventTime + "\n\tgestureDecisionTime:" + this.gestureDecisionTime + "\n\tgestureDetectedTime:" + this.gestureDetectedTime + "\n}";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StatisticsKey implements Comparable {
        private final String label;
        private final int stage;

        public StatisticsKey(String str, int i) {
            this.label = str;
            this.stage = i;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            if (obj != null && (obj instanceof StatisticsKey)) {
                if (this == obj) {
                    return 0;
                }
                StatisticsKey statisticsKey = (StatisticsKey) obj;
                int i = this.stage - statisticsKey.stage;
                if (i != 0) {
                    return i;
                }
                return this.label.compareTo(statisticsKey.label);
            }
            return 1;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof StatisticsKey)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            StatisticsKey statisticsKey = (StatisticsKey) obj;
            if (this.stage != statisticsKey.stage || !this.label.equals(statisticsKey.label)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Objects.hash(this.label, Integer.valueOf(this.stage));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final String toString() {
            return this.label + "-" + ((String) Performance.STAGE_NAMES.get(this.stage));
        }
    }

    protected Performance() {
    }

    public static void display(Logger logger, String str, Object... objArr) {
        logger.log(str, objArr);
    }

    private static void displayBarGraph(Logger logger, String str, String str2, ArrayList arrayList, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            display(logger, "  %s", str2);
        }
        int size = arrayList.size();
        float f = 0.0f;
        for (int i = 0; i < size; i++) {
            f = Math.max(f, ((BarInfo) arrayList.get(i)).value);
        }
        float f2 = 40.0f / f;
        int size2 = arrayList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            BarInfo barInfo = (BarInfo) arrayList.get(i2);
            int i3 = ((int) (barInfo.value * f2)) + 1;
            StringBuilder sb = new StringBuilder(i3);
            for (int i4 = 0; i4 < i3; i4++) {
                sb.append("#");
            }
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str + sb2 + " " + floatToString(barInfo.value));
            float f3 = barInfo.rangeEnd;
            if (f3 != -1.0f) {
                sb3.append("-".concat(String.valueOf(floatToString(f3))));
            }
            sb3.append(str3 + " for " + barInfo.label);
            display(logger, sb3.toString(), new Object[0]);
        }
        display(logger, "", new Object[0]);
    }

    public static void displayStatistics(Logger logger, Statistics statistics) {
        display(logger, "    missing=%s, count=%s, mean=%sms, stdDev=%sms, median=%sms, 90th percentile=%sms, 99th percentile=%sms", Long.valueOf(statistics.numMissing), Long.valueOf(statistics.count), Long.valueOf(statistics.getMean()), Double.valueOf(statistics.getStdDev()), Long.valueOf(statistics.getPercentile(50)), Long.valueOf(statistics.getPercentile(90)), Long.valueOf(statistics.getPercentile(99)));
        ArrayList arrayList = new ArrayList(statistics.histogram.size());
        for (int i = 0; i < statistics.histogram.size(); i++) {
            StringBuilder sb = new StringBuilder();
            long histogramBinToStartValue$ar$ds = Statistics.histogramBinToStartValue$ar$ds(i);
            sb.append(histogramBinToStartValue$ar$ds);
            sb.append("-");
            sb.append(histogramBinToStartValue$ar$ds + histogramBinToStartValue$ar$ds);
            sb.append("ms");
            arrayList.add(new BarInfo(sb.toString(), (float) ((AtomicLong) statistics.histogram.get(i)).longValue()));
        }
        displayBarGraph(logger, "      ", "distribution=", arrayList, "count");
    }

    private static String floatToString(float f) {
        int i = (int) f;
        if (i == f) {
            return formatString("%d", Integer.valueOf(i));
        }
        return formatString("%f", Float.valueOf(f));
    }

    private static String formatString(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }

    private final void incrementNumMissing(String str, int i) {
        Statistics statistics;
        synchronized (this.lockLabelToStats) {
            statistics = (Statistics) this.labelToStats.get(new StatisticsKey(str, i));
        }
        if (statistics != null) {
            statistics.incrementNumMissing();
        }
    }

    public static String orientationToSymbolicName(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "ORIENTATION_");
                }
                return "ORIENTATION_LANDSCAPE";
            }
            return "ORIENTATION_PORTRAIT";
        }
        return "ORIENTATION_UNDEFINED";
    }

    public final void clearAllStats() {
        synchronized (this.lockLabelToStats) {
            this.labelToStats.clear();
        }
        this.allEventStats.clear();
        synchronized (this.lockGestureDetectionToStats) {
            this.gestureDetectionToStats.clear();
        }
    }

    public final void clearRecentEvents() {
        synchronized (this.lockRecentEvents) {
            this.eventIndex.clear();
            this.eventQueue.clear();
        }
    }

    public final void collectMissingLatencies(EventData eventData) {
        String str = eventData.labels[0];
        if (eventData.timeInlineHandled <= 0) {
            incrementNumMissing(str, 1);
        }
        if (eventData.getTimeFeedbackQueued() <= 0) {
            incrementNumMissing(str, 2);
        }
        if (eventData.getTimeFeedbackOutput() <= 0) {
            incrementNumMissing(str, 3);
        }
    }

    public final void displayAllEventStats(Logger logger) {
        display(logger, "displayAllEventStats()", new Object[0]);
        displayStatistics(logger, this.allEventStats);
    }

    public final void displayLabelToStats(Logger logger) {
        display(logger, "displayLabelToStats()", new Object[0]);
        StatisticsKey[] statisticsKeyArr = (StatisticsKey[]) this.labelToStats.keySet().toArray(new StatisticsKey[this.labelToStats.size()]);
        Arrays.sort(statisticsKeyArr);
        for (StatisticsKey statisticsKey : statisticsKeyArr) {
            Statistics statistics = (Statistics) this.labelToStats.get(statisticsKey);
            display(logger, "  %s", statisticsKey);
            displayStatistics(logger, statistics);
        }
    }

    public final void displayStatToLabelCompare(Logger logger) {
        int i = 0;
        display(logger, "displayStatToLabelCompare()", new Object[0]);
        StatisticsKey[] statisticsKeyArr = (StatisticsKey[]) this.labelToStats.keySet().toArray(new StatisticsKey[this.labelToStats.size()]);
        Arrays.sort(statisticsKeyArr);
        int length = statisticsKeyArr.length;
        ArrayList arrayList = new ArrayList(length);
        ArrayList arrayList2 = new ArrayList(length);
        ArrayList arrayList3 = new ArrayList(length);
        ArrayList arrayList4 = new ArrayList(length);
        ArrayList arrayList5 = new ArrayList(length);
        while (i < length) {
            StatisticsKey statisticsKey = statisticsKeyArr[i];
            Statistics statistics = (Statistics) this.labelToStats.get(statisticsKey);
            arrayList.add(new BarInfo(statisticsKey.toString(), (float) statistics.numMissing));
            arrayList2.add(new BarInfo(statisticsKey.toString(), (float) statistics.count));
            arrayList3.add(new BarInfo(statisticsKey.toString(), (float) statistics.getMean()));
            String statisticsKey2 = statisticsKey.toString();
            float medianBinStart = (float) statistics.getMedianBinStart();
            long medianBinStart2 = statistics.getMedianBinStart();
            arrayList4.add(new BarInfo(statisticsKey2, medianBinStart, (float) (medianBinStart2 + medianBinStart2)));
            arrayList5.add(new BarInfo(statisticsKey.toString(), (float) statistics.getStdDev()));
            i++;
            length = length;
            arrayList2 = arrayList2;
        }
        displayBarGraph(logger, "  ", "missing", arrayList, "");
        displayBarGraph(logger, "  ", "count", arrayList2, "");
        displayBarGraph(logger, "  ", "mean", arrayList3, "ms");
        displayBarGraph(logger, "  ", "median", arrayList4, "ms");
        displayBarGraph(logger, "  ", "stddev", arrayList5, "ms");
    }

    public final Statistics getOrCreateStatistics(String str, int i) {
        Statistics statistics;
        synchronized (this.lockLabelToStats) {
            StatisticsKey statisticsKey = new StatisticsKey(str, i);
            statistics = (Statistics) this.labelToStats.get(statisticsKey);
            if (statistics == null) {
                statistics = new Statistics();
                this.labelToStats.put(statisticsKey, statistics);
            }
        }
        return statistics;
    }

    public final EventData getRecentEvent(EventId eventId) {
        EventData eventData;
        synchronized (this.lockRecentEvents) {
            eventData = (EventData) this.eventIndex.get(eventId);
        }
        return eventData;
    }

    public final void notifyLatencyTracker(Consumer consumer) {
        synchronized (this.latencyTrackers) {
            if (this.latencyTrackers.isEmpty()) {
                return;
            }
            for (EventLatencyLogger eventLatencyLogger : DesugarCollections.unmodifiableList(this.latencyTrackers)) {
                eventLatencyLogger.getExecutor().execute(new ListMenuManager$$ExternalSyntheticLambda3(consumer, eventLatencyLogger, 6, (char[]) null));
            }
        }
    }

    public final EventId onEventReceived(AccessibilityEvent accessibilityEvent) {
        EventId eventId = toEventId(accessibilityEvent);
        if (!trackEvents()) {
            return eventId;
        }
        if (accessibilityEvent.getEventType() == 1048576) {
            this.timeInteractionStart = accessibilityEvent.getEventTime();
        }
        onEventReceived(eventId, new String[]{SpannableUtils$IdentifierSpan.typeToString(accessibilityEvent.getEventType())});
        return eventId;
    }

    public final EventId onGestureEventReceived(int i, MotionEvent motionEvent) {
        long eventTime;
        EventId eventId = new EventId(SystemClock.uptimeMillis(), 8, 0, i);
        String[] strArr = {"GestureEvent-gesture_detection"};
        if (trackEvents()) {
            onEventReceived(eventId, strArr);
            if (motionEvent == null) {
                eventTime = SystemClock.uptimeMillis();
            } else {
                eventTime = motionEvent.getEventTime();
            }
            EventData recentEvent = getRecentEvent(eventId);
            if (recentEvent != null && trackEvents()) {
                GestureEventData gestureEventData = new GestureEventData();
                gestureEventData.displayId = eventId.displayId;
                gestureEventData.gestureDetectionStartedTime = eventTime;
                recentEvent.gestureEventData = gestureEventData;
            }
        }
        return eventId;
    }

    public final void onGestureLastMotionEventTime(EventId eventId, long j) {
        EventData recentEvent = getRecentEvent(eventId);
        if (recentEvent != null && trackEvents()) {
            GestureEventData gestureEventData = recentEvent.gestureEventData;
            gestureEventData.lastMotionEventTime = j;
            gestureEventData.gestureDecisionTime = SystemClock.uptimeMillis();
        }
    }

    public final void onGestureRecognized(EventId eventId, int i) {
        EventData recentEvent = getRecentEvent(eventId);
        if (recentEvent != null && trackEvents()) {
            GestureEventData gestureEventData = recentEvent.gestureEventData;
            gestureEventData.gestureId = i;
            gestureEventData.gestureDetectedTime = SystemClock.uptimeMillis();
            if (gestureEventData.lastMotionEventTime != -1 && gestureEventData.gestureDecisionTime != -1) {
                notifyLatencyTracker(new Interpreters$$ExternalSyntheticLambda1(gestureEventData, 5));
            }
            removeRecentEvent(eventId);
        }
    }

    public final void onHandlerDone(EventId eventId) {
        EventData recentEvent;
        if (this.computeStatsEnabled && (recentEvent = getRecentEvent(eventId)) != null && recentEvent.timeInlineHandled == -1) {
            long currentTimeMillis = System.currentTimeMillis();
            recentEvent.timeInlineHandled = currentTimeMillis;
            long j = currentTimeMillis - recentEvent.timeReceivedAtTalkback;
            String[] strArr = recentEvent.labels;
            for (int i = 0; i <= 0; i++) {
                getOrCreateStatistics(strArr[i], 1).increment(j);
            }
        }
    }

    public final void removeRecentEvent(EventId eventId) {
        synchronized (this.lockRecentEvents) {
            this.eventIndex.remove(eventId);
            this.eventQueue.remove(eventId);
        }
    }

    public final EventId toEventId(AccessibilityEvent accessibilityEvent) {
        return new EventId(accessibilityEvent.getEventTime(), 0, accessibilityEvent.getEventType());
    }

    public final boolean trackEvents() {
        if (!this.computeStatsEnabled && this.latencyTrackers.isEmpty()) {
            return false;
        }
        return true;
    }

    public final void onEventReceived(EventId eventId, String[] strArr) {
        int size;
        EventData eventData;
        if (trackEvents()) {
            EventData eventData2 = new EventData(System.currentTimeMillis(), SystemClock.uptimeMillis(), strArr, eventId);
            synchronized (this.lockRecentEvents) {
                this.eventQueue.add(eventId);
                this.eventIndex.put(eventId, eventData2);
            }
            while (true) {
                synchronized (this.lockRecentEvents) {
                    size = this.eventQueue.size();
                }
                if (size <= 100) {
                    break;
                }
                synchronized (this.lockRecentEvents) {
                    String str = null;
                    eventData = null;
                    if (!this.eventQueue.isEmpty()) {
                        EventData eventData3 = (EventData) this.eventIndex.remove((EventId) this.eventQueue.remove());
                        if (eventData3 != null) {
                            str = eventData3.getUtteranceId();
                        }
                        if (str != null) {
                            this.utteranceToEvent.remove(eventData3.getUtteranceId());
                        }
                        eventData = eventData3;
                    }
                }
                if (eventData != null) {
                    collectMissingLatencies(eventData);
                }
            }
            if (this.computeStatsEnabled) {
                long uptimeMillis = SystemClock.uptimeMillis() - eventId.eventTimeMs;
                this.allEventStats.increment(uptimeMillis);
                for (int i = 0; i <= 0; i++) {
                    getOrCreateStatistics(eventData2.labels[i], 0).increment(uptimeMillis);
                }
            }
        }
    }
}
