package kotlinx.coroutines;

import com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions;
import kotlinx.coroutines.internal.MainDispatcherLoader;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultExecutorKt {
    public static final Delay DefaultDelay;
    private static final boolean defaultMainDelayOptIn;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Delay delay;
        boolean systemProp = TextDetectionOptionalModuleOptions.systemProp("kotlinx.coroutines.main.delay", false);
        defaultMainDelayOptIn = systemProp;
        if (!systemProp) {
            delay = DefaultExecutor.INSTANCE;
        } else {
            CoroutineDispatcher coroutineDispatcher = Dispatchers.Default;
            MainCoroutineDispatcher mainCoroutineDispatcher = MainDispatcherLoader.dispatcher;
            mainCoroutineDispatcher.getImmediate();
            if (!(mainCoroutineDispatcher instanceof Delay)) {
                delay = DefaultExecutor.INSTANCE;
            } else {
                delay = (Delay) mainCoroutineDispatcher;
            }
        }
        DefaultDelay = delay;
    }
}
