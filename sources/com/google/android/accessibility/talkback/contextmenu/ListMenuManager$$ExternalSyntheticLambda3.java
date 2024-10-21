package com.google.android.accessibility.talkback.contextmenu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.ImageReader;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsDBHelper;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.eventprocessor.AccessibilityEventProcessor;
import com.google.android.accessibility.utils.AnalyticsCommon;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInCoordinator;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.signin.internal.SignInResponse;
import com.google.android.gms.tasks.OnCanceledCompletionListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.usagereporting.internal.OptInOptionsResultImpl;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ExecutionList;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.io.File;
import java.util.Set;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ListMenuManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ Object ListMenuManager$$ExternalSyntheticLambda3$ar$f$0;
    public final /* synthetic */ Object ListMenuManager$$ExternalSyntheticLambda3$ar$f$1;
    private final /* synthetic */ int switching_field;

    public ListMenuManager$$ExternalSyntheticLambda3(GoogleApiManager.GoogleApiProgressCallbacks googleApiProgressCallbacks, ConnectionResult connectionResult, int i) {
        this.switching_field = i;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0 = connectionResult;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1 = googleApiProgressCallbacks;
    }

    /* JADX WARN: Type inference failed for: r0v18, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v19, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v20, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v12, types: [com.google.android.accessibility.utils.Consumer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v27, types: [com.google.android.gms.common.api.internal.ListenerHolder$Notifier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v44, types: [java.lang.Object, com.google.android.gms.tasks.OnCompleteListener] */
    /* JADX WARN: Type inference failed for: r1v48, types: [com.google.android.gms.tasks.OnFailureListener, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v52, types: [com.google.android.gms.tasks.OnSuccessListener, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.switching_field) {
            case 0:
                ListMenuManager listMenuManager = (ListMenuManager) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0;
                listMenuManager.attachContentDescriptionOnTitle(listMenuManager.currentDialog, (String) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1);
                return;
            case 1:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = (TalkBackAnalyticsLoggerWithClearcut) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0;
                if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                    Object obj = this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1;
                    SQLiteDatabase safeGetWritableDatabase = talkBackAnalyticsLoggerWithClearcut.dbHelper.safeGetWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    CursorGranularity cursorGranularity = (CursorGranularity) obj;
                    String cursorGranularity2 = cursorGranularity.toString();
                    contentValues.put("granularity", Integer.valueOf(cursorGranularity.id));
                    contentValues.put("compound", cursorGranularity2);
                    contentValues.put("count", (Integer) 1);
                    TalkBackAnalyticsDBHelper.safeUpdate(safeGetWritableDatabase, "granularityMovements", cursorGranularity2, contentValues);
                    return;
                }
                return;
            case 2:
                ((ListMenuManager) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).openAlert$ar$class_merging$ar$class_merging((ApplicationModule) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1);
                return;
            case 3:
                ((AccessibilityEventProcessor) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).accessibilityEventListeners.remove(this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1);
                return;
            case 4:
                ((SpannableUtils$IdentifierSpan) ((DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2).onLabelsExported((File) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1);
                return;
            case 5:
                AnalyticsCommon analyticsCommon = (AnalyticsCommon) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0;
                OnDeviceTextDetectionLoadLogEvent optInOptions$ar$class_merging$ar$class_merging$ar$class_merging = analyticsCommon.getOptInOptions$ar$class_merging$ar$class_merging$ar$class_merging();
                if (optInOptions$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                    OptInOptionsResultImpl optInOptionsResultImpl = (OptInOptionsResultImpl) optInOptions$ar$class_merging$ar$class_merging$ar$class_merging.OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(optInOptionsResultImpl.optInOptions);
                    if (optInOptionsResultImpl.optInOptions.optInUsageReporting == 1) {
                        analyticsCommon.sendLog(this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1);
                        return;
                    }
                    return;
                }
                return;
            case 6:
                Logger logger = Performance.DEFAULT_LOGGER;
                this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1.accept(this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 7:
                return;
            case 8:
                this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1.onDownloadCompleted$ar$ds();
                return;
            case 9:
                this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1.onDownloadCompleted$ar$ds();
                return;
            case 10:
                ((CallbackToFutureAdapter$Completer) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).setException$ar$ds((Throwable) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1.get());
                return;
            case 11:
                GoogleApiManager.GoogleApiProgressCallbacks googleApiProgressCallbacks = (GoogleApiManager.GoogleApiProgressCallbacks) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1;
                GoogleApiManager.ClientConnection clientConnection = (GoogleApiManager.ClientConnection) GoogleApiManager.this.apiMap.get(googleApiProgressCallbacks.apiKey);
                if (clientConnection == null) {
                    return;
                }
                if (((ConnectionResult) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).isSuccess()) {
                    GoogleApiManager.GoogleApiProgressCallbacks googleApiProgressCallbacks2 = (GoogleApiManager.GoogleApiProgressCallbacks) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1;
                    googleApiProgressCallbacks2.serviceBound = true;
                    if (googleApiProgressCallbacks2.client.requiresSignIn()) {
                        ((GoogleApiManager.GoogleApiProgressCallbacks) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).tryGetRemoteService();
                        return;
                    }
                    try {
                        Api$Client api$Client = ((GoogleApiManager.GoogleApiProgressCallbacks) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).client;
                        api$Client.getRemoteService(null, api$Client.getScopesForConnectionlessNonSignIn());
                        return;
                    } catch (SecurityException e) {
                        Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                        ((GoogleApiManager.GoogleApiProgressCallbacks) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).client.disconnect("Failed to get service from broker.");
                        clientConnection.onConnectionFailed(new ConnectionResult(10));
                        return;
                    }
                }
                clientConnection.onConnectionFailed((ConnectionResult) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 12:
                Object obj2 = ((ListenerHolder) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).listener;
                ?? r1 = this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0;
                if (obj2 == null) {
                    return;
                }
                r1.notifyListener(obj2);
                return;
            case 13:
                SignInResponse signInResponse = (SignInResponse) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1;
                ConnectionResult connectionResult = signInResponse.connectionResult;
                boolean isSuccess = connectionResult.isSuccess();
                Object obj3 = this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0;
                if (isSuccess) {
                    ResolveAccountResponse resolveAccountResponse = signInResponse.resolveAccountResponse;
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(resolveAccountResponse);
                    ConnectionResult connectionResult2 = resolveAccountResponse.connectionResult;
                    if (!connectionResult2.isSuccess()) {
                        String valueOf = String.valueOf(String.valueOf(connectionResult2));
                        Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(valueOf), new Exception());
                        SignInCoordinator signInCoordinator = (SignInCoordinator) obj3;
                        signInCoordinator.mCallback$ar$class_merging$71a2fb1_0.onSignInFailed(connectionResult2);
                        signInCoordinator.mSignInClient$ar$class_merging.disconnect();
                        return;
                    }
                    SignInCoordinator signInCoordinator2 = (SignInCoordinator) obj3;
                    GoogleApiManager.GoogleApiProgressCallbacks googleApiProgressCallbacks3 = signInCoordinator2.mCallback$ar$class_merging$71a2fb1_0;
                    IAccountAccessor accountAccessor = resolveAccountResponse.getAccountAccessor();
                    Set set = signInCoordinator2.mScopes;
                    if (accountAccessor != null && set != null) {
                        googleApiProgressCallbacks3.resolvedAccountAccessor = accountAccessor;
                        googleApiProgressCallbacks3.scopes = set;
                        googleApiProgressCallbacks3.tryGetRemoteService();
                    } else {
                        Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                        googleApiProgressCallbacks3.onSignInFailed(new ConnectionResult(4));
                    }
                } else {
                    ((SignInCoordinator) obj3).mCallback$ar$class_merging$71a2fb1_0.onSignInFailed(connectionResult);
                }
                ((SignInCoordinator) obj3).mSignInClient$ar$class_merging.disconnect();
                return;
            case 14:
                ((Activity) ((WindowTrackerFactory) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).WindowTrackerFactory$ar$executorProvider).startActivity((Intent) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 15:
                ((Activity) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).startActivityForResult((Intent) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1, BrailleInputEvent.CMD_TURN_OFF_BRAILLE_DISPLAY);
                return;
            case 16:
                synchronized (((OnCanceledCompletionListener) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).lock) {
                    ((OnCanceledCompletionListener) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).OnCanceledCompletionListener$ar$onCanceled$ar$class_merging.onComplete((Task) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1);
                }
                return;
            case 17:
                synchronized (((OnCanceledCompletionListener) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).lock) {
                    ?? r12 = ((OnCanceledCompletionListener) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).OnCanceledCompletionListener$ar$onCanceled$ar$class_merging;
                    Exception exception = ((Task) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).getException();
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(exception);
                    r12.onFailure(exception);
                }
                return;
            case 18:
                synchronized (((OnCanceledCompletionListener) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).lock) {
                    ((OnCanceledCompletionListener) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).OnCanceledCompletionListener$ar$onCanceled$ar$class_merging.onSuccess(((Task) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).getResult());
                }
                return;
            case 19:
                ((ScreenCaptureController.ScreenCaptureImageProcessor) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).m189x7e161aa9((ImageReader) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0);
                return;
            default:
                Object obj4 = ((ExecutionList.RunnableExecutorPair) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0).ExecutionList$RunnableExecutorPair$ar$next;
                obj4.getClass();
                ((WorkQueue) obj4).onNewResults(((ImmutableList.Builder) this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1).build());
                return;
        }
    }

    public /* synthetic */ ListMenuManager$$ExternalSyntheticLambda3(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0 = obj;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1 = obj2;
    }

    public ListMenuManager$$ExternalSyntheticLambda3(Object obj, Object obj2, int i, byte[] bArr) {
        this.switching_field = i;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1 = obj2;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0 = obj;
    }

    public /* synthetic */ ListMenuManager$$ExternalSyntheticLambda3(Object obj, Object obj2, int i, char[] cArr) {
        this.switching_field = i;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$1 = obj;
        this.ListMenuManager$$ExternalSyntheticLambda3$ar$f$0 = obj2;
    }
}
