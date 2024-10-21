package kotlin.enums;

import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EnumEntriesSerializationProxy implements Serializable {
    private static final long serialVersionUID = 0;
    private final Class c;

    public EnumEntriesSerializationProxy(Enum[] enumArr) {
        Class<?> componentType = enumArr.getClass().getComponentType();
        componentType.getClass();
        this.c = componentType;
    }

    private final Object readResolve() {
        Object[] enumConstants = this.c.getEnumConstants();
        enumConstants.getClass();
        return OnDevicePoseDetectionLogEvent.enumEntries$ar$class_merging((Enum[]) enumConstants);
    }
}
