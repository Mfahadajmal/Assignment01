package io.grpc;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.base.Charsets;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.io.BaseEncoding;
import com.google.mlkit.logging.schema.OnDeviceImageCaptioningInferenceLogEvent;
import io.grpc.internal.GrpcUtil;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Metadata {
    static final BaseEncoding BASE64_ENCODING_OMIT_PADDING;
    public Object[] namesAndValues;
    public int size;
    public static final Logger logger = Logger.getLogger(Metadata.class.getName());
    public static final OnDeviceImageCaptioningInferenceLogEvent BINARY_BYTE_MARSHALLER$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceImageCaptioningInferenceLogEvent();
    public static final AsciiMarshaller ASCII_STRING_MARSHALLER = new GrpcUtil.TimeoutMarshaller(1);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AsciiKey extends Key {
        private final AsciiMarshaller marshaller;

        public AsciiKey(String str, AsciiMarshaller asciiMarshaller) {
            super(str, false);
            ContextDataProvider.checkArgument(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", str, "-bin");
            asciiMarshaller.getClass();
            this.marshaller = asciiMarshaller;
        }

        @Override // io.grpc.Metadata.Key
        public final Object parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(new String(bArr, Charsets.US_ASCII));
        }

        @Override // io.grpc.Metadata.Key
        public final byte[] toBytes(Object obj) {
            return this.marshaller.toAsciiString(obj).getBytes(Charsets.US_ASCII);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AsciiMarshaller {
        Object parseAsciiString(String str);

        String toAsciiString(Object obj);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BinaryKey extends Key {
        private final OnDeviceImageCaptioningInferenceLogEvent marshaller$ar$class_merging$ar$class_merging$ar$class_merging;

        public BinaryKey(String str, OnDeviceImageCaptioningInferenceLogEvent onDeviceImageCaptioningInferenceLogEvent) {
            super(str, false);
            ContextDataProvider.checkArgument(str.endsWith("-bin"), "Binary header is named %s. It must end with %s", str, "-bin");
            ContextDataProvider.checkArgument(str.length() > 4, (Object) "empty key name");
            onDeviceImageCaptioningInferenceLogEvent.getClass();
            this.marshaller$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceImageCaptioningInferenceLogEvent;
        }

        @Override // io.grpc.Metadata.Key
        public final Object parseBytes(byte[] bArr) {
            return OnDeviceImageCaptioningInferenceLogEvent.parseBytes$ar$ds(bArr);
        }

        @Override // io.grpc.Metadata.Key
        public final byte[] toBytes(Object obj) {
            return OnDeviceImageCaptioningInferenceLogEvent.toBytes$ar$ds$db6b0411_0(obj);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Key {
        private static final BitSet VALID_T_CHARS;
        public final String name;
        public final byte[] nameBytes;
        private final String originalName;

        static {
            BitSet bitSet = new BitSet(BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE);
            bitSet.set(45);
            bitSet.set(95);
            bitSet.set(46);
            for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
                bitSet.set(c);
            }
            for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
                bitSet.set(c2);
            }
            VALID_T_CHARS = bitSet;
        }

        public Key(String str, boolean z) {
            str.getClass();
            this.originalName = str;
            String lowerCase = str.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            ContextDataProvider.checkArgument(!lowerCase.isEmpty(), (Object) "token must have at least 1 tchar");
            if (lowerCase.equals("connection")) {
                Metadata.logger.logp(Level.WARNING, "io.grpc.Metadata$Key", "validateName", "Metadata key is 'Connection', which should not be used. That is used by HTTP/1 for connection-specific headers which are not to be forwarded. There is probably an HTTP/1 conversion bug. Simply removing the Connection header is not enough; you should remove all headers it references as well. See RFC 7230 section 6.1", (Throwable) new RuntimeException("exception to show backtrace"));
            }
            int i = 0;
            while (i < lowerCase.length()) {
                char charAt = lowerCase.charAt(i);
                if (z && charAt == ':') {
                    if (i == 0) {
                        i = 0;
                        i++;
                    } else {
                        charAt = ':';
                    }
                }
                if (VALID_T_CHARS.get(charAt)) {
                    i++;
                } else {
                    throw new IllegalArgumentException(ContextDataProvider.lenientFormat("Invalid character '%s' in key name '%s'", Character.valueOf(charAt), lowerCase));
                }
            }
            this.name = lowerCase;
            this.nameBytes = lowerCase.getBytes(Charsets.US_ASCII);
        }

        public static Key of(String str, AsciiMarshaller asciiMarshaller) {
            return new AsciiKey(str, asciiMarshaller);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                return this.name.equals(((Key) obj).name);
            }
            return false;
        }

        public final int hashCode() {
            return this.name.hashCode();
        }

        public abstract Object parseBytes(byte[] bArr);

        public abstract byte[] toBytes(Object obj);

        public final String toString() {
            return "Key{name='" + this.name + "'}";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TrustedAsciiKey extends Key {
        private final TrustedAsciiMarshaller marshaller;

        public TrustedAsciiKey(String str, boolean z, TrustedAsciiMarshaller trustedAsciiMarshaller) {
            super(str, z);
            ContextDataProvider.checkArgument(!str.endsWith("-bin"), "ASCII header is named %s.  Only binary headers may end with %s", str, "-bin");
            trustedAsciiMarshaller.getClass();
            this.marshaller = trustedAsciiMarshaller;
        }

        @Override // io.grpc.Metadata.Key
        public final Object parseBytes(byte[] bArr) {
            return this.marshaller.parseAsciiString(bArr);
        }

        @Override // io.grpc.Metadata.Key
        public final byte[] toBytes(Object obj) {
            byte[] asciiString = this.marshaller.toAsciiString(obj);
            asciiString.getClass();
            return asciiString;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface TrustedAsciiMarshaller {
        Object parseAsciiString(byte[] bArr);

        byte[] toAsciiString(Object obj);
    }

    static {
        BaseEncoding baseEncoding = BaseEncoding.BASE64;
        BaseEncoding.StandardBaseEncoding standardBaseEncoding = (BaseEncoding.StandardBaseEncoding) baseEncoding;
        if (standardBaseEncoding.paddingChar != null) {
            baseEncoding = standardBaseEncoding.newInstance(standardBaseEncoding.alphabet, null);
        }
        BASE64_ENCODING_OMIT_PADDING = baseEncoding;
    }

    public Metadata() {
    }

    private final int cap() {
        Object[] objArr = this.namesAndValues;
        if (objArr != null) {
            return objArr.length;
        }
        return 0;
    }

    private final void expand(int i) {
        Object[] objArr = new Object[i];
        if (!isEmpty()) {
            System.arraycopy(this.namesAndValues, 0, objArr, 0, len());
        }
        this.namesAndValues = objArr;
    }

    private final boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    private final Object value(int i) {
        return this.namesAndValues[i + i + 1];
    }

    public final void discardAll(Key key) {
        if (!isEmpty()) {
            int i = 0;
            for (int i2 = 0; i2 < this.size; i2++) {
                if (!Arrays.equals(key.nameBytes, name(i2))) {
                    int i3 = i + i;
                    name(i, name(i2));
                    Object value = value(i2);
                    if (this.namesAndValues instanceof byte[][]) {
                        expand(cap());
                    }
                    this.namesAndValues[i3 + 1] = value;
                    i++;
                }
            }
            Arrays.fill(this.namesAndValues, i + i, len(), (Object) null);
            this.size = i;
        }
    }

    public final Object get(Key key) {
        int i = this.size;
        do {
            i--;
            if (i < 0) {
                return null;
            }
        } while (!Arrays.equals(key.nameBytes, name(i)));
        Object value = value(i);
        if (value instanceof byte[]) {
            return key.parseBytes((byte[]) value);
        }
        throw null;
    }

    public final int len() {
        int i = this.size;
        return i + i;
    }

    public final void merge(Metadata metadata) {
        if (metadata.isEmpty()) {
            return;
        }
        int cap = cap() - len();
        if (isEmpty() || cap < metadata.len()) {
            expand(len() + metadata.len());
        }
        System.arraycopy(metadata.namesAndValues, 0, this.namesAndValues, len(), metadata.len());
        this.size += metadata.size;
    }

    public final byte[] name(int i) {
        return (byte[]) this.namesAndValues[i + i];
    }

    public final void put(Key key, Object obj) {
        key.getClass();
        obj.getClass();
        if (len() == 0 || len() == cap()) {
            int len = len();
            expand(Math.max(len + len, 8));
        }
        name(this.size, key.nameBytes);
        int i = this.size;
        this.namesAndValues[i + i + 1] = key.toBytes(obj);
        this.size++;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Metadata(");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                sb.append(',');
            }
            String str = new String(name(i), Charsets.US_ASCII);
            sb.append(str);
            sb.append('=');
            if (str.endsWith("-bin")) {
                sb.append(BASE64_ENCODING_OMIT_PADDING.encode(valueAsBytes(i)));
            } else {
                sb.append(new String(valueAsBytes(i), Charsets.US_ASCII));
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public final byte[] valueAsBytes(int i) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        throw null;
    }

    public Metadata(byte[]... bArr) {
        this.size = bArr.length >> 1;
        this.namesAndValues = bArr;
    }

    private final void name(int i, byte[] bArr) {
        this.namesAndValues[i + i] = bArr;
    }
}
