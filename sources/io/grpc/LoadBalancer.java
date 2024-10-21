package io.grpc;

import _COROUTINE._BOUNDARY;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent;
import io.grpc.Attributes;
import j$.util.DesugarCollections;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LoadBalancer {
    private int recursionCount;
    public static final Attributes.Key ATTR_HEALTH_CHECKING_CONFIG = new Attributes.Key("internal:health-checking-config");
    public static final CreateSubchannelArgs.Key HEALTH_CONSUMER_LISTENER_ARG_KEY = new CreateSubchannelArgs.Key();
    public static final Attributes.Key HAS_HEALTH_PRODUCER_LISTENER_KEY = new Attributes.Key("internal:has-health-check-producer-listener");
    public static final Attributes.Key IS_PETIOLE_POLICY = new Attributes.Key("io.grpc.IS_PETIOLE_POLICY");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CreateSubchannelArgs {
        public final List addrs;
        public final Attributes attrs;
        private final Object[][] customOptions;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private List addrs;
            private final Attributes attrs = Attributes.EMPTY;
            public Object[][] customOptions = (Object[][]) Array.newInstance((Class<?>) Object.class, 0, 2);

            public final CreateSubchannelArgs build() {
                return new CreateSubchannelArgs(this.addrs, this.attrs, this.customOptions);
            }

            public final void setAddresses$ar$ds(List list) {
                ContextDataProvider.checkArgument(!list.isEmpty(), (Object) "addrs is empty");
                this.addrs = DesugarCollections.unmodifiableList(new ArrayList(list));
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Key {
            private final String debugString = "internal:health-check-consumer-listener";

            public final String toString() {
                return this.debugString;
            }
        }

        public CreateSubchannelArgs(List list, Attributes attributes, Object[][] objArr) {
            list.getClass();
            this.addrs = list;
            attributes.getClass();
            this.attrs = attributes;
            objArr.getClass();
            this.customOptions = objArr;
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("addrs", this.addrs);
            stringHelper.addHolder$ar$ds("attrs", this.attrs);
            stringHelper.addHolder$ar$ds("customOptions", Arrays.deepToString(this.customOptions));
            return stringHelper.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Factory {
        public abstract LoadBalancer newLoadBalancer(Helper helper);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FixedResultPicker extends SubchannelPicker {
        private final PickResult result;

        public FixedResultPicker(PickResult pickResult) {
            pickResult.getClass();
            this.result = pickResult;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public final PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return this.result;
        }

        public final String toString() {
            return "FixedResultPicker(" + this.result.toString() + ")";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Helper {
        public Subchannel createSubchannel(CreateSubchannelArgs createSubchannelArgs) {
            throw null;
        }

        public ChannelLogger getChannelLogger() {
            throw null;
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            throw null;
        }

        public SynchronizationContext getSynchronizationContext() {
            throw null;
        }

        public void refreshNameResolution() {
            throw null;
        }

        public abstract void updateBalancingState(ConnectivityState connectivityState, SubchannelPicker subchannelPicker);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface PickDetailsConsumer {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PickResult {
        public static final PickResult NO_RESULT = new PickResult(null, Status.OK, false);
        public final boolean drop;
        public final Status status;
        private final OnDeviceFaceMeshLoadLogEvent streamTracerFactory$ar$class_merging$ar$class_merging = null;
        public final Subchannel subchannel;

        public PickResult(Subchannel subchannel, Status status, boolean z) {
            this.subchannel = subchannel;
            status.getClass();
            this.status = status;
            this.drop = z;
        }

        public static PickResult withError(Status status) {
            ContextDataProvider.checkArgument(!status.isOk(), (Object) "error status shouldn't be OK");
            return new PickResult(null, status, false);
        }

        public static PickResult withSubchannel(Subchannel subchannel) {
            return new PickResult(subchannel, Status.OK, false);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof PickResult)) {
                return false;
            }
            PickResult pickResult = (PickResult) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.subchannel, pickResult.subchannel) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.status, pickResult.status)) {
                OnDeviceFaceMeshLoadLogEvent onDeviceFaceMeshLoadLogEvent = pickResult.streamTracerFactory$ar$class_merging$ar$class_merging;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null) && this.drop == pickResult.drop) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.subchannel, this.status, null, Boolean.valueOf(this.drop)});
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("subchannel", this.subchannel);
            stringHelper.addHolder$ar$ds("streamTracerFactory", null);
            stringHelper.addHolder$ar$ds("status", this.status);
            return stringHelper.add("drop", this.drop).toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PickSubchannelArgs {
        public final CallOptions callOptions;
        public final Metadata headers;
        public final MethodDescriptor method;
        private final PickDetailsConsumer pickDetailsConsumer;

        public PickSubchannelArgs() {
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                PickSubchannelArgs pickSubchannelArgs = (PickSubchannelArgs) obj;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.callOptions, pickSubchannelArgs.callOptions) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.headers, pickSubchannelArgs.headers) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.method, pickSubchannelArgs.method) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.pickDetailsConsumer, pickSubchannelArgs.pickDetailsConsumer)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.callOptions, this.headers, this.method, this.pickDetailsConsumer});
        }

        public final String toString() {
            CallOptions callOptions = this.callOptions;
            Metadata metadata = this.headers;
            return "[method=" + this.method.toString() + " headers=" + metadata.toString() + " callOptions=" + callOptions.toString() + "]";
        }

        public PickSubchannelArgs(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, PickDetailsConsumer pickDetailsConsumer) {
            this();
            methodDescriptor.getClass();
            this.method = methodDescriptor;
            metadata.getClass();
            this.headers = metadata;
            callOptions.getClass();
            this.callOptions = callOptions;
            pickDetailsConsumer.getClass();
            this.pickDetailsConsumer = pickDetailsConsumer;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ResolvedAddresses {
        public final List addresses;
        public final Attributes attributes;
        public final Object loadBalancingPolicyConfig;

        public ResolvedAddresses(List list, Attributes attributes, Object obj) {
            list.getClass();
            this.addresses = DesugarCollections.unmodifiableList(new ArrayList(list));
            attributes.getClass();
            this.attributes = attributes;
            this.loadBalancingPolicyConfig = obj;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof ResolvedAddresses)) {
                return false;
            }
            ResolvedAddresses resolvedAddresses = (ResolvedAddresses) obj;
            if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.addresses, resolvedAddresses.addresses) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.attributes, resolvedAddresses.attributes) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.loadBalancingPolicyConfig, resolvedAddresses.loadBalancingPolicyConfig)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.addresses, this.attributes, this.loadBalancingPolicyConfig});
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("addresses", this.addresses);
            stringHelper.addHolder$ar$ds("attributes", this.attributes);
            stringHelper.addHolder$ar$ds("loadBalancingPolicyConfig", this.loadBalancingPolicyConfig);
            return stringHelper.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Subchannel {
        public List getAllAddresses() {
            throw null;
        }

        public Object getInternalSubchannel() {
            throw null;
        }

        public abstract void requestConnection();

        public abstract void shutdown();

        public void start(SubchannelStateListener subchannelStateListener) {
            throw null;
        }

        public void updateAddresses(List list) {
            throw null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class SubchannelPicker {
        public abstract PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface SubchannelStateListener {
        void onSubchannelState(ConnectivityStateInfo connectivityStateInfo);
    }

    public Status acceptResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        throw null;
    }

    public abstract void handleNameResolutionError(Status status);

    public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        int i = this.recursionCount;
        this.recursionCount = i + 1;
        if (i == 0) {
            acceptResolvedAddresses(resolvedAddresses);
        }
        this.recursionCount = 0;
    }

    public abstract void shutdown();

    public void requestConnection() {
    }
}
