package org.chromium.net;

import android.os.ParcelFileDescriptor;
import com.google.mlkit.logging.schema.acceleration.GPUInfo;
import java.io.File;
import java.nio.ByteBuffer;
import org.chromium.net.apihelpers.UploadDataProviders$2;
import org.chromium.net.apihelpers.UploadDataProviders$ByteBufferUploadProvider;
import org.chromium.net.apihelpers.UploadDataProviders$FileUploadProvider;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public final class UploadDataProviders {
    private UploadDataProviders() {
    }

    public static UploadDataProvider create(ParcelFileDescriptor parcelFileDescriptor) {
        return new UploadDataProviders$FileUploadProvider(new UploadDataProviders$2(parcelFileDescriptor, 0));
    }

    public static UploadDataProvider create(File file) {
        return new UploadDataProviders$FileUploadProvider(new UploadDataProviders$2(file, 1));
    }

    public static UploadDataProvider create(ByteBuffer byteBuffer) {
        return new UploadDataProviders$ByteBufferUploadProvider(byteBuffer.slice());
    }

    public static UploadDataProvider create(byte[] bArr) {
        return GPUInfo.create(bArr, 0, bArr.length);
    }

    public static UploadDataProvider create(byte[] bArr, int i, int i2) {
        return GPUInfo.create(bArr, i, i2);
    }
}
