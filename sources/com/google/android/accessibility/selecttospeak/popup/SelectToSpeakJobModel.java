package com.google.android.accessibility.selecttospeak.popup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.TriggerBasedInvalidationTracker$onAllowRefresh$1;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakJobModel extends ViewModel {
    public OptionalMethod analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
    public SelectToSpeakJob job;
    public SpeechControllerImpl speechController;
    public final SelectToSpeakJob.JobUpdateListener jobUpdateListener = new SelectToSpeakJobModel$jobUpdateListener$1(this, 0);
    public final SelectToSpeakJobModel$speechControllerDelegate$1 speechControllerDelegate = new SpeechController.Delegate() { // from class: com.google.android.accessibility.selecttospeak.popup.SelectToSpeakJobModel$speechControllerDelegate$1
        @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
        public final boolean isAudioPlaybackActive() {
            return false;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
        public final boolean isMicrophoneActiveAndHeadphoneOff() {
            return false;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
        public final boolean isPhoneCallActive() {
            return false;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
        public final boolean isSsbActiveAndHeadphoneOff() {
            return false;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
        public final void onSpeakingForcedFeedback() {
        }

        @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
        public final /* synthetic */ void onTtsReady() {
        }
    };
    private final Lazy onUserInitiatedFinished$delegate = new SynchronizedLazyImpl(TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$23822fe0_0);
    private final Lazy onProgramInitiatedFinished$delegate = new SynchronizedLazyImpl(TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$c1c37390_0);
    private final Lazy isPaused$delegate = new SynchronizedLazyImpl(TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$66e53fc8_0);
    private final AtomicBoolean shutDown = new AtomicBoolean(false);

    public final boolean canIncreaseSpeechRate() {
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob != null) {
            return selectToSpeakJob.canIncreaseSpeechRate();
        }
        return false;
    }

    public final boolean canReduceSpeechRate() {
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob != null) {
            return selectToSpeakJob.canReduceSpeechRate();
        }
        return false;
    }

    public final void finish(boolean z) {
        LogUtils.w("SelectToSpeakJobModel", "User initiated finish. " + z, new Object[0]);
        if (!this.shutDown.getAndSet(true)) {
            SelectToSpeakJob selectToSpeakJob = this.job;
            if (selectToSpeakJob != null) {
                selectToSpeakJob.stop();
            }
            SpeechControllerImpl speechControllerImpl = this.speechController;
            SpeechControllerImpl speechControllerImpl2 = null;
            if (speechControllerImpl == null) {
                Intrinsics.throwUninitializedPropertyAccessException("speechController");
                speechControllerImpl = null;
            }
            if (speechControllerImpl.mIsSpeaking) {
                SpeechControllerImpl speechControllerImpl3 = this.speechController;
                if (speechControllerImpl3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("speechController");
                    speechControllerImpl3 = null;
                }
                speechControllerImpl3.interrupt(true);
            }
            SpeechControllerImpl speechControllerImpl4 = this.speechController;
            if (speechControllerImpl4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("speechController");
            } else {
                speechControllerImpl2 = speechControllerImpl4;
            }
            speechControllerImpl2.shutdown();
        }
        if (z) {
            OptionalMethod optionalMethod = this.analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
            if (optionalMethod != null) {
                optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_STOP_ACTION$ar$edu);
            }
            MutableLiveData onUserInitiatedFinished = getOnUserInitiatedFinished();
            Integer num = (Integer) getOnUserInitiatedFinished().getValue();
            if (num == null) {
                num = 0;
            }
            onUserInitiatedFinished.setValue(Integer.valueOf(num.intValue() + 1));
            return;
        }
        getOnProgramInitiatedFinished().setValue(true);
    }

    public final MutableLiveData getOnProgramInitiatedFinished() {
        return (MutableLiveData) this.onProgramInitiatedFinished$delegate.getValue();
    }

    public final MutableLiveData getOnUserInitiatedFinished() {
        return (MutableLiveData) this.onUserInitiatedFinished$delegate.getValue();
    }

    public final MutableLiveData isPaused() {
        return (MutableLiveData) this.isPaused$delegate.getValue();
    }

    public final void pause() {
        LogUtils.v("SelectToSpeakJobModel", "pause", new Object[0]);
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob != null) {
            selectToSpeakJob.pause();
        }
    }

    public final void resume() {
        LogUtils.v("SelectToSpeakJobModel", "resume", new Object[0]);
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob != null) {
            selectToSpeakJob.resume();
        }
    }

    public final int speechIndex() {
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob != null) {
            return selectToSpeakJob.speechRateController.speechRateIndex;
        }
        return 0;
    }
}
