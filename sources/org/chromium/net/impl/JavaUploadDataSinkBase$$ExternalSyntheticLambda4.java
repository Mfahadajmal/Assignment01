package org.chromium.net.impl;

import java.nio.ByteBuffer;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class JavaUploadDataSinkBase$$ExternalSyntheticLambda4 implements JavaUrlRequestUtils$CheckedRunnable {
    public final /* synthetic */ JavaUploadDataSinkBase f$0;
    public final /* synthetic */ boolean f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ JavaUploadDataSinkBase$$ExternalSyntheticLambda4(JavaUploadDataSinkBase javaUploadDataSinkBase, boolean z, int i) {
        this.switching_field = i;
        this.f$0 = javaUploadDataSinkBase;
        this.f$1 = z;
    }

    @Override // org.chromium.net.impl.JavaUrlRequestUtils$CheckedRunnable
    public final void run() {
        if (this.switching_field != 0) {
            JavaUploadDataSinkBase javaUploadDataSinkBase = this.f$0;
            long length = javaUploadDataSinkBase.mUploadProvider.getLength();
            javaUploadDataSinkBase.mTotalBytes = length;
            if (length == 0) {
                javaUploadDataSinkBase.finish();
                return;
            }
            if (length > 0 && length < 8192) {
                javaUploadDataSinkBase.mBuffer = ByteBuffer.allocateDirect(((int) length) + 1);
            } else {
                javaUploadDataSinkBase.mBuffer = ByteBuffer.allocateDirect(8192);
            }
            boolean z = this.f$1;
            javaUploadDataSinkBase.initializeStart(javaUploadDataSinkBase.mTotalBytes);
            if (z) {
                javaUploadDataSinkBase.startRead();
                return;
            } else {
                javaUploadDataSinkBase.mSinkState.set(1);
                javaUploadDataSinkBase.mUploadProvider.rewind(javaUploadDataSinkBase);
                return;
            }
        }
        JavaUploadDataSinkBase javaUploadDataSinkBase2 = this.f$0;
        long j = javaUploadDataSinkBase2.mTotalBytes;
        if (j != -1 && j - javaUploadDataSinkBase2.mWrittenBytes < javaUploadDataSinkBase2.mBuffer.remaining()) {
            javaUploadDataSinkBase2.processUploadError(new IllegalArgumentException(String.format(Locale.getDefault(), "Read upload data length %d exceeds expected length %d", Long.valueOf(javaUploadDataSinkBase2.mWrittenBytes + javaUploadDataSinkBase2.mBuffer.remaining()), Long.valueOf(javaUploadDataSinkBase2.mTotalBytes))));
            return;
        }
        boolean z2 = this.f$1;
        if (javaUploadDataSinkBase2.mBuffer.remaining() == 0 && !z2) {
            javaUploadDataSinkBase2.processUploadError(new IllegalStateException("Bytes read can't be zero except for last chunk!"));
            return;
        }
        long processSuccessfulRead = javaUploadDataSinkBase2.mWrittenBytes + javaUploadDataSinkBase2.processSuccessfulRead(javaUploadDataSinkBase2.mBuffer);
        javaUploadDataSinkBase2.mWrittenBytes = processSuccessfulRead;
        long j2 = javaUploadDataSinkBase2.mTotalBytes;
        if (processSuccessfulRead >= j2 && (j2 != -1 || z2)) {
            if (j2 == -1) {
                javaUploadDataSinkBase2.finish();
                return;
            } else if (j2 == processSuccessfulRead) {
                javaUploadDataSinkBase2.finish();
                return;
            } else {
                javaUploadDataSinkBase2.processUploadError(new IllegalArgumentException(String.format(Locale.getDefault(), "Read upload data length %d exceeds expected length %d", Long.valueOf(javaUploadDataSinkBase2.mWrittenBytes), Long.valueOf(javaUploadDataSinkBase2.mTotalBytes))));
                return;
            }
        }
        javaUploadDataSinkBase2.mSinkState.set(0);
        javaUploadDataSinkBase2.readFromProvider();
    }
}
