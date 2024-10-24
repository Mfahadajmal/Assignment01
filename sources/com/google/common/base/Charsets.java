package com.google.common.base;

import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Charsets {
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    public static final Charset UTF_8;

    static {
        Charset.forName("ISO-8859-1");
        UTF_8 = Charset.forName("UTF-8");
        Charset.forName("UTF-16BE");
        Charset.forName("UTF-16LE");
        Charset.forName("UTF-16");
    }
}
