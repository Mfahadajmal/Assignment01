package com.google.android.accessibility.selecttospeak.debug;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableRect {
    private final int bottom;
    private final int left;
    private final int right;
    private final int top;

    public SerializableRect(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableRect)) {
            return false;
        }
        SerializableRect serializableRect = (SerializableRect) obj;
        if (this.left == serializableRect.left && this.top == serializableRect.top && this.right == serializableRect.right && this.bottom == serializableRect.bottom) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    public final String toString() {
        return "SerializableRect(left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ")";
    }
}
