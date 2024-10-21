package com.google.gson.internal.bind;

import _COROUTINE._BOUNDARY;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final List reflectionFilters;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Adapter extends TypeAdapter {
        private final FieldsData fieldsData;

        public Adapter(FieldsData fieldsData) {
            this.fieldsData = fieldsData;
        }

        @Override // com.google.gson.TypeAdapter
        public final void write(JsonWriter jsonWriter, Object obj) {
            if (obj == null) {
                jsonWriter.nullValue$ar$ds();
                return;
            }
            jsonWriter.beginObject$ar$ds();
            try {
                Iterator it = this.fieldsData.serializedFields.iterator();
                while (it.hasNext()) {
                    ((BoundField) it.next()).write(jsonWriter, obj);
                }
                jsonWriter.endObject$ar$ds();
            } catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BoundField {
        final Field field;
        final String serializedName;
        final /* synthetic */ Method val$accessor;
        final /* synthetic */ boolean val$blockInaccessible;
        final /* synthetic */ TypeAdapter val$writeTypeAdapter;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public BoundField(String str, Field field, boolean z, Method method, TypeAdapter typeAdapter) {
            this(str, field);
            this.val$blockInaccessible = z;
            this.val$accessor = method;
            this.val$writeTypeAdapter = typeAdapter;
        }

        public final void write(JsonWriter jsonWriter, Object obj) {
            Object obj2;
            if (this.val$blockInaccessible) {
                Method method = this.val$accessor;
                if (method == null) {
                    ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                } else {
                    ReflectiveTypeAdapterFactory.checkAccessible(obj, method);
                }
            }
            Method method2 = this.val$accessor;
            if (method2 != null) {
                try {
                    obj2 = method2.invoke(obj, null);
                } catch (InvocationTargetException e) {
                    throw new JsonIOException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(ReflectionHelper.getAccessibleObjectDescription(this.val$accessor, false), "Accessor ", " threw exception"), e.getCause());
                }
            } else {
                obj2 = this.field.get(obj);
            }
            if (obj2 == obj) {
                return;
            }
            jsonWriter.name$ar$ds(this.serializedName);
            this.val$writeTypeAdapter.write(jsonWriter, obj2);
        }

        protected BoundField(String str, Field field) {
            this.serializedName = str;
            this.field = field;
            field.getName();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FieldReflectionAdapter extends Adapter {
        public FieldReflectionAdapter(FieldsData fieldsData) {
            super(fieldsData);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FieldsData {
        public static final FieldsData EMPTY;
        public final List serializedFields;

        static {
            Collections.emptyMap();
            EMPTY = new FieldsData(Collections.emptyList());
        }

        public FieldsData(List list) {
            this.serializedFields = list;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RecordAdapter extends Adapter {
        static final Map PRIMITIVE_DEFAULTS;
        private final Map componentIndices;
        private final Constructor constructor;
        private final Object[] constructorArgsDefaults;

        static {
            HashMap hashMap = new HashMap();
            hashMap.put(Byte.TYPE, (byte) 0);
            hashMap.put(Short.TYPE, (short) 0);
            hashMap.put(Integer.TYPE, 0);
            hashMap.put(Long.TYPE, 0L);
            hashMap.put(Float.TYPE, Float.valueOf(0.0f));
            hashMap.put(Double.TYPE, Double.valueOf(0.0d));
            hashMap.put(Character.TYPE, (char) 0);
            hashMap.put(Boolean.TYPE, false);
            PRIMITIVE_DEFAULTS = hashMap;
        }

        public RecordAdapter(Class cls, FieldsData fieldsData, boolean z) {
            super(fieldsData);
            this.componentIndices = new HashMap();
            Constructor canonicalRecordConstructor = ReflectionHelper.RECORD_HELPER.getCanonicalRecordConstructor(cls);
            this.constructor = canonicalRecordConstructor;
            if (z) {
                ReflectiveTypeAdapterFactory.checkAccessible(null, canonicalRecordConstructor);
            } else {
                ReflectionHelper.makeAccessible(canonicalRecordConstructor);
            }
            String[] recordComponentNames = ReflectionHelper.RECORD_HELPER.getRecordComponentNames(cls);
            for (int i = 0; i < recordComponentNames.length; i++) {
                this.componentIndices.put(recordComponentNames[i], Integer.valueOf(i));
            }
            Class<?>[] parameterTypes = this.constructor.getParameterTypes();
            this.constructorArgsDefaults = new Object[parameterTypes.length];
            for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                this.constructorArgsDefaults[i2] = PRIMITIVE_DEFAULTS.get(parameterTypes[i2]);
            }
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory, List list) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        this.reflectionFilters = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void checkAccessible(Object obj, AccessibleObject accessibleObject) {
        if (true == Modifier.isStatic(((Member) accessibleObject).getModifiers())) {
            obj = null;
        }
        if (CustomRemoteModelManager.canAccess(accessibleObject, obj)) {
        } else {
            throw new JsonIOException(String.valueOf(ReflectionHelper.getAccessibleObjectDescription(accessibleObject, true)).concat(" is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type."));
        }
    }

    private static IllegalArgumentException createDuplicateFieldException(Class cls, String str, Field field, Field field2) {
        throw new IllegalArgumentException("Class " + cls.getName() + " declares multiple JSON fields named '" + str + "'; conflict is caused by fields " + ReflectionHelper.fieldToString(field) + " and " + ReflectionHelper.fieldToString(field2) + "\nSee " + CustomRemoteModelManager.createUrl("duplicate-fields"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v7, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int] */
    /* JADX WARN: Type inference failed for: r5v9 */
    private final FieldsData getBoundFields(Gson gson, TypeToken typeToken, Class cls, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        Method method;
        int i;
        Collection collection;
        ?? r5;
        ?? r15;
        TypeToken typeToken2;
        String str;
        Field field;
        int i2;
        int i3;
        boolean z5;
        TypeAdapter typeAdapter;
        TypeToken typeToken3;
        TypeAdapter typeAdapter2;
        TypeAdapter typeAdapter3;
        if (cls.isInterface()) {
            return FieldsData.EMPTY;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        TypeToken typeToken4 = typeToken;
        boolean z6 = z;
        Class cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            boolean z7 = true;
            boolean z8 = false;
            if (cls2 != cls && declaredFields.length > 0) {
                int filterResult$ar$edu = CustomRemoteModelManager.getFilterResult$ar$edu(this.reflectionFilters, cls2);
                if (filterResult$ar$edu != 4) {
                    if (filterResult$ar$edu == 3) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                } else {
                    throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + String.valueOf(cls2) + " (supertype of " + String.valueOf(cls) + "). Register a TypeAdapter for this type or adjust the access filter.");
                }
            } else {
                z3 = z6;
            }
            int length = declaredFields.length;
            int i4 = 0;
            while (i4 < length) {
                Field field2 = declaredFields[i4];
                boolean includeField = includeField(field2, z7);
                boolean includeField2 = includeField(field2, z8);
                if (!includeField) {
                    if (!includeField2) {
                        i3 = i4;
                        i2 = length;
                        z5 = z8;
                        i4 = i3 + 1;
                        z8 = z5;
                        length = i2;
                        z7 = true;
                    } else {
                        includeField2 = z7;
                    }
                }
                if (z2) {
                    if (Modifier.isStatic(field2.getModifiers())) {
                        method = null;
                        z4 = z8;
                    } else {
                        Method accessor = ReflectionHelper.RECORD_HELPER.getAccessor(cls2, field2);
                        if (!z3) {
                            ReflectionHelper.makeAccessible(accessor);
                        }
                        if (accessor.getAnnotation(SerializedName.class) != null && field2.getAnnotation(SerializedName.class) == null) {
                            throw new JsonIOException("@SerializedName on " + ReflectionHelper.getAccessibleObjectDescription(accessor, z8) + " is not supported");
                        }
                        z4 = includeField2;
                        method = accessor;
                    }
                } else {
                    z4 = includeField2;
                    method = null;
                }
                if (!z3 && method == null) {
                    ReflectionHelper.makeAccessible(field2);
                }
                Type resolve = C$Gson$Types.resolve(typeToken4.type, cls2, field2.getGenericType());
                SerializedName serializedName = (SerializedName) field2.getAnnotation(SerializedName.class);
                if (serializedName == null) {
                    switch (((Enum) this.fieldNamingPolicy).ordinal()) {
                        case 0:
                            i = i4;
                            r5 = z8;
                            r15 = Collections.singletonList(field2.getName());
                            break;
                        case 1:
                            throw null;
                        case 2:
                            throw null;
                        case 3:
                            throw null;
                        case 4:
                            throw null;
                        case 5:
                            throw null;
                        case 6:
                            throw null;
                        default:
                            throw null;
                    }
                } else {
                    String value = serializedName.value();
                    String[] alternate = serializedName.alternate();
                    int length2 = alternate.length;
                    if (length2 == 0) {
                        collection = Collections.singletonList(value);
                        i = i4;
                    } else {
                        i = i4;
                        ArrayList arrayList = new ArrayList(length2 + 1);
                        arrayList.add(value);
                        Collections.addAll(arrayList, alternate);
                        collection = arrayList;
                    }
                    r5 = 0;
                    r15 = collection;
                }
                String str2 = (String) r15.get(r5);
                TypeToken typeToken5 = new TypeToken(resolve);
                Class cls3 = typeToken5.rawType;
                if (cls3 instanceof Class) {
                    cls3.isPrimitive();
                }
                int modifiers = field2.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    Modifier.isFinal(modifiers);
                }
                JsonAdapter jsonAdapter = (JsonAdapter) field2.getAnnotation(JsonAdapter.class);
                if (jsonAdapter != null) {
                    typeToken2 = typeToken5;
                    str = str2;
                    field = field2;
                    i3 = i;
                    z5 = false;
                    i2 = length;
                    typeAdapter = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken2, jsonAdapter, false);
                } else {
                    typeToken2 = typeToken5;
                    str = str2;
                    field = field2;
                    i2 = length;
                    i3 = i;
                    z5 = false;
                    typeAdapter = null;
                }
                if (typeAdapter == null) {
                    typeToken3 = typeToken2;
                    typeAdapter2 = gson.getAdapter(typeToken3);
                } else {
                    typeToken3 = typeToken2;
                    typeAdapter2 = typeAdapter;
                }
                if (includeField && typeAdapter == null) {
                    typeAdapter3 = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, typeToken3.type);
                } else {
                    typeAdapter3 = typeAdapter2;
                }
                BoundField boundField = new BoundField(str, field, z3, method, typeAdapter3);
                if (z4) {
                    for (String str3 : r15) {
                        BoundField boundField2 = (BoundField) linkedHashMap.put(str3, boundField);
                        if (boundField2 != null) {
                            throw createDuplicateFieldException(cls, str3, boundField2.field, field);
                        }
                    }
                }
                Field field3 = field;
                if (includeField) {
                    String str4 = str;
                    BoundField boundField3 = (BoundField) linkedHashMap2.put(str4, boundField);
                    if (boundField3 != null) {
                        throw createDuplicateFieldException(cls, str4, boundField3.field, field3);
                    }
                } else {
                    continue;
                }
                i4 = i3 + 1;
                z8 = z5;
                length = i2;
                z7 = true;
            }
            typeToken4 = new TypeToken(C$Gson$Types.resolve(typeToken4.type, cls2, cls2.getGenericSuperclass()));
            cls2 = typeToken4.rawType;
            z6 = z3;
        }
        return new FieldsData(new ArrayList(linkedHashMap2.values()));
    }

    private final boolean includeField(Field field, boolean z) {
        List list;
        Excluder excluder = this.excluder;
        int i = excluder.modifiers;
        if ((field.getModifiers() & 136) == 0) {
            double d = excluder.version;
            if (!field.isSynthetic() && !excluder.excludeClass(field.getType(), z)) {
                if (z) {
                    list = excluder.serializationStrategies;
                } else {
                    list = excluder.deserializationStrategies;
                }
                if (!list.isEmpty()) {
                    new FieldAttributes(field);
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (((ExclusionStrategy) it.next()).shouldSkipField$ar$ds()) {
                            return false;
                        }
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public final TypeAdapter create(Gson gson, TypeToken typeToken) {
        boolean z;
        Class cls = typeToken.rawType;
        if (!Object.class.isAssignableFrom(cls)) {
            return null;
        }
        if (ReflectionHelper.isAnonymousOrNonStaticLocal(cls)) {
            return new TypeAdapter() { // from class: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.1
                public final String toString() {
                    return "AnonymousOrNonStaticLocalClassAdapter";
                }

                @Override // com.google.gson.TypeAdapter
                public final void write(JsonWriter jsonWriter, Object obj) {
                    jsonWriter.nullValue$ar$ds();
                }
            };
        }
        int filterResult$ar$edu = CustomRemoteModelManager.getFilterResult$ar$edu(this.reflectionFilters, cls);
        if (filterResult$ar$edu != 4) {
            if (filterResult$ar$edu == 3) {
                z = true;
            } else {
                z = false;
            }
            if (ReflectionHelper.RECORD_HELPER.isRecord(cls)) {
                return new RecordAdapter(cls, getBoundFields(gson, typeToken, cls, z, true), z);
            }
            this.constructorConstructor.get(typeToken);
            return new FieldReflectionAdapter(getBoundFields(gson, typeToken, cls, z, false));
        }
        throw new JsonIOException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(cls, "ReflectionAccessFilter does not permit using reflection for ", ". Register a TypeAdapter for this type or adjust the access filter."));
    }
}
