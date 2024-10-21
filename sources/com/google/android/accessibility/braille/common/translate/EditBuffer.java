package com.google.android.accessibility.braille.common.translate;

import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface EditBuffer {
    String appendBraille$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, BrailleCharacter brailleCharacter);

    void appendNewline$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    void appendSpace$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    void commit$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    void deleteCharacterBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    void deleteWord$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    BrailleDisplayForBrailleIme.ResultForDisplay.HoldingsInfo getHoldingsInfo$ar$ds();

    boolean moveCursorBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    boolean moveCursorForward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    boolean moveCursorToBeginning$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    boolean moveCursorToEnd$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);

    boolean moveHoldingsCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i);

    boolean moveTextFieldCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i);

    boolean selectAllText$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper);
}
