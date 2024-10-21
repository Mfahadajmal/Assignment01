package com.google.android.gms.common.api.internal;

import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RegistrationMethods$Builder {
    public Feature[] features;
    public ListenerHolder holder;
    public int methodKey;
    public RemoteCall register;
    public RemoteCall unregister;
    private Runnable onConnectionSuspended = new BrailleIme$21$$ExternalSyntheticLambda1(3);
    public boolean shouldAutoResolveMissingFeatures = true;

    public final OptionalMethod build$ar$class_merging$8fd660b1_0$ar$class_merging$ar$class_merging() {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (this.register != null) {
            z = true;
        } else {
            z = false;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(z, "Must set register function");
        if (this.unregister != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(z2, "Must set unregister function");
        if (this.holder == null) {
            z3 = false;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(z3, "Must set holder");
        ListenerHolder.ListenerKey listenerKey = this.holder.listenerKey;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(listenerKey, "Key must not be null");
        return new OptionalMethod((Object) new RegisterListenerMethod(this, this.holder, this.features, this.shouldAutoResolveMissingFeatures, this.methodKey), (Object) new UnregisterListenerMethod(this, listenerKey), (Object) this.onConnectionSuspended, (byte[]) null);
    }
}
