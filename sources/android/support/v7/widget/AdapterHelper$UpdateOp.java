package android.support.v7.widget;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AdapterHelper$UpdateOp {
    public int cmd;
    public int itemCount;
    public Object payload;
    public int positionStart;

    public AdapterHelper$UpdateOp(int i, int i2, int i3, Object obj) {
        this.cmd = i;
        this.positionStart = i2;
        this.itemCount = i3;
        this.payload = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdapterHelper$UpdateOp)) {
            return false;
        }
        AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp) obj;
        int i = this.cmd;
        if (i != adapterHelper$UpdateOp.cmd) {
            return false;
        }
        if (i == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == adapterHelper$UpdateOp.positionStart && this.positionStart == adapterHelper$UpdateOp.itemCount) {
            return true;
        }
        if (this.itemCount != adapterHelper$UpdateOp.itemCount || this.positionStart != adapterHelper$UpdateOp.positionStart) {
            return false;
        }
        Object obj2 = this.payload;
        if (obj2 != null) {
            if (!obj2.equals(adapterHelper$UpdateOp.payload)) {
                return false;
            }
        } else if (adapterHelper$UpdateOp.payload != null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i = this.cmd;
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 8) {
                        str = "??";
                    } else {
                        str = "mv";
                    }
                } else {
                    str = "up";
                }
            } else {
                str = "rm";
            }
        } else {
            str = "add";
        }
        sb.append(str);
        sb.append(",s:");
        sb.append(this.positionStart);
        sb.append("c:");
        sb.append(this.itemCount);
        sb.append(",p:");
        sb.append(this.payload);
        sb.append("]");
        return sb.toString();
    }
}
