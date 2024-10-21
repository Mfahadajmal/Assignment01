package com.google.android.accessibility.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class WeakReferenceHandler extends Handler {
    private final WeakReference parentRef;

    public WeakReferenceHandler(Object obj) {
        this.parentRef = new WeakReference(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object getParent() {
        return this.parentRef.get();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Object parent = getParent();
        if (parent == null) {
            return;
        }
        handleMessage(message, parent);
    }

    protected abstract void handleMessage(Message message, Object obj);

    public WeakReferenceHandler(Object obj, Looper looper) {
        super(looper);
        this.parentRef = new WeakReference(obj);
    }
}
