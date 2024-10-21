package com.google.android.accessibility.talkback;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$Power extends SpannableUtils$NonCopyableTextSpan {
    public final boolean connected;
    public final int percent;

    public Interpretation$Power(boolean z, int i) {
        super((byte[]) null);
        this.connected = z;
        this.percent = i;
    }

    public final boolean equals(Object obj) {
        Interpretation$Power interpretation$Power = (Interpretation$Power) SpannableUtils$NonCopyableTextSpan.castOrNull(obj, Interpretation$Power.class);
        if (interpretation$Power != null && this.connected == interpretation$Power.connected && this.percent == interpretation$Power.percent) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Boolean.valueOf(this.connected), Integer.valueOf(this.percent));
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("Power{", StringBuilderUtils.optionalTag("connected", this.connected), StringBuilderUtils.optionalInt("percent", this.percent, -1), "}");
    }
}
