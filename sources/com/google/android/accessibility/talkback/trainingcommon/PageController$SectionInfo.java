package com.google.android.accessibility.talkback.trainingcommon;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PageController$SectionInfo {
    public final int firstPageNumber;
    public final int indexPageNumber;
    public final int totalPageNumber;

    public PageController$SectionInfo(int i, int i2, int i3) {
        this.indexPageNumber = i;
        this.firstPageNumber = i2;
        this.totalPageNumber = i3;
    }

    public final String toString() {
        return "SectionInfo{indexPageNumber=" + this.indexPageNumber + ", firstPageNumber=" + this.firstPageNumber + ", totalPageNumber=" + this.totalPageNumber + "}";
    }
}
