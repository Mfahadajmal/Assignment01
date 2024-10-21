package j$.time;

import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class B extends z {
    public static final /* synthetic */ int c = 0;
    private static final long serialVersionUID = 8386373296231747096L;
    private final String a;
    private final transient j$.time.zone.e b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public B(String str, j$.time.zone.e eVar) {
        this.a = str;
        this.b = eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static B O(String str) {
        j$.time.zone.e eVar;
        Objects.a(str, "zoneId");
        int length = str.length();
        if (length >= 2) {
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && ((charAt != '/' || i == 0) && ((charAt < '0' || charAt > '9' || i == 0) && ((charAt != '~' || i == 0) && ((charAt != '.' || i == 0) && ((charAt != '_' || i == 0) && ((charAt != '+' || i == 0) && (charAt != '-' || i == 0))))))))) {
                    throw new RuntimeException("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
                }
            }
            try {
                eVar = j$.time.zone.i.a(str, true);
            } catch (j$.time.zone.f unused) {
                eVar = null;
            }
            return new B(str, eVar);
        }
        throw new RuntimeException("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 7, this);
    }

    @Override // j$.time.z
    public final j$.time.zone.e J() {
        j$.time.zone.e eVar = this.b;
        return eVar != null ? eVar : j$.time.zone.i.a(this.a, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.time.z
    public final void N(ObjectOutput objectOutput) {
        objectOutput.writeByte(7);
        objectOutput.writeUTF(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void P(DataOutput dataOutput) {
        dataOutput.writeUTF(this.a);
    }

    @Override // j$.time.z
    public final String m() {
        return this.a;
    }
}
