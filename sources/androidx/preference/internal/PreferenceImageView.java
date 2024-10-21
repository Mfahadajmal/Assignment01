package androidx.preference.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.R$styleable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PreferenceImageView extends ImageView {
    private int mMaxHeight;
    private int mMaxWidth;

    public PreferenceImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView
    public final int getMaxHeight() {
        return this.mMaxHeight;
    }

    @Override // android.widget.ImageView
    public final int getMaxWidth() {
        return this.mMaxWidth;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0025, code lost:
    
        if (r0 == 0) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    @Override // android.widget.ImageView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r7)
            r1 = 0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r3) goto Lf
            if (r0 != 0) goto L1f
            r0 = r1
        Lf:
            int r4 = android.view.View.MeasureSpec.getSize(r7)
            int r5 = r6.mMaxWidth
            if (r5 == r2) goto L1f
            if (r5 < r4) goto L1b
            if (r0 != 0) goto L1f
        L1b:
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r3)
        L1f:
            int r0 = android.view.View.MeasureSpec.getMode(r8)
            if (r0 == r3) goto L28
            if (r0 != 0) goto L39
            goto L29
        L28:
            r1 = r0
        L29:
            int r0 = android.view.View.MeasureSpec.getSize(r8)
            int r4 = r6.mMaxHeight
            if (r4 == r2) goto L39
            if (r4 < r0) goto L35
            if (r1 != 0) goto L39
        L35:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r3)
        L39:
            super.onMeasure(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.internal.PreferenceImageView.onMeasure(int, int):void");
    }

    @Override // android.widget.ImageView
    public final void setMaxHeight(int i) {
        this.mMaxHeight = i;
        super.setMaxHeight(i);
    }

    @Override // android.widget.ImageView
    public final void setMaxWidth(int i) {
        this.mMaxWidth = i;
        super.setMaxWidth(i);
    }

    public PreferenceImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreferenceImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxWidth = Preference.DEFAULT_ORDER;
        this.mMaxHeight = Preference.DEFAULT_ORDER;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PreferenceImageView, i, 0);
        setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(3, Preference.DEFAULT_ORDER));
        setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(2, Preference.DEFAULT_ORDER));
        obtainStyledAttributes.recycle();
    }
}
