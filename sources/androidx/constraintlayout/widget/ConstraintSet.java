package androidx.constraintlayout.widget;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.search.mdi.aratea.proto.FeatureName;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$LanguageCode;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintSet {
    public static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private static final SparseIntArray sMapToConstant;
    private static final SparseIntArray sOverrideMapToConstant;
    private final HashMap mSavedAttributes = new HashMap();
    public final boolean mForceId = true;
    public final HashMap mConstraints = new HashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Layout {
        public static final SparseIntArray sMapToConstant;
        public String mConstraintTag;
        public int mHeight;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public boolean mIsGuideline = false;
        public boolean mApply = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean guidelineUseRtl = true;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int baselineToTop = -1;
        public int baselineToBottom = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public float circleAngle = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = 0;
        public int rightMargin = 0;
        public int topMargin = 0;
        public int bottomMargin = 0;
        public int endMargin = 0;
        public int startMargin = 0;
        public int baselineMargin = 0;
        public int goneLeftMargin = Integer.MIN_VALUE;
        public int goneTopMargin = Integer.MIN_VALUE;
        public int goneRightMargin = Integer.MIN_VALUE;
        public int goneBottomMargin = Integer.MIN_VALUE;
        public int goneEndMargin = Integer.MIN_VALUE;
        public int goneStartMargin = Integer.MIN_VALUE;
        public int goneBaselineMargin = Integer.MIN_VALUE;
        public float verticalWeight = -1.0f;
        public float horizontalWeight = -1.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public int widthDefault = 0;
        public int heightDefault = 0;
        public int widthMax = 0;
        public int heightMax = 0;
        public int widthMin = 0;
        public int heightMin = 0;
        public float widthPercent = 1.0f;
        public float heightPercent = 1.0f;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public int mHelperType = -1;
        public boolean constrainedWidth = false;
        public boolean constrainedHeight = false;
        public boolean mBarrierAllowsGoneWidgets = true;
        public int mWrapBehavior = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sMapToConstant = sparseIntArray;
            int[] iArr = R$styleable.Constraint;
            sparseIntArray.append(43, 24);
            sparseIntArray.append(44, 25);
            sparseIntArray.append(46, 28);
            sparseIntArray.append(47, 29);
            sparseIntArray.append(52, 35);
            sparseIntArray.append(51, 34);
            sparseIntArray.append(24, 4);
            sparseIntArray.append(23, 3);
            sparseIntArray.append(19, 1);
            sparseIntArray.append(61, 6);
            sparseIntArray.append(62, 7);
            sparseIntArray.append(31, 17);
            sparseIntArray.append(32, 18);
            sparseIntArray.append(33, 19);
            sparseIntArray.append(15, 90);
            sparseIntArray.append(0, 26);
            sparseIntArray.append(48, 31);
            sparseIntArray.append(49, 32);
            sparseIntArray.append(30, 10);
            sparseIntArray.append(29, 9);
            sparseIntArray.append(66, 13);
            sparseIntArray.append(69, 16);
            sparseIntArray.append(67, 14);
            sparseIntArray.append(64, 11);
            sparseIntArray.append(68, 15);
            sparseIntArray.append(65, 12);
            sparseIntArray.append(55, 38);
            sparseIntArray.append(41, 37);
            sparseIntArray.append(40, 39);
            sparseIntArray.append(54, 40);
            sparseIntArray.append(39, 20);
            sparseIntArray.append(53, 36);
            sparseIntArray.append(28, 5);
            sparseIntArray.append(42, 91);
            sparseIntArray.append(50, 91);
            sparseIntArray.append(45, 91);
            sparseIntArray.append(22, 91);
            sparseIntArray.append(18, 91);
            sparseIntArray.append(3, 23);
            sparseIntArray.append(5, 27);
            sparseIntArray.append(7, 30);
            sparseIntArray.append(8, 8);
            sparseIntArray.append(4, 33);
            sparseIntArray.append(6, 2);
            sparseIntArray.append(1, 22);
            sparseIntArray.append(2, 21);
            sparseIntArray.append(56, 41);
            sparseIntArray.append(34, 42);
            sparseIntArray.append(17, 41);
            sparseIntArray.append(16, 42);
            sparseIntArray.append(71, 76);
            sparseIntArray.append(25, 61);
            sparseIntArray.append(27, 62);
            sparseIntArray.append(26, 63);
            sparseIntArray.append(60, 69);
            sparseIntArray.append(38, 70);
            sparseIntArray.append(12, 71);
            sparseIntArray.append(10, 72);
            sparseIntArray.append(11, 73);
            sparseIntArray.append(13, 74);
            sparseIntArray.append(9, 75);
            sparseIntArray.append(58, 84);
            sparseIntArray.append(59, 86);
            sparseIntArray.append(58, 83);
            sparseIntArray.append(37, 85);
            sparseIntArray.append(56, 87);
            sparseIntArray.append(34, 88);
            sparseIntArray.append(91, 89);
            sparseIntArray.append(15, 90);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Motion {
        public static final SparseIntArray sMapToConstant;
        public boolean mApply = false;
        public int mAnimateRelativeTo = -1;
        public int mAnimateCircleAngleTo = 0;
        public String mTransitionEasing = null;
        public int mPathMotionArc = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPolarRelativeTo = -1;
        public float mPathRotate = Float.NaN;
        public float mQuantizeMotionPhase = Float.NaN;
        public int mQuantizeMotionSteps = -1;
        public String mQuantizeInterpolatorString = null;
        public int mQuantizeInterpolatorType = -3;
        public int mQuantizeInterpolatorID = -1;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sMapToConstant = sparseIntArray;
            int[] iArr = R$styleable.Constraint;
            sparseIntArray.append(3, 1);
            sparseIntArray.append(5, 2);
            sparseIntArray.append(9, 3);
            sparseIntArray.append(2, 4);
            sparseIntArray.append(1, 5);
            sparseIntArray.append(0, 6);
            sparseIntArray.append(4, 7);
            sparseIntArray.append(8, 8);
            sparseIntArray.append(7, 9);
            sparseIntArray.append(6, 10);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PropertySet {
        public boolean mApply = false;
        public int visibility = 0;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Transform {
        public static final SparseIntArray sMapToConstant;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public int transformPivotTarget = -1;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sMapToConstant = sparseIntArray;
            int[] iArr = R$styleable.Constraint;
            sparseIntArray.append(6, 1);
            sparseIntArray.append(7, 2);
            sparseIntArray.append(8, 3);
            sparseIntArray.append(4, 4);
            sparseIntArray.append(5, 5);
            sparseIntArray.append(0, 6);
            sparseIntArray.append(1, 7);
            sparseIntArray.append(2, 8);
            sparseIntArray.append(3, 9);
            sparseIntArray.append(9, 10);
            sparseIntArray.append(10, 11);
            sparseIntArray.append(11, 12);
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sMapToConstant = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        sOverrideMapToConstant = sparseIntArray2;
        int[] iArr = R$styleable.Constraint;
        sparseIntArray.append(82, 25);
        sparseIntArray.append(83, 26);
        sparseIntArray.append(85, 29);
        sparseIntArray.append(86, 30);
        sparseIntArray.append(92, 36);
        sparseIntArray.append(91, 35);
        sparseIntArray.append(63, 4);
        sparseIntArray.append(62, 3);
        sparseIntArray.append(58, 1);
        sparseIntArray.append(60, 91);
        sparseIntArray.append(59, 92);
        sparseIntArray.append(FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu, 6);
        sparseIntArray.append(102, 7);
        sparseIntArray.append(70, 17);
        sparseIntArray.append(71, 18);
        sparseIntArray.append(72, 19);
        sparseIntArray.append(54, 99);
        sparseIntArray.append(0, 27);
        sparseIntArray.append(87, 32);
        sparseIntArray.append(88, 33);
        sparseIntArray.append(69, 10);
        sparseIntArray.append(68, 9);
        sparseIntArray.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_MOTION_EVENT_SOURCE$ar$edu, 13);
        sparseIntArray.append(109, 16);
        sparseIntArray.append(107, 14);
        sparseIntArray.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_KEY_EVENT$ar$edu, 11);
        sparseIntArray.append(108, 15);
        sparseIntArray.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_DEVICE_ROTATE$ar$edu, 12);
        sparseIntArray.append(95, 40);
        sparseIntArray.append(80, 39);
        sparseIntArray.append(79, 41);
        sparseIntArray.append(94, 42);
        sparseIntArray.append(78, 20);
        sparseIntArray.append(93, 37);
        sparseIntArray.append(67, 5);
        sparseIntArray.append(81, 87);
        sparseIntArray.append(90, 87);
        sparseIntArray.append(84, 87);
        sparseIntArray.append(61, 87);
        sparseIntArray.append(57, 87);
        sparseIntArray.append(5, 24);
        sparseIntArray.append(7, 28);
        sparseIntArray.append(23, 31);
        sparseIntArray.append(24, 8);
        sparseIntArray.append(6, 34);
        sparseIntArray.append(8, 2);
        sparseIntArray.append(3, 23);
        sparseIntArray.append(4, 21);
        sparseIntArray.append(96, 95);
        sparseIntArray.append(73, 96);
        sparseIntArray.append(2, 22);
        sparseIntArray.append(13, 43);
        sparseIntArray.append(26, 44);
        sparseIntArray.append(21, 45);
        sparseIntArray.append(22, 46);
        sparseIntArray.append(20, 60);
        sparseIntArray.append(18, 47);
        sparseIntArray.append(19, 48);
        sparseIntArray.append(14, 49);
        sparseIntArray.append(15, 50);
        sparseIntArray.append(16, 51);
        sparseIntArray.append(17, 52);
        sparseIntArray.append(25, 53);
        sparseIntArray.append(97, 54);
        sparseIntArray.append(74, 55);
        sparseIntArray.append(98, 56);
        sparseIntArray.append(75, 57);
        sparseIntArray.append(99, 58);
        sparseIntArray.append(76, 59);
        sparseIntArray.append(64, 61);
        sparseIntArray.append(66, 62);
        sparseIntArray.append(65, 63);
        sparseIntArray.append(28, 64);
        sparseIntArray.append(BrailleInputEvent.CMD_BRAILLE_DISPLAY_SETTINGS, 65);
        sparseIntArray.append(35, 66);
        sparseIntArray.append(BrailleInputEvent.CMD_TALKBACK_SETTINGS, 67);
        sparseIntArray.append(BrailleInputEvent.CMD_CONTROL_PREVIOUS, 79);
        sparseIntArray.append(1, 38);
        sparseIntArray.append(BrailleInputEvent.CMD_CONTROL_NEXT, 68);
        sparseIntArray.append(100, 69);
        sparseIntArray.append(77, 70);
        sparseIntArray.append(BrailleInputEvent.CMD_HEADING_PREVIOUS, 97);
        sparseIntArray.append(32, 71);
        sparseIntArray.append(30, 72);
        sparseIntArray.append(31, 73);
        sparseIntArray.append(33, 74);
        sparseIntArray.append(29, 75);
        sparseIntArray.append(BrailleInputEvent.CMD_LINK_NEXT, 76);
        sparseIntArray.append(89, 77);
        sparseIntArray.append(BrailleInputEvent.CMD_TURN_OFF_BRAILLE_DISPLAY, 78);
        sparseIntArray.append(56, 80);
        sparseIntArray.append(55, 81);
        sparseIntArray.append(BrailleInputEvent.CMD_TOGGLE_SCREEN_SEARCH, 82);
        sparseIntArray.append(BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE, 83);
        sparseIntArray.append(BrailleInputEvent.CMD_SWITCH_TO_NEXT_INPUT_LANGUAGE, 84);
        sparseIntArray.append(BrailleInputEvent.CMD_OPEN_TALKBACK_MENU, 85);
        sparseIntArray.append(BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL, 86);
        sparseIntArray2.append(85, 6);
        sparseIntArray2.append(85, 7);
        sparseIntArray2.append(0, 27);
        sparseIntArray2.append(89, 13);
        sparseIntArray2.append(92, 16);
        sparseIntArray2.append(90, 14);
        sparseIntArray2.append(87, 11);
        sparseIntArray2.append(91, 15);
        sparseIntArray2.append(88, 12);
        sparseIntArray2.append(78, 40);
        sparseIntArray2.append(71, 39);
        sparseIntArray2.append(70, 41);
        sparseIntArray2.append(77, 42);
        sparseIntArray2.append(69, 20);
        sparseIntArray2.append(76, 37);
        sparseIntArray2.append(60, 5);
        sparseIntArray2.append(72, 87);
        sparseIntArray2.append(75, 87);
        sparseIntArray2.append(73, 87);
        sparseIntArray2.append(57, 87);
        sparseIntArray2.append(56, 87);
        sparseIntArray2.append(5, 24);
        sparseIntArray2.append(7, 28);
        sparseIntArray2.append(23, 31);
        sparseIntArray2.append(24, 8);
        sparseIntArray2.append(6, 34);
        sparseIntArray2.append(8, 2);
        sparseIntArray2.append(3, 23);
        sparseIntArray2.append(4, 21);
        sparseIntArray2.append(79, 95);
        sparseIntArray2.append(64, 96);
        sparseIntArray2.append(2, 22);
        sparseIntArray2.append(13, 43);
        sparseIntArray2.append(26, 44);
        sparseIntArray2.append(21, 45);
        sparseIntArray2.append(22, 46);
        sparseIntArray2.append(20, 60);
        sparseIntArray2.append(18, 47);
        sparseIntArray2.append(19, 48);
        sparseIntArray2.append(14, 49);
        sparseIntArray2.append(15, 50);
        sparseIntArray2.append(16, 51);
        sparseIntArray2.append(17, 52);
        sparseIntArray2.append(25, 53);
        sparseIntArray2.append(80, 54);
        sparseIntArray2.append(65, 55);
        sparseIntArray2.append(81, 56);
        sparseIntArray2.append(66, 57);
        sparseIntArray2.append(82, 58);
        sparseIntArray2.append(67, 59);
        sparseIntArray2.append(59, 62);
        sparseIntArray2.append(58, 63);
        sparseIntArray2.append(28, 64);
        sparseIntArray2.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_DEVICE_ROTATE$ar$edu, 65);
        sparseIntArray2.append(34, 66);
        sparseIntArray2.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_MOTION_EVENT_SOURCE$ar$edu, 67);
        sparseIntArray2.append(96, 79);
        sparseIntArray2.append(1, 38);
        sparseIntArray2.append(97, 98);
        sparseIntArray2.append(95, 68);
        sparseIntArray2.append(83, 69);
        sparseIntArray2.append(68, 70);
        sparseIntArray2.append(32, 71);
        sparseIntArray2.append(30, 72);
        sparseIntArray2.append(31, 73);
        sparseIntArray2.append(33, 74);
        sparseIntArray2.append(29, 75);
        sparseIntArray2.append(98, 76);
        sparseIntArray2.append(74, 77);
        sparseIntArray2.append(107, 78);
        sparseIntArray2.append(55, 80);
        sparseIntArray2.append(54, 81);
        sparseIntArray2.append(100, 82);
        sparseIntArray2.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_KEY_EVENT$ar$edu, 83);
        sparseIntArray2.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu, 84);
        sparseIntArray2.append(102, 85);
        sparseIntArray2.append(FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu, 86);
        sparseIntArray2.append(94, 97);
    }

    private static final Constraint fillFromAttributeList$ar$ds(Context context, AttributeSet attributeSet, boolean z) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R$styleable.ConstraintOverride : R$styleable.Constraint);
        if (z) {
            populateOverride(constraint, obtainStyledAttributes);
        } else {
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index != 1 && index != 23 && index != 24) {
                    constraint.motion.mApply = true;
                    constraint.layout.mApply = true;
                    constraint.propertySet.mApply = true;
                    constraint.transform.mApply = true;
                }
                SparseIntArray sparseIntArray = sMapToConstant;
                switch (sparseIntArray.get(index)) {
                    case 1:
                        Layout layout = constraint.layout;
                        layout.baselineToBaseline = lookupID(obtainStyledAttributes, index, layout.baselineToBaseline);
                        break;
                    case 2:
                        Layout layout2 = constraint.layout;
                        layout2.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout2.bottomMargin);
                        break;
                    case 3:
                        Layout layout3 = constraint.layout;
                        layout3.bottomToBottom = lookupID(obtainStyledAttributes, index, layout3.bottomToBottom);
                        break;
                    case 4:
                        Layout layout4 = constraint.layout;
                        layout4.bottomToTop = lookupID(obtainStyledAttributes, index, layout4.bottomToTop);
                        break;
                    case 5:
                        constraint.layout.dimensionRatio = obtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        Layout layout5 = constraint.layout;
                        layout5.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, layout5.editorAbsoluteX);
                        break;
                    case 7:
                        Layout layout6 = constraint.layout;
                        layout6.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, layout6.editorAbsoluteY);
                        break;
                    case 8:
                        Layout layout7 = constraint.layout;
                        layout7.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout7.endMargin);
                        break;
                    case 9:
                        Layout layout8 = constraint.layout;
                        layout8.endToEnd = lookupID(obtainStyledAttributes, index, layout8.endToEnd);
                        break;
                    case 10:
                        Layout layout9 = constraint.layout;
                        layout9.endToStart = lookupID(obtainStyledAttributes, index, layout9.endToStart);
                        break;
                    case 11:
                        Layout layout10 = constraint.layout;
                        layout10.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout10.goneBottomMargin);
                        break;
                    case 12:
                        Layout layout11 = constraint.layout;
                        layout11.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout11.goneEndMargin);
                        break;
                    case 13:
                        Layout layout12 = constraint.layout;
                        layout12.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout12.goneLeftMargin);
                        break;
                    case 14:
                        Layout layout13 = constraint.layout;
                        layout13.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout13.goneRightMargin);
                        break;
                    case 15:
                        Layout layout14 = constraint.layout;
                        layout14.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout14.goneStartMargin);
                        break;
                    case 16:
                        Layout layout15 = constraint.layout;
                        layout15.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout15.goneTopMargin);
                        break;
                    case 17:
                        Layout layout16 = constraint.layout;
                        layout16.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, layout16.guideBegin);
                        break;
                    case 18:
                        Layout layout17 = constraint.layout;
                        layout17.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, layout17.guideEnd);
                        break;
                    case 19:
                        Layout layout18 = constraint.layout;
                        layout18.guidePercent = obtainStyledAttributes.getFloat(index, layout18.guidePercent);
                        break;
                    case 20:
                        Layout layout19 = constraint.layout;
                        layout19.horizontalBias = obtainStyledAttributes.getFloat(index, layout19.horizontalBias);
                        break;
                    case 21:
                        Layout layout20 = constraint.layout;
                        layout20.mHeight = obtainStyledAttributes.getLayoutDimension(index, layout20.mHeight);
                        break;
                    case 22:
                        PropertySet propertySet = constraint.propertySet;
                        propertySet.visibility = obtainStyledAttributes.getInt(index, propertySet.visibility);
                        PropertySet propertySet2 = constraint.propertySet;
                        propertySet2.visibility = VISIBILITY_FLAGS[propertySet2.visibility];
                        break;
                    case 23:
                        Layout layout21 = constraint.layout;
                        layout21.mWidth = obtainStyledAttributes.getLayoutDimension(index, layout21.mWidth);
                        break;
                    case 24:
                        Layout layout22 = constraint.layout;
                        layout22.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout22.leftMargin);
                        break;
                    case 25:
                        Layout layout23 = constraint.layout;
                        layout23.leftToLeft = lookupID(obtainStyledAttributes, index, layout23.leftToLeft);
                        break;
                    case 26:
                        Layout layout24 = constraint.layout;
                        layout24.leftToRight = lookupID(obtainStyledAttributes, index, layout24.leftToRight);
                        break;
                    case 27:
                        Layout layout25 = constraint.layout;
                        layout25.orientation = obtainStyledAttributes.getInt(index, layout25.orientation);
                        break;
                    case 28:
                        Layout layout26 = constraint.layout;
                        layout26.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout26.rightMargin);
                        break;
                    case 29:
                        Layout layout27 = constraint.layout;
                        layout27.rightToLeft = lookupID(obtainStyledAttributes, index, layout27.rightToLeft);
                        break;
                    case 30:
                        Layout layout28 = constraint.layout;
                        layout28.rightToRight = lookupID(obtainStyledAttributes, index, layout28.rightToRight);
                        break;
                    case 31:
                        Layout layout29 = constraint.layout;
                        layout29.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout29.startMargin);
                        break;
                    case 32:
                        Layout layout30 = constraint.layout;
                        layout30.startToEnd = lookupID(obtainStyledAttributes, index, layout30.startToEnd);
                        break;
                    case 33:
                        Layout layout31 = constraint.layout;
                        layout31.startToStart = lookupID(obtainStyledAttributes, index, layout31.startToStart);
                        break;
                    case 34:
                        Layout layout32 = constraint.layout;
                        layout32.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout32.topMargin);
                        break;
                    case 35:
                        Layout layout33 = constraint.layout;
                        layout33.topToBottom = lookupID(obtainStyledAttributes, index, layout33.topToBottom);
                        break;
                    case 36:
                        Layout layout34 = constraint.layout;
                        layout34.topToTop = lookupID(obtainStyledAttributes, index, layout34.topToTop);
                        break;
                    case 37:
                        Layout layout35 = constraint.layout;
                        layout35.verticalBias = obtainStyledAttributes.getFloat(index, layout35.verticalBias);
                        break;
                    case 38:
                        constraint.mViewId = obtainStyledAttributes.getResourceId(index, constraint.mViewId);
                        break;
                    case 39:
                        Layout layout36 = constraint.layout;
                        layout36.horizontalWeight = obtainStyledAttributes.getFloat(index, layout36.horizontalWeight);
                        break;
                    case 40:
                        Layout layout37 = constraint.layout;
                        layout37.verticalWeight = obtainStyledAttributes.getFloat(index, layout37.verticalWeight);
                        break;
                    case 41:
                        Layout layout38 = constraint.layout;
                        layout38.horizontalChainStyle = obtainStyledAttributes.getInt(index, layout38.horizontalChainStyle);
                        break;
                    case 42:
                        Layout layout39 = constraint.layout;
                        layout39.verticalChainStyle = obtainStyledAttributes.getInt(index, layout39.verticalChainStyle);
                        break;
                    case 43:
                        PropertySet propertySet3 = constraint.propertySet;
                        propertySet3.alpha = obtainStyledAttributes.getFloat(index, propertySet3.alpha);
                        break;
                    case 44:
                        Transform transform = constraint.transform;
                        transform.applyElevation = true;
                        transform.elevation = obtainStyledAttributes.getDimension(index, transform.elevation);
                        break;
                    case 45:
                        Transform transform2 = constraint.transform;
                        transform2.rotationX = obtainStyledAttributes.getFloat(index, transform2.rotationX);
                        break;
                    case 46:
                        Transform transform3 = constraint.transform;
                        transform3.rotationY = obtainStyledAttributes.getFloat(index, transform3.rotationY);
                        break;
                    case 47:
                        Transform transform4 = constraint.transform;
                        transform4.scaleX = obtainStyledAttributes.getFloat(index, transform4.scaleX);
                        break;
                    case 48:
                        Transform transform5 = constraint.transform;
                        transform5.scaleY = obtainStyledAttributes.getFloat(index, transform5.scaleY);
                        break;
                    case 49:
                        Transform transform6 = constraint.transform;
                        transform6.transformPivotX = obtainStyledAttributes.getDimension(index, transform6.transformPivotX);
                        break;
                    case 50:
                        Transform transform7 = constraint.transform;
                        transform7.transformPivotY = obtainStyledAttributes.getDimension(index, transform7.transformPivotY);
                        break;
                    case 51:
                        Transform transform8 = constraint.transform;
                        transform8.translationX = obtainStyledAttributes.getDimension(index, transform8.translationX);
                        break;
                    case 52:
                        Transform transform9 = constraint.transform;
                        transform9.translationY = obtainStyledAttributes.getDimension(index, transform9.translationY);
                        break;
                    case 53:
                        Transform transform10 = constraint.transform;
                        transform10.translationZ = obtainStyledAttributes.getDimension(index, transform10.translationZ);
                        break;
                    case 54:
                        Layout layout40 = constraint.layout;
                        layout40.widthDefault = obtainStyledAttributes.getInt(index, layout40.widthDefault);
                        break;
                    case 55:
                        Layout layout41 = constraint.layout;
                        layout41.heightDefault = obtainStyledAttributes.getInt(index, layout41.heightDefault);
                        break;
                    case 56:
                        Layout layout42 = constraint.layout;
                        layout42.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, layout42.widthMax);
                        break;
                    case 57:
                        Layout layout43 = constraint.layout;
                        layout43.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, layout43.heightMax);
                        break;
                    case 58:
                        Layout layout44 = constraint.layout;
                        layout44.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, layout44.widthMin);
                        break;
                    case 59:
                        Layout layout45 = constraint.layout;
                        layout45.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, layout45.heightMin);
                        break;
                    case 60:
                        Transform transform11 = constraint.transform;
                        transform11.rotation = obtainStyledAttributes.getFloat(index, transform11.rotation);
                        break;
                    case 61:
                        Layout layout46 = constraint.layout;
                        layout46.circleConstraint = lookupID(obtainStyledAttributes, index, layout46.circleConstraint);
                        break;
                    case 62:
                        Layout layout47 = constraint.layout;
                        layout47.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, layout47.circleRadius);
                        break;
                    case 63:
                        Layout layout48 = constraint.layout;
                        layout48.circleAngle = obtainStyledAttributes.getFloat(index, layout48.circleAngle);
                        break;
                    case 64:
                        Motion motion = constraint.motion;
                        motion.mAnimateRelativeTo = lookupID(obtainStyledAttributes, index, motion.mAnimateRelativeTo);
                        break;
                    case 65:
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            constraint.motion.mTransitionEasing = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            constraint.motion.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        }
                    case 66:
                        constraint.motion.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 67:
                        Motion motion2 = constraint.motion;
                        motion2.mPathRotate = obtainStyledAttributes.getFloat(index, motion2.mPathRotate);
                        break;
                    case 68:
                        PropertySet propertySet4 = constraint.propertySet;
                        propertySet4.mProgress = obtainStyledAttributes.getFloat(index, propertySet4.mProgress);
                        break;
                    case 69:
                        constraint.layout.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                        break;
                    case 70:
                        constraint.layout.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                        break;
                    case 71:
                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        break;
                    case 72:
                        Layout layout49 = constraint.layout;
                        layout49.mBarrierDirection = obtainStyledAttributes.getInt(index, layout49.mBarrierDirection);
                        break;
                    case 73:
                        Layout layout50 = constraint.layout;
                        layout50.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout50.mBarrierMargin);
                        break;
                    case 74:
                        constraint.layout.mReferenceIdString = obtainStyledAttributes.getString(index);
                        break;
                    case 75:
                        Layout layout51 = constraint.layout;
                        layout51.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, layout51.mBarrierAllowsGoneWidgets);
                        break;
                    case 76:
                        Motion motion3 = constraint.motion;
                        motion3.mPathMotionArc = obtainStyledAttributes.getInt(index, motion3.mPathMotionArc);
                        break;
                    case 77:
                        constraint.layout.mConstraintTag = obtainStyledAttributes.getString(index);
                        break;
                    case 78:
                        PropertySet propertySet5 = constraint.propertySet;
                        propertySet5.mVisibilityMode = obtainStyledAttributes.getInt(index, propertySet5.mVisibilityMode);
                        break;
                    case 79:
                        Motion motion4 = constraint.motion;
                        motion4.mMotionStagger = obtainStyledAttributes.getFloat(index, motion4.mMotionStagger);
                        break;
                    case 80:
                        Layout layout52 = constraint.layout;
                        layout52.constrainedWidth = obtainStyledAttributes.getBoolean(index, layout52.constrainedWidth);
                        break;
                    case 81:
                        Layout layout53 = constraint.layout;
                        layout53.constrainedHeight = obtainStyledAttributes.getBoolean(index, layout53.constrainedHeight);
                        break;
                    case 82:
                        Motion motion5 = constraint.motion;
                        motion5.mAnimateCircleAngleTo = obtainStyledAttributes.getInteger(index, motion5.mAnimateCircleAngleTo);
                        break;
                    case 83:
                        Transform transform12 = constraint.transform;
                        transform12.transformPivotTarget = lookupID(obtainStyledAttributes, index, transform12.transformPivotTarget);
                        break;
                    case 84:
                        Motion motion6 = constraint.motion;
                        motion6.mQuantizeMotionSteps = obtainStyledAttributes.getInteger(index, motion6.mQuantizeMotionSteps);
                        break;
                    case 85:
                        Motion motion7 = constraint.motion;
                        motion7.mQuantizeMotionPhase = obtainStyledAttributes.getFloat(index, motion7.mQuantizeMotionPhase);
                        break;
                    case 86:
                        TypedValue peekValue = obtainStyledAttributes.peekValue(index);
                        if (peekValue.type == 1) {
                            constraint.motion.mQuantizeInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                            Motion motion8 = constraint.motion;
                            if (motion8.mQuantizeInterpolatorID != -1) {
                                motion8.mQuantizeInterpolatorType = -2;
                                break;
                            } else {
                                break;
                            }
                        } else if (peekValue.type == 3) {
                            constraint.motion.mQuantizeInterpolatorString = obtainStyledAttributes.getString(index);
                            if (constraint.motion.mQuantizeInterpolatorString.indexOf("/") > 0) {
                                constraint.motion.mQuantizeInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                                constraint.motion.mQuantizeInterpolatorType = -2;
                                break;
                            } else {
                                constraint.motion.mQuantizeInterpolatorType = -1;
                                break;
                            }
                        } else {
                            Motion motion9 = constraint.motion;
                            motion9.mQuantizeInterpolatorType = obtainStyledAttributes.getInteger(index, motion9.mQuantizeInterpolatorID);
                            break;
                        }
                    case 87:
                        Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index, "unused attribute 0x", sparseIntArray));
                        break;
                    case 88:
                    case 89:
                    case 90:
                    default:
                        Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index, "Unknown attribute 0x", sparseIntArray));
                        break;
                    case 91:
                        Layout layout54 = constraint.layout;
                        layout54.baselineToTop = lookupID(obtainStyledAttributes, index, layout54.baselineToTop);
                        break;
                    case 92:
                        Layout layout55 = constraint.layout;
                        layout55.baselineToBottom = lookupID(obtainStyledAttributes, index, layout55.baselineToBottom);
                        break;
                    case 93:
                        Layout layout56 = constraint.layout;
                        layout56.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout56.baselineMargin);
                        break;
                    case 94:
                        Layout layout57 = constraint.layout;
                        layout57.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout57.goneBaselineMargin);
                        break;
                    case 95:
                        parseDimensionConstraints(constraint.layout, obtainStyledAttributes, index, 0);
                        break;
                    case BraillebackLogProto$LanguageCode.NORWEGIAN_COMP8$ar$edu /* 96 */:
                        parseDimensionConstraints(constraint.layout, obtainStyledAttributes, index, 1);
                        break;
                    case 97:
                        Layout layout58 = constraint.layout;
                        layout58.mWrapBehavior = obtainStyledAttributes.getInt(index, layout58.mWrapBehavior);
                        break;
                }
            }
            Layout layout59 = constraint.layout;
            if (layout59.mReferenceIdString != null) {
                layout59.mReferenceIds = null;
            }
        }
        obtainStyledAttributes.recycle();
        return constraint;
    }

    public static int lookupID(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        if (resourceId == -1) {
            return typedArray.getInt(i, -1);
        }
        return resourceId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void parseDimensionConstraints(java.lang.Object r8, android.content.res.TypedArray r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.parseDimensionConstraints(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void parseDimensionRatioString(ConstraintLayout.LayoutParams layoutParams, String str) {
        int i;
        int i2 = -1;
        float f = Float.NaN;
        if (str != null) {
            int indexOf = str.indexOf(44);
            int length = str.length();
            int i3 = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (!substring.equalsIgnoreCase("W")) {
                    if (substring.equalsIgnoreCase("H")) {
                        i3 = 1;
                    } else {
                        i3 = -1;
                    }
                }
                int i4 = i3;
                i3 = indexOf + 1;
                i = i4;
            } else {
                i = -1;
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf2 >= 0 && indexOf2 < length - 1) {
                String substring2 = str.substring(i3, indexOf2);
                String substring3 = str.substring(indexOf2 + 1);
                if (substring2.length() > 0 && substring3.length() > 0) {
                    float parseFloat = Float.parseFloat(substring2);
                    float parseFloat2 = Float.parseFloat(substring3);
                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                        f = i == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                    }
                }
                i2 = i;
            } else {
                String substring4 = str.substring(i3);
                if (substring4.length() > 0) {
                    f = Float.parseFloat(substring4);
                }
                i2 = i;
            }
            i2 = i;
        }
        layoutParams.dimensionRatio = str;
        layoutParams.mDimensionRatioValue = f;
        layoutParams.mDimensionRatioSide = i2;
    }

    private static void populateOverride(Constraint constraint, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        Constraint.Delta delta = new Constraint.Delta();
        constraint.mDelta = delta;
        constraint.motion.mApply = false;
        constraint.layout.mApply = false;
        constraint.propertySet.mApply = false;
        constraint.transform.mApply = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (sOverrideMapToConstant.get(index)) {
                case 2:
                    delta.add(2, typedArray.getDimensionPixelSize(index, constraint.layout.bottomMargin));
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case 61:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                default:
                    Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index, "Unknown attribute 0x", sMapToConstant));
                    break;
                case 5:
                    delta.add(5, typedArray.getString(index));
                    break;
                case 6:
                    delta.add(6, typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteX));
                    break;
                case 7:
                    delta.add(7, typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteY));
                    break;
                case 8:
                    delta.add(8, typedArray.getDimensionPixelSize(index, constraint.layout.endMargin));
                    break;
                case 11:
                    delta.add(11, typedArray.getDimensionPixelSize(index, constraint.layout.goneBottomMargin));
                    break;
                case 12:
                    delta.add(12, typedArray.getDimensionPixelSize(index, constraint.layout.goneEndMargin));
                    break;
                case 13:
                    delta.add(13, typedArray.getDimensionPixelSize(index, constraint.layout.goneLeftMargin));
                    break;
                case 14:
                    delta.add(14, typedArray.getDimensionPixelSize(index, constraint.layout.goneRightMargin));
                    break;
                case 15:
                    delta.add(15, typedArray.getDimensionPixelSize(index, constraint.layout.goneStartMargin));
                    break;
                case 16:
                    delta.add(16, typedArray.getDimensionPixelSize(index, constraint.layout.goneTopMargin));
                    break;
                case 17:
                    delta.add(17, typedArray.getDimensionPixelOffset(index, constraint.layout.guideBegin));
                    break;
                case 18:
                    delta.add(18, typedArray.getDimensionPixelOffset(index, constraint.layout.guideEnd));
                    break;
                case 19:
                    delta.add(19, typedArray.getFloat(index, constraint.layout.guidePercent));
                    break;
                case 20:
                    delta.add(20, typedArray.getFloat(index, constraint.layout.horizontalBias));
                    break;
                case 21:
                    delta.add(21, typedArray.getLayoutDimension(index, constraint.layout.mHeight));
                    break;
                case 22:
                    delta.add(22, VISIBILITY_FLAGS[typedArray.getInt(index, constraint.propertySet.visibility)]);
                    break;
                case 23:
                    delta.add(23, typedArray.getLayoutDimension(index, constraint.layout.mWidth));
                    break;
                case 24:
                    delta.add(24, typedArray.getDimensionPixelSize(index, constraint.layout.leftMargin));
                    break;
                case 27:
                    delta.add(27, typedArray.getInt(index, constraint.layout.orientation));
                    break;
                case 28:
                    delta.add(28, typedArray.getDimensionPixelSize(index, constraint.layout.rightMargin));
                    break;
                case 31:
                    delta.add(31, typedArray.getDimensionPixelSize(index, constraint.layout.startMargin));
                    break;
                case 34:
                    delta.add(34, typedArray.getDimensionPixelSize(index, constraint.layout.topMargin));
                    break;
                case 37:
                    delta.add(37, typedArray.getFloat(index, constraint.layout.verticalBias));
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, constraint.mViewId);
                    constraint.mViewId = resourceId;
                    delta.add(38, resourceId);
                    break;
                case 39:
                    delta.add(39, typedArray.getFloat(index, constraint.layout.horizontalWeight));
                    break;
                case 40:
                    delta.add(40, typedArray.getFloat(index, constraint.layout.verticalWeight));
                    break;
                case 41:
                    delta.add(41, typedArray.getInt(index, constraint.layout.horizontalChainStyle));
                    break;
                case 42:
                    delta.add(42, typedArray.getInt(index, constraint.layout.verticalChainStyle));
                    break;
                case 43:
                    delta.add(43, typedArray.getFloat(index, constraint.propertySet.alpha));
                    break;
                case 44:
                    delta.add(44, true);
                    delta.add(44, typedArray.getDimension(index, constraint.transform.elevation));
                    break;
                case 45:
                    delta.add(45, typedArray.getFloat(index, constraint.transform.rotationX));
                    break;
                case 46:
                    delta.add(46, typedArray.getFloat(index, constraint.transform.rotationY));
                    break;
                case 47:
                    delta.add(47, typedArray.getFloat(index, constraint.transform.scaleX));
                    break;
                case 48:
                    delta.add(48, typedArray.getFloat(index, constraint.transform.scaleY));
                    break;
                case 49:
                    delta.add(49, typedArray.getDimension(index, constraint.transform.transformPivotX));
                    break;
                case 50:
                    delta.add(50, typedArray.getDimension(index, constraint.transform.transformPivotY));
                    break;
                case 51:
                    delta.add(51, typedArray.getDimension(index, constraint.transform.translationX));
                    break;
                case 52:
                    delta.add(52, typedArray.getDimension(index, constraint.transform.translationY));
                    break;
                case 53:
                    delta.add(53, typedArray.getDimension(index, constraint.transform.translationZ));
                    break;
                case 54:
                    delta.add(54, typedArray.getInt(index, constraint.layout.widthDefault));
                    break;
                case 55:
                    delta.add(55, typedArray.getInt(index, constraint.layout.heightDefault));
                    break;
                case 56:
                    delta.add(56, typedArray.getDimensionPixelSize(index, constraint.layout.widthMax));
                    break;
                case 57:
                    delta.add(57, typedArray.getDimensionPixelSize(index, constraint.layout.heightMax));
                    break;
                case 58:
                    delta.add(58, typedArray.getDimensionPixelSize(index, constraint.layout.widthMin));
                    break;
                case 59:
                    delta.add(59, typedArray.getDimensionPixelSize(index, constraint.layout.heightMin));
                    break;
                case 60:
                    delta.add(60, typedArray.getFloat(index, constraint.transform.rotation));
                    break;
                case 62:
                    delta.add(62, typedArray.getDimensionPixelSize(index, constraint.layout.circleRadius));
                    break;
                case 63:
                    delta.add(63, typedArray.getFloat(index, constraint.layout.circleAngle));
                    break;
                case 64:
                    delta.add(64, lookupID(typedArray, index, constraint.motion.mAnimateRelativeTo));
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        delta.add(65, typedArray.getString(index));
                        break;
                    } else {
                        delta.add(65, Easing.NAMED_EASING[typedArray.getInteger(index, 0)]);
                        break;
                    }
                case 66:
                    delta.add(66, typedArray.getInt(index, 0));
                    break;
                case 67:
                    delta.add(67, typedArray.getFloat(index, constraint.motion.mPathRotate));
                    break;
                case 68:
                    delta.add(68, typedArray.getFloat(index, constraint.propertySet.mProgress));
                    break;
                case 69:
                    delta.add(69, typedArray.getFloat(index, 1.0f));
                    break;
                case 70:
                    delta.add(70, typedArray.getFloat(index, 1.0f));
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    delta.add(72, typedArray.getInt(index, constraint.layout.mBarrierDirection));
                    break;
                case 73:
                    delta.add(73, typedArray.getDimensionPixelSize(index, constraint.layout.mBarrierMargin));
                    break;
                case 74:
                    delta.add(74, typedArray.getString(index));
                    break;
                case 75:
                    delta.add(75, typedArray.getBoolean(index, constraint.layout.mBarrierAllowsGoneWidgets));
                    break;
                case 76:
                    delta.add(76, typedArray.getInt(index, constraint.motion.mPathMotionArc));
                    break;
                case 77:
                    delta.add(77, typedArray.getString(index));
                    break;
                case 78:
                    delta.add(78, typedArray.getInt(index, constraint.propertySet.mVisibilityMode));
                    break;
                case 79:
                    delta.add(79, typedArray.getFloat(index, constraint.motion.mMotionStagger));
                    break;
                case 80:
                    delta.add(80, typedArray.getBoolean(index, constraint.layout.constrainedWidth));
                    break;
                case 81:
                    delta.add(81, typedArray.getBoolean(index, constraint.layout.constrainedHeight));
                    break;
                case 82:
                    delta.add(82, typedArray.getInteger(index, constraint.motion.mAnimateCircleAngleTo));
                    break;
                case 83:
                    delta.add(83, lookupID(typedArray, index, constraint.transform.transformPivotTarget));
                    break;
                case 84:
                    delta.add(84, typedArray.getInteger(index, constraint.motion.mQuantizeMotionSteps));
                    break;
                case 85:
                    delta.add(85, typedArray.getFloat(index, constraint.motion.mQuantizeMotionPhase));
                    break;
                case 86:
                    TypedValue peekValue = typedArray.peekValue(index);
                    if (peekValue.type == 1) {
                        constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                        delta.add(89, constraint.motion.mQuantizeInterpolatorID);
                        Motion motion = constraint.motion;
                        if (motion.mQuantizeInterpolatorID != -1) {
                            motion.mQuantizeInterpolatorType = -2;
                            delta.add(88, -2);
                            break;
                        } else {
                            break;
                        }
                    } else if (peekValue.type == 3) {
                        constraint.motion.mQuantizeInterpolatorString = typedArray.getString(index);
                        delta.add(90, constraint.motion.mQuantizeInterpolatorString);
                        if (constraint.motion.mQuantizeInterpolatorString.indexOf("/") > 0) {
                            constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                            delta.add(89, constraint.motion.mQuantizeInterpolatorID);
                            constraint.motion.mQuantizeInterpolatorType = -2;
                            delta.add(88, -2);
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorType = -1;
                            delta.add(88, -1);
                            break;
                        }
                    } else {
                        Motion motion2 = constraint.motion;
                        motion2.mQuantizeInterpolatorType = typedArray.getInteger(index, motion2.mQuantizeInterpolatorID);
                        delta.add(88, constraint.motion.mQuantizeInterpolatorType);
                        break;
                    }
                case 87:
                    Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index, "unused attribute 0x", sMapToConstant));
                    break;
                case 93:
                    delta.add(93, typedArray.getDimensionPixelSize(index, constraint.layout.baselineMargin));
                    break;
                case 94:
                    delta.add(94, typedArray.getDimensionPixelSize(index, constraint.layout.goneBaselineMargin));
                    break;
                case 95:
                    parseDimensionConstraints(delta, typedArray, index, 0);
                    break;
                case BraillebackLogProto$LanguageCode.NORWEGIAN_COMP8$ar$edu /* 96 */:
                    parseDimensionConstraints(delta, typedArray, index, 1);
                    break;
                case 97:
                    delta.add(97, typedArray.getInt(index, constraint.layout.mWrapBehavior));
                    break;
                case 98:
                    if (typedArray.peekValue(index).type == 3) {
                        typedArray.getString(index);
                        break;
                    } else {
                        constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                        break;
                    }
                case 99:
                    delta.add(99, typedArray.getBoolean(index, constraint.layout.guidelineUseRtl));
                    break;
            }
        }
    }

    public final void clone(Context context, int i) {
        ConstraintLayout constraintLayout;
        ConstraintLayout constraintLayout2;
        ConstraintSet constraintSet = this;
        ConstraintLayout constraintLayout3 = (ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        int childCount = constraintLayout3.getChildCount();
        constraintSet.mConstraints.clear();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = constraintLayout3.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id != -1) {
                HashMap hashMap = constraintSet.mConstraints;
                Integer valueOf = Integer.valueOf(id);
                if (!hashMap.containsKey(valueOf)) {
                    constraintSet.mConstraints.put(valueOf, new Constraint());
                }
                Constraint constraint = (Constraint) constraintSet.mConstraints.get(valueOf);
                if (constraint == null) {
                    constraintLayout = constraintLayout3;
                } else {
                    HashMap hashMap2 = constraintSet.mSavedAttributes;
                    HashMap hashMap3 = new HashMap();
                    Class<?> cls = childAt.getClass();
                    for (String str : hashMap2.keySet()) {
                        ConstraintAttribute constraintAttribute = (ConstraintAttribute) hashMap2.get(str);
                        try {
                            if (str.equals("BackgroundColor")) {
                                constraintLayout2 = constraintLayout3;
                                try {
                                    hashMap3.put(str, new ConstraintAttribute(constraintAttribute, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                                } catch (IllegalAccessException e) {
                                    e = e;
                                    Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName(), e);
                                    constraintLayout3 = constraintLayout2;
                                } catch (NoSuchMethodException e2) {
                                    e = e2;
                                    Log.e("TransitionLayout", cls.getName() + " must have a method " + str, e);
                                    constraintLayout3 = constraintLayout2;
                                } catch (InvocationTargetException e3) {
                                    e = e3;
                                    Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName(), e);
                                    constraintLayout3 = constraintLayout2;
                                }
                            } else {
                                constraintLayout2 = constraintLayout3;
                                hashMap3.put(str, new ConstraintAttribute(constraintAttribute, cls.getMethod(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(str, "getMap"), null).invoke(childAt, null)));
                            }
                        } catch (IllegalAccessException e4) {
                            e = e4;
                            constraintLayout2 = constraintLayout3;
                        } catch (NoSuchMethodException e5) {
                            e = e5;
                            constraintLayout2 = constraintLayout3;
                        } catch (InvocationTargetException e6) {
                            e = e6;
                            constraintLayout2 = constraintLayout3;
                        }
                        constraintLayout3 = constraintLayout2;
                    }
                    constraintLayout = constraintLayout3;
                    constraint.mCustomConstraints = hashMap3;
                    constraint.mViewId = id;
                    int i3 = layoutParams.leftToLeft;
                    Layout layout = constraint.layout;
                    layout.leftToLeft = i3;
                    layout.leftToRight = layoutParams.leftToRight;
                    layout.rightToLeft = layoutParams.rightToLeft;
                    layout.rightToRight = layoutParams.rightToRight;
                    layout.topToTop = layoutParams.topToTop;
                    layout.topToBottom = layoutParams.topToBottom;
                    layout.bottomToTop = layoutParams.bottomToTop;
                    layout.bottomToBottom = layoutParams.bottomToBottom;
                    layout.baselineToBaseline = layoutParams.baselineToBaseline;
                    layout.baselineToTop = layoutParams.baselineToTop;
                    layout.baselineToBottom = layoutParams.baselineToBottom;
                    layout.startToEnd = layoutParams.startToEnd;
                    layout.startToStart = layoutParams.startToStart;
                    layout.endToStart = layoutParams.endToStart;
                    layout.endToEnd = layoutParams.endToEnd;
                    layout.horizontalBias = layoutParams.horizontalBias;
                    layout.verticalBias = layoutParams.verticalBias;
                    layout.dimensionRatio = layoutParams.dimensionRatio;
                    layout.circleConstraint = layoutParams.circleConstraint;
                    layout.circleRadius = layoutParams.circleRadius;
                    layout.circleAngle = layoutParams.circleAngle;
                    layout.editorAbsoluteX = layoutParams.editorAbsoluteX;
                    layout.editorAbsoluteY = layoutParams.editorAbsoluteY;
                    layout.orientation = layoutParams.orientation;
                    layout.guidePercent = layoutParams.guidePercent;
                    layout.guideBegin = layoutParams.guideBegin;
                    layout.guideEnd = layoutParams.guideEnd;
                    layout.mWidth = layoutParams.width;
                    constraint.layout.mHeight = layoutParams.height;
                    constraint.layout.leftMargin = layoutParams.leftMargin;
                    constraint.layout.rightMargin = layoutParams.rightMargin;
                    constraint.layout.topMargin = layoutParams.topMargin;
                    constraint.layout.bottomMargin = layoutParams.bottomMargin;
                    int i4 = layoutParams.baselineMargin;
                    Layout layout2 = constraint.layout;
                    layout2.baselineMargin = i4;
                    layout2.verticalWeight = layoutParams.verticalWeight;
                    layout2.horizontalWeight = layoutParams.horizontalWeight;
                    layout2.verticalChainStyle = layoutParams.verticalChainStyle;
                    layout2.horizontalChainStyle = layoutParams.horizontalChainStyle;
                    layout2.constrainedWidth = layoutParams.constrainedWidth;
                    layout2.constrainedHeight = layoutParams.constrainedHeight;
                    layout2.widthDefault = layoutParams.matchConstraintDefaultWidth;
                    layout2.heightDefault = layoutParams.matchConstraintDefaultHeight;
                    layout2.widthMax = layoutParams.matchConstraintMaxWidth;
                    layout2.heightMax = layoutParams.matchConstraintMaxHeight;
                    layout2.widthMin = layoutParams.matchConstraintMinWidth;
                    layout2.heightMin = layoutParams.matchConstraintMinHeight;
                    layout2.widthPercent = layoutParams.matchConstraintPercentWidth;
                    layout2.heightPercent = layoutParams.matchConstraintPercentHeight;
                    layout2.mConstraintTag = layoutParams.constraintTag;
                    layout2.goneTopMargin = layoutParams.goneTopMargin;
                    layout2.goneBottomMargin = layoutParams.goneBottomMargin;
                    layout2.goneLeftMargin = layoutParams.goneLeftMargin;
                    layout2.goneRightMargin = layoutParams.goneRightMargin;
                    layout2.goneStartMargin = layoutParams.goneStartMargin;
                    layout2.goneEndMargin = layoutParams.goneEndMargin;
                    layout2.goneBaselineMargin = layoutParams.goneBaselineMargin;
                    layout2.mWrapBehavior = layoutParams.wrapBehaviorInParent;
                    layout2.endMargin = layoutParams.getMarginEnd();
                    constraint.layout.startMargin = layoutParams.getMarginStart();
                    constraint.propertySet.visibility = childAt.getVisibility();
                    constraint.propertySet.alpha = childAt.getAlpha();
                    constraint.transform.rotation = childAt.getRotation();
                    constraint.transform.rotationX = childAt.getRotationX();
                    constraint.transform.rotationY = childAt.getRotationY();
                    constraint.transform.scaleX = childAt.getScaleX();
                    constraint.transform.scaleY = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        Transform transform = constraint.transform;
                        transform.transformPivotX = pivotX;
                        transform.transformPivotY = pivotY;
                    }
                    constraint.transform.translationX = childAt.getTranslationX();
                    constraint.transform.translationY = childAt.getTranslationY();
                    constraint.transform.translationZ = childAt.getTranslationZ();
                    Transform transform2 = constraint.transform;
                    if (transform2.applyElevation) {
                        transform2.elevation = childAt.getElevation();
                    }
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        Layout layout3 = constraint.layout;
                        layout3.mBarrierAllowsGoneWidgets = barrier.mBarrier.mAllowsGoneWidget;
                        layout3.mReferenceIds = Arrays.copyOf(barrier.mIds, barrier.mCount);
                        Layout layout4 = constraint.layout;
                        layout4.mBarrierDirection = barrier.mIndicatedType;
                        layout4.mBarrierMargin = barrier.mBarrier.mMargin;
                    }
                }
                i2++;
                constraintSet = this;
                constraintLayout3 = constraintLayout;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public final int[] convertReferenceString(View view, String str) {
        int length;
        int i;
        Object designInformation$ar$ds;
        String[] split = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            length = split.length;
            if (i2 >= length) {
                break;
            }
            String trim = split[i2].trim();
            try {
                i = R$id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 0) {
                i = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i == 0) {
                if (view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation$ar$ds = ((ConstraintLayout) view.getParent()).getDesignInformation$ar$ds(trim)) != null && (designInformation$ar$ds instanceof Integer)) {
                    i = ((Integer) designInformation$ar$ds).intValue();
                } else {
                    i = 0;
                }
            }
            iArr[i3] = i;
            i2++;
            i3++;
        }
        if (i3 != length) {
            return Arrays.copyOf(iArr, i3);
        }
        return iArr;
    }

    public final void load(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    Constraint fillFromAttributeList$ar$ds = fillFromAttributeList$ar$ds(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        fillFromAttributeList$ar$ds.layout.mIsGuideline = true;
                    }
                    this.mConstraints.put(Integer.valueOf(fillFromAttributeList$ar$ds.mViewId), fillFromAttributeList$ar$ds);
                }
            }
        } catch (IOException e) {
            Log.e("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Error parsing resource: "), e);
        } catch (XmlPullParserException e2) {
            Log.e("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Error parsing resource: "), e2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Constraint {
        Delta mDelta;
        int mViewId;
        public final PropertySet propertySet = new PropertySet();
        public final Motion motion = new Motion();
        public final Layout layout = new Layout();
        public final Transform transform = new Transform();
        public HashMap mCustomConstraints = new HashMap();

        public final void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout = this.layout;
            layoutParams.leftToLeft = layout.leftToLeft;
            layoutParams.leftToRight = layout.leftToRight;
            layoutParams.rightToLeft = layout.rightToLeft;
            layoutParams.rightToRight = layout.rightToRight;
            layoutParams.topToTop = layout.topToTop;
            layoutParams.topToBottom = layout.topToBottom;
            layoutParams.bottomToTop = layout.bottomToTop;
            layoutParams.bottomToBottom = layout.bottomToBottom;
            layoutParams.baselineToBaseline = layout.baselineToBaseline;
            layoutParams.baselineToTop = layout.baselineToTop;
            layoutParams.baselineToBottom = layout.baselineToBottom;
            layoutParams.startToEnd = layout.startToEnd;
            layoutParams.startToStart = layout.startToStart;
            layoutParams.endToStart = layout.endToStart;
            layoutParams.endToEnd = layout.endToEnd;
            layoutParams.leftMargin = layout.leftMargin;
            layoutParams.rightMargin = this.layout.rightMargin;
            layoutParams.topMargin = this.layout.topMargin;
            layoutParams.bottomMargin = this.layout.bottomMargin;
            Layout layout2 = this.layout;
            layoutParams.goneStartMargin = layout2.goneStartMargin;
            layoutParams.goneEndMargin = layout2.goneEndMargin;
            layoutParams.goneTopMargin = layout2.goneTopMargin;
            layoutParams.goneBottomMargin = layout2.goneBottomMargin;
            layoutParams.horizontalBias = layout2.horizontalBias;
            layoutParams.verticalBias = layout2.verticalBias;
            layoutParams.circleConstraint = layout2.circleConstraint;
            layoutParams.circleRadius = layout2.circleRadius;
            layoutParams.circleAngle = layout2.circleAngle;
            layoutParams.dimensionRatio = layout2.dimensionRatio;
            layoutParams.editorAbsoluteX = layout2.editorAbsoluteX;
            layoutParams.editorAbsoluteY = layout2.editorAbsoluteY;
            layoutParams.verticalWeight = layout2.verticalWeight;
            layoutParams.horizontalWeight = layout2.horizontalWeight;
            layoutParams.verticalChainStyle = layout2.verticalChainStyle;
            layoutParams.horizontalChainStyle = layout2.horizontalChainStyle;
            layoutParams.constrainedWidth = layout2.constrainedWidth;
            layoutParams.constrainedHeight = layout2.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = layout2.widthDefault;
            layoutParams.matchConstraintDefaultHeight = layout2.heightDefault;
            layoutParams.matchConstraintMaxWidth = layout2.widthMax;
            layoutParams.matchConstraintMaxHeight = layout2.heightMax;
            layoutParams.matchConstraintMinWidth = layout2.widthMin;
            layoutParams.matchConstraintMinHeight = layout2.heightMin;
            layoutParams.matchConstraintPercentWidth = layout2.widthPercent;
            layoutParams.matchConstraintPercentHeight = layout2.heightPercent;
            layoutParams.orientation = layout2.orientation;
            layoutParams.guidePercent = layout2.guidePercent;
            layoutParams.guideBegin = layout2.guideBegin;
            layoutParams.guideEnd = layout2.guideEnd;
            layoutParams.width = layout2.mWidth;
            layoutParams.height = this.layout.mHeight;
            Layout layout3 = this.layout;
            String str = layout3.mConstraintTag;
            if (str != null) {
                layoutParams.constraintTag = str;
            }
            layoutParams.wrapBehaviorInParent = layout3.mWrapBehavior;
            layoutParams.setMarginStart(layout3.startMargin);
            layoutParams.setMarginEnd(this.layout.endMargin);
            layoutParams.validate();
        }

        public final /* bridge */ /* synthetic */ Object clone() {
            Constraint constraint = new Constraint();
            Layout layout = constraint.layout;
            Layout layout2 = this.layout;
            layout.mIsGuideline = layout2.mIsGuideline;
            layout.mWidth = layout2.mWidth;
            layout.mApply = layout2.mApply;
            layout.mHeight = layout2.mHeight;
            layout.guideBegin = layout2.guideBegin;
            layout.guideEnd = layout2.guideEnd;
            layout.guidePercent = layout2.guidePercent;
            layout.guidelineUseRtl = layout2.guidelineUseRtl;
            layout.leftToLeft = layout2.leftToLeft;
            layout.leftToRight = layout2.leftToRight;
            layout.rightToLeft = layout2.rightToLeft;
            layout.rightToRight = layout2.rightToRight;
            layout.topToTop = layout2.topToTop;
            layout.topToBottom = layout2.topToBottom;
            layout.bottomToTop = layout2.bottomToTop;
            layout.bottomToBottom = layout2.bottomToBottom;
            layout.baselineToBaseline = layout2.baselineToBaseline;
            layout.baselineToTop = layout2.baselineToTop;
            layout.baselineToBottom = layout2.baselineToBottom;
            layout.startToEnd = layout2.startToEnd;
            layout.startToStart = layout2.startToStart;
            layout.endToStart = layout2.endToStart;
            layout.endToEnd = layout2.endToEnd;
            layout.horizontalBias = layout2.horizontalBias;
            layout.verticalBias = layout2.verticalBias;
            layout.dimensionRatio = layout2.dimensionRatio;
            layout.circleConstraint = layout2.circleConstraint;
            layout.circleRadius = layout2.circleRadius;
            layout.circleAngle = layout2.circleAngle;
            layout.editorAbsoluteX = layout2.editorAbsoluteX;
            layout.editorAbsoluteY = layout2.editorAbsoluteY;
            layout.orientation = layout2.orientation;
            layout.leftMargin = layout2.leftMargin;
            layout.rightMargin = layout2.rightMargin;
            layout.topMargin = layout2.topMargin;
            layout.bottomMargin = layout2.bottomMargin;
            layout.endMargin = layout2.endMargin;
            layout.startMargin = layout2.startMargin;
            layout.baselineMargin = layout2.baselineMargin;
            layout.goneLeftMargin = layout2.goneLeftMargin;
            layout.goneTopMargin = layout2.goneTopMargin;
            layout.goneRightMargin = layout2.goneRightMargin;
            layout.goneBottomMargin = layout2.goneBottomMargin;
            layout.goneEndMargin = layout2.goneEndMargin;
            layout.goneStartMargin = layout2.goneStartMargin;
            layout.goneBaselineMargin = layout2.goneBaselineMargin;
            layout.verticalWeight = layout2.verticalWeight;
            layout.horizontalWeight = layout2.horizontalWeight;
            layout.horizontalChainStyle = layout2.horizontalChainStyle;
            layout.verticalChainStyle = layout2.verticalChainStyle;
            layout.widthDefault = layout2.widthDefault;
            layout.heightDefault = layout2.heightDefault;
            layout.widthMax = layout2.widthMax;
            layout.heightMax = layout2.heightMax;
            layout.widthMin = layout2.widthMin;
            layout.heightMin = layout2.heightMin;
            layout.widthPercent = layout2.widthPercent;
            layout.heightPercent = layout2.heightPercent;
            layout.mBarrierDirection = layout2.mBarrierDirection;
            layout.mBarrierMargin = layout2.mBarrierMargin;
            layout.mHelperType = layout2.mHelperType;
            layout.mConstraintTag = layout2.mConstraintTag;
            int[] iArr = layout2.mReferenceIds;
            if (iArr != null && layout2.mReferenceIdString == null) {
                layout.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            } else {
                layout.mReferenceIds = null;
            }
            layout.mReferenceIdString = layout2.mReferenceIdString;
            layout.constrainedWidth = layout2.constrainedWidth;
            layout.constrainedHeight = layout2.constrainedHeight;
            layout.mBarrierAllowsGoneWidgets = layout2.mBarrierAllowsGoneWidgets;
            layout.mWrapBehavior = layout2.mWrapBehavior;
            Motion motion = constraint.motion;
            Motion motion2 = this.motion;
            motion.mApply = motion2.mApply;
            motion.mAnimateRelativeTo = motion2.mAnimateRelativeTo;
            motion.mTransitionEasing = motion2.mTransitionEasing;
            motion.mPathMotionArc = motion2.mPathMotionArc;
            motion.mDrawPath = motion2.mDrawPath;
            motion.mPathRotate = motion2.mPathRotate;
            motion.mMotionStagger = motion2.mMotionStagger;
            motion.mPolarRelativeTo = motion2.mPolarRelativeTo;
            PropertySet propertySet = constraint.propertySet;
            PropertySet propertySet2 = this.propertySet;
            propertySet.mApply = propertySet2.mApply;
            propertySet.visibility = propertySet2.visibility;
            propertySet.alpha = propertySet2.alpha;
            propertySet.mProgress = propertySet2.mProgress;
            propertySet.mVisibilityMode = propertySet2.mVisibilityMode;
            Transform transform = constraint.transform;
            Transform transform2 = this.transform;
            transform.mApply = transform2.mApply;
            transform.rotation = transform2.rotation;
            transform.rotationX = transform2.rotationX;
            transform.rotationY = transform2.rotationY;
            transform.scaleX = transform2.scaleX;
            transform.scaleY = transform2.scaleY;
            transform.transformPivotX = transform2.transformPivotX;
            transform.transformPivotY = transform2.transformPivotY;
            transform.transformPivotTarget = transform2.transformPivotTarget;
            transform.translationX = transform2.translationX;
            transform.translationY = transform2.translationY;
            transform.translationZ = transform2.translationZ;
            transform.applyElevation = transform2.applyElevation;
            transform.elevation = transform2.elevation;
            constraint.mViewId = this.mViewId;
            constraint.mDelta = this.mDelta;
            return constraint;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Delta {
            int[] mTypeInt = new int[10];
            int[] mValueInt = new int[10];
            int mCountInt = 0;
            int[] mTypeFloat = new int[10];
            float[] mValueFloat = new float[10];
            int mCountFloat = 0;
            int[] mTypeString = new int[5];
            String[] mValueString = new String[5];
            int mCountString = 0;
            int[] mTypeBoolean = new int[4];
            boolean[] mValueBoolean = new boolean[4];
            int mCountBoolean = 0;

            final void add(int i, float f) {
                int i2 = this.mCountFloat;
                int[] iArr = this.mTypeFloat;
                int length = iArr.length;
                if (i2 >= length) {
                    this.mTypeFloat = Arrays.copyOf(iArr, length + length);
                    float[] fArr = this.mValueFloat;
                    int length2 = fArr.length;
                    this.mValueFloat = Arrays.copyOf(fArr, length2 + length2);
                }
                int[] iArr2 = this.mTypeFloat;
                int i3 = this.mCountFloat;
                iArr2[i3] = i;
                float[] fArr2 = this.mValueFloat;
                this.mCountFloat = i3 + 1;
                fArr2[i3] = f;
            }

            final void add(int i, int i2) {
                int i3 = this.mCountInt;
                int[] iArr = this.mTypeInt;
                int length = iArr.length;
                if (i3 >= length) {
                    this.mTypeInt = Arrays.copyOf(iArr, length + length);
                    int[] iArr2 = this.mValueInt;
                    int length2 = iArr2.length;
                    this.mValueInt = Arrays.copyOf(iArr2, length2 + length2);
                }
                int[] iArr3 = this.mTypeInt;
                int i4 = this.mCountInt;
                iArr3[i4] = i;
                int[] iArr4 = this.mValueInt;
                this.mCountInt = i4 + 1;
                iArr4[i4] = i2;
            }

            final void add(int i, String str) {
                int i2 = this.mCountString;
                int[] iArr = this.mTypeString;
                int length = iArr.length;
                if (i2 >= length) {
                    this.mTypeString = Arrays.copyOf(iArr, length + length);
                    String[] strArr = this.mValueString;
                    int length2 = strArr.length;
                    this.mValueString = (String[]) Arrays.copyOf(strArr, length2 + length2);
                }
                int[] iArr2 = this.mTypeString;
                int i3 = this.mCountString;
                iArr2[i3] = i;
                String[] strArr2 = this.mValueString;
                this.mCountString = i3 + 1;
                strArr2[i3] = str;
            }

            final void add(int i, boolean z) {
                int i2 = this.mCountBoolean;
                int[] iArr = this.mTypeBoolean;
                int length = iArr.length;
                if (i2 >= length) {
                    this.mTypeBoolean = Arrays.copyOf(iArr, length + length);
                    boolean[] zArr = this.mValueBoolean;
                    int length2 = zArr.length;
                    this.mValueBoolean = Arrays.copyOf(zArr, length2 + length2);
                }
                int[] iArr2 = this.mTypeBoolean;
                int i3 = this.mCountBoolean;
                iArr2[i3] = i;
                boolean[] zArr2 = this.mValueBoolean;
                this.mCountBoolean = i3 + 1;
                zArr2[i3] = z;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:47:0x0079. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:52:0x00e8. Please report as an issue. */
    public final void load(Context context, XmlPullParser xmlPullParser) {
        char c;
        Object obj;
        char c2;
        try {
            int eventType = xmlPullParser.getEventType();
            Constraint constraint = null;
            while (eventType != 1) {
                if (eventType != 0) {
                    int i = 0;
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        switch (name.hashCode()) {
                            case -2025855158:
                                if (name.equals("Layout")) {
                                    c = 6;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -1984451626:
                                if (name.equals("Motion")) {
                                    c = 7;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -1962203927:
                                if (name.equals("ConstraintOverride")) {
                                    c = 1;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -1269513683:
                                if (name.equals("PropertySet")) {
                                    c = 4;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -1238332596:
                                if (name.equals("Transform")) {
                                    c = 5;
                                    break;
                                }
                                c = 65535;
                                break;
                            case -71750448:
                                if (name.equals("Guideline")) {
                                    c = 2;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 366511058:
                                if (name.equals("CustomMethod")) {
                                    c = '\t';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1331510167:
                                if (name.equals("Barrier")) {
                                    c = 3;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1791837707:
                                if (name.equals("CustomAttribute")) {
                                    c = '\b';
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1803088381:
                                if (name.equals("Constraint")) {
                                    c = 0;
                                    break;
                                }
                                c = 65535;
                                break;
                            default:
                                c = 65535;
                                break;
                        }
                        switch (c) {
                            case 0:
                                constraint = fillFromAttributeList$ar$ds(context, Xml.asAttributeSet(xmlPullParser), false);
                                break;
                            case 1:
                                constraint = fillFromAttributeList$ar$ds(context, Xml.asAttributeSet(xmlPullParser), true);
                                break;
                            case 2:
                                constraint = fillFromAttributeList$ar$ds(context, Xml.asAttributeSet(xmlPullParser), false);
                                Layout layout = constraint.layout;
                                layout.mIsGuideline = true;
                                layout.mApply = true;
                                break;
                            case 3:
                                constraint = fillFromAttributeList$ar$ds(context, Xml.asAttributeSet(xmlPullParser), false);
                                constraint.layout.mHelperType = 1;
                                break;
                            case 4:
                                if (constraint != null) {
                                    PropertySet propertySet = constraint.propertySet;
                                    TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.PropertySet);
                                    propertySet.mApply = true;
                                    int indexCount = obtainStyledAttributes.getIndexCount();
                                    for (int i2 = 0; i2 < indexCount; i2++) {
                                        int index = obtainStyledAttributes.getIndex(i2);
                                        if (index == 1) {
                                            propertySet.alpha = obtainStyledAttributes.getFloat(1, propertySet.alpha);
                                        } else if (index == 0) {
                                            propertySet.visibility = obtainStyledAttributes.getInt(0, propertySet.visibility);
                                            propertySet.visibility = VISIBILITY_FLAGS[propertySet.visibility];
                                        } else {
                                            if (index == 4) {
                                                propertySet.mVisibilityMode = obtainStyledAttributes.getInt(4, propertySet.mVisibilityMode);
                                            } else if (index == 3) {
                                                propertySet.mProgress = obtainStyledAttributes.getFloat(3, propertySet.mProgress);
                                            }
                                        }
                                    }
                                    obtainStyledAttributes.recycle();
                                    break;
                                } else {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                                }
                            case 5:
                                if (constraint != null) {
                                    Transform transform = constraint.transform;
                                    TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Transform);
                                    transform.mApply = true;
                                    int indexCount2 = obtainStyledAttributes2.getIndexCount();
                                    for (int i3 = 0; i3 < indexCount2; i3++) {
                                        int index2 = obtainStyledAttributes2.getIndex(i3);
                                        switch (Transform.sMapToConstant.get(index2)) {
                                            case 1:
                                                transform.rotation = obtainStyledAttributes2.getFloat(index2, transform.rotation);
                                                break;
                                            case 2:
                                                transform.rotationX = obtainStyledAttributes2.getFloat(index2, transform.rotationX);
                                                break;
                                            case 3:
                                                transform.rotationY = obtainStyledAttributes2.getFloat(index2, transform.rotationY);
                                                break;
                                            case 4:
                                                transform.scaleX = obtainStyledAttributes2.getFloat(index2, transform.scaleX);
                                                break;
                                            case 5:
                                                transform.scaleY = obtainStyledAttributes2.getFloat(index2, transform.scaleY);
                                                break;
                                            case 6:
                                                transform.transformPivotX = obtainStyledAttributes2.getDimension(index2, transform.transformPivotX);
                                                break;
                                            case 7:
                                                transform.transformPivotY = obtainStyledAttributes2.getDimension(index2, transform.transformPivotY);
                                                break;
                                            case 8:
                                                transform.translationX = obtainStyledAttributes2.getDimension(index2, transform.translationX);
                                                break;
                                            case 9:
                                                transform.translationY = obtainStyledAttributes2.getDimension(index2, transform.translationY);
                                                break;
                                            case 10:
                                                transform.translationZ = obtainStyledAttributes2.getDimension(index2, transform.translationZ);
                                                break;
                                            case 11:
                                                transform.applyElevation = true;
                                                transform.elevation = obtainStyledAttributes2.getDimension(index2, transform.elevation);
                                                break;
                                            case 12:
                                                transform.transformPivotTarget = lookupID(obtainStyledAttributes2, index2, transform.transformPivotTarget);
                                                break;
                                        }
                                    }
                                    obtainStyledAttributes2.recycle();
                                    break;
                                } else {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                                }
                            case 6:
                                if (constraint != null) {
                                    Layout layout2 = constraint.layout;
                                    TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Layout);
                                    layout2.mApply = true;
                                    int indexCount3 = obtainStyledAttributes3.getIndexCount();
                                    for (int i4 = 0; i4 < indexCount3; i4++) {
                                        int index3 = obtainStyledAttributes3.getIndex(i4);
                                        int i5 = Layout.sMapToConstant.get(index3);
                                        switch (i5) {
                                            case 1:
                                                layout2.baselineToBaseline = lookupID(obtainStyledAttributes3, index3, layout2.baselineToBaseline);
                                                break;
                                            case 2:
                                                layout2.bottomMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.bottomMargin);
                                                break;
                                            case 3:
                                                layout2.bottomToBottom = lookupID(obtainStyledAttributes3, index3, layout2.bottomToBottom);
                                                break;
                                            case 4:
                                                layout2.bottomToTop = lookupID(obtainStyledAttributes3, index3, layout2.bottomToTop);
                                                break;
                                            case 5:
                                                layout2.dimensionRatio = obtainStyledAttributes3.getString(index3);
                                                break;
                                            case 6:
                                                layout2.editorAbsoluteX = obtainStyledAttributes3.getDimensionPixelOffset(index3, layout2.editorAbsoluteX);
                                                break;
                                            case 7:
                                                layout2.editorAbsoluteY = obtainStyledAttributes3.getDimensionPixelOffset(index3, layout2.editorAbsoluteY);
                                                break;
                                            case 8:
                                                layout2.endMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.endMargin);
                                                break;
                                            case 9:
                                                layout2.endToEnd = lookupID(obtainStyledAttributes3, index3, layout2.endToEnd);
                                                break;
                                            case 10:
                                                layout2.endToStart = lookupID(obtainStyledAttributes3, index3, layout2.endToStart);
                                                break;
                                            case 11:
                                                layout2.goneBottomMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.goneBottomMargin);
                                                break;
                                            case 12:
                                                layout2.goneEndMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.goneEndMargin);
                                                break;
                                            case 13:
                                                layout2.goneLeftMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.goneLeftMargin);
                                                break;
                                            case 14:
                                                layout2.goneRightMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.goneRightMargin);
                                                break;
                                            case 15:
                                                layout2.goneStartMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.goneStartMargin);
                                                break;
                                            case 16:
                                                layout2.goneTopMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.goneTopMargin);
                                                break;
                                            case 17:
                                                layout2.guideBegin = obtainStyledAttributes3.getDimensionPixelOffset(index3, layout2.guideBegin);
                                                break;
                                            case 18:
                                                layout2.guideEnd = obtainStyledAttributes3.getDimensionPixelOffset(index3, layout2.guideEnd);
                                                break;
                                            case 19:
                                                layout2.guidePercent = obtainStyledAttributes3.getFloat(index3, layout2.guidePercent);
                                                break;
                                            case 20:
                                                layout2.horizontalBias = obtainStyledAttributes3.getFloat(index3, layout2.horizontalBias);
                                                break;
                                            case 21:
                                                layout2.mHeight = obtainStyledAttributes3.getLayoutDimension(index3, layout2.mHeight);
                                                break;
                                            case 22:
                                                layout2.mWidth = obtainStyledAttributes3.getLayoutDimension(index3, layout2.mWidth);
                                                break;
                                            case 23:
                                                layout2.leftMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.leftMargin);
                                                break;
                                            case 24:
                                                layout2.leftToLeft = lookupID(obtainStyledAttributes3, index3, layout2.leftToLeft);
                                                break;
                                            case 25:
                                                layout2.leftToRight = lookupID(obtainStyledAttributes3, index3, layout2.leftToRight);
                                                break;
                                            case 26:
                                                layout2.orientation = obtainStyledAttributes3.getInt(index3, layout2.orientation);
                                                break;
                                            case 27:
                                                layout2.rightMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.rightMargin);
                                                break;
                                            case 28:
                                                layout2.rightToLeft = lookupID(obtainStyledAttributes3, index3, layout2.rightToLeft);
                                                break;
                                            case 29:
                                                layout2.rightToRight = lookupID(obtainStyledAttributes3, index3, layout2.rightToRight);
                                                break;
                                            case 30:
                                                layout2.startMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.startMargin);
                                                break;
                                            case 31:
                                                layout2.startToEnd = lookupID(obtainStyledAttributes3, index3, layout2.startToEnd);
                                                break;
                                            case 32:
                                                layout2.startToStart = lookupID(obtainStyledAttributes3, index3, layout2.startToStart);
                                                break;
                                            case 33:
                                                layout2.topMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.topMargin);
                                                break;
                                            case 34:
                                                layout2.topToBottom = lookupID(obtainStyledAttributes3, index3, layout2.topToBottom);
                                                break;
                                            case 35:
                                                layout2.topToTop = lookupID(obtainStyledAttributes3, index3, layout2.topToTop);
                                                break;
                                            case 36:
                                                layout2.verticalBias = obtainStyledAttributes3.getFloat(index3, layout2.verticalBias);
                                                break;
                                            case 37:
                                                layout2.horizontalWeight = obtainStyledAttributes3.getFloat(index3, layout2.horizontalWeight);
                                                break;
                                            case 38:
                                                layout2.verticalWeight = obtainStyledAttributes3.getFloat(index3, layout2.verticalWeight);
                                                break;
                                            case 39:
                                                layout2.horizontalChainStyle = obtainStyledAttributes3.getInt(index3, layout2.horizontalChainStyle);
                                                break;
                                            case 40:
                                                layout2.verticalChainStyle = obtainStyledAttributes3.getInt(index3, layout2.verticalChainStyle);
                                                break;
                                            case 41:
                                                parseDimensionConstraints(layout2, obtainStyledAttributes3, index3, 0);
                                                break;
                                            case 42:
                                                parseDimensionConstraints(layout2, obtainStyledAttributes3, index3, 1);
                                                break;
                                            default:
                                                switch (i5) {
                                                    case 61:
                                                        layout2.circleConstraint = lookupID(obtainStyledAttributes3, index3, layout2.circleConstraint);
                                                        break;
                                                    case 62:
                                                        layout2.circleRadius = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.circleRadius);
                                                        break;
                                                    case 63:
                                                        layout2.circleAngle = obtainStyledAttributes3.getFloat(index3, layout2.circleAngle);
                                                        break;
                                                    default:
                                                        switch (i5) {
                                                            case 69:
                                                                layout2.widthPercent = obtainStyledAttributes3.getFloat(index3, 1.0f);
                                                                break;
                                                            case 70:
                                                                layout2.heightPercent = obtainStyledAttributes3.getFloat(index3, 1.0f);
                                                                break;
                                                            case 71:
                                                                Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                                break;
                                                            case 72:
                                                                layout2.mBarrierDirection = obtainStyledAttributes3.getInt(index3, layout2.mBarrierDirection);
                                                                break;
                                                            case 73:
                                                                layout2.mBarrierMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.mBarrierMargin);
                                                                break;
                                                            case 74:
                                                                layout2.mReferenceIdString = obtainStyledAttributes3.getString(index3);
                                                                break;
                                                            case 75:
                                                                layout2.mBarrierAllowsGoneWidgets = obtainStyledAttributes3.getBoolean(index3, layout2.mBarrierAllowsGoneWidgets);
                                                                break;
                                                            case 76:
                                                                layout2.mWrapBehavior = obtainStyledAttributes3.getInt(index3, layout2.mWrapBehavior);
                                                                break;
                                                            case 77:
                                                                layout2.baselineToTop = lookupID(obtainStyledAttributes3, index3, layout2.baselineToTop);
                                                                break;
                                                            case 78:
                                                                layout2.baselineToBottom = lookupID(obtainStyledAttributes3, index3, layout2.baselineToBottom);
                                                                break;
                                                            case 79:
                                                                layout2.goneBaselineMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.goneBaselineMargin);
                                                                break;
                                                            case 80:
                                                                layout2.baselineMargin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.baselineMargin);
                                                                break;
                                                            case 81:
                                                                layout2.widthDefault = obtainStyledAttributes3.getInt(index3, layout2.widthDefault);
                                                                break;
                                                            case 82:
                                                                layout2.heightDefault = obtainStyledAttributes3.getInt(index3, layout2.heightDefault);
                                                                break;
                                                            case 83:
                                                                layout2.heightMax = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.heightMax);
                                                                break;
                                                            case 84:
                                                                layout2.widthMax = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.widthMax);
                                                                break;
                                                            case 85:
                                                                layout2.heightMin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.heightMin);
                                                                break;
                                                            case 86:
                                                                layout2.widthMin = obtainStyledAttributes3.getDimensionPixelSize(index3, layout2.widthMin);
                                                                break;
                                                            case 87:
                                                                layout2.constrainedWidth = obtainStyledAttributes3.getBoolean(index3, layout2.constrainedWidth);
                                                                break;
                                                            case 88:
                                                                layout2.constrainedHeight = obtainStyledAttributes3.getBoolean(index3, layout2.constrainedHeight);
                                                                break;
                                                            case 89:
                                                                layout2.mConstraintTag = obtainStyledAttributes3.getString(index3);
                                                                break;
                                                            case 90:
                                                                layout2.guidelineUseRtl = obtainStyledAttributes3.getBoolean(index3, layout2.guidelineUseRtl);
                                                                break;
                                                            case 91:
                                                                Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index3, "unused attribute 0x", Layout.sMapToConstant));
                                                                break;
                                                            default:
                                                                Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index3, "Unknown attribute 0x", Layout.sMapToConstant));
                                                                break;
                                                        }
                                                }
                                        }
                                    }
                                    obtainStyledAttributes3.recycle();
                                    break;
                                } else {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                                }
                            case 7:
                                if (constraint != null) {
                                    Motion motion = constraint.motion;
                                    TypedArray obtainStyledAttributes4 = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Motion);
                                    motion.mApply = true;
                                    int indexCount4 = obtainStyledAttributes4.getIndexCount();
                                    int i6 = 0;
                                    while (i6 < indexCount4) {
                                        int index4 = obtainStyledAttributes4.getIndex(i6);
                                        switch (Motion.sMapToConstant.get(index4)) {
                                            case 1:
                                                motion.mPathRotate = obtainStyledAttributes4.getFloat(index4, motion.mPathRotate);
                                                break;
                                            case 2:
                                                motion.mPathMotionArc = obtainStyledAttributes4.getInt(index4, motion.mPathMotionArc);
                                                break;
                                            case 3:
                                                if (obtainStyledAttributes4.peekValue(index4).type == 3) {
                                                    motion.mTransitionEasing = obtainStyledAttributes4.getString(index4);
                                                    break;
                                                } else {
                                                    motion.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes4.getInteger(index4, 0)];
                                                    break;
                                                }
                                            case 4:
                                                motion.mDrawPath = obtainStyledAttributes4.getInt(index4, i);
                                                break;
                                            case 5:
                                                motion.mAnimateRelativeTo = lookupID(obtainStyledAttributes4, index4, motion.mAnimateRelativeTo);
                                                break;
                                            case 6:
                                                motion.mAnimateCircleAngleTo = obtainStyledAttributes4.getInteger(index4, motion.mAnimateCircleAngleTo);
                                                break;
                                            case 7:
                                                motion.mMotionStagger = obtainStyledAttributes4.getFloat(index4, motion.mMotionStagger);
                                                break;
                                            case 8:
                                                motion.mQuantizeMotionSteps = obtainStyledAttributes4.getInteger(index4, motion.mQuantizeMotionSteps);
                                                break;
                                            case 9:
                                                motion.mQuantizeMotionPhase = obtainStyledAttributes4.getFloat(index4, motion.mQuantizeMotionPhase);
                                                break;
                                            case 10:
                                                TypedValue peekValue = obtainStyledAttributes4.peekValue(index4);
                                                if (peekValue.type == 1) {
                                                    int resourceId = obtainStyledAttributes4.getResourceId(index4, -1);
                                                    motion.mQuantizeInterpolatorID = resourceId;
                                                    if (resourceId != -1) {
                                                        motion.mQuantizeInterpolatorType = -2;
                                                        break;
                                                    } else {
                                                        break;
                                                    }
                                                } else if (peekValue.type == 3) {
                                                    motion.mQuantizeInterpolatorString = obtainStyledAttributes4.getString(index4);
                                                    if (motion.mQuantizeInterpolatorString.indexOf("/") > 0) {
                                                        motion.mQuantizeInterpolatorID = obtainStyledAttributes4.getResourceId(index4, -1);
                                                        motion.mQuantizeInterpolatorType = -2;
                                                        break;
                                                    } else {
                                                        motion.mQuantizeInterpolatorType = -1;
                                                        break;
                                                    }
                                                } else {
                                                    motion.mQuantizeInterpolatorType = obtainStyledAttributes4.getInteger(index4, motion.mQuantizeInterpolatorID);
                                                    break;
                                                }
                                        }
                                        i6++;
                                        i = 0;
                                    }
                                    obtainStyledAttributes4.recycle();
                                    break;
                                } else {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                                }
                            case '\b':
                            case '\t':
                                if (constraint != null) {
                                    HashMap hashMap = constraint.mCustomConstraints;
                                    TypedArray obtainStyledAttributes5 = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.CustomAttribute);
                                    int indexCount5 = obtainStyledAttributes5.getIndexCount();
                                    int i7 = 0;
                                    boolean z = false;
                                    String str = null;
                                    Object obj2 = null;
                                    for (int i8 = 0; i8 < indexCount5; i8++) {
                                        int index5 = obtainStyledAttributes5.getIndex(i8);
                                        if (index5 == 0) {
                                            String string = obtainStyledAttributes5.getString(0);
                                            str = (string == null || string.length() <= 0) ? string : Character.toUpperCase(string.charAt(0)) + string.substring(1);
                                        } else if (index5 == 10) {
                                            str = obtainStyledAttributes5.getString(10);
                                            z = true;
                                        } else {
                                            if (index5 == 1) {
                                                obj2 = Boolean.valueOf(obtainStyledAttributes5.getBoolean(1, false));
                                                i7 = 6;
                                            } else if (index5 == 3) {
                                                obj2 = Integer.valueOf(obtainStyledAttributes5.getColor(3, 0));
                                                i7 = 3;
                                            } else if (index5 == 2) {
                                                obj2 = Integer.valueOf(obtainStyledAttributes5.getColor(2, 0));
                                                i7 = 4;
                                            } else if (index5 == 7) {
                                                obj2 = Float.valueOf(TypedValue.applyDimension(1, obtainStyledAttributes5.getDimension(7, 0.0f), context.getResources().getDisplayMetrics()));
                                                i7 = 7;
                                            } else if (index5 == 4) {
                                                obj2 = Float.valueOf(obtainStyledAttributes5.getDimension(4, 0.0f));
                                                i7 = 7;
                                            } else if (index5 == 5) {
                                                obj2 = Float.valueOf(obtainStyledAttributes5.getFloat(5, Float.NaN));
                                                i7 = 2;
                                            } else if (index5 == 6) {
                                                obj2 = Integer.valueOf(obtainStyledAttributes5.getInteger(6, -1));
                                                i7 = 1;
                                            } else if (index5 == 9) {
                                                obj2 = obtainStyledAttributes5.getString(9);
                                                i7 = 5;
                                            } else if (index5 == 8) {
                                                int resourceId2 = obtainStyledAttributes5.getResourceId(8, -1);
                                                if (resourceId2 == -1) {
                                                    resourceId2 = obtainStyledAttributes5.getInt(8, -1);
                                                }
                                                obj2 = Integer.valueOf(resourceId2);
                                                i7 = 8;
                                            }
                                        }
                                    }
                                    String str2 = str;
                                    if (str2 != null && (obj = obj2) != null) {
                                        hashMap.put(str2, new ConstraintAttribute(str2, i7, obj, z));
                                    }
                                    obtainStyledAttributes5.recycle();
                                    break;
                                } else {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                                }
                                break;
                        }
                    } else if (eventType == 3) {
                        String lowerCase = xmlPullParser.getName().toLowerCase(Locale.ROOT);
                        switch (lowerCase.hashCode()) {
                            case -2075718416:
                                if (lowerCase.equals("guideline")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case -190376483:
                                if (lowerCase.equals("constraint")) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case 426575017:
                                if (lowerCase.equals("constraintoverride")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case 2146106725:
                                if (lowerCase.equals("constraintset")) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                        }
                        c2 = 65535;
                        if (c2 == 0) {
                        }
                        if (c2 == 1 || c2 == 2 || c2 == 3) {
                            try {
                                this.mConstraints.put(Integer.valueOf(constraint.mViewId), constraint);
                                constraint = null;
                            } catch (IOException e) {
                                e = e;
                                Log.e("ConstraintSet", "Error parsing XML resource", e);
                                return;
                            } catch (XmlPullParserException e2) {
                                e = e2;
                                Log.e("ConstraintSet", "Error parsing XML resource", e);
                                return;
                            }
                        }
                    }
                } else {
                    xmlPullParser.getName();
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e3) {
            e = e3;
        } catch (XmlPullParserException e4) {
            e = e4;
        }
    }
}
