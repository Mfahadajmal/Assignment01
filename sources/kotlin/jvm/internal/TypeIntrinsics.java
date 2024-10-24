package kotlin.jvm.internal;

import _COROUTINE._BOUNDARY;
import java.util.Collection;
import java.util.Map;
import kotlin.Function;
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
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypeIntrinsics {
    /* JADX WARN: Multi-variable type inference failed */
    public static Collection asMutableCollection(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableCollection)) {
            throwCce(obj, "kotlin.collections.MutableCollection");
        }
        return obj;
    }

    public static void beforeCheckcastToFunctionOfArity$ar$ds(Object obj, int i) {
        if (obj != null && !isFunctionOfArity(obj, i)) {
            throwCce(obj, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "kotlin.jvm.functions.Function"));
        }
    }

    public static boolean isFunctionOfArity(Object obj, int i) {
        int i2;
        if (obj instanceof Function) {
            if (obj instanceof FunctionBase) {
                i2 = ((FunctionBase) obj).getArity();
            } else if (obj instanceof Function0) {
                i2 = 0;
            } else if (obj instanceof Function1) {
                i2 = 1;
            } else if (obj instanceof Function2) {
                i2 = 2;
            } else if (obj instanceof Function3) {
                i2 = 3;
            } else if (obj instanceof Function4) {
                i2 = 4;
            } else if (obj instanceof Function5) {
                i2 = 5;
            } else if (obj instanceof Function6) {
                i2 = 6;
            } else if (obj instanceof Function7) {
                i2 = 7;
            } else if (obj instanceof Function8) {
                i2 = 8;
            } else if (obj instanceof Function9) {
                i2 = 9;
            } else if (obj instanceof Function10) {
                i2 = 10;
            } else if (obj instanceof Function11) {
                i2 = 11;
            } else if (obj instanceof Function12) {
                i2 = 12;
            } else if (obj instanceof Function13) {
                i2 = 13;
            } else if (obj instanceof Function14) {
                i2 = 14;
            } else if (obj instanceof Function15) {
                i2 = 15;
            } else if (obj instanceof Function16) {
                i2 = 16;
            } else if (obj instanceof Function17) {
                i2 = 17;
            } else if (obj instanceof Function18) {
                i2 = 18;
            } else if (obj instanceof Function19) {
                i2 = 19;
            } else if (obj instanceof Function20) {
                i2 = 20;
            } else if (obj instanceof Function21) {
                i2 = 21;
            } else if (obj instanceof Function22) {
                i2 = 22;
            } else {
                i2 = -1;
            }
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static void throwCce(Object obj, String str) {
        String name;
        if (obj == null) {
            name = "null";
        } else {
            name = obj.getClass().getName();
        }
        ClassCastException classCastException = new ClassCastException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(str, name, " cannot be cast to "));
        Intrinsics.sanitizeStackTrace$ar$ds(classCastException, TypeIntrinsics.class.getName());
        throw classCastException;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Map asMutableMap(Object obj) {
        return obj;
    }
}
