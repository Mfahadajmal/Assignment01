package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.utils.output.SpeechCleanupUtils;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class EditBufferContracted implements EditBuffer {
    private final Context context;
    private int holdingPosition;
    private final BrailleWord holdings = new BrailleWord();
    private final Map initialCharacterTranslationMap;
    private final Map nonInitialCharacterTranslationMap;
    private final OnDeviceTextDetectionLoadLogEvent talkBack$ar$class_merging$ar$class_merging$ar$class_merging;
    private final BrailleTranslator translator;

    public EditBufferContracted(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        HashMap hashMap = new HashMap();
        this.initialCharacterTranslationMap = hashMap;
        HashMap hashMap2 = new HashMap();
        this.nonInitialCharacterTranslationMap = hashMap2;
        this.holdingPosition = -1;
        this.context = context;
        this.translator = brailleTranslator;
        this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceTextDetectionLoadLogEvent;
        fillTranslatorMaps(hashMap, hashMap2);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    private final void clearHoldingsAndSendToEditor$ar$class_merging$4156e7fc_0$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, boolean z, String str) {
        CharSequence charSequence;
        if (this.holdings.isEmpty()) {
            if (!TextUtils.isEmpty(str)) {
                phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText(str, 1);
                return;
            }
            return;
        }
        if (z) {
            this.holdingPosition = this.holdings.size();
        }
        int i = 0;
        String translateToPrint = this.translator.translateToPrint(this.holdings.subword(0, this.holdingPosition));
        BrailleTranslator brailleTranslator = this.translator;
        BrailleWord brailleWord = this.holdings;
        String translateToPrint2 = brailleTranslator.translateToPrint(brailleWord.subword(this.holdingPosition, brailleWord.size()));
        ExtractedText extractedText = phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.getExtractedText(new ExtractedTextRequest(), 0);
        if (extractedText != null && extractedText.text != null && extractedText.selectionStart >= 0) {
            charSequence = extractedText.text.subSequence(0, extractedText.selectionStart);
        } else {
            charSequence = "";
        }
        if (!TextUtils.isEmpty(charSequence)) {
            i = charSequence.length();
        }
        phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText(translateToPrint + str + translateToPrint2, 1);
        moveTextFieldCursor$ar$class_merging$ar$class_merging(phenotypeProcessReaper, translateToPrint.length() + i + str.length());
        this.holdings.clear();
        this.holdingPosition = -1;
    }

    private final void clearHoldingsAndSendToEditor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, boolean z) {
        clearHoldingsAndSendToEditor$ar$class_merging$4156e7fc_0$ar$class_merging(phenotypeProcessReaper, z, "");
    }

    private final String getAnnouncement(Resources resources, BrailleTranslator brailleTranslator, BrailleWord brailleWord, int i) {
        String str;
        BrailleCharacter brailleCharacter = brailleWord.get(i);
        String nonInitialCharacterTranslation = getNonInitialCharacterTranslation(resources, brailleCharacter);
        if (i == 0) {
            nonInitialCharacterTranslation = getInitialCharacterTranslation(resources, brailleCharacter);
            i = 0;
        }
        if (nonInitialCharacterTranslation.isEmpty()) {
            String translateToPrint = brailleTranslator.translateToPrint(brailleWord.subword(0, i + 1));
            String translateToPrint2 = brailleTranslator.translateToPrint(brailleWord.subword(0, i));
            if (translateToPrint.startsWith(translateToPrint2)) {
                str = translateToPrint.substring(translateToPrint2.length());
            } else {
                str = "";
            }
            String str2 = str;
            if (str2.isEmpty() || isLetter(str2.charAt(0))) {
                if (brailleWord.size() > 1) {
                    return getInitialCharacterTranslation(resources, brailleCharacter);
                }
                return getNonInitialCharacterTranslation(resources, brailleCharacter);
            }
            return str2;
        }
        return nonInitialCharacterTranslation;
    }

    private final String getInitialCharacterTranslation(Resources resources, BrailleCharacter brailleCharacter) {
        String textToSpeak = getTextToSpeak(resources, brailleCharacter);
        if (forceInitialDefaultTranslation(brailleCharacter.toString())) {
            textToSpeak = BrailleTranslateUtils.getDotsText(resources, brailleCharacter);
        }
        if (TextUtils.isEmpty(textToSpeak)) {
            textToSpeak = ContextDataProvider.nullToEmpty((String) this.initialCharacterTranslationMap.get(brailleCharacter.toString()));
            if (TextUtils.isEmpty(textToSpeak)) {
                return BrailleTranslateUtils.getDotsText(resources, brailleCharacter);
            }
        }
        return textToSpeak;
    }

    private final String getNonInitialCharacterTranslation(Resources resources, BrailleCharacter brailleCharacter) {
        String textToSpeak = getTextToSpeak(resources, brailleCharacter);
        if (forceNonInitialDefaultTranslation(brailleCharacter.toString())) {
            textToSpeak = BrailleTranslateUtils.getDotsText(resources, brailleCharacter);
        }
        if (TextUtils.isEmpty(textToSpeak)) {
            return ContextDataProvider.nullToEmpty((String) this.nonInitialCharacterTranslationMap.get(brailleCharacter.toString()));
        }
        return textToSpeak;
    }

    private final String getTextToSpeak(Resources resources, BrailleCharacter brailleCharacter) {
        if (brailleCharacter.equals(getCapitalize())) {
            return resources.getString(R.string.capitalize_announcement);
        }
        if (brailleCharacter.equals(getNumeric())) {
            return resources.getString(R.string.number_announcement);
        }
        return "";
    }

    private static final String hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, String str, int i) {
        if (phenotypeProcessReaper.pollingMinutes != 1 && AppCompatDelegate.Api33Impl.isPasswordField((EditorInfo) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider)) {
            return ContextDataProvider.repeat$ar$ds(i);
        }
        return str;
    }

    private final void updateHoldingsPosition(BrailleCharacter brailleCharacter) {
        int i = this.holdingPosition;
        if (i != -1 && i != this.holdings.size()) {
            this.holdings.insert(this.holdingPosition, brailleCharacter);
            this.holdingPosition++;
        } else {
            this.holdings.append(brailleCharacter);
            this.holdingPosition = this.holdings.size();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final String appendBraille$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, BrailleCharacter brailleCharacter) {
        if (!TextUtils.isEmpty(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.getSelectedText(0))) {
            phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.commitText("", 1);
        }
        updateHoldingsPosition(brailleCharacter);
        String announcement = getAnnouncement(this.context.getResources(), this.translator, this.holdings, this.holdingPosition - 1);
        if (AppCompatDelegateImpl.Api33Impl.shouldEmitPerCharacterFeedback$ar$class_merging$ar$class_merging(phenotypeProcessReaper)) {
            String hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging = hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging(phenotypeProcessReaper, announcement, 1);
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, SpeechCleanupUtils.cleanUp(this.context, hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging).toString(), 1);
            return hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging;
        }
        return announcement;
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void appendNewline$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (AppCompatDelegateImpl.Api33Impl.isMultiLineField(((EditorInfo) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).inputType)) {
            clearHoldingsAndSendToEditor$ar$class_merging$4156e7fc_0$ar$class_merging(phenotypeProcessReaper, false, "\n");
        } else {
            Context context = this.context;
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, SpeechCleanupUtils.cleanUp(context, context.getString(R.string.new_line_not_supported)).toString(), 1);
        }
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void appendSpace$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        updateHoldingsPosition(new BrailleCharacter());
        clearHoldingsAndSendToEditor$ar$class_merging$ar$class_merging(phenotypeProcessReaper, false);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void commit$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        clearHoldingsAndSendToEditor$ar$class_merging$ar$class_merging(phenotypeProcessReaper, true);
    }

    /* JADX WARN: Type inference failed for: r6v2, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void deleteCharacterBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (this.holdings.isEmpty()) {
            AppCompatDelegate.Api33Impl.performKeyAction$ar$ds(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable);
            return;
        }
        int i = this.holdingPosition;
        if (i <= 0) {
            return;
        }
        this.holdingPosition = i - 1;
        String announcement = getAnnouncement(this.context.getResources(), this.translator, this.holdings, this.holdingPosition);
        this.holdings.remove(this.holdingPosition);
        if (this.holdings.isEmpty()) {
            this.holdingPosition = -1;
        }
        AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, r0.getString(R.string.read_out_deleted, SpeechCleanupUtils.cleanUp(this.context, hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging(phenotypeProcessReaper, announcement, 1)).toString()), 1);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v2, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void deleteWord$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (!this.holdings.isEmpty()) {
            ImmutableList.Builder builder = new ImmutableList.Builder();
            for (int i = 0; i < this.holdings.size(); i++) {
                builder.add$ar$ds$4f674a09_0(getAnnouncement(this.context.getResources(), this.translator, this.holdings, i));
            }
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, r2.getString(R.string.read_out_deleted, SpeechCleanupUtils.cleanUp(this.context, hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging(phenotypeProcessReaper, TextUtils.join(",", builder.build()), this.holdings.size())).toString()), 1);
            this.holdingPosition = -1;
            this.holdings.clear();
            phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setComposingText("", 0);
            return;
        }
        int lastWordLengthForDeletion = AppCompatDelegate.Api33Impl.getLastWordLengthForDeletion(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.getTextBeforeCursor(50, 0));
        if (lastWordLengthForDeletion > 0) {
            phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.deleteSurroundingText(lastWordLengthForDeletion, 0);
        }
    }

    protected abstract void fillTranslatorMaps(Map map, Map map2);

    protected abstract boolean forceInitialDefaultTranslation(String str);

    protected abstract boolean forceNonInitialDefaultTranslation(String str);

    protected abstract BrailleCharacter getCapitalize();

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final BrailleDisplayForBrailleIme.ResultForDisplay.HoldingsInfo getHoldingsInfo$ar$ds() {
        return new BrailleDisplayForBrailleIme.ResultForDisplay.HoldingsInfo(ByteBuffer.wrap(this.holdings.toByteArray()), this.holdingPosition);
    }

    protected abstract BrailleCharacter getNumeric();

    protected abstract boolean isLetter(char c);

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (this.holdings.isEmpty()) {
            return false;
        }
        return moveHoldingsCursor$ar$class_merging$ar$class_merging(phenotypeProcessReaper, this.holdingPosition - 1);
    }

    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveCursorForward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (this.holdings.isEmpty()) {
            return false;
        }
        return moveHoldingsCursor$ar$class_merging$ar$class_merging(phenotypeProcessReaper, this.holdingPosition + 1);
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

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveHoldingsCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i) {
        int i2;
        if (i >= 0 && i <= this.holdings.size()) {
            int i3 = this.holdingPosition;
            if (i3 > i) {
                i2 = i3;
                i3 = i;
            } else {
                i2 = i;
            }
            this.holdingPosition = i;
            Context context = this.context;
            BrailleTranslator brailleTranslator = this.translator;
            BrailleWord brailleWord = this.holdings;
            Resources resources = context.getResources();
            StringBuilder sb = new StringBuilder();
            for (int i4 = i3; i4 < i2; i4++) {
                sb.append(getAnnouncement(resources, brailleTranslator, brailleWord, i4));
            }
            String hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging = hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging(phenotypeProcessReaper, sb.toString(), i2 - i3);
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, SpeechCleanupUtils.cleanUp(this.context, hideTextForPasswordIfNecessary$ar$ds$ar$class_merging$ar$class_merging).toString(), 1);
            return true;
        }
        int i5 = AppCompatDelegate.Api33Impl.getTextSelection(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable).end;
        commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        if (i < 0) {
            return phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setSelection(i5, i5);
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean moveTextFieldCursor$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i) {
        if (i >= 0 && i <= AppCompatDelegateImpl.Api33Impl.getTextFieldText(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable).length()) {
            return phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setSelection(i, i);
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBuffer
    public final boolean selectAllText$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (!this.holdings.isEmpty()) {
            commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        }
        ?? r4 = phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable;
        String textFieldText = AppCompatDelegateImpl.Api33Impl.getTextFieldText(r4);
        if (!r4.setSelection(0, textFieldText.length())) {
            return false;
        }
        AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, r4.getString(R.string.read_out_selected_text, SpeechCleanupUtils.cleanUp(this.context, textFieldText).toString()), 2);
        return true;
    }
}
