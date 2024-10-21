package com.google.android.accessibility.talkback.eventprocessor;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityService$MagnificationController$OnMagnificationChangedListener;
import android.accessibilityservice.MagnificationConfig;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.MagnificationState;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessorMagnification implements AccessibilityEventListener {
    private final TalkBackAnalytics analytics;
    private final Verifier compositor$ar$class_merging$e4d5cfcc_0;
    private final GlobalVariables globalVariables;
    public final AccessibilityService.MagnificationController magnificationController;
    public final AccessibilityService$MagnificationController$OnMagnificationChangedListener onMagnificationChangedListener;
    private final boolean supportWindowMagnification;
    public float lastScale = 1.0f;
    public int lastMode = 0;

    public ProcessorMagnification(AccessibilityService.MagnificationController magnificationController, GlobalVariables globalVariables, Verifier verifier, TalkBackAnalytics talkBackAnalytics, boolean z) {
        AccessibilityService$MagnificationController$OnMagnificationChangedListener accessibilityService$MagnificationController$OnMagnificationChangedListener;
        this.magnificationController = magnificationController;
        this.globalVariables = globalVariables;
        this.compositor$ar$class_merging$e4d5cfcc_0 = verifier;
        this.analytics = talkBackAnalytics;
        this.supportWindowMagnification = z;
        if (z) {
            accessibilityService$MagnificationController$OnMagnificationChangedListener = new AccessibilityService$MagnificationController$OnMagnificationChangedListener() { // from class: com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification.1
                public final void onMagnificationChanged(AccessibilityService.MagnificationController magnificationController2, Region region, float f, float f2, float f3) {
                }

                /* JADX WARN: Code restructure failed: missing block: B:17:0x0017, code lost:
                
                    if (r6 != r4.lastScale) goto L13;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void onMagnificationChanged(android.accessibilityservice.AccessibilityService.MagnificationController r4, android.graphics.Region r5, android.accessibilityservice.MagnificationConfig r6) {
                    /*
                        r3 = this;
                        com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification r4 = com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification.this
                        int r5 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m275m(r6)
                        float r6 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m$1(r6)
                        int r0 = r4.lastMode
                        if (r5 == r0) goto L10
                        r0 = 1
                        goto L11
                    L10:
                        r0 = 0
                    L11:
                        if (r0 != 0) goto L1f
                        float r1 = r4.lastScale     // Catch: java.lang.Throwable -> L2d
                        int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                        if (r1 == 0) goto L1a
                        goto L1f
                    L1a:
                        r4.lastMode = r5
                        r4.lastScale = r6
                        return
                    L1f:
                        java.lang.Integer r1 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L2d
                        com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification r2 = com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification.this     // Catch: java.lang.Throwable -> L2d
                        float r2 = r2.lastScale     // Catch: java.lang.Throwable -> L2d
                        r4.handleMagnificationChanged(r1, r6, r2, r0)     // Catch: java.lang.Throwable -> L2d
                        com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification r4 = com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification.this
                        goto L1a
                    L2d:
                        r4 = move-exception
                        com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification r0 = com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification.this
                        r0.lastMode = r5
                        r0.lastScale = r6
                        throw r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification.AnonymousClass1.onMagnificationChanged(android.accessibilityservice.AccessibilityService$MagnificationController, android.graphics.Region, android.accessibilityservice.MagnificationConfig):void");
                }
            };
        } else {
            accessibilityService$MagnificationController$OnMagnificationChangedListener = new AccessibilityService$MagnificationController$OnMagnificationChangedListener() { // from class: com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification$$ExternalSyntheticLambda0
                public final void onMagnificationChanged(AccessibilityService.MagnificationController magnificationController2, Region region, float f, float f2, float f3) {
                    ProcessorMagnification processorMagnification = ProcessorMagnification.this;
                    float f4 = processorMagnification.lastScale;
                    if (f == f4) {
                        return;
                    }
                    try {
                        processorMagnification.handleMagnificationChanged(null, f, f4, false);
                    } finally {
                        processorMagnification.lastScale = f;
                    }
                }
            };
        }
        this.onMagnificationChangedListener = accessibilityService$MagnificationController$OnMagnificationChangedListener;
    }

    private final Rect getMagnificationBounds() {
        Region magnificationRegion;
        Region currentMagnificationRegion;
        if (this.supportWindowMagnification) {
            currentMagnificationRegion = this.magnificationController.getCurrentMagnificationRegion();
            return currentMagnificationRegion.getBounds();
        }
        magnificationRegion = this.magnificationController.getMagnificationRegion();
        return magnificationRegion.getBounds();
    }

    private static boolean isLeftToRight(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
        if (TextUtils.isEmpty(text)) {
            if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
                return true;
            }
            return false;
        }
        byte directionality = Character.getDirectionality(text.charAt(0));
        if (directionality != 1 && directionality != 2) {
            return true;
        }
        return false;
    }

    private final void recenterFullScreenMagnifier(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        float scale;
        float f;
        float f2;
        float centerY;
        float centerX;
        float centerX2;
        accessibilityNodeInfoCompat.getBoundsInScreen(new Rect());
        Rect magnificationBounds = getMagnificationBounds();
        scale = this.magnificationController.getScale();
        float width = magnificationBounds.width();
        float f3 = scale + scale;
        float height = magnificationBounds.height();
        if (SpannableUtils$IdentifierSpan.isAtLeastOMR1()) {
            centerY = this.magnificationController.getCenterY();
            f2 = (centerY + (r0.top / scale)) - 5.0f;
            if (isLeftToRight(accessibilityNodeInfoCompat)) {
                centerX2 = this.magnificationController.getCenterX();
                f = (centerX2 + (r0.left / scale)) - 5.0f;
            } else {
                centerX = this.magnificationController.getCenterX();
                f = centerX + (r0.right / scale) + 5.0f;
            }
        } else {
            float f4 = width / f3;
            float f5 = (r0.top + (height / f3)) - 5.0f;
            if (isLeftToRight(accessibilityNodeInfoCompat)) {
                f = (r0.left + f4) - 5.0f;
            } else {
                f = (r0.right - f4) + 5.0f;
            }
            f2 = f5;
        }
        this.magnificationController.setCenter(Math.min(Math.max(f, magnificationBounds.left + 1.0f), magnificationBounds.right - 1.0f), Math.min(Math.max(f2, magnificationBounds.top + 1.0f), magnificationBounds.bottom - 1.0f), true);
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 32776;
    }

    public final void handleMagnificationChanged(Integer num, float f, float f2, boolean z) {
        int i;
        int intValue;
        if (f > 1.0f && (z || f2 == 1.0f)) {
            TalkBackAnalytics talkBackAnalytics = this.analytics;
            if (num == null) {
                intValue = 1;
            } else {
                intValue = num.intValue();
            }
            TalkBackAnalyticsImpl talkBackAnalyticsImpl = (TalkBackAnalyticsImpl) talkBackAnalytics;
            if (talkBackAnalyticsImpl.initialized) {
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = talkBackAnalyticsImpl.talkBackAnalyticsLogger;
                if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                    new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, intValue, 9)).execute(new Void[0]);
                }
            }
            i = 1;
        } else if (f == 1.0f && f2 > 1.0f) {
            i = 0;
        } else if (f > 1.0f) {
            i = 2;
        } else {
            return;
        }
        GlobalVariables globalVariables = this.globalVariables;
        MagnificationState.Builder builder = new MagnificationState.Builder(null);
        builder.mode = 1;
        builder.setCurrentScale$ar$ds(1.0f);
        builder.setState$ar$ds(0);
        builder.mode = num;
        builder.setCurrentScale$ar$ds(f);
        builder.setState$ar$ds(i);
        if (builder.set$0 != 3) {
            StringBuilder sb = new StringBuilder();
            if ((builder.set$0 & 1) == 0) {
                sb.append(" currentScale");
            }
            if ((builder.set$0 & 2) == 0) {
                sb.append(" state");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        globalVariables.magnificationState = new MagnificationState(builder.mode, builder.currentScale, builder.state);
        if (Build.VERSION.SDK_INT != 31 && Build.VERSION.SDK_INT != 32) {
            Verifier verifier = this.compositor$ar$class_merging$e4d5cfcc_0;
            Logger logger = Performance.DEFAULT_LOGGER;
            verifier.handleEvent(1073741940, null);
        }
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        float scale;
        MagnificationConfig magnificationConfig;
        int mode;
        float f;
        MagnificationConfig.Builder centerX;
        MagnificationConfig.Builder centerY;
        MagnificationConfig build;
        MagnificationConfig magnificationConfig2;
        AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
        AccessibilityWindowInfoCompat window = AccessibilityNodeInfoUtils.getWindow(compat);
        if (compat != null && window != null && !AccessibilityNodeInfoUtils.isKeyboard(compat) && SpannableUtils$IdentifierSpan.getType(window) != 6) {
            if (!this.supportWindowMagnification) {
                scale = this.magnificationController.getScale();
            } else {
                magnificationConfig2 = this.magnificationController.getMagnificationConfig();
                scale = magnificationConfig2.getScale();
            }
            if (scale > 1.0f) {
                int type = SpannableUtils$IdentifierSpan.getType(window);
                if (this.supportWindowMagnification) {
                    magnificationConfig = this.magnificationController.getMagnificationConfig();
                    mode = magnificationConfig.getMode();
                    if (mode == 1) {
                        recenterFullScreenMagnifier(compat);
                        return;
                    }
                    if (mode == 2 && type != 3) {
                        Rect rect = new Rect();
                        compat.getBoundsInScreen(rect);
                        Rect magnificationBounds = getMagnificationBounds();
                        float exactCenterY = rect.exactCenterY();
                        if (rect.width() <= magnificationBounds.width()) {
                            f = rect.exactCenterX();
                        } else {
                            float width = rect.width() * 0.2f;
                            if (isLeftToRight(compat)) {
                                f = rect.left + width;
                            } else {
                                f = rect.right - width;
                            }
                        }
                        centerX = new MagnificationConfig.Builder().setCenterX(f);
                        centerY = centerX.setCenterY(exactCenterY);
                        build = centerY.build();
                        this.magnificationController.setMagnificationConfig(build, false);
                        return;
                    }
                    return;
                }
                recenterFullScreenMagnifier(compat);
            }
        }
    }
}
