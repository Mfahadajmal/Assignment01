package androidx.browser.customtabs;

import android.os.LocaleList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CustomTabsIntent$Api24Impl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDefaultLocale() {
        LocaleList adjustedDefault = LocaleList.getAdjustedDefault();
        if (adjustedDefault.size() > 0) {
            return adjustedDefault.get(0).toLanguageTag();
        }
        return null;
    }
}
