package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CopyableThreadContextElement extends ThreadContextElement {
    CopyableThreadContextElement copyForChild();

    CoroutineContext mergeForChild$ar$ds();
}
