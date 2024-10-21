package com.google.common.flogger;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MetadataKey {
    public final long bloomFilterMask;
    public final boolean canRepeat;
    private final Class clazz;
    public final boolean isCustom;
    public final String label;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface KeyValueHandler {
        void handle(String str, Object obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MetadataKey(String str, Class cls, boolean z) {
        this(str, cls, z, true);
    }

    public final Object cast(Object obj) {
        return this.clazz.cast(obj);
    }

    public void emit(Object obj, KeyValueHandler keyValueHandler) {
        keyValueHandler.handle(this.label, obj);
    }

    public void emitRepeated(Iterator it, KeyValueHandler keyValueHandler) {
        while (it.hasNext()) {
            emit(it.next(), keyValueHandler);
        }
    }

    public final String toString() {
        Class cls = this.clazz;
        return getClass().getName() + "/" + this.label + "[" + cls.getName() + "]";
    }

    public MetadataKey(String str, Class cls, boolean z, boolean z2) {
        if (!str.isEmpty()) {
            if (ContextDataProvider.isLetter(str.charAt(0))) {
                for (int i = 1; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    if (!ContextDataProvider.isLetter(charAt) && ((charAt < '0' || charAt > '9') && charAt != '_')) {
                        throw new IllegalArgumentException("identifier must contain only ASCII letters, digits or underscore: ".concat(str));
                    }
                }
                this.label = str;
                this.clazz = cls;
                this.canRepeat = z;
                this.isCustom = z2;
                int identityHashCode = System.identityHashCode(this);
                long j = 0;
                for (int i2 = 0; i2 < 5; i2++) {
                    j |= 1 << (identityHashCode & 63);
                    identityHashCode >>>= 6;
                }
                this.bloomFilterMask = j;
                return;
            }
            throw new IllegalArgumentException("identifier must start with an ASCII letter: ".concat(str));
        }
        throw new IllegalArgumentException("identifier must not be empty");
    }
}
