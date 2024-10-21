package io.grpc.okhttp;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import io.perfmark.Impl;
import io.perfmark.PerfMark;
import java.io.IOException;
import java.net.Socket;
import okio.Buffer;
import okio.Sink;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AsyncSink implements Sink {
    private boolean controlFramesExceeded;
    public int controlFramesInWrite;
    public int queuedControlFrames;
    private final SerializingExecutor serializingExecutor;
    public Sink sink;
    public Socket socket;
    public final ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler;
    public final Object lock = new Object();
    public final Buffer buffer = new Buffer();
    public boolean writeEnqueued = false;
    public boolean flushEnqueued = false;
    private boolean closed = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LimitControlFramesWriter extends ForwardingFrameWriter {
        public LimitControlFramesWriter(FrameWriter frameWriter) {
            super(frameWriter);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public final void ackSettings(Settings settings) {
            AsyncSink.this.controlFramesInWrite++;
            this.delegate.ackSettings(settings);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public final void ping(boolean z, int i, int i2) {
            if (z) {
                AsyncSink.this.controlFramesInWrite++;
            }
            this.delegate.ping(z, i, i2);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public final void rstStream(int i, ErrorCode errorCode) {
            AsyncSink.this.controlFramesInWrite++;
            this.delegate.rstStream(i, errorCode);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class WriteRunnable implements Runnable {
        public WriteRunnable() {
        }

        public abstract void doRun();

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (AsyncSink.this.sink != null) {
                    doRun();
                    return;
                }
                throw new IOException("Unable to perform write due to unavailable sink.");
            } catch (Exception e) {
                AsyncSink.this.transportExceptionHandler.onException(e);
            }
        }
    }

    public AsyncSink(SerializingExecutor serializingExecutor, ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler) {
        serializingExecutor.getClass();
        this.serializingExecutor = serializingExecutor;
        this.transportExceptionHandler = transportExceptionHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void becomeConnected(Sink sink, Socket socket) {
        boolean z;
        if (this.sink == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "AsyncSink's becomeConnected should only be called once.");
        this.sink = sink;
        socket.getClass();
        this.socket = socket;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.serializingExecutor.execute(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 6));
    }

    @Override // okio.Sink, java.io.Flushable
    public final void flush() {
        if (!this.closed) {
            int i = PerfMark.PerfMark$ar$NoOp;
            synchronized (this.lock) {
                if (this.flushEnqueued) {
                    return;
                }
                this.flushEnqueued = true;
                this.serializingExecutor.execute(new WriteRunnable() { // from class: io.grpc.okhttp.AsyncSink.2
                    final OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage link$ar$class_merging;

                    {
                        OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage;
                        identifiedLanguage = Impl.NO_LINK$ar$class_merging;
                        this.link$ar$class_merging = identifiedLanguage;
                    }

                    @Override // io.grpc.okhttp.AsyncSink.WriteRunnable
                    public final void doRun() {
                        Buffer buffer = new Buffer();
                        int i2 = PerfMark.PerfMark$ar$NoOp;
                        synchronized (AsyncSink.this.lock) {
                            Buffer buffer2 = AsyncSink.this.buffer;
                            buffer.write(buffer2, buffer2.size);
                            AsyncSink.this.flushEnqueued = false;
                        }
                        AsyncSink.this.sink.write(buffer, buffer.size);
                        AsyncSink.this.sink.flush();
                    }
                });
                return;
            }
        }
        throw new IOException("closed");
    }

    @Override // okio.Sink
    public final void write(Buffer buffer, long j) {
        if (!this.closed) {
            int i = PerfMark.PerfMark$ar$NoOp;
            synchronized (this.lock) {
                this.buffer.write(buffer, j);
                int i2 = this.queuedControlFrames + this.controlFramesInWrite;
                this.queuedControlFrames = i2;
                boolean z = false;
                this.controlFramesInWrite = 0;
                if (!this.controlFramesExceeded && i2 > 10000) {
                    this.controlFramesExceeded = true;
                    z = true;
                } else {
                    if (!this.writeEnqueued && !this.flushEnqueued && this.buffer.completeSegmentByteCount() > 0) {
                        this.writeEnqueued = true;
                    }
                    return;
                }
                if (z) {
                    try {
                        this.socket.close();
                        return;
                    } catch (IOException e) {
                        this.transportExceptionHandler.onException(e);
                        return;
                    }
                }
                this.serializingExecutor.execute(new WriteRunnable() { // from class: io.grpc.okhttp.AsyncSink.1
                    final OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage link$ar$class_merging;

                    {
                        OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage;
                        identifiedLanguage = Impl.NO_LINK$ar$class_merging;
                        this.link$ar$class_merging = identifiedLanguage;
                    }

                    @Override // io.grpc.okhttp.AsyncSink.WriteRunnable
                    public final void doRun() {
                        int i3;
                        Buffer buffer2 = new Buffer();
                        int i4 = PerfMark.PerfMark$ar$NoOp;
                        synchronized (AsyncSink.this.lock) {
                            Buffer buffer3 = AsyncSink.this.buffer;
                            buffer2.write(buffer3, buffer3.completeSegmentByteCount());
                            AsyncSink asyncSink = AsyncSink.this;
                            asyncSink.writeEnqueued = false;
                            i3 = asyncSink.queuedControlFrames;
                        }
                        AsyncSink.this.sink.write(buffer2, buffer2.size);
                        synchronized (AsyncSink.this.lock) {
                            AsyncSink.this.queuedControlFrames -= i3;
                        }
                    }
                });
                return;
            }
        }
        throw new IOException("closed");
    }
}
