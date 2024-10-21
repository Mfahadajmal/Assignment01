package com.google.android.accessibility.talkback.trainingcommon;

import android.content.BroadcastReceiver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import com.google.android.accessibility.utils.ProximitySensor;
import com.google.android.accessibility.utils.SpellChecker;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.SignInCoordinator;
import com.google.android.gms.tasks.OnCanceledCompletionListener;
import com.google.android.gms.tasks.Tasks$AwaitListener;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentUiProviderImpl;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.foreground.DebouncedForegroundSignalAdapter;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService;
import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl;
import com.google.android.libraries.phenotype.client.stable.FlagStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TrainingActivity$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Object TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TrainingActivity$$ExternalSyntheticLambda1(Object obj, int i) {
        this.switching_field = i;
        this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v37, types: [java.lang.Object, com.google.android.apps.aicore.aidl.ICancellationCallback] */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.lang.Object, com.google.android.apps.aicore.aidl.ICancellationCallback] */
    /* JADX WARN: Type inference failed for: r0v42, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v56, types: [com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController$CaptureListener, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v57, types: [com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController$AuthorizationListener, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        float f;
        switch (this.switching_field) {
            case 0:
                ((TrainingActivity) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).finishOnAbort(false);
                return;
            case 1:
                if (((RepeatedAnnouncingHandler) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).accessibilityManager.isEnabled() && !TextUtils.isEmpty(((RepeatedAnnouncingHandler) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).announcement)) {
                    Object obj = this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                    AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
                    RepeatedAnnouncingHandler repeatedAnnouncingHandler = (RepeatedAnnouncingHandler) obj;
                    obtain.setContentDescription(repeatedAnnouncingHandler.announcement);
                    repeatedAnnouncingHandler.accessibilityManager.sendAccessibilityEvent(obtain);
                    RepeatedAnnouncingHandler repeatedAnnouncingHandler2 = (RepeatedAnnouncingHandler) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                    repeatedAnnouncingHandler2.handler.postDelayed(repeatedAnnouncingHandler2.idleAnnouncementTask, repeatedAnnouncingHandler2.repeatedDelayMs);
                    return;
                }
                return;
            case 2:
                TrainingActivity trainingActivity = (TrainingActivity) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                trainingActivity.passPageIdToService(trainingActivity.getCurrentPageId());
                trainingActivity.sendMessageToService(Message.obtain((Handler) null, 1));
                trainingActivity.sendMessageToService(Message.obtain((Handler) null, 7));
                trainingActivity.unregisterTalkBackEnabledReceiver();
                return;
            case 3:
                ((ProximitySensor) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).mShouldDropEvents = false;
                LogUtils.v("ProximitySensor", "Stopped filtering proximity events at %d.", Long.valueOf(System.currentTimeMillis()));
                return;
            case 4:
                Looper.prepare();
                SpellChecker spellChecker = (SpellChecker) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                LogUtils.v("SpellChecker", "Start a session for [%s]", spellChecker.text);
                if (TextUtils.isEmpty(spellChecker.text)) {
                    LogUtils.e("SpellChecker", "Spell checking in an empty text.", new Object[0]);
                } else {
                    TextServicesManager textServicesManager = spellChecker.textServicesManager;
                    if (textServicesManager == null) {
                        LogUtils.e("SpellChecker", "TextServicesManager is null.", new Object[0]);
                    } else {
                        SpellCheckerSession newSpellCheckerSession = textServicesManager.newSpellCheckerSession(null, null, spellChecker.listener, true);
                        if (newSpellCheckerSession == null) {
                            LogUtils.e("SpellChecker", "Fail to create a SpellingCheckerSession.", new Object[0]);
                        } else {
                            newSpellCheckerSession.getSentenceSuggestions(new TextInfo[]{new TextInfo(spellChecker.text.toString())}, 5);
                            Looper.loop();
                            return;
                        }
                    }
                }
                LogUtils.e("SpellChecker", "Fail to perform spell checker.", new Object[0]);
                return;
            case 5:
                SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = ((SpeechControllerImpl) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).mFullScreenReadNextCallback;
                if (utteranceCompleteRunnable != null) {
                    utteranceCompleteRunnable.run(5);
                    return;
                }
                return;
            case 6:
                String str = AiCoreBaseService.TAG;
                try {
                    this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0.cancel();
                    return;
                } catch (RemoteException e) {
                    Log.w(AiCoreBaseService.TAG, "Failed to cancel preparation of inference engine", e);
                    return;
                }
            case 7:
                String str2 = AiCoreBaseService.TAG;
                try {
                    this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0.cancel();
                    return;
                } catch (RemoteException e2) {
                    Log.w(AiCoreBaseService.TAG, "Failed to cancel inference", e2);
                    return;
                }
            case 8:
                this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0.cancel(false);
                return;
            case 9:
                ((GoogleApiManager.ClientConnection) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).onConnectedInternal();
                return;
            case 10:
                Api$Client api$Client = ((GoogleApiManager.ClientConnection) ((FloatingActionButton.ShadowDelegateImpl) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).FloatingActionButton$ShadowDelegateImpl$ar$this$0).client;
                api$Client.disconnect(String.valueOf(api$Client.getClass().getName()).concat(" disconnecting because it was signed out."));
                return;
            case 11:
                ((SignInCoordinator) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).mCallback$ar$class_merging$71a2fb1_0.onSignInFailed(new ConnectionResult(4));
                return;
            case 12:
                synchronized (((OnCanceledCompletionListener) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).lock) {
                    ((Tasks$AwaitListener) ((OnCanceledCompletionListener) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).OnCanceledCompletionListener$ar$onCanceled$ar$class_merging).onCanceled();
                }
                return;
            case 13:
                ?? r0 = this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                if (r0 != 0) {
                    r0.onScreenCaptureFinished(null, true);
                    return;
                }
                return;
            case 14:
                ?? r02 = this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                if (r02 != 0) {
                    r02.onAuthorizationFinished(true);
                    return;
                }
                return;
            case 15:
                ((MddDebugListFragmentUiProviderImpl) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).swipeRefreshLayout.setRefreshing(false);
                return;
            case 16:
                ProcessLifecycleOwner processLifecycleOwner = (ProcessLifecycleOwner) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                NoPiiString noPiiString = processLifecycleOwner.lastPausedActivity;
                noPiiString.getClass();
                if (processLifecycleOwner.resumedCounter == 0) {
                    processLifecycleOwner.pauseSent = true;
                    Iterator it = processLifecycleOwner.listeners.iterator();
                    while (it.hasNext()) {
                        ((DebouncedForegroundSignalAdapter) it.next()).onActivityPaused(noPiiString);
                    }
                }
                NoPiiString noPiiString2 = processLifecycleOwner.lastPausedActivity;
                noPiiString2.getClass();
                processLifecycleOwner.dispatchStopIfNeeded(noPiiString2);
                return;
            case 17:
                ((CpuProfilingService) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).scheduleNextMonitoringWindow(true);
                return;
            case 18:
                Object obj2 = this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0;
                try {
                    AtomicReference atomicReference = ((TraceMetricServiceImpl) obj2).nonTikTokSampler;
                    AppLifecycleMonitor appLifecycleMonitor = ((TraceMetricServiceImpl) obj2).probabilitySamplerFactory$ar$class_merging$ar$class_merging;
                    if (((TraceConfigurations) ((TraceMetricServiceImpl) obj2).traceConfigurationsProvider.get()).isEnabled()) {
                        f = ((TraceConfigurations) ((TraceMetricServiceImpl) obj2).traceConfigurationsProvider.get()).getSamplingProbability();
                    } else {
                        f = 0.0f;
                    }
                    atomicReference.set(appLifecycleMonitor.create(f));
                    return;
                } catch (Throwable unused) {
                    TraceMetricServiceImpl traceMetricServiceImpl = (TraceMetricServiceImpl) obj2;
                    traceMetricServiceImpl.nonTikTokSampler.set(traceMetricServiceImpl.probabilitySamplerFactory$ar$class_merging$ar$class_merging.create(0.0f));
                    return;
                }
            case 19:
                ((BroadcastReceiver.PendingResult) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).finish();
                return;
            default:
                ((FlagStore) this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0).handleFlagUpdates();
                return;
        }
    }

    public TrainingActivity$$ExternalSyntheticLambda1(Object obj, int i, byte[] bArr) {
        this.switching_field = i;
        this.TrainingActivity$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }
}
