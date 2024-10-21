package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.utils.output.SpeechCleanupUtils;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import j$.util.Optional;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EditBufferUnContracted implements EditBuffer {
    private final Context context;
    protected final BrailleWord holdings = new BrailleWord();
    protected int lastCommitIndexOfHoldings = -1;
    private final OnDeviceTextDetectionLoadLogEvent talkBack$ar$class_merging$ar$class_merging$ar$class_merging;
    private final BrailleTranslator translator;

    public EditBufferUnContracted(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        this.context = context;
        this.translator = brailleTranslator;
        this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceTextDetectionLoadLogEvent;
    }

    private final Optional getAppendBrailleTextToSpeak(Resources resources, BrailleCharacter brailleCharacter) {
        if (brailleCharacter.equals(getNumeric())) {
            return Optional.of(resources.getString(R.string.number_announcement));
        }
        if (brailleCharacter.equals(getCapitalize())) {
            return Optional.of(resources.getString(R.string.capitalize_announcement));
        }
        return Optional.empty();
    }

    private final boolean holdingsEndsWithPrefix() {
        if (!this.holdings.isEmpty()) {
            BrailleTranslator brailleTranslator = this.translator;
            BrailleWord brailleWord = this.holdings;
            if (brailleTranslator.translateToPrint(brailleWord).equals(brailleTranslator.translateToPrint(brailleWord.subword(0, brailleWord.size() - 1)))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v9, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public String appendBraille$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, BrailleCharacter brailleCharacter) {
        String str;
        String translateToPrint = this.translator.translateToPrint(this.holdings);
        this.holdings.append(brailleCharacter);
        String translateToPrint2 = this.translator.translateToPrint(this.holdings);
        if (translateToPrint2.startsWith(translateToPrint)) {
            str = translateToPrint2.substring(translateToPrint.length());
        } else {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            if (AppCompatDelegate.Api33Impl.isTextField((EditorInfo) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider) && !AppCompatDelegate.Api33Impl.isPasswordField((EditorInfo) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider)) {
                phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setComposingText(translateToPrint2, 1);
            } else {
                phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText(str, 1);
            }
            this.lastCommitIndexOfHoldings = this.holdings.size();
        }
        if (TextUtils.isEmpty(str) || !BrailleTranslateUtils.isPronounceable(str)) {
            str = (String) getAppendBrailleTextToSpeak(this.context.getResources(), brailleCharacter).orElse(null);
            if (TextUtils.isEmpty(str)) {
                str = BrailleTranslateUtils.getDotsText(this.context.getResources(), brailleCharacter);
            }
            if (AppCompatDelegateImpl.Api33Impl.shouldEmitPerCharacterFeedback$ar$class_merging$ar$class_merging(phenotypeProcessReaper)) {
                AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, str, 1);
                return str;
            }
        }
        return str;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void appendNewline$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (AppCompatDelegateImpl.Api33Impl.isMultiLineField(((EditorInfo) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).inputType)) {
            commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
            phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText("\n", 1);
        } else {
            Context context = this.context;
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, SpeechCleanupUtils.cleanUp(context, context.getString(R.string.new_line_not_supported)).toString(), 1);
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void appendSpace$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText(" ", 1);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void commit$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        this.holdings.clear();
        this.lastCommitIndexOfHoldings = -1;
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.finishComposingText();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public void deleteCharacterBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (holdingsEndsWithPrefix()) {
            BrailleWord brailleWord = this.holdings;
            int size = brailleWord.size() - 1;
            Context context = this.context;
            BrailleCharacter remove = brailleWord.remove(size);
            String str = (String) getAppendBrailleTextToSpeak(context.getResources(), remove).orElse(null);
            if (TextUtils.isEmpty(str)) {
                str = BrailleTranslateUtils.getDotsText(this.context.getResources(), remove);
            }
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, r3.getString(R.string.read_out_deleted, SpeechCleanupUtils.cleanUp(this.context, str).toString()), 1);
        } else {
            if (!this.holdings.isEmpty()) {
                this.holdings.remove(r0.size() - 1);
            }
            AppCompatDelegate.Api33Impl.performKeyAction$ar$ds(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable);
        }
        this.lastCommitIndexOfHoldings = this.holdings.size();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void deleteWord$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        while (holdingsEndsWithPrefix()) {
            this.holdings.remove(r0.size() - 1);
        }
        if (this.holdings.size() > 0) {
            this.holdings.clear();
            this.lastCommitIndexOfHoldings = -1;
            phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setComposingText("", 0);
        } else {
            int lastWordLengthForDeletion = AppCompatDelegate.Api33Impl.getLastWordLengthForDeletion(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.getTextBeforeCursor(50, 0));
            if (lastWordLengthForDeletion > 0) {
                phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.deleteSurroundingText(lastWordLengthForDeletion, 0);
            }
        }
    }

    protected BrailleCharacter getCapitalize() {
        return BrailleTranslateUtils.DOT6;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final BrailleDisplayForBrailleIme.ResultForDisplay.HoldingsInfo getHoldingsInfo$ar$ds() {
        BrailleWord brailleWord = new BrailleWord();
        int i = -1;
        if (!this.holdings.isEmpty()) {
            BrailleWord brailleWord2 = this.holdings;
            int i2 = this.lastCommitIndexOfHoldings;
            if (i2 == -1) {
                i2 = 0;
            }
            brailleWord = brailleWord2.subword(i2, brailleWord2.size());
        }
        ByteBuffer wrap = ByteBuffer.wrap(brailleWord.toByteArray());
        if (!brailleWord.isEmpty()) {
            i = brailleWord.size();
        }
        return new BrailleDisplayForBrailleIme.ResultForDisplay.HoldingsInfo(wrap, i);
    }

    protected BrailleCharacter getNumeric() {
        return BrailleTranslateUtils.DOTS3456;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        return false;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorForward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        return false;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorToBeginning$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        return phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setSelection(0, 0);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorToEnd$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        int length = AppCompatDelegateImpl.Api33Impl.getTextFieldText(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable).length();
        return phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setSelection(length, length);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final /* synthetic */ boolean moveHoldingsCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i) {
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveTextFieldCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i) {
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        if (i >= 0 && i <= AppCompatDelegateImpl.Api33Impl.getTextFieldText(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable).length()) {
            return phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setSelection(i, i);
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean selectAllText$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        String textFieldText = AppCompatDelegateImpl.Api33Impl.getTextFieldText(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable);
        if (!phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setSelection(0, textFieldText.length())) {
            return false;
        }
        AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, r4.getString(R.string.read_out_selected_text, SpeechCleanupUtils.cleanUp(this.context, textFieldText).toString()), 2);
        return true;
    }

    public final String toString() {
        return this.holdings.toString();
    }
}
