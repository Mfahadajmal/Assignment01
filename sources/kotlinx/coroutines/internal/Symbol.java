package kotlinx.coroutines.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Symbol {
    public final String symbol;

    public Symbol(String str) {
        this.symbol = str;
    }

    public final String toString() {
        return "<" + this.symbol + ">";
    }
}
