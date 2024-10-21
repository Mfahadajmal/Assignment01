package com.google.android.libraries.storage.file.backends;

import android.net.Uri;
import com.google.android.libraries.storage.file.common.LockScope;
import com.google.android.libraries.storage.file.common.internal.BackendInputStream;
import com.google.android.libraries.storage.file.common.internal.BackendOutputStream;
import com.google.android.libraries.storage.file.spi.Backend;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JavaFileBackend implements Backend {
    public JavaFileBackend(byte[] bArr) {
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final void deleteFile(Uri uri) {
        File file$ar$ds = FileUriAdapter.toFile$ar$ds(uri);
        if (!file$ar$ds.isDirectory()) {
            if (!file$ar$ds.delete()) {
                if (!file$ar$ds.exists()) {
                    throw new FileNotFoundException(String.format("%s does not exist", uri));
                }
                throw new IOException(String.format("%s could not be deleted", uri));
            }
            return;
        }
        throw new FileNotFoundException(String.format("%s is a directory", uri));
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final boolean exists(Uri uri) {
        return FileUriAdapter.toFile$ar$ds(uri).exists();
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final String name() {
        return "file";
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final InputStream openForRead(Uri uri) {
        File file$ar$ds = FileUriAdapter.toFile$ar$ds(uri);
        return new BackendInputStream(new FileInputStream(file$ar$ds), file$ar$ds);
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final OutputStream openForWrite(Uri uri) {
        File file$ar$ds = FileUriAdapter.toFile$ar$ds(uri);
        ContextDataProvider.createParentDirs(file$ar$ds);
        return new BackendOutputStream(new FileOutputStream(file$ar$ds), file$ar$ds);
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final void rename(Uri uri, Uri uri2) {
        File file$ar$ds = FileUriAdapter.toFile$ar$ds(uri);
        File file$ar$ds2 = FileUriAdapter.toFile$ar$ds(uri2);
        ContextDataProvider.createParentDirs(file$ar$ds2);
        if (file$ar$ds.renameTo(file$ar$ds2)) {
        } else {
            throw new IOException(String.format("%s could not be renamed to %s", uri, uri2));
        }
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final File toFile(Uri uri) {
        return FileUriAdapter.toFile$ar$ds(uri);
    }

    public JavaFileBackend() {
        new LockScope();
    }
}
