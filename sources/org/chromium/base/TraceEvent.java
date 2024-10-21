package org.chromium.base;

import J.N;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import io.grpc.internal.Http2Ping;
import java.util.ArrayList;
import org.chromium.base.EarlyTraceEvent;
import org.chromium.base.task.PostTask;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TraceEvent implements AutoCloseable {
    public static volatile boolean sEnabled;
    public static boolean sEventNameFilteringEnabled;
    public static volatile boolean sUiThreadReady;
    private final String mName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class BasicLooperMonitor implements Printer {
        private String mCurrentTarget;

        public void beginHandling(String str) {
            int indexOf;
            String str2;
            int indexOf2;
            String str3;
            boolean enabled = EarlyTraceEvent.enabled();
            if (!TraceEvent.sEnabled && !enabled) {
                return;
            }
            if (TraceEvent.sEventNameFilteringEnabled) {
                str3 = "Looper.dispatch: EVENT_NAME_FILTERED";
            } else {
                int indexOf3 = str.indexOf(40, 18);
                if (indexOf3 == -1) {
                    indexOf = -1;
                } else {
                    indexOf = str.indexOf(41, indexOf3);
                }
                String str4 = "";
                if (indexOf == -1) {
                    str2 = "";
                } else {
                    str2 = str.substring(indexOf3 + 1, indexOf);
                }
                int indexOf4 = str.indexOf(BrailleInputEvent.CMD_PREVIOUS_READING_CONTROL, 18);
                if (indexOf4 == -1) {
                    indexOf2 = -1;
                } else {
                    indexOf2 = str.indexOf(58, indexOf4);
                }
                if (indexOf2 == -1) {
                    indexOf2 = str.length();
                }
                if (indexOf4 != -1) {
                    str4 = str.substring(indexOf4 + 2, indexOf2);
                }
                str3 = "Looper.dispatch: " + str2 + "(" + str4 + ")";
            }
            this.mCurrentTarget = str3;
            if (TraceEvent.sEnabled) {
                N.M_y76mct(this.mCurrentTarget);
            } else {
                EarlyTraceEvent.begin$ar$ds();
            }
        }

        public void endHandling(String str) {
            boolean enabled = EarlyTraceEvent.enabled();
            if ((TraceEvent.sEnabled || enabled) && this.mCurrentTarget != null) {
                if (TraceEvent.sEnabled) {
                    N.MLJecZJ9();
                } else {
                    EarlyTraceEvent.end$ar$ds();
                }
            }
            this.mCurrentTarget = null;
        }

        @Override // android.util.Printer
        public final void println(String str) {
            if (str.startsWith(">")) {
                beginHandling(str);
            } else {
                endHandling(str);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class IdleTracingLooperMonitor extends BasicLooperMonitor implements MessageQueue.IdleHandler {
        private boolean mIdleMonitorAttached;
        private long mLastIdleStartedAt;
        private long mLastWorkStartedAt;
        private int mNumIdlesSeen;
        private int mNumTasksSeen;
        private int mNumTasksSinceLastIdle;

        private final void syncIdleMonitoring() {
            if (TraceEvent.sEnabled && !this.mIdleMonitorAttached) {
                this.mLastIdleStartedAt = SystemClock.elapsedRealtime();
                Looper.myQueue().addIdleHandler(this);
                this.mIdleMonitorAttached = true;
            } else if (this.mIdleMonitorAttached && !TraceEvent.sEnabled) {
                Looper.myQueue().removeIdleHandler(this);
                this.mIdleMonitorAttached = false;
            }
        }

        private static void traceAndLog(int i, String str) {
            if (TraceEvent.sEnabled) {
                N.ML40H8ed("TraceEvent.LooperMonitor:IdleStats", str);
            }
            Log.println(i, "TraceEvt_LooperMonitor", str);
        }

        @Override // org.chromium.base.TraceEvent.BasicLooperMonitor
        public final void beginHandling(String str) {
            if (this.mNumTasksSinceLastIdle == 0) {
                TraceEvent.end("Looper.queueIdle");
            }
            this.mLastWorkStartedAt = SystemClock.elapsedRealtime();
            syncIdleMonitoring();
            super.beginHandling(str);
        }

        @Override // org.chromium.base.TraceEvent.BasicLooperMonitor
        public final void endHandling(String str) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.mLastWorkStartedAt;
            if (elapsedRealtime > 16) {
                traceAndLog(5, "observed a task that took " + elapsedRealtime + "ms: " + str);
            }
            super.endHandling(str);
            syncIdleMonitoring();
            this.mNumTasksSeen++;
            this.mNumTasksSinceLastIdle++;
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.mLastIdleStartedAt;
            if (j == 0) {
                this.mLastIdleStartedAt = elapsedRealtime;
                j = elapsedRealtime;
            }
            long j2 = elapsedRealtime - j;
            this.mNumIdlesSeen++;
            TraceEvent.begin("Looper.queueIdle", this.mNumTasksSinceLastIdle + " tasks since last idle.");
            if (j2 > 48) {
                traceAndLog(3, this.mNumTasksSeen + " tasks and " + this.mNumIdlesSeen + " idles processed so far, " + this.mNumTasksSinceLastIdle + " tasks bursted and " + j2 + "ms elapsed since last idle");
            }
            this.mLastIdleStartedAt = elapsedRealtime;
            this.mNumTasksSinceLastIdle = 0;
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LooperMonitorHolder {
        public static final BasicLooperMonitor sInstance;

        static {
            BasicLooperMonitor basicLooperMonitor;
            if (CommandLine.getInstance().hasSwitch$ar$ds()) {
                basicLooperMonitor = new IdleTracingLooperMonitor();
            } else {
                basicLooperMonitor = new BasicLooperMonitor();
            }
            sInstance = basicLooperMonitor;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ViewHierarchyDumper implements MessageQueue.IdleHandler {
        public static boolean sEnabled;
        public static ViewHierarchyDumper sInstance;
        private long mLastDumpTs;

        public static void updateEnabledState() {
            PostTask.runOrPostTask$ar$ds(new BrailleIme$21$$ExternalSyntheticLambda1(11));
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.mLastDumpTs;
            if (j == 0 || elapsedRealtime - j > 1000) {
                this.mLastDumpTs = elapsedRealtime;
                if (TraceEvent.sEnabled && N.MnfJQqTB()) {
                    TraceEvent.begin("instantAndroidViewHierarchy", null);
                    ApplicationStatus.isInitialized$ar$ds();
                    ArrayList arrayList = new ArrayList();
                    if (arrayList.isEmpty()) {
                        TraceEvent.end("instantAndroidViewHierarchy");
                        return true;
                    }
                    long hashCode = arrayList.hashCode();
                    PostTask.postTask(0, new Http2Ping.AnonymousClass1(hashCode, arrayList, 3));
                    TraceEvent.end$ar$ds$6d5c4533_0("instantAndroidViewHierarchy", hashCode);
                    return true;
                }
                return true;
            }
            return true;
        }
    }

    private TraceEvent(String str) {
        this.mName = str;
        begin(str, null);
    }

    public static void begin(String str, String str2) {
        EarlyTraceEvent.begin$ar$ds();
        if (sEnabled) {
            N.M9XfPu17(str, str2);
        }
    }

    public static void dumpViewHierarchy(long j, Object obj) {
        ApplicationStatus.isInitialized$ar$ds();
    }

    public static void end(String str) {
        end$ar$ds$6d5c4533_0(str, 0L);
    }

    public static void end$ar$ds$6d5c4533_0(String str, long j) {
        EarlyTraceEvent.end$ar$ds();
        if (sEnabled) {
            N.Mw73xTww(null, j);
        }
    }

    public static TraceEvent scoped(String str) {
        if (!EarlyTraceEvent.enabled() && !sEnabled) {
            return null;
        }
        return new TraceEvent(str);
    }

    public static void setEnabled(boolean z) {
        BasicLooperMonitor basicLooperMonitor = null;
        if (z) {
            synchronized (EarlyTraceEvent.sLock) {
                if (EarlyTraceEvent.enabled()) {
                    throw null;
                }
            }
        }
        if (sEnabled != z) {
            sEnabled = z;
            Looper uiThreadLooper = ThreadUtils.getUiThreadLooper();
            if (z) {
                basicLooperMonitor = LooperMonitorHolder.sInstance;
            }
            uiThreadLooper.setMessageLogging(basicLooperMonitor);
        }
        if (sEnabled) {
            synchronized (EarlyTraceEvent.sLock) {
                if (!EarlyTraceEvent.sActivityStartupEvents.isEmpty()) {
                    for (EarlyTraceEvent.ActivityStartupEvent activityStartupEvent : EarlyTraceEvent.sActivityStartupEvents) {
                        long j = activityStartupEvent.mId;
                        long j2 = activityStartupEvent.mTimeMs;
                        N.MvcVeOsg(0L, 0L);
                    }
                    EarlyTraceEvent.sActivityStartupEvents.clear();
                }
                if (!EarlyTraceEvent.sActivityLaunchCauseEvents.isEmpty()) {
                    for (EarlyTraceEvent.ActivityLaunchCauseEvent activityLaunchCauseEvent : EarlyTraceEvent.sActivityLaunchCauseEvents) {
                        long j3 = activityLaunchCauseEvent.mId;
                        long j4 = activityLaunchCauseEvent.mTimeMs;
                        int i = activityLaunchCauseEvent.mLaunchCause;
                        N.MbWHcONC(0L, 0L, 0);
                    }
                    EarlyTraceEvent.sActivityLaunchCauseEvents.clear();
                }
            }
        }
        if (sUiThreadReady) {
            ViewHierarchyDumper.updateEnabledState();
        }
    }

    public static void setEventNameFilteringEnabled(boolean z) {
        sEventNameFilteringEnabled = z;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        end(this.mName);
    }
}
