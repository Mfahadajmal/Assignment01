package com.google.apps.tiktok.tracing;

import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExceptionTracer {
    private static final WeakHashMap exceptions = new WeakHashMap();
    private static final WeakHashMap exceptionsWithTraceStack = new WeakHashMap();
    public static final boolean isEnabled = true;

    public static SplitInstallSharedPreferences getTracedException$ar$class_merging(Throwable th) {
        ContextDataProvider.checkState(true, "Trace uncaught exception is disabled.");
        synchronized (exceptions) {
            Throwable th2 = th;
            while (th2 != null) {
                try {
                    if (exceptions.containsKey(th2)) {
                        break;
                    }
                    th2 = th2.getCause();
                } catch (Throwable th3) {
                    throw th3;
                }
            }
            if (th2 == null) {
                return null;
            }
            WeakHashMap weakHashMap = exceptions;
            TraceInfo traceInfo = (TraceInfo) weakHashMap.get(th2);
            weakHashMap.put(th, traceInfo);
            return new SplitInstallSharedPreferences(traceInfo, null);
        }
    }

    public static void reportException(Throwable th) {
        Throwable th2;
        boolean z;
        synchronized (exceptionsWithTraceStack) {
            th2 = th;
            while (th2 != null) {
                if (exceptionsWithTraceStack.containsKey(th2)) {
                    break;
                } else {
                    th2 = th2.getCause();
                }
            }
            WeakHashMap weakHashMap = exceptionsWithTraceStack;
            if (th2 != null) {
                z = true;
            } else {
                z = false;
            }
            weakHashMap.put(th, Boolean.valueOf(z));
        }
        if (th2 == null && getTracedException$ar$class_merging(th) == null) {
            ArrayList arrayList = new ArrayList();
            for (Trace trace = Tracer.get(); trace != null; trace = trace.getParent()) {
                arrayList.add(trace);
            }
            ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(arrayList.size());
            ImmutableList.Builder builderWithExpectedSize2 = ImmutableList.builderWithExpectedSize(arrayList.size());
            for (Trace trace2 : ContextDataProvider.reverse(arrayList)) {
                builderWithExpectedSize2.add$ar$ds$4f674a09_0(trace2.getName());
                builderWithExpectedSize.add$ar$ds$4f674a09_0(trace2.getExtras());
            }
            ImmutableList build = builderWithExpectedSize2.build();
            builderWithExpectedSize.build();
            TraceInfo traceInfo = new TraceInfo(build);
            WeakHashMap weakHashMap2 = exceptions;
            synchronized (weakHashMap2) {
                weakHashMap2.put(th, traceInfo);
            }
        }
    }
}
