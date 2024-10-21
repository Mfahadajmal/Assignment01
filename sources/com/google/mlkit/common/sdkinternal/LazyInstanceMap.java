package com.google.mlkit.common.sdkinternal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LazyInstanceMap {
    private final Map instances = new HashMap();

    protected abstract Object create(Object obj);

    public final Object get(Object obj) {
        synchronized (this.instances) {
            if (this.instances.containsKey(obj)) {
                return this.instances.get(obj);
            }
            Object create = create(obj);
            this.instances.put(obj, create);
            return create;
        }
    }
}
