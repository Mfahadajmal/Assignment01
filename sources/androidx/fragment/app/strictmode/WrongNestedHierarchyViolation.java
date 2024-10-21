package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WrongNestedHierarchyViolation extends Violation {
    public WrongNestedHierarchyViolation(Fragment fragment, Fragment fragment2, int i) {
        super(fragment, "Attempting to nest fragment " + fragment + " within the view of parent fragment " + fragment2 + " via container with ID " + i + " without using parent's childFragmentManager");
    }
}
