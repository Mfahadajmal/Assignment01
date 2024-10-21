package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Api$Client extends Api$AnyClient {
    void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

    void disconnect(String str);

    Feature[] getAvailableFeatures();

    void getEndpointPackageName$ar$ds();

    String getLastDisconnectMessage();

    int getMinApkVersion();

    void getRemoteService(IAccountAccessor iAccountAccessor, Set set);

    Set getScopesForConnectionlessNonSignIn();

    boolean isConnected();

    boolean isConnecting();

    void onUserSignOut$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl);

    void requiresGooglePlayServices$ar$ds();

    boolean requiresSignIn();
}
