package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextAppearance {
    public Typeface font;
    public final String fontFamily;
    private final int fontFamilyResourceId;
    public final boolean hasLetterSpacing;
    public final float letterSpacing;
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public ColorStateList textColor;
    public float textSize;
    public final int textStyle;
    public final int typeface;
    private boolean fontResolved = false;
    private boolean systemFontLoadAttempted = false;

    public TextAppearance(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        this.textSize = obtainStyledAttributes.getDimension(0, 0.0f);
        this.textColor = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 3);
        DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 4);
        DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 5);
        this.textStyle = obtainStyledAttributes.getInt(2, 0);
        this.typeface = obtainStyledAttributes.getInt(1, 1);
        int i2 = true != obtainStyledAttributes.hasValue(12) ? 10 : 12;
        this.fontFamilyResourceId = obtainStyledAttributes.getResourceId(i2, 0);
        this.fontFamily = obtainStyledAttributes.getString(i2);
        obtainStyledAttributes.getBoolean(14, false);
        this.shadowColor = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 6);
        this.shadowDx = obtainStyledAttributes.getFloat(7, 0.0f);
        this.shadowDy = obtainStyledAttributes.getFloat(8, 0.0f);
        this.shadowRadius = obtainStyledAttributes.getFloat(9, 0.0f);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
        this.hasLetterSpacing = obtainStyledAttributes2.hasValue(0);
        this.letterSpacing = obtainStyledAttributes2.getFloat(0, 0.0f);
        obtainStyledAttributes2.recycle();
    }

    private final void createFallbackFont() {
        Typeface typeface;
        String str;
        if (this.font == null && (str = this.fontFamily) != null) {
            this.font = Typeface.create(str, this.textStyle);
        }
        if (this.font == null) {
            int i = this.typeface;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        this.font = Typeface.DEFAULT;
                        this.font = Typeface.create(this.font, this.textStyle);
                    }
                    typeface = Typeface.MONOSPACE;
                } else {
                    typeface = Typeface.SERIF;
                }
            } else {
                typeface = Typeface.SANS_SERIF;
            }
            this.font = typeface;
            this.font = Typeface.create(this.font, this.textStyle);
        }
    }

    private final boolean maybeLoadFontSynchronously(Context context) {
        Typeface loadFont$ar$ds;
        String str;
        Typeface create;
        if (this.fontResolved) {
            return true;
        }
        int i = this.fontFamilyResourceId;
        if (i == 0) {
            return false;
        }
        ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
        Typeface typeface = null;
        if (context.isRestricted()) {
            loadFont$ar$ds = null;
        } else {
            loadFont$ar$ds = ResourcesCompat.loadFont$ar$ds(context, i, new TypedValue(), 0, null, false, true);
        }
        if (loadFont$ar$ds == null) {
            if (!this.systemFontLoadAttempted) {
                this.systemFontLoadAttempted = true;
                int i2 = this.fontFamilyResourceId;
                Resources resources = context.getResources();
                if (i2 != 0 && resources.getResourceTypeName(i2).equals("font")) {
                    try {
                        XmlResourceParser xml = resources.getXml(i2);
                        while (xml.getEventType() != 1) {
                            if (xml.getEventType() == 2 && xml.getName().equals("font-family")) {
                                TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xml), androidx.core.R$styleable.FontFamily);
                                str = obtainAttributes.getString(6);
                                obtainAttributes.recycle();
                                break;
                            }
                            xml.next();
                        }
                    } catch (Throwable unused) {
                    }
                }
                str = null;
                if (str != null && (create = Typeface.create(str, 0)) != Typeface.DEFAULT) {
                    typeface = Typeface.create(create, this.textStyle);
                }
            }
            if (typeface == null) {
                return false;
            }
            this.font = typeface;
            this.fontResolved = true;
            return true;
        }
        this.font = loadFont$ar$ds;
        this.fontResolved = true;
        return true;
    }

    public final Typeface getFallbackFont() {
        createFallbackFont();
        return this.font;
    }

    public final void getFontAsync(Context context, final TextAppearanceFontCallback textAppearanceFontCallback) {
        if (!maybeLoadFontSynchronously(context)) {
            createFallbackFont();
        }
        int i = this.fontFamilyResourceId;
        if (i == 0) {
            this.fontResolved = true;
        }
        if (!this.fontResolved) {
            try {
                ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback(this) { // from class: com.google.android.material.resources.TextAppearance.1
                    final /* synthetic */ TextAppearance this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                    public final void onFontRetrievalFailed(int i2) {
                        this.this$0.fontResolved = true;
                        textAppearanceFontCallback.onFontRetrievalFailed(i2);
                    }

                    @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                    public final void onFontRetrieved(Typeface typeface) {
                        TextAppearance textAppearance = this.this$0;
                        textAppearance.font = Typeface.create(typeface, textAppearance.textStyle);
                        this.this$0.fontResolved = true;
                        textAppearanceFontCallback.onFontRetrieved(this.this$0.font, false);
                    }
                };
                ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
                if (context.isRestricted()) {
                    fontCallback.callbackFailAsync$ar$ds(-4);
                    return;
                } else {
                    ResourcesCompat.loadFont$ar$ds(context, i, new TypedValue(), 0, fontCallback, false, false);
                    return;
                }
            } catch (Resources.NotFoundException unused) {
                this.fontResolved = true;
                textAppearanceFontCallback.onFontRetrievalFailed(1);
                return;
            } catch (Exception unused2) {
                this.fontResolved = true;
                textAppearanceFontCallback.onFontRetrievalFailed(-3);
                return;
            }
        }
        textAppearanceFontCallback.onFontRetrieved(this.font, true);
    }

    public final void updateDrawState(Context context, TextPaint textPaint, TextAppearanceFontCallback textAppearanceFontCallback) {
        int i;
        int i2;
        updateMeasureState(context, textPaint, textAppearanceFontCallback);
        ColorStateList colorStateList = this.textColor;
        if (colorStateList != null) {
            i = colorStateList.getColorForState(textPaint.drawableState, this.textColor.getDefaultColor());
        } else {
            i = -16777216;
        }
        textPaint.setColor(i);
        float f = this.shadowRadius;
        float f2 = this.shadowDx;
        float f3 = this.shadowDy;
        ColorStateList colorStateList2 = this.shadowColor;
        if (colorStateList2 != null) {
            i2 = colorStateList2.getColorForState(textPaint.drawableState, this.shadowColor.getDefaultColor());
        } else {
            i2 = 0;
        }
        textPaint.setShadowLayer(f, f2, f3, i2);
    }

    public final void updateMeasureState(final Context context, final TextPaint textPaint, final TextAppearanceFontCallback textAppearanceFontCallback) {
        Typeface typeface;
        if (maybeLoadFontSynchronously(context) && this.fontResolved && (typeface = this.font) != null) {
            updateTextPaintMeasureState(context, textPaint, typeface);
        } else {
            updateTextPaintMeasureState(context, textPaint, getFallbackFont());
            getFontAsync(context, new TextAppearanceFontCallback(this) { // from class: com.google.android.material.resources.TextAppearance.2
                final /* synthetic */ TextAppearance this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.android.material.resources.TextAppearanceFontCallback
                public final void onFontRetrievalFailed(int i) {
                    textAppearanceFontCallback.onFontRetrievalFailed(i);
                }

                @Override // com.google.android.material.resources.TextAppearanceFontCallback
                public final void onFontRetrieved(Typeface typeface2, boolean z) {
                    this.this$0.updateTextPaintMeasureState(context, textPaint, typeface2);
                    textAppearanceFontCallback.onFontRetrieved(typeface2, z);
                }
            });
        }
    }

    public final void updateTextPaintMeasureState(Context context, TextPaint textPaint, Typeface typeface) {
        float f;
        Typeface maybeCopyWithFontWeightAdjustment = DrawableUtils$OutlineCompatR.maybeCopyWithFontWeightAdjustment(context.getResources().getConfiguration(), typeface);
        if (maybeCopyWithFontWeightAdjustment != null) {
            typeface = maybeCopyWithFontWeightAdjustment;
        }
        textPaint.setTypeface(typeface);
        int i = (~typeface.getStyle()) & this.textStyle;
        boolean z = true;
        if (1 != (i & 1)) {
            z = false;
        }
        textPaint.setFakeBoldText(z);
        if ((i & 2) != 0) {
            f = -0.25f;
        } else {
            f = 0.0f;
        }
        textPaint.setTextSkewX(f);
        textPaint.setTextSize(this.textSize);
        if (this.hasLetterSpacing) {
            textPaint.setLetterSpacing(this.letterSpacing);
        }
    }
}
