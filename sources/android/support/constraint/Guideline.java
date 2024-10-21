package android.support.constraint;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Guideline extends View {
    public Guideline(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
    }
}
