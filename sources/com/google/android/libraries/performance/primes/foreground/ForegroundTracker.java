package com.google.android.libraries.performance.primes.foreground;

import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.common.base.Supplier;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ForegroundTracker {
    public DebouncedForegroundSignalAdapter debouncedForegroundSignalAdapter;
    public final ForegroundSignalMultiplexer foregroundSignalMultiplexer = new ForegroundSignalMultiplexer();
    public ProcessImportanceForegroundSignalAdapter processImportanceForegroundSignalAdapter;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ForegroundSignalMultiplexer extends AbstractForegroundSignalAdapter {
        public final List listeners = new CopyOnWriteArrayList();

        @Override // com.google.android.libraries.performance.primes.foreground.AbstractForegroundSignalAdapter
        public final void emitBackgroundSignal(NoPiiString noPiiString) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/foreground/ForegroundTracker$ForegroundSignalMultiplexer", "emitBackgroundSignal", 148, "ForegroundTracker.java")).log("App transition to background");
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((ForegroundListener) it.next()).onAppToBackground(noPiiString);
            }
        }

        @Override // com.google.android.libraries.performance.primes.foreground.AbstractForegroundSignalAdapter
        public final void emitForegroundSignal(NoPiiString noPiiString) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/foreground/ForegroundTracker$ForegroundSignalMultiplexer", "emitForegroundSignal", 140, "ForegroundTracker.java")).log("App transition to foreground");
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((ForegroundListener) it.next()).onAppToForeground(noPiiString);
            }
        }
    }

    public ForegroundTracker(final AppLifecycleMonitor appLifecycleMonitor, final ProcessLifecycleOwner processLifecycleOwner, BatteryMetricService batteryMetricService, BatteryMetricService batteryMetricService2, Provider provider) {
        provider.getClass();
        final Supplier memoize = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(provider, 3));
        final int i = 1;
        ProcessImportanceForegroundSignalAdapter create$ar$ds$84b3e942_0 = BatteryMetricService.create$ar$ds$84b3e942_0(new ForegroundListener(this) { // from class: com.google.android.libraries.performance.primes.foreground.ForegroundTracker.2
            final /* synthetic */ ForegroundTracker this$0;

            {
                this.this$0 = this;
            }

            private final void class_merging$unregisterSelf() {
                ProcessImportanceForegroundSignalAdapter processImportanceForegroundSignalAdapter = this.this$0.processImportanceForegroundSignalAdapter;
                if (processImportanceForegroundSignalAdapter != null) {
                    ((AppLifecycleMonitor) appLifecycleMonitor).unregister(processImportanceForegroundSignalAdapter);
                    this.this$0.processImportanceForegroundSignalAdapter = null;
                }
            }

            private final void unregisterSelf() {
                DebouncedForegroundSignalAdapter debouncedForegroundSignalAdapter = this.this$0.debouncedForegroundSignalAdapter;
                if (debouncedForegroundSignalAdapter != null) {
                    ((ProcessLifecycleOwner) appLifecycleMonitor).listeners.remove(debouncedForegroundSignalAdapter);
                    this.this$0.debouncedForegroundSignalAdapter = null;
                }
            }

            @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
            public final void onAppToBackground(NoPiiString noPiiString) {
                if (i != 0) {
                    if (((Boolean) memoize.get()).booleanValue()) {
                        class_merging$unregisterSelf();
                        return;
                    } else {
                        this.this$0.foregroundSignalMultiplexer.observeBackgroundSignal(noPiiString);
                        return;
                    }
                }
                if (((Boolean) memoize.get()).booleanValue()) {
                    this.this$0.foregroundSignalMultiplexer.observeBackgroundSignal(noPiiString);
                } else {
                    unregisterSelf();
                }
            }

            @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
            public final void onAppToForeground(NoPiiString noPiiString) {
                if (i != 0) {
                    if (((Boolean) memoize.get()).booleanValue()) {
                        class_merging$unregisterSelf();
                        return;
                    } else {
                        this.this$0.foregroundSignalMultiplexer.observeForegroundSignal(noPiiString);
                        return;
                    }
                }
                if (((Boolean) memoize.get()).booleanValue()) {
                    this.this$0.foregroundSignalMultiplexer.observeForegroundSignal(noPiiString);
                } else {
                    unregisterSelf();
                }
            }
        });
        this.processImportanceForegroundSignalAdapter = create$ar$ds$84b3e942_0;
        appLifecycleMonitor.register(create$ar$ds$84b3e942_0);
        final int i2 = 0;
        DebouncedForegroundSignalAdapter create$ar$ds$c34404f_0 = BatteryMetricService.create$ar$ds$c34404f_0(new ForegroundListener(this) { // from class: com.google.android.libraries.performance.primes.foreground.ForegroundTracker.2
            final /* synthetic */ ForegroundTracker this$0;

            {
                this.this$0 = this;
            }

            private final void class_merging$unregisterSelf() {
                ProcessImportanceForegroundSignalAdapter processImportanceForegroundSignalAdapter = this.this$0.processImportanceForegroundSignalAdapter;
                if (processImportanceForegroundSignalAdapter != null) {
                    ((AppLifecycleMonitor) processLifecycleOwner).unregister(processImportanceForegroundSignalAdapter);
                    this.this$0.processImportanceForegroundSignalAdapter = null;
                }
            }

            private final void unregisterSelf() {
                DebouncedForegroundSignalAdapter debouncedForegroundSignalAdapter = this.this$0.debouncedForegroundSignalAdapter;
                if (debouncedForegroundSignalAdapter != null) {
                    ((ProcessLifecycleOwner) processLifecycleOwner).listeners.remove(debouncedForegroundSignalAdapter);
                    this.this$0.debouncedForegroundSignalAdapter = null;
                }
            }

            @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
            public final void onAppToBackground(NoPiiString noPiiString) {
                if (i2 != 0) {
                    if (((Boolean) memoize.get()).booleanValue()) {
                        class_merging$unregisterSelf();
                        return;
                    } else {
                        this.this$0.foregroundSignalMultiplexer.observeBackgroundSignal(noPiiString);
                        return;
                    }
                }
                if (((Boolean) memoize.get()).booleanValue()) {
                    this.this$0.foregroundSignalMultiplexer.observeBackgroundSignal(noPiiString);
                } else {
                    unregisterSelf();
                }
            }

            @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
            public final void onAppToForeground(NoPiiString noPiiString) {
                if (i2 != 0) {
                    if (((Boolean) memoize.get()).booleanValue()) {
                        class_merging$unregisterSelf();
                        return;
                    } else {
                        this.this$0.foregroundSignalMultiplexer.observeForegroundSignal(noPiiString);
                        return;
                    }
                }
                if (((Boolean) memoize.get()).booleanValue()) {
                    this.this$0.foregroundSignalMultiplexer.observeForegroundSignal(noPiiString);
                } else {
                    unregisterSelf();
                }
            }
        });
        this.debouncedForegroundSignalAdapter = create$ar$ds$c34404f_0;
        processLifecycleOwner.listeners.add(create$ar$ds$c34404f_0);
    }

    public final void register(ForegroundListener foregroundListener) {
        this.foregroundSignalMultiplexer.listeners.add(foregroundListener);
    }

    public final void unregister(ForegroundListener foregroundListener) {
        this.foregroundSignalMultiplexer.listeners.remove(foregroundListener);
    }
}
