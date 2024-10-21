package com.google.android.accessibility.selecttospeak.ui;

import android.content.Context;
import android.support.v7.view.menu.StandardMenuPopup;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.provider.CallbackWithHandler$2;
import com.google.android.marvin.talkback.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HighlightScrollingTextView extends ScrollView {
    public int maxHeightPx;
    public View shadowBottom;
    public View shadowTop;
    public final TextView textView;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HighlightScrollingTextView(Context context) {
        this(context, null, 0, 0, 14, null);
        context.getClass();
    }

    private final int topLine() {
        if (getScrollY() == 0) {
            return 0;
        }
        return (int) Math.ceil(getScrollY() / this.textView.getLineHeight());
    }

    public final void adjustScroll(int i) {
        Layout layout = this.textView.getLayout();
        if (layout == null) {
            post(new CallbackWithHandler$2(this, i, 5));
            return;
        }
        int lineForOffset = layout.getLineForOffset(i);
        int i2 = lineForOffset - topLine();
        int height = (((((getHeight() - getPaddingTop()) - getPaddingBottom()) / this.textView.getLineHeight()) + topLine()) - 1) - lineForOffset;
        if (i2 > 0 && height > 0) {
            return;
        }
        smoothScrollTo(getScrollX(), getScrollY() + (this.textView.getLineHeight() * i2));
    }

    public final View getShadowBottom() {
        View view = this.shadowBottom;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("shadowBottom");
        return null;
    }

    public final View getShadowTop() {
        View view = this.shadowTop;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("shadowTop");
        return null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HighlightScrollingTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        context.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HighlightScrollingTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
        context.getClass();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HighlightScrollingTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        context.getClass();
        TextView textView = new TextView(context);
        this.textView = textView;
        textView.setTextColor(-1);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), (int) getResources().getDimension(R.dimen.contextual_popup_bottom_text_shadow_height));
        addView(textView);
        getViewTreeObserver().addOnGlobalLayoutListener(new StandardMenuPopup.AnonymousClass1(this, 4));
    }

    public /* synthetic */ HighlightScrollingTextView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }
}
