package okio;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Segment {
    public final byte[] data;
    public int limit;
    public Segment next;
    public final boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public final Segment pop() {
        Segment segment;
        Segment segment2 = this.next;
        if (segment2 != this) {
            segment = segment2;
        } else {
            segment = null;
        }
        Segment segment3 = this.prev;
        segment3.getClass();
        segment3.next = segment2;
        Segment segment4 = this.next;
        segment4.getClass();
        segment4.prev = segment3;
        this.next = null;
        this.prev = null;
        return segment;
    }

    public final void push$ar$ds(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        Segment segment2 = this.next;
        segment2.getClass();
        segment2.prev = segment;
        this.next = segment;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true);
    }

    public final void writeTo(Segment segment, int i) {
        if (segment.owner) {
            int i2 = segment.limit;
            int i3 = i2 + i;
            if (i3 > 8192) {
                if (!segment.shared) {
                    int i4 = segment.pos;
                    if (i3 - i4 <= 8192) {
                        byte[] bArr = segment.data;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds(bArr, bArr, 0, i4, i2);
                        i2 = segment.limit - segment.pos;
                        segment.limit = i2;
                        segment.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            byte[] bArr2 = this.data;
            byte[] bArr3 = segment.data;
            int i5 = this.pos;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds(bArr2, bArr3, i2, i5, i5 + i);
            segment.limit += i;
            this.pos += i;
            return;
        }
        throw new IllegalStateException("only owner can write");
    }

    public Segment(byte[] bArr, int i, int i2, boolean z) {
        bArr.getClass();
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.shared = z;
        this.owner = false;
    }
}
