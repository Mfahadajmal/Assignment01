package com.google.android.libraries.surveys.internal.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat$Api23Impl;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$Event;
import java.util.List;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SingleSelectView$$ExternalSyntheticLambda2 implements View.OnFocusChangeListener {
    public final /* synthetic */ View SingleSelectView$$ExternalSyntheticLambda2$ar$f$0;
    public final /* synthetic */ TextView SingleSelectView$$ExternalSyntheticLambda2$ar$f$1;
    public final /* synthetic */ Object SingleSelectView$$ExternalSyntheticLambda2$ar$f$2;
    public final /* synthetic */ int f$3;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SingleSelectView$$ExternalSyntheticLambda2(MultipleSelectView multipleSelectView, int i, CheckBox checkBox, EditText editText, int i2) {
        this.switching_field = i2;
        this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$2 = multipleSelectView;
        this.f$3 = i;
        this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$1 = checkBox;
        this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$0 = editText;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List, java.lang.Object] */
    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        if (this.switching_field != 0) {
            View view2 = this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$0;
            Object obj = this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$2;
            if (z) {
                TextView textView = this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$1;
                MultipleSelectView multipleSelectView = (MultipleSelectView) obj;
                multipleSelectView.responses[this.f$3] = true;
                ((CheckBox) textView).setChecked(true);
                ((EditText) view2).setHintTextColor(ContextCompat$Api23Impl.getColor(multipleSelectView.getContext(), R.color.survey_hint_text_color));
                multipleSelectView.onAnswerSelectClickListener.onClickAnswerSelect$ar$class_merging$ar$class_merging$ar$class_merging(new RemoteModelManager.RemoteModelManagerRegistration(multipleSelectView.otherOptionString, multipleSelectView.responses));
                return;
            }
            ((EditText) view2).setHintTextColor(ContextCompat$Api23Impl.getColor(((MultipleSelectView) obj).getContext(), R.color.survey_hint_text_unfocused_color));
            return;
        }
        TextView textView2 = this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$1;
        View view3 = this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$0;
        if (z) {
            int i = this.f$3;
            ?? r1 = this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$2;
            SingleSelectView singleSelectView = (SingleSelectView) view3;
            ((EditText) textView2).setHintTextColor(ContextCompat$Api23Impl.getColor(singleSelectView.getContext(), R.color.survey_hint_text_color));
            singleSelectView.onAnswerSelectClickListener.onClickAnswerSelect$ar$class_merging$a1759ccb_0(new CronetEngineBuilderImpl.QuicHint(Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu$def17366_0, "", ((Survey$AnswerChoice) r1.get(i)).answerOrdinal_));
            return;
        }
        ((EditText) textView2).setHintTextColor(ContextCompat$Api23Impl.getColor(((SingleSelectView) view3).getContext(), R.color.survey_hint_text_unfocused_color));
    }

    public /* synthetic */ SingleSelectView$$ExternalSyntheticLambda2(SingleSelectView singleSelectView, EditText editText, List list, int i, int i2) {
        this.switching_field = i2;
        this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$0 = singleSelectView;
        this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$1 = editText;
        this.SingleSelectView$$ExternalSyntheticLambda2$ar$f$2 = list;
        this.f$3 = i;
    }
}
