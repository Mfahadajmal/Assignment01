package com.google.android.accessibility.talkback.focusmanagement.record;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription;
import java.util.function.ToDoubleFunction;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NodePathDescription$$ExternalSyntheticLambda2 implements ToDoubleFunction {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ NodePathDescription$$ExternalSyntheticLambda2(int i) {
        this.switching_field = i;
    }

    @Override // java.util.function.ToDoubleFunction
    public final double applyAsDouble(Object obj) {
        if (this.switching_field != 0) {
            MultitouchHandler.PointerWithHistory pointerWithHistory = (MultitouchHandler.PointerWithHistory) obj;
            return _BOUNDARY.distance(pointerWithHistory.pointCurrent, pointerWithHistory.pointInitial);
        }
        return ((NodePathDescription.Match) obj).score();
    }
}
