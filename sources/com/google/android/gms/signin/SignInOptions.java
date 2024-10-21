package com.google.android.gms.signin;

import _COROUTINE._BOUNDARY;
import com.google.android.gms.common.api.Api$ApiOptions;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignInOptions implements Api$ApiOptions {
    public static final SignInOptions DEFAULT = new SignInOptions();
    private final boolean offlineAccessRequested = false;
    private final boolean idTokenRequested = false;
    private final String serverClientId = null;
    private final boolean forceCodeForRefreshToken = false;
    private final boolean waitForAccessTokenRefresh = false;
    private final String hostedDomain = null;
    private final String logSessionId = null;
    private final Long authApiSignInModuleVersion = null;
    private final Long realClientLibraryVersion = null;

    private SignInOptions() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SignInOptions)) {
            return false;
        }
        SignInOptions signInOptions = (SignInOptions) obj;
        boolean z = signInOptions.offlineAccessRequested;
        boolean z2 = signInOptions.idTokenRequested;
        String str = signInOptions.serverClientId;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
            boolean z3 = signInOptions.forceCodeForRefreshToken;
            boolean z4 = signInOptions.waitForAccessTokenRefresh;
            String str2 = signInOptions.hostedDomain;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                String str3 = signInOptions.logSessionId;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                    Long l = signInOptions.authApiSignInModuleVersion;
                    if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                        Long l2 = signInOptions.realClientLibraryVersion;
                        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{false, false, null, false, false, null, null, null, null});
    }
}
