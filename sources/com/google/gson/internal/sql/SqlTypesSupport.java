package com.google.gson.internal.sql;

import com.google.gson.TypeAdapterFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SqlTypesSupport {
    public static final TypeAdapterFactory DATE_FACTORY;
    public static final boolean SUPPORTS_SQL_TYPES;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapterFactory TIME_FACTORY;

    static {
        boolean z;
        TypeAdapterFactory typeAdapterFactory;
        try {
            Class.forName("java.sql.Date");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        SUPPORTS_SQL_TYPES = z;
        if (z) {
            DATE_FACTORY = SqlDateTypeAdapter.FACTORY;
            TIME_FACTORY = SqlTimeTypeAdapter.FACTORY;
            typeAdapterFactory = SqlTimestampTypeAdapter.FACTORY;
        } else {
            typeAdapterFactory = null;
            DATE_FACTORY = null;
            TIME_FACTORY = null;
        }
        TIMESTAMP_FACTORY = typeAdapterFactory;
    }
}
