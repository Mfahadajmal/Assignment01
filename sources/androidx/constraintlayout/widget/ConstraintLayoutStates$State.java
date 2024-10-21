package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Xml;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintLayoutStates$State {
    int mConstraintID;
    ConstraintSet mConstraintSet;
    public int mId;
    public ArrayList mVariants = new ArrayList();

    public ConstraintLayoutStates$State(Context context, XmlPullParser xmlPullParser) {
        this.mConstraintID = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == 0) {
                this.mId = obtainStyledAttributes.getResourceId(0, this.mId);
            } else if (index == 1) {
                this.mConstraintID = obtainStyledAttributes.getResourceId(1, this.mConstraintID);
                String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                context.getResources().getResourceName(this.mConstraintID);
                if ("layout".equals(resourceTypeName)) {
                    ConstraintSet constraintSet = new ConstraintSet();
                    this.mConstraintSet = constraintSet;
                    constraintSet.clone(context, this.mConstraintID);
                }
            }
        }
        obtainStyledAttributes.recycle();
    }
}
