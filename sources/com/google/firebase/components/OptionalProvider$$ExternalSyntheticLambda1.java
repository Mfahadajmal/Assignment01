package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class OptionalProvider$$ExternalSyntheticLambda1 implements Provider {
    private final /* synthetic */ int switching_field;

    @Override // com.google.firebase.inject.Provider
    public final Object get() {
        if (this.switching_field != 0) {
            return Collections.emptySet();
        }
        return null;
    }
}
