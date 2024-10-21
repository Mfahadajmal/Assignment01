package com.google.mlkit.logging.schema;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.AbstractMutableSet;
import kotlin.collections.builders.SetBuilder;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceObjectCreateLogEvent {
    public static final Set build(Set set) {
        ((SetBuilder) set).backing.build();
        if (((AbstractMutableSet) set).getSize() > 0) {
            return set;
        }
        return SetBuilder.Empty;
    }

    public static final Object[] copyOfUninitializedElements(Object[] objArr, int i) {
        objArr.getClass();
        Object[] copyOf = Arrays.copyOf(objArr, i);
        copyOf.getClass();
        return copyOf;
    }

    public static Set plus(Set set, Object obj) {
        set.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet(OnDeviceLanguageIdentificationLogEvent.mapCapacity(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(obj);
        return linkedHashSet;
    }

    public static final void resetAt(Object[] objArr, int i) {
        objArr.getClass();
        objArr[i] = null;
    }

    public static final void resetRange(Object[] objArr, int i, int i2) {
        objArr.getClass();
        while (i < i2) {
            resetAt(objArr, i);
            i++;
        }
    }

    public static final Set setOf(Object obj) {
        Set singleton = Collections.singleton(obj);
        singleton.getClass();
        return singleton;
    }
}
