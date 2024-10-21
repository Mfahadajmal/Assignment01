package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.work.impl.utils.NetworkApi24;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollapsingTextHelper {
    private boolean boundsChanged;
    public final Rect collapsedBounds;
    private float collapsedDrawX;
    private float collapsedDrawY;
    private CancelableFontCallback collapsedFontCallback;
    private float collapsedLetterSpacing;
    private ColorStateList collapsedShadowColor;
    private float collapsedShadowDx;
    private float collapsedShadowDy;
    private float collapsedShadowRadius;
    private float collapsedTextBlend;
    public ColorStateList collapsedTextColor;
    public float collapsedTextWidth;
    private Typeface collapsedTypeface;
    private Typeface collapsedTypefaceBold;
    private Typeface collapsedTypefaceDefault;
    private final RectF currentBounds;
    private float currentDrawX;
    private float currentDrawY;
    private float currentLetterSpacing;
    public int currentOffsetY;
    private int currentShadowColor;
    private float currentShadowDx;
    private float currentShadowDy;
    private float currentShadowRadius;
    private float currentTextSize;
    private Typeface currentTypeface;
    private final Rect expandedBounds;
    private float expandedDrawX;
    private float expandedDrawY;
    private CancelableFontCallback expandedFontCallback;
    public float expandedFraction;
    public float expandedLetterSpacing;
    public int expandedLineCount;
    private ColorStateList expandedShadowColor;
    private float expandedShadowDx;
    private float expandedShadowDy;
    private float expandedShadowRadius;
    private float expandedTextBlend;
    public ColorStateList expandedTextColor;
    private Typeface expandedTypeface;
    private Typeface expandedTypefaceBold;
    private Typeface expandedTypefaceDefault;
    public boolean fadeModeEnabled;
    public float fadeModeStartFraction;
    public float fadeModeThresholdFraction;
    public boolean isRtl;
    private TimeInterpolator positionInterpolator;
    private float scale;
    private int[] state;
    public NetworkApi24 staticLayoutBuilderConfigurer$ar$class_merging$ar$class_merging;
    public CharSequence text;
    private StaticLayout textLayout;
    private final TextPaint textPaint;
    private TimeInterpolator textSizeInterpolator;
    private CharSequence textToDraw;
    private CharSequence textToDrawCollapsed;
    public final TextPaint tmpPaint;
    private final View view;
    private int expandedTextGravity = 16;
    private int collapsedTextGravity = 16;
    private float expandedTextSize = 15.0f;
    private float collapsedTextSize = 15.0f;
    public TextUtils.TruncateAt titleTextEllipsize = TextUtils.TruncateAt.END;
    public boolean isRtlTextDirectionHeuristicsEnabled = true;
    public int maxLines = 1;
    public float lineSpacingMultiplier = 1.0f;
    public int hyphenationFrequency = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.material.internal.CollapsingTextHelper$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements CancelableFontCallback.ApplyFont {
        private final /* synthetic */ int switching_field;
        final /* synthetic */ CollapsingTextHelper this$0;

        public AnonymousClass2(CollapsingTextHelper collapsingTextHelper, int i) {
            this.switching_field = i;
            this.this$0 = collapsingTextHelper;
        }

        @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
        public final void apply(Typeface typeface) {
            if (this.switching_field != 0) {
                CollapsingTextHelper collapsingTextHelper = this.this$0;
                if (collapsingTextHelper.setCollapsedTypefaceInternal(typeface)) {
                    collapsingTextHelper.recalculate();
                    return;
                }
                return;
            }
            CollapsingTextHelper collapsingTextHelper2 = this.this$0;
            if (collapsingTextHelper2.setExpandedTypefaceInternal(typeface)) {
                collapsingTextHelper2.recalculate();
            }
        }
    }

    public CollapsingTextHelper(View view) {
        this.view = view;
        TextPaint textPaint = new TextPaint(BrailleInputEvent.CMD_NAV_BOTTOM_OR_KEY_ACTIVATE);
        this.textPaint = textPaint;
        this.tmpPaint = new TextPaint(textPaint);
        this.collapsedBounds = new Rect();
        this.expandedBounds = new Rect();
        this.currentBounds = new RectF();
        this.fadeModeThresholdFraction = calculateFadeModeThresholdFraction();
        maybeUpdateFontWeightAdjustment(view.getContext().getResources().getConfiguration());
    }

    private static int blendARGB(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb(Math.round((Color.alpha(i) * f2) + (Color.alpha(i2) * f)), Math.round((Color.red(i) * f2) + (Color.red(i2) * f)), Math.round((Color.green(i) * f2) + (Color.green(i2) * f)), Math.round((Color.blue(i) * f2) + (Color.blue(i2) * f)));
    }

    private final void calculateCurrentOffsets() {
        float f;
        float lerp;
        Rect rect;
        float f2 = this.expandedFraction;
        if (this.fadeModeEnabled) {
            RectF rectF = this.currentBounds;
            if (f2 < this.fadeModeThresholdFraction) {
                rect = this.expandedBounds;
            } else {
                rect = this.collapsedBounds;
            }
            rectF.set(rect);
        } else {
            this.currentBounds.left = lerp(this.expandedBounds.left, this.collapsedBounds.left, f2, this.positionInterpolator);
            this.currentBounds.top = lerp(this.expandedDrawY, this.collapsedDrawY, f2, this.positionInterpolator);
            this.currentBounds.right = lerp(this.expandedBounds.right, this.collapsedBounds.right, f2, this.positionInterpolator);
            this.currentBounds.bottom = lerp(this.expandedBounds.bottom, this.collapsedBounds.bottom, f2, this.positionInterpolator);
        }
        if (this.fadeModeEnabled) {
            if (f2 < this.fadeModeThresholdFraction) {
                this.currentDrawX = this.expandedDrawX;
                this.currentDrawY = this.expandedDrawY;
                setInterpolatedTextSize(0.0f);
                f = 0.0f;
            } else {
                this.currentDrawX = this.collapsedDrawX;
                this.currentDrawY = this.collapsedDrawY - Math.max(0, this.currentOffsetY);
                setInterpolatedTextSize(1.0f);
                f = 1.0f;
            }
        } else {
            this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, f2, this.positionInterpolator);
            this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, f2, this.positionInterpolator);
            setInterpolatedTextSize(f2);
            f = f2;
        }
        this.collapsedTextBlend = 1.0f - lerp(0.0f, 1.0f, 1.0f - f2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.view.postInvalidateOnAnimation();
        this.expandedTextBlend = lerp(1.0f, 0.0f, f2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.view.postInvalidateOnAnimation();
        ColorStateList colorStateList = this.collapsedTextColor;
        ColorStateList colorStateList2 = this.expandedTextColor;
        if (colorStateList != colorStateList2) {
            this.textPaint.setColor(blendARGB(getCurrentColor(colorStateList2), getCurrentCollapsedTextColor(), f));
        } else {
            this.textPaint.setColor(getCurrentCollapsedTextColor());
        }
        float f3 = this.collapsedLetterSpacing;
        float f4 = this.expandedLetterSpacing;
        if (f3 != f4) {
            this.textPaint.setLetterSpacing(lerp(f4, f3, f2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        } else {
            this.textPaint.setLetterSpacing(f3);
        }
        this.currentShadowRadius = lerp(this.expandedShadowRadius, this.collapsedShadowRadius, f2, null);
        this.currentShadowDx = lerp(this.expandedShadowDx, this.collapsedShadowDx, f2, null);
        this.currentShadowDy = lerp(this.expandedShadowDy, this.collapsedShadowDy, f2, null);
        int blendARGB = blendARGB(getCurrentColor(this.expandedShadowColor), getCurrentColor(this.collapsedShadowColor), f2);
        this.currentShadowColor = blendARGB;
        this.textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, blendARGB);
        if (this.fadeModeEnabled) {
            int alpha = this.textPaint.getAlpha();
            float f5 = this.fadeModeThresholdFraction;
            if (f2 <= f5) {
                lerp = AnimationUtils.lerp(1.0f, 0.0f, this.fadeModeStartFraction, f5, f2);
            } else {
                lerp = AnimationUtils.lerp(0.0f, 1.0f, f5, 1.0f, f2);
            }
            this.textPaint.setAlpha((int) (lerp * alpha));
            if (Build.VERSION.SDK_INT >= 31) {
                TextPaint textPaint = this.textPaint;
                textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, FileUtils.compositeARGBWithAlpha(this.currentShadowColor, textPaint.getAlpha()));
            }
        }
        this.view.postInvalidateOnAnimation();
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ed, code lost:
    
        if (r11.isRtl != false) goto L64;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void calculateUsingTextSize(float r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 454
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.internal.CollapsingTextHelper.calculateUsingTextSize(float, boolean):void");
    }

    private final int getCurrentColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.state;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    private static boolean isClose(float f, float f2) {
        if (Math.abs(f - f2) < 1.0E-5f) {
            return true;
        }
        return false;
    }

    private static float lerp(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        TimeInterpolator timeInterpolator2 = AnimationUtils.LINEAR_INTERPOLATOR;
        return f + (f3 * (f2 - f));
    }

    private static final float measureTextWidth$ar$ds(TextPaint textPaint, CharSequence charSequence) {
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    private static boolean rectEquals(Rect rect, int i, int i2, int i3, int i4) {
        if (rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4) {
            return true;
        }
        return false;
    }

    private final void setInterpolatedTextSize(float f) {
        calculateUsingTextSize(f, false);
        this.view.postInvalidateOnAnimation();
    }

    private final boolean shouldDrawMultiline() {
        if (this.maxLines <= 1) {
            return false;
        }
        if (this.isRtl && !this.fadeModeEnabled) {
            return false;
        }
        return true;
    }

    public final float calculateFadeModeThresholdFraction() {
        float f = this.fadeModeStartFraction;
        return f + ((1.0f - f) * 0.5f);
    }

    public final boolean calculateIsRtl(CharSequence charSequence) {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat;
        int layoutDirection = this.view.getLayoutDirection();
        boolean z = this.isRtlTextDirectionHeuristicsEnabled;
        boolean z2 = true;
        if (layoutDirection != 1) {
            z2 = false;
        }
        if (z) {
            if (z2) {
                textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
            } else {
                textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
            }
            return textDirectionHeuristicCompat.isRtl$ar$ds(charSequence, charSequence.length());
        }
        return z2;
    }

    public final void draw(Canvas canvas) {
        int save = canvas.save();
        if (this.textToDraw != null && this.currentBounds.width() > 0.0f && this.currentBounds.height() > 0.0f) {
            this.textPaint.setTextSize(this.currentTextSize);
            float f = this.currentDrawX;
            float f2 = this.currentDrawY;
            float f3 = this.scale;
            if (f3 != 1.0f && !this.fadeModeEnabled) {
                canvas.scale(f3, f3, f, f2);
            }
            if (shouldDrawMultiline() && (!this.fadeModeEnabled || this.expandedFraction > this.fadeModeThresholdFraction)) {
                float lineStart = this.currentDrawX - this.textLayout.getLineStart(0);
                int alpha = this.textPaint.getAlpha();
                canvas.translate(lineStart, f2);
                if (!this.fadeModeEnabled) {
                    this.textPaint.setAlpha((int) (this.expandedTextBlend * alpha));
                    if (Build.VERSION.SDK_INT >= 31) {
                        TextPaint textPaint = this.textPaint;
                        textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, FileUtils.compositeARGBWithAlpha(this.currentShadowColor, textPaint.getAlpha()));
                    }
                    this.textLayout.draw(canvas);
                }
                if (!this.fadeModeEnabled) {
                    this.textPaint.setAlpha((int) (this.collapsedTextBlend * alpha));
                }
                if (Build.VERSION.SDK_INT >= 31) {
                    TextPaint textPaint2 = this.textPaint;
                    textPaint2.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, FileUtils.compositeARGBWithAlpha(this.currentShadowColor, textPaint2.getAlpha()));
                }
                int lineBaseline = this.textLayout.getLineBaseline(0);
                CharSequence charSequence = this.textToDrawCollapsed;
                float f4 = lineBaseline;
                canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f4, this.textPaint);
                if (Build.VERSION.SDK_INT >= 31) {
                    this.textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, this.currentShadowColor);
                }
                if (!this.fadeModeEnabled) {
                    String trim = this.textToDrawCollapsed.toString().trim();
                    if (trim.endsWith("…")) {
                        trim = trim.substring(0, trim.length() - 1);
                    }
                    String str = trim;
                    this.textPaint.setAlpha(alpha);
                    canvas.drawText(str, 0, Math.min(this.textLayout.getLineEnd(0), str.length()), 0.0f, f4, (Paint) this.textPaint);
                }
            } else {
                canvas.translate(f, f2);
                this.textLayout.draw(canvas);
            }
            canvas.restoreToCount(save);
        }
    }

    public final float getCollapsedTextHeight() {
        TextPaint textPaint = this.tmpPaint;
        textPaint.setTextSize(this.collapsedTextSize);
        textPaint.setTypeface(this.collapsedTypeface);
        textPaint.setLetterSpacing(this.collapsedLetterSpacing);
        return -this.tmpPaint.ascent();
    }

    public final int getCurrentCollapsedTextColor() {
        return getCurrentColor(this.collapsedTextColor);
    }

    public final void getTextPaintExpanded(TextPaint textPaint) {
        textPaint.setTextSize(this.expandedTextSize);
        textPaint.setTypeface(this.expandedTypeface);
        textPaint.setLetterSpacing(this.expandedLetterSpacing);
    }

    public final void maybeUpdateFontWeightAdjustment(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 31) {
            Typeface typeface = this.collapsedTypefaceDefault;
            if (typeface != null) {
                this.collapsedTypefaceBold = DrawableUtils$OutlineCompatR.maybeCopyWithFontWeightAdjustment(configuration, typeface);
            }
            Typeface typeface2 = this.expandedTypefaceDefault;
            if (typeface2 != null) {
                this.expandedTypefaceBold = DrawableUtils$OutlineCompatR.maybeCopyWithFontWeightAdjustment(configuration, typeface2);
            }
            Typeface typeface3 = this.collapsedTypefaceBold;
            if (typeface3 == null) {
                typeface3 = this.collapsedTypefaceDefault;
            }
            this.collapsedTypeface = typeface3;
            Typeface typeface4 = this.expandedTypefaceBold;
            if (typeface4 == null) {
                typeface4 = this.expandedTypefaceDefault;
            }
            this.expandedTypeface = typeface4;
            recalculate(true);
        }
    }

    public final void recalculate() {
        recalculate(false);
    }

    public final void setCollapsedAndExpandedTextColor(ColorStateList colorStateList) {
        if (this.collapsedTextColor == colorStateList && this.expandedTextColor == colorStateList) {
            return;
        }
        this.collapsedTextColor = colorStateList;
        this.expandedTextColor = colorStateList;
        recalculate();
    }

    public final void setCollapsedBounds(int i, int i2, int i3, int i4) {
        if (!rectEquals(this.collapsedBounds, i, i2, i3, i4)) {
            this.collapsedBounds.set(i, i2, i3, i4);
            this.boundsChanged = true;
        }
    }

    public final void setCollapsedTextAppearance(int i) {
        TextAppearance textAppearance = new TextAppearance(this.view.getContext(), i);
        ColorStateList colorStateList = textAppearance.textColor;
        if (colorStateList != null) {
            this.collapsedTextColor = colorStateList;
        }
        float f = textAppearance.textSize;
        if (f != 0.0f) {
            this.collapsedTextSize = f;
        }
        ColorStateList colorStateList2 = textAppearance.shadowColor;
        if (colorStateList2 != null) {
            this.collapsedShadowColor = colorStateList2;
        }
        this.collapsedShadowDx = textAppearance.shadowDx;
        this.collapsedShadowDy = textAppearance.shadowDy;
        this.collapsedShadowRadius = textAppearance.shadowRadius;
        this.collapsedLetterSpacing = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.collapsedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        this.collapsedFontCallback = new CancelableFontCallback(new AnonymousClass2(this, 1), textAppearance.getFallbackFont());
        textAppearance.getFontAsync(this.view.getContext(), this.collapsedFontCallback);
        recalculate();
    }

    public final void setCollapsedTextColor(ColorStateList colorStateList) {
        if (this.collapsedTextColor != colorStateList) {
            this.collapsedTextColor = colorStateList;
            recalculate();
        }
    }

    public final void setCollapsedTextGravity(int i) {
        if (this.collapsedTextGravity != i) {
            this.collapsedTextGravity = i;
            recalculate();
        }
    }

    public final boolean setCollapsedTypefaceInternal(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.collapsedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        if (this.collapsedTypefaceDefault != typeface) {
            this.collapsedTypefaceDefault = typeface;
            Typeface maybeCopyWithFontWeightAdjustment = DrawableUtils$OutlineCompatR.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), typeface);
            this.collapsedTypefaceBold = maybeCopyWithFontWeightAdjustment;
            if (maybeCopyWithFontWeightAdjustment == null) {
                maybeCopyWithFontWeightAdjustment = this.collapsedTypefaceDefault;
            }
            this.collapsedTypeface = maybeCopyWithFontWeightAdjustment;
            return true;
        }
        return false;
    }

    public final void setExpandedBounds(int i, int i2, int i3, int i4) {
        if (!rectEquals(this.expandedBounds, i, i2, i3, i4)) {
            this.expandedBounds.set(i, i2, i3, i4);
            this.boundsChanged = true;
        }
    }

    public final void setExpandedTextAppearance(int i) {
        TextAppearance textAppearance = new TextAppearance(this.view.getContext(), i);
        ColorStateList colorStateList = textAppearance.textColor;
        if (colorStateList != null) {
            this.expandedTextColor = colorStateList;
        }
        float f = textAppearance.textSize;
        if (f != 0.0f) {
            this.expandedTextSize = f;
        }
        ColorStateList colorStateList2 = textAppearance.shadowColor;
        if (colorStateList2 != null) {
            this.expandedShadowColor = colorStateList2;
        }
        this.expandedShadowDx = textAppearance.shadowDx;
        this.expandedShadowDy = textAppearance.shadowDy;
        this.expandedShadowRadius = textAppearance.shadowRadius;
        this.expandedLetterSpacing = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.expandedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        this.expandedFontCallback = new CancelableFontCallback(new AnonymousClass2(this, 0), textAppearance.getFallbackFont());
        textAppearance.getFontAsync(this.view.getContext(), this.expandedFontCallback);
        recalculate();
    }

    public final void setExpandedTextGravity(int i) {
        if (this.expandedTextGravity != i) {
            this.expandedTextGravity = i;
            recalculate();
        }
    }

    public final void setExpandedTextSize(float f) {
        if (this.expandedTextSize != f) {
            this.expandedTextSize = f;
            recalculate();
        }
    }

    public final boolean setExpandedTypefaceInternal(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.expandedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        if (this.expandedTypefaceDefault != typeface) {
            this.expandedTypefaceDefault = typeface;
            Typeface maybeCopyWithFontWeightAdjustment = DrawableUtils$OutlineCompatR.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), typeface);
            this.expandedTypefaceBold = maybeCopyWithFontWeightAdjustment;
            if (maybeCopyWithFontWeightAdjustment == null) {
                maybeCopyWithFontWeightAdjustment = this.expandedTypefaceDefault;
            }
            this.expandedTypeface = maybeCopyWithFontWeightAdjustment;
            return true;
        }
        return false;
    }

    public final void setExpansionFraction(float f) {
        float clamp = NotificationCompatBuilder$Api26Impl.clamp(f, 0.0f, 1.0f);
        if (clamp != this.expandedFraction) {
            this.expandedFraction = clamp;
            calculateCurrentOffsets();
        }
    }

    public final void setPositionInterpolator(TimeInterpolator timeInterpolator) {
        this.positionInterpolator = timeInterpolator;
        recalculate();
    }

    public final boolean setState(int[] iArr) {
        ColorStateList colorStateList;
        this.state = iArr;
        ColorStateList colorStateList2 = this.collapsedTextColor;
        if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.expandedTextColor) != null && colorStateList.isStateful())) {
            recalculate();
            return true;
        }
        return false;
    }

    public final void setText(CharSequence charSequence) {
        if (charSequence != null && TextUtils.equals(this.text, charSequence)) {
            return;
        }
        this.text = charSequence;
        this.textToDraw = null;
        recalculate();
    }

    public final void setTextSizeInterpolator(TimeInterpolator timeInterpolator) {
        this.textSizeInterpolator = timeInterpolator;
        recalculate();
    }

    public final void recalculate(boolean z) {
        StaticLayout staticLayout;
        if (this.view.getHeight() <= 0 || this.view.getWidth() <= 0) {
            if (!z) {
                return;
            } else {
                z = true;
            }
        }
        calculateUsingTextSize(1.0f, z);
        CharSequence charSequence = this.textToDraw;
        if (charSequence != null && (staticLayout = this.textLayout) != null) {
            this.textToDrawCollapsed = TextUtils.ellipsize(charSequence, this.textPaint, staticLayout.getWidth(), this.titleTextEllipsize);
        }
        CharSequence charSequence2 = this.textToDrawCollapsed;
        float f = 0.0f;
        if (charSequence2 != null) {
            this.collapsedTextWidth = measureTextWidth$ar$ds(this.textPaint, charSequence2);
        } else {
            this.collapsedTextWidth = 0.0f;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(this.collapsedTextGravity, this.isRtl ? 1 : 0);
        int i = absoluteGravity & BrailleInputEvent.CMD_CONTROL_NEXT;
        if (i == 48) {
            this.collapsedDrawY = this.collapsedBounds.top;
        } else if (i != 80) {
            TextPaint textPaint = this.textPaint;
            this.collapsedDrawY = this.collapsedBounds.centerY() - ((textPaint.descent() - textPaint.ascent()) / 2.0f);
        } else {
            this.collapsedDrawY = this.collapsedBounds.bottom + this.textPaint.ascent();
        }
        int i2 = absoluteGravity & 8388615;
        if (i2 == 1) {
            this.collapsedDrawX = this.collapsedBounds.centerX() - (this.collapsedTextWidth / 2.0f);
        } else if (i2 != 5) {
            this.collapsedDrawX = this.collapsedBounds.left;
        } else {
            this.collapsedDrawX = this.collapsedBounds.right - this.collapsedTextWidth;
        }
        calculateUsingTextSize(0.0f, z);
        float height = this.textLayout != null ? r10.getHeight() : 0.0f;
        StaticLayout staticLayout2 = this.textLayout;
        if (staticLayout2 != null && this.maxLines > 1) {
            f = staticLayout2.getWidth();
        } else {
            CharSequence charSequence3 = this.textToDraw;
            if (charSequence3 != null) {
                f = measureTextWidth$ar$ds(this.textPaint, charSequence3);
            }
        }
        StaticLayout staticLayout3 = this.textLayout;
        this.expandedLineCount = staticLayout3 != null ? staticLayout3.getLineCount() : 0;
        int absoluteGravity2 = Gravity.getAbsoluteGravity(this.expandedTextGravity, this.isRtl ? 1 : 0);
        int i3 = absoluteGravity2 & BrailleInputEvent.CMD_CONTROL_NEXT;
        if (i3 == 48) {
            this.expandedDrawY = this.expandedBounds.top;
        } else if (i3 != 80) {
            this.expandedDrawY = this.expandedBounds.centerY() - (height / 2.0f);
        } else {
            this.expandedDrawY = (this.expandedBounds.bottom - height) + this.textPaint.descent();
        }
        int i4 = absoluteGravity2 & 8388615;
        if (i4 == 1) {
            this.expandedDrawX = this.expandedBounds.centerX() - (f / 2.0f);
        } else if (i4 != 5) {
            this.expandedDrawX = this.expandedBounds.left;
        } else {
            this.expandedDrawX = this.expandedBounds.right - f;
        }
        setInterpolatedTextSize(this.expandedFraction);
        calculateCurrentOffsets();
    }
}
