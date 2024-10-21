package com.google.gson;

import java.lang.reflect.Field;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field) {
        field.getClass();
        this.field = field;
    }

    public final String toString() {
        return this.field.toString();
    }
}
