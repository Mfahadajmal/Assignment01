package io.grpc.protobuf.lite;

import androidx.preference.Preference;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import io.grpc.KnownLength;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoLiteUtils {
    public static volatile ExtensionRegistryLite globalRegistry;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MessageMarshaller implements MethodDescriptor.ReflectableMarshaller {
        private static final ThreadLocal bufs = new ThreadLocal();
        private final MessageLite defaultInstance;
        private final Parser parser;

        public MessageMarshaller(MessageLite messageLite) {
            messageLite.getClass();
            this.defaultInstance = messageLite;
            this.parser = messageLite.getParserForType();
        }

        @Override // io.grpc.MethodDescriptor.ReflectableMarshaller
        public final Class getMessageClass() {
            return this.defaultInstance.getClass();
        }

        @Override // io.grpc.MethodDescriptor.Marshaller
        public final /* bridge */ /* synthetic */ Object parse(InputStream inputStream) {
            byte[] bArr;
            if (inputStream instanceof ProtoInputStream) {
                ProtoInputStream protoInputStream = (ProtoInputStream) inputStream;
                if (protoInputStream.parser == this.parser) {
                    try {
                        MessageLite messageLite = protoInputStream.message;
                        if (messageLite == null) {
                            throw new IllegalStateException("message not available");
                        }
                        return messageLite;
                    } catch (IllegalStateException unused) {
                    }
                }
            }
            try {
                CodedInputStream codedInputStream = null;
                if (inputStream instanceof KnownLength) {
                    int available = inputStream.available();
                    if (available > 0 && available <= 4194304) {
                        ThreadLocal threadLocal = bufs;
                        Reference reference = (Reference) threadLocal.get();
                        if (reference == null || (bArr = (byte[]) reference.get()) == null || bArr.length < available) {
                            bArr = new byte[available];
                            threadLocal.set(new WeakReference(bArr));
                        }
                        int i = available;
                        while (i > 0) {
                            int read = inputStream.read(bArr, available - i, i);
                            if (read == -1) {
                                break;
                            }
                            i -= read;
                        }
                        if (i == 0) {
                            codedInputStream = CodedInputStream.newInstance(bArr, 0, available);
                        } else {
                            throw new RuntimeException("size inaccurate: " + available + " != " + (available - i));
                        }
                    } else if (available == 0) {
                        return this.defaultInstance;
                    }
                }
                if (codedInputStream == null) {
                    codedInputStream = CodedInputStream.newInstance(inputStream);
                }
                codedInputStream.sizeLimit = Preference.DEFAULT_ORDER;
                try {
                    Object parseFrom = this.parser.parseFrom(codedInputStream, ProtoLiteUtils.globalRegistry);
                    try {
                        codedInputStream.checkLastTagWas(0);
                        return parseFrom;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw new StatusRuntimeException(Status.INTERNAL.withDescription("Invalid protobuf byte sequence").withCause(e2));
                }
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        }

        @Override // io.grpc.MethodDescriptor.Marshaller
        public final /* bridge */ /* synthetic */ InputStream stream(Object obj) {
            return new ProtoInputStream((MessageLite) obj, this.parser);
        }
    }

    static {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        globalRegistry = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }
}
