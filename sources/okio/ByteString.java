package okio;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.ToxicityDetectionCreateEvent;
import com.google.mlkit.logging.schema.ToxicityDetectionInferenceEvent;
import com.google.mlkit.logging.schema.ToxicityDetectionLoadEvent;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ByteString implements Serializable, Comparable {
    public static final ToxicityDetectionInferenceEvent Companion$ar$class_merging$49cc2443_0 = new ToxicityDetectionInferenceEvent();
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;
    public final byte[] data;
    public transient int hashCode;
    public transient String utf8;

    public ByteString(byte[] bArr) {
        bArr.getClass();
        this.data = bArr;
    }

    public static final ByteString encodeUtf8(String str) {
        return ToxicityDetectionInferenceEvent.encodeUtf8$ar$ds(str);
    }

    public static final ByteString of(byte... bArr) {
        bArr.getClass();
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        copyOf.getClass();
        return new ByteString(copyOf);
    }

    private final void readObject(ObjectInputStream objectInputStream) {
        int readInt = objectInputStream.readInt();
        objectInputStream.getClass();
        if (readInt >= 0) {
            byte[] bArr = new byte[readInt];
            int i = 0;
            while (i < readInt) {
                int read = objectInputStream.read(bArr, i, readInt - i);
                if (read != -1) {
                    i += read;
                } else {
                    throw new EOFException();
                }
            }
            ByteString byteString = new ByteString(bArr);
            Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, byteString.data);
            return;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readInt, "byteCount < 0: "));
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        ByteString byteString = (ByteString) obj;
        byteString.getClass();
        int size$third_party_java_src_okio_okio_jvm = getSize$third_party_java_src_okio_okio_jvm();
        int size$third_party_java_src_okio_okio_jvm2 = byteString.getSize$third_party_java_src_okio_okio_jvm();
        int min = Math.min(size$third_party_java_src_okio_okio_jvm, size$third_party_java_src_okio_okio_jvm2);
        int i = 0;
        while (true) {
            if (i < min) {
                int internalGet$third_party_java_src_okio_okio_jvm = internalGet$third_party_java_src_okio_okio_jvm(i) & 255;
                int internalGet$third_party_java_src_okio_okio_jvm2 = byteString.internalGet$third_party_java_src_okio_okio_jvm(i) & 255;
                if (internalGet$third_party_java_src_okio_okio_jvm == internalGet$third_party_java_src_okio_okio_jvm2) {
                    i++;
                } else if (internalGet$third_party_java_src_okio_okio_jvm < internalGet$third_party_java_src_okio_okio_jvm2) {
                    return -1;
                }
            } else {
                if (size$third_party_java_src_okio_okio_jvm == size$third_party_java_src_okio_okio_jvm2) {
                    return 0;
                }
                if (size$third_party_java_src_okio_okio_jvm < size$third_party_java_src_okio_okio_jvm2) {
                    return -1;
                }
            }
        }
        return 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int size$third_party_java_src_okio_okio_jvm = byteString.getSize$third_party_java_src_okio_okio_jvm();
            byte[] bArr = this.data;
            int length = bArr.length;
            if (size$third_party_java_src_okio_okio_jvm == length && byteString.rangeEquals(0, bArr, 0, length)) {
                return true;
            }
        }
        return false;
    }

    public int getSize$third_party_java_src_okio_okio_jvm() {
        return this.data.length;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = Arrays.hashCode(this.data);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String hex() {
        byte[] bArr = this.data;
        int length = bArr.length;
        char[] cArr = new char[length + length];
        int i = 0;
        for (byte b : bArr) {
            cArr[i] = okio.internal.ByteString.HEX_DIGIT_CHARS[(b >> 4) & 15];
            cArr[i + 1] = okio.internal.ByteString.HEX_DIGIT_CHARS[b & 15];
            i += 2;
        }
        return new String(cArr);
    }

    public byte[] internalArray$third_party_java_src_okio_okio_jvm() {
        return this.data;
    }

    public byte internalGet$third_party_java_src_okio_okio_jvm(int i) {
        return this.data[i];
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        bArr.getClass();
        if (i >= 0) {
            byte[] bArr2 = this.data;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && ToxicityDetectionCreateEvent.arrayRangeEquals(bArr2, i, bArr, i2, i3)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean rangeEquals$ar$ds(ByteString byteString, int i) {
        return byteString.rangeEquals(0, this.data, 0, i);
    }

    public ByteString toAsciiLowercase() {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            int length = bArr.length;
            if (i < length) {
                int i2 = i + 1;
                byte b = bArr[i];
                if (b >= 65 && b <= 90) {
                    byte[] copyOf = Arrays.copyOf(bArr, length);
                    copyOf.getClass();
                    copyOf[i] = (byte) (b + 32);
                    while (i2 < copyOf.length) {
                        int i3 = i2 + 1;
                        byte b2 = copyOf[i2];
                        if (b2 >= 65 && b2 <= 90) {
                            copyOf[i2] = (byte) (b2 + 32);
                        }
                        i2 = i3;
                    }
                    return new ByteString(copyOf);
                }
                i = i2;
            } else {
                return this;
            }
        }
    }

    public byte[] toByteArray() {
        byte[] bArr = this.data;
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        copyOf.getClass();
        return copyOf;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x00d2, code lost:
    
        if (r3 < 65536) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00bb, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x00d5, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x00d8, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x009c, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x011a, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0120, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x010f, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x012e, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0132, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0136, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x00e3, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x013a, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x004f, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0086, code lost:
    
        if (r9 < 65536) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0088, code lost:
    
        r14 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x008b, code lost:
    
        r14 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0075, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x008e, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0063, code lost:
    
        if (r5 != 64) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x00c5, code lost:
    
        if (r5 != 64) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 477
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.toString():java.lang.String");
    }

    public final String utf8() {
        String str = this.utf8;
        if (str == null) {
            String utf8String = ToxicityDetectionLoadEvent.toUtf8String(internalArray$third_party_java_src_okio_okio_jvm());
            this.utf8 = utf8String;
            return utf8String;
        }
        return str;
    }

    public void write$third_party_java_src_okio_okio_jvm$ar$ds(Buffer buffer, int i) {
        char[] cArr = okio.internal.ByteString.HEX_DIGIT_CHARS;
        buffer.write$ar$ds$66c9c9c2_0(this.data, 0, i);
    }
}
