package com.google.android.accessibility.selecttospeak.popup;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SPopupParsedIntent {
    public final String text;

    public S2SPopupParsedIntent(String str) {
        this.text = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof S2SPopupParsedIntent) && Intrinsics.areEqual(this.text, ((S2SPopupParsedIntent) obj).text)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.text.hashCode();
    }

    public final String toString() {
        return "S2SPopupParsedIntent(text=" + this.text + ")";
    }
}
