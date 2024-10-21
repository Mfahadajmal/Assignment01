package com.google.common.base;

import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MoreObjects$ToStringHelper {
    private final String className;
    private final ValueHolder holderHead;
    private ValueHolder holderTail;
    private boolean omitNullValues;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UnconditionalValueHolder extends ValueHolder {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ValueHolder {
        String name;
        ValueHolder next;
        public Object value;
    }

    public MoreObjects$ToStringHelper(String str) {
        ValueHolder valueHolder = new ValueHolder();
        this.holderHead = valueHolder;
        this.holderTail = valueHolder;
        this.omitNullValues = false;
        str.getClass();
        this.className = str;
    }

    public final MoreObjects$ToStringHelper add(String str, int i) {
        addUnconditionalHolder$ar$ds(str, String.valueOf(i));
        return this;
    }

    public final ValueHolder addHolder() {
        ValueHolder valueHolder = new ValueHolder();
        this.holderTail.next = valueHolder;
        this.holderTail = valueHolder;
        return valueHolder;
    }

    public final void addHolder$ar$ds(String str, Object obj) {
        ValueHolder addHolder = addHolder();
        addHolder.value = obj;
        addHolder.name = str;
    }

    public final void addUnconditionalHolder$ar$ds(String str, Object obj) {
        UnconditionalValueHolder unconditionalValueHolder = new UnconditionalValueHolder();
        this.holderTail.next = unconditionalValueHolder;
        this.holderTail = unconditionalValueHolder;
        unconditionalValueHolder.value = obj;
        unconditionalValueHolder.name = str;
    }

    public final void omitNullValues$ar$ds() {
        this.omitNullValues = true;
    }

    public final String toString() {
        boolean z = this.omitNullValues;
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.className);
        sb.append('{');
        String str = "";
        for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
            boolean z2 = valueHolder instanceof UnconditionalValueHolder;
            Object obj = valueHolder.value;
            if (z2 || obj != null || !z) {
                sb.append(str);
                String str2 = valueHolder.name;
                if (str2 != null) {
                    sb.append(str2);
                    sb.append('=');
                }
                if (obj != null && obj.getClass().isArray()) {
                    sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r4.length() - 1);
                } else {
                    sb.append(obj);
                }
                str = ", ";
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public final MoreObjects$ToStringHelper add(String str, long j) {
        addUnconditionalHolder$ar$ds(str, String.valueOf(j));
        return this;
    }

    public final MoreObjects$ToStringHelper add(String str, boolean z) {
        addUnconditionalHolder$ar$ds(str, String.valueOf(z));
        return this;
    }
}
