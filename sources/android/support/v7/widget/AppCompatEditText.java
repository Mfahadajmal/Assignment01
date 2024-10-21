package android.support.v7.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegateImpl;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.constraintlayout.core.widgets.analyzer.RunGroup;
import androidx.core.content.ContextCompat$Api21Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentViewBehavior;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.lifecycle.ViewModelStore;
import androidx.work.impl.model.WorkName;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AppCompatEditText extends EditText implements OnReceiveContentViewBehavior {
    private final WorkName mAppCompatEmojiEditTextHelper$ar$class_merging$ar$class_merging;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final TextViewOnReceiveContentListener mDefaultOnReceiveContentListener;
    private FloatingActionButton.ShadowDelegateImpl mSuperCaller$ar$class_merging$700ce5cd_0$ar$class_merging$ar$class_merging$ar$class_merging;
    private final RunGroup mTextClassifierHelper$ar$class_merging;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    private final FloatingActionButton.ShadowDelegateImpl getSuperCaller$ar$class_merging$dab0cd70_0$ar$class_merging$ar$class_merging$ar$class_merging() {
        if (this.mSuperCaller$ar$class_merging$700ce5cd_0$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            this.mSuperCaller$ar$class_merging$700ce5cd_0$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        }
        return this.mSuperCaller$ar$class_merging$700ce5cd_0$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    @Override // android.widget.TextView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    @Override // android.widget.TextView
    public final ActionMode.Callback getCustomSelectionActionModeCallback() {
        return DrawableCompat$Api21Impl.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    @Override // android.widget.TextView
    public final TextClassifier getTextClassifier() {
        RunGroup runGroup;
        if (Build.VERSION.SDK_INT >= 28 || (runGroup = this.mTextClassifierHelper$ar$class_merging) == null) {
            return super.getTextClassifier();
        }
        return runGroup.getTextClassifier();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        String[] onReceiveContentMimeTypes;
        final InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AppCompatTextHelper.populateSurroundingTextIfNeeded$ar$ds(this, onCreateInputConnection, editorInfo);
        AppCompatDelegateImpl.Api33Impl.onCreateInputConnection$ar$ds(onCreateInputConnection, editorInfo, this);
        if (onCreateInputConnection != null && Build.VERSION.SDK_INT <= 30 && (onReceiveContentMimeTypes = ViewCompat.getOnReceiveContentMimeTypes(this)) != null) {
            editorInfo.contentMimeTypes = onReceiveContentMimeTypes;
            final FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(this, null);
            ContextCompat$Api21Impl.requireNonNull$ar$ds$6106c18d_0(editorInfo, "editorInfo must be non-null");
            onCreateInputConnection = new InputConnectionWrapper(onCreateInputConnection) { // from class: androidx.core.view.inputmethod.InputConnectionCompat$1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, androidx.core.view.ContentInfoCompat$BuilderCompat] */
                /* JADX WARN: Type inference failed for: r2v6, types: [android.os.Parcelable, java.lang.Object] */
                /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object, androidx.core.view.ContentInfoCompat$BuilderCompat] */
                @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
                public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
                    Bundle bundle2;
                    Bundle bundle3;
                    ViewModelStore viewModelStore = null;
                    if (inputContentInfo != null) {
                        viewModelStore = new ViewModelStore(new ViewModelStore(inputContentInfo, (byte[]) null), (byte[]) null);
                    }
                    FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2 = shadowDelegateImpl;
                    if ((i & 1) != 0) {
                        try {
                            ((ViewModelStore) viewModelStore.ViewModelStore$ar$map).requestPermission();
                            ?? inputContentInfo2 = ((ViewModelStore) viewModelStore.ViewModelStore$ar$map).getInputContentInfo();
                            if (bundle == null) {
                                bundle2 = new Bundle();
                            } else {
                                bundle2 = new Bundle(bundle);
                            }
                            bundle2.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", inputContentInfo2);
                            bundle3 = bundle2;
                        } catch (Exception e) {
                            Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e);
                        }
                    } else {
                        bundle3 = bundle;
                    }
                    Object obj = shadowDelegateImpl2.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                    AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(new ClipData(((ViewModelStore) viewModelStore.ViewModelStore$ar$map).getDescription(), new ClipData.Item(((ViewModelStore) viewModelStore.ViewModelStore$ar$map).getContentUri())), 2);
                    collectionItemInfoCompat.mInfo.setLinkUri(((ViewModelStore) viewModelStore.ViewModelStore$ar$map).getLinkUri());
                    collectionItemInfoCompat.mInfo.setExtras(bundle3);
                    if (ViewCompat.performReceiveContent((View) obj, collectionItemInfoCompat.build()) == null) {
                        return true;
                    }
                    return super.commitContent(inputContentInfo, i, bundle);
                }
            };
        }
        return this.mAppCompatEmojiEditTextHelper$ar$class_merging$ar$class_merging.onCreateInputConnection(onCreateInputConnection, editorInfo);
    }

    @Override // android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT >= 30 && Build.VERSION.SDK_INT < 33) {
            ((InputMethodManager) getContext().getSystemService("input_method")).isActive(this);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 31 && dragEvent.getLocalState() == null && ViewCompat.getOnReceiveContentMimeTypes(this) != null) {
            Context context = getContext();
            while (true) {
                if (context instanceof ContextWrapper) {
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                        break;
                    }
                    context = ((ContextWrapper) context).getBaseContext();
                } else {
                    activity = null;
                    break;
                }
            }
            if (activity == null) {
                toString();
            } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3) {
                AppCompatReceiveContentHelper$OnDropApi24Impl.onDropForTextView(dragEvent, this, activity);
                return true;
            }
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // androidx.core.view.OnReceiveContentViewBehavior
    public final ContentInfoCompat onReceiveContent(ContentInfoCompat contentInfoCompat) {
        return this.mDefaultOnReceiveContentListener.onReceiveContent(this, contentInfoCompat);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, androidx.core.view.ContentInfoCompat$BuilderCompat] */
    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i) {
        ClipData primaryClip;
        int i2;
        if (Build.VERSION.SDK_INT < 31 && ViewCompat.getOnReceiveContentMimeTypes(this) != null) {
            if (i != 16908322) {
                if (i == 16908337) {
                    i = 16908337;
                }
            }
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
            if (clipboardManager == null) {
                primaryClip = null;
            } else {
                primaryClip = clipboardManager.getPrimaryClip();
            }
            if (primaryClip != null && primaryClip.getItemCount() > 0) {
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(primaryClip, 1);
                if (i == 16908322) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                collectionItemInfoCompat.mInfo.setFlags(i2);
                ViewCompat.performReceiveContent(this, collectionItemInfoCompat.build());
            }
            return true;
        }
        return super.onTextContextMenuItem(i);
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
    public final void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.mAppCompatEmojiEditTextHelper$ar$class_merging$ar$class_merging.getKeyListener(keyListener));
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetTextAppearance(context, i);
        }
    }

    @Override // android.widget.TextView
    public final void setTextClassifier(TextClassifier textClassifier) {
        RunGroup runGroup;
        if (Build.VERSION.SDK_INT >= 28 || (runGroup = this.mTextClassifierHelper$ar$class_merging) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            runGroup.RunGroup$ar$mFirstRun = textClassifier;
        }
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper;
        appCompatBackgroundHelper.loadFromAttributes(attributeSet, i);
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.loadFromAttributes(attributeSet, i);
        appCompatTextHelper.applyCompoundDrawablesTints();
        this.mTextClassifierHelper$ar$class_merging = new RunGroup(this);
        this.mDefaultOnReceiveContentListener = new TextViewOnReceiveContentListener();
        WorkName workName = new WorkName(this);
        this.mAppCompatEmojiEditTextHelper$ar$class_merging$ar$class_merging = workName;
        workName.loadFromAttributes(attributeSet, i);
        KeyListener keyListener = getKeyListener();
        if (WorkName.isEmojiCapableKeyListener$ar$ds(keyListener)) {
            boolean isFocusable = super.isFocusable();
            boolean isClickable = super.isClickable();
            boolean isLongClickable = super.isLongClickable();
            int inputType = super.getInputType();
            KeyListener keyListener2 = workName.getKeyListener(keyListener);
            if (keyListener2 == keyListener) {
                return;
            }
            super.setKeyListener(keyListener2);
            super.setRawInputType(inputType);
            super.setFocusable(isFocusable);
            super.setClickable(isClickable);
            super.setLongClickable(isLongClickable);
        }
    }
}
