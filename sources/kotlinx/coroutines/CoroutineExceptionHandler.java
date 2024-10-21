package kotlinx.coroutines;

import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {
    public static final ContinuationInterceptor.Key Key$ar$class_merging$908abe57_0 = ContinuationInterceptor.Key.$$INSTANCE$ar$class_merging;

    void handleException$ar$ds$fd9f1d10_0();
}
