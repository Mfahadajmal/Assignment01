package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import io.grpc.internal.PickFirstLoadBalancer;
import io.grpc.util.MultiChildLoadBalancer;
import java.lang.reflect.Array;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PickFirstLeafLoadBalancer extends LoadBalancer {
    public static final Logger log = Logger.getLogger(PickFirstLeafLoadBalancer.class.getName());
    public Index addressIndex;
    private final boolean enableHappyEyeballs;
    public final LoadBalancer.Helper helper;
    public MultiChildLoadBalancer.AcceptResolvedAddrRetVal scheduleConnectionTask$ar$class_merging$ar$class_merging;
    public final Map subchannels = new HashMap();
    private int numTf = 0;
    private boolean firstPass = true;
    private ConnectivityState rawConnectivityState = ConnectivityState.IDLE;
    private ConnectivityState concludedState = ConnectivityState.IDLE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HealthListener implements LoadBalancer.SubchannelStateListener {
        public ConnectivityStateInfo healthStateInfo = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
        public SubchannelData subchannelData;

        public HealthListener() {
        }

        @Override // io.grpc.LoadBalancer.SubchannelStateListener
        public final void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
            PickFirstLeafLoadBalancer.log.logp(Level.FINE, "io.grpc.internal.PickFirstLeafLoadBalancer$HealthListener", "onSubchannelState", "Received health status {0} for subchannel {1}", new Object[]{connectivityStateInfo, this.subchannelData.subchannel});
            this.healthStateInfo = connectivityStateInfo;
            try {
                PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer = PickFirstLeafLoadBalancer.this;
                SubchannelData subchannelData = (SubchannelData) pickFirstLeafLoadBalancer.subchannels.get(pickFirstLeafLoadBalancer.addressIndex.getCurrentAddress());
                if (subchannelData != null && subchannelData.healthListener == this) {
                    PickFirstLeafLoadBalancer.this.updateHealthCheckedState(this.subchannelData);
                }
            } catch (IllegalStateException unused) {
                PickFirstLeafLoadBalancer.log.logp(Level.FINE, "io.grpc.internal.PickFirstLeafLoadBalancer$HealthListener", "onSubchannelState", "Health listener received state change after subchannel was removed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Index {
        private List addressGroups;
        private int addressIndex;
        private int groupIndex;

        public Index(List list) {
            this.addressGroups = list == null ? Collections.emptyList() : list;
        }

        public final SocketAddress getCurrentAddress() {
            if (isValid()) {
                return (SocketAddress) ((EquivalentAddressGroup) this.addressGroups.get(this.groupIndex)).addrs.get(this.addressIndex);
            }
            throw new IllegalStateException("Index is past the end of the address group list");
        }

        public final Attributes getCurrentEagAttributes() {
            if (isValid()) {
                return ((EquivalentAddressGroup) this.addressGroups.get(this.groupIndex)).attrs;
            }
            throw new IllegalStateException("Index is off the end of the address group list");
        }

        public final boolean increment() {
            if (!isValid()) {
                return false;
            }
            EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) this.addressGroups.get(this.groupIndex);
            int i = this.addressIndex + 1;
            this.addressIndex = i;
            if (i < equivalentAddressGroup.addrs.size()) {
                return true;
            }
            int i2 = this.groupIndex + 1;
            this.groupIndex = i2;
            this.addressIndex = 0;
            if (i2 >= this.addressGroups.size()) {
                return false;
            }
            return true;
        }

        public final boolean isValid() {
            if (this.groupIndex < this.addressGroups.size()) {
                return true;
            }
            return false;
        }

        public final void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public final boolean seekTo(SocketAddress socketAddress) {
            for (int i = 0; i < this.addressGroups.size(); i++) {
                int indexOf = ((EquivalentAddressGroup) this.addressGroups.get(i)).addrs.indexOf(socketAddress);
                if (indexOf != -1) {
                    this.groupIndex = i;
                    this.addressIndex = indexOf;
                    return true;
                }
            }
            return false;
        }

        public final int size() {
            List list = this.addressGroups;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        
            r1 = r1;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void updateGroups(com.google.common.collect.ImmutableList r1) {
            /*
                r0 = this;
                if (r1 != 0) goto L6
                java.util.List r1 = java.util.Collections.emptyList()
            L6:
                r0.addressGroups = r1
                r0.reset()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.PickFirstLeafLoadBalancer.Index.updateGroups(com.google.common.collect.ImmutableList):void");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PickFirstLeafLoadBalancerConfig {
        final Long randomSeed = null;
        public final Boolean shuffleAddressList;

        public PickFirstLeafLoadBalancerConfig(Boolean bool) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RequestConnectionPicker extends LoadBalancer.SubchannelPicker {
        private final AtomicBoolean connectionRequested = new AtomicBoolean(false);
        private final PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer;

        public RequestConnectionPicker(PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer) {
            this.pickFirstLeafLoadBalancer = pickFirstLeafLoadBalancer;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public final LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                PickFirstLeafLoadBalancer.this.helper.getSynchronizationContext().execute(new InternalSubchannel$4$1(this.pickFirstLeafLoadBalancer, 16, null));
            }
            return LoadBalancer.PickResult.NO_RESULT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SubchannelData {
        public boolean completedConnectivityAttempt = false;
        public final HealthListener healthListener;
        public ConnectivityState state;
        public final LoadBalancer.Subchannel subchannel;

        public SubchannelData(LoadBalancer.Subchannel subchannel, ConnectivityState connectivityState, HealthListener healthListener) {
            this.subchannel = subchannel;
            this.state = connectivityState;
            this.healthListener = healthListener;
        }

        public final ConnectivityState getHealthState() {
            return this.healthListener.healthStateInfo.state;
        }

        public final void updateState(ConnectivityState connectivityState) {
            boolean z;
            this.state = connectivityState;
            if (connectivityState != ConnectivityState.READY && connectivityState != ConnectivityState.TRANSIENT_FAILURE) {
                if (connectivityState == ConnectivityState.IDLE) {
                    z = false;
                } else {
                    return;
                }
            } else {
                z = true;
            }
            this.completedConnectivityAttempt = z;
        }
    }

    public PickFirstLeafLoadBalancer(LoadBalancer.Helper helper) {
        int i = PickFirstLoadBalancerProvider.PickFirstLoadBalancerProvider$ar$NoOp;
        this.enableHappyEyeballs = GrpcUtil.getFlag$ar$ds("GRPC_EXPERIMENTAL_XDS_DUALSTACK_ENDPOINTS");
        this.helper = helper;
    }

    private final void cancelScheduleTask() {
        MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = this.scheduleConnectionTask$ar$class_merging$ar$class_merging;
        if (acceptResolvedAddrRetVal != null) {
            acceptResolvedAddrRetVal.cancel();
            this.scheduleConnectionTask$ar$class_merging$ar$class_merging = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r3.size() == 1) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.net.SocketAddress getAddress$ar$ds(io.grpc.LoadBalancer.Subchannel r3) {
        /*
            java.util.List r3 = r3.getAllAddresses()
            r0 = 0
            if (r3 == 0) goto Lf
            int r1 = r3.size()
            r2 = 1
            if (r1 != r2) goto Lf
            goto L10
        Lf:
            r2 = r0
        L10:
            java.lang.String r1 = "%s does not have exactly one group"
            com.google.common.flogger.context.ContextDataProvider.checkState(r2, r1, r3)
            java.lang.Object r3 = r3.get(r0)
            io.grpc.EquivalentAddressGroup r3 = (io.grpc.EquivalentAddressGroup) r3
            java.util.List r3 = r3.addrs
            java.lang.Object r3 = r3.get(r0)
            java.net.SocketAddress r3 = (java.net.SocketAddress) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.PickFirstLeafLoadBalancer.getAddress$ar$ds(io.grpc.LoadBalancer$Subchannel):java.net.SocketAddress");
    }

    private final void scheduleNextConnection() {
        if (this.enableHappyEyeballs) {
            MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = this.scheduleConnectionTask$ar$class_merging$ar$class_merging;
            if (acceptResolvedAddrRetVal == null || !acceptResolvedAddrRetVal.isPending()) {
                try {
                    LoadBalancer.Helper helper = this.helper;
                    this.scheduleConnectionTask$ar$class_merging$ar$class_merging = helper.getSynchronizationContext().schedule$ar$class_merging$ar$class_merging(new InternalSubchannel$4$1(this, 15), 250L, TimeUnit.MILLISECONDS, helper.getScheduledExecutorService());
                } catch (NullPointerException unused) {
                }
            }
        }
    }

    private final void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        if (connectivityState == this.concludedState && (connectivityState == ConnectivityState.IDLE || connectivityState == ConnectivityState.CONNECTING)) {
            return;
        }
        this.concludedState = connectivityState;
        this.helper.updateBalancingState(connectivityState, subchannelPicker);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.LoadBalancer
    public final Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        ConnectivityState connectivityState;
        PickFirstLeafLoadBalancerConfig pickFirstLeafLoadBalancerConfig;
        Boolean bool;
        if (this.rawConnectivityState != ConnectivityState.SHUTDOWN) {
            List list = resolvedAddresses.addresses;
            if (list.isEmpty()) {
                List list2 = resolvedAddresses.addresses;
                Attributes attributes = resolvedAddresses.attributes;
                Status withDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. addrs=" + String.valueOf(list2) + ", attrs=" + attributes.toString());
                handleNameResolutionError(withDescription);
                return withDescription;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((EquivalentAddressGroup) it.next()) == null) {
                    List list3 = resolvedAddresses.addresses;
                    Attributes attributes2 = resolvedAddresses.attributes;
                    Status withDescription2 = Status.UNAVAILABLE.withDescription("NameResolver returned address list with null endpoint. addrs=" + String.valueOf(list3) + ", attrs=" + attributes2.toString());
                    handleNameResolutionError(withDescription2);
                    return withDescription2;
                }
            }
            this.firstPass = true;
            Object obj = resolvedAddresses.loadBalancingPolicyConfig;
            if ((obj instanceof PickFirstLeafLoadBalancerConfig) && (bool = (pickFirstLeafLoadBalancerConfig = (PickFirstLeafLoadBalancerConfig) obj).shuffleAddressList) != null && bool.booleanValue()) {
                ArrayList arrayList = new ArrayList(list);
                Long l = pickFirstLeafLoadBalancerConfig.randomSeed;
                Collections.shuffle(arrayList, new Random());
                list = arrayList;
            }
            ImmutableList.Builder builder = new ImmutableList.Builder();
            builder.addAll$ar$ds$2104aa48_0(list);
            ImmutableList build = builder.build();
            Index index = this.addressIndex;
            if (index == null) {
                this.addressIndex = new Index(build);
            } else if (this.rawConnectivityState == ConnectivityState.READY) {
                SocketAddress currentAddress = index.getCurrentAddress();
                this.addressIndex.updateGroups(build);
                if (this.addressIndex.seekTo(currentAddress)) {
                    LoadBalancer.Subchannel subchannel = ((SubchannelData) this.subchannels.get(currentAddress)).subchannel;
                    Index index2 = this.addressIndex;
                    subchannel.updateAddresses(Collections.singletonList(new EquivalentAddressGroup(index2.getCurrentAddress(), index2.getCurrentEagAttributes())));
                    return Status.OK;
                }
                this.addressIndex.reset();
            } else {
                index.updateGroups(build);
            }
            HashSet<SocketAddress> hashSet = new HashSet(this.subchannels.keySet());
            HashSet hashSet2 = new HashSet();
            int i = ((RegularImmutableList) build).size;
            for (int i2 = 0; i2 < i; i2++) {
                hashSet2.addAll(((EquivalentAddressGroup) build.get(i2)).addrs);
            }
            for (SocketAddress socketAddress : hashSet) {
                if (!hashSet2.contains(socketAddress)) {
                    ((SubchannelData) this.subchannels.remove(socketAddress)).subchannel.shutdown();
                }
            }
            if (hashSet.size() != 0 && (connectivityState = this.rawConnectivityState) != ConnectivityState.CONNECTING && connectivityState != ConnectivityState.READY) {
                if (connectivityState == ConnectivityState.IDLE) {
                    updateBalancingState(ConnectivityState.IDLE, new RequestConnectionPicker(this));
                } else if (connectivityState == ConnectivityState.TRANSIENT_FAILURE) {
                    cancelScheduleTask();
                    requestConnection();
                }
            } else {
                ConnectivityState connectivityState2 = ConnectivityState.CONNECTING;
                this.rawConnectivityState = connectivityState2;
                updateBalancingState(connectivityState2, new Picker(LoadBalancer.PickResult.NO_RESULT));
                cancelScheduleTask();
                requestConnection();
            }
            return Status.OK;
        }
        return Status.FAILED_PRECONDITION.withDescription("Already shut down");
    }

    @Override // io.grpc.LoadBalancer
    public final void handleNameResolutionError(Status status) {
        Iterator it = this.subchannels.values().iterator();
        while (it.hasNext()) {
            ((SubchannelData) it.next()).subchannel.shutdown();
        }
        this.subchannels.clear();
        updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(status)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void processSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        ConnectivityState connectivityState;
        SubchannelData subchannelData = (SubchannelData) this.subchannels.get(getAddress$ar$ds(subchannel));
        if (subchannelData != null && subchannelData.subchannel == subchannel && (connectivityState = connectivityStateInfo.state) != ConnectivityState.SHUTDOWN) {
            if (connectivityState == ConnectivityState.IDLE) {
                this.helper.refreshNameResolution();
            }
            subchannelData.updateState(connectivityState);
            ConnectivityState connectivityState2 = this.rawConnectivityState;
            ConnectivityState connectivityState3 = ConnectivityState.TRANSIENT_FAILURE;
            if (connectivityState2 == connectivityState3 || this.concludedState == connectivityState3) {
                if (connectivityState != ConnectivityState.CONNECTING) {
                    if (connectivityState == ConnectivityState.IDLE) {
                        requestConnection();
                        return;
                    }
                } else {
                    return;
                }
            }
            int ordinal = connectivityState.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal == 3) {
                            this.addressIndex.reset();
                            ConnectivityState connectivityState4 = ConnectivityState.IDLE;
                            this.rawConnectivityState = connectivityState4;
                            updateBalancingState(connectivityState4, new RequestConnectionPicker(this));
                            return;
                        }
                        throw new IllegalArgumentException("Unsupported state:".concat(connectivityState.toString()));
                    }
                    if (this.addressIndex.isValid() && ((SubchannelData) this.subchannels.get(this.addressIndex.getCurrentAddress())).subchannel == subchannel && this.addressIndex.increment()) {
                        cancelScheduleTask();
                        requestConnection();
                    }
                    Index index = this.addressIndex;
                    if (index != null && !index.isValid() && this.subchannels.size() >= this.addressIndex.size()) {
                        Iterator it = this.subchannels.values().iterator();
                        while (it.hasNext()) {
                            if (!((SubchannelData) it.next()).completedConnectivityAttempt) {
                                return;
                            }
                        }
                        ConnectivityState connectivityState5 = ConnectivityState.TRANSIENT_FAILURE;
                        this.rawConnectivityState = connectivityState5;
                        updateBalancingState(connectivityState5, new Picker(LoadBalancer.PickResult.withError(connectivityStateInfo.status)));
                        int i = this.numTf + 1;
                        this.numTf = i;
                        if (i >= this.addressIndex.size() || this.firstPass) {
                            this.firstPass = false;
                            this.numTf = 0;
                            this.helper.refreshNameResolution();
                            return;
                        }
                        return;
                    }
                    return;
                }
                cancelScheduleTask();
                for (SubchannelData subchannelData2 : this.subchannels.values()) {
                    if (!subchannelData2.subchannel.equals(subchannelData.subchannel)) {
                        subchannelData2.subchannel.shutdown();
                    }
                }
                this.subchannels.clear();
                subchannelData.updateState(ConnectivityState.READY);
                this.subchannels.put(getAddress$ar$ds(subchannelData.subchannel), subchannelData);
                this.addressIndex.seekTo(getAddress$ar$ds(subchannel));
                this.rawConnectivityState = ConnectivityState.READY;
                updateHealthCheckedState(subchannelData);
                return;
            }
            ConnectivityState connectivityState6 = ConnectivityState.CONNECTING;
            this.rawConnectivityState = connectivityState6;
            updateBalancingState(connectivityState6, new Picker(LoadBalancer.PickResult.NO_RESULT));
        }
    }

    @Override // io.grpc.LoadBalancer
    public final void requestConnection() {
        LoadBalancer.Subchannel createSubchannel;
        Index index = this.addressIndex;
        if (index != null && index.isValid() && this.rawConnectivityState != ConnectivityState.SHUTDOWN) {
            SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
            if (this.subchannels.containsKey(currentAddress)) {
                createSubchannel = ((SubchannelData) this.subchannels.get(currentAddress)).subchannel;
            } else {
                Attributes currentEagAttributes = this.addressIndex.getCurrentEagAttributes();
                HealthListener healthListener = new HealthListener();
                LoadBalancer.Helper helper = this.helper;
                LoadBalancer.CreateSubchannelArgs.Builder builder = new LoadBalancer.CreateSubchannelArgs.Builder();
                builder.setAddresses$ar$ds(ContextDataProvider.newArrayList(new EquivalentAddressGroup(currentAddress, currentEagAttributes)));
                LoadBalancer.CreateSubchannelArgs.Key key = HEALTH_CONSUMER_LISTENER_ARG_KEY;
                int i = 0;
                while (true) {
                    Object[][] objArr = builder.customOptions;
                    if (i < objArr.length) {
                        if (key.equals(objArr[i][0])) {
                            break;
                        } else {
                            i++;
                        }
                    } else {
                        i = -1;
                        break;
                    }
                }
                if (i == -1) {
                    Object[][] objArr2 = builder.customOptions;
                    int length = objArr2.length;
                    Object[][] objArr3 = (Object[][]) Array.newInstance((Class<?>) Object.class, length + 1, 2);
                    System.arraycopy(objArr2, 0, objArr3, 0, length);
                    builder.customOptions = objArr3;
                    i = builder.customOptions.length - 1;
                }
                Object[][] objArr4 = builder.customOptions;
                Object[] objArr5 = new Object[2];
                objArr5[0] = key;
                objArr5[1] = healthListener;
                objArr4[i] = objArr5;
                createSubchannel = helper.createSubchannel(builder.build());
                SubchannelData subchannelData = new SubchannelData(createSubchannel, ConnectivityState.IDLE, healthListener);
                healthListener.subchannelData = subchannelData;
                this.subchannels.put(currentAddress, subchannelData);
                if (((AbstractSubchannel) createSubchannel).args.attrs.get(LoadBalancer.HAS_HEALTH_PRODUCER_LISTENER_KEY) == null) {
                    healthListener.healthStateInfo = ConnectivityStateInfo.forNonError(ConnectivityState.READY);
                }
                createSubchannel.start(new PickFirstLoadBalancer.AnonymousClass1(this, createSubchannel, 1));
            }
            int ordinal = ((SubchannelData) this.subchannels.get(currentAddress)).state.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal == 3) {
                            createSubchannel.requestConnection();
                            ((SubchannelData) this.subchannels.get(currentAddress)).updateState(ConnectivityState.CONNECTING);
                            scheduleNextConnection();
                            return;
                        }
                        return;
                    }
                    this.addressIndex.increment();
                    requestConnection();
                    return;
                }
                log.logp(Level.WARNING, "io.grpc.internal.PickFirstLeafLoadBalancer", "requestConnection", "Requesting a connection even though we have a READY subchannel");
                return;
            }
            if (this.enableHappyEyeballs) {
                scheduleNextConnection();
            } else {
                createSubchannel.requestConnection();
            }
        }
    }

    @Override // io.grpc.LoadBalancer
    public final void shutdown() {
        log.logp(Level.FINE, "io.grpc.internal.PickFirstLeafLoadBalancer", "shutdown", "Shutting down, currently have {} subchannels created", Integer.valueOf(this.subchannels.size()));
        this.rawConnectivityState = ConnectivityState.SHUTDOWN;
        this.concludedState = ConnectivityState.SHUTDOWN;
        cancelScheduleTask();
        Iterator it = this.subchannels.values().iterator();
        while (it.hasNext()) {
            ((SubchannelData) it.next()).subchannel.shutdown();
        }
        this.subchannels.clear();
    }

    public final void updateHealthCheckedState(SubchannelData subchannelData) {
        if (subchannelData.state == ConnectivityState.READY) {
            ConnectivityState healthState = subchannelData.getHealthState();
            ConnectivityState connectivityState = ConnectivityState.READY;
            if (healthState != connectivityState) {
                ConnectivityState healthState2 = subchannelData.getHealthState();
                ConnectivityState connectivityState2 = ConnectivityState.TRANSIENT_FAILURE;
                if (healthState2 == connectivityState2) {
                    updateBalancingState(connectivityState2, new Picker(LoadBalancer.PickResult.withError(subchannelData.healthListener.healthStateInfo.status)));
                    return;
                } else {
                    if (this.concludedState != connectivityState2) {
                        updateBalancingState(subchannelData.getHealthState(), new Picker(LoadBalancer.PickResult.NO_RESULT));
                        return;
                    }
                    return;
                }
            }
            updateBalancingState(connectivityState, new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withSubchannel(subchannelData.subchannel)));
        }
    }
}
