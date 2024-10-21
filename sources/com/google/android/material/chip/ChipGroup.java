package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.internal.CheckableGroup;
import com.google.android.material.internal.FlowLayout;
import com.google.android.material.internal.MaterialCheckable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import io.grpc.internal.RetryingNameResolver;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ChipGroup extends FlowLayout {
    public final CheckableGroup checkableGroup;
    private int chipSpacingHorizontal;
    private int chipSpacingVertical;
    private final int defaultCheckedId;
    private final PassThroughHierarchyChangeListener passThroughListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams() {
            super(-2, -2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        public ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        public PassThroughHierarchyChangeListener() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewAdded(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    view2.setId(View.generateViewId());
                }
                ChipGroup chipGroup = ChipGroup.this;
                Chip chip = (Chip) view2;
                Integer valueOf = Integer.valueOf(chip.getId());
                CheckableGroup checkableGroup = chipGroup.checkableGroup;
                checkableGroup.checkables.put(valueOf, chip);
                if (chip.isChecked()) {
                    checkableGroup.checkInternal(chip);
                }
                chip.setInternalOnCheckedChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(new RetryingNameResolver.ResolutionResultListener(checkableGroup));
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewRemoved(View view, View view2) {
            ChipGroup chipGroup = ChipGroup.this;
            if (view == chipGroup && (view2 instanceof Chip)) {
                CheckableGroup checkableGroup = chipGroup.checkableGroup;
                Chip chip = (Chip) view2;
                chip.setInternalOnCheckedChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null);
                checkableGroup.checkables.remove(Integer.valueOf(chip.getId()));
                checkableGroup.checkedIds.remove(Integer.valueOf(chip.getId()));
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams)) {
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public final boolean isChildVisible(int i) {
        if (getChildAt(i).getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public final boolean isSingleSelection() {
        return this.checkableGroup.singleSelection;
    }

    @Override // android.view.View
    protected final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.defaultCheckedId;
        if (i != -1) {
            CheckableGroup checkableGroup = this.checkableGroup;
            MaterialCheckable materialCheckable = (MaterialCheckable) checkableGroup.checkables.get(Integer.valueOf(i));
            if (materialCheckable != null && checkableGroup.checkInternal(materialCheckable)) {
                checkableGroup.onCheckedStateChanged();
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
        if (isSingleLine()) {
            i = 0;
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                if ((getChildAt(i2) instanceof Chip) && isChildVisible(i2)) {
                    i++;
                }
            }
        } else {
            i = -1;
        }
        int i3 = this.rowCount;
        int i4 = 1;
        if (true != isSingleSelection()) {
            i4 = 2;
        }
        accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$ar$class_merging(i3, i, i4));
    }

    @Override // android.view.ViewGroup
    public final void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.passThroughListener.onHierarchyChangeListener = onHierarchyChangeListener;
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.chipGroupStyle);
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ChipGroup(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, R.style.Widget_MaterialComponents_ChipGroup), attributeSet, i);
        CheckableGroup checkableGroup = new CheckableGroup();
        this.checkableGroup = checkableGroup;
        this.passThroughListener = new PassThroughHierarchyChangeListener();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, R$styleable.ChipGroup, i, R.style.Widget_MaterialComponents_ChipGroup, new int[0]);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(2, dimensionPixelOffset);
        if (this.chipSpacingHorizontal != dimensionPixelOffset2) {
            this.chipSpacingHorizontal = dimensionPixelOffset2;
            this.itemSpacing = dimensionPixelOffset2;
            requestLayout();
        }
        int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(3, dimensionPixelOffset);
        if (this.chipSpacingVertical != dimensionPixelOffset3) {
            this.chipSpacingVertical = dimensionPixelOffset3;
            this.lineSpacing = dimensionPixelOffset3;
            requestLayout();
        }
        setSingleLine(obtainStyledAttributes.getBoolean(5, false));
        boolean z = obtainStyledAttributes.getBoolean(6, false);
        if (checkableGroup.singleSelection != z) {
            checkableGroup.singleSelection = z;
            boolean z2 = !checkableGroup.checkedIds.isEmpty();
            Iterator it = checkableGroup.checkables.values().iterator();
            while (it.hasNext()) {
                checkableGroup.uncheckInternal((MaterialCheckable) it.next(), false);
            }
            if (z2) {
                checkableGroup.onCheckedStateChanged();
            }
        }
        this.checkableGroup.selectionRequired = obtainStyledAttributes.getBoolean(4, false);
        this.defaultCheckedId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        this.checkableGroup.onCheckedStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging = new FileUtils();
        super.setOnHierarchyChangeListener(this.passThroughListener);
        setImportantForAccessibility(1);
    }
}
