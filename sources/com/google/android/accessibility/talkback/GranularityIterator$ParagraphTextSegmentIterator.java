package com.google.android.accessibility.talkback;

/* compiled from: PG */
/* loaded from: classes.dex */
final class GranularityIterator$ParagraphTextSegmentIterator extends GranularityIterator$AbstractTextSegmentIterator {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LazyHolder {
        static final GranularityIterator$ParagraphTextSegmentIterator PARAGRAPH_TEXT_SEGMENT_ITERATOR = new GranularityIterator$ParagraphTextSegmentIterator();
    }

    private final boolean isEndBoundary(int i) {
        if (i <= 0 || this.iteratorText.charAt(i - 1) == '\n') {
            return false;
        }
        if (i != this.iteratorText.length() && this.iteratorText.charAt(i) != '\n') {
            return false;
        }
        return true;
    }

    private final boolean isStartBoundary(int i) {
        if (this.iteratorText.charAt(i) == '\n') {
            return false;
        }
        if (i != 0 && this.iteratorText.charAt(i - 1) != '\n') {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        r1 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
    
        if (r1 >= r0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002d, code lost:
    
        if (isEndBoundary(r1) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002f, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0036, code lost:
    
        return getRange(r4, r1);
     */
    @Override // com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int[] following(int r4) {
        /*
            r3 = this;
            java.lang.String r0 = r3.iteratorText
            int r0 = r0.length()
            if (r0 > 0) goto L9
            goto L37
        L9:
            if (r4 >= r0) goto L37
            if (r4 >= 0) goto Le
            r4 = 0
        Le:
            if (r4 >= r0) goto L23
            java.lang.String r1 = r3.iteratorText
            char r1 = r1.charAt(r4)
            r2 = 10
            if (r1 != r2) goto L23
            boolean r1 = r3.isStartBoundary(r4)
            if (r1 != 0) goto L23
            int r4 = r4 + 1
            goto Le
        L23:
            if (r4 >= r0) goto L37
            int r1 = r4 + 1
        L27:
            if (r1 >= r0) goto L32
            boolean r2 = r3.isEndBoundary(r1)
            if (r2 != 0) goto L32
            int r1 = r1 + 1
            goto L27
        L32:
            int[] r4 = r3.getRange(r4, r1)
            return r4
        L37:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.GranularityIterator$ParagraphTextSegmentIterator.following(int):int[]");
    }

    @Override // com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    public final int[] preceding(int i) {
        int length = this.iteratorText.length();
        if (length > 0 && i > 0) {
            if (i > length) {
                i = length;
            }
            while (i > 0) {
                int i2 = i - 1;
                if (this.iteratorText.charAt(i2) != '\n' || isEndBoundary(i)) {
                    break;
                }
                i = i2;
            }
            if (i > 0) {
                int i3 = i - 1;
                while (i3 > 0 && !isStartBoundary(i3)) {
                    i3--;
                }
                return getRange(i3, i);
            }
            return null;
        }
        return null;
    }
}
