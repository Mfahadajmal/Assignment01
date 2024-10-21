package com.google.android.play.core.splitcompat;

import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SplitFileInfo {
    private final File splitFile;
    private final String splitId;

    public SplitFileInfo() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SplitFileInfo) {
            SplitFileInfo splitFileInfo = (SplitFileInfo) obj;
            if (this.splitFile.equals(splitFileInfo.splitFile()) && this.splitId.equals(splitFileInfo.splitId())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.splitFile.hashCode() ^ 1000003) * 1000003) ^ this.splitId.hashCode();
    }

    public final File splitFile() {
        return this.splitFile;
    }

    public final String splitId() {
        return this.splitId;
    }

    public final String toString() {
        return "SplitFileInfo{splitFile=" + this.splitFile.toString() + ", splitId=" + this.splitId + "}";
    }

    public SplitFileInfo(File file, String str) {
        this();
        if (file == null) {
            throw new NullPointerException("Null splitFile");
        }
        this.splitFile = file;
        if (str != null) {
            this.splitId = str;
            return;
        }
        throw new NullPointerException("Null splitId");
    }
}
