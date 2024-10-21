package com.google.android.accessibility.talkback.focusmanagement.record;

import android.content.pm.ResolveInfo;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfigReader;
import java.util.function.ToIntFunction;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NodePathDescription$$ExternalSyntheticLambda1 implements ToIntFunction {
    private final /* synthetic */ int switching_field;

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                int i2 = VendorConfigReader.VendorConfigReader$ar$NoOp;
                return ((ResolveInfo) obj).activityInfo.applicationInfo.flags & 1;
            }
            return ((Integer) obj).intValue();
        }
        return ((NodePathDescription.Match) obj).depth();
    }
}
