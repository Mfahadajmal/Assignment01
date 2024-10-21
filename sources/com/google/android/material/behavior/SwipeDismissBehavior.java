package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private boolean interceptingEvents;
    public RetryingNameResolver.ResolutionResultListener listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean requestingDisallowInterceptTouchEvent;
    ViewDragHelper viewDragHelper;
    public int swipeDirection = 2;
    final float dragDismissThreshold = 0.5f;
    public float alphaStartSwipeDistance = 0.0f;
    public float alphaEndSwipeDistance = 0.5f;
    private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.1
        private int activePointerId = -1;
        private int originalCapturedViewLeft;

        public AnonymousClass1() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int clampViewPositionHorizontal$ar$ds(View view, int i) {
            int width;
            int width2;
            int width3;
            int layoutDirection = view.getLayoutDirection();
            int i2 = SwipeDismissBehavior.this.swipeDirection;
            if (i2 == 0) {
                if (layoutDirection == 1) {
                    width = this.originalCapturedViewLeft - view.getWidth();
                    width2 = this.originalCapturedViewLeft;
                } else {
                    width = this.originalCapturedViewLeft;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i2 == 1) {
                if (layoutDirection == 1) {
                    width = this.originalCapturedViewLeft;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                } else {
                    width = this.originalCapturedViewLeft - view.getWidth();
                    width2 = this.originalCapturedViewLeft;
                }
            } else {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = view.getWidth() + this.originalCapturedViewLeft;
            }
            return Math.min(Math.max(width, i), width2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int clampViewPositionVertical$ar$ds(View view, int i) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewCaptured(View view, int i) {
            this.activePointerId = i;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = true;
                parent.requestDisallowInterceptTouchEvent(true);
                SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = false;
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewDragStateChanged(int i) {
            RetryingNameResolver.ResolutionResultListener resolutionResultListener = SwipeDismissBehavior.this.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging;
            if (resolutionResultListener != null) {
                if (i != 0) {
                    SnackbarManager.getInstance().pauseTimeout$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(((BaseTransientBottomBar) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                } else {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(((BaseTransientBottomBar) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                }
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewPositionChanged$ar$ds(View view, int i, int i2) {
            float width = view.getWidth() * SwipeDismissBehavior.this.alphaStartSwipeDistance;
            float width2 = view.getWidth() * SwipeDismissBehavior.this.alphaEndSwipeDistance;
            float abs = Math.abs(i - this.originalCapturedViewLeft);
            if (abs <= width) {
                view.setAlpha(1.0f);
            } else if (abs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp$ar$ds(1.0f - ((abs - width) / (width2 - width))));
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0046, code lost:
        
            if (java.lang.Math.abs(r7.getLeft() - r6.originalCapturedViewLeft) >= java.lang.Math.round(r7.getWidth() * 0.5f)) goto L63;
         */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onViewReleased(android.view.View r7, float r8, float r9) {
            /*
                r6 = this;
                r9 = -1
                r6.activePointerId = r9
                r9 = 0
                int r0 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                int r1 = r7.getWidth()
                r2 = 1
                if (r0 == 0) goto L2f
                int r3 = r7.getLayoutDirection()
                com.google.android.material.behavior.SwipeDismissBehavior r4 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r4 = r4.swipeDirection
                r5 = 2
                if (r4 != r5) goto L19
                goto L48
            L19:
                if (r4 != 0) goto L25
                if (r3 != r2) goto L22
                int r0 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                if (r0 < 0) goto L48
                goto L5c
            L22:
                if (r0 <= 0) goto L5c
                goto L48
            L25:
                if (r3 != r2) goto L2a
                if (r0 <= 0) goto L5c
                goto L48
            L2a:
                int r0 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                if (r0 >= 0) goto L5c
                goto L48
            L2f:
                int r0 = r7.getLeft()
                int r3 = r6.originalCapturedViewLeft
                int r0 = r0 - r3
                int r3 = r7.getWidth()
                float r3 = (float) r3
                r4 = 1056964608(0x3f000000, float:0.5)
                float r3 = r3 * r4
                int r3 = java.lang.Math.round(r3)
                int r0 = java.lang.Math.abs(r0)
                if (r0 < r3) goto L5c
            L48:
                int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                if (r8 < 0) goto L57
                int r8 = r7.getLeft()
                int r9 = r6.originalCapturedViewLeft
                if (r8 >= r9) goto L55
                goto L57
            L55:
                int r9 = r9 + r1
                goto L5f
            L57:
                int r8 = r6.originalCapturedViewLeft
                int r9 = r8 - r1
                goto L5f
            L5c:
                int r9 = r6.originalCapturedViewLeft
                r2 = 0
            L5f:
                com.google.android.material.behavior.SwipeDismissBehavior r8 = com.google.android.material.behavior.SwipeDismissBehavior.this
                androidx.customview.widget.ViewDragHelper r8 = r8.viewDragHelper
                int r0 = r7.getTop()
                boolean r8 = r8.settleCapturedViewAt(r9, r0)
                if (r8 == 0) goto L78
                com.google.android.material.behavior.SwipeDismissBehavior r8 = com.google.android.material.behavior.SwipeDismissBehavior.this
                com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable r9 = new com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable
                r9.<init>(r7, r2)
                r7.postOnAnimation(r9)
                return
            L78:
                if (r2 == 0) goto L83
                com.google.android.material.behavior.SwipeDismissBehavior r8 = com.google.android.material.behavior.SwipeDismissBehavior.this
                io.grpc.internal.RetryingNameResolver$ResolutionResultListener r8 = r8.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging
                if (r8 == 0) goto L83
                r8.onDismiss(r7)
            L83:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final boolean tryCaptureView(View view, int i) {
            int i2 = this.activePointerId;
            if ((i2 == -1 || i2 == i) && SwipeDismissBehavior.this.canSwipeDismissView(view)) {
                return true;
            }
            return false;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.material.behavior.SwipeDismissBehavior$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends ViewDragHelper.Callback {
        private int activePointerId = -1;
        private int originalCapturedViewLeft;

        public AnonymousClass1() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int clampViewPositionHorizontal$ar$ds(View view, int i) {
            int width;
            int width2;
            int width3;
            int layoutDirection = view.getLayoutDirection();
            int i2 = SwipeDismissBehavior.this.swipeDirection;
            if (i2 == 0) {
                if (layoutDirection == 1) {
                    width = this.originalCapturedViewLeft - view.getWidth();
                    width2 = this.originalCapturedViewLeft;
                } else {
                    width = this.originalCapturedViewLeft;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i2 == 1) {
                if (layoutDirection == 1) {
                    width = this.originalCapturedViewLeft;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                } else {
                    width = this.originalCapturedViewLeft - view.getWidth();
                    width2 = this.originalCapturedViewLeft;
                }
            } else {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = view.getWidth() + this.originalCapturedViewLeft;
            }
            return Math.min(Math.max(width, i), width2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int clampViewPositionVertical$ar$ds(View view, int i) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewCaptured(View view, int i) {
            this.activePointerId = i;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = true;
                parent.requestDisallowInterceptTouchEvent(true);
                SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = false;
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewDragStateChanged(int i) {
            RetryingNameResolver.ResolutionResultListener resolutionResultListener = SwipeDismissBehavior.this.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging;
            if (resolutionResultListener != null) {
                if (i != 0) {
                    SnackbarManager.getInstance().pauseTimeout$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(((BaseTransientBottomBar) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                } else {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(((BaseTransientBottomBar) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                }
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewPositionChanged$ar$ds(View view, int i, int i2) {
            float width = view.getWidth() * SwipeDismissBehavior.this.alphaStartSwipeDistance;
            float width2 = view.getWidth() * SwipeDismissBehavior.this.alphaEndSwipeDistance;
            float abs = Math.abs(i - this.originalCapturedViewLeft);
            if (abs <= width) {
                view.setAlpha(1.0f);
            } else if (abs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp$ar$ds(1.0f - ((abs - width) / (width2 - width))));
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewReleased(View view, float f, float f2) {
            /*  JADX ERROR: Method code generation error
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.IContainer.get(jadx.api.plugins.input.data.attributes.IJadxAttrType)" because "cont" is null
                	at jadx.core.codegen.RegionGen.declareVars(RegionGen.java:70)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                */
            /*
                this = this;
                r9 = -1
                r6.activePointerId = r9
                r9 = 0
                int r0 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                int r1 = r7.getWidth()
                r2 = 1
                if (r0 == 0) goto L2f
                int r3 = r7.getLayoutDirection()
                com.google.android.material.behavior.SwipeDismissBehavior r4 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r4 = r4.swipeDirection
                r5 = 2
                if (r4 != r5) goto L19
                goto L48
            L19:
                if (r4 != 0) goto L25
                if (r3 != r2) goto L22
                int r0 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                if (r0 < 0) goto L48
                goto L5c
            L22:
                if (r0 <= 0) goto L5c
                goto L48
            L25:
                if (r3 != r2) goto L2a
                if (r0 <= 0) goto L5c
                goto L48
            L2a:
                int r0 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                if (r0 >= 0) goto L5c
                goto L48
            L2f:
                int r0 = r7.getLeft()
                int r3 = r6.originalCapturedViewLeft
                int r0 = r0 - r3
                int r3 = r7.getWidth()
                float r3 = (float) r3
                r4 = 1056964608(0x3f000000, float:0.5)
                float r3 = r3 * r4
                int r3 = java.lang.Math.round(r3)
                int r0 = java.lang.Math.abs(r0)
                if (r0 < r3) goto L5c
            L48:
                int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                if (r8 < 0) goto L57
                int r8 = r7.getLeft()
                int r9 = r6.originalCapturedViewLeft
                if (r8 >= r9) goto L55
                goto L57
            L55:
                int r9 = r9 + r1
                goto L5f
            L57:
                int r8 = r6.originalCapturedViewLeft
                int r9 = r8 - r1
                goto L5f
            L5c:
                int r9 = r6.originalCapturedViewLeft
                r2 = 0
            L5f:
                com.google.android.material.behavior.SwipeDismissBehavior r8 = com.google.android.material.behavior.SwipeDismissBehavior.this
                androidx.customview.widget.ViewDragHelper r8 = r8.viewDragHelper
                int r0 = r7.getTop()
                boolean r8 = r8.settleCapturedViewAt(r9, r0)
                if (r8 == 0) goto L78
                com.google.android.material.behavior.SwipeDismissBehavior r8 = com.google.android.material.behavior.SwipeDismissBehavior.this
                com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable r9 = new com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable
                r9.<init>(r7, r2)
                r7.postOnAnimation(r9)
                return
            L78:
                if (r2 == 0) goto L83
                com.google.android.material.behavior.SwipeDismissBehavior r8 = com.google.android.material.behavior.SwipeDismissBehavior.this
                io.grpc.internal.RetryingNameResolver$ResolutionResultListener r8 = r8.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging
                if (r8 == 0) goto L83
                r8.onDismiss(r7)
            L83:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final boolean tryCaptureView(View view, int i) {
            int i2 = this.activePointerId;
            if ((i2 == -1 || i2 == i) && SwipeDismissBehavior.this.canSwipeDismissView(view)) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.material.behavior.SwipeDismissBehavior$2 */
    /* loaded from: classes.dex */
    final class AnonymousClass2 implements AccessibilityViewCommand {
        public AnonymousClass2() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        
            if (r5 == false) goto L35;
         */
        /* JADX WARN: Removed duplicated region for block: B:12:0x003a  */
        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean perform(android.view.View r4, androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments r5) {
            /*
                r3 = this;
                com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                boolean r5 = r5.canSwipeDismissView(r4)
                r0 = 0
                if (r5 == 0) goto L3e
                int r5 = r4.getLayoutDirection()
                r1 = 1
                if (r5 != r1) goto L12
                r5 = r1
                goto L13
            L12:
                r5 = r0
            L13:
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r2 = r2.swipeDirection
                if (r2 != 0) goto L1c
                if (r5 != 0) goto L21
                goto L1d
            L1c:
                r0 = r5
            L1d:
                if (r2 != r1) goto L27
                if (r0 != 0) goto L27
            L21:
                int r5 = r4.getWidth()
                int r5 = -r5
                goto L2b
            L27:
                int r5 = r4.getWidth()
            L2b:
                int[] r0 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
                r4.offsetLeftAndRight(r5)
                r5 = 0
                r4.setAlpha(r5)
                com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                io.grpc.internal.RetryingNameResolver$ResolutionResultListener r5 = r5.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging
                if (r5 == 0) goto L3d
                r5.onDismiss(r4)
            L3d:
                return r1
            L3e:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass2.perform(android.view.View, androidx.core.view.accessibility.AccessibilityViewCommand$CommandArguments):boolean");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SettleRunnable implements Runnable {
        private final boolean dismiss;
        private final View view;

        public SettleRunnable(View view, boolean z) {
            this.view = view;
            this.dismiss = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            RetryingNameResolver.ResolutionResultListener resolutionResultListener;
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.viewDragHelper;
            if (viewDragHelper != null && viewDragHelper.continueSettling$ar$ds()) {
                this.view.postOnAnimation(this);
            } else if (this.dismiss && (resolutionResultListener = SwipeDismissBehavior.this.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging) != null) {
                resolutionResultListener.onDismiss(this.view);
            }
        }
    }

    public static float clamp$ar$ds(float f) {
        return Math.min(Math.max(0.0f, f), 1.0f);
    }

    public boolean canSwipeDismissView(View view) {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                this.interceptingEvents = false;
            }
        } else {
            z = coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.interceptingEvents = z;
        }
        if (z) {
            if (this.viewDragHelper == null) {
                this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
            }
            if (!this.requestingDisallowInterceptTouchEvent && this.viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
            ViewCompat.removeAccessibilityAction(view, 1048576);
            if (canSwipeDismissView(view)) {
                ViewCompat.addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, r1.mId, null, new AccessibilityViewCommand() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.2
                    public AnonymousClass2() {
                    }

                    @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                    public final boolean perform(View view2, AccessibilityViewCommand.CommandArguments commandArguments) {
                        /*  JADX ERROR: Method code generation error
                            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.IContainer.get(jadx.api.plugins.input.data.attributes.IJadxAttrType)" because "cont" is null
                            	at jadx.core.codegen.RegionGen.declareVars(RegionGen.java:70)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            */
                        /*
                            this = this;
                            com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                            boolean r5 = r5.canSwipeDismissView(r4)
                            r0 = 0
                            if (r5 == 0) goto L3e
                            int r5 = r4.getLayoutDirection()
                            r1 = 1
                            if (r5 != r1) goto L12
                            r5 = r1
                            goto L13
                        L12:
                            r5 = r0
                        L13:
                            com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                            int r2 = r2.swipeDirection
                            if (r2 != 0) goto L1c
                            if (r5 != 0) goto L21
                            goto L1d
                        L1c:
                            r0 = r5
                        L1d:
                            if (r2 != r1) goto L27
                            if (r0 != 0) goto L27
                        L21:
                            int r5 = r4.getWidth()
                            int r5 = -r5
                            goto L2b
                        L27:
                            int r5 = r4.getWidth()
                        L2b:
                            int[] r0 = androidx.core.view.ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS
                            r4.offsetLeftAndRight(r5)
                            r5 = 0
                            r4.setAlpha(r5)
                            com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                            io.grpc.internal.RetryingNameResolver$ResolutionResultListener r5 = r5.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging
                            if (r5 == 0) goto L3d
                            r5.onDismiss(r4)
                        L3d:
                            return r1
                        L3e:
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass2.perform(android.view.View, androidx.core.view.accessibility.AccessibilityViewCommand$CommandArguments):boolean");
                    }
                }, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS.mViewCommandArgumentClass));
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.viewDragHelper != null) {
            if (!this.requestingDisallowInterceptTouchEvent || motionEvent.getActionMasked() != 3) {
                this.viewDragHelper.processTouchEvent(motionEvent);
                return true;
            }
            return true;
        }
        return false;
    }
}
