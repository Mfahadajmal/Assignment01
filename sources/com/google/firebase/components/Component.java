package com.google.firebase.components;

import com.google.firebase.components.Qualified;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Component {
    public final Set dependencies;
    public final ComponentFactory factory;
    public final Set providedInterfaces;
    public final Set publishedEvents;
    private final int type;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private final Set dependencies;
        private ComponentFactory factory;
        private final Set providedInterfaces;
        private final Set publishedEvents;
        public int type;

        @SafeVarargs
        public Builder(Class cls, Class... clsArr) {
            HashSet hashSet = new HashSet();
            this.providedInterfaces = hashSet;
            this.dependencies = new HashSet();
            this.type = 0;
            this.publishedEvents = new HashSet();
            hashSet.add(new Qualified(Qualified.Unqualified.class, cls));
            for (Class cls2 : clsArr) {
                if (cls2 != null) {
                    this.providedInterfaces.add(new Qualified(Qualified.Unqualified.class, cls2));
                } else {
                    throw new NullPointerException("Null interface");
                }
            }
        }

        public final void add$ar$ds$327096f_0(Dependency dependency) {
            if (!this.providedInterfaces.contains(dependency.anInterface)) {
                this.dependencies.add(dependency);
                return;
            }
            throw new IllegalArgumentException("Components are not allowed to depend on interfaces they themselves provide.");
        }

        public final Component build() {
            if (this.factory != null) {
                return new Component(new HashSet(this.providedInterfaces), new HashSet(this.dependencies), this.type, this.factory, this.publishedEvents);
            }
            throw new IllegalStateException("Missing required property: factory.");
        }

        public final void factory$ar$ds(ComponentFactory componentFactory) {
            this.factory = componentFactory;
        }
    }

    public Component(Set set, Set set2, int i, ComponentFactory componentFactory, Set set3) {
        this.providedInterfaces = DesugarCollections.unmodifiableSet(set);
        this.dependencies = DesugarCollections.unmodifiableSet(set2);
        this.type = i;
        this.factory = componentFactory;
        this.publishedEvents = DesugarCollections.unmodifiableSet(set3);
    }

    public static Builder builder(Class cls) {
        return new Builder(cls, new Class[0]);
    }

    public static Builder intoSetBuilder(Class cls) {
        Builder builder = builder(cls);
        builder.type = 1;
        return builder;
    }

    @SafeVarargs
    public static Component of(final Object obj, Class cls, Class... clsArr) {
        Builder builder = new Builder(cls, clsArr);
        builder.factory$ar$ds(new ComponentFactory() { // from class: com.google.firebase.components.Component$$ExternalSyntheticLambda4
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return obj;
            }
        });
        return builder.build();
    }

    public final boolean isValue() {
        if (this.type == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.providedInterfaces.toArray()) + ">{0, type=" + this.type + ", deps=" + Arrays.toString(this.dependencies.toArray()) + "}";
    }
}
