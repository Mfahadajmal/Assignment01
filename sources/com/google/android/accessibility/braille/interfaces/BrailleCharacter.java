package com.google.android.accessibility.braille.interfaces;

import android.util.Range;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleCharacter {
    private static final Range DOT_RANGE;
    public final BitSet dotNumbers;
    public static final BrailleCharacter EMPTY_CELL = new BrailleCharacter();
    public static final BrailleCharacter FULL_CELL = new BrailleCharacter("12345678");
    public static final BrailleCharacter DOT1 = new BrailleCharacter("1");
    public static final BrailleCharacter DOT2 = new BrailleCharacter("2");
    public static final BrailleCharacter DOT3 = new BrailleCharacter("3");
    public static final BrailleCharacter DOT4 = new BrailleCharacter("4");
    public static final BrailleCharacter DOT5 = new BrailleCharacter("5");
    public static final BrailleCharacter DOT6 = new BrailleCharacter("6");
    public static final BrailleCharacter DOT7 = new BrailleCharacter("7");
    public static final BrailleCharacter DOT8 = new BrailleCharacter("8");

    static {
        new BrailleCharacter("12");
        DOT_RANGE = new Range(1, 8);
    }

    public BrailleCharacter() {
        this.dotNumbers = new BitSet(8);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BrailleCharacter)) {
            return false;
        }
        return this.dotNumbers.equals(((BrailleCharacter) obj).dotNumbers);
    }

    public final int getOnCount() {
        return this.dotNumbers.cardinality();
    }

    public final int hashCode() {
        return this.dotNumbers.hashCode();
    }

    public final boolean isEmpty() {
        return this.dotNumbers.isEmpty();
    }

    public final byte toByte() {
        if (isEmpty()) {
            return (byte) 0;
        }
        return this.dotNumbers.toByteArray()[0];
    }

    public final int toInt() {
        return toByte() & 255;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < this.dotNumbers.length()) {
            int i2 = i + 1;
            if (this.dotNumbers.get(i)) {
                sb.append(i2);
            }
            i = i2;
        }
        return sb.toString();
    }

    public final BrailleCharacter union(BrailleCharacter brailleCharacter) {
        BitSet bitSet = (BitSet) this.dotNumbers.clone();
        bitSet.or(brailleCharacter.dotNumbers);
        return new BrailleCharacter(bitSet);
    }

    public BrailleCharacter(byte b) {
        this.dotNumbers = BitSet.valueOf(new byte[]{b});
    }

    public BrailleCharacter(String str) {
        this();
        for (char c : str.toCharArray()) {
            int digit = Character.digit(c, 9);
            ContextDataProvider.checkArgument(digit != -1, "dot %s out of range", c);
            this.dotNumbers.set(digit - 1);
        }
    }

    public BrailleCharacter(BitSet bitSet) {
        ContextDataProvider.checkArgument(bitSet.length() <= 8, "Bitset length %s out of range", bitSet.length());
        this.dotNumbers = bitSet;
    }

    public BrailleCharacter(Collection collection) {
        this();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Range range = DOT_RANGE;
            ContextDataProvider.checkArgument(range.contains((Range) num), "dot %s out of range %s", num, range);
            this.dotNumbers.set(num.intValue() - 1);
        }
    }

    public BrailleCharacter(Integer... numArr) {
        this(Arrays.asList(numArr));
    }
}
