package kotlinx.coroutines.scheduling;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NanoTimeSource extends SchedulerTimeSource {
    public static final NanoTimeSource INSTANCE = new NanoTimeSource();

    private NanoTimeSource() {
    }

    @Override // kotlinx.coroutines.scheduling.SchedulerTimeSource
    public final long nanoTime() {
        return System.nanoTime();
    }
}
