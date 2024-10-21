package com.google.android.accessibility.selecttospeak.tts;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.fragment.FragmentNavigator;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2 extends Lambda implements Function1 {
    final /* synthetic */ Object ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$speechController;
    final /* synthetic */ Object ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$voicesDialog;
    final /* synthetic */ Object ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$waitDialog;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2(AlertDialog alertDialog, Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2, int i) {
        super(1);
        this.switching_field = i;
        this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$waitDialog = alertDialog;
        this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$voicesDialog = ref$ObjectRef;
        this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$speechController = ref$ObjectRef2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        int i = this.switching_field;
        boolean z = true;
        if (i != 0) {
            if (i == 1) {
                List list = ((FragmentNavigator) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$voicesDialog).pendingOps;
                LifecycleOwner lifecycleOwner = (LifecycleOwner) obj;
                if (!list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (Intrinsics.areEqual(((Pair) it.next()).first, ((Fragment) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$waitDialog).getTag())) {
                            break;
                        }
                    }
                }
                z = false;
                if (lifecycleOwner != null && !z) {
                    Lifecycle lifecycle = ((Fragment) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$waitDialog).getViewLifecycleOwner().getLifecycle();
                    if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                        lifecycle.addObserver((LifecycleObserver) ((FragmentNavigator) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$voicesDialog).fragmentViewObserver.invoke(this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$speechController));
                    }
                }
                return Unit.INSTANCE;
            }
            throw null;
        }
        ((AlertDialog) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$waitDialog).hide();
        AlertDialog alertDialog = (AlertDialog) ((Ref$ObjectRef) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$voicesDialog).element;
        if (alertDialog != null) {
            alertDialog.hide();
        }
        SpeechControllerImpl speechControllerImpl = (SpeechControllerImpl) ((Ref$ObjectRef) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$speechController).element;
        if (speechControllerImpl.mIsSpeaking) {
            speechControllerImpl.interrupt(true);
        }
        ((SpeechControllerImpl) ((Ref$ObjectRef) this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$speechController).element).shutdown();
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2(Object obj, Object obj2, Object obj3, int i) {
        super(1);
        this.switching_field = i;
        this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$voicesDialog = obj;
        this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$waitDialog = obj2;
        this.ChangeVoiceSettingsDialogUtil$buildAndShowChangeVoiceDialog$1$1$1$2$ar$$speechController = obj3;
    }
}
