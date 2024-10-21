package com.google.android.accessibility.braille.brailledisplay.controller;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import googledata.experiments.mobile.accessibility_suite.features.BrailleDisplayConfig;
import j$.util.Collection;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditorConsumer implements EventConsumer {
    private final FloatingActionButton.ShadowDelegateImpl behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context context;

    public EditorConsumer(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.context = context;
        this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.triggerUpdateDisplay();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onDeactivate() {
        this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.onFocusCleared();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final boolean onMappedInputEvent(BrailleInputEvent brailleInputEvent) {
        int command = brailleInputEvent.getCommand();
        if (command != 1) {
            if (command != 2) {
                if (command != 20) {
                    if (command != 50) {
                        if (command != 60) {
                            if (command != 128) {
                                if (command != 129) {
                                    switch (command) {
                                        case 7:
                                            break;
                                        case 8:
                                            break;
                                        case 9:
                                            return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveCursorBackward();
                                        case 10:
                                            return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveCursorForward();
                                        case 11:
                                            this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveCursorBackwardByWord$ar$ds();
                                            return true;
                                        case 12:
                                            return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveCursorForwardByWord();
                                        default:
                                            switch (command) {
                                                case 42:
                                                    if (!BrailleDisplayConfig.selectAll(this.context)) {
                                                        return true;
                                                    }
                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectAllText();
                                                case 43:
                                                    if (!BrailleDisplayConfig.cutCopyPaste(this.context)) {
                                                        return true;
                                                    }
                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.cutSelectedText();
                                                case 44:
                                                    if (!BrailleDisplayConfig.cutCopyPaste(this.context)) {
                                                        return true;
                                                    }
                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.copySelectedText();
                                                case 45:
                                                    if (!BrailleDisplayConfig.cutCopyPaste(this.context)) {
                                                        return true;
                                                    }
                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.pasteSelectedText();
                                                case 46:
                                                    if (!BrailleDisplayConfig.selectCurrentToStartOrEnd(this.context)) {
                                                        return true;
                                                    }
                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectCurrentToStart();
                                                case 47:
                                                    if (!BrailleDisplayConfig.selectCurrentToStartOrEnd(this.context)) {
                                                        return true;
                                                    }
                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectCurrentToEnd();
                                                default:
                                                    switch (command) {
                                                        case 70:
                                                            break;
                                                        case 71:
                                                            this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.deleteBackward$ar$ds();
                                                            return true;
                                                        case 72:
                                                            this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.deleteWordBackward$ar$ds();
                                                            return true;
                                                        default:
                                                            switch (command) {
                                                                case 77:
                                                                    if (!BrailleDisplayConfig.selectPreviousNextCharacterWordLine(this.context)) {
                                                                        return true;
                                                                    }
                                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectPreviousCharacter();
                                                                case 78:
                                                                    if (!BrailleDisplayConfig.selectPreviousNextCharacterWordLine(this.context)) {
                                                                        return true;
                                                                    }
                                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectNextCharacter();
                                                                case 79:
                                                                    if (!BrailleDisplayConfig.selectPreviousNextCharacterWordLine(this.context)) {
                                                                        return true;
                                                                    }
                                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectPreviousWord();
                                                                case 80:
                                                                    if (!BrailleDisplayConfig.selectPreviousNextCharacterWordLine(this.context)) {
                                                                        return true;
                                                                    }
                                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectNextWord();
                                                                case 81:
                                                                    if (!BrailleDisplayConfig.selectPreviousNextCharacterWordLine(this.context)) {
                                                                        return true;
                                                                    }
                                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectPreviousLine();
                                                                case 82:
                                                                    if (!BrailleDisplayConfig.selectPreviousNextCharacterWordLine(this.context)) {
                                                                        return true;
                                                                    }
                                                                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.selectNextLine();
                                                                default:
                                                                    BrailleKeyBindingUtils.SupportedCommand supportedCommand = (BrailleKeyBindingUtils.SupportedCommand) Collection.EL.stream(BrailleKeyBindingUtils.getSupportedCommands(this.context)).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(brailleInputEvent, 0)).findFirst().orElse(null);
                                                                    if (supportedCommand == null) {
                                                                        return false;
                                                                    }
                                                                    BrailleCharacter pressDot = supportedCommand.getPressDot();
                                                                    if (pressDot != null && !pressDot.equals(BrailleCharacter.EMPTY_CELL) && supportedCommand.hasSpace()) {
                                                                        return false;
                                                                    }
                                                                    this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.sendBrailleDots$ar$ds(pressDot.toInt());
                                                                    return true;
                                                            }
                                                    }
                                            }
                                    }
                                }
                                return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveToEnd();
                            }
                            return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveToBeginning();
                        }
                        this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.sendBrailleDots$ar$ds(brailleInputEvent.getArgument());
                        return true;
                    }
                    return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveCursor(brailleInputEvent.getArgument());
                }
                return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.performEnterKeyAction();
            }
            return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveCursorForwardByLine();
        }
        return this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.moveCursorBackwardByLine();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onActivate() {
    }
}
