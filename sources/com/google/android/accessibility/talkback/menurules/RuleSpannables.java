package com.google.android.accessibility.talkback.menurules;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.OnContextMenuItemClickListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RuleSpannables extends NodeMenuRule {
    private final TalkBackAnalytics analytics;

    public RuleSpannables(TalkBackAnalytics talkBackAnalytics) {
        super(R.string.pref_show_context_menu_links_setting_key, R.bool.pref_show_context_menu_links_default);
        this.analytics = talkBackAnalytics;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return SpannableUtils$IdentifierSpan.hasTargetClickableSpanInNodeTree(accessibilityNodeInfoCompat, AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ca  */
    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List getMenuItemsForNode(android.content.Context r17, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r18, boolean r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.Class r3 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            r5 = r18
            com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.searchSpannableStringsForClickableSpanInNodeTree(r5, r4, r2, r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r4 = r2.size()
            r5 = 0
            r6 = r5
        L20:
            if (r6 >= r4) goto Ldb
            java.lang.Object r7 = r2.get(r6)
            android.text.SpannableString r7 = (android.text.SpannableString) r7
            if (r7 != 0) goto L2d
        L2a:
            r13 = r5
            goto Ld6
        L2d:
            int r8 = r7.length()
            java.lang.Class r9 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN
            java.lang.Object[] r8 = r7.getSpans(r5, r8, r9)
            if (r8 == 0) goto L2a
            int r9 = r8.length
            if (r9 == 0) goto L2a
            r9 = r5
        L3d:
            int r10 = r8.length
            if (r9 >= r10) goto L2a
            r10 = r8[r9]
            if (r10 != 0) goto L47
            r13 = r5
            goto Ld1
        L47:
            boolean r11 = r10 instanceof android.text.style.URLSpan
            r12 = 2131427752(0x7f0b01a8, float:1.847713E38)
            if (r11 == 0) goto L92
            r11 = r10
            android.text.style.URLSpan r11 = (android.text.style.URLSpan) r11
            com.google.android.accessibility.talkback.analytics.TalkBackAnalytics r14 = r0.analytics
            java.lang.String r15 = r11.getURL()
            int r13 = r7.getSpanStart(r11)
            int r5 = r7.getSpanEnd(r11)
            if (r13 < 0) goto L92
            if (r5 >= 0) goto L64
            goto L92
        L64:
            java.lang.CharSequence r5 = r7.subSequence(r13, r5)
            boolean r13 = android.text.TextUtils.isEmpty(r15)
            if (r13 != 0) goto L92
            boolean r13 = android.text.TextUtils.isEmpty(r5)
            if (r13 == 0) goto L75
            goto L92
        L75:
            android.net.Uri r13 = android.net.Uri.parse(r15)
            boolean r15 = r13.isRelative()
            if (r15 == 0) goto L80
            goto L92
        L80:
            java.lang.Class r15 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN
            com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.stripTargetSpanFromText(r5, r15)
            r15 = 0
            com.google.android.accessibility.talkback.contextmenu.ContextMenuItem r5 = com.google.android.accessibility.talkback.contextmenu.ContextMenu.createMenuItem(r1, r12, r9, r15, r5)
            com.google.android.accessibility.talkback.menurules.RuleSpannables$UrlSpanMenuItemClickListener r15 = new com.google.android.accessibility.talkback.menurules.RuleSpannables$UrlSpanMenuItemClickListener
            r15.<init>(r1, r11, r13, r14)
            r5.listener = r15
            goto L93
        L92:
            r5 = 0
        L93:
            if (r5 != 0) goto Laa
            boolean r11 = r10 instanceof android.text.style.ClickableSpan
            if (r11 == 0) goto Laa
            android.text.style.ClickableSpan r10 = (android.text.style.ClickableSpan) r10
            com.google.android.accessibility.talkback.analytics.TalkBackAnalytics r5 = r0.analytics
            int r11 = r7.getSpanStart(r10)
            int r13 = r7.getSpanEnd(r10)
            if (r11 < 0) goto Lca
            if (r13 >= 0) goto Lac
        La9:
            r5 = 0
        Laa:
            r13 = 0
            goto Lcc
        Lac:
            java.lang.CharSequence r11 = r7.subSequence(r11, r13)
            boolean r13 = android.text.TextUtils.isEmpty(r11)
            if (r13 == 0) goto Lb7
            goto La9
        Lb7:
            java.lang.Class r13 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN
            com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.stripTargetSpanFromText(r11, r13)
            r13 = 0
            com.google.android.accessibility.talkback.contextmenu.ContextMenuItem r11 = com.google.android.accessibility.talkback.contextmenu.ContextMenu.createMenuItem(r1, r12, r9, r13, r11)
            com.google.android.accessibility.talkback.menurules.RuleSpannables$ClickableSpanMenuItemClickListener r12 = new com.google.android.accessibility.talkback.menurules.RuleSpannables$ClickableSpanMenuItemClickListener
            r12.<init>(r10, r5)
            r11.listener = r12
            r5 = r11
            goto Lcc
        Lca:
            r13 = 0
            r5 = 0
        Lcc:
            if (r5 == 0) goto Ld1
            r3.add(r5)
        Ld1:
            int r9 = r9 + 1
            r5 = r13
            goto L3d
        Ld6:
            int r6 = r6 + 1
            r5 = r13
            goto L20
        Ldb:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.menurules.RuleSpannables.getMenuItemsForNode(android.content.Context, androidx.core.view.accessibility.AccessibilityNodeInfoCompat, boolean):java.util.List");
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final CharSequence getUserFriendlyMenuName(Context context) {
        return context.getString(R.string.links);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ClickableSpanMenuItemClickListener implements OnContextMenuItemClickListener {
        final TalkBackAnalytics analytics;
        final ClickableSpan clickableSpan;

        public ClickableSpanMenuItemClickListener(ClickableSpan clickableSpan, TalkBackAnalytics talkBackAnalytics) {
            this.clickableSpan = clickableSpan;
            this.analytics = talkBackAnalytics;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            this.analytics.onLocalContextMenuAction(7, -1);
            try {
                this.clickableSpan.onClick(null);
            } catch (Exception e) {
                LogUtils.e("RuleSpannables", "Failed to invoke ClickableSpan: %s\n%s", menuItem.getTitle(), e);
            }
            return true;
        }

        @Override // com.google.android.accessibility.talkback.contextmenu.OnContextMenuItemClickListener
        public final /* synthetic */ void clear() {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UrlSpanMenuItemClickListener implements OnContextMenuItemClickListener {
        final TalkBackAnalytics analytics;
        final Context context;
        final URLSpan span;
        final Uri uri;

        public UrlSpanMenuItemClickListener(Context context, URLSpan uRLSpan, Uri uri, TalkBackAnalytics talkBackAnalytics) {
            this.context = context;
            this.span = uRLSpan;
            this.uri = uri;
            this.analytics = talkBackAnalytics;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            if (this.context == null) {
                return false;
            }
            this.analytics.onLocalContextMenuAction(7, -1);
            try {
                this.span.onClick(null);
                return true;
            } catch (Exception e) {
                LogUtils.e("RuleSpannables", "Failed to invoke URLSpan: %s\n%s", menuItem.getTitle(), e);
                Intent intent = new Intent("android.intent.action.VIEW", this.uri);
                intent.addFlags(268435456);
                try {
                    this.context.startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException unused) {
                    return false;
                }
            }
        }

        @Override // com.google.android.accessibility.talkback.contextmenu.OnContextMenuItemClickListener
        public final /* synthetic */ void clear() {
        }
    }
}
