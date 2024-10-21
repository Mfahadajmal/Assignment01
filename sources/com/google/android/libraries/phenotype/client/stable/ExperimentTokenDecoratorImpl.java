package com.google.android.libraries.phenotype.client.stable;

import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExperimentTokenDecoratorImpl extends DefaultExperimentTokenDecorator {
    public static final AtomicBoolean registeredModifier = new AtomicBoolean(false);
    public static final ConcurrentMap accountScopedConfigPackageKeyMap = new ConcurrentHashMap();
    public static final ConcurrentMap deviceScopedConfigPackageKeyMap = new ConcurrentHashMap();
    public static final ConcurrentMap accountScopedLogSourceKeyMap = new ConcurrentHashMap();
    public static final ConcurrentMap deviceScopedLogSourceKeyMap = new ConcurrentHashMap();
    public static final ClearcutLogger.EventModifier eventModifier = new ClearcutLogger.EventModifier() { // from class: com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda4
        @Override // com.google.android.gms.clearcut.ClearcutLogger.EventModifier
        public final ClearcutLogger.LogEventBuilder apply(ClearcutLogger.LogEventBuilder logEventBuilder) {
            AtomicBoolean atomicBoolean = ExperimentTokenDecoratorImpl.registeredModifier;
            if (!((ClearcutLogger) logEventBuilder.logger).isDeidentified()) {
                ExperimentTokenDecoratorImpl.modifyLogEvent(logEventBuilder, new ConfigurationsModule$$ExternalSyntheticLambda0(logEventBuilder, 16), new AiCoreClientImpl$$ExternalSyntheticLambda2(logEventBuilder, 4));
                ExperimentTokenDecoratorImpl.modifyLogEvent(logEventBuilder, new ConfigurationsModule$$ExternalSyntheticLambda0(logEventBuilder, 17), new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(0));
            }
            return logEventBuilder;
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:108:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x018a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void modifyLogEvent(com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder r19, com.google.common.base.Supplier r20, com.google.common.base.Function r21) {
        /*
            Method dump skipped, instructions count: 626
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl.modifyLogEvent(com.google.android.gms.clearcut.ClearcutLogger$LogEventBuilder, com.google.common.base.Supplier, com.google.common.base.Function):void");
    }

    public final void putOrAddToSet$ar$ds(ExperimentTokenData experimentTokenData, Object obj, ConcurrentMap concurrentMap) {
        Set set = (Set) concurrentMap.putIfAbsent(obj, new SingletonImmutableSet(experimentTokenData));
        if (set != null) {
            ImmutableSet.Builder builder = new ImmutableSet.Builder();
            builder.addAll$ar$ds$9575dc1a_0(set);
            builder.add$ar$ds$187ad64f_0(experimentTokenData);
            concurrentMap.put(obj, builder.build());
        }
    }
}
