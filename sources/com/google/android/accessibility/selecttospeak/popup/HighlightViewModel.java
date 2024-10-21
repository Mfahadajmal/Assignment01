package com.google.android.accessibility.selecttospeak.popup;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.room.TriggerBasedInvalidationTracker$onAllowRefresh$1;
import com.google.android.accessibility.selecttospeak.iterator.InSentenceOffset;
import com.google.android.accessibility.selecttospeak.iterator.Sentence;
import com.google.android.accessibility.selecttospeak.ui.HighlightBoard;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HighlightViewModel extends ViewModel {
    public final int highlightColor;
    public SpannableString spannable = new SpannableString("");
    private final Lazy invalidate$delegate = new SynchronizedLazyImpl(TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$b028af8d_0);
    private final Lazy highlightedText$delegate = new SynchronizedLazyImpl(TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$c6fcde7f_0);
    private final Lazy highlightedTextIndex$delegate = new SynchronizedLazyImpl(TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$369147ba_0);
    public final HighlightBoard board = new HighlightBoard() { // from class: com.google.android.accessibility.selecttospeak.popup.HighlightViewModel$board$1
        @Override // com.google.android.accessibility.selecttospeak.ui.HighlightBoard
        public final void clear$ar$ds() {
            HighlightViewModel highlightViewModel = HighlightViewModel.this;
            SpannableString spannableString = highlightViewModel.spannable;
            ForegroundColorSpan[] foregroundColorSpanArr = (ForegroundColorSpan[]) spannableString.getSpans(0, spannableString.length(), ForegroundColorSpan.class);
            if (foregroundColorSpanArr == null) {
                foregroundColorSpanArr = new ForegroundColorSpan[0];
            }
            for (ForegroundColorSpan foregroundColorSpan : foregroundColorSpanArr) {
                highlightViewModel.spannable.removeSpan(foregroundColorSpan);
            }
            highlightViewModel.getHighlightedText().setValue(new SpannableString(highlightViewModel.spannable));
            HighlightViewModel.this.getInvalidate().setValue(true);
        }

        @Override // com.google.android.accessibility.selecttospeak.ui.HighlightBoard
        public final void highlight(boolean z, Sentence sentence, InSentenceOffset inSentenceOffset) {
            int i = sentence.offsetFromParagraph + inSentenceOffset.end;
            HighlightViewModel highlightViewModel = HighlightViewModel.this;
            highlightViewModel.spannable.setSpan(new ForegroundColorSpan(highlightViewModel.highlightColor), 0, i, 17);
            highlightViewModel.getHighlightedText().setValue(new SpannableString(highlightViewModel.spannable));
            highlightViewModel.getHighlightedTextIndex().setValue(Integer.valueOf(i));
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HighlightViewModelFactory implements ViewModelProvider.Factory {
        private final int highlightColor;

        public HighlightViewModelFactory(int i) {
            this.highlightColor = i;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final /* synthetic */ ViewModel create(Class cls, CreationExtras creationExtras) {
            ViewModel create;
            create = create(cls);
            return create;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final /* synthetic */ ViewModel create(KClass kClass, CreationExtras creationExtras) {
            ViewModel create;
            create = create(OnDeviceShadowRemovalLogEvent.getJavaClass(kClass), creationExtras);
            return create;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls) {
            if (cls.isAssignableFrom(HighlightViewModel.class)) {
                return new HighlightViewModel(this.highlightColor);
            }
            throw new IllegalArgumentException("Unknown View Model Class");
        }
    }

    public HighlightViewModel(int i) {
        this.highlightColor = i;
    }

    public final MutableLiveData getHighlightedText() {
        return (MutableLiveData) this.highlightedText$delegate.getValue();
    }

    public final MutableLiveData getHighlightedTextIndex() {
        return (MutableLiveData) this.highlightedTextIndex$delegate.getValue();
    }

    public final MutableLiveData getInvalidate() {
        return (MutableLiveData) this.invalidate$delegate.getValue();
    }
}
