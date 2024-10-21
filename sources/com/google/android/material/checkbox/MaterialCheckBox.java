package com.google.android.material.checkbox;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCompoundButtonHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CompoundButton;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.widget.CompoundButtonCompat$Api21Impl;
import androidx.core.widget.CompoundButtonCompat$Api23Impl;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.chromium.base.BundleUtils$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialCheckBox extends AppCompatCheckBox {
    private static final int DEF_STYLE_RES = 2132150272;
    private boolean broadcasting;
    private Drawable buttonDrawable;
    private Drawable buttonIconDrawable;
    final ColorStateList buttonIconTintList;
    private final PorterDuff.Mode buttonIconTintMode;
    ColorStateList buttonTintList;
    private final boolean centerIfNoTextEnabled;
    private int checkedState;
    public int[] currentStateChecked;
    private CharSequence customStateDescription;
    private final CharSequence errorAccessibilityLabel;
    private final boolean errorShown;
    private ColorStateList materialThemeColorsTintList;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private final LinkedHashSet onCheckedStateChangedListeners;
    private final AnimatedVectorDrawableCompat transitionToUnchecked;
    private final Animatable2Compat$AnimationCallback transitionToUncheckedCallback;
    private boolean useMaterialThemeColors;
    private boolean usingMaterialButtonDrawable;
    private static final int[] INDETERMINATE_STATE_SET = {R.attr.state_indeterminate};
    private static final int[] ERROR_STATE_SET = {R.attr.state_error};
    private static final int[][] CHECKBOX_STATES = {new int[]{android.R.attr.state_enabled, R.attr.state_error}, new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, new int[]{android.R.attr.state_enabled, -16842912}, new int[]{-16842910, android.R.attr.state_checked}, new int[]{-16842910, -16842912}};
    private static final int FRAMEWORK_BUTTON_DRAWABLE_RES_ID = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnCheckedStateChangedListener {
        void onCheckedStateChangedListener$ar$ds();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialCheckBox(android.content.Context r10, android.util.AttributeSet r11) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.checkbox.MaterialCheckBox.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private final void refreshButtonDrawable() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
        Animator.AnimatorListener animatorListener;
        this.buttonDrawable = DrawableUtils$OutlineCompatR.createTintableMutatedDrawableIfNeeded(this.buttonDrawable, this.buttonTintList, CompoundButtonCompat$Api21Impl.getButtonTintMode(this));
        this.buttonIconDrawable = DrawableUtils$OutlineCompatR.createTintableMutatedDrawableIfNeeded(this.buttonIconDrawable, this.buttonIconTintList, this.buttonIconTintMode);
        if (this.usingMaterialButtonDrawable) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat2 = this.transitionToUnchecked;
            if (animatedVectorDrawableCompat2 != null) {
                Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback = this.transitionToUncheckedCallback;
                if (animatable2Compat$AnimationCallback != null) {
                    Drawable drawable = animatedVectorDrawableCompat2.mDelegateDrawable;
                    if (drawable != null) {
                        AnimatedVectorDrawableCompat.Api23Impl.unregisterAnimationCallback((AnimatedVectorDrawable) drawable, animatable2Compat$AnimationCallback.getPlatformCallback());
                    }
                    ArrayList arrayList = animatedVectorDrawableCompat2.mAnimationCallbacks;
                    if (arrayList != null) {
                        arrayList.remove(animatable2Compat$AnimationCallback);
                        if (animatedVectorDrawableCompat2.mAnimationCallbacks.size() == 0 && (animatorListener = animatedVectorDrawableCompat2.mAnimatorListener) != null) {
                            animatedVectorDrawableCompat2.mAnimatedVectorState.mAnimatorSet.removeListener(animatorListener);
                            animatedVectorDrawableCompat2.mAnimatorListener = null;
                        }
                    }
                }
                final AnimatedVectorDrawableCompat animatedVectorDrawableCompat3 = this.transitionToUnchecked;
                Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback2 = this.transitionToUncheckedCallback;
                if (animatable2Compat$AnimationCallback2 != null) {
                    Drawable drawable2 = animatedVectorDrawableCompat3.mDelegateDrawable;
                    if (drawable2 != null) {
                        AnimatedVectorDrawableCompat.Api23Impl.registerAnimationCallback((AnimatedVectorDrawable) drawable2, animatable2Compat$AnimationCallback2.getPlatformCallback());
                    } else {
                        if (animatedVectorDrawableCompat3.mAnimationCallbacks == null) {
                            animatedVectorDrawableCompat3.mAnimationCallbacks = new ArrayList();
                        }
                        if (!animatedVectorDrawableCompat3.mAnimationCallbacks.contains(animatable2Compat$AnimationCallback2)) {
                            animatedVectorDrawableCompat3.mAnimationCallbacks.add(animatable2Compat$AnimationCallback2);
                            if (animatedVectorDrawableCompat3.mAnimatorListener == null) {
                                animatedVectorDrawableCompat3.mAnimatorListener = new AnimatorListenerAdapter() { // from class: androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat.2
                                    public AnonymousClass2() {
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationEnd(Animator animator) {
                                        ArrayList arrayList2 = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                                        int size = arrayList2.size();
                                        for (int i = 0; i < size; i++) {
                                            ((Animatable2Compat$AnimationCallback) arrayList2.get(i)).onAnimationEnd(AnimatedVectorDrawableCompat.this);
                                        }
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationStart(Animator animator) {
                                        ArrayList arrayList2 = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                                        int size = arrayList2.size();
                                        for (int i = 0; i < size; i++) {
                                            ((Animatable2Compat$AnimationCallback) arrayList2.get(i)).onAnimationStart(AnimatedVectorDrawableCompat.this);
                                        }
                                    }
                                };
                            }
                            animatedVectorDrawableCompat3.mAnimatedVectorState.mAnimatorSet.addListener(animatedVectorDrawableCompat3.mAnimatorListener);
                        }
                    }
                }
            }
            Drawable drawable3 = this.buttonDrawable;
            if ((drawable3 instanceof AnimatedStateListDrawable) && (animatedVectorDrawableCompat = this.transitionToUnchecked) != null) {
                ((AnimatedStateListDrawable) drawable3).addTransition(R.id.checked, R.id.unchecked, animatedVectorDrawableCompat, false);
                ((AnimatedStateListDrawable) this.buttonDrawable).addTransition(R.id.indeterminate, R.id.unchecked, this.transitionToUnchecked, false);
            }
        }
        Drawable drawable4 = this.buttonDrawable;
        if (drawable4 != null && (colorStateList2 = this.buttonTintList) != null) {
            DrawableCompat$Api21Impl.setTintList(drawable4, colorStateList2);
        }
        Drawable drawable5 = this.buttonIconDrawable;
        if (drawable5 != null && (colorStateList = this.buttonIconTintList) != null) {
            DrawableCompat$Api21Impl.setTintList(drawable5, colorStateList);
        }
        Drawable drawable6 = this.buttonDrawable;
        Drawable drawable7 = this.buttonIconDrawable;
        if (drawable6 == null) {
            drawable6 = drawable7;
        } else if (drawable7 != null) {
            int intrinsicWidth = drawable7.getIntrinsicWidth();
            if (intrinsicWidth == -1) {
                intrinsicWidth = drawable6.getIntrinsicWidth();
            }
            int intrinsicHeight = drawable7.getIntrinsicHeight();
            if (intrinsicHeight == -1) {
                intrinsicHeight = drawable6.getIntrinsicHeight();
            }
            if (intrinsicWidth > drawable6.getIntrinsicWidth() || intrinsicHeight > drawable6.getIntrinsicHeight()) {
                float f = intrinsicWidth / intrinsicHeight;
                if (f >= drawable6.getIntrinsicWidth() / drawable6.getIntrinsicHeight()) {
                    int intrinsicWidth2 = drawable6.getIntrinsicWidth();
                    intrinsicHeight = (int) (intrinsicWidth2 / f);
                    intrinsicWidth = intrinsicWidth2;
                } else {
                    intrinsicHeight = drawable6.getIntrinsicHeight();
                    intrinsicWidth = (int) (f * intrinsicHeight);
                }
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable6, drawable7});
            layerDrawable.setLayerSize(1, intrinsicWidth, intrinsicHeight);
            layerDrawable.setLayerGravity(1, 17);
            drawable6 = layerDrawable;
        }
        super.setButtonDrawable(drawable6);
        refreshDrawableState();
    }

    private final void setDefaultStateDescription() {
        String string;
        if (Build.VERSION.SDK_INT >= 30 && this.customStateDescription == null) {
            int i = this.checkedState;
            if (i == 1) {
                string = getResources().getString(R.string.mtrl_checkbox_state_description_checked);
            } else if (i == 0) {
                string = getResources().getString(R.string.mtrl_checkbox_state_description_unchecked);
            } else {
                string = getResources().getString(R.string.mtrl_checkbox_state_description_indeterminate);
            }
            super.setStateDescription(string);
        }
    }

    @Override // android.widget.CompoundButton
    public final Drawable getButtonDrawable() {
        return this.buttonDrawable;
    }

    @Override // android.widget.CompoundButton
    public final ColorStateList getButtonTintList() {
        return this.buttonTintList;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final boolean isChecked() {
        if (this.checkedState == 1) {
            return true;
        }
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && this.buttonTintList == null && this.buttonIconTintList == null) {
            this.useMaterialThemeColors = true;
            if (this.materialThemeColorsTintList == null) {
                int[][] iArr = CHECKBOX_STATES;
                int length = iArr.length;
                int color = FileUtils.getColor(this, R.attr.colorControlActivated);
                int color2 = FileUtils.getColor(this, R.attr.colorError);
                int color3 = FileUtils.getColor(this, R.attr.colorSurface);
                int color4 = FileUtils.getColor(this, R.attr.colorOnSurface);
                this.materialThemeColorsTintList = new ColorStateList(iArr, new int[]{FileUtils.layer(color3, color2, 1.0f), FileUtils.layer(color3, color, 1.0f), FileUtils.layer(color3, color4, 0.54f), FileUtils.layer(color3, color4, 0.38f), FileUtils.layer(color3, color4, 0.38f)});
            }
            CompoundButtonCompat$Api21Impl.setButtonTintList(this, this.materialThemeColorsTintList);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected final int[] onCreateDrawableState(int i) {
        int[] iArr;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (this.checkedState == 2) {
            mergeDrawableStates(onCreateDrawableState, INDETERMINATE_STATE_SET);
        }
        if (this.errorShown) {
            mergeDrawableStates(onCreateDrawableState, ERROR_STATE_SET);
        }
        int i2 = 0;
        while (true) {
            int length = onCreateDrawableState.length;
            if (i2 < length) {
                int i3 = onCreateDrawableState[i2];
                if (i3 == 16842912) {
                    iArr = onCreateDrawableState;
                    break;
                }
                if (i3 == 0) {
                    iArr = (int[]) onCreateDrawableState.clone();
                    iArr[i2] = 16842912;
                    break;
                }
                i2++;
            } else {
                int[] copyOf = Arrays.copyOf(onCreateDrawableState, length + 1);
                copyOf[length] = 16842912;
                iArr = copyOf;
                break;
            }
        }
        this.currentStateChecked = iArr;
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected final void onDraw(Canvas canvas) {
        Drawable buttonDrawable;
        if (this.centerIfNoTextEnabled && TextUtils.isEmpty(getText()) && (buttonDrawable = CompoundButtonCompat$Api23Impl.getButtonDrawable(this)) != null) {
            int i = 1;
            if (true == DrawableUtils$OutlineCompatL.isLayoutRtl(this)) {
                i = -1;
            }
            int width = getWidth() - buttonDrawable.getIntrinsicWidth();
            int save = canvas.save();
            int i2 = (width / 2) * i;
            canvas.translate(i2, 0.0f);
            super.onDraw(canvas);
            canvas.restoreToCount(save);
            if (getBackground() != null) {
                Rect bounds = buttonDrawable.getBounds();
                DrawableCompat$Api21Impl.setHotspotBounds(getBackground(), bounds.left + i2, bounds.top, bounds.right + i2, bounds.bottom);
                return;
            }
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && this.errorShown) {
            accessibilityNodeInfo.setText(String.valueOf(accessibilityNodeInfo.getText()) + ", " + String.valueOf(this.errorAccessibilityLabel));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCheckedState(savedState.checkedState);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.checkedState = this.checkedState;
        return savedState;
    }

    @Override // android.support.v7.widget.AppCompatCheckBox, android.widget.CompoundButton
    public final void setButtonDrawable(int i) {
        setButtonDrawable(AppCompatDelegate.Api33Impl.getDrawable(getContext(), i));
    }

    @Override // android.widget.CompoundButton
    public final void setButtonTintList(ColorStateList colorStateList) {
        if (this.buttonTintList == colorStateList) {
            return;
        }
        this.buttonTintList = colorStateList;
        refreshButtonDrawable();
    }

    @Override // android.widget.CompoundButton
    public final void setButtonTintMode(PorterDuff.Mode mode) {
        AppCompatCompoundButtonHelper appCompatCompoundButtonHelper = this.mCompoundButtonHelper;
        if (appCompatCompoundButtonHelper != null) {
            appCompatCompoundButtonHelper.mButtonTintMode = mode;
            appCompatCompoundButtonHelper.mHasButtonTintMode = true;
            appCompatCompoundButtonHelper.applyButtonTint();
        }
        refreshButtonDrawable();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void setChecked(boolean z) {
        setCheckedState(z ? 1 : 0);
    }

    public final void setCheckedState(int i) {
        boolean z;
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.checkedState != i) {
            this.checkedState = i;
            if (i == 1) {
                z = true;
            } else {
                z = false;
            }
            super.setChecked(z);
            refreshDrawableState();
            setDefaultStateDescription();
            if (!this.broadcasting) {
                this.broadcasting = true;
                LinkedHashSet linkedHashSet = this.onCheckedStateChangedListeners;
                if (linkedHashSet != null) {
                    Iterator it = linkedHashSet.iterator();
                    while (it.hasNext()) {
                        ((OnCheckedStateChangedListener) it.next()).onCheckedStateChangedListener$ar$ds();
                    }
                }
                if (this.checkedState != 2 && (onCheckedChangeListener = this.onCheckedChangeListener) != null) {
                    onCheckedChangeListener.onCheckedChanged(this, isChecked());
                }
                AutofillManager m268m = BundleUtils$$ExternalSyntheticApiModelOutline0.m268m(getContext().getSystemService(BundleUtils$$ExternalSyntheticApiModelOutline0.m$2()));
                if (m268m != null) {
                    m268m.notifyValueChanged(this);
                }
                this.broadcasting = false;
            }
        }
    }

    @Override // android.widget.CompoundButton
    public final void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public final void setStateDescription(CharSequence charSequence) {
        this.customStateDescription = charSequence;
        if (charSequence == null) {
            setDefaultStateDescription();
        } else {
            super.setStateDescription(charSequence);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        setCheckedState(!isChecked() ? 1 : 0);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new ServiceDumpRequestCreator(3);
        int checkedState;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.checkedState = ((Integer) parcel.readValue(getClass().getClassLoader())).intValue();
        }

        public final String toString() {
            String str;
            String hexString = Integer.toHexString(System.identityHashCode(this));
            int i = this.checkedState;
            if (i != 1) {
                if (i != 2) {
                    str = "unchecked";
                } else {
                    str = "indeterminate";
                }
            } else {
                str = "checked";
            }
            return "MaterialCheckBox.SavedState{" + hexString + " CheckedState=" + str + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Integer.valueOf(this.checkedState));
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.support.v7.widget.AppCompatCheckBox, android.widget.CompoundButton
    public final void setButtonDrawable(Drawable drawable) {
        this.buttonDrawable = drawable;
        this.usingMaterialButtonDrawable = false;
        refreshButtonDrawable();
    }
}
