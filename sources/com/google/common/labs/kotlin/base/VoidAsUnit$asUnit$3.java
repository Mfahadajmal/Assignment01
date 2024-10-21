package com.google.common.labs.kotlin.base;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class VoidAsUnit$asUnit$3 extends FunctionReference implements Function2 {
    public VoidAsUnit$asUnit$3(Object obj) {
        super(2, obj, VoidAsUnit$VoidFunction2.class, "invoke", "invoke(Ljava/lang/Object;Ljava/lang/Object;)V");
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        ((VoidAsUnit$VoidFunction2) this.receiver).invoke(obj, obj2);
        return Unit.INSTANCE;
    }
}
