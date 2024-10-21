package kotlin.jvm.internal;

import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty0;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PropertyReference extends CallableReference implements KProperty, KProperty0 {
    public PropertyReference(Object obj, Class cls, byte[] bArr) {
        this(obj, cls);
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
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            if (getOwner().equals(propertyReference.getOwner()) && this.name.equals(propertyReference.name) && this.signature.equals(propertyReference.signature) && Intrinsics.areEqual(this.receiver, propertyReference.receiver)) {
                return true;
            }
            return false;
        }
        if (!(obj instanceof KProperty)) {
            return false;
        }
        return obj.equals(compute());
    }

    public Object get() {
        return getGetter().call$ar$ds();
    }

    @Override // kotlin.reflect.KProperty0
    public final KProperty0.Getter getGetter() {
        return ((KProperty0) getReflected()).getGetter();
    }

    protected final KProperty getReflected() {
        KCallable compute = compute();
        if (compute != this) {
            return (KProperty) compute;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    public final int hashCode() {
        return (((getOwner().hashCode() * 31) + this.name.hashCode()) * 31) + this.signature.hashCode();
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return get();
    }

    public final String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        return "property " + this.name + " (Kotlin reflection is not available)";
    }

    public PropertyReference(Object obj, Class cls, char[] cArr) {
        this(obj, cls, (byte[]) null);
    }

    public PropertyReference() {
        super(CallableReference.NO_RECEIVER, null, null, null, false);
    }

    public PropertyReference(Object obj, Class cls) {
        super(obj, cls, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", true);
    }
}
