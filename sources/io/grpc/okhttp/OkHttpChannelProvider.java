package io.grpc.okhttp;

import com.google.mlkit.logging.schema.OnDeviceImageCaptioningLoadLogEvent;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannelProvider;
import io.grpc.internal.GrpcUtil;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OkHttpChannelProvider extends ManagedChannelProvider {
    @Override // io.grpc.ManagedChannelProvider
    public final /* synthetic */ ManagedChannelBuilder builderForAddress(String str, int i) {
        return new OkHttpChannelBuilder(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    @Override // io.grpc.ManagedChannelProvider
    public final int priority() {
        if (OnDeviceImageCaptioningLoadLogEvent.isAndroid(getClass().getClassLoader())) {
            return 8;
        }
        return 3;
    }

    @Override // io.grpc.ManagedChannelProvider
    public final void isAvailable$ar$ds$f3439281_0() {
    }
}
