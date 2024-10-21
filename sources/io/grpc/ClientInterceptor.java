package io.grpc;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ClientInterceptor {
    ClientCall interceptCall(MethodDescriptor methodDescriptor, CallOptions callOptions, Channel channel);
}
