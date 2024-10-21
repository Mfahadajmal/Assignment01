package com.google.android.accessibility.selecttospeak.overlayui;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class UIDelayedJobScheduler$hideAllExcept$1 extends ContinuationImpl {
    UIDelayedJobScheduler L$0$ar$dn$129fa825_0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ UIDelayedJobScheduler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UIDelayedJobScheduler$hideAllExcept$1(UIDelayedJobScheduler uIDelayedJobScheduler, Continuation continuation) {
        super(continuation);
        this.this$0 = uIDelayedJobScheduler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.hideAllExcept(null, 0L, this);
    }
}
