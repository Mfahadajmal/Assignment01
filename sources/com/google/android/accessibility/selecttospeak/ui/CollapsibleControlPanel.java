package com.google.android.accessibility.selecttospeak.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.accessibility.brailleime.tutorial.TutorialView$TypeLetterA$1;
import com.google.android.accessibility.selecttospeak.R$styleable;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CollapsibleControlPanel extends LinearLayout {
    public static final /* synthetic */ int CollapsibleControlPanel$ar$NoOp = 0;
    private static final int[][] INIT_RESOURCE_MATRIX;
    private static final int[][] INIT_RESOURCE_MATRIX_FOR_COLLAPSE_EXPAND_BUTTON;
    private static final int[][] INIT_RESOURCE_MATRIX_FOR_PLAY_PAUSE_BUTTON;
    private static final int UNSPECIFIED_MEASURE_SPEC = View.MeasureSpec.makeMeasureSpec(0, 0);
    public CharSequence collapseButtonContentDescription;
    public Drawable collapseButtonDrawable;
    public final CollapseExpandAnimator collapseExpandAnimator;
    public ImageButton collapseExpandButton;
    private final View.OnClickListener controlButtonClickListener;
    public CharSequence expandButtonContentDescription;
    public Drawable expandButtonDrawable;
    public boolean isCollapsed;
    public boolean isShowingSettingButton;
    public View leftCollapsiblePanel;
    public final List listeners;
    public View.OnTouchListener onTouchListener;
    private Drawable pauseButtonDrawable;
    private Drawable playButtonDrawable;
    public int playButtonState;
    public View rightCollapsiblePanel;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CollapseExpandAnimator extends ValueAnimator {
        public boolean isExpanding;
        private float leftPanelFromAlpha;
        private int leftPanelFromWidth;
        private float leftPanelToAlpha;
        private int leftPanelToWidth;
        private float rightPanelFromAlpha;
        private int rightPanelFromWidth;
        private float rightPanelToAlpha;
        private int rightPanelToWidth;
        private final Interpolator expandAnimationInterpolator = new DecelerateInterpolator();
        private final Interpolator collapseAnimationInterpolator = new DecelerateInterpolator();
        private final Animator.AnimatorListener defaultAnimatorListener = new TutorialView$TypeLetterA$1(this, 2);

        public CollapseExpandAnimator() {
            addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 5));
        }

        /* renamed from: lambda$new$0$com-google-android-accessibility-selecttospeak-ui-CollapsibleControlPanel$CollapseExpandAnimator, reason: not valid java name */
        public final /* synthetic */ void m93xfe6f1bb8(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float f = 1.0f - floatValue;
            float f2 = this.leftPanelToWidth * floatValue;
            CollapsibleControlPanel.this.updatePanelLayout((int) ((this.leftPanelFromWidth * f) + f2), (this.leftPanelFromAlpha * f) + (this.leftPanelToAlpha * floatValue), (int) ((this.rightPanelFromWidth * f) + (this.rightPanelToWidth * floatValue)), (this.rightPanelFromAlpha * f) + (this.rightPanelToAlpha * floatValue));
        }

        public final void prepareForCollapseAnimation$ar$ds() {
            cancel();
            removeAllListeners();
            addListener(this.defaultAnimatorListener);
            this.isExpanding = false;
            this.leftPanelFromWidth = CollapsibleControlPanel.getViewWidth(CollapsibleControlPanel.this.leftCollapsiblePanel);
            this.leftPanelToWidth = 0;
            this.leftPanelFromAlpha = CollapsibleControlPanel.getViewAlpha(CollapsibleControlPanel.this.leftCollapsiblePanel);
            this.leftPanelToAlpha = 0.0f;
            this.rightPanelFromWidth = CollapsibleControlPanel.getViewWidth(CollapsibleControlPanel.this.rightCollapsiblePanel);
            this.rightPanelToWidth = 0;
            this.rightPanelFromAlpha = CollapsibleControlPanel.getViewAlpha(CollapsibleControlPanel.this.rightCollapsiblePanel);
            this.rightPanelToAlpha = 0.0f;
            setFloatValues(0.0f, 1.0f);
            setInterpolator(this.collapseAnimationInterpolator);
            setDuration(this.leftPanelFromAlpha * 250.0f);
        }

        public final void prepareForExpandAnimation$ar$ds() {
            cancel();
            removeAllListeners();
            addListener(this.defaultAnimatorListener);
            this.isExpanding = true;
            this.leftPanelFromWidth = CollapsibleControlPanel.getViewWidth(CollapsibleControlPanel.this.leftCollapsiblePanel);
            this.leftPanelToWidth = CollapsibleControlPanel.measureViewWidthWrapContent(CollapsibleControlPanel.this.leftCollapsiblePanel);
            this.leftPanelFromAlpha = CollapsibleControlPanel.getViewAlpha(CollapsibleControlPanel.this.leftCollapsiblePanel);
            this.leftPanelToAlpha = 1.0f;
            this.rightPanelFromWidth = CollapsibleControlPanel.getViewWidth(CollapsibleControlPanel.this.rightCollapsiblePanel);
            this.rightPanelToWidth = CollapsibleControlPanel.measureViewWidthWrapContent(CollapsibleControlPanel.this.rightCollapsiblePanel);
            this.rightPanelFromAlpha = CollapsibleControlPanel.getViewAlpha(CollapsibleControlPanel.this.rightCollapsiblePanel);
            this.rightPanelToAlpha = 1.0f;
            setFloatValues(0.0f, 1.0f);
            setInterpolator(this.expandAnimationInterpolator);
            setDuration((1.0f - this.leftPanelFromAlpha) * 250.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CollapseExpandButtonTouchDelegate extends TouchDelegate {
        private final View delegateButton;
        private boolean delegateTargeted;
        private final Rect extendedHitRect;
        private final int widthDiff;

        public CollapseExpandButtonTouchDelegate(Context context, View view) {
            super(new Rect(), view);
            this.delegateButton = view;
            this.extendedHitRect = new Rect();
            this.widthDiff = -context.getResources().getDimensionPixelSize(R.dimen.collapse_expand_button_touch_area_extension_width);
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        
            if (r0 != false) goto L17;
         */
        @Override // android.view.TouchDelegate
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean onTouchEvent(android.view.MotionEvent r7) {
            /*
                r6 = this;
                float r0 = r7.getX()
                int r0 = (int) r0
                float r1 = r7.getY()
                int r1 = (int) r1
                int r2 = r7.getAction()
                r3 = 1
                r4 = 0
                if (r2 == 0) goto L25
                if (r2 == r3) goto L20
                r0 = 2
                if (r2 == r0) goto L20
                r0 = 3
                if (r2 == r0) goto L1b
                goto L44
            L1b:
                boolean r0 = r6.delegateTargeted
                r6.delegateTargeted = r4
                goto L22
            L20:
                boolean r0 = r6.delegateTargeted
            L22:
                if (r0 == 0) goto L44
                goto L3d
            L25:
                android.view.View r2 = r6.delegateButton
                android.graphics.Rect r5 = r6.extendedHitRect
                r2.getHitRect(r5)
                android.graphics.Rect r2 = r6.extendedHitRect
                int r5 = r6.widthDiff
                r2.offset(r5, r4)
                android.graphics.Rect r2 = r6.extendedHitRect
                boolean r0 = r2.contains(r0, r1)
                if (r0 == 0) goto L44
                r6.delegateTargeted = r3
            L3d:
                android.view.View r0 = r6.delegateButton
                boolean r7 = r0.dispatchTouchEvent(r7)
                return r7
            L44:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel.CollapseExpandButtonTouchDelegate.onTouchEvent(android.view.MotionEvent):boolean");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CollapsibleControlPanelDragDetector extends DragActionDetector {
        private final CollapsibleControlPanel controlPanel;
        public final int controlPanelMaxWidth;
        public final int controlPanelMinWidth;
        public boolean isDraggingTowardsRight;
        public float lastX;
        public int startPanelWidth;
        public float startX;
        private final int subPanelMaxWidth;
        private final int subRightPanelMaxWidth;

        public CollapsibleControlPanelDragDetector(Context context, CollapsibleControlPanel collapsibleControlPanel) {
            super(context);
            this.controlPanel = collapsibleControlPanel;
            int i = CollapsibleControlPanel.CollapsibleControlPanel$ar$NoOp;
            if (collapsibleControlPanel.isShowingSettingButton) {
                this.controlPanelMaxWidth = context.getResources().getDimensionPixelSize(R.dimen.expanded_setting_control_panel_width);
            } else {
                this.controlPanelMaxWidth = context.getResources().getDimensionPixelSize(R.dimen.expanded_control_panel_width);
            }
            this.controlPanelMinWidth = context.getResources().getDimensionPixelSize(R.dimen.collapsed_control_panel_width);
            this.subPanelMaxWidth = CollapsibleControlPanel.measureViewWidthWrapContent(collapsibleControlPanel.leftCollapsiblePanel);
            this.subRightPanelMaxWidth = CollapsibleControlPanel.measureViewWidthWrapContent(collapsibleControlPanel.rightCollapsiblePanel);
        }

        private final void updateLayoutForDragPosition(float f) {
            float f2 = this.startX - f;
            if (isLayoutRtl()) {
                f2 = -f2;
            }
            float clampValue = SpannableUtils$IdentifierSpan.clampValue(SpannableUtils$IdentifierSpan.scaleValue(Math.round(this.startPanelWidth + f2), this.controlPanelMinWidth, this.controlPanelMaxWidth, 0.0f, 1.0f), 0.0f, 1.0f);
            this.controlPanel.updatePanelLayout(CollapsibleControlPanel.m92$$Nest$smscaleWithLimits$ar$ds(clampValue, this.subPanelMaxWidth), clampValue, CollapsibleControlPanel.m92$$Nest$smscaleWithLimits$ar$ds(clampValue, this.subRightPanelMaxWidth), clampValue);
        }

        public final boolean isLayoutRtl() {
            return SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.controlPanel.getContext());
        }

        @Override // com.google.android.accessibility.selecttospeak.ui.DragActionDetector
        public final void onDrag$ar$ds(float f, float f2) {
            boolean z;
            if (f > this.lastX) {
                z = true;
            } else {
                z = false;
            }
            this.isDraggingTowardsRight = z;
            this.lastX = f;
            updateLayoutForDragPosition(f);
        }

        @Override // com.google.android.accessibility.selecttospeak.ui.DragActionDetector
        public void onStartDragging(View view, float f, float f2) {
            this.startX = f;
            this.lastX = f;
            int i = CollapsibleControlPanel.CollapsibleControlPanel$ar$NoOp;
            this.controlPanel.transformInvisibleChildren();
            this.startPanelWidth = CollapsibleControlPanel.getViewWidth(this.controlPanel);
        }

        @Override // com.google.android.accessibility.selecttospeak.ui.DragActionDetector
        public void onStopDragging(View view, float f, float f2) {
            this.lastX = f;
            updateLayoutForDragPosition(f);
        }
    }

    /* renamed from: -$$Nest$smscaleWithLimits$ar$ds, reason: not valid java name */
    static /* bridge */ /* synthetic */ int m92$$Nest$smscaleWithLimits$ar$ds(float f, int i) {
        if (i > 0) {
            if (f <= 0.0f) {
                return 0;
            }
            if (f < 1.0f) {
                return SpannableUtils$IdentifierSpan.clampValue$ar$ds((int) SpannableUtils$IdentifierSpan.scaleValue(f, 0.0f, 1.0f, 0.0f, i), i);
            }
            return i;
        }
        return i;
    }

    static {
        int[] iArr = R$styleable.CollapsibleControlPanel;
        INIT_RESOURCE_MATRIX = new int[][]{new int[]{R.id.setting_button, 20, 19, 3}, new int[]{R.id.reduce_speed_button, 18, 17, 3}, new int[]{R.id.increase_speed_button, 7, 6, 3}, new int[]{R.id.previous_item_button, 16, 15, 3}, new int[]{R.id.next_item_button, 10, 9, 3}};
        INIT_RESOURCE_MATRIX_FOR_PLAY_PAUSE_BUTTON = new int[][]{new int[]{R.id.play_pause_button, 14, 13, 3}, new int[]{R.id.play_pause_button, 12, 11, 3}};
        INIT_RESOURCE_MATRIX_FOR_COLLAPSE_EXPAND_BUTTON = new int[][]{new int[]{R.id.collapse_expand_button, 5, 4, 2}, new int[]{R.id.collapse_expand_button, 1, 0, 2}};
    }

    public CollapsibleControlPanel(Context context) {
        super(context);
        this.isCollapsed = false;
        this.isShowingSettingButton = false;
        this.collapseExpandAnimator = new CollapseExpandAnimator();
        this.listeners = new ArrayList();
        this.controlButtonClickListener = new UIManager$$ExternalSyntheticLambda2(this, 8);
        initLayout(null, 0, 0);
    }

    public static float getViewAlpha(View view) {
        if (view.getVisibility() != 0) {
            return 0.0f;
        }
        return view.getAlpha();
    }

    private final int getViewIdByAction(int i) {
        int i2 = R.id.play_pause_button;
        int i3 = -1;
        switch (i) {
            case 1:
                return R.id.reduce_speed_button;
            case 2:
                return R.id.increase_speed_button;
            case 3:
                return R.id.previous_item_button;
            case 4:
                return R.id.next_item_button;
            case 5:
            default:
                return -1;
            case 6:
                i3 = R.id.play_pause_button;
                i2 = -1;
                break;
            case 7:
                break;
            case 8:
                return R.id.setting_button;
        }
        if (isShowingPlayButton()) {
            return i3;
        }
        return i2;
    }

    public static int getViewWidth(View view) {
        if (view.getVisibility() != 0) {
            return 0;
        }
        int i = view.getLayoutParams().width;
        if (i == -2) {
            return measureViewWidthWrapContent(view);
        }
        return i;
    }

    private final void initButtonAppearance(TypedArray typedArray, int i, int i2, int i3, int i4) {
        ImageButton imageButton = (ImageButton) findViewById(i);
        if (imageButton == null) {
            return;
        }
        imageButton.setImageDrawable(typedArray.getDrawable(i2));
        imageButton.setContentDescription(typedArray.getString(i3));
        imageButton.setImageTintList(ColorStateList.valueOf(typedArray.getColor(i4, -1)));
    }

    private final void initLayout(AttributeSet attributeSet, int i, int i2) {
        inflate(SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(getContext()), R.layout.layout_widget_collapsible_control_panel, this);
        int[][] iArr = INIT_RESOURCE_MATRIX;
        int length = iArr.length;
        for (int i3 = 0; i3 < 5; i3++) {
            View findViewById = findViewById(iArr[i3][0]);
            if (findViewById != null) {
                findViewById.setOnClickListener(this.controlButtonClickListener);
            }
        }
        View findViewById2 = findViewById(INIT_RESOURCE_MATRIX_FOR_PLAY_PAUSE_BUTTON[0][0]);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(this.controlButtonClickListener);
        }
        setCollapseExpandButtonClickListener(new UIManager$$ExternalSyntheticLambda2(this, 9, null));
        this.leftCollapsiblePanel = findViewById(R.id.collapsible_panel_left);
        this.rightCollapsiblePanel = findViewById(R.id.collapsible_panel_right);
        this.collapseExpandButton = (ImageButton) findViewById(R.id.collapse_expand_button);
        setTouchDelegate(new CollapseExpandButtonTouchDelegate(SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(getContext()), this.collapseExpandButton));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R$styleable.CollapsibleControlPanel, i, i2);
            setDisplayPlayButtonInitState();
            boolean z = obtainStyledAttributes.getBoolean(22, false);
            this.isShowingSettingButton = z;
            setStyle(z ? 1 : 0);
            try {
                int[][] iArr2 = INIT_RESOURCE_MATRIX;
                int length2 = iArr2.length;
                for (int i4 = 0; i4 < 5; i4++) {
                    int[] iArr3 = iArr2[i4];
                    initButtonAppearance(obtainStyledAttributes, iArr3[0], iArr3[1], iArr3[2], iArr3[3]);
                }
                int[] iArr4 = INIT_RESOURCE_MATRIX_FOR_PLAY_PAUSE_BUTTON[!isShowingPlayButton() ? 1 : 0];
                initButtonAppearance(obtainStyledAttributes, iArr4[0], iArr4[1], iArr4[2], iArr4[3]);
                this.playButtonDrawable = obtainStyledAttributes.getDrawable(14);
                this.pauseButtonDrawable = obtainStyledAttributes.getDrawable(12);
                int[] iArr5 = INIT_RESOURCE_MATRIX_FOR_COLLAPSE_EXPAND_BUTTON[!this.isCollapsed ? 1 : 0];
                initButtonAppearance(obtainStyledAttributes, iArr5[0], iArr5[1], iArr5[2], iArr5[3]);
                this.collapseButtonContentDescription = obtainStyledAttributes.getString(0);
                this.collapseButtonDrawable = obtainStyledAttributes.getDrawable(1);
                this.expandButtonContentDescription = obtainStyledAttributes.getString(4);
                this.expandButtonDrawable = obtainStyledAttributes.getDrawable(5);
                this.isCollapsed = obtainStyledAttributes.getBoolean(8, false);
                ViewCompat.setAccessibilityDelegate((ImageButton) findViewById(R.id.play_pause_button), new AccessibilityDelegateCompat() { // from class: com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel.1
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                        CollapsibleControlPanel collapsibleControlPanel = CollapsibleControlPanel.this;
                        int i5 = collapsibleControlPanel.playButtonState;
                        if (i5 != 1) {
                            if (i5 != 2) {
                                accessibilityNodeInfoCompat.setContentDescription(collapsibleControlPanel.getContext().getString(R.string.start_continuous_reading_button));
                                return;
                            } else {
                                accessibilityNodeInfoCompat.setContentDescription(collapsibleControlPanel.getContext().getString(R.string.pause_button));
                                return;
                            }
                        }
                        accessibilityNodeInfoCompat.setContentDescription(collapsibleControlPanel.getContext().getString(R.string.resume_button));
                    }
                });
                obtainStyledAttributes.recycle();
                if (this.isCollapsed) {
                    collapse$ar$ds$57f5e804_0(false);
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
    }

    public static int measureViewWidthWrapContent(View view) {
        int i = UNSPECIFIED_MEASURE_SPEC;
        view.measure(i, i);
        return view.getMeasuredWidth();
    }

    private static void updateLayoutForView(View view, int i, float f) {
        view.getLayoutParams().width = i;
        view.setAlpha(f);
        view.requestLayout();
    }

    public final void addControlActionListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        if (resolutionResultListener != null && !this.listeners.contains(resolutionResultListener)) {
            this.listeners.add(resolutionResultListener);
        }
    }

    public final void collapse$ar$ds$57f5e804_0(boolean z) {
        this.collapseExpandAnimator.cancel();
        if (z) {
            this.collapseExpandAnimator.prepareForCollapseAnimation$ar$ds();
            this.collapseExpandAnimator.start();
            return;
        }
        this.collapseExpandButton.setContentDescription(this.expandButtonContentDescription);
        this.collapseExpandButton.setImageDrawable(this.expandButtonDrawable);
        this.leftCollapsiblePanel.setVisibility(8);
        this.rightCollapsiblePanel.setVisibility(8);
        this.isCollapsed = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View.OnTouchListener onTouchListener = this.onTouchListener;
        if (onTouchListener != null && onTouchListener.onTouch(this, motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void displayPauseButton() {
        this.playButtonState = 2;
        ((ImageButton) findViewById(R.id.play_pause_button)).setImageDrawable(this.pauseButtonDrawable);
    }

    public final void displayPlayButton() {
        this.playButtonState = 1;
        ((ImageButton) findViewById(R.id.play_pause_button)).setImageDrawable(this.playButtonDrawable);
    }

    public final boolean isShowingPlayButton() {
        if (this.playButtonState != 2) {
            return true;
        }
        return false;
    }

    public final void removeAction$ar$ds$9d6da02a_0() {
        int viewIdByAction = getViewIdByAction(8);
        if (viewIdByAction != -1) {
            findViewById(viewIdByAction).setVisibility(8);
        }
    }

    public final void setCollapseExpandButtonClickListener(View.OnClickListener onClickListener) {
        View findViewById = findViewById(R.id.collapse_expand_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(onClickListener);
        }
    }

    public final void setCollapseExpandButtonEnabled(boolean z) {
        View findViewById = findViewById(R.id.collapse_expand_button);
        if (findViewById != null) {
            findViewById.setEnabled(z);
        }
    }

    public final void setDisplayPlayButtonInitState() {
        this.playButtonState = 0;
    }

    public final void setEnabled(int i, boolean z) {
        int viewIdByAction = getViewIdByAction(i);
        if (viewIdByAction != -1) {
            View findViewById = findViewById(viewIdByAction);
            findViewById.setEnabled(z);
            if (z) {
                findViewById.setAlpha(1.0f);
            } else {
                findViewById.setAlpha(0.3f);
            }
        }
    }

    @Override // android.view.View
    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public final void setStyle(int i) {
        if (i == 1) {
            this.isShowingSettingButton = true;
            ImageButton imageButton = (ImageButton) findViewById(R.id.reduce_speed_button);
            ImageButton imageButton2 = (ImageButton) findViewById(R.id.increase_speed_button);
            ImageButton imageButton3 = (ImageButton) findViewById(R.id.setting_button);
            imageButton.setVisibility(8);
            imageButton2.setVisibility(8);
            imageButton3.setVisibility(0);
            return;
        }
        this.isShowingSettingButton = false;
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.reduce_speed_button);
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.increase_speed_button);
        ImageButton imageButton6 = (ImageButton) findViewById(R.id.setting_button);
        imageButton4.setVisibility(0);
        imageButton5.setVisibility(0);
        imageButton6.setVisibility(8);
    }

    public final void transformInvisibleChildren() {
        if (this.leftCollapsiblePanel.getVisibility() == 8) {
            updateLayoutForView(this.leftCollapsiblePanel, 0, 0.0f);
            this.leftCollapsiblePanel.setVisibility(0);
        }
        if (this.rightCollapsiblePanel.getVisibility() == 8) {
            updateLayoutForView(this.rightCollapsiblePanel, 0, 0.0f);
            this.rightCollapsiblePanel.setVisibility(0);
        }
    }

    public final void updatePanelLayout(int i, float f, int i2, float f2) {
        updateLayoutForView(this.leftCollapsiblePanel, i, f);
        updateLayoutForView(this.rightCollapsiblePanel, i2, f2);
    }

    public CollapsibleControlPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCollapsed = false;
        this.isShowingSettingButton = false;
        this.collapseExpandAnimator = new CollapseExpandAnimator();
        this.listeners = new ArrayList();
        this.controlButtonClickListener = new UIManager$$ExternalSyntheticLambda2(this, 8);
        initLayout(attributeSet, 0, 0);
    }

    public CollapsibleControlPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isCollapsed = false;
        this.isShowingSettingButton = false;
        this.collapseExpandAnimator = new CollapseExpandAnimator();
        this.listeners = new ArrayList();
        this.controlButtonClickListener = new UIManager$$ExternalSyntheticLambda2(this, 8);
        initLayout(attributeSet, i, 0);
    }

    public CollapsibleControlPanel(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isCollapsed = false;
        this.isShowingSettingButton = false;
        this.collapseExpandAnimator = new CollapseExpandAnimator();
        this.listeners = new ArrayList();
        this.controlButtonClickListener = new UIManager$$ExternalSyntheticLambda2(this, 8);
        initLayout(attributeSet, i, i2);
    }
}
