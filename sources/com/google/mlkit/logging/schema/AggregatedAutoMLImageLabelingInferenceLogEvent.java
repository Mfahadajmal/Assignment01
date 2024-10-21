package com.google.mlkit.logging.schema;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.vision.text.internal.client.BoundingBoxParcel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AggregatedAutoMLImageLabelingInferenceLogEvent {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogEventKey {
        public final boolean equals(Object obj) {
            throw null;
        }

        public final int hashCode() {
            throw null;
        }
    }

    public static Rect computeBoundingBoxFromCornerPoints(List list) {
        Iterator it = list.iterator();
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        while (it.hasNext()) {
            Point point = (Point) it.next();
            i2 = Math.min(i2, point.x);
            i = Math.max(i, point.x);
            i3 = Math.min(i3, point.y);
            i4 = Math.max(i4, point.y);
        }
        return new Rect(i2, i3, i, i4);
    }

    public static List computeCornerPointsFromBoundingBox(BoundingBoxParcel boundingBoxParcel) {
        double sin = Math.sin(Math.toRadians(boundingBoxParcel.angleDegrees));
        double cos = Math.cos(Math.toRadians(boundingBoxParcel.angleDegrees));
        double d = boundingBoxParcel.left;
        double d2 = boundingBoxParcel.width;
        Point[] pointArr = {new Point(boundingBoxParcel.left, boundingBoxParcel.top), new Point((int) (d + (d2 * cos)), (int) (boundingBoxParcel.top + (d2 * sin))), new Point((int) (r5.x - (boundingBoxParcel.height * sin)), (int) (pointArr[1].y + (boundingBoxParcel.height * cos))), new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y))};
        return Arrays.asList(pointArr);
    }
}
