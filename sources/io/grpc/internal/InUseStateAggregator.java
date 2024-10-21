package io.grpc.internal;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class InUseStateAggregator {
    public final Set inUseObjects = Collections.newSetFromMap(new IdentityHashMap());

    protected abstract void handleInUse();

    protected abstract void handleNotInUse();

    public final void updateObjectInUse(Object obj, boolean z) {
        int size = this.inUseObjects.size();
        if (z) {
            this.inUseObjects.add(obj);
            if (size == 0) {
                handleInUse();
                return;
            }
            return;
        }
        if (this.inUseObjects.remove(obj) && size == 1) {
            handleNotInUse();
        }
    }
}
