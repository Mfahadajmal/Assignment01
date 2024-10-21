package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnregisterListenerMethod {
    public final ListenerHolder.ListenerKey listenerKey;
    final /* synthetic */ RegistrationMethods$Builder this$0;

    protected UnregisterListenerMethod(ListenerHolder.ListenerKey listenerKey) {
        this.listenerKey = listenerKey;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UnregisterListenerMethod(RegistrationMethods$Builder registrationMethods$Builder, ListenerHolder.ListenerKey listenerKey) {
        this(listenerKey);
        this.this$0 = registrationMethods$Builder;
    }
}
