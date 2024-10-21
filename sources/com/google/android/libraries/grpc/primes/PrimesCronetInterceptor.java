package com.google.android.libraries.grpc.primes;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;
import io.grpc.cronet.CronetClientStream;
import io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesCronetInterceptor implements ClientInterceptor {
    private static final SpannableUtils$NonCopyableTextSpan annotation$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    public static volatile PrimesCronetInterceptor instance;
    private final MetadataUtils$HeaderAttachingClientInterceptor delegate$ar$class_merging$202585dc_0;

    public PrimesCronetInterceptor(MetadataUtils$HeaderAttachingClientInterceptor metadataUtils$HeaderAttachingClientInterceptor) {
        metadataUtils$HeaderAttachingClientInterceptor.getClass();
        this.delegate$ar$class_merging$202585dc_0 = metadataUtils$HeaderAttachingClientInterceptor;
    }

    @Override // io.grpc.ClientInterceptor
    public final ClientCall interceptCall(MethodDescriptor methodDescriptor, CallOptions callOptions, Channel channel) {
        ArrayList arrayList;
        SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = annotation$ar$class_merging$ar$class_merging;
        Collection collection = (Collection) callOptions.getOption(CronetClientStream.CRONET_ANNOTATIONS_KEY);
        if (collection == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = new ArrayList(collection);
        }
        MetadataUtils$HeaderAttachingClientInterceptor metadataUtils$HeaderAttachingClientInterceptor = this.delegate$ar$class_merging$202585dc_0;
        arrayList.add(spannableUtils$NonCopyableTextSpan);
        return metadataUtils$HeaderAttachingClientInterceptor.interceptCall(methodDescriptor, callOptions.withOption(CronetClientStream.CRONET_ANNOTATIONS_KEY, DesugarCollections.unmodifiableList(arrayList)), channel);
    }
}
