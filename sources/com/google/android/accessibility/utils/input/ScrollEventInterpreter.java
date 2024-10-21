package com.google.android.accessibility.utils.input;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.Supplier;
import com.google.android.accessibility.utils.monitor.AudioPlaybackMonitor;
import com.google.android.accessibility.utils.output.ScrollActionRecord;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScrollEventInterpreter implements AccessibilityEventListener {
    private final AudioPlaybackMonitor audioPlaybackMonitor;
    public Supplier scrollActorState;
    private final boolean supportMultipleAutoScroll;
    private final UiChangesTracker touchMonitor$ar$class_merging;
    private int handledScrollInstanceId = -1;
    private final HashMap cachedPositionInfo = new HashMap();
    private final List listeners = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NodeIdentifier {
        private final int nodeHashCode;
        private final int windowId;

        public NodeIdentifier(AccessibilityNodeInfo accessibilityNodeInfo) {
            this.nodeHashCode = accessibilityNodeInfo.hashCode();
            this.windowId = accessibilityNodeInfo.getWindowId();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NodeIdentifier)) {
                return false;
            }
            NodeIdentifier nodeIdentifier = (NodeIdentifier) obj;
            if (this.nodeHashCode == nodeIdentifier.nodeHashCode && this.windowId == nodeIdentifier.windowId) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.nodeHashCode), Integer.valueOf(this.windowId));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PositionInfo {
        public final int fromIndex;
        private final int itemCount;
        private final int scrollDeltaX;
        private final int scrollDeltaY;
        public final int scrollX;
        public final int scrollY;
        private final int toIndex;

        public PositionInfo(AccessibilityEvent accessibilityEvent) {
            this.fromIndex = accessibilityEvent.getFromIndex();
            this.toIndex = accessibilityEvent.getToIndex();
            this.scrollX = accessibilityEvent.getScrollX();
            this.scrollY = accessibilityEvent.getScrollY();
            this.itemCount = accessibilityEvent.getItemCount();
            this.scrollDeltaX = SpannableUtils$IdentifierSpan.getScrollDeltaX(accessibilityEvent);
            this.scrollDeltaY = SpannableUtils$IdentifierSpan.getScrollDeltaY(accessibilityEvent);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PositionInfo)) {
                return false;
            }
            PositionInfo positionInfo = (PositionInfo) obj;
            if (this.fromIndex == positionInfo.fromIndex && this.toIndex == positionInfo.toIndex && this.scrollX == positionInfo.scrollX && this.scrollY == positionInfo.scrollY && this.itemCount == positionInfo.itemCount && this.scrollDeltaY == positionInfo.scrollDeltaY && this.scrollDeltaX == positionInfo.scrollDeltaX) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.fromIndex), Integer.valueOf(this.toIndex), Integer.valueOf(this.scrollX), Integer.valueOf(this.scrollY), Integer.valueOf(this.itemCount), Integer.valueOf(this.scrollDeltaX), Integer.valueOf(this.scrollDeltaY));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ScrollEventHandler {
        void onScrollEvent(AccessibilityEvent accessibilityEvent, ScrollEventInterpretation scrollEventInterpretation, Performance.EventId eventId);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ScrollEventInterpretation {
        public static final ScrollEventInterpretation DEFAULT_INTERPRETATION = new ScrollEventInterpretation(0, 0, false, false, false, false, -1);
        public final boolean hasValidIndex;
        public final boolean isDuplicateEvent;
        public final boolean isFromScrollable;
        public final boolean isMediaPlayerAutoScroll;
        public final int scrollDirection;
        public final int scrollInstanceId;
        public final int userAction;

        public ScrollEventInterpretation(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, int i3) {
            this.userAction = i;
            this.scrollDirection = i2;
            this.hasValidIndex = z;
            this.isDuplicateEvent = z2;
            this.isFromScrollable = z3;
            this.isMediaPlayerAutoScroll = z4;
            this.scrollInstanceId = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ScrollEventInterpretation)) {
                return false;
            }
            ScrollEventInterpretation scrollEventInterpretation = (ScrollEventInterpretation) obj;
            if (this.userAction == scrollEventInterpretation.userAction && this.scrollDirection == scrollEventInterpretation.scrollDirection && this.hasValidIndex == scrollEventInterpretation.hasValidIndex && this.isDuplicateEvent == scrollEventInterpretation.isDuplicateEvent && this.scrollInstanceId == scrollEventInterpretation.scrollInstanceId) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.userAction), Integer.valueOf(this.scrollDirection), Boolean.valueOf(this.hasValidIndex), Boolean.valueOf(this.isDuplicateEvent), Integer.valueOf(this.scrollInstanceId));
        }

        public final String toString() {
            return "ScrollEventInterpretation{userAction=" + ScrollActionRecord.userActionToString(this.userAction) + ", scrollDirection=" + TraversalStrategyUtils.directionToString(this.scrollDirection) + ", hasValidIndex=" + this.hasValidIndex + ", isDuplicateEvent=" + this.isDuplicateEvent + ", isFromScrollable=" + this.isFromScrollable + ", isMediaPlayerAutoScroll=" + this.isMediaPlayerAutoScroll + ", scrollInstanceId=" + this.scrollInstanceId + '}';
        }
    }

    public ScrollEventInterpreter(AudioPlaybackMonitor audioPlaybackMonitor, UiChangesTracker uiChangesTracker, boolean z) {
        this.audioPlaybackMonitor = audioPlaybackMonitor;
        this.touchMonitor$ar$class_merging = uiChangesTracker;
        this.supportMultipleAutoScroll = z;
    }

    private static final int getScrollDirectionFromDeltas$ar$ds(AccessibilityEvent accessibilityEvent) {
        int scrollDeltaX;
        int scrollDeltaY;
        scrollDeltaX = accessibilityEvent.getScrollDeltaX();
        scrollDeltaY = accessibilityEvent.getScrollDeltaY();
        if (Math.abs(scrollDeltaX) < 15 && Math.abs(scrollDeltaY) < 15) {
            return 0;
        }
        if (scrollDeltaX - 15 <= 0 && scrollDeltaY - 15 <= 0) {
            if (scrollDeltaX + 15 >= 0 && scrollDeltaY + 15 >= 0) {
                return 0;
            }
            return 2;
        }
        return 1;
    }

    private static boolean hasValidIndex(AccessibilityEvent accessibilityEvent) {
        int scrollDeltaX;
        int scrollDeltaY;
        if (accessibilityEvent.getFromIndex() == -1 || accessibilityEvent.getToIndex() == -1 || accessibilityEvent.getItemCount() <= 0) {
            if ((accessibilityEvent.getScrollX() < 0 && accessibilityEvent.getScrollY() < 0) || (accessibilityEvent.getMaxScrollX() <= 0 && accessibilityEvent.getMaxScrollY() <= 0)) {
                if (SpannableUtils$IdentifierSpan.isAtLeastP()) {
                    scrollDeltaX = accessibilityEvent.getScrollDeltaX();
                    if (scrollDeltaX == -1) {
                        scrollDeltaY = accessibilityEvent.getScrollDeltaY();
                        if (scrollDeltaY == -1) {
                            return false;
                        }
                        return true;
                    }
                    return true;
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public final void addListener(ScrollEventHandler scrollEventHandler) {
        this.listeners.add(scrollEventHandler);
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 6176;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0102, code lost:
    
        if (r11 <= 1000) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x007c, code lost:
    
        if (r20.getFromIndex() < r2.fromIndex) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x011d, code lost:
    
        if (r6 != false) goto L77;
     */
    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAccessibilityEvent(android.view.accessibility.AccessibilityEvent r20, com.google.android.accessibility.utils.Performance.EventId r21) {
        /*
            Method dump skipped, instructions count: 519
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.input.ScrollEventInterpreter.onAccessibilityEvent(android.view.accessibility.AccessibilityEvent, com.google.android.accessibility.utils.Performance$EventId):void");
    }
}
