package org.chromium.base;

import _COROUTINE._BOUNDARY;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LocaleUtils {
    private LocaleUtils() {
    }

    public static String getDefaultCountryCode() {
        CommandLine commandLine = CommandLine.getInstance();
        if (commandLine.hasSwitch$ar$ds()) {
            return commandLine.getSwitchValue$ar$ds();
        }
        return Locale.getDefault().getCountry();
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x000e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDefaultLocaleListString() {
        /*
            android.os.LocaleList r0 = org.chromium.base.BuildInfo$$ExternalSyntheticApiModelOutline1.m()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
        La:
            int r3 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m(r0)
            if (r2 >= r3) goto L3d
            java.util.Locale r3 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m(r0, r2)
            java.lang.String r4 = r3.getLanguage()
            java.lang.String r5 = getUpdatedLanguageForChromium(r4)
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L33
            java.util.Locale$Builder r4 = new java.util.Locale$Builder
            r4.<init>()
            java.util.Locale$Builder r3 = r4.setLocale(r3)
            java.util.Locale$Builder r3 = r3.setLanguage(r5)
            java.util.Locale r3 = r3.build()
        L33:
            java.lang.String r3 = toLanguageTag(r3)
            r1.add(r3)
            int r2 = r2 + 1
            goto La
        L3d:
            java.lang.String r0 = ","
            java.lang.String r0 = android.text.TextUtils.join(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.LocaleUtils.getDefaultLocaleListString():java.lang.String");
    }

    public static String getDefaultLocaleString() {
        return toLanguageTag(Locale.getDefault());
    }

    public static String getUpdatedLanguageForChromium(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 3365) {
            if (hashCode != 3374) {
                if (hashCode != 3391) {
                    if (hashCode != 3405) {
                        if (hashCode != 3704) {
                            if (hashCode == 102533 && str.equals("gom")) {
                                c = 0;
                            }
                            c = 65535;
                        } else {
                            if (str.equals("tl")) {
                                c = 5;
                            }
                            c = 65535;
                        }
                    } else {
                        if (str.equals("jw")) {
                            c = 4;
                        }
                        c = 65535;
                    }
                } else {
                    if (str.equals("ji")) {
                        c = 3;
                    }
                    c = 65535;
                }
            } else {
                if (str.equals("iw")) {
                    c = 2;
                }
                c = 65535;
            }
        } else {
            if (str.equals("in")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            if (c != 5) {
                                return str;
                            }
                            return "fil";
                        }
                        return "jv";
                    }
                    return "yi";
                }
                return "he";
            }
            return "id";
        }
        return "kok";
    }

    public static String toLanguageTag(Locale locale) {
        String updatedLanguageForChromium = getUpdatedLanguageForChromium(locale.getLanguage());
        String country = locale.getCountry();
        if (updatedLanguageForChromium.equals("no") && country.equals("NO") && locale.getVariant().equals("NY")) {
            return "nn-NO";
        }
        if (country.isEmpty()) {
            return updatedLanguageForChromium;
        }
        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(country, updatedLanguageForChromium, "-");
    }
}
