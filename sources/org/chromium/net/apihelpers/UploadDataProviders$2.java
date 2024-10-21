package org.chromium.net.apihelpers;

import android.os.ParcelFileDescriptor;
import j$.nio.channels.DesugarChannels;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UploadDataProviders$2 implements UploadDataProviders$FileChannelProvider {
    final /* synthetic */ Object UploadDataProviders$2$ar$val$fd;
    private final /* synthetic */ int switching_field;

    public UploadDataProviders$2(Object obj, int i) {
        this.switching_field = i;
        this.UploadDataProviders$2$ar$val$fd = obj;
    }

    @Override // org.chromium.net.apihelpers.UploadDataProviders$FileChannelProvider
    public final FileChannel getChannel() {
        if (this.switching_field != 0) {
            return DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(new FileInputStream((File) this.UploadDataProviders$2$ar$val$fd).getChannel());
        }
        if (((ParcelFileDescriptor) this.UploadDataProviders$2$ar$val$fd).getStatSize() != -1) {
            return new ParcelFileDescriptor.AutoCloseInputStream((ParcelFileDescriptor) this.UploadDataProviders$2$ar$val$fd).getChannel();
        }
        ((ParcelFileDescriptor) this.UploadDataProviders$2$ar$val$fd).close();
        Object obj = this.UploadDataProviders$2$ar$val$fd;
        Objects.toString(obj);
        throw new IllegalArgumentException("Not a file: ".concat(String.valueOf(obj)));
    }
}
