package com.google.android.accessibility.talkback.braille;

import android.content.SharedPreferences;
import com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.DimScreenActor;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.grpc.internal.RetryingNameResolver;
import j$.util.DesugarArrays;
import j$.util.Optional;
import j$.util.stream.Collectors;
import java.util.Arrays;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackForBrailleImeImpl implements TalkBackForBrailleIme {
    static final SelectorController.Setting[] VALID_CURSOR_GRANULARITIES;
    public static final Set VALID_GRANULARITIES;
    static final SelectorController.Setting[] VALID_NON_CURSOR_GRANULARITIES;
    public FloatingActionButton.ShadowDelegateImpl brailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final DimScreenActor dimScreenController;
    public final SharedPreferences prefs;
    public final TalkBackService.ProximitySensorListener proximitySensorListener;
    private final NodeMenuRuleCreator screenReaderActionPerformer$ar$class_merging$ar$class_merging;
    private final SelectorController selectorController;
    public final TalkBackService service;
    public final RetryingNameResolver.ResolutionResultListener talkBackPrivateMethodProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    static {
        int i = 4;
        int i2 = 0;
        SelectorController.Setting[] settingArr = {SelectorController.Setting.GRANULARITY_CHARACTERS, SelectorController.Setting.GRANULARITY_WORDS, SelectorController.Setting.GRANULARITY_LINES, SelectorController.Setting.GRANULARITY_PARAGRAPHS};
        VALID_CURSOR_GRANULARITIES = settingArr;
        SelectorController.Setting[] settingArr2 = {SelectorController.Setting.GRANULARITY_TYPO};
        VALID_NON_CURSOR_GRANULARITIES = settingArr2;
        Object[] copyOf = Arrays.copyOf(settingArr, 5);
        while (i2 <= 0) {
            copyOf[i] = settingArr2[i2];
            i2++;
            i++;
        }
        VALID_GRANULARITIES = (Set) DesugarArrays.stream((SelectorController.Setting[]) copyOf).collect(Collectors.toUnmodifiableSet());
    }

    public TalkBackForBrailleImeImpl(TalkBackService talkBackService, DimScreenActor dimScreenActor, TalkBackService.ProximitySensorListener proximitySensorListener, RetryingNameResolver.ResolutionResultListener resolutionResultListener, NodeMenuRuleCreator nodeMenuRuleCreator, SelectorController selectorController) {
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(talkBackService);
        this.service = talkBackService;
        this.dimScreenController = dimScreenActor;
        this.proximitySensorListener = proximitySensorListener;
        this.talkBackPrivateMethodProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.screenReaderActionPerformer$ar$class_merging$ar$class_merging = nodeMenuRuleCreator;
        this.selectorController = selectorController;
    }

    private final boolean isSwitchGranularityValid() {
        SelectorController.Setting[] settingArr = VALID_CURSOR_GRANULARITIES;
        int length = settingArr.length;
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            if (this.selectorController.isSettingAvailable(settingArr[i2])) {
                i++;
            }
        }
        SelectorController.Setting[] settingArr2 = VALID_NON_CURSOR_GRANULARITIES;
        int length2 = settingArr2.length;
        int i3 = 0;
        for (char c = 0; c <= 0; c = 1) {
            if (this.selectorController.isSettingAvailable(settingArr2[0])) {
                i3++;
            }
        }
        if (i <= 0 || i3 + i < 2) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme
    public final int getServiceStatus$ar$edu() {
        if (TalkBackService.isServiceActive()) {
            return 1;
        }
        return 2;
    }

    @Override // com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme
    public final void interruptSpeak() {
        this.service.interruptAllFeedback$ar$ds$404beace_1();
    }

    @Override // com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme
    public final boolean isCurrentGranularityTypoCorrection() {
        if (SelectorController.getCurrentSetting(this.service) == SelectorController.Setting.GRANULARITY_TYPO && isSwitchGranularityValid()) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme
    public final boolean performAction$ar$edu$3bc9316c_0(int i, Object... objArr) {
        return this.screenReaderActionPerformer$ar$class_merging$ar$class_merging.performAction$ar$edu(i, 5, objArr);
    }

    public final void performMovingCursor(boolean z) {
        Logger logger = Performance.DEFAULT_LOGGER;
        this.selectorController.adjustSelectedSetting(null, z);
    }

    @Override // com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme
    public final void resetGranularity() {
        if (SelectorController.getCurrentSetting(this.service) == SelectorController.Setting.GRANULARITY_CHARACTERS) {
            return;
        }
        this.selectorController.selectSetting$ar$edu$ar$ds(SelectorController.Setting.GRANULARITY_CHARACTERS, 1);
    }

    @Override // com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme
    public final boolean shouldUseCharacterGranularity() {
        if (SelectorController.getCurrentSetting(this.service) != SelectorController.Setting.GRANULARITY_CHARACTERS && isSwitchGranularityValid()) {
            return false;
        }
        return true;
    }

    public final boolean switchGranularity(boolean z) {
        if (!isSwitchGranularityValid()) {
            return false;
        }
        SelectorController.Setting currentSetting = SelectorController.getCurrentSetting(this.service);
        do {
            SelectorController selectorController = this.selectorController;
            Logger logger = Performance.DEFAULT_LOGGER;
            Optional nextOrPreviousSetting = selectorController.getNextOrPreviousSetting(z);
            if (!nextOrPreviousSetting.isEmpty()) {
                selectorController.setCurrentSetting$ar$edu(null, (SelectorController.Setting) nextOrPreviousSetting.get(), 2, false);
            }
            if (VALID_GRANULARITIES.contains(SelectorController.getCurrentSetting(this.service))) {
                return true;
            }
        } while (currentSetting != SelectorController.getCurrentSetting(this.service));
        return true;
    }
}
