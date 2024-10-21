package com.google.android.accessibility.braille.brailledisplay.platform;

import android.util.Range;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import j$.util.function.Predicate$CC;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Connectioneer$AspectConnection$$ExternalSyntheticLambda7 implements Predicate {
    public final /* synthetic */ Object Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$0;
    public final /* synthetic */ Object Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ Connectioneer$AspectConnection$$ExternalSyntheticLambda7(Connectioneer.AspectConnection aspectConnection, String str, int i) {
        this.switching_field = i;
        this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$0 = aspectConnection;
        this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$1 = str;
    }

    public final /* synthetic */ Predicate and(Predicate predicate) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
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
                MultitouchHandler.PointerWithHistory pointerWithHistory = (MultitouchHandler.PointerWithHistory) obj;
                if (!((MultitouchHandler) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$1).isAccumulationMode) {
                    if (!((Range) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$0).contains((Range) Long.valueOf(pointerWithHistory.momentMadeInactive))) {
                        return false;
                    }
                }
                return true;
            }
            Object obj2 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$1;
            return ((Connectioneer.AspectConnection) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$0).m40xe5dfe334((String) obj2, (ConnectableDevice) obj);
        }
        Object obj3 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$1;
        return ((Connectioneer.AspectConnection) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$0).m39x95e7f418((String) obj3, (ConnectableDevice) obj);
    }

    public /* synthetic */ Connectioneer$AspectConnection$$ExternalSyntheticLambda7(MultitouchHandler multitouchHandler, Range range, int i) {
        this.switching_field = i;
        this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$1 = multitouchHandler;
        this.Connectioneer$AspectConnection$$ExternalSyntheticLambda7$ar$f$0 = range;
    }
}
