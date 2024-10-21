package kotlin.jvm.internal;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Lambda implements FunctionBase, Serializable {
    private final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public final int getArity() {
        return this.arity;
    }

    public final String toString() {
        Intrinsics intrinsics = Reflection.factory$ar$class_merging$693c3180_0;
        String renderLambdaToString$ar$ds = Intrinsics.renderLambdaToString$ar$ds(this);
        renderLambdaToString$ar$ds.getClass();
        return renderLambdaToString$ar$ds;
    }
}
