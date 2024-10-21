package com.google.android.accessibility.talkback;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class GranularityIterator$AbstractTextSegmentIterator implements GranularityIterator$TextSegmentIterator {
    public String iteratorText;
    private final int[] segment = new int[2];

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int[] getRange(int i, int i2) {
        if (i >= 0 && i2 >= 0 && i != i2) {
            int[] iArr = this.segment;
            iArr[0] = i;
            iArr[1] = i2;
            return iArr;
        }
        return null;
    }
}
