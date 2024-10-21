package com.google.android.gms.common.api.internal;

import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionlessLifecycleHelper extends BaseLifecycleHelper {
    public final ArraySet managedApiKeys;
    private final GoogleApiManager manager;

    public ConnectionlessLifecycleHelper(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager) {
        super(lifecycleFragment, GoogleApiAvailability.INSTANCE);
        this.managedApiKeys = new ArraySet();
        this.manager = googleApiManager;
        this.mLifecycleFragment.addCallback$ar$ds$e7521d11_0(this);
    }

    @Override // com.google.android.gms.common.api.internal.BaseLifecycleHelper
    protected final void onErrorResolutionFailed(ConnectionResult connectionResult, int i) {
        this.manager.onErrorResolutionFailed(connectionResult, i);
    }

    @Override // com.google.android.gms.common.api.internal.BaseLifecycleHelper
    protected final void onErrorsResolved() {
        this.manager.onErrorsResolved();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onResume() {
        registerManagedApiKeys();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        this.mStarted = true;
        registerManagedApiKeys();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        this.mStarted = false;
        Object obj = GoogleApiManager.lock;
        GoogleApiManager googleApiManager = this.manager;
        synchronized (obj) {
            if (googleApiManager.activeLifecycleHelper == this) {
                googleApiManager.activeLifecycleHelper = null;
                googleApiManager.activeLifecycleHelperApis.clear();
            }
        }
    }

    public final void registerManagedApiKeys() {
        if (!this.managedApiKeys.isEmpty()) {
            this.manager.registerLifecycleHelper(this);
        }
    }
}
