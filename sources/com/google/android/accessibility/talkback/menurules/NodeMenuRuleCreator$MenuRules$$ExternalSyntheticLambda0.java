package com.google.android.accessibility.talkback.menurules;

import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import j$.util.function.Predicate$CC;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NodeMenuRuleCreator$MenuRules$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ NodeMenuRuleCreator$MenuRules$$ExternalSyntheticLambda0(int i, int i2) {
        this.switching_field = i2;
        this.f$0 = i;
    }

    public final /* synthetic */ Predicate and(Predicate predicate) {
        if (this.switching_field != 0) {
            return Predicate$CC.$default$and(this, predicate);
        }
        return Predicate$CC.$default$and(this, predicate);
    }

    public final /* synthetic */ Predicate negate() {
        if (this.switching_field != 0) {
            return Predicate$CC.$default$negate(this);
        }
        return Predicate$CC.$default$negate(this);
    }

    public final /* synthetic */ Predicate or(Predicate predicate) {
        if (this.switching_field != 0) {
            return Predicate$CC.$default$or(this, predicate);
        }
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        if (this.switching_field != 0) {
            BrailleKeyBindingUtils.SupportedCommand supportedCommand = (BrailleKeyBindingUtils.SupportedCommand) obj;
            if (!supportedCommand.hasSpace()) {
                if (supportedCommand.getPressDot().toInt() == this.f$0) {
                    return true;
                }
            }
            return false;
        }
        if (this.f$0 == ((NodeMenuRuleCreator.MenuRules) obj).ruleId) {
            return true;
        }
        return false;
    }
}
