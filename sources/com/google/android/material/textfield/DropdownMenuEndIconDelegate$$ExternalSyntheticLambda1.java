package com.google.android.material.textfield;

import android.support.v7.widget.ListPopupWindow;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1 implements View.OnTouchListener {
    public final /* synthetic */ Object DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0;
    private final /* synthetic */ int switching_field;

    public DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(ListPopupWindow listPopupWindow, int i) {
        this.switching_field = i;
        this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0 = listPopupWindow;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.switching_field != 0) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0) {
                PopupWindow popupWindow = ((ListPopupWindow) this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0).mPopup;
                if (popupWindow != null && popupWindow.isShowing() && x >= 0 && x < ((ListPopupWindow) this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0).mPopup.getWidth() && y >= 0 && y < ((ListPopupWindow) this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0).mPopup.getHeight()) {
                    ListPopupWindow listPopupWindow = (ListPopupWindow) this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0;
                    listPopupWindow.mHandler.postDelayed(listPopupWindow.mResizePopupRunnable$ar$class_merging, 250L);
                }
            } else if (action == 1) {
                ListPopupWindow listPopupWindow2 = (ListPopupWindow) this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0;
                listPopupWindow2.mHandler.removeCallbacks(listPopupWindow2.mResizePopupRunnable$ar$class_merging);
            }
            return false;
        }
        if (motionEvent.getAction() == 1) {
            DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = (DropdownMenuEndIconDelegate) this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0;
            if (dropdownMenuEndIconDelegate.isDropdownPopupActive()) {
                dropdownMenuEndIconDelegate.dropdownPopupDirty = false;
            }
            dropdownMenuEndIconDelegate.showHideDropdown();
            dropdownMenuEndIconDelegate.updateDropdownPopupDirty();
        }
        return false;
    }

    public /* synthetic */ DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate, int i) {
        this.switching_field = i;
        this.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1$ar$f$0 = dropdownMenuEndIconDelegate;
    }
}
