package com.google.android.accessibility.selecttospeak.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewConfiguration;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DragActionDetector implements View.OnTouchListener {
    private final int scaledTouchSlop;
    private float touchDownX;
    private float touchDownY;
    public boolean isEnabled = true;
    private boolean isTouching = false;
    private boolean isDragging = false;

    public DragActionDetector(Context context) {
        this.scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public final void cancelDragDetection() {
        this.isTouching = false;
        this.isDragging = false;
    }

    public abstract void onDrag$ar$ds(float f, float f2);

    public abstract void onStartDragging(View view, float f, float f2);

    public abstract void onStopDragging(View view, float f, float f2);

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
    
        if (java.lang.Math.abs(r2 - r6.touchDownY) > r6.scaledTouchSlop) goto L21;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            boolean r0 = r6.isEnabled
            r1 = 0
            if (r0 != 0) goto L6
            goto L64
        L6:
            float r0 = r8.getRawX()
            float r2 = r8.getRawY()
            int r3 = r8.getAction()
            r4 = 1
            if (r3 == 0) goto L65
            if (r3 == r4) goto L58
            r5 = 2
            if (r3 == r5) goto L1b
            goto L64
        L1b:
            boolean r3 = r6.isTouching
            if (r3 == 0) goto L64
            boolean r3 = r6.isDragging
            if (r3 == 0) goto L27
            r6.onDrag$ar$ds(r0, r2)
            return r4
        L27:
            float r3 = r6.touchDownX
            float r0 = r0 - r3
            int r3 = r6.scaledTouchSlop
            float r0 = java.lang.Math.abs(r0)
            float r3 = (float) r3
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 > 0) goto L43
            float r0 = r6.touchDownY
            float r2 = r2 - r0
            int r0 = r6.scaledTouchSlop
            float r2 = java.lang.Math.abs(r2)
            float r0 = (float) r0
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 <= 0) goto L64
        L43:
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r8)
            r0 = 3
            r8.setAction(r0)
            r7.dispatchTouchEvent(r8)
            r6.isDragging = r4
            float r8 = r6.touchDownX
            float r0 = r6.touchDownY
            r6.onStartDragging(r7, r8, r0)
            return r4
        L58:
            r6.isTouching = r1
            boolean r8 = r6.isDragging
            if (r8 == 0) goto L64
            r6.isDragging = r1
            r6.onStopDragging(r7, r0, r2)
            return r4
        L64:
            return r1
        L65:
            r6.isDragging = r1
            r6.isTouching = r4
            r6.touchDownX = r0
            r6.touchDownY = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.ui.DragActionDetector.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
