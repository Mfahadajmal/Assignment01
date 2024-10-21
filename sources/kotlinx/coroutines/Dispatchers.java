package kotlinx.coroutines;

import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Dispatchers {
    public static final CoroutineDispatcher Default = DefaultScheduler.INSTANCE;
    public static final CoroutineDispatcher IO = DefaultIoScheduler.INSTANCE;
}
