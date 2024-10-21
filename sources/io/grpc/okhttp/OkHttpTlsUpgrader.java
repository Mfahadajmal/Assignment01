package io.grpc.okhttp;

import io.grpc.okhttp.internal.Protocol;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class OkHttpTlsUpgrader {
    public static final /* synthetic */ int OkHttpTlsUpgrader$ar$NoOp = 0;
    static final List TLS_PROTOCOLS = DesugarCollections.unmodifiableList(Arrays.asList(Protocol.HTTP_2));
}
