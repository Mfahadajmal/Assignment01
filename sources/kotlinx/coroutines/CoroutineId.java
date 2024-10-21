package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement {
    public static final ContinuationInterceptor.Key Key$ar$class_merging$3b7a11c9_0 = new ContinuationInterceptor.Key();
    public final long id;

    public CoroutineId(long j) {
        super(Key$ar$class_merging$3b7a11c9_0);
        this.id = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof CoroutineId) && this.id == ((CoroutineId) obj).id) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        long j = this.id;
        return (int) (j ^ (j >>> 32));
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public final /* bridge */ /* synthetic */ void restoreThreadContext$ar$ds(Object obj) {
        Thread.currentThread().setName((String) obj);
    }

    public final String toString() {
        return "CoroutineId(" + this.id + ")";
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public final /* bridge */ /* synthetic */ Object updateThreadContext(CoroutineContext coroutineContext) {
        if (((CoroutineName) coroutineContext.get(CoroutineName.Key$ar$class_merging$1ccfc3dd_0)) == null) {
            Thread currentThread = Thread.currentThread();
            String name = currentThread.getName();
            int lastIndex = OnDeviceStainRemovalLogEvent.getLastIndex(name);
            name.getClass();
            int lastIndexOf = name.lastIndexOf(" @", lastIndex);
            if (lastIndexOf < 0) {
                lastIndexOf = name.length();
            }
            StringBuilder sb = new StringBuilder(lastIndexOf + 19);
            String substring = name.substring(0, lastIndexOf);
            substring.getClass();
            sb.append(substring);
            sb.append(" @coroutine#");
            sb.append(this.id);
            currentThread.setName(sb.toString());
            return name;
        }
        throw null;
    }
}
