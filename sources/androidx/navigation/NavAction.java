package androidx.navigation;

import android.os.Bundle;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavAction {
    public final int destinationId;
    public NavOptions navOptions = null;
    public Bundle defaultArguments = null;

    public NavAction(int i) {
        this.destinationId = i;
    }

    public final boolean equals(Object obj) {
        Set<String> keySet;
        Object obj2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NavAction)) {
            return false;
        }
        NavAction navAction = (NavAction) obj;
        if (this.destinationId == navAction.destinationId && Intrinsics.areEqual(this.navOptions, navAction.navOptions)) {
            if (Intrinsics.areEqual(this.defaultArguments, navAction.defaultArguments)) {
                return true;
            }
            Bundle bundle = this.defaultArguments;
            if (bundle != null && (keySet = bundle.keySet()) != null) {
                if (keySet.isEmpty()) {
                    return true;
                }
                for (String str : keySet) {
                    Bundle bundle2 = this.defaultArguments;
                    Object obj3 = null;
                    if (bundle2 != null) {
                        obj2 = bundle2.get(str);
                    } else {
                        obj2 = null;
                    }
                    Bundle bundle3 = navAction.defaultArguments;
                    if (bundle3 != null) {
                        obj3 = bundle3.get(str);
                    }
                    if (!Intrinsics.areEqual(obj2, obj3)) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        Set<String> keySet;
        Object obj;
        int i2;
        NavOptions navOptions = this.navOptions;
        if (navOptions != null) {
            i = navOptions.hashCode();
        } else {
            i = 0;
        }
        int i3 = this.destinationId;
        Bundle bundle = this.defaultArguments;
        int i4 = (i3 * 31) + i;
        if (bundle != null && (keySet = bundle.keySet()) != null) {
            for (String str : keySet) {
                int i5 = i4 * 31;
                Bundle bundle2 = this.defaultArguments;
                if (bundle2 != null) {
                    obj = bundle2.get(str);
                } else {
                    obj = null;
                }
                if (obj != null) {
                    i2 = obj.hashCode();
                } else {
                    i2 = 0;
                }
                i4 = i5 + i2;
            }
        }
        return i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(0x");
        sb.append(Integer.toHexString(this.destinationId));
        sb.append(")");
        if (this.navOptions != null) {
            sb.append(" navOptions=");
            sb.append(this.navOptions);
        }
        return sb.toString();
    }
}
