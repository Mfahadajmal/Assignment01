package com.google.android.accessibility.brailleime.input;

import _COROUTINE._BOUNDARY;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatDelegateImpl;
import android.text.TextUtils;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Properties;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleInputView extends View implements View.OnAttachStateChangeListener {
    public AutoPerformer autoPerformer;
    public int calibrationType$ar$edu;
    public final Callback callback;
    public CaptionText captionText;
    private final FloatingActionButton.ShadowDelegateImpl customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final BrailleInputPlane inputPlane;
    private final InputViewCaption inputViewCaption;
    private boolean isTableMode;
    public BrailleInputOptions options;
    private int orientation;
    public Size screenSizeInPixels;
    public boolean touchInteracting;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        boolean isCalibrationHoldRecognized(boolean z, int i);

        String onBrailleProduced(BrailleCharacter brailleCharacter);

        boolean onCalibration$ar$edu$55d81d41_0(int i, int i2);

        void onCalibrationFailed$ar$edu(int i);

        boolean onDotHoldAndDotSwipe(Swipe swipe, BrailleCharacter brailleCharacter);

        boolean onHoldProduced(int i);

        boolean onSwipeProduced(Swipe swipe);

        void onTwoStepCalibrationRetry(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CaptionText {
        public final ValueAnimator animator;
        public final String text;
        public final Paint textPaint;

        public CaptionText(String str) {
            this.text = str;
            Resources resources = BrailleInputView.this.getResources();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            this.animator = ofFloat;
            ofFloat.setDuration(400L);
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.accessibility.brailleime.input.BrailleInputView.CaptionText.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    BrailleInputView.this.captionText = null;
                }
            });
            ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 0));
            Paint paint = new Paint();
            this.textPaint = paint;
            paint.setColor(resources.getColor(R.color.input_plane_most_recent_animation));
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(resources.getDimensionPixelSize(R.dimen.input_view_most_recent_text_size));
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InputViewCaption {
        public final int captionBottomMarginInPixels;
        public final String text;
        public final Paint textPaint;

        public InputViewCaption(String str) {
            this.text = str;
            Resources resources = BrailleInputView.this.getResources();
            Paint paint = new Paint();
            this.textPaint = paint;
            paint.setColor(resources.getColor(R.color.input_plane_caption));
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(resources.getDimensionPixelSize(R.dimen.input_view_caption_text_size));
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setTypeface(Typeface.create(BrailleInputView.this.getContext().getString(R.string.accessibility_font), 0));
            this.captionBottomMarginInPixels = resources.getDimensionPixelOffset(R.dimen.input_view_caption_bottom_margin);
        }
    }

    public BrailleInputView(Context context, Callback callback, Size size, BrailleInputOptions brailleInputOptions) {
        super(context);
        BrailleInputPlane brailleInputPlaneTablet;
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(this);
        this.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.callback = callback;
        this.screenSizeInPixels = size;
        this.orientation = getResources().getConfiguration().orientation;
        this.options = brailleInputOptions;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        if (AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(context.getResources())) {
            brailleInputPlaneTablet = new BrailleInputPlanePhone(context, this.screenSizeInPixels, hapticPatternParser$$ExternalSyntheticLambda1, this.orientation, this.options, shadowDelegateImpl);
        } else {
            brailleInputPlaneTablet = new BrailleInputPlaneTablet(context, this.screenSizeInPixels, hapticPatternParser$$ExternalSyntheticLambda1, this.orientation, this.options, shadowDelegateImpl);
        }
        this.inputPlane = brailleInputPlaneTablet;
        setBackgroundColor(getResources().getColor(R.color.input_plane_background));
        this.inputViewCaption = new InputViewCaption(context.getString(R.string.input_view_caption));
        addOnAttachStateChangeListener(this);
    }

    public final boolean inTwoStepCalibration() {
        return this.inputPlane.inTwoStepCalibration();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        if (r6 == r3) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0056  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onDraw(android.graphics.Canvas r11) {
        /*
            Method dump skipped, instructions count: 449
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.brailleime.input.BrailleInputView.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            Rect rect = new Rect();
            rect.set(getLeft(), getTop(), getRight(), getBottom());
            ImmutableList of = ImmutableList.of((Object) rect);
            if (Build.VERSION.SDK_INT >= 29) {
                ViewCompat.Api29Impl.setSystemGestureExclusionRects(this, of);
            }
        }
    }

    public final void onOrientationChanged(int i, Size size) {
        this.orientation = i;
        this.screenSizeInPixels = size;
        BrailleInputPlane brailleInputPlane = this.inputPlane;
        brailleInputPlane.orientation = i;
        brailleInputPlane.sizeInPixels = size;
        brailleInputPlane.dotTargets = brailleInputPlane.buildDotTargets(size);
        invalidate();
        requestLayout();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                this.touchInteracting = false;
            }
        } else {
            this.touchInteracting = true;
        }
        BrailleInputPlane brailleInputPlane = this.inputPlane;
        brailleInputPlane.multitouchHandler.onTouchEvent$ar$ds$19775094_0(brailleInputPlane.context, motionEvent);
        brailleInputPlane.currentlyPressedDots = brailleInputPlane.matchTouchToTargets(brailleInputPlane.multitouchHandler.getActivePoints());
        invalidate();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        if (inTwoStepCalibration()) {
            this.callback.onCalibrationFailed$ar$edu(this.calibrationType$ar$edu);
        }
        removeOnAttachStateChangeListener(this);
    }

    public final boolean processResult(BrailleInputPlaneResult brailleInputPlaneResult) {
        int i = brailleInputPlaneResult.type;
        if (i == 0) {
            String onBrailleProduced = this.callback.onBrailleProduced(brailleInputPlaneResult.releasedBrailleCharacter);
            if (onBrailleProduced != null) {
                CaptionText captionText = this.captionText;
                if (captionText != null) {
                    captionText.animator.cancel();
                }
                CaptionText captionText2 = new CaptionText(onBrailleProduced);
                this.captionText = captionText2;
                captionText2.animator.start();
            }
        } else {
            if (i == 1) {
                boolean onSwipeProduced = this.callback.onSwipeProduced(brailleInputPlaneResult.swipe);
                if (("eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE)) && brailleInputPlaneResult.swipe.touchCount == 5 && this.autoPerformer == null) {
                    AutoPerformer autoPerformer = new AutoPerformer(getContext(), new FloatingActionButton.ShadowDelegateImpl(this));
                    this.autoPerformer = autoPerformer;
                    _BOUNDARY.d("AutoPerformer", "autoperform begin");
                    File file = new File(autoPerformer.context.getExternalFilesDir(null), "braillekeyboard_autoperform.txt");
                    if (file.exists()) {
                        try {
                            FileReader fileReader = new FileReader(file);
                            Properties properties = new Properties();
                            properties.load(fileReader);
                            fileReader.close();
                            String property = properties.getProperty("braille");
                            if (!TextUtils.isEmpty(property)) {
                                autoPerformer.interCharacterDelay = _BOUNDARY.parseIntWithDefault(properties.getProperty("interCharacterDelay"), 500);
                                autoPerformer.interWordDelay = _BOUNDARY.parseIntWithDefault(properties.getProperty("interWordDelay"), 1000);
                                int parseIntWithDefault = _BOUNDARY.parseIntWithDefault(properties.getProperty("initialDelay"), 1000);
                                autoPerformer.characters = new ArrayDeque();
                                Iterator it = Splitter.on(' ').omitEmptyStrings().split(property).iterator();
                                while (it.hasNext()) {
                                    autoPerformer.characters.addAll(new BrailleWord((String) it.next()).toList());
                                    autoPerformer.characters.add(new BrailleCharacter());
                                }
                                autoPerformer.sendEmptyMessageDelayed(0, parseIntWithDefault);
                            }
                        } catch (IOException e) {
                            _BOUNDARY.e("AutoPerformer", "autoperform read failure: ".concat(String.valueOf(e.getMessage())));
                        }
                    } else {
                        _BOUNDARY.e("AutoPerformer", "autoperform file not found at: ".concat(String.valueOf(file.getAbsolutePath())));
                    }
                }
                return onSwipeProduced;
            }
            if (i == 2) {
                int i2 = brailleInputPlaneResult.pointersHeldCount;
                if (i2 == 5) {
                    this.calibrationType$ar$edu = 1;
                    return this.callback.onCalibration$ar$edu$55d81d41_0(1, 5);
                }
                int i3 = 6;
                if (i2 == 6) {
                    this.calibrationType$ar$edu = 2;
                    return this.callback.onCalibration$ar$edu$55d81d41_0(2, 2);
                }
                if (i2 == 7) {
                    this.calibrationType$ar$edu = 3;
                    return this.callback.onCalibration$ar$edu$55d81d41_0(3, 3);
                }
                int i4 = 8;
                if (i2 == 8) {
                    this.calibrationType$ar$edu = 4;
                    return this.callback.onCalibration$ar$edu$55d81d41_0(4, 4);
                }
                if (i2 == 3) {
                    Callback callback = this.callback;
                    int i5 = this.calibrationType$ar$edu;
                    if (!brailleInputPlaneResult.isLeft) {
                        i3 = 7;
                    }
                    return callback.onCalibration$ar$edu$55d81d41_0(i5, i3);
                }
                if (i2 == 4) {
                    Callback callback2 = this.callback;
                    int i6 = this.calibrationType$ar$edu;
                    if (!brailleInputPlaneResult.isLeft) {
                        i4 = 9;
                    }
                    return callback2.onCalibration$ar$edu$55d81d41_0(i6, i4);
                }
                this.callback.onCalibrationFailed$ar$edu(this.calibrationType$ar$edu);
            } else {
                if (i == 3) {
                    return this.callback.onHoldProduced(brailleInputPlaneResult.pointersHeldCount);
                }
                return this.callback.onDotHoldAndDotSwipe(brailleInputPlaneResult.swipe, brailleInputPlaneResult.heldBrailleCharacter);
            }
        }
        return false;
    }

    public final void setTableMode(boolean z) {
        if (this.isTableMode != z) {
            BrailleInputPlane brailleInputPlane = this.inputPlane;
            brailleInputPlane.isTableTopMode = z;
            brailleInputPlane.calibrationFailCount = 0;
            brailleInputPlane.refresh();
            if (brailleInputPlane.twoStepCalibrationState$ar$edu == 3) {
                brailleInputPlane.twoStepCalibrationState$ar$edu = 1;
                brailleInputPlane.calibrationFailCount = 0;
                brailleInputPlane.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onTwoStepCalibrationFailed();
            }
            this.isTableMode = z;
            invalidate();
            requestLayout();
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
