package com.google.android.accessibility.talkback.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.core.graphics.BlendModeCompat;
import androidx.core.graphics.PaintCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.widget.SimpleOverlay;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HighlightOverlay extends SimpleOverlay {
    public static View highlightView;
    public HashSet refocusNodePath;
    public HashMap skippedNodes;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MultipleHighlightView extends View {
        private final Paint borderPaint;
        private final Paint refocusPaint;
        private final Paint skippedNodePaint;

        public MultipleHighlightView(Context context) {
            super(context);
            Paint paint = new Paint();
            this.refocusPaint = paint;
            Paint paint2 = new Paint();
            this.skippedNodePaint = paint2;
            Paint paint3 = new Paint();
            this.borderPaint = paint3;
            paint.setColor(-16711936);
            PaintCompat.setBlendMode$ar$ds(paint, BlendModeCompat.COLOR);
            paint2.setStyle(Paint.Style.FILL);
            PaintCompat.setBlendMode$ar$ds(paint2, BlendModeCompat.OVERLAY);
            paint3.setColor(-16777216);
            paint3.setStyle(Paint.Style.STROKE);
            PaintCompat.setBlendMode$ar$ds(paint3, BlendModeCompat.DARKEN);
            paint3.setStrokeWidth(context.getResources().getDimensionPixelSize(R.dimen.highlight_overlay_border));
        }

        private final void drawRectangle(Canvas canvas, Rect rect, Paint paint) {
            int[] iArr = {0, 0};
            HighlightOverlay.highlightView.getLocationOnScreen(iArr);
            int i = -iArr[0];
            int i2 = -iArr[1];
            Rect rect2 = new Rect(rect.left + i, rect.top + i2, rect.right + i, rect.bottom + i2);
            canvas.drawRect(rect2, paint);
            canvas.drawRect(rect2, this.borderPaint);
        }

        private final void processUnfocusableNodes(Integer num, Canvas canvas) {
            new ArrayList();
            ArrayList arrayList = (ArrayList) HighlightOverlay.this.skippedNodes.get(num);
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) arrayList.get(i);
                    Rect rect = new Rect();
                    accessibilityNodeInfoCompat.getBoundsInScreen(rect);
                    drawRectangle(canvas, rect, this.skippedNodePaint);
                }
            }
        }

        @Override // android.view.View
        public final void onDraw(Canvas canvas) {
            if (HighlightOverlay.this.skippedNodes != null) {
                this.skippedNodePaint.setColor(-65536);
                processUnfocusableNodes(0, canvas);
                processUnfocusableNodes(1, canvas);
                processUnfocusableNodes(3, canvas);
                processUnfocusableNodes(2, canvas);
            }
            HashSet hashSet = HighlightOverlay.this.refocusNodePath;
            if (hashSet != null) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    AccessibilityNode accessibilityNode = (AccessibilityNode) it.next();
                    Rect rect = new Rect();
                    accessibilityNode.getBoundsInScreen(rect);
                    drawRectangle(canvas, rect, this.refocusPaint);
                }
            }
        }
    }

    public HighlightOverlay(Context context) {
        super(context);
        this.skippedNodes = null;
        this.refocusNodePath = null;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2032;
        layoutParams.format = -3;
        layoutParams.flags |= 256;
        layoutParams.flags |= 16;
        layoutParams.flags |= 8;
        layoutParams.width = -1;
        layoutParams.height = -1;
        FrameLayout frameLayout = new FrameLayout(context);
        highlightView = new MultipleHighlightView(context);
        highlightView.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
        highlightView.setVisibility(4);
        highlightView.setAlpha(0.25f);
        frameLayout.addView(highlightView);
        setContentView(frameLayout);
        setParams(layoutParams);
    }

    public final void clearHighlight() {
        highlightView.setVisibility(4);
        hide();
    }
}
