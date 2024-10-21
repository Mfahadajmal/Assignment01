package androidx.constraintlayout.widget;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintAttribute {
    boolean mBooleanValue;
    public int mColorValue;
    public float mFloatValue;
    public int mIntegerValue;
    public final boolean mMethod;
    final String mName;
    public String mStringValue;
    public final int mType$ar$edu$527a520a_0;

    public ConstraintAttribute(ConstraintAttribute constraintAttribute, Object obj) {
        this.mMethod = false;
        this.mName = constraintAttribute.mName;
        this.mType$ar$edu$527a520a_0 = constraintAttribute.mType$ar$edu$527a520a_0;
        setValue(obj);
    }

    public final void setValue(Object obj) {
        int i = this.mType$ar$edu$527a520a_0;
        if (i != 0) {
            switch (i - 1) {
                case 0:
                case 7:
                    this.mIntegerValue = ((Integer) obj).intValue();
                    return;
                case 1:
                    this.mFloatValue = ((Float) obj).floatValue();
                    return;
                case 2:
                case 3:
                    this.mColorValue = ((Integer) obj).intValue();
                    return;
                case 4:
                    this.mStringValue = (String) obj;
                    return;
                case 5:
                    this.mBooleanValue = ((Boolean) obj).booleanValue();
                    return;
                case 6:
                    this.mFloatValue = ((Float) obj).floatValue();
                    return;
                default:
                    return;
            }
        }
        throw null;
    }

    public ConstraintAttribute(String str, int i, Object obj, boolean z) {
        this.mName = str;
        this.mType$ar$edu$527a520a_0 = i;
        this.mMethod = z;
        setValue(obj);
    }
}
