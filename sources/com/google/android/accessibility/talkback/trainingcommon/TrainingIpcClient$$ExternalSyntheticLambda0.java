package com.google.android.accessibility.talkback.trainingcommon;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.UserInterface$UserInputEventListener;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TrainingIpcClient$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ Object TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TrainingIpcClient$$ExternalSyntheticLambda0(AccessibilityEvent accessibilityEvent, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, int i) {
        this.switching_field = i;
        this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$1 = accessibilityEvent;
        this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$0 = spannableUtils$NonCopyableTextSpan;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        if (this.switching_field != 0) {
            ((UserInterface$UserInputEventListener) obj).newItemFocused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(((AccessibilityEvent) this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$1).getSource(), (SpannableUtils$NonCopyableTextSpan) this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$0);
            return;
        }
        String str = (String) obj;
        boolean equals = TextUtils.equals(str, "is_any_gesture_changed");
        Object obj2 = this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$1;
        Object obj3 = this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$0;
        if (equals) {
            ((TrainingIpcClient) obj3).serviceData.isAnyGestureChanged = ((Bundle) obj2).getBoolean("is_any_gesture_changed", true);
        } else {
            String string = ((Bundle) obj2).getString(str);
            if (string == null) {
                return;
            }
            ((TrainingIpcClient) obj3).serviceData.actionKeyToGestureText.put(str, string);
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        if (this.switching_field != 0) {
            return Consumer$CC.$default$andThen(this, consumer);
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public /* synthetic */ TrainingIpcClient$$ExternalSyntheticLambda0(TrainingIpcClient trainingIpcClient, Bundle bundle, int i) {
        this.switching_field = i;
        this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$0 = trainingIpcClient;
        this.TrainingIpcClient$$ExternalSyntheticLambda0$ar$f$1 = bundle;
    }
}
