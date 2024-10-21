package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.WindowDecorActionBar;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.accessibility.selecttospeak.ui.OverlayCoordinatesAnimator;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.Iterator;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CircularProgressDrawable extends Drawable implements Animatable {
    private Animator mAnimator;
    boolean mFinishing;
    public final Resources mResources;
    public final Ring mRing;
    private float mRotation;
    float mRotationCount;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final int[] COLORS = {-16777216};

    /* compiled from: PG */
    /* renamed from: androidx.swiperefreshlayout.widget.CircularProgressDrawable$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ Object CircularProgressDrawable$1$ar$this$0;
        final /* synthetic */ Object CircularProgressDrawable$1$ar$val$ring;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(CircularProgressDrawable circularProgressDrawable, Ring ring, int i) {
            this.switching_field = i;
            this.CircularProgressDrawable$1$ar$this$0 = circularProgressDrawable;
            this.CircularProgressDrawable$1$ar$val$ring = ring;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        ((MaterialShapeDrawable) this.CircularProgressDrawable$1$ar$this$0).setElevation(floatValue);
                        AppBarLayout appBarLayout = (AppBarLayout) this.CircularProgressDrawable$1$ar$val$ring;
                        Drawable drawable = appBarLayout.statusBarForeground;
                        if (drawable instanceof MaterialShapeDrawable) {
                            ((MaterialShapeDrawable) drawable).setElevation(floatValue);
                        }
                        Iterator it = appBarLayout.liftOnScrollListeners.iterator();
                        while (it.hasNext()) {
                            ((AppBarLayout.LiftOnScrollListener) it.next()).onUpdate$ar$ds();
                        }
                        return;
                    }
                    float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    OverlayCoordinatesAnimator overlayCoordinatesAnimator = (OverlayCoordinatesAnimator) this.CircularProgressDrawable$1$ar$val$ring;
                    int[] iArr = overlayCoordinatesAnimator.fromPixelCoordinates;
                    int i2 = iArr[0];
                    int[] iArr2 = overlayCoordinatesAnimator.toPixelCoordinates;
                    ((NodeMenuRuleCreator) this.CircularProgressDrawable$1$ar$this$0).setPixelCoordinates((int) (i2 + ((iArr2[0] - i2) * floatValue2)), (int) (iArr[1] + (floatValue2 * (iArr2[1] - r3))));
                    return;
                }
                ((View) ((WindowDecorActionBar) ((FloatingActionButton.ShadowDelegateImpl) this.CircularProgressDrawable$1$ar$val$ring).FloatingActionButton$ShadowDelegateImpl$ar$this$0).mContainerView.getParent()).invalidate();
                return;
            }
            float floatValue3 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            CircularProgressDrawable.updateRingColor$ar$ds(floatValue3, (Ring) this.CircularProgressDrawable$1$ar$val$ring);
            ((CircularProgressDrawable) this.CircularProgressDrawable$1$ar$this$0).applyTransformation(floatValue3, (Ring) this.CircularProgressDrawable$1$ar$val$ring, false);
            ((CircularProgressDrawable) this.CircularProgressDrawable$1$ar$this$0).invalidateSelf();
        }

        public /* synthetic */ AnonymousClass1(Object obj, Object obj2, int i) {
            this.switching_field = i;
            this.CircularProgressDrawable$1$ar$val$ring = obj;
            this.CircularProgressDrawable$1$ar$this$0 = obj2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Ring {
        int mAlpha;
        Path mArrow;
        int mArrowHeight;
        final Paint mArrowPaint;
        float mArrowScale;
        int mArrowWidth;
        final Paint mCirclePaint;
        int mColorIndex;
        int[] mColors;
        int mCurrentColor;
        float mEndTrim;
        final Paint mPaint;
        float mRingCenterRadius;
        float mRotation;
        boolean mShowArrow;
        float mStartTrim;
        float mStartingEndTrim;
        float mStartingRotation;
        float mStartingStartTrim;
        float mStrokeWidth;
        final RectF mTempBounds = new RectF();

        public Ring() {
            Paint paint = new Paint();
            this.mPaint = paint;
            Paint paint2 = new Paint();
            this.mArrowPaint = paint2;
            Paint paint3 = new Paint();
            this.mCirclePaint = paint3;
            this.mStartTrim = 0.0f;
            this.mEndTrim = 0.0f;
            this.mRotation = 0.0f;
            this.mStrokeWidth = 5.0f;
            this.mArrowScale = 1.0f;
            this.mAlpha = PrivateKeyType.INVALID;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        final int getNextColorIndex() {
            int length = this.mColors.length;
            return 0;
        }

        final int getStartingColor() {
            return this.mColors[0];
        }

        final void resetOriginals() {
            this.mStartingStartTrim = 0.0f;
            this.mStartingEndTrim = 0.0f;
            this.mStartingRotation = 0.0f;
            this.mStartTrim = 0.0f;
            this.mEndTrim = 0.0f;
            this.mRotation = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void setColorIndex(int i) {
            this.mColorIndex = 0;
            this.mCurrentColor = this.mColors[0];
        }

        final void setShowArrow(boolean z) {
            if (this.mShowArrow != z) {
                this.mShowArrow = z;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void setStrokeWidth(float f) {
            this.mStrokeWidth = f;
            this.mPaint.setStrokeWidth(f);
        }

        final void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }
    }

    public CircularProgressDrawable(Context context) {
        context.getClass();
        this.mResources = context.getResources();
        final Ring ring = new Ring();
        this.mRing = ring;
        ring.mColors = COLORS;
        ring.setColorIndex(0);
        ring.setStrokeWidth(2.5f);
        invalidateSelf();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new AnonymousClass1(this, ring, 0));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(LINEAR_INTERPOLATOR);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: androidx.swiperefreshlayout.widget.CircularProgressDrawable.2
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.this.applyTransformation(1.0f, ring, true);
                ring.storeOriginals();
                Ring ring2 = ring;
                ring2.getNextColorIndex();
                ring2.setColorIndex(0);
                CircularProgressDrawable circularProgressDrawable = CircularProgressDrawable.this;
                if (circularProgressDrawable.mFinishing) {
                    circularProgressDrawable.mFinishing = false;
                    animator.cancel();
                    animator.setDuration(1332L);
                    animator.start();
                    ring.setShowArrow(false);
                    return;
                }
                circularProgressDrawable.mRotationCount += 1.0f;
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                CircularProgressDrawable.this.mRotationCount = 0.0f;
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
            }
        });
        this.mAnimator = ofFloat;
    }

    static final void updateRingColor$ar$ds(float f, Ring ring) {
        if (f > 0.75f) {
            int startingColor = ring.getStartingColor();
            int[] iArr = ring.mColors;
            ring.getNextColorIndex();
            int i = iArr[0];
            int i2 = startingColor >> 24;
            int i3 = startingColor >> 16;
            int i4 = startingColor >> 8;
            int i5 = startingColor & PrivateKeyType.INVALID;
            int i6 = i >> 24;
            int i7 = i >> 16;
            int i8 = i >> 8;
            int i9 = i & PrivateKeyType.INVALID;
            int i10 = i8 & PrivateKeyType.INVALID;
            int i11 = i7 & PrivateKeyType.INVALID;
            float f2 = (f - 0.75f) / 0.25f;
            int i12 = (i4 & PrivateKeyType.INVALID) + ((int) ((i10 - r4) * f2));
            int i13 = (i3 & PrivateKeyType.INVALID) + ((int) ((i11 - r3) * f2));
            ring.mCurrentColor = (((i2 & PrivateKeyType.INVALID) + ((int) (f2 * ((i6 & PrivateKeyType.INVALID) - r2)))) << 24) | (i13 << 16) | (i12 << 8) | (i5 + ((int) ((i9 - i5) * f2)));
            return;
        }
        ring.mCurrentColor = ring.getStartingColor();
    }

    final void applyTransformation(float f, Ring ring, boolean z) {
        float interpolation;
        float f2;
        if (this.mFinishing) {
            updateRingColor$ar$ds(f, ring);
            double floor = Math.floor(ring.mStartingRotation / 0.8f) + 1.0d;
            float f3 = ring.mStartingStartTrim;
            float f4 = ring.mStartingEndTrim;
            ring.mStartTrim = f3 + ((((-0.01f) + f4) - f3) * f);
            ring.mEndTrim = f4;
            float f5 = ring.mStartingRotation;
            ring.mRotation = f5 + ((((float) floor) - f5) * f);
            return;
        }
        if (f == 1.0f && !z) {
            return;
        }
        float f6 = ring.mStartingRotation;
        if (f < 0.5f) {
            interpolation = ring.mStartingStartTrim;
            f2 = (MATERIAL_INTERPOLATOR.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
        } else {
            float f7 = ring.mStartingStartTrim + 0.79f;
            interpolation = f7 - (((1.0f - MATERIAL_INTERPOLATOR.getInterpolation(((-0.5f) + f) / 0.5f)) * 0.79f) + 0.01f);
            f2 = f7;
        }
        float f8 = f6 + (0.20999998f * f);
        float f9 = f + this.mRotationCount;
        ring.mStartTrim = interpolation;
        ring.mEndTrim = f2;
        ring.mRotation = f8;
        this.mRotation = f9 * 216.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        Ring ring = this.mRing;
        float f = ring.mRingCenterRadius;
        float f2 = (ring.mStrokeWidth / 2.0f) + f;
        if (f <= 0.0f) {
            f2 = (Math.min(bounds.width(), bounds.height()) / 2.0f) - Math.max((ring.mArrowWidth * ring.mArrowScale) / 2.0f, ring.mStrokeWidth / 2.0f);
        }
        RectF rectF = ring.mTempBounds;
        rectF.set(bounds.centerX() - f2, bounds.centerY() - f2, bounds.centerX() + f2, bounds.centerY() + f2);
        float f3 = ring.mStartTrim;
        float f4 = ring.mRotation;
        float f5 = f3 + f4;
        float f6 = ring.mEndTrim + f4;
        ring.mPaint.setColor(ring.mCurrentColor);
        ring.mPaint.setAlpha(ring.mAlpha);
        float f7 = ring.mStrokeWidth / 2.0f;
        rectF.inset(f7, f7);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, ring.mCirclePaint);
        float f8 = -f7;
        rectF.inset(f8, f8);
        float f9 = f5 * 360.0f;
        float f10 = (f6 * 360.0f) - f9;
        canvas.drawArc(rectF, f9, f10, false, ring.mPaint);
        if (ring.mShowArrow) {
            Path path = ring.mArrow;
            if (path == null) {
                ring.mArrow = new Path();
                ring.mArrow.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            float min = Math.min(rectF.width(), rectF.height()) / 2.0f;
            float f11 = (ring.mArrowWidth * ring.mArrowScale) / 2.0f;
            ring.mArrow.moveTo(0.0f, 0.0f);
            ring.mArrow.lineTo(ring.mArrowWidth * ring.mArrowScale, 0.0f);
            Path path2 = ring.mArrow;
            float f12 = ring.mArrowWidth;
            float f13 = ring.mArrowScale;
            path2.lineTo((f12 * f13) / 2.0f, ring.mArrowHeight * f13);
            ring.mArrow.offset((min + rectF.centerX()) - f11, rectF.centerY() + (ring.mStrokeWidth / 2.0f));
            ring.mArrow.close();
            ring.mArrowPaint.setColor(ring.mCurrentColor);
            ring.mArrowPaint.setAlpha(ring.mAlpha);
            canvas.save();
            canvas.rotate(f9 + f10, rectF.centerX(), rectF.centerY());
            canvas.drawPath(ring.mArrow, ring.mArrowPaint);
            canvas.restore();
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.mRing.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.mRing.mAlpha = i;
        invalidateSelf();
    }

    public final void setArrowEnabled(boolean z) {
        this.mRing.setShowArrow(z);
        invalidateSelf();
    }

    public final void setArrowScale(float f) {
        Ring ring = this.mRing;
        if (f != ring.mArrowScale) {
            ring.mArrowScale = f;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.mRing.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final void setStartEndTrim$ar$ds(float f) {
        Ring ring = this.mRing;
        ring.mStartTrim = 0.0f;
        ring.mEndTrim = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.mAnimator.cancel();
        this.mRing.storeOriginals();
        Ring ring = this.mRing;
        if (ring.mEndTrim != ring.mStartTrim) {
            this.mFinishing = true;
            this.mAnimator.setDuration(666L);
            this.mAnimator.start();
        } else {
            ring.setColorIndex(0);
            this.mRing.resetOriginals();
            this.mAnimator.setDuration(1332L);
            this.mAnimator.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.mAnimator.cancel();
        this.mRotation = 0.0f;
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        invalidateSelf();
    }
}
