package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.SystemClock;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionInfo;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskImpl;
import com.google.search.mdi.aratea.proto.FeatureName;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MethodInvocationLoggingListener implements OnCompleteListener {
    private final ApiKey apiKey;
    private final GoogleApiManager googleApiManager;
    private final int methodKey;
    private final long methodStartElapsedRealtime;
    private final long methodStartTimeMillis;

    public MethodInvocationLoggingListener(GoogleApiManager googleApiManager, int i, ApiKey apiKey, long j, long j2) {
        this.googleApiManager = googleApiManager;
        this.methodKey = i;
        this.apiKey = apiKey;
        this.methodStartTimeMillis = j;
        this.methodStartElapsedRealtime = j2;
    }

    public static ConnectionTelemetryConfiguration getConfigIfShouldLogMethodInvocation(GoogleApiManager.ClientConnection clientConnection, BaseGmsClient baseGmsClient, int i) {
        ConnectionTelemetryConfiguration connectionTelemetryConfiguration;
        int[] iArr;
        int[] iArr2;
        ConnectionInfo connectionInfo = baseGmsClient.connectionInfo;
        if (connectionInfo == null) {
            connectionTelemetryConfiguration = null;
        } else {
            connectionTelemetryConfiguration = connectionInfo.connectionTelemetryConfiguration;
        }
        if (connectionTelemetryConfiguration == null || !connectionTelemetryConfiguration.methodInvocationTelemetryEnabled || ((iArr = connectionTelemetryConfiguration.methodInvocationMethodKeyAllowlist) != null ? !StrictModeUtils$VmPolicyBuilderCompatS.contains(iArr, i) : !((iArr2 = connectionTelemetryConfiguration.methodInvocationMethodKeyDisallowlist) == null || !StrictModeUtils$VmPolicyBuilderCompatS.contains(iArr2, i))) || clientConnection.numMethodInvocationsLogged >= connectionTelemetryConfiguration.maxMethodInvocationsLogged) {
            return null;
        }
        return connectionTelemetryConfiguration;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        GoogleApiManager.ClientConnection clientConnectionForKey;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j;
        long j2;
        if (this.googleApiManager.isClientTelemetryPossiblyEnabled()) {
            RootTelemetryConfiguration rootTelemetryConfiguration = RootTelemetryConfigManager.getInstance().config;
            if ((rootTelemetryConfiguration == null || rootTelemetryConfiguration.methodInvocationTelemetryEnabled) && (clientConnectionForKey = this.googleApiManager.getClientConnectionForKey(this.apiKey)) != null) {
                Object obj = clientConnectionForKey.client;
                if (obj instanceof BaseGmsClient) {
                    boolean z2 = true;
                    if (this.methodStartTimeMillis > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (rootTelemetryConfiguration != null) {
                        z &= rootTelemetryConfiguration.methodTimingTelemetryEnabled;
                        BaseGmsClient baseGmsClient = (BaseGmsClient) obj;
                        boolean hasConnectionInfo = baseGmsClient.hasConnectionInfo();
                        i = rootTelemetryConfiguration.batchPeriodMillis;
                        int i8 = rootTelemetryConfiguration.maxMethodInvocationsInBatch;
                        int i9 = rootTelemetryConfiguration.version;
                        if (hasConnectionInfo && !baseGmsClient.isConnecting()) {
                            ConnectionTelemetryConfiguration configIfShouldLogMethodInvocation = getConfigIfShouldLogMethodInvocation(clientConnectionForKey, baseGmsClient, this.methodKey);
                            if (configIfShouldLogMethodInvocation != null) {
                                if (!configIfShouldLogMethodInvocation.methodTimingTelemetryEnabled || this.methodStartTimeMillis <= 0) {
                                    z2 = false;
                                }
                                i8 = configIfShouldLogMethodInvocation.maxMethodInvocationsLogged;
                                i2 = i9;
                                z = z2;
                            } else {
                                return;
                            }
                        } else {
                            i2 = i9;
                        }
                        i3 = i8;
                    } else {
                        i = 5000;
                        i2 = 0;
                        i3 = 100;
                    }
                    GoogleApiManager googleApiManager = this.googleApiManager;
                    if (task.isSuccessful()) {
                        i6 = 0;
                        i5 = 0;
                    } else if (((TaskImpl) task).canceled) {
                        i5 = -1;
                        i6 = 100;
                    } else {
                        Exception exception = task.getException();
                        if (exception instanceof ApiException) {
                            Status status = ((ApiException) exception).mStatus;
                            i4 = status.statusCode;
                            ConnectionResult connectionResult = status.connectionResult;
                            if (connectionResult != null) {
                                i5 = connectionResult.statusCode;
                                i6 = i4;
                            }
                        } else {
                            i4 = FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu;
                        }
                        i5 = -1;
                        i6 = i4;
                    }
                    if (z) {
                        long j3 = this.methodStartTimeMillis;
                        long j4 = this.methodStartElapsedRealtime;
                        long currentTimeMillis = System.currentTimeMillis();
                        i7 = (int) (SystemClock.elapsedRealtime() - j4);
                        j = j3;
                        j2 = currentTimeMillis;
                    } else {
                        i7 = -1;
                        j = 0;
                        j2 = 0;
                    }
                    int i10 = ((BaseGmsClient) obj).gCoreServiceId;
                    Handler handler = googleApiManager.handler;
                    handler.sendMessage(handler.obtainMessage(18, new MethodInvocationMessage(new MethodInvocation(this.methodKey, i6, i5, j, j2, null, null, i10, i7), i2, i, i3)));
                }
            }
        }
    }
}
