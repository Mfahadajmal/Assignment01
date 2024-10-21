package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GmsClient extends BaseGmsClient implements Api$Client {
    private static volatile Executor bindServiceExecutor;
    private final Account account;
    public final ClientSettings clientSettings;
    public final Set scopes;

    /* JADX INFO: Access modifiers changed from: protected */
    public GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.INSTANCE, i, new FloatingActionButton.ShadowDelegateImpl(connectionCallbacks), new FloatingActionButton.ShadowDelegateImpl(onConnectionFailedListener), clientSettings.realClientClassName);
        this.clientSettings = clientSettings;
        this.account = clientSettings.account;
        Set set = clientSettings.allRequestedScopes;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (!set.contains((Scope) it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.scopes = set;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Account getAccount() {
        return this.account;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public int getMinApkVersion() {
        throw null;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.api.Api$Client
    public final Set getScopesForConnectionlessNonSignIn() {
        if (requiresSignIn()) {
            return this.scopes;
        }
        return Collections.emptySet();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final void getBindServiceExecutor$ar$ds() {
    }
}
