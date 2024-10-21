package com.google.android.accessibility.selecttospeak;

import android.content.Context;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.surveys.SurveyData;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SelectToSpeakService$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ Object SelectToSpeakService$$ExternalSyntheticLambda6$ar$f$0;
    public final /* synthetic */ Context f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SelectToSpeakService$$ExternalSyntheticLambda6(SelectToSpeakService selectToSpeakService, Context context, int i) {
        this.switching_field = i;
        this.SelectToSpeakService$$ExternalSyntheticLambda6$ar$f$0 = selectToSpeakService;
        this.f$1 = context;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.function.Consumer, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        if (this.switching_field != 0) {
            SentenceIterator sentenceIterator = (SentenceIterator) obj;
            ?? r0 = this.SelectToSpeakService$$ExternalSyntheticLambda6$ar$f$0;
            if (sentenceIterator != null && sentenceIterator.hasNext()) {
                ((SelectToSpeakService) this.f$1).startJob(sentenceIterator);
                r0.accept(true);
            } else {
                r0.accept(false);
            }
            return Unit.INSTANCE;
        }
        SurveyData surveyData = (SurveyData) obj;
        if (surveyData != null && !((SelectToSpeakService) this.SelectToSpeakService$$ExternalSyntheticLambda6$ar$f$0).serviceState.isZombieState) {
            SpannableUtils$NonCopyableTextSpan.launchActivity$ar$ds(this.f$1, surveyData);
            return null;
        }
        return null;
    }

    public /* synthetic */ SelectToSpeakService$$ExternalSyntheticLambda6(SelectToSpeakService selectToSpeakService, Consumer consumer, int i) {
        this.switching_field = i;
        this.f$1 = selectToSpeakService;
        this.SelectToSpeakService$$ExternalSyntheticLambda6$ar$f$0 = consumer;
    }
}
