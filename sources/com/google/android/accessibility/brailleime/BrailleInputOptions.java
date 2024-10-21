package com.google.android.accessibility.brailleime;

import _COROUTINE._BOUNDARY;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleInputOptions {
    public final int brailleType$ar$edu;
    public final boolean reverseDots;
    public final boolean tutorialMode;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private int brailleType$ar$edu;
        private boolean reverseDots;
        private byte set$0;
        private boolean tutorialMode;

        public Builder() {
        }

        public final BrailleInputOptions build() {
            int i;
            if (this.set$0 == 3 && (i = this.brailleType$ar$edu) != 0) {
                return new BrailleInputOptions(this.tutorialMode, this.reverseDots, i);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" tutorialMode");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" reverseDots");
            }
            if (this.brailleType$ar$edu == 0) {
                sb.append(" brailleType");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final void setBrailleType$ar$edu$ar$ds(int i) {
            this.brailleType$ar$edu = i;
        }

        public final void setReverseDots$ar$ds(boolean z) {
            this.reverseDots = z;
            this.set$0 = (byte) (this.set$0 | 2);
        }

        public final void setTutorialMode$ar$ds(boolean z) {
            this.tutorialMode = z;
            this.set$0 = (byte) (this.set$0 | 1);
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public BrailleInputOptions() {
    }

    public static Builder builder() {
        Builder builder = new Builder(null);
        builder.setTutorialMode$ar$ds(false);
        builder.setBrailleType$ar$edu$ar$ds(6);
        builder.setReverseDots$ar$ds(false);
        return builder;
    }

    public final int brailleType$ar$edu$77dc1e4d_0() {
        return this.brailleType$ar$edu;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BrailleInputOptions) {
            BrailleInputOptions brailleInputOptions = (BrailleInputOptions) obj;
            if (this.tutorialMode == brailleInputOptions.tutorialMode() && this.reverseDots == brailleInputOptions.reverseDots()) {
                int i = this.brailleType$ar$edu;
                int brailleType$ar$edu$77dc1e4d_0 = brailleInputOptions.brailleType$ar$edu$77dc1e4d_0();
                if (i != 0) {
                    if (i == brailleType$ar$edu$77dc1e4d_0) {
                        return true;
                    }
                } else {
                    throw null;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = 1237;
        if (true != this.tutorialMode) {
            i = 1237;
        } else {
            i = 1231;
        }
        if (true == this.reverseDots) {
            i2 = 1231;
        }
        return ((((i ^ 1000003) * 1000003) ^ i2) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.brailleType$ar$edu);
    }

    public final boolean reverseDots() {
        return this.reverseDots;
    }

    public final String toString() {
        String str;
        int i = this.brailleType$ar$edu;
        if (i != 6) {
            if (i != 8) {
                str = "null";
            } else {
                str = "EIGHT_DOT";
            }
        } else {
            str = "SIX_DOT";
        }
        boolean z = this.reverseDots;
        return "BrailleInputOptions{tutorialMode=" + this.tutorialMode + ", reverseDots=" + z + ", brailleType=" + str + "}";
    }

    public final boolean tutorialMode() {
        return this.tutorialMode;
    }

    public BrailleInputOptions(boolean z, boolean z2, int i) {
        this();
        this.tutorialMode = z;
        this.reverseDots = z2;
        this.brailleType$ar$edu = i;
    }
}
