package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class NavType {
    public final boolean isNullableAllowed;
    public static final AccessibilityNodeInfoCompat.Api30Impl Companion$ar$class_merging$b85c379f_0 = new AccessibilityNodeInfoCompat.Api30Impl();
    public static final NavType IntType = new NavType() { // from class: androidx.navigation.NavType$Companion$IntType$1
        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            Object obj = bundle.get(str);
            obj.getClass();
            return (Integer) obj;
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "integer";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            int parseInt;
            str.getClass();
            if (OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(str, "0x")) {
                String substring = str.substring(2);
                substring.getClass();
                OnDeviceSmartReplyLogEvent.checkRadix$ar$ds(16);
                parseInt = Integer.parseInt(substring, 16);
            } else {
                parseInt = Integer.parseInt(str);
            }
            return Integer.valueOf(parseInt);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putInt(str, ((Number) obj).intValue());
        }
    };
    public static final NavType ReferenceType = new NavType() { // from class: androidx.navigation.NavType$Companion$ReferenceType$1
        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            Object obj = bundle.get(str);
            obj.getClass();
            return (Integer) obj;
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "reference";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            int parseInt;
            str.getClass();
            if (OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(str, "0x")) {
                String substring = str.substring(2);
                substring.getClass();
                OnDeviceSmartReplyLogEvent.checkRadix$ar$ds(16);
                parseInt = Integer.parseInt(substring, 16);
            } else {
                parseInt = Integer.parseInt(str);
            }
            return Integer.valueOf(parseInt);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putInt(str, ((Number) obj).intValue());
        }
    };
    public static final NavType IntArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$IntArrayType$1
        public static final int[] parseValue$ar$ds$d45de060_0(String str) {
            str.getClass();
            return new int[]{((Number) NavType.IntType.parseValue(str)).intValue()};
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (int[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "integer[]";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            return parseValue$ar$ds$d45de060_0(str);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putIntArray(str, (int[]) obj);
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str, Object obj) {
            int[] iArr = (int[]) obj;
            if (iArr != null) {
                int[] parseValue$ar$ds$d45de060_0 = parseValue$ar$ds$d45de060_0(str);
                int length = iArr.length;
                int[] copyOf = Arrays.copyOf(iArr, length + 1);
                System.arraycopy(parseValue$ar$ds$d45de060_0, 0, copyOf, length, 1);
                copyOf.getClass();
                return copyOf;
            }
            return parseValue$ar$ds$d45de060_0(str);
        }
    };
    public static final NavType LongType = new NavType() { // from class: androidx.navigation.NavType$Companion$LongType$1
        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            Object obj = bundle.get(str);
            obj.getClass();
            return (Long) obj;
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "long";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            boolean endsWith;
            String str2;
            long parseLong;
            str.getClass();
            endsWith = str.endsWith("L");
            if (endsWith) {
                str2 = str.substring(0, str.length() - 1);
                str2.getClass();
            } else {
                str2 = str;
            }
            if (OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(str, "0x")) {
                String substring = str2.substring(2);
                substring.getClass();
                OnDeviceSmartReplyLogEvent.checkRadix$ar$ds(16);
                parseLong = Long.parseLong(substring, 16);
            } else {
                parseLong = Long.parseLong(str2);
            }
            return Long.valueOf(parseLong);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putLong(str, ((Number) obj).longValue());
        }
    };
    public static final NavType LongArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$LongArrayType$1
        public static final long[] parseValue$ar$ds$bc766f19_0(String str) {
            str.getClass();
            return new long[]{((Number) NavType.LongType.parseValue(str)).longValue()};
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (long[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "long[]";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            return parseValue$ar$ds$bc766f19_0(str);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putLongArray(str, (long[]) obj);
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str, Object obj) {
            long[] jArr = (long[]) obj;
            if (jArr != null) {
                long[] parseValue$ar$ds$bc766f19_0 = parseValue$ar$ds$bc766f19_0(str);
                int length = jArr.length;
                long[] copyOf = Arrays.copyOf(jArr, length + 1);
                System.arraycopy(parseValue$ar$ds$bc766f19_0, 0, copyOf, length, 1);
                copyOf.getClass();
                return copyOf;
            }
            return parseValue$ar$ds$bc766f19_0(str);
        }
    };
    public static final NavType FloatType = new NavType() { // from class: androidx.navigation.NavType$Companion$FloatType$1
        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            Object obj = bundle.get(str);
            obj.getClass();
            return (Float) obj;
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "float";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            str.getClass();
            return Float.valueOf(Float.parseFloat(str));
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putFloat(str, ((Number) obj).floatValue());
        }
    };
    public static final NavType FloatArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$FloatArrayType$1
        public static final float[] parseValue$ar$ds$5c105a9e_0(String str) {
            str.getClass();
            return new float[]{((Number) NavType.FloatType.parseValue(str)).floatValue()};
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (float[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "float[]";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            return parseValue$ar$ds$5c105a9e_0(str);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putFloatArray(str, (float[]) obj);
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str, Object obj) {
            float[] fArr = (float[]) obj;
            if (fArr != null) {
                float[] parseValue$ar$ds$5c105a9e_0 = parseValue$ar$ds$5c105a9e_0(str);
                int length = fArr.length;
                float[] copyOf = Arrays.copyOf(fArr, length + 1);
                System.arraycopy(parseValue$ar$ds$5c105a9e_0, 0, copyOf, length, 1);
                copyOf.getClass();
                return copyOf;
            }
            return parseValue$ar$ds$5c105a9e_0(str);
        }
    };
    public static final NavType BoolType = new NavType() { // from class: androidx.navigation.NavType$Companion$BoolType$1
        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (Boolean) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "boolean";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            boolean z;
            str.getClass();
            if (Intrinsics.areEqual(str, "true")) {
                z = true;
            } else if (Intrinsics.areEqual(str, "false")) {
                z = false;
            } else {
                throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
            }
            return Boolean.valueOf(z);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        }
    };
    public static final NavType BoolArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$BoolArrayType$1
        public static final boolean[] parseValue$ar$ds(String str) {
            str.getClass();
            return new boolean[]{((Boolean) NavType.BoolType.parseValue(str)).booleanValue()};
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (boolean[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "boolean[]";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            return parseValue$ar$ds(str);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putBooleanArray(str, (boolean[]) obj);
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str, Object obj) {
            boolean[] zArr = (boolean[]) obj;
            if (zArr != null) {
                boolean[] parseValue$ar$ds = parseValue$ar$ds(str);
                int length = zArr.length;
                boolean[] copyOf = Arrays.copyOf(zArr, length + 1);
                System.arraycopy(parseValue$ar$ds, 0, copyOf, length, 1);
                copyOf.getClass();
                return copyOf;
            }
            return parseValue$ar$ds(str);
        }
    };
    public static final NavType StringType = new NavType() { // from class: androidx.navigation.NavType$Companion$StringType$1
        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (String) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "string";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            str.getClass();
            if (Intrinsics.areEqual(str, "null")) {
                return null;
            }
            return str;
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putString(str, (String) obj);
        }
    };
    public static final NavType StringArrayType = new NavType() { // from class: androidx.navigation.NavType$Companion$StringArrayType$1
        public static final String[] parseValue$ar$ds$c7285a01_0(String str) {
            str.getClass();
            return new String[]{str};
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (String[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            return "string[]";
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            return parseValue$ar$ds$c7285a01_0(str);
        }

        @Override // androidx.navigation.NavType
        public final /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            bundle.putStringArray(str, (String[]) obj);
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str, Object obj) {
            String[] strArr = (String[]) obj;
            if (strArr == null) {
                return parseValue$ar$ds$c7285a01_0(str);
            }
            String[] parseValue$ar$ds$c7285a01_0 = parseValue$ar$ds$c7285a01_0(str);
            int length = strArr.length;
            Object[] copyOf = Arrays.copyOf(strArr, length + 1);
            System.arraycopy(parseValue$ar$ds$c7285a01_0, 0, copyOf, length, 1);
            copyOf.getClass();
            return (String[]) copyOf;
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EnumType extends SerializableType {
        private final Class type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EnumType(Class cls) {
            super(cls, null);
            cls.getClass();
            if (cls.isEnum()) {
                this.type = cls;
            } else {
                Objects.toString(cls);
                throw new IllegalArgumentException(cls.toString().concat(" is not an Enum type."));
            }
        }

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        public final String getName() {
            String name = this.type.getName();
            name.getClass();
            return name;
        }

        @Override // androidx.navigation.NavType.SerializableType, androidx.navigation.NavType
        public final Enum parseValue(String str) {
            Object obj;
            str.getClass();
            Object[] enumConstants = this.type.getEnumConstants();
            enumConstants.getClass();
            int i = 0;
            while (true) {
                if (i >= enumConstants.length) {
                    obj = null;
                    break;
                }
                obj = enumConstants[i];
                if (OnDeviceStainRemovalLogEvent.equals$ar$ds$566ac877_0(((Enum) obj).name(), str)) {
                    break;
                }
                i++;
            }
            Enum r2 = (Enum) obj;
            if (r2 != null) {
                return r2;
            }
            throw new IllegalArgumentException("Enum value " + str + " not found for type " + this.type.getName() + '.');
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ParcelableArrayType extends NavType {
        private final Class arrayType;

        public ParcelableArrayType(Class cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls)) {
                try {
                    Class<?> cls2 = Class.forName("[L" + cls.getName() + ';');
                    cls2.getClass();
                    this.arrayType = cls2;
                    return;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            Objects.toString(cls);
            throw new IllegalArgumentException(cls.toString().concat(" does not implement Parcelable."));
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Intrinsics.areEqual(getClass(), obj.getClass())) {
                return Intrinsics.areEqual(this.arrayType, ((ParcelableArrayType) obj).arrayType);
            }
            return false;
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (Parcelable[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            String name = this.arrayType.getName();
            name.getClass();
            return name;
        }

        public final int hashCode() {
            return this.arrayType.hashCode();
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            str.getClass();
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            this.arrayType.cast(parcelableArr);
            bundle.putParcelableArray(str, parcelableArr);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ParcelableType extends NavType {
        private final Class type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ParcelableType(Class cls) {
            super(true);
            cls.getClass();
            if (!Parcelable.class.isAssignableFrom(cls) && !Serializable.class.isAssignableFrom(cls)) {
                Objects.toString(cls);
                throw new IllegalArgumentException(cls.toString().concat(" does not implement Parcelable or Serializable."));
            }
            this.type = cls;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Intrinsics.areEqual(getClass(), obj.getClass())) {
                return Intrinsics.areEqual(this.type, ((ParcelableType) obj).type);
            }
            return false;
        }

        @Override // androidx.navigation.NavType
        public final Object get(Bundle bundle, String str) {
            str.getClass();
            return bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            String name = this.type.getName();
            name.getClass();
            return name;
        }

        public final int hashCode() {
            return this.type.hashCode();
        }

        @Override // androidx.navigation.NavType
        public final Object parseValue(String str) {
            str.getClass();
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public final void put(Bundle bundle, String str, Object obj) {
            this.type.cast(obj);
            if (obj != null && !(obj instanceof Parcelable)) {
                if (obj instanceof Serializable) {
                    bundle.putSerializable(str, (Serializable) obj);
                    return;
                }
                return;
            }
            bundle.putParcelable(str, (Parcelable) obj);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SerializableArrayType extends NavType {
        private final Class arrayType;

        public SerializableArrayType(Class cls) {
            super(true);
            if (Serializable.class.isAssignableFrom(cls)) {
                try {
                    Class<?> cls2 = Class.forName("[L" + cls.getName() + ';');
                    cls2.getClass();
                    this.arrayType = cls2;
                    return;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            Objects.toString(cls);
            throw new IllegalArgumentException(cls.toString().concat(" does not implement Serializable."));
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Intrinsics.areEqual(getClass(), obj.getClass())) {
                return Intrinsics.areEqual(this.arrayType, ((SerializableArrayType) obj).arrayType);
            }
            return false;
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (Serializable[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public final String getName() {
            String name = this.arrayType.getName();
            name.getClass();
            return name;
        }

        public final int hashCode() {
            return this.arrayType.hashCode();
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object parseValue(String str) {
            str.getClass();
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            Object obj2 = (Serializable[]) obj;
            this.arrayType.cast(obj2);
            bundle.putSerializable(str, (Serializable) obj2);
        }
    }

    public NavType(boolean z) {
        this.isNullableAllowed = z;
    }

    public abstract Object get(Bundle bundle, String str);

    public String getName() {
        throw null;
    }

    public abstract Object parseValue(String str);

    public Object parseValue(String str, Object obj) {
        return parseValue(str);
    }

    public abstract void put(Bundle bundle, String str, Object obj);

    public final String toString() {
        return getName();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SerializableType extends NavType {
        private final Class type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SerializableType(Class cls) {
            super(true);
            cls.getClass();
            if (Serializable.class.isAssignableFrom(cls)) {
                if (cls.isEnum()) {
                    Objects.toString(cls);
                    throw new IllegalArgumentException(cls.toString().concat(" is an Enum. You should use EnumType instead."));
                }
                this.type = cls;
                return;
            }
            Objects.toString(cls);
            throw new IllegalArgumentException(cls.toString().concat(" does not implement Serializable."));
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SerializableType)) {
                return false;
            }
            return Intrinsics.areEqual(this.type, ((SerializableType) obj).type);
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ Object get(Bundle bundle, String str) {
            str.getClass();
            return (Serializable) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            String name = this.type.getName();
            name.getClass();
            return name;
        }

        public final int hashCode() {
            return this.type.hashCode();
        }

        @Override // androidx.navigation.NavType
        public Serializable parseValue(String str) {
            str.getClass();
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public final /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            Serializable serializable = (Serializable) obj;
            serializable.getClass();
            this.type.cast(serializable);
            bundle.putSerializable(str, serializable);
        }

        public SerializableType(Class cls, byte[] bArr) {
            super(false);
            if (Serializable.class.isAssignableFrom(cls)) {
                this.type = cls;
            } else {
                Objects.toString(cls);
                throw new IllegalArgumentException(cls.toString().concat(" does not implement Serializable."));
            }
        }
    }
}
