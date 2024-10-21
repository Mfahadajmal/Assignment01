package androidx.core.os;

import android.content.Context;
import android.os.UserManager;
import android.text.InputFilter;
import android.text.method.TransformationMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UserManagerCompat$Api24Impl {
    public static boolean isUserUnlocked(Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }

    public InputFilter[] getFilters(InputFilter[] inputFilterArr) {
        throw null;
    }

    public boolean isEnabled() {
        throw null;
    }

    public TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod) {
        throw null;
    }

    public void setAllCaps(boolean z) {
    }

    public void setEnabled(boolean z) {
    }
}
