package com.google.android.accessibility.utils.braille;

import android.util.Range;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleUnicode {
    private static final Range BRAILLE_CHAR_RANGE;
    private static final Range OFFSET_RANGE;

    static {
        Range range = new Range((char) 10240, (char) 10495);
        BRAILLE_CHAR_RANGE = range;
        OFFSET_RANGE = new Range(0, Integer.valueOf(((Character) range.getUpper()).charValue() - ((Character) range.getLower()).charValue()));
    }

    public static boolean isBraille(char c) {
        return BRAILLE_CHAR_RANGE.contains((Range) Character.valueOf(c));
    }

    public static List toDotNumbers(char c) {
        int charValue = c - ((Character) BRAILLE_CHAR_RANGE.getLower()).charValue();
        Range range = OFFSET_RANGE;
        Integer valueOf = Integer.valueOf(charValue);
        if (range.contains((Range) valueOf)) {
            return toDotNumbers(BitSet.valueOf(new byte[]{(byte) charValue}));
        }
        throw new IllegalArgumentException(ContextDataProvider.lenientFormat("offset %s out of range %s", valueOf, range));
    }

    public static List toDotNumbers(BitSet bitSet) {
        ContextDataProvider.checkArgument(bitSet.length() <= 8, "Bitset length %s out of range", bitSet.length());
        ArrayList arrayList = new ArrayList();
        int nextSetBit = bitSet.nextSetBit(0);
        while (nextSetBit != -1) {
            int i = nextSetBit + 1;
            arrayList.add(Integer.valueOf(i));
            nextSetBit = bitSet.nextSetBit(i);
        }
        return arrayList;
    }
}
