package com.google.android.gms.common.api.internal;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.ActionMenuPresenter;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.RetriableStream;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseLifecycleHelper extends LifecycleCallback implements DialogInterface.OnCancelListener {
    public final Handler connectionFailedHandler;
    protected final GoogleApiAvailability mApiAvailability;
    protected final AtomicReference mFailingConnectionResult;
    protected volatile boolean mStarted;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ConnectionFailedResolver implements Runnable {
        public final Object BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult;
        public final /* synthetic */ Object BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
        private final /* synthetic */ int switching_field;

        public ConnectionFailedResolver(Object obj, Object obj2, int i) {
            this.switching_field = i;
            this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0 = obj;
            this.BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult = obj2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v8, types: [android.content.DialogInterface$OnCancelListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v5, types: [android.content.DialogInterface$OnCancelListener, java.lang.Object] */
        @Override // java.lang.Runnable
        public final void run() {
            MenuBuilder.Callback callback;
            int i = this.switching_field;
            int i2 = 0;
            if (i != 0) {
                if (i != 1) {
                    RetriableStream retriableStream = (RetriableStream) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                    RetriableStream.Substream createSubstream = retriableStream.createSubstream(retriableStream.state.hedgingAttemptCount, false);
                    if (createSubstream == null) {
                        return;
                    }
                    ((RetriableStream) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).callExecutor.execute(new DelayedClientCall.AnonymousClass4(this, createSubstream, 19));
                    return;
                }
                MenuBuilder menuBuilder = ((ActionMenuPresenter) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).mMenu;
                if (menuBuilder != null && (callback = menuBuilder.mCallback) != null) {
                    callback.onMenuModeChange(menuBuilder);
                }
                View view = (View) ((ActionMenuPresenter) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).mMenuView;
                if (view != null && view.getWindowToken() != null && ((MenuPopupHelper) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult).tryShow()) {
                    ((ActionMenuPresenter) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).mOverflowPopup = (ActionMenuPresenter.OverflowPopup) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult;
                }
                ((ActionMenuPresenter) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).mPostedOpenRunnable$ar$class_merging = null;
                return;
            }
            if (((BaseLifecycleHelper) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).mStarted) {
                ConnectionResult connectionResult = (ConnectionResult) ((OrderVerifyingClientCall.State) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult).OrderVerifyingClientCall$State$ar$cancellationStatus;
                if (connectionResult.hasResolution()) {
                    Object obj = this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                    PendingIntent pendingIntent = connectionResult.pendingIntent;
                    Activity activity = ((LifecycleCallback) obj).getActivity();
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(pendingIntent);
                    ((BaseLifecycleHelper) obj).mLifecycleFragment.startActivityForResult(GoogleApiActivity.getIntentForResolution(activity, pendingIntent, ((OrderVerifyingClientCall.State) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult).type$ar$edu$88c656f2_0, false), 1);
                    return;
                }
                Object obj2 = this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                if (((BaseLifecycleHelper) obj2).mApiAvailability.getErrorResolutionIntent(((LifecycleCallback) obj2).getActivity(), connectionResult.statusCode, null) != null) {
                    ?? r1 = this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                    int i3 = connectionResult.statusCode;
                    Activity activity2 = ((LifecycleCallback) r1).getActivity();
                    GoogleApiAvailability googleApiAvailability = ((BaseLifecycleHelper) r1).mApiAvailability;
                    Dialog errorDialog$ar$ds = googleApiAvailability.getErrorDialog$ar$ds(activity2, i3, new DialogRedirect() { // from class: com.google.android.gms.common.internal.DialogRedirect.3
                        final /* synthetic */ LifecycleFragment val$fragment;
                        final /* synthetic */ Intent val$intent;

                        public AnonymousClass3(Intent intent, LifecycleFragment lifecycleFragment) {
                            r1 = intent;
                            r2 = lifecycleFragment;
                        }

                        @Override // com.google.android.gms.common.internal.DialogRedirect
                        public final void redirect() {
                            Intent intent = r1;
                            if (intent != null) {
                                r2.startActivityForResult(intent, 2);
                            }
                        }
                    }, r1);
                    if (errorDialog$ar$ds != null) {
                        googleApiAvailability.showDialogFragment(activity2, errorDialog$ar$ds, "GooglePlayServicesErrorDialog", r1);
                        return;
                    }
                    return;
                }
                if (connectionResult.statusCode == 18) {
                    ?? r0 = this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                    Activity activity3 = ((LifecycleCallback) r0).getActivity();
                    ProgressBar progressBar = new ProgressBar(activity3, null, R.attr.progressBarStyleLarge);
                    progressBar.setIndeterminate(true);
                    progressBar.setVisibility(0);
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity3);
                    builder.setView(progressBar);
                    builder.setMessage(ConnectionErrorMessages.getErrorMessage(activity3, 18));
                    builder.setPositiveButton("", (DialogInterface.OnClickListener) null);
                    AlertDialog create = builder.create();
                    ((BaseLifecycleHelper) r0).mApiAvailability.showDialogFragment(activity3, create, "GooglePlayServicesUpdatingDialog", r0);
                    Context applicationContext = ((LifecycleCallback) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).getActivity().getApplicationContext();
                    GooglePlayServicesUpdatedReceiver.Callback callback2 = new GooglePlayServicesUpdatedReceiver.Callback(this, create);
                    IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                    intentFilter.addDataScheme("package");
                    GooglePlayServicesUpdatedReceiver googlePlayServicesUpdatedReceiver = new GooglePlayServicesUpdatedReceiver(callback2);
                    if (SpannableUtils$NonCopyableTextSpan.isAtLeastT()) {
                        if (true == SpannableUtils$NonCopyableTextSpan.isAtLeastT()) {
                            i2 = 2;
                        }
                        applicationContext.registerReceiver(googlePlayServicesUpdatedReceiver, intentFilter, i2);
                    } else {
                        applicationContext.registerReceiver(googlePlayServicesUpdatedReceiver, intentFilter);
                    }
                    googlePlayServicesUpdatedReceiver.mContext = applicationContext;
                    if (!GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating$ar$ds(applicationContext)) {
                        callback2.onUpdated();
                        googlePlayServicesUpdatedReceiver.unregister();
                        return;
                    }
                    return;
                }
                ((BaseLifecycleHelper) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).markErrorResolutionFailed(connectionResult, ((OrderVerifyingClientCall.State) this.BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult).type$ar$edu$88c656f2_0);
            }
        }
    }

    public BaseLifecycleHelper(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.mFailingConnectionResult = new AtomicReference(null);
        this.connectionFailedHandler = new TracingHandler(Looper.getMainLooper());
        this.mApiAvailability = googleApiAvailability;
    }

    private static final int getFailingClientId$ar$ds$ar$class_merging$ar$class_merging(OrderVerifyingClientCall.State state) {
        if (state == null) {
            return -1;
        }
        return state.type$ar$edu$88c656f2_0;
    }

    public final void markErrorResolutionFailed(ConnectionResult connectionResult, int i) {
        this.mFailingConnectionResult.set(null);
        onErrorResolutionFailed(connectionResult, i);
    }

    public final void markErrorsResolved() {
        this.mFailingConnectionResult.set(null);
        onErrorsResolved();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onActivityResult(int i, int i2, Intent intent) {
        OrderVerifyingClientCall.State state = (OrderVerifyingClientCall.State) this.mFailingConnectionResult.get();
        if (i != 1) {
            if (i == 2) {
                int isGooglePlayServicesAvailable = this.mApiAvailability.isGooglePlayServicesAvailable(getActivity());
                if (isGooglePlayServicesAvailable == 0) {
                    markErrorsResolved();
                    return;
                } else if (state != null) {
                    if (((ConnectionResult) state.OrderVerifyingClientCall$State$ar$cancellationStatus).statusCode == 18 && isGooglePlayServicesAvailable == 18) {
                        return;
                    }
                } else {
                    return;
                }
            }
        } else {
            if (i2 == -1) {
                markErrorsResolved();
                return;
            }
            if (i2 == 0) {
                if (state != null) {
                    int i3 = 13;
                    if (intent != null) {
                        i3 = intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                    }
                    markErrorResolutionFailed(new ConnectionResult(i3, null, ((ConnectionResult) state.OrderVerifyingClientCall$State$ar$cancellationStatus).toString()), getFailingClientId$ar$ds$ar$class_merging$ar$class_merging(state));
                    return;
                }
                return;
            }
        }
        if (state != null) {
            markErrorResolutionFailed((ConnectionResult) state.OrderVerifyingClientCall$State$ar$cancellationStatus, state.type$ar$edu$88c656f2_0);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        markErrorResolutionFailed(new ConnectionResult(13, null), getFailingClientId$ar$ds$ar$class_merging$ar$class_merging((OrderVerifyingClientCall.State) this.mFailingConnectionResult.get()));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(Bundle bundle) {
        OrderVerifyingClientCall.State state;
        if (bundle != null) {
            AtomicReference atomicReference = this.mFailingConnectionResult;
            if (bundle.getBoolean("resolving_error", false)) {
                state = new OrderVerifyingClientCall.State(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
            } else {
                state = null;
            }
            atomicReference.set(state);
        }
    }

    protected abstract void onErrorResolutionFailed(ConnectionResult connectionResult, int i);

    protected abstract void onErrorsResolved();

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle) {
        OrderVerifyingClientCall.State state = (OrderVerifyingClientCall.State) this.mFailingConnectionResult.get();
        if (state == null) {
            return;
        }
        bundle.putBoolean("resolving_error", true);
        bundle.putInt("failed_client_id", state.type$ar$edu$88c656f2_0);
        bundle.putInt("failed_status", ((ConnectionResult) state.OrderVerifyingClientCall$State$ar$cancellationStatus).statusCode);
        bundle.putParcelable("failed_resolution", ((ConnectionResult) state.OrderVerifyingClientCall$State$ar$cancellationStatus).pendingIntent);
    }
}
