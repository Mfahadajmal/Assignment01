package androidx.navigation;

import android.os.Bundle;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavArgument {
    private final Object defaultValue;
    public final boolean isDefaultValuePresent;
    public final boolean isNullable;
    public final NavType type;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object NavArgument$Builder$ar$type;
        public Object defaultValue;
        public boolean defaultValuePresent;
        public boolean isNullable;

        public final void clear() {
            this.NavArgument$Builder$ar$type = null;
            this.defaultValuePresent = false;
            this.defaultValue = null;
            this.isNullable = false;
        }
    }

    public NavArgument(NavType navType, boolean z, Object obj, boolean z2) {
        if (!navType.isNullableAllowed && z) {
            throw new IllegalArgumentException(navType.getName().concat(" does not allow nullable values"));
        }
        if (!z && z2 && obj == null) {
            throw new IllegalArgumentException("Argument with type " + navType.getName() + " has null value but is not nullable.");
        }
        this.type = navType;
        this.isNullable = z;
        this.defaultValue = obj;
        this.isDefaultValuePresent = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Intrinsics.areEqual(getClass(), obj.getClass())) {
            return false;
        }
        NavArgument navArgument = (NavArgument) obj;
        if (this.isNullable != navArgument.isNullable || this.isDefaultValuePresent != navArgument.isDefaultValuePresent || !Intrinsics.areEqual(this.type, navArgument.type)) {
            return false;
        }
        Object obj2 = this.defaultValue;
        if (obj2 != null) {
            return Intrinsics.areEqual(obj2, navArgument.defaultValue);
        }
        if (navArgument.defaultValue == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = this.type.hashCode() * 31;
        Object obj = this.defaultValue;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        return ((((hashCode + (this.isNullable ? 1 : 0)) * 31) + (this.isDefaultValuePresent ? 1 : 0)) * 31) + i;
    }

    public final void putDefaultValue(String str, Bundle bundle) {
        str.getClass();
        if (this.isDefaultValuePresent) {
            this.type.put(bundle, str, this.defaultValue);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        NavType navType = this.type;
        Objects.toString(navType);
        sb.append(" Type: ".concat(navType.toString()));
        sb.append(" Nullable: " + this.isNullable);
        if (this.isDefaultValuePresent) {
            Object obj = this.defaultValue;
            Objects.toString(obj);
            sb.append(" DefaultValue: ".concat(String.valueOf(obj)));
        }
        return sb.toString();
    }
}
