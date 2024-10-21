package com.google.android.material.datepicker;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class SmoothCalendarLayoutManager extends LinearLayoutManager {
    public SmoothCalendarLayoutManager(Context context, int i) {
        super(i);
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition$ar$ds(RecyclerView recyclerView, int i) {
        RecyclerView.SmoothScroller smoothScroller = new RecyclerView.SmoothScroller(recyclerView.getContext()) { // from class: com.google.android.material.datepicker.SmoothCalendarLayoutManager.1
            @Override // android.support.v7.widget.RecyclerView.SmoothScroller
            protected final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 100.0f / displayMetrics.densityDpi;
            }
        };
        smoothScroller.mTargetPosition = i;
        startSmoothScroll(smoothScroller);
    }
}
