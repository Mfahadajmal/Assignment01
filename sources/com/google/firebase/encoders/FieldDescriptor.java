package com.google.firebase.encoders;

import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.firebase.encoders.proto.Protobuf;
import j$.util.DesugarCollections;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FieldDescriptor {
    public final String name;
    private final Map properties;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private final Object FieldDescriptor$Builder$ar$name;
        public Object FieldDescriptor$Builder$ar$properties;

        public Builder(SplitInstallModule splitInstallModule) {
            this.FieldDescriptor$Builder$ar$name = splitInstallModule;
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
        public final FieldDescriptor build() {
            Map unmodifiableMap;
            ?? r1 = this.FieldDescriptor$Builder$ar$properties;
            if (r1 == 0) {
                unmodifiableMap = Collections.emptyMap();
            } else {
                unmodifiableMap = DesugarCollections.unmodifiableMap(new HashMap((Map) r1));
            }
            return new FieldDescriptor((String) this.FieldDescriptor$Builder$ar$name, unmodifiableMap);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
        public final void withProperty$ar$ds(Annotation annotation) {
            if (this.FieldDescriptor$Builder$ar$properties == null) {
                this.FieldDescriptor$Builder$ar$properties = new HashMap();
            }
            this.FieldDescriptor$Builder$ar$properties.put(Protobuf.class, annotation);
        }

        public Builder(String str) {
            this.FieldDescriptor$Builder$ar$properties = null;
            this.FieldDescriptor$Builder$ar$name = str;
        }
    }

    public FieldDescriptor(String str, Map map) {
        this.name = str;
        this.properties = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldDescriptor)) {
            return false;
        }
        FieldDescriptor fieldDescriptor = (FieldDescriptor) obj;
        if (this.name.equals(fieldDescriptor.name) && this.properties.equals(fieldDescriptor.properties)) {
            return true;
        }
        return false;
    }

    public final Annotation getProperty(Class cls) {
        return (Annotation) this.properties.get(cls);
    }

    public final int hashCode() {
        return (this.name.hashCode() * 31) + this.properties.hashCode();
    }

    public final String toString() {
        return "FieldDescriptor{name=" + this.name + ", properties=" + String.valueOf(this.properties.values()) + "}";
    }
}
