package androidx.core.content.res;

import _COROUTINE._BOUNDARY;
import android.content.res.Resources;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ResourcesCompat$ThemeCompat$Api29Impl {
    public static int indexOf(int i) {
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i != 8) {
                if (i == 16) {
                    return 4;
                }
                if (i != 32) {
                    if (i != 64) {
                        if (i != 128) {
                            if (i == 256) {
                                return 8;
                            }
                            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "type needs to be >= FIRST and <= LAST, type="));
                        }
                        return 7;
                    }
                    return 6;
                }
                return 5;
            }
            return 3;
        }
        return 0;
    }

    public static void rebase(Resources.Theme theme) {
        theme.rebase();
    }
}
