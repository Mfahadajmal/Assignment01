package com.google.common.base;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Predicates$CompositionPredicate implements Predicate, Serializable {
    private static final long serialVersionUID = 0;
    final Function f;
    final Predicate p;

    public Predicates$CompositionPredicate(Predicate predicate, Function function) {
        this.p = predicate;
        function.getClass();
        this.f = function;
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        return this.p.apply(this.f.apply(obj));
    }

    @Override // com.google.common.base.Predicate
    public final boolean equals(Object obj) {
        if (obj instanceof Predicates$CompositionPredicate) {
            Predicates$CompositionPredicate predicates$CompositionPredicate = (Predicates$CompositionPredicate) obj;
            if (this.f.equals(predicates$CompositionPredicate.f) && this.p.equals(predicates$CompositionPredicate.p)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        Predicate predicate = this.p;
        return predicate.hashCode() ^ this.f.hashCode();
    }

    public final String toString() {
        Function function = this.f;
        return this.p.toString() + "(" + function.toString() + ")";
    }
}
