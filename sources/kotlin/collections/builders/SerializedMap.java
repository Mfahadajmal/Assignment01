package kotlin.collections.builders;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import kotlin.collections.EmptyMap;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SerializedMap implements Externalizable {
    private static final long serialVersionUID = 0;
    private Map map;

    public SerializedMap(Map map) {
        this.map = map;
    }

    private final Object readResolve() {
        return this.map;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        objectInput.getClass();
        byte readByte = objectInput.readByte();
        if (readByte == 0) {
            int readInt = objectInput.readInt();
            if (readInt >= 0) {
                MapBuilder mapBuilder = new MapBuilder(readInt);
                for (int i = 0; i < readInt; i++) {
                    mapBuilder.put(objectInput.readObject(), objectInput.readObject());
                }
                this.map = OnDeviceLanguageIdentificationLogEvent.build(mapBuilder);
                return;
            }
            throw new InvalidObjectException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(readInt, "Illegal size value: ", "."));
        }
        throw new InvalidObjectException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readByte, "Unsupported flags value: "));
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.getClass();
        objectOutput.writeByte(0);
        objectOutput.writeInt(this.map.size());
        for (Map.Entry entry : this.map.entrySet()) {
            objectOutput.writeObject(entry.getKey());
            objectOutput.writeObject(entry.getValue());
        }
    }

    public SerializedMap() {
        this(EmptyMap.INSTANCE);
    }
}
