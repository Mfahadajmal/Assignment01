package com.google.android.accessibility.talkback.feedbackpolicy;

import android.accessibilityservice.AccessibilityService;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.ReadOnly;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenFeedbackManager implements AccessibilityEventListener, WindowEventInterpreter.WindowEventHandler {
    protected final OptionalMethod feedbackComposer$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final WindowEventInterpreter interpreter;
    private boolean listeningToInterpreter = false;
    private final Pipeline.FeedbackReturner pipeline;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Feedback extends ReadOnly {
        public final List parts = new ArrayList();

        protected Feedback() {
        }

        public final void addPart(FeedbackPart feedbackPart) {
            checkIsWritable();
            this.parts.add(feedbackPart);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            for (FeedbackPart feedbackPart : this.parts) {
                sb.append("[");
                sb.append(feedbackPart);
                sb.append("] ");
            }
            return sb.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeedbackPart {
        public boolean forceFeedbackEvenIfAudioPlaybackActive = false;
        public boolean forceFeedbackEvenIfMicrophoneActive = false;
        public boolean forceFeedbackEvenIfSsbActive = false;
        public final CharSequence speech;

        public FeedbackPart(CharSequence charSequence) {
            this.speech = charSequence;
        }

        public final String toString() {
            String format;
            CharSequence charSequence = this.speech;
            if (charSequence == null) {
                format = "null";
            } else {
                format = String.format("\"%s\"", charSequence);
            }
            return StringBuilderUtils.joinFields(format.toString(), "", StringBuilderUtils.optionalTag("forceFeedbackEvenIfAudioPlaybackActive", this.forceFeedbackEvenIfAudioPlaybackActive), StringBuilderUtils.optionalTag(" forceFeedbackEvenIfMicrophoneActive", this.forceFeedbackEvenIfMicrophoneActive), StringBuilderUtils.optionalTag(" forceFeedbackEvenIfSsbActive", this.forceFeedbackEvenIfSsbActive));
        }
    }

    public ScreenFeedbackManager(AccessibilityService accessibilityService, WindowEventInterpreter windowEventInterpreter, AppLifecycleMonitor appLifecycleMonitor, GestureShortcutMapping gestureShortcutMapping, Pipeline.FeedbackReturner feedbackReturner) {
        this.interpreter = windowEventInterpreter;
        this.feedbackComposer$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod(accessibilityService, appLifecycleMonitor, gestureShortcutMapping, (char[]) null);
        this.pipeline = feedbackReturner;
    }

    protected static void logCompose(int i, String str, String str2, Object... objArr) {
        char[] cArr = new char[i + i];
        Arrays.fill(cArr, ' ');
        LogUtils.v("ScreenFeedbackManager", "%s%s() %s", new String(cArr), str, String.format(str2, objArr));
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 4194336;
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0149, code lost:
    
        if (r17.picInPicChanged != false) goto L61;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0147  */
    /* JADX WARN: Type inference failed for: r7v28, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r7v31, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r7v8, types: [android.text.SpannableStringBuilder] */
    @Override // com.google.android.accessibility.utils.input.WindowEventInterpreter.WindowEventHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handle(com.google.android.accessibility.utils.input.WindowEventInterpreter.EventInterpretation r17, com.google.android.accessibility.utils.Performance.EventId r18) {
        /*
            Method dump skipped, instructions count: 710
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.feedbackpolicy.ScreenFeedbackManager.handle(com.google.android.accessibility.utils.input.WindowEventInterpreter$EventInterpretation, com.google.android.accessibility.utils.Performance$EventId):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
    
        if ((r8 & 1031) == 0) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x046b  */
    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAccessibilityEvent(android.view.accessibility.AccessibilityEvent r18, com.google.android.accessibility.utils.Performance.EventId r19) {
        /*
            Method dump skipped, instructions count: 1205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.feedbackpolicy.ScreenFeedbackManager.onAccessibilityEvent(android.view.accessibility.AccessibilityEvent, com.google.android.accessibility.utils.Performance$EventId):void");
    }
}
