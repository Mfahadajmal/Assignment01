package com.google.android.accessibility.braille.brailledisplay.controller;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.PowerManager;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityWindowInfo;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventManager implements EventConsumer {
    private final AutoScrollManager autoScrollManager;
    private final FloatingActionButton.ShadowDelegateImpl behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorNavigation$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorNodeText$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorScreenReader$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final CellsContentManager cellsContentConsumer$ar$class_merging;
    private final Context context;
    private EventConsumer currentConsumer;
    private final DefaultConsumer defaultConsumer;
    private final EditorConsumer editorConsumer;
    private final ApplicationModule feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging;
    private final PowerManager powerManager;
    private boolean windowActive;

    public EventManager(Context context, CellsContentManager cellsContentManager, ApplicationModule applicationModule, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl3, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl4, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl5, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl6) {
        this.context = context;
        this.cellsContentConsumer$ar$class_merging = cellsContentManager;
        this.autoScrollManager = new AutoScrollManager(context, shadowDelegateImpl6, applicationModule, shadowDelegateImpl5);
        this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.behaviorScreenReader$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl3;
        this.behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl2;
        this.behaviorNodeText$ar$class_merging$ar$class_merging = shadowDelegateImpl4;
        this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl5;
        this.behaviorNavigation$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl6;
        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
        DefaultConsumer defaultConsumer = new DefaultConsumer(context, cellsContentManager, applicationModule, shadowDelegateImpl4, shadowDelegateImpl2, shadowDelegateImpl3, shadowDelegateImpl5, shadowDelegateImpl);
        this.defaultConsumer = defaultConsumer;
        this.editorConsumer = new EditorConsumer(context, shadowDelegateImpl);
        this.currentConsumer = defaultConsumer;
        this.powerManager = (PowerManager) context.getSystemService("power");
        this.windowActive = shadowDelegateImpl.isOnscreenKeyboardActive();
    }

    public final void displayTimedMessage(String str) {
        if (this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging.isBrailleDisplayConnected()) {
            this.cellsContentConsumer$ar$class_merging.setTimedContent(new CellsContent(str), BrailleUserPreferences.getTimedMessageDurationInMillisecond(this.context, str.length()));
        }
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        boolean isOnscreenKeyboardActive;
        String string;
        AppCompatTextViewAutoSizeHelper.Api23Impl.v("EventManager", "isInteractive: " + this.powerManager.isInteractive());
        CharSequence charSequence = "";
        if (this.powerManager.isInteractive()) {
            if ((accessibilityEvent.getEventType() & 4302888) != 0) {
                if (this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.acceptInput()) {
                    EventConsumer eventConsumer = this.currentConsumer;
                    if (eventConsumer != this.editorConsumer) {
                        eventConsumer.onDeactivate();
                        EditorConsumer editorConsumer = this.editorConsumer;
                        this.currentConsumer = editorConsumer;
                        editorConsumer.onActivate();
                    }
                } else {
                    EventConsumer eventConsumer2 = this.currentConsumer;
                    if (eventConsumer2 != this.defaultConsumer) {
                        eventConsumer2.onDeactivate();
                        DefaultConsumer defaultConsumer = this.defaultConsumer;
                        this.currentConsumer = defaultConsumer;
                        defaultConsumer.onActivate();
                    }
                }
                this.currentConsumer.onAccessibilityEvent(accessibilityEvent);
                if (accessibilityEvent.getEventType() == 4194304 && this.windowActive != (isOnscreenKeyboardActive = this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.isOnscreenKeyboardActive())) {
                    this.windowActive = isOnscreenKeyboardActive;
                    if (isOnscreenKeyboardActive) {
                        AccessibilityWindowInfo onscreenInputWindowInfo = SpannableUtils$IdentifierSpan.getOnscreenInputWindowInfo((AccessibilityService) ((BdController) this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer);
                        if (onscreenInputWindowInfo != null) {
                            charSequence = onscreenInputWindowInfo.getTitle();
                        }
                        if (TextUtils.isEmpty(charSequence)) {
                            charSequence = this.context.getString(R.string.bd_keyboard);
                        }
                        string = this.context.getString(R.string.bd_keyboard_showing, charSequence);
                    } else {
                        string = this.context.getString(R.string.bd_keyboard_hidden);
                    }
                    displayTimedMessage(string);
                    return;
                }
                return;
            }
            return;
        }
        this.cellsContentConsumer$ar$class_merging.setContent$ar$edu(new CellsContent(""), 4);
        this.autoScrollManager.stop();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onActivate() {
        this.currentConsumer.onActivate();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onDeactivate() {
        this.currentConsumer.onDeactivate();
        this.autoScrollManager.stop();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x02c9, code lost:
    
        if (r4 != false) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0207, code lost:
    
        if (r0 != false) goto L121;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:42:0x0128. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:54:0x019f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x009b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0173  */
    /* JADX WARN: Type inference failed for: r0v47, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v42, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onMappedInputEvent(com.google.android.accessibility.braille.brltty.BrailleInputEvent r9) {
        /*
            Method dump skipped, instructions count: 764
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.braille.brailledisplay.controller.EventManager.onMappedInputEvent(com.google.android.accessibility.braille.brltty.BrailleInputEvent):boolean");
    }
}
