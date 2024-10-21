package com.google.protobuf;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import io.grpc.okhttp.internal.framed.Settings;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite<MessageType, BuilderType> {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Object, GeneratedMessageLite<?, ?>> defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize = -1;
    public UnknownFieldSetLite unknownFields = UnknownFieldSetLite.DEFAULT_INSTANCE;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite.Builder<MessageType, BuilderType> {
        private final MessageType defaultInstance;
        public MessageType instance;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(MessageType messagetype) {
            this.defaultInstance = messagetype;
            if (!messagetype.isMutable()) {
                this.instance = newMutableInstance();
                return;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        private static <MessageType> void mergeFromInstance(MessageType messagetype, MessageType messagetype2) {
            Protobuf.INSTANCE.schemaFor(messagetype).mergeFrom(messagetype, messagetype2);
        }

        private MessageType newMutableInstance() {
            return (MessageType) this.defaultInstance.newMutableInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public final MessageType build() {
            MessageType buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public MessageType buildPartial() {
            if (!this.instance.isMutable()) {
                return this.instance;
            }
            this.instance.makeImmutable();
            return this.instance;
        }

        public final BuilderType clear() {
            if (!this.defaultInstance.isMutable()) {
                this.instance = newMutableInstance();
                return this;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        public final void copyOnWrite() {
            if (!this.instance.isMutable()) {
                copyOnWriteInternal();
            }
        }

        protected void copyOnWriteInternal() {
            MessageType newMutableInstance = newMutableInstance();
            mergeFromInstance(newMutableInstance, this.instance);
            this.instance = newMutableInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public MessageType getDefaultInstanceForType() {
            return this.defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return GeneratedMessageLite.isInitialized(this.instance, false);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public BuilderType mo227clone() {
            BuilderType buildertype = (BuilderType) getDefaultInstanceForType().newBuilderForType();
            buildertype.instance = buildPartial();
            return buildertype;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.AbstractMessageLite.Builder
        public BuilderType internalMergeFrom(MessageType messagetype) {
            return mergeFrom((Builder<MessageType, BuilderType>) messagetype);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            copyOnWrite();
            try {
                Protobuf.INSTANCE.schemaFor(this.instance).mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(this.instance, Settings.forCodedInput$ar$class_merging(codedInputStream), extensionRegistryLite);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        /* renamed from: clear, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ MessageLite.Builder m240clear() {
            clear();
            return this;
        }

        public BuilderType mergeFrom(MessageType messagetype) {
            if (getDefaultInstanceForType().equals(messagetype)) {
                return this;
            }
            copyOnWrite();
            mergeFromInstance(this.instance, messagetype);
            return this;
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType mo234mergeFrom(byte[] bArr, int i, int i2) {
            ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
            Protobuf protobuf = Protobuf.INSTANCE;
            return mo235mergeFrom(bArr, i, i2, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] */
        public BuilderType mo235mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
            copyOnWrite();
            try {
                Protobuf.INSTANCE.schemaFor(this.instance).mergeFrom(this.instance, bArr, i, i + i2, new ArrayDecoders$Registers(extensionRegistryLite));
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException unused) {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DefaultInstanceBasedParser extends AbstractParser {
        private final GeneratedMessageLite defaultInstance;

        public DefaultInstanceBasedParser(GeneratedMessageLite generatedMessageLite) {
            this.defaultInstance = generatedMessageLite;
        }

        @Override // com.google.protobuf.Parser
        public final /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser
        public final /* bridge */ /* synthetic */ MessageLite parsePartialFrom$ar$ds(byte[] bArr, int i, ExtensionRegistryLite extensionRegistryLite) {
            return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, bArr, 1, i, extensionRegistryLite);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> implements MessageLiteOrBuilder {
        /* JADX INFO: Access modifiers changed from: protected */
        public ExtendableBuilder(ExtendableMessage extendableMessage) {
            super(extendableMessage);
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Builder, com.google.protobuf.MessageLite.Builder
        public final ExtendableMessage buildPartial() {
            if (!((ExtendableMessage) this.instance).isMutable()) {
                return (ExtendableMessage) this.instance;
            }
            ((ExtendableMessage) this.instance).extensions.makeImmutable();
            return (ExtendableMessage) super.buildPartial();
        }

        @Override // com.google.protobuf.GeneratedMessageLite.Builder
        protected final void copyOnWriteInternal() {
            super.copyOnWriteInternal();
            if (((ExtendableMessage) this.instance).extensions != FieldSet.DEFAULT_INSTANCE) {
                ExtendableMessage extendableMessage = (ExtendableMessage) this.instance;
                extendableMessage.extensions = extendableMessage.extensions.m237clone();
            }
        }

        public final void setExtension$ar$ds(ExtensionLite extensionLite, Object obj) {
            GeneratedExtension checkIsLite = GeneratedMessageLite.checkIsLite(extensionLite);
            if (checkIsLite.containingTypeDefaultInstance == getDefaultInstanceForType()) {
                copyOnWrite();
                FieldSet fieldSet = ((ExtendableMessage) this.instance).extensions;
                if (fieldSet.isImmutable) {
                    fieldSet = fieldSet.m237clone();
                    ((ExtendableMessage) this.instance).extensions = fieldSet;
                }
                ExtensionDescriptor extensionDescriptor = checkIsLite.descriptor;
                if (extensionDescriptor.isRepeated) {
                    if (extensionDescriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = ((List) obj).iterator();
                        while (it.hasNext()) {
                            arrayList.add(checkIsLite.singularToFieldSetType(it.next()));
                        }
                        obj = arrayList;
                    }
                } else {
                    obj = checkIsLite.singularToFieldSetType(obj);
                }
                fieldSet.setField$ar$class_merging(extensionDescriptor, obj);
                return;
            }
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends GeneratedMessageLite<MessageType, BuilderType> implements MessageLiteOrBuilder {
        public FieldSet extensions = FieldSet.DEFAULT_INSTANCE;

        public final FieldSet ensureExtensionsAreMutable() {
            FieldSet fieldSet = this.extensions;
            if (fieldSet.isImmutable) {
                this.extensions = fieldSet.m237clone();
            }
            return this.extensions;
        }

        public final void verifyExtensionContainingType(GeneratedExtension generatedExtension) {
            if (generatedExtension.containingTypeDefaultInstance == getDefaultInstanceForType()) {
            } else {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ExtensionDescriptor implements Comparable {
        public final Internal.EnumLiteMap enumTypeMap;
        public final boolean isPacked;
        public final boolean isRepeated;
        public final int number;
        public final WireFormat.FieldType type;

        public ExtensionDescriptor(Internal.EnumLiteMap enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, boolean z2) {
            this.enumTypeMap = enumLiteMap;
            this.number = i;
            this.type = fieldType;
            this.isRepeated = z;
            this.isPacked = z2;
        }

        public static final MutableMessageLite internalMergeFrom$ar$ds$bad72047_0() {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return this.number - ((ExtensionDescriptor) obj).number;
        }

        public final WireFormat.JavaType getLiteJavaType() {
            return this.type.javaType;
        }

        public final WireFormat.FieldType getLiteType() {
            return this.type;
        }

        public final int getNumber() {
            return this.number;
        }

        public final boolean isPacked() {
            return this.isPacked;
        }

        public final boolean isRepeated() {
            return this.isRepeated;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GeneratedExtension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {
        public final MessageLite containingTypeDefaultInstance;
        public final Object defaultValue;
        public final ExtensionDescriptor descriptor;
        public final MessageLite messageDefaultInstance;

        public GeneratedExtension(MessageLite messageLite, Object obj, MessageLite messageLite2, ExtensionDescriptor extensionDescriptor) {
            if (messageLite != null) {
                if (extensionDescriptor.type == WireFormat.FieldType.MESSAGE && messageLite2 == null) {
                    throw new IllegalArgumentException("Null messageDefaultInstance");
                }
                this.containingTypeDefaultInstance = messageLite;
                this.defaultValue = obj;
                this.messageDefaultInstance = messageLite2;
                this.descriptor = extensionDescriptor;
                return;
            }
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }

        public final Object fromFieldSetType(Object obj) {
            ExtensionDescriptor extensionDescriptor = this.descriptor;
            if (extensionDescriptor.isRepeated) {
                if (extensionDescriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = ((List) obj).iterator();
                    while (it.hasNext()) {
                        arrayList.add(singularFromFieldSetType(it.next()));
                    }
                    return arrayList;
                }
                return obj;
            }
            return singularFromFieldSetType(obj);
        }

        public final WireFormat.FieldType getLiteType() {
            return this.descriptor.type;
        }

        public final int getNumber() {
            return this.descriptor.number;
        }

        public final boolean isRepeated() {
            return this.descriptor.isRepeated;
        }

        final Object singularFromFieldSetType(Object obj) {
            if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                ExtensionDescriptor extensionDescriptor = this.descriptor;
                ((Integer) obj).intValue();
                return extensionDescriptor.enumTypeMap.findValueByNumber$ar$ds();
            }
            return obj;
        }

        final Object singularToFieldSetType(Object obj) {
            if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                return Integer.valueOf(((Internal.EnumLite) obj).getNumber());
            }
            return obj;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum MethodToInvoke {
        GET_MEMOIZED_IS_INITIALIZED,
        SET_MEMOIZED_IS_INITIALIZED,
        BUILD_MESSAGE_INFO,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>, T> GeneratedExtension<MessageType, T> checkIsLite(ExtensionLite<MessageType, T> extensionLite) {
        return (GeneratedExtension) extensionLite;
    }

    private static <T extends GeneratedMessageLite<T, ?>> T checkMessageInitialized(T t) {
        if (t != null && !t.isInitialized()) {
            throw t.newUninitializedMessageException().asInvalidProtocolBufferException();
        }
        return t;
    }

    private int computeSerializedSize(Schema<?> schema) {
        if (schema == null) {
            return Protobuf.INSTANCE.schemaFor(this).getSerializedSize(this);
        }
        return schema.getSerializedSize(this);
    }

    protected static Internal.BooleanList emptyBooleanList() {
        return BooleanArrayList.EMPTY_LIST;
    }

    protected static Internal.DoubleList emptyDoubleList() {
        return DoubleArrayList.EMPTY_LIST;
    }

    protected static Internal.FloatList emptyFloatList() {
        return FloatArrayList.EMPTY_LIST;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Internal.IntList emptyIntList() {
        return IntArrayList.EMPTY_LIST;
    }

    public static Internal.LongList emptyLongList() {
        return LongArrayList.EMPTY_LIST;
    }

    public static <E> Internal.ProtobufList<E> emptyProtobufList() {
        return ProtobufArrayList.EMPTY_LIST;
    }

    private void ensureUnknownFieldsInitialized() {
        if (this.unknownFields == UnknownFieldSetLite.DEFAULT_INSTANCE) {
            this.unknownFields = new UnknownFieldSetLite();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends GeneratedMessageLite> T getDefaultInstance(Class<T> cls) {
        GeneratedMessageLite<?, ?> generatedMessageLite = defaultInstanceMap.get(cls);
        if (generatedMessageLite == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                generatedMessageLite = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (generatedMessageLite == null) {
            generatedMessageLite = ((GeneratedMessageLite) UnsafeUtil.allocateInstance(cls)).getDefaultInstanceForType();
            if (generatedMessageLite != null) {
                defaultInstanceMap.put(cls, generatedMessageLite);
            } else {
                throw new IllegalStateException();
            }
        }
        return generatedMessageLite;
    }

    static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
            throw ((RuntimeException) cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object newMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        return new RawMessageInfo(messageLite, str, objArr);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingtype, MessageLite messageLite, Internal.EnumLiteMap enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, Class cls) {
        return new GeneratedExtension<>(containingtype, Collections.emptyList(), messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, true, z));
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, Internal.EnumLiteMap enumLiteMap, int i, WireFormat.FieldType fieldType, Class cls) {
        return new GeneratedExtension<>(containingtype, type, messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, false, false));
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T t, InputStream inputStream) {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        T t2 = (T) parsePartialDelimitedFrom(t, inputStream, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        checkMessageInitialized(t2);
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, ByteString byteString) {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        T t2 = (T) parseFrom(t, byteString, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        checkMessageInitialized(t2);
        return t2;
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialDelimitedFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        try {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            CodedInputStream newInstance = CodedInputStream.newInstance(new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(read, inputStream)));
            T t2 = (T) parsePartialFrom(t, newInstance, extensionRegistryLite);
            newInstance.checkLastTagWas(0);
            return t2;
        } catch (InvalidProtocolBufferException e) {
            if (e.wasThrownFromInputStream) {
                throw new InvalidProtocolBufferException(e);
            }
            throw e;
        } catch (IOException e2) {
            throw new InvalidProtocolBufferException(e2);
        }
    }

    private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream newCodedInput = byteString.newCodedInput();
        T t2 = (T) parsePartialFrom(t, newCodedInput, extensionRegistryLite);
        newCodedInput.checkLastTagWas(0);
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends GeneratedMessageLite> void registerDefaultInstance(Class<T> cls, T t) {
        t.markImmutable();
        defaultInstanceMap.put(cls, t);
    }

    public Object buildMessageInfo() {
        return dynamicMethod(MethodToInvoke.BUILD_MESSAGE_INFO);
    }

    public void clearMemoizedHashCode() {
        this.memoizedHashCode = 0;
    }

    public void clearMemoizedSerializedSize() {
        setMemoizedSerializedSize(Preference.DEFAULT_ORDER);
    }

    public int computeHashCode() {
        return Protobuf.INSTANCE.schemaFor(this).hashCode(this);
    }

    public final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder() {
        return (BuilderType) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    protected Object dynamicMethod(MethodToInvoke methodToInvoke) {
        return dynamicMethod(methodToInvoke, null, null);
    }

    protected abstract Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Protobuf.INSTANCE.schemaFor(this).equals(this, (GeneratedMessageLite) obj);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public final MessageType getDefaultInstanceForType() {
        return (MessageType) dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    public int getMemoizedHashCode() {
        return this.memoizedHashCode;
    }

    @Override // com.google.protobuf.AbstractMessageLite
    public int getMemoizedSerializedSize() {
        return this.memoizedSerializedSize & Preference.DEFAULT_ORDER;
    }

    @Override // com.google.protobuf.MessageLite
    public final Parser<MessageType> getParserForType() {
        return (Parser) dynamicMethod(MethodToInvoke.GET_PARSER);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        return getSerializedSize(null);
    }

    public int hashCode() {
        if (isMutable()) {
            return computeHashCode();
        }
        if (hashCodeIsNotMemoized()) {
            setMemoizedHashCode(computeHashCode());
        }
        return getMemoizedHashCode();
    }

    public boolean hashCodeIsNotMemoized() {
        if (getMemoizedHashCode() == 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        return isInitialized(this, true);
    }

    public boolean isMutable() {
        if ((this.memoizedSerializedSize & MUTABLE_FLAG_MASK) != 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void makeImmutable() {
        Protobuf.INSTANCE.schemaFor(this).makeImmutable(this);
        markImmutable();
    }

    public void markImmutable() {
        this.memoizedSerializedSize &= Preference.DEFAULT_ORDER;
    }

    protected void mergeLengthDelimitedField(int i, ByteString byteString) {
        ensureUnknownFieldsInitialized();
        UnknownFieldSetLite unknownFieldSetLite = this.unknownFields;
        unknownFieldSetLite.checkMutable();
        if (i != 0) {
            unknownFieldSetLite.storeField(WireFormat.makeTag(i, 2), byteString);
            return;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    protected final void mergeUnknownFields(UnknownFieldSetLite unknownFieldSetLite) {
        this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, unknownFieldSetLite);
    }

    protected void mergeVarintField(int i, int i2) {
        ensureUnknownFieldsInitialized();
        UnknownFieldSetLite unknownFieldSetLite = this.unknownFields;
        unknownFieldSetLite.checkMutable();
        if (i != 0) {
            unknownFieldSetLite.storeField(WireFormat.makeTag(i, 0), Long.valueOf(i2));
            return;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    @Override // com.google.protobuf.AbstractMessageLite
    public MutableMessageLite mutableCopy() {
        throw new UnsupportedOperationException("Lite does not support the mutable API.");
    }

    @Override // com.google.protobuf.MessageLite
    public final BuilderType newBuilderForType() {
        return (BuilderType) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    public MessageType newMutableInstance() {
        return (MessageType) dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
    }

    protected boolean parseUnknownField(int i, CodedInputStream codedInputStream) {
        if (WireFormat.getTagWireType(i) == 4) {
            return false;
        }
        ensureUnknownFieldsInitialized();
        return this.unknownFields.mergeFieldFrom(i, codedInputStream);
    }

    public void setMemoizedHashCode(int i) {
        this.memoizedHashCode = i;
    }

    @Override // com.google.protobuf.AbstractMessageLite
    public void setMemoizedSerializedSize(int i) {
        if (i >= 0) {
            this.memoizedSerializedSize = i | (this.memoizedSerializedSize & MUTABLE_FLAG_MASK);
            return;
        }
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "serialized size must be non-negative, was "));
    }

    @Override // com.google.protobuf.MessageLite
    public final BuilderType toBuilder() {
        return (BuilderType) ((Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER)).mergeFrom((Builder) this);
    }

    public String toString() {
        String obj = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(obj);
        MessageLiteToString.reflectivePrintWithIndent(this, sb, 0);
        return sb.toString();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) {
        Schema schemaFor = Protobuf.INSTANCE.schemaFor(this);
        ExecutorSelector executorSelector = codedOutputStream.wrapper$ar$class_merging$36e28e02_0$ar$class_merging;
        if (executorSelector == null) {
            executorSelector = new ExecutorSelector(codedOutputStream);
        }
        schemaFor.writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging(this, executorSelector);
    }

    protected static final <T extends GeneratedMessageLite<T, ?>> boolean isInitialized(T t, boolean z) {
        byte byteValue = ((Byte) t.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean isInitialized = Protobuf.INSTANCE.schemaFor(t).isInitialized(t);
        if (z) {
            t.dynamicMethod(MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED, true != isInitialized ? null : t);
        }
        return isInitialized;
    }

    protected static Internal.BooleanList mutableCopy(Internal.BooleanList booleanList) {
        int size = booleanList.size();
        return booleanList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder(MessageType messagetype) {
        return (BuilderType) createBuilder().mergeFrom(messagetype);
    }

    protected Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj) {
        return dynamicMethod(methodToInvoke, obj, null);
    }

    @Override // com.google.protobuf.AbstractMessageLite
    public int getSerializedSize(Schema schema) {
        if (isMutable()) {
            int computeSerializedSize = computeSerializedSize(schema);
            if (computeSerializedSize >= 0) {
                return computeSerializedSize;
            }
            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(computeSerializedSize, "serialized size must be non-negative, was "));
        }
        if (getMemoizedSerializedSize() != Integer.MAX_VALUE) {
            return getMemoizedSerializedSize();
        }
        int computeSerializedSize2 = computeSerializedSize(schema);
        setMemoizedSerializedSize(computeSerializedSize2);
        return computeSerializedSize2;
    }

    protected static Internal.DoubleList mutableCopy(Internal.DoubleList doubleList) {
        int size = doubleList.size();
        return doubleList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, CodedInputStream codedInputStream) {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        return (T) parsePartialFrom(t, codedInputStream, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        T t2 = (T) parsePartialDelimitedFrom(t, inputStream, extensionRegistryLite);
        checkMessageInitialized(t2);
        return t2;
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        T t2 = (T) parsePartialFrom(t, byteString, extensionRegistryLite);
        checkMessageInitialized(t2);
        return t2;
    }

    protected static Internal.FloatList mutableCopy(Internal.FloatList floatList) {
        int size = floatList.size();
        return floatList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, CodedInputStream codedInputStream) {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        return (T) parseFrom(t, codedInputStream, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
    }

    static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        T t2 = (T) t.newMutableInstance();
        try {
            Schema schemaFor = Protobuf.INSTANCE.schemaFor(t2);
            schemaFor.mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(t2, Settings.forCodedInput$ar$class_merging(codedInputStream), extensionRegistryLite);
            schemaFor.makeImmutable(t2);
            return t2;
        } catch (InvalidProtocolBufferException e) {
            if (e.wasThrownFromInputStream) {
                throw new InvalidProtocolBufferException(e);
            }
            throw e;
        } catch (UninitializedMessageException e2) {
            throw e2.asInvalidProtocolBufferException();
        } catch (IOException e3) {
            if (e3.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e3.getCause());
            }
            throw new InvalidProtocolBufferException(e3);
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e4.getCause());
            }
            throw e4;
        }
    }

    public static Internal.IntList mutableCopy(Internal.IntList intList) {
        int size = intList.size();
        return intList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        T t2 = (T) parsePartialFrom(t, codedInputStream, extensionRegistryLite);
        checkMessageInitialized(t2);
        return t2;
    }

    public static Internal.LongList mutableCopy(Internal.LongList longList) {
        int size = longList.size();
        return longList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, InputStream inputStream) {
        CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        T t2 = (T) parsePartialFrom(t, newInstance, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        checkMessageInitialized(t2);
        return t2;
    }

    public static <E> Internal.ProtobufList<E> mutableCopy(Internal.ProtobufList<E> protobufList) {
        int size = protobufList.size();
        return protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        T t2 = (T) parsePartialFrom(t, CodedInputStream.newInstance(inputStream), extensionRegistryLite);
        checkMessageInitialized(t2);
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, ByteBuffer byteBuffer) {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        return (T) parseFrom(t, byteBuffer, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream newInstance;
        int i = CodedInputStream.CodedInputStream$ar$NoOp;
        if (byteBuffer.hasArray()) {
            newInstance = CodedInputStream.newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), false);
        } else if (byteBuffer.isDirect() && UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS) {
            newInstance = new CodedInputStream.UnsafeDirectNioDecoder(byteBuffer);
        } else {
            int remaining = byteBuffer.remaining();
            byte[] bArr = new byte[remaining];
            byteBuffer.duplicate().get(bArr);
            newInstance = CodedInputStream.newInstance(bArr, 0, remaining, true);
        }
        T t2 = (T) parseFrom(t, newInstance, extensionRegistryLite);
        checkMessageInitialized(t2);
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T t, byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
        if (i2 == 0) {
            return t;
        }
        T t2 = (T) t.newMutableInstance();
        try {
            Schema schemaFor = Protobuf.INSTANCE.schemaFor(t2);
            schemaFor.mergeFrom(t2, bArr, i, i + i2, new ArrayDecoders$Registers(extensionRegistryLite));
            schemaFor.makeImmutable(t2);
            return t2;
        } catch (InvalidProtocolBufferException e) {
            if (e.wasThrownFromInputStream) {
                throw new InvalidProtocolBufferException(e);
            }
            throw e;
        } catch (UninitializedMessageException e2) {
            throw e2.asInvalidProtocolBufferException();
        } catch (IOException e3) {
            if (e3.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e3.getCause());
            }
            throw new InvalidProtocolBufferException(e3);
        } catch (IndexOutOfBoundsException unused) {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, byte[] bArr) {
        int length = bArr.length;
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        T t2 = (T) parsePartialFrom(t, bArr, 0, length, ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        checkMessageInitialized(t2);
        return t2;
    }

    public static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T t, byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        T t2 = (T) parsePartialFrom(t, bArr, 0, bArr.length, extensionRegistryLite);
        checkMessageInitialized(t2);
        return t2;
    }
}
