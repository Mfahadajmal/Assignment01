package com.google.android.gsf;

import android.net.Uri;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GservicesConstants {
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri CONTENT_PREFIX_URI = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern TRUE_PATTERN = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern FALSE_PATTERN = Pattern.compile("^(0|false|f|off|no|n)$", 2);
}
