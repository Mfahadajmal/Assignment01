package com.google.android.accessibility.brailleime.input;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.Size;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableMap;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import j$.util.Collection;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BrailleInputPlane {
    public static final /* synthetic */ int BrailleInputPlane$ar$NoOp = 0;
    private static final ImmutableMap dotNumberOrderMap;
    public int calibrationFailCount;
    protected final Context context;
    public Set currentlyPressedDots;
    public final FloatingActionButton.ShadowDelegateImpl customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final int dotBackgroundColorDefault;
    public final int dotBackgroundColorDefaultCalibration;
    public final int dotBackgroundColorPressedCalibration;
    public final int dotBackgroundColorPressedDefault;
    public final Paint dotBackgroundPaint;
    public PointF[] dotCenterPosition;
    public final int dotNumberColorDefault;
    public final int dotNumberColorDefaultCalibration;
    public final int dotNumberColorPressedCalibration;
    public final int dotNumberColorPressedDefault;
    public final Paint dotNumberPaint;
    public final int dotRadius;
    public List dotTargets;
    protected boolean isTableTopMode;
    public final MultitouchHandler multitouchHandler;
    private final HapticPatternParser$$ExternalSyntheticLambda1 multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private int numberOfColumnsTabletop;
    private int numberOfRowsScreenAway;
    public List oldDotTargets;
    public BrailleInputOptions options;
    protected int orientation;
    public final Resources resources;
    public Size sizeInPixels;
    public final int textBaseline;
    public PointF[] textPosition;
    public final Paint touchCirclesPaint;
    public int twoStepCalibrationState$ar$edu = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum InputDotType {
        SCREEN_AWAY,
        SCREEN_AWAY_EIGHT_DOT,
        TABLE_TOP,
        TABLE_TOP_EIGHT_DOT
    }

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0(InputDotType.SCREEN_AWAY, new int[]{1, 2, 3, 4, 5, 6});
        builder.put$ar$ds$de9b9d28_0(InputDotType.SCREEN_AWAY_EIGHT_DOT, new int[]{1, 2, 3, 7, 4, 5, 6, 8});
        builder.put$ar$ds$de9b9d28_0(InputDotType.TABLE_TOP, new int[]{3, 2, 1, 4, 5, 6});
        builder.put$ar$ds$de9b9d28_0(InputDotType.TABLE_TOP_EIGHT_DOT, new int[]{7, 3, 2, 1, 4, 5, 6, 8});
        dotNumberOrderMap = builder.buildOrThrow();
    }

    public BrailleInputPlane(Context context, Size size, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, int i, BrailleInputOptions brailleInputOptions, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda12 = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda12;
        Resources resources = context.getResources();
        this.resources = resources;
        this.multitouchHandler = new MultitouchHandler(resources, hapticPatternParser$$ExternalSyntheticLambda1, hapticPatternParser$$ExternalSyntheticLambda12);
        this.orientation = i;
        this.sizeInPixels = size;
        this.options = brailleInputOptions;
        this.context = context;
        this.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        int i2 = brailleInputOptions.brailleType$ar$edu;
        this.numberOfColumnsTabletop = i2;
        this.numberOfRowsScreenAway = i2 >> 1;
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.input_plane_dot_radius);
        this.dotRadius = dimensionPixelSize;
        Paint paint = new Paint();
        this.dotNumberPaint = paint;
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(context.getString(R.string.accessibility_font), 0));
        paint.setTextSize(_BOUNDARY.getResourcesFloat(resources, R.dimen.input_plane_dot_number_size_multiplier) * dimensionPixelSize);
        paint.setStrokeWidth(resources.getDimension(R.dimen.input_plane_dot_number_stroke_width));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.textBaseline = _BOUNDARY.getPaintTextBaselineInPixels(paint);
        this.dotTargets = buildDotTargets(size);
        this.currentlyPressedDots = new HashSet();
        Paint paint2 = new Paint();
        this.dotBackgroundPaint = paint2;
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        int color = context.getColor(R.color.input_plane_background);
        int color2 = resources.getColor(R.color.input_plane_dot_background_default);
        this.dotBackgroundColorDefault = color2;
        int color3 = resources.getColor(R.color.input_plane_dot_background_pressed);
        this.dotBackgroundColorPressedDefault = color3;
        this.dotBackgroundColorDefaultCalibration = _BOUNDARY.averageColors(color2, color);
        this.dotBackgroundColorPressedCalibration = _BOUNDARY.averageColors(color3, color);
        int color4 = resources.getColor(R.color.input_plane_dot_number_default);
        this.dotNumberColorDefault = color4;
        int color5 = resources.getColor(R.color.input_plane_dot_number_pressed);
        this.dotNumberColorPressedDefault = color5;
        this.dotNumberColorPressedCalibration = _BOUNDARY.averageColors(color5, color);
        this.dotNumberColorDefaultCalibration = _BOUNDARY.averageColors(color4, color);
        Paint paint3 = new Paint();
        this.touchCirclesPaint = paint3;
        paint3.setColor(resources.getColor(R.color.input_plane_touch_circle));
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(resources.getDimension(R.dimen.input_plane_touch_circle_stroke_width));
    }

    public abstract List buildDotTargetCenters(Size size);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List buildDotTargetCentersLandscape(Size size) {
        int i;
        int i2;
        BrailleInputPlane brailleInputPlane = this;
        ArrayList arrayList = new ArrayList();
        int width = size.getWidth();
        int height = size.getHeight();
        boolean z = brailleInputPlane.isTableTopMode;
        if (z) {
            i = brailleInputPlane.numberOfColumnsTabletop;
        } else {
            i = 2;
        }
        if (z) {
            i2 = 1;
        } else {
            i2 = brailleInputPlane.numberOfRowsScreenAway;
        }
        int i3 = brailleInputPlane.dotRadius;
        float f = height;
        int i4 = i2 + 1;
        float f2 = width;
        int i5 = i3 + i3;
        int i6 = i * i5;
        int i7 = i + 1;
        int i8 = 0;
        while (i8 < i) {
            float f3 = i7;
            float f4 = f2 - i6;
            int i9 = brailleInputPlane.dotRadius;
            float f5 = i5;
            int i10 = 0;
            while (i10 < i2) {
                float f6 = f4 / f3;
                int i11 = i7;
                float f7 = (f - (i2 * i5)) / i4;
                arrayList.add(new PointF(f2 - ((f6 + i9) + (i8 * (f5 + f6))), f7 + brailleInputPlane.dotRadius + (i10 * (f5 + f7))));
                i10++;
                brailleInputPlane = this;
                f2 = f2;
                i = i;
                i6 = i6;
                i7 = i11;
                i2 = i2;
            }
            i8++;
            brailleInputPlane = this;
        }
        return arrayList;
    }

    public final List buildDotTargets(Size size) {
        List arrayList = new ArrayList();
        if (!this.options.tutorialMode) {
            arrayList = readLayoutPoints(size);
        }
        if (arrayList.isEmpty()) {
            arrayList = buildDotTargetCenters(size);
        }
        sortDotCenters(arrayList);
        ArrayList arrayList2 = new ArrayList();
        int[] dotNumberOrder = getDotNumberOrder();
        for (int i = 0; i < arrayList.size(); i++) {
            PointF pointF = (PointF) arrayList.get(i);
            arrayList2.add(new OrderVerifyingClientCall.State(dotNumberOrder[i], pointF.x, pointF.y));
        }
        int i2 = this.options.brailleType$ar$edu;
        this.dotCenterPosition = new PointF[i2];
        this.textPosition = new PointF[i2];
        for (int i3 = 0; i3 < this.options.brailleType$ar$edu; i3++) {
            this.dotCenterPosition[i3] = new PointF(((PointF) ((OrderVerifyingClientCall.State) arrayList2.get(i3)).OrderVerifyingClientCall$State$ar$cancellationStatus).x, ((PointF) ((OrderVerifyingClientCall.State) arrayList2.get(i3)).OrderVerifyingClientCall$State$ar$cancellationStatus).y);
            this.textPosition[i3] = new PointF(((PointF) ((OrderVerifyingClientCall.State) arrayList2.get(i3)).OrderVerifyingClientCall$State$ar$cancellationStatus).x, ((PointF) ((OrderVerifyingClientCall.State) arrayList2.get(i3)).OrderVerifyingClientCall$State$ar$cancellationStatus).y + this.textBaseline);
        }
        return arrayList2;
    }

    public abstract BrailleInputPlaneResult createDotHoldAndSwipe(Swipe swipe, BrailleCharacter brailleCharacter);

    public abstract BrailleInputPlaneResult createSwipe(Swipe swipe);

    public abstract PointF getCaptionCenterPoint(Size size);

    public final int[] getDotNumberOrder() {
        InputDotType inputDotType;
        ImmutableMap immutableMap = dotNumberOrderMap;
        boolean z = this.isTableTopMode;
        if (this.options.brailleType$ar$edu == 8) {
            if (z) {
                inputDotType = InputDotType.TABLE_TOP_EIGHT_DOT;
            } else {
                inputDotType = InputDotType.SCREEN_AWAY_EIGHT_DOT;
            }
        } else if (this.options.brailleType$ar$edu == 6) {
            if (z) {
                inputDotType = InputDotType.TABLE_TOP;
            } else {
                inputDotType = InputDotType.SCREEN_AWAY;
            }
        } else {
            throw new IllegalArgumentException("dotCount should be either 6 or 8.");
        }
        int[] iArr = (int[]) immutableMap.get(inputDotType);
        if (this.options.reverseDots) {
            int[] iArr2 = new int[iArr.length];
            int i = 0;
            if (!this.isTableTopMode) {
                while (true) {
                    int length = iArr.length >> 1;
                    if (i >= length) {
                        break;
                    }
                    int i2 = length + i;
                    iArr2[i] = iArr[i2];
                    iArr2[i2] = iArr[i];
                    i++;
                }
            } else {
                while (true) {
                    int length2 = iArr.length;
                    if (i >= (length2 >> 1)) {
                        break;
                    }
                    int i3 = (length2 - 1) - i;
                    iArr2[i] = iArr[i3];
                    iArr2[i3] = iArr[i];
                    i++;
                }
            }
            return iArr2;
        }
        return iArr;
    }

    public abstract Size getInputViewCaptionScreenSize(Size size);

    public abstract int[] getInputViewCaptionTranslate(Size size);

    public abstract int getRotateDegree();

    public final boolean inTwoStepCalibration() {
        if (this.twoStepCalibrationState$ar$edu != 1) {
            return true;
        }
        return false;
    }

    public final Set matchTouchToTargetNumbers(Collection collection) {
        return (Set) Collection.EL.stream(matchTouchToTargets(collection)).map(new BtConnectManager$$ExternalSyntheticLambda1(11)).collect(Collectors.toSet());
    }

    public final Set matchTouchToTargets(java.util.Collection collection) {
        HashSet hashSet = new HashSet();
        HashSet<PointF> hashSet2 = new HashSet(collection);
        HashSet<OrderVerifyingClientCall.State> hashSet3 = new HashSet(this.dotTargets);
        while (!hashSet2.isEmpty() && !hashSet3.isEmpty()) {
            OrderVerifyingClientCall.State state = null;
            double d = Double.MAX_VALUE;
            PointF pointF = null;
            for (OrderVerifyingClientCall.State state2 : hashSet3) {
                for (PointF pointF2 : hashSet2) {
                    double distance = _BOUNDARY.distance((PointF) state2.OrderVerifyingClientCall$State$ar$cancellationStatus, pointF2);
                    if (distance < d) {
                        pointF = pointF2;
                    }
                    if (distance < d) {
                        state = state2;
                    }
                    if (distance < d) {
                        d = distance;
                    }
                }
            }
            hashSet.add(new OrderVerifyingClientCall.State(state.type$ar$edu$88c656f2_0, pointF.x, pointF.y));
            hashSet2.remove(pointF);
            hashSet3.remove(state);
        }
        return hashSet;
    }

    public abstract List readLayoutPoints(Size size);

    public final void refresh() {
        int i = this.options.brailleType$ar$edu;
        this.numberOfColumnsTabletop = i;
        this.numberOfRowsScreenAway = i >> 1;
        this.dotTargets = buildDotTargets(this.sizeInPixels);
    }

    public final void sortDotCenters(List list) {
        sortDotCentersFirstTime(list);
        ArrayList arrayList = new ArrayList(list);
        list.clear();
        int i = 0;
        while (i < 2) {
            int i2 = this.options.brailleType$ar$edu;
            boolean z = true;
            int i3 = i + 1;
            ArrayList arrayList2 = new ArrayList(arrayList.subList((i * i2) / 2, (i2 >> 1) * i3));
            if (i != 0) {
                z = false;
            }
            sortDotCentersByGroup(arrayList2, z);
            list.addAll(arrayList2);
            i = i3;
        }
    }

    public abstract void sortDotCentersByGroup(List list, boolean z);

    public abstract void sortDotCentersFirstTime(List list);

    public abstract void writeLayoutPoints(List list, Size size);
}
