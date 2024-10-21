package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.Writer;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypeAdapters {
    public static final TypeAdapter ATOMIC_BOOLEAN;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY;
    public static final TypeAdapter ATOMIC_INTEGER;
    public static final TypeAdapter ATOMIC_INTEGER_ARRAY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY;
    public static final TypeAdapter BIG_DECIMAL;
    public static final TypeAdapter BIG_INTEGER;
    public static final TypeAdapter BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter BOOLEAN;
    public static final TypeAdapter BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter CURRENCY;
    public static final TypeAdapterFactory CURRENCY_FACTORY;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter LAZILY_PARSED_NUMBER;
    public static final TypeAdapter LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter LONG;
    public static final TypeAdapter SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter STRING;
    public static final TypeAdapter STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapter URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    /* compiled from: PG */
    /* renamed from: com.google.gson.internal.bind.TypeAdapters$33, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass33 implements TypeAdapterFactory {
        private final /* synthetic */ int switching_field;
        final /* synthetic */ Class val$base;
        final /* synthetic */ Class val$sub;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass33(Class cls, Class cls2, TypeAdapter typeAdapter, int i) {
            this.switching_field = i;
            this.val$base = cls;
            this.val$sub = cls2;
            this.val$typeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public final TypeAdapter create(Gson gson, TypeToken typeToken) {
            if (this.switching_field != 0) {
                Class cls = typeToken.rawType;
                if (cls != this.val$sub && cls != this.val$base) {
                    return null;
                }
                return this.val$typeAdapter;
            }
            Class cls2 = typeToken.rawType;
            if (cls2 != this.val$base && cls2 != this.val$sub) {
                return null;
            }
            return this.val$typeAdapter;
        }

        public final String toString() {
            if (this.switching_field != 0) {
                Class cls = this.val$base;
                Class cls2 = this.val$sub;
                TypeAdapter typeAdapter = this.val$typeAdapter;
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + typeAdapter.toString() + "]";
            }
            Class cls3 = this.val$base;
            Class cls4 = this.val$sub;
            TypeAdapter typeAdapter2 = this.val$typeAdapter;
            return "Factory[type=" + cls3.getName() + "+" + cls4.getName() + ",adapter=" + typeAdapter2.toString() + "]";
        }

        public AnonymousClass33(Class cls, Class cls2, TypeAdapter typeAdapter, int i, byte[] bArr) {
            this.switching_field = i;
            this.val$sub = cls;
            this.val$base = cls2;
            this.val$typeAdapter = typeAdapter;
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.gson.internal.bind.TypeAdapters$34, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass34 implements TypeAdapterFactory {
        private final /* synthetic */ int switching_field;
        final /* synthetic */ Class val$clazz;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass34(Class cls, TypeAdapter typeAdapter, int i) {
            this.switching_field = i;
            this.val$clazz = cls;
            this.val$typeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public final TypeAdapter create(Gson gson, TypeToken typeToken) {
            if (this.switching_field != 0) {
                if (typeToken.rawType != this.val$clazz) {
                    return null;
                }
                return this.val$typeAdapter;
            }
            if (!this.val$clazz.isAssignableFrom(typeToken.rawType)) {
                return null;
            }
            return new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.34.1
                @Override // com.google.gson.TypeAdapter
                public final void write(JsonWriter jsonWriter, Object obj) {
                    AnonymousClass34.this.val$typeAdapter.write(jsonWriter, obj);
                }
            };
        }

        public final String toString() {
            if (this.switching_field != 0) {
                Class cls = this.val$clazz;
                TypeAdapter typeAdapter = this.val$typeAdapter;
                return "Factory[type=" + cls.getName() + ",adapter=" + typeAdapter.toString() + "]";
            }
            Class cls2 = this.val$clazz;
            TypeAdapter typeAdapter2 = this.val$typeAdapter;
            return "Factory[typeHierarchy=" + cls2.getName() + ",adapter=" + typeAdapter2.toString() + "]";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class EnumTypeAdapter extends TypeAdapter {
        private final Map nameToConstant = new HashMap();
        private final Map stringToConstant = new HashMap();
        private final Map constantToName = new HashMap();

        public EnumTypeAdapter(final Class cls) {
            try {
                for (Field field : (Field[]) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.google.gson.internal.bind.TypeAdapters.EnumTypeAdapter.1
                    @Override // java.security.PrivilegedAction
                    public final /* bridge */ /* synthetic */ Object run() {
                        Field[] declaredFields = cls.getDeclaredFields();
                        ArrayList arrayList = new ArrayList(declaredFields.length);
                        for (Field field2 : declaredFields) {
                            if (field2.isEnumConstant()) {
                                arrayList.add(field2);
                            }
                        }
                        Field[] fieldArr = (Field[]) arrayList.toArray(new Field[0]);
                        AccessibleObject.setAccessible(fieldArr, true);
                        return fieldArr;
                    }
                })) {
                    Enum r4 = (Enum) field.get(null);
                    String name = r4.name();
                    String str = r4.toString();
                    SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
                    if (serializedName != null) {
                        name = serializedName.value();
                        for (String str2 : serializedName.alternate()) {
                            this.nameToConstant.put(str2, r4);
                        }
                    }
                    this.nameToConstant.put(name, r4);
                    this.stringToConstant.put(str, r4);
                    this.constantToName.put(r4, name);
                }
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
            String str;
            Enum r3 = (Enum) obj;
            if (r3 == null) {
                str = null;
            } else {
                str = (String) this.constantToName.get(r3);
            }
            jsonWriter.value$ar$ds$1248a0e6_0(str);
        }
    }

    static {
        TypeAdapter.AnonymousClass1 anonymousClass1 = new TypeAdapter.AnonymousClass1();
        CLASS = anonymousClass1;
        CLASS_FACTORY = new AnonymousClass34(Class.class, anonymousClass1, 1);
        TypeAdapter.AnonymousClass1 anonymousClass12 = new TypeAdapter.AnonymousClass1();
        BIT_SET = anonymousClass12;
        BIT_SET_FACTORY = new AnonymousClass34(BitSet.class, anonymousClass12, 1);
        TypeAdapter typeAdapter = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.3
            @Override // com.google.gson.TypeAdapter
            public final /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String str;
                Boolean bool = (Boolean) obj;
                if (bool == null) {
                    jsonWriter.nullValue$ar$ds();
                    return;
                }
                jsonWriter.writeDeferredName();
                jsonWriter.beforeValue();
                Writer writer = jsonWriter.out;
                if (true != bool.booleanValue()) {
                    str = "false";
                } else {
                    str = "true";
                }
                writer.write(str);
            }
        };
        BOOLEAN = typeAdapter;
        BOOLEAN_AS_STRING = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.4
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String bool;
                Boolean bool2 = (Boolean) obj;
                if (bool2 == null) {
                    bool = "null";
                } else {
                    bool = bool2.toString();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(bool);
            }
        };
        BOOLEAN_FACTORY = new AnonymousClass33(Boolean.TYPE, Boolean.class, typeAdapter, 1, null);
        TypeAdapter typeAdapter2 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.5
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                if (((Number) obj) == null) {
                    jsonWriter.nullValue$ar$ds();
                } else {
                    jsonWriter.value$ar$ds$89b27ec2_0(r4.byteValue());
                }
            }
        };
        BYTE = typeAdapter2;
        BYTE_FACTORY = new AnonymousClass33(Byte.TYPE, Byte.class, typeAdapter2, 1, null);
        TypeAdapter typeAdapter3 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.6
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                if (((Number) obj) == null) {
                    jsonWriter.nullValue$ar$ds();
                } else {
                    jsonWriter.value$ar$ds$89b27ec2_0(r4.shortValue());
                }
            }
        };
        SHORT = typeAdapter3;
        SHORT_FACTORY = new AnonymousClass33(Short.TYPE, Short.class, typeAdapter3, 1, null);
        TypeAdapter typeAdapter4 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.7
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                if (((Number) obj) == null) {
                    jsonWriter.nullValue$ar$ds();
                } else {
                    jsonWriter.value$ar$ds$89b27ec2_0(r4.intValue());
                }
            }
        };
        INTEGER = typeAdapter4;
        INTEGER_FACTORY = new AnonymousClass33(Integer.TYPE, Integer.class, typeAdapter4, 1, null);
        TypeAdapter.AnonymousClass1 anonymousClass13 = new TypeAdapter.AnonymousClass1();
        ATOMIC_INTEGER = anonymousClass13;
        ATOMIC_INTEGER_FACTORY = new AnonymousClass34(AtomicInteger.class, anonymousClass13, 1);
        TypeAdapter.AnonymousClass1 anonymousClass14 = new TypeAdapter.AnonymousClass1();
        ATOMIC_BOOLEAN = anonymousClass14;
        ATOMIC_BOOLEAN_FACTORY = new AnonymousClass34(AtomicBoolean.class, anonymousClass14, 1);
        TypeAdapter.AnonymousClass1 anonymousClass15 = new TypeAdapter.AnonymousClass1();
        ATOMIC_INTEGER_ARRAY = anonymousClass15;
        ATOMIC_INTEGER_ARRAY_FACTORY = new AnonymousClass34(AtomicIntegerArray.class, anonymousClass15, 1);
        LONG = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.11
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                Number number = (Number) obj;
                if (number == null) {
                    jsonWriter.nullValue$ar$ds();
                } else {
                    jsonWriter.value$ar$ds$89b27ec2_0(number.longValue());
                }
            }
        };
        TypeAdapter typeAdapter5 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.14
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String obj2;
                Character ch = (Character) obj;
                if (ch == null) {
                    obj2 = null;
                } else {
                    obj2 = ch.toString();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(obj2);
            }
        };
        CHARACTER = typeAdapter5;
        CHARACTER_FACTORY = new AnonymousClass33(Character.TYPE, Character.class, typeAdapter5, 1, null);
        TypeAdapter typeAdapter6 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.15
            @Override // com.google.gson.TypeAdapter
            public final /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                jsonWriter.value$ar$ds$1248a0e6_0((String) obj);
            }
        };
        STRING = typeAdapter6;
        BIG_DECIMAL = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.16
            @Override // com.google.gson.TypeAdapter
            public final /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                jsonWriter.value$ar$ds$8dbd521e_0((BigDecimal) obj);
            }
        };
        BIG_INTEGER = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.17
            @Override // com.google.gson.TypeAdapter
            public final /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                jsonWriter.value$ar$ds$8dbd521e_0((BigInteger) obj);
            }
        };
        LAZILY_PARSED_NUMBER = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.18
            @Override // com.google.gson.TypeAdapter
            public final /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                jsonWriter.value$ar$ds$8dbd521e_0((LazilyParsedNumber) obj);
            }
        };
        STRING_FACTORY = new AnonymousClass34(String.class, typeAdapter6, 1);
        TypeAdapter typeAdapter7 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.19
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String sb;
                StringBuilder sb2 = (StringBuilder) obj;
                if (sb2 == null) {
                    sb = null;
                } else {
                    sb = sb2.toString();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(sb);
            }
        };
        STRING_BUILDER = typeAdapter7;
        STRING_BUILDER_FACTORY = new AnonymousClass34(StringBuilder.class, typeAdapter7, 1);
        TypeAdapter typeAdapter8 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.20
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String stringBuffer;
                StringBuffer stringBuffer2 = (StringBuffer) obj;
                if (stringBuffer2 == null) {
                    stringBuffer = null;
                } else {
                    stringBuffer = stringBuffer2.toString();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(stringBuffer);
            }
        };
        STRING_BUFFER = typeAdapter8;
        STRING_BUFFER_FACTORY = new AnonymousClass34(StringBuffer.class, typeAdapter8, 1);
        TypeAdapter typeAdapter9 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.21
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String externalForm;
                URL url = (URL) obj;
                if (url == null) {
                    externalForm = null;
                } else {
                    externalForm = url.toExternalForm();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(externalForm);
            }
        };
        URL = typeAdapter9;
        URL_FACTORY = new AnonymousClass34(URL.class, typeAdapter9, 1);
        TypeAdapter typeAdapter10 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.22
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String aSCIIString;
                URI uri = (URI) obj;
                if (uri == null) {
                    aSCIIString = null;
                } else {
                    aSCIIString = uri.toASCIIString();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(aSCIIString);
            }
        };
        URI = typeAdapter10;
        URI_FACTORY = new AnonymousClass34(URI.class, typeAdapter10, 1);
        TypeAdapter typeAdapter11 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.23
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String hostAddress;
                InetAddress inetAddress = (InetAddress) obj;
                if (inetAddress == null) {
                    hostAddress = null;
                } else {
                    hostAddress = inetAddress.getHostAddress();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(hostAddress);
            }
        };
        INET_ADDRESS = typeAdapter11;
        INET_ADDRESS_FACTORY = new AnonymousClass34(InetAddress.class, typeAdapter11, 0);
        TypeAdapter typeAdapter12 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.24
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String uuid;
                UUID uuid2 = (UUID) obj;
                if (uuid2 == null) {
                    uuid = null;
                } else {
                    uuid = uuid2.toString();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(uuid);
            }
        };
        UUID = typeAdapter12;
        UUID_FACTORY = new AnonymousClass34(UUID.class, typeAdapter12, 1);
        TypeAdapter.AnonymousClass1 anonymousClass16 = new TypeAdapter.AnonymousClass1();
        CURRENCY = anonymousClass16;
        CURRENCY_FACTORY = new AnonymousClass34(Currency.class, anonymousClass16, 1);
        TypeAdapter typeAdapter13 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.26
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                if (((Calendar) obj) == null) {
                    jsonWriter.nullValue$ar$ds();
                    return;
                }
                jsonWriter.beginObject$ar$ds();
                jsonWriter.name$ar$ds("year");
                jsonWriter.value$ar$ds$89b27ec2_0(r4.get(1));
                jsonWriter.name$ar$ds("month");
                jsonWriter.value$ar$ds$89b27ec2_0(r4.get(2));
                jsonWriter.name$ar$ds("dayOfMonth");
                jsonWriter.value$ar$ds$89b27ec2_0(r4.get(5));
                jsonWriter.name$ar$ds("hourOfDay");
                jsonWriter.value$ar$ds$89b27ec2_0(r4.get(11));
                jsonWriter.name$ar$ds("minute");
                jsonWriter.value$ar$ds$89b27ec2_0(r4.get(12));
                jsonWriter.name$ar$ds("second");
                jsonWriter.value$ar$ds$89b27ec2_0(r4.get(13));
                jsonWriter.endObject$ar$ds();
            }
        };
        CALENDAR = typeAdapter13;
        CALENDAR_FACTORY = new AnonymousClass33(Calendar.class, GregorianCalendar.class, typeAdapter13, 0);
        TypeAdapter typeAdapter14 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.27
            @Override // com.google.gson.TypeAdapter
            public final /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
                String locale;
                Locale locale2 = (Locale) obj;
                if (locale2 == null) {
                    locale = null;
                } else {
                    locale = locale2.toString();
                }
                jsonWriter.value$ar$ds$1248a0e6_0(locale);
            }
        };
        LOCALE = typeAdapter14;
        LOCALE_FACTORY = new AnonymousClass34(Locale.class, typeAdapter14, 1);
        TypeAdapter typeAdapter15 = new TypeAdapter() { // from class: com.google.gson.internal.bind.TypeAdapters.28
            @Override // com.google.gson.TypeAdapter
            public final void write(JsonWriter jsonWriter, JsonElement jsonElement) {
                if (jsonElement == null || (jsonElement instanceof JsonNull)) {
                    jsonWriter.nullValue$ar$ds();
                    return;
                }
                if (jsonElement instanceof JsonPrimitive) {
                    throw null;
                }
                if (jsonElement instanceof JsonArray) {
                    jsonWriter.beginArray$ar$ds();
                    Iterator it = ((JsonArray) jsonElement).iterator();
                    while (it.hasNext()) {
                        write(jsonWriter, (JsonElement) it.next());
                    }
                    jsonWriter.endArray$ar$ds();
                    return;
                }
                if (jsonElement instanceof JsonObject) {
                    jsonWriter.beginObject$ar$ds();
                    LinkedTreeMap.EntrySet.AnonymousClass1 anonymousClass17 = new LinkedTreeMap.EntrySet.AnonymousClass1((LinkedTreeMap.EntrySet) ((JsonObject) jsonElement).members.entrySet());
                    while (anonymousClass17.hasNext()) {
                        LinkedTreeMap.Node nextNode = anonymousClass17.nextNode();
                        jsonWriter.name$ar$ds((String) nextNode.getKey());
                        write(jsonWriter, (JsonElement) nextNode.getValue());
                    }
                    jsonWriter.endObject$ar$ds();
                    return;
                }
                throw new IllegalArgumentException("Couldn't write ".concat(String.valueOf(String.valueOf(jsonElement.getClass()))));
            }
        };
        JSON_ELEMENT = typeAdapter15;
        JSON_ELEMENT_FACTORY = new AnonymousClass34(JsonElement.class, typeAdapter15, 0);
        ENUM_FACTORY = new JsonAdapterAnnotationTypeAdapterFactory.DummyTypeAdapterFactory(2);
    }
}
