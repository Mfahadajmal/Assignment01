package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.CallOptions;
import io.grpc.InternalConfigSelector;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.RetriableStream;
import io.grpc.util.MultiChildLoadBalancer;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManagedChannelServiceConfig {
    private final MethodInfo defaultMethodConfig;
    public final Map healthCheckingConfig;
    public final Object loadBalancingConfig;
    public final RetriableStream.Throttle retryThrottling;
    private final Map serviceMap;
    private final Map serviceMethodMap;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MethodInfo {
        static final CallOptions.Key KEY = new CallOptions.Key("io.grpc.internal.ManagedChannelServiceConfig.MethodInfo", null);
        final HedgingPolicy hedgingPolicy;
        final Integer maxInboundMessageSize;
        final Integer maxOutboundMessageSize;
        final RetryPolicy retryPolicy;
        final Long timeoutNanos;
        final Boolean waitForReady;

        public MethodInfo(Map map, boolean z, int i, int i2) {
            Map map2;
            boolean z2;
            boolean z3;
            String str;
            boolean z4;
            boolean z5;
            boolean z6;
            boolean z7;
            boolean z8;
            RetryPolicy retryPolicy;
            Map map3;
            boolean z9;
            boolean z10;
            HedgingPolicy hedgingPolicy;
            boolean z11;
            boolean z12;
            this.timeoutNanos = JsonUtil.getStringAsDuration(map, "timeout");
            this.waitForReady = JsonUtil.getBoolean(map, "waitForReady");
            Integer numberAsInteger = JsonUtil.getNumberAsInteger(map, "maxResponseMessageBytes");
            this.maxInboundMessageSize = numberAsInteger;
            if (numberAsInteger != null) {
                if (numberAsInteger.intValue() >= 0) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                ContextDataProvider.checkArgument(z12, "maxInboundMessageSize %s exceeds bounds", numberAsInteger);
            }
            Integer numberAsInteger2 = JsonUtil.getNumberAsInteger(map, "maxRequestMessageBytes");
            this.maxOutboundMessageSize = numberAsInteger2;
            if (numberAsInteger2 != null) {
                if (numberAsInteger2.intValue() >= 0) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                ContextDataProvider.checkArgument(z11, "maxOutboundMessageSize %s exceeds bounds", numberAsInteger2);
            }
            if (z) {
                map2 = JsonUtil.getObject(map, "retryPolicy");
            } else {
                map2 = null;
            }
            if (map2 == null) {
                str = "maxAttempts";
                retryPolicy = null;
            } else {
                Integer numberAsInteger3 = JsonUtil.getNumberAsInteger(map2, "maxAttempts");
                numberAsInteger3.getClass();
                int intValue = numberAsInteger3.intValue();
                if (intValue >= 2) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                ContextDataProvider.checkArgument(z2, "maxAttempts must be greater than 1: %s", intValue);
                int min = Math.min(intValue, i);
                Long stringAsDuration = JsonUtil.getStringAsDuration(map2, "initialBackoff");
                stringAsDuration.getClass();
                long longValue = stringAsDuration.longValue();
                if (longValue > 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                ContextDataProvider.checkArgument(z3, "initialBackoffNanos must be greater than 0: %s", longValue);
                Long stringAsDuration2 = JsonUtil.getStringAsDuration(map2, "maxBackoff");
                stringAsDuration2.getClass();
                str = "maxAttempts";
                long longValue2 = stringAsDuration2.longValue();
                if (longValue2 > 0) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                ContextDataProvider.checkArgument(z4, "maxBackoff must be greater than 0: %s", longValue2);
                Double numberAsDouble = JsonUtil.getNumberAsDouble(map2, "backoffMultiplier");
                numberAsDouble.getClass();
                double doubleValue = numberAsDouble.doubleValue();
                if (doubleValue > 0.0d) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                ContextDataProvider.checkArgument(z5, "backoffMultiplier must be greater than 0: %s", numberAsDouble);
                Long stringAsDuration3 = JsonUtil.getStringAsDuration(map2, "perAttemptRecvTimeout");
                if (stringAsDuration3 != null && stringAsDuration3.longValue() < 0) {
                    z6 = false;
                } else {
                    z6 = true;
                }
                ContextDataProvider.checkArgument(z6, "perAttemptRecvTimeout cannot be negative: %s", stringAsDuration3);
                Set listOfStatusCodesAsSet = ServiceConfigUtil.getListOfStatusCodesAsSet(map2, "retryableStatusCodes");
                if (listOfStatusCodesAsSet != null) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                ContextDataProvider.verify(z7, "%s is required in retry policy", "retryableStatusCodes");
                ContextDataProvider.verify(!listOfStatusCodesAsSet.contains(Status.Code.OK), "%s must not contain OK", "retryableStatusCodes");
                if (stringAsDuration3 == null && listOfStatusCodesAsSet.isEmpty()) {
                    z8 = false;
                } else {
                    z8 = true;
                }
                ContextDataProvider.checkArgument(z8, (Object) "retryableStatusCodes cannot be empty without perAttemptRecvTimeout");
                retryPolicy = new RetryPolicy(min, longValue, longValue2, doubleValue, stringAsDuration3, listOfStatusCodesAsSet);
            }
            this.retryPolicy = retryPolicy;
            if (z) {
                map3 = JsonUtil.getObject(map, "hedgingPolicy");
            } else {
                map3 = null;
            }
            if (map3 == null) {
                hedgingPolicy = null;
            } else {
                Integer numberAsInteger4 = JsonUtil.getNumberAsInteger(map3, str);
                numberAsInteger4.getClass();
                int intValue2 = numberAsInteger4.intValue();
                if (intValue2 >= 2) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                ContextDataProvider.checkArgument(z9, "maxAttempts must be greater than 1: %s", intValue2);
                int min2 = Math.min(intValue2, i2);
                Long stringAsDuration4 = JsonUtil.getStringAsDuration(map3, "hedgingDelay");
                stringAsDuration4.getClass();
                long longValue3 = stringAsDuration4.longValue();
                if (longValue3 >= 0) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                ContextDataProvider.checkArgument(z10, "hedgingDelay must not be negative: %s", longValue3);
                Set listOfStatusCodesAsSet2 = ServiceConfigUtil.getListOfStatusCodesAsSet(map3, "nonFatalStatusCodes");
                if (listOfStatusCodesAsSet2 == null) {
                    listOfStatusCodesAsSet2 = DesugarCollections.unmodifiableSet(EnumSet.noneOf(Status.Code.class));
                } else {
                    ContextDataProvider.verify(true ^ listOfStatusCodesAsSet2.contains(Status.Code.OK), "%s must not contain OK", "nonFatalStatusCodes");
                }
                hedgingPolicy = new HedgingPolicy(min2, longValue3, listOfStatusCodesAsSet2);
            }
            this.hedgingPolicy = hedgingPolicy;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof MethodInfo)) {
                return false;
            }
            MethodInfo methodInfo = (MethodInfo) obj;
            if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.timeoutNanos, methodInfo.timeoutNanos) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.waitForReady, methodInfo.waitForReady) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.maxInboundMessageSize, methodInfo.maxInboundMessageSize) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.maxOutboundMessageSize, methodInfo.maxOutboundMessageSize) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.retryPolicy, methodInfo.retryPolicy) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.hedgingPolicy, methodInfo.hedgingPolicy)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.timeoutNanos, this.waitForReady, this.maxInboundMessageSize, this.maxOutboundMessageSize, this.retryPolicy, this.hedgingPolicy});
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("timeoutNanos", this.timeoutNanos);
            stringHelper.addHolder$ar$ds("waitForReady", this.waitForReady);
            stringHelper.addHolder$ar$ds("maxInboundMessageSize", this.maxInboundMessageSize);
            stringHelper.addHolder$ar$ds("maxOutboundMessageSize", this.maxOutboundMessageSize);
            stringHelper.addHolder$ar$ds("retryPolicy", this.retryPolicy);
            stringHelper.addHolder$ar$ds("hedgingPolicy", this.hedgingPolicy);
            return stringHelper.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ServiceConfigConvertedSelector extends InternalConfigSelector {
        final ManagedChannelServiceConfig config;

        public ServiceConfigConvertedSelector(ManagedChannelServiceConfig managedChannelServiceConfig) {
            this.config = managedChannelServiceConfig;
        }

        @Override // io.grpc.InternalConfigSelector
        public final MultiChildLoadBalancer.AcceptResolvedAddrRetVal selectConfig$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging() {
            boolean z;
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
            onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = this.config;
            if (onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode != null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "config is not set");
            return new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(Status.OK, onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode);
        }
    }

    public ManagedChannelServiceConfig(MethodInfo methodInfo, Map map, Map map2, RetriableStream.Throttle throttle, Object obj, Map map3) {
        Map map4;
        this.defaultMethodConfig = methodInfo;
        this.serviceMethodMap = DesugarCollections.unmodifiableMap(new HashMap(map));
        this.serviceMap = DesugarCollections.unmodifiableMap(new HashMap(map2));
        this.retryThrottling = throttle;
        this.loadBalancingConfig = obj;
        if (map3 != null) {
            map4 = DesugarCollections.unmodifiableMap(new HashMap(map3));
        } else {
            map4 = null;
        }
        this.healthCheckingConfig = map4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.defaultMethodConfig, managedChannelServiceConfig.defaultMethodConfig) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.serviceMethodMap, managedChannelServiceConfig.serviceMethodMap) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.serviceMap, managedChannelServiceConfig.serviceMap) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.retryThrottling, managedChannelServiceConfig.retryThrottling) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.loadBalancingConfig, managedChannelServiceConfig.loadBalancingConfig)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final InternalConfigSelector getDefaultConfigSelector() {
        if (this.serviceMap.isEmpty() && this.serviceMethodMap.isEmpty() && this.defaultMethodConfig == null) {
            return null;
        }
        return new ServiceConfigConvertedSelector(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final MethodInfo getMethodConfig(MethodDescriptor methodDescriptor) {
        MethodInfo methodInfo = (MethodInfo) this.serviceMethodMap.get(methodDescriptor.fullMethodName);
        if (methodInfo == null) {
            methodInfo = (MethodInfo) this.serviceMap.get(methodDescriptor.serviceName);
        }
        if (methodInfo == null) {
            return this.defaultMethodConfig;
        }
        return methodInfo;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.defaultMethodConfig, this.serviceMethodMap, this.serviceMap, this.retryThrottling, this.loadBalancingConfig});
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("defaultMethodConfig", this.defaultMethodConfig);
        stringHelper.addHolder$ar$ds("serviceMethodMap", this.serviceMethodMap);
        stringHelper.addHolder$ar$ds("serviceMap", this.serviceMap);
        stringHelper.addHolder$ar$ds("retryThrottling", this.retryThrottling);
        stringHelper.addHolder$ar$ds("loadBalancingConfig", this.loadBalancingConfig);
        return stringHelper.toString();
    }
}
