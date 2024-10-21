package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.shape.EdgeTreatment;

/* compiled from: PG */
/* loaded from: classes.dex */
final class RightSheetDelegate extends SheetDelegate {
    final SideSheetBehavior sheetBehavior;

    public RightSheetDelegate(SideSheetBehavior sideSheetBehavior) {
        this.sheetBehavior = sideSheetBehavior;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int calculateInnerMargin(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.rightMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final float calculateSlideOffset(int i) {
        float hiddenOffset = getHiddenOffset();
        return (hiddenOffset - i) / (hiddenOffset - getExpandedOffset());
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getExpandedOffset() {
        int hiddenOffset = getHiddenOffset();
        SideSheetBehavior sideSheetBehavior = this.sheetBehavior;
        return Math.max(0, (hiddenOffset - sideSheetBehavior.childWidth) - sideSheetBehavior.innerMargin);
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getHiddenOffset() {
        return this.sheetBehavior.parentWidth;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getMaxViewPositionHorizontal() {
        return this.sheetBehavior.parentWidth;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getMinViewPositionHorizontal() {
        return getExpandedOffset();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getOuterEdge(View view) {
        return view.getLeft() - this.sheetBehavior.innerMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getParentInnerEdge(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getRight();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getSheetEdge() {
        return 0;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final boolean isExpandingOutwards(float f) {
        if (f < 0.0f) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final boolean isReleasedCloseToInnerEdge(View view) {
        if (view.getLeft() > (getHiddenOffset() + getExpandedOffset()) / 2) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final boolean isSwipeSignificant(float f, float f2) {
        if (EdgeTreatment.isSwipeMostlyHorizontal(f, f2) && Math.abs(f) > 500.0f) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final boolean shouldHide(View view, float f) {
        if (Math.abs(view.getRight() + (f * this.sheetBehavior.hideFriction)) > 0.5f) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
        int i3 = this.sheetBehavior.parentWidth;
        if (i <= i3) {
            marginLayoutParams.rightMargin = i3 - i;
        }
    }
}
