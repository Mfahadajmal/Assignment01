package com.google.android.accessibility.talkback;

import android.view.KeyEvent;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$Key extends SpannableUtils$NonCopyableTextSpan {
    public final KeyEvent event;

    public Interpretation$Key(KeyEvent keyEvent) {
        super((byte[]) null);
        this.event = keyEvent;
    }

    public final boolean equals(Object obj) {
        Interpretation$Key interpretation$Key = (Interpretation$Key) SpannableUtils$NonCopyableTextSpan.castOrNull(obj, Interpretation$Key.class);
        if (interpretation$Key != null && Objects.equals(this.event, interpretation$Key.event)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.event);
    }

    public final String toString() {
        return this.event.toString();
    }
}
