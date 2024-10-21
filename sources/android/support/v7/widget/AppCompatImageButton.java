package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.SharedResourceHolder;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AppCompatImageButton extends ImageButton {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private boolean mHasLevel;
    private final SharedResourceHolder.Instance mImageHelper$ar$class_merging$ar$class_merging;

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
        SharedResourceHolder.Instance instance = this.mImageHelper$ar$class_merging$ar$class_merging;
        if (instance != null) {
            instance.applySupportImageTint();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        if (this.mImageHelper$ar$class_merging$ar$class_merging.hasOverlappingRendering() && super.hasOverlappingRendering()) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundDrawable$ar$ds();
        }
    }

    @Override // android.view.View
    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundResource(i);
        }
    }

    @Override // android.widget.ImageView
    public final void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        SharedResourceHolder.Instance instance = this.mImageHelper$ar$class_merging$ar$class_merging;
        if (instance != null) {
            instance.applySupportImageTint();
        }
    }

    @Override // android.widget.ImageView
    public final void setImageDrawable(Drawable drawable) {
        SharedResourceHolder.Instance instance = this.mImageHelper$ar$class_merging$ar$class_merging;
        if (instance != null && drawable != null && !this.mHasLevel) {
            instance.obtainLevelFromDrawable(drawable);
        }
        super.setImageDrawable(drawable);
        SharedResourceHolder.Instance instance2 = this.mImageHelper$ar$class_merging$ar$class_merging;
        if (instance2 != null) {
            instance2.applySupportImageTint();
            if (!this.mHasLevel) {
                this.mImageHelper$ar$class_merging$ar$class_merging.applyImageLevel();
            }
        }
    }

    @Override // android.widget.ImageView
    public final void setImageLevel(int i) {
        super.setImageLevel(i);
        this.mHasLevel = true;
    }

    @Override // android.widget.ImageView
    public final void setImageResource(int i) {
        this.mImageHelper$ar$class_merging$ar$class_merging.setImageResource(i);
    }

    @Override // android.widget.ImageView
    public final void setImageURI(Uri uri) {
        super.setImageURI(uri);
        SharedResourceHolder.Instance instance = this.mImageHelper$ar$class_merging$ar$class_merging;
        if (instance != null) {
            instance.applySupportImageTint();
        }
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mHasLevel = false;
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper;
        appCompatBackgroundHelper.loadFromAttributes(attributeSet, i);
        SharedResourceHolder.Instance instance = new SharedResourceHolder.Instance((ImageView) this);
        this.mImageHelper$ar$class_merging$ar$class_merging = instance;
        instance.loadFromAttributes(attributeSet, i);
    }
}
