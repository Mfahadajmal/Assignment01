package com.google.android.accessibility.selecttospeak.overlayui;

import android.app.Activity;
import com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder;
import com.google.android.accessibility.selecttospeak.tts.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.internal.NopCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UIDelayedJobScheduler$scheduleAutoCollapse$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UIDelayedJobScheduler$scheduleAutoCollapse$1(Activity activity, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0 = activity;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [kotlinx.coroutines.flow.Flow, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return new UIDelayedJobScheduler$scheduleAutoCollapse$1((Flow) this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0, continuation, 3);
                }
                return new UIDelayedJobScheduler$scheduleAutoCollapse$1((Activity) this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0, continuation, 2);
            }
            return new UIDelayedJobScheduler$scheduleAutoCollapse$1((ImprovedSelectionFinder) this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0, continuation, 1);
        }
        return new UIDelayedJobScheduler$scheduleAutoCollapse$1((UIDelayedJobScheduler) this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0, continuation, 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return ((UIDelayedJobScheduler$scheduleAutoCollapse$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }
                return ((UIDelayedJobScheduler$scheduleAutoCollapse$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((UIDelayedJobScheduler$scheduleAutoCollapse$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((UIDelayedJobScheduler$scheduleAutoCollapse$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r5v14, types: [kotlinx.coroutines.flow.Flow, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    } else {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                        ?? r5 = this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0;
                        this.label = 1;
                        Object collect = r5.collect(NopCollector.INSTANCE, this);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            collect = Unit.INSTANCE;
                        }
                        if (collect == obj2) {
                            return obj2;
                        }
                    }
                    return Unit.INSTANCE;
                }
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                } else {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1 changeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1 = new ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1((Activity) this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0, null);
                    this.label = 1;
                    if (ScannerAutoZoomEvent.setupTimeout(new TimeoutCoroutine(2000L, this), changeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
                return Unit.INSTANCE;
            }
            CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                Job job = ((ImprovedSelectionFinder) this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0).screenshotJob;
                if (job == null) {
                    return null;
                }
                this.label = 1;
                if (job.join(this) == coroutineSingletons2) {
                    return coroutineSingletons2;
                }
            }
            return Unit.INSTANCE;
        }
        CoroutineSingletons coroutineSingletons3 = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            Object obj3 = this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0;
            this.label = 1;
            if (((UIDelayedJobScheduler) obj3).collapse(10000L, this) == coroutineSingletons3) {
                return coroutineSingletons3;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UIDelayedJobScheduler$scheduleAutoCollapse$1(ImprovedSelectionFinder improvedSelectionFinder, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0 = improvedSelectionFinder;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UIDelayedJobScheduler$scheduleAutoCollapse$1(UIDelayedJobScheduler uIDelayedJobScheduler, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0 = uIDelayedJobScheduler;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UIDelayedJobScheduler$scheduleAutoCollapse$1(Flow flow, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.UIDelayedJobScheduler$scheduleAutoCollapse$1$ar$this$0 = flow;
    }
}
