package com.google.mlkit.logging.schema;

import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class OnDeviceFaceMeshCreateLogEvent {
    public static Channel intercept(final Channel channel, List list) {
        channel.getClass();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final ClientInterceptor clientInterceptor = (ClientInterceptor) it.next();
            channel = new Channel(channel, clientInterceptor) { // from class: io.grpc.ClientInterceptors$InterceptorChannel
                private final Channel channel;
                private final ClientInterceptor interceptor;

                {
                    this.channel = channel;
                    clientInterceptor.getClass();
                    this.interceptor = clientInterceptor;
                }

                @Override // io.grpc.Channel
                public final String authority() {
                    return this.channel.authority();
                }

                @Override // io.grpc.Channel
                public final ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
                    return this.interceptor.interceptCall(methodDescriptor, callOptions, this.channel);
                }
            };
        }
        return channel;
    }

    public static Channel interceptForward(Channel channel, List list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.reverse(arrayList);
        return intercept(channel, arrayList);
    }

    public void onClose(Status status, Metadata metadata) {
        throw null;
    }

    public void onHeaders(Metadata metadata) {
        throw null;
    }

    public void onMessage(Object obj) {
        throw null;
    }

    public static Channel intercept(Channel channel, ClientInterceptor... clientInterceptorArr) {
        return intercept(channel, Arrays.asList(clientInterceptorArr));
    }

    public void onReady() {
    }
}
