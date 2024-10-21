package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SetUserVisibleHintViolation extends Violation {
    public SetUserVisibleHintViolation(Fragment fragment, boolean z) {
        super(fragment, "Attempting to set user visible hint to " + z + " for fragment " + fragment);
    }
}
