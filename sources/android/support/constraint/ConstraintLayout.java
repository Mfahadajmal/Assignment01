package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.search.mdi.aratea.proto.FeatureName;
import java.util.ArrayList;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    final SparseArray mChildrenByIds;
    private ConstraintSet mConstraintSet;
    private boolean mDirtyHierarchy;
    final ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    private int mMinHeight;
    private int mMinWidth;
    private int mOptimizationLevel;
    private final ArrayList mVariableDimensionsWidgets;

    public ConstraintLayout(Context context) {
        super(context);
        this.mChildrenByIds = new SparseArray();
        this.mVariableDimensionsWidgets = new ArrayList(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Preference.DEFAULT_ORDER;
        this.mMaxHeight = Preference.DEFAULT_ORDER;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 2;
        this.mConstraintSet = null;
        init(null);
    }

    private final ConstraintWidget getTargetWidget(int i) {
        View view;
        if (i != 0 && (view = (View) this.mChildrenByIds.get(i)) != this) {
            if (view == null) {
                return null;
            }
            return ((LayoutParams) view.getLayoutParams()).widget;
        }
        return this.mLayoutWidget;
    }

    private final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    private final void init(AttributeSet attributeSet) {
        this.mLayoutWidget.mCompanionWidget = this;
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 16) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(16, this.mMinWidth);
                } else if (index == 17) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(17, this.mMinHeight);
                } else if (index == 14) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(14, this.mMaxWidth);
                } else if (index == 15) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(15, this.mMaxHeight);
                } else if (index == 113) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(BrailleInputEvent.CMD_CONTROL_PREVIOUS, this.mOptimizationLevel);
                } else if (index == 34) {
                    int resourceId = obtainStyledAttributes.getResourceId(34, 0);
                    ConstraintSet constraintSet = new ConstraintSet();
                    this.mConstraintSet = constraintSet;
                    constraintSet.load(getContext(), resourceId);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.mOptimizationLevel = this.mOptimizationLevel;
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8 || layoutParams.isGuideline || isInEditMode) {
                ConstraintWidget constraintWidget = layoutParams.widget;
                int drawX = constraintWidget.getDrawX();
                int drawY = constraintWidget.getDrawY();
                childAt.layout(drawX, drawY, constraintWidget.getWidth() + drawX, constraintWidget.getHeight() + drawY);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:245:0x050e, code lost:
    
        if (r10.height == (-1)) goto L244;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0568 A[EDGE_INSN: B:273:0x0568->B:274:0x0568 BREAK  A[LOOP:4: B:226:0x04d0->B:258:0x0562], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:276:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0583  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x06c0  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0689  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0054  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onMeasure(int r33, int r34) {
        /*
            Method dump skipped, instructions count: 1733
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        boolean z = view instanceof Guideline;
        ConstraintWidget viewWidget = getViewWidget(view);
        if (z && !(viewWidget instanceof android.support.constraint.solver.widgets.Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new android.support.constraint.solver.widgets.Guideline();
            layoutParams.isGuideline = true;
            ((android.support.constraint.solver.widgets.Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
            ConstraintWidget constraintWidget = layoutParams.widget;
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mChildrenByIds.remove(view.getId());
        this.mLayoutWidget.remove(getViewWidget(view));
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.View
    public final void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    protected final void solveLinearSystem() {
        this.mLayoutWidget.layout();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int baselineToBaseline;
        public int bottomToBottom;
        public int bottomToTop;
        public String dimensionRatio;
        int dimensionRatioSide;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;

        public LayoutParams() {
            super(-2, -2);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = 0.0f;
            this.verticalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
        }

        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        public final void resolveLayoutDirection(int i) {
            super.resolveLayoutDirection(i);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            if (getLayoutDirection() == 1) {
                int i2 = this.startToEnd;
                if (i2 != -1) {
                    this.resolvedRightToLeft = i2;
                } else {
                    int i3 = this.startToStart;
                    if (i3 != -1) {
                        this.resolvedRightToRight = i3;
                    }
                }
                int i4 = this.endToStart;
                if (i4 != -1) {
                    this.resolvedLeftToRight = i4;
                }
                int i5 = this.endToEnd;
                if (i5 != -1) {
                    this.resolvedLeftToLeft = i5;
                }
                int i6 = this.goneStartMargin;
                if (i6 != -1) {
                    this.resolveGoneRightMargin = i6;
                }
                int i7 = this.goneEndMargin;
                if (i7 != -1) {
                    this.resolveGoneLeftMargin = i7;
                }
                this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
            } else {
                int i8 = this.startToEnd;
                if (i8 != -1) {
                    this.resolvedLeftToRight = i8;
                }
                int i9 = this.startToStart;
                if (i9 != -1) {
                    this.resolvedLeftToLeft = i9;
                }
                int i10 = this.endToStart;
                if (i10 != -1) {
                    this.resolvedRightToLeft = i10;
                }
                int i11 = this.endToEnd;
                if (i11 != -1) {
                    this.resolvedRightToRight = i11;
                }
                int i12 = this.goneStartMargin;
                if (i12 != -1) {
                    this.resolveGoneLeftMargin = i12;
                }
                int i13 = this.goneEndMargin;
                if (i13 != -1) {
                    this.resolveGoneRightMargin = i13;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1) {
                int i14 = this.rightToLeft;
                if (i14 != -1) {
                    this.resolvedRightToLeft = i14;
                } else {
                    int i15 = this.rightToRight;
                    if (i15 != -1) {
                        this.resolvedRightToRight = i15;
                    }
                }
            }
            if (this.startToStart == -1 && this.startToEnd == -1) {
                int i16 = this.leftToLeft;
                if (i16 != -1) {
                    this.resolvedLeftToLeft = i16;
                    return;
                }
                int i17 = this.leftToRight;
                if (i17 != -1) {
                    this.resolvedLeftToRight = i17;
                }
            }
        }

        public final void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
            }
            if (this.guidePercent == -1.0f && this.guideBegin == -1 && this.guideEnd == -1) {
                return;
            }
            this.isGuideline = true;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (!(this.widget instanceof android.support.constraint.solver.widgets.Guideline)) {
                this.widget = new android.support.constraint.solver.widgets.Guideline();
            }
            ((android.support.constraint.solver.widgets.Guideline) this.widget).setOrientation(this.orientation);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            int i;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = 0.0f;
            this.verticalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == 84) {
                    int resourceId = obtainStyledAttributes.getResourceId(84, this.leftToLeft);
                    this.leftToLeft = resourceId;
                    if (resourceId == -1) {
                        this.leftToLeft = obtainStyledAttributes.getInt(84, -1);
                    }
                } else if (index == 85) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(85, this.leftToRight);
                    this.leftToRight = resourceId2;
                    if (resourceId2 == -1) {
                        this.leftToRight = obtainStyledAttributes.getInt(85, -1);
                    }
                } else if (index == 87) {
                    int resourceId3 = obtainStyledAttributes.getResourceId(87, this.rightToLeft);
                    this.rightToLeft = resourceId3;
                    if (resourceId3 == -1) {
                        this.rightToLeft = obtainStyledAttributes.getInt(87, -1);
                    }
                } else if (index == 88) {
                    int resourceId4 = obtainStyledAttributes.getResourceId(88, this.rightToRight);
                    this.rightToRight = resourceId4;
                    if (resourceId4 == -1) {
                        this.rightToRight = obtainStyledAttributes.getInt(88, -1);
                    }
                } else if (index == 94) {
                    int resourceId5 = obtainStyledAttributes.getResourceId(94, this.topToTop);
                    this.topToTop = resourceId5;
                    if (resourceId5 == -1) {
                        this.topToTop = obtainStyledAttributes.getInt(94, -1);
                    }
                } else if (index == 93) {
                    int resourceId6 = obtainStyledAttributes.getResourceId(93, this.topToBottom);
                    this.topToBottom = resourceId6;
                    if (resourceId6 == -1) {
                        this.topToBottom = obtainStyledAttributes.getInt(93, -1);
                    }
                } else if (index == 65) {
                    int resourceId7 = obtainStyledAttributes.getResourceId(65, this.bottomToTop);
                    this.bottomToTop = resourceId7;
                    if (resourceId7 == -1) {
                        this.bottomToTop = obtainStyledAttributes.getInt(65, -1);
                    }
                } else if (index == 64) {
                    int resourceId8 = obtainStyledAttributes.getResourceId(64, this.bottomToBottom);
                    this.bottomToBottom = resourceId8;
                    if (resourceId8 == -1) {
                        this.bottomToBottom = obtainStyledAttributes.getInt(64, -1);
                    }
                } else if (index == 60) {
                    int resourceId9 = obtainStyledAttributes.getResourceId(60, this.baselineToBaseline);
                    this.baselineToBaseline = resourceId9;
                    if (resourceId9 == -1) {
                        this.baselineToBaseline = obtainStyledAttributes.getInt(60, -1);
                    }
                } else if (index == 103) {
                    this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu, this.editorAbsoluteX);
                } else if (index == 104) {
                    this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_KEY_EVENT$ar$edu, this.editorAbsoluteY);
                } else if (index == 72) {
                    this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(72, this.guideBegin);
                } else if (index == 73) {
                    this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(73, this.guideEnd);
                } else if (index == 74) {
                    this.guidePercent = obtainStyledAttributes.getFloat(74, this.guidePercent);
                } else if (index == 0) {
                    this.orientation = obtainStyledAttributes.getInt(0, this.orientation);
                } else if (index == 89) {
                    int resourceId10 = obtainStyledAttributes.getResourceId(89, this.startToEnd);
                    this.startToEnd = resourceId10;
                    if (resourceId10 == -1) {
                        this.startToEnd = obtainStyledAttributes.getInt(89, -1);
                    }
                } else if (index == 90) {
                    int resourceId11 = obtainStyledAttributes.getResourceId(90, this.startToStart);
                    this.startToStart = resourceId11;
                    if (resourceId11 == -1) {
                        this.startToStart = obtainStyledAttributes.getInt(90, -1);
                    }
                } else if (index == 71) {
                    int resourceId12 = obtainStyledAttributes.getResourceId(71, this.endToStart);
                    this.endToStart = resourceId12;
                    if (resourceId12 == -1) {
                        this.endToStart = obtainStyledAttributes.getInt(71, -1);
                    }
                } else if (index == 70) {
                    int resourceId13 = obtainStyledAttributes.getResourceId(70, this.endToEnd);
                    this.endToEnd = resourceId13;
                    if (resourceId13 == -1) {
                        this.endToEnd = obtainStyledAttributes.getInt(70, -1);
                    }
                } else if (index == 108) {
                    this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(108, this.goneLeftMargin);
                } else if (index == 111) {
                    this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(BrailleInputEvent.CMD_HEADING_PREVIOUS, this.goneTopMargin);
                } else if (index == 109) {
                    this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(109, this.goneRightMargin);
                } else if (index == 106) {
                    this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_MOTION_EVENT_SOURCE$ar$edu, this.goneBottomMargin);
                } else if (index == 110) {
                    this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(BrailleInputEvent.CMD_HEADING_NEXT, this.goneStartMargin);
                } else if (index == 107) {
                    this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(107, this.goneEndMargin);
                } else if (index == 80) {
                    this.horizontalBias = obtainStyledAttributes.getFloat(80, this.horizontalBias);
                } else if (index == 95) {
                    this.verticalBias = obtainStyledAttributes.getFloat(95, this.verticalBias);
                } else if (index == 69) {
                    String string = obtainStyledAttributes.getString(69);
                    this.dimensionRatio = string;
                    this.dimensionRatioSide = -1;
                    if (string != null) {
                        int length = string.length();
                        int indexOf = this.dimensionRatio.indexOf(44);
                        if (indexOf <= 0 || indexOf >= length - 1) {
                            i = 0;
                        } else {
                            String substring = this.dimensionRatio.substring(0, indexOf);
                            if (substring.equalsIgnoreCase("W")) {
                                this.dimensionRatioSide = 0;
                            } else if (substring.equalsIgnoreCase("H")) {
                                this.dimensionRatioSide = 1;
                            }
                            i = indexOf + 1;
                        }
                        int indexOf2 = this.dimensionRatio.indexOf(58);
                        if (indexOf2 >= 0 && indexOf2 < length - 1) {
                            String substring2 = this.dimensionRatio.substring(i, indexOf2);
                            String substring3 = this.dimensionRatio.substring(indexOf2 + 1);
                            if (substring2.length() > 0 && substring3.length() > 0) {
                                try {
                                    float parseFloat = Float.parseFloat(substring2);
                                    float parseFloat2 = Float.parseFloat(substring3);
                                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                                        if (this.dimensionRatioSide == 1) {
                                            Math.abs(parseFloat2 / parseFloat);
                                        } else {
                                            Math.abs(parseFloat / parseFloat2);
                                        }
                                    }
                                } catch (NumberFormatException unused) {
                                }
                            }
                        } else {
                            String substring4 = this.dimensionRatio.substring(i);
                            if (substring4.length() > 0) {
                                Float.parseFloat(substring4);
                            }
                        }
                    }
                } else if (index == 82) {
                    this.horizontalWeight = obtainStyledAttributes.getFloat(82, 0.0f);
                } else if (index == 97) {
                    this.verticalWeight = obtainStyledAttributes.getFloat(97, 0.0f);
                } else if (index == 81) {
                    this.horizontalChainStyle = obtainStyledAttributes.getInt(81, 0);
                } else if (index == 96) {
                    this.verticalChainStyle = obtainStyledAttributes.getInt(96, 0);
                } else if (index == 99) {
                    this.matchConstraintDefaultWidth = obtainStyledAttributes.getInt(99, 0);
                } else if (index == 76) {
                    this.matchConstraintDefaultHeight = obtainStyledAttributes.getInt(76, 0);
                } else if (index == 101) {
                    this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu, this.matchConstraintMinWidth);
                } else if (index == 100) {
                    this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(100, this.matchConstraintMaxWidth);
                } else if (index == 78) {
                    this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(78, this.matchConstraintMinHeight);
                } else if (index == 77) {
                    this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(77, this.matchConstraintMaxHeight);
                }
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = 0.0f;
            this.verticalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
        }
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildrenByIds = new SparseArray();
        this.mVariableDimensionsWidgets = new ArrayList(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Preference.DEFAULT_ORDER;
        this.mMaxHeight = Preference.DEFAULT_ORDER;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 2;
        this.mConstraintSet = null;
        init(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChildrenByIds = new SparseArray();
        this.mVariableDimensionsWidgets = new ArrayList(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Preference.DEFAULT_ORDER;
        this.mMaxHeight = Preference.DEFAULT_ORDER;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 2;
        this.mConstraintSet = null;
        init(attributeSet);
    }
}
