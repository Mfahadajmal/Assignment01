package android.support.v4.app;

import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.SpecialEffectsController;
import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.lifecycle.ViewModelStore;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroupAdapter;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.background.systemalarm.ConstraintProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.utils.PackageManagerHelper;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.TouchDots;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.gms.common.api.internal.ConnectionlessLifecycleHelper;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.sampling.Sampler;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.play.core.splitcompat.FileStorage;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.scone.proto.Service$SurveyTriggerRequest;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigRequest;
import io.grpc.CallCredentials$RequestInfo;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.internal.SharedResourceHolder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.scheduling.WorkQueue;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
    public final /* synthetic */ Object DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
    private final /* synthetic */ int switching_field;

    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(Intent intent, Context context, BroadcastReceiver.PendingResult pendingResult, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = intent;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = context;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = pendingResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v117, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v28, types: [java.lang.Object, com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback] */
    /* JADX WARN: Type inference failed for: r0v90, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.lang.Object, com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback] */
    /* JADX WARN: Type inference failed for: r1v19, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v42, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v43, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v46, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.Object, com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback] */
    /* JADX WARN: Type inference failed for: r2v43, types: [java.util.concurrent.Future, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object, com.google.android.accessibility.brailleime.input.BrailleInputView$Callback] */
    @Override // java.lang.Runnable
    public final void run() {
        int preferenceAdapterPosition;
        int i = 4;
        Bundle bundle = null;
        byte b = 0;
        boolean z = true;
        switch (this.switching_field) {
            case 0:
                DefaultSpecialEffectsController$TransitionEffect defaultSpecialEffectsController$TransitionEffect = (DefaultSpecialEffectsController$TransitionEffect) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                FragmentTransition.callSharedElementStartEnd(((SpecialEffectsController.Operation) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).fragment, ((SpecialEffectsController.Operation) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).fragment, defaultSpecialEffectsController$TransitionEffect.isPop, defaultSpecialEffectsController$TransitionEffect.lastInViews, false);
                return;
            case 1:
                int i2 = DefaultSpecialEffectsController$AnimationEffect$onCommit$1.DefaultSpecialEffectsController$AnimationEffect$onCommit$1$ar$NoOp;
                ((ViewGroup) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).endViewTransition((View) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0);
                Object obj = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                ((DefaultSpecialEffectsController$AnimationEffect) obj).animationInfo.operation.completeEffect((SpecialEffectsController.Effect) obj);
                return;
            case 2:
                FragmentTransitionImpl.getBoundsOnScreen$ar$ds((View) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1, (Rect) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2);
                return;
            case 3:
                RecyclerView.Adapter adapter = ((PreferenceFragmentCompat) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).mList.mAdapter;
                if (!(adapter instanceof PreferenceGroupAdapter)) {
                    if (adapter == null) {
                        return;
                    } else {
                        throw new IllegalStateException("Adapter must implement PreferencePositionCallback");
                    }
                }
                Object obj2 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                if (obj2 != null) {
                    preferenceAdapterPosition = ((PreferenceGroupAdapter) adapter).getPreferenceAdapterPosition((Preference) obj2);
                } else {
                    preferenceAdapterPosition = ((PreferenceGroupAdapter) adapter).getPreferenceAdapterPosition((String) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1);
                }
                if (preferenceAdapterPosition != -1) {
                    ((PreferenceFragmentCompat) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).mList.scrollToPosition(preferenceAdapterPosition);
                    return;
                }
                adapter.registerAdapterDataObserver$ar$class_merging(new PreferenceFragmentCompat.ScrollToPreferenceObserver(adapter, ((PreferenceFragmentCompat) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).mList, (Preference) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2, (String) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1));
                return;
            case 4:
                boolean z2 = ((AtomicBoolean) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).get();
                ?? r1 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
                Object obj3 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                if (z2) {
                    return;
                }
                try {
                    r1.invoke();
                    ((CallbackToFutureAdapter$Completer) obj3).set$ar$ds(null);
                    return;
                } catch (Throwable th) {
                    ((CallbackToFutureAdapter$Completer) obj3).setException$ar$ds(th);
                    return;
                }
            case 5:
                boolean z3 = ((AtomicBoolean) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).get();
                ?? r12 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
                Object obj4 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                if (z3) {
                    return;
                }
                try {
                    ((CallbackToFutureAdapter$Completer) obj4).set$ar$ds(r12.invoke());
                    return;
                } catch (Throwable th2) {
                    ((CallbackToFutureAdapter$Completer) obj4).setException$ar$ds(th2);
                    return;
                }
            case 6:
                Object obj5 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0;
                ?? r13 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
                Object obj6 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                try {
                    z = ((Boolean) r13.get()).booleanValue();
                } catch (InterruptedException | ExecutionException unused) {
                }
                synchronized (((Processor) obj6).mLock) {
                    WorkGenerationalId workGenerationalId = ((WorkerWrapper) obj5).getWorkGenerationalId();
                    String str = workGenerationalId.workSpecId;
                    if (((Processor) obj6).getWorkerWrapperUnsafe(str) == obj5) {
                        ((Processor) obj6).cleanUpWorkerUnsafe(str);
                    }
                    Logger.get$ar$ds$16341a92_0();
                    obj6.getClass().getSimpleName();
                    Iterator it = ((Processor) obj6).mOuterListeners.iterator();
                    while (it.hasNext()) {
                        ((ExecutionListener) it.next()).onExecuted(workGenerationalId, z);
                    }
                }
                return;
            case 7:
                ((Processor) ((WorkName) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).WorkName$ar$name).startWork$ar$class_merging$1716d22_0$ar$class_merging$ar$class_merging$ar$class_merging((ViewModelStore) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, (AppCompatTextClassifierHelper$Api26Impl) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2);
                return;
            case 8:
                try {
                    boolean booleanExtra = ((Intent) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).getBooleanExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", false);
                    boolean booleanExtra2 = ((Intent) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).getBooleanExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", false);
                    boolean booleanExtra3 = ((Intent) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).getBooleanExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", false);
                    boolean booleanExtra4 = ((Intent) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).getBooleanExtra("KEY_NETWORK_STATE_PROXY_ENABLED", false);
                    Logger.get$ar$ds$16341a92_0();
                    int i3 = ConstraintProxyUpdateReceiver.ConstraintProxyUpdateReceiver$ar$NoOp;
                    PackageManagerHelper.setComponentEnabled((Context) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, ConstraintProxy.BatteryNotLowProxy.class, booleanExtra);
                    PackageManagerHelper.setComponentEnabled((Context) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, ConstraintProxy.BatteryChargingProxy.class, booleanExtra2);
                    PackageManagerHelper.setComponentEnabled((Context) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, ConstraintProxy.StorageNotLowProxy.class, booleanExtra3);
                    PackageManagerHelper.setComponentEnabled((Context) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, ConstraintProxy.NetworkStateProxy.class, booleanExtra4);
                    return;
                } finally {
                    Object obj7 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                }
            case 9:
                KeyboardView keyboardView = (KeyboardView) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
                keyboardView.brailleInputView = new BrailleInputView(keyboardView.context, this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, keyboardView.getScreenSize(), (BrailleInputOptions) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2);
                keyboardView.brailleInputView.inputPlane.multitouchHandler.isAccumulationMode = BrailleUserPreferences.readAccumulateMode(keyboardView.context);
                Context context = keyboardView.context;
                BrailleInputView brailleInputView = keyboardView.brailleInputView;
                if (BrailleUserPreferences.readLayoutMode(context) != TouchDots.TABLETOP) {
                    z = false;
                }
                brailleInputView.setTableMode(z);
                RetryingNameResolver.ResolutionResultListener resolutionResultListener = keyboardView.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                KeyboardView.ViewContainer viewContainer = keyboardView.viewContainer;
                BrailleInputView brailleInputView2 = keyboardView.brailleInputView;
                resolutionResultListener.getClass();
                viewContainer.addView$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(brailleInputView2, new RetryingNameResolver.ResolutionResultListener(resolutionResultListener, b == true ? 1 : 0));
                return;
            case 10:
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = (TalkBackAnalyticsLoggerWithClearcut) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                    talkBackAnalyticsLoggerWithClearcut.dbHelper.cacheSettingChange((String) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, (String) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1, 1);
                    return;
                }
                return;
            case 11:
                try {
                    ?? r0 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    for (Label label : r0) {
                        if (label != null) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("package_name", label.mPackageName);
                            jSONObject2.put("package_signature", label.mPackageSignature);
                            jSONObject2.put("view_name", label.mViewName);
                            jSONObject2.put("label_text", label.mText);
                            jSONObject2.put("locale", label.mLocale);
                            jSONObject2.put("package_version", label.mPackageVersion);
                            jSONObject2.put("timestamp", label.mTimestampMillis);
                            jSONArray.put(jSONObject2);
                        }
                    }
                    jSONObject.put("labels_array", jSONArray);
                    String jSONObject3 = jSONObject.toString();
                    if (jSONObject3 != null) {
                        File file = new File(((Context) ((ProcessStatsCapture) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).ProcessStatsCapture$ar$context).getExternalCacheDir(), String.format("Talkback_custom_labels_%s.tbl", new SimpleDateFormat("MMddyyyy").format(new Date())));
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                        bufferedWriter.write(jSONObject3);
                        bufferedWriter.close();
                        if (this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 != null) {
                            ((Handler) ((ProcessStatsCapture) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).ProcessStatsCapture$ar$oomScoreAdjCapture).post(new ListMenuManager$$ExternalSyntheticLambda3(this, file, 4, (byte[]) null));
                            return;
                        }
                        return;
                    }
                    ((ProcessStatsCapture) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).notifyFailure$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging((SpannableUtils$IdentifierSpan) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2);
                    return;
                } catch (Exception unused2) {
                    ((ProcessStatsCapture) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).notifyFailure$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging((SpannableUtils$IdentifierSpan) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2);
                    LogUtils.e("CustomLabelMigrManager", "Failed to export labels", new Object[0]);
                    return;
                }
            case 12:
                return;
            case 13:
                this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1.onDownloadFailed$ar$ds();
                return;
            case 14:
                SharedResourceHolder.Instance instance = (SharedResourceHolder.Instance) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0;
                if (instance.refcount > 0) {
                    Object obj8 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                    Object obj9 = instance.SharedResourceHolder$Instance$ar$destroyTask;
                    if (obj9 != null) {
                        bundle = ((Bundle) obj9).getBundle((String) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1);
                    }
                    ((LifecycleCallback) obj8).onCreate(bundle);
                }
                if (((SharedResourceHolder.Instance) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).refcount >= 2) {
                    ((LifecycleCallback) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2).onStart();
                }
                if (((SharedResourceHolder.Instance) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).refcount >= 3) {
                    ((ConnectionlessLifecycleHelper) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2).registerManagedApiKeys();
                }
                if (((SharedResourceHolder.Instance) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).refcount >= 4) {
                    ((LifecycleCallback) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2).onStop();
                    return;
                }
                return;
            case 15:
                int i4 = DirectBootUtils.DirectBootUtils$ar$NoOp;
                if (((AtomicBoolean) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1).compareAndSet(false, true)) {
                    ((Context) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).unregisterReceiver((BroadcastReceiver) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2);
                    return;
                }
                return;
            case 16:
                Sampler.submitFireAndForget(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2, this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0, i), this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1);
                return;
            case 17:
                Object obj10 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                Object obj11 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
                try {
                    try {
                        ContextDataProvider.getDone(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0);
                    } catch (ExecutionException e) {
                        Log.w("PhenotypeBackgroundRecv", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0((String) obj11, "Failed to update local snapshot for "), e);
                    }
                    return;
                } finally {
                    ((BroadcastReceiver.PendingResult) obj10).finish();
                }
            case 18:
                ((NetworkCallerGrpc) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0).m207x3afd4cf2((Service$GetSurveyStartupConfigRequest) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1, (CallCredentials$RequestInfo) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2);
                return;
            case 19:
                ((NetworkCallerGrpc) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2).m208xb4d41164((Service$SurveyTriggerRequest) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1, (Stopwatch) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0);
                return;
            default:
                try {
                    Object obj12 = ((WorkQueue) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2).WorkQueue$ar$producerIndex;
                    Iterator it2 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1.iterator();
                    while (it2.hasNext()) {
                        if (!((FileStorage) ((Verifier) obj12).Verifier$ar$fileStorage).fileForVerifiedSplit(((Intent) it2.next()).getStringExtra("split_id")).exists()) {
                            Object obj13 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                            ?? r14 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1;
                            ?? r2 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0;
                            Integer copyAndVerifySync = ((WorkQueue) obj13).copyAndVerifySync(r14);
                            if (copyAndVerifySync == null) {
                                return;
                            }
                            if (copyAndVerifySync.intValue() == 0) {
                                r2.whenVerified();
                                return;
                            } else {
                                r2.whenFailed(copyAndVerifySync.intValue());
                                return;
                            }
                        }
                    }
                    Object obj14 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2;
                    ?? r15 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0;
                    try {
                        if (!SplitCompat.internalInstall(NativeLibraryPathListMutex.getApplicationContext((Context) ((WorkQueue) obj14).WorkQueue$ar$buffer), true)) {
                            Log.e("SplitCompat", "Emulating splits failed.");
                            r15.whenFailed(-12);
                            return;
                        } else {
                            r15.whenEmulated();
                            return;
                        }
                    } catch (Exception e2) {
                        Log.e("SplitCompat", "Error emulating splits.", e2);
                        r15.whenFailed(-12);
                        return;
                    }
                } catch (Exception e3) {
                    Log.e("SplitCompat", "Error checking verified files.", e3);
                    this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0.whenFailed(-11);
                    return;
                }
        }
    }

    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference, String str, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = preferenceFragmentCompat;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = preference;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = str;
    }

    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(ProcessStatsCapture processStatsCapture, List list, SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = list;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = spannableUtils$IdentifierSpan;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = processStatsCapture;
    }

    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(SharedResourceHolder.Instance instance, LifecycleCallback lifecycleCallback, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = lifecycleCallback;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = "ConnectionlessLifecycleHelper";
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = instance;
    }

    public /* synthetic */ DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(Object obj, Object obj2, Object obj3, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = obj;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = obj2;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = obj3;
    }

    public /* synthetic */ DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(Object obj, Object obj2, Object obj3, int i, byte[] bArr) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = obj;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = obj2;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = obj3;
    }

    public /* synthetic */ DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(Object obj, Object obj2, Object obj3, int i, char[] cArr) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = obj;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = obj2;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = obj3;
    }

    public /* synthetic */ DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(Object obj, Object obj2, Object obj3, int i, short[] sArr) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = obj;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = obj2;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = obj3;
    }

    public /* synthetic */ DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(AtomicBoolean atomicBoolean, CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer, Function0 function0, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = atomicBoolean;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = callbackToFutureAdapter$Completer;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = function0;
    }

    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(WorkQueue workQueue, List list, DownloadedStateInterceptor$Callback downloadedStateInterceptor$Callback, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$1 = list;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$0 = downloadedStateInterceptor$Callback;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0$ar$f$2 = workQueue;
    }
}
