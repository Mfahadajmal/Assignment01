package io.grpc;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ManagedChannelProvider {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException() {
            super("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
        }
    }

    public abstract ManagedChannelBuilder builderForAddress(String str, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void isAvailable$ar$ds$f3439281_0();

    public abstract int priority();
}
