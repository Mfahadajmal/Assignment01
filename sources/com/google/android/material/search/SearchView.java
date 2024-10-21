package com.google.android.material.search;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SearchView extends FrameLayout implements CoordinatorLayout.AttachedBehavior {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Behavior extends CoordinatorLayout.Behavior<SearchView> {
        public Behavior() {
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ void onDependentViewChanged$ar$ds(CoordinatorLayout coordinatorLayout, View view, View view2) {
            throw null;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        throw null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public final CoordinatorLayout.Behavior getBehavior() {
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        throw null;
    }

    @Override // android.view.View
    protected final void onFinishInflate() {
        throw null;
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        throw null;
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        throw null;
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        throw null;
    }
}
