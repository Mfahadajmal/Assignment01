package androidx.core.os;

import android.os.LocaleList;
import androidx.core.app.SharedElementCallback;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LocaleListCompat {
    public static final LocaleListCompat sEmptyLocaleList = create(new Locale[0]);
    public final LocaleListPlatformWrapper mImpl$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Api21Impl {
        private static final Locale[] PSEUDO_LOCALE = {new Locale("en", "XA"), new Locale("ar", "XB")};

        static Locale forLanguageTag(String str) {
            return Locale.forLanguageTag(str);
        }

        private static boolean isPseudoLocale(Locale locale) {
            Locale[] localeArr = PSEUDO_LOCALE;
            int length = localeArr.length;
            for (int i = 0; i < 2; i++) {
                if (localeArr[i].equals(locale)) {
                    return true;
                }
            }
            return false;
        }

        static boolean matchesLanguageAndScript(Locale locale, Locale locale2) {
            if (locale.equals(locale2)) {
                return true;
            }
            if (!locale.getLanguage().equals(locale2.getLanguage()) || isPseudoLocale(locale) || isPseudoLocale(locale2)) {
                return false;
            }
            String maximizeAndGetScript = SharedElementCallback.maximizeAndGetScript(locale);
            if (maximizeAndGetScript.isEmpty()) {
                String country = locale.getCountry();
                if (country.isEmpty() || country.equals(locale2.getCountry())) {
                    return true;
                }
                return false;
            }
            return maximizeAndGetScript.equals(SharedElementCallback.maximizeAndGetScript(locale2));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        static LocaleList createLocaleList(Locale... localeArr) {
            return new LocaleList(localeArr);
        }

        static LocaleList getAdjustedDefault() {
            return LocaleList.getAdjustedDefault();
        }

        static LocaleList getDefault() {
            return LocaleList.getDefault();
        }
    }

    private LocaleListCompat(LocaleListPlatformWrapper localeListPlatformWrapper) {
        this.mImpl$ar$class_merging = localeListPlatformWrapper;
    }

    public static LocaleListCompat create(Locale... localeArr) {
        return wrap(Api24Impl.createLocaleList(localeArr));
    }

    public static LocaleListCompat forLanguageTags(String str) {
        if (str != null && !str.isEmpty()) {
            String[] split = str.split(",", -1);
            int length = split.length;
            Locale[] localeArr = new Locale[length];
            for (int i = 0; i < length; i++) {
                localeArr[i] = Api21Impl.forLanguageTag(split[i]);
            }
            return create(localeArr);
        }
        return sEmptyLocaleList;
    }

    public static LocaleListCompat wrap(LocaleList localeList) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(localeList));
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof LocaleListCompat) && this.mImpl$ar$class_merging.equals(((LocaleListCompat) obj).mImpl$ar$class_merging)) {
            return true;
        }
        return false;
    }

    public final Locale get(int i) {
        return this.mImpl$ar$class_merging.get(i);
    }

    public final int hashCode() {
        return this.mImpl$ar$class_merging.hashCode();
    }

    public final boolean isEmpty() {
        return this.mImpl$ar$class_merging.isEmpty();
    }

    public final int size() {
        return this.mImpl$ar$class_merging.size();
    }

    public final String toLanguageTags() {
        return this.mImpl$ar$class_merging.toLanguageTags();
    }

    public final String toString() {
        return this.mImpl$ar$class_merging.toString();
    }
}
