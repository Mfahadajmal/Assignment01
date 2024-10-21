package com.google.mlkit.logging.schema;

import kotlin.text.Charsets;
import okio.SegmentedByteString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ToxicityDetectionLoadEvent {
    public static final byte[] asUtf8ToByteArray(String str) {
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        bytes.getClass();
        return bytes;
    }

    public static final int segment(SegmentedByteString segmentedByteString, int i) {
        int i2;
        int length = segmentedByteString.segments.length - 1;
        int i3 = 0;
        while (true) {
            if (i3 <= length) {
                int i4 = i + 1;
                i2 = (i3 + length) >>> 1;
                int i5 = segmentedByteString.directory[i2];
                if (i5 < i4) {
                    i3 = i2 + 1;
                } else {
                    if (i5 <= i4) {
                        break;
                    }
                    length = i2 - 1;
                }
            } else {
                i2 = (-i3) - 1;
                break;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        return ~i2;
    }

    public static final String toUtf8String(byte[] bArr) {
        bArr.getClass();
        return new String(bArr, Charsets.UTF_8);
    }
}
