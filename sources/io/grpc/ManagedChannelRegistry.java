package io.grpc;

import android.support.v7.widget.GapWorker;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageCaptioningLoadLogEvent;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManagedChannelRegistry {
    private static ManagedChannelRegistry instance;
    private static final Logger logger = Logger.getLogger(ManagedChannelRegistry.class.getName());
    private final LinkedHashSet allProviders = new LinkedHashSet();
    private List effectiveProviders = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ManagedChannelPriorityAccessor implements ServiceProviders$PriorityAccessor {
        private final /* synthetic */ int switching_field;

        public ManagedChannelPriorityAccessor(int i) {
            this.switching_field = i;
        }

        @Override // io.grpc.ServiceProviders$PriorityAccessor
        public final /* synthetic */ int getPriority(Object obj) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    ((NameResolverProvider) obj).priority$ar$ds();
                    return 5;
                }
                ((LoadBalancerProvider) obj).getPriority$ar$ds();
                return 5;
            }
            return ((ManagedChannelProvider) obj).priority();
        }

        @Override // io.grpc.ServiceProviders$PriorityAccessor
        public final /* synthetic */ void isAvailable$ar$ds$a901c8e_0(Object obj) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    ((NameResolverProvider) obj).isAvailable$ar$ds$f3439281_1();
                    return;
                } else {
                    ((LoadBalancerProvider) obj).isAvailable$ar$ds();
                    return;
                }
            }
            ((ManagedChannelProvider) obj).isAvailable$ar$ds$f3439281_0();
        }
    }

    private final synchronized void addProvider(ManagedChannelProvider managedChannelProvider) {
        managedChannelProvider.isAvailable$ar$ds$f3439281_0();
        ContextDataProvider.checkArgument(true, (Object) "isAvailable() returned false");
        this.allProviders.add(managedChannelProvider);
    }

    public static synchronized ManagedChannelRegistry getDefaultRegistry() {
        ManagedChannelRegistry managedChannelRegistry;
        synchronized (ManagedChannelRegistry.class) {
            if (instance == null) {
                ArrayList arrayList = new ArrayList();
                try {
                    arrayList.add(Class.forName("io.grpc.okhttp.OkHttpChannelProvider"));
                } catch (ClassNotFoundException e) {
                    logger.logp(Level.FINE, "io.grpc.ManagedChannelRegistry", "getHardCodedClasses", "Unable to find OkHttpChannelProvider", (Throwable) e);
                }
                try {
                    arrayList.add(Class.forName("io.grpc.netty.NettyChannelProvider"));
                } catch (ClassNotFoundException e2) {
                    logger.logp(Level.FINE, "io.grpc.ManagedChannelRegistry", "getHardCodedClasses", "Unable to find NettyChannelProvider", (Throwable) e2);
                }
                try {
                    arrayList.add(Class.forName("io.grpc.netty.UdsNettyChannelProvider"));
                } catch (ClassNotFoundException e3) {
                    logger.logp(Level.FINE, "io.grpc.ManagedChannelRegistry", "getHardCodedClasses", "Unable to find UdsNettyChannelProvider", (Throwable) e3);
                }
                List<ManagedChannelProvider> loadAll = OnDeviceImageCaptioningLoadLogEvent.loadAll(ManagedChannelProvider.class, DesugarCollections.unmodifiableList(arrayList), ManagedChannelProvider.class.getClassLoader(), new ManagedChannelPriorityAccessor(0));
                instance = new ManagedChannelRegistry();
                for (ManagedChannelProvider managedChannelProvider : loadAll) {
                    logger.logp(Level.FINE, "io.grpc.ManagedChannelRegistry", "getDefaultRegistry", "Service loader found ".concat(String.valueOf(String.valueOf(managedChannelProvider))));
                    instance.addProvider(managedChannelProvider);
                }
                instance.refreshProviders();
            }
            managedChannelRegistry = instance;
        }
        return managedChannelRegistry;
    }

    private final synchronized void refreshProviders() {
        ArrayList arrayList = new ArrayList(this.allProviders);
        Collections.sort(arrayList, Collections.reverseOrder(new GapWorker.AnonymousClass1(12)));
        this.effectiveProviders = DesugarCollections.unmodifiableList(arrayList);
    }

    public final synchronized List providers() {
        return this.effectiveProviders;
    }
}
