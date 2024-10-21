package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = new Excluder();
    public final double version = -1.0d;
    public final int modifiers = 136;
    private final boolean serializeInnerClasses = true;
    public final List serializationStrategies = Collections.emptyList();
    public final List deserializationStrategies = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final Excluder m219clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.google.gson.TypeAdapterFactory
    public final TypeAdapter create(final Gson gson, final TypeToken typeToken) {
        Class cls = typeToken.rawType;
        final boolean excludeClass = excludeClass(cls, true);
        boolean excludeClass2 = excludeClass(cls, false);
        if (!excludeClass && !excludeClass2) {
            return null;
        }
        return new TypeAdapter(this) { // from class: com.google.gson.internal.Excluder.1
            private volatile TypeAdapter delegate;
            final /* synthetic */ Excluder this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.gson.TypeAdapter
            public final void write(JsonWriter jsonWriter, Object obj) {
                if (!excludeClass) {
                    TypeAdapter typeAdapter = this.delegate;
                    if (typeAdapter == null) {
                        typeAdapter = gson.getDelegateAdapter(this.this$0, typeToken);
                        this.delegate = typeAdapter;
                    }
                    typeAdapter.write(jsonWriter, obj);
                    return;
                }
                jsonWriter.nullValue$ar$ds();
            }
        };
    }

    public final boolean excludeClass(Class cls, boolean z) {
        List list;
        if (!z) {
            if (!Enum.class.isAssignableFrom(cls) && ReflectionHelper.isAnonymousOrNonStaticLocal(cls)) {
                return true;
            }
            list = this.deserializationStrategies;
        } else {
            list = this.serializationStrategies;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ExclusionStrategy) it.next()).shouldSkipClass$ar$ds()) {
                return true;
            }
        }
        return false;
    }
}
