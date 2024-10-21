package com.google.android.libraries.phenotype.client.stable;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.libraries.performance.primes.transmitter.LifeboatReceiver;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.base.Function;
import com.google.protobuf.MapFieldLite;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PhenotypeAccountStore$$ExternalSyntheticLambda4 implements Function {
    public final /* synthetic */ Object PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$0;
    public final /* synthetic */ Object PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PhenotypeAccountStore$$ExternalSyntheticLambda4(ClearcutMetricTransmitter clearcutMetricTransmitter, SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric, int i) {
        this.switching_field = i;
        this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$0 = clearcutMetricTransmitter;
        this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$1 = systemHealthProto$SystemHealthMetric;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return ((Channel) obj).newCall((MethodDescriptor) this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$1, (CallOptions) this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$0);
            }
            SchedulerOptions.Builder builder = (SchedulerOptions.Builder) ((MetricSnapshot) obj).toBuilder();
            builder.copyOnWrite();
            MetricSnapshot metricSnapshot = (MetricSnapshot) builder.instance;
            Object obj2 = this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$1;
            obj2.getClass();
            metricSnapshot.systemHealthMetric_ = (SystemHealthProto$SystemHealthMetric) obj2;
            metricSnapshot.bitField0_ = 1 | metricSnapshot.bitField0_;
            MetricSnapshot metricSnapshot2 = (MetricSnapshot) builder.build();
            ClearcutMetricTransmitter clearcutMetricTransmitter = (ClearcutMetricTransmitter) this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$0;
            String[] strArr = {clearcutMetricTransmitter.snapshotTransmitter.getClass().getName()};
            Intent intent = new Intent();
            Context context = clearcutMetricTransmitter.context;
            intent.setComponent(new ComponentName(context, (Class<?>) LifeboatReceiver.class));
            intent.setPackage(context.getPackageName());
            intent.putExtra("Transmitters", strArr);
            intent.putExtra("MetricSnapshot", metricSnapshot2.toByteArray());
            context.sendBroadcast(intent);
            return null;
        }
        Accounts accounts = (Accounts) obj;
        ConcurrentMap concurrentMap = PhenotypeAccountStore.accountCommitterByPackage;
        AccountList accountList = AccountList.DEFAULT_INSTANCE;
        MapFieldLite mapFieldLite = accounts.accountLists_;
        Object obj3 = this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$0;
        if (mapFieldLite.containsKey(obj3)) {
            accountList = (AccountList) mapFieldLite.get(obj3);
        }
        Object obj4 = this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$1;
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) accountList.toBuilder();
        if (!Collections.unmodifiableList(((AccountList) builder2.instance).values_).contains(obj4)) {
            builder2.addValues$ar$ds((String) obj4);
        }
        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) accounts.toBuilder();
        builder2.copyOnWrite();
        AccountList accountList2 = (AccountList) builder2.instance;
        accountList2.bitField0_ = 1 | accountList2.bitField0_;
        accountList2.latestAccount_ = (String) obj4;
        builder3.putAccountLists$ar$ds((String) obj3, (AccountList) builder2.build());
        return (Accounts) builder3.build();
    }

    public /* synthetic */ PhenotypeAccountStore$$ExternalSyntheticLambda4(MethodDescriptor methodDescriptor, CallOptions callOptions, int i) {
        this.switching_field = i;
        this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$1 = methodDescriptor;
        this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$0 = callOptions;
    }

    public /* synthetic */ PhenotypeAccountStore$$ExternalSyntheticLambda4(String str, String str2, int i) {
        this.switching_field = i;
        this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$0 = str;
        this.PhenotypeAccountStore$$ExternalSyntheticLambda4$ar$f$1 = "";
    }
}
