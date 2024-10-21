package com.google.android.apps.aicore.client.api.internal;

import android.support.v7.widget.DropDownListView;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FailureSignal {
    public boolean isSignaled;
    public final Object lock = new Object();
    public final List observers = new ArrayList();

    public final ListenableFuture propagate(ListenableFuture listenableFuture, Supplier supplier) {
        return DropDownListView.Api33Impl.getFuture(new AiCoreBaseService$$ExternalSyntheticLambda17(this, listenableFuture, supplier, 2));
    }

    public final void removeObserver(Runnable runnable) {
        synchronized (this.lock) {
            this.observers.remove(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void signal() {
        ImmutableList copyOf;
        synchronized (this.lock) {
            this.isSignaled = true;
            copyOf = ImmutableList.copyOf((Collection) this.observers);
            this.observers.clear();
        }
        int size = copyOf.size();
        for (int i = 0; i < size; i++) {
            ((Runnable) copyOf.get(i)).run();
        }
    }
}
