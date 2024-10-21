package androidx.fragment.app.strictmode;

import _COROUTINE._BOUNDARY;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentTagUsageViolation extends Violation {
    public FragmentTagUsageViolation(Fragment fragment, ViewGroup viewGroup) {
        super(fragment, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(viewGroup, fragment, "Attempting to use <fragment> tag to add fragment ", " to container "));
    }
}
