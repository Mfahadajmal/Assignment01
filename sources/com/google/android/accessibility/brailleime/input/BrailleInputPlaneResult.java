package com.google.android.accessibility.brailleime.input;

import com.google.android.accessibility.braille.interfaces.BrailleCharacter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleInputPlaneResult {
    BrailleCharacter heldBrailleCharacter;
    boolean isLeft;
    public int pointersHeldCount;
    BrailleCharacter releasedBrailleCharacter;
    Swipe swipe;
    public int type;

    public static BrailleInputPlaneResult createCalibration(boolean z, int i) {
        BrailleInputPlaneResult brailleInputPlaneResult = new BrailleInputPlaneResult();
        brailleInputPlaneResult.type = 2;
        brailleInputPlaneResult.pointersHeldCount = i;
        brailleInputPlaneResult.isLeft = z;
        return brailleInputPlaneResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BrailleInputPlaneResult createDotHoldAndDotSwipe(Swipe swipe, BrailleCharacter brailleCharacter) {
        BrailleInputPlaneResult brailleInputPlaneResult = new BrailleInputPlaneResult();
        brailleInputPlaneResult.type = 4;
        brailleInputPlaneResult.heldBrailleCharacter = brailleCharacter;
        brailleInputPlaneResult.swipe = swipe;
        return brailleInputPlaneResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BrailleInputPlaneResult createSwipe(Swipe swipe) {
        BrailleInputPlaneResult brailleInputPlaneResult = new BrailleInputPlaneResult();
        brailleInputPlaneResult.type = 1;
        brailleInputPlaneResult.swipe = swipe;
        return brailleInputPlaneResult;
    }

    public static BrailleInputPlaneResult createTapAndRelease(BrailleCharacter brailleCharacter) {
        BrailleInputPlaneResult brailleInputPlaneResult = new BrailleInputPlaneResult();
        brailleInputPlaneResult.type = 0;
        brailleInputPlaneResult.releasedBrailleCharacter = brailleCharacter;
        return brailleInputPlaneResult;
    }

    public final String toString() {
        return "BrailleInputPlaneResult{type=" + this.type + ", releasedBrailleCharacter=" + String.valueOf(this.releasedBrailleCharacter) + ", swipe=" + String.valueOf(this.swipe) + ", heldBrailleCharacter=" + String.valueOf(this.heldBrailleCharacter) + "}";
    }
}
