package com.google.android.accessibility.accessibilitymenu.view;

import android.graphics.Rect;
import android.support.v7.widget.ActionBarContextView;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yMenuFooter {
    private View bottomListDivider;
    public final FloatingActionButton.ShadowDelegateImpl callBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final ActionBarContextView.AnonymousClass1 footerButtonClickListener$ar$class_merging = new ActionBarContextView.AnonymousClass1(this, 5);
    public ImageButton nextPageBtn;
    public ImageButton previousPageBtn;
    private View topListDivider;

    public A11yMenuFooter(ViewGroup viewGroup, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.callBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        ((ViewGroup) viewGroup.findViewById(R.id.footerlayout)).setVisibility(0);
        this.previousPageBtn = (ImageButton) viewGroup.findViewById(R.id.menu_prev_button);
        this.nextPageBtn = (ImageButton) viewGroup.findViewById(R.id.menu_next_button);
        this.topListDivider = viewGroup.findViewById(R.id.top_listDivider);
        this.bottomListDivider = viewGroup.findViewById(R.id.bottom_listDivider);
        setListener(this.previousPageBtn);
        setListener(this.nextPageBtn);
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardView.AnonymousClass1(this, viewGroup, 1));
    }

    private static int getHitRectHeight(View view) {
        Rect rect = new Rect();
        view.getHitRect(rect);
        return rect.height();
    }

    private final void setListener(View view) {
        if (view != null) {
            view.setOnClickListener(this.footerButtonClickListener$ar$class_merging);
        }
    }

    public final void expandBtnTouchArea(ImageButton imageButton, View view) {
        Rect rect = new Rect();
        imageButton.getHitRect(rect);
        rect.top -= getHitRectHeight(this.topListDivider);
        rect.bottom += getHitRectHeight(this.bottomListDivider);
        view.setTouchDelegate(new TouchDelegate(rect, imageButton));
    }
}
