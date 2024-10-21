package com.google.android.accessibility.selecttospeak.popup;

import androidx.lifecycle.Observer;
import kotlin.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0 implements Observer, FunctionAdapter {
    private final /* synthetic */ Function1 function;
    private final /* synthetic */ int switching_field;

    public SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(Function1 function1, int i) {
        this.switching_field = i;
        this.function = function1;
    }

    public final boolean equals(Object obj) {
        if (this.switching_field != 0) {
            if (!(obj instanceof Observer) || !(obj instanceof FunctionAdapter)) {
                return false;
            }
            return Intrinsics.areEqual(this.function, ((FunctionAdapter) obj).getFunctionDelegate());
        }
        if (!(obj instanceof Observer) || !(obj instanceof FunctionAdapter)) {
            return false;
        }
        return Intrinsics.areEqual(this.function, ((FunctionAdapter) obj).getFunctionDelegate());
    }

    @Override // kotlin.jvm.internal.FunctionAdapter
    public final Function getFunctionDelegate() {
        if (this.switching_field != 0) {
            return this.function;
        }
        return this.function;
    }

    public final int hashCode() {
        if (this.switching_field != 0) {
            return this.function.hashCode();
        }
        return this.function.hashCode();
    }

    @Override // androidx.lifecycle.Observer
    public final /* synthetic */ void onChanged(Object obj) {
        if (this.switching_field != 0) {
            this.function.invoke(obj);
        } else {
            this.function.invoke(obj);
        }
    }
}
