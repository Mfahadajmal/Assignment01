package com.google.android.accessibility.braille.common.translate;

import android.support.v7.app.AppCompatDelegate;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferStub implements EditBuffer {
    private final BrailleTranslator translator;

    public EditBufferStub(BrailleTranslator brailleTranslator) {
        this.translator = brailleTranslator;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final String appendBraille$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, BrailleCharacter brailleCharacter) {
        String translateToPrint = this.translator.translateToPrint(new BrailleWord(brailleCharacter));
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText(translateToPrint, 1);
        return translateToPrint;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void appendNewline$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.finishComposingText();
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText("\n", 1);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void appendSpace$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.finishComposingText();
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText(" ", 1);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void deleteCharacterBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        AppCompatDelegate.Api33Impl.performKeyAction$ar$ds(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void deleteWord$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        int lastWordLengthForDeletion = AppCompatDelegate.Api33Impl.getLastWordLengthForDeletion(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.getTextBeforeCursor(50, 0));
        if (lastWordLengthForDeletion > 0) {
            phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.deleteSurroundingText(lastWordLengthForDeletion, 0);
        }
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final BrailleDisplayForBrailleIme.ResultForDisplay.HoldingsInfo getHoldingsInfo$ar$ds() {
        return null;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        return true;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorForward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        return true;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorToBeginning$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        return true;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorToEnd$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        return true;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final /* synthetic */ boolean moveHoldingsCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i) {
        return false;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveTextFieldCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i) {
        return true;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean selectAllText$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        return false;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void commit$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
    }
}
