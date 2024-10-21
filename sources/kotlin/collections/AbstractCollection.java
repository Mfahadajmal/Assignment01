package kotlin.collections;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.selecttospeak.popup.S2SPopupParsedIntentKt$parseIntent$text$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Stream;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractCollection implements Collection, KMappedMarker, j$.util.Collection {
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

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        if (isEmpty()) {
            return false;
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next(), obj)) {
                return true;
            }
        }
        return false;
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

    public abstract int getSize();

    @Override // java.util.Collection
    public final boolean isEmpty() {
        if (getSize() == 0) {
            return true;
        }
        return false;
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
        return getSize();
    }

    @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection
    public /* synthetic */ Spliterator spliterator() {
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

    public final String toString() {
        return OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(this, ", ", "[", "]", new S2SPopupParsedIntentKt$parseIntent$text$1(this, 9), 24);
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        return CollectionToArray.toArray(this, objArr);
    }
}
