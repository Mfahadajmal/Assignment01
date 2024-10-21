package com.google.android.accessibility.selecttospeak.debug;

import _COROUTINE._BOUNDARY;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Setting {
    private final boolean isContinuousReading;
    private final boolean isOcrEnabled;
    private final String versionNumber;
    private final String voice;

    public Setting(String str, boolean z, boolean z2, String str2) {
        this.versionNumber = str;
        this.isOcrEnabled = z;
        this.isContinuousReading = z2;
        this.voice = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Setting)) {
            return false;
        }
        Setting setting = (Setting) obj;
        if (Intrinsics.areEqual(this.versionNumber, setting.versionNumber) && this.isOcrEnabled == setting.isOcrEnabled && this.isContinuousReading == setting.isContinuousReading && Intrinsics.areEqual(this.voice, setting.voice)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.versionNumber.hashCode() * 31;
        String str = this.voice;
        return ((((hashCode + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.isOcrEnabled)) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.isContinuousReading)) * 31) + str.hashCode();
    }

    public final String toString() {
        return "Setting(versionNumber=" + this.versionNumber + ", isOcrEnabled=" + this.isOcrEnabled + ", isContinuousReading=" + this.isContinuousReading + ", voice=" + this.voice + ")";
    }
}
