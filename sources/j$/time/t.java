package j$.time;

import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class t implements Externalizable {
    private static final long serialVersionUID = -7683839454370182990L;
    private byte a;
    private Object b;

    public t() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Serializable a(ObjectInput objectInput) {
        return b(objectInput.readByte(), objectInput);
    }

    private static Serializable b(byte b, ObjectInput objectInput) {
        switch (b) {
            case 1:
                Duration duration = Duration.ZERO;
                return Duration.r(objectInput.readLong(), objectInput.readInt());
            case 2:
                Instant instant = Instant.EPOCH;
                return Instant.M(objectInput.readLong(), objectInput.readInt());
            case 3:
                g gVar = g.d;
                return g.V(objectInput.readInt(), objectInput.readByte(), objectInput.readByte());
            case 4:
                return k.X(objectInput);
            case 5:
                i iVar = i.c;
                g gVar2 = g.d;
                return i.Q(g.V(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), k.X(objectInput));
            case 6:
                return D.M(objectInput);
            case 7:
                int i = B.c;
                return z.K(objectInput.readUTF());
            case 8:
                return A.V(objectInput);
            case 9:
                return r.K(objectInput);
            case 10:
                return p.L(objectInput);
            case 11:
                int i2 = w.b;
                return w.I(objectInput.readInt());
            case 12:
                int i3 = y.c;
                return y.J(objectInput.readInt(), objectInput.readByte());
            case 13:
                return n.I(objectInput);
            case 14:
                return s.a(objectInput);
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
    }

    private Object readResolve() {
        return this.b;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        this.a = readByte;
        this.b = b(readByte, objectInput);
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) {
        byte b = this.a;
        Object obj = this.b;
        objectOutput.writeByte(b);
        switch (b) {
            case 1:
                ((Duration) obj).writeExternal(objectOutput);
                return;
            case 2:
                ((Instant) obj).Q(objectOutput);
                return;
            case 3:
                ((g) obj).h0(objectOutput);
                return;
            case 4:
                ((k) obj).c0(objectOutput);
                return;
            case 5:
                ((i) obj).Z(objectOutput);
                return;
            case 6:
                ((D) obj).P(objectOutput);
                return;
            case 7:
                ((B) obj).P(objectOutput);
                return;
            case 8:
                ((A) obj).W(objectOutput);
                return;
            case 9:
                ((r) obj).writeExternal(objectOutput);
                return;
            case 10:
                ((p) obj).writeExternal(objectOutput);
                return;
            case 11:
                ((w) obj).M(objectOutput);
                return;
            case 12:
                ((y) obj).P(objectOutput);
                return;
            case 13:
                ((n) obj).J(objectOutput);
                return;
            case 14:
                ((s) obj).writeExternal(objectOutput);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(byte b, Object obj) {
        this.a = b;
        this.b = obj;
    }
}
