package com.google.android.accessibility.talkback;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.PowerManager;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeviceConfigurationMonitor {
    public final Verifier compositor$ar$class_merging$e4d5cfcc_0;
    public A11yAlertDialogWrapper dialog;
    public int lastFontWeightAdjustment;
    public int lastOrientation;
    public final List listeners = new ArrayList();
    public final PowerManager powerManager;

    public DeviceConfigurationMonitor(Verifier verifier, Context context) {
        int i;
        this.compositor$ar$class_merging$e4d5cfcc_0 = verifier;
        this.powerManager = (PowerManager) context.getSystemService("power");
        this.lastOrientation = context.getResources().getConfiguration().orientation;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            i = context.getResources().getConfiguration().fontWeightAdjustment;
            this.lastFontWeightAdjustment = i;
        }
    }
}
