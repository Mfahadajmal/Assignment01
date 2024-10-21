package com.google.android.gms.signin;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignIn {
    public static final SpannableUtils$IdentifierSpan CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan((byte[]) null, (char[]) null);
    public static final SpannableUtils$IdentifierSpan INTERNAL_CLIENT_KEY$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan((byte[]) null, (char[]) null);
    public static final SpannableUtils$IdentifierSpan CLIENT_BUILDER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan() { // from class: com.google.android.gms.signin.SignIn.1
        @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
        public final /* bridge */ /* synthetic */ Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            SignInOptions signInOptions = clientSettings.signInOptions;
            Integer num = clientSettings.sessionId;
            Bundle bundle = new Bundle();
            bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings.account);
            if (num != null) {
                bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
            }
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
            return new SignInClientImpl(context, looper, true, clientSettings, bundle, connectionCallbacks, onConnectionFailedListener);
        }
    };
    static final SpannableUtils$IdentifierSpan INTERNAL_CLIENT_BUILDER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan() { // from class: com.google.android.gms.signin.SignIn.2
        @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
        public final /* bridge */ /* synthetic */ Api$Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            throw null;
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SignInOptionsInternal implements Api$ApiOptions {
        public final boolean equals(Object obj) {
            throw null;
        }

        public final int hashCode() {
            throw null;
        }
    }

    static {
        new Scope("profile");
        new Scope("email");
    }
}
