package androidx.core.app;

import androidx.core.text.ICUCompat$Api24Impl;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedElementCallback {
    public static String maximizeAndGetScript(Locale locale) {
        return ICUCompat$Api24Impl.getScript(ICUCompat$Api24Impl.addLikelySubtags(ICUCompat$Api24Impl.forLocale(locale)));
    }
}
