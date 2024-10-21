package com.google.android.accessibility.braille.interfaces;

import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface BrailleDisplayForBrailleIme {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ResultForDisplay {
        public final String action;
        public final String hint;
        public final HoldingsInfo holdingsInfo;
        public final boolean isMultiLine;
        public final CharSequence onScreenText;
        public final boolean showPassword;
        public final SelectionRange textSelection;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public String action;
            public String hint;
            public HoldingsInfo holdingsInfo;
            public boolean isMultiLine;
            public CharSequence onScreenText;
            public byte set$0;
            public boolean showPassword;
            public SelectionRange textSelection;

            public Builder() {
            }

            public final void setAction$ar$ds(String str) {
                if (str != null) {
                    this.action = str;
                    return;
                }
                throw new NullPointerException("Null action");
            }

            public final void setHint$ar$ds(String str) {
                if (str != null) {
                    this.hint = str;
                    return;
                }
                throw new NullPointerException("Null hint");
            }

            public final void setHoldingsInfo$ar$ds(HoldingsInfo holdingsInfo) {
                if (holdingsInfo != null) {
                    this.holdingsInfo = holdingsInfo;
                    return;
                }
                throw new NullPointerException("Null holdingsInfo");
            }

            public final void setIsMultiLine$ar$ds(boolean z) {
                this.isMultiLine = z;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public final void setOnScreenText$ar$ds(CharSequence charSequence) {
                if (charSequence != null) {
                    this.onScreenText = charSequence;
                    return;
                }
                throw new NullPointerException("Null onScreenText");
            }

            public final void setShowPassword$ar$ds(boolean z) {
                this.showPassword = z;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class HoldingsInfo {
            public final ByteBuffer holdings;
            public final int position;

            public HoldingsInfo() {
            }

            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof HoldingsInfo) {
                    HoldingsInfo holdingsInfo = (HoldingsInfo) obj;
                    if (this.holdings.equals(holdingsInfo.holdings()) && this.position == holdingsInfo.position()) {
                        return true;
                    }
                }
                return false;
            }

            public final int hashCode() {
                return ((this.holdings.hashCode() ^ 1000003) * 1000003) ^ this.position;
            }

            public final ByteBuffer holdings() {
                return this.holdings;
            }

            public final int position() {
                return this.position;
            }

            public final String toString() {
                return "HoldingsInfo{holdings=" + this.holdings.toString() + ", position=" + this.position + "}";
            }

            public HoldingsInfo(ByteBuffer byteBuffer, int i) {
                this();
                if (byteBuffer == null) {
                    throw new NullPointerException("Null holdings");
                }
                this.holdings = byteBuffer;
                this.position = i;
            }
        }

        public ResultForDisplay() {
        }

        public final String action() {
            return this.action;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ResultForDisplay) {
                ResultForDisplay resultForDisplay = (ResultForDisplay) obj;
                if (this.onScreenText.equals(resultForDisplay.onScreenText()) && this.textSelection.equals(resultForDisplay.textSelection()) && this.holdingsInfo.equals(resultForDisplay.holdingsInfo()) && this.isMultiLine == resultForDisplay.isMultiLine() && this.hint.equals(resultForDisplay.hint()) && this.action.equals(resultForDisplay.action()) && this.showPassword == resultForDisplay.showPassword()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int hashCode = ((((this.onScreenText.hashCode() ^ 1000003) * 1000003) ^ this.textSelection.hashCode()) * 1000003) ^ this.holdingsInfo.hashCode();
            int i2 = 1237;
            if (true != this.isMultiLine) {
                i = 1237;
            } else {
                i = 1231;
            }
            int hashCode2 = ((((((hashCode * 1000003) ^ i) * 1000003) ^ this.hint.hashCode()) * 1000003) ^ this.action.hashCode()) * 1000003;
            if (true == this.showPassword) {
                i2 = 1231;
            }
            return hashCode2 ^ i2;
        }

        public final String hint() {
            return this.hint;
        }

        public final HoldingsInfo holdingsInfo() {
            return this.holdingsInfo;
        }

        public final boolean isMultiLine() {
            return this.isMultiLine;
        }

        public final CharSequence onScreenText() {
            return this.onScreenText;
        }

        public final boolean showPassword() {
            return this.showPassword;
        }

        public final SelectionRange textSelection() {
            return this.textSelection;
        }

        public final String toString() {
            HoldingsInfo holdingsInfo = this.holdingsInfo;
            SelectionRange selectionRange = this.textSelection;
            return "ResultForDisplay{onScreenText=" + String.valueOf(this.onScreenText) + ", textSelection=" + String.valueOf(selectionRange) + ", holdingsInfo=" + String.valueOf(holdingsInfo) + ", isMultiLine=" + this.isMultiLine + ", hint=" + this.hint + ", action=" + this.action + ", showPassword=" + this.showPassword + "}";
        }

        public ResultForDisplay(CharSequence charSequence, SelectionRange selectionRange, HoldingsInfo holdingsInfo, boolean z, String str, String str2, boolean z2) {
            this();
            this.onScreenText = charSequence;
            this.textSelection = selectionRange;
            this.holdingsInfo = holdingsInfo;
            this.isMultiLine = z;
            this.hint = str;
            this.action = str2;
            this.showPassword = z2;
        }
    }

    boolean isBrailleDisplayConnectedAndNotSuspended();

    void onImeVisibilityChanged(boolean z);

    void showOnDisplay(ResultForDisplay resultForDisplay);
}
