package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlDateTypeAdapter;
import com.google.gson.internal.sql.SqlTimeTypeAdapter;
import com.google.gson.internal.sql.SqlTimestampTypeAdapter;
import com.google.gson.reflect.TypeToken;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    public static final TypeAdapterFactory TREE_TYPE_CLASS_DUMMY_FACTORY = new DummyTypeAdapterFactory(0);
    private static final TypeAdapterFactory TREE_TYPE_FIELD_DUMMY_FACTORY = new DummyTypeAdapterFactory(0);
    public final ConcurrentMap adapterFactoryMap = new ConcurrentHashMap();
    public final ConstructorConstructor constructorConstructor;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DummyTypeAdapterFactory implements TypeAdapterFactory {
        private final /* synthetic */ int switching_field;

        public DummyTypeAdapterFactory(int i) {
            this.switching_field = i;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public final TypeAdapter create(Gson gson, TypeToken typeToken) {
            Type genericComponentType;
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (typeToken.rawType != Timestamp.class) {
                                    return null;
                                }
                                return new SqlTimestampTypeAdapter(gson.getAdapter(Date.class));
                            }
                            if (typeToken.rawType != Time.class) {
                                return null;
                            }
                            return new SqlTimeTypeAdapter();
                        }
                        if (typeToken.rawType != java.sql.Date.class) {
                            return null;
                        }
                        return new SqlDateTypeAdapter();
                    }
                    Class cls = typeToken.rawType;
                    if (!Enum.class.isAssignableFrom(cls) || cls == Enum.class) {
                        return null;
                    }
                    if (!cls.isEnum()) {
                        cls = cls.getSuperclass();
                    }
                    return new TypeAdapters.EnumTypeAdapter(cls);
                }
                Type type = typeToken.type;
                if (!(type instanceof GenericArrayType)) {
                    if (!(type instanceof Class)) {
                        return null;
                    }
                    Class cls2 = (Class) type;
                    if (!cls2.isArray()) {
                        return null;
                    }
                    genericComponentType = cls2.getComponentType();
                } else {
                    genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                }
                return new ArrayTypeAdapter(gson, gson.getAdapter(new TypeToken(genericComponentType)), C$Gson$Types.getRawType(genericComponentType));
            }
            throw new AssertionError("Factory should not be used");
        }
    }

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }

    public static Object createAdapter(ConstructorConstructor constructorConstructor, Class cls) {
        return constructorConstructor.get(new TypeToken(cls)).construct();
    }

    public static JsonAdapter getAnnotation(Class cls) {
        return (JsonAdapter) cls.getAnnotation(JsonAdapter.class);
    }

    @Override // com.google.gson.TypeAdapterFactory
    public final TypeAdapter create(Gson gson, TypeToken typeToken) {
        JsonAdapter annotation = getAnnotation(typeToken.rawType);
        if (annotation == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, typeToken, annotation, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final TypeAdapter getTypeAdapter(ConstructorConstructor constructorConstructor, Gson gson, TypeToken typeToken, JsonAdapter jsonAdapter, boolean z) {
        JsonSerializer jsonSerializer;
        TypeAdapterFactory typeAdapterFactory;
        TypeAdapter treeTypeAdapter;
        Object createAdapter = createAdapter(constructorConstructor, jsonAdapter.value());
        boolean z2 = createAdapter instanceof TypeAdapter;
        boolean nullSafe = jsonAdapter.nullSafe();
        if (z2) {
            treeTypeAdapter = (TypeAdapter) createAdapter;
        } else if (createAdapter instanceof TypeAdapterFactory) {
            TypeAdapterFactory typeAdapterFactory2 = (TypeAdapterFactory) createAdapter;
            if (z) {
                typeAdapterFactory2 = putFactoryAndGetCurrent(typeToken.rawType, typeAdapterFactory2);
            }
            treeTypeAdapter = typeAdapterFactory2.create(gson, typeToken);
        } else {
            if (!(createAdapter instanceof JsonSerializer)) {
                if (createAdapter instanceof JsonDeserializer) {
                    jsonSerializer = null;
                } else {
                    throw new IllegalArgumentException("Invalid attempt to bind an instance of " + createAdapter.getClass().getName() + " as a @JsonAdapter for " + typeToken.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
                }
            } else {
                jsonSerializer = (JsonSerializer) createAdapter;
            }
            JsonSerializer jsonSerializer2 = jsonSerializer;
            if (createAdapter instanceof JsonDeserializer) {
            }
            if (z) {
                typeAdapterFactory = TREE_TYPE_CLASS_DUMMY_FACTORY;
            } else {
                typeAdapterFactory = TREE_TYPE_FIELD_DUMMY_FACTORY;
            }
            treeTypeAdapter = new TreeTypeAdapter(jsonSerializer2, gson, typeToken, typeAdapterFactory, nullSafe);
            nullSafe = false;
        }
        if (treeTypeAdapter != null && nullSafe) {
            return new TypeAdapter.AnonymousClass1();
        }
        return treeTypeAdapter;
    }

    public final TypeAdapterFactory putFactoryAndGetCurrent(Class cls, TypeAdapterFactory typeAdapterFactory) {
        TypeAdapterFactory typeAdapterFactory2 = (TypeAdapterFactory) this.adapterFactoryMap.putIfAbsent(cls, typeAdapterFactory);
        if (typeAdapterFactory2 != null) {
            return typeAdapterFactory2;
        }
        return typeAdapterFactory;
    }
}
