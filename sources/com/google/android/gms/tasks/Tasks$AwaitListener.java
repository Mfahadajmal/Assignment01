package com.google.android.gms.tasks;

import java.util.concurrent.CountDownLatch;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tasks$AwaitListener implements OnSuccessListener, OnFailureListener {
    public final CountDownLatch latch = new CountDownLatch(1);

    public final void onCanceled() {
        this.latch.countDown();
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.latch.countDown();
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        this.latch.countDown();
    }
}
