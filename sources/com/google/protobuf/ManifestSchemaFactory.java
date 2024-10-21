package com.google.protobuf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManifestSchemaFactory {
    private static final MessageInfoFactory EMPTY_FACTORY = new AnonymousClass1(0);
    public final MessageInfoFactory messageInfoFactory;

    /* compiled from: PG */
    /* renamed from: com.google.protobuf.ManifestSchemaFactory$1, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass1 implements MessageInfoFactory {
        public static final AnonymousClass1 instance$ar$class_merging$34006ca3_0 = new AnonymousClass1(1);
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public final boolean isSupported(Class cls) {
            if (this.switching_field != 0) {
                return GeneratedMessageLite.class.isAssignableFrom(cls);
            }
            return false;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public final MessageInfo messageInfoFor(Class cls) {
            if (this.switching_field != 0) {
                if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
                    try {
                        return (MessageInfo) GeneratedMessageLite.getDefaultInstance(cls.asSubclass(GeneratedMessageLite.class)).buildMessageInfo();
                    } catch (Exception e) {
                        throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
                    }
                }
                throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
            }
            throw new IllegalStateException("This should never be called.");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CompositeMessageInfoFactory implements MessageInfoFactory {
        private final MessageInfoFactory[] factories;

        public CompositeMessageInfoFactory(MessageInfoFactory... messageInfoFactoryArr) {
            this.factories = messageInfoFactoryArr;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public final boolean isSupported(Class cls) {
            for (int i = 0; i < 2; i++) {
                if (this.factories[i].isSupported(cls)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.protobuf.MessageInfoFactory
        public final MessageInfo messageInfoFor(Class cls) {
            for (int i = 0; i < 2; i++) {
                MessageInfoFactory messageInfoFactory = this.factories[i];
                if (messageInfoFactory.isSupported(cls)) {
                    return messageInfoFactory.messageInfoFor(cls);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public ManifestSchemaFactory() {
        MessageInfoFactory messageInfoFactory = EMPTY_FACTORY;
        Protobuf protobuf = Protobuf.INSTANCE;
        CompositeMessageInfoFactory compositeMessageInfoFactory = new CompositeMessageInfoFactory(AnonymousClass1.instance$ar$class_merging$34006ca3_0, messageInfoFactory);
        byte[] bArr = Internal.EMPTY_BYTE_ARRAY;
        this.messageInfoFactory = compositeMessageInfoFactory;
    }

    public static boolean allowExtensions(MessageInfo messageInfo) {
        if (messageInfo.getSyntax$ar$edu() - 1 != 1) {
            return true;
        }
        return false;
    }
}
