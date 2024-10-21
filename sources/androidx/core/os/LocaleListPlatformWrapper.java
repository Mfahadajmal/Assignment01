package androidx.core.os;

import android.os.LocaleList;
import androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LocaleListPlatformWrapper {
    private final LocaleList mLocaleList;

    public LocaleListPlatformWrapper(Object obj) {
        this.mLocaleList = ViewCompat$$ExternalSyntheticApiModelOutline0.m6m(obj);
    }

    public final boolean equals(Object obj) {
        boolean equals;
        equals = this.mLocaleList.equals(((LocaleListPlatformWrapper) obj).getLocaleList());
        return equals;
    }

    public final Locale get(int i) {
        Locale locale;
        locale = this.mLocaleList.get(i);
        return locale;
    }

    public final Object getLocaleList() {
        return this.mLocaleList;
    }

    public final int hashCode() {
        int hashCode;
        hashCode = this.mLocaleList.hashCode();
        return hashCode;
    }

    public final boolean isEmpty() {
        boolean isEmpty;
        isEmpty = this.mLocaleList.isEmpty();
        return isEmpty;
    }

    public final int size() {
        int size;
        size = this.mLocaleList.size();
        return size;
    }

    public final String toLanguageTags() {
        String languageTags;
        languageTags = this.mLocaleList.toLanguageTags();
        return languageTags;
    }

    public final String toString() {
        String localeList;
        localeList = this.mLocaleList.toString();
        return localeList;
    }
}
