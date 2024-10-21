package com.google.android.material.textfield;

import android.R;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    private final AccessibilityManager accessibilityManager;
    public ColorStateList dropDownBackgroundTint;
    public final ListPopupWindow modalListPopup;
    public final float popupElevation;
    private final int simpleItemLayout;
    public final int simpleItemSelectedColor;
    public final ColorStateList simpleItemSelectedRippleColor;
    private final Rect tempRect;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MaterialArrayAdapter extends ArrayAdapter {
        private ColorStateList pressedRippleColor;
        private ColorStateList selectedItemRippleOverlaidColor;

        public MaterialArrayAdapter(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            ColorStateList colorStateList;
            ColorStateList colorStateList2 = null;
            if (!hasSelectedRippleColor()) {
                colorStateList = null;
            } else {
                int[] iArr = {R.attr.state_pressed};
                colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(iArr, 0), 0});
            }
            this.pressedRippleColor = colorStateList;
            if (hasSelectedColor() && hasSelectedRippleColor()) {
                int[] iArr2 = {R.attr.state_hovered, -16842919};
                int[] iArr3 = {R.attr.state_selected, -16842919};
                colorStateList2 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{ColorUtils.compositeColors(MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(iArr3, 0), MaterialAutoCompleteTextView.this.simpleItemSelectedColor), ColorUtils.compositeColors(MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(iArr2, 0), MaterialAutoCompleteTextView.this.simpleItemSelectedColor), MaterialAutoCompleteTextView.this.simpleItemSelectedColor});
            }
            this.selectedItemRippleOverlaidColor = colorStateList2;
        }

        private final boolean hasSelectedColor() {
            if (MaterialAutoCompleteTextView.this.simpleItemSelectedColor != 0) {
                return true;
            }
            return false;
        }

        private final boolean hasSelectedRippleColor() {
            if (MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor != null) {
                return true;
            }
            return false;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            if (view2 instanceof TextView) {
                TextView textView = (TextView) view2;
                Drawable drawable = null;
                if (MaterialAutoCompleteTextView.this.getText().toString().contentEquals(textView.getText()) && hasSelectedColor()) {
                    ColorDrawable colorDrawable = new ColorDrawable(MaterialAutoCompleteTextView.this.simpleItemSelectedColor);
                    if (this.pressedRippleColor != null) {
                        DrawableCompat$Api21Impl.setTintList(colorDrawable, this.selectedItemRippleOverlaidColor);
                        drawable = new RippleDrawable(this.pressedRippleColor, colorDrawable, null);
                    } else {
                        drawable = colorDrawable;
                    }
                }
                textView.setBackground(drawable);
            }
            return view2;
        }
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, com.google.android.marvin.talkback.R.attr.autoCompleteTextViewStyle, 0), attributeSet, com.google.android.marvin.talkback.R.attr.autoCompleteTextViewStyle);
        this.tempRect = new Rect();
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialAutoCompleteTextView, com.google.android.marvin.talkback.R.attr.autoCompleteTextViewStyle, com.google.android.marvin.talkback.R.style.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        if (obtainStyledAttributes.hasValue(0) && obtainStyledAttributes.getInt(0, 0) == 0) {
            setKeyListener(null);
        }
        int resourceId = obtainStyledAttributes.getResourceId(3, com.google.android.marvin.talkback.R.layout.mtrl_auto_complete_simple_item);
        this.simpleItemLayout = resourceId;
        this.popupElevation = obtainStyledAttributes.getDimensionPixelOffset(1, com.google.android.marvin.talkback.R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        if (obtainStyledAttributes.hasValue(2)) {
            this.dropDownBackgroundTint = ColorStateList.valueOf(obtainStyledAttributes.getColor(2, 0));
        }
        this.simpleItemSelectedColor = obtainStyledAttributes.getColor(4, 0);
        this.simpleItemSelectedRippleColor = DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 5);
        this.accessibilityManager = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2, null, com.google.android.marvin.talkback.R.attr.listPopupWindowStyle);
        this.modalListPopup = listPopupWindow;
        listPopupWindow.setModal$ar$ds();
        listPopupWindow.mDropDownAnchorView = this;
        listPopupWindow.setInputMethodMode$ar$ds();
        listPopupWindow.setAdapter(getAdapter());
        listPopupWindow.mItemClickListener = new ContextMenuDialog.AnonymousClass1(this, 4);
        if (obtainStyledAttributes.hasValue(6)) {
            setAdapter(new MaterialArrayAdapter(getContext(), resourceId, getResources().getStringArray(obtainStyledAttributes.getResourceId(6, 0))));
        }
        obtainStyledAttributes.recycle();
    }

    private final TextInputLayout findTextInputLayoutAncestor() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    private final boolean isPopupRequired() {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            AccessibilityManager accessibilityManager2 = this.accessibilityManager;
            if (accessibilityManager2 != null && accessibilityManager2.isEnabled() && (enabledAccessibilityServiceList = this.accessibilityManager.getEnabledAccessibilityServiceList(16)) != null) {
                for (AccessibilityServiceInfo accessibilityServiceInfo : enabledAccessibilityServiceList) {
                    if (accessibilityServiceInfo.getSettingsActivityName() != null && accessibilityServiceInfo.getSettingsActivityName().contains("SwitchAccess")) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return true;
    }

    @Override // android.widget.AutoCompleteTextView
    public final void dismissDropDown() {
        if (isPopupRequired()) {
            this.modalListPopup.dismiss();
        } else {
            super.dismissDropDown();
        }
    }

    @Override // android.widget.TextView
    public final CharSequence getHint() {
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor != null && findTextInputLayoutAncestor.isProvidingHint) {
            return findTextInputLayoutAncestor.getHint();
        }
        return super.getHint();
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor != null && findTextInputLayoutAncestor.isProvidingHint && super.getHint() == null && DrawableUtils$OutlineCompatR.isMeizuDevice()) {
            setHint("");
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.modalListPopup.dismiss();
    }

    @Override // android.widget.TextView, android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
            int i4 = 0;
            if (adapter != null && findTextInputLayoutAncestor != null) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                int min = Math.min(adapter.getCount(), Math.max(0, this.modalListPopup.getSelectedItemPosition()) + 15);
                int max = Math.max(0, min - 15);
                View view = null;
                int i5 = 0;
                while (max < min) {
                    int itemViewType = adapter.getItemViewType(max);
                    if (itemViewType != i4) {
                        i3 = itemViewType;
                    } else {
                        i3 = i4;
                    }
                    if (itemViewType != i4) {
                        view = null;
                    }
                    view = adapter.getView(max, view, findTextInputLayoutAncestor);
                    if (view.getLayoutParams() == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    }
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    i5 = Math.max(i5, view.getMeasuredWidth());
                    max++;
                    i4 = i3;
                }
                Drawable background = this.modalListPopup.getBackground();
                if (background != null) {
                    background.getPadding(this.tempRect);
                    Rect rect = this.tempRect;
                    i5 += rect.left + rect.right;
                }
                i4 = i5 + findTextInputLayoutAncestor.endLayout.endIconView.getMeasuredWidth();
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, i4), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z) {
        if (isPopupRequired()) {
            return;
        }
        super.onWindowFocusChanged(z);
    }

    @Override // android.widget.AutoCompleteTextView
    public final void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.modalListPopup.setAdapter(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public final void setDropDownBackgroundDrawable(Drawable drawable) {
        super.setDropDownBackgroundDrawable(drawable);
        ListPopupWindow listPopupWindow = this.modalListPopup;
        if (listPopupWindow != null) {
            listPopupWindow.setBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.modalListPopup.mItemSelectedListener = getOnItemSelectedListener();
    }

    @Override // android.widget.TextView
    public final void setRawInputType(int i) {
        super.setRawInputType(i);
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor != null) {
            findTextInputLayoutAncestor.updateEditTextBoxBackgroundIfNeeded();
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void showDropDown() {
        if (isPopupRequired()) {
            this.modalListPopup.show();
        } else {
            super.showDropDown();
        }
    }
}
