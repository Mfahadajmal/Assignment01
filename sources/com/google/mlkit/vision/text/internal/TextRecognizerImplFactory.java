package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.mlkit.vision.text.internal.TextRecognizerImplFactory;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextRecognizerImplFactory {
    public final Object TextRecognizerImplFactory$ar$executorSelector;
    public final Object TextRecognizerImplFactory$ar$taskInstanceMap;

    public TextRecognizerImplFactory(TextRecognizerTaskInstanceMap textRecognizerTaskInstanceMap, ExecutorSelector executorSelector) {
        this.TextRecognizerImplFactory$ar$taskInstanceMap = textRecognizerTaskInstanceMap;
        this.TextRecognizerImplFactory$ar$executorSelector = executorSelector;
    }

    public final ByteString build() {
        ((CodedOutputStream) this.TextRecognizerImplFactory$ar$executorSelector).checkNoSpaceLeft();
        return new ByteString.LiteralByteString((byte[]) this.TextRecognizerImplFactory$ar$taskInstanceMap);
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.google.android.gms.common.internal.TelemetryLoggingClient, java.lang.Object] */
    public final synchronized void logThrottledEvent(int i, int i2, long j, long j2) {
        Object obj = this.TextRecognizerImplFactory$ar$taskInstanceMap;
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (((AtomicLong) obj).get() != -1 && elapsedRealtime - ((AtomicLong) this.TextRecognizerImplFactory$ar$taskInstanceMap).get() <= TimeUnit.MINUTES.toMillis(30L)) {
            return;
        }
        this.TextRecognizerImplFactory$ar$executorSelector.log(new TelemetryData(0, Arrays.asList(new MethodInvocation(i, i2, 0, j, j2, null, null, 0, -1)))).addOnFailureListener$ar$ds(new OnFailureListener() { // from class: com.google.mlkit.shared.logger.MLKitTelemetryLogger$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                ((AtomicLong) TextRecognizerImplFactory.this.TextRecognizerImplFactory$ar$taskInstanceMap).set(elapsedRealtime);
            }
        });
    }

    public TextRecognizerImplFactory(int i) {
        byte[] bArr = new byte[i];
        this.TextRecognizerImplFactory$ar$taskInstanceMap = bArr;
        this.TextRecognizerImplFactory$ar$executorSelector = CodedOutputStream.newInstance(bArr);
    }

    public TextRecognizerImplFactory(Context context) {
        this.TextRecognizerImplFactory$ar$taskInstanceMap = new AtomicLong(-1L);
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
        onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = "mlkit:vision";
        this.TextRecognizerImplFactory$ar$executorSelector = new InternalTelemetryLoggingClient(context, onDeviceTextDetectionLoadLogEvent.m224build());
    }
}
