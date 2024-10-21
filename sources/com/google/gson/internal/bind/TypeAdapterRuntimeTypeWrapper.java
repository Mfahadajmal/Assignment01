package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypeAdapterRuntimeTypeWrapper extends TypeAdapter {
    private final Gson context;
    private final TypeAdapter delegate;
    private final Type type;

    public TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter typeAdapter, Type type) {
        this.context = gson;
        this.delegate = typeAdapter;
        this.type = type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.reflect.Type] */
    @Override // com.google.gson.TypeAdapter
    public final void write(JsonWriter jsonWriter, Object obj) {
        Class<?> cls;
        TypeAdapter serializationDelegate;
        ?? r0 = this.type;
        if (obj != null && ((r0 instanceof Class) || (r0 instanceof TypeVariable))) {
            cls = obj.getClass();
        } else {
            cls = r0;
        }
        TypeAdapter typeAdapter = this.delegate;
        if (cls != r0) {
            typeAdapter = this.context.getAdapter(new TypeToken(cls));
            if (typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter) {
                TypeAdapter typeAdapter2 = this.delegate;
                while ((typeAdapter2 instanceof SerializationDelegatingTypeAdapter) && (serializationDelegate = ((SerializationDelegatingTypeAdapter) typeAdapter2).getSerializationDelegate()) != typeAdapter2) {
                    typeAdapter2 = serializationDelegate;
                }
                if (!(typeAdapter2 instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                    typeAdapter = this.delegate;
                }
            }
        }
        typeAdapter.write(jsonWriter, obj);
    }
}
