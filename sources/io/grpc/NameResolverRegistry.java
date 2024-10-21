package io.grpc;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageCaptioningLoadLogEvent;
import io.grpc.ManagedChannelRegistry;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NameResolverRegistry {
    private static NameResolverRegistry instance;
    private static final Logger logger = Logger.getLogger(NameResolverRegistry.class.getName());
    private String defaultScheme = "unknown";
    private final LinkedHashSet allProviders = new LinkedHashSet();
    private ImmutableMap effectiveProviders = RegularImmutableMap.EMPTY;

    private final synchronized void addProvider(NameResolverProvider nameResolverProvider) {
        nameResolverProvider.isAvailable$ar$ds$f3439281_1();
        ContextDataProvider.checkArgument(true, (Object) "isAvailable() returned false");
        this.allProviders.add(nameResolverProvider);
    }

    public static synchronized NameResolverRegistry getDefaultRegistry() {
        NameResolverRegistry nameResolverRegistry;
        synchronized (NameResolverRegistry.class) {
            if (instance == null) {
                ArrayList arrayList = new ArrayList();
                try {
                    arrayList.add(Class.forName("io.grpc.internal.DnsNameResolverProvider"));
                } catch (ClassNotFoundException e) {
                    logger.logp(Level.FINE, "io.grpc.NameResolverRegistry", "getHardCodedClasses", "Unable to find DNS NameResolver", (Throwable) e);
                }
                List<NameResolverProvider> loadAll = OnDeviceImageCaptioningLoadLogEvent.loadAll(NameResolverProvider.class, DesugarCollections.unmodifiableList(arrayList), NameResolverProvider.class.getClassLoader(), new ManagedChannelRegistry.ManagedChannelPriorityAccessor(2));
                if (loadAll.isEmpty()) {
                    logger.logp(Level.WARNING, "io.grpc.NameResolverRegistry", "getDefaultRegistry", "No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
                }
                instance = new NameResolverRegistry();
                for (NameResolverProvider nameResolverProvider : loadAll) {
                    logger.logp(Level.FINE, "io.grpc.NameResolverRegistry", "getDefaultRegistry", "Service loader found ".concat(String.valueOf(String.valueOf(nameResolverProvider))));
                    instance.addProvider(nameResolverProvider);
                }
                instance.refreshProviders();
            }
            nameResolverRegistry = instance;
        }
        return nameResolverRegistry;
    }

    private final synchronized void refreshProviders() {
        HashMap hashMap = new HashMap();
        Iterator it = this.allProviders.iterator();
        String str = "unknown";
        char c = 0;
        while (it.hasNext()) {
            NameResolverProvider nameResolverProvider = (NameResolverProvider) it.next();
            String defaultScheme = nameResolverProvider.getDefaultScheme();
            if (((NameResolverProvider) hashMap.get(defaultScheme)) != null) {
                nameResolverProvider.priority$ar$ds();
            } else {
                hashMap.put(defaultScheme, nameResolverProvider);
            }
            nameResolverProvider.priority$ar$ds();
            if (c < 5) {
                nameResolverProvider.priority$ar$ds();
                str = nameResolverProvider.getDefaultScheme();
            }
            c = 5;
        }
        this.effectiveProviders = ImmutableMap.copyOf((Map) hashMap);
        this.defaultScheme = str;
    }

    public final synchronized String getDefaultScheme() {
        return this.defaultScheme;
    }

    public final NameResolverProvider getProviderForScheme(String str) {
        if (str == null) {
            return null;
        }
        return (NameResolverProvider) providers().get(str.toLowerCase(Locale.US));
    }

    final synchronized Map providers() {
        return this.effectiveProviders;
    }

    public final synchronized void register(NameResolverProvider nameResolverProvider) {
        addProvider(nameResolverProvider);
        refreshProviders();
    }
}
