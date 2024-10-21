package logs.proto.wireless.performance.mobile;

import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExtensionMetric$MetricExtension extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final ExtensionMetric$MetricExtension DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private byte memoizedIsInitialized = 2;

    static {
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension = new ExtensionMetric$MetricExtension();
        DEFAULT_INSTANCE = extensionMetric$MetricExtension;
        GeneratedMessageLite.registerDefaultInstance(ExtensionMetric$MetricExtension.class, extensionMetric$MetricExtension);
    }

    private ExtensionMetric$MetricExtension() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        byte b;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return Byte.valueOf(this.memoizedIsInitialized);
            case SET_MEMOIZED_IS_INITIALIZED:
                if (obj == null) {
                    b = 0;
                } else {
                    b = 1;
                }
                this.memoizedIsInitialized = b;
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case NEW_MUTABLE_INSTANCE:
                return new ExtensionMetric$MetricExtension();
            case NEW_BUILDER:
                return new SchedulerOptions.Builder((int[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ExtensionMetric$MetricExtension.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
