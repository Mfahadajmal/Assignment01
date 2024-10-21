package com.google.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SerializationDelegatingTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Gson {
    private final ConstructorConstructor constructorConstructor;
    final List factories;
    public final FormattingStyle formattingStyle;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final ThreadLocal threadLocalAdapterResults;
    private final ConcurrentMap typeTokenCache;
    static final FormattingStyle DEFAULT_FORMATTING_STYLE = FormattingStyle.COMPACT;
    static final FieldNamingStrategy DEFAULT_FIELD_NAMING_STRATEGY = FieldNamingPolicy.IDENTITY;
    static final ToNumberStrategy DEFAULT_OBJECT_TO_NUMBER_STRATEGY = ToNumberPolicy.DOUBLE;
    static final ToNumberStrategy DEFAULT_NUMBER_TO_NUMBER_STRATEGY = ToNumberPolicy.LAZILY_PARSED_NUMBER;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FutureTypeAdapter extends SerializationDelegatingTypeAdapter {
        public TypeAdapter delegate = null;

        private final TypeAdapter delegate() {
            TypeAdapter typeAdapter = this.delegate;
            if (typeAdapter != null) {
                return typeAdapter;
            }
            throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }

        @Override // com.google.gson.internal.bind.SerializationDelegatingTypeAdapter
        public final TypeAdapter getSerializationDelegate() {
            return delegate();
        }

        @Override // com.google.gson.TypeAdapter
        public final void write(JsonWriter jsonWriter, Object obj) {
            delegate().write(jsonWriter, obj);
        }
    }

    public Gson() {
        Object anonymousClass1;
        final TypeAdapter typeAdapter;
        TypeAdapterFactory newFactory;
        Excluder excluder = Excluder.DEFAULT;
        FieldNamingStrategy fieldNamingStrategy = DEFAULT_FIELD_NAMING_STRATEGY;
        Map emptyMap = Collections.emptyMap();
        FormattingStyle formattingStyle = DEFAULT_FORMATTING_STYLE;
        int i = LongSerializationPolicy.DEFAULT$ar$edu$c432eb35_0;
        Collections.emptyList();
        Collections.emptyList();
        List emptyList = Collections.emptyList();
        ToNumberStrategy toNumberStrategy = DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        ToNumberStrategy toNumberStrategy2 = DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        List emptyList2 = Collections.emptyList();
        this.threadLocalAdapterResults = new ThreadLocal();
        this.typeTokenCache = new ConcurrentHashMap();
        ConstructorConstructor constructorConstructor = new ConstructorConstructor(emptyMap, emptyList2);
        this.constructorConstructor = constructorConstructor;
        this.formattingStyle = formattingStyle;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        TypeAdapterFactory typeAdapterFactory = ObjectTypeAdapter.DOUBLE_FACTORY;
        if (toNumberStrategy == ToNumberPolicy.DOUBLE) {
            anonymousClass1 = ObjectTypeAdapter.DOUBLE_FACTORY;
        } else {
            anonymousClass1 = new ObjectTypeAdapter.AnonymousClass1(toNumberStrategy, 0);
        }
        arrayList.add(anonymousClass1);
        arrayList.add(excluder);
        arrayList.addAll(emptyList);
        arrayList.add(TypeAdapters.STRING_FACTORY);
        arrayList.add(TypeAdapters.INTEGER_FACTORY);
        arrayList.add(TypeAdapters.BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.BYTE_FACTORY);
        arrayList.add(TypeAdapters.SHORT_FACTORY);
        if (i == LongSerializationPolicy.DEFAULT$ar$edu$c432eb35_0) {
            typeAdapter = TypeAdapters.LONG;
        } else {
            typeAdapter = new TypeAdapter() { // from class: com.google.gson.Gson.3
                @Override // com.google.gson.TypeAdapter
                public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                    Number number = (Number) obj;
                    if (number == null) {
                        jsonWriter.nullValue$ar$ds();
                    } else {
                        jsonWriter.value$ar$ds$1248a0e6_0(number.toString());
                    }
                }
            };
        }
        arrayList.add(new TypeAdapters.AnonymousClass33(Long.TYPE, Long.class, typeAdapter, 1, null));
        arrayList.add(new TypeAdapters.AnonymousClass33(Double.TYPE, Double.class, new TypeAdapter() { // from class: com.google.gson.Gson.1
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                Number number = (Number) obj;
                if (number == null) {
                    jsonWriter.nullValue$ar$ds();
                    return;
                }
                double doubleValue = number.doubleValue();
                Gson.checkValidFloatingPoint(doubleValue);
                jsonWriter.value$ar$ds(doubleValue);
            }
        }, 1, null));
        arrayList.add(new TypeAdapters.AnonymousClass33(Float.TYPE, Float.class, new TypeAdapter() { // from class: com.google.gson.Gson.2
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                Number number = (Number) obj;
                if (number == null) {
                    jsonWriter.nullValue$ar$ds();
                    return;
                }
                float floatValue = number.floatValue();
                Gson.checkValidFloatingPoint(floatValue);
                if (!(number instanceof Float)) {
                    number = Float.valueOf(floatValue);
                }
                jsonWriter.value$ar$ds$8dbd521e_0(number);
            }
        }, 1, null));
        TypeAdapterFactory typeAdapterFactory2 = NumberTypeAdapter.LAZILY_PARSED_NUMBER_FACTORY;
        if (toNumberStrategy2 == ToNumberPolicy.LAZILY_PARSED_NUMBER) {
            newFactory = NumberTypeAdapter.LAZILY_PARSED_NUMBER_FACTORY;
        } else {
            newFactory = NumberTypeAdapter.newFactory(toNumberStrategy2);
        }
        arrayList.add(newFactory);
        arrayList.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        arrayList.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        arrayList.add(new TypeAdapters.AnonymousClass34(AtomicLong.class, new TypeAdapter.AnonymousClass1(), 1));
        arrayList.add(new TypeAdapters.AnonymousClass34(AtomicLongArray.class, new TypeAdapter.AnonymousClass1(), 1));
        arrayList.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        arrayList.add(TypeAdapters.CHARACTER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arrayList.add(new TypeAdapters.AnonymousClass34(BigDecimal.class, TypeAdapters.BIG_DECIMAL, 1));
        arrayList.add(new TypeAdapters.AnonymousClass34(BigInteger.class, TypeAdapters.BIG_INTEGER, 1));
        arrayList.add(new TypeAdapters.AnonymousClass34(LazilyParsedNumber.class, TypeAdapters.LAZILY_PARSED_NUMBER, 1));
        arrayList.add(TypeAdapters.URL_FACTORY);
        arrayList.add(TypeAdapters.URI_FACTORY);
        arrayList.add(TypeAdapters.UUID_FACTORY);
        arrayList.add(TypeAdapters.CURRENCY_FACTORY);
        arrayList.add(TypeAdapters.LOCALE_FACTORY);
        arrayList.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arrayList.add(TypeAdapters.BIT_SET_FACTORY);
        arrayList.add(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
        arrayList.add(TypeAdapters.CALENDAR_FACTORY);
        if (SqlTypesSupport.SUPPORTS_SQL_TYPES) {
            arrayList.add(SqlTypesSupport.TIME_FACTORY);
            arrayList.add(SqlTypesSupport.DATE_FACTORY);
            arrayList.add(SqlTypesSupport.TIMESTAMP_FACTORY);
        }
        arrayList.add(ArrayTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.CLASS_FACTORY);
        arrayList.add(new MapTypeAdapterFactory(constructorConstructor, 1));
        arrayList.add(new MapTypeAdapterFactory(constructorConstructor, 0));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor);
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(TypeAdapters.ENUM_FACTORY);
        arrayList.add(new ReflectiveTypeAdapterFactory(constructorConstructor, fieldNamingStrategy, excluder, jsonAdapterAnnotationTypeAdapterFactory, emptyList2));
        this.factories = DesugarCollections.unmodifiableList(arrayList);
    }

    static void checkValidFloatingPoint(double d) {
        if (!Double.isNaN(d) && !Double.isInfinite(d)) {
            return;
        }
        throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    }

    public final TypeAdapter getAdapter(TypeToken typeToken) {
        boolean z;
        TypeAdapter typeAdapter = (TypeAdapter) this.typeTokenCache.get(typeToken);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map map = (Map) this.threadLocalAdapterResults.get();
        boolean z2 = false;
        if (map == null) {
            map = new HashMap();
            this.threadLocalAdapterResults.set(map);
            z = true;
        } else {
            TypeAdapter typeAdapter2 = (TypeAdapter) map.get(typeToken);
            if (typeAdapter2 != null) {
                return typeAdapter2;
            }
            z = false;
        }
        try {
            FutureTypeAdapter futureTypeAdapter = new FutureTypeAdapter();
            map.put(typeToken, futureTypeAdapter);
            Iterator it = this.factories.iterator();
            TypeAdapter typeAdapter3 = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                typeAdapter3 = ((TypeAdapterFactory) it.next()).create(this, typeToken);
                if (typeAdapter3 != null) {
                    if (futureTypeAdapter.delegate == null) {
                        futureTypeAdapter.delegate = typeAdapter3;
                        map.put(typeToken, typeAdapter3);
                    } else {
                        throw new AssertionError("Delegate is already set");
                    }
                }
            }
            if (z) {
                this.threadLocalAdapterResults.remove();
                z2 = true;
            }
            if (typeAdapter3 == null) {
                throw new IllegalArgumentException("GSON (2.10.1) cannot handle ".concat(typeToken.toString()));
            }
            if (z2) {
                this.typeTokenCache.putAll(map);
            }
            return typeAdapter3;
        } catch (Throwable th) {
            if (z) {
                this.threadLocalAdapterResults.remove();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0034, code lost:
    
        if (r0.putFactoryAndGetCurrent(r1, (com.google.gson.TypeAdapterFactory) com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.createAdapter(r0.constructorConstructor, r2)) == r5) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0013, code lost:
    
        if (r2 == r5) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.gson.TypeAdapter getDelegateAdapter(com.google.gson.TypeAdapterFactory r5, com.google.gson.reflect.TypeToken r6) {
        /*
            r4 = this;
            com.google.gson.TypeAdapterFactory r0 = com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.TREE_TYPE_CLASS_DUMMY_FACTORY
            if (r5 != r0) goto L5
            goto L36
        L5:
            com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory r0 = r4.jsonAdapterFactory
            java.lang.Class r1 = r6.rawType
            java.util.concurrent.ConcurrentMap r2 = r0.adapterFactoryMap
            java.lang.Object r2 = r2.get(r1)
            com.google.gson.TypeAdapterFactory r2 = (com.google.gson.TypeAdapterFactory) r2
            if (r2 == 0) goto L16
            if (r2 != r5) goto L38
            goto L36
        L16:
            com.google.gson.annotations.JsonAdapter r2 = com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getAnnotation(r1)
            if (r2 == 0) goto L38
            java.lang.Class r2 = r2.value()
            java.lang.Class<com.google.gson.TypeAdapterFactory> r3 = com.google.gson.TypeAdapterFactory.class
            boolean r3 = r3.isAssignableFrom(r2)
            if (r3 == 0) goto L38
            com.google.gson.internal.ConstructorConstructor r3 = r0.constructorConstructor
            java.lang.Object r2 = com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.createAdapter(r3, r2)
            com.google.gson.TypeAdapterFactory r2 = (com.google.gson.TypeAdapterFactory) r2
            com.google.gson.TypeAdapterFactory r0 = r0.putFactoryAndGetCurrent(r1, r2)
            if (r0 != r5) goto L38
        L36:
            com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory r5 = r4.jsonAdapterFactory
        L38:
            java.util.List r0 = r4.factories
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L3f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L58
            java.lang.Object r2 = r0.next()
            com.google.gson.TypeAdapterFactory r2 = (com.google.gson.TypeAdapterFactory) r2
            if (r1 != 0) goto L51
            if (r2 != r5) goto L3f
            r1 = 1
            goto L3f
        L51:
            com.google.gson.TypeAdapter r2 = r2.create(r4, r6)
            if (r2 == 0) goto L3f
            return r2
        L58:
            if (r1 != 0) goto L5f
            com.google.gson.TypeAdapter r5 = r4.getAdapter(r6)
            return r5
        L5f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "GSON cannot serialize or deserialize "
            java.lang.String r6 = r0.concat(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.getDelegateAdapter(com.google.gson.TypeAdapterFactory, com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter");
    }

    public final void toJson(Object obj, Type type, JsonWriter jsonWriter) {
        TypeAdapter adapter = getAdapter(new TypeToken(type));
        int i = jsonWriter.strictness$ar$edu;
        if (i == 2) {
            jsonWriter.setStrictness$ar$edu(1);
        }
        boolean z = jsonWriter.htmlSafe;
        boolean z2 = jsonWriter.serializeNulls;
        jsonWriter.htmlSafe = true;
        jsonWriter.serializeNulls = false;
        try {
            try {
                adapter.write(jsonWriter, obj);
            } catch (IOException e) {
                throw new JsonIOException(e);
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.10.1): " + e2.getMessage(), e2);
            }
        } finally {
            jsonWriter.setStrictness$ar$edu(i);
            jsonWriter.htmlSafe = z;
            jsonWriter.serializeNulls = z2;
        }
    }

    public final String toString() {
        ConstructorConstructor constructorConstructor = this.constructorConstructor;
        return "{serializeNulls:false,factories:" + String.valueOf(this.factories) + ",instanceCreators:" + constructorConstructor.toString() + "}";
    }

    public final TypeAdapter getAdapter(Class cls) {
        return getAdapter(new TypeToken(cls));
    }
}
