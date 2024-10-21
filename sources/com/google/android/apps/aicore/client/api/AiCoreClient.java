package com.google.android.apps.aicore.client.api;

import com.google.android.apps.aicore.aidl.IAICoreService;
import com.google.android.apps.aicore.client.api.internal.FailureSignal;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AiCoreClient extends AutoCloseable {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ServiceContext {
        private final FailureSignal disconnectSignal;
        private final IAICoreService service;

        public ServiceContext() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ServiceContext) {
                ServiceContext serviceContext = (ServiceContext) obj;
                if (this.service.equals(serviceContext.getService()) && this.disconnectSignal.equals(serviceContext.getDisconnectSignal())) {
                    return true;
                }
            }
            return false;
        }

        public final FailureSignal getDisconnectSignal() {
            return this.disconnectSignal;
        }

        public final IAICoreService getService() {
            return this.service;
        }

        public final int hashCode() {
            return ((this.service.hashCode() ^ 1000003) * 1000003) ^ this.disconnectSignal.hashCode();
        }

        public final String toString() {
            FailureSignal failureSignal = this.disconnectSignal;
            return "ServiceContext{service=" + this.service.toString() + ", disconnectSignal=" + failureSignal.toString() + "}";
        }

        public ServiceContext(IAICoreService iAICoreService, FailureSignal failureSignal) {
            this();
            if (iAICoreService == null) {
                throw new NullPointerException("Null service");
            }
            this.service = iAICoreService;
            if (failureSignal != null) {
                this.disconnectSignal = failureSignal;
                return;
            }
            throw new NullPointerException("Null disconnectSignal");
        }
    }

    ListenableFuture getFeatureStatus(AiFeature aiFeature);

    ListenableFuture getServiceContext();

    ListenableFuture requestDownloadableFeature(AiFeature aiFeature, DownloadCallback downloadCallback);
}
