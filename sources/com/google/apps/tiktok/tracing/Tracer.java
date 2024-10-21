package com.google.apps.tiktok.tracing;

import android.os.Build;
import android.util.Log;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.stitch.util.SystemProperties;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.apps.tiktok.tracing.SpanExtras;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.flogger.context.ContextDataProvider;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tracer {
    public static final ThreadLocal CURRENT_LEGACY;
    static final AppLifecycleMonitor ENABLE_SYSTRACE$ar$class_merging$ar$class_merging$ar$class_merging;
    private static final ImmutableSet GLOBAL_EXEMPTED_PREFIXES;
    public static final Runnable TRACER_SET_ASYNC_RUNNABLE;
    public static final int TRACE_DELIMITER_LENGTH = 4;
    public static final Object UNSET_ASYNC_TRACE;
    public static final WeakHashMap allThreadStates;
    public static final Deque asyncCurrent;
    private static final ImmutableSet exemptedPrefixes;
    public static final Deque traceQueue;

    /* compiled from: PG */
    /* renamed from: com.google.apps.tiktok.tracing.Tracer$1 */
    /* loaded from: classes.dex */
    final class AnonymousClass1 extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        protected final /* bridge */ /* synthetic */ Object initialValue() {
            ThreadUtil.isMainThread();
            ThreadState threadState = new ThreadState();
            Thread currentThread = Thread.currentThread();
            synchronized (Tracer.allThreadStates) {
                Tracer.allThreadStates.put(currentThread, threadState);
            }
            return threadState;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ApiHelperForSdk29 {
        public static boolean isTraceEnabled() {
            boolean isEnabled;
            isEnabled = android.os.Trace.isEnabled();
            return isEnabled;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class EndedNoTraceException extends IllegalStateException {
        public EndedNoTraceException(String str) {
            super(str);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class EndedWrongTraceException extends IllegalStateException {
        public EndedWrongTraceException(String str) {
            super(str);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ThreadState {
        boolean enableSystrace = false;
        Trace trace = null;
        final ContextDataProvider externalStorage$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ThreadStateLocal extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        protected final /* bridge */ /* synthetic */ Object initialValue() {
            return (ThreadState) Tracer.CURRENT_LEGACY.get();
        }
    }

    static {
        SingletonImmutableSet singletonImmutableSet = new SingletonImmutableSet("android.support.v4.app.FragmentViewLifecycleOwner.handleLifecycleEvent");
        GLOBAL_EXEMPTED_PREFIXES = singletonImmutableSet;
        exemptedPrefixes = singletonImmutableSet;
        ENABLE_SYSTRACE$ar$class_merging$ar$class_merging$ar$class_merging = new AppLifecycleMonitor((byte[]) null, (byte[]) null);
        allThreadStates = new WeakHashMap();
        new ThreadStateLocal();
        CURRENT_LEGACY = new ThreadLocal() { // from class: com.google.apps.tiktok.tracing.Tracer.1
            @Override // java.lang.ThreadLocal
            protected final /* bridge */ /* synthetic */ Object initialValue() {
                ThreadUtil.isMainThread();
                ThreadState threadState = new ThreadState();
                Thread currentThread = Thread.currentThread();
                synchronized (Tracer.allThreadStates) {
                    Tracer.allThreadStates.put(currentThread, threadState);
                }
                return threadState;
            }
        };
        traceQueue = new ArrayDeque();
        asyncCurrent = new ArrayDeque();
        UNSET_ASYNC_TRACE = new Object();
        TRACER_SET_ASYNC_RUNNABLE = new BrailleIme$21$$ExternalSyntheticLambda1(7);
    }

    public static SpanEndSignal beginSpan(String str) {
        return beginSpan$ar$edu$7f8f730_0(str, 1, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS, true);
    }

    public static SpanEndSignal beginSpan$ar$edu$7f8f730_0(String str, int i, SpanExtras spanExtras, boolean z) {
        boolean z2;
        Trace trace;
        ThreadState currentThreadState = getCurrentThreadState();
        Trace trace2 = currentThreadState.trace;
        if (trace2 == SkipTrace.INSTANCE) {
            trace2 = null;
            set(currentThreadState, null);
            z2 = true;
        } else {
            z2 = false;
        }
        if (trace2 == null) {
            MissingTraceSpan missingTraceSpan = new MissingTraceSpan(str, spanExtras, z);
            boolean isExempted = isExempted(missingTraceSpan.exception);
            trace = missingTraceSpan;
            if (isExempted) {
                trace = new NoopTrace(NoopTrace.ROOT_NOOP_TRACE_ID, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS);
            } else if (z) {
                checkTrace$ar$ds$c243405c_0(true);
                trace = missingTraceSpan;
            }
        } else if (trace2 instanceof ErrorTrace) {
            trace = ((ErrorTrace) trace2).createChildTrace(str, spanExtras, z);
        } else {
            trace = trace2.createChildTrace$ar$ds(str, spanExtras);
        }
        set(currentThreadState, trace);
        return new SpanEndSignal(trace, z2);
    }

    private static void beginSystraceSection(String str) {
        if (str.length() > 127) {
            str = str.substring(0, BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE);
        }
        android.os.Trace.beginSection(str);
    }

    public static void checkTrace$ar$ds$c243405c_0(boolean z) {
        IllegalStateException illegalStateException;
        if (TraceCheckingFlag.isEnabled()) {
            Trace trace = get();
            if (trace != null && !(trace instanceof SkipTrace)) {
                if (trace instanceof ErrorTrace) {
                    illegalStateException = new IllegalStateException("Was supposed to have a trace - did you forget to propagate or create one? See this exception's cause for the last place a trace was missing. See http://go/tiktok-tracing for more details.", ((ErrorTrace) trace).getException());
                } else {
                    illegalStateException = null;
                }
            } else {
                illegalStateException = new IllegalStateException("Was supposed to have a trace - did you forget to propagate or create one? See http://go/tiktok-tracing for more details.");
            }
            if (illegalStateException != null && !isExempted(illegalStateException)) {
                if (z) {
                    Log.e("Tracer", "Missing trace", illegalStateException);
                    return;
                }
                throw illegalStateException;
            }
        }
    }

    private static void enterWithParents(Trace trace) {
        if (trace.getParent() != null) {
            enterWithParents(trace.getParent());
        }
        beginSystraceSection(trace.getName());
    }

    private static void exitWithParents(Trace trace) {
        android.os.Trace.endSection();
        if (trace.getParent() != null) {
            exitWithParents(trace.getParent());
        }
    }

    public static Trace get() {
        return getCurrentThreadState().trace;
    }

    public static ThreadState getCurrentThreadState() {
        return (ThreadState) CURRENT_LEGACY.get();
    }

    public static Trace getOrCreateDebug() {
        Trace trace = get();
        if (trace != null) {
            return trace;
        }
        MissingRootTrace missingRootTrace = new MissingRootTrace();
        if (isExempted(missingRootTrace.exception)) {
            return new NoopTrace(NoopTrace.ROOT_NOOP_TRACE_ID, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS);
        }
        return missingRootTrace;
    }

    private static boolean isExempted(Throwable th) {
        if (exemptedPrefixes.isEmpty()) {
            return false;
        }
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            UnmodifiableIterator listIterator = exemptedPrefixes.listIterator();
            while (listIterator.hasNext()) {
                if (stackTraceElement.toString().startsWith((String) listIterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Trace set(ThreadState threadState, Trace trace) {
        Trace trace2;
        boolean equals;
        Trace trace3 = threadState.trace;
        if (trace3 == trace) {
            return trace;
        }
        if (trace3 == null) {
            if (Build.VERSION.SDK_INT >= 29) {
                equals = ApiHelperForSdk29.isTraceEnabled();
            } else {
                Object obj = ENABLE_SYSTRACE$ar$class_merging$ar$class_merging$ar$class_merging.AppLifecycleMonitor$ar$tracker;
                Method method = SystemProperties.sGetStringMethod;
                String str = "false";
                try {
                    str = (String) SystemProperties.sGetStringMethod.invoke(null, "tiktok_systrace", "false");
                } catch (Exception e) {
                    Log.e("SystemProperties", "get error", e);
                }
                equals = "true".equals(str);
            }
            threadState.enableSystrace = equals;
        }
        if (threadState.enableSystrace) {
            if (trace3 != null) {
                if (trace != null) {
                    if (trace3.getParent() == trace) {
                        android.os.Trace.endSection();
                    } else if (trace3 == trace.getParent()) {
                        beginSystraceSection(trace.getName());
                    } else {
                        trace2 = trace;
                    }
                } else {
                    trace2 = null;
                }
                exitWithParents(trace3);
            } else {
                trace2 = trace;
            }
            if (trace2 != null) {
                enterWithParents(trace2);
            }
        }
        if (trace == null) {
            trace = null;
        }
        threadState.trace = trace;
        ContextDataProvider contextDataProvider = threadState.externalStorage$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        return trace3;
    }

    public static void set$ar$ds$76df68d1_0(Trace trace) {
        set(getCurrentThreadState(), trace);
    }
}
