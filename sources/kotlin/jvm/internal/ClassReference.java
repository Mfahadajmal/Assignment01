package kotlin.jvm.internal;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KClass;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClassReference implements KClass, ClassBasedDeclarationContainer {
    public static final DefaultConstructorMarker Companion$ar$class_merging$3976a406_0 = new DefaultConstructorMarker();
    public static final Map FUNCTION_CLASSES;
    public static final HashMap classFqNames;
    private static final HashMap primitiveFqNames;
    private static final HashMap primitiveWrapperFqNames;
    public static final Map simpleNames;
    public final Class jClass;

    static {
        int i = 0;
        List asList = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.asList(new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        ArrayList arrayList = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(asList));
        for (Object obj : asList) {
            int i2 = i + 1;
            if (i < 0) {
                OnDeviceLanguageIdentificationLogEvent.throwIndexOverflow();
            }
            arrayList.add(new Pair((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        FUNCTION_CLASSES = OnDeviceLanguageIdentificationLogEvent.toMap(arrayList);
        HashMap hashMap = new HashMap();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        primitiveFqNames = hashMap;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        primitiveWrapperFqNames = hashMap2;
        HashMap hashMap3 = new HashMap();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        Collection<String> values = hashMap.values();
        values.getClass();
        for (String str : values) {
            str.getClass();
            Pair pair = new Pair("kotlin.jvm.internal." + OnDeviceStainRemovalLogEvent.substringAfterLast$default$ar$ds(str) + "CompanionObject", str.concat(".Companion"));
            hashMap3.put(pair.first, pair.second);
        }
        for (Map.Entry entry : FUNCTION_CLASSES.entrySet()) {
            hashMap3.put(((Class) entry.getKey()).getName(), "kotlin.Function" + ((Number) entry.getValue()).intValue());
        }
        classFqNames = hashMap3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(OnDeviceLanguageIdentificationLogEvent.mapCapacity(hashMap3.size()));
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), OnDeviceStainRemovalLogEvent.substringAfterLast$default$ar$ds((String) entry2.getValue()));
        }
        simpleNames = linkedHashMap;
    }

    public ClassReference(Class cls) {
        cls.getClass();
        this.jClass = cls;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof ClassReference) && Intrinsics.areEqual(OnDeviceShadowRemovalLogEvent.getJavaObjectType(this), OnDeviceShadowRemovalLogEvent.getJavaObjectType((KClass) obj))) {
            return true;
        }
        return false;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public final Class getJClass() {
        return this.jClass;
    }

    @Override // kotlin.reflect.KClass
    public final String getQualifiedName() {
        String str;
        Class cls = this.jClass;
        String str2 = null;
        if (cls.isAnonymousClass() || cls.isLocalClass()) {
            return null;
        }
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (str = (String) classFqNames.get(componentType.getName())) != null) {
                str2 = str.concat("Array");
            }
            if (str2 == null) {
                return "kotlin.Array";
            }
            return str2;
        }
        String str3 = (String) classFqNames.get(cls.getName());
        if (str3 == null) {
            return cls.getCanonicalName();
        }
        return str3;
    }

    @Override // kotlin.reflect.KClass
    public final String getSimpleName() {
        String str;
        int indexOf;
        Class cls = this.jClass;
        String str2 = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (cls.isLocalClass()) {
            String simpleName = cls.getSimpleName();
            Method enclosingMethod = cls.getEnclosingMethod();
            if (enclosingMethod != null) {
                simpleName.getClass();
                return OnDeviceStainRemovalLogEvent.substringAfter(simpleName, String.valueOf(enclosingMethod.getName()).concat("$"), simpleName);
            }
            Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
            if (enclosingConstructor != null) {
                simpleName.getClass();
                return OnDeviceStainRemovalLogEvent.substringAfter(simpleName, String.valueOf(enclosingConstructor.getName()).concat("$"), simpleName);
            }
            simpleName.getClass();
            indexOf = simpleName.indexOf(36, 0);
            if (indexOf != -1) {
                String substring = simpleName.substring(indexOf + 1, simpleName.length());
                substring.getClass();
                return substring;
            }
            return simpleName;
        }
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (str = (String) simpleNames.get(componentType.getName())) != null) {
                str2 = str.concat("Array");
            }
            if (str2 == null) {
                return "Array";
            }
            return str2;
        }
        String str3 = (String) simpleNames.get(cls.getName());
        if (str3 == null) {
            return cls.getSimpleName();
        }
        return str3;
    }

    public final int hashCode() {
        return OnDeviceShadowRemovalLogEvent.getJavaObjectType(this).hashCode();
    }

    public final String toString() {
        return String.valueOf(this.jClass.toString()).concat(" (Kotlin reflection is not available)");
    }
}
