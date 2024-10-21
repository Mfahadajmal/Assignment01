package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConfigurationCompat$Api24Impl {
    public static LocaleList getLocales(Configuration configuration) {
        return configuration.getLocales();
    }

    static void setLocales(Configuration configuration, LocaleListCompat localeListCompat) {
        configuration.setLocales((LocaleList) localeListCompat.mImpl$ar$class_merging.getLocaleList());
    }
}
