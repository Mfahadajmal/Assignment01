package com.google.mlkit.common.sdkinternal;

import j$.util.DesugarCollections;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Cleaner {
    public final Object Cleaner$ar$cleanables;
    public final Object Cleaner$ar$referenceQueue;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CleanableImpl extends PhantomReference {
        public final Set cleanables;
        public final Runnable cleaningAction;

        public CleanableImpl(Object obj, ReferenceQueue referenceQueue, Set set, Runnable runnable) {
            super(obj, referenceQueue);
            this.cleanables = set;
            this.cleaningAction = runnable;
        }
    }

    public Cleaner(Executor executor, Runnable runnable) {
        this.Cleaner$ar$cleanables = executor;
        this.Cleaner$ar$referenceQueue = runnable;
    }

    public static /* synthetic */ Thread lambda$create$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "MlKitCleaner");
        thread.setDaemon(true);
        return thread;
    }

    public Cleaner() {
        this.Cleaner$ar$referenceQueue = new ReferenceQueue();
        this.Cleaner$ar$cleanables = DesugarCollections.synchronizedSet(new HashSet());
    }
}
