package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.CheckedTextView;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AppCompatCheckedTextViewHelper {
    private final ColorStateList mCheckMarkTintList = null;
    private final PorterDuff.Mode mCheckMarkTintMode = null;
    private final boolean mHasCheckMarkTint = false;
    private final boolean mHasCheckMarkTintMode = false;
    public boolean mSkipNextApply;
    private final CheckedTextView mView;

    public AppCompatCheckedTextViewHelper(CheckedTextView checkedTextView) {
        this.mView = checkedTextView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void applyCheckMarkTint() {
        this.mView.getCheckMarkDrawable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0060 A[Catch: all -> 0x0083, TryCatch #0 {all -> 0x0083, blocks: (B:3:0x0026, B:5:0x002c, B:8:0x0032, B:9:0x0059, B:11:0x0060, B:12:0x0069, B:14:0x0070, B:21:0x0040, B:23:0x0046, B:25:0x004c), top: B:2:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0070 A[Catch: all -> 0x0083, TRY_LEAVE, TryCatch #0 {all -> 0x0083, blocks: (B:3:0x0026, B:5:0x002c, B:8:0x0032, B:9:0x0059, B:11:0x0060, B:12:0x0069, B:14:0x0070, B:21:0x0040, B:23:0x0046, B:25:0x004c), top: B:2:0x0026 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void loadFromAttributes$ar$ds(android.util.AttributeSet r12) {
        /*
            r11 = this;
            android.widget.CheckedTextView r0 = r11.mView
            android.content.Context r0 = r0.getContext()
            int[] r1 = android.support.v7.appcompat.R$styleable.CheckedTextView
            r2 = 2130968786(0x7f0400d2, float:1.7546235E38)
            r3 = 0
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(r0, r12, r1, r2, r3)
            java.lang.Object r1 = r0.ExecutionList$RunnableExecutorPair$ar$runnable
            android.widget.CheckedTextView r4 = r11.mView
            android.content.Context r5 = r4.getContext()
            int[] r6 = android.support.v7.appcompat.R$styleable.CheckedTextView
            r8 = r1
            android.content.res.TypedArray r8 = (android.content.res.TypedArray) r8
            r9 = 2130968786(0x7f0400d2, float:1.7546235E38)
            r10 = 0
            r7 = r12
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r4, r5, r6, r7, r8, r9, r10)
            r12 = 1
            boolean r1 = r0.hasValue(r12)     // Catch: java.lang.Throwable -> L83
            if (r1 == 0) goto L40
            int r12 = r0.getResourceId(r12, r3)     // Catch: java.lang.Throwable -> L83
            if (r12 == 0) goto L40
            android.widget.CheckedTextView r1 = r11.mView     // Catch: android.content.res.Resources.NotFoundException -> L40 java.lang.Throwable -> L83
            android.content.Context r2 = r1.getContext()     // Catch: android.content.res.Resources.NotFoundException -> L40 java.lang.Throwable -> L83
            android.graphics.drawable.Drawable r12 = android.support.v7.app.AppCompatDelegate.Api33Impl.getDrawable(r2, r12)     // Catch: android.content.res.Resources.NotFoundException -> L40 java.lang.Throwable -> L83
            r1.setCheckMarkDrawable(r12)     // Catch: android.content.res.Resources.NotFoundException -> L40 java.lang.Throwable -> L83
            goto L59
        L40:
            boolean r12 = r0.hasValue(r3)     // Catch: java.lang.Throwable -> L83
            if (r12 == 0) goto L59
            int r12 = r0.getResourceId(r3, r3)     // Catch: java.lang.Throwable -> L83
            if (r12 == 0) goto L59
            android.widget.CheckedTextView r1 = r11.mView     // Catch: java.lang.Throwable -> L83
            android.content.Context r2 = r1.getContext()     // Catch: java.lang.Throwable -> L83
            android.graphics.drawable.Drawable r12 = android.support.v7.app.AppCompatDelegate.Api33Impl.getDrawable(r2, r12)     // Catch: java.lang.Throwable -> L83
            r1.setCheckMarkDrawable(r12)     // Catch: java.lang.Throwable -> L83
        L59:
            r12 = 2
            boolean r1 = r0.hasValue(r12)     // Catch: java.lang.Throwable -> L83
            if (r1 == 0) goto L69
            android.widget.CheckedTextView r1 = r11.mView     // Catch: java.lang.Throwable -> L83
            android.content.res.ColorStateList r12 = r0.getColorStateList(r12)     // Catch: java.lang.Throwable -> L83
            r1.setCheckMarkTintList(r12)     // Catch: java.lang.Throwable -> L83
        L69:
            r12 = 3
            boolean r1 = r0.hasValue(r12)     // Catch: java.lang.Throwable -> L83
            if (r1 == 0) goto L7f
            android.widget.CheckedTextView r1 = r11.mView     // Catch: java.lang.Throwable -> L83
            r2 = -1
            int r12 = r0.getInt(r12, r2)     // Catch: java.lang.Throwable -> L83
            r2 = 0
            android.graphics.PorterDuff$Mode r12 = _COROUTINE._BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(r12, r2)     // Catch: java.lang.Throwable -> L83
            r1.setCheckMarkTintMode(r12)     // Catch: java.lang.Throwable -> L83
        L7f:
            r0.recycle()
            return
        L83:
            r12 = move-exception
            r0.recycle()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatCheckedTextViewHelper.loadFromAttributes$ar$ds(android.util.AttributeSet):void");
    }
}
