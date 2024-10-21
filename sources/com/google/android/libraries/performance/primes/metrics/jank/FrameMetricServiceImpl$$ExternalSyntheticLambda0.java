package com.google.android.libraries.performance.primes.metrics.jank;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceImpl;
import com.google.common.android.concurrent.ContextHolder;
import com.google.common.android.concurrent.FutureCallbackViewModel;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.flogger.GoogleLogger;
import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FrameMetricServiceImpl$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ Object FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FrameMetricServiceImpl$$ExternalSyntheticLambda0(Object obj, Context context, int i) {
        this.switching_field = i;
        this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0 = obj;
        this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1 = context;
    }

    /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Object, dagger.Lazy] */
    /* JADX WARN: Type inference failed for: r0v29, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v0, types: [javax.inject.Provider, java.lang.Object] */
    @Override // com.google.common.base.Supplier
    public final Object get() {
        String replace;
        Object of;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        FutureCallbackViewModel futureCallbackViewModel = (FutureCallbackViewModel) ((ViewModelProvider) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0.get()).get(FutureCallbackViewModel.class);
                        futureCallbackViewModel.contextHolder = (ContextHolder) ((OptionalMethod) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1).OptionalMethod$ar$methodName;
                        return futureCallbackViewModel;
                    }
                    return ((BaseEncoding) ((OptionalMethod) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1).OptionalMethod$ar$methodName).encode(((ByteString) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0).toByteArray());
                }
                int i2 = TimerMetricServiceImpl.TimerMetricServiceImpl$ar$NoOp;
                return ((AppLifecycleMonitor) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1).create(((TimerConfigurations) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0.get()).getSamplingProbability());
            }
            Object obj = this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1;
            synchronized (this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0) {
                String currentProcessName = ProcessStats.getCurrentProcessName();
                String str = currentProcessName + ".trace";
                File file = new File(((Context) obj).getFilesDir(), _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(currentProcessName, "primes_profiling_"));
                if (!file.exists() && !file.mkdir()) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService", "lambda$new$0", BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL, "CpuProfilingService.java")).log("Could not create directory");
                    of = Absent.INSTANCE;
                } else {
                    File file2 = new File(file, str);
                    file2.deleteOnExit();
                    CpuProfilingService.clearFileAndSwallowResultingExceptions(file2);
                    of = Optional.of(file2);
                }
            }
            return of;
        }
        replace = ((PerfettoTraceConfigurations$JankPerfettoConfigurations) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0.get()).triggerNameFormatString_.replace("%PACKAGE_NAME%", ((Context) this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1).getPackageName());
        return replace;
    }

    public /* synthetic */ FrameMetricServiceImpl$$ExternalSyntheticLambda0(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$1 = obj;
        this.FrameMetricServiceImpl$$ExternalSyntheticLambda0$ar$f$0 = obj2;
    }
}
