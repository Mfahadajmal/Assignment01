package com.google.android.accessibility.utils.coordinate;

import android.graphics.Rect;
import android.graphics.RectF;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoordinateConverter {
    public static final CoordinateConverter INSTANCE = new CoordinateConverter();

    private CoordinateConverter() {
    }

    public static final void convert$ar$ds(Rect rect, Rect rect2, Rect rect3) {
        float width = rect.width();
        float height = rect.height();
        RectF rectF = new RectF((rect3.left - rect.left) / width, (rect3.top - rect.top) / height, (rect3.right - rect.left) / width, (rect3.bottom - rect.top) / height);
        rect3.set(((int) (rectF.left * rect2.width())) + rect2.left, ((int) (rectF.top * rect2.height())) + rect2.top, ((int) (rectF.right * rect2.width())) + rect2.left, ((int) (rectF.bottom * rect2.height())) + rect2.top);
    }
}
