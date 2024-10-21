package android.support.constraint;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.search.mdi.aratea.proto.FeatureName;
import java.io.IOException;
import java.util.HashMap;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintSet {
    private static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private static final SparseIntArray mapToConstant;
    public final HashMap mConstraints = new HashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Constraint {
        public int mHeight;
        int mViewId;
        public int mWidth;
        boolean mIsGuideline = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = -1;
        public int rightMargin = -1;
        public int topMargin = -1;
        public int bottomMargin = -1;
        public int endMargin = -1;
        public int startMargin = -1;
        public int visibility = 0;
        public int goneLeftMargin = -1;
        public int goneTopMargin = -1;
        public int goneRightMargin = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneStartMargin = -1;
        public float verticalWeight = 0.0f;
        public float horizontalWeight = 0.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public float alpha = 1.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = 0.0f;
        public float transformPivotY = 0.0f;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;
        public int widthDefault = -1;
        public int heightDefault = -1;
        public int widthMax = -1;
        public int heightMax = -1;
        public int widthMin = -1;
        public int heightMin = -1;

        public final void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            layoutParams.leftToLeft = this.leftToLeft;
            layoutParams.leftToRight = this.leftToRight;
            layoutParams.rightToLeft = this.rightToLeft;
            layoutParams.rightToRight = this.rightToRight;
            layoutParams.topToTop = this.topToTop;
            layoutParams.topToBottom = this.topToBottom;
            layoutParams.bottomToTop = this.bottomToTop;
            layoutParams.bottomToBottom = this.bottomToBottom;
            layoutParams.baselineToBaseline = this.baselineToBaseline;
            layoutParams.startToEnd = this.startToEnd;
            layoutParams.startToStart = this.startToStart;
            layoutParams.endToStart = this.endToStart;
            layoutParams.endToEnd = this.endToEnd;
            layoutParams.leftMargin = this.leftMargin;
            layoutParams.rightMargin = this.rightMargin;
            layoutParams.topMargin = this.topMargin;
            layoutParams.bottomMargin = this.bottomMargin;
            layoutParams.goneStartMargin = this.goneStartMargin;
            layoutParams.goneEndMargin = this.goneEndMargin;
            layoutParams.horizontalBias = this.horizontalBias;
            layoutParams.verticalBias = this.verticalBias;
            layoutParams.dimensionRatio = this.dimensionRatio;
            layoutParams.editorAbsoluteX = this.editorAbsoluteX;
            layoutParams.editorAbsoluteY = this.editorAbsoluteY;
            layoutParams.verticalWeight = this.verticalWeight;
            layoutParams.horizontalWeight = this.horizontalWeight;
            layoutParams.verticalChainStyle = this.verticalChainStyle;
            layoutParams.horizontalChainStyle = this.horizontalChainStyle;
            layoutParams.matchConstraintDefaultWidth = this.widthDefault;
            layoutParams.matchConstraintDefaultHeight = this.heightDefault;
            layoutParams.matchConstraintMaxWidth = this.widthMax;
            layoutParams.matchConstraintMaxHeight = this.heightMax;
            layoutParams.matchConstraintMinWidth = this.widthMin;
            layoutParams.matchConstraintMinHeight = this.heightMin;
            layoutParams.orientation = this.orientation;
            layoutParams.guidePercent = this.guidePercent;
            layoutParams.guideBegin = this.guideBegin;
            layoutParams.guideEnd = this.guideEnd;
            layoutParams.width = this.mWidth;
            layoutParams.height = this.mHeight;
            layoutParams.setMarginStart(this.startMargin);
            layoutParams.setMarginEnd(this.endMargin);
            layoutParams.validate();
        }

        public final /* bridge */ /* synthetic */ Object clone() {
            Constraint constraint = new Constraint();
            constraint.mIsGuideline = this.mIsGuideline;
            constraint.mWidth = this.mWidth;
            constraint.mHeight = this.mHeight;
            constraint.guideBegin = this.guideBegin;
            constraint.guideEnd = this.guideEnd;
            constraint.guidePercent = this.guidePercent;
            constraint.leftToLeft = this.leftToLeft;
            constraint.leftToRight = this.leftToRight;
            constraint.rightToLeft = this.rightToLeft;
            constraint.rightToRight = this.rightToRight;
            constraint.topToTop = this.topToTop;
            constraint.topToBottom = this.topToBottom;
            constraint.bottomToTop = this.bottomToTop;
            constraint.bottomToBottom = this.bottomToBottom;
            constraint.baselineToBaseline = this.baselineToBaseline;
            constraint.startToEnd = this.startToEnd;
            constraint.startToStart = this.startToStart;
            constraint.endToStart = this.endToStart;
            constraint.endToEnd = this.endToEnd;
            constraint.horizontalBias = this.horizontalBias;
            constraint.verticalBias = this.verticalBias;
            constraint.dimensionRatio = this.dimensionRatio;
            constraint.editorAbsoluteX = this.editorAbsoluteX;
            constraint.editorAbsoluteY = this.editorAbsoluteY;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.horizontalBias = this.horizontalBias;
            constraint.orientation = this.orientation;
            constraint.leftMargin = this.leftMargin;
            constraint.rightMargin = this.rightMargin;
            constraint.topMargin = this.topMargin;
            constraint.bottomMargin = this.bottomMargin;
            constraint.endMargin = this.endMargin;
            constraint.startMargin = this.startMargin;
            constraint.visibility = this.visibility;
            constraint.goneLeftMargin = this.goneLeftMargin;
            constraint.goneTopMargin = this.goneTopMargin;
            constraint.goneRightMargin = this.goneRightMargin;
            constraint.goneBottomMargin = this.goneBottomMargin;
            constraint.goneEndMargin = this.goneEndMargin;
            constraint.goneStartMargin = this.goneStartMargin;
            constraint.verticalWeight = this.verticalWeight;
            constraint.horizontalWeight = this.horizontalWeight;
            constraint.horizontalChainStyle = this.horizontalChainStyle;
            constraint.verticalChainStyle = this.verticalChainStyle;
            constraint.alpha = this.alpha;
            constraint.applyElevation = this.applyElevation;
            constraint.elevation = this.elevation;
            constraint.rotationX = this.rotationX;
            constraint.rotationY = this.rotationY;
            constraint.scaleX = this.scaleX;
            constraint.scaleY = this.scaleY;
            constraint.transformPivotX = this.transformPivotX;
            constraint.transformPivotY = this.transformPivotY;
            constraint.translationX = this.translationX;
            constraint.translationY = this.translationY;
            constraint.translationZ = this.translationZ;
            constraint.widthDefault = this.widthDefault;
            constraint.heightDefault = this.heightDefault;
            constraint.widthMax = this.widthMax;
            constraint.heightMax = this.heightMax;
            constraint.widthMin = this.widthMin;
            constraint.heightMin = this.heightMin;
            return constraint;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        mapToConstant = sparseIntArray;
        int[] iArr = R$styleable.ConstraintLayout_Layout;
        sparseIntArray.append(85, 25);
        sparseIntArray.append(86, 26);
        sparseIntArray.append(88, 29);
        sparseIntArray.append(89, 30);
        sparseIntArray.append(95, 36);
        sparseIntArray.append(94, 35);
        sparseIntArray.append(67, 4);
        sparseIntArray.append(66, 3);
        sparseIntArray.append(62, 1);
        sparseIntArray.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu, 6);
        sparseIntArray.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_KEY_EVENT$ar$edu, 7);
        sparseIntArray.append(74, 17);
        sparseIntArray.append(75, 18);
        sparseIntArray.append(76, 19);
        sparseIntArray.append(0, 27);
        sparseIntArray.append(90, 32);
        sparseIntArray.append(91, 33);
        sparseIntArray.append(73, 10);
        sparseIntArray.append(72, 9);
        sparseIntArray.append(108, 13);
        sparseIntArray.append(BrailleInputEvent.CMD_HEADING_PREVIOUS, 16);
        sparseIntArray.append(109, 14);
        sparseIntArray.append(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_MOTION_EVENT_SOURCE$ar$edu, 11);
        sparseIntArray.append(BrailleInputEvent.CMD_HEADING_NEXT, 15);
        sparseIntArray.append(107, 12);
        sparseIntArray.append(98, 40);
        sparseIntArray.append(83, 39);
        sparseIntArray.append(82, 41);
        sparseIntArray.append(97, 42);
        sparseIntArray.append(81, 20);
        sparseIntArray.append(96, 37);
        sparseIntArray.append(71, 5);
        sparseIntArray.append(84, 60);
        sparseIntArray.append(93, 60);
        sparseIntArray.append(87, 60);
        sparseIntArray.append(65, 60);
        sparseIntArray.append(61, 60);
        sparseIntArray.append(5, 24);
        sparseIntArray.append(7, 28);
        sparseIntArray.append(25, 31);
        sparseIntArray.append(26, 8);
        sparseIntArray.append(6, 34);
        sparseIntArray.append(8, 2);
        sparseIntArray.append(3, 23);
        sparseIntArray.append(4, 21);
        sparseIntArray.append(2, 22);
        sparseIntArray.append(15, 43);
        sparseIntArray.append(28, 44);
        sparseIntArray.append(23, 45);
        sparseIntArray.append(24, 46);
        sparseIntArray.append(20, 47);
        sparseIntArray.append(21, 48);
        sparseIntArray.append(16, 49);
        sparseIntArray.append(17, 50);
        sparseIntArray.append(18, 51);
        sparseIntArray.append(19, 52);
        sparseIntArray.append(27, 53);
        sparseIntArray.append(99, 54);
        sparseIntArray.append(77, 55);
        sparseIntArray.append(100, 56);
        sparseIntArray.append(78, 57);
        sparseIntArray.append(FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu, 58);
        sparseIntArray.append(79, 59);
        sparseIntArray.append(1, 38);
    }

    private static int lookupID(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        if (resourceId == -1) {
            return typedArray.getInt(i, -1);
        }
        return resourceId;
    }

    public final void load(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xml.getName();
                        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                        Constraint constraint = new Constraint();
                        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, R$styleable.ConstraintSet);
                        int indexCount = obtainStyledAttributes.getIndexCount();
                        for (int i2 = 0; i2 < indexCount; i2++) {
                            int index = obtainStyledAttributes.getIndex(i2);
                            SparseIntArray sparseIntArray = mapToConstant;
                            int i3 = sparseIntArray.get(index);
                            if (i3 != 60) {
                                switch (i3) {
                                    case 1:
                                        constraint.baselineToBaseline = lookupID(obtainStyledAttributes, index, constraint.baselineToBaseline);
                                        break;
                                    case 2:
                                        constraint.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.bottomMargin);
                                        break;
                                    case 3:
                                        constraint.bottomToBottom = lookupID(obtainStyledAttributes, index, constraint.bottomToBottom);
                                        break;
                                    case 4:
                                        constraint.bottomToTop = lookupID(obtainStyledAttributes, index, constraint.bottomToTop);
                                        break;
                                    case 5:
                                        constraint.dimensionRatio = obtainStyledAttributes.getString(index);
                                        break;
                                    case 6:
                                        constraint.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, constraint.editorAbsoluteX);
                                        break;
                                    case 7:
                                        constraint.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, constraint.editorAbsoluteY);
                                        break;
                                    case 8:
                                        constraint.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.endMargin);
                                        break;
                                    case 9:
                                        constraint.bottomToTop = lookupID(obtainStyledAttributes, index, constraint.endToEnd);
                                        break;
                                    case 10:
                                        constraint.endToStart = lookupID(obtainStyledAttributes, index, constraint.endToStart);
                                        break;
                                    case 11:
                                        constraint.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.goneBottomMargin);
                                        break;
                                    case 12:
                                        constraint.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.goneEndMargin);
                                        break;
                                    case 13:
                                        constraint.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.goneLeftMargin);
                                        break;
                                    case 14:
                                        constraint.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.goneRightMargin);
                                        break;
                                    case 15:
                                        constraint.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.goneStartMargin);
                                        break;
                                    case 16:
                                        constraint.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.goneTopMargin);
                                        break;
                                    case 17:
                                        constraint.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, constraint.guideBegin);
                                        break;
                                    case 18:
                                        constraint.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, constraint.guideEnd);
                                        break;
                                    case 19:
                                        constraint.guidePercent = obtainStyledAttributes.getFloat(index, constraint.guidePercent);
                                        break;
                                    case 20:
                                        constraint.horizontalBias = obtainStyledAttributes.getFloat(index, constraint.horizontalBias);
                                        break;
                                    case 21:
                                        constraint.mHeight = obtainStyledAttributes.getLayoutDimension(index, constraint.mHeight);
                                        break;
                                    case 22:
                                        int i4 = obtainStyledAttributes.getInt(index, constraint.visibility);
                                        constraint.visibility = i4;
                                        constraint.visibility = VISIBILITY_FLAGS[i4];
                                        break;
                                    case 23:
                                        constraint.mWidth = obtainStyledAttributes.getLayoutDimension(index, constraint.mWidth);
                                        break;
                                    case 24:
                                        constraint.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.leftMargin);
                                        break;
                                    case 25:
                                        constraint.leftToLeft = lookupID(obtainStyledAttributes, index, constraint.leftToLeft);
                                        break;
                                    case 26:
                                        constraint.leftToRight = lookupID(obtainStyledAttributes, index, constraint.leftToRight);
                                        break;
                                    case 27:
                                        constraint.orientation = obtainStyledAttributes.getInt(index, constraint.orientation);
                                        break;
                                    case 28:
                                        constraint.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.rightMargin);
                                        break;
                                    case 29:
                                        constraint.rightToLeft = lookupID(obtainStyledAttributes, index, constraint.rightToLeft);
                                        break;
                                    case 30:
                                        constraint.rightToRight = lookupID(obtainStyledAttributes, index, constraint.rightToRight);
                                        break;
                                    case 31:
                                        constraint.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.startMargin);
                                        break;
                                    case 32:
                                        constraint.startToEnd = lookupID(obtainStyledAttributes, index, constraint.startToEnd);
                                        break;
                                    case 33:
                                        constraint.startToStart = lookupID(obtainStyledAttributes, index, constraint.startToStart);
                                        break;
                                    case 34:
                                        constraint.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, constraint.topMargin);
                                        break;
                                    case 35:
                                        constraint.topToBottom = lookupID(obtainStyledAttributes, index, constraint.topToBottom);
                                        break;
                                    case 36:
                                        constraint.topToTop = lookupID(obtainStyledAttributes, index, constraint.topToTop);
                                        break;
                                    case 37:
                                        constraint.verticalBias = obtainStyledAttributes.getFloat(index, constraint.verticalBias);
                                        break;
                                    case 38:
                                        constraint.mViewId = obtainStyledAttributes.getResourceId(index, constraint.mViewId);
                                        break;
                                    case 39:
                                        constraint.horizontalWeight = obtainStyledAttributes.getFloat(index, constraint.horizontalWeight);
                                        break;
                                    case 40:
                                        constraint.verticalWeight = obtainStyledAttributes.getFloat(index, constraint.verticalWeight);
                                        break;
                                    case 41:
                                        constraint.horizontalChainStyle = obtainStyledAttributes.getInt(index, constraint.horizontalChainStyle);
                                        break;
                                    case 42:
                                        constraint.verticalChainStyle = obtainStyledAttributes.getInt(index, constraint.verticalChainStyle);
                                        break;
                                    case 43:
                                        constraint.alpha = obtainStyledAttributes.getFloat(index, constraint.alpha);
                                        break;
                                    case 44:
                                        constraint.applyElevation = true;
                                        constraint.elevation = obtainStyledAttributes.getFloat(index, constraint.elevation);
                                        break;
                                    case 45:
                                        constraint.rotationX = obtainStyledAttributes.getFloat(index, constraint.rotationX);
                                        break;
                                    case 46:
                                        constraint.rotationY = obtainStyledAttributes.getFloat(index, constraint.rotationY);
                                        break;
                                    case 47:
                                        constraint.scaleX = obtainStyledAttributes.getFloat(index, constraint.scaleX);
                                        break;
                                    case 48:
                                        constraint.scaleY = obtainStyledAttributes.getFloat(index, constraint.scaleY);
                                        break;
                                    case 49:
                                        constraint.transformPivotX = obtainStyledAttributes.getFloat(index, constraint.transformPivotX);
                                        break;
                                    case 50:
                                        constraint.transformPivotY = obtainStyledAttributes.getFloat(index, constraint.transformPivotY);
                                        break;
                                    case 51:
                                        constraint.translationX = obtainStyledAttributes.getFloat(index, constraint.translationX);
                                        break;
                                    case 52:
                                        constraint.translationY = obtainStyledAttributes.getFloat(index, constraint.translationY);
                                        break;
                                    case 53:
                                        constraint.translationZ = obtainStyledAttributes.getFloat(index, constraint.translationZ);
                                        break;
                                    default:
                                        Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index, "Unknown attribute 0x", sparseIntArray));
                                        break;
                                }
                            } else {
                                Log.w("ConstraintSet", _BOUNDARY._BOUNDARY$ar$MethodOutlining(index, "unused attribute 0x", sparseIntArray));
                            }
                        }
                        obtainStyledAttributes.recycle();
                        if (name.equalsIgnoreCase("Guideline")) {
                            constraint.mIsGuideline = true;
                        }
                        this.mConstraints.put(Integer.valueOf(constraint.mViewId), constraint);
                    }
                } else {
                    xml.getName();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }
}
