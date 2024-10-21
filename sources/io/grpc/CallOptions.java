package io.grpc;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceFaceDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent;
import j$.util.DesugarCollections;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallOptions {
    public static final CallOptions DEFAULT;
    public final OnDeviceFaceDetectionLogEvent credentials$ar$class_merging;
    private final Object[][] customOptions;
    public final Deadline deadline;
    public final Executor executor;
    public final Integer maxInboundMessageSize;
    public final Integer maxOutboundMessageSize;
    public final List streamTracerFactories;
    private final Boolean waitForReady;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public OnDeviceFaceDetectionLogEvent credentials$ar$class_merging;
        Object[][] customOptions;
        public Deadline deadline;
        public Executor executor;
        Integer maxInboundMessageSize;
        Integer maxOutboundMessageSize;
        List streamTracerFactories;
        public Boolean waitForReady;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Key {
        private final String debugString;
        public final Object defaultValue = null;

        public Key(String str, Object obj) {
            this.debugString = str;
        }

        public final String toString() {
            return this.debugString;
        }
    }

    static {
        Builder builder = new Builder();
        builder.customOptions = (Object[][]) Array.newInstance((Class<?>) Object.class, 0, 2);
        builder.streamTracerFactories = Collections.emptyList();
        DEFAULT = new CallOptions(builder);
    }

    public CallOptions(Builder builder) {
        this.deadline = builder.deadline;
        this.executor = builder.executor;
        this.credentials$ar$class_merging = builder.credentials$ar$class_merging;
        this.customOptions = builder.customOptions;
        this.streamTracerFactories = builder.streamTracerFactories;
        this.waitForReady = builder.waitForReady;
        this.maxInboundMessageSize = builder.maxInboundMessageSize;
        this.maxOutboundMessageSize = builder.maxOutboundMessageSize;
    }

    public static Builder toBuilder(CallOptions callOptions) {
        Builder builder = new Builder();
        builder.deadline = callOptions.deadline;
        builder.executor = callOptions.executor;
        builder.credentials$ar$class_merging = callOptions.credentials$ar$class_merging;
        builder.customOptions = callOptions.customOptions;
        builder.streamTracerFactories = callOptions.streamTracerFactories;
        builder.waitForReady = callOptions.waitForReady;
        builder.maxInboundMessageSize = callOptions.maxInboundMessageSize;
        builder.maxOutboundMessageSize = callOptions.maxOutboundMessageSize;
        return builder;
    }

    public final Object getOption(Key key) {
        key.getClass();
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i < objArr.length) {
                if (key.equals(objArr[i][0])) {
                    return this.customOptions[i][1];
                }
                i++;
            } else {
                return null;
            }
        }
    }

    public final boolean isWaitForReady() {
        return Boolean.TRUE.equals(this.waitForReady);
    }

    public final String toString() {
        Class<?> cls;
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("deadline", this.deadline);
        stringHelper.addHolder$ar$ds("authority", null);
        stringHelper.addHolder$ar$ds("callCredentials", this.credentials$ar$class_merging);
        Executor executor = this.executor;
        if (executor != null) {
            cls = executor.getClass();
        } else {
            cls = null;
        }
        stringHelper.addHolder$ar$ds("executor", cls);
        stringHelper.addHolder$ar$ds("compressorName", null);
        stringHelper.addHolder$ar$ds("customOptions", Arrays.deepToString(this.customOptions));
        MoreObjects$ToStringHelper add = stringHelper.add("waitForReady", isWaitForReady());
        add.addHolder$ar$ds("maxInboundMessageSize", this.maxInboundMessageSize);
        add.addHolder$ar$ds("maxOutboundMessageSize", this.maxOutboundMessageSize);
        add.addHolder$ar$ds("streamTracerFactories", this.streamTracerFactories);
        return add.toString();
    }

    public final CallOptions withMaxInboundMessageSize(int i) {
        boolean z;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, "invalid maxsize %s", i);
        Builder builder = toBuilder(this);
        builder.maxInboundMessageSize = Integer.valueOf(i);
        return new CallOptions(builder);
    }

    public final CallOptions withMaxOutboundMessageSize(int i) {
        boolean z;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, "invalid maxsize %s", i);
        Builder builder = toBuilder(this);
        builder.maxOutboundMessageSize = Integer.valueOf(i);
        return new CallOptions(builder);
    }

    public final CallOptions withOption(Key key, Object obj) {
        int i;
        key.getClass();
        obj.getClass();
        Builder builder = toBuilder(this);
        int i2 = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i2 < objArr.length) {
                if (key.equals(objArr[i2][0])) {
                    break;
                }
                i2++;
            } else {
                i2 = -1;
                break;
            }
        }
        Object[][] objArr2 = this.customOptions;
        if (i2 == -1) {
            i = 1;
        } else {
            i = 0;
        }
        builder.customOptions = (Object[][]) Array.newInstance((Class<?>) Object.class, objArr2.length + i, 2);
        Object[][] objArr3 = this.customOptions;
        System.arraycopy(objArr3, 0, builder.customOptions, 0, objArr3.length);
        if (i2 == -1) {
            Object[][] objArr4 = builder.customOptions;
            int length = this.customOptions.length;
            Object[] objArr5 = new Object[2];
            objArr5[0] = key;
            objArr5[1] = obj;
            objArr4[length] = objArr5;
        } else {
            Object[][] objArr6 = builder.customOptions;
            Object[] objArr7 = new Object[2];
            objArr7[0] = key;
            objArr7[1] = obj;
            objArr6[i2] = objArr7;
        }
        return new CallOptions(builder);
    }

    public final CallOptions withStreamTracerFactory$ar$class_merging$ar$class_merging(OnDeviceFaceMeshLoadLogEvent onDeviceFaceMeshLoadLogEvent) {
        ArrayList arrayList = new ArrayList(this.streamTracerFactories.size() + 1);
        arrayList.addAll(this.streamTracerFactories);
        arrayList.add(onDeviceFaceMeshLoadLogEvent);
        Builder builder = toBuilder(this);
        builder.streamTracerFactories = DesugarCollections.unmodifiableList(arrayList);
        return new CallOptions(builder);
    }
}
