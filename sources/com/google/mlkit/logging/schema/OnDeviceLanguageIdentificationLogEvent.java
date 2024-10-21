package com.google.mlkit.logging.schema;

import _COROUTINE._BOUNDARY;
import androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.Result;
import kotlin.SafePublicationLazyImpl;
import kotlin.SynchronizedLazyImpl;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.UnsafeLazyImpl;
import kotlin.collections.ArrayAsCollection;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptyMap;
import kotlin.collections.EmptySet;
import kotlin.collections.builders.ListBuilder;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceLanguageIdentificationLogEvent {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IdentifiedLanguage {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IdentifyLanguageResult {
        public static final Object createFailure(Throwable th) {
            th.getClass();
            return new Result.Failure(th);
        }

        public static final Lazy lazy$ar$edu(int i, Function0 function0) {
            if (i != 0) {
                int i2 = LazyThreadSafetyMode.SYNCHRONIZED$ar$edu$40d6d9d0_0;
                int i3 = i - 1;
                if (i3 != 0) {
                    if (i3 != 1) {
                        return new UnsafeLazyImpl(function0);
                    }
                    return new SafePublicationLazyImpl(function0);
                }
                return new SynchronizedLazyImpl(function0);
            }
            throw null;
        }

        public static final void throwOnFailure(Object obj) {
            if (!(obj instanceof Result.Failure)) {
            } else {
                throw ((Result.Failure) obj).exception;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IdentifyPossibleLanguagesResult {
        public static /* synthetic */ boolean AbstractList$Companion$ar$MethodMerging() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public static /* synthetic */ boolean AbstractList$Companion$ar$MethodMerging$dc56d17a_0() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public static List asList(Object[] objArr) {
            objArr.getClass();
            List asList = Arrays.asList(objArr);
            asList.getClass();
            return asList;
        }

        public static final void checkElementIndex$kotlin_stdlib$ar$ds(int i, int i2) {
            if (i >= 0 && i < i2) {
            } else {
                throw new IndexOutOfBoundsException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i2, i, "index: ", ", size: "));
            }
        }

        public static final void checkPositionIndex$kotlin_stdlib$ar$ds(int i, int i2) {
            if (i >= 0 && i <= i2) {
            } else {
                throw new IndexOutOfBoundsException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i2, i, "index: ", ", size: "));
            }
        }

        public static final void checkRangeIndexes$kotlin_stdlib$ar$ds(int i, int i2, int i3) {
            if (i >= 0 && i2 <= i3) {
                if (i <= i2) {
                    return;
                } else {
                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i2, i, "fromIndex: ", " > toIndex: "));
                }
            }
            throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
        }

        public static boolean contentDeepEquals(Object[] objArr, Object[] objArr2) {
            if (objArr == objArr2) {
                return true;
            }
            int length = objArr.length;
            if (length != objArr2.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                Object obj2 = objArr2[i];
                if (obj != obj2) {
                    if (obj == null || obj2 == null) {
                        return false;
                    }
                    if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                        if (!contentDeepEquals((Object[]) obj, (Object[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                        if (!Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                        if (!Arrays.equals((short[]) obj, (short[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                        if (!Arrays.equals((int[]) obj, (int[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                        if (!Arrays.equals((long[]) obj, (long[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                        if (!Arrays.equals((float[]) obj, (float[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                        if (!Arrays.equals((double[]) obj, (double[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                        if (!Arrays.equals((char[]) obj, (char[]) obj2)) {
                            return false;
                        }
                    } else if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                        if (!Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                            return false;
                        }
                    } else {
                        if ((obj instanceof UByteArray) && (obj2 instanceof UByteArray)) {
                            throw null;
                        }
                        if ((obj instanceof UShortArray) && (obj2 instanceof UShortArray)) {
                            throw null;
                        }
                        if ((obj instanceof UIntArray) && (obj2 instanceof UIntArray)) {
                            throw null;
                        }
                        if ((obj instanceof ULongArray) && (obj2 instanceof ULongArray)) {
                            throw null;
                        }
                        if (!Intrinsics.areEqual(obj, obj2)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public static void copyInto$ar$ds(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
            bArr.getClass();
            System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
        }

        public static void copyInto$ar$ds$e21159aa_0(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
            objArr.getClass();
            objArr2.getClass();
            System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
        }

        public static void copyInto$ar$ds$edac78be_0(int[] iArr, int[] iArr2, int i, int i2, int i3) {
            iArr.getClass();
            iArr2.getClass();
            System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
        }

        public static /* synthetic */ void copyInto$default$ar$ds$41d531b2_0(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
            if ((i3 & 8) != 0) {
                i2 = objArr.length;
            }
            if ((i3 & 4) != 0) {
                i = 0;
            }
            copyInto$ar$ds$e21159aa_0(objArr, objArr2, 0, i, i2);
        }

        public static Object[] copyOfRange(Object[] objArr, int i, int i2) {
            objArr.getClass();
            copyOfRangeToIndexCheck(i2, objArr.length);
            Object[] copyOfRange = Arrays.copyOfRange(objArr, i, i2);
            copyOfRange.getClass();
            return copyOfRange;
        }

        public static final void copyOfRangeToIndexCheck(int i, int i2) {
            if (i <= i2) {
                return;
            }
            throw new IndexOutOfBoundsException("toIndex (" + i + ") is greater than size (" + i2 + ").");
        }

        public static void fill(Object[] objArr, Object obj, int i, int i2) {
            objArr.getClass();
            Arrays.fill(objArr, i, i2, obj);
        }

        public static List filterNotNull(Object[] objArr) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : objArr) {
                if (obj != null) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }

        public static int getLastIndex(Object[] objArr) {
            objArr.getClass();
            return objArr.length - 1;
        }

        public static Object getOrNull(Object[] objArr, int i) {
            if (i >= 0 && i <= getLastIndex(objArr)) {
                return objArr[i];
            }
            return null;
        }

        public static final int newCapacity$kotlin_stdlib$ar$ds(int i, int i2) {
            int i3 = i + (i >> 1);
            if (i3 - i2 < 0) {
                i3 = i2;
            }
            if ((-2147483639) + i3 > 0) {
                if (i2 <= 2147483639) {
                    return 2147483639;
                }
                return Preference.DEFAULT_ORDER;
            }
            return i3;
        }

        public static List toList(Object[] objArr) {
            int length = objArr.length;
            if (length != 0) {
                if (length != 1) {
                    return new ArrayList(new ArrayAsCollection(objArr, false));
                }
                return OnDeviceLanguageIdentificationLogEvent.listOf(objArr[0]);
            }
            return EmptyList.INSTANCE;
        }
    }

    public static void addAll$ar$ds$2b82a983_0(Collection collection, Iterable iterable) {
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    public static Sequence asSequence(Iterable iterable) {
        iterable.getClass();
        return new ViewGroupKt$special$$inlined$Sequence$1(iterable, 3);
    }

    public static final List build(List list) {
        ListBuilder listBuilder = (ListBuilder) list;
        if (listBuilder.backing == null) {
            listBuilder.checkIsMutable();
            listBuilder.isReadOnly = true;
            return listBuilder.length > 0 ? list : ListBuilder.Empty;
        }
        throw new IllegalStateException();
    }

    public static int collectionSizeOrDefault$ar$ds(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }

    public static boolean contains(Iterable iterable, Object obj) {
        int i;
        iterable.getClass();
        if (!(iterable instanceof Collection)) {
            if (iterable instanceof List) {
                i = ((List) iterable).indexOf(obj);
            } else {
                int i2 = 0;
                for (Object obj2 : iterable) {
                    if (i2 < 0) {
                        throwIndexOverflow();
                    }
                    if (!Intrinsics.areEqual(obj, obj2)) {
                        i2++;
                    } else {
                        i = i2;
                    }
                }
                return false;
            }
            if (i < 0) {
                return false;
            }
            return true;
        }
        return ((Collection) iterable).contains(obj);
    }

    public static void filterInPlace$CollectionsKt__MutableCollectionsKt$ar$ds(Iterable iterable, Function1 function1, boolean z) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            if (((Boolean) function1.invoke(it.next())).booleanValue() == z) {
                it.remove();
            }
        }
    }

    public static Object first(List list) {
        list.getClass();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static Object firstOrNull(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static int getLastIndex(List list) {
        list.getClass();
        return list.size() - 1;
    }

    public static final Object getOrImplicitDefaultNullable(Map map, Object obj) {
        Object obj2 = map.get(obj);
        if (obj2 == null && !map.containsKey(obj)) {
            throw new NoSuchElementException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(obj, "Key ", " is missing in the map."));
        }
        return obj2;
    }

    public static Object getOrNull(List list, int i) {
        list.getClass();
        if (i >= 0 && i <= getLastIndex(list)) {
            return list.get(i);
        }
        return null;
    }

    public static void joinTo$ar$ds(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1) {
        appendable.append(charSequence2);
        Iterator it = iterable.iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            i2++;
            if (i2 > 1) {
                appendable.append(charSequence);
            }
            if (i >= 0 && i2 > 0) {
                i = 0;
                break;
            }
            OnDeviceStainRemovalLogEvent.appendElement(appendable, next, function1);
        }
        if (i >= 0 && i2 > 0) {
            appendable.append(charSequence4);
        }
        appendable.append(charSequence3);
    }

    public static /* synthetic */ Appendable joinTo$default$ar$ds(Iterable iterable, Appendable appendable, CharSequence charSequence) {
        joinTo$ar$ds(iterable, appendable, "\n", "", "", -1, "...", null);
        return appendable;
    }

    public static /* synthetic */ String joinToString$default$ar$ds(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Function1 function1, int i) {
        CharSequence charSequence4;
        CharSequence charSequence5;
        CharSequence charSequence6;
        Function1 function12;
        int i2;
        iterable.getClass();
        if (1 == (i & 1)) {
            charSequence = ", ";
        }
        CharSequence charSequence7 = charSequence;
        charSequence7.getClass();
        if ((i & 2) != 0) {
            charSequence4 = "";
        } else {
            charSequence4 = charSequence2;
        }
        charSequence4.getClass();
        if ((i & 4) != 0) {
            charSequence5 = "";
        } else {
            charSequence5 = charSequence3;
        }
        charSequence5.getClass();
        if ((i & 16) != 0) {
            charSequence6 = "...";
        } else {
            charSequence6 = null;
        }
        charSequence6.getClass();
        StringBuilder sb = new StringBuilder();
        if ((i & 32) != 0) {
            function12 = null;
        } else {
            function12 = function1;
        }
        if ((i & 8) != 0) {
            i2 = -1;
        } else {
            i2 = 0;
        }
        joinTo$ar$ds(iterable, sb, charSequence7, charSequence4, charSequence5, i2, charSequence6, function12);
        return sb.toString();
    }

    public static Object last(List list) {
        if (!list.isEmpty()) {
            return list.get(getLastIndex(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static Object lastOrNull(List list) {
        list.getClass();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static final List listOf(Object obj) {
        List singletonList = Collections.singletonList(obj);
        singletonList.getClass();
        return singletonList;
    }

    public static int mapCapacity(int i) {
        if (i >= 0) {
            if (i < 3) {
                return i + 1;
            }
            if (i >= 1073741824) {
                return Preference.DEFAULT_ORDER;
            }
            return (int) ((i / 0.75f) + 1.0f);
        }
        return i;
    }

    public static Map mapOf(Pair pair) {
        pair.getClass();
        Map singletonMap = Collections.singletonMap(pair.first, pair.second);
        singletonMap.getClass();
        return singletonMap;
    }

    public static Comparable maxOrNull(Iterable iterable) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Comparable comparable = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable comparable2 = (Comparable) it.next();
            if (comparable.compareTo(comparable2) < 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }

    public static List mutableListOf(Object... objArr) {
        return new ArrayList(new ArrayAsCollection(objArr, true));
    }

    public static List optimizeReadOnlyList(List list) {
        int size = list.size();
        if (size != 0) {
            if (size != 1) {
                return list;
            }
            return listOf(list.get(0));
        }
        return EmptyList.INSTANCE;
    }

    public static List plus(Collection collection, Iterable iterable) {
        collection.getClass();
        iterable.getClass();
        if (iterable instanceof Collection) {
            Collection collection2 = (Collection) iterable;
            ArrayList arrayList = new ArrayList(collection.size() + collection2.size());
            arrayList.addAll(collection);
            arrayList.addAll(collection2);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(collection);
        addAll$ar$ds$2b82a983_0(arrayList2, iterable);
        return arrayList2;
    }

    public static void putAll(Map map, Iterable iterable) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            map.put(pair.first, pair.second);
        }
    }

    public static Object removeFirst(List list) {
        if (!list.isEmpty()) {
            return list.remove(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static Object removeLast(List list) {
        if (!list.isEmpty()) {
            return list.remove(getLastIndex(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static int reverseIteratorIndex$CollectionsKt__ReversedViewsKt(List list, int i) {
        return getLastIndex(list) - i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List reversed(Iterable iterable) {
        iterable.getClass();
        if (iterable.size() <= 1) {
            return toList(iterable);
        }
        List mutableList = toMutableList(iterable);
        Collections.reverse(mutableList);
        return mutableList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List sorted(Iterable iterable) {
        iterable.getClass();
        if (iterable.size() <= 1) {
            return toList(iterable);
        }
        Object[] array = iterable.toArray(new Comparable[0]);
        Comparable[] comparableArr = (Comparable[]) array;
        comparableArr.getClass();
        if (comparableArr.length > 1) {
            Arrays.sort(comparableArr);
        }
        return IdentifyPossibleLanguagesResult.asList(array);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List sortedWith(Iterable iterable, Comparator comparator) {
        iterable.getClass();
        if (iterable.size() <= 1) {
            return toList(iterable);
        }
        Object[] array = iterable.toArray(new Object[0]);
        array.getClass();
        if (array.length > 1) {
            Arrays.sort(array, comparator);
        }
        return IdentifyPossibleLanguagesResult.asList(array);
    }

    public static final void terminateCollectionToArray$ar$ds(int i, Object[] objArr) {
        if (i < objArr.length) {
            objArr[i] = null;
        }
    }

    public static void throwCountOverflow() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static void throwIndexOverflow() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static void toCollection$ar$ds$67becfcd_0(Iterable iterable, Collection collection) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    public static int[] toIntArray(Collection collection) {
        int[] iArr = new int[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = ((Number) it.next()).intValue();
            i++;
        }
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List toList(Iterable iterable) {
        Object next;
        iterable.getClass();
        int size = iterable.size();
        if (size != 0) {
            if (size != 1) {
                return toMutableList((Collection) iterable);
            }
            if (iterable instanceof List) {
                next = ((List) iterable).get(0);
            } else {
                next = iterable.iterator().next();
            }
            return listOf(next);
        }
        return EmptyList.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Map toMap(Iterable iterable) {
        int size = iterable.size();
        if (size == 0) {
            return EmptyMap.INSTANCE;
        }
        if (size != 1) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity(iterable.size()));
            putAll(linkedHashMap, iterable);
            return linkedHashMap;
        }
        return mapOf((Pair) iterable.get(0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List toMutableList(Iterable iterable) {
        return toMutableList((Collection) iterable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Set toSet(Iterable iterable) {
        int size = iterable.size();
        if (size != 0) {
            if (size != 1) {
                LinkedHashSet linkedHashSet = new LinkedHashSet(mapCapacity(iterable.size()));
                toCollection$ar$ds$67becfcd_0(iterable, linkedHashSet);
                return linkedHashSet;
            }
            return OnDeviceObjectCreateLogEvent.setOf(iterable.get(0));
        }
        return EmptySet.INSTANCE;
    }

    public static Map toSingletonMap(Map map) {
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map singletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        singletonMap.getClass();
        return singletonMap;
    }

    public static List toMutableList(Collection collection) {
        collection.getClass();
        return new ArrayList(collection);
    }

    public static Map build(Map map) {
        return ((MapBuilder) map).build();
    }

    public static Map toMap(Map map) {
        int size = map.size();
        if (size == 0) {
            return EmptyMap.INSTANCE;
        }
        if (size != 1) {
            return new LinkedHashMap(map);
        }
        return toSingletonMap(map);
    }

    public static List plus(Collection collection, Object obj) {
        collection.getClass();
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(obj);
        return arrayList;
    }
}
