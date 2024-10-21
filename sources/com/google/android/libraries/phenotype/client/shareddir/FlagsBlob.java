package com.google.android.libraries.phenotype.client.shareddir;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.ByteString;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagsBlob {
    public static final FlagsBlob EMPTY = new FlagsBlob(RegularImmutableSortedSet.NATURAL_EMPTY_SET);
    public final ImmutableSortedSet values;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ParsedParam implements Comparable {
        public final long intName;
        final long intOrFloat;
        final String stringName;
        final Object stringOrBytes;
        final int type;

        public ParsedParam(long j, String str, int i, long j2, Object obj) {
            boolean z;
            boolean z2;
            if (j != 0) {
                z = false;
            } else {
                z = true;
            }
            if (str == null) {
                z2 = false;
            } else {
                z2 = true;
            }
            ContextDataProvider.checkArgument(z == z2);
            this.intName = j;
            this.stringName = str;
            this.type = i;
            this.intOrFloat = j2;
            this.stringOrBytes = obj;
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            ParsedParam parsedParam = (ParsedParam) obj;
            int compare = Long.compare(this.intName, parsedParam.intName);
            if (compare == 0) {
                if (this.intName != 0) {
                    return 0;
                }
                String str = this.stringName;
                str.getClass();
                String str2 = parsedParam.stringName;
                str2.getClass();
                return str.compareTo(str2);
            }
            return compare;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ParsedParam)) {
                return false;
            }
            ParsedParam parsedParam = (ParsedParam) obj;
            if (this.intName == parsedParam.intName && Objects.equals(this.stringName, parsedParam.stringName)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Long.valueOf(this.intName), this.stringName);
        }

        public final String nameAsString() {
            String str = this.stringName;
            if (str != null) {
                return str;
            }
            return Long.toString(this.intName);
        }

        public final Object toObject() {
            int i = this.type;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i == 5) {
                                    Object obj = this.stringOrBytes;
                                    obj.getClass();
                                    if (obj instanceof byte[]) {
                                        return (byte[]) obj;
                                    }
                                    return ((ByteString) obj).toByteArray();
                                }
                                throw new AssertionError("Impossible, this was validated when parsed or created");
                            }
                            Object obj2 = this.stringOrBytes;
                            obj2.getClass();
                            return obj2;
                        }
                        return Double.valueOf(Double.longBitsToDouble(this.intOrFloat));
                    }
                    return Long.valueOf(this.intOrFloat);
                }
                return true;
            }
            return false;
        }

        public final String toString() {
            return nameAsString() + ":" + String.valueOf(toObject());
        }
    }

    public FlagsBlob(ImmutableSortedSet immutableSortedSet) {
        this.values = immutableSortedSet;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof FlagsBlob) {
            return this.values.equals(((FlagsBlob) obj).values);
        }
        return false;
    }

    public final int hashCode() {
        return ContextDataProvider.hashCodeImpl(this.values);
    }
}
