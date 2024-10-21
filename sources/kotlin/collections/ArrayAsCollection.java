package kotlin.collections;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Stream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayAsCollection implements Collection, KMappedMarker, j$.util.Collection {
    private final boolean isVarargs;
    private final Object[] values;

    public ArrayAsCollection(Object[] objArr, boolean z) {
        this.values = objArr;
        this.isVarargs = z;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.AbstractList$Companion$ar$MethodMerging();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.AbstractList$Companion$ar$MethodMerging$dc56d17a_0();
    }

    @Override // java.util.Collection
    public final void clear() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0025 A[RETURN] */
    @Override // java.util.Collection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean contains(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object[] r0 = r5.values
            r1 = 0
            if (r6 != 0) goto L11
            int r6 = r0.length
            r2 = r1
        L7:
            if (r2 >= r6) goto L25
            r3 = r0[r2]
            if (r3 != 0) goto Le
            goto L21
        Le:
            int r2 = r2 + 1
            goto L7
        L11:
            int r2 = r0.length
            r3 = r1
        L13:
            if (r3 >= r2) goto L25
            r4 = r0[r3]
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r4)
            if (r4 != 0) goto L20
            int r3 = r3 + 1
            goto L13
        L20:
            r2 = r3
        L21:
            if (r2 < 0) goto L25
            r6 = 1
            return r6
        L25:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.ArrayAsCollection.contains(java.lang.Object):boolean");
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        collection.getClass();
        if (collection.isEmpty()) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Iterable, j$.util.Collection
    public final /* synthetic */ void forEach(Consumer consumer) {
        Iterable$CC.$default$forEach(this, consumer);
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        if (this.values.length == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new ArrayIterator(this.values);
    }

    @Override // java.util.Collection, j$.util.Collection
    public final /* synthetic */ Stream parallelStream() {
        return Collection.CC.$default$parallelStream(this);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.AbstractList$Companion$ar$MethodMerging();
    }

    @Override // java.util.Collection
    public final boolean removeAll(java.util.Collection collection) {
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.AbstractList$Companion$ar$MethodMerging$dc56d17a_0();
    }

    @Override // java.util.Collection, j$.util.Collection
    public final boolean removeIf(Predicate predicate) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(java.util.Collection collection) {
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.AbstractList$Companion$ar$MethodMerging$dc56d17a_0();
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return this.values.length;
    }

    @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection
    public final /* synthetic */ Spliterator spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    @Override // java.util.Collection, j$.util.Collection
    public final /* synthetic */ Stream stream() {
        return Collection.CC.$default$stream(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
        Object[] array;
        array = toArray((Object[]) intFunction.apply(0));
        return array;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        boolean z = this.isVarargs;
        Object[] objArr = this.values;
        if (z && Intrinsics.areEqual(objArr.getClass(), Object[].class)) {
            return objArr;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        copyOf.getClass();
        return copyOf;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        return CollectionToArray.toArray(this, objArr);
    }
}
