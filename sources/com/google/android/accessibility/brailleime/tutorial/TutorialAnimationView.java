package com.google.android.accessibility.brailleime.tutorial;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v7.app.AppCompatDelegateImpl;
import android.util.Size;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TutorialAnimationView extends FrameLayout {
    private final TextView actionResult;
    private final Animation actionResultAnimation;
    public final CanvasView canvasView;
    private final TextView hintToast;
    private final Animation hintToastAnimation;
    private final boolean isTabletop;
    private final SwipeAnimation swipeAnimation;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CanvasView extends View {
        public Size canvasSize;
        private final boolean isTabletop;
        public int orientation;
        public final List painters;

        public CanvasView(Context context, int i, Size size, boolean z) {
            super(context);
            this.painters = new ArrayList();
            this.orientation = i;
            this.isTabletop = z;
            this.canvasSize = calculateCanvasSize(size);
        }

        public final Size calculateCanvasSize(Size size) {
            if (AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(getResources()) && this.orientation == 1) {
                return new Size(size.getHeight(), size.getWidth());
            }
            return size;
        }

        @Override // android.view.View
        protected final void onDraw(Canvas canvas) {
            float f;
            float f2;
            float f3;
            float f4;
            canvas.save();
            if (AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(getResources())) {
                float f5 = 0.0f;
                if (this.orientation == 1) {
                    if (true != this.isTabletop) {
                        f3 = 270.0f;
                    } else {
                        f3 = 90.0f;
                    }
                    canvas.rotate(f3);
                    if (this.isTabletop) {
                        f4 = 0.0f;
                    } else {
                        f4 = -this.canvasSize.getWidth();
                    }
                    if (this.isTabletop) {
                        f5 = -this.canvasSize.getHeight();
                    }
                    canvas.translate(f4, f5);
                } else {
                    if (true != this.isTabletop) {
                        f = 0.0f;
                    } else {
                        f = 180.0f;
                    }
                    canvas.rotate(f);
                    if (this.isTabletop) {
                        f2 = -this.canvasSize.getWidth();
                    } else {
                        f2 = 0.0f;
                    }
                    if (this.isTabletop) {
                        f5 = -this.canvasSize.getHeight();
                    }
                    canvas.translate(f2, f5);
                }
            }
            Iterator it = this.painters.iterator();
            while (it.hasNext()) {
                ((SwipeAnimation) it.next()).onDraw$ar$ds$10b4e3b9_0(canvas);
            }
            canvas.restore();
        }

        public final void removePainter$ar$class_merging(SwipeAnimation swipeAnimation) {
            this.painters.remove(swipeAnimation);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SwipeAnimation {
        public ValueAnimator animator;
        private final Paint circlePaint;
        public final int dotRadiusInPixels;
        public final int gestureGradientColor;
        public final Runnable invalidate;
        public final Paint roundRectPaint;
        public int direction$ar$edu = 1;
        public final List gesturesCircleCoordinates = new ArrayList();

        public SwipeAnimation(Context context, Runnable runnable) {
            this.invalidate = runnable;
            Paint paint = new Paint();
            this.circlePaint = paint;
            paint.setColor(context.getColor(R.color.gesture_circle));
            int color = context.getColor(R.color.gesture_circle_gradient);
            this.gestureGradientColor = color;
            Paint paint2 = new Paint();
            this.roundRectPaint = paint2;
            paint2.setColor(color);
            this.dotRadiusInPixels = context.getResources().getDimensionPixelSize(R.dimen.tutorial_gesture_dot_radius);
        }

        public final void onDraw$ar$ds$10b4e3b9_0(Canvas canvas) {
            float floatValue = ((Float) this.animator.getAnimatedValue()).floatValue();
            for (Point point : this.gesturesCircleCoordinates) {
                int i = this.direction$ar$edu;
                int i2 = i - 1;
                if (i != 0) {
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 != 2) {
                                if (i2 == 3) {
                                    int i3 = point.y;
                                    int i4 = this.dotRadiusInPixels;
                                    float f = i4;
                                    canvas.drawRoundRect(point.x + this.dotRadiusInPixels, point.y - this.dotRadiusInPixels, (point.x - this.dotRadiusInPixels) - floatValue, i3 + i4, f, f, this.roundRectPaint);
                                    canvas.drawCircle(point.x - floatValue, point.y, this.dotRadiusInPixels, this.circlePaint);
                                }
                            } else {
                                int i5 = point.y;
                                int i6 = this.dotRadiusInPixels;
                                float f2 = i6;
                                canvas.drawRoundRect(point.x - this.dotRadiusInPixels, point.y - this.dotRadiusInPixels, point.x + this.dotRadiusInPixels + floatValue, i5 + i6, f2, f2, this.roundRectPaint);
                                canvas.drawCircle(point.x + floatValue, point.y, this.dotRadiusInPixels, this.circlePaint);
                            }
                        } else {
                            int i7 = point.x - this.dotRadiusInPixels;
                            int i8 = point.y + this.dotRadiusInPixels;
                            int i9 = point.x + this.dotRadiusInPixels;
                            int i10 = point.y;
                            float f3 = this.dotRadiusInPixels;
                            canvas.drawRoundRect(i7, i8, i9, (i10 - r4) - floatValue, f3, f3, this.roundRectPaint);
                            canvas.drawCircle(point.x, point.y - floatValue, this.dotRadiusInPixels, this.circlePaint);
                        }
                    } else {
                        int i11 = point.x - this.dotRadiusInPixels;
                        int i12 = point.y - this.dotRadiusInPixels;
                        int i13 = point.x + this.dotRadiusInPixels;
                        int i14 = point.y;
                        float f4 = this.dotRadiusInPixels;
                        canvas.drawRoundRect(i11, i12, i13, i14 + r4 + floatValue, f4, f4, this.roundRectPaint);
                        canvas.drawCircle(point.x, point.y + floatValue, this.dotRadiusInPixels, this.circlePaint);
                    }
                } else {
                    throw null;
                }
            }
        }
    }

    public TutorialAnimationView(Context context, int i, Size size, boolean z) {
        super(context);
        inflate(getContext(), R.layout.tutorial_animation_view, this);
        this.isTabletop = z;
        CanvasView canvasView = new CanvasView(context, i, size, z);
        this.canvasView = canvasView;
        canvasView.setLayoutParams(new FrameLayout.LayoutParams(size.getWidth(), size.getHeight()));
        addView(canvasView);
        this.hintToast = (TextView) findViewById(R.id.hint_toast);
        this.actionResult = (TextView) findViewById(R.id.action_result);
        this.swipeAnimation = new SwipeAnimation(context, new ContextMenuDialog$$ExternalSyntheticLambda5(canvasView, 9));
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        this.actionResultAnimation = alphaAnimation;
        alphaAnimation.setDuration(300L);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        this.hintToastAnimation = alphaAnimation2;
        alphaAnimation2.setDuration(200L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void reset() {
        this.hintToast.setVisibility(4);
        this.actionResult.setVisibility(4);
        this.canvasView.removePainter$ar$class_merging(this.swipeAnimation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void startActionResultAnimation(String str) {
        this.actionResult.setText(str);
        this.actionResult.setVisibility(0);
        this.actionResult.startAnimation(this.actionResultAnimation);
    }

    public final void startHintToastAnimation(String str) {
        this.hintToast.setText(str);
        this.hintToast.setVisibility(0);
        this.hintToast.startAnimation(this.hintToastAnimation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void startSwipeAnimation$ar$edu(int i, int i2) {
        int height;
        int i3;
        int width;
        int height2;
        int i4;
        int i5;
        int height3;
        int i6 = i2;
        Size size = this.canvasView.canvasSize;
        if (this.isTabletop) {
            if (i6 == 3) {
                i6 = 4;
            } else if (i6 == 4) {
                i6 = 3;
            }
        }
        SwipeAnimation swipeAnimation = this.swipeAnimation;
        swipeAnimation.direction$ar$edu = i6;
        int i7 = i6 - 1;
        if (i7 != 0 && i7 != 1) {
            height = size.getWidth();
        } else {
            height = size.getHeight();
        }
        float f = height * 0.5f;
        swipeAnimation.gesturesCircleCoordinates.clear();
        int i8 = swipeAnimation.dotRadiusInPixels;
        int i9 = i8 + i8;
        int i10 = i - 1;
        for (int i11 = 0; i11 < i; i11++) {
            int i12 = (int) f;
            int i13 = i9 + 75;
            int i14 = (i10 * i13) / 2;
            if (i7 != 0) {
                if (i7 != 1) {
                    if (i7 != 2) {
                        i5 = i13 * i11;
                        i4 = ((size.getWidth() - i12) / 2) + i12;
                        height3 = size.getHeight() / 2;
                    } else {
                        i5 = i13 * i11;
                        i4 = (size.getWidth() - i12) / 2;
                        height3 = size.getHeight() / 2;
                    }
                    height2 = (height3 - i14) + i5;
                    swipeAnimation.gesturesCircleCoordinates.add(new Point(i4, height2));
                } else {
                    i3 = i13 * i11;
                    width = (size.getWidth() / 2) - i14;
                    height2 = ((size.getHeight() - i12) / 2) + i12;
                }
            } else {
                i3 = i13 * i11;
                width = (size.getWidth() / 2) - i14;
                height2 = (size.getHeight() - i12) / 2;
            }
            i4 = width + i3;
            swipeAnimation.gesturesCircleCoordinates.add(new Point(i4, height2));
        }
        int i15 = (int) f;
        Rect rect = new Rect();
        rect.left = ((Point) swipeAnimation.gesturesCircleCoordinates.get(0)).x - swipeAnimation.dotRadiusInPixels;
        rect.top = ((Point) swipeAnimation.gesturesCircleCoordinates.get(0)).y - swipeAnimation.dotRadiusInPixels;
        if (i7 != 0) {
            if (i7 != 1) {
                if (i7 != 2) {
                    rect.left = ((Point) swipeAnimation.gesturesCircleCoordinates.get(0)).x + swipeAnimation.dotRadiusInPixels;
                    rect.right = rect.left - i15;
                    rect.bottom = rect.top;
                } else {
                    rect.right = rect.left + i15;
                    rect.bottom = rect.top;
                }
            } else {
                rect.right = rect.left;
                rect.top = ((Point) swipeAnimation.gesturesCircleCoordinates.get(0)).y + swipeAnimation.dotRadiusInPixels;
                rect.bottom = rect.top - i15;
            }
        } else {
            rect.right = rect.left;
            rect.bottom = rect.top + i15;
        }
        swipeAnimation.roundRectPaint.setShader(new LinearGradient(rect.left, rect.top, rect.right, rect.bottom, 0, swipeAnimation.gestureGradientColor, Shader.TileMode.CLAMP));
        swipeAnimation.animator = ValueAnimator.ofFloat(0.0f, f, f);
        swipeAnimation.animator.setDuration(1500L);
        swipeAnimation.animator.setRepeatCount(-1);
        swipeAnimation.animator.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(swipeAnimation, 4));
        CanvasView canvasView = this.canvasView;
        SwipeAnimation swipeAnimation2 = this.swipeAnimation;
        if (!canvasView.painters.contains(swipeAnimation2)) {
            canvasView.painters.add(swipeAnimation2);
        }
        this.swipeAnimation.animator.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void stopSwipeAnimation() {
        this.canvasView.removePainter$ar$class_merging(this.swipeAnimation);
        this.swipeAnimation.animator.cancel();
    }
}
