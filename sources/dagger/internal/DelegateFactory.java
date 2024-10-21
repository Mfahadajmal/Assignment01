package dagger.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DelegateFactory implements Factory {
    public Provider delegate;

    @Override // javax.inject.Provider
    public final Object get() {
        Provider provider = this.delegate;
        if (provider != null) {
            return provider.get();
        }
        throw new IllegalStateException();
    }
}
