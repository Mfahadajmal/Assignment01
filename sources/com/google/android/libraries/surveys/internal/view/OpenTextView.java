package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.marvin.talkback.R;
import com.google.scone.proto.Survey$OpenText;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OpenTextView extends LinearLayout {
    public OnOpenTextResponseListener onOpenTextResponseListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnOpenTextResponseListener {
        void onOpenTextResponse(String str);
    }

    public OpenTextView(Context context) {
        super(context);
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.survey_question_open_text_item, (ViewGroup) this, true);
    }

    public final void setUpOpenTextView(Survey$OpenText survey$OpenText) {
        EditText editText = (EditText) findViewById(R.id.survey_open_text);
        DefaultExperimentTokenDecorator.appendEditTextHintWithHelperTextView(editText, (TextView) findViewById(R.id.survey_open_text_personal_info));
        editText.setSingleLine(false);
        if (!survey$OpenText.placeholder_.isEmpty()) {
            editText.setHint(survey$OpenText.placeholder_);
        }
        if (!SurveyUtils.isInTalkBackMode(getContext())) {
            editText.requestFocus();
        }
        editText.addTextChangedListener(new AnonymousClass1(this, 0));
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.surveys.internal.view.OpenTextView$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ Object OpenTextView$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.OpenTextView$1$ar$this$0 = obj;
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            if (this.switching_field != 0) {
                ((BaseDialog) this.OpenTextView$1$ar$this$0).setButtonEnabled(-1, !TextUtils.isEmpty(editable));
            }
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.switching_field != 0) {
                return;
            }
            ((OpenTextView) this.OpenTextView$1$ar$this$0).onOpenTextResponseListener.onOpenTextResponse(charSequence.toString().trim());
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
