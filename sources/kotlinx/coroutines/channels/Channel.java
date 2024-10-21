package kotlinx.coroutines.channels;

import com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Channel extends SendChannel, ReceiveChannel {
    public static final Factory Factory = Factory.$$INSTANCE;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Factory {
        static final /* synthetic */ Factory $$INSTANCE = new Factory();
        public static final int CHANNEL_DEFAULT_CAPACITY = TextDetectionOptionalModuleOptions.systemProp("kotlinx.coroutines.channels.defaultBuffer", 64, 1, 2147483646);

        private Factory() {
        }
    }
}
