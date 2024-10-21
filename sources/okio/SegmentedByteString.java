package okio;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.ToxicityDetectionCreateEvent;
import com.google.mlkit.logging.schema.ToxicityDetectionLoadEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SegmentedByteString extends ByteString {
    public final transient int[] directory;
    public final transient byte[][] segments;

    public SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.EMPTY.data);
        this.segments = bArr;
        this.directory = iArr;
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    private final Object writeReplace() {
        return toByteString();
    }

    @Override // okio.ByteString
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.getSize$third_party_java_src_okio_okio_jvm() == getSize$third_party_java_src_okio_okio_jvm() && rangeEquals$ar$ds(byteString, getSize$third_party_java_src_okio_okio_jvm())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public final int getSize$third_party_java_src_okio_okio_jvm() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public final int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            byte[][] bArr = this.segments;
            int i2 = 0;
            int i3 = 1;
            int i4 = 0;
            while (true) {
                int length = bArr.length;
                if (i2 < length) {
                    int[] iArr = this.directory;
                    byte[][] bArr2 = this.segments;
                    int i5 = iArr[length + i2];
                    int i6 = iArr[i2];
                    int i7 = (i6 - i4) + i5;
                    byte[] bArr3 = bArr2[i2];
                    while (i5 < i7) {
                        i3 = (i3 * 31) + bArr3[i5];
                        i5++;
                    }
                    i2++;
                    i4 = i6;
                } else {
                    this.hashCode = i3;
                    return i3;
                }
            }
        } else {
            return i;
        }
    }

    @Override // okio.ByteString
    public final String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public final byte[] internalArray$third_party_java_src_okio_okio_jvm() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public final byte internalGet$third_party_java_src_okio_okio_jvm(int i) {
        int i2;
        ToxicityDetectionCreateEvent.checkOffsetAndCount(this.directory[this.segments.length - 1], i, 1L);
        int segment = ToxicityDetectionLoadEvent.segment(this, i);
        if (segment == 0) {
            i2 = 0;
        } else {
            i2 = this.directory[segment - 1];
        }
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[segment][(i - i2) + iArr[bArr.length + segment]];
    }

    @Override // okio.ByteString
    public final boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        int i4;
        bArr.getClass();
        if (i < 0 || i > getSize$third_party_java_src_okio_okio_jvm() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i5 = i3 + i;
        int segment = ToxicityDetectionLoadEvent.segment(this, i);
        while (i < i5) {
            if (segment == 0) {
                segment = 0;
                i4 = 0;
            } else {
                i4 = this.directory[segment - 1];
            }
            int[] iArr = this.directory;
            int i6 = iArr[segment] - i4;
            int i7 = iArr[this.segments.length + segment];
            int min = Math.min(i5, i6 + i4) - i;
            if (!ToxicityDetectionCreateEvent.arrayRangeEquals(this.segments[segment], i7 + (i - i4), bArr, i2, min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public final boolean rangeEquals$ar$ds(ByteString byteString, int i) {
        int i2;
        byteString.getClass();
        if (getSize$third_party_java_src_okio_okio_jvm() - i < 0) {
            return false;
        }
        int segment = ToxicityDetectionLoadEvent.segment(this, 0);
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (segment == 0) {
                segment = 0;
                i2 = 0;
            } else {
                i2 = this.directory[segment - 1];
            }
            int[] iArr = this.directory;
            int i5 = iArr[segment] - i2;
            int i6 = iArr[this.segments.length + segment];
            int min = Math.min(i, i5 + i2) - i3;
            if (!byteString.rangeEquals(i4, this.segments[segment], i6 + (i3 - i2), min)) {
                return false;
            }
            i4 += min;
            i3 += min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public final ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public final byte[] toByteArray() {
        byte[] bArr = new byte[getSize$third_party_java_src_okio_okio_jvm()];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[][] bArr2 = this.segments;
            int length = bArr2.length;
            if (i < length) {
                int[] iArr = this.directory;
                int i4 = iArr[length + i];
                int i5 = iArr[i];
                int i6 = i5 - i2;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds(bArr2[i], bArr, i3, i4, i4 + i6);
                i3 += i6;
                i++;
                i2 = i5;
            } else {
                return bArr;
            }
        }
    }

    @Override // okio.ByteString
    public final String toString() {
        return toByteString().toString();
    }

    @Override // okio.ByteString
    public final void write$third_party_java_src_okio_okio_jvm$ar$ds(Buffer buffer, int i) {
        int i2;
        int segment = ToxicityDetectionLoadEvent.segment(this, 0);
        int i3 = 0;
        while (i3 < i) {
            if (segment == 0) {
                segment = 0;
                i2 = 0;
            } else {
                i2 = this.directory[segment - 1];
            }
            int[] iArr = this.directory;
            int i4 = iArr[segment] - i2;
            int i5 = iArr[this.segments.length + segment];
            int min = Math.min(i, i4 + i2) - i3;
            int i6 = i5 + (i3 - i2);
            Segment segment2 = new Segment(this.segments[segment], i6, i6 + min, true);
            Segment segment3 = buffer.head;
            if (segment3 == null) {
                segment2.prev = segment2;
                segment2.next = segment2.prev;
                buffer.head = segment2.next;
            } else {
                Segment segment4 = segment3.prev;
                segment4.getClass();
                segment4.push$ar$ds(segment2);
            }
            i3 += min;
            segment++;
        }
        buffer.size += i;
    }
}
