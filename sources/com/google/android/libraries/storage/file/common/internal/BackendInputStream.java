package com.google.android.libraries.storage.file.common.internal;

import com.google.android.libraries.storage.file.common.FileConvertible;
import java.io.File;
import java.io.FileInputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackendInputStream extends ForwardingInputStream implements FileConvertible {
    private final File file;
    private final FileInputStream fileInputStream;

    public BackendInputStream(FileInputStream fileInputStream, File file) {
        super(fileInputStream);
        this.fileInputStream = fileInputStream;
        this.file = file;
    }

    @Override // com.google.android.libraries.storage.file.common.FileConvertible
    public final File toFile() {
        return this.file;
    }
}
