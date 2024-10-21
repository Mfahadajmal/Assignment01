package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GapWorker;
import androidx.collection.ArraySet;
import androidx.core.content.ContextCompat$Api30Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.internal.ApiCallRunner;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.ConnectionlessLifecycleHelper;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.GoogleApiWrapper;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.SupportLifecycleFragmentImpl;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import io.grpc.okhttp.internal.OptionalMethod;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleApi {
    public final OptionalMethod api$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ApiKey apiKey;
    public final Api$ApiOptions apiOptions;
    public final AppLifecycleMonitor attributionSourceWrapper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final String attributionTag;
    public final Context context;
    public final int id;
    public final Looper looper;
    protected final GoogleApiManager manager;
    private final SpannableUtils$IdentifierSpan mapper$ar$class_merging$ar$class_merging$ar$class_merging;
    public final GoogleApiClient wrapper;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Settings {
        public static final Settings DEFAULT_SETTINGS = new Builder().build();
        public final Looper looper;
        public final SpannableUtils$IdentifierSpan mapper$ar$class_merging$ar$class_merging$ar$class_merging;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public Object GoogleApi$Settings$Builder$ar$looper;
            public Object GoogleApi$Settings$Builder$ar$mapper$ar$class_merging;

            public Builder() {
            }

            public final Settings build() {
                if (this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging == null) {
                    this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging = new SpannableUtils$IdentifierSpan();
                }
                if (this.GoogleApi$Settings$Builder$ar$looper == null) {
                    this.GoogleApi$Settings$Builder$ar$looper = Looper.getMainLooper();
                }
                return new Settings((SpannableUtils$IdentifierSpan) this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging, (Looper) this.GoogleApi$Settings$Builder$ar$looper);
            }

            /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v10, types: [java.util.List, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v6, types: [java.util.List, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v9, types: [java.util.List, java.lang.Object] */
            public final GestureShortcutMapping.TalkBackGesture getPrioritizedGesture() {
                if (!this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging.isEmpty()) {
                    Collections.sort(this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging, new GapWorker.AnonymousClass1(7));
                    return (GestureShortcutMapping.TalkBackGesture) this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging.get(0);
                }
                if (!this.GoogleApi$Settings$Builder$ar$looper.isEmpty()) {
                    Collections.sort(this.GoogleApi$Settings$Builder$ar$looper, new GapWorker.AnonymousClass1(7));
                    return (GestureShortcutMapping.TalkBackGesture) this.GoogleApi$Settings$Builder$ar$looper.get(0);
                }
                return null;
            }

            public Builder(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
                this.GoogleApi$Settings$Builder$ar$looper = accessibilityNodeInfoCompat;
                this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging = eventId;
            }

            public Builder(byte[] bArr, byte[] bArr2) {
                this();
            }

            public Builder(char[] cArr) {
                this.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging = new ArrayList();
                this.GoogleApi$Settings$Builder$ar$looper = new ArrayList();
            }
        }

        public Settings(SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan, Looper looper) {
            this.mapper$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan;
            this.looper = looper;
        }
    }

    public GoogleApi(Context context, Activity activity, OptionalMethod optionalMethod, Api$ApiOptions api$ApiOptions, Settings settings) {
        SupportLifecycleFragmentImpl supportLifecycleFragmentImpl;
        AttributionSource attributionSource;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(context, "Null context is not permitted.");
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(optionalMethod, "Api must not be null.");
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(applicationContext, "The provided context did not have an application context.");
        this.context = applicationContext;
        AppLifecycleMonitor appLifecycleMonitor = null;
        String attributionTag = (Build.VERSION.SDK_INT < 30 || context == null || Build.VERSION.SDK_INT < 30) ? null : ContextCompat$Api30Impl.getAttributionTag(context);
        this.attributionTag = attributionTag;
        if (Build.VERSION.SDK_INT >= 31 && context != null) {
            attributionSource = context.getAttributionSource();
            appLifecycleMonitor = new AppLifecycleMonitor(attributionSource, (byte[]) null);
        }
        this.attributionSourceWrapper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.api$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        this.apiOptions = api$ApiOptions;
        this.looper = settings.looper;
        ApiKey apiKey = new ApiKey(optionalMethod, api$ApiOptions, attributionTag);
        this.apiKey = apiKey;
        this.wrapper = new GoogleApiWrapper(this);
        GoogleApiManager googleApiManager = GoogleApiManager.getInstance(applicationContext);
        this.manager = googleApiManager;
        this.id = googleApiManager.nextApiInstanceId.getAndIncrement();
        this.mapper$ar$class_merging$ar$class_merging$ar$class_merging = settings.mapper$ar$class_merging$ar$class_merging$ar$class_merging;
        if (activity != null && Looper.myLooper() == Looper.getMainLooper()) {
            Object obj = new AppLifecycleMonitor(activity).AppLifecycleMonitor$ar$tracker;
            WeakReference weakReference = (WeakReference) SupportLifecycleFragmentImpl.fragmentByActivity.get(obj);
            if (weakReference == null || (supportLifecycleFragmentImpl = (SupportLifecycleFragmentImpl) weakReference.get()) == null) {
                try {
                    supportLifecycleFragmentImpl = (SupportLifecycleFragmentImpl) ((FragmentActivity) obj).getSupportFragmentManager().findFragmentByTag("SLifecycleFragmentImpl");
                    if (supportLifecycleFragmentImpl == null || supportLifecycleFragmentImpl.isRemoving()) {
                        supportLifecycleFragmentImpl = new SupportLifecycleFragmentImpl();
                        BackStackRecord backStackRecord = new BackStackRecord(((FragmentActivity) obj).getSupportFragmentManager());
                        backStackRecord.add$ar$ds$4410556b_0(supportLifecycleFragmentImpl, "SLifecycleFragmentImpl");
                        backStackRecord.commitAllowingStateLoss$ar$ds();
                    }
                    SupportLifecycleFragmentImpl.fragmentByActivity.put(obj, new WeakReference(supportLifecycleFragmentImpl));
                } catch (ClassCastException e) {
                    throw new IllegalStateException("Fragment with tag SLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
                }
            }
            ConnectionlessLifecycleHelper connectionlessLifecycleHelper = (ConnectionlessLifecycleHelper) supportLifecycleFragmentImpl.getCallbackOrNull$ar$ds(ConnectionlessLifecycleHelper.class);
            connectionlessLifecycleHelper = connectionlessLifecycleHelper == null ? new ConnectionlessLifecycleHelper(supportLifecycleFragmentImpl, googleApiManager) : connectionlessLifecycleHelper;
            connectionlessLifecycleHelper.managedApiKeys.add(apiKey);
            googleApiManager.registerLifecycleHelper(connectionlessLifecycleHelper);
        }
        Handler handler = googleApiManager.handler;
        handler.sendMessage(handler.obtainMessage(7, this));
    }

    private final Task doNonListenerCall(int i, TaskApiCall taskApiCall) {
        AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((short[]) null);
        int i2 = taskApiCall.methodKey;
        GoogleApiManager googleApiManager = this.manager;
        googleApiManager.maybeAddInvocationListener$ar$class_merging$ar$class_merging(appLifecycleMonitor, i2, this);
        ApiCallRunner.TaskRunner taskRunner = new ApiCallRunner.TaskRunner(i, taskApiCall, appLifecycleMonitor, this.mapper$ar$class_merging$ar$class_merging$ar$class_merging);
        Handler handler = googleApiManager.handler;
        handler.sendMessage(handler.obtainMessage(4, new PhenotypeProcessReaper(taskRunner, googleApiManager.signOutCount.get(), this)));
        return (Task) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
    }

    public final ClientSettings.Builder createClientSettingsBuilder() {
        Set emptySet;
        GoogleSignInAccount googleSignInAccount;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        Api$ApiOptions api$ApiOptions = this.apiOptions;
        Account account = null;
        if ((api$ApiOptions instanceof Api$ApiOptions.HasGoogleSignInAccountOptions) && (googleSignInAccount = ((Api$ApiOptions.HasGoogleSignInAccountOptions) api$ApiOptions).getGoogleSignInAccount()) != null) {
            String str = googleSignInAccount.email;
            if (str != null) {
                account = new Account(str, "com.google");
            }
        } else {
            Api$ApiOptions api$ApiOptions2 = this.apiOptions;
            if (api$ApiOptions2 instanceof Api$ApiOptions.HasAccountOptions) {
                account = ((Api$ApiOptions.HasAccountOptions) api$ApiOptions2).getAccount();
            }
        }
        builder.ClientSettings$Builder$ar$account = account;
        Api$ApiOptions api$ApiOptions3 = this.apiOptions;
        if (api$ApiOptions3 instanceof Api$ApiOptions.HasGoogleSignInAccountOptions) {
            GoogleSignInAccount googleSignInAccount2 = ((Api$ApiOptions.HasGoogleSignInAccountOptions) api$ApiOptions3).getGoogleSignInAccount();
            if (googleSignInAccount2 == null) {
                emptySet = Collections.emptySet();
            } else {
                emptySet = googleSignInAccount2.getRequestedScopes();
            }
        } else {
            emptySet = Collections.emptySet();
        }
        if (builder.ClientSettings$Builder$ar$requiredScopes == null) {
            builder.ClientSettings$Builder$ar$requiredScopes = new ArraySet();
        }
        ((ArraySet) builder.ClientSettings$Builder$ar$requiredScopes).addAll(emptySet);
        builder.ClientSettings$Builder$ar$realClientClassName = this.context.getClass().getName();
        builder.ClientSettings$Builder$ar$realClientPackageName = this.context.getPackageName();
        return builder;
    }

    public final Task doBestEffortWrite(TaskApiCall taskApiCall) {
        return doNonListenerCall(2, taskApiCall);
    }

    public final void doNonListenerCall$ar$ds(int i, BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        boolean z = true;
        if (!baseImplementation$ApiMethodImpl.isInChain && !((Boolean) BasePendingResult.sTransformRunning.get()).booleanValue()) {
            z = false;
        }
        baseImplementation$ApiMethodImpl.isInChain = z;
        GoogleApiManager googleApiManager = this.manager;
        googleApiManager.handler.sendMessage(googleApiManager.handler.obtainMessage(4, new PhenotypeProcessReaper(new ApiCallRunner.PendingResultApiCallRunner(i, baseImplementation$ApiMethodImpl), googleApiManager.signOutCount.get(), this)));
    }

    public final Task doRead(TaskApiCall taskApiCall) {
        return doNonListenerCall(0, taskApiCall);
    }

    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Object, java.lang.Runnable] */
    public final Task doRegisterEventListener$ar$class_merging$ar$class_merging$ar$class_merging(OptionalMethod optionalMethod) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(((RegisterListenerMethod) optionalMethod.OptionalMethod$ar$methodName).getListenerKey(), "Listener has already been released.");
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(((UnregisterListenerMethod) optionalMethod.OptionalMethod$ar$methodParams).listenerKey, "Listener has already been released.");
        AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((short[]) null);
        RegisterListenerMethod registerListenerMethod = (RegisterListenerMethod) optionalMethod.OptionalMethod$ar$methodName;
        int i = registerListenerMethod.methodKey;
        GoogleApiManager googleApiManager = this.manager;
        googleApiManager.maybeAddInvocationListener$ar$class_merging$ar$class_merging(appLifecycleMonitor, i, this);
        ApiCallRunner.RegisterListenerRunner registerListenerRunner = new ApiCallRunner.RegisterListenerRunner(new OptionalMethod(registerListenerMethod, (UnregisterListenerMethod) optionalMethod.OptionalMethod$ar$methodParams, (Runnable) optionalMethod.OptionalMethod$ar$returnType, (byte[]) null), appLifecycleMonitor);
        Handler handler = googleApiManager.handler;
        handler.sendMessage(handler.obtainMessage(8, new PhenotypeProcessReaper(registerListenerRunner, googleApiManager.signOutCount.get(), this)));
        return (Task) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
    }

    public final Task doWrite(TaskApiCall taskApiCall) {
        return doNonListenerCall(1, taskApiCall);
    }

    public GoogleApi(Context context, OptionalMethod optionalMethod, Api$ApiOptions api$ApiOptions, Settings settings) {
        this(context, null, optionalMethod, api$ApiOptions, settings);
    }
}
