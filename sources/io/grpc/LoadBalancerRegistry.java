package io.grpc;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageCaptioningLoadLogEvent;
import io.grpc.ManagedChannelRegistry;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LoadBalancerRegistry {
    private static final Iterable HARDCODED_CLASSES;
    private static LoadBalancerRegistry instance;
    private static final Logger logger = Logger.getLogger(LoadBalancerRegistry.class.getName());
    private final LinkedHashSet allProviders = new LinkedHashSet();
    private final LinkedHashMap effectiveProviders = new LinkedHashMap();

    static {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.internal.PickFirstLoadBalancerProvider"));
        } catch (ClassNotFoundException e) {
            logger.logp(Level.WARNING, "io.grpc.LoadBalancerRegistry", "getHardCodedClasses", "Unable to find pick-first LoadBalancer", (Throwable) e);
        }
        try {
            arrayList.add(Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider"));
        } catch (ClassNotFoundException e2) {
            logger.logp(Level.FINE, "io.grpc.LoadBalancerRegistry", "getHardCodedClasses", "Unable to find round-robin LoadBalancer", (Throwable) e2);
        }
        HARDCODED_CLASSES = DesugarCollections.unmodifiableList(arrayList);
    }

    private final synchronized void addProvider(LoadBalancerProvider loadBalancerProvider) {
        loadBalancerProvider.isAvailable$ar$ds();
        ContextDataProvider.checkArgument(true, (Object) "isAvailable() returned false");
        this.allProviders.add(loadBalancerProvider);
    }

    public static synchronized LoadBalancerRegistry getDefaultRegistry() {
        LoadBalancerRegistry loadBalancerRegistry;
        synchronized (LoadBalancerRegistry.class) {
            if (instance == null) {
                List<LoadBalancerProvider> loadAll = OnDeviceImageCaptioningLoadLogEvent.loadAll(LoadBalancerProvider.class, HARDCODED_CLASSES, LoadBalancerProvider.class.getClassLoader(), new ManagedChannelRegistry.ManagedChannelPriorityAccessor(1));
                instance = new LoadBalancerRegistry();
                for (LoadBalancerProvider loadBalancerProvider : loadAll) {
                    logger.logp(Level.FINE, "io.grpc.LoadBalancerRegistry", "getDefaultRegistry", "Service loader found ".concat(String.valueOf(String.valueOf(loadBalancerProvider))));
                    instance.addProvider(loadBalancerProvider);
                }
                instance.refreshProviderMap();
            }
            loadBalancerRegistry = instance;
        }
        return loadBalancerRegistry;
    }

    private final synchronized void refreshProviderMap() {
        this.effectiveProviders.clear();
        Iterator it = this.allProviders.iterator();
        while (it.hasNext()) {
            LoadBalancerProvider loadBalancerProvider = (LoadBalancerProvider) it.next();
            String policyName = loadBalancerProvider.getPolicyName();
            if (((LoadBalancerProvider) this.effectiveProviders.get(policyName)) != null) {
                loadBalancerProvider.getPriority$ar$ds();
            } else {
                this.effectiveProviders.put(policyName, loadBalancerProvider);
            }
        }
    }

    public final synchronized LoadBalancerProvider getProvider(String str) {
        return (LoadBalancerProvider) this.effectiveProviders.get(str);
    }
}
