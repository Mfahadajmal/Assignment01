package com.google.android.accessibility.selecttospeak.popup;

import androidx.lifecycle.MutableLiveData;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import io.grpc.okhttp.internal.OptionalMethod;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakPopupActivity$setupViews$18 extends Lambda implements Function0 {
    final /* synthetic */ Object SelectToSpeakPopupActivity$setupViews$18$ar$this$0;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectToSpeakPopupActivity$setupViews$18(Object obj, int i) {
        super(0);
        this.switching_field = i;
        this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0 = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        SelectToSpeakJob selectToSpeakJob;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return new MutableLiveData(Float.valueOf(((TextSizeModel) this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0).currentPx()));
                            }
                            SelectToSpeakJobModel jobModel = ((SelectToSpeakPopupActivity) this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0).getJobModel();
                            LogUtils.d("SelectToSpeakJobModel", "increase speech rate. currentIndex : " + jobModel.speechIndex(), new Object[0]);
                            OptionalMethod optionalMethod = jobModel.analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
                            if (optionalMethod != null) {
                                optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_INCREASE_SPEED_ACTION$ar$edu);
                            }
                            if (jobModel.canIncreaseSpeechRate() && (selectToSpeakJob = jobModel.job) != null) {
                                selectToSpeakJob.increaseSpeechRate();
                            }
                            return Unit.INSTANCE;
                        }
                        return Boolean.valueOf(((SelectToSpeakPopupActivity) this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0).getJobModel().canReduceSpeechRate());
                    }
                    return Boolean.valueOf(((SelectToSpeakPopupActivity) this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0).getJobModel().canIncreaseSpeechRate());
                }
                return Integer.valueOf(((SelectToSpeakPopupActivity) this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0).getTextSizeModel().currentIndex);
            }
            TextSizeModel textSizeModel = ((SelectToSpeakPopupActivity) this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0).getTextSizeModel();
            OptionalMethod optionalMethod2 = textSizeModel.analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
            if (optionalMethod2 != null) {
                optionalMethod2.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_INCREASE_TEXT_SIZE_ACTION$ar$edu);
            }
            if (textSizeModel.canIncreaseTextSize()) {
                textSizeModel.currentIndex++;
                textSizeModel.getTextSizePx().setValue(Float.valueOf(textSizeModel.currentPx()));
            }
            return Unit.INSTANCE;
        }
        TextSizeModel textSizeModel2 = ((SelectToSpeakPopupActivity) this.SelectToSpeakPopupActivity$setupViews$18$ar$this$0).getTextSizeModel();
        OptionalMethod optionalMethod3 = textSizeModel2.analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
        if (optionalMethod3 != null) {
            optionalMethod3.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_DECREASE_TEXT_SIZE_ACTION$ar$edu);
        }
        if (textSizeModel2.canReduceTextSize()) {
            textSizeModel2.currentIndex--;
            textSizeModel2.getTextSizePx().setValue(Float.valueOf(textSizeModel2.currentPx()));
        }
        return Unit.INSTANCE;
    }
}
