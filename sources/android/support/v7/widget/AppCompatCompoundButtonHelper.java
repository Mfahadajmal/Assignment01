package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.widget.CompoundButtonCompat$Api23Impl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatCompoundButtonHelper {
    public ColorStateList mButtonTintList = null;
    public PorterDuff.Mode mButtonTintMode = null;
    public boolean mHasButtonTint = false;
    public boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    public AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    public final void applyButtonTint() {
        Drawable buttonDrawable = CompoundButtonCompat$Api23Impl.getButtonDrawable(this.mView);
        if (buttonDrawable != null) {
            if (this.mHasButtonTint || this.mHasButtonTintMode) {
                Drawable mutate = buttonDrawable.mutate();
                if (this.mHasButtonTint) {
                    DrawableCompat$Api21Impl.setTintList(mutate, null);
                }
                if (this.mHasButtonTintMode) {
                    DrawableCompat$Api21Impl.setTintMode(mutate, this.mButtonTintMode);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.mView.getDrawableState());
                }
                this.mView.setButtonDrawable(mutate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x005b A[Catch: all -> 0x007e, TryCatch #1 {all -> 0x007e, blocks: (B:3:0x0021, B:5:0x0027, B:8:0x002d, B:9:0x0054, B:11:0x005b, B:12:0x0064, B:14:0x006b, B:21:0x003b, B:23:0x0041, B:25:0x0047), top: B:2:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006b A[Catch: all -> 0x007e, TRY_LEAVE, TryCatch #1 {all -> 0x007e, blocks: (B:3:0x0021, B:5:0x0027, B:8:0x002d, B:9:0x0054, B:11:0x005b, B:12:0x0064, B:14:0x006b, B:21:0x003b, B:23:0x0041, B:25:0x0047), top: B:2:0x0021 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void loadFromAttributes(android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CompoundButton r0 = r10.mView
            android.content.Context r0 = r0.getContext()
            int[] r1 = android.support.v7.appcompat.R$styleable.CompoundButton
            r2 = 0
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(r0, r11, r1, r12, r2)
            java.lang.Object r1 = r0.ExecutionList$RunnableExecutorPair$ar$runnable
            android.widget.CompoundButton r3 = r10.mView
            android.content.Context r4 = r3.getContext()
            int[] r5 = android.support.v7.appcompat.R$styleable.CompoundButton
            r7 = r1
            android.content.res.TypedArray r7 = (android.content.res.TypedArray) r7
            r9 = 0
            r6 = r11
            r8 = r12
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r3, r4, r5, r6, r7, r8, r9)
            r11 = 1
            boolean r12 = r0.hasValue(r11)     // Catch: java.lang.Throwable -> L7e
            if (r12 == 0) goto L3b
            int r11 = r0.getResourceId(r11, r2)     // Catch: java.lang.Throwable -> L7e
            if (r11 == 0) goto L3b
            android.widget.CompoundButton r12 = r10.mView     // Catch: android.content.res.Resources.NotFoundException -> L3b java.lang.Throwable -> L7e
            android.content.Context r1 = r12.getContext()     // Catch: android.content.res.Resources.NotFoundException -> L3b java.lang.Throwable -> L7e
            android.graphics.drawable.Drawable r11 = android.support.v7.app.AppCompatDelegate.Api33Impl.getDrawable(r1, r11)     // Catch: android.content.res.Resources.NotFoundException -> L3b java.lang.Throwable -> L7e
            r12.setButtonDrawable(r11)     // Catch: android.content.res.Resources.NotFoundException -> L3b java.lang.Throwable -> L7e
            goto L54
        L3b:
            boolean r11 = r0.hasValue(r2)     // Catch: java.lang.Throwable -> L7e
            if (r11 == 0) goto L54
            int r11 = r0.getResourceId(r2, r2)     // Catch: java.lang.Throwable -> L7e
            if (r11 == 0) goto L54
            android.widget.CompoundButton r12 = r10.mView     // Catch: java.lang.Throwable -> L7e
            android.content.Context r1 = r12.getContext()     // Catch: java.lang.Throwable -> L7e
            android.graphics.drawable.Drawable r11 = android.support.v7.app.AppCompatDelegate.Api33Impl.getDrawable(r1, r11)     // Catch: java.lang.Throwable -> L7e
            r12.setButtonDrawable(r11)     // Catch: java.lang.Throwable -> L7e
        L54:
            r11 = 2
            boolean r12 = r0.hasValue(r11)     // Catch: java.lang.Throwable -> L7e
            if (r12 == 0) goto L64
            android.widget.CompoundButton r12 = r10.mView     // Catch: java.lang.Throwable -> L7e
            android.content.res.ColorStateList r11 = r0.getColorStateList(r11)     // Catch: java.lang.Throwable -> L7e
            androidx.core.widget.CompoundButtonCompat$Api21Impl.setButtonTintList(r12, r11)     // Catch: java.lang.Throwable -> L7e
        L64:
            r11 = 3
            boolean r12 = r0.hasValue(r11)     // Catch: java.lang.Throwable -> L7e
            if (r12 == 0) goto L7a
            android.widget.CompoundButton r12 = r10.mView     // Catch: java.lang.Throwable -> L7e
            r1 = -1
            int r11 = r0.getInt(r11, r1)     // Catch: java.lang.Throwable -> L7e
            r1 = 0
            android.graphics.PorterDuff$Mode r11 = _COROUTINE._BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(r11, r1)     // Catch: java.lang.Throwable -> L7e
            androidx.core.widget.CompoundButtonCompat$Api21Impl.setButtonTintMode(r12, r11)     // Catch: java.lang.Throwable -> L7e
        L7a:
            r0.recycle()
            return
        L7e:
            r11 = move-exception
            r0.recycle()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatCompoundButtonHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onSetButtonDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
        } else {
            this.mSkipNextApply = true;
            applyButtonTint();
        }
    }
}
