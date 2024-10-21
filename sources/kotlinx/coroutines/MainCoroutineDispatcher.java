package kotlinx.coroutines;

import kotlinx.coroutines.internal.MainDispatcherLoader;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    public abstract MainCoroutineDispatcher getImmediate();

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String stringInternalImpl = toStringInternalImpl();
        if (stringInternalImpl == null) {
            return DebugStringsKt.getClassSimpleName(this) + "@" + DebugStringsKt.getHexAddress(this);
        }
        return stringInternalImpl;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String toStringInternalImpl() {
        MainCoroutineDispatcher mainCoroutineDispatcher;
        CoroutineDispatcher coroutineDispatcher = Dispatchers.Default;
        MainCoroutineDispatcher mainCoroutineDispatcher2 = MainDispatcherLoader.dispatcher;
        if (this == mainCoroutineDispatcher2) {
            return "Dispatchers.Main";
        }
        try {
            mainCoroutineDispatcher = mainCoroutineDispatcher2.getImmediate();
        } catch (UnsupportedOperationException unused) {
            mainCoroutineDispatcher = null;
        }
        if (this != mainCoroutineDispatcher) {
            return null;
        }
        return "Dispatchers.Main.immediate";
    }
}
