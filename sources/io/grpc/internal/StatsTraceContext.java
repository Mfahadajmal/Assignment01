package io.grpc.internal;

import com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StatsTraceContext {
    public final AtomicBoolean closed = new AtomicBoolean(false);
    public final OnDeviceImageLabelCreateLogEvent[] tracers$ar$class_merging$ar$class_merging;

    static {
        new StatsTraceContext(new OnDeviceImageLabelCreateLogEvent[0]);
    }

    public StatsTraceContext(OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr) {
        this.tracers$ar$class_merging$ar$class_merging = onDeviceImageLabelCreateLogEventArr;
    }

    public static StatsTraceContext newClientContext(ClientStreamTracer[] clientStreamTracerArr, Attributes attributes, Metadata metadata) {
        StatsTraceContext statsTraceContext = new StatsTraceContext(clientStreamTracerArr);
        for (ClientStreamTracer clientStreamTracer : clientStreamTracerArr) {
            clientStreamTracer.streamCreated$ar$ds(attributes);
        }
        return statsTraceContext;
    }

    public final void clientOutboundHeaders() {
        int i = 0;
        while (true) {
            OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr = this.tracers$ar$class_merging$ar$class_merging;
            if (i < onDeviceImageLabelCreateLogEventArr.length) {
                i++;
            } else {
                return;
            }
        }
    }

    public final void inboundUncompressedSize$ar$ds() {
        int i = 0;
        while (true) {
            OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr = this.tracers$ar$class_merging$ar$class_merging;
            if (i < onDeviceImageLabelCreateLogEventArr.length) {
                OnDeviceImageLabelCreateLogEvent onDeviceImageLabelCreateLogEvent = onDeviceImageLabelCreateLogEventArr[i];
                i++;
            } else {
                return;
            }
        }
    }

    public final void inboundWireSize(long j) {
        int i = 0;
        while (true) {
            OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr = this.tracers$ar$class_merging$ar$class_merging;
            if (i < onDeviceImageLabelCreateLogEventArr.length) {
                onDeviceImageLabelCreateLogEventArr[i].inboundWireSize(j);
                i++;
            } else {
                return;
            }
        }
    }

    public final void outboundMessage$ar$ds() {
        int i = 0;
        while (true) {
            OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr = this.tracers$ar$class_merging$ar$class_merging;
            if (i < onDeviceImageLabelCreateLogEventArr.length) {
                OnDeviceImageLabelCreateLogEvent onDeviceImageLabelCreateLogEvent = onDeviceImageLabelCreateLogEventArr[i];
                i++;
            } else {
                return;
            }
        }
    }

    public final void outboundMessageSent$ar$ds() {
        int i = 0;
        while (true) {
            OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr = this.tracers$ar$class_merging$ar$class_merging;
            if (i < onDeviceImageLabelCreateLogEventArr.length) {
                OnDeviceImageLabelCreateLogEvent onDeviceImageLabelCreateLogEvent = onDeviceImageLabelCreateLogEventArr[i];
                i++;
            } else {
                return;
            }
        }
    }

    public final void outboundUncompressedSize$ar$ds() {
        int i = 0;
        while (true) {
            OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr = this.tracers$ar$class_merging$ar$class_merging;
            if (i < onDeviceImageLabelCreateLogEventArr.length) {
                OnDeviceImageLabelCreateLogEvent onDeviceImageLabelCreateLogEvent = onDeviceImageLabelCreateLogEventArr[i];
                i++;
            } else {
                return;
            }
        }
    }

    public final void outboundWireSize(long j) {
        int i = 0;
        while (true) {
            OnDeviceImageLabelCreateLogEvent[] onDeviceImageLabelCreateLogEventArr = this.tracers$ar$class_merging$ar$class_merging;
            if (i < onDeviceImageLabelCreateLogEventArr.length) {
                onDeviceImageLabelCreateLogEventArr[i].outboundWireSize(j);
                i++;
            } else {
                return;
            }
        }
    }
}
