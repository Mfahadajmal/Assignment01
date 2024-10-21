package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.VerifyException;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.LoadBalancerProvider;
import io.grpc.Status;
import j$.util.DesugarCollections;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServiceConfigUtil {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LbConfig {
        public final String policyName;
        public final Map rawConfigValue;

        public LbConfig(String str, Map map) {
            str.getClass();
            this.policyName = str;
            map.getClass();
            this.rawConfigValue = map;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof LbConfig) {
                LbConfig lbConfig = (LbConfig) obj;
                if (this.policyName.equals(lbConfig.policyName) && this.rawConfigValue.equals(lbConfig.rawConfigValue)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.policyName, this.rawConfigValue});
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("policyName", this.policyName);
            stringHelper.addHolder$ar$ds("rawConfigValue", this.rawConfigValue);
            return stringHelper.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PolicySelection {
        final Object config;
        final LoadBalancerProvider provider;

        public PolicySelection(LoadBalancerProvider loadBalancerProvider, Object obj) {
            this.provider = loadBalancerProvider;
            this.config = obj;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                PolicySelection policySelection = (PolicySelection) obj;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.provider, policySelection.provider) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.config, policySelection.config)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.provider, this.config});
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("provider", this.provider);
            stringHelper.addHolder$ar$ds("config", this.config);
            return stringHelper.toString();
        }
    }

    public static Set getListOfStatusCodesAsSet(Map map, String str) {
        Status.Code code;
        boolean z;
        List list = JsonUtil.getList(map, str);
        if (list == null) {
            return null;
        }
        EnumSet noneOf = EnumSet.noneOf(Status.Code.class);
        for (Object obj : list) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                int intValue = d.intValue();
                boolean z2 = true;
                if (intValue == d.doubleValue()) {
                    z = true;
                } else {
                    z = false;
                }
                ContextDataProvider.verify(z, "Status code %s is not integral", obj);
                code = Status.fromCodeValue(intValue).code;
                if (code.value != d.intValue()) {
                    z2 = false;
                }
                ContextDataProvider.verify(z2, "Status code %s is not valid", obj);
            } else if (obj instanceof String) {
                try {
                    code = (Status.Code) Enum.valueOf(Status.Code.class, (String) obj);
                } catch (IllegalArgumentException e) {
                    throw new VerifyException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(obj, "Status code ", " is not valid"), e);
                }
            } else {
                throw new VerifyException("Can not convert status code " + String.valueOf(obj) + " to Status.Code, because its type is " + String.valueOf(obj.getClass()));
            }
            noneOf.add(code);
        }
        return DesugarCollections.unmodifiableSet(noneOf);
    }

    public static final PasswordAuthentication requestPasswordAuthentication$ar$ds(String str, InetAddress inetAddress, int i) {
        URL url;
        try {
            url = new URL("https", str, i, "");
        } catch (MalformedURLException unused) {
            ProxyDetectorImpl.log.logp(Level.WARNING, "io.grpc.internal.ProxyDetectorImpl$1", "requestPasswordAuthentication", "failed to create URL for Authenticator: {0} {1}", new Object[]{"https", str});
            url = null;
        }
        return Authenticator.requestPasswordAuthentication(str, inetAddress, i, "https", "", null, url, Authenticator.RequestorType.PROXY);
    }
}
