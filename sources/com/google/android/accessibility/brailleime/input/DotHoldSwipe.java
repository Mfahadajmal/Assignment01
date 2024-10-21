package com.google.android.accessibility.brailleime.input;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.input.Swipe;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DotHoldSwipe implements Gesture {
    private final Swipe.Direction direction;
    private final BrailleCharacter heldBrailleCharacter;
    private final int touchCount;

    public DotHoldSwipe(Swipe.Direction direction, int i, BrailleCharacter brailleCharacter) {
        this.direction = direction;
        this.touchCount = i;
        this.heldBrailleCharacter = brailleCharacter;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DotHoldSwipe)) {
            return false;
        }
        DotHoldSwipe dotHoldSwipe = (DotHoldSwipe) obj;
        if (!this.direction.equals(dotHoldSwipe.direction) || this.touchCount != dotHoldSwipe.touchCount || !this.heldBrailleCharacter.equals(dotHoldSwipe.heldBrailleCharacter)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        Swipe.Direction direction = this.direction;
        int i = 0;
        if (direction == null) {
            hashCode = 0;
        } else {
            hashCode = direction.hashCode();
        }
        BrailleCharacter brailleCharacter = this.heldBrailleCharacter;
        if (brailleCharacter != null) {
            i = brailleCharacter.hashCode();
        }
        return ((((hashCode + 481) * 37) + i) * 37) + this.touchCount;
    }

    @Override // com.google.android.accessibility.brailleime.input.Gesture
    public final Gesture mirrorDots() {
        byte b = this.heldBrailleCharacter.toByte();
        int i = b << 3;
        int i2 = b >> 3;
        int i3 = b + b;
        return new DotHoldSwipe(this.direction, this.touchCount, new BrailleCharacter((byte) (((b >> 1) & 64) | (i & 56) | (i2 & 7) | ((byte) (i3 & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE)))));
    }
}
