package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.surveys.internal.utils.FlagsUtil;
import com.google.android.libraries.surveys.internal.view.SingleSelectView;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.protobuf.Internal;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$MultiSelect;
import googledata.experiments.mobile.surveys_android.features.HatsV1M14Bugfixes;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultipleSelectView extends LinearLayout {
    public OnAnswerSelectClickListener onAnswerSelectClickListener;
    public String otherOptionString;
    public boolean[] responses;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CheckboxChangeListener implements CompoundButton.OnCheckedChangeListener {
        private final int index;

        public CheckboxChangeListener(int i) {
            this.index = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CheckBox checkBox;
            int i = 0;
            if ("NoneOfTheAbove".equals(compoundButton.getTag())) {
                MultipleSelectView multipleSelectView = MultipleSelectView.this;
                multipleSelectView.responses[this.index] = z;
                if (z) {
                    if (multipleSelectView.getChildCount() != MultipleSelectView.this.responses.length + 1) {
                        Log.e("SurveyMultipleSelectView", "Number of children (checkboxes) contained in the answers container was not equal to the number of possible responses including \"None of the Above\". Note this is not expected to happen in prod.");
                    }
                    for (int i2 = 0; i2 < MultipleSelectView.this.getChildCount(); i2++) {
                        CheckBox checkBox2 = (CheckBox) MultipleSelectView.this.getChildAt(i2).findViewById(R.id.survey_multiple_select_checkbox);
                        if (!"NoneOfTheAbove".equals(checkBox2.getTag())) {
                            checkBox2.setChecked(false);
                        }
                    }
                }
            } else if ("OtherPleaseSpecify".equals(compoundButton.getTag())) {
                MultipleSelectView.this.responses[this.index] = z;
                if (z) {
                    while (true) {
                        if (i >= MultipleSelectView.this.getChildCount()) {
                            break;
                        }
                        EditText editText = (EditText) MultipleSelectView.this.getChildAt(i).findViewById(R.id.survey_other_option);
                        if (editText != null) {
                            editText.requestFocus();
                            break;
                        }
                        i++;
                    }
                }
            } else {
                MultipleSelectView multipleSelectView2 = MultipleSelectView.this;
                multipleSelectView2.responses[this.index] = z;
                if (z && (checkBox = (CheckBox) multipleSelectView2.findViewWithTag("NoneOfTheAbove")) != null) {
                    checkBox.setChecked(false);
                }
            }
            MultipleSelectView multipleSelectView3 = MultipleSelectView.this;
            multipleSelectView3.onAnswerSelectClickListener.onClickAnswerSelect$ar$class_merging$ar$class_merging$ar$class_merging(new RemoteModelManager.RemoteModelManagerRegistration(multipleSelectView3.otherOptionString, multipleSelectView3.responses));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnAnswerSelectClickListener {
        void onClickAnswerSelect$ar$class_merging$ar$class_merging$ar$class_merging(RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration);
    }

    public MultipleSelectView(Context context) {
        super(context);
        setOrientation(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setUpMultipleSelectView(Survey$MultiSelect survey$MultiSelect, boolean[] zArr) {
        String str;
        if (zArr == null) {
            Survey$AnswerChoices survey$AnswerChoices = survey$MultiSelect.answerChoices_;
            if (survey$AnswerChoices == null) {
                survey$AnswerChoices = Survey$AnswerChoices.DEFAULT_INSTANCE;
            }
            this.responses = new boolean[survey$AnswerChoices.answerChoice_.size()];
        } else {
            this.responses = zArr;
        }
        Survey$AnswerChoices survey$AnswerChoices2 = survey$MultiSelect.answerChoices_;
        if (survey$AnswerChoices2 == null) {
            survey$AnswerChoices2 = Survey$AnswerChoices.DEFAULT_INSTANCE;
        }
        Internal.ProtobufList protobufList = survey$AnswerChoices2.answerChoice_;
        for (int i = 0; i < protobufList.size(); i++) {
            int forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.forNumber$ar$edu$ac62307f_0(((Survey$AnswerChoice) protobufList.get(i)).answerType_);
            if (forNumber$ar$edu$ac62307f_0 == 0) {
                forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.UNRECOGNIZED$ar$edu$57c31185_0;
            }
            int i2 = 1;
            if (forNumber$ar$edu$ac62307f_0 == Survey$AnswerChoice.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu) {
                LayoutInflater.from(getContext()).inflate(R.layout.survey_question_multiple_select_other_option, (ViewGroup) this, true);
                LinearLayout linearLayout = (LinearLayout) getChildAt(i);
                CheckBox checkBox = (CheckBox) linearLayout.findViewById(R.id.survey_multiple_select_checkbox);
                checkBox.setContentDescription(getResources().getString(R.string.survey_other_option_hint));
                checkBox.setChecked(this.responses[i]);
                checkBox.setOnCheckedChangeListener(new CheckboxChangeListener(i));
                checkBox.setTag("OtherPleaseSpecify");
                EditText editText = (EditText) linearLayout.findViewById(R.id.survey_other_option);
                DefaultExperimentTokenDecorator.appendEditTextHintWithHelperTextView(editText, (TextView) linearLayout.findViewById(R.id.tv_survey_other_option_pii_info));
                View findViewById = linearLayout.findViewById(R.id.survey_other_option_background);
                findViewById.setOnTouchListener(new RatingView$$ExternalSyntheticLambda6(checkBox, findViewById, editText, i2));
                editText.addTextChangedListener(new SingleSelectView.AnonymousClass1(this, i, checkBox, 1));
                editText.setOnFocusChangeListener(new SingleSelectView$$ExternalSyntheticLambda2(this, i, checkBox, editText, 1));
                CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (HatsV1M14Bugfixes.INSTANCE.get().fixOtherOptionsAutoSelectedBug(getContext())) {
                    editText.setFocusable(false);
                    editText.postDelayed(new FlagStore$$ExternalSyntheticLambda3(editText, 6), 500L);
                }
            } else {
                boolean z = this.responses[i];
                int forNumber$ar$edu$ac62307f_02 = Survey$AnswerChoice.AnswerType.forNumber$ar$edu$ac62307f_0(((Survey$AnswerChoice) protobufList.get(i)).answerType_);
                if (forNumber$ar$edu$ac62307f_02 == 0) {
                    forNumber$ar$edu$ac62307f_02 = Survey$AnswerChoice.AnswerType.UNRECOGNIZED$ar$edu$57c31185_0;
                }
                if (forNumber$ar$edu$ac62307f_02 == Survey$AnswerChoice.AnswerType.ANSWER_TYPE_NONE_OF_THE_ABOVE$ar$edu) {
                    str = "NoneOfTheAbove";
                } else {
                    str = null;
                }
                String str2 = ((Survey$AnswerChoice) protobufList.get(i)).text_;
                LayoutInflater.from(getContext()).inflate(R.layout.survey_question_multiple_select_item, (ViewGroup) this, true);
                FrameLayout frameLayout = (FrameLayout) getChildAt(i);
                CheckBox checkBox2 = (CheckBox) frameLayout.findViewById(R.id.survey_multiple_select_checkbox);
                checkBox2.setText(str2);
                checkBox2.setContentDescription(str2);
                checkBox2.setChecked(z);
                checkBox2.setOnCheckedChangeListener(new CheckboxChangeListener(i));
                frameLayout.setOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(checkBox2, 4));
                if (str != null) {
                    checkBox2.setTag(str);
                }
            }
        }
    }
}
