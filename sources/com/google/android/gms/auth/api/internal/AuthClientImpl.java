package com.google.android.gms.auth.api.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.AuthProxyOptions;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthClientImpl extends GmsClient {
    private final Bundle authOptions;

    public AuthClientImpl(Context context, Looper looper, ClientSettings clientSettings, AuthProxyOptions authProxyOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 16, clientSettings, connectionCallbacks, onConnectionFailedListener);
        Bundle bundle;
        if (authProxyOptions == null) {
            bundle = new Bundle();
        } else {
            bundle = new Bundle(authProxyOptions.authOptions);
        }
        this.authOptions = bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
        if (queryLocalInterface instanceof IAuthService) {
            return (IAuthService) queryLocalInterface;
        }
        return new IAuthService$Stub$Proxy(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        return this.authOptions;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final boolean requiresSignIn() {
        String str;
        ClientSettings clientSettings = this.clientSettings;
        Account account = clientSettings.account;
        if (account != null) {
            str = account.name;
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            if (((AppLifecycleMonitor) clientSettings.optionalApiSettingsMap.get(AuthProxy.API$ar$class_merging$ar$class_merging$ar$class_merging)) == null) {
                if (!clientSettings.requiredScopes.isEmpty()) {
                    return true;
                }
                return false;
            }
            throw null;
        }
        return false;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }
}
