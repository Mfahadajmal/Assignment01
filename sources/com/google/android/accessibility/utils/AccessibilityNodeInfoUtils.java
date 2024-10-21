package com.google.android.accessibility.utils;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcelable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.SuggestionSpan;
import android.util.Pair;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.widget.GridView;
import android.widget.ListView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.compat.CompatUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityNodeInfoUtils {
    public static final Class BASE_CLICKABLE_SPAN;
    public static final Class CLASS_TOUCHWIZ_TWADAPTERVIEW;
    public static final ImmutableSet CONTAINER_ROLES;
    public static final Filter FILTER_AUTO_SCROLL;
    public static final Filter FILTER_CLICKABLE;
    public static final Filter FILTER_COLLECTION;
    public static final Filter FILTER_CONTAINER;
    public static final Filter FILTER_CONTAINER_WITH_UNFOCUSABLE_HEADING;
    public static final Filter FILTER_CONTROL;
    public static final Filter FILTER_COULD_SCROLL_BACKWARD;
    public static final Filter FILTER_COULD_SCROLL_FORWARD;
    public static final Filter FILTER_HEADING;
    public static final Filter FILTER_LINK;
    public static final Filter FILTER_NON_FOCUSABLE_NON_VISIBLE_HAS_TEXT_NODE;
    public static final Filter FILTER_NON_FOCUSABLE_VISIBLE_NODE;
    public static final Filter FILTER_SCROLLABLE;
    public static final Filter FILTER_SCROLLABLE_GRID;
    public static final Filter FILTER_SHOULD_FOCUS;
    public static final Filter FILTER_SHOULD_FOCUS_EXCEPT_WEB_VIEW;
    private static final Pattern PIN_KEY_PATTERN;
    private static final Pattern RESOURCE_NAME_SPLIT_PATTERN;
    private static final HashMap actionIdToName;
    private static final String CLASS_LISTVIEW = ListView.class.getName();
    private static final String CLASS_GRIDVIEW = GridView.class.getName();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NodeCounter extends Filter {
        public int count = 0;

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            this.count++;
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpellingSuggestion {
        private final int end;
        private final CharSequence misspelledWord;
        private final int start;
        private final SuggestionSpan suggestionSpan;

        public SpellingSuggestion() {
        }

        public final int end() {
            return this.end;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SpellingSuggestion) {
                SpellingSuggestion spellingSuggestion = (SpellingSuggestion) obj;
                if (this.start == spellingSuggestion.start() && this.end == spellingSuggestion.end() && this.misspelledWord.equals(spellingSuggestion.misspelledWord()) && this.suggestionSpan.equals(spellingSuggestion.suggestionSpan())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((((((this.start ^ 1000003) * 1000003) ^ this.end) * 1000003) ^ this.misspelledWord.hashCode()) * 1000003) ^ this.suggestionSpan.hashCode();
        }

        public final CharSequence misspelledWord() {
            return this.misspelledWord;
        }

        public final int start() {
            return this.start;
        }

        public final SuggestionSpan suggestionSpan() {
            return this.suggestionSpan;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(Locale.ENGLISH, "[%d-%d][%s]", Integer.valueOf(start()), Integer.valueOf(end()), misspelledWord()));
            for (String str : suggestionSpan().getSuggestions()) {
                sb.append(String.format(Locale.ENGLISH, "[suggestion=%s]", str));
            }
            return sb.toString();
        }

        public SpellingSuggestion(int i, int i2, CharSequence charSequence, SuggestionSpan suggestionSpan) {
            this();
            this.start = i;
            this.end = i2;
            if (charSequence == null) {
                throw new NullPointerException("Null misspelledWord");
            }
            this.misspelledWord = charSequence;
            if (suggestionSpan != null) {
                this.suggestionSpan = suggestionSpan;
                return;
            }
            throw new NullPointerException("Null suggestionSpan");
        }
    }

    static {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction9;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction10;
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN.getId()), "ACTION_SHOW_ON_SCREEN");
        hashMap.put(Integer.valueOf(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION.getId()), "ACTION_SCROLL_TO_POSITION");
        hashMap.put(Integer.valueOf(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP.getId()), "ACTION_SCROLL_UP");
        hashMap.put(Integer.valueOf(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT.getId()), "ACTION_SCROLL_LEFT");
        hashMap.put(Integer.valueOf(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN.getId()), "ACTION_SCROLL_DOWN");
        hashMap.put(Integer.valueOf(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT.getId()), "ACTION_SCROLL_RIGHT");
        hashMap.put(Integer.valueOf(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK.getId()), "ACTION_CONTEXT_CLICK");
        accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
        hashMap.put(Integer.valueOf(accessibilityAction.getId()), "ACTION_SET_PROGRESS");
        accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
        hashMap.put(Integer.valueOf(accessibilityAction2.getId()), "ACTION_MOVE_WINDOW");
        if (SpannableUtils$IdentifierSpan.isAtLeastP()) {
            accessibilityAction9 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
            hashMap.put(Integer.valueOf(accessibilityAction9.getId()), "ACTION_SHOW_TOOLTIP");
            accessibilityAction10 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
            hashMap.put(Integer.valueOf(accessibilityAction10.getId()), "ACTION_HIDE_TOOLTIP");
        }
        if (SpannableUtils$IdentifierSpan.isAtLeastQ()) {
            accessibilityAction5 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
            hashMap.put(Integer.valueOf(accessibilityAction5.getId()), "ACTION_PAGE_RIGHT");
            accessibilityAction6 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
            hashMap.put(Integer.valueOf(accessibilityAction6.getId()), "ACTION_PAGE_LEFT");
            accessibilityAction7 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
            hashMap.put(Integer.valueOf(accessibilityAction7.getId()), "ACTION_PAGE_DOWN");
            accessibilityAction8 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
            hashMap.put(Integer.valueOf(accessibilityAction8.getId()), "ACTION_PAGE_UP");
        }
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD;
            hashMap.put(Integer.valueOf(accessibilityAction3.getId()), "ACTION_PRESS_AND_HOLD");
            accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER;
            hashMap.put(Integer.valueOf(accessibilityAction4.getId()), "ACTION_IME_ENTER");
        }
        actionIdToName = hashMap;
        CLASS_TOUCHWIZ_TWADAPTERVIEW = CompatUtils.getClass("com.sec.android.touchwiz.widget.TwAdapterView");
        RESOURCE_NAME_SPLIT_PATTERN = Pattern.compile(":id/");
        BASE_CLICKABLE_SPAN = ClickableSpan.class;
        PIN_KEY_PATTERN = Pattern.compile("com.android.systemui:id/key\\d{0,9}");
        Filter filter = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.1
            @Override // com.google.android.accessibility.utils.Filter
            public final /* synthetic */ boolean accept(Object obj) {
                return AccessibilityNodeInfoUtils.isScrollable((AccessibilityNodeInfoCompat) obj);
            }
        };
        FILTER_SCROLLABLE = filter;
        FILTER_COULD_SCROLL_FORWARD = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.2
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat != null && AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, 4096)) {
                    return true;
                }
                return false;
            }
        };
        FILTER_COULD_SCROLL_BACKWARD = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.3
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat != null && AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, 8192)) {
                    return true;
                }
                return false;
            }
        };
        Filter filter2 = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.4
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat != null && AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat)) {
                    return true;
                }
                return false;
            }
        };
        FILTER_SHOULD_FOCUS = filter2;
        FILTER_SHOULD_FOCUS_EXCEPT_WEB_VIEW = filter2.and(new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.5
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                if (SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj) != 15) {
                    return true;
                }
                return false;
            }
        });
        FILTER_HEADING = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.6
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat != null && AccessibilityNodeInfoUtils.isHeading(accessibilityNodeInfoCompat)) {
                    return true;
                }
                return false;
            }
        };
        CONTAINER_ROLES = ImmutableSet.of((Object) 8, (Object) 5, (Object) 16, (Object) 30, (Object) 31, (Object) 15, (Object[]) new Integer[0]);
        FILTER_CONTAINER = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.7
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat == null) {
                    return false;
                }
                if (!AccessibilityNodeInfoUtils.CONTAINER_ROLES.contains(Integer.valueOf(SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat))) && TextUtils.isEmpty(accessibilityNodeInfoCompat.getContainerTitle())) {
                    return false;
                }
                return true;
            }
        };
        FILTER_CONTAINER_WITH_UNFOCUSABLE_HEADING = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.8
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                if (AccessibilityNodeInfoUtils.searchFromBfs((AccessibilityNodeInfoCompat) obj, AccessibilityNodeInfoUtils.FILTER_HEADING.and(new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.8.1
                    @Override // com.google.android.accessibility.utils.Filter
                    public final /* bridge */ /* synthetic */ boolean accept(Object obj2) {
                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj2;
                        if (accessibilityNodeInfoCompat.getChildCount() == 0 && !AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat)) {
                            return true;
                        }
                        return false;
                    }
                })) != null) {
                    return true;
                }
                return false;
            }
        };
        FILTER_SCROLLABLE_GRID = filter.and(new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(11)));
        FILTER_NON_FOCUSABLE_VISIBLE_NODE = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.11
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (AccessibilityNodeInfoUtils.isVisible(accessibilityNodeInfoCompat) && !AccessibilityNodeInfoUtils.isAccessibilityFocusable(accessibilityNodeInfoCompat)) {
                    return true;
                }
                return false;
            }
        };
        FILTER_NON_FOCUSABLE_NON_VISIBLE_HAS_TEXT_NODE = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.12
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (!AccessibilityNodeInfoUtils.isVisible(accessibilityNodeInfoCompat) && !AccessibilityNodeInfoUtils.isAccessibilityFocusable(accessibilityNodeInfoCompat) && !TextUtils.isEmpty(AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat))) {
                    return true;
                }
                return false;
            }
        };
        FILTER_CONTROL = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.13
            /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
            
                if (com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isLongClickable(r5) == false) goto L36;
             */
            @Override // com.google.android.accessibility.utils.Filter
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final /* bridge */ /* synthetic */ boolean accept(java.lang.Object r5) {
                /*
                    r4 = this;
                    androidx.core.view.accessibility.AccessibilityNodeInfoCompat r5 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat) r5
                    r0 = 0
                    if (r5 != 0) goto L6
                    goto L4b
                L6:
                    int r1 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRole(r5)
                    r2 = 1
                    if (r1 == r2) goto L4c
                    r3 = 7
                    if (r1 == r3) goto L4c
                    r3 = 4
                    if (r1 == r3) goto L4c
                    r3 = 2
                    if (r1 == r3) goto L4c
                    r3 = 9
                    if (r1 == r3) goto L4c
                    r3 = 13
                    if (r1 == r3) goto L4c
                    r3 = 11
                    if (r1 == r3) goto L4c
                    r3 = 3
                    if (r1 == r3) goto L4c
                    r3 = 10
                    if (r1 == r3) goto L4c
                    java.lang.Class r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.CLASS_TOUCHWIZ_TWADAPTERVIEW
                    androidx.core.view.accessibility.AccessibilityNodeInfoCompat r1 = r5.getParent()
                    if (r1 != 0) goto L32
                    goto L3e
                L32:
                    int r1 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRole(r1)
                    r3 = 8
                    if (r1 == r3) goto L4d
                    r3 = 5
                    if (r1 != r3) goto L3e
                    goto L4d
                L3e:
                    boolean r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isClickable(r5)
                    if (r1 != 0) goto L4c
                    boolean r5 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isLongClickable(r5)
                    if (r5 == 0) goto L4b
                    goto L4c
                L4b:
                    return r0
                L4c:
                    r0 = r2
                L4d:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.AnonymousClass13.accept(java.lang.Object):boolean");
            }
        };
        FILTER_LINK = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.14
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                return SpannableUtils$IdentifierSpan.hasTargetClickableSpanInNodeTree((AccessibilityNodeInfoCompat) obj, AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN);
            }
        };
        FILTER_CLICKABLE = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.15
            @Override // com.google.android.accessibility.utils.Filter
            public final /* synthetic */ boolean accept(Object obj) {
                return AccessibilityNodeInfoUtils.isClickable((AccessibilityNodeInfoCompat) obj);
            }
        };
        FILTER_AUTO_SCROLL = new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.17
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (!AccessibilityNodeInfoUtils.isScrollable(accessibilityNodeInfoCompat) || !AccessibilityNodeInfoUtils.isVisible(accessibilityNodeInfoCompat)) {
                    return false;
                }
                int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat);
                if (role != 3 && role != 8 && role != 5 && role != 30 && role != 31 && !AccessibilityNodeInfoUtils.nodeMatchesAnyClassByType(accessibilityNodeInfoCompat, AccessibilityNodeInfoUtils.CLASS_TOUCHWIZ_TWADAPTERVIEW)) {
                    return false;
                }
                return true;
            }
        };
        FILTER_COLLECTION = new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(12));
    }

    public static String actionToString(int i) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 4:
                        return "ACTION_SELECT";
                    case 8:
                        return "ACTION_CLEAR_SELECTION";
                    case 16:
                        return "ACTION_CLICK";
                    case 32:
                        return "ACTION_LONG_CLICK";
                    case 64:
                        return "ACTION_ACCESSIBILITY_FOCUS";
                    case BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE /* 128 */:
                        return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                    case 256:
                        return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                    case 512:
                        return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                    case 1024:
                        return "ACTION_NEXT_HTML_ELEMENT";
                    case 2048:
                        return "ACTION_PREVIOUS_HTML_ELEMENT";
                    case 4096:
                        return "ACTION_SCROLL_FORWARD";
                    case 8192:
                        return "ACTION_SCROLL_BACKWARD";
                    case 16384:
                        return "ACTION_COPY";
                    case 32768:
                        return "ACTION_PASTE";
                    case 65536:
                        return "ACTION_CUT";
                    case 131072:
                        return "ACTION_SET_SELECTION";
                    case 262144:
                        return "ACTION_EXPAND";
                    case 524288:
                        return "ACTION_COLLAPSE";
                    case 1048576:
                        return "ACTION_DISMISS";
                    case 2097152:
                        return "ACTION_SET_TEXT";
                    default:
                        String str = (String) actionIdToName.get(Integer.valueOf(i));
                        if (str == null) {
                            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "(unhandled action:", ")");
                        }
                        return str;
                }
            }
            return "ACTION_CLEAR_FOCUS";
        }
        return "ACTION_FOCUS";
    }

    public static CharSequence getActionLabelById(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        List actionList = accessibilityNodeInfoCompat.getActionList();
        int size = actionList.size();
        for (int i2 = 0; i2 < size; i2++) {
            AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat = (AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i2);
            if (accessibilityActionCompat.getId() == i) {
                return accessibilityActionCompat.getLabel();
            }
        }
        return "";
    }

    public static AccessibilityNodeInfoCompat getCollectionRoot(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, FILTER_COLLECTION);
    }

    public static Filter getFilterExcludingSmallTopAndBottomBorderNode(final Context context) {
        final Point screenPixelSizeWithoutWindowDecor = SpannableUtils$IdentifierSpan.getScreenPixelSizeWithoutWindowDecor(context);
        return new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.10
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (AccessibilityNodeInfoUtils.isSmallNodeInHeight(context, accessibilityNodeInfoCompat) && AccessibilityNodeInfoUtils.isTopOrBottomBorderNode(screenPixelSizeWithoutWindowDecor, accessibilityNodeInfoCompat)) {
                    return false;
                }
                return true;
            }
        };
    }

    public static CharSequence getHintText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Bundle extras;
        CharSequence hintText = accessibilityNodeInfoCompat.getHintText();
        if (TextUtils.isEmpty(hintText) && (extras = accessibilityNodeInfoCompat.getExtras()) != null) {
            return extras.getCharSequence("AccessibilityNodeInfo.hint");
        }
        return hintText;
    }

    public static Locale getLocalesByNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityWindowInfoCompat window;
        Object obj;
        LocaleList locales;
        int size;
        Locale locale;
        Locale locale2;
        if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.isAtLeastU() && (window = accessibilityNodeInfoCompat.getWindow()) != null && (obj = window.mInfo) != null) {
            locales = ((AccessibilityWindowInfo) obj).getLocales();
            Locale locale3 = Locale.getDefault();
            if (locales != null) {
                size = locales.size();
                if (size == 0) {
                    return null;
                }
                locale = locales.get(0);
                if (locale3.equals(locale)) {
                    return null;
                }
                locale2 = locales.get(0);
                return locale2;
            }
            return null;
        }
        return null;
    }

    private static AccessibilityNodeInfoCompat getMatchingAncestor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2, Filter filter) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        hashSet.add(accessibilityNodeInfoCompat);
        for (AccessibilityNodeInfoCompat parent = accessibilityNodeInfoCompat.getParent(); parent != null && hashSet.add(parent); parent = parent.getParent()) {
            if (accessibilityNodeInfoCompat2 != null && parent.equals(accessibilityNodeInfoCompat2)) {
                return null;
            }
            if (filter.accept(parent)) {
                return parent;
            }
        }
        return null;
    }

    public static AccessibilityNodeInfoCompat getMatchingDescendant(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        return getMatchingDescendant(accessibilityNodeInfoCompat, filter, new HashSet());
    }

    private static void getMatchingDescendants$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter, Set set, List list) {
        if (!set.contains(accessibilityNodeInfoCompat)) {
            set.add(accessibilityNodeInfoCompat);
            if (filter.accept(accessibilityNodeInfoCompat)) {
                list.add(accessibilityNodeInfoCompat);
            }
            int childCount = accessibilityNodeInfoCompat.getChildCount();
            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfoCompat child = accessibilityNodeInfoCompat.getChild(i);
                if (child != null) {
                    getMatchingDescendants$ar$ds(child, filter, set, list);
                }
            }
        }
    }

    public static List getMatchingDescendantsOrRoot(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        getMatchingDescendants$ar$ds(accessibilityNodeInfoCompat, filter, new HashSet(), arrayList);
        return arrayList;
    }

    public static int getMovementGranularity(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat) && TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription()) && TextUtils.isEmpty(getText(accessibilityNodeInfoCompat))) {
            return 0;
        }
        return accessibilityNodeInfoCompat.getMovementGranularities();
    }

    public static Rect getNodeBoundsInScreen(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Rect rect = new Rect();
        if (accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        }
        return rect;
    }

    public static CharSequence getNodeText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        CharSequence contentDescription = accessibilityNodeInfoCompat.getContentDescription();
        if (!TextUtils.isEmpty(contentDescription) && TextUtils.getTrimmedLength(contentDescription) > 0) {
            return contentDescription;
        }
        CharSequence text = getText(accessibilityNodeInfoCompat);
        if (TextUtils.isEmpty(text) || TextUtils.getTrimmedLength(text) <= 0) {
            return null;
        }
        return text;
    }

    public static float getProgressPercent(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat rangeInfo$ar$class_merging;
        if (accessibilityNodeInfoCompat == null || (rangeInfo$ar$class_merging = accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging()) == null) {
            return 0.0f;
        }
        float max = rangeInfo$ar$class_merging.getMax();
        float min = rangeInfo$ar$class_merging.getMin();
        float f = max - min;
        float current = rangeInfo$ar$class_merging.getCurrent();
        if (f <= 0.0f) {
            logError$ar$ds$26fd34a9_0("Range is invalid. [%f, %f]", Float.valueOf(min), Float.valueOf(max));
            return 0.0f;
        }
        if (current < min) {
            logError$ar$ds$26fd34a9_0("Current percent is out of range. Current: %f Range: [%f, %f]", Float.valueOf(current), Float.valueOf(min), Float.valueOf(max));
            return 0.0f;
        }
        if (current > max) {
            logError$ar$ds$26fd34a9_0("Current percent is out of range. Current: %f Range: [%f, %f]", Float.valueOf(current), Float.valueOf(min), Float.valueOf(max));
            return 100.0f;
        }
        return Math.max(0.0f, Math.min(1.0f, (current - min) / f)) * 100.0f;
    }

    public static AccessibilityNodeInfoCompat getRoot(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        AccessibilityWindowInfoCompat window = getWindow(accessibilityNodeInfoCompat);
        if (window != null) {
            try {
                return window.getRoot();
            } catch (SecurityException e) {
                LogUtils.e("AccessibilityWindowInfoUtils", "SecurityException occurred at AccessibilityWindowInfoUtils#getRoot(): %s", e);
                return null;
            }
        }
        HashSet hashSet = new HashSet();
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = null;
        while (true) {
            if (accessibilityNodeInfoCompat2 != null) {
                if (hashSet.contains(accessibilityNodeInfoCompat2)) {
                    return null;
                }
                hashSet.add(accessibilityNodeInfoCompat2);
            }
            AccessibilityNodeInfoCompat parent = accessibilityNodeInfoCompat.getParent();
            if (parent != null) {
                accessibilityNodeInfoCompat2 = accessibilityNodeInfoCompat;
                accessibilityNodeInfoCompat = parent;
            } else {
                return accessibilityNodeInfoCompat;
            }
        }
    }

    public static CharSequence getSelectedNodeText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CharSequence subsequenceSafe = subsequenceSafe(getText(accessibilityNodeInfoCompat), accessibilityNodeInfoCompat.getTextSelectionStart(), accessibilityNodeInfoCompat.getTextSelectionEnd());
        if (!TextUtils.isEmpty(subsequenceSafe) && TextUtils.getTrimmedLength(subsequenceSafe) > 0) {
            return subsequenceSafe;
        }
        return null;
    }

    public static Pair getSelectionIndexesSafe(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i;
        int textSelectionStart = accessibilityNodeInfoCompat.getTextSelectionStart();
        if (textSelectionStart < 0) {
            textSelectionStart = 0;
        }
        int textSelectionEnd = accessibilityNodeInfoCompat.getTextSelectionEnd();
        if (textSelectionEnd < 0) {
            textSelectionEnd = textSelectionStart;
        }
        if (textSelectionEnd < textSelectionStart) {
            i = textSelectionStart;
        } else {
            i = textSelectionEnd;
        }
        if (textSelectionEnd < textSelectionStart) {
            textSelectionStart = textSelectionEnd;
        }
        return Pair.create(Integer.valueOf(textSelectionStart), Integer.valueOf(i));
    }

    public static AccessibilityNodeInfoCompat getSelfOrMatchingAncestor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2, Filter filter) {
        return filter.accept(accessibilityNodeInfoCompat) ? accessibilityNodeInfoCompat : getMatchingAncestor(accessibilityNodeInfoCompat, accessibilityNodeInfoCompat2, filter);
    }

    public static AccessibilityNodeInfoCompat getSelfOrMatchingDescendant(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        if (filter.accept(accessibilityNodeInfoCompat)) {
            return accessibilityNodeInfoCompat;
        }
        return getMatchingDescendant(accessibilityNodeInfoCompat, filter);
    }

    public static ImmutableList getSpellingSuggestions(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return getSpellingSuggestions(context, accessibilityNodeInfoCompat, true);
    }

    public static CharSequence getState(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        CharSequence stateDescription = accessibilityNodeInfoCompat.getStateDescription();
        if (TextUtils.isEmpty(stateDescription) || TextUtils.getTrimmedLength(stateDescription) <= 0) {
            return null;
        }
        return stateDescription;
    }

    public static ImmutableList getSuggestionSpans(Context context, CharSequence charSequence, int i) {
        if (!TextUtils.isEmpty(charSequence) && !hasNoSuggestionsNeed(i)) {
            CharSequence textWithSuggestionSpans = SpellChecker.getTextWithSuggestionSpans(context, charSequence);
            if (!TextUtils.isEmpty(textWithSuggestionSpans) && (textWithSuggestionSpans instanceof Spannable)) {
                SuggestionSpan[] suggestionSpanArr = (SuggestionSpan[]) ((Spanned) textWithSuggestionSpans).getSpans(0, textWithSuggestionSpans.length(), SuggestionSpan.class);
                if (suggestionSpanArr.length == 0) {
                    int i2 = ImmutableList.ImmutableList$ar$NoOp;
                    return RegularImmutableList.EMPTY;
                }
                return ImmutableList.copyOf(suggestionSpanArr);
            }
            int i3 = ImmutableList.ImmutableList$ar$NoOp;
            return RegularImmutableList.EMPTY;
        }
        int i4 = ImmutableList.ImmutableList$ar$NoOp;
        return RegularImmutableList.EMPTY;
    }

    public static CharSequence getText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return accessibilityNodeInfoCompat.getText();
    }

    public static List getTextLocations(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CharSequence charSequence, int i, int i2) {
        AccessibilityNodeInfo accessibilityNodeInfo;
        boolean refreshWithExtraData;
        Parcelable[] parcelableArray;
        if (accessibilityNodeInfoCompat != null && i >= 0 && !TextUtils.isEmpty(charSequence)) {
            int length = charSequence.length();
            if (((i >= i2 || i2 >= length) && i != i2 && length != i2) || (accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo) == null) {
                return null;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", i);
            bundle.putInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", i2 - i);
            refreshWithExtraData = accessibilityNodeInfo.refreshWithExtraData("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY", bundle);
            if (!refreshWithExtraData || (parcelableArray = accessibilityNodeInfo.getExtras().getParcelableArray("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                if (parcelable != null) {
                    RectF rectF = (RectF) parcelable;
                    arrayList.add(new Rect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
                }
            }
            return arrayList;
        }
        return null;
    }

    public static String getViewIdText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String viewIdResourceName;
        if (accessibilityNodeInfoCompat != null && (viewIdResourceName = accessibilityNodeInfoCompat.getViewIdResourceName()) != null) {
            String[] split = RESOURCE_NAME_SPLIT_PATTERN.split(viewIdResourceName, 2);
            if (split.length == 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                return split[1].replace('_', ' ');
            }
            return null;
        }
        return null;
    }

    public static AccessibilityWindowInfo getWindow(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return null;
        }
        try {
            return accessibilityNodeInfo.getWindow();
        } catch (SecurityException unused) {
            LogUtils.e("AccessibilityNodeInfoUtils", "SecurityException in AccessibilityWindowInfo.getWindow()", new Object[0]);
            return null;
        }
    }

    public static int getWindowType(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityWindowInfoCompat window;
        if (accessibilityNodeInfoCompat == null || (window = getWindow(accessibilityNodeInfoCompat)) == null) {
            return -1;
        }
        if (isPictureInPicture(accessibilityNodeInfoCompat)) {
            return 1000;
        }
        return window.getType();
    }

    public static boolean hasAncestor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
        if (accessibilityNodeInfoCompat == null || accessibilityNodeInfoCompat2 == null || getMatchingAncestor(accessibilityNodeInfoCompat, new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.22
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                return AccessibilityNodeInfoCompat.this.equals((AccessibilityNodeInfoCompat) obj);
            }
        }) == null) {
            return false;
        }
        return true;
    }

    public static boolean hasMatchingAncestor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        if (accessibilityNodeInfoCompat != null && getMatchingAncestor(accessibilityNodeInfoCompat, filter) != null) {
            return true;
        }
        return false;
    }

    public static boolean hasMatchingDescendant(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        if (accessibilityNodeInfoCompat != null && getMatchingDescendant(accessibilityNodeInfoCompat, filter) != null) {
            return true;
        }
        return false;
    }

    private static boolean hasNoSuggestionsNeed(int i) {
        if (i == 524288) {
            return true;
        }
        return false;
    }

    public static boolean hasRequestInitialAccessibilityFocus(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        boolean booleanProperty;
        AccessibilityNodeInfo accessibilityNodeInfo;
        boolean hasRequestInitialAccessibilityFocus;
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 34) {
            booleanProperty = AccessibilityNodeInfoCompat.Api34Impl.hasRequestInitialAccessibilityFocus(accessibilityNodeInfoCompat.mInfo);
        } else {
            booleanProperty = accessibilityNodeInfoCompat.getBooleanProperty(32);
        }
        if (!booleanProperty && SpannableUtils$IdentifierSpan.isAtLeastU() && (accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo) != null) {
            hasRequestInitialAccessibilityFocus = accessibilityNodeInfo.hasRequestInitialAccessibilityFocus();
            return hasRequestInitialAccessibilityFocus;
        }
        return booleanProperty;
    }

    private static boolean hasStateDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (!TextUtils.isEmpty(accessibilityNodeInfoCompat.getStateDescription()) || accessibilityNodeInfoCompat.isCheckable()) {
            return true;
        }
        if (!hasValidRangeInfo(accessibilityNodeInfoCompat)) {
            return false;
        }
        return true;
    }

    private static boolean hasText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null || accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null) {
            return false;
        }
        if (!TextUtils.isEmpty(getText(accessibilityNodeInfoCompat)) || !TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription())) {
            return true;
        }
        if (TextUtils.isEmpty(accessibilityNodeInfoCompat.getHintText())) {
            return false;
        }
        return true;
    }

    public static boolean hasUsableCollectionItemInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
        if (accessibilityNodeInfoCompat.getCollectionItemInfo() != null && accessibilityNodeInfoCompat.getCollectionItemInfo().getRowIndex() >= 0 && accessibilityNodeInfoCompat.getCollectionItemInfo().getColumnIndex() >= 0 && accessibilityNodeInfoCompat2.getCollectionInfo$ar$class_merging() != null && accessibilityNodeInfoCompat2.getCollectionInfo$ar$class_merging().getRowCount() > 0 && accessibilityNodeInfoCompat2.getCollectionInfo$ar$class_merging().getColumnCount() > 0 && accessibilityNodeInfoCompat.getCollectionItemInfo().getRowIndex() < accessibilityNodeInfoCompat2.getCollectionInfo$ar$class_merging().getRowCount() && accessibilityNodeInfoCompat.getCollectionItemInfo().getColumnIndex() < accessibilityNodeInfoCompat2.getCollectionInfo$ar$class_merging().getColumnCount()) {
            return true;
        }
        return false;
    }

    public static boolean hasValidRangeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat rangeInfo$ar$class_merging = accessibilityNodeInfoCompat.getRangeInfo$ar$class_merging();
        if (rangeInfo$ar$class_merging == null) {
            return false;
        }
        float max = rangeInfo$ar$class_merging.getMax();
        float min = rangeInfo$ar$class_merging.getMin();
        float f = max - min;
        float current = rangeInfo$ar$class_merging.getCurrent();
        if (f <= 0.0f || current < min || current > max) {
            return false;
        }
        return true;
    }

    public static boolean isAccessibilityFocusable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (isFocusableOrClickable(accessibilityNodeInfoCompat)) {
            return true;
        }
        if (isTopLevelScrollItem(accessibilityNodeInfoCompat) && isSpeakingNode(accessibilityNodeInfoCompat, null, new HashSet())) {
            return true;
        }
        return false;
    }

    public static boolean isActionableForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (isClickable(accessibilityNodeInfoCompat) || isLongClickable(accessibilityNodeInfoCompat) || accessibilityNodeInfoCompat.isFocusable()) {
            return true;
        }
        if (WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
            return supportsAnyAction(accessibilityNodeInfoCompat, 1);
        }
        return supportsAnyAction(accessibilityNodeInfoCompat, 1, 1024, 2048);
    }

    public static boolean isClickable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (!accessibilityNodeInfoCompat.isClickable() && !supportsAnyAction(accessibilityNodeInfoCompat, 16)) {
            return false;
        }
        return true;
    }

    public static boolean isCollapsible(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return supportsAnyAction(accessibilityNodeInfoCompat, 524288);
    }

    public static boolean isEmptyEditTextRegardlessOfHint(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null || !accessibilityNodeInfoCompat.isEditable() || (!TextUtils.isEmpty(getText(accessibilityNodeInfoCompat)) && supportsAction(accessibilityNodeInfoCompat, 131072))) {
            return false;
        }
        return true;
    }

    public static boolean isExpandable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return supportsAnyAction(accessibilityNodeInfoCompat, 262144);
    }

    public static boolean isFocusable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (!accessibilityNodeInfoCompat.isFocusable() && !supportsAnyAction(accessibilityNodeInfoCompat, 1)) {
            return false;
        }
        return true;
    }

    private static boolean isFocusableOrClickable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null || !isVisible(accessibilityNodeInfoCompat)) {
            return false;
        }
        if (!accessibilityNodeInfoCompat.isScreenReaderFocusable() && !isActionableForAccessibility(accessibilityNodeInfoCompat)) {
            return false;
        }
        return true;
    }

    public static boolean isHeading(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return accessibilityNodeInfoCompat.isHeading();
    }

    public static boolean isInWindow(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        AccessibilityNodeInfoCompat root;
        if (accessibilityWindowInfoCompat == null || (root = accessibilityWindowInfoCompat.getRoot()) == null || getMatchingDescendant(root, new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.23
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                return AccessibilityNodeInfoCompat.this.equals((AccessibilityNodeInfoCompat) obj);
            }
        }) == null) {
            return false;
        }
        return true;
    }

    public static boolean isKeyboard(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityWindowInfoCompat window;
        if (accessibilityNodeInfoCompat == null || (window = getWindow(accessibilityNodeInfoCompat)) == null || SpannableUtils$IdentifierSpan.getType(window) != 2) {
            return false;
        }
        return true;
    }

    public static boolean isLongClickable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (!accessibilityNodeInfoCompat.isLongClickable() && !supportsAnyAction(accessibilityNodeInfoCompat, 32)) {
            return false;
        }
        return true;
    }

    public static boolean isNonEditableSelectableText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        boolean isTextSelectable;
        if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.isAtLeastT() && !accessibilityNodeInfoCompat.isEditable()) {
            isTextSelectable = accessibilityNodeInfoCompat.mInfo.isTextSelectable();
            if (isTextSelectable) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOrHasMatchingAncestor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        if (accessibilityNodeInfoCompat != null && getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, filter) != null) {
            return true;
        }
        return false;
    }

    public static boolean isPictureInPicture(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
        if (accessibilityNodeInfo != null && SpannableUtils$IdentifierSpan.isPictureInPicture(getWindow(accessibilityNodeInfo))) {
            return true;
        }
        return false;
    }

    public static boolean isPinEntry(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat != null && TextUtils.equals(accessibilityNodeInfoCompat.getViewIdResourceName(), "com.android.systemui:id/pinEntry")) {
            return true;
        }
        return false;
    }

    public static boolean isPinKey(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        String viewIdResourceName = accessibilityNodeInfoCompat.getViewIdResourceName();
        if (TextUtils.isEmpty(viewIdResourceName) || !PIN_KEY_PATTERN.matcher(viewIdResourceName).matches()) {
            return false;
        }
        return true;
    }

    public static boolean isScrollItem(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int role;
        if (accessibilityNodeInfoCompat == null || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 3) {
            return false;
        }
        if (!isScrollable(accessibilityNodeInfoCompat) && (role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat)) != 8 && role != 5 && role != 30 && role != 31 && !nodeMatchesAnyClassByType(accessibilityNodeInfoCompat, CLASS_TOUCHWIZ_TWADAPTERVIEW)) {
            return false;
        }
        return true;
    }

    public static boolean isScrollable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat.AccessibilityActionCompat[] accessibilityActionCompatArr = {AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT};
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        List actionList = accessibilityNodeInfoCompat.getActionList();
        for (int i = 0; i < 6; i++) {
            if (actionList.contains(accessibilityActionCompatArr[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSelfOrAncestorFocused(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (!accessibilityNodeInfoCompat.isAccessibilityFocused() && !hasMatchingAncestor(accessibilityNodeInfoCompat, new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.19
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj;
                if (accessibilityNodeInfoCompat2 != null && accessibilityNodeInfoCompat2.isAccessibilityFocused()) {
                    return true;
                }
                return false;
            }
        })) {
            return false;
        }
        return true;
    }

    public static boolean isSmallNodeInHeight(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Rect rect = new Rect();
        accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        if (rect.height() < SpannableUtils$IdentifierSpan.dpToPx(context, 32)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0139, code lost:
    
        com.google.android.libraries.accessibility.utils.log.LogUtils.v("AccessibilityNodeInfoUtils", "Speaking, has non-actionable speaking children", new java.lang.Object[0]);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean isSpeakingNode(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r8, java.util.Map r9, java.util.Set r10) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isSpeakingNode(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, java.util.Map, java.util.Set):boolean");
    }

    public static boolean isTextSelectable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        boolean z;
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4 && !accessibilityNodeInfoCompat.isEditable()) {
            z = false;
        } else {
            z = true;
        }
        boolean isNonEditableSelectableText = isNonEditableSelectableText(accessibilityNodeInfoCompat);
        if (!z && !isNonEditableSelectableText) {
            return false;
        }
        return true;
    }

    public static boolean isTopLevelScrollItem(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat != null && isVisible(accessibilityNodeInfoCompat)) {
            return isScrollItem(accessibilityNodeInfoCompat.getParent());
        }
        return false;
    }

    public static boolean isTopOrBottomBorderNode(Point point, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final Rect rect = new Rect();
        accessibilityNodeInfoCompat.getBoundsInScreen(rect);
        if (rect.top > 0 && rect.bottom < point.y) {
            final Rect rect2 = new Rect();
            return hasMatchingAncestor(accessibilityNodeInfoCompat, new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.24
                @Override // com.google.android.accessibility.utils.Filter
                public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj;
                    if (!AccessibilityNodeInfoUtils.isScrollItem(accessibilityNodeInfoCompat2)) {
                        return false;
                    }
                    accessibilityNodeInfoCompat2.getBoundsInScreen(rect2);
                    if (rect2.top != rect.top) {
                        if (rect2.bottom != rect.bottom) {
                            return false;
                        }
                    }
                    return true;
                }
            });
        }
        return true;
    }

    public static boolean isVisible(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (accessibilityNodeInfoCompat.isVisibleToUser()) {
            return true;
        }
        if (!WebInterfaceUtils.isWebContainer(accessibilityNodeInfoCompat) || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 15) {
            return false;
        }
        return true;
    }

    private static void logError$ar$ds$26fd34a9_0(String str, Object... objArr) {
        LogUtils.e("AccessibilityNodeInfoUtils", "getProgressPercent() " + String.format(str, objArr), new Object[0]);
    }

    private static void logShouldFocusNode(boolean z, Integer num, String str, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (z) {
            num.intValue();
            LogUtils.v("AccessibilityNodeInfoUtils", "%s %s", str, accessibilityNodeInfoCompat);
        }
    }

    public static boolean nodeMatchesAnyClassByType(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Class... clsArr) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (!ClassLoadingCache.checkInstanceOf(accessibilityNodeInfoCompat.getClassName(), clsArr[0])) {
            return false;
        }
        return true;
    }

    public static boolean nodeMatchesClassByType(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Class cls) {
        if (accessibilityNodeInfoCompat != null && cls != null) {
            CharSequence className = accessibilityNodeInfoCompat.getClassName();
            if (TextUtils.equals(className, cls.getName())) {
                return true;
            }
            return ClassLoadingCache.checkInstanceOf(className, cls);
        }
        return false;
    }

    private static String printId(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return String.format("Node(id=%s class=%s)", Integer.valueOf(accessibilityNodeInfoCompat.hashCode()), accessibilityNodeInfoCompat.getClassName());
    }

    public static int roundForProgressPercent(double d) {
        if (d < 0.0d) {
            return 0;
        }
        if (d > 0.0d && d < 1.0d) {
            return 1;
        }
        if (d > 99.0d && d < 100.0d) {
            return 99;
        }
        if (d > 100.0d) {
            return 100;
        }
        return (int) Math.round(d);
    }

    public static AccessibilityNodeInfoCompat searchFromBfs(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        return searchFromBfs(accessibilityNodeInfoCompat, filter, null);
    }

    public static boolean shouldFocusNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return shouldFocusNode(accessibilityNodeInfoCompat, null, true);
    }

    public static CharSequence subsequenceSafe(CharSequence charSequence, int i, int i2) {
        int i3;
        if (charSequence == null) {
            return "";
        }
        if (i2 < i) {
            i3 = i;
        } else {
            i3 = i2;
        }
        if (i2 < i) {
            i = i2;
        }
        if (i < 0) {
            i = 0;
        } else if (i > charSequence.length()) {
            i = charSequence.length();
        }
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 > charSequence.length()) {
            i3 = charSequence.length();
        }
        return charSequence.subSequence(i, i3);
    }

    public static boolean supportsAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        List actionList = accessibilityNodeInfoCompat.getActionList();
        int size = actionList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((AccessibilityNodeInfoCompat.AccessibilityActionCompat) actionList.get(i2)).getId() == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean supportsAnyAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int... iArr) {
        if (accessibilityNodeInfoCompat != null) {
            int actions = accessibilityNodeInfoCompat.getActions();
            for (int i : iArr) {
                if ((actions & i) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0006, code lost:
    
        r2 = r2.getAvailableExtraData();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean supportsTextLocation(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r2) {
        /*
            android.view.accessibility.AccessibilityNodeInfo r2 = r2.mInfo
            r0 = 0
            if (r2 != 0) goto L6
            return r0
        L6:
            java.util.List r2 = org.chromium.base.BundleUtils$$ExternalSyntheticApiModelOutline0.m272m(r2)
            if (r2 == 0) goto L16
            java.lang.String r1 = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY"
            boolean r2 = r2.contains(r1)
            if (r2 == 0) goto L16
            r2 = 1
            return r2
        L16:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.supportsTextLocation(androidx.core.view.accessibility.AccessibilityNodeInfoCompat):boolean");
    }

    public static AccessibilityNodeInfoCompat toCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return null;
        }
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
    }

    public static String toStringShort(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CharSequence charSequence;
        if (accessibilityNodeInfoCompat == null) {
            return "null";
        }
        String optionalInt = StringBuilderUtils.optionalInt("id", accessibilityNodeInfoCompat.hashCode(), -1);
        String optionalText = StringBuilderUtils.optionalText("class", accessibilityNodeInfoCompat.getClassName());
        String optionalText2 = StringBuilderUtils.optionalText("package", accessibilityNodeInfoCompat.getPackageName());
        if (getText(accessibilityNodeInfoCompat) == null) {
            charSequence = null;
        } else if (FeatureSupport.logcatIncludePsi()) {
            charSequence = getText(accessibilityNodeInfoCompat);
        } else {
            charSequence = "***";
        }
        return StringBuilderUtils.joinFields("AccessibilityNodeInfoCompat", optionalInt, optionalText, optionalText2, StringBuilderUtils.optionalText("text", charSequence), StringBuilderUtils.optionalText("state", accessibilityNodeInfoCompat.getStateDescription()), StringBuilderUtils.optionalText("content", accessibilityNodeInfoCompat.getContentDescription()), StringBuilderUtils.optionalText("viewIdResName", accessibilityNodeInfoCompat.getViewIdResourceName()), StringBuilderUtils.optionalText("hint", accessibilityNodeInfoCompat.getHintText()), StringBuilderUtils.optionalTag("enabled", accessibilityNodeInfoCompat.isEnabled()), StringBuilderUtils.optionalTag("checkable", accessibilityNodeInfoCompat.isCheckable()), StringBuilderUtils.optionalTag("checked", accessibilityNodeInfoCompat.isChecked()), StringBuilderUtils.optionalTag("accessibilityFocused", accessibilityNodeInfoCompat.isAccessibilityFocused()), StringBuilderUtils.optionalTag("focusable", isFocusable(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("screenReaderFocusable", accessibilityNodeInfoCompat.isScreenReaderFocusable()), StringBuilderUtils.optionalTag("focused", accessibilityNodeInfoCompat.isFocused()), StringBuilderUtils.optionalTag("selected", accessibilityNodeInfoCompat.isSelected()), StringBuilderUtils.optionalTag("clickable", isClickable(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("longClickable", isLongClickable(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("password", accessibilityNodeInfoCompat.isPassword()), StringBuilderUtils.optionalTag("textEntryKey", accessibilityNodeInfoCompat.isTextEntryKey()), StringBuilderUtils.optionalTag("scrollable", isScrollable(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("heading", accessibilityNodeInfoCompat.isHeading()), StringBuilderUtils.optionalTag("collapsible", isCollapsible(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("expandable", isExpandable(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("dismissable", supportsAnyAction(accessibilityNodeInfoCompat, 1048576)), StringBuilderUtils.optionalTag("pinKey", isPinKey(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("pinEntry", isPinEntry(accessibilityNodeInfoCompat)), StringBuilderUtils.optionalTag("visible", accessibilityNodeInfoCompat.isVisibleToUser()));
    }

    private static AccessibilityNodeInfoCompat getMatchingDescendant(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter, HashSet hashSet) {
        if (hashSet.contains(accessibilityNodeInfoCompat)) {
            return null;
        }
        hashSet.add(accessibilityNodeInfoCompat);
        int childCount = accessibilityNodeInfoCompat.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfoCompat child = accessibilityNodeInfoCompat.getChild(i);
            if (child != null) {
                if (filter.accept(child)) {
                    return child;
                }
                AccessibilityNodeInfoCompat matchingDescendant = getMatchingDescendant(child, filter, hashSet);
                if (matchingDescendant != null) {
                    return matchingDescendant;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
    
        if (java.lang.Character.isLetterOrDigit(r13.charAt(r1)) == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006c, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0069, code lost:
    
        if (java.lang.Character.isLetterOrDigit(r13.charAt(r0)) != false) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.common.collect.ImmutableList getSpellingSuggestions(android.content.Context r13, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getSpellingSuggestions(android.content.Context, androidx.core.view.accessibility.AccessibilityNodeInfoCompat, boolean):com.google.common.collect.ImmutableList");
    }

    public static AccessibilityNodeInfoCompat searchFromBfs(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter, Filter filter2) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        HashSet hashSet = new HashSet();
        arrayDeque.add(accessibilityNodeInfoCompat);
        while (!arrayDeque.isEmpty()) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) arrayDeque.removeFirst();
            hashSet.add(accessibilityNodeInfoCompat2);
            if (filter2 == null || !filter2.accept(accessibilityNodeInfoCompat2)) {
                if (filter.accept(accessibilityNodeInfoCompat2)) {
                    return accessibilityNodeInfoCompat2;
                }
                int childCount = accessibilityNodeInfoCompat2.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    AccessibilityNodeInfoCompat child = accessibilityNodeInfoCompat2.getChild(i);
                    if (child != null && !hashSet.contains(child)) {
                        arrayDeque.addLast(child);
                    }
                }
            }
        }
        return null;
    }

    public static boolean shouldFocusNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Map map) {
        return shouldFocusNode(accessibilityNodeInfoCompat, map, true);
    }

    public static AccessibilityNodeInfoCompat getSelfOrMatchingAncestor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return filter.accept(accessibilityNodeInfoCompat) ? accessibilityNodeInfoCompat : getMatchingAncestor(accessibilityNodeInfoCompat, filter);
    }

    public static AccessibilityWindowInfoCompat getWindow(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        try {
            return accessibilityNodeInfoCompat.getWindow();
        } catch (SecurityException unused) {
            LogUtils.e("AccessibilityNodeInfoUtils", "SecurityException in AccessibilityWindowInfoCompat.getWindow()", new Object[0]);
            return null;
        }
    }

    public static boolean shouldFocusNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, final Map map, boolean z) {
        if (accessibilityNodeInfoCompat == null) {
            LogUtils.v("AccessibilityNodeInfoUtils", "Don't focus, node=null", new Object[0]);
            return false;
        }
        if (WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
            AccessibilityNodeInfoCompat selfOrMatchingAncestor = WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat) ? getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, WebInterfaceUtils.FILTER_WEB_VIEW_CONTAINER) : null;
            return selfOrMatchingAncestor != null && selfOrMatchingAncestor.isVisibleToUser();
        }
        if (!isVisible(accessibilityNodeInfoCompat)) {
            logShouldFocusNode(z, 3, "Don't focus, is not visible: ", accessibilityNodeInfoCompat);
            return false;
        }
        if (isPictureInPicture(accessibilityNodeInfoCompat)) {
            return true;
        }
        AccessibilityWindowInfoCompat window = getWindow(accessibilityNodeInfoCompat);
        if (window != null) {
            Rect rect = new Rect();
            window.getBoundsInScreen(rect);
            Rect rect2 = new Rect();
            accessibilityNodeInfoCompat.getBoundsInScreen(rect2);
            if (rect.equals(rect2) && accessibilityNodeInfoCompat.getChildCount() > 0 && !isFocusableOrClickable(accessibilityNodeInfoCompat)) {
                logShouldFocusNode(z, 2, "Don't focus, bounds are same as window root node bounds, node has children and is neither actionable nor focusable: ", accessibilityNodeInfoCompat);
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        boolean z2 = isFocusableOrClickable(accessibilityNodeInfoCompat) || (isTopLevelScrollItem(accessibilityNodeInfoCompat) && isSpeakingNode(accessibilityNodeInfoCompat, null, hashSet));
        if (!z) {
            LogUtils.d("AccessibilityNodeInfoUtils", "checkChildren=false and isAccessibilityFocusable=%s", Boolean.valueOf(z2));
            return z2;
        }
        if (z2) {
            hashSet.clear();
            if (accessibilityNodeInfoCompat.getChildCount() == 0) {
                logShouldFocusNode(true, -1, "Focus, is focusable and cannot keep search children: ", accessibilityNodeInfoCompat);
                return true;
            }
            if (isSpeakingNode(accessibilityNodeInfoCompat, map, hashSet)) {
                logShouldFocusNode(true, -1, "Focus, is focusable and has something to speak: ", accessibilityNodeInfoCompat);
                return true;
            }
            logShouldFocusNode(true, 1, "Don't focus, is focusable but has nothing to speak: ", accessibilityNodeInfoCompat);
            return false;
        }
        if (!hasMatchingAncestor(accessibilityNodeInfoCompat, new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.18
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                return AccessibilityNodeInfoUtils.shouldFocusNode((AccessibilityNodeInfoCompat) obj, map, false);
            }
        }) && (hasText(accessibilityNodeInfoCompat) || hasStateDescription(accessibilityNodeInfoCompat))) {
            logShouldFocusNode(true, -1, "Focus, has text and no focusable ancestors: ", accessibilityNodeInfoCompat);
            return true;
        }
        logShouldFocusNode(true, 0, "Don't focus, failed all focusability tests: ", accessibilityNodeInfoCompat);
        return false;
    }

    public static AccessibilityNodeInfoCompat getMatchingAncestor(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Filter filter) {
        return getMatchingAncestor(accessibilityNodeInfoCompat, null, filter);
    }
}
