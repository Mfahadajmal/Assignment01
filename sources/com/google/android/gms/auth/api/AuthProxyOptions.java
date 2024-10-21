package com.google.android.gms.auth.api;

import _COROUTINE._BOUNDARY;
import android.os.Bundle;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import java.util.Arrays;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthProxyOptions implements Api$ApiOptions {
    public final Bundle authOptions;

    static {
        new AppLifecycleMonitor((short[]) null, (byte[]) null).build();
    }

    public AuthProxyOptions(Bundle bundle) {
        this.authOptions = bundle;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthProxyOptions)) {
            return false;
        }
        Bundle bundle = this.authOptions;
        Bundle bundle2 = ((AuthProxyOptions) obj).authOptions;
        if (bundle.size() == bundle2.size()) {
            Set<String> keySet = bundle.keySet();
            if (keySet.containsAll(bundle2.keySet())) {
                for (String str : keySet) {
                    if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(bundle.get(str), bundle2.get(str))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.authOptions});
    }
}
