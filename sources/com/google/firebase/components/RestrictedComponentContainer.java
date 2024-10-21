package com.google.firebase.components;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.firebase.components.Qualified;
import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import j$.util.DesugarCollections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
final class RestrictedComponentContainer implements ComponentContainer {
    private final Set allowedDirectInterfaces;
    private final Set allowedProviderInterfaces;
    private final Set allowedPublishedEvents;
    private final Set allowedSetDirectInterfaces;
    private final ComponentContainer delegateContainer;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RestrictedPublisher implements Publisher {
    }

    public RestrictedComponentContainer(Component component, ComponentContainer componentContainer) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        for (Dependency dependency : component.dependencies) {
            if (dependency.isDirectInjection()) {
                if (dependency.isSet()) {
                    hashSet4.add(dependency.anInterface);
                } else {
                    hashSet.add(dependency.anInterface);
                }
            } else if (dependency.isSet()) {
                hashSet5.add(dependency.anInterface);
            } else {
                hashSet2.add(dependency.anInterface);
            }
        }
        if (!component.publishedEvents.isEmpty()) {
            hashSet.add(new Qualified(Qualified.Unqualified.class, Publisher.class));
        }
        this.allowedDirectInterfaces = DesugarCollections.unmodifiableSet(hashSet);
        this.allowedProviderInterfaces = DesugarCollections.unmodifiableSet(hashSet2);
        DesugarCollections.unmodifiableSet(hashSet3);
        this.allowedSetDirectInterfaces = DesugarCollections.unmodifiableSet(hashSet4);
        DesugarCollections.unmodifiableSet(hashSet5);
        this.allowedPublishedEvents = component.publishedEvents;
        this.delegateContainer = componentContainer;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final Object get(Class cls) {
        if (this.allowedDirectInterfaces.contains(new Qualified(Qualified.Unqualified.class, cls))) {
            Object $default$get = ContextDataProvider.$default$get(this.delegateContainer, cls);
            if (!cls.equals(Publisher.class)) {
                return $default$get;
            }
            return new RestrictedPublisher();
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", cls));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final Provider getProvider(Qualified qualified) {
        throw null;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final Set setOf(Qualified qualified) {
        if (this.allowedSetDirectInterfaces.contains(qualified)) {
            return ContextDataProvider.$default$setOf(this.delegateContainer, qualified);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Set<%s>.", qualified));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final Provider setOfProvider(Qualified qualified) {
        throw null;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public final Provider getProvider(Class cls) {
        Qualified qualified = new Qualified(Qualified.Unqualified.class, cls);
        if (this.allowedProviderInterfaces.contains(qualified)) {
            return this.delegateContainer.getProvider(qualified);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<%s>.", qualified));
    }
}
