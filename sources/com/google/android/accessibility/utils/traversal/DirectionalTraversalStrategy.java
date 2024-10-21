package com.google.android.accessibility.utils.traversal;

import android.graphics.Rect;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.Preference;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectionalTraversalStrategy implements TraversalStrategy {
    private final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final AccessibilityNodeInfoCompat root;
    private final Rect rootRectPadded;
    private final Set visitedNodes = new HashSet();
    public final List focusableNodes = new ArrayList();
    private final Set containerNodes = new HashSet();
    private final Map speakingNodesCache = new HashMap();

    public DirectionalTraversalStrategy(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AppLifecycleMonitor appLifecycleMonitor) {
        this.root = accessibilityNodeInfoCompat;
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        Rect rect = new Rect();
        if (accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        }
        int width = rect.width() / 20;
        Rect rect2 = new Rect(rect);
        this.rootRectPadded = rect2;
        int i = -width;
        rect2.inset(i, i);
        processNodes$ar$ds(accessibilityNodeInfoCompat);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0022, code lost:
    
        if (r11.bottom <= r13.top) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
    
        if (r10 == 3) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0041, code lost:
    
        if (r10 != 4) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
    
        r12 = majorAxisDistance(r10, r11, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
    
        if (r10 == 3) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004a, code lost:
    
        if (r10 == 4) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
    
        if (r10 == 5) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        if (r10 != 6) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0050, code lost:
    
        r10 = r13.bottom;
        r11 = r11.bottom;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
    
        if (r12 >= java.lang.Math.max(1, r10 - r11)) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0071, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005a, code lost:
    
        throw new java.lang.IllegalArgumentException("direction must be a SearchDirection");
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        r10 = r11.top;
        r11 = r13.top;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
    
        r10 = r13.right;
        r11 = r11.right;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
    
        r10 = r11.left;
        r11 = r13.left;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x002f, code lost:
    
        if (r11.top >= r13.bottom) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0036, code lost:
    
        if (r11.right <= r13.left) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x003d, code lost:
    
        if (r11.left >= r13.right) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean beamBeats(int r10, android.graphics.Rect r11, android.graphics.Rect r12, android.graphics.Rect r13) {
        /*
            r9 = this;
            boolean r0 = beamsOverlap$ar$ds(r10, r11, r12)
            boolean r1 = beamsOverlap$ar$ds(r10, r11, r13)
            r2 = 0
            if (r1 != 0) goto L73
            if (r0 != 0) goto Lf
            goto L73
        Lf:
            java.lang.String r0 = "direction must be a SearchDirection"
            r1 = 6
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 1
            if (r10 == r5) goto L39
            if (r10 == r4) goto L32
            if (r10 == r3) goto L2b
            if (r10 != r1) goto L25
            int r7 = r11.bottom
            int r8 = r13.top
            if (r7 > r8) goto L72
            goto L3f
        L25:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>(r0)
            throw r10
        L2b:
            int r7 = r11.top
            int r8 = r13.bottom
            if (r7 < r8) goto L72
            goto L3f
        L32:
            int r7 = r11.right
            int r8 = r13.left
            if (r7 > r8) goto L72
            goto L3f
        L39:
            int r7 = r11.left
            int r8 = r13.right
            if (r7 < r8) goto L72
        L3f:
            if (r10 == r5) goto L72
            if (r10 != r4) goto L44
            goto L72
        L44:
            int r12 = majorAxisDistance(r10, r11, r12)
            if (r10 == r5) goto L65
            if (r10 == r4) goto L60
            if (r10 == r3) goto L5b
            if (r10 != r1) goto L55
            int r10 = r13.bottom
            int r11 = r11.bottom
            goto L69
        L55:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>(r0)
            throw r10
        L5b:
            int r10 = r11.top
            int r11 = r13.top
            goto L69
        L60:
            int r10 = r13.right
            int r11 = r11.right
            goto L69
        L65:
            int r10 = r11.left
            int r11 = r13.left
        L69:
            int r10 = r10 - r11
            int r10 = java.lang.Math.max(r6, r10)
            if (r12 >= r10) goto L71
            return r6
        L71:
            return r2
        L72:
            return r6
        L73:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.traversal.DirectionalTraversalStrategy.beamBeats(int, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect):boolean");
    }

    private static final boolean beamsOverlap$ar$ds(int i, Rect rect, Rect rect2) {
        if (i != 3 && i != 4) {
            if (i != 5 && i != 6) {
                throw new IllegalArgumentException("direction must be a SearchDirection");
            }
            if (rect2.right >= rect.left && rect2.left <= rect.right) {
                return true;
            }
            return false;
        }
        if (rect2.bottom >= rect.top && rect2.top <= rect.bottom) {
            return true;
        }
        return false;
    }

    private final AccessibilityNodeInfoCompat findFocusFromRect(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect, int i) {
        Rect rect2 = new Rect(rect);
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i == 6) {
                        rect2.offset(0, -(rect.height() + 1));
                    }
                } else {
                    rect2.offset(0, rect.height() + 1);
                }
            } else {
                rect2.offset(-(rect.width() + 1), 0);
            }
        } else {
            rect2.offset(rect.width() + 1, 0);
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = null;
        for (AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 : this.focusableNodes) {
            if (!accessibilityNodeInfoCompat3.equals(accessibilityNodeInfoCompat) && !accessibilityNodeInfoCompat3.equals(this.root)) {
                Rect rect3 = new Rect();
                getAssumedRectInScreen(accessibilityNodeInfoCompat3, rect3);
                if (isCandidate$ar$ds(rect, rect3, i) && (!isCandidate$ar$ds(rect, rect2, i) || beamBeats(i, rect, rect3, rect2) || (!beamBeats(i, rect, rect2, rect3) && getWeightedDistanceFor$ar$ds(majorAxisDistance(i, rect, rect3), minorAxisDistance(i, rect, rect3)) < getWeightedDistanceFor$ar$ds(majorAxisDistance(i, rect, rect2), minorAxisDistance(i, rect, rect2))))) {
                    rect2.set(rect3);
                    accessibilityNodeInfoCompat2 = accessibilityNodeInfoCompat3;
                }
            }
        }
        return accessibilityNodeInfoCompat2;
    }

    private final void getAssumedRectInScreen(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect) {
        accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        if (this.containerNodes.contains(accessibilityNodeInfoCompat)) {
            rect.set(rect.left, rect.top, rect.right, rect.top + 1);
        }
    }

    private static final int getWeightedDistanceFor$ar$ds(int i, int i2) {
        if (i <= 10000 && i2 <= 10000) {
            return (i * 13 * i) + (i2 * i2);
        }
        return Preference.DEFAULT_ORDER;
    }

    private static final boolean isCandidate$ar$ds(Rect rect, Rect rect2, int i) {
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i == 6) {
                        if ((rect.top < rect2.top || rect.bottom <= rect2.top) && rect.bottom < rect2.bottom) {
                            return true;
                        }
                        return false;
                    }
                    throw new IllegalArgumentException("direction must be a SearchDirection");
                }
                if ((rect.bottom > rect2.bottom || rect.top >= rect2.bottom) && rect.top > rect2.top) {
                    return true;
                }
                return false;
            }
            if ((rect.left < rect2.left || rect.right <= rect2.left) && rect.right < rect2.right) {
                return true;
            }
            return false;
        }
        if ((rect.right > rect2.right || rect.left >= rect2.right) && rect.left > rect2.left) {
            return true;
        }
        return false;
    }

    static int majorAxisDistance(int i, Rect rect, Rect rect2) {
        int i2;
        int i3;
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i == 6) {
                        i2 = rect2.top;
                        i3 = rect.bottom;
                    } else {
                        throw new IllegalArgumentException("direction must be a SearchDirection");
                    }
                } else {
                    i2 = rect.top;
                    i3 = rect2.bottom;
                }
            } else {
                i2 = rect2.left;
                i3 = rect.right;
            }
        } else {
            i2 = rect.left;
            i3 = rect2.right;
        }
        return Math.max(0, i2 - i3);
    }

    static int minorAxisDistance(int i, Rect rect, Rect rect2) {
        if (i != 3 && i != 4) {
            if (i != 5 && i != 6) {
                throw new IllegalArgumentException("direction must be a SearchDirection");
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }

    private final boolean processNodes$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null || this.visitedNodes.contains(accessibilityNodeInfoCompat)) {
            return false;
        }
        Rect rect = new Rect();
        accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        if (!Rect.intersects(rect, this.rootRectPadded)) {
            return false;
        }
        this.visitedNodes.add(accessibilityNodeInfoCompat);
        if (WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
            this.focusableNodes.add(accessibilityNodeInfoCompat);
            return true;
        }
        boolean shouldFocusNode = AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat, this.speakingNodesCache);
        if (shouldFocusNode) {
            this.focusableNodes.add(accessibilityNodeInfoCompat);
        }
        int childCount = accessibilityNodeInfoCompat.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfoCompat child = accessibilityNodeInfoCompat.getChild(i);
            if (child != null) {
                z |= processNodes$ar$ds(child);
            }
        }
        if (z) {
            this.containerNodes.add(accessibilityNodeInfoCompat);
        }
        if (!shouldFocusNode && !z) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    public final AccessibilityNodeInfoCompat findFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        if (accessibilityNodeInfoCompat.equals(this.root)) {
            Filter filter = new Filter() { // from class: com.google.android.accessibility.utils.traversal.DirectionalTraversalStrategy.1
                @Override // com.google.android.accessibility.utils.Filter
                public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj;
                    if (accessibilityNodeInfoCompat2 != null && DirectionalTraversalStrategy.this.focusableNodes.contains(accessibilityNodeInfoCompat2)) {
                        return true;
                    }
                    return false;
                }
            };
            AccessibilityNodeInfoCompat selfOrMatchingAncestor = AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.findFocusCompat(1), filter);
            if (selfOrMatchingAncestor != null) {
                return selfOrMatchingAncestor;
            }
            return TraversalStrategyUtils.searchFocus(new OrderedTraversalStrategy(this.root), this.root, 1, filter);
        }
        Rect rect = new Rect();
        getAssumedRectInScreen(accessibilityNodeInfoCompat, rect);
        return findFocusFromRect(accessibilityNodeInfoCompat, rect, i);
    }

    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    public final AccessibilityNodeInfoCompat focusFirst(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        Rect rect = new Rect();
        accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        AccessibilityNodeInfoCompat findFocusCompat = this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.findFocusCompat(2);
        Rect rect2 = new Rect();
        if (findFocusCompat != null) {
            Rect rect3 = new Rect();
            findFocusCompat.getBoundsInScreen(rect3);
            Rect rect4 = new Rect();
            this.root.getBoundsInScreen(rect4);
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        if (i == 6) {
                            rect2.set(rect3.left, rect4.top - rect3.height(), rect3.right, rect4.top);
                        } else {
                            throw new IllegalArgumentException("direction must be a SearchDirection");
                        }
                    } else {
                        rect2.set(rect3.left, rect4.bottom, rect3.right, rect4.bottom + rect3.height());
                    }
                } else {
                    rect2.set(rect4.left - rect3.width(), rect3.top, rect4.left, rect3.bottom);
                }
            } else {
                rect2.set(rect4.right, rect3.top, rect4.right + rect3.width(), rect3.bottom);
            }
        } else if (i == 3) {
            rect2.set(rect.right, rect.top, rect.right + 1, rect.bottom);
        } else if (i == 4) {
            rect2.set(rect.left - 1, rect.top, rect.left, rect.bottom);
        } else if (i == 5) {
            rect2.set(rect.left, rect.bottom, rect.right, rect.bottom + 1);
        } else {
            rect2.set(rect.left, rect.top - 1, rect.right, rect.top);
        }
        return findFocusFromRect(findFocusCompat, rect2, i);
    }

    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    public final /* synthetic */ AccessibilityNodeInfoCompat focusInitial(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return null;
    }

    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    public final Map getSpeakingNodesCache() {
        return null;
    }
}
