package io.grpc;

import android.text.TextUtils;
import androidx.core.text.BidiFormatter;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent;
import io.grpc.CallOptions;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ClientStreamTracer extends OnDeviceImageLabelCreateLogEvent {
    public static final CallOptions.Key NAME_RESOLUTION_DELAYED = new CallOptions.Key("io.grpc.ClientStreamTracer.NAME_RESOLUTION_DELAYED", null);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StreamInfo {
        private final CallOptions callOptions;
        private final boolean isTransparentRetry;
        private final int previousAttempts;

        public StreamInfo(CallOptions callOptions, int i, boolean z) {
            callOptions.getClass();
            this.callOptions = callOptions;
            this.previousAttempts = i;
            this.isTransparentRetry = z;
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("callOptions", this.callOptions);
            return stringHelper.add("previousAttempts", this.previousAttempts).add("isTransparentRetry", this.isTransparentRetry).toString();
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public Object ClientStreamTracer$StreamInfo$Builder$ar$callOptions;
            public boolean isTransparentRetry;
            public int previousAttempts;

            public Builder(byte[] bArr) {
                this.isTransparentRetry = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
                this.ClientStreamTracer$StreamInfo$Builder$ar$callOptions = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
                this.previousAttempts = 2;
            }

            public Builder() {
                this.ClientStreamTracer$StreamInfo$Builder$ar$callOptions = CallOptions.DEFAULT;
            }
        }
    }

    public void inboundHeaders() {
    }

    public void streamCreated$ar$ds(Attributes attributes) {
    }
}
