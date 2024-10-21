package com.google.android.gms.auth;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.auth.IAuthManagerService;
import com.google.android.auth.IAuthManagerService$Stub$Proxy;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks$AwaitListener;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleAuthUtilLight {
    public static final String[] ACCEPTABLE_ACCOUNT_TYPES = {"com.google", "com.google.work", "cn.google"};
    public static final String KEY_CALLER_UID = "callerUid";
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    public static final ComponentName GET_TOKEN_SERVICE_COMPONENT_NAME = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    public static final WindowTrackerFactory logger$ar$class_merging$adff595e_0$ar$class_merging = new WindowTrackerFactory("GoogleAuthUtil");

    public static Object connectAndExecute$ar$class_merging$ar$ds(Context context, ComponentName componentName, GoogleAuthUtilLight$$ExternalSyntheticLambda3 googleAuthUtilLight$$ExternalSyntheticLambda3) {
        IAuthManagerService iAuthManagerService$Stub$Proxy;
        BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
        GmsClientSupervisor gmsClientSupervisor = GmsClientSupervisor.getInstance(context);
        try {
            try {
                if (gmsClientSupervisor.bindService$ar$ds(new GmsClientSupervisor.ConnectionStatusConfig(componentName, GmsClientSupervisor.defaultBindFlags), blockingServiceConnection, "GoogleAuthUtil")) {
                    try {
                        StrictModeUtils$VmPolicyBuilderCompatS.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
                        if (!blockingServiceConnection.mUsed) {
                            blockingServiceConnection.mUsed = true;
                            IBinder iBinder = (IBinder) blockingServiceConnection.blockingQueue.take();
                            Account account = googleAuthUtilLight$$ExternalSyntheticLambda3.f$0;
                            String str = googleAuthUtilLight$$ExternalSyntheticLambda3.f$1;
                            Bundle bundle = googleAuthUtilLight$$ExternalSyntheticLambda3.f$2;
                            Context context2 = googleAuthUtilLight$$ExternalSyntheticLambda3.f$3;
                            if (iBinder == null) {
                                iAuthManagerService$Stub$Proxy = null;
                            } else {
                                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
                                if (queryLocalInterface instanceof IAuthManagerService) {
                                    iAuthManagerService$Stub$Proxy = (IAuthManagerService) queryLocalInterface;
                                } else {
                                    iAuthManagerService$Stub$Proxy = new IAuthManagerService$Stub$Proxy(iBinder);
                                }
                            }
                            Bundle tokenByAccount = iAuthManagerService$Stub$Proxy.getTokenByAccount(account, str, bundle);
                            if (tokenByAccount != null) {
                                return extractTokenDataFrom$ar$ds(context2, tokenByAccount);
                            }
                            throw new IOException("Service call returned null");
                        }
                        throw new IllegalStateException("Cannot call get on this connection more than once");
                    } catch (RemoteException | InterruptedException | TimeoutException e) {
                        throw new IOException("Error on service connection.", e);
                    }
                }
                throw new IOException("Could not bind to service.");
            } finally {
                gmsClientSupervisor.unbindService$ar$ds(componentName, blockingServiceConnection);
            }
        } catch (SecurityException e2) {
            Log.w("GoogleAuthUtil", String.format("SecurityException while bind to auth service: %s", e2.getMessage()));
            throw new IOException("SecurityException while binding to Auth service.", e2);
        }
    }

    public static TokenData extractTokenDataFrom$ar$ds(Context context, Bundle bundle) {
        TokenData tokenData;
        Status status;
        ClassLoader classLoader = TokenData.class.getClassLoader();
        if (classLoader != null) {
            bundle.setClassLoader(classLoader);
        }
        Bundle bundle2 = bundle.getBundle("tokenDetails");
        if (bundle2 == null) {
            tokenData = null;
        } else {
            if (classLoader != null) {
                bundle2.setClassLoader(classLoader);
            }
            tokenData = (TokenData) bundle2.getParcelable("TokenData");
        }
        if (tokenData != null) {
            return tokenData;
        }
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("userRecoveryPendingIntent");
        Status[] values = Status.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i < length) {
                status = values[i];
                if (status.gaiaErrorCode.equals(string)) {
                    break;
                }
                i++;
            } else {
                status = Status.UNKNOWN;
                break;
            }
        }
        WindowTrackerFactory windowTrackerFactory = logger$ar$class_merging$adff595e_0$ar$class_merging;
        windowTrackerFactory.w(String.format("[GoogleAuthUtil] error status:%s with method:%s", status, "getTokenWithDetails"), new Object[0]);
        if (!Status.BAD_AUTHENTICATION.equals(status) && !Status.CAPTCHA.equals(status) && !Status.NEED_PERMISSION.equals(status) && !Status.NEED_REMOTE_CONSENT.equals(status) && !Status.NEEDS_BROWSER.equals(status) && !Status.USER_CANCEL.equals(status) && !Status.DEVICE_MANAGEMENT_REQUIRED.equals(status) && !Status.DM_INTERNAL_ERROR.equals(status) && !Status.DM_SYNC_DISABLED.equals(status) && !Status.DM_ADMIN_BLOCKED.equals(status) && !Status.DM_ADMIN_PENDING_APPROVAL.equals(status) && !Status.DM_STALE_SYNC_REQUIRED.equals(status) && !Status.DM_DEACTIVATED.equals(status) && !Status.DM_REQUIRED.equals(status) && !Status.THIRD_PARTY_DEVICE_MANAGEMENT_REQUIRED.equals(status) && !Status.DM_SCREENLOCK_REQUIRED.equals(status)) {
            if (!Status.NETWORK_ERROR.equals(status) && !Status.SERVICE_UNAVAILABLE.equals(status) && !Status.INTNERNAL_ERROR.equals(status) && !Status.AUTH_SECURITY_ERROR.equals(status) && !Status.ACCOUNT_NOT_PRESENT.equals(status)) {
                throw new GoogleAuthException(string);
            }
            throw new IOException(string);
        }
        PhenotypeFlag.maybeInit(context);
        if (pendingIntent != null && intent != null) {
            throw new UserRecoverableAuthException(string, UserRecoverableAuthException.Type.AUTH_INSTANTIATION);
        }
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.INSTANCE;
        int apkVersion = GooglePlayServicesUtilLight.getApkVersion(context);
        if (apkVersion >= 233800000 && pendingIntent == null) {
            windowTrackerFactory.e(String.format("Recovery PendingIntent is missing on current Gms version: %s for method: %s. It should always be present on or above Gms version %s. This indicates a bug in Gms implementation.", Integer.valueOf(apkVersion), "getTokenWithDetails", 233800000), new Object[0]);
        }
        if (intent == null) {
            windowTrackerFactory.e(String.format("no recovery Intent found with status=%s for method=%s. This shouldn't happen", string, "getTokenWithDetails"), new Object[0]);
        }
        throw new UserRecoverableAuthException(string, intent);
    }

    public static Object getResultFromTask$ar$ds(Task task) {
        try {
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotMainThread();
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotGoogleApiHandlerThread();
            if (task.isComplete()) {
                return SpannableUtils$NonCopyableTextSpan.getResultOrThrowExecutionException(task);
            }
            Tasks$AwaitListener tasks$AwaitListener = new Tasks$AwaitListener();
            SpannableUtils$NonCopyableTextSpan.addListener$ar$class_merging$33f29cd9_0(task, tasks$AwaitListener);
            tasks$AwaitListener.latch.await();
            return SpannableUtils$NonCopyableTextSpan.getResultOrThrowExecutionException(task);
        } catch (InterruptedException e) {
            String format = String.format("Interrupted while waiting for the task of %s to finish.", "token retrieval");
            logger$ar$class_merging$adff595e_0$ar$class_merging.w(format, new Object[0]);
            throw new IOException(format, e);
        } catch (CancellationException e2) {
            String format2 = String.format("Canceled while waiting for the task of %s to finish.", "token retrieval");
            logger$ar$class_merging$adff595e_0$ar$class_merging.w(format2, new Object[0]);
            throw new IOException(format2, e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof ApiException) {
                throw ((ApiException) cause);
            }
            String format3 = String.format("Unable to get a result for %s due to ExecutionException.", "token retrieval");
            logger$ar$class_merging$adff595e_0$ar$class_merging.w(format3, new Object[0]);
            throw new IOException(format3, e3);
        }
    }

    public static void validateAccount(Account account) {
        if (!TextUtils.isEmpty(account.name)) {
            String[] strArr = ACCEPTABLE_ACCOUNT_TYPES;
            for (int i = 0; i < 3; i++) {
                if (strArr[i].equals(account.type)) {
                    return;
                }
            }
            throw new IllegalArgumentException("Account type not supported");
        }
        throw new IllegalArgumentException("Account name cannot be empty!");
    }
}
