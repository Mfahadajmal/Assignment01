package com.google.android.accessibility.talkback.compositor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MagnificationState {
    public final float currentScale;
    public final Integer mode;
    public final int state;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public float currentScale;
        public Integer mode;
        public byte set$0;
        public int state;

        public Builder() {
        }

        public final void setCurrentScale$ar$ds(float f) {
            this.currentScale = f;
            this.set$0 = (byte) (this.set$0 | 1);
        }

        public final void setState$ar$ds(int i) {
            this.state = i;
            this.set$0 = (byte) (this.set$0 | 2);
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public MagnificationState() {
    }

    public final float currentScale() {
        return this.currentScale;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MagnificationState) {
            MagnificationState magnificationState = (MagnificationState) obj;
            Integer num = this.mode;
            if (num != null ? num.equals(magnificationState.mode()) : magnificationState.mode() == null) {
                if (Float.floatToIntBits(this.currentScale) == Float.floatToIntBits(magnificationState.currentScale()) && this.state == magnificationState.state()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        Integer num = this.mode;
        if (num == null) {
            hashCode = 0;
        } else {
            hashCode = num.hashCode();
        }
        return ((((hashCode ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.currentScale)) * 1000003) ^ this.state;
    }

    public final Integer mode() {
        return this.mode;
    }

    public final int state() {
        return this.state;
    }

    public final String toString() {
        return "MagnificationState{mode=" + this.mode + ", currentScale=" + this.currentScale + ", state=" + this.state + "}";
    }

    public MagnificationState(Integer num, float f, int i) {
        this();
        this.mode = num;
        this.currentScale = f;
        this.state = i;
    }
}
