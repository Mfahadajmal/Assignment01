package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ComponentContainer {
    Object get(Class cls);

    Provider getProvider(Qualified qualified);

    Provider getProvider(Class cls);

    Set setOf(Qualified qualified);

    Provider setOfProvider(Qualified qualified);
}
