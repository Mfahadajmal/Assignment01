package com.google.android.libraries.accessibility.utils.screenunderstanding;

import android.graphics.Rect;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AnnotationComparator implements Comparator {
    private final Rect target;

    public AnnotationComparator(Rect rect) {
        this.target = new Rect(rect);
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        return Float.compare(SpannableUtils$NonCopyableTextSpan.calculate(((Annotation) obj2).getBounds(), this.target), SpannableUtils$NonCopyableTextSpan.calculate(((Annotation) obj).getBounds(), this.target));
    }
}
