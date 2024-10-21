package com.google.android.libraries.storage.file.common.internal;

import com.google.android.libraries.storage.file.common.FileConvertible;
import java.io.File;
import java.io.FileOutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackendOutputStream extends ForwardingOutputStream implements FileConvertible {
    private final File file;
    private final FileOutputStream fileOutputStream;

    public BackendOutputStream(FileOutputStream fileOutputStream, File file) {
        super(fileOutputStream);
        this.fileOutputStream = fileOutputStream;
        this.file = file;
    }

    public final void sync() {
        this.fileOutputStream.getFD().sync();
    }

    @Override // com.google.android.libraries.storage.file.common.FileConvertible
    public final File toFile() {
        return this.file;
    }
}
