package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WrongFragmentContainerViolation extends Violation {
    public WrongFragmentContainerViolation(Fragment fragment, ViewGroup viewGroup) {
        super(fragment, "Attempting to add fragment " + fragment + " to container " + viewGroup + " which is not a FragmentContainerView");
    }
}
