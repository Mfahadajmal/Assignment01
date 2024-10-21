package com.google.android.accessibility.utils;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.Interpreters$$ExternalSyntheticLambda1;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TreeDebug {
    public static final Logger defaultLogger = new TreeDebug$$ExternalSyntheticLambda0(0);

    private static void logNodeTree(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, HashSet hashSet, Logger logger) {
        if (!hashSet.add(accessibilityNodeInfoCompat)) {
            logger.log("Cycle: %d", Integer.valueOf(accessibilityNodeInfoCompat.hashCode()));
            return;
        }
        logger.log("%s(%d)%s", str, Integer.valueOf(accessibilityNodeInfoCompat.hashCode()), nodeDebugDescription(accessibilityNodeInfoCompat));
        String valueOf = String.valueOf(str);
        int childCount = accessibilityNodeInfoCompat.getChildCount();
        for (int i = 0; i < childCount; i++) {
            String concat = valueOf.concat("  ");
            AccessibilityNodeInfoCompat child = accessibilityNodeInfoCompat.getChild(i);
            if (child == null) {
                logger.log("%sCouldn't get child %d", concat, Integer.valueOf(i));
            } else {
                logNodeTree(child, concat, hashSet, logger);
            }
        }
    }

    public static void logNodeTrees(List list, Logger logger) {
        if (list != null && !list.isEmpty()) {
            logger.log("------------Node tree------------ display %d", Integer.valueOf(SpannableUtils$IdentifierSpan.getDisplayId((AccessibilityWindowInfo) list.get(0))));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) it.next();
                if (accessibilityWindowInfo != null) {
                    logger.log("Window: %s", accessibilityWindowInfo);
                    AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(SpannableUtils$IdentifierSpan.getRoot(accessibilityWindowInfo));
                    if (compat != null) {
                        logNodeTree(compat, "", new HashSet(), logger);
                    }
                }
            }
        }
    }

    public static void logNodeTreesOnAllDisplays(AccessibilityService accessibilityService, Logger logger) {
        SpannableUtils$IdentifierSpan.forEachWindowInfoListOnAllDisplays(accessibilityService, new Interpreters$$ExternalSyntheticLambda1(logger, 6));
    }

    public static CharSequence nodeDebugDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfo accessibilityNodeInfo;
        String rowTitle;
        String columnTitle;
        String columnTitle2;
        String rowTitle2;
        StringBuilder sb = new StringBuilder();
        sb.append(accessibilityNodeInfoCompat.getWindowId());
        if (accessibilityNodeInfoCompat.getClassName() != null) {
            CharSequence className = accessibilityNodeInfoCompat.getClassName();
            int lastIndexOf = TextUtils.lastIndexOf(className, '.');
            if (lastIndexOf < 0) {
                lastIndexOf = 0;
            }
            sb.append(className, lastIndexOf, className.length());
        } else {
            sb.append("unknownClassName");
        }
        String uniqueId = accessibilityNodeInfoCompat.getUniqueId();
        if (uniqueId != null) {
            sb.append(":uniqueId(");
            sb.append(uniqueId);
            sb.append(")");
        }
        if (AccessibilityNodeInfoUtils.hasRequestInitialAccessibilityFocus(accessibilityNodeInfoCompat)) {
            sb.append(":hasRequestInitialAccessibilityFocus");
        }
        long minDurationBetweenContentChangesMillis = accessibilityNodeInfoCompat.getMinDurationBetweenContentChangesMillis();
        if (minDurationBetweenContentChangesMillis != 0) {
            sb.append(":rate-update(");
            sb.append(minDurationBetweenContentChangesMillis);
            sb.append(")");
        }
        if (!accessibilityNodeInfoCompat.isVisibleToUser()) {
            sb.append(":invisible");
        }
        Rect rect = new Rect();
        accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        sb.append(":(");
        sb.append(rect.left);
        sb.append(", ");
        sb.append(rect.top);
        sb.append(" - ");
        sb.append(rect.right);
        sb.append(", ");
        sb.append(rect.bottom);
        sb.append(")");
        if (!TextUtils.isEmpty(accessibilityNodeInfoCompat.getPaneTitle())) {
            sb.append(":PANE{");
            sb.append(accessibilityNodeInfoCompat.getPaneTitle());
            sb.append("}");
        }
        CharSequence text = AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat);
        String spansToStringForLogging = SpannableUtils$IdentifierSpan.spansToStringForLogging(text);
        if (text != null) {
            sb.append(":TEXT{");
            sb.append(text.toString().trim());
            if (!TextUtils.isEmpty(spansToStringForLogging)) {
                sb.append(spansToStringForLogging);
            }
            sb.append("}");
        }
        if (accessibilityNodeInfoCompat.getContentDescription() != null) {
            sb.append(":CONTENT{");
            sb.append(accessibilityNodeInfoCompat.getContentDescription().toString().trim());
            sb.append("}");
        }
        if (AccessibilityNodeInfoUtils.getState(accessibilityNodeInfoCompat) != null) {
            sb.append(":STATE{");
            sb.append(AccessibilityNodeInfoUtils.getState(accessibilityNodeInfoCompat).toString().trim());
            sb.append("}");
        }
        sb.append(":GRANULARITY{");
        sb.append(AccessibilityNodeInfoUtils.getMovementGranularity(accessibilityNodeInfoCompat));
        sb.append("}");
        if (accessibilityNodeInfoCompat.isCheckable()) {
            sb.append(":");
            if (accessibilityNodeInfoCompat.isChecked()) {
                sb.append("checked");
            } else {
                sb.append("not checked");
            }
        }
        int actions = accessibilityNodeInfoCompat.getActions();
        if (actions != 0) {
            sb.append("(action:");
            if ((actions & 1) != 0) {
                sb.append("FOCUS/");
            }
            if ((actions & 64) != 0) {
                sb.append("A11Y_FOCUS/");
            }
            if ((actions & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0) {
                sb.append("CLEAR_A11Y_FOCUS/");
            }
            if ((actions & 8192) != 0) {
                sb.append("SCROLL_BACKWARD/");
            }
            if ((actions & 4096) != 0) {
                sb.append("SCROLL_FORWARD/");
            }
            if ((actions & 16) != 0) {
                sb.append("CLICK/");
            }
            if ((actions & 32) != 0) {
                sb.append("LONG_CLICK/");
            }
            if ((262144 & actions) != 0) {
                sb.append("EXPAND/");
            }
            if ((524288 & actions) != 0) {
                sb.append("COLLAPSE/");
            }
            if ((actions & AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION.getId()) != 0) {
                sb.append("SCROLL_TO_POSITION/");
            }
            sb.setLength(sb.length() - 1);
            sb.append(")");
        }
        sb.append("(custom action:");
        Iterator it = accessibilityNodeInfoCompat.getActionList().iterator();
        while (it.hasNext()) {
            CharSequence label = ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) it.next()).getLabel();
            if (label != null) {
                sb.append("LABEL:");
                sb.append(label);
                sb.append("/");
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");
        if (accessibilityNodeInfoCompat.isFocusable()) {
            sb.append(":focusable");
        }
        if (accessibilityNodeInfoCompat.isScreenReaderFocusable()) {
            sb.append(":screenReaderfocusable");
        }
        if (accessibilityNodeInfoCompat.isFocused()) {
            sb.append(":focused");
        }
        if (accessibilityNodeInfoCompat.isSelected()) {
            sb.append(":selected");
        }
        if (accessibilityNodeInfoCompat.isScrollable()) {
            sb.append(":scrollable");
        }
        if (accessibilityNodeInfoCompat.isClickable()) {
            sb.append(":clickable");
        }
        if (accessibilityNodeInfoCompat.isLongClickable()) {
            sb.append(":longClickable");
        }
        if (accessibilityNodeInfoCompat.isAccessibilityFocused()) {
            sb.append(":accessibilityFocused");
        }
        if (AccessibilityNodeInfoUtils.supportsTextLocation(accessibilityNodeInfoCompat)) {
            sb.append(":supportsTextLocation");
        }
        if (!accessibilityNodeInfoCompat.isEnabled()) {
            sb.append(":disabled");
        }
        if (accessibilityNodeInfoCompat.isEditable()) {
            sb.append(":editable");
        }
        if (AccessibilityNodeInfoUtils.isTextSelectable(accessibilityNodeInfoCompat)) {
            sb.append(":textSelectable");
        }
        int liveRegion = accessibilityNodeInfoCompat.getLiveRegion();
        if (liveRegion == 1) {
            sb.append(":politeLiveRegion");
        } else if (liveRegion == 2) {
            sb.append(":assertiveLiveRegion");
        }
        if (accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null) {
            sb.append(":collection#ROWS=");
            sb.append(accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging().getRowCount());
            sb.append("#COLS=");
            sb.append(accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging().getColumnCount());
        }
        if (AccessibilityNodeInfoUtils.isHeading(accessibilityNodeInfoCompat)) {
            sb.append(":heading");
        } else if (accessibilityNodeInfoCompat.getCollectionItemInfo() != null) {
            sb.append(":item");
        }
        if (accessibilityNodeInfoCompat.getCollectionItemInfo() != null) {
            sb.append("#rowIndex=");
            sb.append(accessibilityNodeInfoCompat.getCollectionItemInfo().getRowIndex());
            sb.append(",colIndex=");
            sb.append(accessibilityNodeInfoCompat.getCollectionItemInfo().getColumnIndex());
            if (SpannableUtils$IdentifierSpan.isAtLeastT() && (accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo) != null && accessibilityNodeInfo.getCollectionItemInfo() != null) {
                AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo = accessibilityNodeInfoCompat.mInfo.getCollectionItemInfo();
                rowTitle = collectionItemInfo.getRowTitle();
                if (rowTitle != null) {
                    sb.append(",RowTitle=");
                    rowTitle2 = collectionItemInfo.getRowTitle();
                    sb.append(rowTitle2);
                }
                columnTitle = collectionItemInfo.getColumnTitle();
                if (columnTitle != null) {
                    sb.append(",ColumnTitle=");
                    columnTitle2 = collectionItemInfo.getColumnTitle();
                    sb.append(columnTitle2);
                }
            }
        }
        if (accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging() != null) {
            sb.append(":range#max=");
            sb.append(accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging().getMax());
            sb.append(",min=");
            sb.append(accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging().getMin());
            sb.append(",current=");
            sb.append(accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging().getCurrent());
            sb.append(",type=");
            sb.append(accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging().getType());
        }
        if (AccessibilityNodeInfoUtils.getViewIdText(accessibilityNodeInfoCompat) != null) {
            sb.append(":resourceId=");
            sb.append(AccessibilityNodeInfoUtils.getViewIdText(accessibilityNodeInfoCompat));
        }
        return sb.toString();
    }
}
