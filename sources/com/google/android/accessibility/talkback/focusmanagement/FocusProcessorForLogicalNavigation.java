package com.google.android.accessibility.talkback.focusmanagement;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.NodeActionFilter;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ScrollableNodeInfo;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.monitor.CollectionState;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.traversal.TraversalStrategy;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import io.grpc.internal.RetriableStream;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusProcessorForLogicalNavigation {
    public static final /* synthetic */ int FocusProcessorForLogicalNavigation$ar$NoOp = 0;
    private static final Filter SCROLLABLE_ROLE_FILTER_FOR_DIRECTION_NAVIGATION = AccessibilityNodeInfoUtils.FILTER_AUTO_SCROLL;
    private static final Filter SCROLLABLE_ROLE_FILTER_FOR_SCROLL_GESTURE = AccessibilityNodeInfoUtils.FILTER_SCROLLABLE.and(new Filter() { // from class: com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation.1
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            int role = SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj);
            if (role != 10 && role != 27 && role != 28) {
                return true;
            }
            return false;
        }
    });
    public final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public ActorState actorState;
    public final Filter filterWindowForWindowNavigation;
    public final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final boolean isWindowNavigationSupported;
    public AccessibilityNodeInfoCompat lastScrolledNodeForNativeMacroGranularity;
    public Pipeline.FeedbackReturner pipeline;
    private final HapticPatternParser$$ExternalSyntheticLambda1 screenState$ar$class_merging$ar$class_merging$ar$class_merging;
    public TaskQueue scrollCallback$ar$class_merging$f9bb3c40_0;
    private final FloatingActionButton.ShadowDelegateImpl searchState$ar$class_merging$ar$class_merging;
    public final AccessibilityService service;
    private boolean reachEdge = false;
    public AccessibilityNodeInfoCompat stealWindowNavigationTarget = null;
    public int stealWindowNavigationTargetDirection = 0;
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DirectionalNavigationWindowFilter extends Filter {
        final Context context;
        final FloatingActionButton.ShadowDelegateImpl searchState$ar$class_merging$ar$class_merging;

        public DirectionalNavigationWindowFilter(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
            this.context = context;
            this.searchState$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final boolean accept(AccessibilityWindowInfo accessibilityWindowInfo) {
            if (accessibilityWindowInfo == null) {
                return false;
            }
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.searchState$ar$class_merging$ar$class_merging;
            int type = SpannableUtils$IdentifierSpan.getType(accessibilityWindowInfo);
            if (shadowDelegateImpl.isUiVisible()) {
                if (!FocusProcessorForLogicalNavigation.isSearchOverlay(this.context, accessibilityWindowInfo)) {
                    if (type == 3) {
                        if (SpannableUtils$NonCopyableTextSpan.isSystemBar(this.context, accessibilityWindowInfo)) {
                            type = 3;
                        }
                    }
                    if (type != 6 && type != 2) {
                        return false;
                    }
                }
                return true;
            }
            if (type != 1 && type != 5) {
                if (type == 3) {
                    if (SpannableUtils$NonCopyableTextSpan.isSystemBar(this.context, accessibilityWindowInfo)) {
                        type = 3;
                    }
                }
                if (type != 6 && type != 2) {
                    return false;
                }
            }
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MaxSizeNodeAccumulator extends Filter {
        AccessibilityNodeInfoCompat maximumScrollableNode;
        int maximumSize;
        final Filter scrollableFilter;

        public MaxSizeNodeAccumulator(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
            this.scrollableFilter = filter;
            if (accessibilityNodeInfoCompat == null) {
                this.maximumSize = 0;
                return;
            }
            this.maximumScrollableNode = accessibilityNodeInfoCompat;
            Rect rect = new Rect();
            this.maximumScrollableNode.getBoundsInScreen(rect);
            this.maximumSize = rect.width() * rect.height();
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat == null) {
                return true;
            }
            Rect rect = new Rect();
            accessibilityNodeInfoCompat.getBoundsInScreen(rect);
            int width = rect.width() * rect.height();
            if (width <= this.maximumSize) {
                return true;
            }
            if (!this.scrollableFilter.accept(accessibilityNodeInfoCompat)) {
                return false;
            }
            this.maximumScrollableNode = accessibilityNodeInfoCompat;
            this.maximumSize = width;
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class WindowNavigationFilter extends Filter {
        final Context context;
        final FloatingActionButton.ShadowDelegateImpl searchState$ar$class_merging$ar$class_merging;

        public WindowNavigationFilter(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
            this.context = context;
            this.searchState$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) obj;
            if (accessibilityWindowInfo == null) {
                return false;
            }
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.searchState$ar$class_merging$ar$class_merging;
            int type = SpannableUtils$IdentifierSpan.getType(accessibilityWindowInfo);
            if (shadowDelegateImpl.isUiVisible()) {
                if (!FocusProcessorForLogicalNavigation.isSearchOverlay(this.context, accessibilityWindowInfo) && type != 2 && type != 3 && type != 6) {
                    return false;
                }
            } else if (type != 1 && type != 2 && type != 3 && type != 6) {
                return false;
            }
            return true;
        }
    }

    public FocusProcessorForLogicalNavigation(AccessibilityService accessibilityService, AppLifecycleMonitor appLifecycleMonitor, AccessibilityFocusMonitor accessibilityFocusMonitor, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.service = accessibilityService;
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.screenState$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.searchState$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.isWindowNavigationSupported = !r0.isAndroidTv;
        this.filterWindowForWindowNavigation = new WindowNavigationFilter(accessibilityService, shadowDelegateImpl);
    }

    private final boolean autoScroll(ScrollableNodeInfo scrollableNodeInfo, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, Performance.EventId eventId) {
        Integer supportedScrollDirection = scrollableNodeInfo.getSupportedScrollDirection(navigationAction.searchDirection);
        if (supportedScrollDirection == null) {
            return false;
        }
        NavigationAction.Builder copy = NavigationAction.Builder.copy(navigationAction);
        copy.searchDirection = supportedScrollDirection.intValue();
        return performScrollActionInternal$ar$edu(1, scrollableNodeInfo.node, accessibilityNodeInfoCompat, TraversalStrategyUtils.convertSearchDirectionToScrollAction(new NavigationAction(copy).searchDirection), navigationAction, 1000, eventId);
    }

    private final TraversalStrategy createTraversal(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        return TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, i);
    }

    private final OrderVerifyingClientCall.State findTargetFromMiddlePivot$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, Filter filter, TraversalStrategy traversalStrategy, Performance.EventId eventId) {
        if (accessibilityNodeInfoCompat == null) {
            return OrderVerifyingClientCall.State.create$ar$edu$ca49f0b7_0$ar$class_merging$ar$class_merging(1);
        }
        if (!WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
            return OrderVerifyingClientCall.State.create$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat);
        }
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 15) {
            if (navigationAction.targetType == 0) {
                if (TraversalStrategyUtils.getLogicalDirection(navigationAction.searchDirection, SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.service)) == 2) {
                    return navigateToHtmlTargetWithFallBack$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, navigationAction, filter, traversalStrategy, eventId);
                }
                return OrderVerifyingClientCall.State.create$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat);
            }
            return findTargetFromWebElement$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, navigationAction, filter, traversalStrategy, eventId);
        }
        throw new IllegalArgumentException("Middle-pivot must be either native or WebView container!");
    }

    private final OrderVerifyingClientCall.State findTargetFromWebElement$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, Filter filter, TraversalStrategy traversalStrategy, Performance.EventId eventId) {
        AccessibilityService accessibilityService = this.service;
        int i = navigationAction.searchDirection;
        int logicalDirection = TraversalStrategyUtils.getLogicalDirection(i, SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(accessibilityService));
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 15 && navigationAction.targetType == 0 && logicalDirection == 2) {
            return OrderVerifyingClientCall.State.create$ar$class_merging$ar$class_merging(TraversalStrategyUtils.searchFocus(traversalStrategy, accessibilityNodeInfoCompat, i, filter));
        }
        return navigateToHtmlTargetWithFallBack$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, navigationAction, filter, traversalStrategy, eventId);
    }

    public static Filter getScrollFilter(NavigationAction navigationAction) {
        int convertSearchDirectionToScrollAction;
        int i = navigationAction.actionType;
        if (i != 1) {
            switch (i) {
                case 4:
                case 6:
                case 8:
                    convertSearchDirectionToScrollAction = 4096;
                    break;
                case 5:
                case 7:
                case 9:
                    convertSearchDirectionToScrollAction = 8192;
                    break;
                default:
                    convertSearchDirectionToScrollAction = 0;
                    break;
            }
        } else {
            convertSearchDirectionToScrollAction = TraversalStrategyUtils.convertSearchDirectionToScrollAction(navigationAction.searchDirection);
        }
        if (convertSearchDirectionToScrollAction == 0) {
            return null;
        }
        int i2 = navigationAction.actionType;
        if (i2 != 4 && i2 != 5 && i2 != 6 && i2 != 7 && i2 != 8 && i2 != 9) {
            return new NodeActionFilter(convertSearchDirectionToScrollAction).and(SCROLLABLE_ROLE_FILTER_FOR_DIRECTION_NAVIGATION);
        }
        return new NodeActionFilter(convertSearchDirectionToScrollAction).and(SCROLLABLE_ROLE_FILTER_FOR_SCROLL_GESTURE);
    }

    public static Filter getScrollOrPageActionFilter(NavigationAction navigationAction) {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        int id;
        int id2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
        switch (navigationAction.actionType) {
            case 6:
                accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
                id = accessibilityAction.getId();
                id2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId();
                break;
            case 7:
                accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
                id = accessibilityAction2.getId();
                id2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId();
                break;
            case 8:
                accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
                id = accessibilityAction3.getId();
                id2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId();
                break;
            case 9:
                accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
                id = accessibilityAction4.getId();
                id2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId();
                break;
            default:
                id = 0;
                id2 = 0;
                break;
        }
        if (id != 0 && id2 != 0) {
            return new NodeActionFilter(id).or(new NodeActionFilter(id2));
        }
        return null;
    }

    public static boolean isSearchOverlay(Context context, AccessibilityWindowInfo accessibilityWindowInfo) {
        CharSequence title;
        if (SpannableUtils$IdentifierSpan.getType(accessibilityWindowInfo) != 4) {
            return false;
        }
        title = accessibilityWindowInfo.getTitle();
        if (Objects.equals(title.toString(), context.getString(R.string.title_screen_search))) {
            return true;
        }
        return false;
    }

    private final OrderVerifyingClientCall.State navigateToHtmlTargetWithFallBack$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, Filter filter, TraversalStrategy traversalStrategy, Performance.EventId eventId) {
        if (navigateToHtmlTarget(accessibilityNodeInfoCompat, navigationAction, eventId)) {
            return OrderVerifyingClientCall.State.create$ar$edu$ca49f0b7_0$ar$class_merging$ar$class_merging(3);
        }
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 15) {
            if (!WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
                accessibilityNodeInfoCompat = null;
            } else {
                accessibilityNodeInfoCompat = AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, WebInterfaceUtils.FILTER_WEB_VIEW);
            }
        }
        return OrderVerifyingClientCall.State.create$ar$class_merging$ar$class_merging(TraversalStrategyUtils.searchFocus(traversalStrategy, accessibilityNodeInfoCompat, navigationAction.searchDirection, filter));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.List, java.lang.Object] */
    private static final boolean needPauseWhenTraverseAcrossWindow$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(RetriableStream.HedgingPlan hedgingPlan, boolean z, AccessibilityWindowInfo accessibilityWindowInfo, int i, Filter filter) {
        if (TraversalStrategyUtils.getLogicalDirection(i, z) == 1) {
            int windowIndex = hedgingPlan.getWindowIndex(accessibilityWindowInfo);
            if (windowIndex != -1) {
                int size = hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis.size();
                for (int i2 = windowIndex + 1; i2 < size; i2++) {
                    if (filter.accept((AccessibilityWindowInfo) hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis.get(i2))) {
                        return false;
                    }
                }
            }
        } else {
            int windowIndex2 = hedgingPlan.getWindowIndex(accessibilityWindowInfo);
            if (windowIndex2 > 0) {
                for (int i3 = windowIndex2 - 1; i3 >= 0; i3--) {
                    if (filter.accept((AccessibilityWindowInfo) hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis.get(i3))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static AccessibilityNodeInfoCompat refreshAndGetFirstOrLastChild(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        int i;
        if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.refresh() && accessibilityNodeInfoCompat.getChildCount() > 0) {
            if (!z) {
                i = accessibilityNodeInfoCompat.getChildCount() - 1;
            } else {
                i = 0;
            }
            return accessibilityNodeInfoCompat.getChild(i);
        }
        return null;
    }

    public final void announceNativeElement(int i, int i2, Performance.EventId eventId) {
        String string;
        int i3;
        try {
            if (SpannableUtils$IdentifierSpan.isMacroGranularity(i2)) {
                AccessibilityService accessibilityService = this.service;
                if (i2 != 202) {
                    switch (i2) {
                        case 1048577:
                            string = accessibilityService.getString(R.string.display_name_heading);
                            break;
                        case 1048578:
                            string = accessibilityService.getString(R.string.display_name_control);
                            break;
                        case 1048579:
                            string = accessibilityService.getString(R.string.display_name_link);
                            break;
                        default:
                            LogUtils.e("NavigationTarget", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i2, "nativeTargetToDisplayName() unhandled target type="), new Object[0]);
                            string = "(unknown)";
                            break;
                    }
                } else {
                    string = accessibilityService.getString(R.string.display_name_container);
                }
                if (i == 1) {
                    i3 = R.string.end_of_page;
                } else {
                    i3 = R.string.start_of_page;
                }
                String string2 = this.service.getString(i3, new Object[]{string});
                SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                speakOptions.mQueueMode = 2;
                speakOptions.mFlags = 4096;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(string2, speakOptions));
            }
        } catch (IllegalArgumentException unused) {
            LogUtils.w("FocusProcessor-LogicalNav", "Invalid navigation target type.", new Object[0]);
        }
    }

    public final AccessibilityNodeInfoCompat currentContainer(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, AccessibilityNodeInfoUtils.FILTER_CONTAINER);
    }

    public final AccessibilityNodeInfoCompat currentPaneContainer(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(5)));
    }

    public final boolean ensureOnScreen(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Performance.EventId eventId) {
        boolean z;
        Boolean bool = false;
        ScrollableNodeInfo findScrollableNodeForDirection = ScrollableNodeInfo.findScrollableNodeForDirection(i, accessibilityNodeInfoCompat, false, SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.service));
        if (findScrollableNodeForDirection != null) {
            boolean isAutoScrollEdgeListItem$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = TraversalStrategyUtils.isAutoScrollEdgeListItem$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, findScrollableNodeForDirection, false, i, this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = findScrollableNodeForDirection.node;
            if (!isAutoScrollEdgeListItem$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging) {
                AccessibilityService accessibilityService = this.service;
                if (accessibilityNodeInfoCompat2 != null) {
                    if (i != 3 && i != 4) {
                        if (i != 5 && i != 6) {
                            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionInfo$ar$class_merging = accessibilityNodeInfoCompat2.getCollectionInfo$ar$class_merging();
                            if (collectionInfo$ar$class_merging != null && collectionInfo$ar$class_merging.getRowCount() > 0 && collectionInfo$ar$class_merging.getColumnCount() > 0) {
                                if (1 != CollectionState.getCollectionAlignmentInternal$ar$class_merging(collectionInfo$ar$class_merging)) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                bool = Boolean.valueOf(z);
                            } else {
                                int i2 = 0;
                                while (true) {
                                    if (i2 < accessibilityNodeInfoCompat2.getChildCount() - 1) {
                                        Rect nodeBoundsInScreen = AccessibilityNodeInfoUtils.getNodeBoundsInScreen(accessibilityNodeInfoCompat2.getChild(i2));
                                        i2++;
                                        Rect nodeBoundsInScreen2 = AccessibilityNodeInfoUtils.getNodeBoundsInScreen(accessibilityNodeInfoCompat2.getChild(i2));
                                        if (!nodeBoundsInScreen.isEmpty() && !nodeBoundsInScreen2.isEmpty()) {
                                            if (nodeBoundsInScreen.centerX() == nodeBoundsInScreen2.centerX()) {
                                                break;
                                            }
                                            if (nodeBoundsInScreen.centerY() == nodeBoundsInScreen2.centerY()) {
                                                bool = true;
                                                break;
                                            }
                                        }
                                    } else {
                                        bool = null;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        bool = true;
                    }
                    if (bool != null) {
                        Rect nodeBoundsInScreen3 = AccessibilityNodeInfoUtils.getNodeBoundsInScreen(accessibilityNodeInfoCompat2);
                        Rect nodeBoundsInScreen4 = AccessibilityNodeInfoUtils.getNodeBoundsInScreen(accessibilityNodeInfoCompat);
                        boolean isScreenLayoutRTL = SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(accessibilityService);
                        if (TraversalStrategyUtils.isSpatialDirection(i)) {
                            i = TraversalStrategyUtils.getLogicalDirection(i, isScreenLayoutRTL);
                        }
                        if (i == 1) {
                        }
                    }
                }
            }
            return ensureOnScreenInternal(accessibilityNodeInfoCompat2, accessibilityNodeInfoCompat, eventId);
        }
        return false;
    }

    public final boolean ensureOnScreenInternal(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2, Performance.EventId eventId) {
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        Feedback.Scroll.Builder builder2 = Feedback.Scroll.builder();
        builder2.setAction$ar$edu$e02d76b2_0$ar$ds(3);
        builder2.nodeCompat = accessibilityNodeInfoCompat;
        builder2.nodeToMoveOnScreen = accessibilityNodeInfoCompat2;
        builder2.setUserAction$ar$ds(1);
        builder2.setNodeAction$ar$ds(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN.getId());
        builder2.source = "FOCUS";
        builder.scroll = builder2.build();
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, builder);
    }

    public final boolean navigateToHtmlTarget(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, Performance.EventId eventId) {
        int i = navigationAction.targetType;
        switch (i) {
            case 1048577:
                i = 262147;
                break;
            case 1048578:
                i = 262146;
                break;
            case 1048579:
                i = 262145;
                break;
        }
        NavigationAction.Builder copy = NavigationAction.Builder.copy(navigationAction);
        copy.targetType = i;
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.webDirectionHtml(accessibilityNodeInfoCompat, new NavigationAction(copy)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x038c, code lost:
    
        if (com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(r31.pipeline, r35, com.google.android.accessibility.talkback.Feedback.nodeAction(r3, androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION.getId(), r4)) != false) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x03f2, code lost:
    
        if (autoScroll(r1, r3, r2, r35) != false) goto L136;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:195:0x05f8. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0625  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0688  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0267  */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v45 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onDirectionalNavigationAction(final androidx.core.view.accessibility.AccessibilityNodeInfoCompat r32, boolean r33, com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction r34, com.google.android.accessibility.utils.Performance.EventId r35) {
        /*
            Method dump skipped, instructions count: 1778
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation.onDirectionalNavigationAction(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, boolean, com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }

    public final boolean performPageOrScrollAction(NavigationAction navigationAction, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
        switch (navigationAction.actionType) {
            case 6:
                accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, accessibilityAction.getId())) {
                    Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                    accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, accessibilityAction2.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId()));
                }
                return false;
            case 7:
                accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, accessibilityAction3.getId())) {
                    Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
                    accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, accessibilityAction4.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId()));
                }
                return false;
            case 8:
                accessibilityAction5 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, accessibilityAction5.getId())) {
                    Pipeline.FeedbackReturner feedbackReturner3 = this.pipeline;
                    accessibilityAction6 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner3, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, accessibilityAction6.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId()));
                }
                return false;
            case 9:
                accessibilityAction7 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, accessibilityAction7.getId())) {
                    Pipeline.FeedbackReturner feedbackReturner4 = this.pipeline;
                    accessibilityAction8 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner4, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, accessibilityAction8.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT.getId()));
                }
                if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId())) {
                    return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat, AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD.getId()));
                }
                return false;
            default:
                return false;
        }
    }

    public final boolean performScrollActionInternal$ar$edu(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2, int i2, NavigationAction navigationAction, int i3, Performance.EventId eventId) {
        int i4 = navigationAction.actionType;
        if ((i4 == 5 || i4 == 4) && !AccessibilityNodeInfoUtils.hasAncestor(accessibilityNodeInfoCompat2, accessibilityNodeInfoCompat)) {
            this.scrollCallback$ar$class_merging$f9bb3c40_0 = null;
        } else {
            this.scrollCallback$ar$class_merging$f9bb3c40_0 = new TaskQueue(this, navigationAction, accessibilityNodeInfoCompat2, false);
        }
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        int i5 = navigationAction.autoScrollAttempt;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        Feedback.Scroll.Builder builder2 = Feedback.Scroll.builder();
        builder2.setAction$ar$edu$e02d76b2_0$ar$ds(1);
        builder2.nodeCompat = accessibilityNodeInfoCompat;
        builder2.setUserAction$ar$ds(i);
        builder2.setNodeAction$ar$ds(i2);
        builder2.source = "FOCUS";
        builder2.setTimeout$ar$edu$ar$ds(i3);
        builder2.autoScrollAttempt = Integer.valueOf(i5);
        builder.scroll = builder2.build();
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, builder);
    }

    public final AccessibilityNodeInfoCompat searchScrollableNodeFromBfs(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, boolean z) {
        Filter scrollOrPageActionFilter;
        AccessibilityNodeInfoCompat searchFromBfs;
        MaxSizeNodeAccumulator maxSizeNodeAccumulator;
        if (accessibilityNodeInfoCompat == null || (searchFromBfs = AccessibilityNodeInfoUtils.searchFromBfs(accessibilityNodeInfoCompat, (scrollOrPageActionFilter = getScrollOrPageActionFilter(navigationAction)))) == null) {
            return null;
        }
        if (z) {
            if (SpannableUtils$IdentifierSpan.getRole(searchFromBfs) == 16) {
                maxSizeNodeAccumulator = new MaxSizeNodeAccumulator(null, scrollOrPageActionFilter.and(new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(7))));
            } else {
                maxSizeNodeAccumulator = new MaxSizeNodeAccumulator(searchFromBfs, scrollOrPageActionFilter.and(new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(8))));
            }
        } else {
            maxSizeNodeAccumulator = new MaxSizeNodeAccumulator(searchFromBfs, scrollOrPageActionFilter);
        }
        AccessibilityNodeInfoUtils.searchFromBfs(accessibilityNodeInfoCompat, new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(9)), maxSizeNodeAccumulator);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = maxSizeNodeAccumulator.maximumScrollableNode;
        if (accessibilityNodeInfoCompat2 == null) {
            return searchFromBfs;
        }
        return accessibilityNodeInfoCompat2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0103, code lost:
    
        return null;
     */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.util.List, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.core.view.accessibility.AccessibilityNodeInfoCompat searchTargetInNextOrPreviousWindow$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState r16, io.grpc.internal.RetriableStream.HedgingPlan r17, boolean r18, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r19, android.view.accessibility.AccessibilityWindowInfo r20, int r21, com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor r22, boolean r23, com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1 r24, com.google.android.accessibility.utils.Filter r25, com.google.android.accessibility.utils.Filter r26) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation.searchTargetInNextOrPreviousWindow$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState, io.grpc.internal.RetriableStream$HedgingPlan, boolean, androidx.core.view.accessibility.AccessibilityNodeInfoCompat, android.view.accessibility.AccessibilityWindowInfo, int, com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor, boolean, com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1, com.google.android.accessibility.utils.Filter, com.google.android.accessibility.utils.Filter):androidx.core.view.accessibility.AccessibilityNodeInfoCompat");
    }

    public final boolean setAccessibilityFocusInternal(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NavigationAction navigationAction, Performance.EventId eventId) {
        this.reachEdge = false;
        this.lastScrolledNodeForNativeMacroGranularity = null;
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        FocusActionInfo.Builder builder = new FocusActionInfo.Builder();
        builder.sourceAction = 4;
        builder.navigationAction = navigationAction;
        Feedback.Focus.Builder focus = Feedback.focus(accessibilityNodeInfoCompat, new FocusActionInfo(builder));
        focus.setForceRefocus$ar$ds(true);
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, focus);
    }
}
