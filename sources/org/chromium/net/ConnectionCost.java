package org.chromium.net;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: PG */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface ConnectionCost {
    public static final int LAST = 3;
    public static final int METERED = 2;
    public static final int UNKNOWN = 0;
    public static final int UNMETERED = 1;
}
