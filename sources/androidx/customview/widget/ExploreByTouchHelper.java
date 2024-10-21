package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.preference.Preference;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    private static final Rect INVALID_BOUNDS = new Rect(Preference.DEFAULT_ORDER, Preference.DEFAULT_ORDER, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final IconCompat.Api23Impl NODE_ADAPTER$ar$class_merging$ar$class_merging = new IconCompat.Api23Impl();
    private static final IconCompat.Api26Impl SPARSE_VALUES_ADAPTER$ar$class_merging$ar$class_merging = new IconCompat.Api26Impl();
    public final View mHost;
    public final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final int[] mTempGlobalRect = new int[2];
    public int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    public int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MyNodeProvider extends AccessibilityNodeProviderCompat {
        public MyNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(i));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final AccessibilityNodeInfoCompat findFocus(int i) {
            int i2;
            if (i == 2) {
                i2 = ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId;
            } else {
                i2 = ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            }
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(i2);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final boolean performAction(int i, int i2, Bundle bundle) {
            int i3;
            ExploreByTouchHelper exploreByTouchHelper = ExploreByTouchHelper.this;
            if (i != -1) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 64) {
                            if (i2 != 128) {
                                return exploreByTouchHelper.onPerformActionForVirtualView(i, i2, bundle);
                            }
                            return exploreByTouchHelper.clearAccessibilityFocus(i);
                        }
                        if (!exploreByTouchHelper.mManager.isEnabled() || !exploreByTouchHelper.mManager.isTouchExplorationEnabled() || (i3 = exploreByTouchHelper.mAccessibilityFocusedVirtualViewId) == i) {
                            return false;
                        }
                        if (i3 != Integer.MIN_VALUE) {
                            exploreByTouchHelper.clearAccessibilityFocus(i3);
                        }
                        exploreByTouchHelper.mAccessibilityFocusedVirtualViewId = i;
                        exploreByTouchHelper.mHost.invalidate();
                        exploreByTouchHelper.sendEventForVirtualView$ar$ds(i, 32768);
                        return true;
                    }
                    return exploreByTouchHelper.clearKeyboardFocusForVirtualView(i);
                }
                return exploreByTouchHelper.requestKeyboardFocusForVirtualView(i);
            }
            return exploreByTouchHelper.mHost.performAccessibilityAction(i2, bundle);
        }
    }

    public ExploreByTouchHelper(View view) {
        if (view != null) {
            this.mHost = view;
            this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    private final AccessibilityEvent createEvent(int i, int i2) {
        if (i != -1) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
            AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i);
            obtain.getText().add(obtainAccessibilityNodeInfo.getText());
            obtain.setContentDescription(obtainAccessibilityNodeInfo.getContentDescription());
            obtain.setScrollable(obtainAccessibilityNodeInfo.isScrollable());
            obtain.setPassword(obtainAccessibilityNodeInfo.isPassword());
            obtain.setEnabled(obtainAccessibilityNodeInfo.isEnabled());
            obtain.setChecked(obtainAccessibilityNodeInfo.isChecked());
            onPopulateEventForVirtualView(i, obtain);
            if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            }
            obtain.setClassName(obtainAccessibilityNodeInfo.getClassName());
            obtain.setSource(this.mHost, i);
            obtain.setPackageName(this.mHost.getContext().getPackageName());
            return obtain;
        }
        AccessibilityEvent obtain2 = AccessibilityEvent.obtain(i2);
        this.mHost.onInitializeAccessibilityEvent(obtain2);
        return obtain2;
    }

    private final AccessibilityNodeInfoCompat createNodeForChild(int i) {
        boolean z;
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        obtain.setEnabled(true);
        obtain.setFocusable$ar$ds();
        obtain.setClassName("android.view.View");
        Rect rect = INVALID_BOUNDS;
        obtain.setBoundsInParent(rect);
        obtain.setBoundsInScreen(rect);
        obtain.mParentVirtualDescendantId = -1;
        obtain.mInfo.setParent(this.mHost);
        onPopulateNodeForVirtualView(i, obtain);
        if (obtain.getText() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.mTempParentRect);
        obtain.getBoundsInScreen(this.mTempScreenRect);
        if (this.mTempParentRect.equals(rect) && this.mTempScreenRect.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds or screen bounds in populateNodeForVirtualViewId()");
        }
        int actions = obtain.getActions();
        if ((actions & 64) == 0) {
            if ((actions & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) == 0) {
                obtain.mInfo.setPackageName(this.mHost.getContext().getPackageName());
                View view = this.mHost;
                obtain.mVirtualDescendantId = i;
                obtain.mInfo.setSource(view, i);
                if (this.mAccessibilityFocusedVirtualViewId == i) {
                    obtain.setAccessibilityFocused(true);
                    obtain.addAction(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                } else {
                    obtain.setAccessibilityFocused(false);
                    obtain.addAction(64);
                }
                if (this.mKeyboardFocusedVirtualViewId == i) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    obtain.addAction(2);
                } else if (obtain.isFocusable()) {
                    obtain.addAction(1);
                }
                obtain.mInfo.setFocused(z);
                this.mHost.getLocationOnScreen(this.mTempGlobalRect);
                if (this.mTempScreenRect.equals(rect)) {
                    Rect rect2 = this.mTempParentRect;
                    obtain.setBoundsInParent(rect2);
                    Rect rect3 = new Rect();
                    rect3.set(rect2);
                    if (obtain.mParentVirtualDescendantId != -1) {
                        AccessibilityNodeInfoCompat obtain2 = AccessibilityNodeInfoCompat.obtain();
                        Rect rect4 = new Rect();
                        for (int i2 = obtain.mParentVirtualDescendantId; i2 != -1; i2 = obtain2.mParentVirtualDescendantId) {
                            View view2 = this.mHost;
                            obtain2.mParentVirtualDescendantId = -1;
                            obtain2.mInfo.setParent(view2, -1);
                            obtain2.setBoundsInParent(INVALID_BOUNDS);
                            onPopulateNodeForVirtualView(0, obtain2);
                            obtain2.getBoundsInParent(rect4);
                            rect3.offset(rect4.left, rect4.top);
                        }
                    }
                    this.mHost.getLocationOnScreen(this.mTempGlobalRect);
                    rect3.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                    obtain.setBoundsInScreen(rect3);
                    obtain.getBoundsInScreen(this.mTempScreenRect);
                }
                if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                    this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                    if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                        obtain.setBoundsInScreen(this.mTempScreenRect);
                        Rect rect5 = this.mTempScreenRect;
                        if (rect5 != null && !rect5.isEmpty() && this.mHost.getWindowVisibility() == 0) {
                            Object parent = this.mHost.getParent();
                            while (true) {
                                if (parent instanceof View) {
                                    View view3 = (View) parent;
                                    if (view3.getAlpha() <= 0.0f || view3.getVisibility() != 0) {
                                        break;
                                    }
                                    parent = view3.getParent();
                                } else if (parent != null) {
                                    obtain.mInfo.setVisibleToUser(true);
                                }
                            }
                        }
                    }
                }
                return obtain;
            }
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    }

    private final void updateHoveredVirtualView(int i) {
        int i2 = this.mHoveredVirtualViewId;
        if (i2 == i) {
            return;
        }
        this.mHoveredVirtualViewId = i;
        sendEventForVirtualView$ar$ds(i, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        sendEventForVirtualView$ar$ds(i2, 256);
    }

    public final boolean clearAccessibilityFocus(int i) {
        if (this.mAccessibilityFocusedVirtualViewId == i) {
            this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
            this.mHost.invalidate();
            sendEventForVirtualView$ar$ds(i, 65536);
            return true;
        }
        return false;
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        if (this.mKeyboardFocusedVirtualViewId != i) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i, false);
        sendEventForVirtualView$ar$ds(i, 8);
        return true;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            if (action != 7 && action != 9) {
                if (action != 10 || this.mHoveredVirtualViewId == Integer.MIN_VALUE) {
                    return false;
                }
                updateHoveredVirtualView(Integer.MIN_VALUE);
                return true;
            }
            int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
            updateHoveredVirtualView(virtualViewAt);
            if (virtualViewAt != Integer.MIN_VALUE) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }

    protected abstract int getVirtualViewAt(float f, float f2);

    protected abstract void getVisibleVirtualViews(List list);

    public final void invalidateVirtualView(int i) {
        ViewParent parent;
        if (i != Integer.MIN_VALUE && this.mManager.isEnabled() && (parent = this.mHost.getParent()) != null) {
            AccessibilityEvent createEvent = createEvent(i, 2048);
            createEvent.setContentChangeTypes(0);
            parent.requestSendAccessibilityEvent(this.mHost, createEvent);
        }
    }

    final AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i) {
        if (i == -1) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(this.mHost));
            View view = this.mHost;
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.mInfo);
            ArrayList arrayList = new ArrayList();
            getVisibleVirtualViews(arrayList);
            if (accessibilityNodeInfoCompat.getChildCount() > 0 && arrayList.size() > 0) {
                throw new RuntimeException("Views cannot have both real and virtual children");
            }
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                accessibilityNodeInfoCompat.mInfo.addChild(this.mHost, ((Integer) arrayList.get(i2)).intValue());
            }
            return accessibilityNodeInfoCompat;
        }
        return createNodeForChild(i);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        onPopulateNodeForHost(accessibilityNodeInfoCompat);
    }

    public abstract boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle);

    protected abstract void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        int i2;
        if ((this.mHost.isFocused() || this.mHost.requestFocus()) && (i2 = this.mKeyboardFocusedVirtualViewId) != i) {
            if (i2 != Integer.MIN_VALUE) {
                clearKeyboardFocusForVirtualView(i2);
            }
            if (i != Integer.MIN_VALUE) {
                this.mKeyboardFocusedVirtualViewId = i;
                onVirtualViewKeyboardFocusChanged(i, true);
                sendEventForVirtualView$ar$ds(i, 8);
                return true;
            }
            return false;
        }
        return false;
    }

    public final void sendEventForVirtualView$ar$ds(int i, int i2) {
        ViewParent parent;
        if (i != Integer.MIN_VALUE && this.mManager.isEnabled() && (parent = this.mHost.getParent()) != null) {
            parent.requestSendAccessibilityEvent(this.mHost, createEvent(i, i2));
        }
    }

    protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
    }

    protected void onVirtualViewKeyboardFocusChanged(int i, boolean z) {
    }
}
