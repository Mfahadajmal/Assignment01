package com.google.android.libraries.storage.file.openers;

import com.google.android.libraries.storage.file.Opener;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import java.io.InputStream;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadProtoOpener implements Opener {
    private final Parser parser;
    private final ExtensionRegistryLite registry;

    private ReadProtoOpener(Parser parser) {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        this.registry = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        this.parser = parser;
    }

    public static ReadProtoOpener create(MessageLite messageLite) {
        return new ReadProtoOpener(messageLite.getParserForType());
    }

    @Override // com.google.android.libraries.storage.file.Opener
    public final /* bridge */ /* synthetic */ Object open$ar$class_merging$a9b907d0_0$ar$class_merging(WorkQueue workQueue) {
        InputStream open$ar$ds$ar$class_merging$ar$class_merging = ReadStreamOpener.open$ar$ds$ar$class_merging$ar$class_merging(workQueue);
        try {
            Object parseFrom = this.parser.parseFrom(open$ar$ds$ar$class_merging$ar$class_merging, this.registry);
            if (open$ar$ds$ar$class_merging$ar$class_merging != null) {
                open$ar$ds$ar$class_merging$ar$class_merging.close();
            }
            return parseFrom;
        } catch (Throwable th) {
            if (open$ar$ds$ar$class_merging$ar$class_merging != null) {
                try {
                    open$ar$ds$ar$class_merging$ar$class_merging.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
