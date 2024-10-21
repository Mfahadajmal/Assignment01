package io.grpc.okhttp;

import com.google.mlkit.logging.schema.OnDeviceImageQualityAnalysisLogEvent;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import io.grpc.util.MultiChildLoadBalancer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExceptionHandlingFrameWriter implements FrameWriter {
    private static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private final MultiChildLoadBalancer.AcceptResolvedAddrRetVal frameLogger$ar$class_merging = new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(Level.FINE, OkHttpClientTransport.class);
    private final FrameWriter frameWriter;
    private final TransportExceptionHandler transportExceptionHandler;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TransportExceptionHandler {
        void onException(Throwable th);
    }

    public ExceptionHandlingFrameWriter(TransportExceptionHandler transportExceptionHandler, FrameWriter frameWriter) {
        this.transportExceptionHandler = transportExceptionHandler;
        this.frameWriter = frameWriter;
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void ackSettings(Settings settings) {
        MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = this.frameLogger$ar$class_merging;
        if (acceptResolvedAddrRetVal.isEnabled()) {
            ((Logger) acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren).logp((Level) acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status, "io.grpc.okhttp.OkHttpFrameLogger", "logSettingsAck", OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(2).concat(" SETTINGS: ack=true"));
        }
        try {
            this.frameWriter.ackSettings(settings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Level level;
        try {
            this.frameWriter.close();
        } catch (IOException e) {
            Class<?> cls = e.getClass();
            Logger logger = log;
            if (cls.equals(IOException.class)) {
                level = Level.FINE;
            } else {
                level = Level.INFO;
            }
            logger.logp(level, "io.grpc.okhttp.ExceptionHandlingFrameWriter", "close", "Failed closing connection", (Throwable) e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void connectionPreface() {
        try {
            this.frameWriter.connectionPreface();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void data(boolean z, int i, Buffer buffer, int i2) {
        this.frameLogger$ar$class_merging.logData$ar$edu(2, i, buffer, i2, z);
        try {
            this.frameWriter.data(z, i, buffer, i2);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void goAway$ar$ds(ErrorCode errorCode, byte[] bArr) {
        this.frameLogger$ar$class_merging.logGoAway$ar$edu(2, 0, errorCode, ByteString.of(bArr));
        try {
            this.frameWriter.goAway$ar$ds(errorCode, bArr);
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final int maxDataLength() {
        return this.frameWriter.maxDataLength();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void ping(boolean z, int i, int i2) {
        long j = i2;
        long j2 = i;
        if (z) {
            MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = this.frameLogger$ar$class_merging;
            long j3 = j2 << 32;
            long j4 = j & 4294967295L;
            if (acceptResolvedAddrRetVal.isEnabled()) {
                long j5 = j4 | j3;
                Object obj = acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
                Logger logger = (Logger) obj;
                logger.logp((Level) acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status, "io.grpc.okhttp.OkHttpFrameLogger", "logPingAck", OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(2) + " PING: ack=true bytes=" + j5);
            }
        } else {
            this.frameLogger$ar$class_merging.logPing$ar$edu(2, (j & 4294967295L) | (j2 << 32));
        }
        try {
            this.frameWriter.ping(z, i, i2);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void rstStream(int i, ErrorCode errorCode) {
        this.frameLogger$ar$class_merging.logRstStream$ar$edu(2, i, errorCode);
        try {
            this.frameWriter.rstStream(i, errorCode);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void settings(Settings settings) {
        this.frameLogger$ar$class_merging.logSettings$ar$edu(2, settings);
        try {
            this.frameWriter.settings(settings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void synStream$ar$ds(boolean z, int i, List list) {
        try {
            this.frameWriter.synStream$ar$ds(z, i, list);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void windowUpdate(int i, long j) {
        this.frameLogger$ar$class_merging.logWindowsUpdate$ar$edu(2, i, j);
        try {
            this.frameWriter.windowUpdate(i, j);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }
}
