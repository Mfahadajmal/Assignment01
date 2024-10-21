package j$.time.chrono;

import java.io.Externalizable;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/* loaded from: classes2.dex */
final class G implements Externalizable {
    private static final long serialVersionUID = -6103370247208168577L;
    private byte a;
    private Object b;

    public G() {
    }

    private Object readResolve() {
        return this.b;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        Object h;
        byte readByte = objectInput.readByte();
        this.a = readByte;
        switch (readByte) {
            case 1:
                int i = AbstractC0011a.c;
                h = AbstractC0011a.h(objectInput.readUTF());
                break;
            case 2:
                h = ((InterfaceC0012b) objectInput.readObject()).B((j$.time.k) objectInput.readObject());
                break;
            case 3:
                h = ((InterfaceC0015e) objectInput.readObject()).v((j$.time.A) objectInput.readObject()).k((j$.time.z) objectInput.readObject());
                break;
            case 4:
                j$.time.g gVar = z.d;
                int readInt = objectInput.readInt();
                byte readByte2 = objectInput.readByte();
                byte readByte3 = objectInput.readByte();
                x.d.getClass();
                h = new z(j$.time.g.V(readInt, readByte2, readByte3));
                break;
            case 5:
                A a = A.d;
                h = A.C(objectInput.readByte());
                break;
            case 6:
                q qVar = (q) objectInput.readObject();
                int readInt2 = objectInput.readInt();
                byte readByte4 = objectInput.readByte();
                byte readByte5 = objectInput.readByte();
                qVar.getClass();
                h = s.Q(qVar, readInt2, readByte4, readByte5);
                break;
            case 7:
                int readInt3 = objectInput.readInt();
                byte readByte6 = objectInput.readByte();
                byte readByte7 = objectInput.readByte();
                C.d.getClass();
                h = new E(j$.time.g.V(readInt3 + 1911, readByte6, readByte7));
                break;
            case 8:
                int readInt4 = objectInput.readInt();
                byte readByte8 = objectInput.readByte();
                byte readByte9 = objectInput.readByte();
                I.d.getClass();
                h = new K(j$.time.g.V(readInt4 - 543, readByte8, readByte9));
                break;
            case 9:
                int i2 = C0018h.e;
                h = new C0018h(AbstractC0011a.h(objectInput.readUTF()), objectInput.readInt(), objectInput.readInt(), objectInput.readInt());
                break;
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
        this.b = h;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) {
        byte b = this.a;
        Object obj = this.b;
        objectOutput.writeByte(b);
        switch (b) {
            case 1:
                objectOutput.writeUTF(((AbstractC0011a) obj).m());
                return;
            case 2:
                ((C0017g) obj).writeExternal(objectOutput);
                return;
            case 3:
                ((m) obj).writeExternal(objectOutput);
                return;
            case 4:
                z zVar = (z) obj;
                zVar.getClass();
                objectOutput.writeInt(j$.time.temporal.k.a(zVar, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.k.a(zVar, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.k.a(zVar, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 5:
                ((A) obj).H(objectOutput);
                return;
            case 6:
                ((s) obj).writeExternal(objectOutput);
                return;
            case 7:
                E e = (E) obj;
                e.getClass();
                objectOutput.writeInt(j$.time.temporal.k.a(e, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.k.a(e, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.k.a(e, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 8:
                K k = (K) obj;
                k.getClass();
                objectOutput.writeInt(j$.time.temporal.k.a(k, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.k.a(k, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.k.a(k, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 9:
                ((C0018h) obj).writeExternal(objectOutput);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public G(byte b, Object obj) {
        this.a = b;
        this.b = obj;
    }
}
