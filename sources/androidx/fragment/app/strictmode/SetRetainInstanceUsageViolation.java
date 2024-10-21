package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SetRetainInstanceUsageViolation extends RetainInstanceUsageViolation {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetRetainInstanceUsageViolation(Fragment fragment) {
        super(fragment, "Attempting to set retain instance for fragment ".concat(fragment.toString()));
        Objects.toString(fragment);
    }
}
