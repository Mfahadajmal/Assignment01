package com.google.android.accessibility.brailleime.input;

import android.content.Context;
import android.graphics.PointF;
import com.google.android.accessibility.braille.common.settings.AddLanguageActivity;
import com.google.android.accessibility.talkback.actor.LanguageActor;
import io.grpc.ServiceProviders$PriorityAccessor;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleInputPlanePhone$$ExternalSyntheticLambda1 implements Comparator {
    public final /* synthetic */ Object BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0;
    private final /* synthetic */ int switching_field;

    public BrailleInputPlanePhone$$ExternalSyntheticLambda1(ServiceProviders$PriorityAccessor serviceProviders$PriorityAccessor, int i) {
        this.switching_field = i;
        this.BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0 = serviceProviders$PriorityAccessor;
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [io.grpc.ServiceProviders$PriorityAccessor, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        PointF pointF;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        ?? r0 = this.BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0;
                        int priority = r0.getPriority(obj) - r0.getPriority(obj2);
                        if (priority != 0) {
                            return priority;
                        }
                        return obj.getClass().getName().compareTo(obj2.getClass().getName());
                    }
                    Context context = (Context) this.BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0;
                    return LanguageActor.getLocaleString(context, (Locale) obj).compareTo(LanguageActor.getLocaleString(context, (Locale) obj2));
                }
                PointF pointF2 = (PointF) obj;
                PointF pointF3 = (PointF) obj2;
                boolean z = ((BrailleInputPlaneTablet) this.BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0).isTableTopMode;
                if (true != z) {
                    pointF = pointF3;
                } else {
                    pointF = pointF2;
                }
                if (true == z) {
                    pointF2 = pointF3;
                }
                return Float.compare(pointF.x, pointF2.x);
            }
            return AddLanguageActivity.AddLanguageFragment.lambda$initPreferences$1((Collator) this.BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0, (Locale) obj, (Locale) obj2);
        }
        PointF pointF4 = (PointF) obj;
        PointF pointF5 = (PointF) obj2;
        BrailleInputPlanePhone brailleInputPlanePhone = (BrailleInputPlanePhone) this.BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0;
        if (brailleInputPlanePhone.orientation == 1) {
            if (brailleInputPlanePhone.isTableTopMode) {
                return Float.compare(pointF5.y, pointF4.y);
            }
            return Float.compare(pointF4.y, pointF5.y);
        }
        if (brailleInputPlanePhone.isTableTopMode) {
            return Float.compare(pointF4.x, pointF5.x);
        }
        return Float.compare(pointF5.x, pointF4.x);
    }

    public /* synthetic */ BrailleInputPlanePhone$$ExternalSyntheticLambda1(Object obj, int i) {
        this.switching_field = i;
        this.BrailleInputPlanePhone$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }
}
