package com.google.android.accessibility.talkback.compositor;

import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.function.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class EventFeedback$$ExternalSyntheticLambda0 implements Supplier {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ EventFeedback$$ExternalSyntheticLambda0(int i) {
        this.switching_field = i;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return new ImmutableMap.Builder();
                        }
                        return new SplitInstallSharedPreferences();
                    }
                    return new ImmutableSet.Builder();
                }
                return new ImmutableList.Builder();
            }
            return new ArrayList();
        }
        return "";
    }
}
