package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GetTargetFragmentUsageViolation extends TargetFragmentUsageViolation {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetTargetFragmentUsageViolation(Fragment fragment) {
        super(fragment, "Attempting to get target fragment from fragment ".concat(fragment.toString()));
        Objects.toString(fragment);
    }
}
