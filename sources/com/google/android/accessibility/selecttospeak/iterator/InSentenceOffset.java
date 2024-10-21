package com.google.android.accessibility.selecttospeak.iterator;

import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InSentenceOffset {
    public int end;
    public int offset;
    public int start;

    public InSentenceOffset() {
        this(null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InSentenceOffset)) {
            return false;
        }
        InSentenceOffset inSentenceOffset = (InSentenceOffset) obj;
        if (this.start == inSentenceOffset.start && this.end == inSentenceOffset.end && this.offset == inSentenceOffset.offset) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((this.start * 31) + this.end) * 31) + this.offset;
    }

    public final boolean isStarted() {
        if (this.start != -1 && this.end != -1) {
            return true;
        }
        return false;
    }

    public final void resetOffset() {
        this.offset = 0;
        this.start = -1;
        this.end = -1;
    }

    public final String toString() {
        return "InSentenceOffset(start=" + this.start + ", end=" + this.end + ", offset=" + this.offset + ")";
    }

    public final void updateOffset() {
        if (!isStarted()) {
            LogUtils.e("InSentenceOffset", "Attempted to update offset before tts utterance callback is started.", new Object[0]);
            this.start = 0;
            this.end = 0;
        }
        this.offset = this.start;
    }

    public /* synthetic */ InSentenceOffset(byte[] bArr) {
        this.start = -1;
        this.end = -1;
        this.offset = 0;
    }
}
