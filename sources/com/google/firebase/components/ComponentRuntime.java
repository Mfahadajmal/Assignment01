package com.google.firebase.components;

import android.util.Log;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.firebase.dynamicloading.ComponentLoader;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ComponentRuntime implements ComponentContainer, ComponentLoader {
    private static final Provider EMPTY_PROVIDER = new OptionalProvider$$ExternalSyntheticLambda1(1);
    private final ComponentRegistrarProcessor componentRegistrarProcessor;
    private final EventBus eventBus;
    private final List unprocessedRegistrarProviders;
    public final Map components = new HashMap();
    private final Map lazyInstanceMap = new HashMap();
    private final Map lazySetMap = new HashMap();
    private Set processedCoroutineDispatcherInterfaces = new HashSet();
    public final AtomicReference eagerComponentsInitializedWith = new AtomicReference();

    public ComponentRuntime(Executor executor, Iterable iterable, Collection collection, ComponentRegistrarProcessor componentRegistrarProcessor) {
        EventBus eventBus = new EventBus();
        this.eventBus = eventBus;
        this.componentRegistrarProcessor = componentRegistrarProcessor;
        ArrayList<Component> arrayList = new ArrayList();
        int i = 1;
        arrayList.add(Component.of(eventBus, EventBus.class, Subscriber.class, Publisher.class));
        arrayList.add(Component.of(this, ComponentLoader.class, new Class[0]));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Component component = (Component) it.next();
            if (component != null) {
                arrayList.add(component);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = iterable.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next());
        }
        this.unprocessedRegistrarProviders = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        synchronized (this) {
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                try {
                    ComponentRegistrar componentRegistrar = (ComponentRegistrar) ((Provider) it3.next()).get();
                    if (componentRegistrar != null) {
                        arrayList.addAll(componentRegistrar.getComponents());
                        it3.remove();
                    }
                } catch (InvalidRegistrarException e) {
                    it3.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", e);
                }
            }
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                Object[] array = ((Component) it4.next()).providedInterfaces.toArray();
                int length = array.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        Object obj = array[i2];
                        if (obj.toString().contains("kotlinx.coroutines.CoroutineDispatcher")) {
                            if (this.processedCoroutineDispatcherInterfaces.contains(obj.toString())) {
                                it4.remove();
                                break;
                            }
                            this.processedCoroutineDispatcherInterfaces.add(obj.toString());
                        }
                        i2++;
                    }
                }
            }
            if (this.components.isEmpty()) {
                ContextDataProvider.detect(arrayList);
            } else {
                ArrayList arrayList4 = new ArrayList(this.components.keySet());
                arrayList4.addAll(arrayList);
                ContextDataProvider.detect(arrayList4);
            }
            for (final Component component2 : arrayList) {
                this.components.put(component2, new Lazy(new Provider() { // from class: com.google.firebase.components.ComponentRuntime$$ExternalSyntheticLambda3
                    @Override // com.google.firebase.inject.Provider
                    public final Object get() {
                        Component component3 = component2;
                        return component3.factory.create(new RestrictedComponentContainer(component3, ComponentRuntime.this));
                    }
                }));
            }
            ArrayList arrayList5 = new ArrayList();
            for (Component component3 : arrayList) {
                if (component3.isValue()) {
                    Provider provider = (Provider) this.components.get(component3);
                    for (Qualified qualified : component3.providedInterfaces) {
                        if (!this.lazyInstanceMap.containsKey(qualified)) {
                            this.lazyInstanceMap.put(qualified, provider);
                        } else {
                            arrayList5.add(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0((OptionalProvider) ((Provider) this.lazyInstanceMap.get(qualified)), provider, 20));
                        }
                    }
                }
            }
            arrayList3.addAll(arrayList5);
            ArrayList arrayList6 = new ArrayList();
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : this.components.entrySet()) {
                Component component4 = (Component) entry.getKey();
                if (!component4.isValue()) {
                    Provider provider2 = (Provider) entry.getValue();
                    for (Qualified qualified2 : component4.providedInterfaces) {
                        if (!hashMap.containsKey(qualified2)) {
                            hashMap.put(qualified2, new HashSet());
                        }
                        ((Set) hashMap.get(qualified2)).add(provider2);
                    }
                }
            }
            for (Map.Entry entry2 : hashMap.entrySet()) {
                if (!this.lazySetMap.containsKey(entry2.getKey())) {
                    this.lazySetMap.put((Qualified) entry2.getKey(), LazySet.fromCollection((Collection) entry2.getValue()));
                } else {
                    LazySet lazySet = (LazySet) this.lazySetMap.get(entry2.getKey());
                    Iterator it5 = ((Set) entry2.getValue()).iterator();
                    while (it5.hasNext()) {
                        arrayList6.add(new EventBus$$ExternalSyntheticLambda0(lazySet, (Provider) it5.next(), i, null));
                    }
                }
            }
            arrayList3.addAll(arrayList6);
            for (Component component5 : this.components.keySet()) {
                for (Dependency dependency : component5.dependencies) {
                    if (dependency.isSet() && !this.lazySetMap.containsKey(dependency.anInterface)) {
                        this.lazySetMap.put(dependency.anInterface, LazySet.fromCollection(Collections.emptySet()));
                    } else if (this.lazyInstanceMap.containsKey(dependency.anInterface)) {
                        continue;
                    } else if (dependency.type != 1) {
                        if (!dependency.isSet()) {
                            this.lazyInstanceMap.put(dependency.anInterface, new OptionalProvider(OptionalProvider.NOOP_HANDLER$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, OptionalProvider.EMPTY_PROVIDER));
                        }
                    } else {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component5, dependency.anInterface));
                    }
                }
            }
        }
        int size = arrayList3.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((Runnable) arrayList3.get(i3)).run();
        }
        Boolean bool = (Boolean) this.eagerComponentsInitializedWith.get();
        if (bool != null) {
            Map map = this.components;
            bool.booleanValue();
            doInitializeEagerComponents$ar$ds(map);
        }
    }

    public final void doInitializeEagerComponents$ar$ds(Map map) {
        Queue<Event> queue;
        for (Map.Entry entry : map.entrySet()) {
        }
        EventBus eventBus = this.eventBus;
        synchronized (eventBus) {
            queue = eventBus.pendingEvents;
            if (queue != null) {
                eventBus.pendingEvents = null;
            } else {
                queue = null;
            }
        }
        if (queue != null) {
            for (Event event : queue) {
                event.getClass();
                synchronized (eventBus) {
                    Queue queue2 = eventBus.pendingEvents;
                    if (queue2 != null) {
                        queue2.add(event);
                    } else {
                        for (Map.Entry entry2 : eventBus.getHandlers$ar$ds()) {
                            ((Executor) entry2.getValue()).execute(new EventBus$$ExternalSyntheticLambda0(entry2, event, 0));
                        }
                    }
                }
            }
        }
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final /* synthetic */ Object get(Class cls) {
        throw null;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final /* synthetic */ Provider getProvider(Class cls) {
        throw null;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final /* synthetic */ Set setOf(Qualified qualified) {
        throw null;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final synchronized Provider setOfProvider(Qualified qualified) {
        LazySet lazySet = (LazySet) this.lazySetMap.get(qualified);
        if (lazySet != null) {
            return lazySet;
        }
        return EMPTY_PROVIDER;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final synchronized Provider getProvider(Qualified qualified) {
        return (Provider) this.lazyInstanceMap.get(qualified);
    }
}
