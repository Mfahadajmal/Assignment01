package com.google.android.accessibility.selecttospeak.popup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import io.grpc.okhttp.internal.OptionalMethod;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextSizeModel extends ViewModel {
    public OptionalMethod analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
    public int currentIndex;
    private final float defaultTextSizePx;
    private final Lazy textSizePx$delegate = new SynchronizedLazyImpl(new SelectToSpeakPopupActivity$setupViews$18(this, 6));

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TextViewModelFactory implements ViewModelProvider.Factory {
        private final float defaultTextSizePx;
        private final int savedTextSizeIndex;

        public TextViewModelFactory(float f, int i) {
            this.defaultTextSizePx = f;
            this.savedTextSizeIndex = i;
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
            if (cls.isAssignableFrom(TextSizeModel.class)) {
                return new TextSizeModel(this.defaultTextSizePx, this.savedTextSizeIndex);
            }
            throw new IllegalArgumentException("Unknown View Model Class");
        }
    }

    public TextSizeModel(float f, int i) {
        this.defaultTextSizePx = f;
        this.currentIndex = i;
    }

    public final boolean canIncreaseTextSize() {
        if (this.currentIndex < 5) {
            return true;
        }
        return false;
    }

    public final boolean canReduceTextSize() {
        if (this.currentIndex > 0) {
            return true;
        }
        return false;
    }

    public final float currentPx() {
        return this.defaultTextSizePx * (((this.currentIndex - 1) * 0.4f) + 1.0f);
    }

    public final MutableLiveData getTextSizePx() {
        return (MutableLiveData) this.textSizePx$delegate.getValue();
    }
}
