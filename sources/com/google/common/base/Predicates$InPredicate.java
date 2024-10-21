package com.google.common.base;

import java.io.Serializable;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Predicates$InPredicate implements Predicate, Serializable {
    private static final long serialVersionUID = 0;
    private final Collection target;

    public Predicates$InPredicate(Collection collection) {
        collection.getClass();
        this.target = collection;
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        try {
            return this.target.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // com.google.common.base.Predicate
    public final boolean equals(Object obj) {
        if (obj instanceof Predicates$InPredicate) {
            return this.target.equals(((Predicates$InPredicate) obj).target);
        }
        return false;
    }

    public final int hashCode() {
        return this.target.hashCode();
    }

    public final String toString() {
        return "Predicates.in(" + this.target.toString() + ")";
    }
}
