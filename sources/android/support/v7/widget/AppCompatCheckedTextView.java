package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatCheckedTextView extends CheckedTextView {
    private NodeBrailler mAppCompatEmojiTextHelper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatCheckedTextViewHelper mCheckedHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        super(TintContextWrapper.wrap(context), attributeSet, R.attr.checkedTextViewStyle);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.loadFromAttributes(attributeSet, R.attr.checkedTextViewStyle);
        appCompatTextHelper.applyCompoundDrawablesTints();
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper;
        appCompatBackgroundHelper.loadFromAttributes(attributeSet, R.attr.checkedTextViewStyle);
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = new AppCompatCheckedTextViewHelper(this);
        this.mCheckedHelper = appCompatCheckedTextViewHelper;
        appCompatCheckedTextViewHelper.loadFromAttributes$ar$ds(attributeSet);
        getEmojiTextViewHelper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().loadFromAttributes(attributeSet, R.attr.checkedTextViewStyle);
    }

    private final NodeBrailler getEmojiTextViewHelper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        if (this.mAppCompatEmojiTextHelper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            this.mAppCompatEmojiTextHelper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new NodeBrailler(this);
        }
        return this.mAppCompatEmojiTextHelper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.mCheckedHelper;
        if (appCompatCheckedTextViewHelper != null) {
            appCompatCheckedTextViewHelper.applyCheckMarkTint();
        }
    }

    @Override // android.widget.TextView
    public final ActionMode.Callback getCustomSelectionActionModeCallback() {
        return DrawableCompat$Api21Impl.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AppCompatDelegateImpl.Api33Impl.onCreateInputConnection$ar$ds(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.TextView
    public final void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().setAllCaps(z);
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

    @Override // android.widget.CheckedTextView
    public final void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(AppCompatDelegate.Api33Impl.getDrawable(getContext(), i));
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    @Override // android.widget.TextView
    public final void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(DrawableCompat$Api21Impl.wrapCustomSelectionActionModeCallback(this, callback));
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetTextAppearance(context, i);
        }
    }

    @Override // android.widget.CheckedTextView
    public final void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.mCheckedHelper;
        if (appCompatCheckedTextViewHelper != null) {
            if (appCompatCheckedTextViewHelper.mSkipNextApply) {
                appCompatCheckedTextViewHelper.mSkipNextApply = false;
            } else {
                appCompatCheckedTextViewHelper.mSkipNextApply = true;
                appCompatCheckedTextViewHelper.applyCheckMarkTint();
            }
        }
    }
}
