package com.google.android.libraries.performance.primes;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.StackSize;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Primes {
    private static final Primes DEFAULT_PRIMES;
    public static final /* synthetic */ int Primes$ar$NoOp = 0;
    private static volatile Primes primes;
    private static volatile boolean warningNotYetLogged;
    public final PrimesApi primesApi;

    static {
        Primes primes2 = new Primes(new NoopPrimesApi());
        DEFAULT_PRIMES = primes2;
        warningNotYetLogged = true;
        primes = primes2;
    }

    public Primes(PrimesApi primesApi) {
        this.primesApi = primesApi;
    }

    static synchronized void cache(Primes primes2) {
        synchronized (Primes.class) {
            if (isCached()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/Primes", "cache", 146, "Primes.java")).log("Primes cached more than once. This call will be ignored.");
            } else {
                primes = primes2;
            }
        }
    }

    public static Primes get() {
        StackSize stackSize;
        if (primes == DEFAULT_PRIMES && warningNotYetLogged) {
            warningNotYetLogged = false;
            GoogleLogger.Api api = (GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning();
            if (Math.random() < 0.01d) {
                stackSize = StackSize.FULL;
            } else {
                stackSize = StackSize.NONE;
            }
            ((GoogleLogger.Api) ((GoogleLogger.Api) api.withStackTrace(stackSize)).withInjectedLogSite("com/google/android/libraries/performance/primes/Primes", "get", 186, "Primes.java")).log("Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.");
        }
        return primes;
    }

    public static synchronized void initialize$ar$class_merging$fd7e8a43_0$ar$ds(DaggerProdInternalComponent.ProdInternalComponentImpl prodInternalComponentImpl) {
        synchronized (Primes.class) {
            if (!ThreadUtil.isMainThread()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/Primes", "initialize", BrailleInputEvent.CMD_TOGGLE_VOICE_FEEDBACK, "Primes.java")).log("Primes.initialize() should only be called from the main thread.");
            }
            if (isCached()) {
                get();
                return;
            }
            Primes primes2 = (Primes) prodInternalComponentImpl.providePrimesProvider.get();
            primes2.primesApi.initialize();
            cache(primes2);
        }
    }

    private static boolean isCached() {
        if (primes != DEFAULT_PRIMES) {
            return true;
        }
        return false;
    }

    public final void recordNetwork(NetworkEvent networkEvent) {
        this.primesApi.recordNetwork(networkEvent);
    }

    public final void startCrashMonitor() {
        this.primesApi.startCrashMonitor();
    }

    public final void startMemoryMonitor() {
        this.primesApi.startMemoryMonitor();
    }

    public final TimerEvent startTimer() {
        return this.primesApi.startTimer();
    }

    public final void stopTimer(TimerEvent timerEvent, NoPiiString noPiiString) {
        this.primesApi.stopTimer$ar$edu$2eed496a_0(timerEvent, noPiiString, null, 1);
    }
}
