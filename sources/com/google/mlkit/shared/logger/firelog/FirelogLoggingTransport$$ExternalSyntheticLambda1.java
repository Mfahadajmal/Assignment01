package com.google.mlkit.shared.logger.firelog;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.firebase.inject.Provider;
import com.google.mlkit.common.model.RemoteModelManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FirelogLoggingTransport$$ExternalSyntheticLambda1 implements Provider {
    public final /* synthetic */ Object FirelogLoggingTransport$$ExternalSyntheticLambda1$ar$f$0$ar$class_merging$ee42672b_0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FirelogLoggingTransport$$ExternalSyntheticLambda1(Object obj, int i) {
        this.switching_field = i;
        this.FirelogLoggingTransport$$ExternalSyntheticLambda1$ar$f$0$ar$class_merging$ee42672b_0 = obj;
    }

    @Override // com.google.firebase.inject.Provider
    public final Object get() {
        int i = this.switching_field;
        if (i != 0) {
            final int i2 = 1;
            if (i != 1) {
                return ((AppLifecycleMonitor) this.FirelogLoggingTransport$$ExternalSyntheticLambda1$ar$f$0$ar$class_merging$ee42672b_0).getTransport$ar$class_merging$ar$ds$ar$class_merging(new Encoding("proto"), new Transformer() { // from class: com.google.mlkit.shared.logger.firelog.FirelogLoggingTransport$$ExternalSyntheticLambda3
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return obj;
                    }
                });
            }
            return RemoteModelManager.RemoteModelManagerRegistration.instantiate((String) this.FirelogLoggingTransport$$ExternalSyntheticLambda1$ar$f$0$ar$class_merging$ee42672b_0);
        }
        final int i3 = 0;
        return ((AppLifecycleMonitor) this.FirelogLoggingTransport$$ExternalSyntheticLambda1$ar$f$0$ar$class_merging$ee42672b_0).getTransport$ar$class_merging$ar$ds$ar$class_merging(new Encoding("json"), new Transformer() { // from class: com.google.mlkit.shared.logger.firelog.FirelogLoggingTransport$$ExternalSyntheticLambda3
            @Override // com.google.android.datatransport.Transformer
            public final Object apply(Object obj) {
                return obj;
            }
        });
    }
}
