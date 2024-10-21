package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class JobNode extends LockFreeLinkedListNode implements InternalCompletionHandler, DisposableHandle, Incomplete {
    public JobSupport job;

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        getJob().removeNode$kotlinx_coroutines_core(this);
    }

    public final JobSupport getJob() {
        JobSupport jobSupport = this.job;
        if (jobSupport != null) {
            return jobSupport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("job");
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public final NodeList getList() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public final boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final String toString() {
        return DebugStringsKt.getClassSimpleName(this) + "@" + DebugStringsKt.getHexAddress(this) + "[job@" + DebugStringsKt.getHexAddress(getJob()) + "]";
    }
}
