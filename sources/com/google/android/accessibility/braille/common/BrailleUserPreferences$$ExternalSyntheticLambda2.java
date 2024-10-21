package com.google.android.accessibility.braille.common;

import android.text.TextUtils;
import com.google.android.accessibility.braille.brltty.SupportedDevicesHelper;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import j$.util.function.Predicate$CC;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleUserPreferences$$ExternalSyntheticLambda2 implements Predicate {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BrailleUserPreferences$$ExternalSyntheticLambda2(int i) {
        this.switching_field = i;
    }

    public final /* synthetic */ Predicate and(Predicate predicate) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return Predicate$CC.$default$and(this, predicate);
                    }
                    return Predicate$CC.$default$and(this, predicate);
                }
                return Predicate$CC.$default$and(this, predicate);
            }
            return Predicate$CC.$default$and(this, predicate);
        }
        return Predicate$CC.$default$and(this, predicate);
    }

    public final /* synthetic */ Predicate negate() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return Predicate$CC.$default$negate(this);
                    }
                    return Predicate$CC.$default$negate(this);
                }
                return Predicate$CC.$default$negate(this);
            }
            return Predicate$CC.$default$negate(this);
        }
        return Predicate$CC.$default$negate(this);
    }

    public final /* synthetic */ Predicate or(Predicate predicate) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return Predicate$CC.$default$or(this, predicate);
                    }
                    return Predicate$CC.$default$or(this, predicate);
                }
                return Predicate$CC.$default$or(this, predicate);
            }
            return Predicate$CC.$default$or(this, predicate);
        }
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return TextUtils.equals((String) obj, "android.permission.POST_NOTIFICATIONS");
                    }
                    return TextUtils.equals((String) obj, "android.permission.POST_NOTIFICATIONS");
                }
                return ((MultitouchHandler.PointerWithHistory) obj).isHoldInProgress;
            }
            if (SupportedDevicesHelper.getDeviceInfo((String) obj, false) == null) {
                return false;
            }
            return true;
        }
        if (((BrailleLanguages.Code) obj).equals(BrailleLanguages.Code.STUB)) {
            return false;
        }
        return true;
    }
}
