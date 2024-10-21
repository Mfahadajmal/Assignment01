package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Violation extends RuntimeException {
    public final Fragment fragment;

    public Violation(Fragment fragment, String str) {
        super(str);
        this.fragment = fragment;
    }
}
