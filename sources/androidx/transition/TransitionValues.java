package androidx.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransitionValues {
    public View view;
    public final Map values = new HashMap();
    final ArrayList mTargetedTransitions = new ArrayList();

    @Deprecated
    public TransitionValues() {
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TransitionValues) {
            TransitionValues transitionValues = (TransitionValues) obj;
            if (this.view == transitionValues.view && this.values.equals(transitionValues.values)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return (this.view.hashCode() * 31) + this.values.hashCode();
    }

    public final String toString() {
        String str = ("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.view + "\n";
        String concat = str.concat("    values:");
        for (String str2 : this.values.keySet()) {
            concat = concat + "    " + str2 + ": " + this.values.get(str2) + "\n";
        }
        return concat;
    }

    public TransitionValues(View view) {
        this.view = view;
    }
}
