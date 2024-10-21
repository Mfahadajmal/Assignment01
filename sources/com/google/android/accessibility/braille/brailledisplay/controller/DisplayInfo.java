package com.google.android.accessibility.braille.brailledisplay.controller;

import _COROUTINE._BOUNDARY;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DisplayInfo {
    public final boolean blink;
    public final ByteBuffer displayedBraille;
    public final ImmutableList displayedBrailleToTextPositions;
    public final ByteBuffer displayedOverlaidBraille;
    public final CharSequence displayedText;
    public final int source$ar$edu;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public boolean blink;
        public ByteBuffer displayedBraille;
        public ImmutableList displayedBrailleToTextPositions;
        public ByteBuffer displayedOverlaidBraille;
        public CharSequence displayedText;
        public byte set$0;
        public int source$ar$edu;

        public Builder() {
        }

        public Builder(byte[] bArr) {
            this();
        }
    }

    public DisplayInfo() {
    }

    public final boolean blink() {
        return this.blink;
    }

    public final ByteBuffer displayedBraille() {
        return this.displayedBraille;
    }

    public final ImmutableList displayedBrailleToTextPositions() {
        return this.displayedBrailleToTextPositions;
    }

    public final ByteBuffer displayedOverlaidBraille() {
        return this.displayedOverlaidBraille;
    }

    public final CharSequence displayedText() {
        return this.displayedText;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DisplayInfo) {
            DisplayInfo displayInfo = (DisplayInfo) obj;
            if (this.displayedBraille.equals(displayInfo.displayedBraille()) && this.displayedOverlaidBraille.equals(displayInfo.displayedOverlaidBraille()) && this.displayedText.equals(displayInfo.displayedText()) && ContextDataProvider.equalsImpl(this.displayedBrailleToTextPositions, displayInfo.displayedBrailleToTextPositions()) && this.blink == displayInfo.blink()) {
                int i = this.source$ar$edu;
                int source$ar$edu$ea4c7884_0 = displayInfo.source$ar$edu$ea4c7884_0();
                if (i != 0) {
                    if (i == source$ar$edu$ea4c7884_0) {
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
        int hashCode = ((((((this.displayedBraille.hashCode() ^ 1000003) * 1000003) ^ this.displayedOverlaidBraille.hashCode()) * 1000003) ^ this.displayedText.hashCode()) * 1000003) ^ this.displayedBrailleToTextPositions.hashCode();
        if (true != this.blink) {
            i = 1237;
        } else {
            i = 1231;
        }
        return (((hashCode * 1000003) ^ i) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.source$ar$edu);
    }

    public final int source$ar$edu$ea4c7884_0() {
        return this.source$ar$edu;
    }

    public final String toString() {
        String str;
        int i = this.source$ar$edu;
        ImmutableList immutableList = this.displayedBrailleToTextPositions;
        CharSequence charSequence = this.displayedText;
        ByteBuffer byteBuffer = this.displayedOverlaidBraille;
        String valueOf = String.valueOf(this.displayedBraille);
        String valueOf2 = String.valueOf(byteBuffer);
        String valueOf3 = String.valueOf(charSequence);
        String valueOf4 = String.valueOf(immutableList);
        if (i != 1) {
            if (i != 2) {
                str = "null";
            } else {
                str = "IME";
            }
        } else {
            str = "DEFAULT";
        }
        return "DisplayInfo{displayedBraille=" + valueOf + ", displayedOverlaidBraille=" + valueOf2 + ", displayedText=" + valueOf3 + ", displayedBrailleToTextPositions=" + valueOf4 + ", blink=" + this.blink + ", source=" + str + "}";
    }

    public DisplayInfo(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, CharSequence charSequence, ImmutableList immutableList, boolean z, int i) {
        this();
        this.displayedBraille = byteBuffer;
        this.displayedOverlaidBraille = byteBuffer2;
        this.displayedText = charSequence;
        this.displayedBrailleToTextPositions = immutableList;
        this.blink = z;
        this.source$ar$edu = i;
    }
}
