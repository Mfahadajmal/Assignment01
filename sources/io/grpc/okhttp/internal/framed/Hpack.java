package io.grpc.okhttp.internal.framed;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import j$.util.DesugarCollections;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.RealBufferedSource;
import okio.Source;
import org.chromium.net.PrivateKeyType;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class Hpack {
    public static final Map NAME_TO_FIRST_INDEX;
    public static final ByteString PSEUDO_PREFIX = ByteString.encodeUtf8(":");
    public static final Header[] STATIC_HEADER_TABLE = {new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Reader {
        int nextDynamicTableIndex;
        public final BufferedSource source;
        public final List headerList = new ArrayList();
        Header[] dynamicTable = new Header[8];
        int dynamicTableHeaderCount = 0;
        int dynamicTableByteCount = 0;
        public int headerTableSizeSetting = 4096;
        public int maxDynamicTableByteCount = 4096;

        public Reader(Source source) {
            this.nextDynamicTableIndex = r0.length - 1;
            this.source = new RealBufferedSource(source);
        }

        private final void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextDynamicTableIndex = this.dynamicTable.length - 1;
            this.dynamicTableHeaderCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private final void evictToRecoverBytes$ar$ds(int i) {
            int i2;
            if (i > 0) {
                int length = this.dynamicTable.length - 1;
                int i3 = 0;
                while (true) {
                    i2 = this.nextDynamicTableIndex;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    int i4 = this.dynamicTable[length].hpackSize;
                    i -= i4;
                    this.dynamicTableByteCount -= i4;
                    this.dynamicTableHeaderCount--;
                    i3++;
                    length--;
                }
                Header[] headerArr = this.dynamicTable;
                int i5 = i2 + 1;
                System.arraycopy(headerArr, i5, headerArr, i5 + i3, this.dynamicTableHeaderCount);
                this.nextDynamicTableIndex += i3;
            }
        }

        public static final boolean isStaticHeader$ar$ds(int i) {
            if (i >= 0) {
                int length = Hpack.STATIC_HEADER_TABLE.length;
                if (i <= 60) {
                    return true;
                }
                return false;
            }
            return false;
        }

        private final int readByte() {
            return this.source.readByte() & 255;
        }

        public final void adjustDynamicTableByteCount() {
            int i = this.maxDynamicTableByteCount;
            int i2 = this.dynamicTableByteCount;
            if (i < i2) {
                if (i == 0) {
                    clearDynamicTable();
                } else {
                    evictToRecoverBytes$ar$ds(i2 - i);
                }
            }
        }

        public final int dynamicTableIndex(int i) {
            return this.nextDynamicTableIndex + 1 + i;
        }

        public final ByteString getName(int i) {
            if (isStaticHeader$ar$ds(i)) {
                return Hpack.STATIC_HEADER_TABLE[i].name;
            }
            int length = Hpack.STATIC_HEADER_TABLE.length;
            int dynamicTableIndex = dynamicTableIndex(i - 61);
            if (dynamicTableIndex >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (dynamicTableIndex < headerArr.length) {
                    return headerArr[dynamicTableIndex].name;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        public final void insertIntoDynamicTable$ar$ds(Header header) {
            this.headerList.add(header);
            int i = header.hpackSize;
            int i2 = this.maxDynamicTableByteCount;
            if (i > i2) {
                clearDynamicTable();
                return;
            }
            evictToRecoverBytes$ar$ds((this.dynamicTableByteCount + i) - i2);
            int i3 = this.dynamicTableHeaderCount + 1;
            Header[] headerArr = this.dynamicTable;
            int length = headerArr.length;
            if (i3 > length) {
                Header[] headerArr2 = new Header[length + length];
                System.arraycopy(headerArr, 0, headerArr2, length, length);
                this.nextDynamicTableIndex = this.dynamicTable.length - 1;
                this.dynamicTable = headerArr2;
            }
            int i4 = this.nextDynamicTableIndex;
            this.nextDynamicTableIndex = i4 - 1;
            this.dynamicTable[i4] = header;
            this.dynamicTableHeaderCount++;
            this.dynamicTableByteCount += i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final ByteString readByteString() {
            int i;
            int readByte = readByte();
            int i2 = readByte & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
            long readInt = readInt(readByte, BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE);
            if (i2 == 128) {
                BufferedSource bufferedSource = this.source;
                Huffman huffman = Huffman.INSTANCE;
                byte[] readByteArray = bufferedSource.readByteArray(readInt);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                CronetEngineBuilderImpl.QuicHint quicHint = huffman.root$ar$class_merging;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (i3 < readByteArray.length) {
                    int i6 = readByteArray[i3] & 255;
                    int i7 = i4 << 8;
                    i5 += 8;
                    while (true) {
                        i = i7 | i6;
                        if (i5 >= 8) {
                            int i8 = i5 - 8;
                            quicHint = ((CronetEngineBuilderImpl.QuicHint[]) quicHint.CronetEngineBuilderImpl$QuicHint$ar$mHost)[(i >>> i8) & PrivateKeyType.INVALID];
                            if (quicHint.CronetEngineBuilderImpl$QuicHint$ar$mHost == null) {
                                byteArrayOutputStream.write(quicHint.mAlternatePort);
                                i5 -= quicHint.mPort;
                                quicHint = huffman.root$ar$class_merging;
                            } else {
                                i5 = i8;
                            }
                        }
                    }
                    i3++;
                    i4 = i;
                }
                while (i5 > 0) {
                    CronetEngineBuilderImpl.QuicHint quicHint2 = ((CronetEngineBuilderImpl.QuicHint[]) quicHint.CronetEngineBuilderImpl$QuicHint$ar$mHost)[(i4 << (8 - i5)) & PrivateKeyType.INVALID];
                    if (quicHint2.CronetEngineBuilderImpl$QuicHint$ar$mHost != null || quicHint2.mPort > i5) {
                        break;
                    }
                    byteArrayOutputStream.write(quicHint2.mAlternatePort);
                    i5 -= quicHint2.mPort;
                    quicHint = huffman.root$ar$class_merging;
                }
                return ByteString.of(byteArrayOutputStream.toByteArray());
            }
            return this.source.readByteString(readInt);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final int readInt(int i, int i2) {
            int i3 = i & i2;
            if (i3 >= i2) {
                int i4 = 0;
                while (true) {
                    int readByte = readByte();
                    if ((readByte & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0) {
                        i2 += (readByte & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE) << i4;
                        i4 += 7;
                    } else {
                        return i2 + (readByte << i4);
                    }
                }
            } else {
                return i3;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Writer {
        private int dynamicTableByteCount;
        int dynamicTableHeaderCount;
        public int nextDynamicTableIndex;
        public final Buffer out;
        Header[] dynamicTable = new Header[8];
        private int maxDynamicTableByteCount = 4096;

        public Writer(Buffer buffer) {
            this.nextDynamicTableIndex = r0.length - 1;
            this.out = buffer;
        }

        public final void insertIntoDynamicTable(Header header) {
            int i;
            int i2 = header.hpackSize;
            int i3 = this.maxDynamicTableByteCount;
            if (i2 > i3) {
                Arrays.fill(this.dynamicTable, (Object) null);
                this.nextDynamicTableIndex = this.dynamicTable.length - 1;
                this.dynamicTableHeaderCount = 0;
                this.dynamicTableByteCount = 0;
                return;
            }
            int i4 = (this.dynamicTableByteCount + i2) - i3;
            if (i4 > 0) {
                int length = this.dynamicTable.length - 1;
                int i5 = 0;
                while (true) {
                    i = this.nextDynamicTableIndex;
                    if (length < i || i4 <= 0) {
                        break;
                    }
                    int i6 = this.dynamicTable[length].hpackSize;
                    i4 -= i6;
                    this.dynamicTableByteCount -= i6;
                    this.dynamicTableHeaderCount--;
                    i5++;
                    length--;
                }
                Header[] headerArr = this.dynamicTable;
                int i7 = i + 1;
                System.arraycopy(headerArr, i7, headerArr, i7 + i5, this.dynamicTableHeaderCount);
                this.nextDynamicTableIndex += i5;
            }
            int i8 = this.dynamicTableHeaderCount + 1;
            Header[] headerArr2 = this.dynamicTable;
            int length2 = headerArr2.length;
            if (i8 > length2) {
                Header[] headerArr3 = new Header[length2 + length2];
                System.arraycopy(headerArr2, 0, headerArr3, length2, length2);
                this.nextDynamicTableIndex = this.dynamicTable.length - 1;
                this.dynamicTable = headerArr3;
            }
            int i9 = this.nextDynamicTableIndex;
            this.nextDynamicTableIndex = i9 - 1;
            this.dynamicTable[i9] = header;
            this.dynamicTableHeaderCount++;
            this.dynamicTableByteCount += i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void writeByteString(ByteString byteString) {
            writeInt(byteString.getSize$third_party_java_src_okio_okio_jvm(), BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE, 0);
            this.out.write$ar$ds(byteString);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void writeInt(int i, int i2, int i3) {
            if (i < i2) {
                this.out.writeByte$ar$ds(i | i3);
                return;
            }
            this.out.writeByte$ar$ds(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.out.writeByte$ar$ds(128 | (i4 & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE));
                i4 >>>= 7;
            }
            this.out.writeByte$ar$ds(i4);
        }
    }

    static {
        int i = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        while (true) {
            Header[] headerArr = STATIC_HEADER_TABLE;
            int length = headerArr.length;
            if (i < 61) {
                if (!linkedHashMap.containsKey(headerArr[i].name)) {
                    linkedHashMap.put(headerArr[i].name, Integer.valueOf(i));
                }
                i++;
            } else {
                NAME_TO_FIRST_INDEX = DesugarCollections.unmodifiableMap(linkedHashMap);
                return;
            }
        }
    }

    public static void checkLowercase$ar$ds(ByteString byteString) {
        int size$third_party_java_src_okio_okio_jvm = byteString.getSize$third_party_java_src_okio_okio_jvm();
        for (int i = 0; i < size$third_party_java_src_okio_okio_jvm; i++) {
            byte internalGet$third_party_java_src_okio_okio_jvm = byteString.internalGet$third_party_java_src_okio_okio_jvm(i);
            if (internalGet$third_party_java_src_okio_okio_jvm >= 65 && internalGet$third_party_java_src_okio_okio_jvm <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: ".concat(byteString.utf8()));
            }
        }
    }
}
