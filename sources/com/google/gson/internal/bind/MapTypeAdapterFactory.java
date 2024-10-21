package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final /* synthetic */ int switching_field;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Adapter extends TypeAdapter {
        private final TypeAdapter valueTypeAdapter;

        public Adapter(Gson gson, Type type, TypeAdapter typeAdapter) {
            this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
        }

        @Override // com.google.gson.TypeAdapter
        public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
            Map map = (Map) obj;
            if (map == null) {
                jsonWriter.nullValue$ar$ds();
                return;
            }
            jsonWriter.beginObject$ar$ds();
            for (Map.Entry entry : map.entrySet()) {
                jsonWriter.name$ar$ds(String.valueOf(entry.getKey()));
                this.valueTypeAdapter.write(jsonWriter, entry.getValue());
            }
            jsonWriter.endObject$ar$ds();
        }
    }

    public MapTypeAdapterFactory(ConstructorConstructor constructorConstructor, int i) {
        this.switching_field = i;
        this.constructorConstructor = constructorConstructor;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public final TypeAdapter create(final Gson gson, TypeToken typeToken) {
        Type[] typeArr;
        final Type type;
        if (this.switching_field != 0) {
            Class cls = typeToken.rawType;
            if (!Collection.class.isAssignableFrom(cls)) {
                return null;
            }
            Type supertype = C$Gson$Types.getSupertype(typeToken.type, cls, Collection.class);
            if (supertype instanceof ParameterizedType) {
                type = ((ParameterizedType) supertype).getActualTypeArguments()[0];
            } else {
                type = Object.class;
            }
            final TypeAdapter adapter = gson.getAdapter(new TypeToken(type));
            this.constructorConstructor.get(typeToken);
            return new TypeAdapter(gson, type, adapter) { // from class: com.google.gson.internal.bind.CollectionTypeAdapterFactory$Adapter
                private final TypeAdapter elementTypeAdapter;

                {
                    this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, adapter, type);
                }

                @Override // com.google.gson.TypeAdapter
                public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                    Collection collection = (Collection) obj;
                    if (collection == null) {
                        jsonWriter.nullValue$ar$ds();
                        return;
                    }
                    jsonWriter.beginArray$ar$ds();
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        this.elementTypeAdapter.write(jsonWriter, it.next());
                    }
                    jsonWriter.endArray$ar$ds();
                }
            };
        }
        Class cls2 = typeToken.rawType;
        if (!Map.class.isAssignableFrom(cls2)) {
            return null;
        }
        Type type2 = typeToken.type;
        if (type2 == Properties.class) {
            typeArr = new Type[]{String.class, String.class};
        } else {
            Type supertype2 = C$Gson$Types.getSupertype(type2, cls2, Map.class);
            if (supertype2 instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) supertype2).getActualTypeArguments();
            } else {
                typeArr = new Type[]{Object.class, Object.class};
            }
        }
        Type type3 = typeArr[0];
        if (type3 != Boolean.TYPE && type3 != Boolean.class) {
            gson.getAdapter(new TypeToken(type3));
        }
        TypeAdapter adapter2 = gson.getAdapter(new TypeToken(typeArr[1]));
        this.constructorConstructor.get(typeToken);
        Type type4 = typeArr[0];
        return new Adapter(gson, typeArr[1], adapter2);
    }
}
