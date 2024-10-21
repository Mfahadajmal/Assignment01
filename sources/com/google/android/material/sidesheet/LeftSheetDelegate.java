package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.shape.EdgeTreatment;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LeftSheetDelegate extends SheetDelegate {
    final SideSheetBehavior sheetBehavior;

    public LeftSheetDelegate(SideSheetBehavior sideSheetBehavior) {
        this.sheetBehavior = sideSheetBehavior;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int calculateInnerMargin(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.leftMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final float calculateSlideOffset(int i) {
        float hiddenOffset = getHiddenOffset();
        return (i - hiddenOffset) / (getExpandedOffset() - hiddenOffset);
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getExpandedOffset() {
        SideSheetBehavior sideSheetBehavior = this.sheetBehavior;
        return Math.max(0, sideSheetBehavior.parentInnerEdge + sideSheetBehavior.innerMargin);
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getHiddenOffset() {
        SideSheetBehavior sideSheetBehavior = this.sheetBehavior;
        return (-sideSheetBehavior.childWidth) - sideSheetBehavior.innerMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getMaxViewPositionHorizontal() {
        return this.sheetBehavior.innerMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getMinViewPositionHorizontal() {
        return -this.sheetBehavior.childWidth;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getOuterEdge(View view) {
        return view.getRight() + this.sheetBehavior.innerMargin;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getParentInnerEdge(CoordinatorLayout coordinatorLayout) {
        return coordinatorLayout.getLeft();
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final int getSheetEdge() {
        return 1;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final boolean isExpandingOutwards(float f) {
        if (f > 0.0f) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final boolean isReleasedCloseToInnerEdge(View view) {
        if (view.getRight() < (getExpandedOffset() - getHiddenOffset()) / 2) {
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
        if (Math.abs(view.getLeft() + (f * this.sheetBehavior.hideFriction)) > 0.5f) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.material.sidesheet.SheetDelegate
    public final void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
        if (i <= this.sheetBehavior.parentWidth) {
            marginLayoutParams.leftMargin = i2;
        }
    }
}
