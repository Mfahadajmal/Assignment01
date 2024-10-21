package com.google.android.accessibility.selecttospeak.tts;

import android.app.Activity;
import android.app.AlertDialog;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Activity $activity;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1(Activity activity, Continuation continuation) {
        super(2, continuation);
        this.$activity = activity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1(this.$activity, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            final Activity activity = this.$activity;
            this.L$0 = activity;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(R.string.s2s_loading_voices);
            builder.setCancelable(true);
            builder.setOnCancelListener(new ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda1(0));
            final AlertDialog create = builder.create();
            create.getClass();
            create.show();
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            ref$ObjectRef2.element = new SpeechControllerImpl(activity, new SpeechController.Delegate() { // from class: com.google.android.accessibility.selecttospeak.tts.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$1
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
                public final void onTtsReady() {
                    CancellableContinuationImpl.this.resume(Unit.INSTANCE, null);
                    create.hide();
                    SpeechControllerImpl speechControllerImpl = (SpeechControllerImpl) ref$ObjectRef2.element;
                    if (speechControllerImpl != null) {
                        Ref$ObjectRef ref$ObjectRef3 = ref$ObjectRef;
                        AlertDialog createVoicesDialog = ChangeVoiceSettingsDialogUtil.INSTANCE.createVoicesDialog(activity, speechControllerImpl);
                        createVoicesDialog.show();
                        ref$ObjectRef3.element = createVoicesDialog;
                    }
                }

                @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
                public final void onSpeakingForcedFeedback() {
                }
            });
            cancellableContinuationImpl.invokeOnCancellation(new ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2(create, ref$ObjectRef, ref$ObjectRef2, 0));
            if (cancellableContinuationImpl.getResult() == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }
}
