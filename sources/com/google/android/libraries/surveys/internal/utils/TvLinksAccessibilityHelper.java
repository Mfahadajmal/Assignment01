package com.google.android.libraries.surveys.internal.utils;

import _COROUTINE._BOUNDARY;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TvLinksAccessibilityHelper extends ExploreByTouchHelper {
    private final TextView view;

    public TvLinksAccessibilityHelper(TextView textView) {
        super(textView);
        this.view = textView;
    }

    private final CharSequence getTextForSpan(ClickableSpan clickableSpan) {
        CharSequence text = this.view.getText();
        if (text instanceof Spanned) {
            Spanned spanned = (Spanned) text;
            return spanned.subSequence(spanned.getSpanStart(clickableSpan), spanned.getSpanEnd(clickableSpan));
        }
        return text;
    }

    final void getBoundsForSpan$ar$ds(ClickableSpan clickableSpan, Rect rect) {
        Layout layout;
        CharSequence text = this.view.getText();
        if ((text instanceof Spanned) && (layout = this.view.getLayout()) != null) {
            Spanned spanned = (Spanned) text;
            int spanStart = spanned.getSpanStart(clickableSpan);
            int spanEnd = spanned.getSpanEnd(clickableSpan);
            float primaryHorizontal = layout.getPrimaryHorizontal(spanStart);
            float primaryHorizontal2 = layout.getPrimaryHorizontal(spanEnd);
            int lineForOffset = layout.getLineForOffset(spanStart);
            int lineForOffset2 = layout.getLineForOffset(spanEnd);
            layout.getLineBounds(lineForOffset, rect);
            rect.right = (int) primaryHorizontal2;
            if (lineForOffset == lineForOffset2) {
                rect.left = (int) primaryHorizontal;
            } else {
                rect.left = 0;
            }
            rect.top = layout.getLineTop(lineForOffset2);
            rect.bottom = layout.getLineBottom(lineForOffset2);
            TextView textView = this.view;
            rect.offset(textView.getTotalPaddingLeft(), textView.getTotalPaddingTop());
        }
    }

    final ClickableSpan getSpanForOffset(int i) {
        CharSequence text = this.view.getText();
        if (text instanceof Spanned) {
            ClickableSpan[] clickableSpanArr = (ClickableSpan[]) ((Spanned) text).getSpans(i, i, ClickableSpan.class);
            if (clickableSpanArr.length == 1) {
                return clickableSpanArr[0];
            }
            return null;
        }
        return null;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected final int getVirtualViewAt(float f, float f2) {
        CharSequence text = this.view.getText();
        if (text instanceof Spanned) {
            Spanned spanned = (Spanned) text;
            int offsetForPosition = this.view.getOffsetForPosition(f, f2);
            ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spanned.getSpans(offsetForPosition, offsetForPosition, ClickableSpan.class);
            if (clickableSpanArr.length == 1) {
                return spanned.getSpanStart(clickableSpanArr[0]);
            }
            return Integer.MIN_VALUE;
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected final void getVisibleVirtualViews(List list) {
        CharSequence text = this.view.getText();
        if (text instanceof Spanned) {
            Spanned spanned = (Spanned) text;
            for (ClickableSpan clickableSpan : (ClickableSpan[]) spanned.getSpans(0, spanned.length(), ClickableSpan.class)) {
                list.add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
            }
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.removeAction$ar$ds(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        accessibilityNodeInfoCompat.setClickable(false);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public final boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
        if (i2 == 16) {
            ClickableSpan spanForOffset = getSpanForOffset(i);
            if (spanForOffset != null) {
                spanForOffset.onClick(this.view);
                return true;
            }
            Log.e("TvLinksAccessibilityHelper", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "LinkSpan is null for offset: "));
            return false;
        }
        return false;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
        ClickableSpan spanForOffset = getSpanForOffset(i);
        if (spanForOffset != null) {
            accessibilityEvent.setContentDescription(getTextForSpan(spanForOffset));
        } else {
            Log.w("TvLinksAccessibilityHelper", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "LinkSpan is null for offset: "));
            accessibilityEvent.setContentDescription(this.view.getText());
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ClickableSpan spanForOffset = getSpanForOffset(i);
        Rect rect = new Rect();
        if (spanForOffset != null) {
            accessibilityNodeInfoCompat.setContentDescription(getTextForSpan(spanForOffset));
        } else {
            Log.w("TvLinksAccessibilityHelper", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "LinkSpan is null for offset: "));
            accessibilityNodeInfoCompat.setContentDescription(this.view.getText());
        }
        accessibilityNodeInfoCompat.setFocusable$ar$ds();
        accessibilityNodeInfoCompat.setClickable(true);
        getBoundsForSpan$ar$ds(spanForOffset, rect);
        if (!rect.isEmpty()) {
            getBoundsForSpan$ar$ds(spanForOffset, rect);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
        } else {
            Log.w("TvLinksAccessibilityHelper", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "LinkSpan bounds is empty for: "));
            rect.set(0, 0, 1, 1);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
        }
        accessibilityNodeInfoCompat.addAction(16);
    }
}
