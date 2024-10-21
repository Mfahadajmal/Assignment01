package androidx.room.util;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ForeignKeyWithSequence implements Comparable {
    public final String from;
    public final int id;
    private final int sequence;
    public final String to;

    public ForeignKeyWithSequence(int i, int i2, String str, String str2) {
        this.id = i;
        this.sequence = i2;
        this.from = str;
        this.to = str2;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        ForeignKeyWithSequence foreignKeyWithSequence = (ForeignKeyWithSequence) obj;
        foreignKeyWithSequence.getClass();
        int i = this.id - foreignKeyWithSequence.id;
        if (i == 0) {
            return this.sequence - foreignKeyWithSequence.sequence;
        }
        return i;
    }
}
