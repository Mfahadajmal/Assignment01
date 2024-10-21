package kotlin.collections.builders;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.EmptyList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializedCollection implements Externalizable {
    private static final long serialVersionUID = 0;
    private Collection collection;
    private final int tag;

    public SerializedCollection(Collection collection, int i) {
        this.collection = collection;
        this.tag = i;
    }

    private final Object readResolve() {
        return this.collection;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        Collection build;
        objectInput.getClass();
        byte readByte = objectInput.readByte();
        int i = readByte & 1;
        if ((readByte & (-2)) == 0) {
            int readInt = objectInput.readInt();
            if (readInt >= 0) {
                int i2 = 0;
                if (i != 0) {
                    SetBuilder setBuilder = new SetBuilder(new MapBuilder(readInt));
                    while (i2 < readInt) {
                        setBuilder.add(objectInput.readObject());
                        i2++;
                    }
                    build = OnDeviceObjectCreateLogEvent.build(setBuilder);
                } else {
                    ListBuilder listBuilder = new ListBuilder(readInt);
                    while (i2 < readInt) {
                        listBuilder.add(objectInput.readObject());
                        i2++;
                    }
                    build = OnDeviceLanguageIdentificationLogEvent.build(listBuilder);
                }
                this.collection = build;
                return;
            }
            throw new InvalidObjectException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(readInt, "Illegal size value: ", "."));
        }
        throw new InvalidObjectException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(readByte, "Unsupported flags value: ", "."));
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.getClass();
        objectOutput.writeByte(this.tag);
        objectOutput.writeInt(this.collection.size());
        Iterator it = this.collection.iterator();
        while (it.hasNext()) {
            objectOutput.writeObject(it.next());
        }
    }

    public SerializedCollection() {
        this(EmptyList.INSTANCE, 0);
    }
}
