package org.chromium.net;

import J.N;
import org.chromium.net.GURLUtils;
import org.jni_zero.JniStaticTestMocker;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GURLUtilsJni implements GURLUtils.Natives {
    public static final JniStaticTestMocker<GURLUtils.Natives> TEST_HOOKS = new JniStaticTestMocker() { // from class: org.chromium.net.GURLUtilsJni.1
    };
    private static GURLUtils.Natives testInstance;

    public static GURLUtils.Natives get() {
        return new GURLUtilsJni();
    }

    @Override // org.chromium.net.GURLUtils.Natives
    public String getOrigin(String str) {
        return N.MpCt7siL(str);
    }
}
