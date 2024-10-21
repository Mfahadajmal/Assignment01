package com.google.firebase.components;

import com.google.firebase.components.Qualified;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Dependency {
    public final Qualified anInterface;
    private final int injection;
    public final int type;

    public Dependency(Class cls, int i, int i2) {
        this.anInterface = new Qualified(Qualified.Unqualified.class, cls);
        this.type = i;
        this.injection = i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Dependency) {
            Dependency dependency = (Dependency) obj;
            if (this.anInterface.equals(dependency.anInterface) && this.type == dependency.type && this.injection == dependency.injection) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.anInterface.hashCode() ^ 1000003) * 1000003) ^ this.type) * 1000003) ^ this.injection;
    }

    public final boolean isDirectInjection() {
        if (this.injection == 0) {
            return true;
        }
        return false;
    }

    public final boolean isSet() {
        if (this.type == 2) {
            return true;
        }
        return false;
    }

    public final String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.anInterface);
        sb.append(", type=");
        if (this.type == 1) {
            str = "required";
        } else {
            str = "set";
        }
        sb.append(str);
        sb.append(", injection=");
        if (this.injection != 0) {
            str2 = "provider";
        } else {
            str2 = "direct";
        }
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }
}
