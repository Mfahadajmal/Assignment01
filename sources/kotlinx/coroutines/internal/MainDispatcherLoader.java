package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.android.google.ProguardConstantsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MainDispatcherLoader {
    private static final boolean FAST_SERVICE_LOADER_ENABLED = TextDetectionOptionalModuleOptions.systemProp("kotlinx.coroutines.fast.service.loader", true);
    public static final MainCoroutineDispatcher dispatcher = ProguardConstantsKt.MAIN_DISPATCHER;
}
