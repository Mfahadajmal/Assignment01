package com.google.android.libraries.performance.primes;

import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.common.base.Joiner;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoPiiString {
    public final String value;

    public NoPiiString(String str) {
        this.value = str;
    }

    public static NoPiiString concat(NoPiiString noPiiString, NoPiiString... noPiiStringArr) {
        return new NoPiiString(String.valueOf(noPiiString.value).concat(String.valueOf(new Joiner("").join(ContextDataProvider.transform(Arrays.asList(noPiiStringArr), new AccessibilityEventUtils$$ExternalSyntheticLambda0(13))))));
    }

    public static NoPiiString fromClass(Class cls) {
        if (!ContextDataProvider.stringIsNullOrEmpty(null)) {
            return new NoPiiString("null".concat(String.valueOf(cls.getSimpleName())));
        }
        return new NoPiiString(cls.getSimpleName());
    }

    public static NoPiiString fromEnum(Enum r2) {
        if (!ContextDataProvider.stringIsNullOrEmpty(null)) {
            return new NoPiiString("null".concat(String.valueOf(r2.name())));
        }
        return new NoPiiString(r2.name());
    }

    public static String safeToString(NoPiiString noPiiString) {
        if (noPiiString == null) {
            return null;
        }
        return noPiiString.value;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof NoPiiString) {
            return this.value.equals(((NoPiiString) obj).value);
        }
        return false;
    }

    public final int hashCode() {
        return this.value.hashCode();
    }

    public final String toString() {
        return this.value;
    }
}
