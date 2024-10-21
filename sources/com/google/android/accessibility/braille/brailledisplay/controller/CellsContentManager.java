package com.google.android.accessibility.braille.brailledisplay.controller;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.Spanned;
import android.util.Range;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.brailledisplay.controller.ContentHelper;
import com.google.android.accessibility.braille.brailledisplay.controller.TranslatorManager;
import com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.interfaces.SelectionRange;
import com.google.android.accessibility.braille.translate.TranslationResult;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription$$ExternalSyntheticLambda1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$BraillebackExtension;
import j$.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CellsContentManager {
    public Range actionRange;
    public DisplayInfoWrapper commonDisplayInfoWrapper;
    public final Context context;
    public WrapStrategy editingWrapStrategy;
    public Range holdingsRange;
    public final FloatingActionButton.ShadowDelegateImpl imeStatusProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl inputEventListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public List onScreenRange;
    public boolean overlaysOn;
    private boolean panUpOverflow;
    public WrapStrategy preferredWrapStrategy;
    public final AsyncInterceptorsClientCallListener.PendingMessage pulseHandler$ar$class_merging;
    public DisplayInfoWrapper timedMessageDisplayInfoWrapper;
    public final TimedMessager timedMessager;
    private final FloatingActionButton.ShadowDelegateImpl timedMessagerCallback$ar$class_merging$ar$class_merging$ar$class_merging;
    public final TranslatorManager translatorManager;
    public final List onDisplayContentChangeListeners = new ArrayList();
    public final ContentHelper.WrapStrategyRetriever wrapStrategyRetriever = new ContentHelper$$ExternalSyntheticLambda0(this, 1);
    public final TranslatorManager.OutputCodeChangedListener outputCodeChangedListener = new BrailleDisplaySettingsFragment.AnonymousClass7(this, 1);
    public final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 3);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Cursor {
        public final int position;
        public final int type$ar$edu;

        public Cursor() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Cursor) {
                Cursor cursor = (Cursor) obj;
                if (this.position == cursor.position() && this.type$ar$edu == cursor.type$ar$edu$75f59e1d_0()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.position ^ 1000003) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.type$ar$edu);
        }

        public final int position() {
            return this.position;
        }

        public final String toString() {
            String str;
            int i = this.type$ar$edu;
            if (i != 1) {
                if (i != 2) {
                    str = "ACTION";
                } else {
                    str = "HOLDINGS";
                }
            } else {
                str = "TEXT_FIELD";
            }
            return "Cursor{position=" + this.position + ", type=" + str + "}";
        }

        public final int type$ar$edu$75f59e1d_0() {
            return this.type$ar$edu;
        }

        public Cursor(int i, int i2) {
            this();
            this.position = i;
            this.type$ar$edu = i2;
        }
    }

    public CellsContentManager(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, TranslatorManager translatorManager, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2) {
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl3 = new FloatingActionButton.ShadowDelegateImpl(this);
        this.timedMessagerCallback$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl3;
        this.context = context;
        this.imeStatusProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.translatorManager = translatorManager;
        this.inputEventListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl2;
        this.pulseHandler$ar$class_merging = new AsyncInterceptorsClientCallListener.PendingMessage(new FloatingActionButton.ShadowDelegateImpl(this, null), BrailleUserPreferences.readBlinkingIntervalMs(context));
        this.timedMessager = new TimedMessager(shadowDelegateImpl3);
    }

    public final void cellOnDisplayContentChanged() {
        Iterator it = this.onDisplayContentChangeListeners.iterator();
        while (it.hasNext()) {
            ((FloatingActionButton.ShadowDelegateImpl) it.next()).onDisplayContentChanged();
        }
    }

    public final void clearTimedMessage() {
        TimedMessager timedMessager = this.timedMessager;
        timedMessager.timedMessageDisplaying = false;
        timedMessager.handler.removeCallbacksAndMessages(null);
        timedMessager.callback$ar$class_merging$8a5b80ac_0$ar$class_merging$ar$class_merging.onTimedMessageCleared();
    }

    public final AccessibilityNodeInfoCompat getAccessibilityNode(int i) {
        int i2;
        ContentHelper contentHelper = getCurrentDisplayInfoWrapper().contentHelper;
        int wholeContentIndex = contentHelper.toWholeContentIndex(i);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = null;
        if (wholeContentIndex != -1) {
            int displayToTextPosition = ContentHelper.displayToTextPosition(contentHelper.currentTranslationResult, wholeContentIndex);
            CharSequence text = contentHelper.currentTranslationResult.text();
            if (text instanceof Spanned) {
                Spanned spanned = (Spanned) text;
                AccessibilityNodeInfoCompat[] accessibilityNodeInfoCompatArr = (AccessibilityNodeInfoCompat[]) spanned.getSpans(displayToTextPosition, displayToTextPosition, AccessibilityNodeInfoCompat.class);
                if (accessibilityNodeInfoCompatArr.length != 0) {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = accessibilityNodeInfoCompatArr[0];
                    int i3 = 1;
                    accessibilityNodeInfoCompat = accessibilityNodeInfoCompat2;
                    int spanEnd = spanned.getSpanEnd(accessibilityNodeInfoCompat2) - spanned.getSpanStart(accessibilityNodeInfoCompat2);
                    while (i3 < accessibilityNodeInfoCompatArr.length) {
                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = accessibilityNodeInfoCompatArr[i3];
                        int spanEnd2 = spanned.getSpanEnd(accessibilityNodeInfoCompat3) - spanned.getSpanStart(accessibilityNodeInfoCompat3);
                        if (spanEnd2 < spanEnd) {
                            i2 = spanEnd2;
                        } else {
                            i2 = spanEnd;
                        }
                        if (spanEnd2 < spanEnd) {
                            accessibilityNodeInfoCompat = accessibilityNodeInfoCompat3;
                        }
                        i3++;
                        spanEnd = i2;
                    }
                }
            }
        }
        return accessibilityNodeInfoCompat;
    }

    public final DisplayInfoWrapper getCurrentDisplayInfoWrapper() {
        if (this.timedMessageDisplayInfoWrapper.hasDisplayInfo()) {
            return this.timedMessageDisplayInfoWrapper;
        }
        return this.commonDisplayInfoWrapper;
    }

    public final boolean panUp() {
        int indexOfKey;
        DisplayInfoWrapper currentDisplayInfoWrapper = getCurrentDisplayInfoWrapper();
        currentDisplayInfoWrapper.reachToEnd = false;
        if (!currentDisplayInfoWrapper.hasDisplayInfo() || !currentDisplayInfoWrapper.reachToBeginning) {
            ContentHelper contentHelper = currentDisplayInfoWrapper.contentHelper;
            int i = currentDisplayInfoWrapper.displayInfo.source$ar$edu;
            WrapStrategy wrapStrategy = contentHelper.wrapStrategyRetriever.getWrapStrategy();
            DisplayInfo displayInfo = null;
            if (wrapStrategy.isValid && (indexOfKey = wrapStrategy.lineBreaks.indexOfKey(wrapStrategy.displayStart)) > 0 && indexOfKey < wrapStrategy.lineBreaks.size()) {
                wrapStrategy.displayEnd = wrapStrategy.displayStart;
                wrapStrategy.displayStart = wrapStrategy.lineBreaks.keyAt(indexOfKey - 1);
                displayInfo = contentHelper.getDisplayInfo$ar$edu(contentHelper.currentTranslationResult.text(), wrapStrategy.getDisplayStart(), wrapStrategy.getDisplayEnd(), contentHelper.currentTranslationResult.brailleToTextPositions(), i);
            }
            if (displayInfo == null) {
                currentDisplayInfoWrapper.reachToBeginning = true;
            } else {
                currentDisplayInfoWrapper.displayInfo = displayInfo;
                currentDisplayInfoWrapper.reachToBeginning = false;
                refresh();
                cellOnDisplayContentChanged();
                return true;
            }
        }
        if (this.timedMessager.timedMessageDisplaying) {
            clearTimedMessage();
            return true;
        }
        this.panUpOverflow = true;
        return false;
    }

    public final void refresh() {
        byte[] array;
        DisplayInfo displayInfo = getCurrentDisplayInfoWrapper().displayInfo;
        if (displayInfo != null) {
            if (this.overlaysOn) {
                array = displayInfo.displayedOverlaidBraille.array();
            } else {
                array = displayInfo.displayedBraille.array();
            }
            this.inputEventListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.displayDots(array, displayInfo.displayedText, Collection.EL.stream(displayInfo.displayedBrailleToTextPositions).mapToInt(new NodePathDescription$$ExternalSyntheticLambda1(1)).toArray());
            if (displayInfo.blink) {
                AsyncInterceptorsClientCallListener.PendingMessage pendingMessage = this.pulseHandler$ar$class_merging;
                if (!((Handler) pendingMessage.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors).hasMessages(0)) {
                    ((Handler) pendingMessage.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors).sendEmptyMessageDelayed(0, pendingMessage.currentStage);
                    return;
                }
                return;
            }
            this.pulseHandler$ar$class_merging.cancelPulse();
            this.overlaysOn = true;
        }
    }

    public final void setContent(List list, Range range, Range range2, SelectionRange selectionRange, TranslationResult translationResult, boolean z) {
        int intValue;
        int intValue2;
        int intValue3;
        this.onScreenRange = list;
        this.holdingsRange = range;
        this.actionRange = range2;
        if (list.isEmpty()) {
            if (((Integer) range.getLower()).intValue() == -1) {
                intValue = Math.min(selectionRange.start, selectionRange.end);
            } else {
                intValue = ((Integer) range.getLower()).intValue();
            }
        } else {
            intValue = ((Integer) ((Range) list.get(0)).getLower()).intValue();
        }
        if (((Integer) range.getLower()).intValue() == -1) {
            intValue2 = Math.max(selectionRange.start, selectionRange.end);
        } else {
            intValue2 = ((Integer) range.getUpper()).intValue();
        }
        if (list.isEmpty()) {
            intValue3 = Math.max(selectionRange.start, selectionRange.end);
        } else {
            intValue3 = ((Integer) ((Range) ContextDataProvider.getLast(list)).getUpper()).intValue();
        }
        DisplayInfoWrapper displayInfoWrapper = this.commonDisplayInfoWrapper;
        displayInfoWrapper.displayInfo = displayInfoWrapper.contentHelper.generateDisplayInfo$ar$edu(1, selectionRange, intValue, Math.max(intValue2, intValue3), z, translationResult, 2);
        displayInfoWrapper.reset();
        this.panUpOverflow = false;
        refresh();
        cellOnDisplayContentChanged();
    }

    public final void setContent$ar$edu(CellsContent cellsContent, int i) {
        CharSequence charSequence = cellsContent.text;
        charSequence.getClass();
        this.commonDisplayInfoWrapper.renewDisplayInfo$ar$ds(charSequence, cellsContent.panStrategy);
        BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(this.context);
        int length = brailleDisplayAnalytics.readCount + this.commonDisplayInfoWrapper.displayInfo.displayedBraille.array().length;
        brailleDisplayAnalytics.readCount = length;
        brailleDisplayAnalytics.readCount = length % 200;
        for (int i2 = 0; i2 < length / 200; i2++) {
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
            builder.copyOnWrite();
            BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder.instance;
            braillebackLogProto$BraillebackExtension.bitField0_ |= 16;
            braillebackLogProto$BraillebackExtension.readRecord_ = 200;
            brailleDisplayAnalytics.sendLogs((BraillebackLogProto$BraillebackExtension) builder.build());
        }
        if (i == 2 && this.panUpOverflow) {
            do {
            } while (getCurrentDisplayInfoWrapper().panDown());
            refresh();
        } else {
            refresh();
        }
        cellOnDisplayContentChanged();
        this.panUpOverflow = false;
    }

    public final void setTimedContent(CellsContent cellsContent, int i) {
        cellsContent.text.getClass();
        TimedMessager timedMessager = this.timedMessager;
        timedMessager.timedMessageDisplaying = true;
        timedMessager.callback$ar$class_merging$8a5b80ac_0$ar$class_merging$ar$class_merging.onTimedMessageDisplayed(cellsContent);
        timedMessager.handler.removeCallbacksAndMessages(null);
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = timedMessager.callback$ar$class_merging$8a5b80ac_0$ar$class_merging$ar$class_merging;
        shadowDelegateImpl.getClass();
        timedMessager.handler.postDelayed(new WorkerKt$$ExternalSyntheticLambda0(shadowDelegateImpl, 10), i);
    }
}
