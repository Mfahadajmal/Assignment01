package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PickFirstLoadBalancer extends LoadBalancer {
    public ConnectivityState currentState = ConnectivityState.IDLE;
    public final LoadBalancer.Helper helper;
    private LoadBalancer.Subchannel subchannel;

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.PickFirstLoadBalancer$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements LoadBalancer.SubchannelStateListener {
        final /* synthetic */ LoadBalancer PickFirstLoadBalancer$1$ar$this$0;
        private final /* synthetic */ int switching_field;
        final /* synthetic */ LoadBalancer.Subchannel val$subchannel;

        public /* synthetic */ AnonymousClass1(PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer, LoadBalancer.Subchannel subchannel, int i) {
            this.switching_field = i;
            this.PickFirstLoadBalancer$1$ar$this$0 = pickFirstLeafLoadBalancer;
            this.val$subchannel = subchannel;
        }

        @Override // io.grpc.LoadBalancer.SubchannelStateListener
        public final void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
            LoadBalancer.SubchannelPicker picker;
            if (this.switching_field != 0) {
                ((PickFirstLeafLoadBalancer) this.PickFirstLoadBalancer$1$ar$this$0).processSubchannelState(this.val$subchannel, connectivityStateInfo);
                return;
            }
            ConnectivityState connectivityState = connectivityStateInfo.state;
            if (connectivityState != ConnectivityState.SHUTDOWN) {
                LoadBalancer loadBalancer = this.PickFirstLoadBalancer$1$ar$this$0;
                if (connectivityState == ConnectivityState.TRANSIENT_FAILURE || connectivityState == ConnectivityState.IDLE) {
                    ((PickFirstLoadBalancer) loadBalancer).helper.refreshNameResolution();
                }
                PickFirstLoadBalancer pickFirstLoadBalancer = (PickFirstLoadBalancer) loadBalancer;
                if (pickFirstLoadBalancer.currentState == ConnectivityState.TRANSIENT_FAILURE) {
                    if (connectivityState != ConnectivityState.CONNECTING) {
                        if (connectivityState == ConnectivityState.IDLE) {
                            loadBalancer.requestConnection();
                            return;
                        }
                    } else {
                        return;
                    }
                }
                LoadBalancer.Subchannel subchannel = this.val$subchannel;
                int ordinal = connectivityState.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            if (ordinal == 3) {
                                picker = new RequestConnectionPicker(subchannel);
                            } else {
                                throw new IllegalArgumentException("Unsupported state:".concat(connectivityState.toString()));
                            }
                        } else {
                            picker = new Picker(LoadBalancer.PickResult.withError(connectivityStateInfo.status));
                        }
                    } else {
                        picker = new Picker(LoadBalancer.PickResult.withSubchannel(subchannel));
                    }
                } else {
                    picker = new Picker(LoadBalancer.PickResult.NO_RESULT);
                }
                pickFirstLoadBalancer.updateBalancingState(connectivityState, picker);
            }
        }

        public AnonymousClass1(PickFirstLoadBalancer pickFirstLoadBalancer, LoadBalancer.Subchannel subchannel, int i) {
            this.switching_field = i;
            this.val$subchannel = subchannel;
            this.PickFirstLoadBalancer$1$ar$this$0 = pickFirstLoadBalancer;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PickFirstLoadBalancerConfig {
        final Long randomSeed = null;
        public final Boolean shuffleAddressList;

        public PickFirstLoadBalancerConfig(Boolean bool) {
            this.shuffleAddressList = bool;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Picker extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.PickResult result;

        public Picker(LoadBalancer.PickResult pickResult) {
            pickResult.getClass();
            this.result = pickResult;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public final LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return this.result;
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(Picker.class);
            stringHelper.addHolder$ar$ds("result", this.result);
            return stringHelper.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RequestConnectionPicker extends LoadBalancer.SubchannelPicker {
        private final AtomicBoolean connectionRequested = new AtomicBoolean(false);
        public final LoadBalancer.Subchannel subchannel;

        public RequestConnectionPicker(LoadBalancer.Subchannel subchannel) {
            this.subchannel = subchannel;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public final LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                PickFirstLoadBalancer.this.helper.getSynchronizationContext().execute(new InternalSubchannel$4$1(this, 17));
            }
            return LoadBalancer.PickResult.NO_RESULT;
        }
    }

    public PickFirstLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = helper;
    }

    @Override // io.grpc.LoadBalancer
    public final Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        PickFirstLoadBalancerConfig pickFirstLoadBalancerConfig;
        Boolean bool;
        List list = resolvedAddresses.addresses;
        if (!list.isEmpty()) {
            Object obj = resolvedAddresses.loadBalancingPolicyConfig;
            if ((obj instanceof PickFirstLoadBalancerConfig) && (bool = (pickFirstLoadBalancerConfig = (PickFirstLoadBalancerConfig) obj).shuffleAddressList) != null && bool.booleanValue()) {
                ArrayList arrayList = new ArrayList(list);
                Long l = pickFirstLoadBalancerConfig.randomSeed;
                Collections.shuffle(arrayList, new Random());
                list = arrayList;
            }
            LoadBalancer.Subchannel subchannel = this.subchannel;
            if (subchannel == null) {
                LoadBalancer.Helper helper = this.helper;
                LoadBalancer.CreateSubchannelArgs.Builder builder = new LoadBalancer.CreateSubchannelArgs.Builder();
                builder.setAddresses$ar$ds(list);
                LoadBalancer.Subchannel createSubchannel = helper.createSubchannel(builder.build());
                createSubchannel.start(new AnonymousClass1(this, createSubchannel, 0));
                this.subchannel = createSubchannel;
                updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withSubchannel(createSubchannel)));
                createSubchannel.requestConnection();
            } else {
                subchannel.updateAddresses(list);
            }
            return Status.OK;
        }
        List list2 = resolvedAddresses.addresses;
        Attributes attributes = resolvedAddresses.attributes;
        Status withDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. addrs=" + String.valueOf(list2) + ", attrs=" + attributes.toString());
        handleNameResolutionError(withDescription);
        return withDescription;
    }

    @Override // io.grpc.LoadBalancer
    public final void handleNameResolutionError(Status status) {
        LoadBalancer.Subchannel subchannel = this.subchannel;
        if (subchannel != null) {
            subchannel.shutdown();
            this.subchannel = null;
        }
        updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(status)));
    }

    @Override // io.grpc.LoadBalancer
    public final void requestConnection() {
        LoadBalancer.Subchannel subchannel = this.subchannel;
        if (subchannel != null) {
            subchannel.requestConnection();
        }
    }

    @Override // io.grpc.LoadBalancer
    public final void shutdown() {
        LoadBalancer.Subchannel subchannel = this.subchannel;
        if (subchannel != null) {
            subchannel.shutdown();
        }
    }

    public final void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        this.currentState = connectivityState;
        this.helper.updateBalancingState(connectivityState, subchannelPicker);
    }
}
