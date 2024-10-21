package kotlin.jvm.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageReference implements ClassBasedDeclarationContainer {
    private final Class jClass;

    public PackageReference(Class cls) {
        this.jClass = cls;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof PackageReference) && Intrinsics.areEqual(this.jClass, ((PackageReference) obj).jClass)) {
            return true;
        }
        return false;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public final Class getJClass() {
        return this.jClass;
    }

    public final int hashCode() {
        return this.jClass.hashCode();
    }

    public final String toString() {
        return String.valueOf(this.jClass.toString()).concat(" (Kotlin reflection is not available)");
    }
}
