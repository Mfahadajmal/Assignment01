package dagger.internal;

import androidx.preference.Preference;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentLoadLogEvent;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SetFactory implements Factory {
    public static final Factory EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List collectionProviders;
    private final List individualProviders;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private final List collectionProviders;
        private final List individualProviders;

        public Builder(int i, int i2) {
            this.individualProviders = OnDeviceExplicitContentLoadLogEvent.presizedList(i);
            this.collectionProviders = OnDeviceExplicitContentLoadLogEvent.presizedList(i2);
        }

        public final void addCollectionProvider$ar$ds(Provider provider) {
            this.collectionProviders.add(provider);
        }

        public final void addProvider$ar$ds(Provider provider) {
            this.individualProviders.add(provider);
        }

        public final SetFactory build() {
            return new SetFactory(this.individualProviders, this.collectionProviders);
        }
    }

    public SetFactory(List list, List list2) {
        this.individualProviders = list;
        this.collectionProviders = list2;
    }

    @Override // javax.inject.Provider
    public final Set get() {
        List list = this.collectionProviders;
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(list.size());
        int size2 = this.collectionProviders.size();
        for (int i = 0; i < size2; i++) {
            Collection collection = (Collection) ((Provider) this.collectionProviders.get(i)).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet hashSet = new HashSet(size < 3 ? size + 1 : size < 1073741824 ? (int) ((size / 0.75f) + 1.0f) : Preference.DEFAULT_ORDER);
        int size3 = this.individualProviders.size();
        for (int i2 = 0; i2 < size3; i2++) {
            Object obj = ((Provider) this.individualProviders.get(i2)).get();
            obj.getClass();
            hashSet.add(obj);
        }
        int size4 = arrayList.size();
        for (int i3 = 0; i3 < size4; i3++) {
            for (Object obj2 : (Collection) arrayList.get(i3)) {
                obj2.getClass();
                hashSet.add(obj2);
            }
        }
        return DesugarCollections.unmodifiableSet(hashSet);
    }
}
