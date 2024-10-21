package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChildHandleNode extends JobCancellingNode implements ChildHandle {
    public final JobSupport childJob$ar$class_merging;

    public ChildHandleNode(JobSupport jobSupport) {
        this.childJob$ar$class_merging = jobSupport;
    }

    @Override // kotlinx.coroutines.ChildHandle
    public final boolean childCancelled(Throwable th) {
        return getJob().childCancelled(th);
    }

    @Override // kotlinx.coroutines.ChildHandle
    public final Job getParent() {
        return getJob();
    }

    @Override // kotlinx.coroutines.InternalCompletionHandler
    public final void invoke(Throwable th) {
        this.childJob$ar$class_merging.parentCancelled$ar$class_merging(getJob());
    }
}
