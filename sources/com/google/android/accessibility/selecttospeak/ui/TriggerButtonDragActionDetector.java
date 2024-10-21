package com.google.android.accessibility.selecttospeak.ui;

import android.content.Context;
import android.view.View;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TriggerButtonDragActionDetector extends DragActionDetector {
    public boolean dragVertically;
    private final NodeMenuRuleCreator overlayCoordinatesSynchronizer$ar$class_merging;
    private final int[] overlayInitialCoordinates;
    private final float[] touchDownPosition;

    public TriggerButtonDragActionDetector(Context context, NodeMenuRuleCreator nodeMenuRuleCreator) {
        super(context);
        this.overlayInitialCoordinates = new int[2];
        this.touchDownPosition = new float[2];
        this.dragVertically = false;
        this.overlayCoordinatesSynchronizer$ar$class_merging = nodeMenuRuleCreator;
    }

    @Override // com.google.android.accessibility.selecttospeak.ui.DragActionDetector
    public final void onDrag$ar$ds(float f, float f2) {
        int i;
        if (this.dragVertically) {
            i = this.overlayInitialCoordinates[0];
        } else {
            i = (int) ((this.overlayInitialCoordinates[0] + f) - this.touchDownPosition[0]);
        }
        this.overlayCoordinatesSynchronizer$ar$class_merging.setPixelCoordinates(i, (int) ((this.overlayInitialCoordinates[1] + f2) - this.touchDownPosition[1]));
    }

    @Override // com.google.android.accessibility.selecttospeak.ui.DragActionDetector
    public void onStartDragging(View view, float f, float f2) {
        view.post(new ContextMenuDialog$$ExternalSyntheticLambda5(view, 20));
        this.overlayCoordinatesSynchronizer$ar$class_merging.getPixelCoordinates(this.overlayInitialCoordinates);
        float[] fArr = this.touchDownPosition;
        fArr[0] = f;
        fArr[1] = f2;
    }

    @Override // com.google.android.accessibility.selecttospeak.ui.DragActionDetector
    public void onStopDragging(View view, float f, float f2) {
    }
}
