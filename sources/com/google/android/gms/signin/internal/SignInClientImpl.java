package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignInClientImpl extends GmsClient implements Api$Client {
    private final ClientSettings clientSettings;
    private final boolean isExposedSignInApi;
    private final Integer sessionId;
    private final Bundle signInOptionsBundle;

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.isExposedSignInApi = true;
        this.clientSettings = clientSettings;
        this.signInOptionsBundle = bundle;
        this.sessionId = clientSettings.sessionId;
    }

    public final void connect() {
        connect(new BaseGmsClient.LegacyClientCallbackAdapter());
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (queryLocalInterface instanceof ISignInService) {
            return (ISignInService) queryLocalInterface;
        }
        return new ISignInService$Stub$Proxy(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        if (!this.context.getPackageName().equals(this.clientSettings.realClientPackageName)) {
            this.signInOptionsBundle.putString("com.google.android.gms.signin.internal.realClientPackageName", this.clientSettings.realClientPackageName);
        }
        return this.signInOptionsBundle;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final boolean requiresSignIn() {
        return this.isExposedSignInApi;
    }

    public final void signIn(ISignInCallbacks iSignInCallbacks) {
        GoogleSignInAccount googleSignInAccount;
        Uri uri;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        try {
            Account account = this.clientSettings.account;
            if (account == null) {
                account = new Account("<<default account>>", "com.google");
            }
            if ("<<default account>>".equals(account.name)) {
                Context context = this.context;
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(context);
                Storage.sLk.lock();
                try {
                    if (Storage.sInstance == null) {
                        Storage.sInstance = new Storage(context.getApplicationContext());
                    }
                    Storage storage = Storage.sInstance;
                    Storage.sLk.unlock();
                    String fromStore = storage.getFromStore("defaultGoogleSignInAccount");
                    if (!TextUtils.isEmpty(fromStore)) {
                        String fromStore2 = storage.getFromStore("googleSignInAccount:" + fromStore);
                        if (fromStore2 != null) {
                            if (!TextUtils.isEmpty(fromStore2)) {
                                JSONObject jSONObject = new JSONObject(fromStore2);
                                String optString = jSONObject.optString("photoUrl");
                                if (!TextUtils.isEmpty(optString)) {
                                    uri = Uri.parse(optString);
                                } else {
                                    uri = null;
                                }
                                long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
                                HashSet hashSet = new HashSet();
                                JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
                                int length = jSONArray.length();
                                for (int i = 0; i < length; i++) {
                                    hashSet.add(new Scope(jSONArray.getString(i)));
                                }
                                String optString2 = jSONObject.optString("id");
                                if (jSONObject.has("tokenId")) {
                                    str = jSONObject.optString("tokenId");
                                } else {
                                    str = null;
                                }
                                if (jSONObject.has("email")) {
                                    str2 = jSONObject.optString("email");
                                } else {
                                    str2 = null;
                                }
                                if (jSONObject.has("displayName")) {
                                    str3 = jSONObject.optString("displayName");
                                } else {
                                    str3 = null;
                                }
                                if (jSONObject.has("givenName")) {
                                    str4 = jSONObject.optString("givenName");
                                } else {
                                    str4 = null;
                                }
                                if (jSONObject.has("familyName")) {
                                    str5 = jSONObject.optString("familyName");
                                } else {
                                    str5 = null;
                                }
                                Long valueOf = Long.valueOf(parseLong);
                                String string = jSONObject.getString("obfuscatedIdentifier");
                                valueOf.getClass();
                                StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds(string);
                                googleSignInAccount = new GoogleSignInAccount(optString2, str, str2, str3, uri, null, parseLong, string, new ArrayList(hashSet), str4, str5);
                                if (jSONObject.has("serverAuthCode")) {
                                    str6 = jSONObject.optString("serverAuthCode");
                                } else {
                                    str6 = null;
                                }
                                googleSignInAccount.serverAuthCode = str6;
                                Integer num = this.sessionId;
                                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(num);
                                ((ISignInService) getService()).signIn(new SignInRequest(1, new ResolveAccountRequest(2, account, num.intValue(), googleSignInAccount)), iSignInCallbacks);
                            }
                        }
                    }
                } catch (Throwable th) {
                    Storage.sLk.unlock();
                    throw th;
                }
            }
            googleSignInAccount = null;
            Integer num2 = this.sessionId;
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(num2);
            ((ISignInService) getService()).signIn(new SignInRequest(1, new ResolveAccountRequest(2, account, num2.intValue(), googleSignInAccount)), iSignInCallbacks);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                iSignInCallbacks.onSignInComplete(new SignInResponse(1, new ConnectionResult(8, null), null));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }
}
