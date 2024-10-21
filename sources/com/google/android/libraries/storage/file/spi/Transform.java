package com.google.android.libraries.storage.file.spi;

import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Transform {
    String encode$ar$ds();

    String name();

    InputStream wrapForRead$ar$ds();

    OutputStream wrapForWrite$ar$ds();
}
