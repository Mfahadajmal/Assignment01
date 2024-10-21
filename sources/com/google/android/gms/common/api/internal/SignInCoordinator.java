package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.internal.ISignInCallbacks;
import com.google.android.gms.signin.internal.SignInClientImpl;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignInCoordinator extends ISignInCallbacks.Stub implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final SpannableUtils$IdentifierSpan sClientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = SignIn.CLIENT_BUILDER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public GoogleApiManager.GoogleApiProgressCallbacks mCallback$ar$class_merging$71a2fb1_0;
    public final SpannableUtils$IdentifierSpan mClientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ClientSettings mClientSettings;
    public final Context mContext;
    public final Handler mHandler;
    public final Set mScopes;
    public SignInClientImpl mSignInClient$ar$class_merging;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignInCoordinator(Context context, Handler handler, ClientSettings clientSettings) {
        super(null);
        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = sClientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        this.mContext = context;
        this.mHandler = handler;
        this.mClientSettings = clientSettings;
        this.mScopes = clientSettings.requiredScopes;
        this.mClientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected$ar$ds() {
        this.mSignInClient$ar$class_merging.signIn(this);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.mCallback$ar$class_merging$71a2fb1_0.onSignInFailed(connectionResult);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        GoogleApiManager.GoogleApiProgressCallbacks googleApiProgressCallbacks = this.mCallback$ar$class_merging$71a2fb1_0;
        GoogleApiManager.ClientConnection clientConnection = (GoogleApiManager.ClientConnection) GoogleApiManager.this.apiMap.get(googleApiProgressCallbacks.apiKey);
        if (clientConnection != null) {
            if (clientConnection.resuming) {
                clientConnection.onSignInFailed(new ConnectionResult(17));
            } else {
                clientConnection.onConnectionSuspended(i);
            }
        }
    }

    @Override // com.google.android.gms.signin.internal.ISignInCallbacks.Stub, com.google.android.gms.signin.internal.ISignInCallbacks
    public final void onSignInComplete(SignInResponse signInResponse) {
        this.mHandler.post(new ListMenuManager$$ExternalSyntheticLambda3(this, signInResponse, 13, (byte[]) null));
    }
}
