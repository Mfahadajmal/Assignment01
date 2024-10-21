package com.google.android.libraries.performance.primes.transmitter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.CollectionFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LifeboatReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("MetricSnapshot") && intent.hasExtra("Transmitters")) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("MetricSnapshot");
            byteArrayExtra.getClass();
            try {
                MetricSnapshot metricSnapshot = (MetricSnapshot) GeneratedMessageLite.parseFrom(MetricSnapshot.DEFAULT_INSTANCE, byteArrayExtra, ExtensionRegistryLite.getGeneratedRegistry());
                BroadcastReceiver.PendingResult goAsync = goAsync();
                String[] stringArrayExtra = intent.getStringArrayExtra("Transmitters");
                stringArrayExtra.getClass();
                ArrayList arrayList = new ArrayList(stringArrayExtra.length);
                for (String str : stringArrayExtra) {
                    try {
                        Constructor<?> declaredConstructor = Class.forName(str).getDeclaredConstructor(null);
                        declaredConstructor.setAccessible(true);
                        arrayList.add(((MetricSnapshotTransmitter) declaredConstructor.newInstance(null)).transmit(context, metricSnapshot));
                    } catch (Throwable th) {
                        Log.e("PrimesLifeboatReceiver", String.format("Unable to transmit the crash using %s.", str), th);
                    }
                }
                CollectionFuture collectionFuture = new CollectionFuture(ImmutableList.copyOf((Iterable) arrayList), null);
                goAsync.getClass();
                collectionFuture.addListener(new TrainingActivity$$ExternalSyntheticLambda1(goAsync, 19), DirectExecutor.INSTANCE);
            } catch (InvalidProtocolBufferException e) {
                Log.e("PrimesLifeboatReceiver", "Unable to parse the payload of MetricSnapshot.", e);
            }
        }
    }
}
