package com.google.android.accessibility.talkback.compositor;

import com.google.android.accessibility.utils.ReadOnly;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.input.TextEventInterpretation;
import com.google.android.play.core.splitcompat.ingestion.Verifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventInterpretation extends ReadOnly {
    public AccessibilityFocusEventInterpretation mAccessibilityFocus;
    public int mEvent;
    public HintEventInterpretation mHint;
    public CharSequence mPackageName;
    public TextEventInterpretation mText;

    public EventInterpretation(int i) {
        this.mEvent = i;
    }

    public final void setTextEventInterpretation(TextEventInterpretation textEventInterpretation) {
        checkIsWritable();
        this.mText = textEventInterpretation;
    }

    public final String toString() {
        return StringBuilderUtils.joinFields(Verifier.eventTypeToString(this.mEvent), StringBuilderUtils.optionalText("Package", this.mPackageName), StringBuilderUtils.optionalSubObj("Text", this.mText), StringBuilderUtils.optionalSubObj("Hint", this.mHint));
    }
}
