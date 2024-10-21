package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$Event;
import java.util.List;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleSelectView extends LinearLayout {
    private static final ImmutableMap MULTI_CHOICE_SMILEY_ICON_RESOURCE_MAP;
    public static final /* synthetic */ int SingleSelectView$ar$NoOp = 0;
    public OnAnswerSelectClickListener onAnswerSelectClickListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.surveys.internal.view.SingleSelectView$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ View SingleSelectView$1$ar$this$0;
        final /* synthetic */ Object SingleSelectView$1$ar$val$answers;
        private final /* synthetic */ int switching_field;
        final /* synthetic */ int val$idx;

        public AnonymousClass1(MultipleSelectView multipleSelectView, int i, CheckBox checkBox, int i2) {
            this.switching_field = i2;
            this.val$idx = i;
            this.SingleSelectView$1$ar$this$0 = checkBox;
            this.SingleSelectView$1$ar$val$answers = multipleSelectView;
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [java.util.List, java.lang.Object] */
        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.switching_field != 0) {
                String trim = charSequence.toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    Object obj = this.SingleSelectView$1$ar$val$answers;
                    ((MultipleSelectView) obj).responses[this.val$idx] = true;
                    ((CheckBox) this.SingleSelectView$1$ar$this$0).setChecked(true);
                    MultipleSelectView multipleSelectView = (MultipleSelectView) this.SingleSelectView$1$ar$val$answers;
                    multipleSelectView.otherOptionString = trim;
                    multipleSelectView.onAnswerSelectClickListener.onClickAnswerSelect$ar$class_merging$ar$class_merging$ar$class_merging(new RemoteModelManager.RemoteModelManagerRegistration(multipleSelectView.otherOptionString, multipleSelectView.responses));
                    return;
                }
                return;
            }
            String trim2 = charSequence.toString().trim();
            if (!TextUtils.isEmpty(trim2)) {
                ((SingleSelectView) this.SingleSelectView$1$ar$this$0).onAnswerSelectClickListener.onClickAnswerSelect$ar$class_merging$a1759ccb_0(new CronetEngineBuilderImpl.QuicHint(Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu$def17366_0, trim2, ((Survey$AnswerChoice) this.SingleSelectView$1$ar$val$answers.get(this.val$idx)).answerOrdinal_));
            }
        }

        public AnonymousClass1(SingleSelectView singleSelectView, List list, int i, int i2) {
            this.switching_field = i2;
            this.SingleSelectView$1$ar$val$answers = list;
            this.val$idx = i;
            this.SingleSelectView$1$ar$this$0 = singleSelectView;
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnAnswerSelectClickListener {
        void onClickAnswerSelect$ar$class_merging$a1759ccb_0(CronetEngineBuilderImpl.QuicHint quicHint);
    }

    static {
        Integer valueOf = Integer.valueOf(R.drawable.quantum_ic_sentiment_very_satisfied_grey600_36);
        Integer valueOf2 = Integer.valueOf(R.drawable.quantum_ic_sentiment_satisfied_grey600_36);
        Integer valueOf3 = Integer.valueOf(R.drawable.quantum_ic_sentiment_neutral_grey600_36);
        Integer valueOf4 = Integer.valueOf(R.drawable.quantum_ic_sentiment_dissatisfied_grey600_36);
        Integer valueOf5 = Integer.valueOf(R.drawable.quantum_ic_sentiment_very_dissatisfied_grey600_36);
        ContextDataProvider.checkEntryNotNull(0, valueOf);
        ContextDataProvider.checkEntryNotNull(1, valueOf2);
        ContextDataProvider.checkEntryNotNull(2, valueOf3);
        ContextDataProvider.checkEntryNotNull(3, valueOf4);
        ContextDataProvider.checkEntryNotNull(4, valueOf5);
        MULTI_CHOICE_SMILEY_ICON_RESOURCE_MAP = RegularImmutableMap.create(5, new Object[]{0, valueOf, 1, valueOf2, 2, valueOf3, 3, valueOf4, 4, valueOf5});
    }

    public SingleSelectView(Context context) {
        super(context);
        setOrientation(1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
    
        if (r0 == com.google.scone.proto.Survey$SingleSelect.IconType.ICON_TYPE_SMILEYS_DISSATISFIED_FIRST$ar$edu) goto L18;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setUpSingleSelectView(com.google.scone.proto.Survey$SingleSelect r18) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.view.SingleSelectView.setUpSingleSelectView(com.google.scone.proto.Survey$SingleSelect):void");
    }
}
