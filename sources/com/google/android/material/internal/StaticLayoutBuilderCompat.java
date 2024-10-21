package com.google.android.material.internal;

import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.work.impl.utils.NetworkApi24;

/* compiled from: PG */
/* loaded from: classes.dex */
final class StaticLayoutBuilderCompat {
    public int end;
    public boolean isRtl;
    public final TextPaint paint;
    public CharSequence source;
    public NetworkApi24 staticLayoutBuilderConfigurer$ar$class_merging$ar$class_merging;
    public final int width;
    public Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
    public int maxLines = Preference.DEFAULT_ORDER;
    public float lineSpacingMultiplier = 1.0f;
    public int hyphenationFrequency = 1;
    public boolean includePad = true;
    public TextUtils.TruncateAt ellipsize = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class StaticLayoutBuilderCompatException extends Exception {
    }

    public StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i) {
        this.source = charSequence;
        this.paint = textPaint;
        this.width = i;
        this.end = charSequence.length();
    }
}
