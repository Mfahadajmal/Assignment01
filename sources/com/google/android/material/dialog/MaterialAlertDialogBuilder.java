package com.google.android.material.dialog;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.ListAdapter;
import androidx.core.view.ViewCompat;
import com.google.android.material.shape.MaterialShapeDrawable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialAlertDialogBuilder extends AlertDialog.Builder {
    private final Drawable background;
    private final Rect backgroundInsets;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialAlertDialogBuilder(android.content.Context r12, int r13) {
        /*
            r11 = this;
            r0 = 2130969465(0x7f040379, float:1.7547613E38)
            android.util.TypedValue r0 = com.google.android.material.drawable.DrawableUtils$OutlineCompatR.resolve(r12, r0)
            r1 = 0
            if (r0 != 0) goto Lc
            r0 = r1
            goto Le
        Lc:
            int r0 = r0.data
        Le:
            r2 = 0
            r3 = 2130968623(0x7f04002f, float:1.7545905E38)
            r4 = 2132148718(0x7f1601ee, float:1.9939422E38)
            android.content.Context r12 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r12, r2, r3, r4)
            if (r0 == 0) goto L21
            androidx.appcompat.view.ContextThemeWrapper r5 = new androidx.appcompat.view.ContextThemeWrapper
            r5.<init>(r12, r0)
            r12 = r5
        L21:
            r11.<init>(r12, r13)
            android.content.Context r12 = r11.getContext()
            android.content.res.Resources$Theme r13 = r12.getTheme()
            int[] r7 = com.google.android.material.dialog.R$styleable.MaterialAlertDialog
            r9 = 2132148718(0x7f1601ee, float:1.9939422E38)
            int[] r10 = new int[r1]
            r6 = 0
            r8 = 2130968623(0x7f04002f, float:1.7545905E38)
            r5 = r12
            android.content.res.TypedArray r0 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r5, r6, r7, r8, r9, r10)
            android.content.res.Resources r5 = r12.getResources()
            r6 = 2131166255(0x7f07042f, float:1.794675E38)
            int r5 = r5.getDimensionPixelSize(r6)
            r6 = 2
            int r5 = r0.getDimensionPixelSize(r6, r5)
            android.content.res.Resources r6 = r12.getResources()
            r7 = 2131166256(0x7f070430, float:1.7946752E38)
            int r6 = r6.getDimensionPixelSize(r7)
            r7 = 3
            int r6 = r0.getDimensionPixelSize(r7, r6)
            android.content.res.Resources r7 = r12.getResources()
            r8 = 2131166254(0x7f07042e, float:1.7946748E38)
            int r7 = r7.getDimensionPixelSize(r8)
            r8 = 1
            int r7 = r0.getDimensionPixelSize(r8, r7)
            android.content.res.Resources r9 = r12.getResources()
            r10 = 2131166253(0x7f07042d, float:1.7946746E38)
            int r9 = r9.getDimensionPixelSize(r10)
            int r1 = r0.getDimensionPixelSize(r1, r9)
            r0.recycle()
            android.content.res.Resources r0 = r12.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.getLayoutDirection()
            if (r0 != r8) goto L8e
            r9 = r7
            goto L8f
        L8e:
            r9 = r5
        L8f:
            if (r0 == r8) goto L92
            r5 = r7
        L92:
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>(r9, r6, r5, r1)
            r11.backgroundInsets = r0
            java.lang.Class r0 = r11.getClass()
            java.lang.String r0 = r0.getCanonicalName()
            r1 = 2130968925(0x7f04015d, float:1.7546517E38)
            int r0 = com.google.android.libraries.vision.visionkit.base.FileUtils.getColor(r12, r1, r0)
            int[] r1 = com.google.android.material.dialog.R$styleable.MaterialAlertDialog
            android.content.res.TypedArray r1 = r12.obtainStyledAttributes(r2, r1, r3, r4)
            r5 = 4
            int r0 = r1.getColor(r5, r0)
            r1.recycle()
            com.google.android.material.shape.MaterialShapeDrawable r1 = new com.google.android.material.shape.MaterialShapeDrawable
            r1.<init>(r12, r2, r3, r4)
            r1.initializeElevationOverlay(r12)
            android.content.res.ColorStateList r12 = android.content.res.ColorStateList.valueOf(r0)
            r1.setFillColor(r12)
            int r12 = android.os.Build.VERSION.SDK_INT
            r0 = 28
            if (r12 < r0) goto Lf3
            android.util.TypedValue r12 = new android.util.TypedValue
            r12.<init>()
            r0 = 16844145(0x1010571, float:2.3697462E-38)
            r13.resolveAttribute(r0, r12, r8)
            android.content.Context r13 = r11.getContext()
            android.content.res.Resources r13 = r13.getResources()
            android.util.DisplayMetrics r13 = r13.getDisplayMetrics()
            float r13 = r12.getDimension(r13)
            int r12 = r12.type
            r0 = 5
            if (r12 != r0) goto Lf3
            r12 = 0
            int r12 = (r13 > r12 ? 1 : (r13 == r12 ? 0 : -1))
            if (r12 < 0) goto Lf3
            r1.setCornerSize(r13)
        Lf3:
            r11.background = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.dialog.MaterialAlertDialogBuilder.<init>(android.content.Context, int):void");
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final AlertDialog create() {
        AlertDialog create = super.create();
        Window window = create.getWindow();
        View decorView = window.getDecorView();
        ((MaterialShapeDrawable) this.background).setElevation(ViewCompat.Api21Impl.getElevation(decorView));
        Rect rect = this.backgroundInsets;
        window.setBackgroundDrawable(new InsetDrawable(this.background, rect.left, rect.top, rect.right, rect.bottom));
        decorView.setOnTouchListener(new InsetDialogOnTouchListener(create, this.backgroundInsets));
        return create;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* bridge */ /* synthetic */ AlertDialog.Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
        super.setAdapter(listAdapter, null);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setCancelable(boolean z) {
        super.setCancelable(z);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setMessage(int i) {
        super.setMessage(i);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ void setMultiChoiceItems$ar$ds(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        super.setMultiChoiceItems$ar$ds(charSequenceArr, zArr, onMultiChoiceClickListener);
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        super.setNegativeButton(i, onClickListener);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        super.setNeutralButton(charSequence, onClickListener);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ void setNeutralButton$ar$ds(int i, DialogInterface.OnClickListener onClickListener) {
        super.setNeutralButton$ar$ds(i, onClickListener);
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        super.setOnCancelListener(onCancelListener);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ void setOnDismissListener$ar$ds(DialogInterface.OnDismissListener onDismissListener) {
        super.setOnDismissListener$ar$ds(onDismissListener);
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        super.setPositiveButton(i, onClickListener);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ void setSingleChoiceItems$ar$ds(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
        super.setSingleChoiceItems$ar$ds(charSequenceArr, i, onClickListener);
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setTitle(int i) {
        super.setTitle(i);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setView(View view) {
        super.setView(view);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setMessage(CharSequence charSequence) {
        super.setMessage(charSequence);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        super.setNegativeButton(charSequence, onClickListener);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        super.setPositiveButton(charSequence, onClickListener);
        return this;
    }

    @Override // android.support.v7.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        return this;
    }
}
