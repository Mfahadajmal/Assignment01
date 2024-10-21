package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Reflection;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class RestrictedSuspendLambda extends RestrictedContinuationImpl implements FunctionBase {
    public RestrictedSuspendLambda(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public final int getArity() {
        return 2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final String toString() {
        if (this.completion == null) {
            String renderLambdaToString = Reflection.renderLambdaToString(this);
            renderLambdaToString.getClass();
            return renderLambdaToString;
        }
        return super.toString();
    }
}
