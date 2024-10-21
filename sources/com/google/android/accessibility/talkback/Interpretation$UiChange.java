package com.google.android.accessibility.talkback;

import android.graphics.Rect;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$UiChange extends SpannableUtils$NonCopyableTextSpan {
    public static final Interpretation$UiChange WHOLE_SCREEN_UI_CHANGE = new Interpretation$UiChange(null, UiChangeType.WHOLE_SCREEN_UI_CHANGED);
    private final Rect sourceBoundsInScreen;
    private final UiChangeType uiChangeType;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum UiChangeType {
        UNKNOWN,
        WHOLE_SCREEN_UI_CHANGED,
        VIEW_CLICKED,
        VIEW_SCROLLED,
        WINDOW_CONTENT_CHANGED
    }

    public Interpretation$UiChange() {
        super((byte[]) null);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Interpretation$UiChange) {
            Interpretation$UiChange interpretation$UiChange = (Interpretation$UiChange) obj;
            Rect rect = this.sourceBoundsInScreen;
            if (rect != null ? rect.equals(interpretation$UiChange.sourceBoundsInScreen()) : interpretation$UiChange.sourceBoundsInScreen() == null) {
                if (this.uiChangeType.equals(interpretation$UiChange.uiChangeType())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        Rect rect = this.sourceBoundsInScreen;
        if (rect == null) {
            hashCode = 0;
        } else {
            hashCode = rect.hashCode();
        }
        return ((hashCode ^ 1000003) * 1000003) ^ this.uiChangeType.hashCode();
    }

    public final Rect sourceBoundsInScreen() {
        return this.sourceBoundsInScreen;
    }

    public final String toString() {
        UiChangeType uiChangeType = this.uiChangeType;
        return "UiChange{sourceBoundsInScreen=" + String.valueOf(this.sourceBoundsInScreen) + ", uiChangeType=" + uiChangeType.toString() + "}";
    }

    public final UiChangeType uiChangeType() {
        return this.uiChangeType;
    }

    public Interpretation$UiChange(Rect rect, UiChangeType uiChangeType) {
        this();
        this.sourceBoundsInScreen = rect;
        if (uiChangeType == null) {
            throw new NullPointerException("Null uiChangeType");
        }
        this.uiChangeType = uiChangeType;
    }
}
