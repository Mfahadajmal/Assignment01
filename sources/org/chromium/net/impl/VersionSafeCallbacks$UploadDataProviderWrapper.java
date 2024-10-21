package org.chromium.net.impl;

import java.nio.ByteBuffer;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VersionSafeCallbacks$UploadDataProviderWrapper extends UploadDataProvider {
    private final UploadDataProvider mWrappedProvider;

    public VersionSafeCallbacks$UploadDataProviderWrapper(UploadDataProvider uploadDataProvider) {
        this.mWrappedProvider = uploadDataProvider;
    }

    @Override // org.chromium.net.UploadDataProvider, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.mWrappedProvider.close();
    }

    @Override // org.chromium.net.UploadDataProvider
    public final long getLength() {
        return this.mWrappedProvider.getLength();
    }

    @Override // org.chromium.net.UploadDataProvider
    public final void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
        this.mWrappedProvider.read(uploadDataSink, byteBuffer);
    }

    @Override // org.chromium.net.UploadDataProvider
    public final void rewind(UploadDataSink uploadDataSink) {
        this.mWrappedProvider.rewind(uploadDataSink);
    }
}
