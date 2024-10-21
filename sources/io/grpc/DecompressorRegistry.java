package io.grpc;

import com.google.common.base.Joiner;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.Codec;
import io.grpc.internal.RetriableStream;
import j$.util.DesugarCollections;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DecompressorRegistry {
    static final Joiner ACCEPT_ENCODING_JOINER = new Joiner(String.valueOf(','));
    public static final DecompressorRegistry DEFAULT_INSTANCE = new DecompressorRegistry(Codec.Identity.NONE, false, new DecompressorRegistry(new Codec.Identity(1), true, new DecompressorRegistry()));
    public final byte[] advertisedDecompressors;
    public final Map decompressors;

    private DecompressorRegistry() {
        this.decompressors = new LinkedHashMap(0);
        this.advertisedDecompressors = new byte[0];
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [io.grpc.Decompressor, java.lang.Object] */
    private DecompressorRegistry(Decompressor decompressor, boolean z, DecompressorRegistry decompressorRegistry) {
        String messageEncoding = decompressor.getMessageEncoding();
        ContextDataProvider.checkArgument(!messageEncoding.contains(","), (Object) "Comma is currently not allowed in message encoding");
        int size = decompressorRegistry.decompressors.size();
        LinkedHashMap linkedHashMap = new LinkedHashMap(decompressorRegistry.decompressors.containsKey(decompressor.getMessageEncoding()) ? size : size + 1);
        for (RetriableStream.HedgingPlan hedgingPlan : decompressorRegistry.decompressors.values()) {
            String messageEncoding2 = hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis.getMessageEncoding();
            if (!messageEncoding2.equals(messageEncoding)) {
                linkedHashMap.put(messageEncoding2, new RetriableStream.HedgingPlan(hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis, hedgingPlan.isHedgeable));
            }
        }
        linkedHashMap.put(messageEncoding, new RetriableStream.HedgingPlan(decompressor, z));
        Map unmodifiableMap = DesugarCollections.unmodifiableMap(linkedHashMap);
        this.decompressors = unmodifiableMap;
        Joiner joiner = ACCEPT_ENCODING_JOINER;
        HashSet hashSet = new HashSet(unmodifiableMap.size());
        for (Map.Entry entry : unmodifiableMap.entrySet()) {
            if (((RetriableStream.HedgingPlan) entry.getValue()).isHedgeable) {
                hashSet.add((String) entry.getKey());
            }
        }
        this.advertisedDecompressors = joiner.join(DesugarCollections.unmodifiableSet(hashSet)).getBytes(Charset.forName("US-ASCII"));
    }
}
