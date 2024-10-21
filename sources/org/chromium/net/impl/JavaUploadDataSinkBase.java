package org.chromium.net.impl;

import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCall$$ExternalSyntheticLambda10;
import io.grpc.internal.RetriableStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JavaUploadDataSinkBase extends UploadDataSink {
    public ByteBuffer mBuffer;
    public final Executor mExecutor;
    private WritableByteChannel mOutputChannel;
    private final AtomicBoolean mOutputChannelClosed;
    public int mReadCount;
    public final AtomicInteger mSinkState;
    public long mTotalBytes;
    public final VersionSafeCallbacks$UploadDataProviderWrapper mUploadProvider;
    private final HttpURLConnection mUrlConnection;
    private OutputStream mUrlConnectionOutputStream;
    private final Executor mUserUploadExecutor;
    public long mWrittenBytes;
    final /* synthetic */ JavaUrlRequest this$0;

    public JavaUploadDataSinkBase(Executor executor, Executor executor2, UploadDataProvider uploadDataProvider) {
        this.mSinkState = new AtomicInteger(3);
        this.mUserUploadExecutor = new AsyncInterceptorsClientCall$$ExternalSyntheticLambda10(this, executor, 2);
        this.mExecutor = executor2;
        this.mUploadProvider = new VersionSafeCallbacks$UploadDataProviderWrapper(uploadDataProvider);
    }

    public final void closeOutputChannel() {
        if (this.mOutputChannel != null && this.mOutputChannelClosed.compareAndSet(false, true)) {
            this.mOutputChannel.close();
        }
    }

    public final void executeOnUploadExecutor(JavaUrlRequestUtils$CheckedRunnable javaUrlRequestUtils$CheckedRunnable) {
        try {
            this.mUserUploadExecutor.execute(getUploadErrorSettingRunnable(javaUrlRequestUtils$CheckedRunnable));
        } catch (RejectedExecutionException e) {
            processUploadError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void finish() {
        closeOutputChannel();
        this.this$0.fireGetHeaders();
    }

    protected final Runnable getErrorSettingRunnable(JavaUrlRequestUtils$CheckedRunnable javaUrlRequestUtils$CheckedRunnable) {
        return new RetriableStream.Sublistener.AnonymousClass4(this.this$0, javaUrlRequestUtils$CheckedRunnable, 10, (byte[]) null);
    }

    protected final Runnable getUploadErrorSettingRunnable(JavaUrlRequestUtils$CheckedRunnable javaUrlRequestUtils$CheckedRunnable) {
        return new RetriableStream.Sublistener.AnonymousClass4(this.this$0, javaUrlRequestUtils$CheckedRunnable, 11, (byte[]) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void initializeRead() {
        if (this.mOutputChannel == null) {
            this.this$0.mAdditionalStatusDetails = 10;
            this.mUrlConnection.setDoOutput(true);
            this.mUrlConnection.connect();
            this.this$0.mAdditionalStatusDetails = 12;
            OutputStream outputStream = this.mUrlConnection.getOutputStream();
            this.mUrlConnectionOutputStream = outputStream;
            this.mOutputChannel = Channels.newChannel(outputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void initializeStart(long j) {
        if (j > 0) {
            this.mUrlConnection.setFixedLengthStreamingMode(j);
        } else {
            this.mUrlConnection.setChunkedStreamingMode(8192);
        }
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onReadError(Exception exc) {
        processUploadError(exc);
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onReadSucceeded(boolean z) {
        if (this.mSinkState.compareAndSet(0, 2)) {
            this.mExecutor.execute(getErrorSettingRunnable(new JavaUploadDataSinkBase$$ExternalSyntheticLambda4(this, z, 0)));
        } else {
            throw new IllegalStateException("onReadSucceeded() called when not awaiting a read result; in state: " + this.mSinkState.get());
        }
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onRewindError(Exception exc) {
        processUploadError(exc);
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onRewindSucceeded() {
        if (this.mSinkState.compareAndSet(1, 2)) {
            startRead();
            return;
        }
        throw new IllegalStateException("onRewindSucceeded() called when not awaiting a rewind; in state: " + this.mSinkState.get());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int processSuccessfulRead(ByteBuffer byteBuffer) {
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            i += this.mOutputChannel.write(byteBuffer);
        }
        this.mUrlConnectionOutputStream.flush();
        return i;
    }

    public final void processUploadError(Throwable th) {
        this.this$0.enterUploadErrorState(th);
    }

    public final void readFromProvider() {
        executeOnUploadExecutor(new JavaUploadDataSinkBase$$ExternalSyntheticLambda3(this, 1));
    }

    public final void startRead() {
        this.mExecutor.execute(getErrorSettingRunnable(new JavaUploadDataSinkBase$$ExternalSyntheticLambda3(this, 0)));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavaUploadDataSinkBase(JavaUrlRequest javaUrlRequest, Executor executor, Executor executor2, HttpURLConnection httpURLConnection, VersionSafeCallbacks$UploadDataProviderWrapper versionSafeCallbacks$UploadDataProviderWrapper) {
        this(executor, executor2, versionSafeCallbacks$UploadDataProviderWrapper);
        this.this$0 = javaUrlRequest;
        this.mOutputChannelClosed = new AtomicBoolean(false);
        this.mUrlConnection = httpURLConnection;
    }
}
