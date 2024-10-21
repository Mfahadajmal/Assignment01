package com.google.common.base;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Predicates$NotPredicate implements Predicate, Serializable {
    private static final long serialVersionUID = 0;
    final Predicate predicate;

    public Predicates$NotPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        if (!this.predicate.apply(obj)) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.base.Predicate
    public final boolean equals(Object obj) {
        if (obj instanceof Predicates$NotPredicate) {
            return this.predicate.equals(((Predicates$NotPredicate) obj).predicate);
        }
        return false;
    }

    public final int hashCode() {
        return ~this.predicate.hashCode();
    }

    public final String toString() {
        return "Predicates.not(" + this.predicate.toString() + ")";
    }
}
