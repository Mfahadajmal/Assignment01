package com.google.android.accessibility.selecttospeak.iterator;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Paragraph {
    public Sentence head;
    public Paragraph next;
    public Paragraph prev;
    public Sentence tail;

    public Paragraph() {
        this(null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Paragraph)) {
            return false;
        }
        Paragraph paragraph = (Paragraph) obj;
        if (Intrinsics.areEqual(this.head, paragraph.head) && Intrinsics.areEqual(this.tail, paragraph.tail) && Intrinsics.areEqual(this.next, paragraph.next) && Intrinsics.areEqual(this.prev, paragraph.prev)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        int hashCode3;
        Sentence sentence = this.head;
        int i = 0;
        if (sentence == null) {
            hashCode = 0;
        } else {
            hashCode = sentence.hashCode();
        }
        Sentence sentence2 = this.tail;
        if (sentence2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = sentence2.hashCode();
        }
        int i2 = hashCode * 31;
        Paragraph paragraph = this.next;
        if (paragraph == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = paragraph.hashCode();
        }
        int i3 = (((i2 + hashCode2) * 31) + hashCode3) * 31;
        Paragraph paragraph2 = this.prev;
        if (paragraph2 != null) {
            i = paragraph2.hashCode();
        }
        return i3 + i;
    }

    public final String toString() {
        return "Paragraph(head=" + this.head + ", tail=" + this.tail + ", next=" + this.next + ", prev=" + this.prev + ")";
    }

    public /* synthetic */ Paragraph(byte[] bArr) {
        this.head = null;
        this.tail = null;
        this.next = null;
        this.prev = null;
    }
}
