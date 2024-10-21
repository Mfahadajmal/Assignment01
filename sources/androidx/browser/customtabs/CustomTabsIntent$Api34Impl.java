package androidx.browser.customtabs;

import _COROUTINE._BOUNDARY;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayoutStates$State;
import androidx.constraintlayout.widget.ConstraintSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CustomTabsIntent$Api34Impl {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x002c. Please report as an issue. */
    public static final void load$ar$objectUnboxing(Context context, int i, SparseArray sparseArray, SparseArray sparseArray2) {
        char c;
        int i2;
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            ConstraintLayoutStates$State constraintLayoutStates$State = null;
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    int i3 = -1;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c != 2) {
                        if (c != 3) {
                            if (c == 4) {
                                ConstraintSet constraintSet = new ConstraintSet();
                                int attributeCount = xml.getAttributeCount();
                                for (int i4 = 0; i4 < attributeCount; i4++) {
                                    String attributeName = xml.getAttributeName(i4);
                                    String attributeValue = xml.getAttributeValue(i4);
                                    if (attributeName != null && attributeValue != null && "id".equals(attributeName)) {
                                        if (attributeValue.contains("/")) {
                                            i2 = context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName());
                                        } else {
                                            i2 = -1;
                                        }
                                        if (i2 == -1) {
                                            if (attributeValue.length() > 1) {
                                                i3 = Integer.parseInt(attributeValue.substring(1));
                                            } else {
                                                Log.e("ConstraintLayoutStates", "error in parsing id");
                                            }
                                        } else {
                                            i3 = i2;
                                        }
                                        constraintSet.load(context, xml);
                                        sparseArray2.put(i3, constraintSet);
                                    }
                                }
                            }
                        } else {
                            Object obj = new Object(context, xml) { // from class: androidx.constraintlayout.widget.ConstraintLayoutStates$Variant
                                int mConstraintID;
                                ConstraintSet mConstraintSet;
                                float mMaxHeight;
                                float mMaxWidth;
                                float mMinHeight;
                                float mMinWidth;

                                {
                                    this.mMinWidth = Float.NaN;
                                    this.mMinHeight = Float.NaN;
                                    this.mMaxWidth = Float.NaN;
                                    this.mMaxHeight = Float.NaN;
                                    this.mConstraintID = -1;
                                    TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xml), R$styleable.Variant);
                                    int indexCount = obtainStyledAttributes.getIndexCount();
                                    for (int i5 = 0; i5 < indexCount; i5++) {
                                        int index = obtainStyledAttributes.getIndex(i5);
                                        if (index == 0) {
                                            this.mConstraintID = obtainStyledAttributes.getResourceId(0, this.mConstraintID);
                                            String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                                            context.getResources().getResourceName(this.mConstraintID);
                                            if ("layout".equals(resourceTypeName)) {
                                                ConstraintSet constraintSet2 = new ConstraintSet();
                                                this.mConstraintSet = constraintSet2;
                                                constraintSet2.clone(context, this.mConstraintID);
                                            }
                                        } else if (index == 1) {
                                            this.mMaxHeight = obtainStyledAttributes.getDimension(1, this.mMaxHeight);
                                        } else if (index == 2) {
                                            this.mMinHeight = obtainStyledAttributes.getDimension(2, this.mMinHeight);
                                        } else if (index == 3) {
                                            this.mMaxWidth = obtainStyledAttributes.getDimension(3, this.mMaxWidth);
                                        } else if (index == 4) {
                                            this.mMinWidth = obtainStyledAttributes.getDimension(4, this.mMinWidth);
                                        }
                                    }
                                    obtainStyledAttributes.recycle();
                                }
                            };
                            if (constraintLayoutStates$State != null) {
                                constraintLayoutStates$State.mVariants.add(obj);
                            }
                        }
                    } else {
                        ConstraintLayoutStates$State constraintLayoutStates$State2 = new ConstraintLayoutStates$State(context, xml);
                        sparseArray.put(constraintLayoutStates$State2.mId, constraintLayoutStates$State2);
                        constraintLayoutStates$State = constraintLayoutStates$State2;
                    }
                }
            }
        } catch (IOException e) {
            Log.e("ConstraintLayoutStates", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Error parsing resource: "), e);
        } catch (XmlPullParserException e2) {
            Log.e("ConstraintLayoutStates", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Error parsing resource: "), e2);
        }
    }

    public static void setShareIdentityEnabled(ActivityOptions activityOptions, boolean z) {
        activityOptions.setShareIdentityEnabled(z);
    }
}
