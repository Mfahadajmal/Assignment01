package kotlin.enums;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import j$.util.List;
import java.io.Serializable;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EnumEntriesList extends AbstractList implements Serializable, List, java.util.List, KMappedMarker {
    private final Enum[] entries;

    public EnumEntriesList(Enum[] enumArr) {
        this.entries = enumArr;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.entries);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r4 = (Enum) obj;
        r4.getClass();
        if (OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.getOrNull(this.entries, r4.ordinal()) != r4) {
            return false;
        }
        return true;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this.entries.length);
        return this.entries[i];
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.entries.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        r4.getClass();
        Enum[] enumArr = this.entries;
        int ordinal = r4.ordinal();
        if (OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.getOrNull(enumArr, ordinal) != r4) {
            return -1;
        }
        return ordinal;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r2 = (Enum) obj;
        r2.getClass();
        return indexOf(r2);
    }
}
