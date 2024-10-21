package com.google.android.material.textfield;

import _COROUTINE._BOUNDARY;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.menu.CascadingMenuPopup;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper;
import com.google.android.marvin.talkback.R;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.util.concurrent.ExecutionList;
import io.grpc.internal.RetryingNameResolver;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EndCompoundLayout extends LinearLayout {
    private final AccessibilityManager accessibilityManager;
    public EditText editText;
    public final TextWatcher editTextWatcher;
    private final LinkedHashSet endIconChangedListeners;
    private final EndIconDelegates endIconDelegates;
    private final FrameLayout endIconFrame;
    private int endIconMinSize;
    public int endIconMode;
    public ColorStateList endIconTintList;
    public PorterDuff.Mode endIconTintMode;
    public final CheckableImageButton endIconView;
    public ColorStateList errorIconTintList;
    private PorterDuff.Mode errorIconTintMode;
    public final CheckableImageButton errorIconView;
    private boolean hintExpanded;
    private final RetryingNameResolver.ResolutionResultListener onEditTextAttachedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public CharSequence suffixText;
    public final TextView suffixTextView;
    final TextInputLayout textInputLayout;
    private RetryingNameResolver.ResolutionResultListener touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EndIconDelegates {
        public final int customEndIconDrawableId;
        public final SparseArray delegates = new SparseArray();
        public final EndCompoundLayout endLayout;
        public final int passwordIconDrawableId;

        public EndIconDelegates(EndCompoundLayout endCompoundLayout, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
            this.endLayout = endCompoundLayout;
            int[] iArr = R$styleable.MaterialAutoCompleteTextView;
            this.customEndIconDrawableId = runnableExecutorPair.getResourceId(28, 0);
            this.passwordIconDrawableId = runnableExecutorPair.getResourceId(52, 0);
        }
    }

    public EndCompoundLayout(TextInputLayout textInputLayout, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        super(textInputLayout.getContext());
        this.endIconMode = 0;
        this.endIconChangedListeners = new LinkedHashSet();
        this.editTextWatcher = new TextWatcherAdapter() { // from class: com.google.android.material.textfield.EndCompoundLayout.1
            @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                EndCompoundLayout.this.getEndIconDelegate().afterEditTextChanged$ar$ds();
            }

            @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                EndCompoundLayout.this.getEndIconDelegate().beforeEditTextChanged$ar$ds();
            }
        };
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(this);
        this.onEditTextAttachedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.textInputLayout = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.endIconFrame = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater from = LayoutInflater.from(getContext());
        CheckableImageButton createIconView = createIconView(this, from, R.id.text_input_error_icon);
        this.errorIconView = createIconView;
        CheckableImageButton createIconView2 = createIconView(frameLayout, from, R.id.text_input_end_icon);
        this.endIconView = createIconView2;
        this.endIconDelegates = new EndIconDelegates(this, runnableExecutorPair);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.suffixTextView = appCompatTextView;
        int[] iArr = R$styleable.MaterialAutoCompleteTextView;
        if (runnableExecutorPair.hasValue(38)) {
            this.errorIconTintList = DrawableUtils$OutlineCompatR.getColorStateList$ar$class_merging$ar$class_merging$ar$class_merging(getContext(), runnableExecutorPair, 38);
        }
        if (runnableExecutorPair.hasValue(39)) {
            this.errorIconTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(runnableExecutorPair.getInt(39, -1), null);
        }
        if (runnableExecutorPair.hasValue(37)) {
            setErrorIconDrawable(runnableExecutorPair.getDrawable(37));
        }
        createIconView.setContentDescription(getResources().getText(R.string.error_icon_content_description));
        createIconView.setImportantForAccessibility(2);
        createIconView.setClickable(false);
        createIconView.pressable = false;
        createIconView.setFocusable(false);
        if (!runnableExecutorPair.hasValue(53)) {
            if (runnableExecutorPair.hasValue(32)) {
                this.endIconTintList = DrawableUtils$OutlineCompatR.getColorStateList$ar$class_merging$ar$class_merging$ar$class_merging(getContext(), runnableExecutorPair, 32);
            }
            if (runnableExecutorPair.hasValue(33)) {
                this.endIconTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(runnableExecutorPair.getInt(33, -1), null);
            }
        }
        if (runnableExecutorPair.hasValue(30)) {
            setEndIconMode(runnableExecutorPair.getInt(30, 0));
            if (runnableExecutorPair.hasValue(27)) {
                setEndIconContentDescription(runnableExecutorPair.getText(27));
            }
            setEndIconCheckable(runnableExecutorPair.getBoolean(26, true));
        } else if (runnableExecutorPair.hasValue(53)) {
            if (runnableExecutorPair.hasValue(54)) {
                this.endIconTintList = DrawableUtils$OutlineCompatR.getColorStateList$ar$class_merging$ar$class_merging$ar$class_merging(getContext(), runnableExecutorPair, 54);
            }
            if (runnableExecutorPair.hasValue(55)) {
                this.endIconTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(runnableExecutorPair.getInt(55, -1), null);
            }
            setEndIconMode(runnableExecutorPair.getBoolean(53, false) ? 1 : 0);
            setEndIconContentDescription(runnableExecutorPair.getText(51));
        }
        int dimensionPixelSize = runnableExecutorPair.getDimensionPixelSize(29, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size));
        if (dimensionPixelSize >= 0) {
            if (dimensionPixelSize != this.endIconMinSize) {
                this.endIconMinSize = dimensionPixelSize;
                EdgeTreatment.setIconMinSize(createIconView2, dimensionPixelSize);
                EdgeTreatment.setIconMinSize(createIconView, dimensionPixelSize);
            }
            if (runnableExecutorPair.hasValue(31)) {
                ImageView.ScaleType convertScaleType = EdgeTreatment.convertScaleType(runnableExecutorPair.getInt(31, -1));
                createIconView2.setScaleType(convertScaleType);
                createIconView.setScaleType(convertScaleType);
            }
            appCompatTextView.setVisibility(8);
            appCompatTextView.setId(R.id.textinput_suffix_text);
            appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
            appCompatTextView.setAccessibilityLiveRegion(1);
            appCompatTextView.setTextAppearance(runnableExecutorPair.getResourceId(72, 0));
            if (runnableExecutorPair.hasValue(73)) {
                appCompatTextView.setTextColor(runnableExecutorPair.getColorStateList(73));
            }
            CharSequence text = runnableExecutorPair.getText(71);
            this.suffixText = true != TextUtils.isEmpty(text) ? text : null;
            appCompatTextView.setText(text);
            updateSuffixTextVisibility();
            frameLayout.addView(createIconView2);
            addView(appCompatTextView);
            addView(frameLayout);
            addView(createIconView);
            textInputLayout.editTextAttachedListeners.add(resolutionResultListener);
            if (textInputLayout.editText != null) {
                resolutionResultListener.onEditTextAttached(textInputLayout);
            }
            addOnAttachStateChangeListener(new CascadingMenuPopup.AnonymousClass2(this, 3));
            return;
        }
        throw new IllegalArgumentException("endIconSize cannot be less than 0");
    }

    private final CheckableImageButton createIconView(ViewGroup viewGroup, LayoutInflater layoutInflater, int i) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(R.layout.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i);
        if (DrawableUtils$OutlineCompatR.isFontScaleAtLeast1_3(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginStart(0);
        }
        return checkableImageButton;
    }

    private final void updateEndLayoutVisibility() {
        int i;
        boolean z;
        int i2 = 0;
        if (this.endIconView.getVisibility() == 0 && !isErrorIconVisible()) {
            i = 0;
        } else {
            i = 8;
        }
        this.endIconFrame.setVisibility(i);
        if (this.suffixText != null && !this.hintExpanded) {
            z = false;
        } else {
            z = 8;
        }
        if (!isEndIconVisible() && !isErrorIconVisible() && z) {
            i2 = 8;
        }
        setVisibility(i2);
    }

    private final void updateSuffixTextVisibility() {
        int visibility = this.suffixTextView.getVisibility();
        boolean z = false;
        int i = 8;
        if (this.suffixText != null && !this.hintExpanded) {
            i = 0;
        }
        if (visibility != i) {
            EndIconDelegate endIconDelegate = getEndIconDelegate();
            if (i == 0) {
                z = true;
            }
            endIconDelegate.onSuffixVisibilityChanged(z);
        }
        updateEndLayoutVisibility();
        this.suffixTextView.setVisibility(i);
        this.textInputLayout.updateDummyDrawables();
    }

    public final void addTouchExplorationStateChangeListenerIfNeeded() {
        if (this.touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null && this.accessibilityManager != null && isAttachedToWindow()) {
            this.accessibilityManager.addTouchExplorationStateChangeListener(new AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper(this.touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging));
        }
    }

    public final EndIconDelegate getEndIconDelegate() {
        EndIconDelegate customEndIconDelegate;
        EndIconDelegates endIconDelegates = this.endIconDelegates;
        SparseArray sparseArray = endIconDelegates.delegates;
        int i = this.endIconMode;
        EndIconDelegate endIconDelegate = (EndIconDelegate) sparseArray.get(i);
        if (endIconDelegate == null) {
            if (i != -1) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                customEndIconDelegate = new DropdownMenuEndIconDelegate(endIconDelegates.endLayout);
                            } else {
                                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Invalid end icon mode: "));
                            }
                        } else {
                            customEndIconDelegate = new ClearTextEndIconDelegate(endIconDelegates.endLayout);
                        }
                    } else {
                        endIconDelegate = new PasswordToggleEndIconDelegate(endIconDelegates.endLayout, endIconDelegates.passwordIconDrawableId);
                        endIconDelegates.delegates.append(i, endIconDelegate);
                    }
                } else {
                    customEndIconDelegate = new NoEndIconDelegate(endIconDelegates.endLayout);
                }
            } else {
                customEndIconDelegate = new CustomEndIconDelegate(endIconDelegates.endLayout);
            }
            endIconDelegate = customEndIconDelegate;
            endIconDelegates.delegates.append(i, endIconDelegate);
        }
        return endIconDelegate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getSuffixTextEndOffset() {
        int marginStart;
        if (!isEndIconVisible() && !isErrorIconVisible()) {
            marginStart = 0;
        } else {
            CheckableImageButton checkableImageButton = this.endIconView;
            marginStart = ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).getMarginStart() + checkableImageButton.getMeasuredWidth();
        }
        return getPaddingEnd() + this.suffixTextView.getPaddingEnd() + marginStart;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasEndIcon() {
        if (this.endIconMode != 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEndIconVisible() {
        if (this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isErrorIconVisible() {
        if (this.errorIconView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onHintStateChanged(boolean z) {
        this.hintExpanded = z;
        updateSuffixTextVisibility();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void refreshEndIconDrawableState() {
        EdgeTreatment.refreshIconDrawableState(this.textInputLayout, this.endIconView, this.endIconTintList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void refreshIconState(boolean z) {
        boolean isActivated;
        boolean z2;
        EndIconDelegate endIconDelegate = getEndIconDelegate();
        boolean z3 = false;
        boolean z4 = true;
        if (endIconDelegate.isIconCheckable() && (z2 = this.endIconView.checked) != endIconDelegate.isIconChecked()) {
            this.endIconView.setChecked(!z2);
            z3 = true;
        }
        if (endIconDelegate.isIconActivable() && (isActivated = this.endIconView.isActivated()) != endIconDelegate.isIconActivated()) {
            this.endIconView.setActivated(!isActivated);
        } else {
            z4 = z3;
        }
        if (!z && !z4) {
            return;
        }
        refreshEndIconDrawableState();
    }

    public final void removeTouchExplorationStateChangeListenerIfNeeded() {
        AccessibilityManager accessibilityManager;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (resolutionResultListener != null && (accessibilityManager = this.accessibilityManager) != null) {
            accessibilityManager.removeTouchExplorationStateChangeListener(new AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper(resolutionResultListener));
        }
    }

    final void setEndIconCheckable(boolean z) {
        this.endIconView.setCheckable(z);
    }

    final void setEndIconContentDescription(CharSequence charSequence) {
        if (this.endIconView.getContentDescription() != charSequence) {
            this.endIconView.setContentDescription(charSequence);
        }
    }

    final void setEndIconMode(int i) {
        boolean z;
        Drawable drawable;
        if (this.endIconMode == i) {
            return;
        }
        EndIconDelegate endIconDelegate = getEndIconDelegate();
        removeTouchExplorationStateChangeListenerIfNeeded();
        CharSequence charSequence = null;
        this.touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        endIconDelegate.tearDown();
        this.endIconMode = i;
        Iterator it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            ((TextInputLayout.OnEndIconChangedListener) it.next()).onEndIconChanged$ar$ds();
        }
        if (i != 0) {
            z = true;
        } else {
            z = false;
        }
        setEndIconVisible(z);
        EndIconDelegate endIconDelegate2 = getEndIconDelegate();
        int i2 = this.endIconDelegates.customEndIconDrawableId;
        if (i2 == 0) {
            i2 = endIconDelegate2.getIconDrawableResId();
        }
        if (i2 != 0) {
            drawable = AppCompatDelegate.Api33Impl.getDrawable(getContext(), i2);
        } else {
            drawable = null;
        }
        this.endIconView.setImageDrawable(drawable);
        if (drawable != null) {
            EdgeTreatment.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
            refreshEndIconDrawableState();
        }
        int iconContentDescriptionResId = endIconDelegate2.getIconContentDescriptionResId();
        if (iconContentDescriptionResId != 0) {
            charSequence = getResources().getText(iconContentDescriptionResId);
        }
        setEndIconContentDescription(charSequence);
        setEndIconCheckable(endIconDelegate2.isIconCheckable());
        int i3 = this.textInputLayout.boxBackgroundMode;
        if (endIconDelegate2.isBoxBackgroundModeSupported(i3)) {
            endIconDelegate2.setUp();
            this.touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = endIconDelegate2.getTouchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
            addTouchExplorationStateChangeListenerIfNeeded();
            EdgeTreatment.setIconOnClickListener$ar$ds(this.endIconView, endIconDelegate2.getOnIconClickListener());
            EditText editText = this.editText;
            if (editText != null) {
                endIconDelegate2.onEditTextAttached(editText);
                setOnFocusChangeListenersIfNeeded(endIconDelegate2);
            }
            EdgeTreatment.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
            refreshIconState(true);
            return;
        }
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i, i3, "The current box background mode ", " is not supported by the end icon mode "));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setEndIconVisible(boolean z) {
        int i;
        if (isEndIconVisible() != z) {
            CheckableImageButton checkableImageButton = this.endIconView;
            if (true != z) {
                i = 8;
            } else {
                i = 0;
            }
            checkableImageButton.setVisibility(i);
            updateEndLayoutVisibility();
            updateSuffixTextViewPadding();
            this.textInputLayout.updateDummyDrawables();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setErrorIconDrawable(Drawable drawable) {
        this.errorIconView.setImageDrawable(drawable);
        updateErrorIconVisibility();
        EdgeTreatment.applyIconTint(this.textInputLayout, this.errorIconView, this.errorIconTintList, this.errorIconTintMode);
    }

    public final void setOnFocusChangeListenersIfNeeded(EndIconDelegate endIconDelegate) {
        EditText editText = this.editText;
        if (editText != null) {
            if (endIconDelegate.getOnEditTextFocusChangeListener() != null) {
                editText.setOnFocusChangeListener(endIconDelegate.getOnEditTextFocusChangeListener());
            }
            if (endIconDelegate.getOnIconViewFocusChangeListener() != null) {
                this.endIconView.setOnFocusChangeListener(endIconDelegate.getOnIconViewFocusChangeListener());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateErrorIconVisibility() {
        /*
            r4 = this;
            com.google.android.material.internal.CheckableImageButton r0 = r4.errorIconView
            android.graphics.drawable.Drawable r0 = r0.getDrawable()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1a
            com.google.android.material.textfield.TextInputLayout r0 = r4.textInputLayout
            com.google.android.material.textfield.IndicatorViewController r3 = r0.indicatorViewController
            boolean r3 = r3.errorEnabled
            if (r3 == 0) goto L1a
            boolean r0 = r0.shouldShowError()
            if (r0 == 0) goto L1a
            r0 = r1
            goto L1b
        L1a:
            r0 = r2
        L1b:
            com.google.android.material.internal.CheckableImageButton r3 = r4.errorIconView
            if (r1 == r0) goto L21
            r2 = 8
        L21:
            r3.setVisibility(r2)
            r4.updateEndLayoutVisibility()
            r4.updateSuffixTextViewPadding()
            boolean r0 = r4.hasEndIcon()
            if (r0 != 0) goto L35
            com.google.android.material.textfield.TextInputLayout r0 = r4.textInputLayout
            r0.updateDummyDrawables()
        L35:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.EndCompoundLayout.updateErrorIconVisibility():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateSuffixTextViewPadding() {
        if (this.textInputLayout.editText == null) {
            return;
        }
        int i = 0;
        if (!isEndIconVisible() && !isErrorIconVisible()) {
            i = this.textInputLayout.editText.getPaddingEnd();
        }
        this.suffixTextView.setPaddingRelative(getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), this.textInputLayout.editText.getPaddingTop(), i, this.textInputLayout.editText.getPaddingBottom());
    }
}
