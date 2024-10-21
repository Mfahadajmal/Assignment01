package com.google.android.accessibility.braille.brailledisplay.platform;

import android.content.Context;
import android.os.PowerManager;
import com.google.android.accessibility.braille.brailledisplay.controller.BdController;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManagerProxy;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brltty.BrailleInputEventIA;
import com.google.android.accessibility.braille.common.BrailleUserPreferences$$ExternalSyntheticLambda2;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleDisplayManager {
    public boolean connectedService;
    public boolean connectedToDisplay;
    public final Connectioneer connectioneer;
    public final Context context;
    public final BdController controller$ar$class_merging;
    public final Displayer displayer;
    private final RetryingNameResolver.ResolutionResultListener displayerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final PowerManager.WakeLock wakeLock;
    public final Connectioneer.AspectConnection.Callback connectionCallback = new BrailleDisplaySettingsFragment.AnonymousClass4(this, 1);
    public final FloatingActionButton.ShadowDelegateImpl trafficCallback$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AccessibilityServiceContextProvider {
        Context getAccessibilityServiceContext();
    }

    public BrailleDisplayManager(Context context, BdController bdController, BrailleInputEventIA brailleInputEventIA) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(this);
        this.displayerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.context = context;
        this.controller$ar$class_merging = bdController;
        this.wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435482, "BrailleDisplayManager");
        this.displayer = new Displayer(context, resolutionResultListener);
        this.connectioneer = Connectioneer.getInstance$ar$class_merging$67dd8c5b_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(new WindowTrackerFactory(context.getApplicationContext(), new BrailleUserPreferences$$ExternalSyntheticLambda2(1)));
    }

    public final boolean canSendPackets() {
        if (this.connectedService && this.connectedToDisplay) {
            return true;
        }
        return false;
    }

    public final boolean canSendRenderPackets() {
        Displayer displayer;
        if (this.connectedService && this.connectedToDisplay && (displayer = this.displayer) != null && displayer.isDisplayReady()) {
            return true;
        }
        return false;
    }

    public final void setAccessibilityServiceContextProvider(AccessibilityServiceContextProvider accessibilityServiceContextProvider) {
        ConnectManagerProxy connectManagerProxy = this.connectioneer.connectManagerProxy;
        connectManagerProxy.btConnectManager.accessibilityServiceContextProvider = accessibilityServiceContextProvider;
        connectManagerProxy.usbConnectManager.accessibilityServiceContextProvider = accessibilityServiceContextProvider;
    }
}
