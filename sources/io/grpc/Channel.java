package io.grpc;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Channel {
    public abstract String authority();

    public abstract ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions);
}
