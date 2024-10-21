package io.grpc.util;

import androidx.preference.Preference;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageQualityAnalysisLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLogEvent;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolverProvider;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.PickFirstLoadBalancerProvider;
import io.grpc.okhttp.OkHttpFrameLogger$SettingParams;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Settings;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiChildLoadBalancer extends LoadBalancer {
    public static final Logger logger = Logger.getLogger(MultiChildLoadBalancer.class.getName());
    public final Map childLbStates;
    protected ConnectivityState currentConnectivityState;
    protected LoadBalancer.SubchannelPicker currentPicker;
    public final LoadBalancer.Helper helper;
    protected final LoadBalancerProvider pickFirstLbProvider;
    protected boolean resolvingAddresses;
    private final AtomicInteger sequence;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AcceptResolvedAddrRetVal {
        public final Object MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
        public final Object MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status;

        public AcceptResolvedAddrRetVal(Status status, List list) {
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status = status;
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren = list;
        }

        private static String toString(Buffer buffer) {
            long j = buffer.size;
            if (j <= 64) {
                return buffer.snapshot().hex();
            }
            return buffer.snapshot((int) Math.min(j, 64L)).hex().concat("...");
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.concurrent.ScheduledFuture, java.lang.Object] */
        public final void cancel() {
            ((SynchronizationContext.ManagedRunnable) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status).isCancelled = true;
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren.cancel(false);
        }

        public final boolean isEnabled() {
            return ((Logger) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren).isLoggable((Level) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status);
        }

        public final boolean isPending() {
            SynchronizationContext.ManagedRunnable managedRunnable = (SynchronizationContext.ManagedRunnable) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status;
            if (!managedRunnable.hasStarted && !managedRunnable.isCancelled) {
                return true;
            }
            return false;
        }

        public final void logData$ar$edu(int i, int i2, Buffer buffer, int i3, boolean z) {
            if (isEnabled()) {
                Object obj = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
                Logger logger = (Logger) obj;
                logger.logp((Level) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status, "io.grpc.okhttp.OkHttpFrameLogger", "logData", OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(i) + " DATA: streamId=" + i2 + " endStream=" + z + " length=" + i3 + " bytes=" + toString(buffer));
            }
        }

        public final void logGoAway$ar$edu(int i, int i2, ErrorCode errorCode, ByteString byteString) {
            if (isEnabled()) {
                Object obj = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
                Object obj2 = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status;
                String stringGenerated73b4b721e526cce6 = OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(i);
                String valueOf = String.valueOf(errorCode);
                int size$third_party_java_src_okio_okio_jvm = byteString.getSize$third_party_java_src_okio_okio_jvm();
                Buffer buffer = new Buffer();
                buffer.write$ar$ds(byteString);
                Logger logger = (Logger) obj;
                logger.logp((Level) obj2, "io.grpc.okhttp.OkHttpFrameLogger", "logGoAway", stringGenerated73b4b721e526cce6 + " GO_AWAY: lastStreamId=" + i2 + " errorCode=" + valueOf + " length=" + size$third_party_java_src_okio_okio_jvm + " bytes=" + toString(buffer));
            }
        }

        public final void logPing$ar$edu(int i, long j) {
            if (isEnabled()) {
                Object obj = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
                Logger logger = (Logger) obj;
                logger.logp((Level) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status, "io.grpc.okhttp.OkHttpFrameLogger", "logPing", OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(i) + " PING: ack=false bytes=" + j);
            }
        }

        public final void logRstStream$ar$edu(int i, int i2, ErrorCode errorCode) {
            if (isEnabled()) {
                Object obj = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
                Logger logger = (Logger) obj;
                logger.logp((Level) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status, "io.grpc.okhttp.OkHttpFrameLogger", "logRstStream", OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(i) + " RST_STREAM: streamId=" + i2 + " errorCode=" + String.valueOf(errorCode));
            }
        }

        public final void logSettings$ar$edu(int i, Settings settings) {
            if (isEnabled()) {
                Object obj = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
                Object obj2 = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status;
                String stringGenerated73b4b721e526cce6 = OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(i);
                EnumMap enumMap = new EnumMap(OkHttpFrameLogger$SettingParams.class);
                for (OkHttpFrameLogger$SettingParams okHttpFrameLogger$SettingParams : OkHttpFrameLogger$SettingParams.values()) {
                    int i2 = okHttpFrameLogger$SettingParams.bit;
                    if (settings.isSet(i2)) {
                        enumMap.put((EnumMap) okHttpFrameLogger$SettingParams, (OkHttpFrameLogger$SettingParams) Integer.valueOf(settings.get(i2)));
                    }
                }
                ((Logger) obj).logp((Level) obj2, "io.grpc.okhttp.OkHttpFrameLogger", "logSettings", stringGenerated73b4b721e526cce6 + " SETTINGS: ack=false settings=" + enumMap.toString());
            }
        }

        public final void logWindowsUpdate$ar$edu(int i, int i2, long j) {
            if (isEnabled()) {
                Object obj = this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren;
                Logger logger = (Logger) obj;
                logger.logp((Level) this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status, "io.grpc.okhttp.OkHttpFrameLogger", "logWindowsUpdate", OnDeviceImageQualityAnalysisLogEvent.toStringGenerated73b4b721e526cce6(i) + " WINDOW_UPDATE: streamId=" + i2 + " windowSizeIncrement=" + j);
            }
        }

        public AcceptResolvedAddrRetVal(Object obj, Object obj2) {
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren = obj;
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status = obj2;
        }

        public AcceptResolvedAddrRetVal(Status status, Object obj) {
            status.getClass();
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status = status;
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren = obj;
        }

        public AcceptResolvedAddrRetVal(URI uri, NameResolverProvider nameResolverProvider) {
            uri.getClass();
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status = uri;
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren = nameResolverProvider;
        }

        public AcceptResolvedAddrRetVal(SynchronizationContext.ManagedRunnable managedRunnable, ScheduledFuture scheduledFuture) {
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status = managedRunnable;
            scheduledFuture.getClass();
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren = scheduledFuture;
        }

        public AcceptResolvedAddrRetVal(Level level, Class cls) {
            Logger logger = Logger.getLogger(cls.getName());
            level.getClass();
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status = level;
            logger.getClass();
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren = logger;
        }

        public AcceptResolvedAddrRetVal(LoadBalancerRegistry loadBalancerRegistry, String str) {
            loadBalancerRegistry.getClass();
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status = loadBalancerRegistry;
            str.getClass();
            this.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren = str;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ChildLbState {
        public LoadBalancer.SubchannelPicker currentPicker;
        public ConnectivityState currentState = ConnectivityState.CONNECTING;
        public final Object key;
        public final LoadBalancer lb;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class ChildLbStateHelper extends ForwardingLoadBalancerHelper {
            protected ChildLbStateHelper() {
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper
            protected final LoadBalancer.Helper delegate() {
                return MultiChildLoadBalancer.this.helper;
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
            public final void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
                ChildLbState childLbState = ChildLbState.this;
                if (MultiChildLoadBalancer.this.childLbStates.containsKey(childLbState.key)) {
                    ChildLbState childLbState2 = ChildLbState.this;
                    childLbState2.currentState = connectivityState;
                    childLbState2.currentPicker = subchannelPicker;
                    if (!MultiChildLoadBalancer.this.resolvingAddresses) {
                        if (connectivityState == ConnectivityState.IDLE) {
                            ChildLbState.this.lb.requestConnection();
                        }
                        MultiChildLoadBalancer.this.updateOverallBalancingState();
                    }
                }
            }
        }

        public ChildLbState(Object obj, LoadBalancer.Factory factory, LoadBalancer.SubchannelPicker subchannelPicker) {
            this.key = obj;
            this.currentPicker = subchannelPicker;
            this.lb = factory.newLoadBalancer(new ChildLbStateHelper());
        }

        protected final void shutdown() {
            this.lb.shutdown();
            this.currentState = ConnectivityState.SHUTDOWN;
            MultiChildLoadBalancer.logger.logp(Level.FINE, "io.grpc.util.MultiChildLoadBalancer$ChildLbState", "shutdown", "Child balancer {0} deleted", this.key);
        }

        public final String toString() {
            return "Address = " + this.key.toString() + ", state = " + String.valueOf(this.currentState) + ", picker type: " + String.valueOf(this.currentPicker.getClass()) + ", lb: " + String.valueOf(this.lb);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Endpoint {
        final String[] addrs;
        final int hashCode;

        public Endpoint(EquivalentAddressGroup equivalentAddressGroup) {
            equivalentAddressGroup.getClass();
            this.addrs = new String[equivalentAddressGroup.addrs.size()];
            Iterator it = equivalentAddressGroup.addrs.iterator();
            int i = 0;
            while (it.hasNext()) {
                this.addrs[i] = ((SocketAddress) it.next()).toString();
                i++;
            }
            Arrays.sort(this.addrs);
            this.hashCode = Arrays.hashCode(this.addrs);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Endpoint)) {
                return false;
            }
            Endpoint endpoint = (Endpoint) obj;
            if (endpoint.hashCode == this.hashCode) {
                String[] strArr = endpoint.addrs;
                String[] strArr2 = this.addrs;
                if (strArr.length == strArr2.length) {
                    return Arrays.equals(strArr, strArr2);
                }
            }
            return false;
        }

        public final int hashCode() {
            return this.hashCode;
        }

        public final String toString() {
            return Arrays.toString(this.addrs);
        }
    }

    protected MultiChildLoadBalancer(LoadBalancer.Helper helper) {
        this.childLbStates = new LinkedHashMap();
        this.pickFirstLbProvider = new PickFirstLoadBalancerProvider();
        this.helper = helper;
        logger.logp(Level.FINE, "io.grpc.util.MultiChildLoadBalancer", "<init>", "Created");
    }

    private final void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        if (connectivityState == this.currentConnectivityState && subchannelPicker.equals(this.currentPicker)) {
            return;
        }
        this.helper.updateBalancingState(connectivityState, subchannelPicker);
        this.currentConnectivityState = connectivityState;
        this.currentPicker = subchannelPicker;
    }

    /* JADX WARN: Type inference failed for: r12v6, types: [java.util.List, java.lang.Object] */
    @Override // io.grpc.LoadBalancer
    public final Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        AcceptResolvedAddrRetVal acceptResolvedAddrRetVal;
        Endpoint endpoint;
        EquivalentAddressGroup equivalentAddressGroup;
        Object obj;
        try {
            this.resolvingAddresses = true;
            logger.logp(Level.FINE, "io.grpc.util.MultiChildLoadBalancer", "acceptResolvedAddressesInternal", "Received resolution result: {0}", resolvedAddresses);
            HashMap hashMap = new HashMap();
            Iterator it = resolvedAddresses.addresses.iterator();
            while (it.hasNext()) {
                Endpoint endpoint2 = new Endpoint((EquivalentAddressGroup) it.next());
                ChildLbState childLbState = (ChildLbState) this.childLbStates.get(endpoint2);
                if (childLbState != null) {
                    hashMap.put(endpoint2, childLbState);
                } else {
                    hashMap.put(endpoint2, new ChildLbState(endpoint2, this.pickFirstLbProvider, new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.NO_RESULT)));
                }
            }
            if (hashMap.isEmpty()) {
                Status withDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. ".concat(resolvedAddresses.toString()));
                handleNameResolutionError(withDescription);
                acceptResolvedAddrRetVal = new AcceptResolvedAddrRetVal(withDescription, (List) null);
            } else {
                for (Map.Entry entry : hashMap.entrySet()) {
                    Object key = entry.getKey();
                    if (!this.childLbStates.containsKey(key)) {
                        this.childLbStates.put(key, (ChildLbState) entry.getValue());
                    }
                }
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    ChildLbState childLbState2 = (ChildLbState) this.childLbStates.get(entry2.getKey());
                    Object key2 = entry2.getKey();
                    if (key2 instanceof EquivalentAddressGroup) {
                        endpoint = new Endpoint((EquivalentAddressGroup) key2);
                    } else {
                        ContextDataProvider.checkArgument(key2 instanceof Endpoint, (Object) "key is wrong type");
                        endpoint = (Endpoint) key2;
                    }
                    Iterator it2 = resolvedAddresses.addresses.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            equivalentAddressGroup = (EquivalentAddressGroup) it2.next();
                            if (endpoint.equals(new Endpoint(equivalentAddressGroup))) {
                                break;
                            }
                        } else {
                            equivalentAddressGroup = null;
                            break;
                        }
                    }
                    String.valueOf(key2);
                    equivalentAddressGroup.getClass();
                    OnDeviceTextDetectionLogEvent onDeviceTextDetectionLogEvent = new OnDeviceTextDetectionLogEvent();
                    onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$recognizerOptions = resolvedAddresses.addresses;
                    onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$imageInfo = resolvedAddresses.attributes;
                    onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$inferenceCommonLogEvent = resolvedAddresses.loadBalancingPolicyConfig;
                    onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$recognizerOptions = Collections.singletonList(equivalentAddressGroup);
                    Attributes.Builder builder = new Attributes.Builder(Attributes.EMPTY);
                    builder.set$ar$ds$d0d6fadb_0(IS_PETIOLE_POLICY, true);
                    onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$imageInfo = builder.build();
                    onDeviceTextDetectionLogEvent.OnDeviceTextDetectionLogEvent$ar$inferenceCommonLogEvent = null;
                    childLbState2.lb.handleResolvedAddresses(onDeviceTextDetectionLogEvent.build());
                }
                Status status = Status.OK;
                Set keySet = hashMap.keySet();
                ArrayList arrayList = new ArrayList();
                ImmutableList copyOf = ImmutableList.copyOf((Collection) this.childLbStates.keySet());
                int size = copyOf.size();
                for (int i = 0; i < size; i++) {
                    Object obj2 = copyOf.get(i);
                    if (!keySet.contains(obj2)) {
                        arrayList.add((ChildLbState) this.childLbStates.remove(obj2));
                    }
                }
                acceptResolvedAddrRetVal = new AcceptResolvedAddrRetVal(status, (List) arrayList);
            }
            if (!((Status) acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status).isOk()) {
                obj = acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status;
            } else {
                updateOverallBalancingState();
                Iterator it3 = acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren.iterator();
                while (it3.hasNext()) {
                    ((ChildLbState) it3.next()).shutdown();
                }
                obj = acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status;
            }
            this.resolvingAddresses = false;
            return (Status) obj;
        } catch (Throwable th) {
            this.resolvingAddresses = false;
            throw th;
        }
    }

    protected final LoadBalancer.SubchannelPicker createReadyPicker(Collection collection) {
        final ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((ChildLbState) it.next()).currentPicker);
        }
        final AtomicInteger atomicInteger = this.sequence;
        return new LoadBalancer.SubchannelPicker(arrayList, atomicInteger) { // from class: io.grpc.util.RoundRobinLoadBalancer$ReadyPicker
            private final int hashCode;
            private final AtomicInteger index;
            private final List subchannelPickers;

            {
                ContextDataProvider.checkArgument(!arrayList.isEmpty(), (Object) "empty list");
                this.subchannelPickers = arrayList;
                this.index = atomicInteger;
                Iterator it2 = arrayList.iterator();
                int i = 0;
                while (it2.hasNext()) {
                    i += ((LoadBalancer.SubchannelPicker) it2.next()).hashCode();
                }
                this.hashCode = i;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof RoundRobinLoadBalancer$ReadyPicker)) {
                    return false;
                }
                RoundRobinLoadBalancer$ReadyPicker roundRobinLoadBalancer$ReadyPicker = (RoundRobinLoadBalancer$ReadyPicker) obj;
                if (roundRobinLoadBalancer$ReadyPicker == this) {
                    return true;
                }
                if (this.hashCode != roundRobinLoadBalancer$ReadyPicker.hashCode || this.index != roundRobinLoadBalancer$ReadyPicker.index || this.subchannelPickers.size() != roundRobinLoadBalancer$ReadyPicker.subchannelPickers.size() || !new HashSet(this.subchannelPickers).containsAll(roundRobinLoadBalancer$ReadyPicker.subchannelPickers)) {
                    return false;
                }
                return true;
            }

            public final int hashCode() {
                return this.hashCode;
            }

            @Override // io.grpc.LoadBalancer.SubchannelPicker
            public final LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                return ((LoadBalancer.SubchannelPicker) this.subchannelPickers.get((this.index.getAndIncrement() & Preference.DEFAULT_ORDER) % this.subchannelPickers.size())).pickSubchannel(pickSubchannelArgs);
            }

            public final String toString() {
                MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(RoundRobinLoadBalancer$ReadyPicker.class);
                stringHelper.addHolder$ar$ds("subchannelPickers", this.subchannelPickers);
                return stringHelper.toString();
            }
        };
    }

    public final Collection getChildLbStates() {
        return this.childLbStates.values();
    }

    @Override // io.grpc.LoadBalancer
    public final void handleNameResolutionError(Status status) {
        if (this.currentConnectivityState != ConnectivityState.READY) {
            this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withError(status)));
        }
    }

    @Override // io.grpc.LoadBalancer
    public final void shutdown() {
        logger.logp(Level.FINE, "io.grpc.util.MultiChildLoadBalancer", "shutdown", "Shutdown");
        Iterator it = this.childLbStates.values().iterator();
        while (it.hasNext()) {
            ((ChildLbState) it.next()).shutdown();
        }
        this.childLbStates.clear();
    }

    protected final void updateOverallBalancingState() {
        ArrayList arrayList = new ArrayList();
        for (ChildLbState childLbState : getChildLbStates()) {
            if (childLbState.currentState == ConnectivityState.READY) {
                arrayList.add(childLbState);
            }
        }
        if (arrayList.isEmpty()) {
            Iterator it = getChildLbStates().iterator();
            while (it.hasNext()) {
                ConnectivityState connectivityState = ((ChildLbState) it.next()).currentState;
                if (connectivityState == ConnectivityState.CONNECTING || connectivityState == ConnectivityState.IDLE) {
                    updateBalancingState(ConnectivityState.CONNECTING, new RoundRobinLoadBalancer$EmptyPicker());
                    return;
                }
            }
            updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, createReadyPicker(getChildLbStates()));
            return;
        }
        updateBalancingState(ConnectivityState.READY, createReadyPicker(arrayList));
    }

    public MultiChildLoadBalancer(LoadBalancer.Helper helper, byte[] bArr) {
        this(helper);
        this.sequence = new AtomicInteger(new Random().nextInt());
        this.currentPicker = new RoundRobinLoadBalancer$EmptyPicker();
    }
}
