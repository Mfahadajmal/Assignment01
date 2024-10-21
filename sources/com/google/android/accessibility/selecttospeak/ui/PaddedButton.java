package com.google.android.accessibility.selecttospeak.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.selecttospeak.R$styleable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PaddedButton extends AppCompatButton {
    public final Drawable paddedDrawable;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PaddedButton(Context context) {
        this(context, null, 0, 6, null);
        context.getClass();
    }

    @Override // android.widget.TextView, android.view.View
    protected final void onDraw(Canvas canvas) {
        canvas.getClass();
        this.paddedDrawable.setState(getDrawableState());
        if (isEnabled()) {
            this.paddedDrawable.setAlpha(PrivateKeyType.INVALID);
        } else {
            this.paddedDrawable.setAlpha(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        }
        this.paddedDrawable.draw(canvas);
        super.onDraw(canvas);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PaddedButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        context.getClass();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PaddedButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Throwable th;
        TypedArray obtainStyledAttributes;
        context.getClass();
        TypedArray typedArray = null;
        try {
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PaddedButton);
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            Drawable drawable = obtainStyledAttributes.getDrawable(3);
            drawable.getClass();
            this.paddedDrawable = drawable;
            final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(2, 0);
            final int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            final Integer valueOf = obtainStyledAttributes.hasValue(1) ? Integer.valueOf(obtainStyledAttributes.getColor(1, 0)) : null;
            if (obtainStyledAttributes != null) {
                obtainStyledAttributes.recycle();
            }
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.android.accessibility.selecttospeak.ui.PaddedButton.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    int width = PaddedButton.this.getWidth() - dimensionPixelSize;
                    int height = PaddedButton.this.getHeight();
                    int i2 = dimensionPixelSize2;
                    int i3 = width / 2;
                    int i4 = (height - i2) / 2;
                    Rect rect = new Rect(i3, i4, dimensionPixelSize + i3, i2 + i4);
                    Integer num = valueOf;
                    if (num != null) {
                        PaddedButton paddedButton = PaddedButton.this;
                        paddedButton.paddedDrawable.setTintList(ColorStateList.valueOf(num.intValue()));
                    }
                    PaddedButton.this.paddedDrawable.setBounds(rect);
                    PaddedButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } catch (Throwable th3) {
            th = th3;
            typedArray = obtainStyledAttributes;
            if (typedArray == null) {
                throw th;
            }
            typedArray.recycle();
            throw th;
        }
    }

    public /* synthetic */ PaddedButton(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
