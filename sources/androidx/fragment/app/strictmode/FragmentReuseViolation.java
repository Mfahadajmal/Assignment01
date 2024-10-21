package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentReuseViolation extends Violation {
    public FragmentReuseViolation(Fragment fragment, String str) {
        super(fragment, "Attempting to reuse fragment " + fragment + " with previous ID " + str);
    }
}
