package com.google.mlkit.logging.schema;

import kotlin.coroutines.CombinedContext$toString$1;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceObjectLoadLogEvent {
    public static Object fold(CoroutineContext.Element element, Object obj, Function2 function2) {
        return function2.invoke(obj, element);
    }

    public static CoroutineContext.Element get(CoroutineContext.Element element, CoroutineContext.Key key) {
        if (Intrinsics.areEqual(element.getKey(), key)) {
            return element;
        }
        return null;
    }

    public static CoroutineContext minusKey(CoroutineContext.Element element, CoroutineContext.Key key) {
        if (Intrinsics.areEqual(element.getKey(), key)) {
            return EmptyCoroutineContext.INSTANCE;
        }
        return element;
    }

    public static CoroutineContext plus(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        coroutineContext2.getClass();
        return coroutineContext2 == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) coroutineContext2.fold(coroutineContext, CombinedContext$toString$1.INSTANCE$ar$class_merging$9bb6b845_0);
    }

    public static CoroutineContext plus(CoroutineContext.Element element, CoroutineContext coroutineContext) {
        coroutineContext.getClass();
        return plus((CoroutineContext) element, coroutineContext);
    }
}
