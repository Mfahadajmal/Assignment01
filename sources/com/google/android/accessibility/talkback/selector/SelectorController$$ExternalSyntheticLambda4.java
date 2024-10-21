package com.google.android.accessibility.talkback.selector;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.brailleime.BrailleImeActions;
import com.google.android.accessibility.brailleime.settings.BrailleImeGestureCommandActivity;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.common.collect.ImmutableList;
import j$.util.function.Predicate$CC;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SelectorController$$ExternalSyntheticLambda4 implements Predicate {
    public final /* synthetic */ Object SelectorController$$ExternalSyntheticLambda4$ar$f$0;
    public final /* synthetic */ Object SelectorController$$ExternalSyntheticLambda4$ar$f$1;
    public final /* synthetic */ Object SelectorController$$ExternalSyntheticLambda4$ar$f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SelectorController$$ExternalSyntheticLambda4(Object obj, Object obj2, Object obj3, int i) {
        this.switching_field = i;
        this.SelectorController$$ExternalSyntheticLambda4$ar$f$0 = obj;
        this.SelectorController$$ExternalSyntheticLambda4$ar$f$1 = obj2;
        this.SelectorController$$ExternalSyntheticLambda4$ar$f$2 = obj3;
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

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        if (this.switching_field != 0) {
            Object obj2 = this.SelectorController$$ExternalSyntheticLambda4$ar$f$2;
            Object obj3 = this.SelectorController$$ExternalSyntheticLambda4$ar$f$1;
            return ((BrailleImeGestureCommandActivity.BrailleImeGestureCommandFragment) this.SelectorController$$ExternalSyntheticLambda4$ar$f$0).m73x8d3a20e7((BrailleImeActions.Category) obj3, (BrailleImeActions.SubCategory) obj2, (BrailleImeActions) obj);
        }
        SelectorController.ContextualSetting contextualSetting = (SelectorController.ContextualSetting) obj;
        ImmutableList immutableList = SelectorController.SELECTOR_SETTINGS;
        if (!this.SelectorController$$ExternalSyntheticLambda4$ar$f$0.contains(contextualSetting.getSetting())) {
            if (contextualSetting.shouldActivateSetting((Context) this.SelectorController$$ExternalSyntheticLambda4$ar$f$1, (AccessibilityNodeInfoCompat) this.SelectorController$$ExternalSyntheticLambda4$ar$f$2)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
