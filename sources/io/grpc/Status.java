package io.grpc;

import _COROUTINE._BOUNDARY;
import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Throwables;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.Metadata;
import j$.util.DesugarCollections;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Status {
    public static final Status CANCELLED;
    static final Metadata.Key CODE_KEY;
    public static final Status DEADLINE_EXCEEDED;
    public static final Status FAILED_PRECONDITION;
    public static final Status INTERNAL;
    static final Metadata.Key MESSAGE_KEY;
    public static final Status OK;
    public static final Status PERMISSION_DENIED;
    public static final Status RESOURCE_EXHAUSTED;
    public static final List STATUS_LIST;
    private static final Metadata.TrustedAsciiMarshaller STATUS_MESSAGE_MARSHALLER;
    public static final Status UNAUTHENTICATED;
    public static final Status UNAVAILABLE;
    public static final Status UNKNOWN;
    public final Throwable cause;
    public final Code code;
    public final String description;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);

        public final int value;
        public final byte[] valueAscii;

        Code(int i) {
            this.value = i;
            this.valueAscii = Integer.toString(i).getBytes(Charsets.US_ASCII);
        }

        public final Status toStatus() {
            return (Status) Status.STATUS_LIST.get(this.value);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class StatusCodeMarshaller implements Metadata.TrustedAsciiMarshaller {
        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public final /* synthetic */ Object parseAsciiString(byte[] bArr) {
            int i;
            int i2;
            byte b;
            List list = Status.STATUS_LIST;
            int length = bArr.length;
            char c = 0;
            if (length == 1 && bArr[0] == 48) {
                return Status.OK;
            }
            if (length != 1) {
                if (length == 2 && (b = bArr[0]) >= 48 && b <= 57) {
                    i = (b - 48) * 10;
                    c = 1;
                }
                return Status.UNKNOWN.withDescription("Unknown code ".concat(new String(bArr, Charsets.US_ASCII)));
            }
            i = 0;
            byte b2 = bArr[c];
            if (b2 >= 48 && b2 <= 57 && (i2 = i + (b2 - 48)) < Status.STATUS_LIST.size()) {
                return (Status) Status.STATUS_LIST.get(i2);
            }
            return Status.UNKNOWN.withDescription("Unknown code ".concat(new String(bArr, Charsets.US_ASCII)));
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public final /* bridge */ /* synthetic */ byte[] toAsciiString(Object obj) {
            return ((Status) obj).code.valueAscii;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class StatusMessageMarshaller implements Metadata.TrustedAsciiMarshaller {
        private static final byte[] HEX = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};

        private static boolean isEscapingChar(byte b) {
            if (b >= 32 && b < 126 && b != 37) {
                return false;
            }
            return true;
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public final /* bridge */ /* synthetic */ Object parseAsciiString(byte[] bArr) {
            int length;
            int i = 0;
            while (true) {
                length = bArr.length;
                if (i < length) {
                    byte b = bArr[i];
                    if (b < 32 || b >= 126 || (b == 37 && i + 2 < length)) {
                        break;
                    }
                    i++;
                } else {
                    return new String(bArr, 0);
                }
            }
            ByteBuffer allocate = ByteBuffer.allocate(length);
            int i2 = 0;
            while (true) {
                int length2 = bArr.length;
                if (i2 < length2) {
                    int i3 = i2 + 1;
                    if (bArr[i2] == 37 && i2 + 2 < length2) {
                        try {
                            allocate.put((byte) Integer.parseInt(new String(bArr, i3, 2, Charsets.US_ASCII), 16));
                            i2 += 3;
                        } catch (NumberFormatException unused) {
                        }
                    }
                    allocate.put(bArr[i2]);
                    i2 = i3;
                } else {
                    return new String(allocate.array(), 0, allocate.position(), Charsets.UTF_8);
                }
            }
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public final /* bridge */ /* synthetic */ byte[] toAsciiString(Object obj) {
            byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
            int i = 0;
            while (true) {
                int length = bytes.length;
                if (i < length) {
                    if (isEscapingChar(bytes[i])) {
                        byte[] bArr = new byte[((length - i) * 3) + i];
                        if (i != 0) {
                            System.arraycopy(bytes, 0, bArr, 0, i);
                        }
                        int i2 = i;
                        while (i < bytes.length) {
                            int i3 = i2 + 1;
                            byte b = bytes[i];
                            if (isEscapingChar(b)) {
                                bArr[i2] = 37;
                                byte[] bArr2 = HEX;
                                bArr[i3] = bArr2[(b >> 4) & 15];
                                bArr[i2 + 2] = bArr2[b & 15];
                                i2 += 3;
                            } else {
                                bArr[i2] = b;
                                i2 = i3;
                            }
                            i++;
                        }
                        return Arrays.copyOf(bArr, i2);
                    }
                    i++;
                } else {
                    return bytes;
                }
            }
        }
    }

    static {
        TreeMap treeMap = new TreeMap();
        for (Code code : Code.values()) {
            Status status = (Status) treeMap.put(Integer.valueOf(code.value), new Status(code, null, null));
            if (status != null) {
                throw new IllegalStateException("Code value duplication between " + status.code.name() + " & " + code.name());
            }
        }
        STATUS_LIST = DesugarCollections.unmodifiableList(new ArrayList(treeMap.values()));
        OK = Code.OK.toStatus();
        CANCELLED = Code.CANCELLED.toStatus();
        UNKNOWN = Code.UNKNOWN.toStatus();
        Code.INVALID_ARGUMENT.toStatus();
        DEADLINE_EXCEEDED = Code.DEADLINE_EXCEEDED.toStatus();
        Code.NOT_FOUND.toStatus();
        Code.ALREADY_EXISTS.toStatus();
        PERMISSION_DENIED = Code.PERMISSION_DENIED.toStatus();
        UNAUTHENTICATED = Code.UNAUTHENTICATED.toStatus();
        RESOURCE_EXHAUSTED = Code.RESOURCE_EXHAUSTED.toStatus();
        FAILED_PRECONDITION = Code.FAILED_PRECONDITION.toStatus();
        Code.ABORTED.toStatus();
        Code.OUT_OF_RANGE.toStatus();
        Code.UNIMPLEMENTED.toStatus();
        INTERNAL = Code.INTERNAL.toStatus();
        UNAVAILABLE = Code.UNAVAILABLE.toStatus();
        Code.DATA_LOSS.toStatus();
        CODE_KEY = new Metadata.TrustedAsciiKey("grpc-status", false, new StatusCodeMarshaller());
        StatusMessageMarshaller statusMessageMarshaller = new StatusMessageMarshaller();
        STATUS_MESSAGE_MARSHALLER = statusMessageMarshaller;
        MESSAGE_KEY = new Metadata.TrustedAsciiKey("grpc-message", false, statusMessageMarshaller);
    }

    private Status(Code code, String str, Throwable th) {
        code.getClass();
        this.code = code;
        this.description = str;
        this.cause = th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String formatThrowableMessage(Status status) {
        String str = status.description;
        Code code = status.code;
        if (str == null) {
            return code.toString();
        }
        return code.toString() + ": " + str;
    }

    public static Status fromCodeValue(int i) {
        if (i >= 0) {
            List list = STATUS_LIST;
            if (i < list.size()) {
                return (Status) list.get(i);
            }
        }
        return UNKNOWN.withDescription(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unknown code "));
    }

    public static Status fromThrowable(Throwable th) {
        th.getClass();
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof StatusException) {
                return ((StatusException) th2).status;
            }
            if (th2 instanceof StatusRuntimeException) {
                return ((StatusRuntimeException) th2).status;
            }
        }
        return UNKNOWN.withCause(th);
    }

    public final Status augmentDescription(String str) {
        String str2 = this.description;
        if (str2 == null) {
            return new Status(this.code, str, this.cause);
        }
        return new Status(this.code, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str, str2, "\n"), this.cause);
    }

    public final boolean isOk() {
        if (Code.OK == this.code) {
            return true;
        }
        return false;
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("code", this.code.name());
        stringHelper.addHolder$ar$ds("description", this.description);
        Throwable th = this.cause;
        Object obj = th;
        if (th != null) {
            obj = Throwables.getStackTraceAsString(th);
        }
        stringHelper.addHolder$ar$ds("cause", obj);
        return stringHelper.toString();
    }

    public final Status withCause(Throwable th) {
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.cause, th)) {
            return this;
        }
        return new Status(this.code, this.description, th);
    }

    public final Status withDescription(String str) {
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.description, str)) {
            return this;
        }
        return new Status(this.code, str, this.cause);
    }
}
