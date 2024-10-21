package com.google.android.libraries.storage.protostore;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoDataMigrationInitializer implements AsyncFunction {
    public final Executor executor;
    public final List migrations;

    public ProtoDataMigrationInitializer(List list, Executor executor) {
        this.migrations = list;
        this.executor = executor;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* bridge */ /* synthetic */ ListenableFuture apply(Object obj) {
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = (FloatingActionButton.ShadowDelegateImpl) obj;
        int i = ((RegularImmutableList) this.migrations).size;
        ArrayList arrayList = new ArrayList(i);
        UnmodifiableListIterator it = ((ImmutableList) this.migrations).iterator();
        while (it.hasNext()) {
            arrayList.add(((ProtoDataMigration) it.next()).shouldMigrate());
        }
        return AbstractTransformFuture.create(shadowDelegateImpl.updateDataAsync(TracePropagation.propagateAsyncFunction(new ProtoDataMigrationInitializer$$ExternalSyntheticLambda3(this, arrayList, i, 1)), DirectExecutor.INSTANCE), TracePropagation.propagateAsyncFunction(new ProtoDataMigrationInitializer$$ExternalSyntheticLambda3(this, i, arrayList, 0)), DirectExecutor.INSTANCE);
    }
}
