package com.google.mlkit.shared.logger.firelog;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;
import com.google.mlkit.shared.logger.LoggingTransportInterface;
import com.google.mlkit.shared.logger.MLKitLoggingOptions;
import com.google.mlkit.shared.logger.SchemaLogEvent;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FirelogLoggingTransport implements LoggingTransportInterface {
    private Provider jsonTransportProvider;
    private final MLKitLoggingOptions mlKitLoggingOptions;
    private final Provider protoTransportProvider;

    public FirelogLoggingTransport(Context context, MLKitLoggingOptions mLKitLoggingOptions) {
        this.mlKitLoggingOptions = mLKitLoggingOptions;
        if (TransportRuntime.instance == null) {
            synchronized (TransportRuntime.class) {
                if (TransportRuntime.instance == null) {
                    TransportRuntime.instance = new TransportRuntime(context);
                }
            }
        }
        TransportRuntime transportRuntime = TransportRuntime.instance;
        if (transportRuntime != null) {
            AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor(transportRuntime.context);
            if (Collections.singleton(new Encoding("proto")).contains(new Encoding("json"))) {
                this.jsonTransportProvider = new Lazy(new FirelogLoggingTransport$$ExternalSyntheticLambda1(appLifecycleMonitor, 0));
            }
            this.protoTransportProvider = new Lazy(new FirelogLoggingTransport$$ExternalSyntheticLambda1(appLifecycleMonitor, 2));
            return;
        }
        throw new IllegalStateException("Not initialized!");
    }

    static Event getTransportEvent$ar$class_merging(MLKitLoggingOptions mLKitLoggingOptions, SchemaLogEvent schemaLogEvent) {
        int i = schemaLogEvent.priority;
        int firelogEventType = mLKitLoggingOptions.getFirelogEventType();
        if (i != 0) {
            return new Event(schemaLogEvent.toBytes$ar$ds(firelogEventType), 1);
        }
        return new Event(schemaLogEvent.toBytes$ar$ds(firelogEventType), 2);
    }

    @Override // com.google.mlkit.shared.logger.LoggingTransportInterface
    public final void logSdkEvent$ar$class_merging(SchemaLogEvent schemaLogEvent) {
        if (this.mlKitLoggingOptions.getFirelogEventType() == 0) {
            Provider provider = this.jsonTransportProvider;
            if (provider != null) {
                ((WindowTrackerFactory) provider.get()).send(getTransportEvent$ar$class_merging(this.mlKitLoggingOptions, schemaLogEvent));
                return;
            }
            return;
        }
        ((WindowTrackerFactory) this.protoTransportProvider.get()).send(getTransportEvent$ar$class_merging(this.mlKitLoggingOptions, schemaLogEvent));
    }
}
