package android.support.v7.widget;

import _COROUTINE._BOUNDARY;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavArgument;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatBackgroundHelper {
    public NavArgument.Builder mBackgroundTint$ar$class_merging;
    private NavArgument.Builder mInternalBackgroundTint$ar$class_merging;
    private NavArgument.Builder mTmpInfo$ar$class_merging;
    private final View mView;
    private int mBackgroundResId = -1;
    private final AppCompatDrawableManager mDrawableManager = AppCompatDrawableManager.get();

    public AppCompatBackgroundHelper(View view) {
        this.mView = view;
    }

    public final void applySupportBackgroundTint() {
        Drawable background = this.mView.getBackground();
        if (background != null) {
            if (this.mInternalBackgroundTint$ar$class_merging != null) {
                if (this.mTmpInfo$ar$class_merging == null) {
                    this.mTmpInfo$ar$class_merging = new NavArgument.Builder();
                }
                NavArgument.Builder builder = this.mTmpInfo$ar$class_merging;
                builder.clear();
                ColorStateList backgroundTintList = ViewCompat.Api21Impl.getBackgroundTintList(this.mView);
                if (backgroundTintList != null) {
                    builder.defaultValuePresent = true;
                    builder.NavArgument$Builder$ar$type = backgroundTintList;
                }
                PorterDuff.Mode backgroundTintMode = ViewCompat.Api21Impl.getBackgroundTintMode(this.mView);
                if (backgroundTintMode != null) {
                    builder.isNullable = true;
                    builder.defaultValue = backgroundTintMode;
                }
                if (builder.defaultValuePresent || builder.isNullable) {
                    ResourceManagerInternal.tintDrawable$ar$class_merging(background, builder, this.mView.getDrawableState());
                    return;
                }
            }
            NavArgument.Builder builder2 = this.mBackgroundTint$ar$class_merging;
            if (builder2 != null) {
                ResourceManagerInternal.tintDrawable$ar$class_merging(background, builder2, this.mView.getDrawableState());
                return;
            }
            NavArgument.Builder builder3 = this.mInternalBackgroundTint$ar$class_merging;
            if (builder3 != null) {
                ResourceManagerInternal.tintDrawable$ar$class_merging(background, builder3, this.mView.getDrawableState());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void loadFromAttributes(AttributeSet attributeSet, int i) {
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(this.mView.getContext(), attributeSet, R$styleable.ViewBackgroundHelper, i, 0);
        Object obj = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable;
        View view = this.mView;
        ViewCompat.saveAttributeDataForStyleable(view, view.getContext(), R$styleable.ViewBackgroundHelper, attributeSet, (TypedArray) obj, i, 0);
        try {
            if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(0)) {
                this.mBackgroundResId = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(0, -1);
                ColorStateList tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
                if (tintList != null) {
                    setInternalBackgroundTint(tintList);
                }
            }
            if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(1)) {
                ViewCompat.Api21Impl.setBackgroundTintList(this.mView, obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getColorStateList(1));
            }
            if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(2)) {
                ViewCompat.Api21Impl.setBackgroundTintMode(this.mView, _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(2, -1), null));
            }
        } finally {
            obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onSetBackgroundDrawable$ar$ds() {
        this.mBackgroundResId = -1;
        setInternalBackgroundTint(null);
        applySupportBackgroundTint();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onSetBackgroundResource(int i) {
        ColorStateList colorStateList;
        this.mBackgroundResId = i;
        AppCompatDrawableManager appCompatDrawableManager = this.mDrawableManager;
        if (appCompatDrawableManager != null) {
            colorStateList = appCompatDrawableManager.getTintList(this.mView.getContext(), i);
        } else {
            colorStateList = null;
        }
        setInternalBackgroundTint(colorStateList);
        applySupportBackgroundTint();
    }

    final void setInternalBackgroundTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalBackgroundTint$ar$class_merging == null) {
                this.mInternalBackgroundTint$ar$class_merging = new NavArgument.Builder();
            }
            NavArgument.Builder builder = this.mInternalBackgroundTint$ar$class_merging;
            builder.NavArgument$Builder$ar$type = colorStateList;
            builder.defaultValuePresent = true;
        } else {
            this.mInternalBackgroundTint$ar$class_merging = null;
        }
        applySupportBackgroundTint();
    }
}
