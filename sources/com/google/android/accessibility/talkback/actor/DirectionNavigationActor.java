package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.accessibility.talkback.CursorGranularityManager;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.monitor.InputModeTracker;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.traversal.TraversalStrategy;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectionNavigationActor {
    public final Object DirectionNavigationActor$ar$accessibilityFocusMonitor;
    public final Object DirectionNavigationActor$ar$analytics;
    public final Object DirectionNavigationActor$ar$cursorGranularityManager;
    public final Object DirectionNavigationActor$ar$focusProcessorForLogicalNavigation;
    public final Object DirectionNavigationActor$ar$inputModeTracker;
    public Object DirectionNavigationActor$ar$pipeline;
    public final Context DirectionNavigationActor$ar$service;
    public final Object DirectionNavigationActor$ar$state;

    public DirectionNavigationActor(Context context, Configuration configuration, WorkManagerTaskExecutor workManagerTaskExecutor, ForegroundProcessor foregroundProcessor, WorkDatabase workDatabase, WorkSpec workSpec, List list) {
        context.getClass();
        workDatabase.getClass();
        this.DirectionNavigationActor$ar$cursorGranularityManager = configuration;
        this.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation = workManagerTaskExecutor;
        this.DirectionNavigationActor$ar$analytics = foregroundProcessor;
        this.DirectionNavigationActor$ar$state = workDatabase;
        this.DirectionNavigationActor$ar$accessibilityFocusMonitor = workSpec;
        this.DirectionNavigationActor$ar$inputModeTracker = list;
        Context applicationContext = context.getApplicationContext();
        applicationContext.getClass();
        this.DirectionNavigationActor$ar$service = applicationContext;
        this.DirectionNavigationActor$ar$pipeline = new AppCompatTextClassifierHelper$Api26Impl(null);
    }

    private final boolean adjustGranularity(int i, Performance.EventId eventId) {
        AccessibilityNodeInfoCompat accessibilityFocus = ((AccessibilityFocusMonitor) this.DirectionNavigationActor$ar$accessibilityFocusMonitor).getAccessibilityFocus(true);
        boolean adjustGranularityAt = ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).adjustGranularityAt(accessibilityFocus, i, eventId);
        CursorGranularity cursorGranularity = ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).currentGranularity;
        if (adjustGranularityAt) {
            if (!cursorGranularity.isNativeMacroGranularity() && cursorGranularity != CursorGranularity.DEFAULT && cursorGranularity != CursorGranularity.WINDOWS && accessibilityFocus == null) {
                ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).adjustGranularityAt(null, -i, eventId);
                return false;
            }
            granularityUpdatedAnnouncement(((AccessibilityService) this.DirectionNavigationActor$ar$service).getString(cursorGranularity.resourceId), true, eventId);
        }
        return adjustGranularityAt;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final void granularityUpdatedAnnouncement(String str, boolean z, Performance.EventId eventId) {
        if (z) {
            ?? r4 = this.DirectionNavigationActor$ar$pipeline;
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            speakOptions.mQueueMode = 1;
            speakOptions.mFlags = 60;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r4, eventId, Feedback.speech(str, speakOptions));
        }
    }

    private final boolean navigateWithMacroOrDefaultGranularity(int i, CursorGranularity cursorGranularity, boolean z, boolean z2, boolean z3, int i2, Performance.EventId eventId) {
        int i3 = 0;
        if (TraversalStrategyUtils.convertSearchDirectionToScrollAction(i) == 0) {
            return false;
        }
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = 1;
        builder.searchDirection = i;
        builder.shouldWrap = z;
        builder.shouldScroll = z2;
        builder.useInputFocusAsPivotIfEmpty = z3;
        builder.inputMode = i2;
        CursorGranularity cursorGranularity2 = CursorGranularity.DEFAULT;
        switch (cursorGranularity.ordinal()) {
            case 5:
                i3 = 262147;
                break;
            case 6:
                i3 = 262145;
                break;
            case 7:
                i3 = 65641;
                break;
            case 8:
                i3 = 262146;
                break;
            case 9:
                i3 = 1048577;
                break;
            case 10:
                i3 = 1048578;
                break;
            case 11:
                i3 = 1048579;
                break;
            case 12:
                i3 = 65644;
                break;
        }
        builder.targetType = i3;
        builder.originalNavigationGranularity = cursorGranularity;
        return sendNavigationAction(new NavigationAction(builder), eventId);
    }

    private final boolean sendNavigationAction(NavigationAction navigationAction, Performance.EventId eventId) {
        int i;
        int i2;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        AccessibilityNodeInfoCompat root;
        int i3;
        boolean z = false;
        if (navigationAction.actionType == 0) {
            LogUtils.w("FocusProcessor-LogicalNav", "Cannot perform navigation action: action type undefined.", new Object[0]);
        } else {
            FocusProcessorForLogicalNavigation focusProcessorForLogicalNavigation = (FocusProcessorForLogicalNavigation) this.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation;
            AccessibilityNodeInfoCompat accessibilityFocus = focusProcessorForLogicalNavigation.accessibilityFocusMonitor.getAccessibilityFocus(navigationAction.useInputFocusAsPivotIfEmpty, false);
            if (accessibilityFocus == null || !accessibilityFocus.refresh()) {
                accessibilityFocus = SpannableUtils$IdentifierSpan.getRootInActiveWindow(focusProcessorForLogicalNavigation.service);
            }
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = accessibilityFocus;
            if (accessibilityNodeInfoCompat2 == null) {
                LogUtils.w("FocusProcessor-LogicalNav", "Cannot find pivot node for %s", navigationAction);
            } else {
                int i4 = navigationAction.actionType;
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = null;
                switch (i4) {
                    case 1:
                        z = focusProcessorForLogicalNavigation.onDirectionalNavigationAction(accessibilityNodeInfoCompat2, false, navigationAction, eventId);
                        break;
                    case 2:
                    case 3:
                        AccessibilityNodeInfoCompat root2 = AccessibilityNodeInfoUtils.getRoot(accessibilityNodeInfoCompat2);
                        if (root2 == null) {
                            LogUtils.w("FocusProcessor-LogicalNav", "Cannot perform jump action: unable to find root node.", new Object[0]);
                            break;
                        } else {
                            int i5 = 2;
                            if (navigationAction.actionType == 2) {
                                i = 1;
                            } else {
                                i = 2;
                            }
                            TraversalStrategy traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(root2, focusProcessorForLogicalNavigation.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, i);
                            AccessibilityNodeInfoCompat findFirstFocusInNodeTree = TraversalStrategyUtils.findFirstFocusInNodeTree(traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, root2, i, SpannableUtils$IdentifierSpan.createNodeFilter(0, traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getSpeakingNodesCache()));
                            if (findFirstFocusInNodeTree != null) {
                                if (navigationAction.actionType != 2) {
                                    i5 = 1;
                                }
                                focusProcessorForLogicalNavigation.ensureOnScreen(findFirstFocusInNodeTree, i5, eventId);
                                z = focusProcessorForLogicalNavigation.setAccessibilityFocusInternal(findFirstFocusInNodeTree, navigationAction, eventId);
                                break;
                            }
                        }
                        break;
                    case 4:
                    case 5:
                        if (i4 == 4) {
                            i2 = 4096;
                        } else if (i4 == 5) {
                            i2 = 8192;
                        } else {
                            throw new IllegalArgumentException("Unknown scroll action.");
                        }
                        int i6 = i2;
                        Filter scrollFilter = FocusProcessorForLogicalNavigation.getScrollFilter(navigationAction);
                        if (scrollFilter != null) {
                            if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat2, i6)) {
                                accessibilityNodeInfoCompat3 = accessibilityNodeInfoCompat2;
                            } else if (accessibilityNodeInfoCompat2.isAccessibilityFocused()) {
                                accessibilityNodeInfoCompat3 = AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat2, scrollFilter);
                            }
                            if (accessibilityNodeInfoCompat3 == null && (root = AccessibilityNodeInfoUtils.getRoot(accessibilityNodeInfoCompat2)) != null) {
                                accessibilityNodeInfoCompat = AccessibilityNodeInfoUtils.searchFromBfs(root, scrollFilter);
                            } else {
                                accessibilityNodeInfoCompat = accessibilityNodeInfoCompat3;
                            }
                            if (accessibilityNodeInfoCompat != null && focusProcessorForLogicalNavigation.performScrollActionInternal$ar$edu(2, accessibilityNodeInfoCompat, accessibilityNodeInfoCompat2, i6, navigationAction, 500, eventId)) {
                                z = true;
                                break;
                            }
                        }
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        AccessibilityNodeInfoCompat selfOrMatchingAncestor = AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat2, FocusProcessorForLogicalNavigation.getScrollOrPageActionFilter(navigationAction));
                        if (selfOrMatchingAncestor != null) {
                            if (SpannableUtils$IdentifierSpan.getRole(selfOrMatchingAncestor) == 16) {
                                int i7 = navigationAction.actionType;
                                if (i7 == 8 || i7 == 9) {
                                    z = focusProcessorForLogicalNavigation.performPageOrScrollAction(navigationAction, selfOrMatchingAncestor, eventId);
                                    break;
                                }
                            } else {
                                z = focusProcessorForLogicalNavigation.performPageOrScrollAction(navigationAction, selfOrMatchingAncestor, eventId);
                                break;
                            }
                        }
                        AccessibilityNodeInfoCompat root3 = AccessibilityNodeInfoUtils.getRoot(accessibilityNodeInfoCompat2);
                        int i8 = navigationAction.actionType;
                        if (i8 != 6 && i8 != 7) {
                            if (i8 == 8 || i8 == 9) {
                                accessibilityNodeInfoCompat3 = focusProcessorForLogicalNavigation.searchScrollableNodeFromBfs(root3, navigationAction, false);
                            }
                        } else {
                            accessibilityNodeInfoCompat3 = focusProcessorForLogicalNavigation.searchScrollableNodeFromBfs(root3, navigationAction, true);
                        }
                        if (accessibilityNodeInfoCompat3 != null) {
                            z = focusProcessorForLogicalNavigation.performPageOrScrollAction(navigationAction, accessibilityNodeInfoCompat3, eventId);
                            break;
                        }
                        break;
                }
            }
        }
        if (z && (i3 = navigationAction.inputMode) != -1) {
            ((InputModeTracker) this.DirectionNavigationActor$ar$inputModeTracker).setInputMode(i3);
            return true;
        }
        return z;
    }

    public final void followTo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Performance.EventId eventId) {
        CursorGranularityManager cursorGranularityManager;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2;
        Object obj = this.DirectionNavigationActor$ar$cursorGranularityManager;
        if (accessibilityNodeInfoCompat != null && (accessibilityNodeInfoCompat2 = (cursorGranularityManager = (CursorGranularityManager) obj).lockedNode) != null && !accessibilityNodeInfoCompat2.equals(accessibilityNodeInfoCompat)) {
            CursorGranularity cursorGranularity = cursorGranularityManager.savedGranularity;
            cursorGranularityManager.clear();
            cursorGranularityManager.setGranularityAt(accessibilityNodeInfoCompat, cursorGranularity, eventId);
        }
        if (i == 1) {
            ((CursorGranularityManager) obj).navigateInternal(256, eventId, false);
        } else if (i == 2) {
            CursorGranularityManager cursorGranularityManager2 = (CursorGranularityManager) obj;
            cursorGranularityManager2.currentNodeIndex = cursorGranularityManager2.navigableNodes.size() - 1;
            cursorGranularityManager2.navigateInternal(512, eventId, false);
        }
    }

    public final void jumpToBottom$ar$ds(int i, Performance.EventId eventId) {
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = 3;
        builder.inputMode = i;
        sendNavigationAction(new NavigationAction(builder), eventId);
    }

    public final void jumpToTop$ar$ds(int i, Performance.EventId eventId) {
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = 2;
        builder.inputMode = i;
        sendNavigationAction(new NavigationAction(builder), eventId);
    }

    public final void less$ar$ds(Performance.EventId eventId) {
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = 5;
        sendNavigationAction(new NavigationAction(builder), eventId);
    }

    public final void more$ar$ds(Performance.EventId eventId) {
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = 4;
        sendNavigationAction(new NavigationAction(builder), eventId);
    }

    public final boolean navigate(int i, boolean z, boolean z2, boolean z3, int i2, Performance.EventId eventId) {
        CursorGranularity cursorGranularity;
        boolean z4;
        int i3;
        CursorGranularity cursorGranularity2;
        CursorGranularity cursorGranularity3 = ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).currentGranularity;
        if (((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).isLockedToNodeOrEditingNode(((AccessibilityFocusMonitor) this.DirectionNavigationActor$ar$accessibilityFocusMonitor).getAccessibilityFocus(true)) && (cursorGranularity = ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).currentGranularity) != null && cursorGranularity.isMicroGranularity()) {
            int logicalDirection = TraversalStrategyUtils.getLogicalDirection(i, SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.DirectionNavigationActor$ar$service));
            CursorGranularityManager cursorGranularityManager = (CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = cursorGranularityManager.lockedNode;
            if (accessibilityNodeInfoCompat != null && ((accessibilityNodeInfoCompat.isEditable() || SpannableUtils$IdentifierSpan.getRole(cursorGranularityManager.lockedNode) == 4) && cursorGranularityManager.lockedNode.isFocused())) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (logicalDirection == 1) {
                i3 = 256;
            } else {
                i3 = 512;
            }
            int navigateInternal = cursorGranularityManager.navigateInternal(i3, eventId, z4);
            LogUtils.d("DirectionNavigationActor", "navigate- navigateWithMicroGranularity result = %d", Integer.valueOf(navigateInternal));
            if (navigateInternal == 1) {
                ((InputModeTracker) this.DirectionNavigationActor$ar$inputModeTracker).setInputMode(i2);
                ((TalkBackAnalytics) this.DirectionNavigationActor$ar$analytics).onMoveWithGranularity(cursorGranularity3);
                return true;
            }
            if (navigateInternal == 0 || navigateInternal == 2) {
                return false;
            }
        }
        CursorGranularityManager cursorGranularityManager2 = (CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager;
        CursorGranularity cursorGranularity4 = cursorGranularityManager2.savedGranularity;
        if (cursorGranularity4 == null) {
            cursorGranularity2 = cursorGranularityManager2.currentGranularity;
        } else {
            cursorGranularity2 = cursorGranularity4;
        }
        boolean navigateWithMacroOrDefaultGranularity = navigateWithMacroOrDefaultGranularity(i, cursorGranularity2, z, z2, z3, i2, eventId);
        if (navigateWithMacroOrDefaultGranularity) {
            ((TalkBackAnalytics) this.DirectionNavigationActor$ar$analytics).onMoveWithGranularity(cursorGranularity3);
        }
        return navigateWithMacroOrDefaultGranularity;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final boolean navigateToHtmlElement(int i, int i2, int i3, Performance.EventId eventId) {
        CursorGranularity cursorGranularity;
        if (!SpannableUtils$IdentifierSpan.isHtmlTarget(i)) {
            return false;
        }
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.searchDirection = i2;
        builder.targetType = i;
        builder.inputMode = i3;
        NavigationAction navigationAction = new NavigationAction(builder);
        AccessibilityNodeInfoCompat accessibilityFocus = ((AccessibilityFocusMonitor) this.DirectionNavigationActor$ar$accessibilityFocusMonitor).getAccessibilityFocus(navigationAction.useInputFocusAsPivotIfEmpty);
        if (accessibilityFocus == null || !accessibilityFocus.refresh()) {
            accessibilityFocus = SpannableUtils$IdentifierSpan.getRootInActiveWindow((AccessibilityService) this.DirectionNavigationActor$ar$service);
        }
        boolean $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.DirectionNavigationActor$ar$pipeline, eventId, Feedback.webDirectionHtml(accessibilityFocus, navigationAction));
        if ($default$returnFeedback) {
            Object obj = this.DirectionNavigationActor$ar$analytics;
            int i4 = navigationAction.targetType;
            if (i4 != 201) {
                if (i4 != 202) {
                    if (i4 != 65641) {
                        if (i4 != 65644) {
                            switch (i4) {
                                default:
                                    switch (i4) {
                                        case 262145:
                                            cursorGranularity = CursorGranularity.WEB_LINK;
                                            break;
                                        case 262146:
                                            cursorGranularity = CursorGranularity.WEB_CONTROL;
                                            break;
                                        case 262147:
                                            break;
                                        default:
                                            switch (i4) {
                                                case 1048577:
                                                    cursorGranularity = CursorGranularity.HEADING;
                                                    break;
                                                case 1048578:
                                                    cursorGranularity = CursorGranularity.CONTROL;
                                                    break;
                                                case 1048579:
                                                    cursorGranularity = CursorGranularity.LINK;
                                                    break;
                                                default:
                                                    cursorGranularity = CursorGranularity.DEFAULT;
                                                    break;
                                            }
                                    }
                                case 65647:
                                case 65648:
                                case 65649:
                                case 65650:
                                case 65651:
                                case 65652:
                                    cursorGranularity = CursorGranularity.WEB_HEADING;
                                    break;
                            }
                        } else {
                            cursorGranularity = CursorGranularity.WEB_LANDMARK;
                        }
                    } else {
                        cursorGranularity = CursorGranularity.WEB_LIST;
                    }
                } else {
                    cursorGranularity = CursorGranularity.CONTAINER;
                }
            } else {
                cursorGranularity = CursorGranularity.WINDOWS;
            }
            ((TalkBackAnalytics) obj).onMoveWithGranularity(cursorGranularity);
        }
        if ($default$returnFeedback && i3 != -1) {
            ((InputModeTracker) this.DirectionNavigationActor$ar$inputModeTracker).setInputMode(i3);
        }
        return $default$returnFeedback;
    }

    public final boolean navigateToNextOrPreviousWindow(int i, boolean z, int i2, Performance.EventId eventId) {
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = 1;
        builder.searchDirection = i;
        builder.useInputFocusAsPivotIfEmpty = z;
        builder.targetType = 201;
        builder.inputMode = i2;
        boolean sendNavigationAction = sendNavigationAction(new NavigationAction(builder), eventId);
        if (sendNavigationAction) {
            ((TalkBackAnalytics) this.DirectionNavigationActor$ar$analytics).onMoveWithGranularity(CursorGranularity.WINDOWS);
        }
        return sendNavigationAction;
    }

    public final boolean navigateWithSpecifiedGranularity(int i, CursorGranularity cursorGranularity, boolean z, int i2, Performance.EventId eventId) {
        boolean z2;
        if (cursorGranularity == CursorGranularity.DEFAULT) {
            return navigateWithMacroOrDefaultGranularity(i, cursorGranularity, z, true, true, i2, eventId);
        }
        CursorGranularity cursorGranularity2 = ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).currentGranularity;
        if (cursorGranularity2 == cursorGranularity) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            setGranularity$ar$ds(cursorGranularity, null, false, eventId);
        }
        boolean navigate = navigate(i, false, true, true, i2, eventId);
        if (!z2) {
            setGranularity$ar$ds(cursorGranularity2, null, false, eventId);
        }
        return navigate;
    }

    public final boolean nextContainer(int i, int i2, Performance.EventId eventId) {
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = 1;
        builder.searchDirection = i;
        builder.targetType = 202;
        builder.inputMode = i2;
        boolean sendNavigationAction = sendNavigationAction(new NavigationAction(builder), eventId);
        if (sendNavigationAction) {
            ((TalkBackAnalytics) this.DirectionNavigationActor$ar$analytics).onMoveWithGranularity(CursorGranularity.CONTAINER);
        }
        return sendNavigationAction;
    }

    public final void nextGranularity$ar$ds$a4bf5620_0(Performance.EventId eventId) {
        adjustGranularity(1, eventId);
    }

    public final void onAutoScrollFailed(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        FocusProcessorForLogicalNavigation focusProcessorForLogicalNavigation = (FocusProcessorForLogicalNavigation) this.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation;
        TaskQueue taskQueue = focusProcessorForLogicalNavigation.scrollCallback$ar$class_merging$f9bb3c40_0;
        if (taskQueue != null) {
            LogUtils.d("FocusProcessor-LogicalNav", "AutoScrollCallback onAutoScrollFailed, assumeScrollSuccess=" + taskQueue.isActive + ",actionType=" + NavigationAction.actionTypeToString(((NavigationAction) taskQueue.TaskQueue$ar$runningThread).actionType), new Object[0]);
            if (taskQueue.isActive) {
                Logger logger = Performance.DEFAULT_LOGGER;
                taskQueue.onAutoScrolled(accessibilityNodeInfoCompat, null, 0, 0);
            } else {
                NavigationAction navigationAction = (NavigationAction) taskQueue.TaskQueue$ar$runningThread;
                if (navigationAction.actionType == 1) {
                    ((FocusProcessorForLogicalNavigation) taskQueue.TaskQueue$ar$pendingTasks).onDirectionalNavigationAction(accessibilityNodeInfoCompat, true, navigationAction, null);
                }
                taskQueue.clear();
            }
            focusProcessorForLogicalNavigation.scrollCallback$ar$class_merging$f9bb3c40_0 = null;
        }
    }

    public final void previousGranularity$ar$ds(Performance.EventId eventId) {
        adjustGranularity(-1, eventId);
    }

    public final void scrollDirection$ar$ds(Performance.EventId eventId, int i) {
        NavigationAction.Builder builder = new NavigationAction.Builder();
        builder.actionType = i;
        sendNavigationAction(new NavigationAction(builder), eventId);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
    
        if (r4 == null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean searchAndFocus(boolean r7, com.google.android.accessibility.utils.Filter r8) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation
            r1 = 0
            r2 = 0
            if (r7 != 0) goto L10
            r7 = r0
            com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation r7 = (com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation) r7
            com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor r7 = r7.accessibilityFocusMonitor
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r7 = r7.getAccessibilityFocus(r1)
            goto L11
        L10:
            r7 = r2
        L11:
            r3 = 1
            if (r7 == 0) goto L22
            boolean r4 = r7.refresh()
            if (r4 != 0) goto L1b
            goto L22
        L1b:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getRoot(r7)
            if (r4 != 0) goto L2f
            goto L59
        L22:
            r7 = r0
            com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation r7 = (com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation) r7
            android.accessibilityservice.AccessibilityService r7 = r7.service
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r7 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRootInActiveWindow(r7)
            if (r7 != 0) goto L2e
            goto L59
        L2e:
            r4 = r7
        L2f:
            com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation r0 = (com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation) r0
            com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor r5 = r0.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
            com.google.android.accessibility.utils.traversal.TraversalStrategy r4 = com.google.android.accessibility.utils.traversal.TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(r4, r5, r3)
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r7 = com.google.android.accessibility.utils.traversal.TraversalStrategyUtils.searchFocus(r4, r7, r3, r8)
            if (r7 != 0) goto L3e
            goto L59
        L3e:
            com.google.android.accessibility.utils.Logger r8 = com.google.android.accessibility.utils.Performance.DEFAULT_LOGGER
            com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction$Builder r8 = new com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction$Builder
            r8.<init>()
            r8.actionType = r3
            r8.searchDirection = r3
            com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction r4 = new com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction
            r4.<init>(r8)
            boolean r8 = r0.setAccessibilityFocusInternal(r7, r4, r2)
            if (r8 != 0) goto L55
            goto L59
        L55:
            com.google.android.accessibility.utils.AccessibilityNode r2 = com.google.android.accessibility.utils.AccessibilityNode.takeOwnership(r7)
        L59:
            if (r2 == 0) goto L5c
            return r3
        L5c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.DirectionNavigationActor.searchAndFocus(boolean, com.google.android.accessibility.utils.Filter):boolean");
    }

    public final void setGranularity$ar$ds(CursorGranularity cursorGranularity, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, Performance.EventId eventId) {
        if (accessibilityNodeInfoCompat == null) {
            accessibilityNodeInfoCompat = ((AccessibilityFocusMonitor) this.DirectionNavigationActor$ar$accessibilityFocusMonitor).getAccessibilityFocus(true);
        }
        if (accessibilityNodeInfoCompat == null) {
            if (cursorGranularity == CursorGranularity.DEFAULT) {
                CursorGranularityManager cursorGranularityManager = (CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager;
                cursorGranularityManager.currentGranularity = CursorGranularity.DEFAULT;
                cursorGranularityManager.savedGranularity = CursorGranularity.DEFAULT;
                cursorGranularityManager.clear();
                return;
            }
            return;
        }
        boolean granularityAt = ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).setGranularityAt(accessibilityNodeInfoCompat, cursorGranularity, eventId);
        String string = ((AccessibilityService) this.DirectionNavigationActor$ar$service).getString(cursorGranularity.resourceId);
        granularityUpdatedAnnouncement(string, z, eventId);
        LogUtils.v("DirectionNavigationActor", "setGranularity success=%b: current=%s, granularity=%s, isFromUser=%b", Boolean.valueOf(granularityAt), accessibilityNodeInfoCompat, string, Boolean.valueOf(z));
    }

    public final void setSelectionModeActive(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Performance.EventId eventId) {
        if (!((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).isLockedToNodeOrEditingNode(accessibilityNodeInfoCompat)) {
            setGranularity$ar$ds(CursorGranularity.CHARACTER, accessibilityNodeInfoCompat, false, eventId);
        }
        ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).setSelectionModeActive(true);
    }

    public final void setSelectionModeInactive() {
        ((CursorGranularityManager) this.DirectionNavigationActor$ar$cursorGranularityManager).setSelectionModeActive(false);
    }

    public final void updateStealNextWindowNavigation$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        FocusProcessorForLogicalNavigation focusProcessorForLogicalNavigation = (FocusProcessorForLogicalNavigation) this.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation;
        if (accessibilityNodeInfoCompat == null) {
            focusProcessorForLogicalNavigation.stealWindowNavigationTarget = null;
            focusProcessorForLogicalNavigation.stealWindowNavigationTargetDirection = 0;
        } else {
            if (accessibilityNodeInfoCompat.equals(focusProcessorForLogicalNavigation.stealWindowNavigationTarget)) {
                return;
            }
            focusProcessorForLogicalNavigation.stealWindowNavigationTarget = accessibilityNodeInfoCompat;
            focusProcessorForLogicalNavigation.stealWindowNavigationTargetDirection = i;
        }
    }

    public DirectionNavigationActor(InputModeTracker inputModeTracker, GlobalVariables globalVariables, TalkBackAnalytics talkBackAnalytics, AccessibilityService accessibilityService, AppLifecycleMonitor appLifecycleMonitor, ProcessorPhoneticLetters processorPhoneticLetters, AccessibilityFocusMonitor accessibilityFocusMonitor, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.DirectionNavigationActor$ar$state = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.DirectionNavigationActor$ar$service = accessibilityService;
        this.DirectionNavigationActor$ar$inputModeTracker = inputModeTracker;
        this.DirectionNavigationActor$ar$analytics = talkBackAnalytics;
        this.DirectionNavigationActor$ar$accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.DirectionNavigationActor$ar$cursorGranularityManager = new CursorGranularityManager(globalVariables, accessibilityFocusMonitor, processorPhoneticLetters);
        this.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation = new FocusProcessorForLogicalNavigation(accessibilityService, appLifecycleMonitor, accessibilityFocusMonitor, hapticPatternParser$$ExternalSyntheticLambda1, shadowDelegateImpl);
    }
}
