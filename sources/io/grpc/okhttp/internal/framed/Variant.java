package io.grpc.okhttp.internal.framed;

import io.grpc.okhttp.internal.framed.Http2;
import okio.BufferedSink;
import okio.BufferedSource;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Variant {
    Http2.Reader newReader$ar$class_merging$ar$ds(BufferedSource bufferedSource);

    FrameWriter newWriter$ar$ds(BufferedSink bufferedSink);
}
