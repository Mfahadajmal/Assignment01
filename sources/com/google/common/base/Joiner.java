package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Joiner {
    private final String separator;

    public Joiner(Joiner joiner) {
        this.separator = joiner.separator;
    }

    public final void appendTo$ar$ds(StringBuilder sb, Iterator it) {
        try {
            if (it.hasNext()) {
                sb.append(toString(it.next()));
                while (it.hasNext()) {
                    sb.append((CharSequence) this.separator);
                    sb.append(toString(it.next()));
                }
            }
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final String join(Iterable iterable) {
        Iterator it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        appendTo$ar$ds(sb, it);
        return sb.toString();
    }

    public CharSequence toString(Object obj) {
        obj.getClass();
        if (obj instanceof CharSequence) {
            return (CharSequence) obj;
        }
        return obj.toString();
    }

    public Joiner(String str) {
        str.getClass();
        this.separator = str;
    }
}
