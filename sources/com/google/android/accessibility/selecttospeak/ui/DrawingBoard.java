package com.google.android.accessibility.selecttospeak.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.google.android.accessibility.selecttospeak.R$styleable;
import com.google.android.accessibility.selecttospeak.iterator.InSentenceOffset;
import com.google.android.accessibility.selecttospeak.iterator.Sentence;
import com.google.android.accessibility.utils.RectUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.coordinate.CoordinateConverter;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DrawingBoard extends View implements HighlightBoard {
    public OnDeviceTextDetectionLoadLogEvent coordinateConverter$ar$class_merging$ar$class_merging;
    private final float cornerRadius;
    private final Rect highlightForSelection;
    private final List highlightRects;
    private boolean isScreenCaptureRunning;
    private Drawable leftBottomHandleDrawable;
    private Drawable leftTopHandleDrawable;
    private final int[] locationOnScreen;
    private int minSelectionWidth;
    private int outlineColor;
    private final Paint outlinePaint;
    private final float outlineStrokeWidth;
    private Drawable rightBottomHandleDrawable;
    private Drawable rightTopHandleDrawable;
    private final Rect selectRect;
    private RetryingNameResolver.ResolutionResultListener selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private int state;

    public DrawingBoard(Context context) {
        super(context);
        this.locationOnScreen = new int[2];
        this.selectRect = new Rect();
        this.highlightForSelection = new Rect();
        this.highlightRects = new ArrayList();
        this.outlinePaint = new Paint();
        this.outlineColor = -13408298;
        this.leftTopHandleDrawable = null;
        this.leftBottomHandleDrawable = null;
        this.rightTopHandleDrawable = null;
        this.rightBottomHandleDrawable = null;
        this.state = 0;
        this.isScreenCaptureRunning = false;
        this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        this.outlineStrokeWidth = SpannableUtils$IdentifierSpan.dpToPx(context, 4);
        this.cornerRadius = SpannableUtils$IdentifierSpan.dpToPx(context, 2);
        this.minSelectionWidth = ViewConfiguration.get(context).getScaledTouchSlop();
        initPaint();
    }

    private final void handleMotionEvent$ar$ds(MotionEvent motionEvent) {
        if (this.state == 1) {
            int round = Math.round(SpannableUtils$IdentifierSpan.clampValue(motionEvent.getX(), getLeft() + this.outlineStrokeWidth, getRight() - this.outlineStrokeWidth));
            int round2 = Math.round(SpannableUtils$IdentifierSpan.clampValue(motionEvent.getY(), getTop() + this.outlineStrokeWidth, getBottom() - this.outlineStrokeWidth));
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action != 2 && action != 7) {
                        if (action != 9) {
                            if (action != 10) {
                                return;
                            }
                        }
                    } else {
                        this.selectRect.right = round;
                        this.selectRect.bottom = round2;
                        this.highlightForSelection.set(this.selectRect);
                        this.highlightForSelection.sort();
                        invalidate();
                        return;
                    }
                }
                Rect rect = this.selectRect;
                int i = this.minSelectionWidth;
                int i2 = rect.left;
                int i3 = rect.right;
                int abs = (i - Math.abs(rect.left - rect.right)) / 2;
                if (abs > 0) {
                    if (i2 > i3) {
                        rect.left += abs;
                        rect.right -= abs;
                    } else {
                        rect.left -= abs;
                        rect.right += abs;
                    }
                }
                int i4 = rect.top;
                int i5 = rect.bottom;
                int abs2 = (i - Math.abs(rect.bottom - rect.top)) / 2;
                if (abs2 > 0) {
                    if (i4 > i5) {
                        rect.top += abs2;
                        rect.bottom -= abs2;
                    } else {
                        rect.top -= abs2;
                        rect.bottom += abs2;
                    }
                }
                this.highlightForSelection.set(this.selectRect);
                this.highlightForSelection.sort();
                invalidate();
                this.state = 2;
                if (this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                    Rect rect2 = new Rect(this.highlightForSelection);
                    getLocationOnScreen(this.locationOnScreen);
                    int[] iArr = this.locationOnScreen;
                    rect2.offset(iArr[0], iArr[1]);
                    this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSelectionEnd(rect2);
                    this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
                    return;
                }
                return;
            }
            this.selectRect.set(round, round2, round, round2);
            this.highlightForSelection.set(round, round2, round, round2);
            if (this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                getLocationOnScreen(this.locationOnScreen);
                RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                int[] iArr2 = this.locationOnScreen;
                new Point(round + iArr2[0], round2 + iArr2[1]);
                resolutionResultListener.onSelectionStart$ar$ds();
            }
            invalidate();
        }
    }

    private final void initPaint() {
        this.outlinePaint.setStyle(Paint.Style.STROKE);
        this.outlinePaint.setColor(this.outlineColor);
        this.outlinePaint.setStrokeWidth(this.outlineStrokeWidth);
        this.outlinePaint.setAntiAlias(true);
    }

    @Override // com.google.android.accessibility.selecttospeak.ui.HighlightBoard
    public final void clear$ar$ds() {
        this.state = 0;
        this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        this.selectRect.setEmpty();
        this.highlightForSelection.setEmpty();
        this.highlightRects.clear();
        invalidate();
    }

    @Override // com.google.android.accessibility.selecttospeak.ui.HighlightBoard
    public final void highlight(boolean z, Sentence sentence, InSentenceOffset inSentenceOffset) {
        List<Rect> bounds;
        Rect displayGlobal;
        if (inSentenceOffset.isStarted()) {
            bounds = sentence.bounds(z, inSentenceOffset.start, inSentenceOffset.end);
        } else {
            bounds = sentence.bounds(z, 0, sentence.text.length());
        }
        if (bounds != null && bounds.size() > 1) {
            ArrayList arrayList = new ArrayList(bounds);
            Collections.sort(arrayList, RectUtils.RECT_POSITION_COMPARATOR);
            bounds.clear();
            Rect rect = new Rect((Rect) arrayList.get(0));
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Rect rect2 = (Rect) arrayList.get(i);
                if (rect.top == rect2.top && rect.bottom == rect2.bottom) {
                    rect.set(Math.min(rect.left, rect2.left), Math.min(rect.top, rect2.top), Math.max(rect.right, rect2.right), Math.max(rect.bottom, rect2.bottom));
                } else {
                    bounds.add(rect);
                    rect = new Rect(rect2);
                }
            }
            bounds.add(rect);
        }
        getLocationOnScreen(this.locationOnScreen);
        this.state = 3;
        this.highlightForSelection.setEmpty();
        this.highlightRects.clear();
        for (Rect rect3 : bounds) {
            int[] iArr = this.locationOnScreen;
            rect3.offset(-iArr[0], -iArr[1]);
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = this.coordinateConverter$ar$class_merging$ar$class_merging;
            rect3.getClass();
            Rect displayLocal = onDeviceTextDetectionLoadLogEvent.getDisplayLocal();
            if (displayLocal != null && (displayGlobal = onDeviceTextDetectionLoadLogEvent.getDisplayGlobal()) != null) {
                CoordinateConverter.convert$ar$ds(displayLocal, displayGlobal, rect3);
            }
            this.highlightRects.add(rect3);
        }
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        boolean z;
        if (!this.isScreenCaptureRunning) {
            if (!this.highlightForSelection.isEmpty() || !this.highlightRects.isEmpty()) {
                if (!this.highlightForSelection.isEmpty()) {
                    float f = this.highlightForSelection.left;
                    float f2 = this.highlightForSelection.top;
                    float f3 = this.highlightForSelection.right;
                    float f4 = this.highlightForSelection.bottom;
                    float f5 = this.cornerRadius;
                    canvas.drawRoundRect(f, f2, f3, f4, f5, f5, this.outlinePaint);
                }
                float strokeWidth = this.outlinePaint.getStrokeWidth() / 2.0f;
                if (!this.highlightRects.isEmpty()) {
                    int i = Resources.getSystem().getDisplayMetrics().widthPixels;
                    for (Rect rect : this.highlightRects) {
                        float max = Math.max(strokeWidth, rect.left);
                        float f6 = rect.top;
                        float min = Math.min(rect.right + strokeWidth, (i - strokeWidth) - 1.0f);
                        float f7 = rect.bottom;
                        float f8 = this.cornerRadius;
                        canvas.drawRoundRect(max, f6, min, f7, f8, f8, this.outlinePaint);
                    }
                }
                int i2 = this.state;
                boolean z2 = true;
                if (i2 == 1 || i2 == 2) {
                    Rect rect2 = this.selectRect;
                    int i3 = rect2.left - rect2.right;
                    Rect rect3 = this.selectRect;
                    int i4 = rect3.top - rect3.bottom;
                    if ((i3 * i3) + (i4 * i4) >= 30000) {
                        if (i3 > 0) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (i4 > 0) {
                            z2 = false;
                        }
                        if (z == z2) {
                            Drawable drawable = this.leftTopHandleDrawable;
                            if (drawable != null) {
                                int intrinsicWidth = this.highlightForSelection.left - this.leftTopHandleDrawable.getIntrinsicWidth();
                                int intrinsicHeight = this.highlightForSelection.top - this.leftBottomHandleDrawable.getIntrinsicHeight();
                                Rect rect4 = this.highlightForSelection;
                                drawable.setBounds(intrinsicWidth, intrinsicHeight, rect4.left, rect4.top);
                                this.leftTopHandleDrawable.draw(canvas);
                            }
                            Drawable drawable2 = this.rightBottomHandleDrawable;
                            if (drawable2 != null) {
                                Rect rect5 = this.highlightForSelection;
                                drawable2.setBounds(rect5.right, rect5.bottom, rect5.right + this.rightBottomHandleDrawable.getIntrinsicWidth(), this.highlightForSelection.bottom + this.rightBottomHandleDrawable.getIntrinsicHeight());
                                this.rightBottomHandleDrawable.draw(canvas);
                            }
                        } else {
                            Drawable drawable3 = this.leftBottomHandleDrawable;
                            if (drawable3 != null) {
                                int intrinsicWidth2 = this.highlightForSelection.left - this.leftBottomHandleDrawable.getIntrinsicWidth();
                                Rect rect6 = this.highlightForSelection;
                                drawable3.setBounds(intrinsicWidth2, rect6.bottom, rect6.left, rect6.bottom + this.leftBottomHandleDrawable.getIntrinsicHeight());
                                this.leftBottomHandleDrawable.draw(canvas);
                            }
                            Drawable drawable4 = this.rightTopHandleDrawable;
                            if (drawable4 != null) {
                                Rect rect7 = this.highlightForSelection;
                                drawable4.setBounds(rect7.right, rect7.top - this.rightTopHandleDrawable.getIntrinsicHeight(), this.highlightForSelection.right + this.rightTopHandleDrawable.getIntrinsicWidth(), this.highlightForSelection.top);
                                this.rightTopHandleDrawable.draw(canvas);
                            }
                        }
                    }
                    float f9 = this.cornerRadius;
                    canvas.drawRoundRect(this.highlightForSelection.left - strokeWidth, this.highlightForSelection.top - strokeWidth, this.highlightForSelection.right + strokeWidth, this.highlightForSelection.bottom + strokeWidth, f9, f9, this.outlinePaint);
                }
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        handleMotionEvent$ar$ds(motionEvent);
        return true;
    }

    public final void onScreenCaptureDone() {
        this.isScreenCaptureRunning = false;
    }

    public final void onScreenCaptureStart() {
        this.isScreenCaptureRunning = true;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        handleMotionEvent$ar$ds(motionEvent);
        return true;
    }

    public final void requestSelection$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        clear$ar$ds();
        this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.state = 1;
    }

    public DrawingBoard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.locationOnScreen = new int[2];
        this.selectRect = new Rect();
        this.highlightForSelection = new Rect();
        this.highlightRects = new ArrayList();
        this.outlinePaint = new Paint();
        this.outlineColor = -13408298;
        this.leftTopHandleDrawable = null;
        this.leftBottomHandleDrawable = null;
        this.rightTopHandleDrawable = null;
        this.rightBottomHandleDrawable = null;
        this.state = 0;
        this.isScreenCaptureRunning = false;
        this.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        this.minSelectionWidth = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.DrawingBoard, 0, 0);
        try {
            this.outlineColor = obtainStyledAttributes.getColor(7, -13408298);
            this.outlineStrokeWidth = obtainStyledAttributes.getDimension(8, SpannableUtils$IdentifierSpan.dpToPx(context, 4));
            this.minSelectionWidth = (int) obtainStyledAttributes.getDimension(6, ViewConfiguration.get(context).getScaledTouchSlop());
            this.cornerRadius = obtainStyledAttributes.getDimension(0, SpannableUtils$IdentifierSpan.dpToPx(context, 2));
            this.leftTopHandleDrawable = obtainStyledAttributes.getDrawable(5);
            this.leftBottomHandleDrawable = obtainStyledAttributes.getDrawable(4);
            this.rightTopHandleDrawable = obtainStyledAttributes.getDrawable(10);
            this.rightBottomHandleDrawable = obtainStyledAttributes.getDrawable(9);
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(obtainStyledAttributes.getColor(1, -12417548), PorterDuff.Mode.SRC_IN);
            this.leftTopHandleDrawable.setColorFilter(porterDuffColorFilter);
            this.leftBottomHandleDrawable.setColorFilter(porterDuffColorFilter);
            this.rightTopHandleDrawable.setColorFilter(porterDuffColorFilter);
            this.rightBottomHandleDrawable.setColorFilter(porterDuffColorFilter);
            obtainStyledAttributes.recycle();
            initPaint();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public DrawingBoard(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }
}
