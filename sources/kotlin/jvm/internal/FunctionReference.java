package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int arity;
    private final int flags;

    public FunctionReference(int i, Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, 1 == i2);
        this.arity = i;
        this.flags = 0;
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected final KCallable computeReflected() {
        Intrinsics intrinsics = Reflection.factory$ar$class_merging$693c3180_0;
        return this;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            if (this.name.equals(functionReference.name) && this.signature.equals(functionReference.signature)) {
                int i = functionReference.flags;
                if (this.arity == functionReference.arity && Intrinsics.areEqual(this.receiver, functionReference.receiver) && Intrinsics.areEqual(getOwner(), functionReference.getOwner())) {
                    return true;
                }
            }
            return false;
        }
        if (!(obj instanceof KFunction)) {
            return false;
        }
        return obj.equals(compute());
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public final int getArity() {
        return this.arity;
    }

    public final int hashCode() {
        int hashCode;
        if (getOwner() == null) {
            hashCode = 0;
        } else {
            hashCode = getOwner().hashCode() * 31;
        }
        return ((hashCode + this.name.hashCode()) * 31) + this.signature.hashCode();
    }

    public final String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        if ("<init>".equals(this.name)) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + this.name + " (Kotlin reflection is not available)";
    }

    public FunctionReference(int i, Class cls, String str, String str2, int i2) {
        this(i, NO_RECEIVER, cls, str, str2, i2);
    }

    public FunctionReference(int i, Object obj, Class cls, String str, String str2) {
        this(i, obj, cls, str, str2, 0);
    }
}
