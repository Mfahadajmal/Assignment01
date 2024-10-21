package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.accessibility.selecttospeak.tts.ChangeVoiceSettingsDialogUtil;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.utils.output.SpeechController;
import java.util.List;
import kotlin.jvm.internal.Ref$IntRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class LinkCondition$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ Object LinkCondition$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object LinkCondition$$ExternalSyntheticLambda0$ar$f$1;
    public final /* synthetic */ Object LinkCondition$$ExternalSyntheticLambda0$ar$f$2;
    public final /* synthetic */ Object LinkCondition$$ExternalSyntheticLambda0$ar$f$3;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ LinkCondition$$ExternalSyntheticLambda0(LinkCondition linkCondition, TrainingIpcClient.ServiceData serviceData, Context context, View.OnClickListener onClickListener, int i) {
        this.switching_field = i;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$0 = linkCondition;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$1 = serviceData;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$2 = context;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$3 = onClickListener;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [android.view.View$OnClickListener, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, com.google.android.accessibility.utils.output.SpeechController] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List, java.lang.Object] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.switching_field != 0) {
            Object obj = this.LinkCondition$$ExternalSyntheticLambda0$ar$f$2;
            ?? r0 = this.LinkCondition$$ExternalSyntheticLambda0$ar$f$3;
            ChangeVoiceSettingsDialogUtil.createVoicesDialog$lambda$9$lambda$8$lambda$7$ar$ds((Ref$IntRef) this.LinkCondition$$ExternalSyntheticLambda0$ar$f$0, this.LinkCondition$$ExternalSyntheticLambda0$ar$f$1, r0, (Bundle) obj);
            return;
        }
        Object obj2 = this.LinkCondition$$ExternalSyntheticLambda0$ar$f$1;
        LinkCondition linkCondition = (LinkCondition) this.LinkCondition$$ExternalSyntheticLambda0$ar$f$0;
        if (!linkCondition.condition.test((TrainingIpcClient.ServiceData) obj2)) {
            linkCondition.conditionFailedConsumer.accept(this.LinkCondition$$ExternalSyntheticLambda0$ar$f$2);
        } else {
            this.LinkCondition$$ExternalSyntheticLambda0$ar$f$3.onClick(view);
        }
    }

    public /* synthetic */ LinkCondition$$ExternalSyntheticLambda0(Ref$IntRef ref$IntRef, List list, SpeechController speechController, Bundle bundle, int i) {
        this.switching_field = i;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$0 = ref$IntRef;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$1 = list;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$3 = speechController;
        this.LinkCondition$$ExternalSyntheticLambda0$ar$f$2 = bundle;
    }
}
