package io.grpc;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MethodDescriptor {
    public final String fullMethodName;
    public final Marshaller requestMarshaller;
    public final Marshaller responseMarshaller;
    private final boolean sampledToLocalTracing;
    public final String serviceName;
    public final MethodType type;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public String fullMethodName;
        public Marshaller requestMarshaller;
        public Marshaller responseMarshaller;
        private boolean sampledToLocalTracing;
        public MethodType type;

        public final MethodDescriptor build() {
            return new MethodDescriptor(this.type, this.fullMethodName, this.requestMarshaller, this.responseMarshaller, this.sampledToLocalTracing);
        }

        public final void setSampledToLocalTracing$ar$ds() {
            this.sampledToLocalTracing = true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Marshaller {
        Object parse(InputStream inputStream);

        InputStream stream(Object obj);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum MethodType {
        UNARY,
        CLIENT_STREAMING,
        SERVER_STREAMING,
        BIDI_STREAMING,
        UNKNOWN
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ReflectableMarshaller extends Marshaller {
        Class getMessageClass();
    }

    public MethodDescriptor(MethodType methodType, String str, Marshaller marshaller, Marshaller marshaller2, boolean z) {
        String substring;
        new AtomicReferenceArray(2);
        methodType.getClass();
        this.type = methodType;
        str.getClass();
        this.fullMethodName = str;
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf == -1) {
            substring = null;
        } else {
            substring = str.substring(0, lastIndexOf);
        }
        this.serviceName = substring;
        marshaller.getClass();
        this.requestMarshaller = marshaller;
        marshaller2.getClass();
        this.responseMarshaller = marshaller2;
        this.sampledToLocalTracing = z;
    }

    public static String generateFullMethodName(String str, String str2) {
        str.getClass();
        str2.getClass();
        return str + "/" + str2;
    }

    public static Builder newBuilder() {
        Builder builder = new Builder();
        builder.requestMarshaller = null;
        builder.responseMarshaller = null;
        return builder;
    }

    public final InputStream streamRequest(Object obj) {
        return this.requestMarshaller.stream(obj);
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("fullMethodName", this.fullMethodName);
        stringHelper.addHolder$ar$ds("type", this.type);
        MoreObjects$ToStringHelper add = stringHelper.add("idempotent", false).add("safe", false).add("sampledToLocalTracing", this.sampledToLocalTracing);
        add.addHolder$ar$ds("requestMarshaller", this.requestMarshaller);
        add.addHolder$ar$ds("responseMarshaller", this.responseMarshaller);
        add.addHolder$ar$ds("schemaDescriptor", null);
        add.omitNullValues$ar$ds();
        return add.toString();
    }
}
