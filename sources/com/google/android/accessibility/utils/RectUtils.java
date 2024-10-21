package com.google.android.accessibility.utils;

import android.graphics.Rect;
import android.support.v7.widget.GapWorker;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RectUtils {
    public static final Comparator RECT_POSITION_COMPARATOR = new GapWorker.AnonymousClass1(9);

    public static void adjustRectToAvoidIntersection(Rect rect, Rect rect2) {
        int width;
        rect.sort();
        rect2.sort();
        if (!rect.contains(rect2) && Rect.intersects(rect, rect2)) {
            Rect[] rectArr = {new Rect(rect.left, rect.top, rect2.left, rect.bottom), new Rect(rect.left, rect.top, rect.right, rect2.top), new Rect(rect2.right, rect.top, rect.right, rect.bottom), new Rect(rect.left, rect2.bottom, rect.right, rect.bottom)};
            int i = -1;
            int i2 = 0;
            for (int i3 = 0; i3 < 4; i3++) {
                Rect rect3 = rectArr[i3];
                if (rect3.left <= rect3.right && rect3.top <= rect3.bottom && Rect.intersects(rectArr[i3], rect) && (width = rectArr[i3].width() * rectArr[i3].height()) > i2) {
                    i2 = width;
                    i = i3;
                }
            }
            if (i2 <= 0) {
                rect.setEmpty();
            } else {
                rect.set(rectArr[i]);
            }
        }
    }

    public static boolean isEmpty(Rect rect) {
        if (rect.left != rect.right && rect.top != rect.bottom) {
            return false;
        }
        return true;
    }
}
