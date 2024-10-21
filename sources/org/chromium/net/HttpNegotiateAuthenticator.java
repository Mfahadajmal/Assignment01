package org.chromium.net;

import J.N;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import java.io.IOException;
import java.util.Locale;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class HttpNegotiateAuthenticator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "net_auth";
    private final String mAccountType;
    private Bundle mSpnegoContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GetTokenCallback implements AccountManagerCallback {
        public final RequestData mRequestData;
        private final /* synthetic */ int switching_field;
        final /* synthetic */ HttpNegotiateAuthenticator this$0;

        public GetTokenCallback(HttpNegotiateAuthenticator httpNegotiateAuthenticator, RequestData requestData, int i) {
            this.switching_field = i;
            this.this$0 = httpNegotiateAuthenticator;
            this.mRequestData = requestData;
        }

        @Override // android.accounts.AccountManagerCallback
        public final void run(AccountManagerFuture accountManagerFuture) {
            if (this.switching_field != 0) {
                try {
                    Account[] accountArr = (Account[]) accountManagerFuture.getResult();
                    int length = accountArr.length;
                    if (length == 0) {
                        Log.w("cr_".concat(HttpNegotiateAuthenticator.TAG), "ERR_MISSING_AUTH_CREDENTIALS: No account provided for the kerberos authentication. Please verify the configuration policies and that the CONTACTS runtime permission is granted. ");
                        N.M0s8NeYn(this.mRequestData.nativeResultObject, this.this$0, NetError.ERR_MISSING_AUTH_CREDENTIALS, null);
                        return;
                    } else {
                        if (length > 1) {
                            Log.w("cr_".concat(HttpNegotiateAuthenticator.TAG), String.format(Locale.US, "ERR_MISSING_AUTH_CREDENTIALS: Found %d accounts eligible for the kerberos authentication. Please fix the configuration by providing a single account.", Integer.valueOf(length)));
                            N.M0s8NeYn(this.mRequestData.nativeResultObject, this.this$0, NetError.ERR_MISSING_AUTH_CREDENTIALS, null);
                            return;
                        }
                        if (this.this$0.lacksPermission(ContextUtils.sApplicationContext, "android.permission.USE_CREDENTIALS", true)) {
                            Log.e("cr_".concat(HttpNegotiateAuthenticator.TAG), "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: USE_CREDENTIALS permission not granted. Aborting authentication.");
                            N.M0s8NeYn(this.mRequestData.nativeResultObject, this.this$0, NetError.ERR_MISCONFIGURED_AUTH_ENVIRONMENT, null);
                            return;
                        } else {
                            RequestData requestData = this.mRequestData;
                            requestData.account = accountArr[0];
                            requestData.accountManager.getAuthToken(requestData.account, requestData.authTokenType, requestData.options, true, (AccountManagerCallback<Bundle>) new GetTokenCallback(this.this$0, requestData, 0), new Handler(ThreadUtils.getUiThreadLooper()));
                            return;
                        }
                    }
                } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                    Log.w("cr_".concat(HttpNegotiateAuthenticator.TAG), "ERR_UNEXPECTED: Error while attempting to retrieve accounts.", e);
                    N.M0s8NeYn(this.mRequestData.nativeResultObject, this.this$0, -9, null);
                    return;
                }
            }
            try {
                Bundle bundle = (Bundle) accountManagerFuture.getResult();
                if (!bundle.containsKey("intent")) {
                    this.this$0.processResult(bundle, this.mRequestData);
                } else {
                    final Context context = ContextUtils.sApplicationContext;
                    ContextUtils.registerProtectedBroadcastReceiver(context, new BroadcastReceiver() { // from class: org.chromium.net.HttpNegotiateAuthenticator.GetTokenCallback.1
                        @Override // android.content.BroadcastReceiver
                        public final void onReceive(Context context2, Intent intent) {
                            context.unregisterReceiver(this);
                            GetTokenCallback getTokenCallback = GetTokenCallback.this;
                            HttpNegotiateAuthenticator httpNegotiateAuthenticator = getTokenCallback.this$0;
                            RequestData requestData2 = getTokenCallback.mRequestData;
                            requestData2.accountManager.getAuthToken(requestData2.account, requestData2.authTokenType, requestData2.options, true, (AccountManagerCallback<Bundle>) new GetTokenCallback(httpNegotiateAuthenticator, requestData2, 0), (Handler) null);
                        }
                    }, new IntentFilter("android.accounts.LOGIN_ACCOUNTS_CHANGED"));
                }
            } catch (AuthenticatorException | OperationCanceledException | IOException e2) {
                Log.w("cr_".concat(HttpNegotiateAuthenticator.TAG), "ERR_UNEXPECTED: Error while attempting to obtain a token.", e2);
                N.M0s8NeYn(this.mRequestData.nativeResultObject, this.this$0, -9, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RequestData {
        public Account account;
        public AccountManager accountManager;
        public String authTokenType;
        public long nativeResultObject;
        public Bundle options;
    }

    protected HttpNegotiateAuthenticator(String str) {
        this.mAccountType = str;
    }

    static HttpNegotiateAuthenticator create(String str) {
        return new HttpNegotiateAuthenticator(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processResult(Bundle bundle, RequestData requestData) {
        this.mSpnegoContext = bundle.getBundle(HttpNegotiateConstants.KEY_SPNEGO_CONTEXT);
        int i = -9;
        switch (bundle.getInt(HttpNegotiateConstants.KEY_SPNEGO_RESULT, 1)) {
            case 0:
                i = 0;
                break;
            case 2:
                i = -3;
                break;
            case 3:
                i = NetError.ERR_UNEXPECTED_SECURITY_LIBRARY_STATUS;
                break;
            case 4:
                i = NetError.ERR_INVALID_RESPONSE;
                break;
            case 5:
                i = NetError.ERR_INVALID_AUTH_CREDENTIALS;
                break;
            case 6:
                i = NetError.ERR_UNSUPPORTED_AUTH_SCHEME;
                break;
            case 7:
                i = NetError.ERR_MISSING_AUTH_CREDENTIALS;
                break;
            case 8:
                i = NetError.ERR_UNDOCUMENTED_SECURITY_LIBRARY_STATUS;
                break;
            case 9:
                i = NetError.ERR_MALFORMED_IDENTITY;
                break;
        }
        N.M0s8NeYn(requestData.nativeResultObject, this, i, bundle.getString("authtoken"));
    }

    private void requestTokenWithActivity(Context context, Activity activity, RequestData requestData, String[] strArr) {
        if (lacksPermission(context, "android.permission.GET_ACCOUNTS", false)) {
            Log.e("cr_".concat(TAG), String.format(Locale.US, "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: %s permission not granted. Aborting authentication", "android.permission.GET_ACCOUNTS"));
            N.M0s8NeYn(requestData.nativeResultObject, this, NetError.ERR_MISCONFIGURED_AUTH_ENVIRONMENT, null);
        } else {
            requestData.accountManager.getAuthTokenByFeatures(this.mAccountType, requestData.authTokenType, strArr, activity, null, requestData.options, new GetTokenCallback(this, requestData, 0), new Handler(ThreadUtils.getUiThreadLooper()));
        }
    }

    private void requestTokenWithoutActivity(Context context, RequestData requestData, String[] strArr) {
        if (lacksPermission(context, "android.permission.GET_ACCOUNTS", true)) {
            Log.e("cr_".concat(TAG), "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: GET_ACCOUNTS permission not granted. Aborting authentication.");
            N.M0s8NeYn(requestData.nativeResultObject, this, NetError.ERR_MISCONFIGURED_AUTH_ENVIRONMENT, null);
        } else {
            requestData.accountManager.getAccountsByTypeAndFeatures(this.mAccountType, strArr, new GetTokenCallback(this, requestData, 1), new Handler(ThreadUtils.getUiThreadLooper()));
        }
    }

    void getNextAuthToken(long j, String str, String str2, boolean z) {
        Context context = ContextUtils.sApplicationContext;
        RequestData requestData = new RequestData();
        requestData.authTokenType = HttpNegotiateConstants.SPNEGO_TOKEN_TYPE_BASE.concat(String.valueOf(str));
        requestData.accountManager = AccountManager.get(context);
        requestData.nativeResultObject = j;
        String[] strArr = {HttpNegotiateConstants.SPNEGO_FEATURE};
        requestData.options = new Bundle();
        if (str2 != null) {
            requestData.options.putString(HttpNegotiateConstants.KEY_INCOMING_AUTH_TOKEN, str2);
        }
        Bundle bundle = this.mSpnegoContext;
        if (bundle != null) {
            requestData.options.putBundle(HttpNegotiateConstants.KEY_SPNEGO_CONTEXT, bundle);
        }
        requestData.options.putBoolean(HttpNegotiateConstants.KEY_CAN_DELEGATE, z);
        ApplicationStatus.ApplicationStateListener applicationStateListener = ApplicationStatus.sNativeApplicationStateListener;
        requestTokenWithoutActivity(context, requestData, strArr);
    }

    public boolean lacksPermission(Context context, String str, boolean z) {
        if (z || context.checkPermission(str, Process.myPid(), Process.myUid()) == 0) {
            return false;
        }
        return true;
    }
}
