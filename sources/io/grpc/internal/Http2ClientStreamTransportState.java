package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Codec;
import io.grpc.InternalMetadata;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.RetriableStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Http2ClientStreamTransportState extends AbstractStream.TransportState {
    private static final Metadata.Key HTTP2_STATUS;
    private static final InternalMetadata.TrustedAsciiMarshaller HTTP_STATUS_MARSHALLER;
    private Charset errorCharset;
    private boolean headersReceived;
    private Status transportError;
    private Metadata transportErrorMetadata;

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.Http2ClientStreamTransportState$1, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass1 implements InternalMetadata.TrustedAsciiMarshaller {
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public final /* synthetic */ Object parseAsciiString(byte[] bArr) {
            if (this.switching_field != 0) {
                return bArr;
            }
            if (bArr.length >= 3) {
                return Integer.valueOf(((bArr[0] - 48) * 100) + ((bArr[1] - 48) * 10) + (bArr[2] - 48));
            }
            throw new NumberFormatException("Malformed status code ".concat(new String(bArr, InternalMetadata.US_ASCII)));
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public final /* synthetic */ byte[] toAsciiString(Object obj) {
            if (this.switching_field != 0) {
                return (byte[]) obj;
            }
            throw new UnsupportedOperationException();
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        HTTP_STATUS_MARSHALLER = anonymousClass1;
        HTTP2_STATUS = InternalMetadata.keyOf(":status", anonymousClass1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Http2ClientStreamTransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer, CallOptions callOptions) {
        super(i, statsTraceContext, transportTracer, null);
        this.errorCharset = Charsets.UTF_8;
    }

    private static Charset extractCharset(Metadata metadata) {
        String str = (String) metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (str != null) {
            try {
                return Charset.forName(str.split("charset=", 2)[r2.length - 1].trim());
            } catch (Exception unused) {
            }
        }
        return Charsets.UTF_8;
    }

    private static void stripTransportDetails(Metadata metadata) {
        metadata.discardAll(HTTP2_STATUS);
        metadata.discardAll(InternalStatus.CODE_KEY);
        metadata.discardAll(InternalStatus.MESSAGE_KEY);
    }

    private static final Status validateInitialMetadata$ar$ds(Metadata metadata) {
        char charAt;
        Integer num = (Integer) metadata.get(HTTP2_STATUS);
        if (num == null) {
            return Status.INTERNAL.withDescription("Missing HTTP status code");
        }
        String str = (String) metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (str != null && str.length() >= 16) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (lowerCase.startsWith("application/grpc") && (lowerCase.length() == 16 || (charAt = lowerCase.charAt(16)) == '+' || charAt == ';')) {
                return null;
            }
        }
        return GrpcUtil.httpStatusToGrpcStatus(num.intValue()).augmentDescription("invalid content-type: ".concat(String.valueOf(str)));
    }

    protected abstract void http2ProcessingFailed(Status status, boolean z, Metadata metadata);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void transportDataReceived(ReadableBuffer readableBuffer, boolean z) {
        Status status = this.transportError;
        boolean z2 = false;
        if (status != null) {
            Charset charset = this.errorCharset;
            ReadableBuffer readableBuffer2 = ReadableBuffers.EMPTY_BUFFER;
            charset.getClass();
            int readableBytes = readableBuffer.readableBytes();
            byte[] bArr = new byte[readableBytes];
            readableBuffer.readBytes(bArr, 0, readableBytes);
            this.transportError = status.augmentDescription("DATA-----------------------------\n".concat(new String(bArr, charset)));
            readableBuffer.close();
            if (this.transportError.description.length() > 1000 || z) {
                http2ProcessingFailed(this.transportError, false, this.transportErrorMetadata);
                return;
            }
            return;
        }
        if (!this.headersReceived) {
            http2ProcessingFailed(Status.INTERNAL.withDescription("headers not received before payload"), false, new Metadata());
            return;
        }
        int readableBytes2 = readableBuffer.readableBytes();
        try {
            if (this.statusReported) {
                AbstractClientStream.log.logp(Level.INFO, "io.grpc.internal.AbstractClientStream$TransportState", "inboundDataReceived", "Received data on closed stream");
                readableBuffer.close();
            } else {
                try {
                    this.deframer.deframe(readableBuffer);
                } catch (Throwable th) {
                    try {
                        deframeFailed(th);
                    } catch (Throwable th2) {
                        th = th2;
                        if (z2) {
                            readableBuffer.close();
                        }
                        throw th;
                    }
                }
            }
            if (z) {
                if (readableBytes2 > 0) {
                    this.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on non-empty DATA frame from server");
                } else {
                    this.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on empty DATA frame from server");
                }
                Metadata metadata = new Metadata();
                this.transportErrorMetadata = metadata;
                transportReportStatus(this.transportError, false, metadata);
            }
        } catch (Throwable th3) {
            th = th3;
            z2 = true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void transportHeadersReceived(Metadata metadata) {
        Codec codec;
        Status status = this.transportError;
        if (status != null) {
            this.transportError = status.augmentDescription("headers: ".concat(metadata.toString()));
            return;
        }
        try {
            if (this.headersReceived) {
                this.transportError = Status.INTERNAL.withDescription("Received headers twice");
            } else {
                Integer num = (Integer) metadata.get(HTTP2_STATUS);
                if (num == null || num.intValue() < 100 || num.intValue() >= 200) {
                    this.headersReceived = true;
                    Status validateInitialMetadata$ar$ds = validateInitialMetadata$ar$ds(metadata);
                    this.transportError = validateInitialMetadata$ar$ds;
                    if (validateInitialMetadata$ar$ds != null) {
                        this.transportError = validateInitialMetadata$ar$ds.augmentDescription("headers: ".concat(metadata.toString()));
                        this.transportErrorMetadata = metadata;
                        this.errorCharset = extractCharset(metadata);
                        return;
                    }
                    stripTransportDetails(metadata);
                    ContextDataProvider.checkState(!this.statusReported, "Received headers on closed stream");
                    for (OnDeviceImageLabelCreateLogEvent onDeviceImageLabelCreateLogEvent : this.statsTraceCtx.tracers$ar$class_merging$ar$class_merging) {
                        ((ClientStreamTracer) onDeviceImageLabelCreateLogEvent).inboundHeaders();
                    }
                    String str = (String) metadata.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                    if (str != null) {
                        RetriableStream.HedgingPlan hedgingPlan = (RetriableStream.HedgingPlan) this.decompressorRegistry.decompressors.get(str);
                        if (hedgingPlan != null) {
                            codec = hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis;
                        } else {
                            codec = null;
                        }
                        if (codec == null) {
                            deframeFailed(new StatusRuntimeException(Status.INTERNAL.withDescription(String.format("Can't find decompressor for %s", str))));
                        } else if (codec != Codec.Identity.NONE) {
                            this.deframer.setDecompressor(codec);
                        }
                    }
                    this.listener.headersRead(metadata);
                }
            }
            Status status2 = this.transportError;
            if (status2 != null) {
                this.transportError = status2.augmentDescription("headers: ".concat(metadata.toString()));
                this.transportErrorMetadata = metadata;
                this.errorCharset = extractCharset(metadata);
            }
        } catch (Throwable th) {
            Status status3 = this.transportError;
            if (status3 != null) {
                this.transportError = status3.augmentDescription("headers: ".concat(metadata.toString()));
                this.transportErrorMetadata = metadata;
                this.errorCharset = extractCharset(metadata);
            }
            throw th;
        }
    }

    public final void transportTrailersReceived(Metadata metadata) {
        Status withDescription;
        Status augmentDescription;
        Status status = this.transportError;
        if (status == null && !this.headersReceived) {
            status = validateInitialMetadata$ar$ds(metadata);
            this.transportError = status;
            if (status != null) {
                this.transportErrorMetadata = metadata;
            }
        }
        if (status == null) {
            Status status2 = (Status) metadata.get(InternalStatus.CODE_KEY);
            if (status2 != null) {
                augmentDescription = status2.withDescription((String) metadata.get(InternalStatus.MESSAGE_KEY));
            } else if (this.headersReceived) {
                augmentDescription = Status.UNKNOWN.withDescription("missing GRPC status in response");
            } else {
                Integer num = (Integer) metadata.get(HTTP2_STATUS);
                if (num != null) {
                    withDescription = GrpcUtil.httpStatusToGrpcStatus(num.intValue());
                } else {
                    withDescription = Status.INTERNAL.withDescription("missing HTTP status code");
                }
                augmentDescription = withDescription.augmentDescription("missing GRPC status, inferred error from HTTP status code");
            }
            stripTransportDetails(metadata);
            if (this.statusReported) {
                AbstractClientStream.log.logp(Level.INFO, "io.grpc.internal.AbstractClientStream$TransportState", "inboundTrailersReceived", "Received trailers on closed stream:\n {1}\n {2}", new Object[]{augmentDescription, metadata});
                return;
            }
            for (OnDeviceImageLabelCreateLogEvent onDeviceImageLabelCreateLogEvent : this.statsTraceCtx.tracers$ar$class_merging$ar$class_merging) {
            }
            transportReportStatus(augmentDescription, false, metadata);
            return;
        }
        Status augmentDescription2 = status.augmentDescription("trailers: ".concat(metadata.toString()));
        this.transportError = augmentDescription2;
        http2ProcessingFailed(augmentDescription2, false, this.transportErrorMetadata);
    }
}
