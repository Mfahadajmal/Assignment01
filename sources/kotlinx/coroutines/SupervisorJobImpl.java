package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SupervisorJobImpl extends JobImpl {
    public SupervisorJobImpl(Job job) {
        super(null);
    }

    @Override // kotlinx.coroutines.JobSupport
    public final boolean childCancelled(Throwable th) {
        return false;
    }
}
