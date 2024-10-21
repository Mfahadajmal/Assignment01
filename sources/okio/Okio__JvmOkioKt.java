package okio;

import com.google.mlkit.logging.schema.ToxicityDetectionCreateEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Okio__JvmOkioKt {
    public static final /* synthetic */ int Okio__JvmOkioKt$ar$NoOp = 0;

    static {
        Logger.getLogger("okio.Okio");
    }

    public static final Sink sink(Socket socket) {
        socket.getClass();
        final SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.getClass();
        final OutputStreamSink outputStreamSink = new OutputStreamSink(outputStream, socketAsyncTimeout);
        return new Sink() { // from class: okio.AsyncTimeout$sink$1
            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                try {
                    outputStreamSink.close();
                } catch (IOException e) {
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public final void flush() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                try {
                    outputStreamSink.flush();
                } catch (IOException e) {
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            public final String toString() {
                return "AsyncTimeout.sink(" + outputStreamSink + ")";
            }

            @Override // okio.Sink
            public final void write(Buffer buffer, long j) {
                ToxicityDetectionCreateEvent.checkOffsetAndCount(buffer.size, 0L, j);
                while (true) {
                    long j2 = 0;
                    if (j > 0) {
                        Segment segment = buffer.head;
                        segment.getClass();
                        while (true) {
                            if (j2 >= 65536) {
                                break;
                            }
                            j2 += segment.limit - segment.pos;
                            if (j2 >= j) {
                                j2 = j;
                                break;
                            } else {
                                segment = segment.next;
                                segment.getClass();
                            }
                        }
                        AsyncTimeout asyncTimeout = AsyncTimeout.this;
                        try {
                            try {
                                outputStreamSink.write(buffer, j2);
                                asyncTimeout.exit();
                                j -= j2;
                            } catch (IOException e) {
                                asyncTimeout.exit();
                                throw e;
                            }
                        } catch (Throwable th) {
                            asyncTimeout.exit();
                            throw th;
                        }
                    } else {
                        return;
                    }
                }
            }
        };
    }

    public static final Source source(Socket socket) {
        socket.getClass();
        final SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout();
        InputStream inputStream = socket.getInputStream();
        inputStream.getClass();
        final InputStreamSource inputStreamSource = new InputStreamSource(inputStream, socketAsyncTimeout);
        return new Source() { // from class: okio.AsyncTimeout$source$1
            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                try {
                    inputStreamSource.close();
                } catch (IOException e) {
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Source
            public final long read(Buffer buffer, long j) {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                try {
                    return inputStreamSource.read(buffer, j);
                } catch (IOException e) {
                    throw e;
                } finally {
                    asyncTimeout.exit();
                }
            }

            public final String toString() {
                return "AsyncTimeout.source(" + inputStreamSource + ")";
            }
        };
    }
}
