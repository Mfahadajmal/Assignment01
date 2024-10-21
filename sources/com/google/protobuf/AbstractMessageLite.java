package com.google.protobuf;

import com.google.mlkit.vision.text.internal.TextRecognizerImplFactory;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite {
    protected int memoizedHashCode = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite.Builder {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class LimitedInputStream extends FilterInputStream implements InputStreamRetargetInterface {
            private int limit;

            public LimitedInputStream(InputStream inputStream, int i) {
                super(inputStream);
                this.limit = i;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public final int available() {
                return Math.min(super.available(), this.limit);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public final int read() {
                if (this.limit <= 0) {
                    return -1;
                }
                int read = super.read();
                if (read >= 0) {
                    this.limit--;
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public final long skip(long j) {
                int skip = (int) super.skip(Math.min(j, this.limit));
                if (skip >= 0) {
                    this.limit -= skip;
                }
                return skip;
            }

            @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
            public final /* synthetic */ long transferTo(OutputStream outputStream) {
                return DesugarInputStream.transferTo(this, outputStream);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public final int read(byte[] bArr, int i, int i2) {
                int i3 = this.limit;
                if (i3 <= 0) {
                    return -1;
                }
                int read = super.read(bArr, i, Math.min(i2, i3));
                if (read >= 0) {
                    this.limit -= read;
                }
                return read;
            }
        }

        @Deprecated
        protected static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
            addAll((Iterable) iterable, (List) collection);
        }

        private static <T> void addAllCheckingNulls(Iterable<T> iterable, List<? super T> list) {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size = list.size();
            for (T t : iterable) {
                if (t == null) {
                    String str = "Element at index " + (list.size() - size) + " is null.";
                    int size2 = list.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            break;
                        } else {
                            list.remove(size2);
                        }
                    }
                    throw new NullPointerException(str);
                }
                list.add(t);
            }
        }

        private String getReadingExceptionMessage(String str) {
            return "Reading " + getClass().getName() + " from a " + str + " threw an IOException (should never happen).";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException();
        }

        @Override // 
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public abstract BuilderType mo227clone();

        protected abstract BuilderType internalMergeFrom(MessageType messagetype);

        public boolean mergeDelimitedFrom(InputStream inputStream) {
            ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
            Protobuf protobuf = Protobuf.INSTANCE;
            return mergeDelimitedFrom(inputStream, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType m228mergeFrom(ByteString byteString) {
            try {
                CodedInputStream newCodedInput = byteString.newCodedInput();
                m230mergeFrom(newCodedInput);
                newCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("ByteString"), e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public abstract BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        protected static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
            iterable.getClass();
            if (!(iterable instanceof LazyStringList)) {
                if (iterable instanceof PrimitiveNonBoxingCollection) {
                    list.addAll((Collection) iterable);
                    return;
                } else {
                    addAllCheckingNulls(iterable, list);
                    return;
                }
            }
            List underlyingElements = ((LazyStringList) iterable).getUnderlyingElements();
            LazyStringList lazyStringList = (LazyStringList) list;
            int size = list.size();
            for (Object obj : underlyingElements) {
                if (obj == null) {
                    String str = "Element at index " + (lazyStringList.size() - size) + " is null.";
                    int size2 = lazyStringList.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            break;
                        } else {
                            lazyStringList.remove(size2);
                        }
                    }
                    throw new NullPointerException(str);
                }
                if (obj instanceof ByteString) {
                    lazyStringList.add$ar$ds$3cea9816_0();
                } else if (obj instanceof byte[]) {
                    ByteString.copyFrom((byte[]) obj);
                    lazyStringList.add$ar$ds$3cea9816_0();
                } else {
                    lazyStringList.add((String) obj);
                }
            }
        }

        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            int read = inputStream.read();
            if (read == -1) {
                return false;
            }
            m232mergeFrom((InputStream) new LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(read, inputStream)), extensionRegistryLite);
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType m229mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            try {
                CodedInputStream newCodedInput = byteString.newCodedInput();
                mergeFrom(newCodedInput, extensionRegistryLite);
                newCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("ByteString"), e2);
            }
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType m230mergeFrom(CodedInputStream codedInputStream) {
            ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
            Protobuf protobuf = Protobuf.INSTANCE;
            return mergeFrom(codedInputStream, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(MessageLite messageLite) {
            if (getDefaultInstanceForType().getClass().isInstance(messageLite)) {
                return (BuilderType) internalMergeFrom((AbstractMessageLite) messageLite);
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType m231mergeFrom(InputStream inputStream) {
            CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
            m230mergeFrom(newInstance);
            newInstance.checkLastTagWas(0);
            return this;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType m232mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(newInstance, extensionRegistryLite);
            newInstance.checkLastTagWas(0);
            return this;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType m233mergeFrom(byte[] bArr) {
            return mo234mergeFrom(bArr, 0, bArr.length);
        }

        @Override // 
        /* renamed from: mergeFrom */
        public BuilderType mo234mergeFrom(byte[] bArr, int i, int i2) {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(bArr, i, i2);
                m230mergeFrom(newInstance);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("byte array"), e2);
            }
        }

        @Override // 
        /* renamed from: mergeFrom */
        public BuilderType mo235mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(bArr, i, i2);
                mergeFrom(newInstance, extensionRegistryLite);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("byte array"), e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return mo235mergeFrom(bArr, 0, bArr.length, extensionRegistryLite);
        }
    }

    public static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
        Builder.addAll((Iterable) iterable, (List) list);
    }

    protected static void checkByteStringIsUtf8(ByteString byteString) {
        if (byteString.isValidUtf8()) {
        } else {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    private String getSerializingExceptionMessage(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }

    public int getMemoizedSerializedSize() {
        throw new UnsupportedOperationException();
    }

    public int getSerializedSize(Schema schema) {
        int memoizedSerializedSize = getMemoizedSerializedSize();
        if (memoizedSerializedSize == -1) {
            int serializedSize = schema.getSerializedSize(this);
            setMemoizedSerializedSize(serializedSize);
            return serializedSize;
        }
        return memoizedSerializedSize;
    }

    public MutableMessageLite mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy() is not implemented.");
    }

    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException();
    }

    public void setMemoizedSerializedSize(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream newInstance = CodedOutputStream.newInstance(bArr);
            writeTo(newInstance);
            newInstance.checkNoSpaceLeft();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(getSerializingExceptionMessage("byte array"), e);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            TextRecognizerImplFactory textRecognizerImplFactory = new TextRecognizerImplFactory(getSerializedSize());
            writeTo((CodedOutputStream) textRecognizerImplFactory.TextRecognizerImplFactory$ar$executorSelector);
            return textRecognizerImplFactory.build();
        } catch (IOException e) {
            throw new RuntimeException(getSerializingExceptionMessage("ByteString"), e);
        }
    }

    public void writeDelimitedTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        CodedOutputStream.AbstractBufferedEncoder abstractBufferedEncoder = new CodedOutputStream.AbstractBufferedEncoder(outputStream, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeUInt32SizeNoTag(serializedSize) + serializedSize));
        abstractBufferedEncoder.writeUInt32NoTag(serializedSize);
        writeTo(abstractBufferedEncoder);
        abstractBufferedEncoder.flush();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) {
        CodedOutputStream.AbstractBufferedEncoder abstractBufferedEncoder = new CodedOutputStream.AbstractBufferedEncoder(outputStream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(abstractBufferedEncoder);
        abstractBufferedEncoder.flush();
    }
}
