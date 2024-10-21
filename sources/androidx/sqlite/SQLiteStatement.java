package androidx.sqlite;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SQLiteStatement {
    void close();

    int getColumnCount();

    String getColumnName(int i);

    long getLong(int i);

    String getText(int i);

    boolean isNull(int i);

    void reset();

    boolean step();
}
