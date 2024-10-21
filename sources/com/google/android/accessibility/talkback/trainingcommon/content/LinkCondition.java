package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.view.View;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.utils.Consumer;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LinkCondition extends ClickableChip {
    public final PageConfig.PageAndContentPredicate condition;
    public final Consumer conditionFailedConsumer;

    public LinkCondition(PageConfig.PageAndContentPredicate pageAndContentPredicate, Consumer consumer, int[] iArr) {
        super(R.string.practice_gestures_link_text, R.string.practice_gestures_link_subtext, R.drawable.ic_gesture_googblue_24dp, iArr);
        this.condition = pageAndContentPredicate;
        this.conditionFailedConsumer = consumer;
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.ClickableChip
    protected final View.OnClickListener createOnClickListener(Context context, TrainingIpcClient.ServiceData serviceData) {
        return new LinkCondition$$ExternalSyntheticLambda0(this, serviceData, context, new TrainingActivity$$ExternalSyntheticLambda0(this, 3), 0);
    }
}
