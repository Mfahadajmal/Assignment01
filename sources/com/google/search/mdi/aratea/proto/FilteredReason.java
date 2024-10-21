package com.google.search.mdi.aratea.proto;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum FilteredReason implements Internal.EnumLite {
    FILTERED_REASON_UNSPECIFIED(0),
    IMAGE_SAFETY(1),
    TEXT_SAFETY(2),
    TEXT_LOW_QUALITY(3),
    TEXT_BLOCKLIST(4),
    TEXT_IRRELEVANT(5),
    FILTERED_REASON_MEMORIZATION(6),
    QUERY_INVALID(7);

    private final int value;

    FilteredReason(int i) {
        this.value = i;
    }

    public static FilteredReason forNumber(int i) {
        switch (i) {
            case 0:
                return FILTERED_REASON_UNSPECIFIED;
            case 1:
                return IMAGE_SAFETY;
            case 2:
                return TEXT_SAFETY;
            case 3:
                return TEXT_LOW_QUALITY;
            case 4:
                return TEXT_BLOCKLIST;
            case 5:
                return TEXT_IRRELEVANT;
            case 6:
                return FILTERED_REASON_MEMORIZATION;
            case 7:
                return QUERY_INVALID;
            default:
                return null;
        }
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.value);
    }
}
