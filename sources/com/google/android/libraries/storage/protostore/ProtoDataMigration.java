package com.google.android.libraries.storage.protostore;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ProtoDataMigration {
    ListenableFuture cleanup();

    ListenableFuture migrate$ar$ds();

    ListenableFuture shouldMigrate();
}
