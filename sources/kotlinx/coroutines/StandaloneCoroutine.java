package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OtherError;
import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public class StandaloneCoroutine extends AbstractCoroutine {
    public StandaloneCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(coroutineContext, z);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final boolean handleJobException(Throwable th) {
        OtherError.handleCoroutineException(this.context, th);
        return true;
    }
}
